package commands.listing;

import commands.BaseCommand;
import core.TaskManagementSystemRepositoryImpl;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ListAllTasksCommandTest {
    private TaskManagementSystemRepository repository;
    private BaseCommand command;
    private List<Task> allTasks;
    private List<String> parameters;

    ListAllTasksCommandTest() {
    }

    @BeforeEach
    public void setupTest() {
        repository = new TaskManagementSystemRepositoryImpl();
        command = new ListAllTasksCommand(repository);
        allTasks = new ArrayList<>();
        parameters = new ArrayList<>();
        repository.createTask("Task 1fhhfgh", "Description 1", LocalDate.parse("2023-07-24"));
        repository.createTask("Task 3fhgfjfkk", "Description 3", LocalDate.parse("2023-07-21"));
        repository.createTask("Task 2fhgfjfkk", "Description 2", LocalDate.parse("2023-07-21"));
        repository.createTask("Task 4fhgfjfkk", "Description 4", LocalDate.parse("2023-07-21"));
    }

    @Test
    void executeCommand_Should_Return_AllTasks_SortedByDueDate_When_ListNotEmpty() {
        assertNotNull(allTasks);
        assertEquals(4, repository.getAllTasks().size(), () -> "two tasks should be returned, as two tasks are set in memory");
        String expectedOutput = "1.Task {id=3, title='Task 2fhgfjfkk', description='Description 2', status=New, dueDate=2023-07-21}"
                + System.lineSeparator() +
                "2.Task {id=2, title='Task 3fhgfjfkk', description='Description 3', status=New, dueDate=2023-07-21}"
                + System.lineSeparator() +
                "3.Task {id=4, title='Task 4fhgfjfkk', description='Description 4', status=New, dueDate=2023-07-21}"
                + System.lineSeparator() +
                "4.Task {id=1, title='Task 1fhhfgh', description='Description 1', status=New, dueDate=2023-07-24}";
        String actualTasks = command.executeCommand(parameters);
        assertEquals(expectedOutput, actualTasks);
    }
}