package pom;

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
    By firstPlaylist = By.cssSelector(".playlist:nth-child(3)");
    By playlistNameField = By.cssSelector("[name='name']");
    By userAvatarIcon = By.cssSelector("img.avatar");

    By avatarIcon = By.cssSelector("img[class='avatar']");

    static By sidebarPlayListsBy = By.xpath("//section[@id='playlists']/ul/li[3]");

    By firstPL = By.cssSelector("#playlists > ul > li:nth-child(3) > a");


    public WebElement getUserAvatar () {
        return findElement(userAvatarIcon);
    }
    public void doubleClickPlaylist() {
        doubleClick(firstPlaylist);
    }
    public void enterNewPlaylistName(String playlistName) {
        findElement(playlistNameField).sendKeys(Keys.chord(Keys.COMMAND,"A", Keys.BACK_SPACE));
        findElement(playlistNameField).sendKeys(playlistName);
        findElement(playlistNameField).sendKeys(Keys.ENTER);
    }
    public boolean doesPlaylistExist(String playlistName) {
        By newPlaylist = By.xpath("//a[text()='"+playlistName+"']");
        return findElement(newPlaylist).isDisplayed();
    }

    public boolean isAvatarDisplayed() {
        return findElement(avatarIcon).isDisplayed();
    }

    public String getPlaylistName() {
        System.out.println(findElement(firstPL).getText() + " - New name");
        return findElement(firstPL).getText();
    }

    public static boolean checkIfPlaylistsExist() {
        List<WebElement> sidebarPlayLists = driver.findElements(sidebarPlayListsBy);
        return !sidebarPlayLists.isEmpty();
    }

}
