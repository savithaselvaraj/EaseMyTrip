package AutomationProject1.EaseMyTrip;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightList {
	
	private WebDriver driver;
	
	public FlightList(WebDriver driver) {
		this.driver = driver;
	}

	public ArrayList<String> getFlights() {
		
		System.out.println(driver.getCurrentUrl());

		List<WebElement> flightNames=(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='txt-r4 ng-binding']")));

		List<WebElement> flightTimes =  driver.findElements(By.xpath("//span[@class='txt-r2-n ng-binding']"));
		
		List<WebElement> flightPrices = driver.findElements(By.xpath("//div[contains(@id,'spnPrice')]"));
		
		//System.out.println("There are "+flightNames.size()+" flights.");
		//System.out.println("There are "+flightTimes.size()+" flight timings");
		
		ArrayList<String> matchingRows = new ArrayList<>();
		
		for(int i=0, j=0; i<flightNames.size();i++,j=j+2) {
			//System.out.print(flightNames.get(i).getText()+"\t"+flightTimes.get(j).getText()+"\t"+flightTimes.get(j+1).getText()+"\t"+flightPrices.get(j).getText());
			//System.out.println();
			matchingRows.add(flightNames.get(i).getText()+"\t"+flightTimes.get(j).getText()+"\t"+flightTimes.get(j+1).getText()+"\t"+flightPrices.get(j).getText());
		}
		
		return matchingRows;
	}	
}
