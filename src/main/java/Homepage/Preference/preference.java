package Homepage.Preference;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class preference extends BaseActions {

    public preference(AndroidDriver driver) {
        super(driver);
    }

    public void navigateToPreference() {
        try {
            clickPreference();
            close();
            clickPreference();
            clickDropDown();
            clickPreference();
            clickDropDowns();
            clickPreference();
            clickAddPrefrence();
            clickingPreferenceName();
            navigateBack();
            clickAddPrefrence();
            clickingPreferenceName();
            savePreference();

            test.log(Status.PASS, "All preference actions completed successfully",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PreferenceFlowCompleted")).build());

        } catch (Exception e) {
            test.log(Status.FAIL, "Preference flow failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PreferenceFlowFailed")).build());
            throw e;
        }
    }

    protected void clickPreference() {
        clickElement(By.id("com.affairscloud:id/toolbar_title"));
        test.log(Status.PASS, "Preference button clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PreferenceClicked")).build());
        System.out.println("Successfully Clicked the Preference Button");
    }

    protected void clickDropDown() {
        clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/menu_title\" and @text=\"Railway\"]"));
        test.log(Status.PASS, "Railway dropdown clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("RailwayClicked")).build());
        System.out.println("Successfully Clicked The Railway Button");
    }

    protected void clickDropDowns() {
        clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/menu_title\" and @text=\"Bank & Insurance\"]"));
        test.log(Status.PASS, "Bank & Insurance dropdown clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("BankInsuranceClicked")).build());
        System.out.println("Successfully Clicked The Bank & Insurance Button");
    }

    protected void clickAddPrefrence() {
        clickElement(By.id("com.affairscloud:id/btn_change_preference"));
        test.log(Status.PASS, "Add Preference button clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("AddPreferenceClicked")).build());
        System.out.println("Successfully Clicked The Add Preference Button");
    }

    protected void close() {
        clickElement(By.id("com.affairscloud:id/iv_close"));
        test.log(Status.PASS, "Close icon clicked",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("CloseClicked")).build());
        System.out.println("Successfully Clicked The close Icon");
    }

    protected void clickingPreferenceName() {
        clickElement(By.xpath("(//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/checkbox\"])[2]"));
        test.log(Status.PASS, "Preference name selected",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PreferenceNameSelected")).build());
        System.out.println("Successfully Clicked The Preference Name");
    }

    protected void savePreference() {
        clickElement(By.id("com.affairscloud:id/btn_save_preference"));
        test.log(Status.PASS, "Preferences saved",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("PreferencesSaved")).build());
        System.out.println("Successfully Clicked The Save Preference");
    }
}