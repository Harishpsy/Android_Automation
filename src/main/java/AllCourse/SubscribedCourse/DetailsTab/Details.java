package AllCourse.SubscribedCourse.DetailsTab;

import AllCourse.SubscribedCourse.EbooksTab.Ebook;
import Menu.MyNotes.Video_Module;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Details extends BaseActions {

    private final Video_Module video;
    private final Ebook ebook;

    public Details(AndroidDriver driver) {
        super ( driver );
        video = new Video_Module ( driver );   // Initialize the video_Module
        ebook = new Ebook ( driver );
    }

    public void performDetailsActions() throws InterruptedException {

        try {

            scrollRight ();
            clickingDetailsTab ();
            scrollToEnd ();
            faqPlusIcon ();
            faqPlusIcon ();
            scrollToBeginning ();
            Thread.sleep ( 1000 );
            email ();
            Thread.sleep ( 2000 );
            message ();
            video.navigateBackToApp ();
            Thread.sleep ( 2000 );
            phone ();
            video.navigateBackToApp ();

            navigateBack ();


        } catch (NoSuchElementException e) {
            System.out.println ( "Error during the detailsActions" + e.getMessage () );
        }
    }

    protected void scrollRight() {

        try {
            WebElement detailsTab = driver.findElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Details\"]" ) );
            if (detailsTab.isDisplayed ()) {
                detailsTab.click ();
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            ebook.scrollHorizontalRight ( 1 ); // Scroll right once
            System.out.println ("Successfully Scrolled Right");
        }
    }

    protected void clickingDetailsTab() {
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 50 ) );
        clickElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Details\"]" ) );
    }

    public void email() {

        try {

            WebElement emailTab = driver.findElement (By.xpath ( "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_email\"]" ));

             if (emailTab.isDisplayed ()) {
                 driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 50 ) );
                 clickElement ( By.xpath ( "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_email\"]" ) );

                 /* Performing The Email Actions */
                 emailActions ();
             }

        } catch (NoSuchElementException e) {
            System.out.println ( "Email Was Not Present In The Detail Tab" );
        }
    }

    protected void emailActions() {
        clickTextField ();
        enterText ();
        clickCancel ();
//        email();
//        clickTextField();
//        enterText();
//        clickSend();
    }

    protected void clickTextField() {
        clickElement ( By.xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_question\"]" ) );
        System.out.println ( "Successfully clicked the text field" );
    }

    protected void enterText() {
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 50 ) );
        enteringText ( By.xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_question\"]" ) );
        System.out.println ( "Successfully Entered the text" );
//        driver.hideKeyboard ();
//        System.out.println ("Hide");
    }

    protected void clickSend() {
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 50 ) );
        clickElement ( By.xpath ( "//android.widget.Button[@resource-id=\"com.affairscloud:id/btn_send\"]" ) );
        System.out.println ( "Successfully clicked the button" );
    }

    protected void clickCancel() {
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 20 ) );
        clickElement ( By.id ( "com.affairscloud:id/image_alert" ) );
        System.out.println ( "Successfully clicked the Cancel button" );
    }

    public void message() {

        try {
            WebElement messageElement = driver.findElement ( By.xpath ( "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_msg\"]" ) );
            if (messageElement.isDisplayed ()) {
                driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
                clickElement ( By.xpath ( "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_msg\"]" ) );
                System.out.println ( "Successfully clicked the msg button" );
            }
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println ( "Message Was Not Present In The Detail Tab" );
        }
    }

    public void phone() {

        try {
            WebElement phoneElement = driver.findElement ( By.xpath ( "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_call\"]" ) );

            if(phoneElement.isDisplayed ()) {
                driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 50 ) );
                clickElement ( By.xpath ( "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_call\"]" ) );
                System.out.println ( "Successfully clicked the phone button" );
            }

        } catch (NoSuchElementException e) {
            System.out.println ( "Phone Was Not Present In The Detail Tab" );
        }
    }

    public void faqPlusIcon() {
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 50 ) );
        clickElement ( By.xpath ( "(//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_plus\"])[1]" ) );
    }
}
