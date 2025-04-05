package AllCourse.NonSubscribedCourse.PathTab;

import AllCourse.SubscribedCourse.PathTab.Path;
import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class PathNonSubscribed extends BaseActions {
    private static final String EBOOK_XPATH = "(//android.widget.FrameLayout[@resource-id=\"com.affairscloud:id/cv_path_frequent\"]/following::*[@text = \"Ebook\"])[1]";
    private static final String ARTICLE_XPATH = "(//android.widget.FrameLayout[@resource-id=\"com.affairscloud:id/cv_path_frequent\"]/following::*[@text = \"Article\"])[1]";
    private Path pathModule;

    public PathNonSubscribed(AndroidDriver driver) {
        super(driver);
        pathModule = new Path(driver);
    }

    public void performPathActions() throws InterruptedException {
        try {
            pathModule.clickPath();
            pathModule.clickSubPath();
            pathModule.clickSubSubPath();
            testAllTabs();

            test.log(Status.PASS, "Path actions completed successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PathActionsComplete")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Path actions failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PathActionsFailed")).build());
            throw e;
        }
    }

    public void testAllTabs() throws InterruptedException {
        Thread.sleep(2000);
        testTab("Monthly");
        testTab("Weekly");
        testTab("Daily");
        testTab("PIB News");
    }

    private void testTab(String tabName) throws InterruptedException {
        try {
            Thread.sleep(1000);
            WebElement tab = driver.findElement(By.xpath(String.format("//android.widget.LinearLayout[@content-desc=\"%s\"]", tabName)));
            if (tab.isDisplayed()) {
                Thread.sleep(1000);
                tab.click();
                test.log(Status.PASS, "Clicked " + tabName + " tab",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot(tabName + "TabClicked")).build());
                System.out.println("Successfully clicked the " + tabName + " tab");
                tabActionInPath();
            }
        } catch (NoSuchElementException e) {
            test.log(Status.INFO, tabName + " tab not found");
            System.out.println(tabName + " tab not found");
            tabActionInPath();
        }
    }

    private void tabActionInPath() {
        clickingEbook();
        clickingArticle();
    }

    private void clickingEbook() {
        try {
            WebElement ebook = driver.findElement(By.xpath(EBOOK_XPATH));
            test.log(Status.INFO, "Ebook found in path");
            System.out.println("Ebook was found in the path");
            if (ebook.isDisplayed()) {
                ebook.click();
                test.log(Status.PASS, "Clicked ebook in path",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("EbookClicked")).build());
            }
        } catch (NoSuchElementException e) {
            test.log(Status.INFO, "Ebook not found in path");
            System.out.println("Ebook was not found");
        }
    }

    private void clickingArticle() {
        try {
            WebElement article = driver.findElement(By.xpath(ARTICLE_XPATH));
            test.log(Status.INFO, "Article found in path");
            System.out.println("Article was found in the path");
            if (article.isDisplayed()) {
                article.click();
                test.log(Status.PASS, "Clicked article in path",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("ArticleClicked")).build());
            }
        } catch (NoSuchElementException e) {
            test.log(Status.INFO, "Article not found in path");
            System.out.println("Article was not found in the path");
        }
    }
}