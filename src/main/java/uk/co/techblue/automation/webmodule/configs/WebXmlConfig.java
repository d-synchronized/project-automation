package uk.co.techblue.automation.webmodule.configs;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * 
 * version="3.0" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 * xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
 * 
 * */
@Data
@XmlRootElement(name = "web-app")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebXmlConfig {
    
    @XmlAttribute(name = "version")
    private String version;
    
    @XmlAttribute(name = "xmlns")
    private String xmlNameSpace;
    
    @XmlAttribute(name = "xmlns:xsi")
    private String xmlSchemaInstance;
    
    @XmlAttribute(name = "xsi:schemaLocation")
    private String xmlSchemaLocation;

    @XmlElement(name = "display-name")
    private String displayName;

    @XmlElement(name = "context-param")
    private List<ContextParam> contextParams;

    @XmlElement(name = "listener")
    private List<Listener> listeners;

    @XmlElement(name = "session-config")
    private SessionConfig sessionConfig;

    @XmlElement(name = "welcome-file-list")
    private WelcomeFileList welcomeFileList;

}
