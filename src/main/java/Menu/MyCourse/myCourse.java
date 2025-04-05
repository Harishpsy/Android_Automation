package Menu.MyCourse;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static io.appium.java_client.AppiumBy.id;

public class myCourse extends BaseActions {

    public myCourse(AndroidDriver driver) {
        super(driver);
    }

    /**
     * Test method to interact with the My Course section in the application.
     * <p>
     * This method performs two main actions:
     * 1. Clicks the "My Course" button located in the side menu to navigate to the My Course section.
     * 2. Click the back button to return to the previous screen after accessing the My Course section.
     * <p>
     * The method outputs console messages and ExtentReports logs confirming the execution of each action.
     */
    @Test
    public void clickingMyCourse() throws InterruptedException {
        try {
            // Clicking the menu Button
            clickMenu();
            test.log(Status.PASS, "Successfully clicked menu button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Menu Clicked")).build());

            // Clicking the My Course Button in the side bar
            clickElement(id("com.affairscloud:id/item_my_courses"));
            test.log(Status.PASS, "Successfully clicked My Course button in side menu",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("My Course Clicked")).build());
            System.out.println("Successfully Clicked The My Course Button In The Side Menu");

            // Clicking The back button
            clickElement(By.id("com.affairscloud:id/img_back_press"));
            test.log(Status.PASS, "Successfully clicked back button in My Course",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Back Clicked")).build());
            System.out.println("Successfully Clicked The Back Button In My Course");

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform My Course actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("My Course Error")).build());
            throw e;
        }
    }
}