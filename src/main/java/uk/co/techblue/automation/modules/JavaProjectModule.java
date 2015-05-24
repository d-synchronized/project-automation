package uk.co.techblue.automation.modules;

import java.io.IOException;
import java.util.List;

import org.apache.maven.model.DependencyManagement;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.techblue.automation.dto.AutomationConstant;
import uk.co.techblue.automation.frameworks.FrameworkPackage;
import uk.co.techblue.automation.library.ThirdPartyLibraryPackage;

/**
 * The Class JavaProjectModule.
 */

/** The Constant log. */
@Slf4j
@NoArgsConstructor
public class JavaProjectModule extends ProjectModule {

    /** The framework packages. */
    private List<FrameworkPackage> frameworkPackages;

    /** The third party library packages. */
    private List<ThirdPartyLibraryPackage> thirdPartyLibraryPackages;

    /**
     * Instantiates a new java project module.
     * 
     * @param frameworkPackages the framework packages
     * @param thirdPartyLibraryPackages the third party library packages
     */
    public JavaProjectModule(final List<FrameworkPackage> frameworkPackages, final List<ThirdPartyLibraryPackage> thirdPartyLibraryPackages) {
        setFrameworkPackages(frameworkPackages);
        setThirdPartyLibraryPackages(thirdPartyLibraryPackages);
        packaging = AutomationConstant.PACKAGING_JAR;
    }
    
    /**
     * Sets the framework packages.
     * 
     * @param frameworkPackages the new framework packages
     */
    public void setFrameworkPackages(final List<FrameworkPackage> frameworkPackages){
        this.frameworkPackages = frameworkPackages;
        for (final FrameworkPackage frameworkPackage : frameworkPackages) {
            addDependencies(frameworkPackage.getDependenciesList());
        }
    }
    
    /**
     * Sets the third party library packages.
     * 
     * @param thirdPartyLibraryPackages the new third party library packages
     */
    public void setThirdPartyLibraryPackages(final List<ThirdPartyLibraryPackage> thirdPartyLibraryPackages){
        this.thirdPartyLibraryPackages = thirdPartyLibraryPackages;
        for (final ThirdPartyLibraryPackage thirdPartyLibraryPackage : thirdPartyLibraryPackages) {
            updateDependency(thirdPartyLibraryPackage.getDependencyInformation(false));
        }
    }

    /* (non-Javadoc)
     * @see uk.co.techblue.automation.modules.ProjectModule#setParentModule(uk.co.techblue.automation.modules.ProjectModule)
     */
    @Override
    public void setParentModule(final ProjectModule parentModule) {
        super.setParentModule(parentModule);
        
        DependencyManagement dependencyManagement = this.getPom().getDependencyManagement();
        if(dependencyManagement == null){
            dependencyManagement = new DependencyManagement();
            parentModule.getPom().setDependencyManagement(dependencyManagement);
        }
        
        if(frameworkPackages!=null){
            for(final FrameworkPackage frameworkPackage : frameworkPackages){
                parentModule.updateDependencies(frameworkPackage.getDependenciesListWithVersion(), parentModule.getPom().getDependencies());
            }
        }
        
        if(thirdPartyLibraryPackages!=null){
            for(final ThirdPartyLibraryPackage thirdPartyLibraryPackage : thirdPartyLibraryPackages){
                parentModule.updateDependency(thirdPartyLibraryPackage.getDependencyInformation(false));
            } 
        }
    }

    /**
     * Generate folders.
     * 
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    protected void generateFolders() throws IOException {
        super.generateFolders();
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
