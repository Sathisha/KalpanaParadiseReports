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

public class VoucherExcelReader {
	public List<VoucherData> readExcel(String fileName, String yymm) throws IOException {
		String excelFilePath = fileName; //"D:\\KalpanaParadise\\Apr-2019.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(1);
		Iterator<Row> iterator = firstSheet.iterator();

		System.out.println("firstSheet.getPhysicalNumberOfRows(-)" + firstSheet.getPhysicalNumberOfRows());
		List<VoucherData> dataList = new ArrayList<VoucherData>();

		for (int i = 6; i < 30 + 6; i++) {
			try {

				VoucherData vData = new VoucherData();
				Row row = firstSheet.getRow(i);
				if (row == null) continue;
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				
				vData.setvDate(dateFormat.format(row.getCell(1).getDateCellValue()));
				vData.setvOnAccountOf(row.getCell(2).getStringCellValue());
				vData.setvAmountDigit((int)row.getCell(3).getNumericCellValue());
				vData.setvAmountText(NumberInWords.convert(vData.getvAmountDigit()));
				vData.setvAmountText(vData.getvAmountText().substring(0, 1).toUpperCase() + vData.getvAmountText().substring(1) + " only");
				vData.setvCreditOrDebit("Debit by " + row.getCell(4).getStringCellValue());
				
				System.out.println("Data read...:" + vData.toString());
				dataList.add(vData);
			}catch(NullPointerException npe) {
				System.out.println("NPE occured - ignoring row");
				//npe.printStackTrace();
			}
			
			
		}
		
		inputStream.close();
		
		
		
		
		return dataList;
	}

}
