package Menu.MyNotes;

import Setup.BaseActions;
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
            System.out.println("Article was not found in the My Notes List Page");
        } catch (InterruptedException e) {
            throw new RuntimeException ( e );
        }
    }

    private void clickCourseTitle() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(COURSE_TITLE))).click();
        System.out.println("Successfully clicked the course title");
        navigateBack();
    }

    private void clickArticle() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ARTICLE_ITEM))).click();
        System.out.println("Successfully clicked the article");
    }

    private void storeArticleNameBeforeClick() {
        String articleTitle = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(ARTICLE_TITLE_BEFORE))).getText();
        articleNames.put("beforeClick", articleTitle);
        System.out.println("Article name before click: " + articleTitle);
    }

    private void storeArticleNameAfterClick() {
        String articleTitle = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id(ARTICLE_TITLE_AFTER))).getText();
        articleNames.put("afterClick", articleTitle);
        System.out.println("Article name after click: " + articleTitle);
    }

    public void scrollArticleList() {
        scrollList();
        System.out.println("Scrolled through the article list");
    }

    private void verifyArticleNames() {
        String beforeClick = articleNames.get("beforeClick");
        String afterClick = articleNames.get("afterClick");

        if (beforeClick != null && beforeClick.equals(afterClick)) {
            System.out.println("✅ Verification passed - Article names match");
        } else {
            System.out.println("❌ Verification failed - Article names differ");
            System.out.println("Before: [" + beforeClick + "]");
            System.out.println("After: [" + afterClick + "]");
        }
    }
}