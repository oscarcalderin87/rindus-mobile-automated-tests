package framework.bases.po;

import framework.context.TestContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public abstract class BaseScreen extends BaseObject {

    public BaseScreen(final AppiumDriver<MobileElement> driver, final TestContext testContext) {
        super(driver, testContext);
    }
}
