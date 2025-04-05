package Menu.AllCourse;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.By.id;

public class allCourse extends BaseActions {

    // Private fields to store user details
    public String userName;
    private String userEmail;

    public allCourse(AndroidDriver driver) {
        super(driver);
    }

    public void performingAllCourseActions() throws InterruptedException {
        try {
            clickMenu();
            test.log(Status.PASS, "Menu clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("MenuClicked")).build());

            userDetails(); // This will populate userName and userEmail fields
            clickingAllCourse();

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform All Course actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("AllCourseActionsFailed")).build());
            throw e;
        }
    }

    @Test
    public void clickingAllCourse() {
        try {
            // Click All Course button
            clickElement(id("com.affairscloud:id/item_all_courses"));
            test.log(Status.PASS, "All Course button clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("AllCourseClicked")).build());
            System.out.println("Successfully clicked the All Course button In The Side Menu");

            // Click back button
            clickElement(id("com.affairscloud:id/img_back_press"));
            test.log(Status.PASS, "Application back button clicked successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("BackButtonClicked")).build());
            System.out.println("Successfully Fully Clicked The Application Back Arrow");

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to complete All Course navigation: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("AllCourseNavigationFailed")).build());
            throw e;
        }
    }

    public void userDetails() {
        try {
            this.userName = getUserNameFromProfile();  // Fetches and stores username
            this.userEmail = getUserMailId();
            test.log(Status.INFO, "Retrieved user details - Name: " + userName + ", Email: " + userEmail);
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to retrieve user details: " + e.getMessage());
            throw e;
        }
    }

    public String getUserNameFromProfile() {
        try {
            String userName = getElementText(By.id("com.affairscloud:id/nav_user_first_name"));
            test.log(Status.INFO, "Login User Name: " + userName);
            System.out.println("Login User Name: " + userName);
            return userName;
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to get username: " + e.getMessage());
            throw e;
        }
    }

    public String getUserMailId() {
        try {
            String mailId = getElementText(By.id("com.affairscloud:id/nav_email"));
            test.log(Status.INFO, "Login User Mail Id: " + mailId);
            System.out.println("Login User Mail Id: " + mailId);
            return mailId;
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to get user email: " + e.getMessage());
            throw e;
        }
    }

    private String getElementText(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getText();
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to get element text: " + e.getMessage());
            throw e;
        }
    }
}