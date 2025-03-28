package Doubts.AllDoubts;

import Doubts.CreatingDoubt.CreatingDoubt;
import Setup.BaseActions;
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

        doubtfooterActions();
        clickingAnswer();
        String enteredText = enteringText();
        cameraActions();
        clickingSendButton();
        verifyAnswerText(enteredText);
        clickLike();
        clickReplyButton();
        handleReplyActions();
        driver.manage ().timeouts ().implicitlyWait ( DEFAULT_WAIT );
        navigateBack();
        scrollList ();
    }

    public void clickingAnswer() {
        waitAndClick(By.xpath("(//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_answer\"])[1]"));
    }

    private String enteringText() {
        driver.manage ().timeouts ().implicitlyWait (Duration.ofSeconds ( 30 ));
        By textFieldLocator = By.xpath("//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_answer\"]");
        WebElement textField = waitForElement(textFieldLocator);

        textField.sendKeys("Thank you for the valuable content");
        driver.hideKeyboard();

        String enteredText = textField.getText();
        System.out.println("Entered Text: " + enteredText);
        return enteredText;
    }

    private void enteringTextInReply() throws InterruptedException {
        By replyFieldLocator = By.xpath("//android.widget.EditText[@resource-id=\"com.affairscloud:id/tv_write_comment\"]");
        WebElement textField = waitForElement(replyFieldLocator);

        Thread.sleep(3000);
        textField.sendKeys("Thank you for the content");
        driver.hideKeyboard();

        System.out.println("Entered Reply Text: " + textField.getText());
    }

    private void cameraActions() {
        try {
            performCameraOperations();
        } catch (NoSuchElementException e) {
            System.err.println("Camera Action Error: " + e.getMessage());
            throw e; // Re-throw to fail the test
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
        driver.manage ().timeouts ().implicitlyWait ( DEFAULT_WAIT );
        waitAndClick(By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_camera\"]"));
    }

    private void clickingSendButton() throws InterruptedException {
        Thread.sleep(3000);
        waitAndClick(By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/img_send\"]"));
        System.out.println("Send button clicked successfully");
    }

    private void clickingSendButtonInReply() {
        waitAndClick(By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_send\"]"));
        System.out.println("Reply send button clicked successfully");
    }

    private void verifyAnswerText(String expectedText) throws InterruptedException {
        Thread.sleep(3000);
        By answerTextLocator = By.xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/txt_post_description\"]");
        List<WebElement> answerElements = driver.findElements(answerTextLocator);

        boolean found = false;
        for (WebElement element : answerElements) {
            String actualText = element.getText();
            System.out.println("Found answer text: " + actualText);

            if (expectedText.equals(actualText)) {
                found = true;
                System.out.println("Text verification successful");
                break;
            }
        }

        if (!found) {
            throw new AssertionError("Expected text not found in any answer: " + expectedText);
        }
    }

    private void clickLike() {
        scrollAndClick("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                + ".scrollIntoView(new UiSelector().resourceId(\"com.affairscloud:id/iv_likes\").instance(0))");
    }

    private void clickReplyButton() {
        scrollAndClick("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                + ".scrollIntoView(new UiSelector().resourceId(\"com.affairscloud:id/txt_reply\").instance(0))");
    }

    private void clickingReplyAnswerButton() {
        waitAndClick(By.xpath("//android.widget.EditText[@resource-id=\"com.affairscloud:id/tv_write_comment\"]"));
    }

    private void handleReplyActions() throws InterruptedException {
        Thread.sleep ( 2000 );
        clickingReplyAnswerButton();
        enteringTextInReply();
        Thread.sleep ( 2000 );
        clickingSendButtonInReply();
        Thread.sleep ( 2000 );
        navigateBack();
    }

    // Helper methods
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
        clickElement ( By.xpath ( "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/img_share\"]" ) );
        commonCancel ();
    }

    private void clickFollow(){
        clickElement ( By.xpath ( "(//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_follow\"])[1]" ) );
    }

    private void threeDotActions() {
        doubtThreeDots();
        report ();
        reportButton ();
        doubtThreeDots();
        share ();
    }

    private void doubtThreeDots() {
        clickElement ( By.xpath ( "(//android.widget.ImageButton[@resource-id=\"com.affairscloud:id/more_btn\"])[1]" ) );
    }

}