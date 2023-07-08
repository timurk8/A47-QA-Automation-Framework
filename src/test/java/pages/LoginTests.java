package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.List;

public class LoginTests extends BasePagesTest {
  @Test
  public void LoginValidEmailPasswordTest() {
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);

//    List<WebElement> links = driver.findElements(By.tagName("a"));
//    for (WebElement link : links) {
//      System.out.println(link.getAttribute("href"));
//    }

    loginPage.login();
    Assert.assertTrue(homePage.getUserAvatar().isDisplayed());

    //homePage.getUserAvatar();

  }

}
