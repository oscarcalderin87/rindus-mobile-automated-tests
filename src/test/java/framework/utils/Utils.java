package framework.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

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


    public static void swipeElementToLeft(final AppiumDriver<MobileElement> driver, final MobileElement element) {
        new TouchAction(driver)
                .press(point(element.getCenter()))
                .waitAction(waitOptions(ofMillis(300)))
                .moveTo(point(0,100))
                .release()
                .perform();
    }


    public static void scrollListToLeft(final AppiumDriver<MobileElement> driver, final By listSelector, final String expectedText) {
        try {
            driver.findElement(listSelector);
            while (driver.findElements(MobileBy.AndroidUIAutomator("text(\""+ expectedText + "\")")).size() == 0) {
                driver.findElement(MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).setAsHorizontalList().scrollForward(10)"));
            }
        } catch (InvalidSelectorException e) {
            // ignore
        }
    }

}
