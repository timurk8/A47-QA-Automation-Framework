import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class BaseTest {

    public static WebDriverWait wait;

    public static WebDriver driver = null;
    public static String url = "https://qa.koel.app/";

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

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        url = BaseURL;
        driver.get(url);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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


    //Open Web app         // openLoginUrl(); Commented because of parameters
//    protected static void openLoginUrl() {
//        String url = "https://qa.koel.app/";
//        driver.get(url);
//    }

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
        //Delete the first playlist if exists
        protected static void deleteFirstPlaylist()  {
            List<WebElement> sidebarPlayLists = driver.findElements(By.xpath("//section[@id='playlists']/ul/li[3]"));

            //int countPL = sidebarPlaylists.size();
            if (sidebarPlayLists.isEmpty()) {
                // Create a Playlist
                WebElement playlistSidebarElement = driver.findElement(By.xpath("//section[@id='playlists']/h1/i"));
                playlistSidebarElement.click();
                WebElement newPlaylistSidebarElement = driver.findElement(By.xpath("//section[@id='playlists']/nav/ul/li[1]"));
                newPlaylistSidebarElement.click();
                WebElement newPlaylistSidebarEnter = driver.findElement(By.xpath("//section[@id='playlists']/form/input"));
                newPlaylistSidebarEnter.sendKeys("The First Playlist", Keys.ENTER);
                System.out.println("No playlists. We created \"The First Playlist\". Try to delete now");


            } else {
                // Delete the first playlist
                WebElement firstPlaylistElement = sidebarPlayLists.get(0);
                firstPlaylistElement.click();

                WebElement deletePlaylist = driver.findElement(By.xpath("//button[normalize-space()='Playlist']"));
                deletePlaylist.click();

                List<WebElement> okButtons = driver.findElements(By.xpath("//button[normalize-space()='Ok']"));
                int countOk = okButtons.size();

                if (countOk !=0) {
                    okButtons.get(0).click();
                    System.out.println("Ok button clicked and the first playlist has been deleted");
                }
                else {
                    System.out.println("The first playlist was empty and has been deleted");
//                    WebElement notificationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
//                    System.out.println(notificationMsg.getText());
                }


            }

    }
    //Check notification:
    // 1) No: The song is already in the playlist
    // 2) Added 1 song into "The First Playlist."
    // 3) Created playlist "The First Playlist."
    public static void printNotificationText(String noNotification) {
        List<WebElement> notificationElements = driver.findElements(By.cssSelector("div.success.show"));

        if (!notificationElements.isEmpty()) {
            WebElement notificationElement = notificationElements.get(0);
            String notificationText = notificationElement.getText();
            System.out.println("The notification is: "+notificationText);
        } else {
            String notificationText = noNotification;
            System.out.println(notificationText);
        }

    }

    //Get Notification
//    public static String getMsg() {
//        WebElement notificationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
//        return notificationMsg.getText();
//    }


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