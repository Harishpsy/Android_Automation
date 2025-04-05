package Setup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.AppiumBy.id;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

public class BaseActions extends Base {

    public WebDriverWait wait;
    protected ExtentTest test;

    public BaseActions(AndroidDriver driver) {
        Base.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    protected void clickElement(By locator) {
        try {
            WebElement clickAction = wait.until(ExpectedConditions.elementToBeClickable(locator));
            clickAction.click();
            test.log(Status.PASS, "Clicked element: " + locator.toString(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Click_" + locator.toString())).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click element: " + locator.toString(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Click_Fail_" + locator.toString())).build());
            throw e;
        }
    }

    protected void clickWithWait(String xpath, String elementName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
            test.log(Status.PASS, "Successfully Clicked The " + elementName,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked_" + elementName)).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click " + elementName + ": " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Click_Fail_" + elementName)).build());
            throw e;
        }
    }

    protected String getText(By locator) {
        try {
            WebElement gettingText = wait.until(ExpectedConditions.elementToBeClickable(locator));
            String text = gettingText.getText();
            test.log(Status.INFO, "Got text: " + text + " from element: " + locator.toString(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("GetText_" + locator.toString())).build());
            return text;
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to get text from element: " + locator.toString(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("GetText_Fail_" + locator.toString())).build());
            throw e;
        }
    }

    protected String enteringText(By locator) {
        try {
            WebElement enteringText = wait.until(ExpectedConditions.elementToBeClickable(locator));
            enteringText.sendKeys("Thanks for the valuable content");
            test.log(Status.PASS, "Entered text in element: " + locator.toString(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("EnterText_" + locator.toString())).build());
            return "";
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to enter text in element: " + locator.toString(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("EnterText_Fail_" + locator.toString())).build());
            throw e;
        }
    }

    public void scrollList() {
        try {
            scrollToEnd();
            scrollToBeginning();
            test.log(Status.PASS, "Successfully scrolled list",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll_List")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to scroll list: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll_Fail")).build());
        }
    }

    protected void scrollDown() throws InterruptedException {
        try {
            Thread.sleep(1000);
            driver.findElement(new AppiumBy.ByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));
            Thread.sleep(1000);
            test.log(Status.PASS, "Successfully scrolled down",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll_Down")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Error while scrolling down: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll_Down_Fail")).build());
        }
    }

    protected void scrollToEnd() {
        try {
            driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(30)"));
            test.log(Status.PASS, "Successfully scrolled to the bottom",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll_End")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Error while scrolling to bottom: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll_End_Fail")).build());
        }
    }

    protected void scrollToBeginning() {
        try {
            driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(30)"));
            test.log(Status.PASS, "Successfully scrolled to the top",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll_Top")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Error while scrolling to top: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scroll_Top_Fail")).build());
        }
    }

    public void threeDotsActions() throws InterruptedException {
        try {
            test.log(Status.INFO, "Starting three dots actions");

            threedots();
            test.log(Status.PASS, "Clicked three dots",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ThreeDots_Click")).build());

            share();
            test.log(Status.PASS, "Shared content",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Share_Action")).build());

            threedots();
            report();
            test.log(Status.PASS, "Reported content",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Report_Action")).build());

            cancel();
            test.log(Status.PASS, "Cancelled action",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Cancel_Action")).build());

            threedots();
            report();
            reportButton();
            test.log(Status.PASS, "Completed report action",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Report_Complete")).build());

            threedots();
            removedSaved();
            test.log(Status.PASS, "Removed saved item",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Remove_Saved")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Error in three dots actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ThreeDots_Error")).build());
            throw e;
        }
    }

    protected void threedotsTabAction() {
        try {
            clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.affairscloud:id/ivMore\"])[1]"));
            test.log(Status.PASS, "Clicked three dots tab",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ThreeDots_Tab")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click three dots tab",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ThreeDots_Tab_Fail")).build());
            throw e;
        }
    }

    protected void threedotsAction() {
        try {
            threedotsTabAction();
            removedSaved();
            threedotsTabAction();
            share();
            threedotsTabAction();
            report();
            reportButton();
            test.log(Status.PASS, "Completed three dots actions",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ThreeDots_Actions_Complete")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Error in three dots actions",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ThreeDots_Actions_Fail")).build());
            throw e;
        }
    }

    @Test(enabled = true)
    public void threedots() throws InterruptedException {
        try {
            /*First*/
            try {
                Thread.sleep(5000);
                WebElement threeDots1 = driver.findElement(By.id("com.affairscloud:id/iv_more"));
                if (threeDots1.isDisplayed()) {
                    threeDots1.click();
                    test.log(Status.PASS, "Clicked the first three dots button",
                            MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ThreeDots1_Click")).build());
                    return;
                }
            } catch (NoSuchElementException e) {
                test.log(Status.INFO, "First three dots button not found, trying second");
            }

            /*Second*/
            try {
                Thread.sleep(5000);
                WebElement threeDots2 = driver.findElement(By.id("com.affairscloud:id/more_btn"));
                if (threeDots2.isDisplayed()) {
                    threeDots2.click();
                    test.log(Status.PASS, "Clicked the second three dots button",
                            MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ThreeDots2_Click")).build());
                }
            } catch (NoSuchElementException e) {
                test.log(Status.FAIL, "Neither three dots button could be clicked",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ThreeDots_Fail")).build());
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Error in threedots method: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ThreeDots_Error")).build());
            throw e;
        }
    }

    public void removedSaved() {
        try {
            clickElement(xpath("(//*[@resource-id=\"com.affairscloud:id/content\"])[1]"));
            test.log(Status.PASS, "Successfully clicked save/remove button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Remove_Saved")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click save/remove button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Remove_Saved_Fail")).build());
            throw e;
        }
    }

    public void share() {
        try {
            clickElement(xpath("//*[@text=\"Share\"]"));
            test.log(Status.PASS, "Successfully clicked share button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Share_Click")).build());

            commonCancel();
            test.log(Status.PASS, "Cancelled share action",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Share_Cancel")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Error in share action",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Share_Error")).build());
            throw e;
        }
    }

    public void report() {
        try {
            clickElement(xpath("//*[@text=\"Report\"]"));
            clickElement(xpath("(//*[@resource-id=\"com.affairscloud:id/cbReport\"])[2]"));
            enteringText(By.className("android.widget.EditText"));
            test.log(Status.PASS, "Successfully completed report action",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Report_Action")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Error in report action",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Report_Error")).build());
            throw e;
        }
    }

    public void commonCancel() {
        try {
            clickElement(xpath("//*[@text=\"Cancel\"]"));
            test.log(Status.PASS, "Successfully clicked cancel button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Cancel_Click")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click cancel button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Cancel_Fail")).build());
            throw e;
        }
    }

    public void cancel() {
        try {
            clickElement(id("com.affairscloud:id/tv_cancel"));
            test.log(Status.PASS, "Successfully clicked cancel button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Cancel_Click")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click cancel button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Cancel_Fail")).build());
            throw e;
        }
    }

    public void reportButton() {
        try {
            clickElement(id("com.affairscloud:id/tv_report"));
            test.log(Status.PASS, "Successfully clicked report button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Report_Click")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click report button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Report_Fail")).build());
            throw e;
        }
    }

    public void reportAction() {
        try {
            report();
            reportButton();
            test.log(Status.PASS, "Completed report action",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Report_Complete")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Error in report action",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Report_Error")).build());
            throw e;
        }
    }

    public void footerCommonActions() {
        // Implement if needed with logging
    }

    private void performLikeAction() {
        try {
            clickElement(id("com.affairscloud:id/like_layout"));
            test.log(Status.PASS, "Successfully performed like action",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Like_Action")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform like action",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Like_Fail")).build());
            throw e;
        }
    }

    protected void performCommentAction() {
        try {
            clickElement(id("com.affairscloud:id/comment_layout"));
            enteringText(className("android.widget.EditText"));
            clickElement(id("com.affairscloud:id/iv_send"));
            test.log(Status.PASS, "Successfully performed comment action",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Comment_Action")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform comment action",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Comment_Fail")).build());
            throw e;
        }
    }

    protected void performReplyActionIfPresent() {
        try {
            WebElement clickingReply = driver.findElement(xpath("(//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_replay\"])[1]"));
            if (clickingReply.isDisplayed()) {
                Thread.sleep(5000);
                clickElement(xpath("(//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_replay\"])[1]"));
                Thread.sleep(5000);
                enteringText(className("android.widget.EditText"));
                Thread.sleep(3000);
                clickElement(id("com.affairscloud:id/iv_send"));
                Thread.sleep(2000);
                navigateBack();
                test.log(Status.PASS, "Successfully performed reply action",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Reply_Action")).build());
            } else {
                test.log(Status.INFO, "No reply button present",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No_Reply")).build());
            }
        } catch (Exception e) {
            test.log(Status.INFO, "No reply button present",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No_Reply")).build());
        }
    }

    protected void navigateBack() {
        try {
            // Attempt to click the first back button
            WebElement backButton1 = driver.findElement(By.id("com.affairscloud:id/btn_back"));
            if (backButton1.isDisplayed()) {
                backButton1.click();
                test.log(Status.PASS, "Clicked the first back button",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Back1_Click")).build());
                return;
            }
        } catch (NoSuchElementException e) {
            test.log(Status.INFO, "First back button not found, trying second");
        }

        try {
            // Attempt to click the second back button
            WebElement backButton2 = driver.findElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/img_back_press\"]"));
            if (backButton2.isDisplayed()) {
                backButton2.click();
                test.log(Status.PASS, "Clicked the second back button",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Back2_Click")).build());
                return;
            }
        } catch (NoSuchElementException e) {
            test.log(Status.FAIL, "Neither back button could be clicked",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Back_Fail")).build());
        }
    }

    public void clickMenu() throws InterruptedException {
        try {
            Thread.sleep(5000);
            WebElement menuButton = driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.affairscloud:id/menu_btn']"));
            if (menuButton.isDisplayed()) {
                menuButton.click();
                test.log(Status.PASS, "Successfully clicked menu button",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Menu_Click")).build());
            } else {
                test.log(Status.FAIL, "Menu button not displayed",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Menu_Not_Displayed")).build());
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Error clicking menu button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Menu_Error")).build());
            throw e;
        }
    }
}