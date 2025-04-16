package io.codeclou.ctendemo.services.model;


public class LicenseInformation {

  private Boolean licenseErrors = false;
  private String licenseErrorType; // e.g NOT_PRESENT, EXPIRED,TYPE_MISMATCH ..
  private String licenseErrorMessage; // for user

  private String detailHostLicenseEdition; // unlimited, 10, 25, 100 usw. => JIRA License with amount users
  private Boolean detailHostLicenseIsDataCenter = false;
  private Boolean detailHostLicenseIsEvaluation= false;

  private String detailAppLicenseEdition; // unlimited, 10, 25, 100 usw. => App License with amount users
  private Boolean detailAppLicenseIsDataCenter = false;
  private Boolean detailAppLicenseIsEvaluation = false;




  public Boolean getLicenseErrors() {
    return licenseErrors;
  }

  public void setLicenseErrors(Boolean licenseErrors) {
    this.licenseErrors = licenseErrors;
  }

  public String getLicenseErrorType() {
    return licenseErrorType;
  }

  public void setLicenseErrorType(String licenseErrorType) {
    this.licenseErrorType = licenseErrorType;
  }

  public String getLicenseErrorMessage() {
    return licenseErrorMessage;
  }

  public void setLicenseErrorMessage(String licenseErrorMessage) {
    this.licenseErrorMessage = licenseErrorMessage;
  }

  public String getDetailHostLicenseEdition() {
    return detailHostLicenseEdition;
  }

  public void setDetailHostLicenseEdition(String detailHostLicenseEdition) {
    this.detailHostLicenseEdition = detailHostLicenseEdition;
  }

  public Boolean getDetailHostLicenseIsDataCenter() {
    return detailHostLicenseIsDataCenter;
  }

  public void setDetailHostLicenseIsDataCenter(Boolean detailHostLicenseIsDataCenter) {
    this.detailHostLicenseIsDataCenter = detailHostLicenseIsDataCenter;
  }

  public Boolean getDetailHostLicenseIsEvaluation() {
    return detailHostLicenseIsEvaluation;
  }

  public void setDetailHostLicenseIsEvaluation(Boolean detailHostLicenseIsEvaluation) {
    this.detailHostLicenseIsEvaluation = detailHostLicenseIsEvaluation;
  }

  public String getDetailAppLicenseEdition() {
    return detailAppLicenseEdition;
  }

  public void setDetailAppLicenseEdition(String detailAppLicenseEdition) {
    this.detailAppLicenseEdition = detailAppLicenseEdition;
  }

  public Boolean getDetailAppLicenseIsDataCenter() {
    return detailAppLicenseIsDataCenter;
  }

  public void setDetailAppLicenseIsDataCenter(Boolean detailAppLicenseIsDataCenter) {
    this.detailAppLicenseIsDataCenter = detailAppLicenseIsDataCenter;
  }

  public Boolean getDetailAppLicenseIsEvaluation() {
    return detailAppLicenseIsEvaluation;
  }

  public void setDetailAppLicenseIsEvaluation(Boolean detailAppLicenseIsEvaluation) {
    this.detailAppLicenseIsEvaluation = detailAppLicenseIsEvaluation;
  }
}
