package uk.co.techblue.automation.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionContext {

    /** The command. */
    String command;

    /** The parameters. */
    Map<String, String> parameters;

    /** The execution path. */
    String executionPath;

}
