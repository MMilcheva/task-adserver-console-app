package core;

import commands.contracts.Command;
import core.contracts.CommandFactory;
import core.contracts.TaskManagementSystemEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagementSystemEngineImpl implements TaskManagementSystemEngine {

    private static final String TERMINATION_COMMAND = "Exit";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";

    private final CommandFactory commandFactory;
    private final TaskManagementSystemRepositoryImpl taskManagementRepository;

    public TaskManagementSystemEngineImpl() {
        this.commandFactory = new CommandFactoryImpl();
        this.taskManagementRepository = new TaskManagementSystemRepositoryImpl();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isBlank()) {
                    System.out.println(EMPTY_COMMAND_ERROR);
                    continue;
                }
                if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(inputLine);
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex);
                }
            }
        }
    }

    private void processCommand(String inputLine) {
        String commandName = extractCommandName(inputLine);
        Command command = commandFactory.createCommandFromCommandName(commandName, taskManagementRepository);
        List<String> parameters = extractCommandParameters(inputLine);

        String executionResult = command.execute(parameters);
        System.out.println(executionResult);
    }


    private String extractCommandName(String inputLine) {
        return inputLine.split("@")[0];
    }


    private List<String> extractCommandParameters(String inputLine) {
        String[] commandParts = inputLine.split("@");
        List<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }

}
