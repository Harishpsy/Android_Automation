package Menu.MyNotes;

import Menu.menubase;
import Setup.Base;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class MyNotes extends menubase {

    private Video_Module videoModule; // Declare video_Module instance
    private Article_Module articleModule;
    private Quiz_Module quizModule;

    public MyNotes(AndroidDriver driver) {
        super(driver);
        Base.driver = driver;
        videoModule = new Video_Module (driver); // Initialize video_Module
        articleModule = new Article_Module ( driver );
        quizModule = new Quiz_Module ( driver );

    }

    @Test
    public void navigateToMyNotes() throws InterruptedException {

        /* Calling the clickMenu Method */
        clickMenu();
        System.out.println("Successfully Clicked The Menu Button");

        /* Clicking The My Notes Button In The Menu */
        clickElement(By.id("com.affairscloud:id/item_my_notes"));
        System.out.println("Successfully Clicked The My Notes Button In The Side Menu");
/*
       //  Calling The Video Module
        setVideoModule();

        *//*Calling The Article Method *//*
        setArticleModule();

*/
        /*Calling The Quiz Module*/
        setQuizModule ();

    }

    @Test (enabled = false)
    public void setVideoModule() throws InterruptedException {
        /* Calling The play Button Method and Course Title Method */
        videoModule.performVideoActions (); // Calling video actions before navigating to My Notes
    }

    @Test (enabled = false)
    public void setArticleModule() throws InterruptedException {
        /* Calling The Article Method and Course Title Method */
        articleModule.performArticleActions();
    }

    @Test(enabled = true)
    public void setQuizModule() throws InterruptedException {
        /* Calling The Article Method and Course Title Method */
        quizModule.performQuizActions();
    }
}

