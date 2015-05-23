package uk.co.techblue.automation.core;

import java.util.Map;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import uk.co.techblue.automation.core.command.GenerateCommand;
import uk.co.techblue.automation.dto.AutomationConstant;
import uk.co.techblue.automation.dto.ExecutionContext;
import uk.co.techblue.automation.interfaces.ExecutionCommand;

/** The Constant log. */

/** The Constant log. */
@Slf4j
public class AutomationExecutor {

    /** The execution context. */
    private ExecutionContext executionContext;

    /**
     * Gets the execution command.
     * 
     * @return the execution command
     */
    @Getter
    private ExecutionCommand executionCommand;

    /**
     * Instantiates a new automation executor.
     * 
     * @param executionContext the execution context
     */
    public AutomationExecutor(final ExecutionContext executionContext) {
        this.executionContext = executionContext;
        initialize();
    }

    /**
     * Initialize.
     */
    private void initialize() {
        final String commandName = executionContext.getCommand();
        final Map<String, String> executionParams = executionContext.getParameters();

        log.info("Initializing automation executor , execution command '{}' , command parameters '{}'", commandName, executionParams);
        final ExecutionCommand executionCommand = getExecutionCommand(commandName);
        executionCommand.setParameters(executionParams);
        this.executionCommand = executionCommand;
        log.info("Automation Executor successfully initialized");
    }

    /**
     * Gets the execution command.
     * 
     * @param commandName the command name
     * @return the execution command
     */
    private ExecutionCommand getExecutionCommand(final String commandName) {
        log.info("Obtaining execution command against the supplied command name '{}'", commandName);
        switch (commandName) {
            case AutomationConstant.COMMAND_GENERATE:
                final ExecutionCommand generateCommand = new GenerateCommand();
                return generateCommand;

            default:
                return null;
        }
    }

    /**
     * Execute command.
     * 
     * @return the boolean
     */
    public Boolean executeCommand() {
        executionCommand.execute(executionContext);
        return true;
    }

}
