package AutomationProject1.EaseMyTrip;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import java.io.IOException;

public class AppTest extends BaseUI
{
	private WebDriver driver;
	
    @Test(priority = 0)
	public void setUp() {
		driver = invokeBrowser();
		getURL();
	}
   
    @Test(priority = 1)
    public void NavToFlights() {
    	HomePage homepage = new HomePage(driver);
    	homepage.navToFlights();
    }
    
    @Test(priority = 1)
    public void OneWayFlight() {
    	FlightsPage flightpage = new FlightsPage(driver);
    	flightpage.oneWay();
    }
    
    @Test(priority = 2)    
	public void searchFlights() throws IOException {
		
    	FlightsPage flightpage = new FlightsPage(driver);
    	String[] testData = FileIO.getTestDataByRowInd(FileIO.getExcelData(), 1);
		
		String from = testData[0];
		String to = testData[1];
		String date = testData[2];
		String adults = testData[3];
		String children = testData[4];
		String infants = testData[5];
		String tclass =testData[6];
		
		System.out.println("Unpacked Test Data:");
		System.out.println(from+"\t"+to+"\t"+date+"\t"+adults+"\t"+children+"\t"+infants+"\t"+tclass);
		
		flightpage.inputFrom(from);
		flightpage.inputTo(to);
		flightpage.pickDate(date);
	}
    /*
    
    @Test(priority = 9)
	public void testTearDown()
    {
    	BaseUI.tearDown();
    }

    @Test(priority = 4)
    public void testSearchFlights() {
    	HomePage.searchFlights("Mum", "Chennai", "1/3/2023", "Economy", new int[]{4,3,2});
    }

       
   @Test(priority = 10)
   public void testReadExcelFile() throws IOException {
	   FileIO.printData(FileIO.getExcelData());
   }
    */
    
}
