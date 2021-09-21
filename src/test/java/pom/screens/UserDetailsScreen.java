package pom.screens;

import framework.bases.po.BaseScreen;
import framework.context.TestContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pom.items.TodoItem;

import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsScreen extends BaseScreen {
    private static final String LAST_TODO_TEXT = "et porro tempora";

    private static final By userDetailsScreenTextBy = By.xpath("//*[@text='User details']");
    private static final By nameBy = By.id("tv_userDetail_name");
    private static final By usernameBy = By.id("tv_userDetail_username");
    private static final By companyBy = By.id("tv_userDetail_company");
    private static final By goBackButtonBy = MobileBy.AccessibilityId("Navigate up");
    private static final By albumsSectionTitleBy = MobileBy.AndroidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
                    ".scrollIntoView(new UiSelector().resourceIdMatches(\".*tv_userDetail_albums_title.*\"))");
    private static final By todosSectionTitleBy = MobileBy.AndroidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
                    ".scrollIntoView(new UiSelector().resourceIdMatches(\".*tv_userDetail_todos_title.*\"))");
    private static final By postsSectionTitleBy = MobileBy.AndroidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
                    ".scrollIntoView(new UiSelector().resourceIdMatches(\".*tv_userDetail_posts_title.*\"))");
    private static final By todosListBy = MobileBy.AndroidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
                    ".scrollIntoView(new UiSelector().resourceIdMatches(\".*rv_userDetail_todos.*\"))");
    private static final By todoLayoutBy = By.xpath("//android.widget.CheckBox[contains(@resource-id,'cb_todo_checked')]/parent::*/parent::*");




    public UserDetailsScreen(final AppiumDriver<MobileElement> driver, final TestContext testContext) {
        super(driver, testContext);
    }

    public boolean userDetailsScreenIsDisplayed() {
        return driver.findElement(userDetailsScreenTextBy).isDisplayed();
    }

    public String getName() {
        return driver.findElement(nameBy).getText();
    }

    public String getUserName() {
        return driver.findElement(usernameBy).getText();
    }

    public String getCompany() {
        return driver.findElement(companyBy).getText();
    }

    public void goBack() {
        driver.findElement(goBackButtonBy).click();
    }

    public boolean isDisplayedSection(String section) {
        boolean displayed;
        switch (section) {
            case "Albums":
                displayed = albumSectionIsDisplayed();
                break;
            case "Todo's":
                displayed = todosSectionIsDisplayed();
                break;
            case "Posts":
                displayed = postsSectionIsDisplayed();
                break;
            default:
                throw new IllegalStateException("Invalid section: " + section);
        }
        return displayed;
    }

    public void goToSection(String section) {
        By sectionSelector;
        switch (section) {
            case "Albums":
                sectionSelector = albumsSectionTitleBy;
                break;
            case "Todo's":
                sectionSelector = todosSectionTitleBy;
                break;
            case "Posts":
                sectionSelector = postsSectionTitleBy;
                break;
            default:
                throw new IllegalStateException("Invalid section: " + section);
        }
        driver.findElement(sectionSelector).click();
    }

    public boolean albumSectionIsDisplayed() {
        return driver.findElement(albumsSectionTitleBy).isDisplayed();
    }

    public boolean todosSectionIsDisplayed() {
        return driver.findElement(todosSectionTitleBy).isDisplayed();
    }

    public boolean postsSectionIsDisplayed() {
        return driver.findElement(postsSectionTitleBy).isDisplayed();
    }

    public List<TodoItem> getTodosOnScreen() {
        return driver.findElements(todoLayoutBy).stream().map(
                element -> new TodoItem(driver, testContext, element
                )).collect(Collectors.toList());
    }

    public TodoItem getLastTodo() {
        scrollListToLeft(todosListBy, LAST_TODO_TEXT);
        List<TodoItem> todos = getTodosOnScreen();
        return todos.get(todos.size() - 1);
    }
    public void selectLastTodo() {
        TodoItem lastTodo = getLastTodo();
        lastTodo.selectTask();
    }

    public boolean isLastTodoSelected() {
        TodoItem lastTodo = getLastTodo();
        return lastTodo.selected();
    }

}
