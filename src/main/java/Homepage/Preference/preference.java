package Homepage.Preference;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class preference extends BaseActions {

    public preference(AndroidDriver driver) {
        super(driver);
    }

    public void navigateToPreference() {
        clickPreference();
        close();
        clickPreference();
        clickDropDown();
        clickPreference();
        clickDropDowns();
        clickPreference();
        clickAddPrefrence();
        clickingPreferenceName();
        navigateBack ();
        clickAddPrefrence();
        clickingPreferenceName();
        savePreference();
    }

    protected void clickPreference(){
        clickElement ( By.id ( "com.affairscloud:id/toolbar_title" ) );
        System.out.println ("Successfully Clicked the Preference Button");
    }

    protected void clickDropDown(){
        clickElement ( By.xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/menu_title\" and @text=\"Railway\"]" ) );
        System.out.println ("Successfully Clicked The Railway Button");
    }

    protected void clickDropDowns(){
        clickElement ( By.xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/menu_title\" and @text=\"Bank & Insurance\"]" ) );
        System.out.println ("Successfully Clicked The Bank & Insurance Button");
    }

    protected void clickAddPrefrence() {
        clickElement ( By.id ( "com.affairscloud:id/btn_change_preference" ) );
        System.out.println ("Successfully Clicked The Add Preference Button");
    }

    protected void close(){
        clickElement ( By.id ( "com.affairscloud:id/iv_close" ) );
        System.out.println ("Successfully Clicked The close Icon");
    }

    protected void clickingPreferenceName(){
        clickElement ( By.xpath ( "(//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/checkbox\"])[2]" ) );
        System.out.println ("Successfully Clicked The Preference Name");
    }

    protected void savePreference(){
        clickElement ( By.id ( "com.affairscloud:id/btn_save_preference" ) );
        System.out.println ("Successfully Clicked The Save Preference");
    }
}
