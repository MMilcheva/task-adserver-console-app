package utils;

import models.contracts.Printable;
import models.contracts.Task;
import models.contracts.User;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers {
    public static String printTaskToString(List<Task> tasks) {
        return elementsToString(tasks);
    }

    public static <T extends Printable> String elementsToString(List<T> elements) {
        List<String> result = new ArrayList<>();

        int counter = 1;

        for (T element : elements) {
            result.add(String.format("%d.%s", counter, element.toString()));
            counter++;
        }
        return String.join(System.lineSeparator(), result).trim();
    }

}
