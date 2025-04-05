package AllCourse.SubscribedCourse.ArticleTab;

import AllCourse.AllCourseTab.AllCourseActions;
import Menu.MyNotes.Article_Module;
import Setup.BaseActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.time.Duration;
import java.util.*;

import static org.openqa.selenium.By.*;

public class Article extends BaseActions {

    private Article_Module article;

    private final Map<String, String> articleNames = new HashMap<> ();

    private static final String ARTICLE_TITLE_XPATH = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/article_list\"]/android.widget.RelativeLayout/child::*[@resource-id=\"com.affairscloud:id/courses_title\"]";

    public Article(AndroidDriver driver) {
        super(driver);
        article = new Article_Module(driver);
    }

    public void performArticleActions() throws InterruptedException {
        clickArticle();
        storeArticleNameBeforeClick();
        clickingArticle();
        storeArticleNameAfterClick();
        verifyArticleNames();
        scrollArticleList();
//        footerCommonActions ();
        threeDotsActions ();
        navigateBack ();
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds (30) );
        getCourseNames();
        verifyDuplicateCourseNames();
        scrollToBeginning ();
    }

    protected void clickArticle() {
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds(30));
        clickElement ( xpath ( "//android.widget.LinearLayout[@content-desc=\"Articles\"]" ) );
        test.log( Status.PASS, "Successfully Clicked The Article Tab",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked The Article Tab")).build());
        System.out.println ("Successfully Clicked The Article Tab");
    }

    protected void thredotsActions() throws InterruptedException {
        threedots ();
        share ();
        threedots ();
        reportAction();
    }

    protected void clickingArticle(){
        clickElement ( xpath ( "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/article_list\"]/android.widget.RelativeLayout[1]/child::*[@resource-id=\"com.affairscloud:id/courses_title\"]" ) );
        test.log( Status.PASS, "Successfully Clicked The Article",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked The Article")).build());
        System.out.println ("Successfully Clicked The Article");
    }

    protected void storeArticleNameBeforeClick() {
        String articleTitleBeforeClick = getText ( By.xpath ( "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/article_list\"]/android.widget.RelativeLayout[1]/child::*[@resource-id=\"com.affairscloud:id/courses_title\"]" ) );
        articleNames.put ( "beforeClick" , articleTitleBeforeClick );
        System.out.println ( "Article name before click: " + articleTitleBeforeClick );
    }

    protected void storeArticleNameAfterClick() throws InterruptedException {
        Thread.sleep ( 5000 );
        String articleTitleAfterClick = getText ( By.id ( "com.affairscloud:id/title" ) );
        articleNames.put ( "afterClick" , articleTitleAfterClick );
        System.out.println ( "Article name after click: " + articleTitleAfterClick );
    }

    public void scrollArticleList() throws InterruptedException {
        /* Calling The Scroll Method */
        scrollList ();
        test.log( Status.PASS, "Successfully Scrolled Article List",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Scrolled Article List")).build());
        System.out.println ( "Scrolling through the article list." );
    }

    protected void verifyArticleNames() {
        String beforeClick = articleNames.get ( "beforeClick" );
        String afterClick = articleNames.get ( "afterClick" );

        if (beforeClick != null && beforeClick.equals ( afterClick )) {
            test.log( Status.PASS, "Article names match before and after clicking",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article names match")).build());
            System.out.println ( "✅ Article names match before and after clicking." );
        } else {
            test.log( Status.FAIL, "Article names do NOT match",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article names mismatch")).build());
            System.out.println ( "❌ Article names do NOT match!" );
            System.out.println ( "🔹 Before: [" + beforeClick + "], After: [" + afterClick + "]" );
        }
    }

    protected int getCourseNames() throws InterruptedException {
        Set<String> seenCourses = new HashSet<>(); // To track unique Article names
        int uniqueCourseCount = 0;
        int scrollCount = 0; // Counter to track the number of scrolls

        while (scrollCount < 5) { // Scroll exactly five times
            List<WebElement> courseElements = driver.findElements(By.xpath(ARTICLE_TITLE_XPATH));
            boolean newDataFound = false;

            for (WebElement courseElement : courseElements) {
                String courseName = courseElement.getText();

                if (seenCourses.add(courseName)) { // Only adds if it's not already present
                    System.out.println("Course Name: " + courseName);
                    newDataFound = true;
                    uniqueCourseCount++;
                }
            }

            if (!newDataFound) {
                break; // If no new data is found after scrolling, exit the loop
            }

            scrollDown();
            scrollCount++; // Increment the scroll counter
            test.log( Status.PASS, "Scrolled Article List - Count: " + scrollCount,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll " + scrollCount)).build());
            System.out.println("No. Of Times Scrolled: " + scrollCount);

            // Add a small delay to allow new content to load
            Thread.sleep(2000); // Adjust the delay as needed
        }

        // Print the count of unique courses
        test.log( Status.INFO, "Total unique Article Names: " + uniqueCourseCount,
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Unique Articles Count")).build());
        System.out.println("Total unique Article Names: " + uniqueCourseCount);
        return uniqueCourseCount;
    }

    protected void verifyDuplicateCourseNames() {
        try {
            List<WebElement> allCourseElements = driver.findElements(By.xpath(ARTICLE_TITLE_XPATH));

            if (allCourseElements.isEmpty()) {
                test.log( Status.WARNING, "No course elements found on the list page",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Articles Found")).build());
                System.out.println("No course elements found on the list page.");
                return;
            }

            Set<String> uniqueNames = new HashSet<>();
            boolean duplicateFound = false;

            for (WebElement element : allCourseElements) {
                String courseName = element.getText();

                if (!uniqueNames.add(courseName)) { // If the name is already in the set, it's a duplicate
                    test.log( Status.WARNING, "Duplicate Found In The Article List Page: " + courseName,
                            MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Duplicate Article")).build());
                    System.out.println("Duplicate Found In The Article List Page: " + courseName);
                    duplicateFound = true;
                }
            }

            if (!duplicateFound) {
                test.log( Status.PASS, "No duplicates found in the Article list page",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Duplicates")).build());
                System.out.println("No duplicates found in the Article list page.");
            }

        } catch (NoSuchElementException e) {
            test.log( Status.FAIL, "The Article elements were not found: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Element Not Found")).build());
            System.out.println("The Article elements were not found: " + e.getMessage());
        }
    }

    public void scrollDown() throws InterruptedException {
        // Ensure the scrollable container is correctly identified
        driver.findElement(new AppiumBy.ByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));

        // Add a small delay to allow the scroll action to complete
        Thread.sleep(1000); // Adjust the delay as needed
    }
}