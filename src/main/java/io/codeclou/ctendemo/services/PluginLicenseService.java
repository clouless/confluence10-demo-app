package io.codeclou.ctendemo.services;

import com.atlassian.upm.api.license.HostLicenseInformation;
import com.atlassian.upm.api.license.PluginLicenseManager;
import com.atlassian.upm.api.license.entity.LicenseError;
import com.atlassian.upm.api.license.entity.PluginLicense;
import io.codeclou.ctendemo.services.model.LicenseInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// SPRING-BEAN
public class PluginLicenseService {

  private Logger logger = LoggerFactory.getLogger(PluginLicenseService.class);

  private static final String APP_NAME = "CTenDemo";

  // JIRA API
  private final PluginLicenseManager pluginLicenseManager;
  private final HostLicenseInformation hostLicenseInformation;


  public PluginLicenseService(PluginLicenseManager pluginLicenseManager,
                              // See: https://bitbucket.org/atlassian-marketplace/data-center-licensing-compatibility
                              com.atlassian.upm.api.license.HostLicenseInformation upmHostLicenseInformation) {
    this.pluginLicenseManager = pluginLicenseManager;
    this.hostLicenseInformation =  upmHostLicenseInformation;
  }


  public LicenseInformation detectLicense() {
    LicenseInformation licenseInformation = new LicenseInformation();

    //
    // HOST LICENSE (Confluence itself)
    //
    if (hostLicenseInformation.getEdition().isDefined()) {
      licenseInformation.setDetailHostLicenseEdition(hostLicenseInformation.getEdition().get().toString());
    } else {
      licenseInformation.setDetailHostLicenseEdition("unlimited");
    }
    licenseInformation.setDetailAppLicenseIsDataCenter(hostLicenseInformation.isDataCenter());
    licenseInformation.setDetailHostLicenseIsEvaluation(hostLicenseInformation.isEvaluation());

    //
    // LICENSE
    //
    if (pluginLicenseManager.getLicense().isDefined()) {
      PluginLicense license = pluginLicenseManager.getLicense().get();
      if (license.getEdition().isDefined()) {
        licenseInformation.setDetailAppLicenseEdition(license.getEdition().get().toString());
      } else {
        licenseInformation.setDetailAppLicenseEdition("unlimited");
      }
      licenseInformation.setDetailAppLicenseIsDataCenter(license.isDataCenter());
      licenseInformation.setDetailAppLicenseIsEvaluation(license.isEvaluation());

      if (license.getError().isDefined()) {
        // https://developer.atlassian.com/platform/marketplace/server-app-license-validation-rules/
        if (LicenseError.EXPIRED.equals(license.getError().get())) {
          licenseInformation.setLicenseErrors(true);
          licenseInformation.setLicenseErrorType("EXPIRED");
          licenseInformation.setLicenseErrorMessage(
            "Invalid license: Your evaluation license of " + APP_NAME + " expired. " +
              "Please use the 'Buy' button to purchase a new license."
          );
        } else if (LicenseError.TYPE_MISMATCH.equals(license.getError().get())) {
          licenseInformation.setLicenseErrors(true);
          licenseInformation.setLicenseErrorType("TYPE_MISMATCH");
          licenseInformation.setLicenseErrorMessage(
            "Invalid license: Your evaluation license of " + APP_NAME + " expired. " +
              "Please use the 'Buy' button to purchase a new license."
          );
        } else if (LicenseError.USER_MISMATCH.equals(license.getError().get())) {
          licenseInformation.setLicenseErrors(true);
          licenseInformation.setLicenseErrorType("USER_MISMATCH");
          licenseInformation.setLicenseErrorMessage(
            "Invalid license: Your " + APP_NAME + " is only licensed for " + licenseInformation.getDetailAppLicenseEdition() + ". " +
              "Your Confluence installation requires a license for " + licenseInformation.getDetailHostLicenseEdition() + " users. " +
              "Please get a " + APP_NAME + " license for " + licenseInformation.getDetailHostLicenseEdition() + " users and try again."
          );
        } else if (LicenseError.EDITION_MISMATCH.equals(license.getError().get())) {
          licenseInformation.setLicenseErrors(true);
          licenseInformation.setLicenseErrorType("EDITION_MISMATCH");
          licenseInformation.setLicenseErrorMessage(
            "Invalid license: Your " + APP_NAME + " is only licensed for " + licenseInformation.getDetailAppLicenseEdition() + ". " +
              "Your Confluence installation requires a license for " + licenseInformation.getDetailHostLicenseEdition() + " remote agents. " +
              "Please get a " + APP_NAME + " license for " + licenseInformation.getDetailHostLicenseEdition() + " users and try again."
          );
        } else {
          licenseInformation.setLicenseErrorType(license.getError().get().toString());
          licenseInformation.setLicenseErrorMessage("Unknown license error. The License is somehow not valid.");
          licenseInformation.setLicenseErrors(true);
        }
      } else {
        // handle valid license scenario
        licenseInformation.setLicenseErrors(false);
      }
    } else {
      // handle unlicensed scenario
      licenseInformation.setLicenseErrorType("UNLICENSED");
      licenseInformation.setLicenseErrorMessage(
        "There is no license present for " + APP_NAME + ". "+
          "Please get a " + APP_NAME + " license for " + licenseInformation.getDetailHostLicenseEdition() + " users and try again.");
      licenseInformation.setLicenseErrors(true);
    }
    return licenseInformation;
  }

}
