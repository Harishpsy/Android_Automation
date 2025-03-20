package AllCourse;

import Setup.BaseActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.By.xpath;

public class allCoursetab extends BaseActions {

    public allCoursetab(AndroidDriver driver){
        super(driver);
    }

    public void performingAllCourseActions() {
        clickingAllCoursetab();
//        getCourseNames ();
//        scrollToBeginning ();
//        verifyDuplicateCourseNames();
    }

    private void clickingAllCoursetab(){
        clickElement ( By.id ( "com.affairscloud:id/tab_all_course" ) );
        System.out.println ("Successfully Clicked The All Course Tab");
    }

    // Getting The All Course Names
    private int getCourseNames() {
        Set<String> seenCourses = new HashSet<>(); // To track unique course names
        int uniqueCourseCount = 0;

        while (true) {
            List<WebElement> courseElements = driver.findElements( xpath("//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\"]"));
            boolean newDataFound = false;

            for (WebElement courseElement : courseElements) {
                String courseName = courseElement.getText();

                if (seenCourses.add(courseName)) { // Only adds if it's not already present
                    System.out.println("Course Name: " + courseName);
                    newDataFound = true;
                    uniqueCourseCount++;
                }
            }

            if (!newDataFound) {
                break; // If no new data is found after scrolling, exit the loop
            }

            scrollDown();
        }

        // Print the count of unique courses
        System.out.println("Total unique courses: " + uniqueCourseCount);
        return uniqueCourseCount;
    }

    private void verifyDuplicateCourseNames() {
        // Finding All The elements Based On The Xpath (Ebooks, Article, Videos, Quiz, My Courses, All Courses)
        try {
            // Retrieve the list of elements for the categories (Ebooks, Article, Videos, Quiz, My Courses, All Courses)
            List<WebElement> allName = driver.findElements ( xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\"]" ) );

            // Check if the list is empty
            if (allName.isEmpty ()) {
                System.out.println ( "No elements found  in the list page" );
            } else {
                // Create a set to store unique names
                Set<String> uniqueNames = new HashSet<> ();

                // Iterate through the list of elements
                for (WebElement element : allName) {
                    String actualName = element.getText ();

                    // Check if the name is already in the set (duplicate)
                    if (uniqueNames.contains ( actualName )) {
                        System.out.println ( "Duplicate Found In The List Page: " + actualName );
                    } else {
                        uniqueNames.add ( actualName );
                        System.out.println ( "There is no duplicate in the course list page" );
                        break; // If no duplicate found, break the loop
                    }
                }

            }
        } catch (NoSuchElementException e) {
            System.out.println ( "The Course elements were not found." );
        }

    }

    private void scrollDown () {
            driver.findElement ( new AppiumBy.ByAndroidUIAutomator (
            "new UiScrollable(new UiSelector().scrollable(true)).scrollForward();" ) );
    }


}
