package AllCourse.SubscribedCourse.EbooksTab;

import Menu.MyEbooks.myEbooks;
import Setup.BaseActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

import static org.openqa.selenium.By.xpath;

public class Ebook extends BaseActions {

    private static final int SWIPE_DURATION_MS = 500; // Duration of each swipe in milliseconds
    private static final int SWIPE_DELAY_MS = 1000; // Delay between swipes in milliseconds
    private final Map<String, String> EbookNames = new HashMap<> ();
    private static final String EBOOK_TITLE_XPATH = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/ebooks_list\"]/android.widget.RelativeLayout/child::*[@resource-id=\"com.affairscloud:id/txt_courses_title\"]\n";
    private myEbooks EbookModule;

    public Ebook(AndroidDriver driver) {
        super(driver);
        EbookModule = new myEbooks(driver);
    }

    public void performEbookActions() throws InterruptedException {
        Thread.sleep(5000); // Wait for the page to load
        scrollHorizontalRight(1); // Scroll right once
        clickEbookTab(); // Click on the E-Books tab
        storeEbookNameBeforeClick();
        clickingEbook();
        storeEbookNameAfterClick();
        verifyArticleNames();
        ebookAction();
        Thread.sleep ( 3000 );
        getCourseNames();
        verifyDuplicateCourseNames();
        scrollToBeginning ();

    }

    public void ebookAction() throws InterruptedException {
        EbookModule.clickingDownloadButton ();
        EbookModule.pdfViewerActions ();
        EbookModule.clickingReadIcon ();
        EbookModule.pdfViewerActions ();
        threeDotsActions ();
        footerCommonActions ();
        navigateBack ();
    }

    protected void clickEbookTab() {
        clickElement(xpath("//android.widget.LinearLayout[@content-desc=\"E-Books\"]"));
    }

    protected void storeEbookNameBeforeClick() {
        String articleTitleBeforeClick = getText ( By.xpath ( "(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/ebooks_list\"]/android.widget.RelativeLayout)[1]/child::*[@resource-id=\"com.affairscloud:id/txt_courses_title\"]" ) );
        EbookNames.put ( "beforeClick" , articleTitleBeforeClick );
        System.out.println ( "Ebook name before click: " + articleTitleBeforeClick );
    }

    protected void clickingEbook(){
        clickElement ( xpath ( "(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/ebooks_list\"]/android.widget.RelativeLayout)[1]/child::*[@resource-id=\"com.affairscloud:id/txt_courses_title\"]" ) );
    }

    protected void storeEbookNameAfterClick() throws InterruptedException {
        Thread.sleep ( 5000 );
        String articleTitleAfterClick = getText ( By.id ( "com.affairscloud:id/title" ) );
        EbookNames.put ( "afterClick" , articleTitleAfterClick );
        System.out.println ( "Ebook name after click: " + articleTitleAfterClick );
    }

    protected void verifyArticleNames() {
        String beforeClick = EbookNames.get ( "beforeClick" );
        String afterClick = EbookNames.get ( "afterClick" );

        if (beforeClick != null && beforeClick.equals ( afterClick )) {
            System.out.println ( "✅ Ebook names match before and after clicking." );
        } else {
            System.out.println ( "❌ Ebook names do NOT match!" );
            System.out.println ( "🔹 Before: [" + beforeClick + "], After: [" + afterClick + "]" );
        }
    }

    // Getting all course names
    protected int getCourseNames() throws InterruptedException {
        Set<String> seenCourses = new HashSet<> (); // To track unique Article names
        int uniqueCourseCount = 0;
        int scrollCount = 0; // Counter to track the number of scrolls

        while (scrollCount < 5) { // Scroll exactly five times
            List<WebElement> courseElements = driver.findElements(By.xpath(EBOOK_TITLE_XPATH));
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
            System.out.println("No. Of Times Scrolled: " + scrollCount);

            // Add a small delay to allow new content to load
            Thread.sleep(2000); // Adjust the delay as needed
        }

        // Print the count of unique courses
        System.out.println("Total unique Ebooks Names: " + uniqueCourseCount);
        return uniqueCourseCount;
    }

    protected void verifyDuplicateCourseNames() {
        try {
            List<WebElement> allCourseElements = driver.findElements(By.xpath(EBOOK_TITLE_XPATH));

            if (allCourseElements.isEmpty()) {
                System.out.println("No course elements found on the Ebook list page.");
                return;
            }

            Set<String> uniqueNames = new HashSet<>();
            boolean duplicateFound = false;

            for (WebElement element : allCourseElements) {
                String courseName = element.getText();

                if (!uniqueNames.add(courseName)) { // If the name is already in the set, it's a duplicate
                    System.out.println("Duplicate Found In The Ebooks List Page: " + courseName);
                    duplicateFound = true;
                }
            }

            if (!duplicateFound) {
                System.out.println("No duplicates found in the Ebooks list page.");
            }

        } catch (NoSuchElementException e) {
            System.out.println("The Ebooks elements were not found: " + e.getMessage());
        }
    }

    public void scrollDown() throws InterruptedException {
        // Ensure the scrollable container is correctly identified
        driver.findElement(new AppiumBy.ByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));

        // Add a small delay to allow the scroll action to complete
        Thread.sleep(1000); // Adjust the delay as needed
    }

    public void scrollHorizontalRight(int swipeCount) {
        performHorizontalScroll(0.8, 0.2, swipeCount, "right");
    }

    private void performHorizontalScroll(double startXPercentage, double endXPercentage, int swipeCount, String direction) {
        try {
            WebElement scrollView = waitForHorizontalScrollView();
            int startX = calculateCoordinate(scrollView, startXPercentage, true);
            int endX = calculateCoordinate(scrollView, endXPercentage, true);
            int centerY = calculateCoordinate(scrollView, 0.5, false);

            for (int i = 0; i < swipeCount; i++) {
                swipe(startX, centerY, endX, centerY, SWIPE_DURATION_MS);
                System.out.println("Scroll " + direction + " " + (i + 1) + " performed on the HorizontalScrollView.");
                Thread.sleep(SWIPE_DELAY_MS); // Delay between swipes
            }
        } catch (Exception e) {
            handleError("Error performing horizontal scroll: " + e.getMessage());
        }
    }

    private void swipe(int startX, int startY, int endX, int endY, int duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(duration), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    private WebElement waitForHorizontalScrollView() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                xpath("//android.widget.HorizontalScrollView[@resource-id='com.affairscloud:id/courses_detail_tabs']")));
    }

    private int calculateCoordinate(WebElement element, double percentage, boolean isWidth) {
        int baseCoordinate = isWidth ? element.getLocation().getX() : element.getLocation().getY();
        int size = isWidth ? element.getSize().getWidth() : element.getSize().getHeight();
        return baseCoordinate + (int) (size * percentage);
    }

    private void handleError(String message) {
        System.out.println(message);
    }
}