package AutomationProject1.EaseMyTrip;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


public class DriverSetUp {
	
	private static WebDriver driver;
	private static Properties prop;
	
	public static WebDriver getChromeDriver() {
		
		String osName = System.getProperty("os.name");
		String driverFldr ;
		if (osName.toLowerCase().contains("windows")){
			driverFldr = "-src-test-resources-drivers-windows-chromedriver.exe".replace("-",File.separator);
		}else if (osName.toLowerCase().contains("mac")) {
			driverFldr = "-src-test-resources-drivers-mac-chromedriver".replace("-",File.separator);
		}else {
			driverFldr = "-src-test-resources-drivers-linux-chromedriver".replace("-",File.separator);
		}
		
		prop = FileIO.initProperties();
		String userDir = System.getProperty("user.dir");
		System.out.println(userDir);
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", userDir + driverFldr);
		driver = new ChromeDriver(op);
		return driver;
	}
	
	public static WebDriver getFirefoxDriver() {
		prop = FileIO.initProperties();
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0";
		String userDir = System.getProperty("user.dir");
		
	    FirefoxProfile profile = new FirefoxProfile();
	    profile.setPreference("browser.cache.disk.enable", false);
	    profile.setPreference("browser.cache.memory.enable", false);
	    profile.setPreference("browser.cache.offline.enable", false);
	    profile.setPreference("network.http.use-cache", false);
		
		FirefoxOptions fo = new FirefoxOptions().setProfile(profile);
		fo.addPreference("general.useragent.override",userAgent);
		fo.addArguments("--disable-notifications");
		fo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
		
		System.setProperty("webdriver.gecko.driver", userDir + prop.getProperty("firefoxDriver"));
		
		driver = new FirefoxDriver(fo);
		return driver;
	}

}
