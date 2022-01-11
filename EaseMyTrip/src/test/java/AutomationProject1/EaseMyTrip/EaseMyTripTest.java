package AutomationProject1.EaseMyTrip;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Unit test for Ease My Trip Website.
 */
public class EaseMyTripTest extends BaseUI {
	
	private WebDriver driver;
	
	public EaseMyTripTest() {
		super();
	}	
	
	@Test(priority = 0)
	public void openBrowser() {
		driver = invokeBrowser();
	}
	
	@DataProvider
	public Object[][] flightSearchData() throws IOException{
		Object[][] testData = FileIO.getExcelData();
		return testData;
	}
	
    @Test(dataProvider = "flightSearchData", priority = 4)
    public void testSearchFlights
    (String fromStr, String toStr, String dDateStr, String tclass, String adult, String child, String infant )  
    		throws IOException {
		
    	this.driver.navigate().to(prop.getProperty("websiteURL"));
    	HomePage homePage = new HomePage(driver);
 	   	homePage.searchFlights(fromStr, toStr, dDateStr, tclass, adult, child, infant ) ;
 	   	FlightList fl = new FlightList(driver);
 	   	ArrayList<String> result = fl.getFlights();
 	    screenShots();
 	    String timeStamp = new SimpleDateFormat("MM-dd HH-mm").format(new Date());
 	   	String sheetName = fromStr+"-"+toStr+"-"+timeStamp;
 	   	FileIO.writeToExcel(prop.getProperty("filePath"), sheetName, result);
    }
    
    @Test(priority = 6)
	public void tearDown() {
		closeBrowser();
	}
    
}
	
	/*
	 * 
	 	@BeforeTest
	public void setUp() {
		driver = invokeBrowser();
    	this.driver.navigate().to(prop.getProperty("websiteURL"));
	}

	@Test(priority = 1)
	public void verifyPageTitle() {
		
		String title = driver.getTitle();
		Assert.assertEquals(title,prop.getProperty("pageTitle"));
	}
	
    @Test(priority = 4, description = "Checking for flights from Mumbai to chennai on Jan 23rd with 4 adults, 3 children and 2 infants.")
    public void testSearchFlights() throws IOException {
    	String[] testData = FileIO.getTestDataByRowInd(FileIO.getExcelData(), 1);
 	   	HomePage homePage = new HomePage(driver);
 	   	homePage.searchFlights(testData);
    }
    
    @Test(priority = 5)
    public void readFlightsList() throws IOException {
    	
       FlightList fl = new FlightList(driver);
 	   ArrayList<String> result = fl.getFlights();
 	   FileIO.writeToExcel(prop.getProperty("filePath"), prop.getProperty("writeSheetName"), result);
 	   
    }
        @AfterTest
    public void tearDown() {
		BaseUI.closeBrowser();
	
    */

