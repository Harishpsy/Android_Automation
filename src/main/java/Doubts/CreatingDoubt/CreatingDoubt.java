package Doubts.CreatingDoubt;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class CreatingDoubt extends BaseActions {

    // Locators as constants for better maintainability
    private static final By DOUBT_INPUT_FIELD = By.xpath("//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_post\"]");
    private static final By CLOSE_ICON = By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/image_alert\"]");
    private static final By SUBJECT_BUTTON = By.id("com.affairscloud:id/btnSubjects");
    private static final By EXAM_BUTTON = By.id("com.affairscloud:id/btnExams");
    private static final By COURSE_SEARCH_FIELD = By.id("com.affairscloud:id/et_course");
    private static final By COURSE_TITLE = By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/root_view\"]/child::*[@resource-id=\"com.affairscloud:id/courses_title\"]");
    private static final By CANCEL_BUTTON = By.xpath("//android.widget.Button[@resource-id=\"android:id/button2\"]");
    private static final By CONFIRM_BUTTON = By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]");
    private static final By QUESTION_FIELD = By.xpath("//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_question\"]");
    private static final By ADD_IMAGE_BUTTON = By.id("com.affairscloud:id/tvAddImage");
    private static final By GALLERY_OPTION = By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Gallery\"]");
    private static final By IMAGE_THUMBNAIL = By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[5]/android.view.View[2]/android.view.View[2]/android.view.View");
    private static final By CROP_BUTTON = By.xpath("//android.widget.Button[@resource-id=\"com.affairscloud:id/crop_image_menu_crop\"]");
    private static final By DELETE_IMAGE_BUTTON = By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/img_delete\"]");
    private static final By NEXT_BUTTON = By.id("com.affairscloud:id/nextButton");
    private static final By RULES_LINK = By.id("com.affairscloud:id/tv_rules");

    public CreatingDoubt(AndroidDriver driver) {
        super(driver);
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
    }

    public void performCreatingDoubtActions() throws InterruptedException {
        createDoubt();
        clickCloseIcon();
        createDoubt();
        clickSubject();
        navigateBack();
        createDoubt();
        clickExam();
        searchCourse();
        selectCourse();
        clickCancel();
        selectCourse();
        clickConfirm();
        enterQuestionText();
        handleImageUpload();
        clickNextButton();
        Thread.sleep ( 2000 );
        navigateBack();
        Thread.sleep ( 2000 );
        navigateBack();
        clickRules();
        navigateBack();
    }

    private void createDoubt() {
        clickElement(DOUBT_INPUT_FIELD);
        test.log( Status.PASS, "",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("")).build());
    }

    private void clickCloseIcon() {
        clickElement(CLOSE_ICON);
        test.log( Status.PASS, "",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("")).build());
    }

    private void clickSubject() {
        clickElement(SUBJECT_BUTTON);
        test.log( Status.PASS, "",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("")).build());
    }

    private void clickExam() {
        clickElement(EXAM_BUTTON);
        test.log( Status.PASS, "",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("")).build());
    }

    private void searchCourse() {
        clickElement(COURSE_SEARCH_FIELD);
        WebElement searchField = driver.findElement(COURSE_SEARCH_FIELD);
        searchField.sendKeys("Mock");
        test.log( Status.PASS, "",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("")).build());
        driver.hideKeyboard();
    }

    private void selectCourse() {
        clickElement(COURSE_TITLE);
        test.log( Status.PASS, "",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("")).build());
        System.out.println("Successfully clicked the searched course title");
    }

    public void clickCancel() {
        clickElement(CANCEL_BUTTON);
        test.log( Status.PASS, "",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("")).build());
    }

    public void clickConfirm() {
        clickElement(CONFIRM_BUTTON);
        test.log( Status.PASS, "",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("")).build());
    }

    private void enterQuestionText() {
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
        enteringText ( QUESTION_FIELD );
        test.log( Status.PASS, "",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("")).build());
    }

    private void handleImageUpload() {
        clickAddImage();
        selectFromGallery();
        clickImage();
        clickCrop();
        clickDeleteImage();
        clickCancel();
        clickDeleteImage();
        clickConfirm();
        clickAddImage();
        selectFromGallery();
        clickImage();
        clickCrop();
    }

    private void clickAddImage() {

        clickElement(ADD_IMAGE_BUTTON);
        test.log( Status.PASS, "Successfully clicked The Add Image Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("clicked the add image button")).build());
    }

    public void selectFromGallery() {
        clickElement(GALLERY_OPTION);
        test.log( Status.PASS, "Successfully clicked The Gallery Option",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Gallery Option Successfully")).build());

    }

    public void clickImage() {
        clickElement(IMAGE_THUMBNAIL);
        test.log( Status.PASS, "Successfully clicked the image",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Image Successfully ")).build());
    }

    public void clickCrop() {
        clickElement(CROP_BUTTON);
        test.log( Status.PASS, "Successfully Clicked the Crop Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Crop image clicked successfully")).build());
    }

    public void clickDeleteImage() {
        clickElement(DELETE_IMAGE_BUTTON);
        test.log( Status.PASS, "Successfully Clicked the Delete Image Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Delete Image Button")).build());
    }

    private void clickNextButton() {
        clickElement(NEXT_BUTTON);
        test.log( Status.PASS, "Successfully Clicked the Next Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Next Button Successfully")).build());
    }

    private void clickRules() {
        clickElement(RULES_LINK);
        test.log( Status.PASS, "Successfully clicked the rules link",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("RulesLinkClicked")).build());
    }
}