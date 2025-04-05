package Menu.AboutsUs;

import Setup.*;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        WebDriverWait wait = new WebDriverWait ( driver, Duration.ofSeconds ( 40 ) );
        navigateBack ();
    }

    public void clickAboutUs() {
        clickElement ( By.id ( "com.affairscloud:id/tv_about_us" ) );
        test.log( Status.PASS, "Successfully Clicked The About Us Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked The About Us Button")).build());
        System.out.println ( "Successfully Clicked The About Us Button" );
    }
}
