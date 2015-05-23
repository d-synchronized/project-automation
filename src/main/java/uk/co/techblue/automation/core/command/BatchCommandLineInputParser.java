package uk.co.techblue.automation.core.command;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import uk.co.techblue.automation.dto.ExecutionContext;

/**
 * The Class BatchCommandLineInputParser.
 */
public class BatchCommandLineInputParser {

    /**
     * Parses the input.
     * 
     * @param commandLineParams the command line params
     * @param context the context
     * @return the execution context
     */
    public static ExecutionContext parseInput(String[] commandLineParams, ExecutionContext context) {
        context.setCommand(commandLineParams[0]);
        Map<String, String> commandParameters = new HashMap<String, String>();
        for (int i = 1; i < commandLineParams.length; i++) {
            Map.Entry<String, String> parameters = parseParametersFromString(commandLineParams[i]);
            commandParameters.put(parameters.getKey(), parameters.getValue());
        }
        context.setParameters(commandParameters);
        return context;
    }

    /**
     * Parses the parameters from string.
     * 
     * @param parameterString the parameter string
     * @return the map. entry
     */
    private static Map.Entry<String, String> parseParametersFromString(String parameterString) {
        String parameterKey = null;
        String parameterValue = null;
        if (parameterString.startsWith("-")) {
            parameterKey = parameterString.substring(1, parameterString.indexOf("="));
            parameterValue = parameterString.substring(parameterString.indexOf("=") + 1, parameterString.length());
        }
        Map.Entry<String, String> parameter = new AbstractMap.SimpleEntry<String, String>(parameterKey, parameterValue);
        return parameter;
    }

}
