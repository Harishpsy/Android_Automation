package AllCourse.SubscribedAndNonSubscribed;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.By.xpath;

public class SubscribedAndNonSubscribed extends BaseActions {

    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(30);
    private static final int SCROLL_DELAY = 3000;
    private static final int MAX_RETRIES = 3;

    public SubscribedAndNonSubscribed(AndroidDriver driver) {
        super(driver);
    }

    public void performingSubscribeAndUnsubscribeActions() throws InterruptedException {
        try {
            Thread.sleep(SCROLL_DELAY);
            int subscribedCount = gettingCourseList("Subscribed", "(//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_subscribed\"])/parent::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\"]");
            scrollToBeginning();
            Thread.sleep(SCROLL_DELAY);
            int nonSubscribedCount = gettingCourseList("Not Subscribed", "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/rl_price\"]/parent::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\"]");
            scrollToBeginning();

            test.log(Status.PASS, "Course listing completed - Subscribed: " + subscribedCount +
                            ", Non-Subscribed: " + nonSubscribedCount,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("CourseListingComplete")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Course listing failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("CourseListingFailed")).build());
            throw e;
        }
    }

    public int gettingCourseList(String courseType, String xpathExpression) throws InterruptedException {
        Set<String> courses = new HashSet<>();
        int uniqueCourseCount = 0;
        int previousElementCount = 0;
        int retryCount = 0;
        int scrollCount = 0;

        test.log(Status.INFO, "Starting to list " + courseType + " courses");

        while (true) {
            List<WebElement> courseElements = driver.findElements(xpath(xpathExpression));
            int currentElementCount = courseElements.size();

            if (currentElementCount == previousElementCount) {
                retryCount++;
                test.log(Status.INFO, "No new courses found, retry attempt: " + retryCount);

                if (retryCount >= MAX_RETRIES) {
                    test.log(Status.INFO, "Maximum retries reached for " + courseType + " courses");
                    break;
                }
            } else {
                retryCount = 0;
            }

            previousElementCount = currentElementCount;

            for (WebElement courseElement : courseElements) {
                String courseName = courseElement.getText().trim();

                if (!courseName.isEmpty() && courses.add(courseName)) {
                    test.log(Status.INFO, courseType + " Course: " + courseName);
                    System.out.println(courseType + " Course Name: " + courseName);
                    uniqueCourseCount++;
                }
            }

            scrollDown();
            scrollCount++;
            test.log(Status.INFO, "Scrolled down (" + scrollCount + " times)");
        }

        test.log(Status.INFO, "Total unique " + courseType.toLowerCase() + " courses: " + uniqueCourseCount);
        System.out.println("Total unique " + courseType.toLowerCase() + " courses: " + uniqueCourseCount);

        return uniqueCourseCount;
    }


}