package Doubts.MyDoubts;

import Menu.AllCourse.allCourse;
import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class myDoubts extends BaseActions {
    private final allCourse allCourseModule;

    public myDoubts(AndroidDriver driver) {
        super(driver);
        this.allCourseModule = new allCourse(driver);
    }

    public void performMyDoubtsActions() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        clickMyDoubts();
        noFollowedDoubt();

    }
    private void noFollowedDoubt() {

        try {

            WebElement noData = driver.findElement ( By.xpath ( "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/rl_mypost\"]" ) );

            if (noData.isDisplayed ()) {
                test.log( Status.PASS, "There Is No Data In The My Doubts Page",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Data Found In The My Doubts Pag")).build());
                System.out.println ( "There Is No Data In The My Doubts Page" );
            }else {
                verifyUserNameInMyDoubts();
            }

        }catch (NoSuchElementException e){
            test.log( Status.FAIL, "Error In The My Doubts Page While performing Actions",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Error In The My Doubts Page")).build());
            System.out.println ("Error In The My Doubts Page While performing Actions");
        }
    }

    private void clickMyDoubts() {
        clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"My Doubts\"]"));
        test.log( Status.PASS, "Successfully clicked The My Doubts Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The My Doubts Button")).build());
        System.out.println("Successfully clicked on My Doubts");
    }

    private void verifyUserNameInMyDoubts() {

        // Get usernames from My Doubts section
        List<WebElement> userNameElements = driver.findElements(
                By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/user_details\"]" +
                        "/child::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/txt_user_name\"]"));

        // Compare stored username with each username found in My Doubts
        for (WebElement userNameElement : userNameElements) {
            String myDoubtsUserName = userNameElement.getText();
            System.out.println ("My Doubts User Name: " + myDoubtsUserName);

            if (myDoubtsUserName.equals ( allCourseModule.userName )) {
                test.log( Status.PASS, "VERIFICATION SUCCESS: User names match",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("User names match")).build());
                System.out.println("VERIFICATION SUCCESS: User names match");
            }else {
                test.log( Status.FAIL, "VERIFICATION Error: User names not match",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("User names not match")).build());
                System.out.println("VERIFICATION Error: User names not match");
            }
        }
    }
}