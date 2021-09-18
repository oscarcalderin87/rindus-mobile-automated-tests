package pom.screens;

import framework.bases.po.BaseScreen;
import framework.context.TestContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Todo;
import org.openqa.selenium.By;
import pom.items.CreateTodoItem;
import pom.items.TodoItem;

import java.util.List;
import java.util.stream.Collectors;

public class TodosScreen extends BaseScreen {
    private static final By todosScreenTextBy = By.xpath("//*[@text=\"Todo's\"]");
    private static final By buttonAddBy = By.id("fab_add_item");
    private static final By createTodoLayoutBy = By.xpath("//android.widget.EditText[contains(@resource-id,'et_createTodo_content')]/parent::*/parent::*");
    private static final By todoListBy = By.id("rv_list_items");
    private static final By todoItemBy = By.className("android.widget.LinearLayout");
    private static final By messageBy = By.id("snackbar_text");


    public TodosScreen(final AppiumDriver<MobileElement> driver, final TestContext testContext) {
        super(driver, testContext);
    }

    public boolean todosScreenIsDisplayed() {
        return driver.findElement(todosScreenTextBy).isDisplayed();
    }

    public List<TodoItem> getTodosOnScreen() {
        MobileElement todoList = driver.findElement(todoListBy);
        return todoList.findElements(todoItemBy).stream().map(
                element -> new TodoItem(driver, testContext, element
                )).collect(Collectors.toList());
    }

    public TodoItem getFirstTodo() {
        return getTodosOnScreen().get(0);
    }

    public boolean isDisplayedTodoWithTitle() {
        TodoItem firstTodo = getFirstTodo();
        return firstTodo.getTitle().equals(testContext.getCreatedTodo().getTask());
    }

    public void createTask() {
        driver.findElement(buttonAddBy).click();
        String todo = "todo created for testing purpose";
        MobileElement todoLayout = waitForElementDisplayed(createTodoLayoutBy);
        CreateTodoItem createTodoItem = new CreateTodoItem(driver, testContext, todoLayout);
        createTodoItem.createTodo(todo);
        testContext.setCreatedTodo(new Todo(todo));
    }

    public void selectFirstTask() {
        TodoItem firstTodo = getFirstTodo();
        firstTodo.selectTask();
    }

    public boolean getFirstTaskStatus() {
        TodoItem firstTodo = getFirstTodo();
        return firstTodo.selected();
    }

    public void deleteFirstTask() {
        MobileElement todoList = driver.findElement(todoListBy);
        MobileElement firstTodo = todoList.findElements(todoItemBy).get(0);
        swipeElementToLeft(firstTodo);
    }

    public String getMessage() {
        waitForElementDisplayed(messageBy);
        return driver.findElement(messageBy).getText();
    }
}
