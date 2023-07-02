import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class Homework19Test extends BaseTest {

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void deletePlayList(String email, String password) throws InterruptedException {

        //Login Koel
        loginKoel(email,password);

        //Delete the first playlist
        deleteFirstPlaylist();
        Thread.sleep(1500);

        //Notification
        String deletedPlaylistMsg = "Deleted playlist";
        String createdPlaylistMsg = "Created playlist";

        System.out.println(printNotificationText("The notification is: "));

        Thread.sleep(1000);
        Assert.assertTrue(printNotificationText("").contains(deletedPlaylistMsg)||printNotificationText("").contains(createdPlaylistMsg));







    }



}