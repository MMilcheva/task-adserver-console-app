package commands.create;

import commands.BaseCommand;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Task;
import utils.ConstantHelpers;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static utils.ConstantHelpers.TASK_CREATED_MESSAGE;

/**
 * The class creates a Task with the given user input parameters
 */
public class CreateTaskCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private String title;
    private String description;
    private LocalDate dueDate;

    public CreateTaskCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    /**
     * @param parameters represent the user input data
     * @return String message with the newly created task`s details
     */
    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        Task task = getTaskManagementSystemRepository().createTask(this.title, this.description, this.dueDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dueDate = task.getDueDate().format(formatter);
        return String.format(TASK_CREATED_MESSAGE, task.getId(), task.getTitle(), task.getDescription(), dueDate, task.getStatus());
    }

    /**
     *
     * @param parameters
     */
    public void parseParameters(List<String> parameters) {
        try {
            this.title = parameters.get(0);
            this.description = parameters.get(1);
            this.dueDate = ParsingHelpers.tryParseLocalDate(parameters.get(2), ConstantHelpers.INVALID_INPUT_MESSAGE);
        } catch (Exception e) {
            throw new IllegalArgumentException(ConstantHelpers.INVALID_INPUT_MESSAGE);
        }
    }
}
