package Homepage.Ebooks;

import Menu.MyEbooks.myEbooks;
import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ebooks extends BaseActions {

    private myEbooks myEbookActions;
    private static final String EBOOK_XPATH = "//android.widget.TextView[contains(@text, 'Ebook')][1]//following::*[@resource-id='com.affairscloud:id/iv_article'][1]";
    private static final int MAX_SCROLL_ATTEMPTS = 15;

    public ebooks(AndroidDriver driver) {
        super ( driver );
        myEbookActions = new myEbooks ( driver );
    }

    public void performingEbooksActions() throws InterruptedException {
        Thread.sleep(5000); // Reduce wait time
        clickingEbook();  // Directly try to find & click the Ebook
        myEbookActions.performEbookActions();
        scrollToBeginning ();
    }

    private void clickingEbook() throws InterruptedException {
        int attempts = 0;

        while (attempts < MAX_SCROLL_ATTEMPTS) {
            try {
                WebElement element = driver.findElement ( By.xpath ( EBOOK_XPATH ) );
                element.click ();
                test.log ( Status.PASS , "Ebook clicked successfully" ,
                        MediaEntityBuilder.createScreenCaptureFromBase64String ( captureScreenshot ( "EbookClicked" ) ).build () );
                System.out.println ( "Ebook clicked successfully." );
                return; // Exit method after successful click
            } catch (NoSuchElementException e) {
                test.log ( Status.INFO , "Ebook not found, scrolling... Attempt: " + (attempts + 1) );
                System.out.println ( "Ebook not found, scrolling... Attempt: " + (attempts + 1) );
                scrollDown(); // Scroll down dynamically
                attempts++;
            }
        }

        test.log ( Status.WARNING , "Ebook not found after " + MAX_SCROLL_ATTEMPTS + " scroll attempts" );
        System.out.println ( "Ebook not found after " + MAX_SCROLL_ATTEMPTS + " scroll attempts." );
    }
}

