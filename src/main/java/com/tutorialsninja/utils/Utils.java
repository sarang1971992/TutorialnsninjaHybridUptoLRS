package com.tutorialsninja.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utils {

	public static final int Implicit_Wait_Time = 15;
	public static final int PageLoad_time = 20;

	public static String generateTimeStamp() {

		Date date = new Date();
		String name = date.toString().replace(" ", "_").replace(":", "_");

		return "sarang" + name + "@gmail.com";
	}

	public static Object[][] getTestDataFromExcel(String sheetName) {

		File file = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(file);
			workbook = new XSSFWorkbook(fisExcel);
			;
		} catch (Throwable e) {
			e.printStackTrace();
		}

		// XSSFWorkbook workbook;
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {

			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < cols; j++) {

				XSSFCell cell = row.getCell(j);
				org.apache.poi.ss.usermodel.CellType cellType = cell.getCellType();

				switch (cellType) {

				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				default:
					break;

				}

			}

		}
		return data;
	}
	
	public static String captureScreenshots(WebDriver driver,String testName) {
		
	       File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	       String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
	       try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
	       return destinationScreenshotPath;
	}
}
