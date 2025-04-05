package Menu.MyPurchase;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class myPurchase extends BaseActions {

    public myPurchase(AndroidDriver driver) {
        super(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
    }

    public void navigateToMyPurchase() throws InterruptedException {
        clickMenu();
        test.log(Status.PASS, "Successfully Clicked Menu",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked Menu")).build());
        performingMyPurchaseActions();
    }

    protected void performingMyPurchaseActions() throws InterruptedException {
        clickMyPurchase();
        scrollList();
        performingNothificationActions();
        Thread.sleep(3000);
        navigateBack();
    }

    protected void clickMyPurchase() {
        clickElement(By.id("com.affairscloud:id/tv_my_purchase"));
        test.log(Status.PASS, "Successfully Clicked The My Purchase Button In The Menu",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked My Purchase")).build());
        System.out.println("Successfully Clicked The My Purchase Button In The Menu");
    }

    protected void performingNothificationActions() {
        clickNotification();
        clickPromotion();
        clickGeneral();
        navigateBack();
    }

    private void clickNotification() {
        clickElement(By.id("com.affairscloud:id/iv_notification"));
        test.log(Status.PASS, "Successfully Clicked The Notification Icon",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked Notification")).build());
        System.out.println("Successfully Clicked The Notification Icon");
    }

    private void clickPromotion() {
        clickElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/tabTitle\" and @text=\"PROMOTION\"]"));
        test.log(Status.PASS, "Successfully Clicked The Promotion Tab",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked Promotion")).build());
        System.out.println("Successfully Clicked The Promotion");
    }

    private void clickGeneral() {
        clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/tabTitle\" and @text=\"GENERAL\"]"));
        test.log(Status.PASS, "Successfully Clicked The General Tab",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked General")).build());
        System.out.println("SuccessFully Clicked The General");
    }
}