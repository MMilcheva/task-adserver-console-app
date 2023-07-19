package commands.change;

import commands.BaseCommand;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Task;
import models.enums.Status;
import utils.ConstantHelpers;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;


public class ChangeTaskDescriptionCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private int id;
    private String description;


    public ChangeTaskDescriptionCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        return changeTaskDescription(this.id, this.description);
    }

    private String changeTaskDescription(int id, String description) {

        Task taskChangeDescription = getTaskManagementSystemRepository().findTaskById(id);
        taskChangeDescription.changeDescription(description);
        return String.format(ConstantHelpers.TASK_CHANGED_DESCRIPTION, taskChangeDescription.getId(), description);
    }

    public void parseParameters(List<String> parameters) {
        try {
            this.id = ParsingHelpers.tryParseInteger(parameters.get(0), ConstantHelpers.INVALID_INPUT_MESSAGE);
            this.description = parameters.get(1);
        } catch (Exception e) {
            throw new IllegalArgumentException(ConstantHelpers.INVALID_INPUT_MESSAGE);
        }
    }
}
