package commands.create;

import commands.BaseCommand;
import core.contracts.TaskManagementSystemRepository;
import utils.ValidationHelpers;

import java.util.List;

import static utils.ConstantHelpers.USER_CREATED_MESSAGE;

public class CreateUserCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private String name;


    public CreateUserCommand(TaskManagementSystemRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        this.name = parameters.get(0);
        getTaskManagementSystemRepository().createUser(this.name);

        return String.format(USER_CREATED_MESSAGE, this.name);

    }
}