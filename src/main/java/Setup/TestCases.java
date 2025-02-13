package Setup;

import Menu.*;
import LoginPage.*;
import Menu.AllCourse.*;
import Menu.MyCourse.*;
import Menu.MyEbooks.myEbooks;
import org.testng.annotations.Test;

public class TestCases extends Base {
    @Test(priority = 2, enabled = true)
    public void loginpage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithGoogle();
    }
    @Test(priority = 3, dependsOnMethods = "loginpage", enabled = true)
    public void openMenu() throws InterruptedException {
        menubase menu = new menubase(driver);
        menu.clickMenu();
    }
    @Test(priority = 4, dependsOnMethods = "openMenu", enabled = false)
    public void navigateToProfile() throws InterruptedException {
        ProfileActions profileAction = new ProfileActions(driver);
        profileAction.userDetails();
    }
    @Test(priority = 5, dependsOnMethods = "openMenu", enabled = false)
    public void navigateToAllCourses() throws InterruptedException {
        allCourse allCourses = new allCourse (driver);
        allCourses.clickingAllCourse();
    }
    @Test(priority = 6, dependsOnMethods = "openMenu", enabled = false)
    public void navigateToMyCourse() throws InterruptedException {
        myCourse myCourses = new myCourse (driver);
        myCourses.clickingMyCourse();
    }
    @Test(priority = 7, dependsOnMethods = "openMenu", enabled = true)
    public void navigatingToMyEbooks() throws InterruptedException {
        myEbooks MyEbook = new myEbooks ( driver );
        MyEbook.navigateToMyEbooks ();
    }

}

