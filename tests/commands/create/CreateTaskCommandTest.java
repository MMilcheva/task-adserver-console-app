package commands.create;

import commands.BaseCommand;
import core.TaskManagementSystemRepositoryImpl;
import core.contracts.TaskManagementSystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static utils.ConstantHelpers.TASK_CREATED_MESSAGE;

class CreateTaskCommandTest {
    private TaskManagementSystemRepository repository;
    private BaseCommand command;
    private List<String> parameters;
    private String title;
    private String description;
    private LocalDate dueDate;


    @BeforeEach
    public void setupTest() {
        repository = new TaskManagementSystemRepositoryImpl();
        command = new CreateTaskCommand(repository);
        parameters = new ArrayList<>();
        title = "Task title test1";
        description = "Task description test1";
        dueDate = LocalDate.parse("2023-08-01");
    }

    @Test
    void executeCommand_Should_AddNewTaskToRepository_When_ValidParameters() {
        parameters.add(title);
        parameters.add(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dueDateStr = dueDate.format(formatter);
        parameters.add(dueDateStr);

        String expectedMessage = String.format(TASK_CREATED_MESSAGE, 1, title, description, dueDate, "New");

        String actualMessage = command.executeCommand(parameters);


        Assertions.assertEquals(expectedMessage, actualMessage);

    }

    @Test
    void executeCommand_Should_ThrowException_When_ParametersNotParsed() {
        parameters.add(title);
        parameters.add(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dueDateStr = dueDate.format(formatter);
        parameters.add(dueDateStr);
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(parameters));
    }

    @Test
    void executeCommand_Should_ThrowException_When_ParametersMissing() {
        parameters.add(title);
        parameters.add(description);
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(parameters));
    }


}