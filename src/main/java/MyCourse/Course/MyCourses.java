package MyCourse.Course;

import AllCourse.AllCourseTab.AllCourseActions;
import AllCourse.Filter.filter;
import MyCourse.Search.Search;
import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MyCourses extends BaseActions {

    private AllCourseActions ALLCOURSEMODULE;
    private filter FILTERACTION;
    private Search SEARCHACTIONS;

    public MyCourses(AndroidDriver driver) {
        super(driver);
        ALLCOURSEMODULE = new AllCourseActions(driver);
        FILTERACTION = new filter(driver);
        SEARCHACTIONS = new Search(driver);
    }

    public void performMyCourseAction() throws InterruptedException {
        try {
            clickMyCourse();
            ALLCOURSEMODULE.performingAllCourseActions();
            scrollToBeginning();
            clickingParticularCourse();
            navigateBack();
            clickingParticularCourse();
            SEARCHACTIONS.performSearchActions();
            FILTERACTION.performMyCourseFilterAction();
            threedots();
            share();
            threedots();
            reportAction();
            navigateBack();

            test.log(Status.PASS, "All My Course actions completed successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("MyCourseCompleted")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform My Course actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("MyCourseFailed")).build());
            throw e;
        }
    }

    private void clickMyCourse() {
        try {
            clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"My Courses\"]"));
            test.log(Status.PASS, "My Courses tab clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("MyCoursesTabClicked")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click My Courses tab: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("MyCoursesTabFailed")).build());
            throw e;
        }
    }

    private void clickingParticularCourse() {
        try {
            clickElement(By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/root_view\"]/following::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\" and @text=\"Crack Current Affairs 2025 PDF\"]"));
            test.log(Status.PASS, "Current Affairs course clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("CurrentAffairsClicked")).build());
            System.out.println("Successfully clicked The Current Affairs Course");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click Current Affairs course: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("CurrentAffairsFailed")).build());
            throw e;
        }
    }

}