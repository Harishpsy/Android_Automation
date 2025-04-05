package AllCourse.SubscribedCourse.FreeTab;

import AllCourse.SubscribedCourse.ArticleTab.Article;
import AllCourse.SubscribedCourse.EbooksTab.Ebook;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.time.Duration;

public class Free extends BaseActions {

    private Ebook ebook;
    public Free(AndroidDriver driver) {
        super(driver);
        ebook = new Ebook(driver);
    }

    public void performFreeActions() throws InterruptedException {
        scrollRight();
        clickingFreeTab();
        freeContentActions();
    }

    protected void freeContentActions() throws InterruptedException {
        Thread.sleep(3000);
        clickArticle();
        Thread.sleep(3000);
        clickEbook();
        threedotClick();
        removedSaved();
        threedotClick();
        share();
        threedotClick();
        report();
        reportButton();
    }

    protected void clickingFreeTab() {
        clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Free\"]"));
        test.log(Status.PASS, "Successfully Clicked Free Tab",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Free Tab Clicked")).build());
    }

    protected void scrollRight() {
        try {
            WebElement freeTab = driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Free\"]"));
            if (freeTab.isDisplayed()) {
                freeTab.click();
                test.log(Status.PASS, "Free Tab Found and Clicked",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Free Tab Clicked")).build());
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            ebook.scrollHorizontalRight(1); // Scroll right once
            test.log(Status.INFO, "Scrolled Right to Find Free Tab",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Scrolled Right")).build());
        }
    }

    protected void clickArticle() {
        try {
            WebElement article = driver.findElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/rvFreeContentInPaid\"]/android.widget.RelativeLayout/child::*//android.widget.ImageView[@resource-id=\"com.affairscloud:id/img_article\"][1]"));
            if (article.isDisplayed()) {
                clickElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/rvFreeContentInPaid\"]/android.widget.RelativeLayout/child::*//android.widget.ImageView[@resource-id=\"com.affairscloud:id/img_article\"][1]"));
                test.log(Status.PASS, "Clicked Free Article",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article Clicked")).build());

                scrollList();
                test.log(Status.PASS, "Scrolled Article Content",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article Scrolled")).build());

                threeDotsActions();
                navigateBack();
            }
        } catch (NoSuchElementException | InterruptedException e) {
            test.log(Status.INFO, "No Article in Free Content",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Article")).build());
            System.out.println("There is no article in the free content");
        }
    }

    protected void clickEbook() {
        try {
            WebElement article = driver.findElement(By.xpath("(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/rvFreeContentInPaid\"]/android.widget.RelativeLayout/child::*//android.widget.ImageView[@resource-id=\"com.affairscloud:id/img_blur_image\"])[1]"));
            if (article.isDisplayed()) {
                clickElement(By.xpath("(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/rvFreeContentInPaid\"]/android.widget.RelativeLayout/child::*//android.widget.ImageView[@resource-id=\"com.affairscloud:id/img_blur_image\"])[1]"));
                test.log(Status.PASS, "Clicked Free Ebook",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Ebook Clicked")).build());

                ebook.ebookAction();
            }
        } catch (NoSuchElementException | InterruptedException e) {
            test.log(Status.INFO, "No Ebook in Free Content",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Ebook")).build());
            System.out.println("There is no Ebook in the free content");
        }
    }

    protected void threedotClick() {
        clickElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.affairscloud:id/more\"]"));
        test.log(Status.PASS, "Clicked Three Dots Menu",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Three Dots Clicked")).build());
    }
}