package Menu.MyCourse;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static io.appium.java_client.AppiumBy.id;

public class myCourse extends BaseActions {

    public myCourse(AndroidDriver driver) {
        super ( driver );
    }

    /**
     * Test method to interact with the My Course section in the application.
     * <p>
     * This method performs two main actions:
     * 1. Clicks the "My Course" button located in the side menu to navigate to the My Course section.
     * 2. Clicks the back button to return to the previous screen after accessing the My Course section.
     * <p>
     * The method outputs console messages confirming the successful execution of each action.
     */

    @Test
    public void clickingMyCourse() throws InterruptedException {

        //clicking The menu Button
        clickMenu ();

        // Clicking the My Course ButtonIn the side bar
        clickElement ( id ( "com.affairscloud:id/item_my_courses" ) );
        System.out.println ( "Successfully Clicked The My Course Button In The Side enu" );

        // Clicking The back button
        clickElement ( By.id ( "com.affairscloud:id/img_back_press" ) );
        System.out.println ( "Successfully Clicked The Back Button In My Course" );
    }

}
