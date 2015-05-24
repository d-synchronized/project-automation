package uk.co.techblue.automation.modules;

import java.util.Arrays;
import java.util.List;

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
    public void exportModules(final ProjectModule... projectModules) {
        setDependencyManagement(Arrays.asList(projectModules));
    }

    /**
     * Export modules.
     * 
     * @param projectModules the project modules
     */
    public void exportModules(final List<ProjectModule> projectModules) {
        setDependencyManagement(projectModules);
    }

    /**
     * Sets the dependency management.
     * 
     * @param projectModules the new dependency management
     */
    public void setDependencyManagement(final List<ProjectModule> projectModules) {
        final DependencyManagement dependencyManagement = new DependencyManagement();
        for (ProjectModule projectModule : projectModules) {
            final Dependency dependency = new Dependency();
            dependency.setGroupId(projectModule.getPom().getGroupId());
            dependency.setArtifactId(projectModule.getPom().getArtifactId());
            dependency.setVersion(projectModule.getPom().getVersion());
            dependencyManagement.addDependency(dependency);
            
            //Set master project as parent of the module
            projectModule.setParentModule(this);
        }
        this.pom.setDependencyManagement(dependencyManagement);
    }

}
