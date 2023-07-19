package commands.change;

import commands.BaseCommand;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Task;
import utils.ConstantHelpers;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class ChangeTaskDueDateCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private int id;
    private LocalDate dueDate;

    public ChangeTaskDueDateCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return changeTaskDueDate(this.id, this.dueDate);

    }

    private String changeTaskDueDate(int id, LocalDate dueDate) {

        Task taskChangeDueDate = getTaskManagementSystemRepository().findTaskById(id);
        taskChangeDueDate.changeDueDate(dueDate);
        return String.format(ConstantHelpers.TASK_CHANGED_DUEDATE, taskChangeDueDate.getId(), dueDate);
    }

    public void parseParameters(List<String> parameters) {
        try {
            this.id = ParsingHelpers.tryParseInteger(parameters.get(0), ConstantHelpers.INVALID_INPUT_MESSAGE);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.dueDate = LocalDate.parse(parameters.get(1), formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException(ConstantHelpers.INVALID_INPUT_MESSAGE);
        }
    }
}
