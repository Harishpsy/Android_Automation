package Menu.MyPoints;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class myPoints extends BaseActions {

    public myPoints(AndroidDriver driver) {
        super(driver);
    }

    public void navigateToMyPoints() throws InterruptedException {
        clickMenu();
        test.log(Status.PASS, "Successfully Clicked Menu",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked Menu")).build());
        myPointsActions();
    }

    protected void myPointsActions() throws InterruptedException {
        clickMyPoints();
        clickViewEarnedPoints();
        scrollList();
        navigatingBack();
    }

    private void clickMyPoints() {
        clickElement(By.id("com.affairscloud:id/tv_point"));
        test.log(Status.PASS, "Successfully Clicked The My Points Button In The Menu",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked My Points")).build());
        System.out.println("Successfully Clicked The My Points Button In The Menu");
    }

    public void clickViewEarnedPoints() throws InterruptedException {
        clickElement(By.id("com.affairscloud:id/tv_view_earn_points"));
        test.log(Status.PASS, "Successfully Clicked The View Earned Points Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked View Earned Points")).build());
        System.out.println("Successfully Clicked The View Earned Points Button");

        scrollList();
        gettingDates();
        navigateBack();
    }

    public void navigatingBack() {
        clickElement(By.id("com.affairscloud:id/iv_back"));
        test.log(Status.PASS, "Successfully Clicked The Back Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked Back Button")).build());
        System.out.println("Successfully Clicked The Back Button");
    }

    protected String gettingDates() {
        try {
            List<WebElement> dateElements = driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/tv_date\"]"));

            test.log(Status.INFO, "Found " + dateElements.size() + " date elements",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Date Elements List")).build());

            for (WebElement dateElement : dateElements) {
                String dateText = dateElement.getText();
                System.out.println(dateText);
                test.log(Status.INFO, "Date: " + dateText);
            }
            return "";
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to get dates: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed to Get Dates")).build());
            return "";
        }
    }
}