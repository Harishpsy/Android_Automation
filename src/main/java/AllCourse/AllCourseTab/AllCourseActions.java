package AllCourse.AllCourseTab;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.By.xpath;

public class AllCourseActions extends BaseActions {

    private static final String COURSE_TITLE_XPATH = "//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\"]";

    public AllCourseActions(AndroidDriver driver) {
        super(driver);
    }

    public void performingAllCourseActions() throws InterruptedException {
        getCourseNames();
        scrollToBeginning();
        verifyDuplicateCourseNames();
    }

    // Getting all course names
    private int getCourseNames() throws InterruptedException {
        Set<String> seenCourses = new HashSet<>(); // To track unique course names
        int uniqueCourseCount = 0;

        while (true) {
            List<WebElement> courseElements = driver.findElements(xpath(COURSE_TITLE_XPATH));
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
        try {
            List<WebElement> allCourseElements = driver.findElements(xpath(COURSE_TITLE_XPATH));

            if (allCourseElements.isEmpty()) {
                System.out.println("No course elements found on the list page.");
                return;
            }

            Set<String> uniqueNames = new HashSet<>();
            boolean duplicateFound = false;

            for (WebElement element : allCourseElements) {
                String courseName = element.getText();

                if (!uniqueNames.add(courseName)) { // If the name is already in the set, it's a duplicate
                    System.out.println("Duplicate Found In The List Page: " + courseName);
                    duplicateFound = true;
                }
            }

            if (!duplicateFound) {
                System.out.println("No duplicates found in the course list page.");
            }

        } catch (NoSuchElementException e) {
            System.out.println("The course elements were not found: " + e.getMessage());
        }
    }
}