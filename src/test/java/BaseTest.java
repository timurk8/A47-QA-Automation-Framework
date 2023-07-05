import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import org.openqa.selenium.interactions.Actions;


public class BaseTest {

    public static WebDriverWait wait;

    public static WebDriver driver = null;
    public static String url = "https://qa.koel.app/";

    static List<WebElement> sidebarPlayLists; //Number of Playlists

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    @Parameters({ "BaseURL" })
    public void launchBrowser(String BaseURL) {
        // Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        //options.addArguments("--window-size=1200,550");
        //options.addArguments("--window-position=0,0");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        url = BaseURL;
        driver.get(url);

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @DataProvider(name = "IncorrectLoginProviders")
    public static Object[][] getDataFromDataProviders() {
        return new Object[][] {
                { "notExisting@email.com", "NotExistingPassword" },
                { "demo@class.com", "" },
                { "", "" }
        };
    }

    @DataProvider(name = "CorrectLoginProviders")
    public static Object[][] getLoginData() {
        return new Object[][] {
                { "bugbusters@testpro.io", "te$t$tudent" }
        };
    }


    //Login Koel
    protected static void loginKoel(String email, String password) {
        enterInput("email", email);
        enterInput("password", password);
        submitClick("type", "submit");
    }

    //Input elements depending on type
    protected static void enterInput(String inputType, String value) {
        WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='" + inputType + "']")));
        inputElement.click();
        inputElement.clear();
        inputElement.sendKeys(value);
    }

    //Click button
    protected static void submitClick(String buttonAttribute, String valueName) {
        //WebElement submitLogin = driver.findElement(By.cssSelector("button[type='submit']")); //type,submit
        //wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button["+buttonAttribute+"='"+valueName+"']")));
        submitButton.click();
    }

    //Search for a Song
    protected static void searchSong(String song) {
        enterInput("search", song);
    }

    //View all FOUND songs
    protected static void viewAllButton() {
        //driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']")).click();
        submitClick("data-test", "view-all-songs-btn");
    }

    //Select the first Song found
    protected static void selectFirstFoundSong() {
        //driver.findElement(By.cssSelector("#songResultsWrapper tr.song-item td.title")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#songResultsWrapper tr.song-item td.title"))).click();
    }

    //Add to  button
    protected static void addToButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-add-to"))).click();
    }

    //Add a song to Playlist
    protected static void addToPlaylist() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> playlistElements = driver.findElements(By.cssSelector("#songResultsWrapper > header > div.song-list-controls > div > section.existing-playlists > ul > li.playlist"));

        if (playlistElements.isEmpty()) {
            // Create a Playlist inside "ADD TO..."
            WebElement playlistElement = driver.findElement(By.cssSelector("#songResultsWrapper > header > div.song-list-controls > div > section.new-playlist > form > input[type=text]"));
            playlistElement.click();
            playlistElement.clear();
            playlistElement.sendKeys("The First  Playlist (Add to...)");
            WebElement savePlaylistButton = driver.findElement(By.cssSelector("#songResultsWrapper > header > div.song-list-controls > div > section.new-playlist > form > button"));
            savePlaylistButton.click();
        } else {
            // Add to the first playlist
            WebElement playlistElement = playlistElements.get(0);
            wait.until(ExpectedConditions.elementToBeClickable(playlistElement)).click();
            //playlistElement.click();
        }
    }

    //Delete the first playlist if exists (HW-19, HW-20)
    protected static void deleteFirstPlaylist()  {
        boolean playlistsExist = checkPlaylistsExist();
        if (playlistsExist) {
            //Delete the first Playlist
            sidebarPlayLists.get(0).click();
            deletePlaylistAfterClick();
        }
        else{
            // Create the first Playlist
            String namePlaylist = "The First Playlist";
            createPlaylistSidebar(namePlaylist);
            System.out.println("No playlists. We created " + namePlaylist + ". Try to delete now");
        }
    }
    //Check if Playlists exist
    protected static boolean checkPlaylistsExist() {
        sidebarPlayLists = driver.findElements(By.xpath("//section[@id='playlists']/ul/li[3]"));
        if (sidebarPlayLists.isEmpty()) {
            return false;
        }
        else {return true;}
    }

    //Create Playlist on sidebar
    private static void createPlaylistSidebar(String namePlaylist) {
        WebElement playlistSidebarElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='playlists']/h1/i")));
        playlistSidebarElement.click();
        WebElement newPlaylistSidebarElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='playlists']/nav/ul/li[1]")));
        newPlaylistSidebarElement.click();
        WebElement newPlaylistSidebarEnter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='playlists']/form/input")));
        newPlaylistSidebarEnter.sendKeys(namePlaylist, Keys.ENTER);
    }

    //Delete Playlist on sidebar after click, by xPLAYLIST
    private static void deletePlaylistAfterClick() {
        //Find the sign - Playlist is empty
        List<WebElement> emptyPLs = driver.findElements(By.xpath("//section[@id='playlistWrapper']//div[@class='screen-placeholder']//div//div[@class='text']"));
        int countEmpty = emptyPLs.size(); //If countEmpty = 0, playlist is not empty

        WebElement deletePlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Playlist']")));
        deletePlaylist.click();

        if (countEmpty !=0) {
            System.out.println("The first playlist was empty and has been deleted");
        }
        else {
            WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Ok']")));
            okButton.click();
            System.out.println("Ok button clicked and the first playlist has been deleted");
        }
    }

    //Rename the first playlist if exists (HW-21)
    protected static void renameFirstPlaylist() {

        boolean playlistsExist = checkPlaylistsExist();
        if (playlistsExist) {
            //Rename the first Playlist
            sidebarPlayLists.get(0).click();
            renamePlaylistAfterClick();
        } else {
            // Create the first Playlist
            String namePlaylist = "The First Playlist";
            createPlaylistSidebar(namePlaylist);
            System.out.println("No playlists. We created " + namePlaylist + ". Try to rename now");
        }
    }

    protected static void renamePlaylistAfterClick() {
        String namePlaylist = "Renamed Playlist";
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='playlists']/ul/li[3]")));
        Actions action = new Actions(driver);
        action.doubleClick(playlistElement).perform();

        for (int i = 1; i < 30; i++) {
            action.moveToElement(playlistElement).sendKeys(Keys.BACK_SPACE).perform();
        }
        action.moveToElement(playlistElement)
                .sendKeys(namePlaylist)
                .sendKeys(Keys.ENTER)
                .perform();


    }


    public static String printNotificationText(String noNotification) {
        try {
            WebElement notificationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
            String notificationText = notificationElement.getText();
            System.out.println("The notification is: " + notificationText);
            return notificationText;
        } catch (TimeoutException e) {
            System.out.println(noNotification);
            return noNotification;
        }
    }


    //Play song (Homework 18)
    public static void playSong() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //Click "Play next song"
        String playNextButtonLocator = "//i[@data-testid='play-next-btn']";
        WebElement playNextButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(playNextButtonLocator)));
        playNextButtonElement.click();
        //Click "Play button"
        String playButtonLocator = "//span[@data-testid='play-btn']";
        WebElement playButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(playButtonLocator)));
        playButtonElement.click();
    }


    public static boolean isSoundBarDisplayed() {
        WebElement soundBar = driver.findElement(By.cssSelector("div.bars"));
        System.out.println("Sound bar is displayed - " + soundBar.isDisplayed());
        return soundBar.isDisplayed();
    }

    public static boolean isPauseButtonDisplayed() {
        WebElement pauseButton = driver.findElement(By.cssSelector("span[title='Pause']"));
        System.out.println("Pause button is displayed - " + pauseButton.isDisplayed());
        return pauseButton.isDisplayed();
    }



}