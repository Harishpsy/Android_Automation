package Setup;

// Import all necessary page object classes
import AllCourse.AllCourseTab.AllCourseActions;
import AllCourse.AllCourseTab.allCourseTab;
import AllCourse.Filter.filter;
import AllCourse.NonSubscribedCourse.NonSubscribedCourseAction.NonSubscribed;
import AllCourse.SubscribedAndNonSubscribed.SubscribedAndNonSubscribed;
import AllCourse.SubscribedCourse.ArticleTab.Article;
import AllCourse.SubscribedCourse.CourseAction.subscribedcoursemodule;
import AllCourse.SubscribedCourse.DetailsTab.Details;
import AllCourse.SubscribedCourse.EbooksTab.Ebook;
import AllCourse.SubscribedCourse.FreeTab.Free;
import AllCourse.SubscribedCourse.PathTab.Path;
import AllCourse.SubscribedCourse.QuizzesTab.Quiz;
import AllCourse.SubscribedCourse.VideoTab.Video;
import Doubts.AllDoubts.AllDoubts;
import Doubts.Doubt.Doubt;
import Doubts.Followed.followed;
import Doubts.MyAnswered.MyAnswer;
import Doubts.MyDoubts.myDoubts;
import Doubts.Points.points;
import Homepage.Article.articles;
import Homepage.Ebooks.ebooks;
import Homepage.Notification.notification;
import Homepage.Preference.preference;
import Homepage.PreferenceBanner.PreferenceBanner;
import LoginPage.LoginPage;
import Logout.Logout;
import Menu.AboutsUs.aboutUs;
import Menu.AllCourse.allCourse;
import Menu.AppSettings.appSetting;
import Menu.ContactUs.contactUs;
import Menu.InviteFriends.invite;
import Menu.MyCourse.myCourse;
import Menu.MyEbooks.myEbooks;
import Menu.MyNotes.MyNotes;
import Menu.MyPoints.myPoints;
import Menu.MyPurchase.myPurchase;
import Menu.Mycoins.myCoin;
import Menu.Profile.ProfileActions;
import Menu.MenuIcon.menubase;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;

public class TestCases extends Base {

    // Constants for test groups to maintain consistency across tests
    private static final String LOGIN_GROUP = "login";
    private static final String MENU_GROUP = "menu";
    private static final String PROFILE_GROUP = "profile";
    private static final String COURSES_GROUP = "courses";
    private static final String EBOOKS_GROUP = "ebooks";
    private static final String NOTES_GROUP = "notes";
    private static final String COINS_GROUP = "coins";
    private static final String POINTS_GROUP = "points";
    private static final String PURCHASE_GROUP = "purchase";
    private static final String SETTINGS_GROUP = "settings";
    private static final String INVITE_GROUP = "invite";
    private static final String ABOUT_GROUP = "about";
    private static final String CONTACT_GROUP = "contact";
    private static final String PREFERENCE_GROUP = "preference";
    private static final String NOTIFICATION_GROUP = "notification";
    private static final String PREFERENCE_BANNER_GROUP = "preferenceBanner";
    private static final String HOMEPAGE_GROUP = "Homepage";

        @Test(priority = 2, enabled = true, groups = {LOGIN_GROUP})
        public void loginTest() {
            test = extent.createTest ( "Login Test" , "Verify login with Google authentication" );
            test.log ( Status.INFO , "Starting login test with Google" );
            new LoginPage ( driver ).loginWithGoogle ();
            test.log ( Status.PASS , "Login with Google completed successfully" );
        }

        @Test(priority = 3, dependsOnMethods = "loginTest", enabled = true, groups = {MENU_GROUP})
        public void openMenuTest() throws InterruptedException {
            test = extent.createTest ( "Open Menu Test" , "Verify menu can be opened" );
            test.log ( Status.INFO , "Attempting to open menu" );
            new menubase ( driver ).clickMenu ();
            test.log ( Status.PASS , "Menu opened successfully" );
        }

        @Test(priority = 4, dependsOnMethods = "openMenuTest", enabled = true, groups = {MENU_GROUP , PROFILE_GROUP})
        public void navigateToProfileTest() throws InterruptedException {
            test = extent.createTest ( "Profile Navigation Test" , "Verify navigation to profile section" );
            test.log ( Status.INFO , "Navigating to profile" );
            new ProfileActions ( driver ).performingProfile ();
            test.log ( Status.PASS , "Profile section accessed successfully" );
        }

        @Test(priority = 5, dependsOnMethods = "openMenuTest", enabled = true, groups = {MENU_GROUP , COURSES_GROUP})
        public void navigateToAllCoursesTest() throws InterruptedException {
            test = extent.createTest ( "All Courses Navigation Test" , "Verify navigation to all courses section" );
            test.log ( Status.INFO , "Navigating to all courses" );
            new allCourse ( driver ).performingAllCourseActions ();
            test.log ( Status.PASS , "All courses section accessed successfully" );
        }

        @Test(priority = 6, dependsOnMethods = "openMenuTest", enabled = true, groups = {MENU_GROUP , COURSES_GROUP})
        public void navigateToMyCourseTest() throws InterruptedException {
            test = extent.createTest ( "My Courses Navigation Test" , "Verify navigation to my courses section" );
            test.log ( Status.INFO , "Navigating to my courses" );
            new myCourse ( driver ).clickingMyCourse ();
            test.log ( Status.PASS , "My courses section accessed successfully" );
        }

        @Test(priority = 7, dependsOnMethods = "openMenuTest", enabled = true, groups = {MENU_GROUP , EBOOKS_GROUP})
        public void navigateToMyEbooksTest() throws InterruptedException {
            test = extent.createTest ( "My Ebooks Navigation Test" , "Verify navigation to my ebooks section" );
            test.log ( Status.INFO , "Navigating to my ebooks" );
            new myEbooks ( driver ).navigateToMyEbooks ();
            test.log ( Status.PASS , "My ebooks section accessed successfully" );
        }

        @Test(priority = 8, dependsOnMethods = "openMenuTest", enabled = false, groups = {MENU_GROUP , NOTES_GROUP})
        public void navigateToMyNotesTest() throws InterruptedException {
            test = extent.createTest ( "My Notes Navigation Test" , "Verify navigation to my notes section" );
            test.log ( Status.INFO , "Navigating to my notes" );
            new MyNotes ( driver ).navigateToMyNotes ();
            test.log ( Status.PASS , "My notes section accessed successfully" );
        }

        @Test(priority = 9, enabled = false, groups = {MENU_GROUP , COINS_GROUP})
        public void navigateToMyCoinTest() throws InterruptedException {
            test = extent.createTest ( "My Coins Navigation Test" , "Verify navigation to my coins section" );
            test.log ( Status.INFO , "Navigating to my coins" );
            new myCoin ( driver ).navigateToMyCoins ();
            test.log ( Status.PASS , "My coins section accessed successfully" );
        }

        @Test(priority = 10, enabled = true, groups = {MENU_GROUP , POINTS_GROUP})
        public void navigateToMyPointsTest() throws InterruptedException {
            test = extent.createTest ( "My Points Navigation Test" , "Verify navigation to my points section" );
            test.log ( Status.INFO , "Navigating to my points" );
            new myPoints ( driver ).navigateToMyPoints ();
            test.log ( Status.PASS , "My points section accessed successfully" );
        }

        @Test(priority = 11, enabled = true, groups = {MENU_GROUP , PURCHASE_GROUP})
        public void navigateToMyPurchaseTest() throws InterruptedException {
            test = extent.createTest ( "My Purchase Navigation Test" , "Verify navigation to my purchase section" );
            test.log ( Status.INFO , "Navigating to my purchases" );
            new myPurchase ( driver ).navigateToMyPurchase ();
            test.log ( Status.PASS , "My purchases section accessed successfully" );
        }

        @Test(priority = 12, enabled = true, groups = {MENU_GROUP , SETTINGS_GROUP})
        public void navigateToAppSettingsTest() throws InterruptedException {
            test = extent.createTest ( "App Settings Navigation Test" , "Verify navigation to app settings" );
            test.log ( Status.INFO , "Navigating to app settings" );
            new appSetting ( driver ).navigateToAppSettings ();
            test.log ( Status.PASS , "App settings accessed successfully" );
        }

        @Test(priority = 13, enabled = true, groups = {MENU_GROUP , INVITE_GROUP})
        public void navigateToInviteFriendsTest() throws InterruptedException {
            test = extent.createTest ( "Invite Friends Navigation Test" , "Verify navigation to invite friends section" );
            test.log ( Status.INFO , "Navigating to invite friends" );
            new invite ( driver ).navigateToInviteFriends ();
            test.log ( Status.PASS , "Invite friends section accessed successfully" );
        }

        @Test(priority = 14, enabled = true, groups = {MENU_GROUP , ABOUT_GROUP})
        public void navigateToAboutUsTest() throws InterruptedException {
            test = extent.createTest ( "About Us Navigation Test" , "Verify navigation to about us section" );
            test.log ( Status.INFO , "Navigating to about us" );
            new aboutUs ( driver ).navigateToAboutUs ();
            test.log ( Status.PASS , "About us section accessed successfully" );
        }

        @Test(priority = 15, enabled = true, groups = {MENU_GROUP , CONTACT_GROUP})
        public void navigateToContactUsTest() throws InterruptedException {
            test = extent.createTest ( "Contact Us Navigation Test" , "Verify navigation to contact us section" );
            test.log ( Status.INFO , "Navigating to contact us" );
            new contactUs ( driver ).navigateToContactUs ();
            test.log ( Status.PASS , "Contact us section accessed successfully" );
        }

        @Test(priority = 16, enabled = true, groups = {PREFERENCE_GROUP})
        public void preferenceTest() {
            test = extent.createTest ( "Preference Test" , "Verify preference settings functionality" );
            test.log ( Status.INFO , "Testing preference settings" );
            new preference ( driver ).navigateToPreference ();
            test.log ( Status.PASS , "Preference settings tested successfully" );
        }

        @Test(priority = 17, enabled = true, groups = {NOTIFICATION_GROUP})
        public void notificationTest() {
            test = extent.createTest ( "Notification Test" , "Verify notification functionality" );
            test.log ( Status.INFO , "Testing notifications" );
            new notification ( driver ).performingNotificationActions ();
            test.log ( Status.PASS , "Notifications tested successfully" );
        }

        @Test(priority = 18, enabled = true, groups = {PREFERENCE_BANNER_GROUP})
        public void preferenceBannerTest() {
            test = extent.createTest ( "Preference Banner Test" , "Verify preference banner functionality" );
            test.log ( Status.INFO , "Testing preference banner" );
            new PreferenceBanner ( driver ).performPreferenceBannerAction ();
            test.log ( Status.PASS , "Preference banner tested successfully" );
        }

        @Test(priority = 19, enabled = true, groups = {HOMEPAGE_GROUP})
        public void homepageEbooksTest() throws InterruptedException {
            test = extent.createTest ( "Homepage Ebooks Test" , "Verify homepage ebooks functionality" );
            test.log ( Status.INFO , "Testing homepage ebooks" );
            new ebooks ( driver ).performingEbooksActions ();
            test.log ( Status.PASS , "Homepage ebooks tested successfully" );
        }

        @Test(priority = 20, enabled = true, groups = {HOMEPAGE_GROUP})
        public void homepageArticleTest() throws InterruptedException {
            test = extent.createTest ( "Homepage Articles Test" , "Verify homepage articles functionality" );
            test.log ( Status.INFO , "Testing homepage articles" );
            new articles ( driver ).performingArticlesActions ();
            test.log ( Status.PASS , "Homepage articles tested successfully" );
        }

        @Test(priority = 21, enabled = true)
        public void allCourseTabTest() throws InterruptedException {
            test = extent.createTest ( "All Course Tab Test" , "Verify all course tab functionality" );
            test.log ( Status.INFO , "Testing all course tab" );
            new allCourseTab ( driver ).clickingAllCoursetab ();
            test.log ( Status.PASS , "All course tab tested successfully" );
        }

        @Test(priority = 22, enabled = true)
        public void allCourseActionsTest() throws InterruptedException {
            test = extent.createTest ( "All Course Actions Test" , "Verify all course actions functionality" );
            test.log ( Status.INFO , "Testing all course actions" );
            new AllCourseActions ( driver ).performingAllCourseActions ();
            test.log ( Status.PASS , "All course actions tested successfully" );
        }

        @Test(priority = 23, enabled = true)
        public void filterTest() throws InterruptedException {
            test = extent.createTest ( "Course Filter Test" , "Verify course filter functionality" );
            test.log ( Status.INFO , "Testing course filters" );
            new filter ( driver ).performFilterAction ();
            test.log ( Status.PASS , "Course filters tested successfully" );
        }

        @Test(priority = 24, enabled = true)
        public void subscribeAndUnsubscribeTest() throws InterruptedException {
            test = extent.createTest ( "Subscribe/Unsubscribe Test" , "Verify course subscription functionality" );
            test.log ( Status.INFO , "Testing subscription functionality" );
            new SubscribedAndNonSubscribed ( driver ).performingSubscribeAndUnsubscribeActions ();
            test.log ( Status.PASS , "Subscription functionality tested successfully" );
        }

        @Test(priority = 25, enabled = true)
        public void nonSubscribedCourseActionsTest() throws InterruptedException {
            test = extent.createTest ( "Non-Subscribed Course Test" , "Verify non-subscribed course actions" );
            test.log ( Status.INFO , "Testing non-subscribed course actions" );
            new NonSubscribed ( driver ).performNonSubscribedActions ();
            test.log ( Status.PASS , "Non-subscribed course actions tested successfully" );
        }

        @Test(priority = 26, enabled = true)
        public void subscribedCourseTest() throws InterruptedException {
            test = extent.createTest ( "Subscribed Course Test" , "Verify subscribed course functionality" );
            test.log ( Status.INFO , "Testing subscribed course actions" );
            new subscribedcoursemodule ( driver ).performingSubscribedCourseAction ();
            test.log ( Status.PASS , "Subscribed course actions tested successfully" );
        }

        @Test(priority = 27, enabled = true)
        public void Path() throws InterruptedException {
            test = extent.createTest ( "Course Path Test" , "Verify course path functionality" );
            test.log ( Status.INFO , "Testing course path" );
            Path path = new Path ( driver );
            path.performPathAction ();
            test.log ( Status.PASS , "Course path tested successfully" );
        }

        @Test(priority = 28, enabled = true)
        public void Article() throws InterruptedException {
            test = extent.createTest ( "Course Articles Test" , "Verify course articles functionality" );
            test.log ( Status.INFO , "Testing course articles" );
            Article article = new Article ( driver );
            article.performArticleActions ();
            test.log ( Status.PASS , "Course articles tested successfully" );
        }

        @Test(priority = 29, enabled = true)
        public void Videos() throws InterruptedException {
            test = extent.createTest ( "Course Videos Test" , "Verify course videos functionality" );
            test.log ( Status.INFO , "Testing course videos" );
            Video video = new Video ( driver );
            video.performingVideoAction ();
            test.log ( Status.PASS , "Course videos tested successfully" );
        }

        @Test(priority = 30, enabled = true)
        public void Quizzes() throws InterruptedException {
            test = extent.createTest ( "Course Quizzes Test" , "Verify course quizzes functionality" );
            test.log ( Status.INFO , "Testing course quizzes" );
            Quiz quiz = new Quiz ( driver );
            quiz.performQuizActions ();
            test.log ( Status.PASS , "Course quizzes tested successfully" );
        }

        @Test(priority = 31, enabled = true)
        public void Ebooks() throws InterruptedException {
            test = extent.createTest ( "Course Ebooks Test" , "Verify course ebooks functionality" );
            test.log ( Status.INFO , "Testing course ebooks" );
            Ebook ebook = new Ebook ( driver );
            ebook.performEbookActions ();
            test.log ( Status.PASS , "Course ebooks tested successfully" );
        }

        @Test(priority = 32, enabled = true)
        public void Free() throws InterruptedException {
            test = extent.createTest ( "Free Content Test" , "Verify free content functionality" );
            test.log ( Status.INFO , "Testing free content" );
            Free free = new Free ( driver );
            free.performFreeActions ();
            test.log ( Status.PASS , "Free content tested successfully" );
        }

        @Test(priority = 33, enabled = true)
        public void Details() throws InterruptedException {
            test = extent.createTest ( "Course Details Test" , "Verify course details functionality" );
            test.log ( Status.INFO , "Testing course details" );
            Details details = new Details ( driver );
            details.performDetailsActions ();
            test.log ( Status.PASS , "Course details tested successfully" );
        }

        @Test(priority = 34, enabled = true)
        public void doubtsTest() {
            test = extent.createTest ( "Doubts Test" , "Verify doubts functionality" );
            test.log ( Status.INFO , "Testing doubts feature" );
            new Doubt ( driver ).peformDoubtsAction ();
            test.log ( Status.PASS , "Doubts feature tested successfully" );
        }

        @Test(priority = 35, enabled = true)
        public void allDoubtsActionTest() throws InterruptedException {
            test = extent.createTest ( "All Doubts Test" , "Verify all doubts functionality" );
            test.log ( Status.INFO , "Testing all doubts" );
            new AllDoubts ( driver ).performAllDoubtsActions ();
            test.log ( Status.PASS , "All doubts tested successfully" );
        }

        @Test(priority = 36, enabled = true)
        public void myDoubtsActionsTest() throws InterruptedException {
            test = extent.createTest ( "My Doubts Test" , "Verify my doubts functionality" );
            test.log ( Status.INFO , "Testing my doubts" );
            new myDoubts ( driver ).performMyDoubtsActions ();
            test.log ( Status.PASS , "My doubts tested successfully" );
        }

        @Test(priority = 37, enabled = true)
        public void myAnswerActionsTest() throws InterruptedException {
            test = extent.createTest ( "My Answers Test" , "Verify my answers functionality" );
            test.log ( Status.INFO , "Testing my answers" );
            new MyAnswer ( driver ).performMyAnswerActions ();
            test.log ( Status.PASS , "My answers tested successfully" );
        }

        @Test(priority = 38, enabled = true)
        public void followedActionsTest() {
            test = extent.createTest ( "Followed Doubts Test" , "Verify followed doubts functionality" );
            test.log ( Status.INFO , "Testing followed doubts" );
            new followed ( driver ).performFollowedAction ();
            test.log ( Status.PASS , "Followed doubts tested successfully" );
        }

        @Test(priority = 39, enabled = true)
        public void pointsTest() throws InterruptedException {
            test = extent.createTest ( "Points System Test" , "Verify points system functionality" );
            test.log ( Status.INFO , "Testing points system" );
            new points ( driver ).performPointsActions ();
            test.log ( Status.PASS , "Points system tested successfully" );
        }

        @Test(priority = 40, enabled = true)
        public void logoutTest() throws InterruptedException {
            test = extent.createTest ( "Logout Test" , "Verify logout functionality" );
            test.log ( Status.INFO , "Testing logout" );
            new Logout ( driver ).performLogoutActions ();
            test.log ( Status.PASS , "Logout tested successfully" );
        }
    }
