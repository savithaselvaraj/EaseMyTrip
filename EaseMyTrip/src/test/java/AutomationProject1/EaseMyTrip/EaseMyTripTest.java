package AutomationProject1.EaseMyTrip;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Unit test for Ease My Trip Website.
 */
public class EaseMyTripTest extends BaseUI {
	
	private WebDriver driver;
	
	public EaseMyTripTest() {
		super();
	}

	@Test(priority = 0)
	public void setUp() {
		driver = invokeBrowser();
		getURL();
	}
    
    
    @Test(priority = 4, description = "Checking for flights from Mumbai to chennai on Jan 23rd with 4 adults, 3 children and 2 infants.")
    public void testSearchFlights() throws IOException {
 	   	HomePage homePage = new HomePage(driver);
 	   	
    	String[] testData = FileIO.getTestDataByRowInd(FileIO.getExcelData(), 1);
	
//		System.out.println("Unpacked Test Data:");
//		System.out.println(from+"\t"+to+"\t"+date+"\t"+travellers[0]+"\t"+travellers[1]+"\t"+travellers[2]+"\t"+tclass);
 	   	
 	   homePage.searchFlights(testData);
    }
    
    @Test(priority = 5)
    public void readFlightsList() throws IOException {
    	
		FlightList fl = new FlightList(driver);
 	   	ArrayList<String> result = fl.getFlights();
 	   FileIO.writeToExcel(prop.getProperty("filePath"), prop.getProperty("writeSheetName"), result);
    }
    

   @Test(priority = 6)
   public void testReadExcelFile() throws IOException {
	   FileIO.printData(FileIO.getExcelData());
   }

    
}
