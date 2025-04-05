package Menu.AppSettings;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class appSetting extends BaseActions {

    public appSetting(AndroidDriver driver) {
        super(driver);
    }

    public void navigateToAppSettings() throws InterruptedException {
        try {
            clickMenu();
            test.log(Status.PASS, "Menu clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("MenuClicked")).build());

            performingAppSettingsActions();

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to navigate to App Settings: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("AppSettingsNavigationFailed")).build());
            throw e;
        }
    }

    protected void performingAppSettingsActions() throws InterruptedException {
        try {
            clickAppSettings();
            clickNotificationtoogle();
            clickNotificationSoundtoogle();
            termsAndConditions();
            privacyPolicy();
            Thread.sleep(3000);
            navigateBack();

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform App Settings actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("AppSettingsActionsFailed")).build());
            throw e;
        }
    }

    public void clickAppSettings() {
        try {
            clickElement(By.id("com.affairscloud:id/item_app_setting"));
            test.log(Status.PASS, "App Settings button clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("AppSettingsClicked")).build());
            System.out.println("Successfully Clicked The App Settings Button");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click App Settings button: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("AppSettingsClickFailed")).build());
            throw e;
        }
    }

    private void clickNotificationtoogle() {
        try {
            clickElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/rl_notification\"]//child::*[@resource-id=\"com.affairscloud:id/tg_notification\"]"));
            test.log(Status.PASS, "Notification toggle clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("NotificationToggleClicked")).build());
            System.out.println("Successfully Clicked The Notification Toggle");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click Notification toggle: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("NotificationToggleFailed")).build());
            throw e;
        }
    }

    private void clickNotificationSoundtoogle() {
        try {
            clickElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/rl_notification_sound\"]//child::*[@resource-id=\"com.affairscloud:id/tg_notification_sound\"]"));
            test.log(Status.PASS, "Notification sound toggle clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("SoundToggleClicked")).build());
            System.out.println("Successfully Clicked The Notification Sound Toggle");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click Notification sound toggle: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("SoundToggleFailed")).build());
            throw e;
        }
    }

    private void termsAndConditions() throws InterruptedException {
        try {
            clickElement(By.id("com.affairscloud:id/tv_terms"));
            test.log(Status.PASS, "Terms and Conditions clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("TermsClicked")).build());
            System.out.println("Successfully Clicked The Terms And Conditions");

            Thread.sleep(3000);
            navigateBack();

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to handle Terms and Conditions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("TermsFailed")).build());
            throw e;
        }
    }

    private void privacyPolicy() throws InterruptedException {
        try {
            clickElement(By.id("com.affairscloud:id/tv_privacy"));
            test.log(Status.PASS, "Privacy Policy clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PrivacyPolicyClicked")).build());
            System.out.println("Successfully Clicked The Privacy Policy");

            Thread.sleep(3000);
            navigateBack();

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to handle Privacy Policy: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PrivacyPolicyFailed")).build());
            throw e;
        }
    }

    private void logout() {
        try {
            clickElement(By.id("com.affairscloud:id/tv_logout"));
            test.log(Status.PASS, "Logout button clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("LogoutClicked")).build());
            System.out.println("Successfully Clicked The Logout Button");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to logout: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("LogoutFailed")).build());
            throw e;
        }
    }

}