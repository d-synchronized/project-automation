package uk.co.techblue.automation.modules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.maven.model.Build;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;

import uk.co.techblue.automation.core.JaxbMarshallerUtility;
import uk.co.techblue.automation.dto.AutomationConstant;
import uk.co.techblue.automation.frameworks.FrameworkPackage;
import uk.co.techblue.automation.webmodule.configs.WebXmlConfig;

/**
 * The Class WebProjectModule.
 */
@Slf4j
@NoArgsConstructor
public class WebProjectModule extends ProjectModule {

    /** The web xml config. */
    @Getter
    private WebXmlConfig webXmlConfig;

    /**
     * Instantiates a new web project module.
     * 
     * @param frameworkPackages the framework packages
     */
    public WebProjectModule(final List<FrameworkPackage> frameworkPackages) {
        setFrameworkPackages(frameworkPackages);
        packaging = AutomationConstant.PACKAGING_WAR;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.techblue.automation.modules.ProjectModule#setParentModule(uk.co.techblue.automation.modules.ProjectModule)
     */
    @Override
    public void setParentModule(final ProjectModule parentModule) {
        super.setParentModule(parentModule);

        DependencyManagement dependencyManagement = this.getPom().getDependencyManagement();
        if (dependencyManagement == null) {
            dependencyManagement = new DependencyManagement();
            parentModule.getPom().setDependencyManagement(dependencyManagement);
        }

        if (frameworkPackages != null && frameworkPackages.size() > 0) {
            for (final FrameworkPackage frameworkPackage : frameworkPackages) {
                parentModule.updateDependencies(frameworkPackage.getDependenciesListWithVersion(), parentModule.getPom().getDependencies());
            }
        }

        if (plugins != null && plugins.size() > 0) {
            final List<Plugin> pluginList = new ArrayList<Plugin>();
            for (final Plugin plugin : plugins) {
                final Plugin pluginInfo = new Plugin();
                pluginInfo.setArtifactId(plugin.getArtifactId());
                pluginInfo.setGroupId(plugin.getGroupId());
                pluginList.add(pluginInfo);
            }
            if (parentModule.getPom().getBuild() == null) {
                final Build build = new Build();
                parentModule.getPom().setBuild(build);
                
            } 
            if (parentModule.getPom().getBuild().getPluginManagement() == null) {
                final PluginManagement pluginManagement = new PluginManagement();
                parentModule.getPom().getBuild().setPluginManagement(pluginManagement);
            }
            parentModule.updatePluginManagement(pluginList);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.techblue.automation.modules.ProjectModule#setPom(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void setPom(final String projectGroupId, final String projectName, final String projectVersion) {
        super.setPom(projectGroupId, projectName, projectVersion);

        Build build = this.getPom().getBuild();
        if (build == null) {
            build = new Build();
            this.getPom().setBuild(build);
        }

        final Plugin plugin = new Plugin();
        plugin.setArtifactId("maven-war-plugin");
        plugin.setGroupId("org.apache.maven.plugins");

        final List<Plugin> plugins = new ArrayList<Plugin>();
        plugins.add(plugin);
        setPlugins(plugins);

    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.techblue.automation.modules.ProjectModule#generateFolders()
     */
    protected void generateFolders() throws IOException {
        super.generateFolders();
        log.info("Generating folder structure for Web Archive (war) project");
        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER);

        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_MAIN_FOLDER);
        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_MAIN_FOLDER + "/" + AutomationConstant.MAVEN_JAVA_FOLDER);
        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_MAIN_FOLDER + "/" + AutomationConstant.MAVEN_RESOURCES_FOLDER);
        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_MAIN_FOLDER + "/" + AutomationConstant.MAVEN_WEB_APP_FOLDER);
        final String webInfFolder =
            targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_MAIN_FOLDER + "/" + AutomationConstant.MAVEN_WEB_APP_FOLDER + "/"
                + AutomationConstant.MAVEN_WEB_INF_FOLDER;
        generateFolder(webInfFolder);
        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_MAIN_FOLDER + "/" + AutomationConstant.MAVEN_WEB_APP_FOLDER + "/"
            + AutomationConstant.MAVEN_RESOURCES_FOLDER);

        // Generate and create web.xml
        generateWebXmlConfigurations(webInfFolder);

        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_TEST_FOLDER);
        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_TEST_FOLDER + "/" + AutomationConstant.MAVEN_JAVA_FOLDER);
        generateFolder(targetFolder + "/" + AutomationConstant.MAVEN_SRC_FOLDER + "/" + AutomationConstant.MAVEN_TEST_FOLDER + "/" + AutomationConstant.MAVEN_RESOURCES_FOLDER);
        log.info("Web archive (war) project structure successfully created");
    }

    /**
     * Generate web xml configurations.
     * 
     * @param targetFolder the target folder
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void generateWebXmlConfigurations(final String targetFolder) throws IOException {
        final String webXmlFile = targetFolder + "/" + AutomationConstant.WEB_XML_FILE;
        final WebXmlConfig webXmlConfiguration = new WebXmlConfig();
        webXmlConfiguration.setVersion("3.0");
        webXmlConfiguration.setDisplayName(getPom().getArtifactId());
        webXmlConfiguration.setXmlNameSpace("http://java.sun.com/xml/ns/javaee");
        webXmlConfiguration.setXmlSchemaInstance("http://www.w3.org/2001/XMLSchema-instance");
        webXmlConfiguration.setXmlSchemaLocation("http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd");

        this.webXmlConfig = webXmlConfiguration;

        final String webXmlString = JaxbMarshallerUtility.getXmlMarshalledOutput(webXmlConfiguration.getClass().getCanonicalName(), webXmlConfiguration);
        writeFile(webXmlString, webXmlFile);
    }
}
