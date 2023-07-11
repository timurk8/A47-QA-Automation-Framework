import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pom.BasePage;
import pom.HomePage;


import java.util.List;

public class Draft extends BaseTest {

    @Test (enabled = true, priority = 1, description = "Login with valid email and valid password")
    public void loginValidEmailPasswordTest(){

        navigateToPage();
        provideEmail("bugbusters@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();

        System.out.println(checkPlaylistsExist());

    }


//    public static boolean checkPlaylistsExist() {
//        try {
//            List<WebElement> sidebarPlayLists = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='playlists']/ul/li[3]/a")));
//            return !sidebarPlayLists.isEmpty();
//        } catch (TimeoutException e) {
//            return false;
//        }
//    }

    //Check if Playlists exist
    protected static boolean checkPlaylistsExist() {
        List<WebElement> sidebarPlayLists = driver.findElements(By.xpath("//section[@id='playlists']/ul/li[3]"));
        return !sidebarPlayLists.isEmpty();
    }

}