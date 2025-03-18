package Homepage.Notification;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class notification extends BaseActions {


    public notification(AndroidDriver driver) {
        super ( driver );
    }

    public void performingNotificationActions() {
        clickNotification();
        clickPromotion();
        clickGeneral();
        navigateBack ();
    }

    private void clickNotification(){
        clickElement ( By.id ( "com.affairscloud:id/iv_notification" ) );
        System.out.println ("Successfully Clicked The Notificatio Icon ");
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
