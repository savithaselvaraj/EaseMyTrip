package AutomationProject1.EaseMyTrip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class FileIO {

	private static Properties prop;
	
	public static Properties initProperties() {
		if(prop == null) {
			prop = new Properties();
			String userDir = System.getProperty("user.dir");
			try {
				FileInputStream fis = new FileInputStream(userDir + "/src/test/resources/objectrepository/config.properties");
				prop.load(fis);
				System.out.println("Properties Loaded");
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
		return prop;
	}
	
	
	public static String[][] getExcelData() throws IOException{
		
		String userDir = System.getProperty("user.dir");
		String filePath = userDir + prop.getProperty("filePath");
		String sheetName = prop.getProperty("readSheetName");
		
		System.out.println("File path: "+filePath);
		System.out.println("SheetName: "+sheetName);
		
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		
		int numOfRows = sheet.getLastRowNum()+1;
		int numOfCols = sheet.getRow(0).getLastCellNum();
		
		String[][] strData = new String[numOfRows][numOfCols];
		
		XSSFCell cell; 
		
		for (int r=0; r <numOfRows; r++) {
			for ( int c=0; c < numOfCols; c++ ) {
				
				cell = sheet.getRow(r).getCell(c);
				
				switch(cell.getCellType()) {
				case STRING:
					strData[r][c] = sheet.getRow(r).getCell(c).getStringCellValue();
					break;
				case NUMERIC:
					strData[r][c] = String.valueOf(sheet.getRow(r).getCell(c).getNumericCellValue());
					break;
				case BLANK:
					System.out.println("Blank");
					break;
				default: System.out.println("Default");
				}
			}
		}
		
		return strData;
	}
	
	public static void printData(String[][] data){
		for(String[] row: data) {
			for(String cell: row) {
				System.out.print(cell+" ");
			}
			System.out.println();
		}
	}
	
	
	public static void writeToExcel(String filePath, String sheetName, List<WebElement> elements1, List<WebElement> elements2) throws IOException {
		
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		
		int numOfRows = sheet.getLastRowNum()+1;
		
		for(int i=0; i<elements1.size();i++) {
			System.out.println(elements1.get(i).getText());
			System.out.println(elements2.get(i).getText());
			
			XSSFRow row = sheet.createRow(numOfRows++);
			XSSFCell cell = row.createCell(0);
			
			row.createCell(0).setCellValue(elements1.get(i).getText());
			row.createCell(1).setCellValue(elements2.get(i).getText());
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		fos.close();
		
		System.out.println("Written successfully");
	}
	
}

