package uk.co.techblue.automation.webmodule.configs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

/**
 * The Class ContextParam.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ContextParam {

    /** The param name. */
    @XmlElement(name = "param-name")
    private String paramName;

    /** The param value. */
    @XmlElement(name = "param-value")
    private String paramValue;

}
