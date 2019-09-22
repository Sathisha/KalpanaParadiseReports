package com.jasper.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.DataLine;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public List<ReceiptData> readExcel(String fileName, String yymm) throws IOException {
		String excelFilePath = fileName; //"D:\\KalpanaParadise\\Apr-2019.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		System.out.println("firstSheet.getPhysicalNumberOfRows(-)" + firstSheet.getPhysicalNumberOfRows());
		List<ReceiptData> dataList = new ArrayList<ReceiptData>();

		for (int i = 6; i < 26 + 6; i++) {
			try {

				ReceiptData data = new ReceiptData();
				Row row = firstSheet.getRow(i);
				if (row == null) continue;
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				data.setAptNo("Flat no. " + row.getCell(1).getStringCellValue());
				data.setPaymentDate(dateFormat.format(row.getCell(2).getDateCellValue()));
				data.setAmountDigit((int) row.getCell(3).getNumericCellValue());
				data.setPaymentType(row.getCell(4).getStringCellValue());
				data.setAmountText( NumberInWords.convert(data.getAmountDigit()) );
				data.setChequeNo(row.getCell(5).getStringCellValue());
				data.setPaymentFor(row.getCell(6).getStringCellValue());
				data.setAmountText(data.getAmountText().substring(0, 1).toUpperCase() + data.getAmountText().substring(1) + " only");
				data.setSlNo(yymm+"-"+row.getCell(1).getStringCellValue());
				if(data.getAmountDigit()==0) continue;
				System.out.println("Data read...:" + data.toString());
				dataList.add(data);
			}catch(NullPointerException npe) {
				System.out.println("NPE occured - ignoring row");
				npe.printStackTrace();
			}
			
			/*
			 * for (int j = 1; j < 7; j++) { Cell cell = row.getCell(j);
			 * 
			 * 
			 * switch (cell.getCellType()) { case STRING:
			 * System.out.print(cell.getStringCellValue()); break; case BOOLEAN:
			 * System.out.print(cell.getBooleanCellValue()); break; case NUMERIC:
			 * if(DateUtil.isCellDateFormatted(cell)) { //SimpleDateFormat dateFormat = new
			 * SimpleDateFormat("dd-MM-yyyy");
			 * System.out.print(dateFormat.format(cell.getDateCellValue()) );
			 * 
			 * }else { System.out.print(cell.getNumericCellValue()); }
			 * 
			 * 
			 * break; case BLANK: System.out.print(" "); break; } System.out.print("  -  ");
			 * }
			 */
			
		}
		
		inputStream.close();
		
		
		
		
		return dataList;
	}

}
