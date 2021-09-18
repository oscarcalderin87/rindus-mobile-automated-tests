package steps_definitions;

import framework.context.TestContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pom.screens.UsersScreen;

public class UsersScreen_Steps {
    private final AppiumDriver<MobileElement> driver;
    private final UsersScreen usersScreen;

    public UsersScreen_Steps(final TestContext testContext) {
        driver = testContext.getDriverManager().getDriver();
        usersScreen = new UsersScreen(driver, testContext);
    }

    @Given("The Users screen is displayed")
    public void theUsersScreenIsDisplayed() {
        Assert.assertTrue(usersScreen.usersScreenIsDisplayed(),
                "The user should be on the Users screen, but it is not");
    }

    @When("The user selects the last user")
    public void theUserSelectsTheLastUser() {
        usersScreen.selectLastUser();
    }

    @Given("The user selects the first user")
    public void theUserSelectsTheFirstUser() {
        usersScreen.selectFirstUser();
    }

}
