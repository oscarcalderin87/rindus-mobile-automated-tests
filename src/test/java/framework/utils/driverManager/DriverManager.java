package framework.utils.driverManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver<MobileElement>> sharedDriver = new ThreadLocal<>();
    private static final int IMPLICIT_TIMEOUT = 20;
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723/wd/hub";
    private static final String APP_NAME = "qa_task.apk";

    private static final Logger LOGGER = Logger.getLogger(DriverManager.class.getName());

    public AppiumDriver<MobileElement> getDriver() {
        if (sharedDriver.get() == null) {
            try {
                setAppiumDriver(createDriver());
            } catch (MalformedURLException e) {
                LOGGER.log(Level.SEVERE, "The provided url is not well formed");
            }
            getAppiumDriver().manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        }
        return getAppiumDriver();
    }

    private AppiumDriver<MobileElement> createDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(APP_NAME).getFile());
        String absolutePath = file.getAbsolutePath();

        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android_Accelerated_Oreo");
        capabilities.setCapability(MobileCapabilityType.APP, absolutePath);

        URL appiumServerUrl = new URL(APPIUM_SERVER_URL);
        return new AppiumDriver<>(appiumServerUrl, capabilities);
    }

    private AppiumDriver<MobileElement> getAppiumDriver() {
        return sharedDriver.get();
    }

    private void setAppiumDriver(final AppiumDriver<MobileElement> driver) {
        sharedDriver.set(driver);
    }

    public void quitDriver() {
        if (sharedDriver.get() != null) {
            sharedDriver.get().quit();
            sharedDriver.remove();
        }
    }
}
