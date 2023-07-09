import org.testng.Assert;
import org.testng.annotations.Test;


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

        String printNT = printNotificationText("No notification ");
        Assert.assertTrue(printNT.contains(deletedPlaylistMsg)||printNT.contains(createdPlaylistMsg));

    }

}