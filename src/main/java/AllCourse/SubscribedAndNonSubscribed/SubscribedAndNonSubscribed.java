package AllCourse.SubscribedAndNonSubscribed;

import Setup.BaseActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.By.xpath;

public class SubscribedAndNonSubscribed extends BaseActions {

    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds ( 30 );
    private static final int SCROLL_DELAY = 3000;

    public SubscribedAndNonSubscribed(AndroidDriver driver) {
        super ( driver );
    }

    public void performingSubscribeAndUnsubscribeActions() throws InterruptedException {
        Thread.sleep ( SCROLL_DELAY );
        gettingCourseList ( "Subscribed" , "(//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_subscribed\"])/parent::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\"]" );
        scrollToBeginning ();
        Thread.sleep ( SCROLL_DELAY );
        gettingCourseList ( "Not Subscribed" , "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/rl_price\"]/parent::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\"]" );
        scrollToBeginning ();
    }

    public int gettingCourseList(String courseType , String xpathExpression) throws InterruptedException {
        Set<String> courses = new HashSet<> (); // To track unique course names
        int uniqueCourseCount = 0;
        int previousElementCount = 0;
        int retryCount = 0; // To handle cases where new data takes time to load
        final int MAX_RETRIES = 3; // Maximum number of retries if no new data is found

        while (true) {
            List<WebElement> courseElements = driver.findElements ( xpath ( xpathExpression ) );
            int currentElementCount = courseElements.size ();

            // If no new elements are found after scrolling, increment retry count
            if (currentElementCount == previousElementCount) {
                retryCount++;
                if (retryCount >= MAX_RETRIES) {
                    break; // Exit if no new data is found after maximum retries
                }
            } else {
                retryCount = 0; // Reset retry count if new elements are found
            }

            previousElementCount = currentElementCount;

            // Process the current set of elements
            for (WebElement courseElement : courseElements) {
                String courseName = courseElement.getText ().trim (); // Trim to avoid whitespace issues

                if (!courseName.isEmpty () && courses.add ( courseName )) { // Only adds if it's not already present
                    System.out.println ( courseType + " Course Name: " + courseName );
                    uniqueCourseCount++;
                }
            }

            // Scroll down to load more content
            scrollDown ();
        }

        // Print the count of unique courses
        System.out.println ( "Total unique " + courseType.toLowerCase () + " courses: " + uniqueCourseCount );

        return uniqueCourseCount;
    }

    private void scrollDown() throws InterruptedException {
        Thread.sleep ( SCROLL_DELAY );
        driver.findElement ( new AppiumBy.ByAndroidUIAutomator ( "new UiScrollable(new UiSelector().scrollable(true)).scrollForward();" ) );
    }

}