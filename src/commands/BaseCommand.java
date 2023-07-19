package commands;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public abstract class BaseCommand implements Command {


    private static TaskManagementSystemRepository taskManagementSystemRepository;

    public BaseCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        BaseCommand.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    protected static TaskManagementSystemRepository getTaskManagementSystemRepository() {
        return taskManagementSystemRepository;
    }


    public abstract String executeCommand(List<String> parameters);

    @Override
    public String execute(List<String> parameters) {
        return executeCommand(parameters);
    }
}
