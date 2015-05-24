package uk.co.techblue.automation.library;

import org.apache.maven.model.Dependency;

/**
 * The Class ThirdPartyLibraryPackage.
 */
public abstract class ThirdPartyLibraryPackage {

    /** The library group id. */
    protected String libraryGroupId;

    /** The library artifact id. */
    protected String libraryArtifactId;

    /** The library version. */
    protected String libraryVersion;

    /**
     * Gets the dependency information.
     * 
     * @return the dependency information
     */
    public Dependency getDependencyInformation(final boolean isDependencyManagement) {
        final Dependency dependency = new Dependency();
        dependency.setArtifactId(libraryArtifactId);
        dependency.setGroupId(libraryGroupId);
        if(isDependencyManagement){
            dependency.setVersion(libraryVersion);
        }
        return dependency;
    }

}
