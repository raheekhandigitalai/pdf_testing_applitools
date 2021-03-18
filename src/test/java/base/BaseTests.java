package base;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseTests {

//    protected static WebDriver driver;
    protected static RemoteWebDriver driver;
    protected static EyesManager eyesManager;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        Properties props = System.getProperties();
        try {
            props.load(new FileInputStream(new File(System.getProperty("user.dir") + "\\resources\\test.properties")));
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

//        driver = new ChromeDriver();
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("accessKey", System.getProperty("seetest.accesskey"));
        dc.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        dc.setCapability(CapabilityType.PLATFORM_NAME, "Windows 10");
        driver = new RemoteWebDriver(new URL("https://uscloud.experitest.com/wd/hub"), dc);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(System.getProperty("sites.invoices.url"));
        eyesManager = new EyesManager(driver, "Invoice Simple");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
        eyesManager.abort();
    }

}