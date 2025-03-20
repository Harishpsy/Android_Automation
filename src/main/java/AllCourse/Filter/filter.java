package AllCourse.Filter;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;

import static org.openqa.selenium.By.*;

public class filter extends BaseActions {

    public filter(AndroidDriver driver){
        super(driver);

    }

    public void performFilterAction() throws InterruptedException {
        clickFilterButton();
        Thread.sleep ( 3000 );
        navigateBack ();
        clickFilterButton ();
        featuredCourseAction();
        clickFilterButton();
        subjectsAction();
        clickFilterButton();
        examAction();
        clickFilterButton();
        priceTypeAction();
        clickFilterButton();
        priceTypePaid();
        clickingResetButton();
    }

    protected void featuredCourseAction(){
        featuredCourse ();
        featuredCourseSubModulesYes();
        clickingApplyButton();
        clickFilterButton();
        featuredCourse ();
        featuredCourseSubModulesNo();
        clickingApplyButton();

    }

    protected void priceTypeAction(){
        priceTypeFree();
        clickingApplyButton();
        clickFilterButton();
        priceTypePaid();
        clickingApplyButton();

    }

    protected void subjectsAction(){
        subjects ();
        firstSubject ();
        clickingApplyButton();
        clickFilterButton();
        subjects ();
        secondSubject ();
        clickingApplyButton();
        clickFilterButton();
        subjects ();
        thirdSubject ();
        clickingApplyButton();
        clickFilterButton();
        subjects ();
        fourthSubject ();
        clickingApplyButton();
        clickFilterButton();
        subjects ();
        fifthSubject ();
        clickingApplyButton();
        clickFilterButton();
        subjects ();
        sixthSubject ();
        clickingApplyButton();
        clickFilterButton();
        subjects ();
        seventhSubject ();
        clickingApplyButton();
        clickFilterButton();
        subjects ();
        eighthSubject ();
        clickingApplyButton();
        clickFilterButton();
        subjects ();
        ninethSubject();
        clickingApplyButton();
        clickFilterButton();
        subjects ();
        thenthSubject();
        clickingApplyButton();
    }
    protected void examAction(){
        exam ();
        firstExam();
        clickingApplyButton();
        clickFilterButton();
        exam ();
        secondExam();
        clickingApplyButton();
        clickFilterButton();
        exam ();
        thirdExam();
        clickingApplyButton();
    }
    private void clickFilterButton(){
        clickElement ( id ( "com.affairscloud:id/iv_filter" ) );
        System.out.println ("Successfully Clicked The Filter Button");
    }
    private void clickingApplyButton(){
        clickElement ( id ( "com.affairscloud:id/applyFilter" ) );
        System.out.println ("Successfully clicked the Apply Button In Filter");
    }
    protected void clickingResetButton(){
        clickElement ( id ( "com.affairscloud:id/clear_filter" ) );
        System.out.println ("Successfully clicked the Reset Button In Filter");

    }
    private void priceType () {
        clickElement ( xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/filter_item_tv\" and @text=\"Price Type\"]" ) );
        System.out.println ("Successfully Selected The Price Type");
    }
    protected void priceTypeFree(){
        // Clicking The free price type
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/rb_filter\" and @text=\"Free\"]" ) );
        System.out.println ("Successfully Selected The Free Price Type radio button ");
    }
    protected void priceTypePaid(){
        // Clicking The paid price type
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/rb_filter\" and @text=\"Paid\"]" ) );
        System.out.println ("Successfully Selected The Paid Price Type radio button");
    }
    private void featuredCourse() {
        clickElement ( xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/filter_item_tv\" and @text=\"Featured Course\"]" ) );
        System.out.println ("Successfully Selected The Featured Course");
    }
    protected void featuredCourseSubModulesYes(){

        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/rb_filter\" and @text=\"Yes\"]" ) );
        System.out.println ("Successfully Selected The Yes radio button");
    }
    protected void featuredCourseSubModulesNo(){
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/rb_filter\" and @text=\"No\"]" ) );
        System.out.println ("Successfully Selected The No radio button");
    }
    private void subjects() {
        clickElement ( xpath ( "//android.widget.TextView[@resource-id=\"com.affairscloud:id/filter_item_tv\" and @text=\"Subjects\"]" ) );
        System.out.println ("Successfully Selected The Subjects");
    }
    protected  void firstSubject(){
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Current Affairs\"]" ) );
    }
    protected  void secondSubject(){
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Data Interpretation\"]" ) );
    }
    protected  void thirdSubject(){
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Puzzle & Seating\"]" ) );
    }
    protected  void fourthSubject(){
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Logical Reasoning\"]" ) );
    }
    protected  void fifthSubject(){
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Arithmetic\"]" ) );
    }
    protected  void sixthSubject(){
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Quantitative Aptitude\"]" ) );
    }
    protected  void seventhSubject(){
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Banking Awareness\"]" ) );
    }
    protected  void eighthSubject(){
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Computer Awareness\"]" ) );
    }
    protected  void ninethSubject(){
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Computer Awareness\"]" ) );
    }
    protected  void thenthSubject(){
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Exams\"]" ) );
    }
    private void exam(){
        clickElement ( xpath ( "(//android.widget.RelativeLayout[@resource-id=\"com.affairscloud:id/cl_parent\"])[5]" ) );
    }
    protected void firstExam(){
       clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Bank PO\"]" ) );
    }
    protected void secondExam(){
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Bank Clerk\"]" ) );
    }
    protected void thirdExam(){
        clickElement ( xpath ( "//android.widget.CheckBox[@resource-id=\"com.affairscloud:id/cb_filter\" and @text=\"Insurance\"]" ) );
    }

}
