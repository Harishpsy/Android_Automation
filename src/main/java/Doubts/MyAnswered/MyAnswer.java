package Doubts.MyAnswered;

import Doubts.AllDoubts.AllDoubts;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyAnswer extends BaseActions {

    private AllDoubts AllDoubtsModule;

    public MyAnswer (AndroidDriver driver){
        super(driver);
        AllDoubtsModule = new AllDoubts ( driver );
    }

    public void performMyAnswerActions() throws InterruptedException {
        clickingMyAnswer();
        driver.hideKeyboard ();
        myAnswerAction();
        gettingUserNameInAnswer();
        scrollToBeginning ();

    }

    private void clickingMyAnswer() {
        clickElement ( By.xpath ( "//android.widget.LinearLayout[@content-desc=\"My Answered\"]" ) );
    }

    private void myAnswerAction(){
       AllDoubtsModule.clickingAnswer();
    }

//    private void gettingUserNameInAnswer() {
//        // Get usernames from All Doubts section
//        List<WebElement> userNameElements = driver.findElements( By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/user_details\"]" + "/child::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/txt_user_name\"]"));
//
//        for (WebElement userNameElement : userNameElements) {
//            String AllDoubtsUserName = userNameElement.getText();
//            System.out.println ("My Doubts User Name: " + AllDoubtsUserName);
//
//        }
//    }

    protected int gettingUserNameInAnswer() throws InterruptedException {
        Set<String> seenCourses = new HashSet<> (); // To track unique Article names
        int uniqueCourseCount = 0;
        int scrollCount = 0; // Counter to track the number of scrolls

        while (scrollCount < 5) { // Scroll exactly five times
            List<WebElement> userNameElements = driver.findElements( By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/user_details\"]" + "/child::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/txt_user_name\"]"));
            boolean newDataFound = false;

            for (WebElement courseElement : userNameElements) {
                String UserName = courseElement.getText();

                if (seenCourses.add(UserName)) { // Only adds if it's not already present
                    System.out.println("User Name In The Answer: " + UserName);
                    newDataFound = true;
                    uniqueCourseCount++;
                }
            }

            if (!newDataFound) {
                break; // If no new data is found after scrolling, exit the loop
            }

            scrollDown();
            scrollCount++; // Increment the scroll counter
            System.out.println("No. Of Times Scrolled: " + scrollCount);

            // Add a small delay to allow new content to load
            Thread.sleep(1000); // Adjust the delay as needed
        }

        // Print the count of unique courses
        System.out.println("Total unique Ebooks Names: " + uniqueCourseCount);
        return uniqueCourseCount;
    }












}
