package io.codeclou.ctendemo.macro.base;

import com.atlassian.confluence.api.model.accessmode.AccessMode;
import com.atlassian.confluence.api.service.accessmode.AccessModeService;
import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.content.render.xhtml.storage.macro.MacroId;
import com.atlassian.confluence.plugin.services.VelocityHelperService;
import com.atlassian.confluence.setup.BootstrapManager;
import com.atlassian.confluence.xhtml.api.MacroDefinition;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.webresource.api.WebResourceUrlProvider;
import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.v2.RenderMode;
import com.atlassian.renderer.v2.macro.MacroException;
import com.atlassian.sal.api.message.I18nResolver;
import io.codeclou.ctendemo.macro.AppConstants;
import io.codeclou.ctendemo.services.LoggedInConfluenceUserService;
import io.codeclou.ctendemo.services.PluginLicenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;

public class BaseMacro extends com.atlassian.renderer.v2.macro.BaseMacro {

	protected ApplicationProperties applicationProperties;
    // DEPENDENCY INJECTION IN ACTUAL MACRO CONSTRUCTOR
    protected I18nResolver i18n;
	protected BootstrapManager bootstrapManager;
    protected AccessModeService accessModeService; // Since Confluence 6.10 [ACBM-5]
	protected PluginLicenseService pluginLicenseService;
    protected LoggedInConfluenceUserService loggedInConfluenceUserService;
	protected VelocityHelperService velocityHelperService;

	// With Confluence 9 it will move to other package:
	//   protected com.atlassian.webresource.api.WebResourceUrlProvider webResourceUrlProvider;
	protected WebResourceUrlProvider webResourceUrlProvider;
	private static final Logger log = LoggerFactory.getLogger(io.codeclou.ctendemo.macro.base.BaseMacro.class);


	protected ContextContentIdAndMacroId getContextWithParamsForAllMacros(String macroType, String renderTypeForFrontend, Map<String, String> params, String body, ConversionContext conversionContext) {
        Map<String, Object>  context = getDefaultVelocityContext();
        MacroDefinition macroDefinition = (MacroDefinition) conversionContext.getProperty("macroDefinition");
        long contentId = getContentIdFromConversionContext(conversionContext);
        String contentType = getContentTypeFromConversionContext(conversionContext);
        MacroId macroId = macroDefinition.getMacroIdentifier().orElse(MacroId.fromString(UUID.randomUUID().toString()));

        String globaltitle = getStringOrDefaultFromParams(params, AppConstants.MACRO_PARAM_GLOBALTITLE, null);

        context.put(AppConstants.VELOCITY_PLACEHOLDER_GLOBALTITLE, globaltitle);
        context.put(AppConstants.VELOCITY_PLACEHOLDER_MACROID, macroId.getId());
        context.put(AppConstants.VELOCITY_PLACEHOLDER_CONTENT_ID, contentId);
        context.put(AppConstants.VELOCITY_PLACEHOLDER_CONTENT_TYPE, contentType);
        context.put(AppConstants.VELOCITY_PLACEHOLDER_MACROTYPE, macroType);
        context.put(AppConstants.VELOCITY_PLACEHOLDER_TIME_ZONE, this.loggedInConfluenceUserService.getRemoteUserTimeZone());
        context.put(AppConstants.VELOCITY_PLACEHOLDER_LOCALE, this.loggedInConfluenceUserService.getRemoteUserLocale());
        context.put(AppConstants.VELOCITY_PLACEHOLDER_READ_ONLY_MODE, isReadOnlyModeActive());
        context.put(AppConstants.VELOCITY_PLACEHOLDER_RENDER_TYPE, renderTypeForFrontend);
		String baseUrl = this.applicationProperties.getBaseUrl(com.atlassian.sal.api.UrlMode.RELATIVE);
		context.put(AppConstants.VELOCITY_PLACEHOLDER_BASEURL, baseUrl);

		return new ContextContentIdAndMacroId(context, macroId.getId(), contentId, contentType);
    }


	public boolean isReadOnlyModeActive() {
		return AccessMode.READ_ONLY.equals(accessModeService.getAccessMode());
	}

	public String execute(@SuppressWarnings("rawtypes") Map params, String body, RenderContext renderContext)
			throws MacroException {
		return null;
	}

	public RenderMode getBodyRenderMode() {
		return null;
	}

	public boolean hasBody() {
		return false;
	}


	/**
	 * Mockable Method
	 */
	public Map<String, Object> getDefaultVelocityContext() {
		return velocityHelperService.createDefaultVelocityContext();
	}

	/**
	 * Mockable Method
	 */
	public String renderTemplate(String templateName, Map<String, Object> context) {
		return velocityHelperService.getRenderedTemplate(templateName, context);
	}

	public String renderErrorWithTemplate(String errorKey, String errorBody, String macroType) {
		Map<String, Object> context = getDefaultVelocityContext();
		context.put("errorkey", errorKey);
		context.put("errorbody", errorBody);
		context.put("macrotype", macroType);
		return renderTemplate("templates/macro-error.vm", context);
	}

	public String i18n_getText(String key) {
		String result = key;
		result = i18n.getText(key);
		return result;
	}


	//
	// for macros
    //
    protected String getStringOrDefaultFromParams(Map<String, String> params, String keyToGet, String defaultValue) {
        String ret = defaultValue;
        if (params.containsKey(keyToGet)) {
            ret = params.get(keyToGet);
        }
        return ret;
    }
    protected Boolean getBooleanOrDefaultFromParams(Map<String, String> params, String keyToGet, Boolean defaultValue) {
        Boolean ret = defaultValue;
        if (params.containsKey(keyToGet)) {
            ret = Boolean.parseBoolean(params.get(keyToGet));
        }
        return ret;
    }
    protected long getContentIdFromConversionContext(ConversionContext conversionContext) {
        // in some cases there can be NO pageObject - when creating a page and inserting macro for example.
        // Since we only need the pageId for the refreshCache link in frontend, we set it to -1 if no page is available
        long contentId = -1L;
        if (conversionContext.getEntity() != null) {
            contentId = conversionContext.getEntity().getId();
        }
        return contentId;
    }

    protected String getContentTypeFromConversionContext(ConversionContext conversionContext) {
        String type = "none";
        if (conversionContext.getEntity() != null) {
            type = conversionContext.getEntity().getType();
        }
        return type;
    }
}
