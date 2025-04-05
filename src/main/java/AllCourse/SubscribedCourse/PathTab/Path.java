package AllCourse.SubscribedCourse.PathTab;

import Menu.MyEbooks.myEbooks;
import Menu.MyNotes.Article_Module;
import Setup.BaseActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Path extends BaseActions {
    private static final String TITLE_XPATH = "//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_title\"]";
    private static final String PATH_TAB_XPATH = "//android.widget.LinearLayout[@content-desc=\"Path\"]";
    private static final String SUB_PATH_XPATH = "(//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_name\"])[1]";
    private static final String THREE_DOTS_XPATH = "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/ivMoreOption\"]";
    private static final String EBOOK_XPATH = "(//android.widget.FrameLayout[@resource-id=\"com.affairscloud:id/cv_path_frequent\"]/following::*[@text = \"Ebook\"])[1]";
    private static final String ARTICLE_XPATH = "(//android.widget.FrameLayout[@resource-id=\"com.affairscloud:id/cv_path_frequent\"]/following::*[@text = \"Article\"])[1]";

    private final myEbooks ebookModule;
    private final Article_Module articleModule;
    private final WebDriverWait shortWait;
    private final WebDriverWait longWait;

    public Path(AndroidDriver driver) {
        super(driver);
        this.ebookModule = new myEbooks(driver);
        this.articleModule = new Article_Module(driver);
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.longWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void performPathAction() {
        try {
            clickPath();
            test.log(Status.INFO, "Starting Path Tab Actions",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Path Start")).build());

            clickSubPath();
            clickSubSubPath();
            testAllTabs();
            threedotsPath();
        } finally {
            navigateBackArrow();
            navigateBackArrow();
            test.log(Status.INFO, "Completed Path Tab Actions",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Path Complete")).build());
        }
    }

    public void testAllTabs() {
        String[] tabs = {"Monthly", "Weekly", "Daily", "PIB News"};
        for (String tab : tabs) {
            testTab(tab);
        }
    }

    private void tabActionInPath() {
        clickingEbook();
        clickingArticle();
    }

    private void testTab(String tabName) {
        try {
            WebElement tab = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(String.format("//android.widget.LinearLayout[@content-desc=\"%s\"]", tabName))));

            tab.click();
            test.log(Status.PASS, "Clicked " + tabName + " Tab",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot(tabName + " Tab")).build());
            System.out.println("Successfully clicked the " + tabName + " tab");

            verifyActions();
            tabActionInPath();

        } catch (Exception e) {
            test.log(Status.INFO, tabName + " tab not found",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot(tabName + " Missing")).build());
            System.out.println(tabName + " tab not found");
            tabActionInPath();
        }
    }

    public void clickPath() {
        clickElement(By.xpath(PATH_TAB_XPATH));
        test.log(Status.PASS, "Clicked Path Tab",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Path Tab")).build());
        System.out.println("Successfully clicked the path tab");
    }

    public void clickSubPath() {
        try {
            WebElement subPath = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SUB_PATH_XPATH)));
            clickElement(By.xpath(SUB_PATH_XPATH));
            test.log(Status.PASS, "Clicked Sub Path",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Sub Path")).build());
            System.out.println("Successfully clicked the sub path");
        } catch (Exception e) {
            test.log(Status.INFO, "Sub Path not found",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Sub Path Missing")).build());
            System.out.println("Sub Path was not found");
        }
    }

    public void clickSubSubPath() {
        try {
            WebElement subSubPath = longWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SUB_PATH_XPATH)));
            clickElement(By.xpath(SUB_PATH_XPATH));
            test.log(Status.PASS, "Clicked Sub-Sub Path",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Sub-Sub Path")).build());
            System.out.println("Successfully clicked the Sub Sub path");
        } catch (Exception e) {
            test.log(Status.INFO, "Sub-Sub Path not found",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Sub-Sub Path Missing")).build());
            System.out.println("Sub-Sub Path was not found");
        }
    }

    private void threedotsPath() {
        clickElement(By.xpath(THREE_DOTS_XPATH));
        test.log(Status.PASS, "Clicked Three Dots Menu",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Three Dots")).build());

        report();
        test.log(Status.PASS, "Report Action Completed",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Report")).build());

        reportButton();
        test.log(Status.PASS, "Report Button Clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Report Button")).build());
    }

    private void verifyActions() {
        getTitlesAndVerifyDuplicates();
        scrollToBeginning();
    }

    private void clickingEbook() {
        try {
            WebElement ebook = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(EBOOK_XPATH)));
            test.log(Status.INFO, "Ebook Found in Path",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Ebook Found")).build());
            System.out.println("Ebook was found in the path");

            ebook.click();
            test.log(Status.PASS, "Clicked Ebook",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Ebook Clicked")).build());

            // Ebook module actions with logging
            ebookModule.clickingDownloadButton();
            test.log(Status.PASS, "Clicked Download Button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Download")).build());

            ebookModule.pdfViewerActions();
            test.log(Status.PASS, "PDF Viewer Actions Completed",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PDF Actions")).build());

            ebookModule.clickingReadIcon();
            test.log(Status.PASS, "Clicked Read Icon",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Read Icon")).build());

            ebookModule.pdfViewerActions();
            ebookModule.threeDotsActions();
            ebookModule.footerCommonActions();
            ebookModule.threedots();
            ebookModule.removedSaved();

            navigateBack();
        } catch (Exception e) {
            test.log(Status.INFO, "Ebook not found in path",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Ebook")).build());
            System.out.println("Ebook was not found");
        }
    }

    private void clickingArticle() {
        try {
            WebElement article = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ARTICLE_XPATH)));
            test.log(Status.INFO, "Article Found in Path",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article Found")).build());
            System.out.println("Article was found in the path");

            article.click();
            test.log(Status.PASS, "Clicked Article",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article Clicked")).build());

            // Article module actions with logging
            articleModule.scrollArticleList();
            test.log(Status.PASS, "Scrolled Article List",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article Scroll")).build());

            articleModule.threeDotsActions();
            articleModule.footerCommonActions();

            navigateBack();
        } catch (Exception e) {
            test.log(Status.INFO, "Article not found in path",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Article")).build());
            System.out.println("Article was not found in the path");
        }
    }

    private int getTitlesAndVerifyDuplicates() {
        Set<String> seenCourses = new HashSet<>();
        Set<String> uniqueNames = new HashSet<>();
        boolean duplicateFound = false;
        int scrollCount = 0;
        final int MAX_SCROLLS = 3;

        while (scrollCount < MAX_SCROLLS) {
            List<WebElement> courseElements = shortWait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(TITLE_XPATH)));

            if (courseElements.isEmpty()) {
                test.log(Status.INFO, "No course elements found on path page",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Courses")).build());
                System.out.println("No course elements found on the path page.");
                break;
            }

            boolean newDataFound = false;

            for (WebElement courseElement : courseElements) {
                String courseName = courseElement.getText();

                if (seenCourses.add(courseName)) {
                    test.log(Status.INFO, "Found Title: " + courseName);
                    System.out.println("Title Name: " + courseName);
                    newDataFound = true;
                }

                if (!uniqueNames.add(courseName) && !duplicateFound) {
                    test.log(Status.WARNING, "Duplicate Title Found: " + courseName,
                            MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Duplicate")).build());
                    System.out.println("Duplicate Found In The path List Page: " + courseName);
                    duplicateFound = true;
                }
            }

            if (!newDataFound) break;

            scrollDownSmoothly();
            scrollCount++;
            test.log(Status.INFO, "Scrolled Path Content - Count: " + scrollCount,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll " + scrollCount)).build());
            System.out.println("No. Of Times Scrolled: " + scrollCount);
        }

        test.log(Status.INFO, "Total Unique Titles: " + seenCourses.size(),
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Unique Titles")).build());
        System.out.println("Total unique title Names: " + seenCourses.size());

        if (!duplicateFound) {
            test.log(Status.PASS, "No duplicate titles found",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Duplicates")).build());
            System.out.println("No duplicates found in the path list page.");
        }

        return seenCourses.size();
    }

    public void scrollDownSmoothly() {
        try {
            driver.findElement(new AppiumBy.ByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).setSwipeDeadZonePercentage(0.2).scrollForward(30);"));
            test.log(Status.INFO, "Performed Smooth Scroll",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Smooth Scroll")).build());
        } catch (NoSuchElementException e) {
            test.log(Status.INFO, "Scroll not possible - no scrollable content",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Scroll Content")).build());
            System.out.println("Scroll not possible - no scrollable content found");
        }
    }

    private void navigateBackArrow() {
        try {
            WebElement backArrow = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/ivArrowBack\"]")));
            clickElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/ivArrowBack\"]"));
            test.log(Status.PASS, "Clicked Back Arrow",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Back Arrow")).build());
        } catch (Exception e) {
            test.log(Status.INFO, "Back Arrow not found",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Back Arrow")).build());
            System.out.println("Back Arrow was not found");
        }
    }
}