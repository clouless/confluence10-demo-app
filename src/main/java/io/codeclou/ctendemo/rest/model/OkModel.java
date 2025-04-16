package io.codeclou.ctendemo.rest.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OkModel {

    @XmlElement
    private String message = "ok";

    public String getMessage() {
        return message;
    }
}
