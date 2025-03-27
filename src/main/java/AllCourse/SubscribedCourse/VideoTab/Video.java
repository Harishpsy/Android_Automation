package AllCourse.SubscribedCourse.VideoTab;

import Menu.MyNotes.Video_Module;
import Setup.BaseActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Video extends BaseActions {

    private static final String VIDEO_TITLE_XPATH = "//*[@resource-id=\"com.affairscloud:id/videos_list\"]/android.widget.RelativeLayout/child::*[@resource-id=\"com.affairscloud:id/txt_courses_title\"]";

    private Video_Module video;

    public Video(AndroidDriver driver){
        super(driver);
        video = new Video_Module(driver);   // Initialize the video_Module
    }

    public void performingVideoAction() throws InterruptedException {
        clickingVideoTab();
//        threedotsAction();
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
        getCourseNames();
        verifyDuplicateCourseNames();
        scrollToBeginning ();
        clickingvideo();
    }

    protected void clickingVideoTab(){
        clickElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Videos\"]" ) );
    }

    protected void clickingvideo() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait ( driver, Duration.ofSeconds ( 60 ) );
        clickElement ( By.xpath ( "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/videos_list\"]/android.widget.RelativeLayout[1]" ) );
        System.out.println ("Successfully Clicked The Video");
        video.navigateBackToApp();
    }

    // Getting all course names
    protected int getCourseNames() throws InterruptedException {
        Set<String> seenCourses = new HashSet<> (); // To track unique Video names
        int uniqueCourseCount = 0;
        int scrollCount = 0; // Counter to track the number of scrolls

        while (scrollCount < 5) { // Scroll exactly five times
            List<WebElement> courseElements = driver.findElements(By.xpath(VIDEO_TITLE_XPATH));
            boolean newDataFound = false;

            for (WebElement courseElement : courseElements) {
                String courseName = courseElement.getText();

                if (seenCourses.add(courseName)) { // Only adds if it's not already present
                    System.out.println("Video Name: " + courseName);
                    newDataFound = true;
                    uniqueCourseCount++;
                }
            }

            if (!newDataFound) {
                break; // If no new data is found after scrolling, exit the loop
            }

            scrollDown();
            scrollCount++; // Increment the scroll counter
            System.out.println("No. Of Times Scrolled: " + scrollCount);

            // Add a small delay to allow new content to load
            Thread.sleep(2000); // Adjust the delay as needed
        }

        // Print the count of unique courses
        System.out.println("Total unique Video Names: " + uniqueCourseCount);
        return uniqueCourseCount;
    }

    protected void verifyDuplicateCourseNames() {
        try {
            List<WebElement> allCourseElements = driver.findElements(By.xpath(VIDEO_TITLE_XPATH));

            if (allCourseElements.isEmpty()) {
                System.out.println("No Video elements found on the list page.");
                return;
            }

            Set<String> uniqueNames = new HashSet<>();
            boolean duplicateFound = false;

            for (WebElement element : allCourseElements) {
                String courseName = element.getText();

                if (!uniqueNames.add(courseName)) { // If the name is already in the set, it's a duplicate
                    System.out.println("Duplicate Found In The Video List Page: " + courseName);
                    duplicateFound = true;
                }
            }

            if (!duplicateFound) {
                System.out.println("No duplicates found in the Video list page.");
            }

        } catch (NoSuchElementException e) {
            System.out.println("The Video elements were not found: " + e.getMessage());
        }
    }
}
