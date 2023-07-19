package commands.change;

import commands.BaseCommand;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Task;
import models.enums.Status;
import utils.ConstantHelpers;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;


public class ChangeTaskStatusCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private int id;
    private Status status;

    public ChangeTaskStatusCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return changeTaskStatus(this.id, this.status);

    }

    private String changeTaskStatus(int id, Status status) {

        Task taskChangeStatus = getTaskManagementSystemRepository().findTaskById(id);
        taskChangeStatus.changeStatus(status);
        return String.format(ConstantHelpers.TASK_CHANGED_STATUS, taskChangeStatus.getId(), status);
    }

    public void parseParameters(List<String> parameters) {
        try {
            this.id = ParsingHelpers.tryParseInteger(parameters.get(0), ConstantHelpers.INVALID_INPUT_MESSAGE);
            this.status = ParsingHelpers.tryParseEnum(parameters.get(1), Status.class, ConstantHelpers.INVALID_STATUS);
        } catch (Exception e) {
            throw new IllegalArgumentException(ConstantHelpers.INVALID_INPUT_MESSAGE);
        }
    }
}
