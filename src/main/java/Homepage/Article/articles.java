package Homepage.Article;

import Menu.MyNotes.Article_Module;
import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class articles extends BaseActions {

    private Article_Module Article_Module;

    public articles(AndroidDriver driver) {
        super(driver);
        Article_Module = new Article_Module(driver);
    }

    public void performingArticlesActions() throws InterruptedException {
        try {
            Thread.sleep(3000);
            clickingArticle();
            scrollList();
            footerCommonActions();
            threeDotsActions();
            navigateBack();
            scrollToBeginning();

            test.log(Status.PASS, "All article actions completed successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ArticleActionsCompleted")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform article actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ArticleActionsFailed")).build());
            throw e;
        }
    }

    private void clickingArticle() throws InterruptedException {
        String targetXPath = "//android.widget.TextView[contains(@text, 'Article')][1]//following::*[@resource-id='com.affairscloud:id/iv_article'][1]";
        int maxScrollAttempts = 12;
        int attempts = 0;

        while (attempts < maxScrollAttempts) {
            try {
                WebElement element = driver.findElement(By.xpath(targetXPath));
                element.click();
                test.log(Status.PASS, "Article clicked successfully",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ArticleClicked")).build());
                System.out.println("Article clicked successfully.");
                return;
            } catch (NoSuchElementException e) {
                test.log(Status.INFO, "Article not found, scrolling... Attempt: " + (attempts + 1));
                System.out.println("Article not found, scrolling... Attempt: " + (attempts + 1));
                scrollDown();
                attempts++;
            }
        }

        test.log(Status.FAIL, "Article not found after " + maxScrollAttempts + " scroll attempts",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ArticleNotFound")).build());
        System.out.println("Article not found after " + maxScrollAttempts + " scroll attempts.");
    }
}