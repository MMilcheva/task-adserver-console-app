package models;

import models.contracts.Identifiable;
import models.contracts.Printable;
import models.contracts.Task;
import models.enums.Status;
import utils.ValidationHelpers;

import java.time.LocalDate;
import java.util.Objects;

import static models.enums.Status.*;

public class TaskImpl implements Task, Identifiable, Printable {

    public static final int TITLE_MIN_LENGTH = 10;
    public static final int TITLE_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MAX_LENGTH = 500;
    private int id = 0;
    private String title;
    private String description;
    private Status status;
    private LocalDate dueDate;

    public TaskImpl(int id, String title, String description, LocalDate dueDate) {
        setId(id);
        setTitle(title);
        setDescription(description);
        this.dueDate = dueDate;
        setStatus(NEW);
    }

    @Override
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        ValidationHelpers.validateStringLength(title, TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, "Title");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateStringLength(description, DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH, "Description");
        this.description = description;
    }
    @Override
    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void changeTitle(String title) {

        if (Objects.equals(this.getTitle(), title)) {
            throw new IllegalArgumentException(String.format("%s is already %s", getTitle(), title));
        }
        setTitle(title);
    }

    @Override
    public void changeDescription(String description) {
        if (Objects.equals(this.getDescription(), description)) {
            throw new IllegalArgumentException(String.format("%s is already %s", getDescription(), description));
        }
        setDescription(description);
    }
    @Override
    public void changeDueDate(LocalDate dueDate) {
        if (Objects.equals(this.getDueDate(), dueDate)) {
            throw new IllegalArgumentException(String.format("%s is already %s", getDueDate(), dueDate));
        }
        this.dueDate=dueDate;
    }
    @Override
    public void changeStatus(Status status) {
        if (this.getStatus() == status) {
            throw new IllegalArgumentException(String.format("Task with title %s is already at status: %s", getTitle(), getStatus()));
        }
        setStatus(status);
    }

    @Override
    public String toString() {
        return "Task {" + "id=" + id + ", title='" + title + '\'' + ", description='" + description + '\'' + ", status=" + status + ", dueDate=" + dueDate + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskImpl task = (TaskImpl) o;
        return id == task.id &&
                Objects.equals(title, task.title) &&
                Objects.equals(description, task.description) &&
                Objects.equals(dueDate, task.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, dueDate);
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TaskImpl task = (TaskImpl) o;
//        return dueDate.equals(task.dueDate);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(dueDate);
//    }
}
