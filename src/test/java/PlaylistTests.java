import org.testng.Assert;
import org.testng.annotations.Test;
import pagefactory.HomePage;
import pagefactory.LoginPage;

import java.time.LocalTime;

public class PlaylistTests extends BaseTest {

    @Test
    public void renamePlaylist(){

        LocalTime currentTime = LocalTime.now();
        int second = currentTime.getSecond();
        System.out.println("Local time now: " + currentTime);
        String playlistName = "Renamed Playlist POM "+currentTime.getSecond(); //Just add seconds to have different names

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmail("bugbusters@testpro.io");
        loginPage.providePassword("te$t$tudent");
        loginPage.clickSubmit();

        System.out.println("Playlists exist - " + homePage.checkIfPlaylistsExist());

        if (homePage.checkIfPlaylistsExist()){
            homePage.doubleClickPlaylist();
            homePage.enterNewPlaylistName(playlistName);

            System.out.println(playlistName + " - playlistName");
            Assert.assertEquals(homePage.getPlaylistName(), playlistName);
        }
        else{
            System.out.println("No playlists. Create then rename");
        }

    }
}

