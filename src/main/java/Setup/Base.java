package Setup;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Base {

    protected static AndroidDriver driver;  // Use protected for inheritance

    public Base() {
    }

    @Test(priority = 1)
    protected void initializeDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities ();
        caps.setCapability ( "platformName" , "Android" );
        caps.setCapability ( "appium:deviceName" , "Redmi Note 11" );
        caps.setCapability ( "appium:automationName" , "UiAutomator2" );
        caps.setCapability ( "appium:platformVersion" , "13" );
        caps.setCapability ( "appium:appPackage" , "com.affairscloud" );
        caps.setCapability ( "appium:appActivity" , "com.affairscloud.SplashScreenActivity" );
        caps.setCapability ( "appium:udid" , "94054b3d" );
        caps.setCapability ( "appium:ensureWebviewsHavePages" , true );
        caps.setCapability ( "appium:nativeWebScreenshot" , true );


        URL url = new URL ( "http://localhost:4723" );
        driver = new AndroidDriver ( url , caps );
        driver.setSetting ( "enforceXPath1" , true );  // Set this after driver initialization

    }

    // Getter method to provide driver access
    public AndroidDriver getDriver() {
        return driver;
    }

}
