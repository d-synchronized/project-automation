package uk.co.techblue.automation.interfaces;

import java.util.Map;

import uk.co.techblue.automation.dto.ExecutionContext;

/**
 * The Interface ExecutionCommand.
 */
public interface ExecutionCommand {

    /**
     * Gets the command name.
     * 
     * @return the command name
     */
    String getCommandName();

    /**
     * Sets the parameters.
     * 
     * @param commandParameters the command parameters
     */
    void setParameters(Map<String, String> commandParameters);

    /**
     * Execute.
     * 
     * @param context the context
     */
    void execute(ExecutionContext context);

}