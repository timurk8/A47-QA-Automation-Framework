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


        //Validate that a song is playing
          //by verifying the sound bar
        //Assert.assertTrue(isSoundBarDisplayed());

          //by verifying the pause button
        //Assert.assertTrue(isPauseButtonDisplayed());


        //Notification


        //System.out.println("The notification is: "+getMsg());
        printNotificationText("No notification: ");
        Thread.sleep(1000);

//        String deletedPlaylistMsg = "Deleted playlist";
//        Assert.assertTrue(getMsg().contains(deletedPlaylistMsg));





    }



}