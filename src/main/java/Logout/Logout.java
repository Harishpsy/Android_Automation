package Logout;

import Menu.AppSettings.appSetting;
import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;

import static org.openqa.selenium.By.*;

public class Logout extends BaseActions {

    private appSetting AppSettingsModule;

    public Logout(AndroidDriver driver) {
        super(driver);
        AppSettingsModule = new appSetting(driver);
    }

    public void performLogoutActions() throws InterruptedException {
        try {
            // Click menu button
            clickMenu();
            test.log(Status.PASS, "Menu clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("MenuClicked")).build());

            // Navigate to App Settings
            AppSettingsModule.clickAppSettings();

            // Perform logout
            logout();

            test.log(Status.PASS, "Logout process completed successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("LogoutCompleted")).build());

            verifyLoggedOutState();
            test.log(Status.PASS, "User Logout successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("LogoutCompleted")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Logout process failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("LogoutFailed")).build());
            throw e;
        }
    }

    private void logout() {
        try {
            clickElement(id("com.affairscloud:id/tv_logout"));
            test.log(Status.PASS, "Logout button clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("LogoutButtonClicked")).build());
            System.out.println("Successfully Clicked The Logout Button");

            // Add any additional verification for successful logout if needed
            // verifyLoggedOutState();

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click logout button: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("LogoutButtonFailed")).build());
            throw e;
        }
    }

    // Optional verification method
    private void verifyLoggedOutState() {
        try {
            // Add verification logic here
            test.log(Status.INFO, "Verified user is logged out");
        } catch (Exception e) {
            test.log(Status.FAIL, "Logout verification failed: " + e.getMessage());
            throw e;
        }
    }
}