package AutomationProject1.EaseMyTrip;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import java.io.IOException;

/**
 * Unit test for Ease My Trip Website.
 */
public class EaseMyTripTests extends BaseUI {
	
	private WebDriver driver;
	
    @Test(priority = 0)
	public void setUp() {
		driver = invokeBrowser();
		getURL();
	}
    
    
    @Test(priority = 4, description = "Checking for flights from Mumbai to chennai on Jan 23rd with 4 adults, 3 children and 2 infants.")
    public void testSearchFlights() {
 	   	HomePage homePage = new HomePage(driver);
 	   homePage.searchFlights("Mum", "Chennai", "1/3/2023", "Economy", new int[]{4,3,2});
    }
    

   @Test(priority = 5)
   public void testReadExcelFile() throws IOException {
	   FileIO.printData(FileIO.getExcelData());
   }

    
}
