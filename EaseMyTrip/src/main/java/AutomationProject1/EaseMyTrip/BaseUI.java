package AutomationProject1.EaseMyTrip;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseUI {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public BaseUI() {
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
	

	/**************** Get By locator using locator key ****************/
	public static By getLocator(String locatorKey) {
		if (locatorKey.endsWith("_id")) {
			return By.id(prop.getProperty(locatorKey));
		}
		if (locatorKey.endsWith("_name")) {
			return (By.name(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_className")) {
			return (By.className(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_xpath")) {
			return (By.xpath(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_css")) {
			return (By.cssSelector(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_linkText")) {
			return (By.linkText(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_partialLinkText")) {
			return (By.partialLinkText(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_tagName")) {
			return (By.tagName(prop.getProperty(locatorKey)));
		}
		else {
			System.out.println("Failing test case, Invalid locator key: " + locatorKey);
			return null;
		}
	}
	
	/************** Send text to an element ****************/
	public static void sendText(By locator, String text) {
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(locator));
			driver.findElement(locator).sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/************** Click on element with WebElement ****************/
	public static void clickOn(By locator, int timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions
					.elementToBeClickable(locator));
			driver.findElement(locator).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/************** Click on element with Actions ****************/
	public static void clickAction(By locator, int timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions
					.elementToBeClickable(locator));
			Actions action = new Actions(driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(locator));
			jse.executeScript("window.scrollBy(0,-150)");
			action.moveToElement(driver.findElement(locator)).build().perform();
			action.click(driver.findElement(locator)).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
