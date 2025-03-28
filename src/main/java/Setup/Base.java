package Setup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Base {

    protected static ExtentTest test;
    protected static ExtentReports extent;
    protected static AndroidDriver driver;

    private static final String SCREENSHOT_DIR = "./screenshots/";
    private static final String REPORT_DIR = "./test-reports/";
    private static final String REPORT_NAME = "Android_Test_Report.html";

    @BeforeTest
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

    @BeforeClass
    public void setupReport() {
        // Create report directory if it doesn't exist
        createDirectory(REPORT_DIR);
        createDirectory(SCREENSHOT_DIR);

        // Initialize ExtentReports with enhanced configuration
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_DIR + REPORT_NAME);
        sparkReporter.config().setDocumentTitle("Appium Automation Report");
        sparkReporter.config().setReportName("Android Test Execution");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("OS", "Android");
        extent.setSystemInfo("Device", "Redmi Note 11");
        extent.setSystemInfo("App", "AffairsCloud");
    }

    @AfterMethod
    public void captureTestResult(ITestResult result) {
        try {
            String screenshotPath = captureScreenshot(result.getName());
            if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("Test Passed",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else if (result.getStatus() == ITestResult.FAILURE) {
                test.fail(result.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            }
            extent.flush();  // Ensuring report updates after every test
        } catch (Exception e) {
            test.log(Status.WARNING, "Failed to capture test result: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                test.log(Status.INFO, "Driver closed successfully");
            }
        } catch (Exception e) {
            test.log(Status.WARNING, "Failed to close driver: " + e.getMessage());
        } finally {
            if (extent != null) {
                extent.flush();
            }
        }
    }

    protected String captureScreenshot(String testName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        Path screenshotPath = Paths.get(SCREENSHOT_DIR, fileName);

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), screenshotPath);
            return screenshotPath.toString();
        } catch (IOException e) {
            test.log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
            throw e;
        }
    }

    private void createDirectory(String path) {
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            System.err.println("Failed to create directory: " + path);
        }
    }

    public AndroidDriver getDriver() {
        return driver;
    }
}