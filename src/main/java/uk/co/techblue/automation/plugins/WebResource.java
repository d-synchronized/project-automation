package uk.co.techblue.automation.plugins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

/**
 * The Class WebResource.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class WebResource {

    /** The resource directory. */
    @XmlElement(name = "directory")
    private String resourceDirectory;

    /** The resource target path. */
    @XmlElement(name = "targetPath")
    private String resourceTargetPath;

    /** The includes. */
    @XmlElement(name = "includes")
    private Include includes;

}
