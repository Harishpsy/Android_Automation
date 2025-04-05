package Menu.MyNotes;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Article_Module extends BaseActions {
    private final Map<String, String> articleNames = new HashMap<>();
    private final WebDriverWait wait;

    // XPath constants
    private static final String ARTICLE_CARD = "com.affairscloud:id/card_view_article";
    private static final String COURSE_TITLE = "//*[@resource-id=\"com.affairscloud:id/card_view_article\"]//child::*[@resource-id=\"com.affairscloud:id/user_details\"][1]";
    private static final String ARTICLE_ITEM = "(//*[@resource-id=\"com.affairscloud:id/courses_type\"])[1]";
    private static final String ARTICLE_TITLE_BEFORE = "(//*[@resource-id=\"com.affairscloud:id/iv_article\"])[1]//following::*[@resource-id=\"com.affairscloud:id/courses_title\"]";
    private static final String ARTICLE_TITLE_AFTER = "com.affairscloud:id/title";

    public Article_Module(AndroidDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void performArticleActions() {
        try {
            WebElement article = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(ARTICLE_CARD)));
            test.log(Status.PASS, "Article was found in the My Notes List Page",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article Found")).build());
            System.out.println("Article was found in the My Notes List Page");

            // Execute all article actions in sequence
            clickCourseTitle();
            storeArticleNameBeforeClick();
            clickArticle();
            storeArticleNameAfterClick();
            verifyArticleNames();
            scrollArticleList();
            threeDotsActions();
            footerCommonActions();

        } catch (NoSuchElementException e) {
            test.log(Status.FAIL, "Article was not found in the My Notes List Page: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article Not Found")).build());
            System.out.println("Article was not found in the My Notes List Page");
        } catch (Exception e) {
            test.log(Status.FAIL, "Error performing article actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article Action Error")).build());
            throw new RuntimeException(e);
        }
    }

    private void clickCourseTitle() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(COURSE_TITLE))).click();
            test.log(Status.PASS, "Successfully clicked the course title",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked Course Title")).build());
            System.out.println("Successfully clicked the course title");
            navigateBack();
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click course title: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Click Course Title")).build());
            throw e;
        }
    }

    private void clickArticle() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ARTICLE_ITEM))).click();
            test.log(Status.PASS, "Successfully clicked the article",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked Article")).build());
            System.out.println("Successfully clicked the article");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click article: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Click Article")).build());
            throw e;
        }
    }

    private void storeArticleNameBeforeClick() {
        try {
            String articleTitle = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(ARTICLE_TITLE_BEFORE))).getText();
            articleNames.put("beforeClick", articleTitle);
            test.log(Status.INFO, "Article name before click: " + articleTitle,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article Before Click")).build());
            System.out.println("Article name before click: " + articleTitle);
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to store article name before click: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Store Before")).build());
            throw e;
        }
    }

    private void storeArticleNameAfterClick() {
        try {
            String articleTitle = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id(ARTICLE_TITLE_AFTER))).getText();
            articleNames.put("afterClick", articleTitle);
            test.log(Status.INFO, "Article name after click: " + articleTitle,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article After Click")).build());
            System.out.println("Article name after click: " + articleTitle);
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to store article name after click: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Store After")).build());
            throw e;
        }
    }

    public void scrollArticleList() {
        try {
            scrollList();
            test.log(Status.PASS, "Scrolled through the article list",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scrolled Article List")).build());
            System.out.println("Scrolled through the article list");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to scroll article list: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Scroll Articles")).build());
            throw e;
        }
    }

    private void verifyArticleNames() {
        try {
            String beforeClick = articleNames.get("beforeClick");
            String afterClick = articleNames.get("afterClick");

            if (beforeClick != null && beforeClick.equals(afterClick)) {
                test.log(Status.PASS, "Verification passed - Article names match",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article Names Match")).build());
                System.out.println("✅ Verification passed - Article names match");
            } else {
                test.log(Status.FAIL, "Verification failed - Article names differ",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article Names Differ")).build());
                test.log(Status.INFO, "Before: [" + beforeClick + "], After: [" + afterClick + "]");
                System.out.println("❌ Verification failed - Article names differ");
                System.out.println("Before: [" + beforeClick + "]");
                System.out.println("After: [" + afterClick + "]");
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to verify article names: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Verification")).build());
            throw e;
        }
    }
}