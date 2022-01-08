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
		String month = "NOV 2022";
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
		
		//Chaaru's code
		
		WebElement Travellerdropddown = driver.findElement(By.xpath("//*[@id=\"frmHome\"]/div[5]/div[2]/div[3]/div[1]/div[5]/div/a/span"));
		Travellerdropddown.click();
		
		travellers(2, 1, 1);
		
		String travClass= "Prem.Economy";
		driver.findElement(By.xpath("//*[@id='frmHome']/div[5]/div[2]/div[3]/div[1]/div[6]/div/a/span")).click();		
		driver.findElement(By.xpath("//label[contains(text(),'" + travClass + "')]/input[@name='optClass']")).click();	
		driver.findElement(By.xpath("//a[@id='tripType']")).click();
		driver.findElement(By.xpath("//*[@id='search']//input")).click();
		
		//End Chaaru's code
		
		FlightList fl = new FlightList(driver);
		fl.getFlights();
	}
	

	public static void travellers(int adult, int children, int infants) {
	
		WebElement Adultvalue = driver.findElement(By.id("optAdult"));
		int displayvalue = Integer.parseInt(Adultvalue.getAttribute("value"));
		WebElement Adultminus = driver.findElement(By.xpath("//*[@id=\"myDropdown_n\"]/div/div[1]/div[2]/div/div[1]/input"));
		WebElement Adultplus = driver.findElement(By.xpath("//*[@id=\"myDropdown_n\"]/div/div[1]/div[2]/div/div[3]/input"));
		
		while(displayvalue<adult) {
			Adultplus.click();
			displayvalue = Integer.parseInt(Adultvalue.getAttribute("value"));
		}
		
		WebElement Childrenvalue = driver.findElement(By.id("optChild"));
		displayvalue = Integer.parseInt(Childrenvalue.getAttribute("value"));
		WebElement Childrenplus = driver.findElement(By.xpath("//*[@id=\"myDropdown_n\"]/div/div[2]/div[2]/div/div[3]/input"));
		
		while(displayvalue<children) {
			Childrenplus.click();
			displayvalue = Integer.parseInt(Childrenvalue.getAttribute("value"));
		}
		
		WebElement infantsvalue = driver.findElement(By.id("optInfant"));
		displayvalue = Integer.parseInt(infantsvalue.getAttribute("value"));
		WebElement infantsplus = driver.findElement(By.xpath("//*[@id=\"myDropdown_n\"]/div/div[3]/div[2]/div/div[3]/input"));
		
		while(displayvalue<infants) {
			infantsplus.click();
			displayvalue = Integer.parseInt(infantsvalue.getAttribute("value"));
		}
		
		driver.findElement(By.xpath("//*[@id=\"traveLer\"]")).click();
	}
}
