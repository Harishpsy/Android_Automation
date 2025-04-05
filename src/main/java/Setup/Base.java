package Setup;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Logger;

public class Base {

    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static AndroidDriver driver;
    private static final Logger LOGGER = Logger.getLogger(Base.class.getName());

    private static final String SCREENSHOT_DIR = "./screenshots/";
    private static final String REPORT_DIR = "./test-reports/";
    private static final String REPORT_NAME = "Android_Test_Report.html";

    @BeforeSuite
    public void setupReport() {
        createDirectory(REPORT_DIR);
        createDirectory(SCREENSHOT_DIR);

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_DIR + REPORT_NAME);
        // Add this configuration
        sparkReporter.config().setCss(".r-img { width: 100%; }");
        sparkReporter.config().setDocumentTitle("Appium Automation Report");
        sparkReporter.config().setReportName("Android Test Execution");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("OS", "Android");
        extent.setSystemInfo("Device", "Redmi Note 11");
        extent.setSystemInfo("App", "CareersCloud");
        extent.setSystemInfo("Version", "2.3");

        LOGGER.info("Extent Reports initialized successfully.");
    }

    @BeforeTest
    public void initializeDriver() {
        try {
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
            driver.setSetting("enforceXPath1", true);

            LOGGER.info("Android driver initialized successfully.");
        } catch (MalformedURLException e) {
            LOGGER.severe("Invalid Appium server URL: " + e.getMessage());
            throw new RuntimeException("Failed to initialize driver due to invalid URL.");
        } catch (Exception e) {
            LOGGER.severe("Driver initialization failed: " + e.getMessage());
            throw new RuntimeException("Failed to initialize Appium driver.");
        }
    }

    @AfterMethod
    public void captureTestResult(ITestResult result) {
        try {
            test = extent.createTest(result.getName());
            String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

            if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("Test Passed",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
            } else if (result.getStatus() == ITestResult.FAILURE) {
                test.fail(result.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
            }
        } catch (Exception e) {
            LOGGER.warning("Failed to capture test result: " + e.getMessage());
            test.log(Status.WARNING, "Failed to capture test result: " + e.getMessage());
        } finally {
            extent.flush();
        }
    }
/*
    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                LOGGER.info("Driver closed successfully.");
                test.log(Status.INFO, "Driver closed successfully.");
            }
        } catch (Exception e) {
            LOGGER.warning("Failed to close driver: " + e.getMessage());
            test.log(Status.WARNING, "Failed to close driver: " + e.getMessage());
        } finally {
            if (extent != null) {
                extent.flush();
            }
        }
    }*/

    protected String captureScreenshot(String testName) {
        try {
            // Take screenshot as Base64
            String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

            // Also save to file (optional)
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            Path screenshotPath = Paths.get(SCREENSHOT_DIR, fileName);
            Files.createDirectories(Paths.get(SCREENSHOT_DIR));
            byte[] screenshotBytes = Base64.getDecoder().decode(screenshotBase64);
            Files.write(screenshotPath, screenshotBytes);

            return screenshotBase64;
        } catch (Exception e) {
            LOGGER.warning("Failed to capture screenshot: " + e.getMessage());
            test.fail("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    private void createDirectory(String path) {
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            LOGGER.warning("Failed to create directory: " + path);
        }
    }

    public AndroidDriver getDriver() {
        return driver;
    }
}
