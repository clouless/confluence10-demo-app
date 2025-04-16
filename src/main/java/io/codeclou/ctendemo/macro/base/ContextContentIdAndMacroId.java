package io.codeclou.ctendemo.macro.base;

import java.util.Map;

public class ContextContentIdAndMacroId {

    Map<String, Object> context;

    String macroId;

    Long contentId;

    String contentType;

    public ContextContentIdAndMacroId(Map<String, Object> context, String macroId, Long contentId, String contentType) {
        this.context = context;
        this.macroId = macroId;
        this.contentId = contentId;
        this.contentType = contentType;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public String getMacroId() {
        return macroId;
    }

    public void setMacroId(String macroId) {
        this.macroId = macroId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
