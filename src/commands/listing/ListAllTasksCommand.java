package commands.listing;

import commands.BaseCommand;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Task;
import utils.ConstantHelpers;
import utils.ListingHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllTasksCommand extends BaseCommand {
    public ListAllTasksCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        return listAllTasks();
    }

    private String listAllTasks() {

        List<Task> allTasks = getTaskManagementSystemRepository().getAllTasks().stream().sorted(Comparator.comparing(Task::getDueDate)).collect(Collectors.toList());
        if (allTasks.isEmpty()) {
            return ConstantHelpers.NO_REGISTERED_TASKS;
        }
        return ListingHelpers.printTaskToString(allTasks);
    }

}
