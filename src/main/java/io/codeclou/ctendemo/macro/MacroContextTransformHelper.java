package io.codeclou.ctendemo.macro;

import com.atlassian.confluence.api.model.content.ContentStatus;
import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.content.render.xhtml.DefaultConversionContext;
import com.atlassian.renderer.RenderContext;


public class MacroContextTransformHelper {

    // moved this code out of macro to be able to mock it statically during tests :)
    public static ConversionContext transform(RenderContext context) {
        return new DefaultConversionContext(context);
    }

    public static Boolean getIsDraftFromContentEntityObjectNullSafe(ConversionContext conversionContext) {
        if (conversionContext == null || conversionContext.getEntity() == null || conversionContext.getEntity().getContentStatusObject() == null) {
            return false;
        }
        return conversionContext.getEntity().getContentStatusObject().equals(ContentStatus.DRAFT);
    }
}
