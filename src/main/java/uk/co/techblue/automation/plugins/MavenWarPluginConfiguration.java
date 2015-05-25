package uk.co.techblue.automation.plugins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * The Class MavenWarPluginConfiguration.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "configuration")
public class MavenWarPluginConfiguration {

    /** The fail on missing web xml. */
    @XmlElement(name = "failOnMissingWebXml")
    private Boolean failOnMissingWebXml;

    /** The packaging excludes. */
    @XmlElement(name = "packagingExcludes")
    private String packagingExcludes;

    /** The web resources. */
    @XmlElement(name = "webResources")
    private WebResources webResources;

}
