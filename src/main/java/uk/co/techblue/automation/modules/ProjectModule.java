package uk.co.techblue.automation.modules;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import uk.co.techblue.automation.dto.AutomationConstant;

/**
 * The Class ProjectModule.
 */

/*
 * (non-Javadoc)
 * 
 * @see java.lang.Object#toString()
 */
@Data
public class ProjectModule {

    /** The project folder. */
    protected String projectFolder;

    /** The base folder. */
    protected String baseFolder;

    /** The target folder. */
    protected String targetFolder;

    /** The packaging. */
    protected String packaging;

    /** The pom. */
    protected Model pom;

    /**
     * Instantiates a new project module.
     */
    public ProjectModule() {
        packaging = AutomationConstant.PACKAGING_POM;
    }

    /**
     * Adds the dependencies.
     * 
     * @param dependencies the dependencies
     */
    protected void addDependencies(final List<Dependency> dependencies) {
        final List<Dependency> existingDependencies = this.pom.getDependencies();
        updateDependencies(dependencies, existingDependencies);
    }

    /**
     * Update dependency.
     * 
     * @param dependency the dependency
     */
    protected void updateDependency(final Dependency dependency) {
        final Set<Dependency> existingDependencies = new HashSet<>(pom.getDependencies());
        existingDependencies.add(dependency);
        final List<Dependency> updatedDependenciesList = new ArrayList<>(existingDependencies);
        pom.setDependencies(updatedDependenciesList);
    }

    /**
     * Update dependencies.
     * 
     * @param newDependencies the new dependencies
     * @param existingDependencies the existing dependencies
     * @return the list
     */
    protected List<Dependency> updateDependencies(final List<Dependency> newDependencies, final List<Dependency> existingDependencies) {
        final Set<Dependency> set = new HashSet<>(existingDependencies);
        set.addAll(newDependencies);

        final List<Dependency> updatedDependenciesList = new ArrayList<>(set);
        pom.setDependencies(updatedDependenciesList);
        return updatedDependenciesList;
    }

    /**
     * Updated dependency management.
     * 
     * @param newDependencies the new dependencies
     */
    protected void updatedDependencyManagement(final List<Dependency> newDependencies) {
        final List<Dependency> existingDependencies = this.pom.getDependencyManagement().getDependencies();
        final List<Dependency> updatedDependencies = updateDependencies(newDependencies, existingDependencies);
        this.pom.getDependencyManagement().setDependencies(updatedDependencies);
    }

    /**
     * Sets the pom.
     * 
     * @param projectGroupId the project group id
     * @param projectName the project name
     * @param projectVersion the project version
     */
    public void setPom(final String projectGroupId, final String projectName, final String projectVersion) {
        final Model pom = new Model();
        pom.setGroupId(projectGroupId);
        pom.setArtifactId(projectName);
        pom.setModelVersion(AutomationConstant.MAVEN_MODEL_VERSION);
        pom.setVersion(projectVersion);
        pom.setPackaging(packaging);
        this.pom = pom;
    }

    /**
     * Sets the parent module.
     * 
     * @param parentModule the new parent module
     */
    public void setParentModule(final ProjectModule parentModule) {
        if (parentModule.getPom() != null) {
            Parent thisParent = new Parent();
            thisParent.setGroupId(parentModule.getPom().getGroupId());
            thisParent.setArtifactId(parentModule.getPom().getArtifactId());
            thisParent.setVersion(parentModule.getPom().getVersion());
            this.pom.setParent(thisParent);
            parentModule.getPom().getModules().add(projectFolder);
        }
    }

    /**
     * Generate folder.
     * 
     * @param folderName the folder name
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void generateFolder(final String folderName) throws IOException {
        final Path modulePath = Paths.get(folderName);
        Files.createDirectories(modulePath);
    }

    /**
     * Write data.
     * 
     * @param mavenXpp3Writer the maven xpp3 writer
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void writeData(MavenXpp3Writer mavenXpp3Writer) throws IOException {
        targetFolder = baseFolder + "/" + projectFolder;
        generateFolder(targetFolder);
        final FileWriter fileWriter = new FileWriter(targetFolder + "/pom.xml");
        mavenXpp3Writer.write(fileWriter, pom);
    }

}
