package pom.items;

import framework.bases.po.BaseItem;
import framework.context.TestContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class UserItem extends BaseItem {
    private final static By nameBy = By.id("tv_user_name");
    private final static By userNameBy = By.id("tv_user_username");
    private final static By companyBy = By.id("tv_user_company");

    public UserItem(final AppiumDriver<MobileElement> driver, final TestContext testContext,
                    final MobileElement mainElement) {
        super(driver, testContext, mainElement);
    }

    public String getName() {
        return mainElement.findElement(nameBy).getText();
    }

    public String getUserName() {
        return mainElement.findElement(userNameBy).getText();
    }

    public String getCompany() {
        return mainElement.findElement(companyBy).getText();
    }

    public void select() {
        mainElement.click();
    }

}
