package AllCourse.NonSubscribedCourse.SubscribeNow;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.time.Duration;

public class SubscribeNow extends BaseActions {

    public SubscribeNow(AndroidDriver driver) {
        super(driver);
    }

    public void performSubscribeNowAction() {
        clickSubscribeNowButton();
        closeIcon();
        clickSubscribeNowButton();
        couponCodeAction();
        clickBuyNowButton();
        razorPayActions();
        closeIcon();
    }

    private void clickSubscribeNowButton() {
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
        clickElement ( By.xpath ( "//android.widget.Button[@resource-id=\"com.affairscloud:id/btn_subscrib\"]" ) );
    }

    private void closeIcon() {
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
        clickElement ( By.id ( "com.affairscloud:id/iv_close" ) );
    }

    private void couponCodeAction() {
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
        enterCouponCode();
        clickApplyButton();
    }

    private void enterCouponCode() {

        clickElement ( By.id ( "com.affairscloud:id/et_enterCoupon" ) );
        System.out.println ("Successfully clicked the coupon code textfield");

        enteringText ( By.xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_enterCoupon\"]" ) );
        System.out.println ("Successfully entered the coupon code textfield");
        driver.manage().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
        driver.hideKeyboard ();

    }

    private void clickApplyButton() {
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
        clickElement ( By.xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_apply\"]" ) );
    }

    private void clickBuyNowButton() {
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
        clickElement ( By.id ( "com.affairscloud:id/btn_buy" ) );
    }

    protected void razorPayActions() {
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
        clickRazorpayBackButton();
        clickContinueButton();
        clickRazorpayBackButton();
        clickYesButton();
        clickingDismissButton();

    }

    private void clickRazorpayBackButton() {
        clickElement ( By.xpath ( "//android.widget.Button[@text=\"Go back\"]" ) );
    }

    private void clickContinueButton() {
        clickElement ( By.xpath ( "//android.widget.Button[@text=\"Continue to payment\"]" ) );
    }

    private void clickYesButton() {
        clickElement ( By.xpath ( "//android.widget.Button[@text=\"Yes, exit\"]" ) );
    }

    private void clickingDismissButton() {
        clickElement ( By.xpath ( "//android.widget.Button[@resource-id=\"com.affairscloud:id/btn_continue\"]" ) );
    }

















}
