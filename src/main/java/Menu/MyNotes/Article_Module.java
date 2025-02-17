package Menu.MyNotes;

import Menu.MyEbooks.myEbooks;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class Article_Module extends myEbooks {

    public Article_Module(AndroidDriver driver){
        super(driver);
    }

    public void articleActions() throws InterruptedException {

        /*Calling the course title method */
        clickingCourseTitle();

    }

    public void clickingCourseTitle() throws InterruptedException {
        clickElement ( By.xpath ( "//*[@resource-id=\"com.affairscloud:id/user_name\"]" ) );
        System.out.println ("Successfully Clicked The Course Title");

        /*Calling the navigate back method */
        Thread.sleep ( 4000 );
        navigateBack ();


    }















}
