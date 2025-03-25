package AllCourse.NonSubscribedCourse.PathTab;

import AllCourse.SubscribedCourse.PathTab.Path;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class paths extends BaseActions {
    private static final String EBOOK_XPATH = "(//android.widget.FrameLayout[@resource-id=\"com.affairscloud:id/cv_path_frequent\"]/following::*[@text = \"Ebook\"])[1]";
    private static final String ARTICLE_XPATH = "(//android.widget.FrameLayout[@resource-id=\"com.affairscloud:id/cv_path_frequent\"]/following::*[@text = \"Article\"])[1]";
    private Path pathModule;
    public paths(AndroidDriver driver) {
        super(driver);
        pathModule = new Path (driver);
    }

    public void performPathActions() throws InterruptedException {

        pathModule.clickPath();
        pathModule.clickSubPath();
        pathModule.clickSubSubPath();
        testAllTabs();
    }

    public void testAllTabs() throws InterruptedException {
        Thread.sleep ( 2000 );
        testTab("Monthly");
        testTab("Weekly");
        testTab("Daily");
        testTab("PIB News");
    }

    private void testTab(String tabName)  {
        try {
            // Find and click the tab if it exists
            Thread.sleep ( 1000 );
            WebElement tab = driver.findElement( By.xpath(String.format("//android.widget.LinearLayout[@content-desc=\"%s\"]", tabName)));
            if (tab.isDisplayed()) {
                Thread.sleep ( 1000 );
                tab.click();
                System.out.println("Successfully clicked the " + tabName + " tab");
                tabActionInPath();
            }
        } catch (NoSuchElementException | InterruptedException e) {
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
            System.out.println("Ebook was found in the path");
            if (ebook.isDisplayed()) {
                ebook.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Ebook was not found");
        }
    }

    /**
     * Handles article-related actions in the current path
     */
    private void clickingArticle() {
        try {
            WebElement article = driver.findElement(By.xpath(ARTICLE_XPATH));
            System.out.println("Article was found in the path");
            if (article.isDisplayed()) {
                article.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Article was not found in the path");
        }
    }

}
