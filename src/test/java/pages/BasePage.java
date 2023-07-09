package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    public static WebDriver driver;
    public static  WebDriverWait wait;
    public static  Actions actions;
    public static String url;

    public BasePage (WebDriver givenDriver) {
        driver = givenDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
    }

    //Visible
    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Clickable
    public WebElement findElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }




    //Return list of elements
    public static List<WebElement> findElementsList(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void doubleClick(By locator){
        actions.doubleClick(findElement(locator)).perform();
    }



}
