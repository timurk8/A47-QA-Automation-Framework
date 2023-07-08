package tradional;

import org.testng.Assert;
import org.testng.annotations.Test;
//


public class Lecture21Test extends Testing {

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = Testing.class)
    public void lecture21Playlist(String email, String password)  {

        //Login Koel
        loginKoel(email,password);
        renameFirstPlaylist();
        String printNT = printNotificationText("No notification ");
    }



}