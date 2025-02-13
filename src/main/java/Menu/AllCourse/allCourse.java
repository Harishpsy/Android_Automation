package Menu.AllCourse;

import Menu.menubase;
import Setup.Base;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;

import static org.openqa.selenium.By.id;


public class allCourse  extends menubase {

    public allCourse(AndroidDriver driver) {
        Base.driver = driver;
    }
@Test
    public void  clickingAllCourse() throws InterruptedException {
        //Clicking the Menu button
        clickMenu();

        clickElement ( id ( "com.affairscloud:id/item_all_courses" ));
        System.out.println ("Successfully clicked the All Course button In THe Side Menu");

        //Application Back Button
        clickElement ( id ( "com.affairscloud:id/img_back_press" ) );
        System.out.println ("Successfully Fully Clicked The Application Back Arrow");
    }

}
