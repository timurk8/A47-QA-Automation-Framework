import org.testng.Assert;
import org.testng.annotations.Test;


public class Homework18Test extends BaseTest {

    @Test
    public static void checkPlaySong () throws InterruptedException {
        // Open the URL for the web page on the Chrome browser
        openLoginUrl();

        //Login Koel
        loginKoel("bugbusters@testpro.io","te$t$tudent");
        Thread.sleep(2000);

        //Play song
        playSong();
        Thread.sleep(2000);

        //Validate that a song is playing
          //by verifying the sound bar
        Assert.assertTrue(isSoundBarDisplayed());
        Thread.sleep(2000);

          //by verifying the pause button
        Assert.assertTrue(isPauseButtonDisplayed());
        Thread.sleep(2000);

    }



}