package steps_definitions;

import framework.context.TestContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pom.screens.TodosScreen;

public class TodosScreen_Steps {
    private final AppiumDriver<MobileElement> driver;
    private final TodosScreen todosScreen;
    private final TestContext testContext;

    public TodosScreen_Steps(final TestContext testContext) {
        driver = testContext.getDriverManager().getDriver();
        todosScreen = new TodosScreen(driver, testContext);
        this.testContext = testContext;
    }

    @Then("The Todo's screen is displayed")
    public void theTodoSScreenIsDisplayed() {
        Assert.assertTrue(todosScreen.todosScreenIsDisplayed(),
                "The user should be on the Todo's screen, but it is not");
    }

    @And("The user creates a new task")
    public void theUserCreatesANewTask() {
        todosScreen.createTask();
    }

    @And("The new task is displayed in the list")
    public void theNewTaskIsDisplayedInTheList() {
        Assert.assertTrue(todosScreen.isDisplayedTodoWithTitle(),
                "The task with title: '" + testContext.getCreatedTodo().getTask() + ", should be present on the Todo's List," +
                        "but it is not");
    }

    @And("The user selects the first task")
    public void theUserSelectsTheFirstTask() {
        todosScreen.selectFirstTask();
    }

    @And("The first task is selected")
    public void theFirstTaskIsSelected() {
        Assert.assertTrue(todosScreen.getFirstTaskStatus(),
                "The first task should be selected, but it is not");
    }

    @And("The user deletes the created task")
    public void theUserDeletesTheCreatedTask() {
        todosScreen.deleteFirstTask();
    }

    @And("The created task was removed from the list")
    public void theCreatedTaskWasRemovedFromTheList() {
        Assert.assertFalse(todosScreen.isDisplayedTodoWithTitle(),
                "The task with title: '" + testContext.getCreatedTodo().getTask() + ", should not be present on the Todo's List," +
                        "but it is");
    }

    @Then("A message with the text {string} is displayed")
    public void aMessageWithTheTextIsDisplayed(final String message) {
        Assert.assertEquals(todosScreen.getMessage(), message,
                "A message with the text: '" + message + "' should be displayed, but it is not");
    }
}
