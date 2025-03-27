package Setup;

// Import all necessary page object classes
import AllCourse.AllCourseTab.AllCourseActions;
import AllCourse.AllCourseTab.allCourseTab;
import AllCourse.Filter.filter;
import AllCourse.NonSubscribedCourse.NonSubscribedCourseAction.NonSubscribed;
import AllCourse.SubscribedAndNonSubscribed.SubscribedAndNonSubscribed;
import AllCourse.SubscribedCourse.CourseAction.subscribedcoursemodule;
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

    /**
     * Test to verify login functionality using Google authentication
     * Priority: 2 (after setup)
     * Groups: login
     */
    @Test(priority = 2, enabled = true, groups = {LOGIN_GROUP})
    public void loginTest() {
        new LoginPage(driver).loginWithGoogle();
    }

    // ==================== MENU NAVIGATION TESTS ====================

    /**
     * Test to verify the menu can be opened successfully
     * Depends on successful login
     * Priority: 3 (after login)
     * Groups: menu
     */
    @Test(priority = 3, dependsOnMethods = "loginTest", enabled = false, groups = {MENU_GROUP})
    public void openMenuTest() throws InterruptedException {
        new menubase(driver).clickMenu();
    }

    /**
     * Test to verify navigation to profile section from menu
     * Depends on menu being opened
     * Groups: menu, profile
     */
    @Test(priority = 4, dependsOnMethods = "openMenuTest", enabled = false, groups = {MENU_GROUP, PROFILE_GROUP})
    public void navigateToProfileTest() throws InterruptedException {
        new ProfileActions(driver).performingProfile();
    }

    /**
     * Test to verify navigation to All Courses section from menu
     * Depends on menu being opened
     * Groups: menu, courses
     */
    @Test(priority = 5, dependsOnMethods = "openMenuTest", enabled = false, groups = {MENU_GROUP, COURSES_GROUP})
    public void navigateToAllCoursesTest() throws InterruptedException {
        new allCourse(driver).performingAllCourseActions();
    }

    /**
     * Test to verify navigation to My Courses section from menu
     * Depends on menu being opened
     * Groups: menu, courses
     */
    @Test(priority = 6, dependsOnMethods = "openMenuTest", enabled = false, groups = {MENU_GROUP, COURSES_GROUP})
    public void navigateToMyCourseTest() throws InterruptedException {
        new myCourse(driver).clickingMyCourse();
    }

    /**
     * Test to verify navigation to My Ebooks section from menu
     * Depends on menu being opened
     * Groups: menu, ebooks
     */
    @Test(priority = 7, dependsOnMethods = "openMenuTest", enabled = false, groups = {MENU_GROUP, EBOOKS_GROUP})
    public void navigateToMyEbooksTest() throws InterruptedException {
        new myEbooks(driver).navigateToMyEbooks();
    }

    /**
     * Test to verify navigation to My Notes section from menu
     * Depends on menu being opened
     * Groups: menu, notes
     */
    @Test(priority = 8, dependsOnMethods = "openMenuTest", enabled = false, groups = {MENU_GROUP, NOTES_GROUP})
    public void navigateToMyNotesTest() throws InterruptedException {
        new MyNotes(driver).navigateToMyNotes();
    }

    /**
     * Test to verify navigation to My Coins section from menu
     * Groups: menu, coins
     */
    @Test(priority = 9, enabled = false, groups = {MENU_GROUP, COINS_GROUP})
    public void navigateToMyCoinTest() throws InterruptedException {
        new myCoin(driver).navigateToMyCoins();
    }

    /**
     * Test to verify navigation to My Points section from menu
     * Groups: menu, points
     */
    @Test(priority = 10, enabled = false, groups = {MENU_GROUP, POINTS_GROUP})
    public void navigateToMyPointsTest() throws InterruptedException {
        new myPoints(driver).navigateToMyPoints();
    }

    /**
     * Test to verify navigation to My Purchase section from menu
     * Groups: menu, purchase
     */
    @Test(priority = 11, enabled = false, groups = {MENU_GROUP, PURCHASE_GROUP})
    public void navigateToMyPurchaseTest() throws InterruptedException {
        new myPurchase(driver).navigateToMyPurchase();
    }

    /**
     * Test to verify navigation to App Settings from menu
     * Groups: menu, settings
     */
    @Test(priority = 12, enabled = false, groups = {MENU_GROUP, SETTINGS_GROUP})
    public void navigateToAppSettingsTest() throws InterruptedException {
        new appSetting(driver).navigateToAppSettings();
    }

    /**
     * Test to verify navigation to Invite Friends section from menu
     * Groups: menu, invite
     */
    @Test(priority = 13, enabled = false, groups = {MENU_GROUP, INVITE_GROUP})
    public void navigateToInviteFriendsTest() throws InterruptedException {
        new invite(driver).navigateToInviteFriends();
    }

    /**
     * Test to verify navigation to About Us section from menu
     * Groups: menu, about
     */
    @Test(priority = 14, enabled = false, groups = {MENU_GROUP, ABOUT_GROUP})
    public void navigateToAboutUsTest() throws InterruptedException {
        new aboutUs(driver).navigateToAboutUs();
    }

    /**
     * Test to verify navigation to Contact Us section from menu
     * Groups: menu, contact
     */
    @Test(priority = 15, enabled = false, groups = {MENU_GROUP, CONTACT_GROUP})
    public void navigateToContactUsTest() throws InterruptedException {
        new contactUs(driver).navigateToContactUs();
    }

    // ==================== PREFERENCE TESTS ====================

    /**
     * Test to verify preference settings functionality
     * Groups: preference
     */
    @Test(priority = 16, enabled = false, groups = {PREFERENCE_GROUP})
    public void preferenceTest() {
        new preference(driver).navigateToPreference();
    }

    // ==================== NOTIFICATION TEST ====================

    /**
     * Test to verify notification functionality
     * Groups: notification
     */
    @Test(priority = 17, enabled = false, groups = {NOTIFICATION_GROUP})
    public void notificationTest() {
        new notification(driver).performingNotificationActions();
    }

    // ==================== PREFERENCE BANNER TEST ====================

    /**
     * Test to verify preference banner functionality
     * Groups: preferenceBanner
     */
    @Test(priority = 18, enabled = false, groups = {PREFERENCE_BANNER_GROUP})
    public void preferenceBannerTest() {
        new PreferenceBanner(driver).performPreferenceBannerAction();
    }

    // ==================== HOMEPAGE TESTS ====================

    /**
     * Test to verify ebooks functionality on homepage
     * Groups: Homepage
     */
    @Test(priority = 19, enabled = false, groups = {HOMEPAGE_GROUP})
    public void homepageEbooksTest() throws InterruptedException {
        new ebooks(driver).performingEbooksActions();
    }

    /**
     * Test to verify articles functionality on homepage
     * Groups: Homepage
     */
    @Test(priority = 20, enabled = false, groups = {HOMEPAGE_GROUP})
    public void homepageArticleTest() throws InterruptedException {
        new articles(driver).performingArticlesActions();
    }

    // ==================== COURSE-RELATED TESTS ====================

    /**
     * Test to verify All Course tab functionality
     */
    @Test(priority = 21, enabled = false)
    public void allCourseTabTest() throws InterruptedException {
        new allCourseTab(driver).clickingAllCoursetab();
    }

    /**
     * Test to verify actions within All Courses section
     */
    @Test(priority = 22, enabled = false)
    public void allCourseActionsTest() throws InterruptedException {
        new AllCourseActions(driver).performingAllCourseActions();
    }

    /**
     * Test to verify filter functionality in courses
     */
    @Test(priority = 23, enabled = false)
    public void filterTest() throws InterruptedException {
        new filter(driver).performFilterAction();
    }

    /**
     * Test to verify subscription and unsubscription functionality
     */
    @Test(priority = 24, enabled = false)
    public void subscribeAndUnsubscribeTest() throws InterruptedException {
        new SubscribedAndNonSubscribed(driver).performingSubscribeAndUnsubscribeActions();
    }

    /**
     * Test to verify actions on non-subscribed courses
     */
    @Test(priority = 25, enabled = false)
    public void nonSubscribedCourseActionsTest() throws InterruptedException {
        new NonSubscribed(driver).performNonSubscribedActions();
    }

    /**
     * Test to verify actions on subscribed courses
     */
    @Test(priority = 26, enabled = false)
    public void subscribedCourseTest() throws InterruptedException {
        new subscribedcoursemodule(driver).performingSubscribedCourseAction();
    }

    // ==================== DOUBTS-RELATED TESTS ====================

    /**
     * Test to verify basic doubts functionality
     * Enabled: true (active test)
     */
    @Test(priority = 27, enabled = true)
    public void doubtsTest() {
        new Doubt(driver).peformDoubtsAction();
    }

    /**
     * Test to verify All Doubts section functionality
     */
    @Test(priority = 28, enabled = false)
    public void allDoubtsActionTest() throws InterruptedException {
        new AllDoubts(driver).performAllDoubtsActions();
    }

    /**
     * Test to verify My Doubts section functionality
     */
    @Test(priority = 29, enabled = false)
    public void myDoubtsActionsTest() throws InterruptedException {
        new myDoubts(driver).performMyDoubtsActions();
    }

    /**
     * Test to verify My Answers section functionality
     */
    @Test(priority = 30, enabled = false)
    public void myAnswerActionsTest() throws InterruptedException {
        new MyAnswer(driver).performMyAnswerActions();
    }

    /**
     * Test to verify Followed Doubts functionality
     */
    @Test(priority = 31, enabled = false)
    public void followedActionsTest() {
        new followed(driver).performFollowedAction();
    }

    // ==================== POINTS TEST ====================

    /**
     * Test to verify points functionality
     */
    @Test(priority = 32, enabled = true)
    public void pointsTest() throws InterruptedException {
        new points(driver).performPointsActions();
    }

    // ==================== LOGOUT TEST ====================

    /**
     * Test to verify logout functionality
     */
    @Test(priority = 33, enabled = true)
    public void logoutTest() throws InterruptedException {
        new Logout(driver).performLogoutActions();
    }
}