package AllCourse.SubscribedCourse.FreeTab;

import AllCourse.SubscribedCourse.ArticleTab.Article;
import AllCourse.SubscribedCourse.EbooksTab.Ebook;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Free extends BaseActions {

    private Ebook ebook;
    public Free(AndroidDriver driver) {
        super(driver);
        ebook = new Ebook(driver);
    }

    public void performFreeActions () throws InterruptedException {
        scrollRight();
        clickingFreeTab();
        freeContentActions();
    }

    protected void freeContentActions() throws InterruptedException {
        Thread.sleep ( 3000 );
        clickArticle();
        Thread.sleep ( 3000 );
        clickEbook();
        threedotClick();
        removedSaved ();
        threedotClick();
        share ();
        threedotClick();
        report ();
        reportButton ();
    }

    protected void clickingFreeTab(){
        clickElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Free\"]" ) );
    }

    protected void scrollRight(){

        try {

            WebElement freeTab = driver.findElement (By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Free\"]" ));
            if (freeTab.isDisplayed ()) {
                freeTab.click ();

            }
        }catch (NoSuchElementException | IllegalStateException e) {
            ebook.scrollHorizontalRight(1); // Scroll right once
        }
    }

    protected void clickArticle() {

        try {
            WebElement article = driver.findElement ( By.xpath ( "//*[@resource-id=\"com.affairscloud:id/rvFreeContentInPaid\"]/android.widget.RelativeLayout/child::*//android.widget.ImageView[@resource-id=\"com.affairscloud:id/img_article\"][1]" ) );
            if(article.isDisplayed ()){
                clickElement ( By.xpath ( "//*[@resource-id=\"com.affairscloud:id/rvFreeContentInPaid\"]/android.widget.RelativeLayout/child::*//android.widget.ImageView[@resource-id=\"com.affairscloud:id/img_article\"][1]" ) );
                scrollList ();
                footerCommonActions ();
                threeDotsActions ();
                navigateBack ();
            }
        }catch (NoSuchElementException | InterruptedException e){
            System.out.println ("There is no article in the free content");
        }
    }

    protected void clickEbook() {

        try {
            WebElement article = driver.findElement ( By.xpath ( "(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/rvFreeContentInPaid\"]/android.widget.RelativeLayout/child::*//android.widget.ImageView[@resource-id=\"com.affairscloud:id/img_blur_image\"])[1]" ) );
            if(article.isDisplayed ()){
                clickElement ( By.xpath ( "(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/rvFreeContentInPaid\"]/android.widget.RelativeLayout/child::*//android.widget.ImageView[@resource-id=\"com.affairscloud:id/img_blur_image\"])[1]" ) );
                ebook.ebookAction ();
            }
        }catch (NoSuchElementException | InterruptedException e){
            System.out.println ("There is no Ebook in the free content");
        }
    }


    protected void threedotClick(){
        clickElement ( By.xpath ( "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/more\"]" ) );
    }

}
