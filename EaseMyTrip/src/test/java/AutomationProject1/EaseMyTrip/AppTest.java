package AutomationProject1.EaseMyTrip;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	
   /* public AppTest( String testName )
    {
    }*/

    /**
     * Rigourous Test :-)
     */
	/*
    @Test(priority = 0)
	public void testSetUp()
    {
    	BaseUI.setUp();
    }
    @Test(priority = 1)
	public void testInvokeBrowser()
    {
    	WebDriver driver = BaseUI.invokeBrowser();
    }
    @Test(priority = 2)
	public void testGetURL()
    {
    	BaseUI.getURL();
    }
    
    @Test(priority = 3)
	public void testTearDown()
    {
    	BaseUI.tearDown();
    }
    */
    @Test(priority = 4, description = "Checking for flights from Mumbai to chennai on Jan 23rd with 4 adults, 3 children and 2 infants.")
    public void testSearchFlights() {
 	   	HomePage.searchFlights("Mum", "Chennai", "1/3/2023", "Economy", new int[]{4,3,2});
    }
    

   @Test(priority = 5)
   public void testReadExcelFile() throws IOException {
	   FileIO.printData(FileIO.getExcelData());
   }

    
}
