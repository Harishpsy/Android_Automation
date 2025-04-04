package MyCourse.Search;

import Setup.BaseActions;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class Search extends BaseActions {


    public Search(AndroidDriver driver) {
        super ( driver );
    }

    public void performSearchActions() throws InterruptedException {
        driver.manage ().timeouts ().implicitlyWait ( 30, TimeUnit.SECONDS );
        clickSearch();
        clickBackButton();
        clickSearch();
        enteringTextInSearchField();
        Thread.sleep ( 3000 );
        /*executeAdbSearchKey();*/
        clickingSearchInTheKeyBoard();
        Thread.sleep ( 3000 );
        driver.hideKeyboard ();
        clearSearch();
        clickBackButton();
    }


    private void clickSearch(){
        clickElement ( By.xpath ( "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/iv_search\"]" ) );
        System.out.println ("Successfully Clicked Search Button");
    }

    private void enteringTextInSearchField(){

        clickElement ( By.xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/title_search\"]" ) );

        WebElement enterText = driver.findElement ( By.xpath ( "//android.widget.EditText[@resource-id=\"com.affairscloud:id/title_search\"]" ) );
        enterText.sendKeys ( "Current Affairs" );
    System.out.println ("Successfully Entered the text");
    }

    private void clearSearch() {
        clickElement ( By.id ( "com.affairscloud:id/clear_search" ) );
        System.out.println ("Successfully Clicked The Clear Button");
    }

    private void clickBackButton() {
        clickElement ( By.xpath ( "//android.widget.ImageView[@resource-id=\"com.affairscloud:id/back_press\"]" ) );
    System.out.println ("Successfully Clicked The Back Button");
    }

    private void executeAdbSearchKey() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("adb", "shell", "input", "keyevent", "84");
            processBuilder.start();
            System.out.println("ADB Search Key Sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clickingSearchInTheKeyBoard() throws InterruptedException {
        driver.isKeyboardShown ();

        Thread.sleep ( 2000 );
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
        System.out.println ("Successfully Clicked The Search Button In The Keyboard");
    }

}
