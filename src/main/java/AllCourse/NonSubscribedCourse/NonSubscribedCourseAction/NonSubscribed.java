package AllCourse.NonSubscribedCourse.NonSubscribedCourseAction;

import AllCourse.NonSubscribedCourse.PathTab.PathNonSubscribed;
import AllCourse.NonSubscribedCourse.SubscribeNow.SubscribeNow;
import AllCourse.SubscribedAndNonSubscribed.SubscribedAndNonSubscribed;
import AllCourse.SubscribedCourse.DetailsTab.Details;
import AllCourse.SubscribedCourse.FreeTab.Free;
import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

public class NonSubscribed extends BaseActions {

    private SubscribedAndNonSubscribed course;
    private Details detailModule;
    private Free freeModule;
    private PathNonSubscribed pathsModule;
    private SubscribeNow SubscribeNowModule;

    public NonSubscribed(AndroidDriver driver) {
        super(driver);
        course = new SubscribedAndNonSubscribed(driver);
        detailModule = new Details(driver);
        SubscribeNowModule = new SubscribeNow(driver);
        pathsModule = new PathNonSubscribed(driver);
        freeModule = new Free(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void performNonSubscribedActions() throws InterruptedException {
        try {
            clickingCourse();
            detailActions();
            pathActions();
            freeActions();
            navigateBack();

            test.log(Status.PASS, "Non-subscribed course actions completed successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("NonSubscribedActionsComplete")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Non-subscribed course actions failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("NonSubscribedActionsFailed")).build());
            throw e;
        }
    }

    private void clickingCourse() throws InterruptedException {
        try {
            course.gettingCourseList("Not Subscribed", "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/rl_price\"]/parent::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\"]");
            scrollToBeginning();

            try {
                clickElement(By.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/rl_price\"]/parent::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\"])[1]"));
                test.log(Status.PASS, "Clicked on non-subscribed course",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("NonSubscribedCourseClicked")).build());
            } catch (NoSuchElementException e) {
                test.log(Status.WARNING, "Non-subscribed course not found");
                System.out.println("Non-Subscribed Course Was Not Found");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click non-subscribed course: " + e.getMessage());
            throw e;
        }
    }

    private void detailActions() throws InterruptedException {
        try {
            Thread.sleep(1000);
            detailModule.email();
            test.log(Status.INFO, "Email action performed");

            Thread.sleep(1000);
            detailModule.message();
            test.log(Status.INFO, "Message action performed");

            Thread.sleep(1000);
            detailModule.phone();
            test.log(Status.INFO, "Phone action performed");

            scrollToEnd();
            detailModule.faqPlusIcon();
            test.log(Status.INFO, "FAQ action performed");

            scrollToBeginning();
            SubscribeNowModule.performSubscribeNowAction();

        } catch (Exception e) {
            test.log(Status.FAIL, "Detail actions failed: " + e.getMessage());
            throw e;
        }
    }

    private void pathActions() throws InterruptedException {
        try {
            pathsModule.performPathActions();
            Thread.sleep(2000);
            backAction();

        } catch (Exception e) {
            test.log(Status.FAIL, "Path actions failed: " + e.getMessage());
            throw e;
        }
    }

    private void freeActions() throws InterruptedException {
        try {
            freeModule.performFreeActions();
            test.log(Status.INFO, "Free actions performed");

        } catch (Exception e) {
            test.log(Status.FAIL, "Free actions failed: " + e.getMessage());
            throw e;
        }
    }

    private void backAction() {
        clickElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/ivArrowBack\"]"));
        test.log(Status.PASS, "Clicked back button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("BackButtonClicked")).build());
        System.out.println("Successfully Clicked The Back Button");
    }
}