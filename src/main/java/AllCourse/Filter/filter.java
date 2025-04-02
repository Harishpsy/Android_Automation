package AllCourse.Filter;

import Setup.BaseActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.*;

public class filter extends BaseActions {
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Constants for element locators
    private static final By FILTER_BUTTON = id("com.affairscloud:id/iv_filter");
    private static final By APPLY_BUTTON = id("com.affairscloud:id/applyFilter");
    private static final By RESET_BUTTON = id("com.affairscloud:id/clear_filter");
    private static final By PRICE_TYPE = xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/filter_item_tv\" and @text=\"Price Type\"]");
    private static final By FEATURED_COURSE = xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/filter_item_tv\" and @text=\"Featured Course\"]");
    private static final By SUBJECTS = xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/filter_item_tv\" and @text=\"Subjects\"]");
    private static final By EXAMS = xpath("(//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/cl_parent\"])[5]");
    private static final By TAGS = xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/filter_item_tv\" and @text=\"Tags\"]");
    private static final By DATES = xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/filter_item_tv\" and @text=\"Date\"]");


    public filter(AndroidDriver driver) {
        super ( driver );
    }

    public void performFilterAction() {
        safeClickFilterButton();
        navigateBack();
        safeClickFilterButton();

        featuredCourseAction();
        safeClickFilterButton();
        subjectsAction();
        safeClickFilterButton();
        examAction();
        safeClickFilterButton();
        priceTypeAction();
        safeClickFilterButton();
        priceTypePaid();
        clickingResetButton();
    }

    protected void featuredCourseAction() {
        if (clickIfPresent(FEATURED_COURSE, "Featured Course")) {
            featuredCourseSubModulesYes();
            clickingApplyButton();
            safeClickFilterButton();

            clickIfPresent(FEATURED_COURSE, "Featured Course");
            featuredCourseSubModulesNo();
            clickingApplyButton();
        }
    }

    protected void priceTypeAction() {
        if (clickIfPresent(PRICE_TYPE, "Price Type")) {
            priceTypeFree();
            clickingApplyButton();
            safeClickFilterButton();
        }
    }

    protected void subjectsAction() {
        if (clickIfPresent(SUBJECTS, "Subjects")) {
            // Array of subject methods to call
            Runnable[] subjectActions = {
                    this::firstSubject,
                    this::secondSubject,
                    this::thirdSubject,
                    this::fourthSubject,
                    this::fifthSubject,
                    this::sixthSubject,
                    this::seventhSubject,
                    this::eighthSubject,
                    this::ninethSubject,
                    this::thenthSubject
            };

            for (Runnable action : subjectActions) {
                safeClickFilterButton();
                if (clickIfPresent(SUBJECTS, "Subjects")) {
                    action.run();
                    clickingApplyButton();
                }
            }
        }
    }

    protected void examAction() {
        if (clickIfPresent(EXAMS, "Exams")) {
            firstExam();
            clickingApplyButton();
            safeClickFilterButton();

            if (clickIfPresent(EXAMS, "Exams")) {
                secondExam();
                clickingApplyButton();
                safeClickFilterButton();

                if (clickIfPresent(EXAMS, "Exams")) {
                    thirdExam();
                    clickingApplyButton();
                }
            }
        }
    }

    private void safeClickFilterButton() {
        clickIfPresent(FILTER_BUTTON, "Filter Button");
    }

    private boolean clickIfPresent(By locator, String elementName) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            if (element.isDisplayed()) {
                element.click();
                System.out.println("Successfully clicked the " + elementName);
                return true;
            }else{
                System.out.println ("Error:" + elementName + " not present or not clickable");
            }
        } catch (Exception e) {
            System.out.println(elementName + " not present or not clickable");
        }
        return false;
    }

    private void clickingApplyButton() {
        clickIfPresent(APPLY_BUTTON, "Apply Button");
    }

    protected void clickingResetButton() {
        clickIfPresent(RESET_BUTTON, "Reset Button");
    }

    protected void priceTypeFree() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/rb_filter\" and @text=\"Free\"]"),
                "Free Price Type radio button");
    }

    protected void priceTypePaid() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/rb_filter\" and @text=\"Paid\"]"),
                "Paid Price Type radio button");
    }

    protected void featuredCourseSubModulesYes() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/rb_filter\" and @text=\"Yes\"]"),
                "Yes radio button");
    }

    protected void featuredCourseSubModulesNo() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/rb_filter\" and @text=\"No\"]"),
                "No radio button");
    }

    protected void firstSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Current Affairs\"]"),
                "Current Affairs checkbox");
    }

    protected void secondSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Data Interpretation\"]"),
                "Data Interpretation checkbox");
    }

    protected void thirdSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Puzzle & Seating\"]"),
                "Puzzle & Seating checkbox");
    }

    protected void fourthSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Logical Reasoning\"]"),
                "Logical Reasoning checkbox");
    }

    protected void fifthSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Arithmetic\"]"),
                "Arithmetic checkbox");
    }

    protected void sixthSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Quantitative Aptitude\"]"),
                "Quantitative Aptitude checkbox");
    }

    protected void seventhSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Banking Awareness\"]"),
                "Banking Awareness checkbox");
    }

    protected void eighthSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Computer Awareness\"]"),
                "Computer Awareness checkbox");
    }

    protected void ninethSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Computer Awareness\"]"),
                "Computer Awareness checkbox");
    }

    protected void thenthSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Exams\"]"),
                "Exams checkbox");
    }

    protected void firstExam() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Bank PO\"]"),
                "Bank PO checkbox");
    }

    protected void secondExam() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Bank Clerk\"]"),
                "Bank Clerk checkbox");
    }

    protected void thirdExam() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Insurance\"]"),
                "Insurance checkbox");
    }


    /*==================MY COURSE FILTER ACTIONS =============*/
    public void performMyCourseFilterAction() throws InterruptedException {
        driver.manage ().timeouts ().implicitlyWait ( 30, TimeUnit.SECONDS );
        clickiFilterIcon();
        clickTags();
        clickDaily();
        clickingApplyButton();
        clickiFilterIcon();
        clickDaily();
        clickSearch();
        enteringText();
        clickSearchedTag();
        clickingApplyButton ();
        clickiFilterIcon();
        clickingResetButton ();
        clickiFilterIcon();
        clickDates();
        clickingStartDate();
        clickingEndDate();
        clickingApplyButton ();
        clickiFilterIcon();
        clickingResetButton ();
    }

    protected void clickiFilterIcon() {
        clickIfPresent ( FILTER_BUTTON, "Filter Button");
    }

    protected void clickTags() {
        clickIfPresent ( TAGS, "Tags");
    }

    protected void clickDaily() throws InterruptedException {
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Daily\"]"));
        System.out.println ("Successfully clicked the Daily");
    }

    private void clickSearch() {
        clickElement ( xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_search\"]" ));
    }

    private void enteringText(){

        clickElement ( xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_search\"]" ) );

        WebElement enterText = driver.findElement (xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_search\"]" ));
        enterText.sendKeys ( "June" );

    }

    private void clickSearchedTag() {
        clickElement ( xpath ( "//android.view.ViewGroup[@resource-id=\"com.affairscloud:id/cl_multi\"]" ) );
    }

    protected void clickDates() {
        clickIfPresent ( DATES, "Dates" );
    }

    protected void clickingStartDate() {
        clickElement ( xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/frm_date\" and @text=\"Select From Date\"]" ) );

        WebElement clickStartDate = driver.findElement ( new AppiumBy.ByAndroidUIAutomator ("new UiSelector().text(\"1\")") );
        clickOkButton();
    }

    protected void clickOkButton() {
        clickElement ( xpath ( "//android.widget.Button[@resource-id=\"android:id/button1\"]" ) );
    }

    protected  void clickingEndDate() {
        clickElement ( xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/frm_date\" and @text=\"Select To Date\"]" ) );

        WebElement clickStartDate = driver.findElement ( new AppiumBy.ByAndroidUIAutomator ("new UiSelector().text(\"2\")") );

        clickOkButton();
    }



























}