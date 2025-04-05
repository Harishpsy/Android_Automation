package AllCourse.NonSubscribedCourse.SubscribeNow;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SubscribeNow extends BaseActions {

    public SubscribeNow(AndroidDriver driver) {
        super(driver);
    }

    public void performSubscribeNowAction() {
        try {
            clickSubscribeNowButton();
            closeIcon();
            clickSubscribeNowButton();
            couponCodeAction();
            clickBuyNowButton();
            razorPayActions();
            closeIcon();

            test.log(Status.PASS, "Subscription flow completed successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("SubscriptionComplete")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Subscription flow failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("SubscriptionFailed")).build());
            throw e;
        }
    }

    private void clickSubscribeNowButton() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.affairscloud:id/btn_subscrib\"]"));
        test.log(Status.PASS, "Clicked Subscribe Now button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("SubscribeNowClicked")).build());
    }

    private void closeIcon() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        clickElement(By.id("com.affairscloud:id/iv_close"));
        test.log(Status.PASS, "Closed subscription dialog",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("SubscriptionClosed")).build());
    }

    private void couponCodeAction() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            enterCouponCode();
            clickApplyButton();

            test.log(Status.PASS, "Coupon code applied successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("CouponApplied")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to apply coupon code: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("CouponFailed")).build());
            throw e;
        }
    }

    private void enterCouponCode() {
        clickElement(By.id("com.affairscloud:id/et_enterCoupon"));
        test.log(Status.INFO, "Coupon code field clicked");

        enteringText(By.xpath("//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_enterCoupon\"]"));
        test.log(Status.INFO, "Coupon code entered");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.hideKeyboard();
    }

    private void clickApplyButton() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_apply\"]"));
        test.log(Status.PASS, "Apply button clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ApplyClicked")).build());
    }

    private void clickBuyNowButton() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        clickElement(By.id("com.affairscloud:id/btn_buy"));
        test.log(Status.PASS, "Buy Now button clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("BuyNowClicked")).build());
    }

    protected void razorPayActions() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            clickRazorpayBackButton();
            clickContinueButton();
            clickRazorpayBackButton();
            clickYesButton();
            clickingDismissButton();

            test.log(Status.PASS, "Razorpay payment flow completed",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PaymentFlowComplete")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Razorpay payment flow failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PaymentFailed")).build());
            throw e;
        }
    }

    private void clickRazorpayBackButton() {
        new WebDriverWait(driver, Duration.ofSeconds(30));
        clickElement(By.xpath("//android.widget.Button[@text=\"Go back\"]"));
        test.log(Status.INFO, "Clicked Razorpay back button");
    }

    private void clickContinueButton() {
        new WebDriverWait(driver, Duration.ofSeconds(30));
        clickElement(By.xpath("//android.widget.Button[@text=\"Continue to payment\"]"));
        test.log(Status.PASS, "Continued to payment",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PaymentContinued")).build());
    }

    private void clickYesButton() {
        new WebDriverWait(driver, Duration.ofSeconds(30));
        clickElement(By.xpath("//android.widget.Button[@text=\"Yes, exit\"]"));
        test.log(Status.INFO, "Confirmed exit from payment");
    }

    private void clickingDismissButton() {
        new WebDriverWait(driver, Duration.ofSeconds(30));
        clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.affairscloud:id/btn_continue\"]"));
        test.log(Status.PASS, "Dismissed payment dialog",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PaymentDismissed")).build());
    }
}