package uk.co.techblue.automation.core.command;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import uk.co.techblue.automation.dto.AutomationConstant;
import uk.co.techblue.automation.interfaces.ExecutionCommand;
import lombok.Getter;

/**
 * The Class AbstractExecutionCommand.
 */
public abstract class AbstractExecutionCommand implements ExecutionCommand {

    /** The parameters. */
    protected Map<String, String> parameters;

    /** The project name. */
    protected String projectName;

    /** The project group id. */
    protected String projectGroupId;

    /** The project version. */
    protected String projectVersion;

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.techblue.automation.interfaces.ExecutionCommand#getCommandName()
     */
    @Getter
    protected String commandName;

    /**
     * Gets the parameter names.
     * 
     * @return the parameter names
     */
    @Getter
    protected List<String> parameterNames;

    /**
     * Instantiates a new abstract execution command.
     */
    public AbstractExecutionCommand() {
        parameterNames = new LinkedList<String>();
        parameterNames.add(AutomationConstant.PROJECT_GROUP_ID);
        parameterNames.add(AutomationConstant.PROJECT_NAME);
        parameterNames.add(AutomationConstant.PROJECT_VERSION);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.techblue.automation.interfaces.ExecutionCommand#setParameters(java.util.Map)
     */
    @Override
    public void setParameters(Map<String, String> commandParameters) {
        this.projectGroupId = commandParameters.get(AutomationConstant.PROJECT_GROUP_ID);
        this.projectName = commandParameters.get(AutomationConstant.PROJECT_NAME);
        this.projectVersion = commandParameters.get(AutomationConstant.PROJECT_VERSION);
        this.parameters = commandParameters;
    }

}
