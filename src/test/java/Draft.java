import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pom.BasePage;
import pom.HomePage;


import java.util.List;

public class Draft extends BaseTest {

    @Test(enabled = true, priority = 1, description = "Login with valid email and valid password")
    public void loginValidEmailPasswordTest() {

        navigateToPage();
        provideEmail("bugbusters@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();


    }
}