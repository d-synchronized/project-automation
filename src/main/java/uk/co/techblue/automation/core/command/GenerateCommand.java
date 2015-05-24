package uk.co.techblue.automation.core.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import uk.co.techblue.automation.core.ModuleGenerator;
import uk.co.techblue.automation.core.ModuleInfoInitializerFactory;
import uk.co.techblue.automation.dto.AutomationConstant;
import uk.co.techblue.automation.dto.AutomationExceptionConstant;
import uk.co.techblue.automation.dto.ExecutionContext;
import uk.co.techblue.automation.exception.AutomationExecutionException;
import uk.co.techblue.automation.modules.MasterProjectModule;
import uk.co.techblue.automation.modules.ProjectModule;

/**
 * The Class GenerateCommand.
 */
@Slf4j
public class GenerateCommand extends AbstractExecutionCommand {

    /** The Constant HYPHEN. */
    private static final String HYPHEN = "-";

    /**
     * Instantiates a new generate command.
     */
    public GenerateCommand() {
        super();
        commandName = AutomationConstant.COMMAND_GENERATE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.techblue.automation.interfaces.ExecutionCommand#execute(uk.co.techblue.automation.dto.ExecutionContext)
     */
    @Override
    public void execute(ExecutionContext context) {

        String projectBaseFolder = context.getExecutionPath();
        final MasterProjectModule masterProjectModule = (MasterProjectModule) ModuleGenerator.generateProjectModule(projectName, AutomationConstant.PACKAGING_POM, projectBaseFolder);
        masterProjectModule.setPom(projectGroupId, projectName, projectVersion);

        projectBaseFolder = masterProjectModule.getProjectFolder();
        
        final Map<String, String> moduleInfos = ModuleInfoInitializerFactory.fetchModuleInfoMap();
        final List<ProjectModule> projectModules = new ArrayList<ProjectModule>();
        for (final Map.Entry<String, String> moduleInfoEntrySet : moduleInfos.entrySet()) {
            final String moduleName = moduleInfoEntrySet.getKey();
            final String packaging = moduleInfoEntrySet.getValue();

            final ProjectModule projectModule = ModuleGenerator.generateProjectModule(moduleName, packaging, projectBaseFolder);
            projectModule.setPom(projectGroupId, projectName + HYPHEN + moduleName, projectVersion);
            projectModules.add(projectModule);
        }
        masterProjectModule.exportModules(projectModules);
        
        //Write pom for each and every module
        final MavenXpp3Writer mavenXpp3Writer = new MavenXpp3Writer();
        try {
            masterProjectModule.writeData(mavenXpp3Writer);
            for (final ProjectModule projectModule : projectModules) {
                projectModule.writeData(mavenXpp3Writer);
            }
        } catch (IOException ioException) {
            log.error("Error occurred while generating the project architecture , Error - ", ioException);
            throw new AutomationExecutionException(AutomationExceptionConstant.COULD_NOT_WRITE_FILE, ioException);
        }
    }

}
