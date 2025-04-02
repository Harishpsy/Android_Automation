package Menu.MyNotes;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Video_Module extends BaseActions {

    public Video_Module(AndroidDriver driver) {
        super ( driver );
    }

    public void performVideoActions() throws InterruptedException {

        try {

            WebElement video = driver.findElement ( By.id ( "com.affairscloud:id/card_view_videos" ) );

            if (video.isDisplayed ()) {
                videoActions ();
            }

        } catch (NoSuchElementException e) {
            System.out.println ( "No Video is present in the list page" );
        }
    }

    public void videoActions() throws InterruptedException {

        /*Calling The Course Title Method clicking */
        courseTitle ();

        /* Calling The play Button Method */
        playButtonClick ();

        /* Switch back to your application */
        navigateBackToApp ();

        /*ThreeDot Action Method we are calling */
        Thread.sleep ( 5000 );
        threeDotsActions ();

        /* fotter Section */
        try {
            footerCommonActions ();
            System.out.println ( "Successfully Performed The Fotter section of the card" );
        } catch (NoSuchElementException e) {
            System.out.println ( "No data present in the list page" );
        }

    }

    public void playButtonClick() {

        /*Clicking The Video Play Button*/
        clickElement ( By.xpath ( "//*[@resource-id=\"com.affairscloud:id/play\"]" ) );
        System.out.println ( "Successfully Clicked the Play Button In The Video Module" );

    }

    public void courseTitle() throws InterruptedException {
        clickElement ( By.xpath ( "//*[@resource-id=\"com.affairscloud:id/llCourseTittle\"]" ) );
        System.out.println ( "Successfully Clicked The Course Title In The Video Module" );

        /*Clicking The Back Button*/
        Thread.sleep ( 4000 );
        navigateBack ();
    }

    public void navigateBackToApp() {

        /* Open Recent Apps */
        driver.pressKey ( new KeyEvent ( AndroidKey.APP_SWITCH ) );
        System.out.println ( "Opened Recent Apps" );

        /* Wait and then try to activate your app again */
        try {
            Thread.sleep ( 2000 );
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }

        driver.activateApp ( "com.affairscloud" ); // Your App's package name
        System.out.println ( "Switched back to the application." );
    }

}




