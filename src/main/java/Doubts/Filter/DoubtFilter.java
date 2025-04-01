package Doubts.Filter;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DoubtFilter extends BaseActions {
    private final WebDriverWait wait;

    // XPath constants
    private static final String FILTER_BUTTON = "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_filter\"]";
    private static final String SUBJECT_BUTTON = "//android.widget.RadioButton[@resource-id=\"com.affairscloud:id/rbSubject\"]";
    private static final String EXAM_BUTTON = "//android.widget.RadioButton[@resource-id=\"com.affairscloud:id/rbExam\"]";
    private static final String ALL_BUTTON = "//android.widget.RadioButton[@resource-id=\"com.affairscloud:id/rbAll\"]";
    private static final String COURSE_BUTTON = "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/root_view\"]/child::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\" and @text=\"Mock Test testing Exam Course\"]";
    private static final String SEARCH_FIELD = "//android.widget.EditText[@resource-id=\"com.affairscloud:id/etSearchText\"]";
    private static final String CANCEL_BUTTON = "//android.widget.TextView[@resource-id=\"com.affairscloud:id/tvCourseTitle\"]";
    private static final String CLOSE_BUTTON = "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/ivCloseBtn\"]";

    public DoubtFilter(AndroidDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Reduced from 30
    }

    public void performDoubtFilter() {
        try {
            clickFilter();
            clickSubject();
            clickExam();
            clickAll();
            scrollList();
            clickSearch();
            clickCourse();
            clickCancelInFilter();
            clickFilter();
            clickClose();
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Restore original
        }
    }

    private void clickFilter() {
        clickWithWait(FILTER_BUTTON, "Filter Button");
    }

    private void clickSubject() {
        clickWithWait(SUBJECT_BUTTON, "Subject Button");
    }

    private void clickExam() {
        clickWithWait(EXAM_BUTTON, "Exam Button");
    }

    private void clickAll() {
        clickWithWait(ALL_BUTTON, "All Button");
    }

    private void clickCourse() {
        clickWithWait(COURSE_BUTTON, "Course Button");
    }

    private void clickSearch() {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEARCH_FIELD)));
        searchField.click();
        searchField.sendKeys("Mock");
        System.out.println("Successfully searched for 'Mock'");
    }

    private void clickCancelInFilter() {
        clickWithWait(CANCEL_BUTTON, "Cancel Button In Filter");
    }

    private void clickClose() {
        clickWithWait(CLOSE_BUTTON, "Close Button");
    }

    private void clickWithWait(String xpath, String elementName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
            System.out.println("Successfully Clicked The " + elementName);
        } catch (Exception e) {
            System.out.println("Failed to click " + elementName + ": " + e.getMessage());
            throw e;
        }
    }

}