package uk.co.techblue.automation.core.command;

import java.util.Map;

import uk.co.techblue.automation.core.ModuleInfoInitializerFactory;
import uk.co.techblue.automation.dto.AutomationConstant;
import uk.co.techblue.automation.dto.ExecutionContext;

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
        System.out.println(moduleInfos);
    }

}
