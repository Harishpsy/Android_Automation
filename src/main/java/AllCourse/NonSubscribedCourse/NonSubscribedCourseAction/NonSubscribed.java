package AllCourse.NonSubscribedCourse.NonSubscribedCourseAction;

import AllCourse.NonSubscribedCourse.PathTab.paths;
import AllCourse.NonSubscribedCourse.SubscribeNow.SubscribeNow;
import AllCourse.SubscribedAndNonSubscribed.SubscribedAndNonSubscribed;
import AllCourse.SubscribedCourse.DetailsTab.Details;
import AllCourse.SubscribedCourse.FreeTab.Free;
import AllCourse.SubscribedCourse.PathTab.Path;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public class NonSubscribed extends BaseActions {

      private SubscribedAndNonSubscribed course ;
      private Details detailModule;
      private Free freeModule;
      private paths pathsModule;
      private SubscribeNow SubscribeNowModule;


    public NonSubscribed(AndroidDriver driver) {
        super(driver);
        course = new SubscribedAndNonSubscribed ( driver );
        detailModule = new Details ( driver );
        SubscribeNowModule = new SubscribeNow ( driver );
        pathsModule = new paths ( driver );
        freeModule = new Free ( driver );
    }

    public void performNonSubscribedActions() throws InterruptedException {
        clickingCourse();
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
        detailActions ();
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
        pathActions ();
        driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
        freeActions();
        navigateBack ();
    }

    private void clickingCourse() throws InterruptedException {
        course.gettingCourseList ( "Not Subscribed" , "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/rl_price\"]/parent::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\"]" );
        scrollToBeginning ();
        try {
            clickElement ( By.xpath ( "(//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/rl_price\"]/parent::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\"])[1]" ) );
        }catch (NoSuchElementException e) {
            System.out.println ("Non-Subscribed Course Was Not Found");
        }
    }

    private void detailActions () throws InterruptedException {
        Thread.sleep ( 1000 );
        detailModule.email();
        Thread.sleep ( 1000 );
        detailModule.message();
        Thread.sleep ( 1000 );
        detailModule.phone();
        scrollToEnd ();
        detailModule.faqPlusIcon();
        scrollToBeginning ();
        SubscribeNowModule.performSubscribeNowAction ();


    }

    private void pathActions () throws InterruptedException {
        pathsModule.performPathActions ();
        Thread.sleep ( 2000 );
        backAction();

    }

    private void freeActions() throws InterruptedException {
        // Calling The Free Method Here
        freeModule.performFreeActions ();
    }

    private void backAction() {
        clickElement ( By.xpath ( "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/ivArrowBack\"]" ) );
        System.out.println ("Successfully Clicked The Back Button");
    }

}
