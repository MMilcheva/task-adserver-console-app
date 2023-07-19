package core;

import commands.remove.RemoveTaskCommand;
import commands.change.ChangeTaskDescriptionCommand;
import commands.change.ChangeTaskDueDateCommand;
import commands.change.ChangeTaskStatusCommand;
import commands.change.ChangeTaskTitleCommand;
import commands.contracts.Command;
import commands.create.CreateTaskCommand;
import commands.create.CreateUserCommand;
import commands.enums.CommandType;
import commands.listing.ListAllTasksCommand;
import core.contracts.CommandFactory;
import core.contracts.TaskManagementSystemRepository;
import utils.ConstantHelpers;
import utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {


    public Command createCommandFromCommandName(String commandName, TaskManagementSystemRepository taskManagementRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandName, CommandType.class, String.format(ConstantHelpers.INVALID_COMMAND, commandName));

        switch (commandType) {
            case CREATEUSER:
                return new CreateUserCommand(taskManagementRepository);
            case CREATETASK:
                return new CreateTaskCommand(taskManagementRepository);
            case CHANGETASKSTATUS:
                return new ChangeTaskStatusCommand(taskManagementRepository);
            case CHANGETASKTITLE:
                return new ChangeTaskTitleCommand(taskManagementRepository);
            case CHANGETASKDESCRIPTION:
                return new ChangeTaskDescriptionCommand(taskManagementRepository);
            case CHANGETASKDUEDATE:
                return new ChangeTaskDueDateCommand(taskManagementRepository);
            case LISTALLTASKS:
                return new ListAllTasksCommand(taskManagementRepository);
            case REMOVETASK:
                return new RemoveTaskCommand(taskManagementRepository);
            default:
                throw new IllegalArgumentException(String.format(ConstantHelpers.INVALID_COMMAND, commandName));
        }
    }

}