package uk.co.techblue.automation.modules;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import uk.co.techblue.automation.dto.AutomationConstant;

/**
 * The Class JavaProjectModule.
 */
@Slf4j
public class JavaProjectModule extends ProjectModule {

    /**
     * Generate folders.
     * 
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void generateFolders() throws IOException {
        log.info("Generating folder structure for Java Archive (jar) project");
        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER);
        
        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_MAIN_FOLDER);
        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_MAIN_FOLDER + "/" + AutomationConstant.MAVEN_JAVA_FOLDER);
        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_MAIN_FOLDER + "/" + AutomationConstant.MAVEN_RESOURCES_FOLDER);

        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_TEST_FOLDER);
        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_TEST_FOLDER + "/" + AutomationConstant.MAVEN_JAVA_FOLDER);
        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_TEST_FOLDER + "/" + AutomationConstant.MAVEN_RESOURCES_FOLDER);
        log.info("Java archive (jar) project structure successfully created");
    }

}
