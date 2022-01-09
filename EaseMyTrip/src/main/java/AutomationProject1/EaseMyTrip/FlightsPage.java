package AutomationProject1.EaseMyTrip;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightsPage extends BaseUI {
	
	public WebDriver driver;
	
	By oneWayLink = getLocator("oneWayLink_xpath");
	By fromLocator = getLocator("from_xpath");
	By fromInput = getLocator("fromInput_xpath");
	By dest_nm = getLocator("dest_nm_xpath");
	By toLocator = getLocator("to_xpath");
	By toInput = getLocator("toInput_xpath");
	By dest_nm_to = getLocator("dest_nm_to_xpath");
	
	
	public FlightsPage() {
		
	}
	
	public FlightsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void oneWay() {
		try {
			driver.findElement(oneWayLink).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot locate link to 'One Way'");
			e.printStackTrace();
		}
	}
	
	public void inputFrom(String from) {
		System.out.println("From:" + from);
		try {
			driver.findElement(fromLocator).click();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot locate link to 'One Way'");
			e.printStackTrace();
		}
		sendText(fromInput, from);
		clickAction(dest_nm, 20);
	}
	
	public void inputTo(String to) {
		System.out.println("To:" + to);
		try {
			driver.findElement(toLocator).click();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot locate link to 'One Way'");
			e.printStackTrace();
		}
		sendText(toInput, to);
		clickAction(dest_nm_to, 20);
	}
	
	public void pickDate(String date ) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement day2Select = driver.findElement(By.xpath("//div[@id='monthToBind']//li[contains(@id,'" + date + "')]"));
		wait.until(ExpectedConditions.elementToBeClickable(day2Select));
		day2Select.click();
	}
	
}
