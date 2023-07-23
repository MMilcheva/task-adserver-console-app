package commands.change;

import commands.BaseCommand;
import commands.create.CreateTaskCommand;
import core.TaskManagementSystemRepositoryImpl;
import core.contracts.TaskManagementSystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ConstantHelpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static utils.ConstantHelpers.TASK_CREATED_MESSAGE;

class ChangeTaskDescriptionCommandTest {

    private TaskManagementSystemRepository repository;
    private BaseCommand command;
    private List<String> parameters;
    private String description;

    private int id;

    @BeforeEach
    public void setupTest() {
        repository = new TaskManagementSystemRepositoryImpl();
        command = new ChangeTaskDescriptionCommand(repository);
        parameters = new ArrayList<>();
        description = "Changed task description";
        repository.createTask("Task 1fhhfgh", "Description 1", LocalDate.parse("2023-07-22"));
    }

    @Test
    void executeCommand_Should_ChangeTaskDescription_When_ValidParameters() {
        parameters.add(String.valueOf(1));
        parameters.add(description);

        String expectedMessage = String.format(ConstantHelpers.TASK_CHANGED_DESCRIPTION, 1, description);

        String actualMessage = command.executeCommand(parameters);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void executeCommand_Should_ThrowException_When_ParametersMissing() {
        parameters.add(description);
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(parameters));
    }


}