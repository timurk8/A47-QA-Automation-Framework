import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Homework17 extends BaseTest {

    @Test
    public static void addSongToPlaylist () throws InterruptedException {
        // Open the URL for the web page on the Chrome browser
        openLoginUrl();

        //Login Koel
        loginKoel("bugbusters@testpro.io","te$t$tudent");
        Thread.sleep(2000);

        //Search for a Song
        searchSong("Ketsa");
        Thread.sleep(2000);

        //View all found songs
        viewAllButton();
        Thread.sleep(5000);

        //Select the first Song found
        selectFirstFoundSong();
        Thread.sleep(5000);

        //Add to Playlist - Button
        addToButton();
        Thread.sleep(5000);

        //Add to Playlist
        addToPlaylist();
        Thread.sleep(5000);

        //Notification
        printNotificationText();
        Thread.sleep(5000);

    }



}