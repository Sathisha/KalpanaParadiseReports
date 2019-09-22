package com.jasper.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Hello world!
 *
 */
public class ApartmentReceipt {
	public static void main(String[] args) {
		System.out.println("Start!");

		
		if (args == null || args.length <5) {
			System.out.println("Usage: \n Params: <YYMM>, <ReportName.jrxml>, <InputFileName>, <target folder>, <R/V=Receipt/Voucher>");
			return;
		}
		String yymm = args[0];
		String reportName = args[1];
		String InputFile = args[2];
		String target = args[3];
		String receiptOrVoucher = args[4];
		System.out.println("yymm:"+yymm);
		System.out.println("reportName:" + reportName);
		System.out.println("InputFile:" + InputFile);
		System.out.println("target:" + target);
		System.out.println("receiptOrVoucher:" + receiptOrVoucher);
		
		
		 //compileReport(reportName);

		if("R".contentEquals(receiptOrVoucher)) {
			printReceipt(yymm,reportName,InputFile,target);

		}else if ("V".contentEquals(receiptOrVoucher)) {
			printVoucher(yymm,reportName,InputFile,target);

		}else {
			System.out.println("Not valid input...");
		}
	}

	

	static void compileReport(String reportName) {
		System.out.println("Compiling Report Design ...");
		try { /** * Compile the report to a file name same as * the JRXML file name */
			String sourceFileName = reportName+".jrxml";
			JasperCompileManager.compileReportToFile(sourceFileName);
		} catch (JRException e) {
			e.printStackTrace();
		}
		System.out.println("Done compiling!!! ...");
	}


	private static void printReceipt(String yymm, String reportName, String inputFile, String target) {
		String sourceFileName = reportName+".jasper";
		String printFileName = null;
		ArrayList<ReceiptData> dataList = new ArrayList<ReceiptData>();
		ExcelReader reader = new ExcelReader();
		try {
			dataList = (ArrayList<ReceiptData>) reader.readExcel(inputFile, yymm);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (int i=0; i<dataList.size(); i++) {
			compileReport(reportName);
			
			ArrayList<ReceiptData> newDataList = new ArrayList<ReceiptData>();
			newDataList.add(dataList.get(i));
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(newDataList);
			Map parameters = new HashMap();
			try {
				printFileName = JasperFillManager.fillReportToFile(sourceFileName, parameters, beanColDataSource);
				//printFileName = bean.getAptNo();
				System.out.println("Saving file: " + target+yymm+"\\"+dataList.get(i).getSlNo()+".pdf");
				if (printFileName != null) {
					JasperExportManager.exportReportToPdfFile(printFileName, target+yymm+"\\"+dataList.get(i).getSlNo()+".pdf");
				}
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		
		

	}
	
	private static void printVoucher(String yymm, String reportName, String inputFile, String target) {
		String sourceFileName = reportName+".jasper";
		String printFileName = null;
		ArrayList<VoucherData> dataList = new ArrayList<VoucherData>();
		VoucherExcelReader reader = new VoucherExcelReader();
		try {
			dataList = (ArrayList<VoucherData>) reader.readExcel(inputFile, yymm);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		compileReport(reportName);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
		Map parameters = new HashMap();
		try {
			printFileName = JasperFillManager.fillReportToFile(sourceFileName, parameters, beanColDataSource);
			//printFileName = bean.getAptNo();
			System.out.println("Saving file: " + target+"\\Vouchers-"+yymm+".pdf");
			if (printFileName != null) {
				JasperExportManager.exportReportToPdfFile(printFileName,  target+"\\Vouchers-"+yymm+".pdf");
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		/*
		 * for (int i=0; i<dataList.size(); i++) {
		 * 
		 * ArrayList<VoucherData> newDataList = new ArrayList<VoucherData>();
		 * newDataList.add(dataList.get(i)); JRBeanCollectionDataSource
		 * beanColDataSource = new JRBeanCollectionDataSource(newDataList); Map
		 * parameters = new HashMap(); try { printFileName =
		 * JasperFillManager.fillReportToFile(sourceFileName, parameters,
		 * beanColDataSource); //printFileName = bean.getAptNo();
		 * System.out.println("Saving file: " +
		 * target+yymm+"\\"+dataList.get(i).getSlNo()+".pdf"); if (printFileName !=
		 * null) { JasperExportManager.exportReportToPdfFile(printFileName,
		 * target+yymm+"\\"+dataList.get(i).getSlNo()+".pdf"); } } catch (JRException e)
		 * { e.printStackTrace(); } }
		 */
		
		

	}
}
