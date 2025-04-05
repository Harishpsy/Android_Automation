package AllCourse.AllCourseTab;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.By.xpath;

public class AllCourseActions extends BaseActions {

    private static final String COURSE_TITLE_XPATH = "//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\"]";

    public AllCourseActions(AndroidDriver driver) {
        super(driver);
    }

    public void performingAllCourseActions() throws InterruptedException {
        try {
            int courseCount = getCourseNames();
            scrollToBeginning();
            verifyDuplicateCourseNames();

            test.log(Status.PASS, "Successfully processed " + courseCount + " unique courses",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("AllCoursesProcessed")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to process all courses: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("AllCoursesFailed")).build());
            throw e;
        }
    }

    // Getting all course names
    private int getCourseNames() throws InterruptedException {
        Set<String> seenCourses = new HashSet<>();
        int uniqueCourseCount = 0;
        int scrollAttempts = 0;
        final int MAX_SCROLL_ATTEMPTS = 10;

        while (scrollAttempts < MAX_SCROLL_ATTEMPTS) {
            List<WebElement> courseElements = driver.findElements(xpath(COURSE_TITLE_XPATH));
            boolean newDataFound = false;

            for (WebElement courseElement : courseElements) {
                String courseName = courseElement.getText();

                if (seenCourses.add(courseName)) {
                    test.log(Status.INFO, "Found course: " + courseName);
                    System.out.println("Course Name: " + courseName);
                    newDataFound = true;
                    uniqueCourseCount++;
                }
            }

            if (!newDataFound) {
                scrollAttempts++;
                test.log(Status.INFO, "No new courses found, scroll attempt: " + scrollAttempts);
            } else {
                scrollAttempts = 0; // Reset counter if we found new courses
            }

            if (scrollAttempts >= MAX_SCROLL_ATTEMPTS) {
                test.log(Status.INFO, "Reached maximum scroll attempts (" + MAX_SCROLL_ATTEMPTS + ")");
                break;
            }

            scrollDown();
        }

        test.log(Status.INFO, "Total unique courses found: " + uniqueCourseCount,
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("UniqueCoursesCount")).build());
        System.out.println("Total unique courses: " + uniqueCourseCount);
        return uniqueCourseCount;
    }

    private void verifyDuplicateCourseNames() {
        try {
            List<WebElement> allCourseElements = driver.findElements(xpath(COURSE_TITLE_XPATH));

            if (allCourseElements.isEmpty()) {
                test.log(Status.WARNING, "No course elements found on the list page");
                System.out.println("No course elements found on the list page.");
                return;
            }

            Set<String> uniqueNames = new HashSet<>();
            boolean duplicateFound = false;

            for (WebElement element : allCourseElements) {
                String courseName = element.getText();

                if (!uniqueNames.add(courseName)) {
                    test.log(Status.WARNING, "Duplicate course found: " + courseName);
                    System.out.println("Duplicate Found In The List Page: " + courseName);
                    duplicateFound = true;
                }
            }

            if (!duplicateFound) {
                test.log(Status.PASS, "No duplicates found in course list",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("NoDuplicatesFound")).build());
                System.out.println("No duplicates found in the course list page.");
            }

        } catch (NoSuchElementException e) {
            test.log(Status.FAIL, "Course elements not found: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("CourseElementsNotFound")).build());
            System.out.println("The course elements were not found: " + e.getMessage());
        }
    }

}