package Homepage.Notification;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class notification extends BaseActions {

    public notification(AndroidDriver driver) {
        super(driver);
    }

    public void performingNotificationActions() {
        try {
            clickNotification();
            clickPromotion();
            clickGeneral();
            navigateBack();

            test.log(Status.PASS, "All notification actions completed successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("NotificationActionsComplete")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Notification actions failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("NotificationActionsFailed")).build());
            throw e;
        }
    }

    private void clickNotification() {
        clickElement(By.id("com.affairscloud:id/iv_notification"));
        test.log(Status.PASS, "Notification icon clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("NotificationIconClicked")).build());
        System.out.println("Successfully Clicked The Notification Icon");
    }

    private void clickPromotion() {
        new WebDriverWait(driver, Duration.ofSeconds(30));
        clickElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/tabTitle\" and @text=\"PROMOTION\"]"));
        test.log(Status.PASS, "Promotion tab clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PromotionTabClicked")).build());
        System.out.println("Successfully Clicked The Promotion");
    }

    private void clickGeneral() {
        clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/tabTitle\" and @text=\"GENERAL\"]"));
        test.log(Status.PASS, "General tab clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("GeneralTabClicked")).build());
        System.out.println("Successfully Clicked The General");
    }
}