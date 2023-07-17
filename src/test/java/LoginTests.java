import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagefactory.HomePage;
import pagefactory.LoginPage;


public class LoginTests extends BaseTest {

    @Test (dataProvider = "IncorrectLoginData", dataProviderClass = BaseTest.class, enabled = true, priority = 0, description = "Login with invalid email and valid password")
    public void loginInvalidEmailValidPasswordTest(String username, String password){

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmail("incorrect@incorrect.com");
        loginPage.providePassword("incorrect");
        loginPage.clickSubmit();

        Assert.assertEquals(getDriver().getCurrentUrl(), url); //https://qa.koel.app/
    }

    @Test (enabled = true, priority = 1, description = "Login with valid email and valid password")
    public void loginValidEmailPasswordTest(){

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        navigateToPage();
        loginPage.provideEmail("bugbusters@testpro.io");
        loginPage.providePassword("te$t$tudent");
        loginPage.clickSubmit();
        isAvatarDisplayed();
    }

    @Test (enabled = true, priority = 3, description = "Login with valid email and empty password")
    public void loginValidEmailEmptyPasswordTest() {

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        navigateToPage();
        loginPage.provideEmail("bugbusters@testpro.io");
        loginPage.providePassword("");
        loginPage.clickSubmit();

        Assert.assertEquals(getDriver().getCurrentUrl(), url); //https://qa.koel.app/
    }
    public static void isAvatarDisplayed() {
        WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[class='avatar']")));
        Assert.assertTrue(avatarIcon.isDisplayed());
    }

    @Test
    public void LoginValidEmailPasswordTest () {

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmail("bugbusters@testpro.io");
        loginPage.providePassword("te$t$tudent");
        loginPage.clickSubmit();
        Assert.assertTrue(homePage.isAvatarDisplayed());
    }

    @Test
    public void LoginEmptyEmailPasswordTest() {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.provideEmail("");
        loginPage.providePassword("te$t$tudent");
        loginPage.clickSubmit();

        Assert.assertEquals(getDriver().getCurrentUrl(), url);
    }

}