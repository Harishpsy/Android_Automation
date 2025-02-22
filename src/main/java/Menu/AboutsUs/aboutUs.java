package Menu.AboutsUs;

import Setup.*;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class aboutUs extends BaseActions {

    public aboutUs(AndroidDriver driver) {
        super(driver);
    }

    public void navigateToAboutUs() throws InterruptedException {

        clickMenu ();
        scrollToEnd ();
        performingAboutUsActions();
    }

    public void performingAboutUsActions(){
        clickAboutUs();
        navigateBack ();
    }

    public void clickAboutUs() {
        clickElement ( By.xpath ( "com.affairscloud:id/tv_about_us" ) );
        System.out.println ( "Successfully Clicked The About Us Button" );
    }
}
