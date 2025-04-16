package io.codeclou.ctendemo.config;

import com.atlassian.cache.CacheManager;
import com.atlassian.confluence.api.service.accessmode.AccessModeService;
import com.atlassian.confluence.content.service.BlogPostService;
import com.atlassian.confluence.content.service.DraftService;
import com.atlassian.confluence.content.service.PageService;
import com.atlassian.confluence.core.ContentPropertyManager;
import com.atlassian.confluence.pages.CommentManager;
import com.atlassian.confluence.plugin.services.VelocityHelperService;
import com.atlassian.confluence.security.PermissionManager;
import com.atlassian.confluence.setup.BootstrapManager;
import com.atlassian.confluence.setup.settings.GlobalSettingsManager;
import com.atlassian.confluence.user.UserPreferencesAccessor;
import com.atlassian.confluence.xhtml.api.XhtmlContent;
import com.atlassian.plugin.PluginAccessor;
import com.atlassian.plugins.whitelist.OutboundWhitelist;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.sal.api.message.I18nResolver;
import com.atlassian.upm.api.license.HostLicenseInformation;
import com.atlassian.upm.api.license.PluginLicenseManager;
import com.atlassian.user.UserManager;
import com.atlassian.webresource.api.WebResourceUrlProvider;
import io.codeclou.ctendemo.services.ContentAndPermissionService;
import io.codeclou.ctendemo.services.LoggedInConfluenceUserService;
import io.codeclou.ctendemo.services.PluginLicenseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.atlassian.plugins.osgi.javaconfig.OsgiServices.importOsgiService;

@Configuration
public class SpringBeansConfig {

    //
    // APP BEANS
    //

    @Bean
    public ContentAndPermissionService initContentAndPermissionService(
            DraftService draftService,
            BlogPostService blogPostService,
            CommentManager commentManager,
            PermissionManager permissionManager,
            PageService pageService
    ) {
        return new ContentAndPermissionService(draftService, blogPostService, commentManager, permissionManager, pageService);
    }

    @Bean
    public PluginLicenseService initPluginLicenseService(
            PluginLicenseManager pluginLicenseManager,
            HostLicenseInformation upmHostLicenseInformation) {
        return new PluginLicenseService(pluginLicenseManager, upmHostLicenseInformation);
    }


    @Bean
    public LoggedInConfluenceUserService initLoggedInConfluenceUserService(
            UserPreferencesAccessor userAccessor) {
        return new LoggedInConfluenceUserService(userAccessor);
    }

    //
    // CONFLUENCE BEANS
    //
    @Bean
    public ApplicationProperties importApplicationProperties() {
        return importOsgiService(ApplicationProperties.class);
    }

    @Bean
    public I18nResolver importI18nResolver() {
        return importOsgiService(I18nResolver.class);
    }

    @Bean(destroyMethod="") // IMPORTANT! OTHERWISE CACHEMANAGER CRASHES CONFLUENCE ON SHUTDOWN
    public CacheManager importCacheManager() {
        return importOsgiService(CacheManager.class);
    }

    @Bean
    public ContentPropertyManager importContentPropertyManager() {
        return importOsgiService(ContentPropertyManager.class);
    }

    @Bean
    public PageService importPageService() {
        return importOsgiService(PageService.class);
    }

    @Bean
    public UserManager importUserManager() {
        return importOsgiService(UserManager.class);
    }

    @Bean
    public PermissionManager importPermissionManager() {
        return importOsgiService(PermissionManager.class);
    }

    @Bean
    public BootstrapManager importBootstrapManager() {
        return importOsgiService(BootstrapManager.class);
    }

    @Bean
    public AccessModeService importAccessModeService() {
        return importOsgiService(AccessModeService.class);
    }

    @Bean
    public VelocityHelperService importVelocityHelperService() {
        return importOsgiService(VelocityHelperService.class);
    }

    @Bean
    public WebResourceUrlProvider importWebResourceUrlProvider() {
        return importOsgiService(WebResourceUrlProvider.class);
    }

    @Bean
    public GlobalSettingsManager importGlobalSettingsManager() {
        return importOsgiService(GlobalSettingsManager.class);
    }

    @Bean
    public UserPreferencesAccessor importUserAccessor() {
        return importOsgiService(UserPreferencesAccessor.class);
    }


    @Bean
    public PluginAccessor importPluginAccessor() {
        return importOsgiService(PluginAccessor.class);
    }

    @Bean
    public DraftService importDraftService() {
        return importOsgiService(DraftService.class);
    }

    @Bean
    public BlogPostService importBlogPostService() {
        return importOsgiService(BlogPostService.class);
    }

    @Bean
    public CommentManager importCommentManager() {
        return importOsgiService(CommentManager.class);
    }

    @Bean
    public PluginLicenseManager importPluginLicenseManager() {
        return importOsgiService(PluginLicenseManager.class);
    }


    @Bean
    public HostLicenseInformation importHostLicenseInformation() {
        return importOsgiService(HostLicenseInformation.class);
    }

    @Bean
    public XhtmlContent importXhtmlContent() {
        return importOsgiService(XhtmlContent.class);
    }

    @Bean
    public OutboundWhitelist importOutboundWhitelist() {
        return importOsgiService(OutboundWhitelist.class);
    }


}
