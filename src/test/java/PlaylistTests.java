import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;

import java.time.LocalTime;

public class PlaylistTests extends BaseTest {

    //Homework 22
    @Test
    public void renamePlaylist(){

        LocalTime currentTime = LocalTime.now();
        int second = currentTime.getSecond();
        String playlistName = "Renamed Playlist POM "+currentTime.getSecond(); //Just add seconds to have different names

        pom.LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.provideEmail("bugbusters@testpro.io");
        loginPage.providePassword("te$t$tudent");
        loginPage.clickSubmit();

        System.out.println("Playlists exist - " + homePage.checkIfPlaylistsExist());

        if (homePage.checkIfPlaylistsExist()){
            homePage.doubleClickPlaylist();
            homePage.enterNewPlaylistName(playlistName);

            Assert.assertEquals(homePage.getPlaylistName(), playlistName);
        }
        else{
            System.out.println("No playlists. Create then rename");
        }

    }
}