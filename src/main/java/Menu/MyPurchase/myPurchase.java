package Menu.MyPurchase;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class myPurchase extends BaseActions {

    public myPurchase(AndroidDriver driver){
        super(driver);
        driver.manage ().timeouts ().implicitlyWait ( 10, TimeUnit.MILLISECONDS );
    }

    public void navigateToMyPurchase() throws InterruptedException {
        clickMenu ();
        performingMyPurchaseActions();
    }

    protected void performingMyPurchaseActions() throws InterruptedException {
        clickMyPurchase();
        scrollList ();
        performingNothificationActions();
        Thread.sleep ( 3000 );
        navigateBack ();

    }
    protected void clickMyPurchase(){
        clickElement ( By.id ( "com.affairscloud:id/tv_my_purchase" ) );
        System.out.println ("Successfully Clicked The My Purchase Button In The  Menu");
    }

    protected void performingNothificationActions() {
        clickNotification();
        clickPromotion();
        clickGeneral();
        navigateBack ();
    }

    private void clickNotification(){
        clickElement ( By.id ( "com.affairscloud:id/iv_notification" ) );
        System.out.println ("Successfully Clicked The Notification Icon ");
    }

    private void clickPromotion() {
        clickElement ( By.xpath ( "//*[@resource-id=\"com.affairscloud:id/tabTitle\" and @text=\"PROMOTION\"]" ) );
        System.out.println ("Successfully Clicked The Promotion");
    }

    private void clickGeneral() {
        clickElement ( By.xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/tabTitle\" and @text=\"GENERAL\"]" ) );
        System.out.println ("SuccessFully Clicked The General");
    }



}
