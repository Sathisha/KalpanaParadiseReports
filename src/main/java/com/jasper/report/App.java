package com.jasper.report;

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
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		 compileReport();
		//fillReport();

		printReport();
	}

	

	static void compileReport() {
		System.out.println("Compiling Report Design ...");
		try { /** * Compile the report to a file name same as * the JRXML file name */
			String sourceFileName = "D:\\workspace\\JasperReportProj\\Receipt.jrxml";
			JasperCompileManager.compileReportToFile(sourceFileName);
		} catch (JRException e) {
			e.printStackTrace();
		}
		System.out.println("Done compiling!!! ...");
	}

	static void fillReport() {
		String sourceFileName = "D:\\workspace\\JasperReportProj\\Receipt.jrxml";
		ArrayList<PeopleDataBean> PeopleDataBeanList = new ArrayList<PeopleDataBean>();
		PeopleDataBean bean = new PeopleDataBean();
		bean.setCity("city2");
		bean.setCountry("country342");
		bean.setName("name234546");
		bean.setState("state3245");
		PeopleDataBeanList.add(bean);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(PeopleDataBeanList);
		Map parameters = new HashMap();
		try {
			JasperFillManager.fillReportToFile(sourceFileName, parameters, beanColDataSource);
		} catch (JRException e) {
			e.printStackTrace();
		}

		System.out.println("Report fill done...");
	}
	private static void printReport() {
		String sourceFileName = "D:\\workspace\\JasperReportProj\\Receipt.jasper";
		String printFileName = null;
		ArrayList<PeopleDataBean> PeopleDataBeanList = new ArrayList<PeopleDataBean>();
		PeopleDataBean bean = new PeopleDataBean();
		bean.setCity("city 2");
		bean.setCountry("country 342");
		bean.setName("name23 4546");
		bean.setState("state3 245");
		PeopleDataBeanList.add(bean);
		bean = new PeopleDataBean();
		bean.setCity("city 3242");
		bean.setCountry("country 234342");
		bean.setName("name23 2344546");
		bean.setState("state3 234245");
		PeopleDataBeanList.add(bean);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(PeopleDataBeanList);
		Map parameters = new HashMap();
		try {
			printFileName = JasperFillManager.fillReportToFile(sourceFileName, parameters, beanColDataSource);
			System.out.println("Saving file");
			if (printFileName != null) {
				JasperExportManager.exportReportToPdfFile(printFileName, "D:\\workspace\\JasperReportProj\\Receipt.pdf");
			}
		} catch (JRException e) {
			e.printStackTrace();
		}

	}
}
