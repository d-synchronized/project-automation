package uk.co.techblue.automation.webmodule.configs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

/**
 * The Class SessionConfig.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SessionConfig {

    /** The session time out. */
    @XmlElement(name = "session-timeout")
    private String sessionTimeOut;

}
