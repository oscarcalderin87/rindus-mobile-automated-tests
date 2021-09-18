package pom.items;

import framework.bases.po.BaseItem;
import framework.context.TestContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class TodoItem extends BaseItem {
    private static final By todoTitleBy = By.id("tv_todo_title");
    private static final By checkTodoBy = By.id("cb_todo_checked");


    public TodoItem(final AppiumDriver<MobileElement> driver, final TestContext testContext,
                    final MobileElement mainElement) {
        super(driver, testContext, mainElement);
    }

    public String getTitle() {
        return mainElement.findElement(todoTitleBy).getText();
    }

    public boolean selected() {
        return isChecked(mainElement.findElement(checkTodoBy));
    }

    public void selectTask() {
        if (selected())
            return;
        mainElement.findElement(checkTodoBy).click();
    }
}
