package uk.co.techblue.automation.plugins;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

/**
 * The Class WebResources.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class WebResources {

    /** The web resources. */
    @XmlElement(name = "resource")
    private List<WebResource> webResources;

}
