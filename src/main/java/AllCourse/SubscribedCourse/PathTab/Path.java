package AllCourse.SubscribedCourse.PathTab;

import Menu.MyEbooks.myEbooks;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Path extends BaseActions {

    private myEbooks EbookModule;

    public Path(AndroidDriver driver) {
        super ( driver );
        EbookModule = new myEbooks ( driver );
    }

    public void performPathAction() throws InterruptedException {
        clickPath ();
        clickSubPath ();
        clickSubSubPath ();
        Thread.sleep ( 1000 );
        monthly ();
        tabActionInPath();
        Thread.sleep ( 1000 );
        weekly ();
        tabActionInPath();
        Thread.sleep ( 1000 );
        daily ();
        tabActionInPath();
        Thread.sleep ( 1000 );
        pibNews ();
        tabActionInPath();

    }

    protected void clickPath() {
        clickElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Path\"]" ) );
        System.out.println ( "Successfully clicked the path tab" );
    }

    protected void clickSubPath() {
        clickElement ( By.xpath ( "(//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_name\"])[1]" ) );
        System.out.println ( "Successfully clicked the sub path" );

    }

    protected void clickSubSubPath() throws InterruptedException {
        Thread.sleep ( 3000 );
        clickElement ( By.xpath ( "(//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_name\"])[1]" ) );
        System.out.println ( "Successfully clicked the Sub Sub path" );
    }

    protected void monthly() {
        try {
            WebElement monthlyTab = driver.findElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Monthly\"]" ) );
            if (monthlyTab.isDisplayed ()) {
                clickElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Monthly\"]" ) );
                System.out.println ( "Successfully clicked the monthly tab" );
            }
        } catch (NoSuchElementException e) {
            System.out.println ( "Monthly tab not found" );
        }
    }

    protected void weekly() {
        try {
            WebElement weeklyTab = driver.findElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Weekly\"]" ) );
            if (weeklyTab.isDisplayed ()) {
                clickElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Weekly\"]" ) );
                System.out.println ( "Successfully clicked the weekly tab" );
            }
        } catch (NoSuchElementException e) {
            System.out.println ( "Weekly tab not found" );
        }
    }

    protected void daily() {
        try {
            WebElement dailyTab = driver.findElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Daily\"]" ) );
            if (dailyTab.isDisplayed ()) {
                clickElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Daily\"]" ) );
                System.out.println ( "Successfully clicked the Daily tab" );
            }
        } catch (NoSuchElementException e) {
            System.out.println ( "Daily tab not found" );
        }
    }

    protected void pibNews() {
        try {
            WebElement weeklyTab = driver.findElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"PIB News\"]" ) );
            if (weeklyTab.isDisplayed ()) {
                clickElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"PIB News\"]" ) );
                System.out.println ( "Successfully clicked the PIB-News tab" );
            }
        } catch (NoSuchElementException e) {
            System.out.println ( "PIB-News tab not found" );
        }
    }

    protected void tabActionInPath() {
        clickingEbook();
    }

    private void clickingEbook() {

        try {
            WebElement ebook = driver.findElement ( By.xpath ( "(//android.widget.FrameLayout[@resource-id=\"com.affairscloud:id/cv_path_frequent\"]/following::*[@text = \"Ebook\"])[1]" ) );

            if (ebook.isDisplayed ()) {
                clickElement ( By.xpath ( "(//android.widget.FrameLayout[@resource-id=\"com.affairscloud:id/cv_path_frequent\"]/following::*[@text = \"Ebook\"])[1]" ) );

                EbookModule.clickingDownloadButton();
                EbookModule.pdfViewerActions();
                EbookModule.clickingReadIcon ();
                EbookModule.pdfViewerActions();
                EbookModule.threeDotsActions ();
                EbookModule.footerCommonActions ();
                EbookModule.threedots ();
                EbookModule.removedSaved ();
                navigateBack ();
            }
        } catch (NoSuchElementException | InterruptedException e) {
            System.out.println ( "Ebook was not found" );
        }

    }


}
