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
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.time.Duration;
import java.util.*;

import static org.openqa.selenium.By.xpath;

public class Ebook extends BaseActions {

    private static final int SWIPE_DURATION_MS = 500; // Duration of each swipe in milliseconds
    private static final int SWIPE_DELAY_MS = 1000; // Delay between swipes in milliseconds
    private final Map<String, String> EbookNames = new HashMap<>();
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
        Thread.sleep(3000);
        getCourseNames();
        verifyDuplicateCourseNames();
        scrollToBeginning();
    }

    public void ebookAction() throws InterruptedException {
        EbookModule.clickingDownloadButton();
        test.log(Status.PASS, "Clicked Download Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Download Clicked")).build());

        EbookModule.pdfViewerActions();
        test.log(Status.PASS, "Performed PDF Viewer Actions",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PDF Actions")).build());

        EbookModule.clickingReadIcon();
        test.log(Status.PASS, "Clicked Read Icon",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Read Icon Clicked")).build());

        EbookModule.pdfViewerActions();
        threeDotsActions();
        footerCommonActions();
        navigateBack();
    }

    protected void clickEbookTab() {
        clickElement(xpath("//android.widget.LinearLayout[@content-desc=\"E-Books\"]"));
        test.log(Status.PASS, "Successfully Clicked E-Books Tab",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("E-Books Tab Clicked")).build());
    }

    protected void storeEbookNameBeforeClick() {
        String articleTitleBeforeClick = getText(By.xpath("(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/ebooks_list\"]/android.widget.RelativeLayout)[1]/child::*[@resource-id=\"com.affairscloud:id/txt_courses_title\"]"));
        EbookNames.put("beforeClick", articleTitleBeforeClick);
        test.log(Status.INFO, "Ebook name before click: " + articleTitleBeforeClick,
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Before Click")).build());
        System.out.println("Ebook name before click: " + articleTitleBeforeClick);
    }

    protected void clickingEbook() {
        clickElement(xpath("(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/ebooks_list\"]/android.widget.RelativeLayout)[1]/child::*[@resource-id=\"com.affairscloud:id/txt_courses_title\"]"));
        test.log(Status.PASS, "Successfully Clicked Ebook",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Ebook Clicked")).build());
    }

    protected void storeEbookNameAfterClick() throws InterruptedException {
        Thread.sleep(5000);
        String articleTitleAfterClick = getText(By.id("com.affairscloud:id/title"));
        EbookNames.put("afterClick", articleTitleAfterClick);
        test.log(Status.INFO, "Ebook name after click: " + articleTitleAfterClick,
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("After Click")).build());
        System.out.println("Ebook name after click: " + articleTitleAfterClick);
    }

    protected void verifyArticleNames() {
        String beforeClick = EbookNames.get("beforeClick");
        String afterClick = EbookNames.get("afterClick");

        if (beforeClick != null && beforeClick.equals(afterClick)) {
            test.log(Status.PASS, "Ebook names match before and after clicking",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Names Match")).build());
            System.out.println("✅ Ebook names match before and after clicking.");
        } else {
            test.log(Status.FAIL, "Ebook names do NOT match! Before: [" + beforeClick + "], After: [" + afterClick + "]",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Names Mismatch")).build());
            System.out.println("❌ Ebook names do NOT match!");
            System.out.println("🔹 Before: [" + beforeClick + "], After: [" + afterClick + "]");
        }
    }

    protected int getCourseNames() throws InterruptedException {
        Set<String> seenCourses = new HashSet<>(); // To track unique Article names
        int uniqueCourseCount = 0;
        int scrollCount = 0; // Counter to track the number of scrolls

        while (scrollCount < 5) { // Scroll exactly five times
            List<WebElement> courseElements = driver.findElements(By.xpath(EBOOK_TITLE_XPATH));
            boolean newDataFound = false;

            for (WebElement courseElement : courseElements) {
                String courseName = courseElement.getText();

                if (seenCourses.add(courseName)) { // Only adds if it's not already present
                    test.log(Status.INFO, "Ebook Name: " + courseName);
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
            test.log(Status.INFO, "Scrolled Ebook List - Count: " + scrollCount,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll " + scrollCount)).build());
            System.out.println("No. Of Times Scrolled: " + scrollCount);

            Thread.sleep(2000); // Adjust the delay as needed
        }

        test.log(Status.INFO, "Total unique Ebooks Names: " + uniqueCourseCount,
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Unique Ebooks Count")).build());
        System.out.println("Total unique Ebooks Names: " + uniqueCourseCount);
        return uniqueCourseCount;
    }

    protected void verifyDuplicateCourseNames() {
        try {
            List<WebElement> allCourseElements = driver.findElements(By.xpath(EBOOK_TITLE_XPATH));

            if (allCourseElements.isEmpty()) {
                test.log(Status.WARNING, "No course elements found on the Ebook list page",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Ebooks Found")).build());
                System.out.println("No course elements found on the Ebook list page.");
                return;
            }

            Set<String> uniqueNames = new HashSet<>();
            boolean duplicateFound = false;

            for (WebElement element : allCourseElements) {
                String courseName = element.getText();

                if (!uniqueNames.add(courseName)) {
                    test.log(Status.WARNING, "Duplicate Found In The Ebooks List Page: " + courseName,
                            MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Duplicate Ebook")).build());
                    System.out.println("Duplicate Found In The Ebooks List Page: " + courseName);
                    duplicateFound = true;
                }
            }

            if (!duplicateFound) {
                test.log(Status.PASS, "No duplicates found in the Ebooks list page",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Duplicates")).build());
                System.out.println("No duplicates found in the Ebooks list page.");
            }

        } catch (NoSuchElementException e) {
            test.log(Status.FAIL, "The Ebooks elements were not found: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Ebooks Not Found")).build());
            System.out.println("The Ebooks elements were not found: " + e.getMessage());
        }
    }

    public void scrollDown() throws InterruptedException {
        driver.findElement(new AppiumBy.ByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));
        Thread.sleep(1000);
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
                test.log(Status.INFO, "Scroll " + direction + " " + (i + 1) + " performed on the HorizontalScrollView",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll " + direction)).build());
                System.out.println("Scroll " + direction + " " + (i + 1) + " performed on the HorizontalScrollView.");
                Thread.sleep(SWIPE_DELAY_MS);
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Error performing horizontal scroll: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll Error")).build());
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