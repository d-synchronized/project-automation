package uk.co.techblue.automation.webmodule.configs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

/**
 * The Class Listener.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Listener {

    /** The listener class. */
    @XmlElement(name = "listener-class")
    private String listenerClass;

}
