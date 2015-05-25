import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import uk.co.techblue.automation.core.JaxbMarshallerUtility;
import uk.co.techblue.automation.webmodule.configs.ContextParam;
import uk.co.techblue.automation.webmodule.configs.WebXmlConfig;

public class JaxbMarshallerTest {

    public static void main(final String args[]) {
        WebXmlConfig webXmlConfig = new WebXmlConfig();
        webXmlConfig.setVersion("3.0");
        webXmlConfig.setDisplayName("automation-demo");
        webXmlConfig.setXmlNameSpace("http://java.sun.com/xml/ns/javaee");
        
        final List<ContextParam> contextParams = new ArrayList<ContextParam>();
        final ContextParam contextParam = new ContextParam();
        contextParam.setParamName("demo");
        contextParam.setParamValue("demo");
        contextParams.add(contextParam);
        webXmlConfig.setContextParams(contextParams);
        
        JaxbMarshallerUtility.getXmlMarshalledOutput(webXmlConfig.getClass().getCanonicalName(), webXmlConfig);
    }

}
