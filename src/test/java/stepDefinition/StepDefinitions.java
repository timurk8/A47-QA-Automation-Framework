package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.time.LocalTime;

public class StepDefinitions {
    WebDriver driver;
    WebDriverWait wait;
    @Before
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @After
    public void closeBrowser(){
        driver.quit();
    }
    @Given("I open Login page")
    public void openLoginPage() {
        driver.get("https://qa.koel.app");
    }
    @When("I enter email {string}")
    public void i_enter_email(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='email']"))).sendKeys(email);
    }
    @And("I enter password {string}")
    public void i_enter_password(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='password']"))).sendKeys(password);
    }
    @And("I submit")
    public void clickSubmit() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='submit']"))).click();
    }
    @Then("I am logged into the website")
    public void userIsLoggedIn() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar"))).isDisplayed());
    }

    @Then("I am not logged in")
    public void iAmNotLoggedIn() {

    }

    @When("I push the button play")
    public void iPushTheButtonPlay() {

//        WebElement play = driver.findElement(By.cssSelector("[data-testid='play-btn']"));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(play).click().perform();
//
//        //Click "Play next song"
//        String playNextButtonLocator = "//i[@data-testid='play-next-btn']";
//        WebElement playNextButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(playNextButtonLocator)));
//        playNextButtonElement.click();


        //Hover

//            WebElement play = driver.findElement(By.cssSelector("[data-testid='play-btn']"));
//            Actions actions = new Actions(driver);
//            actions.moveToElement(play).click().perform();

//        //Click "Play button"
//        String playButtonLocator = "//span[@data-testid='play-btn']";
//        WebElement playButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(playButtonLocator)));
//        playButtonElement.click();

        //Click "Play button"

//        String playButtonLocator = "//span[@data-testid='play-btn']";
        //*[@id="mainFooter"]/div[1]/span/span[2]/i
//        String playButtonLocator = "//span[@title='Play or resume']";
//        WebElement playButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(playButtonLocator)));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(playButton).click().perform();

//        WebElement playButton = driver.findElement(By.cssSelector("[data-testid='play-btn']"));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(playButton).perform();
//        playButton.click();

        WebElement buttonPlaySong = driver.findElement(By.xpath("//span[@title= 'Play or resume']"));
        Actions actions = new Actions(driver);
        actions.click(buttonPlaySong).perform();


//        WebElement play = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-testid='play-btn']")));
//        play.click();
    }


    @Then("music starts playing")
    public void musicStartsPlaying() {
        Assert.assertTrue(driver.findElement(By.cssSelector("div.bars")).isDisplayed(), "Sound bar is not displayed.");
    }

    @When("I click All Songs")
    public void iClickAllSongs() {
        driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']")).click();
    }


    @And("I click first song")
    public void iClickFirstSong() {

        WebElement play = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-testid='play-btn']")));
        play.click();

    }
}
