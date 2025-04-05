package Menu.MyNotes;

import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Quiz_Module extends BaseActions {

    public Quiz_Module(AndroidDriver driver) {
        super(driver);
    }

    public void performQuizActions() {
        try {
            WebElement quizCard = driver.findElement(By.id("com.affairscloud:id/card_view_quiz"));
            test.log(Status.PASS, "Quiz was present in the list page",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Quiz Found")).build());
            System.out.println("Quiz was present in the list page");

            if (quizCard.isDisplayed()) {
                Thread.sleep(4000);

                /* Clicking The Course Title Method */
                clickCourseTitle();

                /* Getting The Quiz Name in the list page before click */
                storeQuizNameBeforeClick();

                /*                *//*Calling The Three Dots Actions Method*//*
                threeDotsActions ();*/

                /*                *//*Calling The Footer Common Actions Method*//*
                footerCommonActions ();*/

                /* Quiz Actions */
                quizActions();

            } else {
                test.log(Status.FAIL, "Error While executing the quiz actions",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Quiz Display Error")).build());
                System.out.println("Error While executing the quiz actions");
            }

        } catch (Exception e) {
            test.log(Status.INFO, "There is no quiz in the list page",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("No Quiz Found")).build());
            System.out.println("There is no quiz in the list page");
        }
    }

    private void clickCourseTitle() throws InterruptedException {
        try {
            clickElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/card_view_quiz\"]//child::*[@resource-id=\"com.affairscloud:id/user_name\"][1]"));
            test.log(Status.PASS, "Successfully clicked the course title",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked Course Title")).build());
            System.out.println("Successfully clicked the course title.");

            Thread.sleep(5000);
            navigateBack();
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click course title: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Click Course Title")).build());
            throw e;
        }
    }

    private void storeQuizNameBeforeClick() {
        try {
            String QuizTitleBeforeClick = getText(By.xpath("(//*[@resource-id=\"com.affairscloud:id/quiz\"])//following::*[@resource-id=\"com.affairscloud:id/courses_title\"]"));
            test.log(Status.INFO, "Quiz Name Before Click: " + QuizTitleBeforeClick,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Quiz Before Click")).build());
            System.out.println("Quiz Name Before Click: " + QuizTitleBeforeClick);
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to store quiz name before click: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Store Quiz Name")).build());
            throw e;
        }
    }

    private void quizActions() {
        try {
            startQuiz();
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to perform quiz actions: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Quiz Actions")).build());
            throw e;
        }
    }

    public void startQuiz() {
        try {
            clickElement(By.xpath("(//*[@resource-id=\"com.affairscloud:id/btn_start_quiz\"])[1]"));
            test.log(Status.PASS, "Successfully Clicked The Start Quiz Button",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Started Quiz")).build());
            System.out.println("Successfully Clicked The Start Quiz Button");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to start quiz: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Failed Start Quiz")).build());
            throw e;
        }
    }
}