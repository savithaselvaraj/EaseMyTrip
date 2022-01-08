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
    @Test(priority = 0)
    public void testSearchFlights() {
 	   	HomePage.searchFlights();
    }
    

   @Test(priority = 1)
   public void testReadExcelFile() throws IOException {
	   FileIO.printData(FileIO.getExcelData());
   }

    
}
