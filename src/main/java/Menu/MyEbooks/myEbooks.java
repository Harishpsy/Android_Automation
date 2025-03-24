package Menu.MyEbooks;

import Setup.Base;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

import static io.appium.java_client.AppiumBy.id;
import static org.openqa.selenium.By.xpath;

public class myEbooks extends BaseActions {

    private final Map<String, String> beforeClickingEbook = new HashMap<> ();
    private final Map<String, String> collectEbookListData = new HashMap<> ();


    // Constructor to initialize the driver
    public myEbooks(AndroidDriver driver) {
        super ( driver );
        Base.driver = driver; // Assigning the driver to the class

    }

    /**
     * Navigates to the My Ebooks section, performs scrolling, collects ebook data, clicks an ebook,
     * verifies the ebook name, and performs additional actions like downloading and sharing.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    @Test(enabled = true)
    public void navigateToMyEbooks() throws InterruptedException {

        clickMenu (); // Click the menu button
        clickingMyEbook (); //Clicking The My Ebook
        ebookListPageEmpty (); //Ebook list page is empty navigate to back calling that method
    }

    public void clickingMyEbook() {

        // Click the "My Ebooks" button in the side drawer (using ID: com.affairscloud:id/item_my_ebooks)
        clickElement ( id ( "com.affairscloud:id/item_my_ebooks" ) );
        System.out.println ( "Successfully clicked the My-ebook button and the list page is displayed." );
    }


    public void ebookListPageEmpty() throws InterruptedException {

        try {

            WebElement emptyList = driver.findElement ( id ( "com.affairscloud:id/tv_empty_data" ) );
            System.out.println ( "In The My Ebook List Page Saved ebook was not showing So we are Navigating Back" );
            if (emptyList.isDisplayed ()) {
                System.out.println ( "Navigating back..." );
                navigateBack (); /* Calling The Back Method */
            } else {
                System.out.println ( "There is no empty list" );

            }
        } catch (NoSuchElementException e) {
            ebookActions ();
            System.out.println ( "Performing The Ebook Actions" );
        }
    }

    @Test(enabled = true)
    public void ebookActions() throws InterruptedException {

        // Scroll the ebook list and collect data
        scrollList ();
        collectEbookListData ();

        // Click an ebook and verify its name
        clickEbook ();
        afterClickingEbook ();
        verifyEbookNames ();

        // Perform additional actions like downloading, reading, and sharing
        performEbookActions ();
    }


    /**
     * Collects and prints the names of all ebooks in the list.
     *
     * @return
     */
    @Test(enabled = true)
    public List<String> collectEbookListData() {
        new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );

        // Locate all ebook elements using XPath: EBOOK_LIST_XPATH
        List<WebElement> ebookElements = driver.findElements ( xpath ( "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/root_rl\"]/*[@resource-id=\"com.affairscloud:id/details_rl\"]/*[@resource-id=\"com.affairscloud:id/tv_sub_courses_title\"]" ) );
        List<String> ebookNames = new ArrayList<> ();

        try {
            for (WebElement ebook : ebookElements) {
                ebookNames.add ( ebook.getText () );
            }
            System.out.println ( "List of Ebook Names: " + ebookNames );
        } catch (NoSuchElementException e) {
            System.out.println ( "Error in collectEbookListData: " + e.getMessage () );
        }
        return ebookNames;
    }

    /**
     * Clicks the first ebook in the list.
     */
    @Test(enabled = true)
    protected void clickEbook() throws InterruptedException {
        beforeClickingEbook ();
        Thread.sleep ( 7000 );
        clickElement ( xpath ( "(//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/root_rl\"])[1]" ) );
        System.out.println ( "Successfully clicked the ebook." );

    }

    /**
     * Stores the ebook title before clicking on it.
     */
    @Test()
    public void beforeClickingEbook() {
        String ebookTitle = getText ( xpath ( "(//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/details_rl\"])[1]/*[@resource-id=\"com.affairscloud:id/tv_courses_title\"]" ) );
        beforeClickingEbook.put ( "ebookTitle" , ebookTitle ); // We are storing the ebook name in the hashmap
        System.out.println ( "Ebook Title Before Click: " + ebookTitle ); // Additional conformation of the ebook name
    }

    /**
     * Stores the ebook title after clicking on it.
     *
     * @return
     */
    @Test()
    public String afterClickingEbook() {
        String ebookTitle = getText ( xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/ebook_title\"]" ) );
        System.out.println ( "Ebook Title After Click: " + ebookTitle ); // Additional conformation of the ebook name
        return ebookTitle;
    }

    /**
     * Verifies if the ebook title before and after clicking matches.
     */
    @Test()
    private void verifyEbookNames() {
        Map<String, String> afterClickingEbook = new HashMap<> ();
        afterClickingEbook.put ( "ebookTitleAfterClick" , beforeClickingEbook.get ( "ebookTitleAfterClick" ) );

        if (beforeClickingEbook.get ( "ebookTitle" ).equals ( afterClickingEbook.get ( "ebookTitleAfterClick" ) )) {
            System.out.println ( "✅ Ebook details match before and after clicking." + "Before Click" + beforeClickingEbook.get ( "ebookTitle" ) + "After Click" + afterClickingEbook.get ( "ebookTitleAfterClick" ) );
        } else {
            System.out.println ( "❌ Ebook details do NOT match!" );
            System.out.println ( "🔹 Before: [" + beforeClickingEbook.get ( "ebookTitle" ) + "], After: [" + afterClickingEbook.get ( "ebookTitleAfterClick" ) + "]" );
        }
    }

    /**
     * Performs additional actions like downloading, reading, and sharing the ebook.
     */
    @Test(enabled = true)
    public void performEbookActions() throws InterruptedException {

        System.out.println ( "Clicking download button..." );
        clickingDownloadButton (); /* Calling download button method */

        System.out.println ( "Performing PDF viewer actions..." );
        pdfViewerActions (); /* calling the pdf viewer method */

        System.out.println ( "Clicking read icon..." );
        clickingReadIcon (); /* Calling The Read method  */

        System.out.println ( "Performing PDF viewer actions..." );
        pdfViewerActions (); /* calling the pdf viewer method */

        /*Three-Dot Actions Method*/
        threeDotsActions ();

        System.out.println ( "Performing footer common actions..." );
        footerCommonActions (); /* Calling The Footer Common Method */

        System.out.println ( "Clicking three dots..." );
        threedots (); /* Calling The Threedots Method*/

        System.out.println ( "Removing saved item..." );
        removedSaved (); /* Calling The Remove Method*/

        System.out.println ( "Navigating back..." );
        navigateBack (); /* Calling The Back Method */

        System.out.println ( "Verifying ebook names in list page..." );
        verifyEbookNamesInListPage (); /* Calling The Verify Method For The List Page And removed */

        System.out.println ( "Navigating back..." );
        navigateBack (); /* Calling The Back Method */
    }

    /**
     * Clicks the download button for the ebook.
     */
    @Test(dependsOnMethods = "clickEbook", enabled = true)
    public void clickingDownloadButton() throws InterruptedException {
        // Click the download button using XPath: (//android.widget.ImageButton[@content-desc="Download"])[1]
        try{
            WebElement downloadButton = driver.findElement ( xpath ( "(//android.widget.ImageButton[@content-desc=\"Download\"])[1]" ) );
            if (downloadButton.isDisplayed ()) {
                clickElement ( xpath ( "(//android.widget.ImageButton[@content-desc=\"Download\"])[1]" ) );
                System.out.println ( "Successfully clicked the Download button." );
            } else {
                System.out.println ( "Download button not found." );
            }
        }catch (NoSuchElementException e){
            clickingReadIcon();
            System.out.println ("Clicking the read icon becaouse download icon was not found");
        }
    }

    /**
     * Performs actions in the PDF viewer, such as toggling orientation and navigating back.
     */
    @Test(dependsOnMethods = "clickingDownloadButton")
    public void pdfViewerActions() throws InterruptedException {

        /*Rotating The ebook */
        toggleOrientation ();

        // Click the "More" button using ID: com.affairscloud:id/ivMore
        clickElement ( id ( "com.affairscloud:id/ivMore" ) );

        // Click the "Share" button using ID: com.affairscloud:id/title
        clickElement ( id ( "com.affairscloud:id/title" ) );

        // Click the "Cancel" button using ID: android:id/button2
        clickElement ( id ( "android:id/button2" ) );

        // Click the PDF viewer using XPath: //android.widget.RelativeLayout[@resource-id="com.affairscloud:id/pdfView"]
        clickElement ( xpath ( "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/pdfView\"]" ) );

        // Click the back arrow using ID: com.affairscloud:id/ivArrowBack
        Thread.sleep ( 3000 );

        try {
            WebElement backArrow = driver.findElement ( id ( "com.affairscloud:id/ivArrowBack" ) );
            if (backArrow.isDisplayed ()) {
                clickElement ( id ( "com.affairscloud:id/ivArrowBack" ) );
            } else {
                System.out.println ( "The back arrow was not found." );
            }
        }catch (NoSuchElementException e ){

            WebElement clickScreen = driver.findElement ( xpath ( "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/pdfView\"]" ) );
            clickScreen.click ();

            Thread.sleep ( 3000 );
            clickElement ( id ( "com.affairscloud:id/ivArrowBack" ) );
        }
    }

    /**
     * Toggles the PDF viewer orientation between portrait and landscape.
     */
    private void toggleOrientation() throws InterruptedException {
        // Click the orientation button using ID: com.affairscloud:id/orientation
        Thread.sleep ( 5000 );
        clickElement ( id ( "com.affairscloud:id/orientation" ) );
        Thread.sleep ( 5000 );
        clickElement ( id ( "com.affairscloud:id/orientation" ) );

    }

    /**
     * Clicks the read icon for the ebook.
     */
    @Test(dependsOnMethods = "pdfViewerActions", enabled = false)
    public void clickingReadIcon() throws InterruptedException {
        Thread.sleep ( 5000 );
        // Click the read icon using ID: com.affairscloud:id/btn_read
        clickElement ( By.id ( "com.affairscloud:id/btn_read" ) );
        System.out.println ( "Successfully clicked the Read icon." );


    }

    private void verifyEbookNamesInListPage() throws InterruptedException {
        Thread.sleep ( 5000 );
        // Simulating the retrieval of ebook names before and after clicking
        List<String> afterClickingEbook = collectEbookListData ();

        if (!afterClickingEbook.contains ( collectEbookListData )) {
            System.out.println ( "✅ Ebook details do not match before and after clicking." );
        } else {
            System.out.println ( "❌ Ebook details match!" );
            System.out.println ( "🔹 Before: " + afterClickingEbook + ", After: " + collectEbookListData );
        }
    }

    @Test(enabled = true, dependsOnMethods = "navigateToMyEbooks")
    private List<String> verifyDupicateInListPage() {
        new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );

        // Locate all ebook elements using XPath
        List<WebElement> ebookElements = driver.findElements ( xpath ( "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/root_rl\"]/*[@resource-id=\"com.affairscloud:id/details_rl\"]/*[@resource-id=\"com.affairscloud:id/tv_sub_courses_title\"]" ) );
        List<String> ebookNames = new ArrayList<> ();
        Set<String> uniqueEbookNames = new HashSet<> ();
        Set<String> duplicateEbookNames = new HashSet<> ();

        try {
            for (WebElement ebook : ebookElements) {
                String ebookName = ebook.getText ();
                if (!uniqueEbookNames.add ( ebookName )) {
                    duplicateEbookNames.add ( ebookName );
                }
                ebookNames.add ( ebookName );
            }

            System.out.println ( "List of Ebook Names: " + ebookNames );

            if (!duplicateEbookNames.isEmpty ()) {
                System.out.println ( "Duplicate Ebook Names Found: " + duplicateEbookNames );
            } else {
                System.out.println ( "No duplicate ebooks found." );
            }

        } catch (NoSuchElementException e) {
            System.out.println ( "Error in collectEbookListData: " + e.getMessage () );
        }
        return ebookNames;
    }


}