package uk.co.techblue.automation.core.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.techblue.automation.core.ModuleGenerator;
import uk.co.techblue.automation.core.ModuleInfoInitializerFactory;
import uk.co.techblue.automation.dto.AutomationConstant;
import uk.co.techblue.automation.dto.ExecutionContext;
import uk.co.techblue.automation.modules.ProjectModule;

/**
 * The Class GenerateCommand.
 */
public class GenerateCommand extends AbstractExecutionCommand {

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
        final Map<String,String> moduleInfos = ModuleInfoInitializerFactory.fetchModuleInfoMap();
        final List<ProjectModule> projectModules = new ArrayList<ProjectModule>();
        for(final Map.Entry<String,String> moduleInfoEntrySet : moduleInfos.entrySet()){
            final String moduleName = moduleInfoEntrySet.getKey();
            final String packaging = moduleInfoEntrySet.getValue();
            final ProjectModule projectModule = ModuleGenerator.generateProjectModule(moduleName, packaging);
            projectModules.add(projectModule);
        }
    }

}
