package AutomationProject1.EaseMyTrip;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.AssertJUnit;

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
    @Test(priority = 0)
	public void testSetUp()
    {
    	BaseUI.setUp();
    }
    @Test(priority = 1)
	public void testInvokeBrowser()
    {
    	BaseUI.invokeBrowser();
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
   @Test(priority = 4)
   public void testReadExcelFile() throws IOException {
	   FileIO.printData(FileIO.getExcelData());
   }
    
}
