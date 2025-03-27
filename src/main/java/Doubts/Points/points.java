package Doubts.Points;

import AllCourse.SubscribedCourse.EbooksTab.Ebook;
import Menu.MyPoints.myPoints;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

import static org.openqa.selenium.By.xpath;

public class points extends BaseActions {

    private Ebook ebookModule;
    private myPoints MyPointsModule;
    private static final int SWIPE_DURATION_MS = 500; // Duration of each swipe in milliseconds
    private static final int SWIPE_DELAY_MS = 1000; // Delay between swipes in milliseconds

    public points(AndroidDriver driver) {
        super(driver);
        ebookModule = new Ebook ( driver );
        MyPointsModule = new myPoints ( driver );
    }

    public void performPointsActions() throws InterruptedException {
        scrollRight();
        clickingPoints();
        MyPointsModule.clickViewEarnedPoints();
        scrollList ();
        navigateBackInPoints();

    }

    private void clickingPoints(){
        clickElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Points\"]" ) );
    }

    protected void scrollRight() {

        try {
            WebElement clickpoints = driver.findElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Points\"]" ) );
            if (clickpoints.isDisplayed ()) {
                clickpoints.click ();
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            scrollHorizontalRight ( 1 ); // Scroll right once
        }
    }

    private void navigateBackInPoints(){
        clickElement ( xpath ( "//android.widget.ImageButton[@resource-id=\"com.affairscloud:id/img_back_press\"]" ) );

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
                .addAction(finger.createPointerMove( Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(duration), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform( Collections.singletonList(swipe));
    }

    private WebElement waitForHorizontalScrollView() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until( ExpectedConditions.visibilityOfElementLocated(
                xpath("//android.widget.HorizontalScrollView[@resource-id=\"com.affairscloud:id/post_tabs\"]/android.widget.LinearLayout")));
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
