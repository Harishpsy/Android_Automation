package Menu.Mycoins;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class myCoin extends BaseActions {

    public myCoin(AndroidDriver driver) {
        super(driver);
    }

    public void navigateToMyCoins() throws InterruptedException {
        try {
            /* Clicking The Menu Button */
            clickMenu();
            test.log(Status.PASS, "Successfully clicked menu button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Menu Clicked")).build());

            /* Clicking The My Coins Button */
            clickMyCoins();

            /* Calling The My Coin Actions Method */
            Thread.sleep(10000);
            myCoinActions();

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to navigate to My Coins: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("MyCoins Navigation Error")).build());
            throw e;
        }
    }

    /* Clicking My Coins Tab In The Menu */
    private void clickMyCoins() throws InterruptedException {
        try {
            Thread.sleep(4000);
            clickElement(By.id("com.affairscloud:id/tv_coin"));
            test.log(Status.PASS, "Successfully Clicked The My Coins Button In The Side Menu",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("MyCoins Clicked")).build());
            System.out.println("Successfully Clicked The My Coins Button In The Side Menu");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click My Coins button: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed MyCoins Click")).build());
            throw e;
        }
    }

    private void myCoinActions() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
            coinsEarning();
            coinsSpent();
            exploreProduct();
            clickMenu();
            clickMyCoins();
            scrollToEnd();
            viewFaqs();
            scrollToBeginning();
            navigateBack();
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform My Coins actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("MyCoins Actions Error")).build());
            throw e;
        }
    }

    private void coinsEarning() throws InterruptedException {
        try {
            clickElement(By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/rl_coinsearning\"]"));
            test.log(Status.PASS, "Successfully Clicked The Coins Earning Button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Coins Earning Clicked")).build());
            System.out.println("Successfully Clicked The Coins Earning Button");

            scrollList();
            navigateBack();
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform Coins Earning actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Coins Earning Error")).build());
            throw e;
        }
    }

    private void coinsSpent() throws InterruptedException {
        try {
            Thread.sleep(90);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            clickElement(By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/rl_coinsspend\"]"));
            test.log(Status.PASS, "Successfully Clicked The Coins Spent Button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Coins Spent Clicked")).build());

            scrollList();
            Thread.sleep(3000);
            navigateBack();
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform Coins Spent actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Coins Spent Error")).build());
            throw e;
        }
    }

    private void exploreProduct() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            clickElement(By.xpath("//android.widget.TextView[@text=\"EXPLORE PRODUCTS\"]"));
            test.log(Status.PASS, "Successfully Clicked Explore Products",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Explore Products Clicked")).build());

            Thread.sleep(3000);
            navigateBack();
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to explore products: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Explore Products Error")).build());
            throw e;
        }
    }

    private void viewFaqs() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            clickElement(By.xpath("//android.widget.TextView[@text=\"VIEW FAQs\"]"));
            test.log(Status.PASS, "Successfully Clicked View FAQs",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("FAQs Viewed")).build());

            Thread.sleep(3000);
            navigateBack();
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to view FAQs: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("FAQs View Error")).build());
            throw e;
        }
    }
}