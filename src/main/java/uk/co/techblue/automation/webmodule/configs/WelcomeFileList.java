package uk.co.techblue.automation.webmodule.configs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

/**
 * Instantiates a new welcome file list.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class WelcomeFileList {

    /** The welcome file. */
    @XmlElement(name = "welcome-file")
    private String welcomeFile;

}
