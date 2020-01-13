package com.qa.hubspot.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtil {

	//Write a method to read data form excel file
	
	static String TEST_DATA_SHEET_PATH="C:\\Users\\abhpraka\\eclipse-workspace\\NaveenPOMSeries\\src\\main\\java\\com\\qa\\hubspot\\testdata\\HubSpotTestData.xlsx";
	public static Workbook book;
	public static Sheet sheet;
	
	
	public static Object [][] getTestData(String sheetName) {
		try {
			FileInputStream file=new FileInputStream(TEST_DATA_SHEET_PATH);
			book=WorkbookFactory.create(file);
			sheet=book.getSheet(sheetName);
			
			Object data[][]=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0; i<sheet.getLastRowNum(); i++) {
				for (int k=0; k<sheet.getRow(0).getLastCellNum();k++) {
					data[i][k]=sheet.getRow(i+1).getCell(k).toString();//First Go to the second row and Zeroth column for i=0 and k=0 and so on
				}
			}
			
			return data;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
