import org.testng.annotations.Test;
//


public class Homework21Test extends BaseTest {

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void renamePlaylist(String email, String password)  {

        //Login Koel
        loginKoel(email,password);

        //Rename the first playlist
        renameFirstPlaylist();

        String printNT = printNotificationText("No notification ");

    }



}