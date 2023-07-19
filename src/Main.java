import core.TaskManagementSystemEngineImpl;
import core.contracts.TaskManagementSystemEngine;

public class Main {
    public static void main(String[] args) {
        TaskManagementSystemEngine tm = new TaskManagementSystemEngineImpl();
        tm.start();

    }
}