import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BaseTestForPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.List;

public class LoginTests extends BaseTestForPage {
  @Test
  public void LoginValidEmailPasswordTest() {
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);

    loginPage.login();
    Assert.assertTrue(homePage.getUserAvatar().isDisplayed());

    //homePage.getUserAvatar();

  }

}
