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
    public static ProjectModule generateProjectModule(final String moduleName, final String packagingInfo) {
        switch (packagingInfo) {
            case AutomationConstant.PACKAGING_JAR:
                return new JavaProjectModule();

            case AutomationConstant.PACKAGING_WAR:
                return new WebProjectModule();

            case AutomationConstant.PACKAGING_EAR:
                return new EarProjectModule();

            case AutomationConstant.PACKAGING_POM:
                return new MasterProjectModule();

            default:
                return null;
        }
    }

}
