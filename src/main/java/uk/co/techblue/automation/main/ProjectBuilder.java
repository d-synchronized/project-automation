package uk.co.techblue.automation.main;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import uk.co.techblue.automation.core.AutomationExecutor;
import uk.co.techblue.automation.core.BatchCommandLineInputParser;
import uk.co.techblue.automation.core.ModuleInfoInitializerFactory;
import uk.co.techblue.automation.dto.ExecutionContext;

/** The Constant log. */

/** The Constant log. */
@Slf4j
public class ProjectBuilder {

    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(final String args[]) {
        log.info("Welcome to project automation console. Creating an enterprise project is time consuming for you ? You are at right PLACE!");
        if (args.length == 1 && "--help".equals(args[0])) {
            log.info("Commands for running automation builder goes here");
        }
        Scanner userInput = new Scanner(System.in);
        System.out.println("How many modules do u want in your project ?");
        final String numberOfModuleInput = userInput.nextLine();
        Integer numberOfModules = NumberUtils.toInt(numberOfModuleInput);
        if (numberOfModules == 0) {
            log.error("Invalid modules value. Please try again.");
            userInput.close();
            return;
        }
        initializeModuleInfo(userInput, numberOfModules);
        executeAutomationCommand(userInput);
        userInput.close();
        log.info("Project successfully created");
    }

    /**
     * Execute automation command.
     * 
     * @param userInput the user input
     */
    private static void executeAutomationCommand(Scanner userInput) {
        while (true) {
            System.out.println("Do you want to generate the project now ? Enter generation command");
            final String projectGenerationCommand = userInput.nextLine();
            if (StringUtils.isBlank(projectGenerationCommand)) {
                log.error("Invalid command/usage . try again");
                continue;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(projectGenerationCommand, " ");
            if (stringTokenizer.countTokens() == 4) {
                final String[] generateCommandTokens = new String[stringTokenizer.countTokens()];
                int tokenNumber = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    final String generateCommandToken = stringTokenizer.nextToken();
                    generateCommandTokens[tokenNumber] = generateCommandToken;
                    tokenNumber++;
                }

                final ExecutionContext executionContext = new ExecutionContext();
                BatchCommandLineInputParser.parseInput(generateCommandTokens, executionContext);
                
                Path currentRelativePath = Paths.get("");
                String currentPath = currentRelativePath.toAbsolutePath().toString();
                executionContext.setExecutionPath(currentPath);
                
                AutomationExecutor automationExecutor = new AutomationExecutor(executionContext);
                automationExecutor.executeCommand();
                break;
            } else {
                log.error("Invalid command / usage. Please try again");
                continue;
            }
        }
    }

    /**
     * Initialize module info.
     * 
     * @param userInput the user input
     * @param numberOfModules the number of modules
     */
    private static void initializeModuleInfo(Scanner userInput, Integer numberOfModules) {
        int i = 1;
        while (numberOfModules > 0) {
            System.out.println("Enter your module '" + i + "' name and packaging info separated by comma");
            final String moduleNameAndVersion = userInput.nextLine();
            final StringTokenizer stringTokenizer = new StringTokenizer(moduleNameAndVersion, ",");
            final int tokens = stringTokenizer.countTokens();

            if (tokens != 2) {
                log.error("Invalid invalid , please specify the module name and version properly ");
                continue;
            }

            String moduleName = null;
            String packaging = null;
            int tokenPart = 0;
            while (stringTokenizer.hasMoreTokens()) {
                if (tokenPart == 0) {
                    moduleName = stringTokenizer.nextToken();
                } else {
                    packaging = stringTokenizer.nextToken();
                }
                tokenPart++;
            }
            ModuleInfoInitializerFactory.addModuleInfo(moduleName, packaging);
            i++;
            numberOfModules--;
        }
    }

}
