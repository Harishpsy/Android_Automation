package Menu.MyNotes;

import Menu.MyEbooks.myEbooks;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Quiz_Module extends myEbooks {

    public Quiz_Module(AndroidDriver driver) {
        super ( driver );
    }

    public void performQuizActions() {

        /* Verifying The quiz present in the list page or not */
        try {
            WebElement quizCard = driver.findElement ( By.id ( "com.affairscloud:id/card_view_quiz" ) );
            System.out.println ( "Quiz was present in the list page" );

            if (quizCard.isDisplayed ()) {
                Thread.sleep(4000);
                /*Clicking The Course Title Method*/
                clickCourseTitle();

                /*Getting The Quiz Name in the list page before click  */
                storeQuizNameBeforeClick();

            }else {
                System.out.println ("Error While executing the quiz actions");
            }

        } catch (Exception e) {
            System.out.println ("There is no quiz in the list page");
        }
    }

    private void clickCourseTitle() throws InterruptedException {
        clickElement(By.xpath("//*[@resource-id=\"com.affairscloud:id/card_view_quiz\"]//child::*[@resource-id=\"com.affairscloud:id/user_name\"][1]"));
        System.out.println("Successfully clicked the course title.");
        Thread.sleep ( 5000 );
        navigateBack();
    }

    private void storeQuizNameBeforeClick() {
        String QuizTitleBeforeClick = getText(By.xpath("(//*[@resource-id=\"com.affairscloud:id/quiz\"])//following::*[@resource-id=\"com.affairscloud:id/courses_title\"]"));
        System.out.println ("Quiz Name Before Click: " + QuizTitleBeforeClick);
    }




}











