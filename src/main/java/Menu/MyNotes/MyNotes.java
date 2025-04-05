package Menu.MyNotes;

import Setup.Base;
import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class MyNotes extends BaseActions {

    private final Video_Module videoModule;
    private final Article_Module articleModule;
    private final Quiz_Module quizModule;

    public MyNotes(AndroidDriver driver) {
        super(driver);
        Base.driver = driver;
        videoModule = new Video_Module(driver);
        articleModule = new Article_Module(driver);
        quizModule = new Quiz_Module(driver);
    }

    @Test
    public void navigateToMyNotes() throws InterruptedException {
        try {
            /* Calling the clickMenu Method */
            // clickMenu();
            test.log(Status.PASS, "Successfully Clicked The Menu Button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked Menu Button")).build());
            System.out.println("Successfully Clicked The Menu Button");

            /* Clicking The My Notes Button In The Menu */
            clickElement(By.id("com.affairscloud:id/item_my_notes"));
            test.log(Status.PASS, "Successfully Clicked The My Notes Button In The Side Menu",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked My Notes")).build());
            System.out.println("Successfully Clicked The My Notes Button In The Side Menu");

            /* Calling The My Notes Actions Method */
            myNotesActions();
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to navigate to My Notes: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Navigate My Notes")).build());
            throw e;
        }
    }

    public void myNotesActions() throws InterruptedException {
        try {
            WebElement noData = driver.findElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/no_data\"]"));

            if (noData.isDisplayed()) {
                test.log(Status.INFO, "No data was present in the list page so navigating back",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Data Found")).build());
                System.out.println("No data was present in the list page so navigating back");
                navigateBack();
            }
        } catch (NoSuchElementException e) {
            /* Calling The Video Module */
            setVideoModule();

            /* Calling The Article Method */
            setArticleModule();

            // /* Calling The Quiz Module */
            // setQuizModule();

            navigateBack();
        }
    }

    public void setVideoModule() throws InterruptedException {
        try {
            /* Calling The play Button Method and Course Title Method */
            videoModule.performVideoActions();
            test.log(Status.PASS, "Successfully performed video actions",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Video Actions Completed")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform video actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Video Actions")).build());
            throw e;
        }
    }

    public void setArticleModule() throws InterruptedException {
        try {
            /* Calling The Article Method and Course Title Method */
            articleModule.performArticleActions();
            test.log(Status.PASS, "Successfully performed article actions",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Article Actions Completed")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform article actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Article Actions")).build());
            throw e;
        }
    }

    public void setQuizModule() throws InterruptedException {
        try {
            /* Calling The Quiz Method and Course Title Method */
            quizModule.performQuizActions();
            test.log(Status.PASS, "Successfully performed quiz actions",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Quiz Actions Completed")).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform quiz actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Quiz Actions")).build());
            throw e;
        }
    }

}