package AutomationProject1.EaseMyTrip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
		
		int numOfRows = sheet.getLastRowNum();
		int numOfCols = sheet.getRow(0).getLastCellNum();
		
		String[][] strData = new String[numOfRows][numOfCols];
		
		XSSFCell cell; 
		
		for (int r=1, i =0; r <numOfRows+1; i++, r++) {
			for ( int c=0; c < numOfCols; c++ ) {
				
				cell = sheet.getRow(r).getCell(c);
				
				switch(cell.getCellType()) {
				case STRING:
					strData[i][c] = sheet.getRow(r).getCell(c).getStringCellValue();
					break;
				case NUMERIC:
					strData[i][c] = String.valueOf(sheet.getRow(r).getCell(c).getNumericCellValue());
					break;
				case BLANK:
					System.out.println("Blank");
					break;
				default: System.out.println("Default");
				}
			}
		}
		wb.close();
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
	
	public static int countTestDataRows(String[][] data) {
		int numTestRows = (data.length);
		System.out.println("There are "+numTestRows+" rows of test data available.");
		return numTestRows+1;
	}
	
	public static String[] getTestDataByRowInd(String[][] rows, int rowInd) {
		
		return rows[rowInd];
	}
	
	public static void writeToExcel(String fileName, String sheetName, ArrayList<String> data) throws IOException {
		
		File file = new File(System.getProperty("user.dir") + fileName);
		FileInputStream fis = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.createSheet(sheetName);
		
		String headerData = "Flights"+"\t"+"Departure Time"+"\t"+"Arrival Time"+"\t"+"Price";
		XSSFRow row = sheet.createRow(0);
		String[] rowData = headerData.split("\t");
		for(int x=0; x<4; x++) {
			row.createCell(x).setCellValue(rowData[x]);
		}
		int numOfRows = 1;
		
		for(int i=0; i<data.size();i++) {
			row = sheet.createRow(numOfRows++);
			rowData = data.get(i).split("\t");
			for(int j=0; j<rowData.length; j++) {
				row.createCell(j).setCellValue(rowData[j]);
			}
			
//			row.createCell(0).setCellValue(elements1.get(i).getText());
//			row.createCell(1).setCellValue(elements2.get(i).getText());
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		fos.close();
		wb.close();
		
		System.out.println("Written successfully");
	}
	
}

