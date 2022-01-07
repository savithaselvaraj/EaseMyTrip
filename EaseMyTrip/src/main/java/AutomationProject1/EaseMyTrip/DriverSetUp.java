package AutomationProject1.EaseMyTrip;

import java.util.Properties;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSetUp {
	
	private static WebDriver driver;
	private static Properties prop;
	
	public static WebDriver getChromeDriver() {
		prop = FileIO.initProperties();
		String userDir = System.getProperty("user.dir");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", userDir + prop.getProperty("chromeDriver"));
		driver = new ChromeDriver(op);
		return driver;
	}
	
	public static WebDriver getFirefoxDriver() {
		prop = FileIO.initProperties();
		String userDir = System.getProperty("user.dir");
		FirefoxOptions fo = new FirefoxOptions();
		fo.addArguments("--disable-infobars");
		fo.addArguments("--disable-notifications");
		fo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
		System.setProperty("webdriver.gecko.driver",userDir
				+ prop.getProperty("firefoxDriver"));
		driver = new FirefoxDriver(fo);
		return driver;
	}

}
