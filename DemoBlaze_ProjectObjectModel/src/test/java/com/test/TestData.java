package com.test;

import org.testng.annotations.DataProvider;

import com.utils.ExcelUtils;

public class TestData {
	@DataProvider(name = "excelData")
	public Object[][] getExcelData() throws Exception {

		String path = "D:\\EXPLEO - SmartCliff_Training\\Git\\TestNG\\DemoBlaze_ProjectObjectModel\\src\\test\\resources\\LoginData.xlsx";

		String type = System.getProperty("type", "valid");

		return ExcelUtils.getFilteredData(path, "Sheet1", type);
	}
}
