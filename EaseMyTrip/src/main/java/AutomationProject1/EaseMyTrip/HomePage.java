package AutomationProject1.EaseMyTrip;

import java.time.Month;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BaseUI {

	private WebDriver driver;
	
	By fromInput = getLocator("fromInput_id");
	By toInput = getLocator("toInput_id");
	By travellerDropDown = getLocator("Travellerdropddown_xpath");
	By travelClass = getLocator("travelClass_xpath");
	By tripDoneBtn = getLocator("tripDoneBtn_xpath");
	By searchBtn = getLocator("searchBtn_xpath");
	
	public HomePage() {
		
	}
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void searchFlights(String[] testData) {

		String fromCity = testData[0];
		String toCity = testData[1];
		String departureDate = testData[2];
		String travClass = testData[3];
		int adultTravlers = Integer.parseInt(testData[4].replaceAll(".0", ""));
		int childTravelers = Integer.valueOf(testData[5].replaceAll(".0", ""));
		int infantTravelers = Integer.valueOf(testData[6].replaceAll(".0", ""));
		
		clickOn(fromInput, 10);
		sendText(fromInput,fromCity);
		
		WebElement from = driver.findElement(By.xpath("//div[@id='fromautoFill']//span[@class='ct'][contains(text(),'" + fromCity + "')]"));
		from.click();
		
		clickOn(toInput, 10);
		sendText(toInput,toCity);

		WebElement to = driver.findElement(By.xpath("//div[@id='toautoFill']//span[@class='ct'][contains(text(),'" + toCity + "')]"));
		to.click();

		selectDepartureDate(departureDate);

		clickOn(travellerDropDown, 10);

		travellers(adultTravlers, childTravelers, infantTravelers);

		clickOn(travelClass, 10);
		
		driver.findElement(By.xpath("//label[contains(text(),'" + travClass + "')]/input[@name='optClass']")).click();
		
		clickOn(tripDoneBtn,10);
		clickOn(searchBtn,10);


	}

	public void travellers(int adult, int children, int infants) {

		WebElement Adultvalue = driver.findElement(By.id("optAdult"));
		int displayvalue = Integer.parseInt(Adultvalue.getAttribute("value"));
		WebElement Adultminus = driver
				.findElement(By.xpath("//*[@id=\"myDropdown_n\"]/div/div[1]/div[2]/div/div[1]/input"));
		WebElement Adultplus = driver
				.findElement(By.xpath("//*[@id=\"myDropdown_n\"]/div/div[1]/div[2]/div/div[3]/input"));

		while (displayvalue < adult) {
			Adultplus.click();
			displayvalue = Integer.parseInt(Adultvalue.getAttribute("value"));
		}

		WebElement Childrenvalue = driver.findElement(By.id("optChild"));
		displayvalue = Integer.parseInt(Childrenvalue.getAttribute("value"));
		WebElement Childrenplus = driver
				.findElement(By.xpath("//*[@id=\"myDropdown_n\"]/div/div[2]/div[2]/div/div[3]/input"));

		while (displayvalue < children) {
			Childrenplus.click();
			displayvalue = Integer.parseInt(Childrenvalue.getAttribute("value"));
		}

		WebElement infantsvalue = driver.findElement(By.id("optInfant"));
		displayvalue = Integer.parseInt(infantsvalue.getAttribute("value"));
		WebElement infantsplus = driver
				.findElement(By.xpath("//*[@id=\"myDropdown_n\"]/div/div[3]/div[2]/div/div[3]/input"));

		while (displayvalue < infants) {
			infantsplus.click();
			displayvalue = Integer.parseInt(infantsvalue.getAttribute("value"));
		}

		driver.findElement(By.xpath("//*[@id=\"traveLer\"]")).click();
	}

	private void selectDepartureDate(String dateToSelect) {

		By departureDate = By.id("ddate");
		By labelMonth1 = By.xpath("//div[@class='box']//div[@class='month']");
		By labelMonth2 = By.xpath("//div[@class='box1']//div[@class='month2']");
		By nextMonth = By.id("img2Nex");

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(departureDate));
		driver.findElement(departureDate).click();
		String month1 = driver.findElement(labelMonth1).getText().trim();
		String month2 = driver.findElement(labelMonth2).getText().strip();

		int m1 = Month.valueOf(getFullMonthName(month1.split(" ")[0])).getValue();
		int m2 = Month.valueOf(getFullMonthName(month2.split(" ")[0])).getValue();

		int y1 = Integer.valueOf(month1.split(" ")[1]);
		int y2 = Integer.valueOf(month2.split(" ")[1]);

		int yearNeeded = Integer.valueOf(dateToSelect.split("/")[2]);
		int monthNeeded = Integer.valueOf(dateToSelect.split("/")[0]);
		int dayNeededd = Integer.valueOf(dateToSelect.split("/")[1]);

		if (y1 == yearNeeded && m1 == monthNeeded) {
			System.out.println("Month 1 should have the date");
		} else if (y2 == yearNeeded && m2 == monthNeeded) {
			System.out.println("Month 2 should have the date");
		} else {
			
			if (yearNeeded >= y2 || monthNeeded > m2) {
				// navigate to next month
				System.out.println("Need to navigate to next month");
				do {
					driver.findElement(nextMonth).click();
					month2 = driver.findElement(labelMonth2).getText().strip();
					m2 = Month.valueOf(getFullMonthName(month2.split(" ")[0])).getValue();
					y2 = Integer.valueOf(month2.split(" ")[1]);
					System.out.println(m2 + " " + y2 + " | " + monthNeeded + " " + yearNeeded + " " + (! (m2==monthNeeded && y2==yearNeeded)));
				} while (!(m2 == monthNeeded && y2 == yearNeeded));
			} else {
				// may be prior month?
			}
		}

		// click on the date to select
		// "//li[contains(@id,'_15/02/2022')]"
		StringBuffer dayId = new StringBuffer();
		dayId.append("_").append(String.format("%2s", dayNeededd).replace(' ', '0'))
				.append(String.format("/%2s", monthNeeded).replace(' ', '0')).append("/" + yearNeeded);
		WebElement day2Select = driver.findElement(By.xpath("//li[contains(@id,'" + dayId.toString() + "')]"));
		wait.until(ExpectedConditions.elementToBeClickable(day2Select));
		day2Select.click();

	}

	private String getFullMonthName(String abbreviatedMonthname) {
		switch (abbreviatedMonthname) {
		case "JAN":
			return "JANUARY";
		case "FEB":
			return "FEBRUARY";
		case "MAR":
			return "MARCH";
		case "APR":
			return "APRIL";
		case "MAY":
			return "MAY";
		case "JUN":
			return "JUNE";
		case "JUL":
			return "JULY";
		case "AUG":
			return "AUGUST";
		case "SEP":
			return "SEPTEMBER";
		case "OCT":
			return "OCTOBER";
		case "NOV":
			return "NOVEMBER";
		case "DEC":
			return "DECEMBER";
		}
		return "";
	}
}
