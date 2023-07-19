package core.contracts;

import models.contracts.Task;
import models.contracts.User;

import java.time.LocalDate;
import java.util.List;


public interface TaskManagementSystemRepository {
    User createUser(String userName);

    Task createTask(String title, String description, LocalDate dueDate);

    List<Task> getAllTasks();

    Task findTaskById(int id);

    void removeTask(Task taskToDelete);
}
