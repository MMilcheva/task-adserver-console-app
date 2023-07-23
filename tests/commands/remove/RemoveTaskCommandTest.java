package commands.remove;

import commands.BaseCommand;
import core.TaskManagementSystemRepositoryImpl;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ConstantHelpers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class RemoveTaskCommandTest {
    private TaskManagementSystemRepository repository;
    private BaseCommand command;
    private List<Task> allTasks;
    private List<String> parameters;

    @BeforeEach
    public void setupTest() {
        repository = new TaskManagementSystemRepositoryImpl();
        command = new RemoveTaskCommand(repository);
        allTasks = new ArrayList<>();
        parameters = new ArrayList<>();
        repository.createTask("Task 1fhhfgh", "Description 1", LocalDate.parse("2023-07-22"));
        repository.createTask("Task 2fhgfjfkk", "Description 2", LocalDate.parse("2023-07-20"));
    }


    @Test
    void executeCommand_Should_RemoveTaskFromRepository_When_ValidParameter() {
        parameters.add(String.valueOf(2L));
        String expectedMessage = String.format(ConstantHelpers.TASK_DELETED_MESSAGE, "Task 2fhgfjfkk");
        String actualMessage = command.executeCommand(parameters);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}