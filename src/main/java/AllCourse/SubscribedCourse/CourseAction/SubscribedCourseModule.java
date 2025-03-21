package AllCourse.SubscribedCourse.CourseAction;

import AllCourse.SubscribedCourse.ArticleTab.Article;
import AllCourse.SubscribedCourse.EbooksTab.Ebook;
import AllCourse.SubscribedCourse.QuizzesTab.Quiz;
import AllCourse.SubscribedCourse.VideoTab.Video;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SubscribedCourseModule  extends BaseActions {

    private Article ArticleModule;
    private Video VideoModule;
    private Quiz QuizModule;
    private Ebook EbookModule;


    public SubscribedCourseModule(AndroidDriver driver){
        super(driver);
        ArticleModule = new Article(driver);
        VideoModule = new Video ( driver );
        QuizModule = new Quiz ( driver );
        EbookModule = new Ebook ( driver );

    }

    public void performingSubscribedCourseAction() throws InterruptedException {
        clickingParticularCourse();
        Thread.sleep ( 3000 );
        navigateBack ();
        clickingParticularCourse ();
//        threedots ();
//        share ();
//        threedots ();
//        reportAction();
        tabsActions();
    }

    protected  void tabsActions() throws InterruptedException {
//        Thread.sleep ( 3000 );
//        ArticleModule.performArticleActions();
//        Thread.sleep ( 3000 );
//        VideoModule.performingVideoAction();
//        Thread.sleep ( 3000 );
//        QuizModule.performQuizActions();
        Thread.sleep ( 3000 );
        EbookModule.performEbookActions ();
    }

    protected void clickingParticularCourse(){

         // Clicking The Crack Current Affairs Course
        clickElement ( By.xpath ( "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.affairscloud:id/subject_list\"]/android.widget.RelativeLayout/child::*[@resource-id=\"com.affairscloud:id/courses_title\" and @text=\"Crack Current Affairs 2025 PDF\"]" ) );
        System.out.println ("Successfully Clicked The Crack Current Affairs Course");
    }

}
