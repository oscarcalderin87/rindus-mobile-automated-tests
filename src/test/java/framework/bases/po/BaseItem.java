package framework.bases.po;

import framework.context.TestContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public abstract class BaseItem extends BaseObject {
    protected MobileElement mainElement;

    public BaseItem(final AppiumDriver<MobileElement> driver, final TestContext testContext,
                    final MobileElement mainElement) {
        super(driver, testContext);
        this.mainElement = mainElement;
    }
}
