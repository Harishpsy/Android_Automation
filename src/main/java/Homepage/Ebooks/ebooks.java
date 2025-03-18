package Homepage.Ebooks;

import Menu.MyEbooks.myEbooks;
import Setup.BaseActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

public class ebooks extends BaseActions {

    private myEbooks myEbookActions; // Instance of myEbooks

    public ebooks(AndroidDriver driver) {
        super(driver);
        myEbookActions = new myEbooks(driver); // Initializing myEbooks
    }

    public void performingEbooksActions() throws InterruptedException {
        Thread.sleep(5000); // Reduce wait time
        clickingEbook();  // Directly try to find & click the Ebook
        myEbookActions.performEbookActions();
        scrollToBeginning ();
    }

    private void clickingEbook() {
        String targetXPath = "//android.widget.TextView[contains(@text, 'Ebook')][1]//following::*[@resource-id='com.affairscloud:id/iv_article'][1]";

        // Try finding the element first before scrolling
        while (true) {
            try {
                WebElement element = driver.findElement(By.xpath(targetXPath));
                element.click();
                System.out.println("Ebook clicked successfully.");
                break; // Exit the loop if clicked
            } catch (NoSuchElementException e) {
                System.out.println("Ebook not found, scrolling...");
                scrollDown(); // Scroll down dynamically
            }
        }
    }

    private void scrollDown() {
        driver.findElement(new AppiumBy.ByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"
        ));
    }

}
