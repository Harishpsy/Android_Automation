package Homepage.Article;

import Menu.MyNotes.Article_Module;
import Setup.BaseActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class articles extends BaseActions {

    private  Article_Module Article_Module; // Instance of Article_Module

    public articles(AndroidDriver driver){
        super(driver);

        Article_Module = new Article_Module(driver);
    }
    public void performingArticlesActions() throws InterruptedException {
        Thread.sleep ( 3000 );
        clickingArticle();
        scrollList ();
        footerCommonActions ();
        threeDotsActions ();
        navigateBack ();
        scrollToBeginning ();
    }

    private void clickingArticle() {

        String targetXPath = "//android.widget.TextView[contains(@text, 'Article')][1]//following::*[@resource-id='com.affairscloud:id/iv_article'][1]";

        // Try finding the element first before scrolling
        while (true) {
            try {
                WebElement element = driver.findElement( By.xpath(targetXPath));
                element.click();
                System.out.println("article clicked successfully.");
                break; // Exit the loop if clicked
            } catch (NoSuchElementException e) {
                System.out.println("Article not found, scrolling...");
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
