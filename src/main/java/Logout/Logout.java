package Logout;

import Menu.AppSettings.appSetting;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;

import static org.openqa.selenium.By.*;

public class Logout extends BaseActions {

    private appSetting AppSettingsModule;

    public Logout (AndroidDriver driver) {
        super (driver);
        AppSettingsModule = new appSetting ( driver );
    }

    public void performLogoutActions() throws InterruptedException {
        clickMenu ();
        AppSettingsModule.clickAppSettings();
        logout();
    }
    private void logout() {
        clickElement ( id ( "com.affairscloud:id/tv_logout" ) );
        System.out.println ("Successfully Clicked The Logout Button");
    }
}
