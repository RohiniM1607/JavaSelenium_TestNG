package com.test.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.DataProvider;

public class DPExcel {

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() throws IOException {
        return getExcelData(
                "D:\\EXPLEO - SmartCliff_Training\\Git\\TestNG\\DDF_Practice\\src\\test\\resources\\TestData.xlsx",
                "Sheet1");
    }
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() throws IOException {
        return getExcelData(
                "D:\\EXPLEO - SmartCliff_Training\\Git\\TestNG\\DDF_Practice\\src\\test\\resources\\TestData.xlsx",
                "Sheet2");
    }

    @DataProvider(name = "searchData")
    public Object[][] searchDataProvider() throws IOException {
        return getExcelData(
                "D:\\EXPLEO - SmartCliff_Training\\Git\\TestNG\\DDF_Practice\\src\\test\\resources\\TestData.xlsx",
                "Sheet3");
    }

    public Object[][] getExcelData(String fileName, String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        XSSFRow row = sheet.getRow(0);

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = row.getPhysicalNumberOfCells(); 

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) {
            row = sheet.getRow(i);

            for (int j = 0; j < cols; j++) {

                Cell cell = row.getCell(j);
                data[i - 1][j] = (cell != null) ? cell.toString() : "";
            }
        }

        workbook.close();
        fis.close();

        return data;
    }
}