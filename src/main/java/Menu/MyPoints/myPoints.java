package Menu.MyPoints;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class myPoints extends BaseActions {

    public myPoints(AndroidDriver driver) {
        super(driver);
    }

    public void navigateToMyPoints() throws InterruptedException {
        clickMenu ();
        myPointsActions();
    }

    protected void myPointsActions() throws InterruptedException {
        clickMyPoints();
        clickViewEarnedPoints();
        scrollList ();
        navigatingBack();
    }

    private void clickMyPoints(){
        clickElement ( By.id ( "com.affairscloud:id/tv_point" ) );
        System.out.println ("Successfully Clicked The My Points Button In The Menu ");
    }

    private void clickViewEarnedPoints() throws InterruptedException {
        clickElement ( By.id ( "com.affairscloud:id/tv_view_earn_points" ) );
        System.out.println ("Successfully Clicked The View Earned Points Button ");
        scrollList ();
        gettingDates();
        navigateBack ();
    }

    protected void navigatingBack(){
        clickElement ( By.id ( "com.affairscloud:id/iv_back" ) );
        System.out.println ("Successfully Clicked The Back Button ");
    }

    protected String gettingDates() {
        List<WebElement> dateElements = driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_date\"]"));

        for (WebElement dateElement : dateElements) {
            String dateText = dateElement.getText();
            System.out.println(dateText);  // Print the text to the console or use it as needed
        }
        return "";
    }













}
