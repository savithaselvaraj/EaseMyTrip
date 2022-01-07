package AutomationProject1.EaseMyTrip;

import java.util.Properties;
import org.openqa.selenium.WebDriver;

public class BaseUI {
	
	private static WebDriver driver;
	private static Properties prop;
	
	public static void setUp() {
		prop = FileIO.initProperties();
	}
	
	public static WebDriver invokeBrowser() {
		prop = FileIO.initProperties();
		String browserChoice = prop.getProperty("browserName");
		switch(browserChoice.toLowerCase()){
		case "chrome":
			driver = DriverSetUp.getChromeDriver();
			break;
		case "firefox":
			driver = DriverSetUp.getFirefoxDriver();
			break;
		default:
			driver = DriverSetUp.getChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		System.out.println("Browser Started");
		return driver;
	}
	
	
	public static WebDriver getURL() {
		driver.get(prop.getProperty("websiteURL"));
		return driver;
	}
	
	public static void tearDown() {
		driver.close();
	}
}
