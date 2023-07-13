package pagefactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class HomePage extends BasePage {
    @FindBy(css = "img[class='avatar']")
    protected WebElement avatarIcon;
    @FindBy(css = ".playlist:nth-child(3)")
    protected WebElement firstPlaylist;
    @FindBy(css = "[name='name']")
    protected WebElement playlistNameField;
    @FindBy(css = "div.success.show")
    protected WebElement popUpNotification;
    @FindBy(xpath = "//section[@id='playlists']/ul/li[3]")
    protected WebElement sidebarPlayListsBy;
    @FindBy(xpath = "//section[@id='playlists']/ul/li[3]")
    protected List<WebElement> sidebarPlaylists;

    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //Fluent interface
    public HomePage doubleClickPlaylist() {
        doubleClick(firstPlaylist);
        return this;
    }
    public HomePage enterNewPlaylistName(String playlistName) {
        findElement(playlistNameField).sendKeys(playlistName);
        findElement(playlistNameField).sendKeys(Keys.chord(Keys.COMMAND,"a", Keys.BACK_SPACE));
        findElement(playlistNameField).sendKeys(playlistName);
        findElement(playlistNameField).sendKeys(Keys.ENTER);
        findElement(popUpNotification);//wait for the popup notification for successful updating of the playlist name
        return this;
    }

    //Changed the approach for assert
    public String getPlaylistName () {
        return findElement(firstPlaylist).getText();
    }
    public boolean isAvatarDisplayed() {
        return findElement(avatarIcon).isDisplayed();
    }

    public boolean checkIfPlaylistsExist() {
        return !sidebarPlaylists.isEmpty();
    }

}