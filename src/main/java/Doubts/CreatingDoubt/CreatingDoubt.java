package Doubts.CreatingDoubt;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
    }

    public void performCreatingDoubtActions() {
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
        navigateBack();
        navigateBack();
        clickRules();
        navigateBack();
    }

    private void createDoubt() {
        clickElement(DOUBT_INPUT_FIELD);
    }

    private void clickCloseIcon() {
        clickElement(CLOSE_ICON);
    }

    private void clickSubject() {
        clickElement(SUBJECT_BUTTON);
    }

    private void clickExam() {
        clickElement(EXAM_BUTTON);
    }

    private void searchCourse() {
        clickElement(COURSE_SEARCH_FIELD);
        WebElement searchField = driver.findElement(COURSE_SEARCH_FIELD);
        searchField.sendKeys("Mock");
        driver.hideKeyboard();
    }

    private void selectCourse() {
        clickElement(COURSE_TITLE);
        System.out.println("Successfully clicked the searched course title");
    }

    private void clickCancel() {
        clickElement(CANCEL_BUTTON);
    }

    private void clickConfirm() {
        clickElement(CONFIRM_BUTTON);
    }

    private void enterQuestionText() {
        enteringText ( QUESTION_FIELD );
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
    }

    private void selectFromGallery() {
        clickElement(GALLERY_OPTION);
    }

    private void clickImage() {
        clickElement(IMAGE_THUMBNAIL);
    }

    private void clickCrop() {
        clickElement(CROP_BUTTON);
    }

    private void clickDeleteImage() {
        clickElement(DELETE_IMAGE_BUTTON);
    }

    private void clickNextButton() {
        clickElement(NEXT_BUTTON);
    }

    private void clickRules() {
        clickElement(RULES_LINK);
    }
}