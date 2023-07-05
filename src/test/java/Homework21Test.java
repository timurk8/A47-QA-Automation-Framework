import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
//


public class Homework21Test extends BaseTest {

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void renamePlaylist(String email, String password) throws InterruptedException {

        //Login Koel
        loginKoel(email,password);

        //Delete the first playlist
        renameFirstPlaylist();

        Thread.sleep(2000);


        //Notification
        //String deletedPlaylistMsg = "Deleted playlist";
        //String createdPlaylistMsg = "Created playlist";

        String printNT = printNotificationText("No notification ");
        //Assert.assertTrue(printNT.contains(deletedPlaylistMsg)||printNT.contains(createdPlaylistMsg));

    }



}