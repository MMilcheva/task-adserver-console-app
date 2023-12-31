package commands.create;

import commands.BaseCommand;
import core.TaskManagementSystemRepositoryImpl;
import core.contracts.TaskManagementSystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static utils.ConstantHelpers.USER_CREATED_MESSAGE;

class CreateUserCommandTest {
    private TaskManagementSystemRepository repository;
    private BaseCommand command;
    private List<String> parameters;
    private String userName;

    @BeforeEach
    public void setupTest() {
        repository = new TaskManagementSystemRepositoryImpl();
        command = new CreateUserCommand(repository);
        parameters = new ArrayList<>();
        userName = "Petar Petrov";
    }

    @Test
    void executeCommand_Should_AddNewUserToRepository_When_ValidParameters() {
        parameters.add("Petar Petrov");

        String expectedMessage = String.format(USER_CREATED_MESSAGE, this.userName);
        String actualMessage = command.executeCommand(parameters);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void executeCommand_Should_ThrowException_When_ParametersMissing() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.executeCommand(parameters));
    }

}