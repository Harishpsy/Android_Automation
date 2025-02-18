package Menu.MyNotes;

import Menu.MyEbooks.myEbooks;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Article_Module extends myEbooks {

    private Map<String, String> articleNames = new HashMap<>();

    public Article_Module(AndroidDriver driver) {
        super(driver);
    }

    @Test
    public void performArticleActions() throws InterruptedException {

        try{
            WebElement article = driver.findElement (By.id ( "com.affairscloud:id/card_view_article" ));
            System.out.println ("Article Was found in The My Notes List Page ");

            if (article.isDisplayed ()){

                /*Calling the course title method */
                clickCourseTitle();
                /*Calling The Getting Article Name Method*/
                storeArticleNameBeforeClick ();
                /*Calling the article method*/
                clickArticle();
                /*Calling The Getting Article Name Method*/
                storeArticleNameAfterClick();
                /*Calling The Verify Article Method*/
                verifyArticleNames();
                /*Calling The Scroll Method*/
                scrollArticleList ();
                /* Calling The threeDots Method*/
                threeDotsActions();
                /*Calling The Navigate Back Method */
                navigateBack ();
                /*Calling The Footer Common Method*/
                footerCommonActions ();

            }

        }catch (NoSuchElementException e){
            System.out.println ("Article Was not found in The My Notes List Page ");
        }

    }

    private void clickCourseTitle() throws InterruptedException {
        clickElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/card_view_article\"]//child::*[@resource-id=\"com.affairscloud:id/user_details\"][1]"));
        System.out.println("Successfully clicked the course title.");
        Thread.sleep(4000);
        navigateBack();
    }

    private void clickArticle() {
        clickElement ( By.xpath ( "(//*[@resource-id=\"com.affairscloud:id/iv_article\"])[1]" ) );
        System.out.println("Successfully clicked the article.");
    }

    private void storeArticleNameBeforeClick() {
        String articleTitleBeforeClick = getText(By.xpath("(//*[@resource-id=\"com.affairscloud:id/iv_article\"])[1]//following::*[@resource-id=\"com.affairscloud:id/courses_title\"]"));
        articleNames.put("beforeClick", articleTitleBeforeClick);
        System.out.println("Article name before click: " + articleTitleBeforeClick);
    }

    private void storeArticleNameAfterClick() throws InterruptedException {
        Thread.sleep(5000);
        String articleTitleAfterClick = getText(By.id("com.affairscloud:id/title"));
        articleNames.put("afterClick", articleTitleAfterClick);
        System.out.println("Article name after click: " + articleTitleAfterClick);
    }

    private void scrollArticleList() {
        /* Calling The Scroll Method */
        scrollList ();
        System.out.println("Scrolling through the article list.");
    }

    private void verifyArticleNames() {
        String beforeClick = articleNames.get("beforeClick");
        String afterClick = articleNames.get("afterClick");

        if (beforeClick != null && beforeClick.equals(afterClick)) {
            System.out.println("✅ Article names match before and after clicking.");
        } else {
            System.out.println("❌ Article names do NOT match!");
            System.out.println("🔹 Before: [" + beforeClick + "], After: [" + afterClick + "]");
        }
    }
}