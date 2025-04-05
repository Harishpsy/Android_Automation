package Menu.ContactUs;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class contactUs extends BaseActions {

    public contactUs(AndroidDriver driver) {
        super(driver);
    }

    public void navigateToContactUs() throws InterruptedException {
        try {
            clickMenu();
            test.log(Status.PASS, "Successfully clicked menu button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Menu Clicked")).build());

            scrollToEnd();
            test.log(Status.INFO, "Scrolled to end of menu",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Menu Scrolled")).build());

            performingContactUsActions();

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to navigate to Contact Us: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Contact Us Navigation Error")).build());
            throw e;
        }
    }

    private void performingContactUsActions() throws InterruptedException {
        try {
            clickContactUs();
            enteringText();
            clickSubmitButton();

            clickMenu();
            test.log(Status.PASS, "Returned to menu",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Returned to Menu")).build());

            scrollToEnd();
            clickContactUs();
            enteringText();
            navigateBack();

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform Contact Us actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Contact Us Actions Error")).build());
            throw e;
        }
    }

    protected void clickContactUs() {
        try {
            clickElement(By.id("com.affairscloud:id/tv_contact_us"));
            test.log(Status.PASS, "Successfully clicked Contact Us button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Contact Us Clicked")).build());
            System.out.println("Successfully Clicked The Contact Us Button");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click Contact Us button: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Contact Us Click")).build());
            throw e;
        }
    }

    private void enteringText() {
        try {
            enteringText(By.id("com.affairscloud:id/et_contactus"));
            test.log(Status.PASS, "Successfully entered text",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Text Entered")).build());
            System.out.println("Successfully Entered The Text");

            driver.hideKeyboard();
            test.log(Status.INFO, "Keyboard hidden");

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to enter text: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Text Entry Failed")).build());
            throw e;
        }
    }

    private void clickSubmitButton() throws InterruptedException {
        try {
            Thread.sleep(2000);
            clickElement(By.id("com.affairscloud:id/btn_submit_ct"));
            test.log(Status.PASS, "Successfully clicked Submit button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Submit Clicked")).build());
            System.out.println("Successfully Clicked The Submit Button");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click Submit button: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Submit Failed")).build());
            throw e;
        }
    }
}