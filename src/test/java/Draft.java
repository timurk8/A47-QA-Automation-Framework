import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pom.BasePage;
import pom.HomePage;
import pom.LoginPage;


import java.util.List;

public class Draft extends BaseTest {

    @Test(enabled = true, priority = 1, description = "Login with valid email and valid password")
    public void loginValidEmailPasswordTest() {
        pom.LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        navigateToPage();
        loginPage.provideEmail("bugbusters@testpro.io");
        loginPage.providePassword("te$t$tudent");
        loginPage.clickSubmit();


    }
}