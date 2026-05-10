package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	public static Object[][] getFilteredData(String filePath, String sheetName, String requiredType) throws IOException {

		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = (Sheet) workbook.getSheet(sheetName);

		Iterator<Row> rows = sheet.iterator();
		List<Object[]> dataList = new ArrayList<>();

		rows.next(); // skip header

		while (rows.hasNext()) {
			Row row = rows.next();

			String type = row.getCell(0).getStringCellValue();
			String username = row.getCell(1).getStringCellValue();
			String password = row.getCell(2).getStringCellValue();

			if (type.equalsIgnoreCase(requiredType)) {
				dataList.add(new Object[] { type, username, password });
			}
		}

		workbook.close();
		return dataList.toArray(new Object[0][]);
	}
}
