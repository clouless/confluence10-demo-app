package io.codeclou.ctendemo.macro;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.renderer.RenderContext;

public class MacroRenderType {

    public static String determineRenderType(ConversionContext conversionContext) {
        String nullSafeOutPutType = conversionContext != null ? conversionContext.getOutputType() : null;
        if (RenderContext.EMAIL.equalsIgnoreCase(nullSafeOutPutType) ||
                RenderContext.HTML_EXPORT.equalsIgnoreCase(nullSafeOutPutType) ||
                RenderContext.PDF.equalsIgnoreCase(nullSafeOutPutType) ||
                RenderContext.WORD.equalsIgnoreCase(nullSafeOutPutType))
            return AppConstants.MACRO_RENDER_TYPE__EXPORT_NO_JS_PLAIN_TEXT;
        if (RenderContext.PREVIEW.equalsIgnoreCase(nullSafeOutPutType) )
            return AppConstants.MACRO_RENDER_TYPE__PREVIEW_WITH_JS_REDUCED_FEATURES;
        if (RenderContext.FEED.equalsIgnoreCase(nullSafeOutPutType) ||
                RenderContext.DISPLAY.equalsIgnoreCase(nullSafeOutPutType)) {
            return AppConstants.MACRO_RENDER_TYPE__NORMAL_WITH_JS_ALL_FEATURES;
        }
        return AppConstants.MACRO_RENDER_TYPE__NORMAL_WITH_JS_ALL_FEATURES;
    }

}
