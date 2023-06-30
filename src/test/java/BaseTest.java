import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class BaseTest {

    public static WebDriver driver = null;
    public static String url = "https://qa.koel.app/";

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void launchBrowser() {
        //      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }


    //Open Web app
    protected static void openLoginUrl() {
        String url = "https://qa.koel.app/";
        driver.get(url);
    }

    //Login Koel
    protected static void loginKoel(String email, String password) {
        enterInput("email", email);
        enterInput("password", password);
        WebElement submitLogin = driver.findElement(By.cssSelector("button[type='submit']"));
        submitLogin.click();
    }
    //Login Koel (enter Email or password or search depending on type)
    protected static void enterInput(String inputType, String value) {
        WebElement inputElement = driver.findElement(By.cssSelector("input[type='" + inputType + "']"));
        inputElement.click();
        inputElement.clear();
        inputElement.sendKeys(value);
    }

    //Search for a Song
    protected static void searchSong(String song) {
        enterInput("search", song);
    }

    //View all found songs
    protected static void viewAllButton() {
        driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']")).click();
    }

    //Select the first Song found
    protected static void selectFirstFoundSong() {
        driver.findElement(By.cssSelector("#songResultsWrapper tr.song-item td.title")).click();
    }

    //Add to  button
    protected static void addToButton() {
        driver.findElement(By.cssSelector("button.btn-add-to")).click();
        //driver.findElement(By.cssSelector("section[id='playlistWrapper'] li[class='playlist']")).click();
    }


    protected static void addToPlaylist() {
        List<WebElement> playlistElements = driver.findElements(By.cssSelector("#songResultsWrapper > header > div.song-list-controls > div > section.existing-playlists > ul > li.playlist"));

        if (playlistElements.isEmpty()) {
            // Create a Playlist
            WebElement playlistElement = driver.findElement(By.cssSelector("#songResultsWrapper > header > div.song-list-controls > div > section.new-playlist > form > input[type=text]"));
            playlistElement.click();
            playlistElement.clear();
            playlistElement.sendKeys("The First Playlist");
            WebElement savePlaylistButton = driver.findElement(By.cssSelector("#songResultsWrapper > header > div.song-list-controls > div > section.new-playlist > form > button"));
            savePlaylistButton.click();

        } else {
            // Add to the first playlist
            WebElement playlistElement = playlistElements.get(0);
            playlistElement.click();
        }

    }
    //Check notification:
    // 1) No: The song is already in the playlist
    // 2) Added 1 song into "The First Playlist."
    // 3) Created playlist "The First Playlist."
    public static void printNotificationText() {
        List<WebElement> notificationElements = driver.findElements(By.cssSelector("div.success.show"));

        if (!notificationElements.isEmpty()) {
            WebElement notificationElement = notificationElements.get(0);
            String notificationText = notificationElement.getText();
            System.out.println("The notification is: "+notificationText);
        } else {
            String notificationText = "No notification: The song is already in the playlist";
            System.out.println(notificationText);
        }

    }

    //Play song (Homework 18)
    public static void playSong() {
        //Click "Play next song"
        String playNextButtonLocator = "//i[@data-testid='play-next-btn']";
        WebElement playNextButtonElement = driver.findElement(By.xpath(playNextButtonLocator));
        playNextButtonElement.click();
        //Click "Play button"
        String playButtonLocator = "//span[@data-testid='play-btn']";
        WebElement playButtonElement = driver.findElement(By.xpath(playButtonLocator));
        playButtonElement.click();
    }

    public static boolean isSoundBarDisplayed() {
        WebElement soundBar = driver.findElement(By.cssSelector("div.bars"));
        System.out.println("Sound bar is displayed - " + soundBar.isDisplayed());
        return soundBar.isDisplayed();
    }

    public static boolean isPauseButtonDisplayed() {
        //WebElement pauseButton = driver.findElement(By.xpath("//span[@data-testid='pause-btn']"));
        WebElement pauseButton = driver.findElement(By.cssSelector("span[title='Pause']"));
        System.out.println("Pause button is displayed - " + pauseButton.isDisplayed());
        return pauseButton.isDisplayed();
    }



}