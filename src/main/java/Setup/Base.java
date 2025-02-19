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

    public Base() {
    }

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
        caps.setCapability("appium:ensureWebviewsHavePages", true);
        caps.setCapability("appium:nativeWebScreenshot", true);


        URL url = new URL("http://localhost:4723");
        driver = new AndroidDriver(url, caps);
        driver.setSetting("enforceXPath1", true);  // Set this after driver initialization

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
        System.out.println ( "Text Name : " + text );
        return text;
    }

    protected String enteringText(By locator) {
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 40 ) );
        WebElement enteringText = wait.until ( ExpectedConditions.elementToBeClickable ( locator ) );
        enteringText.sendKeys ( "Thanks for the valuable content" );
        return "";
    }

    /**
     * Scrolls the list to the bottom and then back to the top.
     */
    public void scrollList() {
        scrollToEnd();
        scrollToBeginning();
    }

    /**
     * Scrolls the list to the bottom.
     */
    private void scrollToEnd() {
        try {
            // Scroll to the bottom of the list using UiScrollable
            WebElement scrolldown = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(30)"));
            System.out.println("Successfully scrolled to the bottom of the List page.");
        } catch (Exception e) {
            System.out.println("Error while scrolling down");
        }
    }

    /**
     * Scrolls the list back to the top.
     */
    private void scrollToBeginning() {
        try {
            // Scroll to the top of the list using UiScrollable

            WebElement scrolltop = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(30)"));
            System.out.println("Successfully scrolled back to the top of the List page.");
        } catch (Exception e) {
            System.out.println("Error while scrolling to the initial position");
        }
    }

}
