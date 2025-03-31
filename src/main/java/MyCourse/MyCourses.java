package MyCourse;

import AllCourse.AllCourseTab.AllCourseActions;
import AllCourse.Filter.filter;
import AllCourse.SubscribedCourse.CourseAction.subscribedcoursemodule;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MyCourses extends BaseActions {

    private AllCourseActions ALLCOURSEMODULE;
    private filter FILTERACTION;

    public MyCourses (AndroidDriver driver){
        super(driver);
        ALLCOURSEMODULE = new AllCourseActions ( driver );
        FILTERACTION = new filter ( driver );
    }

    public void performMyCourseAction() throws InterruptedException {
        clickMyCourse();
//        FILTERACTION.performFilterAction ();
        ALLCOURSEMODULE.performingAllCourseActions ();
        scrollToBeginning ();
        clickingParticularCourse();
        navigateBack ();
        clickingParticularCourse ();
        threedots ();
        share ();
        threedots ();
        reportAction();
        navigateBack ();
    }

    private void clickMyCourse() {
       clickElement ( By.xpath ( "//android.widget.FrameLayout[@content-desc=\"My Courses\"]" ) );
    }

    private void clickingParticularCourse(){

        clickElement ( By.xpath ( "//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/root_view\"]/following::*//android.widget.TextView[@resource-id=\"com.affairscloud:id/courses_title\" and @text=\"Crack Current Affairs 2025 PDF\"]\n" ) );
        System.out.println ("Succcessfully clicked The Current Affairs Course");
    }

}
