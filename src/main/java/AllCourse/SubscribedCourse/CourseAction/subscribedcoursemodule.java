package AllCourse.SubscribedCourse.CourseAction;

import AllCourse.SubscribedCourse.ArticleTab.Article;
import AllCourse.SubscribedCourse.DetailsTab.Details;
import AllCourse.SubscribedCourse.EbooksTab.Ebook;
import AllCourse.SubscribedCourse.FreeTab.Free;
import AllCourse.SubscribedCourse.PathTab.Path;
import AllCourse.SubscribedCourse.QuizzesTab.Quiz;
import AllCourse.SubscribedCourse.VideoTab.Video;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class subscribedcoursemodule extends BaseActions {

    public Article ArticleModule;
    public Video VideoModule;
    public Quiz QuizModule;
    public Ebook EbookModule;
    public Free FreeModule;
    public Details DetailsModule;
    public Path PathModule;


    public subscribedcoursemodule(AndroidDriver driver){
        super(driver);
    }

    public void performingSubscribedCourseAction() throws InterruptedException {
        clickingParticularCourse();
        Thread.sleep ( 3000 );
        navigateBack ();
        clickingParticularCourse ();
        threedots ();
        share ();
        threedots ();
        reportAction();
    }

    protected void clickingParticularCourse(){

        // Clicking The Crack Current Affairs Course
        clickElement ( By.xpath ( "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/subject_list\"]/android.widget.RelativeLayout/child::*[@resource-id=\"com.affairscloud:id/courses_title\" and @text=\"Crack Current Affairs 2025 PDF\"]" ) );
        System.out.println ("Successfully Clicked The Crack Current Affairs Course");
    }

}
