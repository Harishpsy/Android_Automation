package Homepage.PreferenceBanner;

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

public class PreferenceBanner extends BaseActions {

    private static final String BANNER_XPATH = "//android.widget.ImageView[@resource-id='com.affairscloud:id/image_cover']";
    private static final int SWIPE_DURATION_MS = 500; // Duration of each swipe in milliseconds
    private static final int SWIPE_DELAY_MS = 1000; // Delay between swipes in milliseconds

    public PreferenceBanner(AndroidDriver driver) {
        super(driver);
    }

    /**
     * Performs the main actions related to the preference banner.
     */
    public void performPreferenceBannerAction() {
        try {
            swipeBannerLeft(5); // Swipe left 5 times
            swipeBannerRight(5); // Swipe right 5 times
            handlePreferenceBanner(); // Handle the preference banner interaction
        } catch (Exception e) {
            handleError("Error during preference banner actions: " + e.getMessage());
        }
    }

    /**
     * Handles the preference banner interaction (click and navigate back).
     */
    public void handlePreferenceBanner() {
        try {
            WebElement preferenceBanner = waitForBannerVisibility();
            if (preferenceBanner.isDisplayed()) {
                clickElement(By.xpath(BANNER_XPATH));
                Thread.sleep(3000); // Wait for navigation
                navigateBack();
            } else {
                handleError("The preference banner is not displayed.");
            }
        } catch (NoSuchElementException | InterruptedException e) {
            handleError("Error handling preference banner: " + e.getMessage());
        }
    }

    /**
     * Swipes the banner to the left a specified number of times.
     *
     * @param swipeCount The number of times to swipe left.
     */
    private void swipeBannerLeft(int swipeCount) {
        performSwipeOnBanner(0.9, 0.1, swipeCount, "left");
    }

    /**
     * Swipes the banner to the right a specified number of times.
     *
     * @param swipeCount The number of times to swipe right.
     */
    private void swipeBannerRight(int swipeCount) {
        performSwipeOnBanner(0.1, 0.9, swipeCount, "right");
    }

    /**
     * Performs a swipe gesture on the banner.
     *
     * @param startXPercentage The starting X percentage of the element's width (0.0 to 1.0).
     * @param endXPercentage   The ending X percentage of the element's width (0.0 to 1.0).
     * @param swipeCount       The number of times to swipe.
     * @param direction        The direction of the swipe ("left" or "right").
     */
    private void performSwipeOnBanner(double startXPercentage, double endXPercentage, int swipeCount, String direction) {
        try {
            WebElement preferenceBanner = waitForBannerVisibility();
            int startX = calculateCoordinate(preferenceBanner, startXPercentage, true);
            int endX = calculateCoordinate(preferenceBanner, endXPercentage, true);
            int centerY = calculateCoordinate(preferenceBanner, 0.5, false);

            for (int i = 0; i < swipeCount; i++) {
                swipe(startX, centerY, endX, centerY, SWIPE_DURATION_MS);
                System.out.println("Swipe " + direction + " " + (i + 1) + " performed on the preference banner.");
                Thread.sleep(SWIPE_DELAY_MS); // Delay between swipes
            }
        } catch (Exception e) {
            handleError("Error performing swipe on banner: " + e.getMessage());
        }
    }

    /**
     * Performs a swipe gesture using W3C Actions API.
     *
     * @param startX   The starting X coordinate.
     * @param startY   The starting Y coordinate.
     * @param endX     The ending X coordinate.
     * @param endY     The ending Y coordinate.
     * @param duration The duration of the swipe in milliseconds.
     */
    private void swipe(int startX, int startY, int endX, int endY, int duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(duration), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    /**
     * Waits for the preference banner to be visible.
     *
     * @return The visible WebElement of the preference banner.
     */
    private WebElement waitForBannerVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BANNER_XPATH)));
    }

    /**
     * Calculates the X or Y coordinate based on the element's location and size.
     *
     * @param element    The WebElement to calculate coordinates for.
     * @param percentage The percentage of the element's width or height (0.0 to 1.0).
     * @param isXAxis    Whether to calculate the X coordinate (true) or Y coordinate (false).
     * @return The calculated coordinate.
     */
    private int calculateCoordinate(WebElement element, double percentage, boolean isXAxis) {
        if (isXAxis) {
            return element.getLocation().getX() + (int) (element.getSize().getWidth() * percentage);
        } else {
            return element.getLocation().getY() + (int) (element.getSize().getHeight() * percentage);
        }
    }

    /**
     * Handles errors by logging them to the console.
     *
     * @param message The error message to log.
     */
    private void handleError(String message) {
        System.out.println(message);
    }
}