package Menu.MyNotes;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Video_Module extends BaseActions {

    public Video_Module(AndroidDriver driver) {
        super(driver);
    }

    public void performVideoActions() throws InterruptedException {
        try {
            WebElement video = driver.findElement(By.id("com.affairscloud:id/card_view_videos"));

            if (video.isDisplayed()) {
                test.log(Status.PASS, "Video found in the list page",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Video Found")).build());
                videoActions();
            }
        } catch (NoSuchElementException e) {
            test.log(Status.INFO, "No Video is present in the list page",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Video Found")).build());
            System.out.println("No Video is present in the list page");
        }
    }

    public void videoActions() throws InterruptedException {
        try {
            /* Calling The Course Title Method clicking */
            courseTitle();

            /* Calling The play Button Method */
            playButtonClick();

            /* Switch back to your application */
            navigateBackToApp();

            /* ThreeDot Action Method we are calling */
            Thread.sleep(5000);
            threeDotsActions();

            /* footer Section */
            try {
                footerCommonActions();
                test.log(Status.PASS, "Successfully Performed The Footer section of the card",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Footer Actions Completed")).build());
                System.out.println("Successfully Performed The Footer section of the card");
            } catch (NoSuchElementException e) {
                test.log(Status.INFO, "No data present in the list page",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Footer Data")).build());
                System.out.println("No data present in the list page");
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform video actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Video Actions")).build());
            throw e;
        }
    }

    public void playButtonClick() {
        try {
            /* Clicking The Video Play Button */
            clickElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/play\"]"));
            test.log(Status.PASS, "Successfully Clicked the Play Button In The Video Module",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked Play Button")).build());
            System.out.println("Successfully Clicked the Play Button In The Video Module");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click play button: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Play Button")).build());
            throw e;
        }
    }

    public void courseTitle() throws InterruptedException {
        try {
            clickElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/llCourseTittle\"]"));
            test.log(Status.PASS, "Successfully Clicked The Course Title In The Video Module",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked Course Title")).build());
            System.out.println("Successfully Clicked The Course Title In The Video Module");

            /* Clicking The Back Button */
            Thread.sleep(4000);
            navigateBack();
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click course title: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Course Title")).build());
            throw e;
        }
    }

    public void navigateBackToApp() throws InterruptedException {
        try {
            /* Open Recent Apps */
            driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
            test.log(Status.INFO, "Opened Recent Apps",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Recent Apps Opened")).build());
            System.out.println("Opened Recent Apps");

            /* Wait and then try to activate your app again */
            Thread.sleep(2000);

            driver.activateApp("com.affairscloud");
            test.log(Status.PASS, "Switched back to the application",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("App Reactivated")).build());
            System.out.println("Switched back to the application.");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to navigate back to app: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed App Switch")).build());
            throw e;
        }
    }

}