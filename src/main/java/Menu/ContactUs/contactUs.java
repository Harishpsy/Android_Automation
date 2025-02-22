package Menu.ContactUs;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class contactUs extends BaseActions {

    public contactUs(AndroidDriver driver) {
        super(driver);
    }

    public void navigateToContactUs() throws InterruptedException {
        clickMenu ();
        scrollToEnd ();
        performingContactUsActions();
    }

    private void performingContactUsActions() throws InterruptedException {
        clickContactUs();
        enteringText();
        clickSubmitButton();
        clickMenu ();
        scrollToEnd ();
        clickContactUs ();
        enteringText();
        navigateBack ();
    }

    protected void clickContactUs() {
        clickElement ( By.id ( "com.affairscloud:id/tv_contact_us" ) );
        System.out.println ("Successfully Clicked The Contact Us Button");
    }

    private void enteringText(){
        enteringText (By.id ( "com.affairscloud:id/et_contactus" ));
        System.out.println ("Successfully Entered The Text");
        driver.hideKeyboard ();
    }

    private void clickSubmitButton() throws InterruptedException {
        Thread.sleep ( 2000 );
        clickElement ( By.id ( "com.affairscloud:id/btn_submit_ct" ) );
        System.out.println ("Successfully Clicked The Submit Button");
    }
}
