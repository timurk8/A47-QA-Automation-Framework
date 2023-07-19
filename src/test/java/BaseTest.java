import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver = null;
    public static String url = null;
    public static WebDriverWait wait = null;
    public static Actions actions = null;

    public static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();


    @DataProvider(name="IncorrectLoginData")
    public Object[][] getDataFromDataProviders() {

        return new Object[][] {
                {"invalid@mail.com", "invalidPass"},
                {"demo@class.com", ""},
                {"", ""}
        };
    }


    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL) throws MalformedURLException {

        threadDriver.set( pickBrowser(System.getProperty("browser")) );
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        actions = new Actions(getDriver());

        url = BaseURL;
        navigateToPage();
    }

    @AfterMethod
    public void closeBrowser() {
        getDriver().quit();
        threadDriver.remove();
    }

    public WebDriver getDriver() {
        return threadDriver.get();
    }

    public static WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.1.53:4444";

        if (browser.length() >= 5 && browser.substring(0, 5).equalsIgnoreCase("cloud")) {

            switch (browser) {
                case "cloud-firefox-windows":
                    System.out.println("----CASE: cloud-firefox-windows");
                    return LambdaTests.lambdaTestFirefoxWindows();
                case "cloud-edge-windows":
                    System.out.println("----CASE: cloud-edge-windows");
                    return LambdaTests.lambdaTestEdgeWindows();
                case "cloud-safari-mac":
                    System.out.println("----CASE: cloud-safari-mac");
                    return LambdaTests.lambdaTestSafariMac();
                default:
                    System.out.println("----CASE: cloud-chrome-windows");
                    return LambdaTests.lambdaTestChromeWindows();
            }

        }
        else {

            switch (browser) {
                case "firefox":
                    System.out.println("----CASE: firefox");
                    WebDriverManager.firefoxdriver().setup();
                    return driver = new FirefoxDriver();
                case "MicrosoftEdge":
                    System.out.println("----CASE: MicrosoftEdge");
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    return driver = new EdgeDriver(edgeOptions);

                case "grid-edge":
                    System.out.println("----CASE: Grid-Edge");
                    caps.setCapability("browserName", "MicrosoftEdge");
                    return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
                case "grid-firefox":
                    System.out.println("----CASE: Grid-Firefox");
                    caps.setCapability("browserName", "firefox");
                    return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
                case "grid-chrome":
                    System.out.println("----CASE: Grid-Chrome");
                    caps.setCapability("browserName", "chrome");
                    return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
                case "lambda-chrome-windows":
                    return LambdaTests.lambdaTestChromeWindows();
                default:
                    System.out.println("----CASE: Chrome");
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    return driver = new ChromeDriver(chromeOptions);
            }
        }
    }

    public  void navigateToPage() {
        getDriver().get(url);
    }

}