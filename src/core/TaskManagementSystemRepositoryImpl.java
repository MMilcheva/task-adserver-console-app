package core;

import core.contracts.TaskManagementSystemRepository;
import exceptions.ElementNotFoundException;
import models.TaskImpl;
import models.UserImpl;
import models.contracts.Identifiable;
import models.contracts.Task;
import models.contracts.User;
import models.enums.Status;
import utils.ConstantHelpers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    private final List<User> allUsers;
    private final List<Task> allTasks;
    private int nextId;

    public TaskManagementSystemRepositoryImpl() {
        nextId = 0;
        this.allUsers = new ArrayList<>();
        this.allTasks = new ArrayList<>();
    }

    @Override
    public User createUser(String userName) {
        if (allUsers.stream().map(models.contracts.User::getName).anyMatch(n -> n.equals(userName))) {
            throw new IllegalArgumentException(String.format("This user %s already exists in the system!", userName));
        }
        User user = new UserImpl(userName);
        allUsers.add(user);
        return user;
    }

    @Override
    public Task createTask(String title, String description, LocalDate dueDate) {
        if (allTasks.stream().map(models.contracts.Task::getTitle).anyMatch(t -> t.equals(title))) {
            throw new IllegalArgumentException(String.format("This title %s already exists in the system!", title));
        }
        Task task = new TaskImpl(++nextId, title, description, dueDate);
        allTasks.add(task);
        return task;
    }
    @Override
    public void removeTask(Task task) {
        if (allTasks.size() == 0) {
            throw new IllegalArgumentException(ConstantHelpers.NO_TASK_TO_REMOVE);
        }
        allTasks.remove(task);
    }
    public List<Task> getAllTasks() {
        return new ArrayList<>(allTasks);
    }
    @Override
    public Task findTaskById(int id) {
        return findElementById(getAllTasks(), id);
    }
    private <T extends Identifiable> T findElementById(List<T> elements, int id) {
        for (T element : elements) {
            if (element.getId() == id) {
                return element;
            }
        }
        throw new ElementNotFoundException(String.format("No record with ID %d", id));
    }

}
