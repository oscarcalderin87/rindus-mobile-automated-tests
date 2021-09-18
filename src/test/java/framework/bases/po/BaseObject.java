package framework.bases.po;

import framework.context.TestContext;
import framework.utils.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public abstract class BaseObject {
    protected AppiumDriver<MobileElement> driver;
    protected TestContext testContext;

    public BaseObject(final AppiumDriver<MobileElement> driver, final TestContext testContext) {
        this.driver = driver;
        this.testContext = testContext;
    }

    public void scrollToEnd() {
        Utils.scrollToEnd(driver);
    }

    public boolean isChecked(final MobileElement element) {
        return Utils.isChecked(element);
    }

    public MobileElement waitForElementDisplayed(final By elementBy) {
       return Utils.waitForElementDisplayed(driver, elementBy);
    }

    public void swipeElementToLeft(final MobileElement element) {
        Utils.swipeElementToLeft(driver, element);
    }

    public void scrollListToLeft(final By listSelector, final String expectedText) {
        Utils.scrollListToLeft(driver, listSelector, expectedText);
    }
}
