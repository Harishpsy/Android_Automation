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
        ArticleModule = new Article(driver);
        VideoModule = new Video ( driver );
        QuizModule = new Quiz ( driver );
        EbookModule = new Ebook ( driver );
        FreeModule = new Free ( driver );
        DetailsModule = new Details ( driver );
        PathModule = new Path ( driver );
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
        tabsActions();
        navigateBack ();
    }

    protected  void tabsActions() throws InterruptedException {
        Thread.sleep ( 3000 );
        PathModule.performPathAction();
        Thread.sleep ( 3000 );
        ArticleModule.performArticleActions();
        Thread.sleep ( 3000 );
        VideoModule.performingVideoAction();
        Thread.sleep ( 3000 );
        QuizModule.performQuizActions();
        Thread.sleep ( 3000 );
        EbookModule.performEbookActions ();
        Thread.sleep ( 3000 );
        FreeModule.performFreeActions ();
        Thread.sleep ( 3000 );
        DetailsModule.performDetailsActions ();
    }

    protected void clickingParticularCourse(){

        // Clicking The Crack Current Affairs Course
        clickElement ( By.xpath ( "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/subject_list\"]/android.widget.RelativeLayout/child::*[@resource-id=\"com.affairscloud:id/courses_title\" and @text=\"Crack Current Affairs 2025 PDF\"]" ) );
        System.out.println ("Successfully Clicked The Crack Current Affairs Course");
    }

}
