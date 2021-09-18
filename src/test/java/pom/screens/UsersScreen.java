package pom.screens;

import framework.bases.po.BaseScreen;
import framework.context.TestContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.User;
import org.openqa.selenium.By;
import pom.items.UserItem;

import java.util.List;
import java.util.stream.Collectors;

public class UsersScreen extends BaseScreen {
    private static final By userItemBy = By.id("cl_user_item");
    private static final By usersScreenTextBy = By.xpath("//*[@text='Users']");

    public UsersScreen(final AppiumDriver<MobileElement> driver, final TestContext testContext) {
        super(driver, testContext);
    }

    public List<UserItem> getUsersOnScreen() {
        return driver.findElements(userItemBy).stream().map(
                element -> new UserItem(driver, testContext, element
        )).collect(Collectors.toList());
    }

    public UserItem getLastUser() {
        scrollToEnd();
        List<UserItem> users = getUsersOnScreen();
        return users.get(users.size() - 1);
    }

    public UserItem getFirstUser() {
        List<UserItem> users = getUsersOnScreen();
        return users.get(0);
    }

    public boolean usersScreenIsDisplayed() {
        return driver.findElement(usersScreenTextBy).isDisplayed();
    }

    public void selectUser(UserItem userItem) {
        testContext.setSelectedUser(
                new User(
                        userItem.getName(),
                        userItem.getUserName(),
                        userItem.getCompany()
                )
        );
        userItem.select();
    }

    public void selectFirstUser() {
        UserItem firstUser = getFirstUser();
        selectUser(firstUser);
    }

    public void selectLastUser() {
        UserItem lastUser = getLastUser();
        selectUser(lastUser);
    }

    public void selectSecondUser() {
        UserItem lastUser = getLastUser();
        selectUser(lastUser);
    }
}
