package Menu.Mycoins;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class myCoin extends BaseActions {

    /*Creating An Constructor*/
    public myCoin(AndroidDriver driver) {
        super ( driver );
    }

    public void navigateToMyCoins() throws InterruptedException {

        /*Clicking The Menu Button*/
        clickMenu ();

        /*Clicking The My Coins Button*/
        clickMyCoins ();

        /*Calling The My Coin Actions Method */
        Thread.sleep ( 10000 );
        myCoinActions ();

    }

    /*Clicking My Coins Tab In The Menu*/
    private void clickMyCoins() throws InterruptedException {
        Thread.sleep ( 4000 );
        clickElement ( By.id ( "com.affairscloud:id/tv_coin" ) );
        System.out.println ( "Successfully Clicked The My Coins Button In The Side Menu" );
    }

    private void myCoinActions() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 180 ) );
        coinsEarning ();
        coinsSpent ();
        exploreProduct ();
        clickMenu ();
        clickMyCoins ();
        scrollToEnd ();
        viewFaqs ();
        scrollToBeginning ();
        navigateBack ();
    }

    private void coinsEarning() throws InterruptedException {
        clickElement ( By.xpath ( "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/rl_coinsearning\"]" ) );
        System.out.println ( "Successfully Clicked The Coins Earning Button" );
        scrollList ();
        navigateBack ();
    }

    private void coinsSpent() throws InterruptedException {
        Thread.sleep ( 90 );
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );
        clickElement ( By.xpath ( "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/rl_coinsspend\"]" ) );
        scrollList ();
        navigateBack ();
    }

    private void exploreProduct() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 40 ) );
        clickElement ( By.xpath ( "//android.widget.TextView[@text=\"EXPLORE PRODUCTS\"]" ) );
        navigateBack ();

    }

    private void viewFaqs() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 40 ) );
        clickElement ( By.xpath ( "//android.widget.TextView[@text=\"VIEW FAQs\"]" ) );
        navigateBack ();
    }

}
