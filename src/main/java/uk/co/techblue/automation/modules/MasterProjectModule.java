package uk.co.techblue.automation.modules;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;

/**
 * The Class MasterProjectModule.
 */
public class MasterProjectModule extends ProjectModule {

    /**
     * Sets the export modules.
     * 
     * @param projectModules the new export modules
     */
    public void setExportModules(final ProjectModule... projectModules) {
        final DependencyManagement dependencyManagement = new DependencyManagement();
        for (ProjectModule projectModule : projectModules) {
            final Dependency dependency = new Dependency();
            dependency.setGroupId(projectModule.getPom().getGroupId());
            dependency.setArtifactId(projectModule.getPom().getArtifactId());
            dependency.setVersion(projectModule.getPom().getVersion());
            dependencyManagement.addDependency(dependency);
        }
        this.pom.setDependencyManagement(dependencyManagement);
    }

}
