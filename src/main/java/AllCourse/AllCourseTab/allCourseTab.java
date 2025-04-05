package AllCourse.AllCourseTab;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class allCourseTab extends BaseActions {

    public allCourseTab(AndroidDriver driver){
        super(driver);
    }
    public void clickingAllCoursetab(){
        clickElement ( By.id ( "com.affairscloud:id/tab_all_course" ) );
        test.log( Status.PASS, "Successfully Clicked The All Course Tab",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked The All Course Tab")).build());
        System.out.println ("Successfully Clicked The All Course Tab");
    }
}
