package Menu;

import Setup.Base;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;

import java.time.Duration;

public class menubase extends Base {

    // Constructor - Receive the driver from the test class
    public menubase(AndroidDriver driver) {
        Base.driver = driver;
    }

    public menubase() {
    }

    public void clickMenu() throws InterruptedException {
        Thread.sleep(5000); // Adding a delay for stability, consider using WebDriverWait for better synchronization
        System.out.println("Attempting to open the Menu...");

        WebElement menuButton = driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.affairscloud:id/menu_btn']"));
        System.out.println("Attempting to open the Menu...");

        if (menuButton.isDisplayed()) {
            menuButton.click();
            System.out.println("Successfully clicked the Menu button.");
        } else {
            System.out.println("Error: Menu button is not displayed on the screen or already clicked.");
        }
    }



}
