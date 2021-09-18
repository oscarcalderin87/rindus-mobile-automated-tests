package framework.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class Utils {
    private static final int TIMEOUT = 15;


    public static void scrollToEnd(final AppiumDriver<MobileElement> driver) {
        try {
            driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"));
        } catch (InvalidSelectorException e) {
            // ignore
        }
    }

    public static MobileElement waitForElementDisplayed(final AppiumDriver<MobileElement> driver, final By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.ignoring(NoSuchElementException.class);
        return (MobileElement) wait.until(drv -> drv.findElement(elementBy));
    }

    public static boolean isChecked(final MobileElement element) {
        return element.getAttribute("checked").equalsIgnoreCase("true");
    }


    public static void swipeElementToLeft(AppiumDriver<MobileElement> driver, MobileElement element) {
        new TouchAction(driver)
                .press(PointOption.point(element.getCenter()))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(0,100))
                .release()
                .perform();
    }


    public static void scrollListToLeft(AppiumDriver<MobileElement> driver, By listSelector, String expectedText) {
        try {
            driver.findElement(listSelector);
            if (driver.findElements(By.xpath("//*[text()='" + expectedText + "']")).size() > 0) {
                driver.findElement(MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).setAsHorizontalList().scrollForward(10)"));
                return;
            }
            else {
                driver.findElement(MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).setAsHorizontalList().scrollForward(10)"));
            }

        } catch (InvalidSelectorException e) {
            // ignore
        }
    }

}
