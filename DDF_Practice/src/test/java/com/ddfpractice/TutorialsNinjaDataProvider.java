package com.ddfpractice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class TutorialsNinjaDataProvider {
	@DataProvider(name = "excelData", parallel = true)
	public Object[][] excelDataProvider() throws IOException{
		 String fileName = "src/test/resources/TutorialsNinjaTestData.xlsx";
	     String sheetName = "Sheet1";
	     
	     Object[][] arrObj = getExcelData(fileName, sheetName);
	     return arrObj;
	}
	
	public String[][] getExcelData(String fileName, String sheetName) throws IOException{
		String[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(0);
			
			int rows = sheet.getPhysicalNumberOfRows();
			int cols = row.getLastCellNum();
			
			Cell cell;
			data = new String[rows-1][cols];
			
			for(int i=1; i<rows; i++) {
				for(int j=0; j<cols; j++) {
					row = sheet.getRow(i);
					cell = row.getCell(j);
					data[i-1][j] = cell.getStringCellValue();
				}
			}
			
		}catch(Exception e) {
			System.out.println("The exception is:" +e.getMessage());
		}
		return data;
	}
}
