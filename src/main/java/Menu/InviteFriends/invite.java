package Menu.InviteFriends;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class invite extends BaseActions {

    public invite(AndroidDriver driver) {
        super(driver);
    }

    public void navigateToInviteFriends() throws InterruptedException {
        try {
            // Click menu button
            clickMenu();
            test.log(Status.PASS, "Successfully clicked menu button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Menu Clicked")).build());

            // Click invite button
            clickInvite();

            // Handle cancel action
            commonCancel();

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to navigate to Invite Friends: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Invite Friends Error")).build());
            throw e;
        }
    }

    public void clickInvite() {
        try {
            clickElement(By.id("com.affairscloud:id/tv_invite_friends"));
            test.log(Status.PASS, "Successfully clicked the Invite button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Invite Clicked")).build());
            System.out.println("Successfully Clicked The Invite Button");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click Invite button: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Invite Click")).build());
            throw e;
        }
    }

}