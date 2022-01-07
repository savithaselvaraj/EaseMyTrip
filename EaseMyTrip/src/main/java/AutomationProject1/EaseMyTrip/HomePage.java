package AutomationProject1.EaseMyTrip;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private static WebDriver driver;
	
	public static void goToHomePage() {
		BaseUI.setUp();
		BaseUI.invokeBrowser();
		driver = BaseUI.getURL();
	}
	
	public static void searchFlights() {
		goToHomePage();
		String fromStr = "Mumbai";
		String toStr = "Chennai";
		String month = "DEC";
		WebElement from = driver.findElement(By.id("FromSector_show"));
		from.click();
		from.sendKeys(fromStr);
		from = driver.findElement(By.xpath("//div[@id='fromautoFill']//span[@class='ct'][contains(text(),'BOM')]"));
		from.click();
		
		WebElement to = driver.findElement(By.id("Editbox13_show"));
		to.click();
		to.sendKeys(toStr);
		to = driver.findElement(By.xpath("//div[@id='toautoFill']//span[@class='ct'][contains(text(),'Chennai')]"));
		to.click();
		
		WebElement ddate = driver.findElement(By.id("ddate"));
		ddate.click();
		
		WebElement monthElement = driver.findElement(By.xpath("//div[@class='month2']"));
		
		WebElement navNxt = driver.findElement(By.xpath("//div[@class='month3']//img"));
		
		while(!monthElement.getText().contains(month)){
			navNxt.click();
			monthElement = driver.findElement(By.xpath("//div[@class='month2']"));
			navNxt = driver.findElement(By.xpath("//div[@class='month3']//img"));	
		}
		WebElement dayElement = driver.findElement(By.xpath("//div[@class='days']//li[contains(text(),'15')]"));
		dayElement.click();
		
		WebElement searchBtn = driver.findElement(By.id("search")); 
		searchBtn.click();
	}
}
