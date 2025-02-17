package Menu.MyEbooks;

import Menu.menubase;
import Setup.Base;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.appium.java_client.AppiumBy.id;
import static org.openqa.selenium.By.xpath;

public class myEbooks extends menubase {

    private Map<String, String> beforeClickingEbook = new HashMap<>();

    public myEbooks(AndroidDriver driver) {
        Base.driver = driver;
    }

    /**
     * Performs the following operations in sequence:
     * 1. Clicks the menu button.
     * 2. Clicks the "My-ebook" button in the side drawer.
     * 3. Scrolls the page to fetch all the data.
     * 4. Calls the {@link #collectEbookListData()} method to collect the data.
     * 5. Clicks an ebook to see its details.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    public void navigateToMyEbooks() throws InterruptedException {
        clickMenu();
        clickElement(id("com.affairscloud:id/item_my_ebooks"));
        System.out.println("Successfully clicked the My-ebook button and the list page is displayed.");

        // Method Calling
        scrollMyEbookList(); //Scrolling The ebook list page
        collectEbookListData(); // Getting the ebook data in the list page to find duplicate
        clickEbook(); // clicking the ebook
        afterClickingEbook(); // Getting the ebook name in the ebook detail page to verify
        verifyEbookNames(); // Verifying ebooks name match befor and after click
        clickingDownloadButton (); // clicking the download button
        pdfViewerActions(); // Pdf-viewer action performing
    }

    @Test(enabled = true, dependsOnMethods = "navigateToMyEbooks")
    public void collectEbookListData() {
        new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> ebookElements = driver.findElements(xpath("//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/root_rl\"]/*[@resource-id=\"com.affairscloud:id/details_rl\"]/*[@resource-id=\"com.affairscloud:id/tv_sub_courses_title\"]"));
        List<String> ebookNames = new ArrayList<>();

        try {
            for (WebElement ebook : ebookElements) {
                ebookNames.add(ebook.getText());
            }
            System.out.println("List of Ebook Names: " + ebookNames);
        } catch (NoSuchElementException e) {
            System.out.println("Error in collectEbookListData: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = "navigateToMyEbooks", enabled = true)
    public void scrollMyEbookList() {
        try {
            WebElement scroll = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(30)"));
            System.out.println("Successfully scrolled to the bottom of the My Ebook List page.");
        } catch (Exception e) {
            System.out.println("Error while scrolling down: " + e.getMessage());
        }

        try {
            WebElement scroll = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(30)"));
            System.out.println("Successfully scrolled back to the top of the My Ebook List page.");
        } catch (Exception e) {
            System.out.println("Error while scrolling to the initial position: " + e.getMessage());
        }
    }

    @Test(enabled = true, dependsOnMethods = "navigateToMyEbooks")
    protected void clickEbook() {
        beforeClickingEbook();
        clickElement(xpath("(//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/root_rl\"])[1]"));
        System.out.println("Successfully clicked the ebook.");

    }
@Test(dependsOnMethods = "navigateToMyEbooks")
    public void beforeClickingEbook() {
        String ebookTitle = getText(xpath("(//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/details_rl\"])[1]/*[@resource-id=\"com.affairscloud:id/tv_courses_title\"]"));
        beforeClickingEbook.put("ebookTitle", ebookTitle); // We are storing the ebook name in the hashmap
        System.out.println ( "Ebook Title Before Click: " + ebookTitle ); // Additional conformation of the ebook name
    }
@Test(dependsOnMethods = "clickEbook")
    public void afterClickingEbook() {
        String ebookTitle = getText(xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/ebook_title\"]"));
        beforeClickingEbook.put("ebookTitleAfterClick", ebookTitle); // We are storing the ebook name in the hashmap
        System.out.println ( "Ebook Title After Click: " + ebookTitle ); // Additional conformation of the ebook name
    }
    @Test(dependsOnMethods = "clickEbook")
    private void verifyEbookNames() {
        Map<String, String> afterClickingEbook = new HashMap<>();
        afterClickingEbook.put("ebookTitleAfterClick", beforeClickingEbook.get("ebookTitleAfterClick"));

        if (beforeClickingEbook.get("ebookTitle").equals(afterClickingEbook.get("ebookTitleAfterClick"))) {
            System.out.println("✅ Ebook details match before and after clicking." + "Before Click"+ beforeClickingEbook.get("ebookTitle") + "After Click" +  afterClickingEbook.get ( "ebookTitleAfterClick" ));
        } else {
            System.out.println("❌ Ebook details do NOT match!");
            System.out.println("🔹 Before: [" + beforeClickingEbook.get("ebookTitle") + "], After: [" + afterClickingEbook.get("ebookTitleAfterClick") + "]");
        }
    }
    @Test(dependsOnMethods = "clickEbook")
    public void clickingDownloadButton() throws InterruptedException {

        //Clicking The Download button in the Ebook detail page
        clickElement ( xpath ( "(//android.widget.ImageButton[@content-desc=\"Download\"])[1]" ) );
        System.out.println ( "Successfully clicked the Download button." );

    }

    //This was Not working Need to find some other way
    public void scrollPDFViewer() {
        try {
            new WebDriverWait ( driver , Duration.ofSeconds ( 40 ) );
            WebElement scroll = driver.findElement(new AppiumBy.ByAndroidUIAutomator( "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceId(\"com.affairscloud:id/pdfView\"))" ));
            System.out.println("Successfully scrolled to the bottom of the PDF Viewer.");
        } catch (Exception e) {
            System.out.println("Error while scrolling to the bottom: " + e.getMessage());
        }
    }
    @Test(dependsOnMethods = "clickingDownloadButton")
    public void pdfViewerActions() {

        //Switching From Potriate to Landscape
        clickElement ( id ( "com.affairscloud:id/orientation" ) );
        System.out.println ("Successfully Pdf turned to landscape mode");

        //Switching From Landscape to Potriate
        clickElement ( id ( "com.affairscloud:id/orientation" ) );
        System.out.println ("Successfully Pdf turned to potriate mode");

        //Clicking The threedot
        clickElement ( id ( "com.affairscloud:id/ivMore" ) );
        System.out.println ("Successfully clicked The Threedots");

        // Clicking The share Button
        clickElement ( id ( "com.affairscloud:id/title" ) );
        System.out.println ("SuccessFully Clicked The Share Button");

        //Clicking The cancel Button in the share popup
        clickElement ( id ( "android:id/button2" ) );
        System.out.println ("Successfully CLicked The Cancel Button");

        // Clicking the PDF viewer to see the full screen
         clickElement (xpath ( "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/pdfView\"]" ) );
         System.out.println ("Successfully Clicked The Pdf Viewer");

        // Clicking the PDF viewer to see the full screen
        clickElement (xpath ( "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/pdfView\"]" ) );
        System.out.println ("Successfully Clicked The Pdf Viewer");

        //Clicking The Back arrow In The PDF Viewer
        clickElement ( id ( "com.affairscloud:id/ivArrowBack" ) );
        System.out.println ("Successfully clicked the back arrow");



    }






}