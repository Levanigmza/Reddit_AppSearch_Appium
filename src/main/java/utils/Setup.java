package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class Setup {
    private static Config_Loader configLoader;
    private static AppiumDriver<MobileElement> driver;

    public static void setupCapabilities() throws MalformedURLException {
        configLoader = new Config_Loader();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, configLoader.getProperty("platform.name"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, configLoader.getProperty("platform.version"));
        capabilities.setCapability(MobileCapabilityType.UDID, configLoader.getProperty("udid"));
        capabilities.setCapability("appPackage", configLoader.getProperty("app.package"));
        capabilities.setCapability("appActivity", configLoader.getProperty("app.activity"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, configLoader.getProperty("automation.name"));
        capabilities.setCapability(MobileCapabilityType.NO_RESET, configLoader.getBooleanProperty("no.reset"));

        String appiumServerUrl = configLoader.getProperty("appium.server.url");
        driver = new AndroidDriver<>(new URL(appiumServerUrl), capabilities);
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
