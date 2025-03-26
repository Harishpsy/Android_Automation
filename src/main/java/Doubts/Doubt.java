package Doubts;

import Doubts.CreatingDoubt.CreatingDoubt;
import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;

import static org.openqa.selenium.By.xpath;

public class Doubt extends BaseActions {

    private CreatingDoubt creatingDoubtModule;

    public Doubt(AndroidDriver driver) {
        super(driver);
        creatingDoubtModule = new CreatingDoubt ( driver );
    }

    public void peformDoubtsAction() {
        clickingDoubtsButton();
        creatingDoubtModule.performCreatingDoubtActions();
    }

    private void clickingDoubtsButton() {
        clickElement ( xpath ( "//android.widget.FrameLayout[@content-desc=\"Doubts\"]" ) );
        System.out.println ("Successfully Clicked The Doubt Button In The Footer");
    }


}
