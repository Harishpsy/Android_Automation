package Doubts.Followed;

import Doubts.AllDoubts.AllDoubts;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class followed extends BaseActions {

    private AllDoubts AllDoubtsModule;

    public followed(AndroidDriver driver){
        super(driver);

        AllDoubtsModule = new AllDoubts ( driver );
    }

    public void performFollowedAction() {
        clickingFollowed();
        noFollowedDoubt();
    }


    private void noFollowedDoubt() {

        try {

            WebElement noData = driver.findElement ( By.xpath ( "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/rl_mypost\"]" ) );

            if (noData.isDisplayed ()) {
                System.out.println ( "There Is No Data In The Followed Page" );
            }else {
                scrollList ();
                AllDoubtsModule.doubtfooterActions();
            }

        }catch (NoSuchElementException e){
            System.out.println ("Error In The Followed Page While performing Actions");
        }
    }

    private void clickingFollowed() {
        clickElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"Followed\"]" ) );
    }

}
