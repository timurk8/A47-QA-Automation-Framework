import org.testng.Assert;
import org.testng.annotations.Test;


public class Homework18Test extends BaseTest {

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public static void checkPlaySong (String email, String password) {

        //Login Koel
        loginKoel(email,password);

        //Play song
        playSong();

        //Validate that a song is playing
          //by verifying the sound bar
        Assert.assertTrue(isSoundBarDisplayed());

          //by verifying the pause button
        Assert.assertTrue(isPauseButtonDisplayed());

    }



}