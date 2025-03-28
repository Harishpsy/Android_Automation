package LoginPage;

import Setup.Base;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;

import java.time.Duration;
import java.util.Set;

public class LoginPage extends Base {

    // Constructor to initialize WebDriver instance
    public LoginPage(AndroidDriver driver) {
        Base.driver = driver;
    }

    /**
     * Performs the "Login with Google" functionality in the application.
     * <p>
     * This method waits for the "Login with Google" button to be clickable and subsequently
     * interacts with the button to initiate the Google login process. The method handles
     * switching the context between Native App and WebView for handling Google login. After
     * processing the WebView interaction for Google login, the context is switched back to
     * Native App.
     * <p>
     * Exception handling is implemented to handle any errors during the process, and logs
     * are printed to track the steps during execution.
     * <p>
     * Key operations:
     * - Waits for the "Login with Google" button to be clickable and clicks it.
     * - Identifies and switches to WebView context for Google login interaction.
     * - Waits for specific elements inside the WebView and interacts with them.
     * - Switches back to Native App context after completing the WebView process.
     * - Logs detailed steps and results to Extent Reports.
     */
    public void loginWithGoogle() {
        try {
            test.log(Status.INFO, "Starting Google login process");

            // Wait for the login button to be clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            test.log(Status.INFO, "Waiting for login button to be clickable");

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.affairscloud:id/btn_login")));
            test.log(Status.INFO, "Login button located");

            if (loginButton.isDisplayed()) {
                // Click the login button
                Thread.sleep(30);
                loginButton.click();
                test.log(Status.PASS, "Successfully clicked the login with Google button");

                // Handling WebView (for login) context
                test.log(Status.INFO, "Checking for available contexts");
                Set<String> contextHandles = driver.getContextHandles();

                for (String context : contextHandles) {
                    // Check if it's a WebView context
                    if (context.contains("WEBVIEW")) {
                        driver.context(context); // Switch to the WebView context
                        test.log(Status.PASS, "Switched to WebView context: " + context);
                    }
                }

                // Wait until the element is visible
                test.log(Status.INFO, "Waiting for Google account selection");
                WebElement element = new WebDriverWait(driver, Duration.ofSeconds(30))
                        .until(ExpectedConditions.elementToBeClickable(
                                new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"com.google.android.gms:id/container\").instance(0)")));

                // Perform tap using TouchAction
                element.click();
                test.log(Status.PASS, "Successfully selected Google account");

                driver.context("NATIVE_APP"); // Switch back to the native app
                test.log(Status.INFO, "Switched back to native app context");

                test.log(Status.PASS, "Google login process completed successfully");

            } else {
                test.log(Status.FAIL, "Login with Google Button Not Found");
                throw new RuntimeException("Login with Google Button Not Found");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Error during Google login: " + e.getMessage());
            throw new RuntimeException("Google login failed: " + e.getMessage(), e);
        }
    }
}