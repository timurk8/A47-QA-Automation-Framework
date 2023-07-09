import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BaseTestForPage;
import pages.HomePage;
import pages.LoginPage;

import java.time.LocalTime;

public class Homework22RenamePlaylist extends BaseTestForPage {
  @Test
  public void LoginValidEmailPasswordTest() {
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);

    loginPage.login();

//    LocalTime currentTime = LocalTime.now();
//    int second = currentTime.getSecond();
//    String newPlaylistName = "Renamed Playlist POM "+currentTime.getSecond(); //Just add seconds to have different names

      LocalTime currentTime = LocalTime.now();
      int second = currentTime.getSecond();

      if (checkPlaylistsExist()) {
        //Rename the first Playlist
        String newPlaylistName = "Renamed Playlist POM "+currentTime.getSecond(); //Just add seconds to have different names
        doubleClickPlaylist();
        homePage.enterNewPlaylistName(newPlaylistName);
        System.out.println("Playlist renamed - "+newPlaylistName);
        Assert.assertTrue(doesPlaylistExist(newPlaylistName));
      } else {
        // Create the first Playlist
        String newPlaylistName = "The First Playlist POM "+currentTime.getSecond();
        homePage.createPlaylistSidebar(newPlaylistName);
        System.out.println("No playlists. We created " + newPlaylistName + ". Try to rename now");
        Assert.assertTrue(doesPlaylistExist(newPlaylistName));

      }

  }

}
