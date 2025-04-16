package io.codeclou.ctendemo.rest.model;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestErrorModel {

    @XmlElement
    private String error;

    @XmlElement
    private String errorCode;

    @XmlElement
    private String message;

    @XmlElement
    private String extraMessage;

    RestErrorModel() {}

    public RestErrorModel(String error, String message, String extraMessage) {
        this.error = error;
        this.message = message;
        this.extraMessage = extraMessage;
        this.errorCode = null;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtraMessage() {
        return extraMessage;
    }

    public void setExtraMessage(String extraMessage) {
        this.extraMessage = extraMessage;
    }
}
