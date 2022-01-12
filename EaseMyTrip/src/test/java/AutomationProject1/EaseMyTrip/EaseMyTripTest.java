package AutomationProject1.EaseMyTrip;


import org.testng.Assert;
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
	public static int count=1;
	
	public EaseMyTripTest() {
		super();
	}	
	
	@Test(priority = 0)
	public void openBrowser() {
		driver = invokeBrowser();
	}
	
	@Test(priority = 1)
	public void verifyPageTitle() {
		driver = getURL();
		String title = driver.getTitle();
		Assert.assertEquals(title,prop.getProperty("pageTitle"));
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
 	   	String userDir = System.getProperty("user.dir");
 	   	ArrayList<String> result = fl.getFlights();
 	    String timeStamp = new SimpleDateFormat("MM-dd HH-mm").format(new Date());
 	    String fileName = userDir+prop.getProperty("screenshotsPath")+fromStr+"-"+toStr+(count++)+"-"+timeStamp+".jpg";
 	    screenShots(fileName);
 	   	String sheetName = fromStr+"-"+toStr+"-"+timeStamp;
 	   	FileIO.writeToExcel(prop.getProperty("filePath"), sheetName, result);
    }
    
    @Test(priority = 6)
	public void tearDown() {
		closeBrowser();
	}
    
}
	


