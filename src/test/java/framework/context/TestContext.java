package framework.context;

import framework.utils.driverManager.DriverManager;
import models.Todo;
import models.User;

public class TestContext {
    private final DriverManager driverManager;
    private User selectedUser;
    private Todo createdTodo;

    public TestContext() {
        driverManager = new DriverManager();
    }

    public DriverManager getDriverManager() {
        return driverManager;
    }

    public void setSelectedUser(final User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public Todo getCreatedTodo() {
        return createdTodo;
    }

    public void setCreatedTodo(final Todo createdTodo) {
        this.createdTodo = createdTodo;
    }
}
