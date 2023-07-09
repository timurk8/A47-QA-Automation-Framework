package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {
  public HomePage(WebDriver givenDriver) {
    super(givenDriver);
  }

                       //Locators
  //User Avatar
  By userAvatarIcon = By.cssSelector("img.avatar");

  //All Songs
  By allSongs = By.cssSelector("li a.songs");

  //Play button
  By playBtn = By.cssSelector("[data-testid='play-btn']");

  //First playlist
  By firstPlaylist=By.cssSelector(".playlist:nth-child(3)");

  By playLists = By.xpath("//ul[@class='sidebar-playlist']//li");

  //Enter new name for the First playlist
  By playlistNameField = By.cssSelector("[name='name']");

  //Playlists
  static By sidebarPlayListsBy = By.xpath("//ul[@class='sidebar-playlist']//li");

  //Create a Playlist - plus
  By plusPlaylist = By.xpath("//section[@id='playlists']/h1/i"); //plus
  //New playlist or New smart playlist
  //section[@id='playlists']/nav/ul/li[1]
  By plusNewPlaylist = By.xpath("//section[@id='playlists']/nav/ul/li[1]"); // New playlist
  By plusNewSmartPlaylist = By.xpath("//section[@id='playlists']/nav/ul/li[2]"); // New smart playlist





  //Methods
  public WebElement getUserAvatar() {
    return findElement(userAvatarIcon);
  }

  //Doubleclick on the first Playlist
  public void doubleClickPlaylist() {doubleClick(firstPlaylist);}

  //Enter New name to PL
  public void enterNewPlaylistName (String playlistName) {
    findElement(playlistNameField).sendKeys(Keys.chord(Keys.COMMAND, "A", Keys.BACK_SPACE));
    findElement(playlistNameField).sendKeys(playlistName);
    findElement(playlistNameField).sendKeys(Keys.ENTER);
  }

  //Does playlist "playlistName" exists
  public static boolean doesPlaylistExist(String playlistName) {
    WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + playlistName + "' ]")));
    return playlistElement.isDisplayed();
  }

  //Check if playlists exist
  protected static boolean checkPlaylistsExist() {
    List<WebElement> sidebarPlayLists = findElementsList(sidebarPlayListsBy);
    return !sidebarPlayLists.isEmpty();
  }

  //Create Playlist on sidebar
  public void createPlaylistSidebar(String namePlaylist) {
    findElementClickable(plusPlaylist).click();
    findElementClickable(plusNewPlaylist).click();
//    findElement(By.xpath("//section[@id='playlists']/form/input")).sendKeys(namePlaylist, Keys.ENTER);
    findElement(playlistNameField).sendKeys(namePlaylist, Keys.ENTER);
  }


}

