package AllCourse.SubscribedCourse.VideoTab;

import Menu.MyNotes.Video_Module;
import Setup.BaseActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Video extends BaseActions {

    private static final String VIDEO_TITLE_XPATH = "//*[@resource-id=\"com.affairscloud:id/videos_list\"]/android.widget.RelativeLayout/child::*[@resource-id=\"com.affairscloud:id/txt_courses_title\"]";

    private Video_Module video;
    private WebDriverWait wait;

    public Video(AndroidDriver driver) {
        super(driver);
        video = new Video_Module(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void performingVideoAction() throws InterruptedException {
        try {
            test.log(Status.INFO, "Starting Video Actions",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Video Start")).build());

            clickingVideoTab();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            getCourseNames();
            verifyDuplicateCourseNames();
            scrollToBeginning();
            clickingvideo();

            test.log(Status.PASS, "Successfully completed all video actions",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Video Complete")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Error performing video actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Video Error")).build());
            throw e;
        }
    }

    protected void clickingVideoTab() {
        clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Videos\"]"));
        test.log(Status.PASS, "Successfully Clicked Video Tab",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Video Tab Clicked")).build());
        System.out.println("Successfully Clicked Video Tab");
    }

    protected void clickingvideo() throws InterruptedException {
        try {
            clickElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/videos_list\"]/android.widget.RelativeLayout[1]"));
            test.log(Status.PASS, "Successfully Clicked Video",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Video Clicked")).build());
            System.out.println("Successfully Clicked The Video");

            video.navigateBackToApp();
            test.log(Status.PASS, "Navigated Back From Video",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Video Back")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click video: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Video Click Failed")).build());
            throw e;
        }
    }

    protected int getCourseNames() throws InterruptedException {
        Set<String> seenCourses = new HashSet<>();
        int uniqueCourseCount = 0;
        int scrollCount = 0;

        test.log(Status.INFO, "Starting to collect video names",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Video Names Start")).build());

        while (scrollCount < 5) {
            List<WebElement> courseElements = wait.until(d -> d.findElements(By.xpath(VIDEO_TITLE_XPATH)));
            boolean newDataFound = false;

            for (WebElement courseElement : courseElements) {
                String courseName = courseElement.getText();

                if (seenCourses.add(courseName)) {
                    test.log(Status.INFO, "Found Video: " + courseName);
                    System.out.println("Video Name: " + courseName);
                    newDataFound = true;
                    uniqueCourseCount++;
                }
            }

            if (!newDataFound) {
                test.log(Status.INFO, "No new videos found after scroll",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No New Videos")).build());
                break;
            }

            scrollDown();
            scrollCount++;
            test.log(Status.INFO, "Scrolled Video List - Count: " + scrollCount,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll " + scrollCount)).build());
            System.out.println("No. Of Times Scrolled: " + scrollCount);

            Thread.sleep(2000);
        }

        test.log(Status.INFO, "Total unique Video Names: " + uniqueCourseCount,
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Unique Videos")).build());
        System.out.println("Total unique Video Names: " + uniqueCourseCount);

        return uniqueCourseCount;
    }

    protected void verifyDuplicateCourseNames() {
        try {
            List<WebElement> allCourseElements = wait.until(d -> d.findElements(By.xpath(VIDEO_TITLE_XPATH)));

            if (allCourseElements.isEmpty()) {
                test.log(Status.WARNING, "No video elements found on the list page",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Videos Found")).build());
                System.out.println("No video elements found on the list page.");
                return;
            }

            Set<String> uniqueNames = new HashSet<>();
            boolean duplicateFound = false;

            for (WebElement element : allCourseElements) {
                String courseName = element.getText();

                if (!uniqueNames.add(courseName)) {
                    test.log(Status.WARNING, "Duplicate Video Found: " + courseName,
                            MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Duplicate Video")).build());
                    System.out.println("Duplicate Found In The Video List Page: " + courseName);
                    duplicateFound = true;
                }
            }

            if (!duplicateFound) {
                test.log(Status.PASS, "No duplicates found in the video list page",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Duplicates")).build());
                System.out.println("No duplicates found in the video list page.");
            }

        } catch (NoSuchElementException e) {
            test.log(Status.FAIL, "The video elements were not found: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Videos Not Found")).build());
            System.out.println("The video elements were not found: " + e.getMessage());
        }
    }
}