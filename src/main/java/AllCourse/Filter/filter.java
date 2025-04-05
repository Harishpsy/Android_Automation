package AllCourse.Filter;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
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
        test.log( Status.PASS, "Successfully clicked the Apply Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Apply Button")).build());
    }

    protected void clickingResetButton() {
        clickIfPresent(RESET_BUTTON, "Reset Button");
        test.log( Status.PASS, "Successfully clicked the Reset Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Reset Button")).build());
    }

    protected void priceTypeFree() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/rb_filter\" and @text=\"Free\"]"),
                "Free Price Type radio button");
        test.log( Status.PASS, "Succesfully clicked the Free Price Type radio button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Price Type")).build());
    }

    protected void priceTypePaid() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/rb_filter\" and @text=\"Paid\"]"),
                "Paid Price Type radio button");
        test.log( Status.PASS, "Successfully clicked the Paid Price Type radio button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Paid Type")).build());
    }

    protected void featuredCourseSubModulesYes() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/rb_filter\" and @text=\"Yes\"]"),
                "Yes radio button");
        test.log( Status.PASS, "Successfully clicked the Yes radio button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Yes")).build());
    }

    protected void featuredCourseSubModulesNo() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/rb_filter\" and @text=\"No\"]"),
                "No radio button");
        test.log( Status.PASS, "Successfully clicked the No radio button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The No")).build());
    }

    protected void firstSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Current Affairs\"]"),
                "Current Affairs checkbox");
        test.log( Status.PASS, "Successfully clicked The Current Affairs checkbox",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("")).build());
    }

    protected void secondSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Data Interpretation\"]"),
                "Data Interpretation checkbox");
        test.log( Status.PASS, "Successfully Cliked The Data Interpretation checkbox",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Data Interpretation")).build());
    }

    protected void thirdSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Puzzle & Seating\"]"),
                "Puzzle & Seating checkbox");
        test.log( Status.PASS, "Successfully Cliked The Puzzle & Seating checkbox",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Puzzle & Seating")).build());
    }

    protected void fourthSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Logical Reasoning\"]"),
                "Logical Reasoning checkbox");
        test.log( Status.PASS, "Successfully Cliked The Logical Reasoning checkbox",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Logical Reasoning")).build());
    }

    protected void fifthSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Arithmetic\"]"),
                "Arithmetic checkbox");
        test.log( Status.PASS, "Successfully Cliked The Arithmetic checkbox",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Arithmetic")).build());
    }

    protected void sixthSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Quantitative Aptitude\"]"),
                "Quantitative Aptitude checkbox");
        test.log( Status.PASS, "Successfully clicked The Quantitative Aptitude checkbox",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Quantitative Aptitude")).build());
    }

    protected void seventhSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Banking Awareness\"]"),
                "Banking Awareness checkbox");
        test.log( Status.PASS, "Succesfully clicked The Banking Awareness checkbox",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Banking Awareness")).build());
    }

    protected void eighthSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Computer Awareness\"]"),
                "Computer Awareness checkbox");
        test.log( Status.PASS, "Successfully clicked The Computer Awareness checkbox",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Computer Awareness")).build());
    }

    protected void ninethSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Computer Awareness\"]"),
                "Computer Awareness checkbox");
        test.log( Status.PASS, "Successfully clicked The Computer Awareness checkbox",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Computer Awareness")).build());
    }

    protected void thenthSubject() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Exams\"]"),
                "Exams checkbox");
        test.log( Status.PASS, "Successfully clicked The Exams checkbox",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Exams ")).build());
    }

    protected void firstExam() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Bank PO\"]"),
                "Bank PO checkbox");
        test.log( Status.PASS, "Successfully clicked The Bank PO checkbox",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Bank PO")).build());
    }

    protected void secondExam() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Bank Clerk\"]"),
                "Bank Clerk checkbox");
        test.log( Status.PASS, "Successfully clicked The Bank Clerk checkbox",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Bank Clerk")).build());
    }

    protected void thirdExam() {
        clickIfPresent(xpath("//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Insurance\"]"),
                "Insurance checkbox");
        test.log( Status.PASS, "Successfully clicked The Insurance checkbox",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Insurance")).build());
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
        test.log( Status.PASS, "Successfully clicked the Filter Button Inside The Course",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Filter Button")).build());
    }

    protected void clickTags() {
        clickIfPresent ( TAGS, "Tags");
        test.log( Status.PASS, "Successfully clicked the Tags",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Tags")).build());
    }

    protected void clickDaily() throws InterruptedException {
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Daily\"]"));
        test.log( Status.PASS, "Successfully clicked the Daily",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Daily")).build());
        System.out.println ("Successfully clicked the Daily");
    }

    private void clickSearch() {
        clickElement ( xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_search\"]" ));
        test.log( Status.PASS, "Successfully clicked The Search",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The Search")).build());
    }

    private void enteringText(){

        clickElement ( xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_search\"]" ) );

        WebElement enterText = driver.findElement (xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_search\"]" ));
        enterText.sendKeys ( "June" );
        test.log( Status.PASS, "Successfully Clicked And Entered The Text",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked And Entered The Text")).build());
    }

    private void clickSearchedTag() {
        clickElement ( xpath ( "//android.view.ViewGroup[@resource-id=\"com.affairscloud:id/cl_multi\"]" ) );
        test.log( Status.PASS, "Successfully clicked The Searched Tag",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("clicked The Searched Tag")).build());
    }

    protected void clickDates() {
        clickIfPresent ( DATES, "Dates" );
        test.log( Status.PASS, "Successfully clicked The Dates",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("clicked The Date")).build());
    }

    protected void clickingStartDate() {
        clickElement ( xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/frm_date\" and @text=\"Select From Date\"]" ) );

        WebElement clickStartDate = driver.findElement ( new AppiumBy.ByAndroidUIAutomator ("new UiSelector().text(\"1\")") );
        clickOkButton();
        test.log( Status.PASS, "Successfully clicked The Start Date",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("clicked The Start Date")).build());
    }

    protected void clickOkButton() {
        clickElement ( xpath ( "//android.widget.Button[@resource-id=\"android:id/button1\"]" ) );
        test.log( Status.PASS, "Successfully clicked The Ok Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("clicked The Ok Button")).build());
    }

    protected  void clickingEndDate() {
        clickElement ( xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/frm_date\" and @text=\"Select To Date\"]" ) );

        WebElement clickStartDate = driver.findElement ( new AppiumBy.ByAndroidUIAutomator ("new UiSelector().text(\"2\")") );
        clickOkButton();
        test.log( Status.PASS, "Successfully clicked The End Date",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("clicked The End Date")).build());
    }



























}