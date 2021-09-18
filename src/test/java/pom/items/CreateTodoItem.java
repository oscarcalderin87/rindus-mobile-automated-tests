package pom.items;

import framework.bases.po.BaseItem;
import framework.context.TestContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class CreateTodoItem extends BaseItem {
    private static final By inputTodoBy = By.id("et_createTodo_content");
    private static final By buttonCreateBy = By.id("btn_createTodo_create");

    public CreateTodoItem(final AppiumDriver<MobileElement> driver, final TestContext testContext,
                          final MobileElement mainElement) {
        super(driver, testContext, mainElement);
    }

    public void createTodo(final String text) {
        mainElement.findElement(inputTodoBy).sendKeys(text);
        driver.hideKeyboard();
        mainElement.findElement(buttonCreateBy).click();
    }
}
