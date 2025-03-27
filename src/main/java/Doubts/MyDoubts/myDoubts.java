package Doubts.MyDoubts;

import Menu.AllCourse.allCourse;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class myDoubts extends BaseActions {
    private final allCourse allCourseModule;

    public myDoubts(AndroidDriver driver) {
        super(driver);
        this.allCourseModule = new allCourse(driver);
    }

    public void performMyDoubtsActions() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        clickMyDoubts();
        noFollowedDoubt();

    }
    private void noFollowedDoubt() {

        try {

            WebElement noData = driver.findElement ( By.xpath ( "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/rl_mypost\"]" ) );

            if (noData.isDisplayed ()) {
                System.out.println ( "There Is No Data In The My Doubts Page" );
            }else {
                verifyUserNameInMyDoubts();
            }

        }catch (NoSuchElementException e){
            System.out.println ("Error In The My Doubts Page While performing Actions");
        }
    }

    private void clickMyDoubts() {
        clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"My Doubts\"]"));
        System.out.println("Successfully clicked on My Doubts");
    }

    private void verifyUserNameInMyDoubts() {

        // Get usernames from My Doubts section
        List<WebElement> userNameElements = driver.findElements(
                By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/user_details\"]" +
                        "/child::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/txt_user_name\"]"));

        // Compare stored username with each username found in My Doubts
        for (WebElement userNameElement : userNameElements) {
            String myDoubtsUserName = userNameElement.getText();
            System.out.println ("My Doubts User Name: " + myDoubtsUserName);

            if (myDoubtsUserName.equals ( allCourseModule.userName )) {
                System.out.println("VERIFICATION SUCCESS: User names match");
            }else {
                System.out.println("VERIFICATION Error: User names not match");
            }
        }
    }
}