package LoginPage;

import Setup.Base;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class LoginPage extends Base {

    // Constructor to initialize WebDriver instance
    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * Performs the "Login with Google" functionality in the application.

     * This method waits for the "Login with Google" button to be clickable and subsequently
     * interacts with the button to initiate the Google login process. The method handles
     * switching the context between Native App and WebView for handling Google login. After
     * processing the WebView interaction for Google login, the context is switched back to
     * Native App.

     * Exception handling is implemented to handle any errors during the process, and logs
     * are printed to track the steps during execution.

     * Key operations:
     * - Waits for the "Login with Google" button to be clickable and clicks it.
     * - Identifies and switches to WebView context for Google login interaction.
     * - Waits for specific elements inside the WebView and interacts with them.
     * - Switches back to Native App context after completing the WebView process.
     * - Displays log messages for successful steps and errors, if any.
     */
    public void loginWithGoogle(){

        try {
                // Wait for the login button to be clickable
                WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );
                WebElement loginButton = wait.until ( ExpectedConditions.elementToBeClickable ( By.id ( "com.affairscloud:id/btn_login" ) ) );

            if (loginButton.isDisplayed ()) {
                // Click the login button
                Thread.sleep ( 30 );
                loginButton.click ();
                System.out.println ( "Successfully clicked the login with google button" );

                // Handling WebView (for login) context
                Set<String> contextHandles = driver.getContextHandles ();
                for (String context : contextHandles) {
                    System.out.println ( "Available context: " + context );
                    // Check if it's a WebView context
                    if (context.contains ( "WEBVIEW" )) {
                        driver.context ( context ); // Switch to the WebView context
                        System.out.println ( "Switched to WebView context: " + context );
                    }
                }

                // Wait until the element is visible
                WebElement element = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) ).until ( ExpectedConditions.elementToBeClickable ( new AppiumBy.ByAndroidUIAutomator ( "new UiSelector().resourceId(\"com.google.android.gms:id/container\").instance(0)" ) ) );
                // Perform tap using TouchAction
                element.click ();

                driver.context ( "NATIVE_APP" ); // Switch back to the native app
                System.out.println ( "Switched back to native app context." );

            }else {
                System.out.println ("Login with Google Button Not Found");
            }

        } catch (Exception e) {
            System.out.println ("Error While Login: " + e.getMessage());
        }

    }
}
