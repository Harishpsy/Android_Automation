package Menu.MyNotes;

import Menu.menubase;
import Setup.Base;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class mynotes extends menubase {

    public mynotes(AndroidDriver driver) {
      Base.driver = driver;
    }

    public void navigateToMyNotes() throws InterruptedException {

        /*Calling the clickMenu Method*/
        clickMenu ();
        System.out.println ("Successfully Clicked The Menu Button");

        /*Clicking The My Notes Button In The Menu*/
        clickElement ( By.id ( "com.affairscloud:id/item_my_notes" ) );
        System.out.println ("Successfully Clicked The My Notes Button In The Side Menu");

    }


}
