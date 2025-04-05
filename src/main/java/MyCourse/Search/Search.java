package MyCourse.Search;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class Search extends BaseActions {

    public Search(AndroidDriver driver) {
        super(driver);
    }

    public void performSearchActions() throws InterruptedException {
        try {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            clickSearch();
            clickBackButton();
            clickSearch();
            enteringTextInSearchField();
            Thread.sleep(3000);

            clickingSearchInTheKeyBoard();
            Thread.sleep(3000);
            driver.hideKeyboard();
            clearSearch();
            clickBackButton();

            test.log(Status.PASS, "All search actions completed successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("SearchCompleted")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform search actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("SearchFailed")).build());
            throw e;
        }
    }

    private void clickSearch() {
        try {
            clickElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_search\"]"));
            test.log(Status.PASS, "Search button clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("SearchButtonClicked")).build());
            System.out.println("Successfully Clicked Search Button");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click search button: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("SearchButtonFailed")).build());
            throw e;
        }
    }

    private void enteringTextInSearchField() {
        try {
            clickElement(By.xpath("//android.widget.EditText[@resource-id=\"com.affairscloud:id/title_search\"]"));

            WebElement enterText = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.affairscloud:id/title_search\"]"));
            enterText.sendKeys("Current Affairs");

            test.log(Status.PASS, "Text entered in search field successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("TextEntered")).build());
            System.out.println("Successfully Entered the text");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to enter text in search field: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("TextEntryFailed")).build());
            throw e;
        }
    }

    private void clearSearch() {
        try {
            clickElement(By.id("com.affairscloud:id/clear_search"));
            test.log(Status.PASS, "Search cleared successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("SearchCleared")).build());
            System.out.println("Successfully Clicked The Clear Button");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to clear search: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ClearFailed")).build());
            throw e;
        }
    }

    private void clickBackButton() {
        try {
            clickElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/back_press\"]"));
            test.log(Status.PASS, "Back button clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("BackButtonClicked")).build());
            System.out.println("Successfully Clicked The Back Button");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click back button: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("BackButtonFailed")).build());
            throw e;
        }
    }

    private void executeAdbSearchKey() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("adb", "shell", "input", "keyevent", "84");
            processBuilder.start();
            test.log(Status.INFO, "ADB Search Key Sent");
            System.out.println("ADB Search Key Sent");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to execute ADB search key: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clickingSearchInTheKeyBoard() throws InterruptedException {
        try {
            driver.isKeyboardShown();
            Thread.sleep(2000);
            driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));

            test.log(Status.PASS, "Keyboard search button clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("KeyboardSearchClicked")).build());
            System.out.println("Successfully Clicked The Search Button In The Keyboard");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click keyboard search button: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("KeyboardSearchFailed")).build());
            throw e;
        }
    }
}