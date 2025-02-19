package Menu.MyEbooks;

import Menu.menubase;
import Setup.Base;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

import static io.appium.java_client.AppiumBy.id;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

public class myEbooks extends menubase {

    private Map<String, String> beforeClickingEbook = new HashMap<>();
    private Map<String, String> collectEbookListData = new HashMap<>();

    // Constructor to initialize the driver
    public myEbooks(AndroidDriver driver) {
        Base.driver = driver;
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

    public void clickingMyEbook(){

        // Click the "My Ebooks" button in the side drawer (using ID: com.affairscloud:id/item_my_ebooks)
        clickElement(id("com.affairscloud:id/item_my_ebooks"));
        System.out.println("Successfully clicked the My-ebook button and the list page is displayed.");
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
            System.out.println ("Performing The Ebook Actions");
        }
    }

    @Test(enabled = true)
    public void ebookActions() throws InterruptedException {

        // Scroll the ebook list and collect data
        scrollList();
        collectEbookListData();

        // Click an ebook and verify its name
        clickEbook();
        afterClickingEbook();
        verifyEbookNames();

        // Perform additional actions like downloading, reading, and sharing
        performEbookActions();
    }


    /**
     * Collects and prints the names of all ebooks in the list.
     *
     * @return
     */
    @Test(enabled = true)
    public List<String> collectEbookListData() {
        new WebDriverWait(driver, Duration.ofSeconds(30));

        // Locate all ebook elements using XPath: EBOOK_LIST_XPATH
        List<WebElement> ebookElements = driver.findElements(xpath("//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/root_rl\"]/*[@resource-id=\"com.affairscloud:id/details_rl\"]/*[@resource-id=\"com.affairscloud:id/tv_sub_courses_title\"]"));
        List<String> ebookNames = new ArrayList<> ();

        try {
            for (WebElement ebook : ebookElements) {
                ebookNames.add(ebook.getText());
            }
            System.out.println("List of Ebook Names: " + ebookNames);
        } catch (NoSuchElementException e) {
            System.out.println("Error in collectEbookListData: " + e.getMessage());
        }
        return ebookNames;
    }

    /**
     * Clicks the first ebook in the list.
     */
    @Test(enabled = true)
    protected void clickEbook() throws InterruptedException {
        beforeClickingEbook();
        Thread.sleep ( 7000 );
        clickElement(xpath("(//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/root_rl\"])[1]"));
        System.out.println("Successfully clicked the ebook.");

    }

    /**
     * Stores the ebook title before clicking on it.
     */
    @Test()
    public void beforeClickingEbook() {
        String ebookTitle = getText(xpath("(//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/details_rl\"])[1]/*[@resource-id=\"com.affairscloud:id/tv_courses_title\"]"));
        beforeClickingEbook.put("ebookTitle", ebookTitle); // We are storing the ebook name in the hashmap
        System.out.println ( "Ebook Title Before Click: " + ebookTitle ); // Additional conformation of the ebook name
    }

    /**
     * Stores the ebook title after clicking on it.
     *
     * @return
     */
    @Test()
    public String afterClickingEbook() {
        String ebookTitle = getText(xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/ebook_title\"]"));
        System.out.println ( "Ebook Title After Click: " + ebookTitle ); // Additional conformation of the ebook name
        return ebookTitle;
    }

    /**
     * Verifies if the ebook title before and after clicking matches.
     */
    @Test()
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

    /**
     * Performs additional actions like downloading, reading, and sharing the ebook.
     */
    @Test(enabled = true)
    private void performEbookActions() throws InterruptedException {

        System.out.println("Clicking download button...");
        clickingDownloadButton(); /* Calling download button method */

        System.out.println("Performing PDF viewer actions...");
        pdfViewerActions(); /* calling the pdf viewer method */

        System.out.println("Clicking read icon...");
        clickingReadIcon(); /* Calling The Read method  */

        System.out.println("Performing PDF viewer actions...");
        pdfViewerActions(); /* calling the pdf viewer method */

        /*Three-Dot Actions Method*/
        threeDotsActions();

        System.out.println("Performing footer common actions...");
        footerCommonActions(); /* Calling The Footer Common Method */

        System.out.println("Clicking three dots...");
        threedots(); /* Calling The Threedots Method*/

        System.out.println("Removing saved item...");
        removedSaved(); /* Calling The Remove Method*/

        System.out.println("Navigating back...");
        navigateBack(); /* Calling The Back Method */

        System.out.println("Verifying ebook names in list page...");
        verifyEbookNamesInListPage(); /* Calling The Verify Method For The List Page And removed */

        System.out.println("Navigating back...");
        navigateBack(); /* Calling The Back Method */
    }

    /**
     * Clicks the download button for the ebook.
     */
    @Test(dependsOnMethods = "clickEbook", enabled = true)
    public void clickingDownloadButton() {
        // Click the download button using XPath: (//android.widget.ImageButton[@content-desc="Download"])[1]
        clickElement(xpath("(//android.widget.ImageButton[@content-desc=\"Download\"])[1]"));
        System.out.println("Successfully clicked the Download button.");
    }

    /**
     * Performs actions in the PDF viewer, such as toggling orientation and navigating back.
     */
    @Test(dependsOnMethods = "clickingDownloadButton")
    public void pdfViewerActions() throws InterruptedException {

        /*Rotating The ebook */
        toggleOrientation();

        // Click the "More" button using ID: com.affairscloud:id/ivMore
        clickElement(id("com.affairscloud:id/ivMore"));

        // Click the "Share" button using ID: com.affairscloud:id/title
        clickElement(id("com.affairscloud:id/title"));

        // Click the "Cancel" button using ID: android:id/button2
        clickElement(id("android:id/button2"));

        // Click the PDF viewer using XPath: //android.widget.RelativeLayout[@resource-id="com.affairscloud:id/pdfView"]
        clickElement(xpath("//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/pdfView\"]"));

        // Click the back arrow using ID: com.affairscloud:id/ivArrowBack
        clickElement(id("com.affairscloud:id/ivArrowBack"));
    }

    /**
     * Toggles the PDF viewer orientation between portrait and landscape.
     */
    private void toggleOrientation() throws InterruptedException {
        // Click the orientation button using ID: com.affairscloud:id/orientation
        Thread.sleep(5000);
        clickElement(id("com.affairscloud:id/orientation"));
        Thread.sleep(5000);
        clickElement(id("com.affairscloud:id/orientation"));

    }

    /**
     * Clicks the read icon for the ebook.
     */
    @Test(dependsOnMethods = "pdfViewerActions", enabled = false)
    public void clickingReadIcon() throws InterruptedException {
        Thread.sleep(5000);
        // Click the read icon using ID: com.affairscloud:id/btn_read
        clickElement(By.id("com.affairscloud:id/btn_read"));
        System.out.println("Successfully clicked the Read icon.");


    }

    public void threeDotsActions() throws InterruptedException {

        System.out.println("Clicking three dots...");
        threedots(); /* Calling The Threedots Method*/

        System.out.println("Sharing...");
        share(); /* Calling The Share Method */

        System.out.println("Clicking three dots...");
        threedots(); /* Calling The Threedots Method*/

        System.out.println("Reporting...");
        report(); /* Calling The Report Method */

        System.out.println("Canceling...");
        cancel(); /* Calling The Cancel Method */

        System.out.println("Clicking three dots...");
        threedots(); /* Calling The Threedots Method*/

        System.out.println("Reporting...");
        report(); /* Calling The Report Method */

        System.out.println("Generating reports...");
        reportButton(); /* Calling The Report Method */

        System.out.println("Clicking three dots...");
        threedots();  //Calling The Threedots Method

        System.out.println("Removing saved item...");
        removedSaved(); // Calling The Remove Method

    }

    /**
     * Clicks the three dots menu.
     */
    @Test(enabled = true)
    public void threedots() throws  InterruptedException{
        // Click the three dots menu using ID: com.affairscloud:id/iv_more

        /*First*/
        try {
            // Attempt to click the first back button using its ID
            Thread.sleep ( 5000 );
            WebElement threeDots1 = driver.findElement(By.id("com.affairscloud:id/iv_more"));
            if (threeDots1.isDisplayed()) {
                threeDots1.click();
                System.out.println("Clicked the first three dots button.");
                return; // Exit after clicking the first button
            }
        } catch (NoSuchElementException e) {
            System.out.println("First first three dots button not found. Trying the second three dots button...");
        }

        /*Second*/
        try {
            // Attempt to click the first back button using its ID
            Thread.sleep ( 5000 );
            WebElement threeDots2 = driver.findElement(By.id("com.affairscloud:id/more_btn"));
            if (threeDots2.isDisplayed()) {
                threeDots2.click();
                System.out.println("Clicked the second three dots button.");
                return; // Exit after clicking the second button
            }
        } catch (NoSuchElementException e) {
            System.out.println("First back button not found. Trying the second back button...");
        }

    }

    /**
     * Clicks the remove form my-ebooks.
     */
    public void removedSaved(){
        clickElement ( xpath ( "(//*[@resource-id=\"com.affairscloud:id/content\"])[1]" ) );
        System.out.println ("Successfully Removed From Saved");

    }


    /**
     * Clicks the share button and then cancels the share action.
     */
    public void share() {
        // Click the "Share" button using XPath: //*[@text="Share"]
        new WebDriverWait ( driver, Duration.ofSeconds ( 50 ) );
        clickElement(xpath("//*[@text=\"Share\"]"));
        System.out.println("Successfully Clicked The Share Button");

        // Click the "Cancel" button using XPath: //*[@text="Cancel"]
        clickElement(xpath("//*[@text=\"Cancel\"]"));
        System.out.println("Successfully Clicked The Cancel Buttons In The three Dots");
    }

    /**
     * Reports an issue with the ebook by selecting a checkbox, entering text, and canceling the action.
     */
    public void report() {
        // Click the "Report" button using XPath: //*[@text="Report"]
        clickElement(xpath("//*[@text=\"Report\"]"));

        // Click the second checkbox using XPath: (//*[@resource-id="com.affairscloud:id/cbReport"])[2]
        clickElement(xpath("(//*[@resource-id=\"com.affairscloud:id/cbReport\"])[2]"));

        // Enter text in the text field using ClassName: android.widget.EditText
        enteringText(By.className("android.widget.EditText"));
        System.out.println("Successfully Clicked The Report, Checkbox");

    }

    public void cancel(){
        // Click the "Cancel" button using ID: com.affairscloud:id/tv_cancel
        clickElement(id("com.affairscloud:id/tv_cancel"));
        System.out.println("Successfully Clicked The Cancel Button");

    }

    public void reportButton(){

        new WebDriverWait ( driver, Duration.ofSeconds ( 40 ) );

        // Click the "Cancel" button using ID: com.affairscloud:id/tv_cancel
        clickElement(id("com.affairscloud:id/tv_report"));
        System.out.println("Successfully Clicked The Report Button");

    }

    public void footerCommonActions() {
        // Perform the "Like" action
/*
        performLikeAction(); // This method handles liking the content (e.g., a post or ebook)
*/

        // Perform the "Comment" action
        performCommentAction(); // This method allows the user to add a comment to the content

        // Perform the "Reply" action if the reply button is present
        performReplyActionIfPresent(); // This method checks for the presence of a reply button and performs the reply action if available

        // Navigate back to the previous screen
        navigateBack(); // This method navigates the user back to the previous screen or page

        // Call the share function
        share(); // This method handles sharing the content with others (e.g., via social media or messaging apps)
    }

    private void performLikeAction() {
        // Click the "Like" button using its ID
        clickElement(id("com.affairscloud:id/like_layout"));
    }

    private void performCommentAction() {
        // Click the "Comment" button using its ID
        clickElement(id("com.affairscloud:id/comment_layout"));

        // Enter text into the comment input field using its class name
        enteringText(className("android.widget.EditText"));

        // Click the "Send" button using its ID to post the comment
        clickElement(id("com.affairscloud:id/iv_send"));
    }

    private void performReplyActionIfPresent() {
        // Locate the "Reply" button using its XPath
        WebDriverWait wait = new WebDriverWait ( driver, Duration.ofSeconds ( 30 ) );
        WebElement clickingReply = driver.findElement(xpath("(//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_replay\"])[1]"));

        try {

            // Check if the "Reply" button is displayed
            if (clickingReply.isDisplayed ()) {
                // Click the "Reply" button using its XPath
                clickElement ( xpath ( "(//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_replay\"])[1]" ) );

                // Enter text into the reply input field using its class name
                Thread.sleep ( 5000 );
                enteringText ( className ( "android.widget.EditText" ) );

                // Click the "Send" button using its ID to post the reply
                clickElement ( id ( "com.affairscloud:id/iv_send" ) );

                // Navigate back to the previous screen after replying
                navigateBack ();
            } else {
                // Log a message if the "Reply" button is not present
                System.out.println ( "There is no reply button" );
            }
        }catch (NoSuchElementException | InterruptedException e) {
            System.out.println ( "There is no reply button" );
        }
    }

    protected void navigateBack() {
        try {
            // Attempt to click the first back button using its ID
            WebElement backButton1 = driver.findElement(By.id ( "com.affairscloud:id/btn_back" ));
            if (backButton1.isDisplayed()) {
                WebDriverWait wait = new WebDriverWait ( driver, Duration.ofSeconds ( 40 ) );
                backButton1.click();
                System.out.println("Clicked the first back button.");
                return; // Exit after clicking the first button
            }
        } catch (NoSuchElementException e) {
            System.out.println("First back button not found. Trying the second back button...");
        }

        try {
            // Attempt to click the second back button using its XPath
            WebElement backButton2 = driver.findElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/img_back_press\"]"));
            if (backButton2.isDisplayed()) {
                WebDriverWait wait = new WebDriverWait ( driver, Duration.ofSeconds ( 40 ) );
                backButton2.click();
                System.out.println("Clicked the second back button.");
                return; // Exit after clicking the second button
            }
        } catch (NoSuchElementException e) {
            System.out.println("Second back button not found.");
        }

        // If neither button is found or clickable, log an error
        System.out.println("Error: Neither back button could be clicked.");
    }

    private void verifyEbookNamesInListPage() throws InterruptedException {
        Thread.sleep(5000);
        // Simulating the retrieval of ebook names before and after clicking
        List<String> afterClickingEbook = collectEbookListData();

        if (!afterClickingEbook.contains(collectEbookListData)) {
            System.out.println("✅ Ebook details do not match before and after clicking.");
        } else {
            System.out.println("❌ Ebook details match!");
            System.out.println("🔹 Before: " + afterClickingEbook + ", After: " + collectEbookListData);
        }
    }

    @Test(enabled = true, dependsOnMethods = "navigateToMyEbooks")
    private List<String> verifyDupicateInListPage() {
        new WebDriverWait(driver, Duration.ofSeconds(30));

        // Locate all ebook elements using XPath
        List<WebElement> ebookElements = driver.findElements(xpath("//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/root_rl\"]/*[@resource-id=\"com.affairscloud:id/details_rl\"]/*[@resource-id=\"com.affairscloud:id/tv_sub_courses_title\"]"));
        List<String> ebookNames = new ArrayList<>();
        Set<String> uniqueEbookNames = new HashSet<>();
        Set<String> duplicateEbookNames = new HashSet<>();

        try {
            for (WebElement ebook : ebookElements) {
                String ebookName = ebook.getText();
                if (!uniqueEbookNames.add(ebookName)) {
                    duplicateEbookNames.add(ebookName);
                }
                ebookNames.add(ebookName);
            }

            System.out.println("List of Ebook Names: " + ebookNames);

            if (!duplicateEbookNames.isEmpty()) {
                System.out.println("Duplicate Ebook Names Found: " + duplicateEbookNames);
            } else {
                System.out.println("No duplicate ebooks found.");
            }

        } catch (NoSuchElementException e) {
            System.out.println("Error in collectEbookListData: " + e.getMessage());
        }
        return ebookNames;
    }


}