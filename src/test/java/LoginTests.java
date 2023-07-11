import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;


public class LoginTests extends BaseTest {

    @Test (dataProvider = "IncorrectLoginData", dataProviderClass = BaseTest.class, enabled = true, priority = 0, description = "Login with invalid email and valid password")
    public void loginInvalidEmailValidPasswordTest(String username, String password){

        provideEmail(username);
        providePassword(password);
        clickSubmit();

        Assert.assertEquals(driver.getCurrentUrl(), url); //https://qa.koel.app/
    }

    @Test (enabled = true, priority = 1, description = "Login with valid email and valid password")
    public void loginValidEmailPasswordTest(){

        navigateToPage();
        provideEmail("bugbusters@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();
        isAvatarDisplayed();
    }

    @Test (enabled = true, priority = 3, description = "Login with valid email and empty password")
    public void loginValidEmailEmptyPasswordTest() {

        navigateToPage();
        provideEmail("bugbusters@testpro.io");
        providePassword("");
        clickSubmit();

        Assert.assertEquals(driver.getCurrentUrl(), url); //https://qa.koel.app/
    }
    public static void isAvatarDisplayed() {
        WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[class='avatar']")));
        Assert.assertTrue(avatarIcon.isDisplayed());
    }


    //Fluent interfaces example
    @Test
    public void LoginValidEmailPasswordTest () {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.provideEmail("bugbusters@testpro.io");
        loginPage.providePassword("te$t$tudent");
        loginPage.clickSubmit();
        Assert.assertTrue(homePage.isAvatarDisplayed());
    }

    //    OR
    @Test
    public void LoginEmptyEmailPasswordTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.provideEmail("");
        loginPage.providePassword("te$t$tudent");
        loginPage.clickSubmit();

        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

}