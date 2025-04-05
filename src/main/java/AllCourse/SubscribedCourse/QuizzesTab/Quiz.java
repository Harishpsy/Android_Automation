package AllCourse.SubscribedCourse.QuizzesTab;

import Setup.BaseActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Quiz extends BaseActions {

    private static final String QUIZ_TITLE_XPATH = "(//*[@resource-id=\"com.affairscloud:id/rlPublishedLayout\"])/child::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/tvMockTestTittle\"]";

    public Quiz(AndroidDriver driver) {
        super(driver);
    }

    public void performQuizActions() throws InterruptedException {
        try {
            clickingQuizTab();
            threedotsAction();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            getCourseNames();
            verifyDuplicateCourseNames();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            scrollToBeginning();

            test.log(Status.PASS, "Successfully completed all quiz actions",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Quiz Actions Complete")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Error performing quiz actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Quiz Error")).build());
            throw e;
        }
    }

    protected void clickingQuizTab() {
        clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Quizzes\"]"));
        test.log(Status.PASS, "Successfully Clicked The Quiz Tab",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Quiz Tab Clicked")).build());
        System.out.println("Successfully Clicked The Quiz Tab");
    }

    protected int getCourseNames() throws InterruptedException {
        Set<String> seenCourses = new HashSet<>();
        int uniqueCourseCount = 0;
        int scrollCount = 0;

        test.log(Status.INFO, "Starting to collect quiz names",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Quiz Names Start")).build());

        while (scrollCount < 5) {
            List<WebElement> courseElements = driver.findElements(By.xpath(QUIZ_TITLE_XPATH));
            boolean newDataFound = false;

            for (WebElement courseElement : courseElements) {
                String courseName = courseElement.getText();

                if (seenCourses.add(courseName)) {
                    test.log(Status.INFO, "Found Quiz: " + courseName);
                    System.out.println("Quiz Name: " + courseName);
                    newDataFound = true;
                    uniqueCourseCount++;
                }
            }

            if (!newDataFound) {
                test.log(Status.INFO, "No new quizzes found after scroll",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No New Quizzes")).build());
                break;
            }

            scrollDown();
            scrollCount++;
            test.log(Status.INFO, "Scrolled Quiz List - Count: " + scrollCount,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll " + scrollCount)).build());
            System.out.println("No. Of Times Scrolled: " + scrollCount);

            Thread.sleep(2000);
        }

        test.log(Status.INFO, "Total unique Quiz Names: " + uniqueCourseCount,
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Unique Quizzes")).build());
        System.out.println("Total unique Quiz Names: " + uniqueCourseCount);

        return uniqueCourseCount;
    }

    protected void verifyDuplicateCourseNames() {
        try {
            List<WebElement> allCourseElements = driver.findElements(By.xpath(QUIZ_TITLE_XPATH));

            if (allCourseElements.isEmpty()) {
                test.log(Status.WARNING, "No quiz elements found on the list page",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Quizzes Found")).build());
                System.out.println("No quiz elements found on the list page.");
                return;
            }

            Set<String> uniqueNames = new HashSet<>();
            boolean duplicateFound = false;

            for (WebElement element : allCourseElements) {
                String courseName = element.getText();

                if (!uniqueNames.add(courseName)) {
                    test.log(Status.WARNING, "Duplicate Quiz Found: " + courseName,
                            MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Duplicate Quiz")).build());
                    System.out.println("Duplicate Found In The Quiz List Page: " + courseName);
                    duplicateFound = true;
                }
            }

            if (!duplicateFound) {
                test.log(Status.PASS, "No duplicates found in the quiz list page",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Duplicates")).build());
                System.out.println("No duplicates found in the quiz list page.");
            }

        } catch (NoSuchElementException e) {
            test.log(Status.FAIL, "The quiz elements were not found: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Quizzes Not Found")).build());
            System.out.println("The quiz elements were not found: " + e.getMessage());
        }
    }
}