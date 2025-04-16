package io.codeclou.ctendemo.macro;

import com.atlassian.confluence.api.service.accessmode.AccessModeService;
import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.plugin.services.VelocityHelperService;
import com.atlassian.confluence.setup.BootstrapManager;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.webresource.api.WebResourceUrlProvider;
import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.v2.RenderMode;
import com.atlassian.renderer.v2.macro.MacroException;
import com.atlassian.sal.api.message.I18nResolver;
import io.codeclou.ctendemo.macro.base.BaseMacro;
import io.codeclou.ctendemo.macro.base.ContextContentIdAndMacroId;
import io.codeclou.ctendemo.services.LoggedInConfluenceUserService;
import io.codeclou.ctendemo.services.PluginLicenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.inject.Inject;
import java.util.Map;

@Component
public class CTenDemoMacro extends BaseMacro implements Macro {

    private static final Logger log = LoggerFactory.getLogger(CTenDemoMacro.class);

    @Inject
    public CTenDemoMacro(
            ApplicationProperties applicationProperties,
            I18nResolver i18n,
            BootstrapManager bootstrapManager,
            AccessModeService accessModeService,
            VelocityHelperService velocityHelperService,
            WebResourceUrlProvider webResourceUrlProvider,
            LoggedInConfluenceUserService loggedInConfluenceUserService,
            PluginLicenseService pluginLicenseService) {
        this.applicationProperties = applicationProperties;
        this.i18n = i18n;
        this.bootstrapManager = bootstrapManager;
        this.accessModeService = accessModeService;
        this.pluginLicenseService = pluginLicenseService;
        this.loggedInConfluenceUserService = loggedInConfluenceUserService;
        this.velocityHelperService = velocityHelperService;
        this.webResourceUrlProvider = webResourceUrlProvider;
    }

    // v2 Macro methods

    public String execute(Map<String, String> params, String body, ConversionContext conversionContext) throws MacroExecutionException {
        String renderTypeForFrontend = MacroRenderType.determineRenderType(conversionContext);
        try {
            ContextContentIdAndMacroId contextAndIds = getContextWithParamsForAllMacros(AppConstants.MACRO_TYPE, renderTypeForFrontend, params, body, conversionContext);
            return renderTemplate("templates/macro-success.vm", contextAndIds.getContext());
         } catch (Exception e) {
            log.error("Macro.execute()", e.getMessage());
            throw new MacroExecutionException(e);
        }
    }


    public BodyType getBodyType() {
        return BodyType.PLAIN_TEXT;
    }


    public OutputType getOutputType() {
        return OutputType.BLOCK;
    }

    // v1 Macro methods

    @SuppressWarnings("unchecked")
    @Override
    public String execute(@SuppressWarnings("rawtypes") Map params, String body, RenderContext renderContext) throws MacroException {
        try {
            return execute(params, body, MacroContextTransformHelper.transform(renderContext));
        } catch(Exception e) {
            throw new MacroException(e);
        }
    }

    @Override
    public RenderMode getBodyRenderMode() {
        return RenderMode.ALL;
    }

    @Override
    public boolean hasBody() {
        return true;
    }

}

