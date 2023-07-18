import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class LambdaTests {

    public static WebDriver lambdaTestChromeWindows() throws MalformedURLException {
        String hubURL="https://hub.lambdatest.com/wd/hub";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("114.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "timur.karimov");
        ltOptions.put("accessKey", "MjHDOTIHLk61u0PtoKl9uXDumyYKEQS4ntzRwztKPmPSyYry7Z");
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), browserOptions);

    }


    public static WebDriver lambdaTestFirefoxWindows() throws MalformedURLException {
        String hubURL="https://hub.lambdatest.com/wd/hub";

        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("114.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "timur.karimov");
        ltOptions.put("accessKey", "MjHDOTIHLk61u0PtoKl9uXDumyYKEQS4ntzRwztKPmPSyYry7Z");
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), browserOptions);

    }

    public static WebDriver lambdaTestEdgeWindows() throws MalformedURLException {
        String hubURL="https://hub.lambdatest.com/wd/hub";

        EdgeOptions browserOptions = new EdgeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("111.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "timur.karimov");
        ltOptions.put("accessKey", "MjHDOTIHLk61u0PtoKl9uXDumyYKEQS4ntzRwztKPmPSyYry7Z");
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), browserOptions);

    }

    public static WebDriver lambdaTestSafariMac() throws MalformedURLException {
        String hubURL="https://hub.lambdatest.com/wd/hub";

        SafariOptions browserOptions = new SafariOptions();
        browserOptions.setPlatformName("MacOS Ventura");
        browserOptions.setBrowserVersion("16.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "timur.karimov");
        ltOptions.put("accessKey", "MjHDOTIHLk61u0PtoKl9uXDumyYKEQS4ntzRwztKPmPSyYry7Z");
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), browserOptions);

    }




}
