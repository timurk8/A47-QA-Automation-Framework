import org.testng.annotations.Test;

import java.time.Duration;


public class Homework17Test extends BaseTest {

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public static void addSongToPlaylist (String email, String password) throws InterruptedException {

        //Login Koel
        loginKoel(email,password);
        Thread.sleep(2000);

        //Search for a Song
        searchSong("Ketsa");
        Thread.sleep(2000);

        //View all found songs
        viewAllButton();
        Thread.sleep(2000);

        //Select the first Song found
        selectFirstFoundSong();
        Thread.sleep(2000);

        //Add to Playlist - Button
        addToButton();
        Thread.sleep(2000);

        //Add to Playlist
        addToPlaylist();
        Thread.sleep(2000);

        //Notification
        printNotificationText("No notification: The song is already in the playlist");
        Thread.sleep(2000);

    }



}