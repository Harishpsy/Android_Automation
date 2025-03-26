package Setup;

import AllCourse.AllCourseTab.AllCourseActions;
import AllCourse.AllCourseTab.allCourseTab;
import AllCourse.Filter.filter;
import AllCourse.NonSubscribedCourse.NonSubscribedCourseAction.NonSubscribed;
import AllCourse.SubscribedAndNonSubscribed.SubscribedAndNonSubscribed;
import AllCourse.SubscribedCourse.CourseAction.subscribedcoursemodule;
import Doubts.AllDoubts.AllDoubts;
import Doubts.Doubt.Doubt;
import Doubts.MyDoubts.myDoubts;
import Homepage.Article.articles;
import Homepage.Ebooks.ebooks;
import Homepage.Notification.notification;
import Homepage.Preference.preference;
import Homepage.PreferenceBanner.PreferenceBanner;
import LoginPage.LoginPage;
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

    // Login-related tests
    @Test(priority = 2, enabled = true, groups = {"login"})
    public void loginpage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithGoogle();
    }

    // Menu-related tests
    @Test(priority = 3, dependsOnMethods = "loginpage", enabled = false, groups = {"menu"})
    public void openMenu() throws InterruptedException {
        menubase menu = new menubase(driver);
        menu.clickMenu();
    }

    @Test(priority = 4, dependsOnMethods = "openMenu", enabled = false, groups = {"menu", "profile"})
    public void navigateToProfile() throws InterruptedException {
        ProfileActions profileAction = new ProfileActions(driver);
        profileAction.userDetails();
    }

    @Test(priority = 5, dependsOnMethods = "openMenu", enabled = false, groups = {"menu", "courses"})
    public void navigateToAllCourses() throws InterruptedException {
        allCourse allCourses = new allCourse(driver);
        allCourses.clickingAllCourse();
    }

    @Test(priority = 6, dependsOnMethods = "openMenu", enabled = false, groups = {"menu", "courses"})
    public void navigateToMyCourse() throws InterruptedException {
        myCourse myCourses = new myCourse(driver);
        myCourses.clickingMyCourse();
    }

    @Test(priority = 7, dependsOnMethods = "openMenu", enabled = false, groups = {"menu", "ebooks"})
    public void navigatingToMyEbooks() throws InterruptedException {
        myEbooks MyEbook = new myEbooks(driver);
        MyEbook.navigateToMyEbooks();
    }

    @Test(priority = 8, dependsOnMethods = "openMenu", enabled = false, groups = {"menu", "notes"})
    public void navigatingToMyNotes() throws InterruptedException {
        MyNotes MyNotes = new MyNotes(driver);
        MyNotes.navigateToMyNotes();
    }

    @Test(priority = 9, enabled = false, groups = {"menu", "coins"})
    public void navigateToMyCoin() throws InterruptedException {
        myCoin myCoins = new myCoin(driver);
        myCoins.navigateToMyCoins();
    }

    @Test(priority = 10, enabled = false, groups = {"menu", "points"})
    public void navigateToMyPoints() throws InterruptedException {
        myPoints myPoints = new myPoints(driver);
        myPoints.navigateToMyPoints();
    }

    @Test(priority = 11, enabled = false, groups = {"menu", "purchase"})
    public void navigateToMyPurchase() throws InterruptedException {
        myPurchase myPurchases = new myPurchase(driver);
        myPurchases.navigateToMyPurchase();
    }

    @Test(priority = 12, enabled = false, groups = {"menu", "settings"})
    public void navigateToAppSettings() throws InterruptedException {
        appSetting appSettings = new appSetting(driver);
        appSettings.navigateToAppSettings();
    }

    @Test(priority = 13, enabled = false, groups = {"menu", "invite"})
    public void navigateToInviteFriends() throws InterruptedException {
        invite invite = new invite(driver);
        invite.navigateToInviteFriends();
    }

    @Test(priority = 14, enabled = false, groups = {"menu", "about"})
    public void navigateToAboutUs() throws InterruptedException {
        aboutUs aboutUs = new aboutUs(driver);
        aboutUs.navigateToAboutUs();
    }

    @Test(priority = 15, enabled = false, groups = {"menu", "contact"})
    public void navigateTOContactUs() throws InterruptedException {
        contactUs contactUs = new contactUs(driver);
        contactUs.navigateToContactUs();
    }

    // Preference-related tests
    @Test(priority = 16, enabled = false, groups = {"preference"})
    public void preference() {
        preference preference = new preference(driver);
        preference.navigateToPreference();
    }

    // Notification-related tests
    @Test(priority = 17, enabled = false, groups = {"notification"})
    public void notification() {
        notification notification = new notification(driver);
        notification.performingNotificationActions();
    }

    // Preference Banner-related tests
    @Test(priority = 18, enabled = false, groups = {"preferenceBanner"})
    public void preferenceBanners() {
        PreferenceBanner preferenceBanner = new PreferenceBanner(driver);
        preferenceBanner.performPreferenceBannerAction();
    }

    // Ebooks-related tests In the Homepage
    @Test(priority = 19, enabled = false, groups = {"Homepage"})
    public void HomepageEbooks() throws InterruptedException {
        ebooks ebooksaction = new ebooks (driver);
        ebooksaction.performingEbooksActions ();
    }

    @Test(priority = 20, enabled = false, groups = {"Homepage"})
    // Article-related tests In the Homepage
    public void HomepageArticle() throws InterruptedException {
        articles ariclesaction = new articles (driver);
        ariclesaction.performingArticlesActions ();
    }
    @Test(priority = 21, enabled = false)
    public void AllCourseTab() throws InterruptedException {
        allCourseTab allCourse = new allCourseTab ( driver );
        allCourse.clickingAllCoursetab();
    }

    @Test(priority = 22, enabled = false)
    public void AllCourseActions() throws InterruptedException {
        AllCourseActions allCourse = new AllCourseActions ( driver );
        allCourse.performingAllCourseActions() ;
    }
    @Test(priority = 23, enabled = false)
    public void Filter() throws InterruptedException {
        filter filteraction = new filter ( driver );
        filteraction.performFilterAction();
    }
    @Test(priority = 24, enabled = false)
    public void SubscribeAndUnsubscribe() throws InterruptedException {
        SubscribedAndNonSubscribed subscribeAndUnsubscribe = new SubscribedAndNonSubscribed ( driver );
        subscribeAndUnsubscribe.performingSubscribeAndUnsubscribeActions();
    }

    @Test(priority = 25, enabled = false)
    public void NonSubscribedCourseActions() throws InterruptedException {
        NonSubscribed course = new NonSubscribed ( driver );
        course.performNonSubscribedActions();
    }

    @Test(priority = 26, enabled = false)
    public void SubscribedCourse() throws InterruptedException {
        subscribedcoursemodule subscribedCourse = new subscribedcoursemodule ( driver );
        subscribedCourse.performingSubscribedCourseAction();
    }

    @Test(priority = 27, enabled = true)
    public void Doubts() {
        Doubt doubtActions = new Doubt ( driver );
        doubtActions.peformDoubtsAction();
    }

    @Test(priority = 28, enabled = false)
    public void AllDoubtsAction() throws InterruptedException {
        AllDoubts allDoubts = new AllDoubts ( driver );
        allDoubts.performAllDoubtsActions();
    }

    @Test(priority = 29, enabled = true)
    public void MyDoubtsActions() {
        myDoubts myDoubts = new myDoubts ( driver );
        myDoubts.performMyDoubtsActions();
    }











}