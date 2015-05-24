package uk.co.techblue.automation.frameworks;

import java.util.List;

import lombok.Getter;

import org.apache.maven.model.Dependency;

/**
 * The Class FrameworkPackage.
 */
public abstract class FrameworkPackage {
    

    /** The framework version. */
    protected String frameworkVersion;

    /**
     * Gets the dependencies list.
     * 
     * @return the dependencies list
     */
    @Getter
    protected List<Dependency> dependenciesList;

    /**
     * Gets the dependencies list with version.
     * 
     * @return the dependencies list with version
     */
    @Getter
    protected List<Dependency> dependenciesListWithVersion;

}
