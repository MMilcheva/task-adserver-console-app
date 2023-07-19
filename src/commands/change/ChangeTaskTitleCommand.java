package commands.change;

import commands.BaseCommand;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Task;
import models.enums.Status;
import utils.ConstantHelpers;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;


public class ChangeTaskTitleCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private int id;
    private String title;

    public ChangeTaskTitleCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return changeTaskTitle(this.id, this.title);

    }

    private String changeTaskTitle(int id, String title) {

        Task taskChangeTitle = getTaskManagementSystemRepository().findTaskById(id);
        taskChangeTitle.changeTitle(title);
        return String.format(ConstantHelpers.TASK_CHANGED_TITLE, taskChangeTitle.getId(), title);
    }

    public void parseParameters(List<String> parameters) {
        try {
            this.id = ParsingHelpers.tryParseInteger(parameters.get(0), ConstantHelpers.INVALID_INPUT_MESSAGE);
            this.title = parameters.get(1);
        } catch (Exception e) {
            throw new IllegalArgumentException(ConstantHelpers.INVALID_INPUT_MESSAGE);
        }
    }
}
