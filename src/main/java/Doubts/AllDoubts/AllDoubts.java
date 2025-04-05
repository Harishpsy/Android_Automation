package Doubts.AllDoubts;

import Doubts.CreatingDoubt.CreatingDoubt;
import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class AllDoubts extends BaseActions {

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(30);
    private final CreatingDoubt creatingDoubtModule;

    public AllDoubts(AndroidDriver driver) {
        super(driver);
        creatingDoubtModule = new CreatingDoubt(driver);
    }

    public void performAllDoubtsActions() throws InterruptedException {
        try {
            doubtfooterActions();
            clickingAnswer();
            String enteredText = enteringText();
            cameraActions();
            clickingSendButton();
            verifyAnswerText(enteredText);
            clickLike();
            clickReplyButton();
            handleReplyActions();
            Thread.sleep(2000);
            navigateBack();
            scrollList();

            test.log(Status.PASS, "All doubts actions completed successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("AllDoubtsFlowCompleted")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Doubts flow failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("AllDoubtsFlowFailed")).build());
            throw e;
        }
    }

    public void clickingAnswer() {
        waitAndClick(By.xpath("(//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_answer\"])[1]"));
        test.log(Status.PASS, "Answer field clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("AnswerFieldClicked")).build());
    }

    private String enteringText() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        By textFieldLocator = By.xpath("//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_answer\"]");
        WebElement textField = waitForElement(textFieldLocator);

        textField.sendKeys("Thank you for the valuable content");
        driver.hideKeyboard();

        String enteredText = textField.getText();
        test.log(Status.INFO, "Entered Text: " + enteredText,
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("TextEntered")).build());
        System.out.println("Entered Text: " + enteredText);
        return enteredText;
    }

    private void enteringTextInReply() throws InterruptedException {
        By replyFieldLocator = By.xpath("//android.widget.EditText[@resource-id=\"com.affairscloud:id/tv_write_comment\"]");
        WebElement textField = waitForElement(replyFieldLocator);

        Thread.sleep(3000);
        textField.sendKeys("Thank you for the content");
        driver.hideKeyboard();

        test.log(Status.INFO, "Entered Reply Text: " + textField.getText(),
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ReplyTextEntered")).build());
        System.out.println("Entered Reply Text: " + textField.getText());
    }

    private void cameraActions() {
        try {
            test.log(Status.INFO, "Starting camera actions");
            performCameraOperations();
            test.log(Status.PASS, "Camera actions completed successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("CameraActionsCompleted")).build());
        } catch (NoSuchElementException e) {
            test.log(Status.FAIL, "Camera Action Error: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("CameraActionsFailed")).build());
            throw e;
        }
    }

    private void performCameraOperations() {
        clickingCameraButton();
        creatingDoubtModule.selectFromGallery();
        creatingDoubtModule.clickImage();
        creatingDoubtModule.clickCrop();
        creatingDoubtModule.clickDeleteImage();
        creatingDoubtModule.clickCancel();
        creatingDoubtModule.clickDeleteImage();
        creatingDoubtModule.clickConfirm();
        clickingCameraButton();
        creatingDoubtModule.selectFromGallery();
        creatingDoubtModule.clickImage();
        creatingDoubtModule.clickCrop();
    }

    private void clickingCameraButton() {
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT);
        waitAndClick(By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_camera\"]"));
        test.log(Status.INFO, "Camera button clicked");
    }

    private void clickingSendButton() throws InterruptedException {
        Thread.sleep(3000);
        waitAndClick(By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/img_send\"]"));
        test.log(Status.PASS, "Send button clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("SendButtonClicked")).build());
        System.out.println("Send button clicked successfully");
    }

    private void clickingSendButtonInReply() {
        waitAndClick(By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_send\"]"));
        test.log(Status.PASS, "Reply send button clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ReplySent")).build());
        System.out.println("Reply send button clicked successfully");
    }

    private void verifyAnswerText(String expectedText) throws InterruptedException {
        Thread.sleep(3000);
        By answerTextLocator = By.xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/txt_post_description\"]");
        List<WebElement> answerElements = driver.findElements(answerTextLocator);

        boolean found = false;
        for (WebElement element : answerElements) {
            String actualText = element.getText();
            test.log(Status.INFO, "Found answer text: " + actualText);

            if (expectedText.equals(actualText)) {
                found = true;
                test.log(Status.PASS, "Text verification successful: " + expectedText,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("TextVerified")).build());
                System.out.println("Text verification successful");
                break;
            }
        }

        if (!found) {
            test.log(Status.FAIL, "Expected text not found in any answer: " + expectedText,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("TextVerificationFailed")).build());
            throw new AssertionError("Expected text not found in any answer: " + expectedText);
        }
    }

    private void clickLike() {
        scrollAndClick("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                + ".scrollIntoView(new UiSelector().resourceId(\"com.affairscloud:id/iv_likes\").instance(0))");
        test.log(Status.PASS, "Like button clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("LikeClicked")).build());
    }

    private void clickReplyButton() {
        scrollAndClick("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                + ".scrollIntoView(new UiSelector().resourceId(\"com.affairscloud:id/txt_reply\").instance(0))");
        test.log(Status.PASS, "Reply button clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ReplyClicked")).build());
    }

    private void clickingReplyAnswerButton() {
        waitAndClick(By.xpath("//android.widget.EditText[@resource-id=\"com.affairscloud:id/tv_write_comment\"]"));
        test.log(Status.INFO, "Reply answer field clicked");
    }

    private void handleReplyActions() throws InterruptedException {
        Thread.sleep(2000);
        clickingReplyAnswerButton();
        enteringTextInReply();
        Thread.sleep(2000);
        clickingSendButtonInReply();
        Thread.sleep(2000);
        navigateBack();
    }

    // Helper methods (unchanged)
    private WebElement waitForElement(By locator) {
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT);
        return driver.findElement(locator);
    }

    private void waitAndClick(By locator) {
        waitForElement(locator).click();
    }

    private void scrollAndClick(String uiAutomatorText) {
        WebElement element = driver.findElement(AppiumBy.androidUIAutomator(uiAutomatorText));
        element.click();
    }

    public void doubtfooterActions() {
        clickFollow();
        clickShare();
        threeDotActions();
    }

    private void clickShare() {
        clickElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/img_share\"]"));
        test.log(Status.PASS, "Share button clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ShareClicked")).build());
        commonCancel();
    }

    private void clickFollow() {
        clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_follow\"])[1]"));
        test.log(Status.PASS, "Follow button clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("FollowClicked")).build());
    }

    private void threeDotActions() {
        doubtThreeDots();
        report();
        reportButton();
        doubtThreeDots();
        share();
    }

    private void doubtThreeDots() {
        clickElement(By.xpath("(//android.widget.ImageButton[@resource-id=\"com.affairscloud:id/more_btn\"])[1]"));
        test.log(Status.INFO, "Three dots menu clicked");
    }
}