package Setup;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Base {

    protected static AndroidDriver driver;  // Use protected for inheritance

    @Test(priority = 1)
    protected void initializeDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "Redmi Note 11");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:platformVersion", "13");
        caps.setCapability("appium:appPackage", "com.affairscloud");
        caps.setCapability("appium:appActivity", "com.affairscloud.SplashScreenActivity");
        caps.setCapability("appium:udid", "94054b3d");

        URL url = new URL("http://localhost:4723");
        driver = new AndroidDriver(url, caps);
    }

    // Getter method to provide driver access
    public AndroidDriver getDriver() {
        return driver;
    }

    // Common Click Action From Here we are calling Click action to all
    protected void clickElement(By locator) {
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );
        WebElement clickAction = wait.until ( ExpectedConditions.elementToBeClickable ( locator ) );
        clickAction.click ();
    }
    // Common GetText Action From Here we are calling getText action to all
    protected String getText(By locator) {
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );
        WebElement gettingText = wait.until ( ExpectedConditions.elementToBeClickable ( locator ) );
        String text = gettingText.getText ();
        return text;
    }

}
