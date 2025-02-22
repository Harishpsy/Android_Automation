package Menu.InviteFriends;

import Setup.BaseActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class invite extends BaseActions {

    public invite(AndroidDriver driver) {
        super(driver);
    }

    public void navigateToInviteFriends() throws InterruptedException{
        clickMenu ();
        clickInvite ();
        commonCancel ();
    }

    public void clickInvite(){
        clickElement ( By.id ( "com.affairscloud:id/tv_invite_friends" ) );
        System.out.println ("Successfully CLicked The Invite Button");
    }

}
