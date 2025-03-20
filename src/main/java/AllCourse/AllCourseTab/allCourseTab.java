package AllCourse.AllCourseTab;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class allCourseTab extends BaseActions {

    public allCourseTab(AndroidDriver driver){
        super(driver);
    }
    public void clickingAllCoursetab(){
        clickElement ( By.id ( "com.affairscloud:id/tab_all_course" ) );
        System.out.println ("Successfully Clicked The All Course Tab");
    }
}
