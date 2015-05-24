package uk.co.techblue.automation.core;

import uk.co.techblue.automation.dto.AutomationConstant;
import uk.co.techblue.automation.modules.EarProjectModule;
import uk.co.techblue.automation.modules.JavaProjectModule;
import uk.co.techblue.automation.modules.MasterProjectModule;
import uk.co.techblue.automation.modules.ProjectModule;
import uk.co.techblue.automation.modules.WebProjectModule;

/**
 * The Class ModuleGenerator.
 */
public class ModuleGenerator {
    

    /**
     * Generate project module.
     * 
     * @param moduleName the module name
     * @param packagingInfo the packaging info
     * @return the project module
     */
    public static ProjectModule generateProjectModule(final String moduleName, final String packagingInfo,final String projectFolder) {
        switch (packagingInfo) {
            case AutomationConstant.PACKAGING_JAR:
                final ProjectModule jarProjectModule = new JavaProjectModule();
                jarProjectModule.setBaseFolder(projectFolder);
                jarProjectModule.setProjectFolder(moduleName);
                jarProjectModule.setPackaging(AutomationConstant.PACKAGING_JAR);
                return jarProjectModule;

            case AutomationConstant.PACKAGING_WAR:
                final ProjectModule warProjectModule = new WebProjectModule();
                warProjectModule.setBaseFolder(projectFolder);
                warProjectModule.setProjectFolder(moduleName);
                warProjectModule.setPackaging(AutomationConstant.PACKAGING_WAR);
                return warProjectModule;

            case AutomationConstant.PACKAGING_EAR:
                final ProjectModule earProjectModule = new EarProjectModule();
                earProjectModule.setBaseFolder(projectFolder);
                earProjectModule.setProjectFolder(moduleName);
                earProjectModule.setPackaging(AutomationConstant.PACKAGING_EAR);
                return earProjectModule;

            case AutomationConstant.PACKAGING_POM:
                final ProjectModule masterProjectModule = new MasterProjectModule();
                masterProjectModule.setBaseFolder(projectFolder);
                masterProjectModule.setProjectFolder(moduleName);
                masterProjectModule.setPackaging(AutomationConstant.PACKAGING_POM);
                return masterProjectModule;

            default:
                return null;
        }
    }

}
