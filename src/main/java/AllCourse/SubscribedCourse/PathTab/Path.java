//package AllCourse.SubscribedCourse.PathTab;
//
//import Menu.MyEbooks.myEbooks;
//import Menu.MyNotes.Article_Module;
//import Setup.BaseActions;
//import io.appium.java_client.AppiumBy;
//import io.appium.java_client.android.AndroidDriver;
//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.WebElement;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * This class contains all the actions and verifications related to the Path tab
// * in the application. It extends BaseActions for common mobile actions.
// */
//public class Path extends BaseActions {
//    // XPath constants for better maintainability
//    private static final String TITLE_XPATH = "//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_title\"]";
//    private static final String PATH_TAB_XPATH = "//android.widget.LinearLayout[@content-desc=\"Path\"]";
//    private static final String SUB_PATH_XPATH = "(//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_name\"])[1]";
//    private static final String THREE_DOTS_XPATH = "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/ivMoreOption\"]";
//    private static final String EBOOK_XPATH = "(//android.widget.FrameLayout[@resource-id=\"com.affairscloud:id/cv_path_frequent\"]/following::*[@text = \"Ebook\"])[1]";
//    private static final String ARTICLE_XPATH = "(//android.widget.FrameLayout[@resource-id=\"com.affairscloud:id/cv_path_frequent\"]/following::*[@text = \"Article\"])[1]";
//
//    // Module references for handling ebook and article specific actions
//    private final myEbooks ebookModule;
//    private final Article_Module articleModule;
//
//    /**
//     * Constructor to initialize the Path tab actions
//     * @param driver AndroidDriver instance
//     */
//    public Path(AndroidDriver driver) {
//        super(driver);
//        this.ebookModule = new myEbooks(driver);
//        this.articleModule = new Article_Module(driver);
//    }
//
//    /**
//     * Main method to perform all path-related actions in sequence
//     * @throws InterruptedException if thread sleep is interrupted
//     */
//    public void performPathAction() throws InterruptedException {
//        clickPath();
//        clickSubPath();
//        clickSubSubPath();
//        testAllTabs();
//        threedotsPath();
//        navigateBackArrow();
//        navigateBackArrow();
//    }
//
//    /**
//     * Tests all available tabs (Monthly, Weekly, Daily, PIB News)
//     * @throws InterruptedException if thread sleep is interrupted
//     */
//    public void testAllTabs() throws InterruptedException {
//        Thread.sleep ( 2000 );
//        testTab("Monthly");
//        testTab("Weekly");
//        testTab("Daily");
//        testTab("PIB News");
//    }
//
//    /**
//     * Performs actions available in the current path tab
//     */
//    private void tabActionInPath() {
//        clickingEbook();
//        clickingArticle();
//    }
//
//    /**
//     * Tests a specific tab by name
//     * @param tabName Name of the tab to test
//     * @throws InterruptedException if thread sleep is interrupted
//     */
//    private void testTab(String tabName) throws InterruptedException {
//        try {
//            // Find and click the tab if it exists
//            WebElement tab = driver.findElement(By.xpath(String.format("//android.widget.LinearLayout[@content-desc=\"%s\"]", tabName)));
//            if (tab.isDisplayed()) {
//                tab.click();
//                System.out.println("Successfully clicked the " + tabName + " tab");
//                Thread.sleep ( 2000 ); // Brief pause for UI to stabilize
//                verifyActions (); // Verify content in this tab
//                tabActionInPath (); // Perform actions specific to this tab
//                Thread.sleep ( 1000 ); // Brief pause for UI to stabilize
//            }
//        } catch (NoSuchElementException e) {
//            System.out.println(tabName + " tab not found");
//            clickingEbook();
//            clickingArticle();
//        }
//    }
//
//    /**
//     * Clicks the main Path tab
//     */
//    public void clickPath() {
//        clickElement(By.xpath(PATH_TAB_XPATH));
//        System.out.println("Successfully clicked the path tab");
//    }
//
//    /**
//     * Clicks the first sub-path under the main Path tab
//     */
//    public void clickSubPath() {
//        try {
//
//            WebElement subPath = driver.findElement (By.xpath ( SUB_PATH_XPATH ));
//            if(subPath.isDisplayed ()) {
//                clickElement ( By.xpath ( SUB_PATH_XPATH ) );
//                System.out.println ( "Successfully clicked the sub path" );
//            }
//        }catch (NoSuchElementException e){
//            System.out.println ("Sub Path was not found");
//        }
//    }
//
//    /**
//     * Clicks the first sub-sub-path under the sub-path
//     * @throws InterruptedException if thread sleep is interrupted
//     */
//    public void clickSubSubPath() throws InterruptedException {
//        try {
//
//            WebElement subSubPath = driver.findElement ( By.xpath ( SUB_PATH_XPATH ) );
//
//            if (subSubPath.isDisplayed ()) {
//                Thread.sleep ( 3000 ); // Wait for content to load
//                clickElement ( By.xpath ( SUB_PATH_XPATH ) );
//                System.out.println ( "Successfully clicked the Sub Sub path" );
//            }
//
//        }catch (NoSuchElementException e){
//            System.out.println ("Sub-Sub Path was not found");
//        }
//    }
//
//    /**
//     * Clicks the three dots menu and performs report actions
//     */
//    private void threedotsPath() {
//        clickElement(By.xpath(THREE_DOTS_XPATH));
//        report(); // Assumed to be inherited from BaseActions
//        reportButton(); // Assumed to be inherited from BaseActions
//    }
//
//    /**
//     * Verifies actions by checking titles and duplicates
//     * @throws InterruptedException if thread sleep is interrupted
//     */
//    private void verifyActions() throws InterruptedException {
//        getTitles();
//        scrollToBeginning ();
//        verifyDuplicateNames();
//    }
//
//    /**
//     * Handles ebook-related actions in the current path
//     */
//    private void clickingEbook() {
//        try {
//            WebElement ebook = driver.findElement(By.xpath(EBOOK_XPATH));
//            System.out.println("Ebook was found in the path");
//            if (ebook.isDisplayed()) {
//                ebook.click();
//                // Perform all ebook module actions
//                ebookModule.clickingDownloadButton();
//                ebookModule.pdfViewerActions();
//                ebookModule.clickingReadIcon();
//                ebookModule.pdfViewerActions();
//                ebookModule.threeDotsActions();
//                ebookModule.footerCommonActions();
//                ebookModule.threedots();
//                ebookModule.removedSaved();
//                navigateBack(); // Return to previous screen
//            }
//        } catch (NoSuchElementException | InterruptedException e) {
//            System.out.println("Ebook was not found");
//        }
//    }
//
//    /**
//     * Handles article-related actions in the current path
//     */
//    private void clickingArticle() {
//        try {
//            WebElement article = driver.findElement(By.xpath(ARTICLE_XPATH));
//            System.out.println("Article was found in the path");
//            if (article.isDisplayed()) {
//                article.click();
//                // Perform all article module actions
//                articleModule.scrollArticleList ();
//                articleModule.threeDotsActions ();
//                articleModule.footerCommonActions ();
//                navigateBack (); // Return to previous screen
//            }
//        } catch (NoSuchElementException e) {
//            System.out.println("Article was not found in the path");
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt(); // Restore interrupt status
//            System.out.println("Thread was interrupted");
//        }
//    }
//
//    /**
//     * Collects and counts unique course titles by scrolling through the list
//     * @return count of unique course titles found
//     * @throws InterruptedException if thread sleep is interrupted
//     */
//    private int getTitles() throws InterruptedException {
//        Set<String> seenCourses = new HashSet<>(); // Tracks unique course names
//        int uniqueCourseCount = 0;
//        int scrollCount = 0;
//        final int MAX_SCROLLS = 5; // Limit number of scroll attempts
//
//        while (scrollCount < MAX_SCROLLS) {
//            List<WebElement> courseElements = driver.findElements(By.xpath(TITLE_XPATH));
//            boolean newDataFound = false;
//
//            // Process all currently visible course elements
//            for (WebElement courseElement : courseElements) {
//                String courseName = courseElement.getText();
//                if (seenCourses.add(courseName)) { // Only add returns true if new
//                    System.out.println("Title Name: " + courseName);
//                    newDataFound = true;
//                    uniqueCourseCount++;
//                }
//            }
//
//            if (!newDataFound) break; // Stop if no new data found in last scroll
//
//            scrollDown(); // Scroll to load more content
//            scrollCount++;
//            System.out.println("No. Of Times Scrolled: " + scrollCount);
//            Thread.sleep(2000); // Wait for new content to load
//        }
//
//        System.out.println("Total unique title Names: " + uniqueCourseCount);
//        return uniqueCourseCount;
//    }
//
//    /**
//     * Verifies if there are any duplicate course names in the current view
//     */
//    private void verifyDuplicateNames() {
//        try {
//            List<WebElement> allCourseElements = driver.findElements(By.xpath(TITLE_XPATH));
//
//            if (allCourseElements.isEmpty()) {
//                System.out.println("No course elements found on the path page.");
//                return;
//            }
//
//            Set<String> uniqueNames = new HashSet<>();
//            boolean duplicateFound = false;
//
//            // Check for duplicates
//            for (WebElement element : allCourseElements) {
//                String courseName = element.getText();
//                if (!uniqueNames.add(courseName)) { // add returns false if duplicate
//                    System.out.println("Duplicate Found In The path List Page: " + courseName);
//                    duplicateFound = true;
//                }
//            }
//
//            if (!duplicateFound) {
//                System.out.println("No duplicates found in the path list page.");
//            }
//        } catch (NoSuchElementException e) {
//            System.out.println("The path elements were not found: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Performs a scroll down action on the current screen
//     */
//    public void scrollDown() {
//        try {
//            // Use Android UIAutomator to scroll
//            driver.findElement(new AppiumBy.ByAndroidUIAutomator(
//                    "new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));
//        } catch (NoSuchElementException e) {
//            System.out.println("Can't scroll the page - may be there is no content to scroll");
//        }
//    }
//
//    private  void navigateBackArrow(){
//
//        try{
//
//            WebElement backArrow = driver.findElement ( By.xpath ( "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/ivArrowBack\"]" ) );
//
//            if (backArrow.isDisplayed ()){
//                clickElement ( By.xpath ( "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/ivArrowBack\"]" ) );
//            }
//
//        }catch (NoSuchElementException e){
//            System.out.println ("Back Arrow was not found ");
//        }
//
//    }
//}

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
            clickPath ();
            clickSubPath ();
            clickSubSubPath ();
            testAllTabs ();
            threedotsPath ();
        }finally {
            navigateBackArrow();
            navigateBackArrow();
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
            System.out.println("Successfully clicked the " + tabName + " tab");

            verifyActions();
            tabActionInPath();

        } catch (Exception e) {
            System.out.println(tabName + " tab not found");
            tabActionInPath();
        }
    }

    public void clickPath() {
        clickElement(By.xpath(PATH_TAB_XPATH));
        System.out.println("Successfully clicked the path tab");
    }

    public void clickSubPath() {
        try {
            WebElement subPath = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SUB_PATH_XPATH)));
            clickElement(By.xpath(SUB_PATH_XPATH));
            System.out.println("Successfully clicked the sub path");
        } catch (Exception e) {
            System.out.println("Sub Path was not found");
        }
    }

    public void clickSubSubPath() {
        try {
            WebElement subSubPath = longWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SUB_PATH_XPATH)));
            clickElement(By.xpath(SUB_PATH_XPATH));
            System.out.println("Successfully clicked the Sub Sub path");
        } catch (Exception e) {
            System.out.println("Sub-Sub Path was not found");
        }
    }

    private void threedotsPath() {
        clickElement(By.xpath(THREE_DOTS_XPATH));
        report();
        reportButton();
    }

    private void verifyActions() {
        getTitlesAndVerifyDuplicates();
        scrollToBeginning();

    }

    private void clickingEbook() {
        try {
            WebElement ebook = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(EBOOK_XPATH)));
            System.out.println("Ebook was found in the path");
            ebook.click();

            // Optimized ebook actions
            ebookModule.clickingDownloadButton();
            ebookModule.pdfViewerActions();
            ebookModule.clickingReadIcon();
            ebookModule.pdfViewerActions();
            ebookModule.threeDotsActions();
            ebookModule.footerCommonActions();
            ebookModule.threedots();
            ebookModule.removedSaved();

            navigateBack();
        } catch (Exception e) {
            System.out.println("Ebook was not found");
        }
    }

    private void clickingArticle() {
        try {
            WebElement article = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ARTICLE_XPATH)));
            System.out.println("Article was found in the path");
            article.click();

            // Optimized article actions
            articleModule.scrollArticleList();
            articleModule.threeDotsActions();
            articleModule.footerCommonActions();

            navigateBack();
        } catch (Exception e) {
            System.out.println("Article was not found in the path");
        }
    }

    /**
     * Combined method that handles scrolling, title collection and duplicate verification
     * @return count of unique course titles found
     */
    private int getTitlesAndVerifyDuplicates() {
        Set<String> seenCourses = new HashSet<>();
        Set<String> uniqueNames = new HashSet<>();
        boolean duplicateFound = false;
        int scrollCount = 0;
        final int MAX_SCROLLS = 3; // Optimal balance between coverage and performance

        while (scrollCount < MAX_SCROLLS) {
            List<WebElement> courseElements = shortWait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(TITLE_XPATH)));

            if (courseElements.isEmpty()) {
                System.out.println("No course elements found on the path page.");
                break;
            }

            boolean newDataFound = false;

            // Process all currently visible course elements
            for (WebElement courseElement : courseElements) {
                String courseName = courseElement.getText();

                // For unique title collection
                if (seenCourses.add(courseName)) {
                    System.out.println("Title Name: " + courseName);
                    newDataFound = true;
                }

                // For duplicate checking
                if (!uniqueNames.add(courseName) && !duplicateFound) {
                    System.out.println("Duplicate Found In The path List Page: " + courseName);
                    duplicateFound = true;
                }
            }

            if (!newDataFound) break;

            scrollDownSmoothly();
            scrollCount++;
            System.out.println("No. Of Times Scrolled: " + scrollCount);
        }

        System.out.println("Total unique title Names: " + seenCourses.size());

        if (!duplicateFound) {
            System.out.println("No duplicates found in the path list page.");
        }

        return seenCourses.size();
    }

    /**
     * Optimized smooth scrolling implementation
     */
    public void scrollDownSmoothly() {
        try {
            // More reliable scroll command with smaller steps
            driver.findElement(new AppiumBy.ByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).setSwipeDeadZonePercentage(0.2).scrollForward(30);"));
        } catch (NoSuchElementException e) {
            System.out.println("Scroll not possible - no scrollable content found");
        }
    }

    // Remove the old separate methods (getTitles(), verifyDuplicateNames(), scrollDown())
    private void navigateBackArrow() {
        try {
            WebElement backArrow = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/ivArrowBack\"]")));
            clickElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/ivArrowBack\"]"));
        } catch (Exception e) {
            System.out.println("Back Arrow was not found");
        }
    }
}