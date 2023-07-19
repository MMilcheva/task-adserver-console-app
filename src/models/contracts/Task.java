package models.contracts;


import models.enums.Status;

import java.time.LocalDate;


public interface Task extends Identifiable, Printable {

    String getTitle();

    String getDescription();

    LocalDate getDueDate();

    Status getStatus();

    void changeStatus(Status status);

    void changeDescription(String description);

    void changeTitle(String title);

    void changeDueDate(LocalDate dueDate);

}
