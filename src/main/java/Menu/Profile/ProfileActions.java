package Menu.Profile;

import Setup.Base;
import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ProfileActions extends BaseActions {

    private static Map<String, String> userDetailsBefore;

    public ProfileActions(AndroidDriver driver) {
        super ( driver );
        Base.driver = driver;
    }

    public void performingProfile() throws InterruptedException {
        usernameedit ();
        userDetails();
    }

    @Test(enabled = true)
    public Object userDetails() throws InterruptedException {
        String userName = getElementText ( By.id ( "com.affairscloud:id/nav_user_first_name" ) );
        String mailId = getElementText ( By.id ( "com.affairscloud:id/nav_email" ) );

        System.out.println ( "Login User Name: " + userName );
        System.out.println ( "Login User Mail Id: " + mailId );

        System.out.println ( "---------Successfully Completed the Edit Profile----------" );
        return null;
    }

    @Test(enabled = true)
    public void usernameedit() throws InterruptedException {
        editProfile ();
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );

        userprofiledetails ();

        //Cancel Button
        clickElement ( By.id ( "com.affairscloud:id/btn_cancel" ) );
        test.log( Status.PASS, "Successfully Clicked the Cancel Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked the Cancel Button")).build());
        System.out.println ( "Successfully Clicked the Cancel Button" );

        wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );
        editProfile ();


        wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );

        userprofiledetails ();
        captureUserDetailsBefore ();

        //Submit button
        clickElement ( By.id ( "com.affairscloud:id/btn_submit" ) );
        test.log( Status.PASS, "Successfully Clicked the Submit Button",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked the Submit Button")).build());
        System.out.println ( "Successfully Clicked the Submit Button" );

        wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );
        clickMenu ();
        test.log( Status.PASS, "Successfully Clicked Menu",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked Menu")).build());

        editProfile ();
        test.log( Status.PASS, "Successfully Clicked Edit Profile Again",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked Edit Profile Again")).build());
        System.out.println ( "---------------------------------------------------------" );

        wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );
        verifyUserDetailsAfter ();

        //Application Back Butoon
        clickElement ( By.id ( "com.affairscloud:id/btn_back" ) );
        test.log( Status.PASS, "Successfully Clicked The Application Back Arrow",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked The Application Back Arrow")).build());
        System.out.println ( "Successfully Fully Clicked The Application Back Arrow" );
    }

    @Test(enabled = true)
    public void editProfile() throws InterruptedException {
        clickElement ( By.id ( "com.affairscloud:id/iv_edit" ) );
        test.log( Status.PASS, "Successfully Clicked Edit Profile",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked Edit Profile")).build());
        System.out.println ( "Clicked on Edit Profile" );
    }

    @Test(enabled = true)
    public void userprofiledetails() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 50 ) );

        enterText ( By.xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_first_name\"]" ) , generateRandomFirstName () );
        enterText ( By.xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_last_name\"]" ) , generateRandomLastName () );
        enterText ( By.xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/et_mobile_number\"]" ) , generateRandomMobileNumber () );
        enterText ( By.xpath ( "//android.widget.AutoCompleteTextView[@resource-id=\"com.affairscloud:id/et_location\"]" ) , generateRandomPincode () );

        clickElement ( By.xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/et_state\"]" ) );
        test.log( Status.PASS, "Successfully Clicked State Field",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Successfully Clicked State Field")).build());

        scrollToElement ( "Not Preparing for PSC" );
    }

    @Test
    public void captureUserDetailsBefore() {
        captureUserDetails ( "before" );
    }

    @Test(dependsOnMethods = "captureUserDetailsBefore")
    public void verifyUserDetailsAfter() {
        captureUserDetails ( "after" );
    }

    private void captureUserDetails(String when) {
        Map<String, String> userDetails = new HashMap<> ();
        userDetails.put ( "First Name" , getElementText ( By.xpath ( "//android.widget.EditText[@resource-id='com.affairscloud:id/et_first_name']" ) ) );
        userDetails.put ( "Last Name" , getElementText ( By.xpath ( "//android.widget.EditText[@resource-id='com.affairscloud:id/et_last_name']" ) ) );
        userDetails.put ( "Mobile Number" , getElementText ( By.xpath ( "//android.widget.EditText[@resource-id='com.affairscloud:id/et_mobile_number']" ) ) );
        userDetails.put ( "Pincode" , getElementText ( By.xpath ( "//android.widget.AutoCompleteTextView[@resource-id='com.affairscloud:id/et_location']" ) ) );

        System.out.println ( "User Details " + when + " submit: " + userDetails );

        if (when.equals ( "before" )) {
            userDetailsBefore = new HashMap<> ( userDetails ); // Store details before submission
        } else {
            verifyUserDetails ( userDetails ); // Compare after submission
        }
    }

    private String getElementText(By locator) {
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 30 ) );
        WebElement element = wait.until ( ExpectedConditions.visibilityOfElementLocated ( locator ) );
        return element.getText ();
    }

    private void enterText(By locator , String text) {
        WebDriverWait wait = new WebDriverWait ( driver , Duration.ofSeconds ( 50 ) );
        WebElement textfield = wait.until ( ExpectedConditions.elementToBeClickable ( locator ) );
        textfield.clear ();
        wait = new WebDriverWait ( driver , Duration.ofSeconds ( 40 ) );
        textfield.sendKeys ( text );
        driver.hideKeyboard ();
        System.out.println ( "Successfully entered " + text );
    }

    private void scrollToElement(String text) {
        try {
            WebElement scroll = driver.findElement ( new AppiumBy.ByAndroidUIAutomator ( "new UiScrollable(new UiSelector().scrollable(true))" + ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))" ) );
            scroll.click ();

        } catch (NoSuchElementException e) {
            System.out.println ( e.getMessage () + "Scroll is not available In the profile " );
        }
    }

    private String generateRandomFirstName() {
        String[] firstNames = {"Hema" , "Hemavathi" , "Priya" , "Aarthi" , "Divya" , "Sneha" , "Riya" , "Meera"};
        return firstNames[new Random ().nextInt ( firstNames.length )];
    }

    private String generateRandomLastName() {
        String[] lastNames = {"CC" , "AC" , "Kumar" , "Sharma" , "Rao" , "Patel"};
        return lastNames[new Random ().nextInt ( lastNames.length )];
    }

    private String generateRandomMobileNumber() {
        String[] mobileNumbers = {"8148296804" , "9876543210" , "9123456789" , "8569741230" , "7012345678" , "8807633861"};
        return mobileNumbers[new Random ().nextInt ( mobileNumbers.length )];
    }

    private String generateRandomPincode() {
        String[] pinCodes = {"605008" , "560064" , "560065" , "560063"};
        return pinCodes[new Random ().nextInt ( pinCodes.length )];
    }

    private void verifyUserDetails(Map<String, String> userDetailsAfter) {
        if (userDetailsBefore.equals ( userDetailsAfter )) {
            System.out.println ( "✅ User details match before and after submission." );
            test.log( Status.PASS, "User details match before and after submission",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("User details match")).build());
        } else {
            System.out.println ( "❌ User details do NOT match! Differences:" );
            test.log( Status.FAIL, "User details do NOT match",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("User details mismatch")).build());
            for (String key : userDetailsBefore.keySet ()) {
                if (!userDetailsBefore.get ( key ).equals ( userDetailsAfter.get ( key ) )) {
                    System.out.println ( "🔹 " + key + " mismatch: Before [" + userDetailsBefore.get ( key ) + "], After [" + userDetailsAfter.get ( key ) + "]" );
                    test.log( Status.INFO, key + " mismatch: Before [" + userDetailsBefore.get(key) + "], After [" + userDetailsAfter.get(key) + "]");
                }
            }
        }
    }
}