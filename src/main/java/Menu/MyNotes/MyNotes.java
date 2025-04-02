package Menu.MyNotes;

import Setup.Base;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class MyNotes extends BaseActions {

    private final Video_Module videoModule; // Declare video_Module instance
    private final Article_Module articleModule;
    private final Quiz_Module quizModule;

    public MyNotes(AndroidDriver driver) {
        super ( driver );
        Base.driver = driver;
        videoModule = new Video_Module ( driver ); // Initialize video_Module
        articleModule = new Article_Module ( driver );
        quizModule = new Quiz_Module ( driver );

    }

    @Test
    public void navigateToMyNotes() throws InterruptedException {

        /* Calling the clickMenu Method */
//        clickMenu ();
        System.out.println ( "Successfully Clicked The Menu Button" );

        /* Clicking The My Notes Button In The Menu */
        clickElement ( By.id ( "com.affairscloud:id/item_my_notes" ) );
        System.out.println ( "Successfully Clicked The My Notes Button In The Side Menu" );

        /*Calling The My Notes Actions Method*/
        myNotesActions ();
    }

    public void myNotesActions() throws InterruptedException {

        try {
            WebElement noData = driver.findElement ( By.xpath ( "//*[@resource-id=\"com.affairscloud:id/no_data\"]" ) );

            if (noData.isDisplayed ()) {
                System.out.println ( "No data was present in the list page so navigating back" );
                navigateBack ();
            }

        } catch (NoSuchElementException e) {

            /*Calling The Video Module*/
            setVideoModule ();

            /*Calling The Article Method */
            setArticleModule ();

//            /*Calling The Quiz Module*/
//            setQuizModule ();

            navigateBack ();

        }

    }

    @Test(enabled = true)
    public void setVideoModule() throws InterruptedException {
        /* Calling The play Button Method and Course Title Method */
        videoModule.performVideoActions (); // Calling video actions before navigating to My Notes
    }

    @Test(enabled = true)
    public void setArticleModule() throws InterruptedException {
        /* Calling The Article Method and Course Title Method */
        articleModule.performArticleActions ();
    }

    @Test(enabled = false)
    public void setQuizModule() throws InterruptedException {
        /* Calling The Article Method and Course Title Method */
        quizModule.performQuizActions ();
    }
}

