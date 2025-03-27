package Menu.AllCourse;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import static org.openqa.selenium.By.*;

public class allCourse extends BaseActions {

    // Private fields to store user details
    public String userName;
    private String userEmail;

    public allCourse(AndroidDriver driver) {
        super(driver);
    }

    public void performingAllCourseActions() throws InterruptedException {
        clickMenu();
        userDetails(); // This will populate userName and userEmail fields
        clickingAllCourse();
    }

    @Test
    public void clickingAllCourse() {
        clickElement(id("com.affairscloud:id/item_all_courses"));
        System.out.println("Successfully clicked the All Course button In The Side Menu");

        // Application Back Button
        clickElement(id("com.affairscloud:id/img_back_press"));
        System.out.println("Successfully Fully Clicked The Application Back Arrow");
    }

    public void userDetails() {
        this.userName = getUserNameFromProfile();  // Fetches and stores username
        this.userEmail = getUserMailId();
    }

    public String getUserNameFromProfile() {
        String userName = getElementText(By.id("com.affairscloud:id/nav_user_first_name"));
        System.out.println("Login User Name: " + userName);
        return userName;
    }

    public String getUserMailId() {
        String mailId = getElementText(By.id("com.affairscloud:id/nav_email"));
        System.out.println("Login User Mail Id: " + mailId);
        return mailId;
    }

    private String getElementText(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }
}