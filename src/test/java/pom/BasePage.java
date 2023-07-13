package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected Actions actions;
    protected static WebDriverWait wait;
    protected static WebDriver driver;

    private By allSongsList = By.cssSelector("li a.songs");
    private By soundBarVisualizer = By.cssSelector("[data-testid = 'sound-bar-play']");

    protected BasePage( WebDriver givenDriver) {
        driver = givenDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
    }
    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected void click (By locator) {
        findElement(locator).click();
    }
    public void doubleClick (By locator) {
        actions.doubleClick(findElement(locator)).perform();
    }
    public void chooseAllSongsList() {
        click(allSongsList);
    }
    public boolean isSongPlaying() {
        return findElement(soundBarVisualizer).isDisplayed();
    }

    //Return list of elements
    public static List<WebElement> findElementsList(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }


}

