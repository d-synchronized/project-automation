package uk.co.techblue.automation.core;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory for creating ModuleInfoInitializer objects.
 */
public class ModuleInfoInitializerFactory {

    /** The module infos. */
    private static Map<String, String> moduleInfos;

    /**
     * Fetch module info map.
     * 
     * @return the map
     */
    public static Map<String, String> fetchModuleInfoMap() {
        if (moduleInfos == null) {
            moduleInfos = new HashMap<String, String>();
        }
        return moduleInfos;
    }

    /**
     * Clean module info map.
     */
    public static void cleanModuleInfoMap() {
        moduleInfos = null;
    }

    /**
     * Adds the module info.
     * 
     * @param moduleName the module name
     * @param modulePackaging the module packaging
     */
    public static void addModuleInfo(final String moduleName, final String modulePackaging) {
        fetchModuleInfoMap();
        moduleInfos.put(moduleName, modulePackaging);
    }
    
    

}
