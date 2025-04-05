package AllCourse.SubscribedCourse.CourseAction;

import AllCourse.SubscribedCourse.ArticleTab.Article;
import AllCourse.SubscribedCourse.DetailsTab.Details;
import AllCourse.SubscribedCourse.EbooksTab.Ebook;
import AllCourse.SubscribedCourse.FreeTab.Free;
import AllCourse.SubscribedCourse.PathTab.Path;
import AllCourse.SubscribedCourse.QuizzesTab.Quiz;
import AllCourse.SubscribedCourse.VideoTab.Video;
import Setup.BaseActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class subscribedcoursemodule extends BaseActions {

    public subscribedcoursemodule(AndroidDriver driver){
        super(driver);
    }

    public void performingSubscribedCourseAction() throws InterruptedException {
        clickingParticularCourse();
        Thread.sleep ( 2000 );
        navigateBack ();
        clickingParticularCourse ();
        threedots ();
        share ();
        threedots ();
        reportAction();
    }

    private void clickingParticularCourse(){

        // Clicking The Crack Current Affairs Course
        clickElement ( By.xpath ( "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/subject_list\"]/android.widget.RelativeLayout/child::*[@resource-id=\"com.affairscloud:id/courses_title\" and @text=\"Crack Current Affairs 2025 PDF\"]" ) );
        test.log( Status.PASS, "Successfully Clicked The Crack Current Affairs Course",
                MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot("Clicked Crack Current Affairs")).build());
        System.out.println ("Successfully Clicked The Crack Current Affairs Course");
    }

}
