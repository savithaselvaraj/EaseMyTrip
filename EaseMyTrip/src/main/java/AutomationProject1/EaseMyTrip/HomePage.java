package AutomationProject1.EaseMyTrip;

import java.time.Month;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BaseUI {

	private WebDriver driver;
	
	By fromInput = getLocator("fromInput_id");
	By toInput = getLocator("toInput_id");
	By travellerDropDown = getLocator("Travellerdropddown_xpath");
	By travelClass = getLocator("travelClass_xpath");
	By tripDoneBtn = getLocator("tripDoneBtn_xpath");
	By searchBtn = getLocator("searchBtn_xpath");
	
	
	By AdultV = getLocator("Adultvalue_id");
	By Adultminus = getLocator("Adultminus_xpath");
	By Adultplus = getLocator("Adultplus_xpath");
	
	By ChildrenV = getLocator("Childrenvalue_id");
	By Childrenminus = getLocator("Childrenminus_xpath");
	By Childrenplus = getLocator("Childrenplus_xpath");
	
	By infantsv = getLocator("infantsvalue_id");
	By infantsminus = getLocator("infantsminus_xpath");
	By infantsplus = getLocator("infantsplus_xpath");
	
	By travelerDone = getLocator("travelerDone_xpath");
	
	By departureDate = getLocator("departureDate_id");
	By labelMonth1 = getLocator("labelMonth1_xpath");
	By labelMonth2 = getLocator("labelMonth2_xpath");
	By nextMonth = getLocator("nextMonth_id");
	
	
	public HomePage() {
		
	}
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void searchFlights(String fromStr, String toStr, String dDateStr, String tclass, String adult, String child, String infant ) {

		String fromCity = fromStr;
		String toCity = toStr;
		String departDate = dDateStr;
		String travClass = tclass;
		int adultTravlers = Integer.parseInt(adult.replaceAll(".0", ""));
		int childTravelers = Integer.valueOf(child.replaceAll(".0", ""));
		int infantTravelers = Integer.valueOf(infant.replaceAll(".0", ""));
		
		clickOn(fromInput, 10);
		sendText(fromInput,fromCity);
		
		WebElement from = driver.findElement(By.xpath("//div[@id='fromautoFill']//span[@class='ct'][contains(text(),'" + fromCity + "')]"));
		from.click();
		
		clickOn(toInput, 10);
		sendText(toInput,toCity);

		WebElement to = driver.findElement(By.xpath("//div[@id='toautoFill']//span[@class='ct'][contains(text(),'" + toCity + "')]"));
		to.click();

		selectDepartureDate(departDate);

		clickOn(travellerDropDown, 10);

		travellers(adultTravlers, childTravelers, infantTravelers);

		clickOn(travelClass, 10);
		
		driver.findElement(By.xpath("//label[contains(text(),'" + travClass + "')]/input[@name='optClass']")).click();
		
		clickOn(tripDoneBtn,10);
		clickOn(searchBtn,10);


	}

	public void travellers(int adult, int children, int infants) {

		WebElement Adultvalue = driver.findElement(AdultV);
		int displayvalue = Integer.parseInt(Adultvalue.getAttribute("value"));

		while (displayvalue > adult) {
			clickOn(Adultminus, 10);
			displayvalue = Integer.parseInt(Adultvalue.getAttribute("value"));
		}
		
		while (displayvalue < adult) {
			clickOn(Adultplus, 10);
			displayvalue = Integer.parseInt(Adultvalue.getAttribute("value"));
		}

		WebElement Childrenvalue = driver.findElement(ChildrenV);
		displayvalue = Integer.parseInt(Childrenvalue.getAttribute("value"));
		
		while (displayvalue > children) {
			clickOn(Childrenminus, 10);
			displayvalue = Integer.parseInt(Childrenvalue.getAttribute("value"));
		}
		
		while (displayvalue < children) {
			clickOn(Childrenplus, 10);
			displayvalue = Integer.parseInt(Childrenvalue.getAttribute("value"));
		}

		WebElement infantsvalue = driver.findElement(infantsv);
		displayvalue = Integer.parseInt(infantsvalue.getAttribute("value"));
		
		while (displayvalue > infants) {
			clickOn(infantsminus, 10);
			displayvalue = Integer.parseInt(infantsvalue.getAttribute("value"));
		}
		
		while (displayvalue < infants) {
			clickOn(infantsplus, 10);
			displayvalue = Integer.parseInt(infantsvalue.getAttribute("value"));
		}

		clickOn(travelerDone, 10);
	}

	private void selectDepartureDate(String dateToSelect) {
		
		clickOn(departureDate, 60);
		
		String month1 = getText(labelMonth1).trim();
		String month2 = getText(labelMonth2).strip();

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
					clickOn(nextMonth, 10);
					month2 = getText(labelMonth2).strip();
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
		By day2Select = By.xpath("//li[contains(@id,'" + dayId.toString() + "')]");
		clickOn(day2Select, 10);

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
