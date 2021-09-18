package steps_definitions;

import framework.context.TestContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.User;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pom.screens.UserDetailsScreen;

import java.util.List;

public class UserDetailsScreen_Steps {
    private final AppiumDriver<MobileElement> driver;
    private final UserDetailsScreen userDetailsScreen;
    private final SoftAssert softAssert;
    private final TestContext testContext;

    public UserDetailsScreen_Steps(final TestContext testContext) {
        driver = testContext.getDriverManager().getDriver();
        userDetailsScreen = new UserDetailsScreen(driver, testContext);
        softAssert = new SoftAssert();
        this.testContext = testContext;
    }

    @Then("The details about the selected user are shown")
    public void theDetailsAboutTheSelectedUserAreShown() {
        User selectedUser = testContext.getSelectedUser();
        softAssert.assertEquals(userDetailsScreen.getName(), selectedUser.getName(),
                "The expected name should be '" + selectedUser.getName() + "', but it is '" + userDetailsScreen.getName() + "'");
        softAssert.assertEquals(userDetailsScreen.getUserName(), selectedUser.getUsername(),
                "The expected username should be '" + selectedUser.getUsername() + "', but it is '" + userDetailsScreen.getUserName() + "'");
        softAssert.assertEquals(userDetailsScreen.getCompany(), selectedUser.getCompany(),
                "The expected company should be '" + selectedUser.getCompany() + "', but it is '" + userDetailsScreen.getCompany() + "'");
        softAssert.assertAll();
    }

    @Then("The User Details screen is displayed")
    public void theUserDetailsScreenIsDisplayed() {
        Assert.assertTrue(userDetailsScreen.userDetailsScreenIsDisplayed(),
                "The user should be on the User Details screen, but it is not");
    }

    @Then("The user goes back to the User Screen")
    public void theUserGoesBackToTheUserScreen() {
        userDetailsScreen.goBack();
    }

    @Then("The following sections are shown")
    public void theFollowingSectionsAreShown(final DataTable dataTable) {
        List<String> sections = dataTable.asList(String.class);
        for (String section: sections) {
            softAssert.assertTrue(userDetailsScreen.isDisplayedSection(section),
                    "The section '" + section + "' should be displayed on the User Details screen, but it is not");
        }
    }

    @When("The user access to the section {string}")
    public void theUserAccessToTheSection(final String section) {
        userDetailsScreen.goToSection(section);
    }

    @Then("The user selects the last todo")
    public void theUserSelectsTheLastTodo() {
        userDetailsScreen.selectLastTodo();
    }

    @And("The last todo is selected")
    public void theLastTodoIsSelected() {
        Assert.assertTrue(userDetailsScreen.isLastTodoSelected(),
                "The last Todo should be selected on the User Details screen, but it is not");
    }
}
