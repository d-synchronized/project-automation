package uk.co.techblue.automation.core;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import lombok.extern.slf4j.Slf4j;
import uk.co.techblue.automation.dto.AutomationExceptionConstant;
import uk.co.techblue.automation.exception.AutomationExecutionException;

/** The Constant log. */
@Slf4j
public class JaxbMarshallerUtility {

    /**
     * Gets the xml marshalled output.
     * 
     * @param jaxbContext the jaxb context
     * @return the xml marshalled output
     */
    public static String getXmlMarshalledOutput(final String clazzName,final Object object) {
        String result = null;
        try {
            final Class<?> clazz = Class.forName(clazzName);
            final JAXBContext  jaxbContext = JAXBContext.newInstance(clazz);
            final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            final StringWriter stringWriter = new StringWriter();
            jaxbMarshaller.marshal(object, stringWriter);

            result = stringWriter.toString();
        } catch (JAXBException jaxbException) {
            log.error("Error occurred while parsing the java object , Error - ", jaxbException);
            throw new AutomationExecutionException(AutomationExceptionConstant.INTERNAL_SERVER_ERROR, jaxbException);
        } catch (ClassNotFoundException classNotFoundException) {
            
        }
        return result;
    }

}
