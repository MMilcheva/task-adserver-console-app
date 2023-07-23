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
        repository.createTask("Task 1fhhfgh", "Description 1", LocalDate.parse("2023-07-22"));
        repository.createTask("Task 2fhgfjfkk", "Description 2", LocalDate.parse("2023-07-20"));
    }

    @Test
    void executeCommand_Should_Return_AllTasks_Sorted_When_ListNotEmpty() {
        assertNotNull(allTasks);
        assertEquals(2, repository.getAllTasks().size());
        String expectedOutput = "1.Task {id=2, title='Task 2fhgfjfkk', description='Description 2', status=New, dueDate=2023-07-20}" +
                System.lineSeparator()+
                "2.Task {id=1, title='Task 1fhhfgh', description='Description 1', status=New, dueDate=2023-07-22}";
        String actualTasks = command.executeCommand(parameters);
        assertEquals(expectedOutput, actualTasks);
    }
}