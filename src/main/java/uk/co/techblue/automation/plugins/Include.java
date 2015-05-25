package uk.co.techblue.automation.plugins;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

/**
 * The Class Includes.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Include {

    /** The includes. */
    @XmlElement(name = "include")
    private List<String> includes;

}
