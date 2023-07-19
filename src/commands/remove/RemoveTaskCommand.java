package commands.remove;

import commands.BaseCommand;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Task;
import utils.ConstantHelpers;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class RemoveTaskCommand extends BaseCommand {

    public static final String INVALID_INPUT_MESSAGE = "Invalid input. Expected a number.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public RemoveTaskCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInteger(parameters.get(0), ConstantHelpers.INVALID_INPUT_MESSAGE);

        return removeTask(id);
    }

    private String removeTask(int id) {

        Task taskToDelete = getTaskManagementSystemRepository().findTaskById(id);

        getTaskManagementSystemRepository().removeTask(taskToDelete);
        return String.format(ConstantHelpers.TASK_DELETED_MESSAGE, taskToDelete.getTitle());
    }
}
