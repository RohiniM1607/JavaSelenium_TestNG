package com.utilities;

import java.io.FileInputStream;
import java.util.Arrays;

import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.DataProvider;

public class Data_Provider {

    @DataProvider(name="ValidexcelData", parallel = true)
    public Object[][] ValidexcelDataProvider() throws Exception {
        String[][] data = getExcelData("src\\test\\resources\\Book1.xlsx", "Sheet1");

        return Arrays.stream(data)
                .filter(row -> row[2].equalsIgnoreCase("valid"))
                .map(row -> new Object[]{row[0], row[1]})
                .toArray(Object[][]::new);
    }

    @DataProvider(name="InValidexcelData", parallel = true)
    public Object[][] InvalidexcelDataProvider() throws Exception {
        String[][] data = getExcelData("src\\test\\resources\\Book1.xlsx", "Sheet1");

        return Arrays.stream(data)
                .filter(row -> row[2].equalsIgnoreCase("invalid"))
                .map(row -> new Object[]{row[0], row[1]})
                .toArray(Object[][]::new);
    }

    private String[][] getExcelData(String fileName, String sheetName) {

        String[][] data = null;

        try {
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getLastCellNum();

            data = new String[rows - 1][cols];

            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
                }
            }

            workbook.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}