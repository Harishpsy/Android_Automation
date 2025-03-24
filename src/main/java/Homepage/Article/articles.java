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
        int maxScrollAttempts = 12; // Limit the number of scrolls to prevent infinite loops
        int attempts = 0;

        while (attempts < maxScrollAttempts) {
            try {
                WebElement element = driver.findElement(By.xpath(targetXPath));
                element.click();
                System.out.println("Article clicked successfully.");
                return; // Exit method after successful click
            } catch (NoSuchElementException e) {
                System.out.println("Article not found, scrolling... Attempt: " + (attempts + 1));
                scrollDown(); // Scroll down dynamically
                attempts++;
            }
        }

        System.out.println("Article not found after " + maxScrollAttempts + " scroll attempts.");
    }

    private void scrollDown() {
        driver.findElement(new AppiumBy.ByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"
        ));
    }



}
