package Setup;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static io.appium.java_client.AppiumBy.id;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

public class BaseActions extends Base {


    public BaseActions(AndroidDriver driver) {
        Base.driver = driver;
    }

    // Common Click Action From Here we are calling Click action to all
    protected void clickElement(By locator) {
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );
        WebElement clickAction = wait.until ( ExpectedConditions.elementToBeClickable ( locator ) );
        clickAction.click ();
    }

    // Common GetText Action From Here we are calling getText action to all
    protected String getText(By locator) {
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );
        WebElement gettingText = wait.until ( ExpectedConditions.elementToBeClickable ( locator ) );
        String text = gettingText.getText ();
        System.out.println ( "Text Name : " + text );
        return text;
    }

    protected String enteringText(By locator) {
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 40 ) );
        WebElement enteringText = wait.until ( ExpectedConditions.elementToBeClickable ( locator ) );
        enteringText.sendKeys ( "Thanks for the valuable content" );
        return "";
    }

    /**
     * Scrolls the list to the bottom and then back to the top.
     */
    public void scrollList() throws InterruptedException {
        Thread.sleep ( 3000 );
        scrollToEnd ();
        scrollToBeginning ();
    }

    /**
     * Scrolls the list to the bottom.
     */
    protected void scrollToEnd() {
        try {
            // Scroll to the bottom of the list using UiScrollable
            WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );
            WebElement scrolldown = driver.findElement ( new AppiumBy.ByAndroidUIAutomator ( "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(30)" ) );
            System.out.println ( "Successfully scrolled to the bottom of the List page." );
        } catch (Exception e) {
            System.out.println ( "Error while scrolling down" );
        }
    }

    /**
     * Scrolls the list back to the top.
     */
    protected void scrollToBeginning() {
        try {
            // Scroll to the top of the list using UiScrollable
            WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );
            WebElement scrolltop = driver.findElement ( new AppiumBy.ByAndroidUIAutomator ( "new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(30)" ) );
            System.out.println ( "Successfully scrolled back to the top of the List page." );
        } catch (Exception e) {
            System.out.println ( "Error while scrolling to the initial position" );
        }
    }

    public void threeDotsActions() throws InterruptedException {

        System.out.println ( "Clicking three dots..." );
        threedots (); /* Calling The Threedots Method*/

        System.out.println ( "Sharing..." );
        share (); /* Calling The Share Method */

        System.out.println ( "Clicking three dots..." );
        threedots (); /* Calling The Threedots Method*/

        System.out.println ( "Reporting..." );
        report (); /* Calling The Report Method */

        System.out.println ( "Canceling..." );
        cancel (); /* Calling The Cancel Method */

        System.out.println ( "Clicking three dots..." );
        threedots (); /* Calling The Threedots Method*/

        System.out.println ( "Reporting..." );
        report (); /* Calling The Report Method */

        System.out.println ( "Generating reports..." );
        reportButton (); /* Calling The Report Method */

        System.out.println ( "Clicking three dots..." );
        threedots ();  //Calling The Threedots Method

        System.out.println ( "Removing saved item..." );
        removedSaved (); // Calling The Remove Method

    }

    protected void threedotsTabAction(){
        WebDriverWait wait = new WebDriverWait ( driver, Duration.ofSeconds ( 30 ) );
        clickElement ( By.xpath ( "(//android.widget.ImageView[@resource-id=\"com.affairscloud:id/ivMore\"])[1]" ) );
    }
    protected void threedotsAction(){
        threedotsTabAction();
        removedSaved ();
        threedotsTabAction();
        share ();
        threedotsTabAction();
        report ();
        reportButton ();
    }

    @Test(enabled = true)
    public void threedots() throws InterruptedException {
        // Click the three dots menu using ID: com.affairscloud:id/iv_more

        /*First*/
        try {
            // Attempt to click the first back button using its ID
            Thread.sleep ( 5000 );
            WebElement threeDots1 = driver.findElement ( By.id ( "com.affairscloud:id/iv_more" ) );
            if (threeDots1.isDisplayed ()) {
                threeDots1.click ();
                System.out.println ( "Clicked the first three dots button." );
                return; // Exit after clicking the first button
            }
        } catch (NoSuchElementException e) {
            System.out.println ( "First first three dots button not found. Trying the second three dots button..." );
        }

        /*Second*/
        try {
            // Attempt to click the first back button using its ID
            Thread.sleep ( 5000 );
            WebElement threeDots2 = driver.findElement ( By.id ( "com.affairscloud:id/more_btn" ) );
            if (threeDots2.isDisplayed ()) {
                threeDots2.click ();
                System.out.println ( "Clicked the second three dots button." );
                // Exit after clicking the second button
            }
        } catch (NoSuchElementException e) {
            System.out.println ( "First back button not found. Trying the second back button..." );
        }

    }

    /**
     * Clicks the remove form my-ebooks.
     */
    public void removedSaved() {
        clickElement ( xpath ( "(//*[@resource-id=\"com.affairscloud:id/content\"])[1]" ) );
        System.out.println ( "Successfully Click the save or remove button" );
    }

    /**
     * Clicks the share button and then cancels the share action.
     */
    public void share() {
        // Click the "Share" button using XPath: //*[@text="Share"]
        new WebDriverWait ( driver , Duration.ofSeconds ( 50 ) );
        clickElement ( xpath ( "//*[@text=\"Share\"]" ) );
        System.out.println ( "Successfully Clicked The Share Button" );

        commonCancel();
        System.out.println ( "Successfully Clicked The Cancel Buttons In The three Dots" );
    }

    /**
     * Reports an issue with the ebook by selecting a checkbox, entering text, and canceling the action.
     */
    public void report() {
        // Click the "Report" button using XPath: //*[@text="Report"]
        clickElement ( xpath ( "//*[@text=\"Report\"]" ) );

        // Click the second checkbox using XPath: (//*[@resource-id="com.affairscloud:id/cbReport"])[2]
        clickElement ( xpath ( "(//*[@resource-id=\"com.affairscloud:id/cbReport\"])[2]" ) );

        // Enter text in the text field using ClassName: android.widget.EditText
        enteringText ( By.className ( "android.widget.EditText" ) );
        System.out.println ( "Successfully Clicked The Report send, Checkbox" );

    }

    public void commonCancel() {
        // Click the "Cancel" button using XPath: //*[@text="Cancel"]
        clickElement ( xpath ( "//*[@text=\"Cancel\"]" ) );
    }

    public void cancel() {
        // Click the "Cancel" button using ID: com.affairscloud:id/tv_cancel
        clickElement ( id ( "com.affairscloud:id/tv_cancel" ) );
        System.out.println ( "Successfully Clicked The Cancel Button" );

    }

    public void reportButton() {

        new WebDriverWait ( driver , Duration.ofSeconds ( 40 ) );

        // Click the "Cancel" button using ID: com.affairscloud:id/tv_cancel
        clickElement ( id ( "com.affairscloud:id/tv_report" ) );
        System.out.println ( "Successfully Clicked The Report Button" );

    }

    public void reportAction() {
        report();
        reportButton();
    }

    public void footerCommonActions() {
        // Perform the "Like" action
        performLikeAction (); // This method handles liking the content (e.g., a post or ebook)
        // Perform the "Comment" action
        performCommentAction (); // This method allows the user to add a comment to the content

        // Perform the "Reply" action if the reply button is present
        performReplyActionIfPresent (); // This method checks for the presence of a reply button and performs the reply action if available

        // Navigate back to the previous screen
        WebDriverWait wait  = new WebDriverWait ( driver, Duration.ofSeconds ( 30 ) );
        navigateBack (); // This method navigates the user back to the previous screen or page

        // Call the share function
        share (); // This method handles sharing the content with others (e.g., via social media or messaging apps)
    }

    private void performLikeAction() {
        // Click the "Like" button using its ID
        clickElement ( id ( "com.affairscloud:id/like_layout" ) );
    }

    protected void performCommentAction() {
        // Click the "Comment" button using its ID
        clickElement ( id ( "com.affairscloud:id/comment_layout" ) );

        // Enter text into the comment input field using its class name
        enteringText ( className ( "android.widget.EditText" ) );

        // Click the "Send" button using its ID to post the comment
        clickElement ( id ( "com.affairscloud:id/iv_send" ) );
    }

    protected void performReplyActionIfPresent() {
        // Locate the "Reply" button using its XPath
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );
        WebElement clickingReply = driver.findElement ( xpath ( "(//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_replay\"])[1]" ) );

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
        } catch (NoSuchElementException | InterruptedException e) {
            System.out.println ( "There is no reply button" );
        }
    }

    protected void navigateBack() {
        try {
            // Attempt to click the first back button using its ID
            WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 40 ) );
            WebElement backButton1 = driver.findElement ( By.id ( "com.affairscloud:id/btn_back" ) );
            if (backButton1.isDisplayed ()) {
                new WebDriverWait ( driver , Duration.ofSeconds ( 40 ) );
                backButton1.click ();
                System.out.println ( "Clicked the first back button." );
                return; // Exit after clicking the first button
            }
        } catch (NoSuchElementException e) {
            System.out.println ( "First back button not found. Trying the second back button..." );
        }

        try {
            // Attempt to click the second back button using its XPath
            WebElement backButton2 = driver.findElement ( By.xpath ( "//*[@resource-id=\"com.affairscloud:id/img_back_press\"]" ) );
            if (backButton2.isDisplayed ()) {
                WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 40 ) );
                backButton2.click ();
                System.out.println ( "Clicked the second back button." );
                return; // Exit after clicking the second button
            }
        } catch (NoSuchElementException e) {
            System.out.println ( "Second back button not found." );
        }



        // If neither button is found or clickable, log an error
        System.out.println ( "Error: Neither back button could be clicked." );
    }


    public void clickMenu() throws InterruptedException {
        Thread.sleep ( 5000 ); // Adding a delay for stability, consider using WebDriverWait for better synchronization
        System.out.println ( "Attempting to open the Menu..." );

        WebElement menuButton = driver.findElement ( By.xpath ( "//android.widget.ImageButton[@resource-id='com.affairscloud:id/menu_btn']" ) );
        System.out.println ( "Attempting to open the Menu..." );

        if (menuButton.isDisplayed ()) {
            menuButton.click ();
            System.out.println ( "Successfully clicked the Menu button." );
        } else {
            System.out.println ( "Error: Menu button is not displayed on the screen or already clicked." );
        }
    }


}
