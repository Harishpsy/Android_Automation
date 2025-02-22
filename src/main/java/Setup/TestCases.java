package Setup;

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
import Menu.ProfileActions;
import Menu.menubase;
import org.testng.annotations.Test;

public class TestCases extends Base {
    @Test(priority = 2, enabled = true)
    public void loginpage() {
        LoginPage loginPage = new LoginPage ( driver );
        loginPage.loginWithGoogle ();
    }

    @Test(priority = 3, dependsOnMethods = "loginpage", enabled = true)
    public void openMenu() throws InterruptedException {
        menubase menu = new menubase ( driver );
        menu.clickMenu ();
    }

    @Test(priority = 4, dependsOnMethods = "openMenu", enabled = false)
    public void navigateToProfile() throws InterruptedException {
        ProfileActions profileAction = new ProfileActions ( driver );
        profileAction.userDetails ();
    }

    @Test(priority = 5, dependsOnMethods = "openMenu", enabled = false)
    public void navigateToAllCourses() throws InterruptedException {
        allCourse allCourses = new allCourse ( driver );
        allCourses.clickingAllCourse ();
    }

    @Test(priority = 6, dependsOnMethods = "openMenu", enabled = false)
    public void navigateToMyCourse() throws InterruptedException {
        myCourse myCourses = new myCourse ( driver );
        myCourses.clickingMyCourse ();
    }

    @Test(priority = 7, dependsOnMethods = "openMenu", enabled = false)
    public void navigatingToMyEbooks() throws InterruptedException {
        myEbooks MyEbook = new myEbooks ( driver );
        MyEbook.navigateToMyEbooks ();
    }

    @Test(priority = 8, dependsOnMethods = "openMenu", enabled = false)
    public void navigatingToMyNotes() throws InterruptedException {
        MyNotes MyNotes = new MyNotes ( driver );
        MyNotes.navigateToMyNotes ();
    }

    @Test(priority = 9, enabled = false)
    public void navigateToMyCoin() throws InterruptedException {
        myCoin myCoins = new myCoin ( driver );
        myCoins.navigateToMyCoins ();
    }

    @Test(priority = 10, enabled = false)
    public void navigateToMyPoints() throws InterruptedException {
        myPoints myPoints = new myPoints ( driver );
        myPoints.navigateToMyPoints ();
    }

    @Test(priority = 11,enabled = false)
    public void navigateToMyPurchase() throws InterruptedException {
        myPurchase myPurchases = new myPurchase ( driver );
        myPurchases.navigateToMyPurchase ();
    }

    @Test(priority = 12,enabled = false)
    public void navigateToAppSettings() throws InterruptedException {
        appSetting appSettings = new appSetting ( driver );
        appSettings.navigateToAppSettings ();
    }

    @Test(priority = 13,enabled = false)
    public void navigateToInviteFriends() throws InterruptedException {
        invite invite = new invite ( driver );
        invite.navigateToInviteFriends ();
    }

    @Test(priority = 14, enabled = false)
    public void navigateToAboutUs() throws InterruptedException {
        aboutUs aboutUs = new aboutUs ( driver );
        aboutUs.navigateToAboutUs ();
    }

    @Test(priority = 15, enabled = true)
    public void navigateTOContactUs() throws InterruptedException {
        contactUs contactUs = new contactUs ( driver );
        contactUs.navigateToContactUs ();
    }

   
}

