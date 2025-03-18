package Menu.AppSettings;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class appSetting extends BaseActions {

    public appSetting(AndroidDriver driver) {
        super ( driver );
    }

    public void navigateToAppSettings() throws InterruptedException {

        clickMenu ();
        performingAppSettingsActions();

    }

     protected void performingAppSettingsActions() throws InterruptedException {
         clickAppSettings();
         clickNotificationtoogle();
         clickNotificationSoundtoogle();
         termsAndConditions();
         privacyPolicy();
//         logout();
         navigateBack ();
     }

     private void clickAppSettings() {
        clickElement ( By.id ( "com.affairscloud:id/item_app_setting" ) );
        System.out.println ( "Successfully Clicked The App Settings Button" );
     }

     private void clickNotificationtoogle(){
        clickElement ( By.xpath ( "//*[@resource-id=\"com.affairscloud:id/rl_notification\"]//child::*[@resource-id=\"com.affairscloud:id/tg_notification\"]" ) );
        System.out.println ("Successfully Clicked The Notification Toggle");
     }

     private void clickNotificationSoundtoogle() {
        clickElement ( By.xpath ( "//*[@resource-id=\"com.affairscloud:id/rl_notification_sound\"]//child::*[@resource-id=\"com.affairscloud:id/tg_notification_sound\"]" ) );
        System.out.println ( "Successfully Clicked The Notification Sound Toggle" );
     }

     private void termsAndConditions() throws InterruptedException {
        clickElement ( By.id ( "com.affairscloud:id/tv_terms" ) );
        System.out.println ( "Successfully Clicked The Terms And Conditions" );
        Thread.sleep ( 3000 );
        navigateBack ();
     }

    private void privacyPolicy() throws InterruptedException {
        clickElement ( By.id ( "com.affairscloud:id/tv_privacy" ) );
        System.out.println ( "Successfully Clicked The Privacy Policy" );
        Thread.sleep ( 3000 );
        navigateBack ();
    }

    private void logout() {

        clickElement ( By.id ( "com.affairscloud:id/tv_logout" ) );
        System.out.println ("Successfully Clicked The Logout Button");
    }












}
