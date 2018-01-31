package com.appstek.dc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.appstek.dc.dto.ExcelFileDto;
import com.appstek.dc.service.GenerateReportService;

public class DataConversionExcelUtility {
	public static FileOutputStream outStream = null;
	public static XSSFWorkbook workbook = null;
	public static int secondTableRowCount;
	public static int thirdTableRowCount;

	public static FileOutputStream creatXLSFile(File file) {
		try {
			outStream = new FileOutputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outStream;
	}

	public static XSSFWorkbook createFirstTable(XSSFWorkbook workbook, List<ExcelFileDto> excelFileDtoList1, String editon,String countyName,String stateCode) {
		// Create a blank sheet
		//XSSFSheet sheet = workbook.createSheet("AK_Sitka_06");
		XSSFSheet sheet = workbook.createSheet(stateCode+"_"+countyName+"_"+editon);
		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		Font font = workbook.createFont();
		font.setFontName("Calibri");
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short) 10);
		style.setFont(font);
		Row row0 = sheet.createRow(0);
		Cell cell0 = row0.createCell(0);
		cell0.setCellValue(stateCode+" "+countyName.toUpperCase()+"_"+editon);
		//cell0.setCellValue("AK SITKA_06");
		//sheet.autoSizeColumn(0);
		cell0.setCellStyle(style);

		int rownum = 2;
		for (ExcelFileDto obj : excelFileDtoList1) {
			Row row = sheet.createRow(rownum++);
			// Setting Table Style Start - Lalitha

			CellStyle style1 = workbook.createCellStyle();
			Font font1 = workbook.createFont();
			font1.setFontName("Arial");
			font1.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
			font1.setFontHeightInPoints((short) 7);
			//row.setHeight((short) 200);
			style1.setFont(font1);
			style1.setVerticalAlignment(CellStyle.VERTICAL_TOP);
			style1.setAlignment(CellStyle.ALIGN_LEFT);
			style1.setWrapText(true);
			sheet.setColumnWidth(0, 4500);
			sheet.setColumnWidth(1, 4500);
			//row.setHeightInPoints(10);
			Cell cell_0 = row.createCell(0);
			Cell cell_1 = row.createCell(1);
			cell_0.setCellStyle(style1);
			cell_1.setCellStyle(style1);

			// Setting Table Style End- Lalitha

			cell_0.setCellValue(obj.getProperty1());
			cell_1.setCellValue(obj.getProperty2());
		}
		return workbook;
	}

	public static XSSFWorkbook createSecondTable(FileInputStream inputStream, List<ExcelFileDto> excelFileDtoList1, String editon,String countyName,String stateCode) {
		// AK_Sitka_06
		XSSFWorkbook workbook1 = null;
		try {
			workbook1 = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFCellStyle styleL = ExcelSettingBorderColorUtility.createLeftBorderStyle(workbook1);
		XSSFCellStyle styleR = ExcelSettingBorderColorUtility.createRightBorderStyle(workbook1);
		XSSFCellStyle styleLB = ExcelSettingBorderColorUtility.createLeftBottomBorderStyle(workbook1);
		XSSFCellStyle styleRB = ExcelSettingBorderColorUtility.createRightBottomBorderStyle(workbook1);
		XSSFCellStyle styleB = ExcelSettingBorderColorUtility.createBottomBorderStyle(workbook1);
		XSSFCellStyle styleLTB = ExcelSettingBorderColorUtility.createLeftTopBottomBorderStyle(workbook1);
		XSSFCellStyle styleTB = ExcelSettingBorderColorUtility.createTopBottomBorderStyle(workbook1);
		XSSFCellStyle styleRTB = ExcelSettingBorderColorUtility.createRightTopBottomBorderStyle(workbook1);
		XSSFCellStyle styleLRTB = ExcelSettingBorderColorUtility.createLeftRightTopBottomBorderStyle(workbook1);
		XSSFCellStyle styleLB6 = ExcelSettingBorderColorUtility.createLeftBottomBorderStyle(workbook1);
		styleL.setWrapText(true);
		styleR.setWrapText(true);
		styleLB.setWrapText(true);
		styleRB.setWrapText(true);
		styleB.setWrapText(true);
		styleLTB.setWrapText(true);
		styleTB.setWrapText(true);
		styleRTB.setWrapText(true);
		styleLRTB.setWrapText(true);
		styleLB6.setWrapText(true);
		//XSSFSheet sheet = workbook1.getSheet("AK_Sitka_06");
		XSSFSheet sheet = workbook1.getSheet(stateCode+"_"+countyName+"_"+editon);
		Row row1 = sheet.createRow(1);
		row1.setHeightInPoints(15);
		ExcelFileDto excelFileDto = new ExcelFileDto();
		excelFileDto.setHearder1("Source Data Verification Questions:");
		excelFileDto.setHearder2("Y/N");
		Cell cell5 = row1.createCell(5);
		Cell cell6 = row1.createCell(6);
		Cell cell7 = row1.createCell(7);
		styleRTB.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		styleRTB.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleLB.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleLB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleRB.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleRB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleL.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleR.setBottomBorderColor(IndexedColors.BLACK.getIndex());

		styleLRTB.setFillForegroundColor(ExcelColorUtility.MORTAR);
		styleLRTB.setFillPattern(CellStyle.SOLID_FOREGROUND);

		styleLTB.setFillForegroundColor(ExcelColorUtility.MORTAR);
		styleLTB.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleLRTB.setAlignment(CellStyle.ALIGN_CENTER);
		styleLRTB.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleLTB.setAlignment(CellStyle.ALIGN_CENTER);
		styleLTB.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleTB.setAlignment(CellStyle.ALIGN_CENTER);
		styleTB.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		XSSFFont font = workbook1.createFont();
		font.setFontHeightInPoints((short) 7);
		font.setFontName("Arial");
		font.setColor(IndexedColors.WHITE.getIndex());
		font.setBold(true);

		styleLTB.setFont(font);
		styleRTB.setFont(font);
		styleLRTB.setFont(font);
		cell5.setCellValue(excelFileDto.getHearder1());
		cell5.setCellStyle(styleLTB);

		// Blank text1, lighter 35%
		styleTB.setFillForegroundColor(ExcelColorUtility.MORTAR);
		styleTB.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell6.setCellValue("");
		cell6.setCellStyle(styleTB);

		styleRTB.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		styleRTB.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell7.setCellValue(excelFileDto.getHearder2());
		cell7.setCellStyle(styleLRTB);

		secondTableRowCount = excelFileDtoList1.size();
		int rownum = 2;
		int i = 0;
		int j = 0;
		styleL.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
		styleL.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleB.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
		styleB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleR.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
		styleR.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleR.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
		styleR.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		styleLB.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
		styleLB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleRB.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
		styleRB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleLB6.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
		styleLB6.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		for (ExcelFileDto obj : excelFileDtoList1) {
			CellStyle style_a = workbook1.createCellStyle();
			Font font1 = workbook1.createFont();
			font1.setFontHeightInPoints((short) 7);
			font1.setFontName("Arial");
			style_a.setFont(font1);
			style_a.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			style_a.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style_a.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
			style_a.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style_a.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
			style_a.setRightBorderColor(IndexedColors.BLACK.getIndex());
			i = i + 1;
			j = j + 1;
			Row row = sheet.getRow(rownum);

			if (row != null) {
				Cell cell_5 = row.createCell(5);
				Cell cell_6 = row.createCell(6);
				Cell cell_7 = row.createCell(7);
				sheet.setColumnWidth(5, 13000);
				cell_5.setCellStyle(style_a);
				cell_6.setCellStyle(style_a);
				cell_7.setCellStyle(style_a);
				if (i != excelFileDtoList1.size()) {
					if (j == 7) {
						cell_5.setCellValue("        a" + "." + obj.getProperty1());
						cell_7.setCellValue(obj.getProperty2());
						i = 6;
					} else if (j == 8) {
						cell_5.setCellValue("        b" + "." + obj.getProperty1());
						cell_7.setCellValue(obj.getProperty2());
						i = 6;
					} else {
						cell_5.setCellValue(i + ".   " + obj.getProperty1());
						cell_7.setCellValue(obj.getProperty2());
					}
				} else if (i == excelFileDtoList1.size()) {
					styleB.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
					styleB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
					cell_5.setCellStyle(styleLB);
					cell_6.setCellStyle(styleLB6);
					cell_7.setCellStyle(styleRB);
				} else {
					cell_5.setCellStyle(styleL);
					cell_6.setCellStyle(styleB);
					cell_7.setCellStyle(styleR);
				}
			} else {
				Row newrow = sheet.createRow(rownum);
			//	newrow.setHeightInPoints(10);
				Cell cell_5 = newrow.createCell(5);
				Cell cell_6 = newrow.createCell(6);
				Cell cell_7 = newrow.createCell(7);
				style_a.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
				cell_5.setCellStyle(style_a);
				cell_6.setCellStyle(style_a);
				cell_7.setCellStyle(style_a);
				sheet.setColumnWidth(5, 13000);
			//	newrow.setHeight((short) 10);
				if (i != excelFileDtoList1.size()) {
					if (j == 7) {
						cell_5.setCellValue("  a" + "." + obj.getProperty1());
						cell_7.setCellValue(obj.getProperty2());
						i = 6;
					} else if (j == 8) {
						cell_5.setCellValue("  b" + "." + obj.getProperty1());
						cell_7.setCellValue(obj.getProperty2());
						i = 6;
					} else {
						cell_5.setCellValue(i + "." + obj.getProperty1());
						cell_7.setCellValue(obj.getProperty2());
					}
				} else if (i == excelFileDtoList1.size()) {
					styleB.setBorderBottom(XSSFCellStyle.BORDER_THIN);
					styleB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
					cell_5.setCellStyle(styleLB);
					cell_6.setCellStyle(styleLB6);
					cell_7.setCellStyle(styleRB);
				} else {
					cell_5.setCellStyle(styleL);
					cell_6.setCellStyle(styleB);
					cell_7.setCellStyle(styleR);
				}
			}
			row = null;

			rownum = rownum + 1;
		}

		return workbook1;
	}

	public static XSSFWorkbook createThirdTable(FileInputStream inputStream, List<ExcelFileDto> excelFileDtoList1, String editon,String countyName,String stateCode) {
		XSSFWorkbook workbook1 = null;
		try {
			workbook1 = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workbook1.getSheet(stateCode+"_"+countyName+"_"+editon);
		//XSSFSheet sheet = workbook1.getSheet("AK_Sitka_06");
		sheet.setColumnWidth(5, 13000);
		XSSFCellStyle styleLTB = ExcelSettingBorderColorUtility.createLeftTopBottomBorderStyle(workbook1);

		XSSFCellStyle styleL = ExcelSettingBorderColorUtility.createLeftBorderStyle(workbook1);
		XSSFCellStyle styleR = ExcelSettingBorderColorUtility.createRightBorderStyle(workbook1);
		XSSFCellStyle styleLB = ExcelSettingBorderColorUtility.createLeftBottomBorderStyle(workbook1);
		XSSFCellStyle styleRB = ExcelSettingBorderColorUtility.createRightBottomBorderStyle(workbook1);
		XSSFCellStyle styleRTB = ExcelSettingBorderColorUtility.createRightTopBottomBorderStyle(workbook1);
		styleL.setWrapText(true);
		styleR.setWrapText(true);
		styleLB.setWrapText(true);
		styleRB.setWrapText(true);
		styleRTB.setWrapText(true);

		styleLTB.setFillForegroundColor(ExcelColorUtility.MORTAR);
		styleLTB.setFillPattern(CellStyle.SOLID_FOREGROUND);
		XSSFFont font = workbook1.createFont();
		font.setFontHeightInPoints((short) 7);
		font.setFontName("Arial");
		font.setColor(IndexedColors.WHITE.getIndex());
		font.setBold(true);

		styleRTB.setFillForegroundColor(ExcelColorUtility.MORTAR);
		styleRTB.setFillPattern(CellStyle.SOLID_FOREGROUND);

		styleLTB.setFont(font);
		styleRTB.setFont(font);

		Row row1 = sheet.getRow(1);
		//row1.setHeightInPoints(15);
		if (row1 != null) {
			Cell cell8 = row1.createCell(8);
			Cell cell9 = row1.createCell(9);
			Cell cell10 = row1.createCell(10);
			ExcelFileDto excelFileDto = new ExcelFileDto();
			excelFileDto.setHearder1("List of Source File Name:");
			excelFileDto.setHearder2("Record Count:");
			Cell cell11 = row1.createCell(11);
			Cell cell12 = row1.createCell(12);
			cell11.setCellValue(excelFileDto.getHearder1());
			cell11.setCellStyle(styleLTB);
			cell12.setCellValue(excelFileDto.getHearder2());
			cell12.setCellStyle(styleRTB);
		}

		// System.out.println("Size "+excelFileDtoList1.size());
		thirdTableRowCount = excelFileDtoList1.size();
		int rownum = 2;
		int i = 0;
		// styleLTB,styleRTB
		styleR.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleR.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleL.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleL.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleL.setBorderRight(XSSFCellStyle.BORDER_THIN);
		styleL.setRightBorderColor(IndexedColors.BLACK.getIndex());

		styleRB.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
		styleRB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleLB.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
		styleLB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleLB.setBorderRight(XSSFCellStyle.BORDER_THIN);
		styleLB.setRightBorderColor(IndexedColors.BLACK.getIndex());

		for (ExcelFileDto obj : excelFileDtoList1) {
			i++;
			// System.out.println("I Size:"+i);
			Row row = sheet.getRow(rownum);
			sheet.setColumnWidth(11, 12000);
			sheet.setColumnWidth(12, 4000);
			CellStyle style_a = workbook1.createCellStyle();
			style_a.setWrapText(true);
			Font font1 = workbook1.createFont();
			font1.setFontHeightInPoints((short) 7);
			font1.setFontName("Arial");
			style_a.setFont(font1);

			style_a.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			style_a.setBottomBorderColor(IndexedColors.BLACK.getIndex());

			CellStyle style_b = workbook1.createCellStyle();
			style_b.setWrapText(true);
			Font font2 = workbook1.createFont();
			font2.setFontHeightInPoints((short) 7);
			font2.setFontName("Arial");
			style_b.setFont(font1);

			style_a.setAlignment(CellStyle.ALIGN_CENTER);
			style_a.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
			style_a.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
			style_a.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style_a.setRightBorderColor(IndexedColors.BLACK.getIndex());
			if (row != null) {
				// System.out.println("MAIN IF CONDITION");
				Cell cell_10 = row.createCell(10);
				Cell cell_11 = row.createCell(11);
				Cell cell_12 = row.createCell(12);
				cell_10.setCellValue(i);
				cell_11.setCellValue(obj.getProperty1());
				cell_12.setCellValue(obj.getProperty2());
				cell_10.setCellStyle(style_b);
				cell_11.setCellStyle(style_a);
				cell_12.setCellStyle(style_a);
				if (i == excelFileDtoList1.size()) {
					// System.out.println("IF CONDITION");
					cell_11.setCellStyle(style_a);
					cell_12.setCellStyle(style_a);
				} else {
					// System.out.println("ELSE CONDITION");
					cell_11.setCellStyle(style_a);
					cell_12.setCellStyle(style_a);
				}

			} else {
				// System.out.println("MAIN ELSE ");
				row = sheet.createRow(rownum);
			//	row.setHeightInPoints(10);
				Cell cell_10 = row.createCell(10);
				Cell cell_11 = row.createCell(11);
				Cell cell_12 = row.createCell(12);
				cell_10.setCellValue(i);
				cell_11.setCellValue(obj.getProperty1());
				cell_12.setCellValue(obj.getProperty2());
				cell_10.setCellStyle(style_b);
				cell_11.setCellStyle(style_a);
				cell_12.setCellStyle(style_a);
				if (i == excelFileDtoList1.size()) {
					// System.out.println("IF CONDITION");
					style_a.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
					style_a.setBottomBorderColor(IndexedColors.BLACK.getIndex());
					cell_11.setCellStyle(style_a);
					cell_12.setCellStyle(style_a);
				} else {
					// System.out.println("ELSE CONDITION");
					cell_11.setCellStyle(style_a);
					cell_12.setCellStyle(style_a);
				}

				style_a.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
				style_a.setBottomBorderColor(IndexedColors.BLACK.getIndex());

			}
			row = null;
			rownum = rownum + 1;
		}
		return workbook1;
	}

	public static XSSFWorkbook createForthTable(FileInputStream inputStream, Map<String, Map<String, ?>> param,
			GenerateReportService generateReportService, String territoryName,String editon,String stateCode) {
		int rownum = 0;
		if (secondTableRowCount > thirdTableRowCount)
			rownum = secondTableRowCount;
		else
			rownum = thirdTableRowCount;

		XSSFWorkbook workbook1 = null;
		try {
			workbook1 = new XSSFWorkbook(inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
		//XSSFSheet sheet = workbook1.getSheet("AK_Sitka_06");
		XSSFSheet sheet = workbook1.getSheet(stateCode+"_"+territoryName+"_"+editon);
		XSSFCellStyle styleLTB = ExcelSettingBorderColorUtility.createLeftTopBottomBorderStyle(workbook1);
		XSSFCellStyle styleTB = ExcelSettingBorderColorUtility.createTopBottomBorderStyle(workbook1);
		XSSFCellStyle styleRTB = ExcelSettingBorderColorUtility.createRightTopBottomBorderStyle(workbook1);
		XSSFCellStyle styleLRTB = ExcelSettingBorderColorUtility.createLeftRightTopBottomBorderStyle(workbook1);
		XSSFCellStyle styleLRBlue = ExcelSettingBorderColorUtility.createLeftRightBlueBorderStyle(workbook1);
		styleLTB.setWrapText(true);
		styleTB.setWrapText(true);
		styleRTB.setWrapText(true);
		styleLRTB.setWrapText(true);
		styleLRBlue.setWrapText(true);

		styleLTB.setFillForegroundColor(ExcelColorUtility.CHARCOAL);
		styleLTB.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleTB.setFillForegroundColor(ExcelColorUtility.CHARCOAL);
		styleTB.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleRTB.setFillForegroundColor(ExcelColorUtility.CHARCOAL);
		styleRTB.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleLRBlue.setFillForegroundColor(ExcelColorUtility.CHARCOAL);
		styleLRBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);

		styleLTB.setAlignment(CellStyle.ALIGN_CENTER);
		styleLRBlue.setAlignment(CellStyle.ALIGN_CENTER);
		styleLRBlue.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleLRBlue.setWrapText(true);

		styleLRBlue.setBorderLeft(CellStyle.BORDER_THIN);
		styleLRBlue.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		styleLRBlue.setBorderRight(CellStyle.BORDER_THIN);
		styleLRBlue.setRightBorderColor(IndexedColors.BLACK.getIndex());

		XSSFFont font = workbook1.createFont();
		font.setFontHeightInPoints((short) 7);
		font.setFontName("Arial");
		font.setColor(IndexedColors.WHITE.getIndex());
		font.setBold(true);

		styleTB.setFont(font);
		styleLTB.setFont(font);
		styleRTB.setFont(font);
		styleLRBlue.setFont(font);
		int forthTableFirstHeader = rownum + 3;
		int forthTableSecondHeader = rownum + 4;
		// First Row header started
		Row row = sheet.createRow(forthTableFirstHeader);
		//row.setHeightInPoints(10);
		Cell cell0 = row.createCell(0);
		Cell cell1 = row.createCell(1);
		Cell cell2 = row.createCell(2);
		Cell cell3 = row.createCell(3);
		Cell cell4 = row.createCell(4);
		Cell cell5 = row.createCell(5);
		Cell cell6 = row.createCell(6);
		Cell cell7 = row.createCell(7);
		Cell cell8 = row.createCell(8);
		Cell cell9 = row.createCell(9);
		Cell cell10 = row.createCell(10);
		Cell cell11 = row.createCell(11);
		Cell cell12 = row.createCell(12);

		ExcelFileDto setFirstRowHeader = new ExcelFileDto();
		setFirstRowHeader.setHearder1("Destination Fields");
		setFirstRowHeader.setHearder2("Source Fields");
		setFirstRowHeader.setHearder3("Conversion Instructions");
		cell0.setCellValue(setFirstRowHeader.getHearder1());
		cell5.setCellValue(setFirstRowHeader.getHearder2());
		cell11.setCellValue(setFirstRowHeader.getHearder3());
		cell0.setCellStyle(styleLTB);
		cell1.setCellStyle(styleTB);
		cell2.setCellStyle(styleTB);
		cell3.setCellStyle(styleTB);
		cell4.setCellStyle(styleRTB);
		cell5.setCellStyle(styleLTB);
		cell6.setCellStyle(styleTB);
		cell7.setCellStyle(styleTB);
		cell8.setCellStyle(styleTB);
		cell9.setCellStyle(styleTB);
		cell10.setCellStyle(styleRTB);
		cell11.setCellStyle(styleLTB);
		cell12.setCellStyle(styleRTB);

		// Second Row header started

		ExcelFileDto setSecondRowHeader = new ExcelFileDto();
		setSecondRowHeader.setHearder1("LAN Field Number");
		setSecondRowHeader.setHearder2("LAN Field Name");
		setSecondRowHeader.setHearder3("Length");
		setSecondRowHeader.setHearder4("To");
		setSecondRowHeader.setHearder5("From");
		setSecondRowHeader.setHearder6("FileName");
		setSecondRowHeader.setHearder7("Field Name Start Position");
		setSecondRowHeader.setHearder8("End Position");
		setSecondRowHeader.setHearder9("Pull Forward Condidate");
		setSecondRowHeader.setHearder10("Source Count");
		setSecondRowHeader.setHearder11("Lan Count");
		setSecondRowHeader.setHearder12("Upload Instructions/Comment");
		setSecondRowHeader.setHearder13("Additional Requirement");

		Row row1 = sheet.createRow(forthTableSecondHeader);
	//	row1.setHeightInPoints(30);
		Cell cell_0 = row1.createCell(0);
		Cell cell_1 = row1.createCell(1);
		Cell cell_2 = row1.createCell(2);
		Cell cell_3 = row1.createCell(3);
		Cell cell_4 = row1.createCell(4);
		Cell cell_5 = row1.createCell(5);
		Cell cell_6 = row1.createCell(6);
		Cell cell_7 = row1.createCell(7);
		Cell cell_8 = row1.createCell(8);
		Cell cell_9 = row1.createCell(9);
		Cell cell_10 = row1.createCell(10);
		Cell cell_11 = row1.createCell(11);
		Cell cell_12 = row1.createCell(12);
		// Cell cell_13 = row1.createCell(12);

		cell_0.setCellValue(setSecondRowHeader.getHearder1());
		cell_1.setCellValue(setSecondRowHeader.getHearder2());
		cell_2.setCellValue(setSecondRowHeader.getHearder3());
		cell_3.setCellValue(setSecondRowHeader.getHearder4());
		cell_4.setCellValue(setSecondRowHeader.getHearder5());
		cell_5.setCellValue(setSecondRowHeader.getHearder6());
		cell_6.setCellValue(setSecondRowHeader.getHearder7());
		cell_7.setCellValue(setSecondRowHeader.getHearder8());
		cell_8.setCellValue(setSecondRowHeader.getHearder9());
		cell_9.setCellValue(setSecondRowHeader.getHearder10());
		cell_10.setCellValue(setSecondRowHeader.getHearder11());
		cell_11.setCellValue(setSecondRowHeader.getHearder12());
		cell_12.setCellValue(setSecondRowHeader.getHearder13());

		cell_0.setCellStyle(styleLRBlue);
		cell_1.setCellStyle(styleLRBlue);
		cell_2.setCellStyle(styleLRBlue);
		cell_3.setCellStyle(styleLRBlue);
		cell_4.setCellStyle(styleLRBlue);
		cell_5.setCellStyle(styleLRBlue);
		cell_6.setCellStyle(styleLRBlue);
		cell_7.setCellStyle(styleLRBlue);
		cell_8.setCellStyle(styleLRBlue);
		cell_9.setCellStyle(styleLRBlue);
		cell_10.setCellStyle(styleLRBlue);
		cell_11.setCellStyle(styleLRBlue);
		cell_12.setCellStyle(styleLRBlue);

		// workbook1 =
		// DataConversionExcelUtility.createForthTableRowData(workbook1,
		// forthTableSecondHeader, param);
		workbook1 = DataConversionExcelUtility.createForthTableRowData(workbook1, forthTableSecondHeader, param,
				generateReportService, territoryName,editon,stateCode);

		return workbook1;
	}

	public static XSSFWorkbook createForthTableRowData(XSSFWorkbook workbook, int rowCount,
			Map<String, Map<String, ?>> param, GenerateReportService generateReportService, String territoryName,String edition,String stateCode) {
		rowCount = rowCount + 1;
		System.out.println("param = " + param);
		XSSFCellStyle styleLTB = ExcelSettingBorderColorUtility.createLeftTopBottomBorderStyle(workbook);
		XSSFCellStyle styleLTBW = ExcelSettingBorderColorUtility.createLeftTopBottomBorderStyle(workbook);
		XSSFCellStyle styleLTB1 = ExcelSettingBorderColorUtility.createLeftTopBottomBorderStyle(workbook);
		XSSFCellStyle styleTB = ExcelSettingBorderColorUtility.createTopBottomBorderStyle(workbook);
		XSSFCellStyle styleTB1 = ExcelSettingBorderColorUtility.createTopBottomBorderStyle(workbook);
		XSSFCellStyle styleRTB = ExcelSettingBorderColorUtility.createRightTopBottomBorderStyle(workbook);
		styleLTB.setWrapText(true);
		styleLTBW.setWrapText(true);
		styleLTB1.setWrapText(true);
		styleTB.setWrapText(true);
		styleTB1.setWrapText(true);
		styleRTB.setWrapText(true);
		// styleLTB.setFillForegroundColor(IndexedColors..getIndex());

		styleLTB.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		styleLTB.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleLTBW.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		styleLTBW.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleLTB1.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		styleLTB1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleTB.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		styleTB.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleTB1.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		styleTB1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleRTB.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		styleRTB.setFillPattern(CellStyle.SOLID_FOREGROUND);
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Arial");
		font.setColor(IndexedColors.WHITE.getIndex());
		font.setBold(true);
		styleLTB1.setFont(font);

		XSSFFont font1 = workbook.createFont();
		font1.setFontHeightInPoints((short) 7);
		font1.setFontName("Arial");
		font1.setColor(IndexedColors.RED.getIndex());
		font1.setBold(true);
		styleLTB.setFont(font1);
		styleLTBW.setFont(font1);

		XSSFFont blackFont = workbook.createFont();
		blackFont.setFontHeightInPoints((short) 7);
		blackFont.setFontName("Arial");
		blackFont.setColor(IndexedColors.BLACK.getIndex());

		styleLTB1.setFont(font);

		XSSFFont whiteFont = workbook.createFont();
		whiteFont.setFontHeightInPoints((short) 7);
		whiteFont.setFontName("Arial");
		whiteFont.setColor(IndexedColors.WHITE.getIndex());
		CellStyle whiteStyle = workbook.createCellStyle();
		whiteStyle.setFont(whiteFont);
        whiteStyle.setWrapText(true);
		Map<String, Map<String, ?>> firstMapObj = DataConversionExcelUtility.createDynamicData(generateReportService,
				territoryName,edition);
		// Setting cell values with the getting map
		if (firstMapObj != null) {
			int headerColorCount = 0;
			int rowColorCount1 = 1;
			int rowColorCount2 = 2;
			int listRowCount = 1;
			int colorCount = 0;
			Set<String> keySet = firstMapObj.keySet();
			XSSFSheet sheet = workbook.getSheet(stateCode+"_"+territoryName+"_"+edition);
			
			// Getting first key value which is always be map

			for (String key : keySet) {
				headerColorCount += 1;

				// key is header1
				Map<String, Map<String, ?>> subElementoffirstMapObj = (Map<String, Map<String, ?>>) firstMapObj
						.get(key);
				Set<String> childKeySet = subElementoffirstMapObj.keySet();
				for (String childkey : childKeySet) {
					Row hrow = sheet.createRow(rowCount++);
				//	hrow.setHeightInPoints(10);

					Cell hcell0 = hrow.createCell(0);
					Cell hcell1 = hrow.createCell(1);
					Cell hcell2 = hrow.createCell(2);
					Cell hcell3 = hrow.createCell(3);
					Cell hcell4 = hrow.createCell(4);
					Cell hcell5 = hrow.createCell(5);
					Cell hcell6 = hrow.createCell(6);
					Cell hcell7 = hrow.createCell(7);
					Cell hcell8 = hrow.createCell(8);
					Cell hcell9 = hrow.createCell(9);
					Cell hcell10 = hrow.createCell(10);
					Cell hcell11 = hrow.createCell(11);
					Cell hcell12 = hrow.createCell(12);
					hcell0.setCellValue(key);
					hcell5.setCellValue(childkey);

					XSSFCellStyle headerBackColor1 = ExcelSettingBorderColorUtility
							.createLeftTopBottomBorderStyle(workbook);
					headerBackColor1.setWrapText(true);
					headerBackColor1 = DataConversionExcelUtility.getDifferentColor(headerColorCount, headerBackColor1);
					headerBackColor1.setFont(font);
					XSSFCellStyle headerBackColor2 = ExcelSettingBorderColorUtility
							.createTopBottomBorderStyle(workbook);
					headerBackColor2.setWrapText(true);
					headerBackColor2 = DataConversionExcelUtility.getDifferentColor(headerColorCount, headerBackColor2);
					headerBackColor2.setFont(font);

					hcell0.setCellStyle(headerBackColor1);
					hcell1.setCellStyle(headerBackColor2);
					hcell5.setCellStyle(styleLTBW);
					hcell3.setCellStyle(headerBackColor2);
					hcell4.setCellStyle(headerBackColor2);
					hcell2.setCellStyle(headerBackColor2);
					hcell6.setCellStyle(styleTB1);
					hcell7.setCellStyle(styleTB1);
					hcell8.setCellStyle(styleTB1);
					hcell9.setCellStyle(styleTB1);
					hcell10.setCellStyle(styleTB1);
					hcell11.setCellStyle(styleTB1);
					hcell12.setCellStyle(styleTB1);

					if (subElementoffirstMapObj.get(childkey) instanceof List) {
						// Got list directly put in cell
						colorCount = colorCount + 1;
						List<ExcelFileDto> ExcelFileDtoList = (List<ExcelFileDto>) subElementoffirstMapObj
								.get(childkey);
						for (ExcelFileDto excelFileDto : ExcelFileDtoList) {
							Row lrow = sheet.createRow(rowCount++);
						//	sheet.setColumnWidth(, 9000);
							sheet.setColumnWidth(1, 9000);
							sheet.setColumnWidth(6, 6000);
							//lrow.setHeightInPoints(10);
							//sheet.setDefaultRowHeight((short) 10);
							//sheet.autoSizeColumn(12);
							if (excelFileDto.getUploadInst() != null) {
								System.out.println(excelFileDto.getUploadInst());
								if (excelFileDto.getUploadInst().length() > 65) {
									//lrow.setHeightInPoints(30);
								}
							} else if (excelFileDto.getStartPosition().length() > 33) {
							//	lrow.setHeightInPoints(20);
							} else {
								//lrow.setHeightInPoints(10);
							}

							if (rowCount == 93 || rowCount == 94) {
							//	lrow.setHeightInPoints(30);

							}
							XSSFCellStyle rowColor1 = ExcelSettingBorderColorUtility
									.createLeftRightTopBottomTHINBorderStyle(workbook);
							rowColor1.setWrapText(true);
							rowColor1.setAlignment(CellStyle.ALIGN_CENTER);
							rowColor1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
							XSSFCellStyle rowColor2 = ExcelSettingBorderColorUtility
									.createLeftRightTopBottomTHINBorderStyle(workbook);
							rowColor2.setWrapText(true);
							rowColor2.setAlignment(CellStyle.ALIGN_CENTER);
							rowColor2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
							Cell lcell0 = lrow.createCell(0);
							Cell lcell1 = lrow.createCell(1);
							Cell lcell2 = lrow.createCell(2);
							Cell lcell3 = lrow.createCell(3);
							Cell lcell4 = lrow.createCell(4);
							Cell lcell5 = lrow.createCell(5);
							Cell lcell6 = lrow.createCell(6);
							Cell lcell7 = lrow.createCell(7);
							Cell lcell8 = lrow.createCell(8);
							Cell lcell9 = lrow.createCell(9);
							Cell lcell10 = lrow.createCell(10);
							Cell lcell11 = lrow.createCell(11);
							Cell lcell12 = lrow.createCell(12);

							// Setting header cell value
							lcell0.setCellValue(excelFileDto.getLanFieldNumber());
							lcell1.setCellValue(excelFileDto.getLanFieldName());
							lcell2.setCellValue(excelFileDto.getLength());
							lcell3.setCellValue(excelFileDto.getTo());
							lcell4.setCellValue(excelFileDto.getFrom());
							lcell5.setCellValue(excelFileDto.getFileName());
							lcell6.setCellValue(excelFileDto.getStartPosition());
							lcell7.setCellValue(excelFileDto.getEndPosition());
							lcell8.setCellValue(excelFileDto.getPullForward());
							lcell9.setCellValue(excelFileDto.getSourceCount());
							lcell10.setCellValue(excelFileDto.getLanCount());
							lcell11.setCellValue(excelFileDto.getUploadInst());
							lcell12.setCellValue(excelFileDto.getAdditionalReq());
							if (listRowCount % 2 != 0) {
								lcell0.setCellStyle(rowColor1);
								rowColor1 = DataConversionExcelUtility.getDifferentColorForRows(rowColorCount1,
										rowColor1, colorCount);
								if (rowCount == 93) {
									rowColor1.setFont(whiteFont);
									lcell0.setCellStyle(rowColor1);
									lcell1.setCellStyle(rowColor1);
									lcell2.setCellStyle(rowColor1);
									lcell3.setCellStyle(rowColor1);
									lcell4.setCellStyle(rowColor1);
									lcell5.setCellStyle(rowColor1);
									lcell6.setCellStyle(rowColor1);
									lcell7.setCellStyle(rowColor1);
									lcell8.setCellStyle(rowColor1);
									lcell9.setCellStyle(rowColor1);
									lcell10.setCellStyle(rowColor1);
									lcell11.setCellStyle(rowColor1);
									lcell12.setCellStyle(rowColor1);
								} else {
									rowColor1.setFont(blackFont);
									lcell0.setCellStyle(rowColor1);
									lcell1.setCellStyle(rowColor1);
									lcell2.setCellStyle(rowColor1);
									lcell3.setCellStyle(rowColor1);
									lcell4.setCellStyle(rowColor1);
									lcell5.setCellStyle(rowColor1);
									lcell6.setCellStyle(rowColor1);
									lcell7.setCellStyle(rowColor1);
									lcell8.setCellStyle(rowColor1);
									lcell9.setCellStyle(rowColor1);
									lcell10.setCellStyle(rowColor1);
									lcell11.setCellStyle(rowColor1);
									lcell12.setCellStyle(rowColor1);
								}
							} else {
								rowColor2 = DataConversionExcelUtility.getDifferentColorForRows(rowColorCount2,
										rowColor2, colorCount);
								if (rowCount == 94) {
									rowColor2.setFont(whiteFont);
									lcell0.setCellStyle(rowColor2);
									lcell1.setCellStyle(rowColor2);
									lcell2.setCellStyle(rowColor2);
									lcell3.setCellStyle(rowColor2);
									lcell4.setCellStyle(rowColor2);
									lcell5.setCellStyle(rowColor2);
									lcell6.setCellStyle(rowColor2);
									lcell7.setCellStyle(rowColor2);
									lcell8.setCellStyle(rowColor2);
									lcell9.setCellStyle(rowColor2);
									lcell10.setCellStyle(rowColor2);
									lcell11.setCellStyle(rowColor2);
									lcell12.setCellStyle(rowColor2);
								} else {
									rowColor2.setFont(blackFont);
									lcell0.setCellStyle(rowColor2);
									lcell1.setCellStyle(rowColor2);
									lcell2.setCellStyle(rowColor2);
									lcell3.setCellStyle(rowColor2);
									lcell4.setCellStyle(rowColor2);
									lcell5.setCellStyle(rowColor2);
									lcell6.setCellStyle(rowColor2);
									lcell7.setCellStyle(rowColor2);
									lcell8.setCellStyle(rowColor2);
									lcell9.setCellStyle(rowColor2);
									lcell10.setCellStyle(rowColor2);
									lcell11.setCellStyle(rowColor2);
									lcell12.setCellStyle(rowColor2);
								}
							}
							listRowCount = listRowCount + 1;
						}

						listRowCount = 1;
						rowColorCount1 = rowColorCount1 + 2;
						rowColorCount2 = rowColorCount2 + 2;
					} else if (subElementoffirstMapObj.get(childkey) instanceof Map) {
						colorCount = colorCount + 1;
						headerColorCount += 1;
						XSSFCellStyle subHeaderBackColor1 = ExcelSettingBorderColorUtility
								.createLeftTopBottomBorderStyle(workbook);
						subHeaderBackColor1.setWrapText(true);
						subHeaderBackColor1 = DataConversionExcelUtility.getDifferentColor(headerColorCount,
								subHeaderBackColor1);
						subHeaderBackColor1.setFont(font);
						XSSFCellStyle subHeaderBackColor2 = ExcelSettingBorderColorUtility
								.createTopBottomBorderStyle(workbook);
						subHeaderBackColor2.setWrapText(true);
						subHeaderBackColor2 = DataConversionExcelUtility.getDifferentColor(headerColorCount,
								subHeaderBackColor2);
						subHeaderBackColor2.setFont(font);

						Map lastchildMap = subElementoffirstMapObj.get(childkey);

						Set<String> subHeaderKey = lastchildMap.keySet();
						System.out.println("===================="+subHeaderKey+"====================");
						sheet.setColumnWidth(0,10000);
						for (String childKey1 : subHeaderKey) {
							Row subhrow = sheet.createRow(rowCount++);
						//	sheet.setColumnWidth(0, childKey1.length());
							Cell subhcell0 = subhrow.createCell(0);
							Cell subhcell1 = subhrow.createCell(1);
							Cell subhcell2 = subhrow.createCell(2);
							Cell subhcell3 = subhrow.createCell(3);
							Cell subhcell4 = subhrow.createCell(4);
							Cell subhcell5 = subhrow.createCell(5);
							Cell subhcell6 = subhrow.createCell(6);
							Cell subhcell7 = subhrow.createCell(7);
							Cell subhcell8 = subhrow.createCell(8);
							Cell subhcell9 = subhrow.createCell(9);
							Cell subhcell10 = subhrow.createCell(10);
							Cell subhcell11 = subhrow.createCell(11);
							Cell subhcell12 = subhrow.createCell(12);
							// Setting header cell value
							subhcell0.setCellValue(childKey1);
							subhcell0.setCellStyle(subHeaderBackColor1);
							subhcell1.setCellStyle(subHeaderBackColor2);
							subhcell2.setCellStyle(subHeaderBackColor2);
							subhcell3.setCellStyle(subHeaderBackColor2);
							subhcell4.setCellStyle(subHeaderBackColor2);
							subhcell5.setCellStyle(subHeaderBackColor2);
							subhcell6.setCellStyle(subHeaderBackColor2);
							subhcell7.setCellStyle(subHeaderBackColor2);
							subhcell8.setCellStyle(subHeaderBackColor2);
							subhcell9.setCellStyle(subHeaderBackColor2);
							subhcell10.setCellStyle(subHeaderBackColor2);
							subhcell11.setCellStyle(subHeaderBackColor2);
							subhcell12.setCellStyle(subHeaderBackColor2);

							List<ExcelFileDto> ExcelFileDtoList = (List<ExcelFileDto>) lastchildMap.get(childKey1);
							for (ExcelFileDto excelFileDto : ExcelFileDtoList) {

								XSSFCellStyle subMapRowColor1 = ExcelSettingBorderColorUtility
										.createLeftRightTopBottomTHINBorderStyle(workbook);
								XSSFCellStyle subMapRowColor2 = ExcelSettingBorderColorUtility
										.createLeftRightTopBottomTHINBorderStyle(workbook);
								subMapRowColor1.setWrapText(true);
								subMapRowColor2.setWrapText(true);
								Row shlrow = sheet.createRow(rowCount++);
								//shlrow.setHeightInPoints(10);
								Cell shlcell0 = shlrow.createCell(0);
								Cell shlcell1 = shlrow.createCell(1);
								Cell shlcell2 = shlrow.createCell(2);
								Cell shlcell3 = shlrow.createCell(3);
								Cell shlcell4 = shlrow.createCell(4);
								Cell shlcell5 = shlrow.createCell(5);
								Cell shlcell6 = shlrow.createCell(6);
								Cell shlcell7 = shlrow.createCell(7);
								Cell shlcell8 = shlrow.createCell(8);
								Cell shlcell9 = shlrow.createCell(9);
								Cell shlcell10 = shlrow.createCell(10);
								Cell shlcell11 = shlrow.createCell(11);
								Cell shlcell12 = shlrow.createCell(12);
								// Setting header cell value
								shlcell0.setCellValue(excelFileDto.getLanFieldNumber());
								shlcell1.setCellValue(excelFileDto.getLanFieldName());
								shlcell2.setCellValue(excelFileDto.getLength());
								shlcell3.setCellValue(excelFileDto.getTo());
								shlcell4.setCellValue(excelFileDto.getFrom());
								shlcell5.setCellValue(excelFileDto.getFileName());
								shlcell6.setCellValue(excelFileDto.getStartPosition());
								shlcell7.setCellValue(excelFileDto.getEndPosition());
								shlcell8.setCellValue(excelFileDto.getPullForward());
								shlcell9.setCellValue(excelFileDto.getSourceCount());
								shlcell10.setCellValue(excelFileDto.getLanCount());
								shlcell11.setCellValue(excelFileDto.getUploadInst());
								shlcell12.setCellValue(excelFileDto.getAdditionalReq());
								if (listRowCount % 2 != 0) {
									// shlcell0.setCellStyle(rowColor1);
									subMapRowColor1 = DataConversionExcelUtility
											.getDifferentColorForRows(rowColorCount1, subMapRowColor1, colorCount);
									subMapRowColor1.setFont(blackFont);
									shlcell0.setCellStyle(subMapRowColor1);
									shlcell1.setCellStyle(subMapRowColor1);
									shlcell2.setCellStyle(subMapRowColor1);
									shlcell3.setCellStyle(subMapRowColor1);
									shlcell4.setCellStyle(subMapRowColor1);
									shlcell5.setCellStyle(subMapRowColor1);
									shlcell6.setCellStyle(subMapRowColor1);
									shlcell7.setCellStyle(subMapRowColor1);
									shlcell8.setCellStyle(subMapRowColor1);
									shlcell9.setCellStyle(subMapRowColor1);
									shlcell10.setCellStyle(subMapRowColor1);
									shlcell11.setCellStyle(subMapRowColor1);
									shlcell12.setCellStyle(subMapRowColor1);
								} else {
									subMapRowColor2 = DataConversionExcelUtility
											.getDifferentColorForRows(rowColorCount2, subMapRowColor2, colorCount);
									subMapRowColor2.setFont(blackFont);
									shlcell0.setCellStyle(subMapRowColor2);
									shlcell1.setCellStyle(subMapRowColor2);
									shlcell2.setCellStyle(subMapRowColor2);
									shlcell3.setCellStyle(subMapRowColor2);
									shlcell4.setCellStyle(subMapRowColor2);
									shlcell5.setCellStyle(subMapRowColor2);
									shlcell6.setCellStyle(subMapRowColor2);
									shlcell7.setCellStyle(subMapRowColor2);
									shlcell8.setCellStyle(subMapRowColor2);
									shlcell9.setCellStyle(subMapRowColor2);
									shlcell10.setCellStyle(subMapRowColor2);
									shlcell11.setCellStyle(subMapRowColor2);
									shlcell12.setCellStyle(subMapRowColor2);
								}
								listRowCount = listRowCount + 1;
							}
						}
						listRowCount = 1;
						rowColorCount1 = rowColorCount1 + 2;
						rowColorCount2 = rowColorCount2 + 2;
					}
				}
			}
		}

		return workbook;
	}

/*	public static void main(String[] args) {

		workbook = new XSSFWorkbook();
		workbook = DataConversionExcelUtility.createFirstTable(workbook, null);
		try {
			FileOutputStream out = new FileOutputStream(
					new File(ExcelConstant.EXCEL_FILE_CREATION_DRIVE + ExcelConstant.EXCELFILENAME));
			workbook.write(out);
			out.close();
			workbook = null;
			FileInputStream inputStream = new FileInputStream(new File("F:\\AK_Sitka_06_Data_Generated.xlsx"));
			workbook = DataConversionExcelUtility.createSecondTable(inputStream, null);

			FileOutputStream outSecondTable = new FileOutputStream(new File("F:\\AK_Sitka_06_Data_Generated.xlsx"));
			workbook.write(outSecondTable);
			outSecondTable.close();
			workbook = null;

			FileInputStream inputStream1 = new FileInputStream(new File("F:\\AK_Sitka_06_Data_Generated.xlsx"));
			workbook = DataConversionExcelUtility.createThirdTable(inputStream1, null);
			FileOutputStream outThirdTable = new FileOutputStream(new File("F:\\AK_Sitka_06_Data_Generated.xlsx"));

			workbook.write(outThirdTable);
			outThirdTable.close();
			workbook = null;

			FileInputStream inputStream2 = new FileInputStream(new File("F:\\AK_Sitka_06_Data_Generated.xlsx"));
			// workbook =
			// DataConversionExcelUtility.createForthTable(inputStream2, null);
			FileOutputStream outForthTable = new FileOutputStream(new File("F:\\AK_Sitka_06_Data_Generated.xlsx"));
			workbook.write(outForthTable);
			outForthTable.close();
			workbook = null;

			System.out.println("AK_Sitka_06_Data_Spec_Sheet.xlsx written successfully in drive.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

	public static String getUsedTableNames(int fieldNumber, GenerateReportService generateReportService,
			String territoryName,String edition) {
		String value = generateReportService.getUsedTableNames(fieldNumber, territoryName,edition);
		if (value == null)
			return "";
		else
			return value;
	}

	public static String getUsedColumnNames(int fieldNumber, GenerateReportService generateReportService,
			String territoryName,String edition) {
		String value = generateReportService.getUsedColumnNames(fieldNumber, territoryName,edition);
		if (value == null)
			return "";
		else
			return value;
	}

	public static String getUsedRUL(int fieldNumber, GenerateReportService generateReportService, String territoryName,
			String edition) {
		String value = generateReportService.getUsedRUL(fieldNumber, territoryName, edition);
		if (value == null)
			return "";
		else
			return value;
	}

	public static Map<String, Map<String, ?>> createDynamicData(GenerateReportService generateReportService,
			String territoryName,String edition) {
		System.out.println(edition);
		edition = null;
		Map<String, Map<String, ?>> parentMap = new LinkedHashMap<String, Map<String, ?>>();
		List<ExcelFileDto> excelFileDtoList = new ArrayList<ExcelFileDto>();
		ExcelFileDto obj1 = new ExcelFileDto();
		obj1.setLanFieldNumber("1");
		obj1.setLanFieldName("1-STATE (POSTAL CODE)");
		obj1.setLength("2");
		obj1.setTo("1");
		obj1.setFrom("2");
		obj1.setFileName(getUsedTableNames(1, generateReportService, territoryName,edition));
		obj1.setStartPosition(getUsedColumnNames(1, generateReportService, territoryName,edition));
		obj1.setEndPosition("");
		obj1.setPullForward("");
		obj1.setSourceCount("");
		obj1.setLanCount("");
		// obj1.setUploadInst("Load as 'AK'");
		System.out.println(getUsedRUL(1, generateReportService, territoryName, edition));
		obj1.setUploadInst(getUsedRUL(1, generateReportService, territoryName, edition));
		//obj1.setUploadInst("abs");
		obj1.setAdditionalReq("");
		excelFileDtoList.add(obj1);
		ExcelFileDto obj2 = new ExcelFileDto();
		obj2.setLanFieldNumber("2");
		obj2.setLanFieldName("2-County Name)");
		obj2.setLength("25");
		obj2.setTo("3");
		obj2.setFrom("27");
		obj2.setFileName(getUsedTableNames(2, generateReportService, territoryName, edition));
		obj2.setStartPosition(getUsedColumnNames(2, generateReportService, territoryName, edition));
		// obj2.setFileName("");
		// obj2.setStartPosition("");
		obj2.setEndPosition("");
		obj2.setPullForward("");
		obj2.setSourceCount("");
		obj2.setLanCount("");
		obj2.setUploadInst(getUsedRUL(2, generateReportService, territoryName, edition));
		// obj2.setUploadInst("Load as 'SITKA'");
		obj2.setAdditionalReq("");
		excelFileDtoList.add(obj2);
		ExcelFileDto obj3 = new ExcelFileDto();
		obj3.setLanFieldNumber("90");
		obj3.setLanFieldName("90-FIPS CODE");
		obj3.setLength("5");
		obj3.setTo("1330");
		obj3.setFrom("1334");
		obj3.setFileName(getUsedTableNames(90, generateReportService, territoryName, edition));
		obj3.setStartPosition(getUsedColumnNames(90, generateReportService, territoryName, edition));
		/*
		 * obj3.setFileName(""); obj3.setStartPosition("");
		 */
		obj3.setEndPosition("");
		obj3.setPullForward("");
		obj3.setSourceCount("");
		obj3.setLanCount("");
		obj3.setUploadInst(getUsedRUL(90, generateReportService, territoryName, edition));
		// obj3.setUploadInst("Load as 02220");
		obj3.setAdditionalReq("");
		excelFileDtoList.add(obj3);
		ExcelFileDto obj4 = new ExcelFileDto();
		obj4.setLanFieldNumber("3");
		obj4.setLanFieldName("3-APN-A (OR PIN NUMBER)");
		obj4.setLength("2");
		obj4.setTo("1395");
		obj4.setFrom("1396");
		obj4.setFileName(getUsedTableNames(3, generateReportService, territoryName, edition));
		obj4.setStartPosition(getUsedColumnNames(3, generateReportService, territoryName, edition));
		/*
		 * obj4.setFileName("AK_Sitka_06_Main"); obj4.setStartPosition("");
		 */
		obj4.setEndPosition("");
		obj4.setPullForward("");
		obj4.setSourceCount("");
		obj4.setLanCount("");
		/*
		 * obj4.setUploadInst(
		 * "Load  from [Property #] .Remove the hypen between the numbers and Move the parcel number from starting till the first 8 numbers.Trim Leading and trailing spaces"
		 * );
		 */
		//obj4.setUploadInst(getUsedRUL(3, generateReportService, territoryName, edition));
		obj4.setUploadInst(getUsedRUL(3, generateReportService, territoryName, edition));
		obj4.setAdditionalReq("");
		excelFileDtoList.add(obj4);
		ExcelFileDto obj5 = new ExcelFileDto();
		obj5.setLanFieldNumber("109");
		obj5.setLanFieldName("109-ADDITION NUMBER");
		obj5.setLength("45");
		obj5.setTo("25");
		obj5.setFrom("72");
		obj5.setFileName(getUsedTableNames(109, generateReportService, territoryName, edition));
		obj5.setStartPosition(getUsedColumnNames(109, generateReportService, territoryName, edition));
		/*
		 * obj5.setFileName("AK_Sitka_06_Main"); obj5.setStartPosition("");
		 */
		obj5.setEndPosition("");
		obj5.setPullForward("");
		obj5.setSourceCount("");
		obj5.setLanCount("");
		// obj5.setUploadInst("Load as 2016");
		obj5.setUploadInst(getUsedRUL(109, generateReportService, territoryName, edition));
		obj5.setAdditionalReq("");
		excelFileDtoList.add(obj5);

		ExcelFileDto obj6 = new ExcelFileDto();
		obj6.setLanFieldNumber("40");
		obj6.setLanFieldName("40-ASSESSMENT YEAR");
		obj6.setLength("4");
		obj6.setTo("686");
		obj6.setFrom("689");
		obj6.setFileName(getUsedTableNames(40, generateReportService, territoryName, edition));
		obj6.setStartPosition(getUsedColumnNames(40, generateReportService, territoryName, edition));
		/*
		 * obj5.setFileName("AK_Sitka_06_Main"); obj5.setStartPosition("");
		 */
		obj6.setEndPosition("");
		obj6.setPullForward("");
		obj6.setSourceCount("");
		obj6.setLanCount("");
		// obj6.setUploadInst("Load as 2016");
		obj6.setUploadInst(getUsedRUL(40, generateReportService, territoryName, edition));
		obj6.setAdditionalReq("");
		excelFileDtoList.add(obj6);
		ExcelFileDto obj7 = new ExcelFileDto();
		obj7.setLanFieldNumber("130");
		obj7.setLanFieldName("130-CERTIFICATION DATE");
		obj7.setLength("8");
		obj7.setTo("1479");
		obj7.setFrom("1486");
		obj7.setFileName(getUsedTableNames(130, generateReportService, territoryName, edition));
		obj7.setStartPosition(getUsedColumnNames(130, generateReportService, territoryName, edition));
		/*
		 * obj5.setFileName("AK_Sitka_06_Main"); obj5.setStartPosition("");
		 */
		obj7.setEndPosition("");
		obj7.setPullForward("");
		obj7.setSourceCount("");
		obj7.setLanCount("");
		// obj7.setUploadInst("");
		obj7.setUploadInst(getUsedRUL(130, generateReportService, territoryName, edition));
		obj7.setAdditionalReq("");
		excelFileDtoList.add(obj7);
		ExcelFileDto obj8 = new ExcelFileDto();
		obj8.setLanFieldNumber("91");
		obj8.setLanFieldName("91-TAPE CUT DATE");
		obj8.setLength("6");
		obj8.setTo("1335");
		obj8.setFrom("1340");
		obj8.setFileName(getUsedTableNames(91, generateReportService, territoryName, edition));
		obj8.setStartPosition(getUsedColumnNames(91, generateReportService, territoryName, edition));
		/*
		 * obj5.setFileName("AK_Sitka_06_Main"); obj5.setStartPosition("");
		 */
		obj8.setEndPosition("");
		obj8.setPullForward("");
		obj8.setSourceCount("");
		obj8.setLanCount("");
		// obj8.setUploadInst("");
		obj8.setUploadInst(getUsedRUL(91, generateReportService, territoryName, edition));
		obj8.setAdditionalReq("");
		excelFileDtoList.add(obj8);
		ExcelFileDto obj9 = new ExcelFileDto();
		obj9.setLanFieldNumber("187");
		obj9.setLanFieldName("187-OLD APN");
		obj9.setLength("31");
		obj9.setTo("1825");
		obj9.setFrom("1855");
		obj9.setFileName(getUsedTableNames(187, generateReportService, territoryName, edition));
		obj9.setStartPosition(getUsedColumnNames(187, generateReportService, territoryName, edition));
		/*
		 * obj5.setFileName("AK_Sitka_06_Main"); obj5.setStartPosition("");
		 */
		obj9.setEndPosition("");
		obj9.setPullForward("");
		obj9.setSourceCount("");
		obj9.setLanCount("");
		// obj9.setUploadInst("");
		obj9.setUploadInst(getUsedRUL(187, generateReportService, territoryName, edition));
		obj9.setAdditionalReq("");
		excelFileDtoList.add(obj9);
		ExcelFileDto obj10 = new ExcelFileDto();
		obj10.setLanFieldNumber("129");
		obj10.setLanFieldName("129-Alt/Old APN Indicator");
		obj10.setLength("1");
		obj10.setTo("1478");
		obj10.setFrom("1478");
		obj10.setFileName(getUsedTableNames(129, generateReportService, territoryName, edition));
		obj10.setStartPosition(getUsedColumnNames(129, generateReportService, territoryName, edition));
		/*
		 * obj5.setFileName("AK_Sitka_06_Main"); obj5.setStartPosition("");
		 */
		obj10.setEndPosition("");
		obj10.setPullForward("");
		obj10.setSourceCount("");
		obj10.setLanCount("");
		// obj10.setUploadInst("");
		obj10.setUploadInst(getUsedRUL(129, generateReportService, territoryName, edition));
		obj10.setAdditionalReq("");
		excelFileDtoList.add(obj10);
		Map<String, List<ExcelFileDto>> subMap1 = new LinkedHashMap<String, List<ExcelFileDto>>();
		subMap1.put("Class Comment:", excelFileDtoList);

		List<ExcelFileDto> excelFileDtoList1 = new ArrayList<ExcelFileDto>();
		ExcelFileDto aobj1 = new ExcelFileDto();
		aobj1.setLanFieldNumber("18");
		aobj1.setLanFieldName("18-ASSESSEE (OWNER) NAME");
		aobj1.setLength("80");
		aobj1.setTo("234");
		aobj1.setFrom("313");
		aobj1.setFileName(getUsedTableNames(18, generateReportService, territoryName, edition));
		aobj1.setStartPosition(getUsedColumnNames(18, generateReportService, territoryName, edition));
		/*
		 * aobj1.setFileName(""); aobj1.setStartPosition("");
		 */
		aobj1.setEndPosition("");
		aobj1.setPullForward("");
		aobj1.setSourceCount("");
		aobj1.setLanCount("");
		// aobj1.setUploadInst("Please refer additional document");
		aobj1.setUploadInst(getUsedRUL(18, generateReportService, territoryName, edition));
		aobj1.setAdditionalReq("");
		excelFileDtoList1.add(aobj1);
		ExcelFileDto aobj2 = new ExcelFileDto();
		aobj2.setLanFieldNumber("19");
		aobj2.setLanFieldName("19-2ND ASSESSEE (OWNER) NAME");
		aobj2.setLength("60");
		aobj2.setTo("314");
		aobj2.setFrom("373");
		aobj2.setFileName(getUsedTableNames(19, generateReportService, territoryName, edition));
		aobj2.setStartPosition(getUsedColumnNames(19, generateReportService, territoryName, edition));
		/*
		 * aobj2.setFileName(""); aobj2.setStartPosition("");
		 */
		aobj2.setEndPosition("");
		aobj2.setPullForward("");
		aobj2.setSourceCount("");
		aobj2.setLanCount("");
		// aobj2.setUploadInst("Please refer additional document");
		aobj2.setUploadInst(getUsedRUL(19, generateReportService, territoryName, edition));
		aobj2.setAdditionalReq("");
		excelFileDtoList1.add(aobj2);
		ExcelFileDto aobj3 = new ExcelFileDto();
		aobj3.setLanFieldNumber("20");
		aobj3.setLanFieldName("20-ASSE VESTING/ID CODE");
		aobj3.setLength("2");
		aobj3.setTo("374");
		aobj3.setFrom("375");
		aobj3.setFileName(getUsedTableNames(20, generateReportService, territoryName, edition));
		aobj3.setStartPosition(getUsedColumnNames(20, generateReportService, territoryName, edition));
		/*
		 * aobj3.setFileName(""); aobj3.setStartPosition("");
		 */
		obj3.setEndPosition("");
		aobj3.setPullForward("");
		aobj3.setSourceCount("");
		aobj3.setLanCount("");
		// aobj3.setUploadInst("Please refer additional document");
		aobj3.setUploadInst(getUsedRUL(20, generateReportService, territoryName, edition));
		aobj3.setAdditionalReq("");
		excelFileDtoList1.add(aobj3);
		ExcelFileDto aobj4 = new ExcelFileDto();
		aobj4.setLanFieldNumber("22");
		aobj4.setLanFieldName("22-ASSE MAIL: CARE-OF NAME");
		aobj4.setLength("60");
		aobj4.setTo("406");
		aobj4.setFrom("465");
		aobj4.setFileName(getUsedTableNames(22, generateReportService, territoryName, edition));
		aobj4.setStartPosition(getUsedColumnNames(22, generateReportService, territoryName, edition));
		/*
		 * aobj4.setFileName("AK_Sitka_06_Main"); aobj4.setStartPosition("");
		 */
		aobj4.setEndPosition("");
		aobj4.setPullForward("");
		aobj4.setSourceCount("");
		aobj4.setLanCount("");
		// aobj4.setUploadInst("");
		aobj4.setUploadInst(getUsedRUL(22, generateReportService, territoryName, edition));
		aobj4.setAdditionalReq("");
		excelFileDtoList1.add(aobj4);
		ExcelFileDto aobj5 = new ExcelFileDto();
		aobj5.setLanFieldNumber("124");
		aobj5.setLanFieldName("124-ASSESSEE (OWNER) NAME INDICATOR");
		aobj5.setLength("1");
		aobj5.setTo("1473");
		aobj5.setFrom("1473");
		aobj5.setFileName(getUsedTableNames(124, generateReportService, territoryName, edition));
		aobj5.setStartPosition(getUsedColumnNames(124, generateReportService, territoryName, edition));
		/*
		 * aobj5.setFileName("AK_Sitka_06_Main"); aobj5.setStartPosition("");
		 */
		aobj5.setEndPosition("");
		aobj5.setPullForward("");
		aobj5.setSourceCount("");
		aobj5.setLanCount("");
		// aobj5.setUploadInst("Load 2 if ASSESSE OWNER NAME[18] is populated");
		aobj5.setUploadInst(getUsedRUL(124, generateReportService, territoryName, edition));
		aobj5.setAdditionalReq("");
		excelFileDtoList1.add(aobj5);

		ExcelFileDto aobj6 = new ExcelFileDto();
		aobj6.setLanFieldNumber("125");
		aobj6.setLanFieldName("125-2ND ASSESSEE (OWNER) NAME INDICATOR");
		aobj6.setLength("1");
		aobj6.setTo("1474");
		aobj6.setFrom("1474");
		aobj6.setFileName(getUsedTableNames(125, generateReportService, territoryName, edition));
		aobj6.setStartPosition(getUsedColumnNames(125, generateReportService, territoryName, edition));
		/*
		 * aobj5.setFileName("AK_Sitka_06_Main"); aobj5.setStartPosition("");
		 */
		aobj6.setEndPosition("");
		aobj6.setPullForward("");
		aobj6.setSourceCount("");
		aobj6.setLanCount("");
		// aobj6.setUploadInst("Load '1' if 19-2ND ASSESSEE (OWNER) NAME is
		// populated");
		aobj6.setUploadInst(getUsedRUL(125, generateReportService, territoryName, edition));
		aobj6.setAdditionalReq("");
		excelFileDtoList1.add(aobj6);
		ExcelFileDto aobj7 = new ExcelFileDto();
		aobj7.setLanFieldNumber("126");
		aobj7.setLanFieldName("126-MAIL CARE-OF NAME INDICATOR");
		aobj7.setLength("1");
		aobj7.setTo("1475");
		aobj7.setFrom("1475");
		aobj7.setFileName(getUsedTableNames(126, generateReportService, territoryName, edition));
		aobj7.setStartPosition(getUsedColumnNames(126, generateReportService, territoryName, edition));
		/*
		 * aobj5.setFileName("AK_Sitka_06_Main"); aobj5.setStartPosition("");
		 */
		aobj7.setEndPosition("");
		aobj7.setPullForward("");
		aobj7.setSourceCount("");
		aobj7.setLanCount("");
		// aobj7.setUploadInst("");
		aobj7.setUploadInst(getUsedRUL(126, generateReportService, territoryName, edition));
		aobj7.setAdditionalReq("");
		excelFileDtoList1.add(aobj7);
		ExcelFileDto aobj8 = new ExcelFileDto();
		aobj8.setLanFieldNumber("127");
		aobj8.setLanFieldName("127-ASSESSEE (OWNER) NAME TYPE");
		aobj8.setLength("1");
		aobj8.setTo("1476");
		aobj8.setFrom("1476");
		aobj8.setFileName(getUsedTableNames(127, generateReportService, territoryName, edition));
		aobj8.setStartPosition(getUsedColumnNames(127, generateReportService, territoryName, edition));
		/*
		 * aobj5.setFileName("AK_Sitka_06_Main"); aobj5.setStartPosition("");
		 */
		aobj8.setEndPosition("");
		aobj8.setPullForward("");
		aobj8.setSourceCount("");
		aobj8.setLanCount("");
		// aobj8.setUploadInst("");
		aobj8.setUploadInst(getUsedRUL(127, generateReportService, territoryName, edition));
		aobj8.setAdditionalReq("");
		excelFileDtoList1.add(aobj8);
		ExcelFileDto aobj9 = new ExcelFileDto();
		aobj9.setLanFieldNumber("128");
		aobj9.setLanFieldName("128-2ND ASSESSEE (OWNER) NAME TYPE");
		aobj9.setLength("1");
		aobj9.setTo("1477");
		aobj9.setFrom("1477");
		aobj9.setFileName(getUsedTableNames(124, generateReportService, territoryName, edition));
		aobj9.setStartPosition(getUsedColumnNames(124, generateReportService, territoryName, edition));
		/*
		 * aobj5.setFileName("AK_Sitka_06_Main"); aobj5.setStartPosition("");
		 */
		aobj9.setEndPosition("");
		aobj9.setPullForward("");
		aobj9.setSourceCount("");
		aobj9.setLanCount("");
		// aobj9.setUploadInst("");
		aobj9.setUploadInst(getUsedRUL(124, generateReportService, territoryName, edition));
		aobj9.setAdditionalReq("");
		excelFileDtoList1.add(aobj9);
		ExcelFileDto aobj10 = new ExcelFileDto();
		aobj10.setLanFieldNumber("168");
		aobj10.setLanFieldName("168-CONDO PROJECT/BUILDING NAME");
		aobj10.setLength("20");
		aobj10.setTo("1702");
		aobj10.setFrom("1721");
		aobj10.setFileName(getUsedTableNames(168, generateReportService, territoryName, edition));
		aobj10.setStartPosition(getUsedColumnNames(168, generateReportService, territoryName, edition));
		/*
		 * aobj5.setFileName("AK_Sitka_06_Main"); aobj5.setStartPosition("");
		 */
		aobj10.setEndPosition("");
		aobj10.setPullForward("");
		aobj10.setSourceCount("");
		aobj10.setLanCount("");
		// aobj10.setUploadInst("");
		aobj10.setUploadInst(getUsedRUL(168, generateReportService, territoryName, edition));
		aobj10.setAdditionalReq("");
		excelFileDtoList1.add(aobj10);
		ExcelFileDto aobj11 = new ExcelFileDto();
		aobj11.setLanFieldNumber("153");
		aobj11.setLanFieldName("153-MORTGAGE LENDER NAME");
		aobj11.setLength("40");
		aobj11.setTo("1589");
		aobj11.setFrom("1628");
		aobj11.setFileName(getUsedTableNames(153, generateReportService, territoryName, edition));
		aobj11.setStartPosition(getUsedColumnNames(153, generateReportService, territoryName, edition));
		/*
		 * aobj5.setFileName("AK_Sitka_06_Main"); aobj5.setStartPosition("");
		 */
		aobj11.setEndPosition("");
		aobj11.setPullForward("");
		aobj11.setSourceCount("");
		aobj11.setLanCount("");
		// aobj11.setUploadInst("");
		aobj11.setUploadInst(getUsedRUL(153, generateReportService, territoryName, edition));
		aobj11.setAdditionalReq("");
		excelFileDtoList1.add(aobj11);
		Map<String, List<ExcelFileDto>> subMap2 = new LinkedHashMap<String, List<ExcelFileDto>>();
		subMap2.put("Class Comment:", excelFileDtoList1);

		parentMap.put("Header Fields", subMap1);
		parentMap.put("Name Fields", subMap2);

		List<String> headerList = new ArrayList<String>();
		headerList.add("Lot/Building Fields");
		headerList.add("Exterior Fields");
		headerList.add("Interior Fields");
		headerList.add("Specialty Fields");
		// headerList.add("Speciality Fields");

		List<ExcelFileDto> list1 = new ArrayList<ExcelFileDto>();
		ExcelFileDto bobj1 = new ExcelFileDto();
		bobj1.setLanFieldNumber("76");
		bobj1.setLanFieldName("76-LOT SIZE OR AREA");
		bobj1.setLength("14");
		bobj1.setTo("1225");
		bobj1.setFrom("1238");
		bobj1.setFileName(getUsedTableNames(76, generateReportService, territoryName, edition));
		bobj1.setStartPosition(getUsedColumnNames(76, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		bobj1.setEndPosition("");
		bobj1.setPullForward("");
		bobj1.setSourceCount("");
		bobj1.setLanCount("");
		// bobj1.setUploadInst("Please find the additional document");
		bobj1.setUploadInst(getUsedRUL(76, generateReportService, territoryName, edition));
		bobj1.setAdditionalReq("");
		list1.add(bobj1);
		ExcelFileDto bobj2 = new ExcelFileDto();
		bobj2.setLanFieldNumber("131");
		bobj2.setLanFieldName("131-LOT SIZE SQFT");
		bobj2.setLength("9");
		bobj2.setTo("1487");
		bobj2.setFrom("1495");
		bobj2.setFileName(getUsedTableNames(131, generateReportService, territoryName, edition));
		bobj2.setStartPosition(getUsedColumnNames(131, generateReportService, territoryName, edition));
		/*
		 * bobj2.setFileName(""); bobj2.setStartPosition("");
		 */
		bobj2.setEndPosition("");
		bobj2.setPullForward("");
		bobj2.setSourceCount("");
		bobj2.setLanCount("");
		// bobj2.setUploadInst("Please find the additional document");
		bobj2.setUploadInst(getUsedRUL(131, generateReportService, territoryName, edition));
		bobj2.setAdditionalReq("");
		list1.add(bobj2);
		ExcelFileDto bobj3 = new ExcelFileDto();
		bobj3.setLanFieldNumber("152");
		bobj3.setLanFieldName("152-LOT SIZE ACRES");
		bobj3.setLength("10");
		bobj3.setTo("1579");
		bobj3.setFrom("1588");
		bobj3.setFileName(getUsedTableNames(152, generateReportService, territoryName, edition));
		bobj3.setStartPosition(getUsedColumnNames(152, generateReportService, territoryName, edition));
		/*
		 * bobj3.setFileName(""); bobj3.setStartPosition("");
		 */
		bobj3.setEndPosition("");
		bobj3.setPullForward("");
		bobj3.setSourceCount("");
		obj3.setLanCount("");
		// bobj3.setUploadInst("Please find the additional document");
		bobj3.setUploadInst(getUsedRUL(152, generateReportService, territoryName, edition));
		bobj3.setAdditionalReq("");
		list1.add(bobj3);
		ExcelFileDto bobj4 = new ExcelFileDto();
		bobj4.setLanFieldNumber("189");
		bobj4.setLanFieldName("189-LOT SIZE FRONTAGE FEET");
		bobj4.setLength("10");
		bobj4.setTo("1857");
		bobj4.setFrom("1866");
		bobj4.setFileName(getUsedTableNames(189, generateReportService, territoryName, edition));
		bobj4.setStartPosition(getUsedColumnNames(189, generateReportService, territoryName, edition));
		/*
		 * bobj4.setFileName("AK_Sitka_06_Main"); bobj4.setStartPosition("");
		 */
		bobj4.setEndPosition("");
		bobj4.setPullForward("");
		bobj4.setSourceCount("");
		bobj4.setLanCount("");
		// bobj4.setUploadInst("Please find the additional document");
		bobj4.setUploadInst(getUsedRUL(189, generateReportService, territoryName, edition));
		bobj4.setAdditionalReq("");
		list1.add(bobj4);
		ExcelFileDto bobj5 = new ExcelFileDto();
		bobj5.setLanFieldNumber("190");
		bobj5.setLanFieldName("190-LOT SIZE DEPTH FEET");
		bobj5.setLength("10");
		bobj5.setTo("1867");
		bobj5.setFrom("1876");
		bobj5.setFileName(getUsedTableNames(109, generateReportService, territoryName, edition));
		bobj5.setStartPosition(getUsedColumnNames(109, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj5.setEndPosition("");
		bobj5.setPullForward("");
		bobj5.setSourceCount("");
		bobj5.setLanCount("");
		// bobj5.setUploadInst("Please find the additional document");
		bobj5.setUploadInst(getUsedRUL(109, generateReportService, territoryName, edition));
		bobj5.setAdditionalReq("");
		list1.add(bobj5);

		ExcelFileDto bobj6 = new ExcelFileDto();
		bobj6.setLanFieldNumber("77");
		bobj6.setLanFieldName("77-BUILDING AREA");
		bobj6.setLength("9");
		bobj6.setTo("1239");
		bobj6.setFrom("1247");
		bobj6.setFileName(getUsedTableNames(77, generateReportService, territoryName, edition));
		bobj6.setStartPosition(getUsedColumnNames(77, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj6.setEndPosition("");
		bobj6.setPullForward("");
		bobj6.setSourceCount("");
		bobj6.setLanCount("");
		// bobj6.setUploadInst("Please find the additional document");
		bobj6.setUploadInst(getUsedRUL(77, generateReportService, territoryName, edition));
		bobj6.setAdditionalReq("");
		list1.add(bobj6);
		ExcelFileDto bobj7 = new ExcelFileDto();
		bobj7.setLanFieldNumber("111");
		bobj7.setLanFieldName("111-BUILDING AREA INDICATOR");
		bobj7.setLength("1");
		bobj7.setTo("1400");
		bobj7.setFrom("1400");
		bobj7.setFileName(getUsedTableNames(111, generateReportService, territoryName, edition));
		bobj7.setStartPosition(getUsedColumnNames(111, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj7.setEndPosition("");
		bobj7.setPullForward("");
		bobj7.setSourceCount("");
		bobj7.setLanCount("");
		// bobj7.setUploadInst("Load L if F77>0 and if F73 starts with 1 Else
		// Load T");
		bobj7.setUploadInst(getUsedRUL(111, generateReportService, territoryName, edition));
		bobj7.setAdditionalReq("");
		list1.add(bobj7);
		ExcelFileDto bobj8 = new ExcelFileDto();
		bobj8.setLanFieldNumber("135");
		bobj8.setLanFieldName("135-BUILDING AREA1");
		bobj8.setLength("8");
		bobj8.setTo("1503");
		bobj8.setFrom("1510");
		bobj8.setFileName(getUsedTableNames(135, generateReportService, territoryName, edition));
		bobj8.setStartPosition(getUsedColumnNames(135, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj8.setEndPosition("");
		bobj8.setPullForward("");
		bobj8.setSourceCount("");
		bobj8.setLanCount("");
		bobj8.setUploadInst(getUsedRUL(135, generateReportService, territoryName, edition));
		// bobj8.setUploadInst("");
		bobj8.setAdditionalReq("");
		list1.add(bobj8);

		ExcelFileDto bobj14 = new ExcelFileDto();
		bobj14.setLanFieldNumber("136");
		bobj14.setLanFieldName("136-BUILDING AREA 1 INDICATOR");
		bobj14.setLength("2");
		bobj14.setTo("1511");
		bobj14.setFrom("1512");
		bobj14.setFileName(getUsedTableNames(136, generateReportService, territoryName, edition));
		bobj14.setStartPosition(getUsedColumnNames(136, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj14.setEndPosition("");
		bobj14.setPullForward("");
		bobj14.setSourceCount("");
		bobj14.setLanCount("");
		// bobj14.setUploadInst("");
		bobj14.setUploadInst(getUsedRUL(136, generateReportService, territoryName, edition));
		bobj14.setAdditionalReq("");
		list1.add(bobj14);
		ExcelFileDto bobj15 = new ExcelFileDto();
		bobj15.setLanFieldNumber("137");
		bobj15.setLanFieldName("137-BUILDING AREA 2");
		bobj15.setLength("8");
		bobj15.setTo("1513");
		bobj15.setFrom("1520");
		bobj15.setFileName(getUsedTableNames(137, generateReportService, territoryName, edition));
		bobj15.setStartPosition(getUsedColumnNames(137, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj15.setEndPosition("");
		bobj15.setPullForward("");
		bobj15.setSourceCount("");
		bobj15.setLanCount("");
		// bobj15.setUploadInst("");
		bobj15.setUploadInst(getUsedRUL(137, generateReportService, territoryName, edition));
		bobj15.setAdditionalReq("");
		list1.add(bobj15);
		ExcelFileDto bobj16 = new ExcelFileDto();
		bobj16.setLanFieldNumber("138");
		bobj16.setLanFieldName("138-BUILDING AREA 2 INDICATOR");
		bobj16.setLength("2");
		bobj16.setTo("1521");
		bobj16.setFrom("1522");
		bobj16.setFileName(getUsedTableNames(138, generateReportService, territoryName, edition));
		bobj16.setStartPosition(getUsedColumnNames(138, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj16.setEndPosition("");
		bobj16.setPullForward("");
		bobj16.setSourceCount("");
		bobj16.setLanCount("");
		// bobj16.setUploadInst("Please find the additional document");
		bobj16.setUploadInst(getUsedRUL(138, generateReportService, territoryName, edition));
		bobj16.setAdditionalReq("");
		list1.add(bobj16);
		ExcelFileDto bobj17 = new ExcelFileDto();
		bobj17.setLanFieldNumber("139");
		bobj17.setLanFieldName("139-BUILDING AREA 3");
		bobj17.setLength("8");
		bobj17.setTo("1523");
		bobj17.setFrom("1530");
		bobj17.setFileName(getUsedTableNames(139, generateReportService, territoryName, edition));
		bobj17.setStartPosition(getUsedColumnNames(139, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj17.setEndPosition("");
		bobj17.setPullForward("");
		bobj17.setSourceCount("");
		bobj17.setLanCount("");
		// bobj17.setUploadInst("");
		bobj17.setUploadInst(getUsedRUL(139, generateReportService, territoryName, edition));
		bobj17.setAdditionalReq("");
		list1.add(bobj17);
		ExcelFileDto bobj18 = new ExcelFileDto();
		bobj18.setLanFieldNumber("140");
		bobj18.setLanFieldName("140-BUILDING AREA 3 INDICATOR");
		bobj18.setLength("2");
		bobj18.setTo("1531");
		bobj18.setFrom("1532");
		bobj18.setFileName(getUsedTableNames(140, generateReportService, territoryName, edition));
		bobj18.setStartPosition(getUsedColumnNames(140, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj18.setEndPosition("");
		bobj18.setPullForward("");
		bobj18.setSourceCount("");
		bobj18.setLanCount("");
		// bobj18.setUploadInst("");
		bobj18.setUploadInst(getUsedRUL(140, generateReportService, territoryName, edition));
		bobj18.setAdditionalReq("");
		list1.add(bobj18);
		ExcelFileDto bobj19 = new ExcelFileDto();
		bobj19.setLanFieldNumber("141");
		bobj19.setLanFieldName("141-BUILDING AREA 4");
		bobj19.setLength("8");
		bobj19.setTo("1533");
		bobj19.setFrom("1540");
		bobj19.setFileName(getUsedTableNames(141, generateReportService, territoryName, edition));
		bobj19.setStartPosition(getUsedColumnNames(141, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj19.setEndPosition("");
		bobj19.setPullForward("");
		bobj19.setSourceCount("");
		bobj19.setLanCount("");
		// bobj19.setUploadInst("");
		bobj19.setUploadInst(getUsedRUL(141, generateReportService, territoryName, edition));
		bobj19.setAdditionalReq("");
		list1.add(bobj19);
		ExcelFileDto bobj20 = new ExcelFileDto();
		bobj20.setLanFieldNumber("142");
		bobj20.setLanFieldName("142-BUILDING AREA 4 INDICATOR");
		bobj20.setLength("2");
		bobj20.setTo("1541");
		bobj20.setFrom("1542");
		bobj20.setFileName(getUsedTableNames(142, generateReportService, territoryName, edition));
		bobj20.setStartPosition(getUsedColumnNames(142, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj20.setEndPosition("");
		bobj20.setPullForward("");
		bobj20.setSourceCount("");
		bobj20.setLanCount("");
		// bobj20.setUploadInst("");
		bobj20.setUploadInst(getUsedRUL(142, generateReportService, territoryName, edition));
		bobj20.setAdditionalReq("");
		list1.add(bobj20);
		ExcelFileDto bobj21 = new ExcelFileDto();
		bobj21.setLanFieldNumber("143");
		bobj21.setLanFieldName("143-BUILDING AREA 5");
		bobj21.setLength("8");
		bobj21.setTo("1543");
		bobj21.setFrom("1550");
		bobj21.setFileName(getUsedTableNames(143, generateReportService, territoryName, edition));
		bobj21.setStartPosition(getUsedColumnNames(143, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj21.setEndPosition("");
		bobj21.setPullForward("");
		bobj21.setSourceCount("");
		bobj21.setLanCount("");
		// bobj21.setUploadInst("");
		bobj21.setUploadInst(getUsedRUL(143, generateReportService, territoryName, edition));
		bobj21.setAdditionalReq("");
		list1.add(bobj21);
		ExcelFileDto bobj22 = new ExcelFileDto();
		bobj22.setLanFieldNumber("144");
		bobj22.setLanFieldName("144-BUILDING AREA 5 INDICATOR");
		bobj22.setLength("2");
		bobj22.setTo("1551");
		bobj22.setFrom("1552");
		bobj22.setFileName(getUsedTableNames(144, generateReportService, territoryName, edition));
		bobj22.setStartPosition(getUsedColumnNames(144, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj22.setEndPosition("");
		bobj22.setPullForward("");
		bobj22.setSourceCount("");
		bobj22.setLanCount("");
		// bobj22.setUploadInst("");
		bobj22.setUploadInst(getUsedRUL(144, generateReportService, territoryName, edition));
		bobj22.setAdditionalReq("");
		list1.add(bobj22);
		ExcelFileDto bobj23 = new ExcelFileDto();
		bobj23.setLanFieldNumber("145");
		bobj23.setLanFieldName("145-BUILDING AREA 6");
		bobj23.setLength("8");
		bobj23.setTo("1553");
		bobj23.setFrom("1560");
		bobj23.setFileName(getUsedTableNames(145, generateReportService, territoryName, edition));
		bobj23.setStartPosition(getUsedColumnNames(145, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj23.setEndPosition("");
		bobj23.setPullForward("");
		bobj23.setSourceCount("");
		bobj23.setLanCount("");
		// bobj23.setUploadInst("");
		bobj23.setUploadInst(getUsedRUL(145, generateReportService, territoryName, edition));
		bobj23.setAdditionalReq("");
		list1.add(bobj23);
		ExcelFileDto bobj24 = new ExcelFileDto();
		bobj24.setLanFieldNumber("146");
		bobj24.setLanFieldName("146-BUILDING AREA 6 INDICATOR");
		bobj24.setLength("2");
		bobj24.setTo("1561");
		bobj24.setFrom("1562");
		bobj24.setFileName(getUsedTableNames(146, generateReportService, territoryName, edition));
		bobj24.setStartPosition(getUsedColumnNames(146, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj24.setEndPosition("");
		bobj24.setPullForward("");
		bobj24.setSourceCount("");
		bobj24.setLanCount("");
		// bobj24.setUploadInst("");
		bobj24.setUploadInst(getUsedRUL(146, generateReportService, territoryName, edition));
		bobj24.setAdditionalReq("");
		list1.add(bobj24);
		ExcelFileDto bobj25 = new ExcelFileDto();
		bobj25.setLanFieldNumber("147");
		bobj25.setLanFieldName("147-BUILDING AREA 7");
		bobj25.setLength("8");
		bobj25.setTo("1563");
		bobj25.setFrom("1570");
		bobj25.setFileName(getUsedTableNames(147, generateReportService, territoryName, edition));
		bobj25.setStartPosition(getUsedColumnNames(147, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj25.setEndPosition("");
		bobj25.setPullForward("");
		bobj25.setSourceCount("");
		bobj25.setLanCount("");
		// bobj25.setUploadInst("");
		bobj25.setUploadInst(getUsedRUL(147, generateReportService, territoryName, edition));
		bobj25.setAdditionalReq("");
		list1.add(bobj25);
		ExcelFileDto bobj26 = new ExcelFileDto();
		bobj26.setLanFieldNumber("148");
		bobj26.setLanFieldName("148-BUILDING AREA 7 INDICATOR");
		bobj26.setLength("2");
		bobj26.setTo("1571");
		bobj26.setFrom("1572");
		bobj26.setFileName(getUsedTableNames(148, generateReportService, territoryName, edition));
		bobj26.setStartPosition(getUsedColumnNames(148, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj26.setEndPosition("");
		bobj26.setPullForward("");
		bobj26.setSourceCount("");
		bobj26.setLanCount("");
		// bobj26.setUploadInst("");
		bobj26.setUploadInst(getUsedRUL(148, generateReportService, territoryName, edition));
		bobj26.setAdditionalReq("");
		list1.add(bobj26);
		ExcelFileDto bobj27 = new ExcelFileDto();
		bobj27.setLanFieldNumber("171");
		bobj27.setLanFieldName("171-OTHER IMPR BUILDING AREA 1");
		bobj27.setLength("9");
		bobj27.setTo("1729");
		bobj27.setFrom("1737");
		bobj27.setFileName(getUsedTableNames(171, generateReportService, territoryName, edition));
		bobj27.setStartPosition(getUsedColumnNames(171, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj27.setEndPosition("");
		bobj27.setPullForward("");
		bobj27.setSourceCount("");
		bobj27.setLanCount("");
		bobj27.setUploadInst(getUsedRUL(171, generateReportService, territoryName, edition));
		// bobj27.setUploadInst("");
		bobj27.setAdditionalReq("");
		list1.add(bobj27);
		ExcelFileDto bobj28 = new ExcelFileDto();
		bobj28.setLanFieldNumber("172");
		bobj28.setLanFieldName("172-OTHER IMPR BUILDING AREA 2");
		bobj28.setLength("9");
		bobj28.setTo("1738");
		bobj28.setFrom("1746");
		bobj28.setFileName(getUsedTableNames(172, generateReportService, territoryName, edition));
		bobj28.setStartPosition(getUsedColumnNames(172, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj28.setEndPosition("");
		bobj28.setPullForward("");
		bobj28.setSourceCount("");
		bobj28.setLanCount("");
		// bobj28.setUploadInst("");
		bobj28.setUploadInst(getUsedRUL(172, generateReportService, territoryName, edition));
		bobj28.setAdditionalReq("");
		list1.add(bobj28);
		ExcelFileDto bobj29 = new ExcelFileDto();
		bobj29.setLanFieldNumber("173");
		bobj29.setLanFieldName("173-OTHER IMPR BUILDING AREA 3");
		bobj29.setLength("9");
		bobj29.setTo("1747");
		bobj29.setFrom("1755");
		bobj29.setFileName(getUsedTableNames(173, generateReportService, territoryName, edition));
		bobj29.setStartPosition(getUsedColumnNames(173, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj29.setEndPosition("");
		bobj29.setPullForward("");
		bobj29.setSourceCount("");
		bobj29.setLanCount("");
		// bobj29.setUploadInst("");
		bobj29.setUploadInst(getUsedRUL(173, generateReportService, territoryName, edition));
		bobj29.setAdditionalReq("");
		list1.add(bobj29);
		ExcelFileDto bobj30 = new ExcelFileDto();
		bobj30.setLanFieldNumber("174");
		bobj30.setLanFieldName("174-OTHER IMPR BUILDING AREA 4");
		bobj30.setLength("9");
		bobj30.setTo("1756");
		bobj30.setFrom("1764");
		bobj30.setFileName(getUsedTableNames(174, generateReportService, territoryName, edition));
		bobj30.setStartPosition(getUsedColumnNames(174, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj30.setEndPosition("");
		bobj30.setPullForward("");
		bobj30.setSourceCount("");
		bobj30.setLanCount("");
		// bobj30.setUploadInst("");
		bobj30.setUploadInst(getUsedRUL(174, generateReportService, territoryName, edition));
		bobj30.setAdditionalReq("");
		list1.add(bobj30);
		ExcelFileDto bobj31 = new ExcelFileDto();
		bobj31.setLanFieldNumber("175");
		bobj31.setLanFieldName("175-OTHER IMPR BUILDING AREA 5");
		bobj31.setLength("9");
		bobj31.setTo("1765");
		bobj31.setFrom("1773");
		bobj31.setFileName(getUsedTableNames(175, generateReportService, territoryName, edition));
		bobj31.setStartPosition(getUsedColumnNames(175, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj31.setEndPosition("");
		bobj31.setPullForward("");
		bobj31.setSourceCount("");
		bobj31.setLanCount("");
		// bobj31.setUploadInst("");
		bobj31.setUploadInst(getUsedRUL(175, generateReportService, territoryName, edition));
		bobj31.setAdditionalReq("");
		list1.add(bobj31);
		ExcelFileDto bobj32 = new ExcelFileDto();
		bobj32.setLanFieldNumber("163");
		bobj32.setLanFieldName("163-OTHER IMPR BUILDING INDICATOR 1");
		bobj32.setLength("2");
		bobj32.setTo("1685");
		bobj32.setFrom("1686");
		bobj32.setFileName(getUsedTableNames(163, generateReportService, territoryName, edition));
		bobj32.setStartPosition(getUsedColumnNames(163, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj32.setEndPosition("");
		bobj32.setPullForward("");
		bobj32.setSourceCount("");
		bobj32.setLanCount("");
		// bobj32.setUploadInst("");
		bobj32.setUploadInst(getUsedRUL(163, generateReportService, territoryName, edition));
		bobj32.setAdditionalReq("");
		list1.add(bobj32);
		ExcelFileDto bobj33 = new ExcelFileDto();
		bobj33.setLanFieldNumber("164");
		bobj33.setLanFieldName("164-OTHER IMPR BUILDING INDICATOR 2");
		bobj33.setLength("2");
		bobj33.setTo("1687");
		bobj33.setFrom("1688");
		bobj33.setFileName(getUsedTableNames(164, generateReportService, territoryName, edition));
		bobj33.setStartPosition(getUsedColumnNames(164, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj33.setEndPosition("");
		bobj33.setPullForward("");
		bobj33.setSourceCount("");
		bobj33.setLanCount("");
		// bobj33.setUploadInst("");
		bobj33.setUploadInst(getUsedRUL(164, generateReportService, territoryName, edition));
		bobj33.setAdditionalReq("");
		list1.add(bobj33);
		ExcelFileDto bobj34 = new ExcelFileDto();
		bobj34.setLanFieldNumber("165");
		bobj34.setLanFieldName("165-OTHER IMPR BUILDING INDICATOR 3");
		bobj34.setLength("2");
		bobj34.setTo("1689");
		bobj34.setFrom("1690");
		bobj34.setFileName(getUsedTableNames(165, generateReportService, territoryName, edition));
		bobj34.setStartPosition(getUsedColumnNames(165, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj34.setEndPosition("");
		bobj34.setPullForward("");
		bobj34.setSourceCount("");
		bobj34.setLanCount("");
		// bobj34.setUploadInst("");
		bobj34.setUploadInst(getUsedRUL(165, generateReportService, territoryName, edition));
		bobj34.setAdditionalReq("");
		list1.add(bobj34);
		ExcelFileDto bobj35 = new ExcelFileDto();
		bobj35.setLanFieldNumber("166");
		bobj35.setLanFieldName("166-OTHER IMPR BUILDING INDICATOR 4");
		bobj35.setLength("2");
		bobj35.setTo("1691");
		bobj35.setFrom("1692");
		bobj35.setFileName(getUsedTableNames(166, generateReportService, territoryName, edition));
		bobj35.setStartPosition(getUsedColumnNames(166, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj35.setEndPosition("");
		bobj35.setPullForward("");
		bobj35.setSourceCount("");
		bobj35.setLanCount("");
		// bobj35.setUploadInst("");
		bobj35.setUploadInst(getUsedRUL(166, generateReportService, territoryName, edition));
		bobj35.setAdditionalReq("");
		list1.add(bobj35);
		ExcelFileDto bobj36 = new ExcelFileDto();
		bobj36.setLanFieldNumber("169");
		bobj36.setLanFieldName("169-OTHER IMPR BUILDING INDICATOR 5");
		bobj36.setLength("2");
		bobj36.setTo("1722");
		bobj36.setFrom("1723");
		bobj36.setFileName(getUsedTableNames(169, generateReportService, territoryName, edition));
		bobj36.setStartPosition(getUsedColumnNames(169, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj36.setEndPosition("");
		bobj36.setPullForward("");
		bobj36.setSourceCount("");
		bobj36.setLanCount("");
		// bobj36.setUploadInst("");
		bobj36.setUploadInst(getUsedRUL(169, generateReportService, territoryName, edition));
		bobj36.setAdditionalReq("");
		list1.add(bobj36);
		ExcelFileDto bobj37 = new ExcelFileDto();
		bobj37.setLanFieldNumber("79");
		bobj37.setLanFieldName("79-NO. OF BUILDINGS");
		bobj37.setLength("3");
		bobj37.setTo("1252");
		bobj37.setFrom("1254");
		bobj37.setFileName(getUsedTableNames(79, generateReportService, territoryName, edition));
		bobj37.setStartPosition(getUsedColumnNames(79, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj37.setEndPosition("");
		bobj37.setPullForward("");
		bobj37.setSourceCount("");
		bobj37.setLanCount("");
		// bobj37.setUploadInst("");
		bobj37.setUploadInst(getUsedRUL(79, generateReportService, territoryName, edition));
		bobj37.setAdditionalReq("");
		list1.add(bobj37);
		ExcelFileDto bobj38 = new ExcelFileDto();
		bobj38.setLanFieldNumber("80");
		bobj38.setLanFieldName("80-NO. OF STORIES");
		bobj38.setLength("5");
		bobj38.setTo("1255");
		bobj38.setFrom("1259");
		bobj38.setFileName(getUsedTableNames(80, generateReportService, territoryName, edition));
		bobj38.setStartPosition(getUsedColumnNames(80, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj38.setEndPosition("");
		bobj38.setPullForward("");
		bobj38.setSourceCount("");
		bobj38.setLanCount("");
		// bobj38.setUploadInst("");
		bobj38.setUploadInst(getUsedRUL(80, generateReportService, territoryName, edition));
		bobj38.setAdditionalReq("");
		list1.add(bobj38);
		ExcelFileDto bobj39 = new ExcelFileDto();
		bobj39.setLanFieldNumber("81");
		bobj39.setLanFieldName("81-NO. OF UNITS");
		bobj39.setLength("4");
		bobj39.setTo("1260");
		bobj39.setFrom("1263");
		bobj39.setFileName(getUsedTableNames(81, generateReportService, territoryName, edition));
		bobj39.setStartPosition(getUsedColumnNames(81, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj39.setEndPosition("");
		bobj39.setPullForward("");
		bobj39.setSourceCount("");
		bobj39.setLanCount("");
		// bobj39.setUploadInst("");
		bobj39.setUploadInst(getUsedRUL(81, generateReportService, territoryName, edition));
		bobj39.setAdditionalReq("");
		list1.add(bobj39);
		ExcelFileDto bobj40 = new ExcelFileDto();
		bobj40.setLanFieldNumber("78");
		bobj40.setLanFieldName("78-YEAR BUILT");
		bobj40.setLength("4");
		bobj40.setTo("1248");
		bobj40.setFrom("1251");
		bobj40.setFileName(getUsedTableNames(78, generateReportService, territoryName, edition));
		bobj40.setStartPosition(getUsedColumnNames(78, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj40.setEndPosition("");
		bobj40.setPullForward("");
		bobj40.setSourceCount("");
		bobj40.setLanCount("");
		// bobj40.setUploadInst("");
		bobj40.setUploadInst(getUsedRUL(78, generateReportService, territoryName, edition));
		bobj40.setAdditionalReq("");
		list1.add(bobj40);

		ExcelFileDto bobj41 = new ExcelFileDto();
		bobj41.setLanFieldNumber("149");
		bobj41.setLanFieldName("149-EFFECTIVE YEAR BUILT");
		bobj41.setLength("4");
		bobj41.setTo("1573");
		bobj41.setFrom("1576");
		bobj41.setFileName(getUsedTableNames(149, generateReportService, territoryName, edition));
		bobj41.setStartPosition(getUsedColumnNames(149, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj41.setEndPosition("");
		bobj41.setPullForward("");
		bobj41.setSourceCount("");
		bobj41.setLanCount("");
		// bobj41.setUploadInst("");
		bobj41.setUploadInst(getUsedRUL(149, generateReportService, territoryName, edition));
		bobj41.setAdditionalReq("");
		list1.add(bobj41);

		ExcelFileDto bobj42 = new ExcelFileDto();
		bobj42.setLanFieldNumber("98");
		bobj42.setLanFieldName("98-BUILDING CLASS");
		bobj42.setLength("1");
		bobj42.setTo("1384");
		bobj42.setFrom("1384");
		bobj42.setFileName(getUsedTableNames(98, generateReportService, territoryName, edition));
		bobj42.setStartPosition(getUsedColumnNames(98, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj42.setEndPosition("");
		bobj42.setPullForward("");
		bobj42.setSourceCount("");
		bobj42.setLanCount("");
		// bobj42.setUploadInst("");
		bobj42.setUploadInst(getUsedRUL(98, generateReportService, territoryName, edition));
		bobj42.setAdditionalReq("");
		list1.add(bobj42);

		ExcelFileDto bobj43 = new ExcelFileDto();
		bobj43.setLanFieldNumber("132");
		bobj43.setLanFieldName("132-BUILDING QUALITY");
		bobj43.setLength("2");
		bobj43.setTo("1496");
		bobj43.setFrom("1497");
		bobj43.setFileName(getUsedTableNames(132, generateReportService, territoryName, edition));
		bobj43.setStartPosition(getUsedColumnNames(132, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj43.setEndPosition("");
		bobj43.setPullForward("");
		bobj43.setSourceCount("");
		bobj43.setLanCount("");
		// bobj43.setUploadInst("");
		bobj43.setUploadInst(getUsedRUL(132, generateReportService, territoryName, edition));
		bobj43.setAdditionalReq("");
		list1.add(bobj43);

		ExcelFileDto bobj44 = new ExcelFileDto();
		bobj44.setLanFieldNumber("188");
		bobj44.setLanFieldName("188-BUILDING CONDITION");
		bobj44.setLength("1");
		bobj44.setTo("1856");
		bobj44.setFrom("1856");
		bobj44.setFileName(getUsedTableNames(188, generateReportService, territoryName, edition));
		bobj44.setStartPosition(getUsedColumnNames(188, generateReportService, territoryName, edition));
		/*
		 * bobj5.setFileName("AK_Sitka_06_Main"); bobj5.setStartPosition("");
		 */
		bobj44.setEndPosition("");
		bobj44.setPullForward("");
		bobj44.setSourceCount("");
		bobj44.setLanCount("");
		// bobj44.setUploadInst("");
		bobj44.setUploadInst(getUsedRUL(188, generateReportService, territoryName, edition));
		bobj44.setAdditionalReq("");
		list1.add(bobj44);

		List<ExcelFileDto> list2 = new ArrayList<ExcelFileDto>();
		ExcelFileDto cbobj1 = new ExcelFileDto();
		cbobj1.setLanFieldNumber("99");
		cbobj1.setLanFieldName("99-STYLE");
		cbobj1.setLength("1");
		cbobj1.setTo("1385");
		cbobj1.setFrom("1385");
		cbobj1.setFileName(getUsedTableNames(99, generateReportService, territoryName, edition));
		cbobj1.setStartPosition(getUsedColumnNames(99, generateReportService, territoryName, edition));
		/*
		 * cbobj1.setFileName(""); cbobj1.setStartPosition("");
		 */
		cbobj1.setEndPosition("");
		cbobj1.setPullForward("");
		cbobj1.setSourceCount("");
		cbobj1.setLanCount("");
		// cbobj1.setUploadInst("");
		cbobj1.setUploadInst(getUsedRUL(99, generateReportService, territoryName, edition));
		cbobj1.setAdditionalReq("");
		list2.add(cbobj1);
		ExcelFileDto dbobj2 = new ExcelFileDto();
		dbobj2.setLanFieldNumber("100");
		dbobj2.setLanFieldName("100-TYPE CONSTRUCTION");
		dbobj2.setLength("1");
		dbobj2.setTo("1386");
		dbobj2.setFrom("1386");
		dbobj2.setFileName(getUsedTableNames(100, generateReportService, territoryName, edition));
		dbobj2.setStartPosition(getUsedColumnNames(100, generateReportService, territoryName, edition));
		/*
		 * dbobj2.setFileName(""); dbobj2.setStartPosition("");
		 */
		dbobj2.setEndPosition("");
		dbobj2.setPullForward("");
		dbobj2.setSourceCount("");
		dbobj2.setLanCount("");
		// dbobj2.setUploadInst("");
		dbobj2.setUploadInst(getUsedRUL(100, generateReportService, territoryName, edition));
		dbobj2.setAdditionalReq("");
		list2.add(dbobj2);
		ExcelFileDto ebobj3 = new ExcelFileDto();
		ebobj3.setLanFieldNumber("101");
		ebobj3.setLanFieldName("101-EXTERIOR WALLS");
		ebobj3.setLength("1");
		ebobj3.setTo("1387");
		ebobj3.setFrom("1387");
		ebobj3.setFileName(getUsedTableNames(101, generateReportService, territoryName, edition));
		ebobj3.setStartPosition(getUsedColumnNames(101, generateReportService, territoryName, edition));
		/*
		 * ebobj3.setFileName(""); ebobj3.setStartPosition("");
		 */
		ebobj3.setEndPosition("");
		ebobj3.setPullForward("");
		ebobj3.setSourceCount("");
		ebobj3.setLanCount("");
		// ebobj3.setUploadInst("");
		ebobj3.setUploadInst(getUsedRUL(101, generateReportService, territoryName, edition));
		ebobj3.setAdditionalReq("");
		list2.add(ebobj3);
		ExcelFileDto dbobj4 = new ExcelFileDto();
		dbobj4.setLanFieldNumber("103");
		dbobj4.setLanFieldName("103-ROOF COVER");
		dbobj4.setLength("1");
		dbobj4.setTo("1389");
		dbobj4.setFrom("1389");
		dbobj4.setFileName(getUsedTableNames(103, generateReportService, territoryName, edition));
		dbobj4.setStartPosition(getUsedColumnNames(103, generateReportService, territoryName, edition));
		/*
		 * dbobj4.setFileName("AK_Sitka_06_Main"); dbobj4.setStartPosition("");
		 */
		dbobj4.setEndPosition("");
		dbobj4.setPullForward("");
		dbobj4.setSourceCount("");
		dbobj4.setLanCount("");
		// dbobj4.setUploadInst("");
		dbobj4.setUploadInst(getUsedRUL(103, generateReportService, territoryName, edition));
		dbobj4.setAdditionalReq("");
		list2.add(dbobj4);
		ExcelFileDto fbobj5 = new ExcelFileDto();
		fbobj5.setLanFieldNumber("180");
		fbobj5.setLanFieldName("180-ROOF TYPE");
		fbobj5.setLength("1");
		fbobj5.setTo("1791");
		fbobj5.setFrom("1791");
		fbobj5.setFileName(getUsedTableNames(180, generateReportService, territoryName, edition));
		fbobj5.setStartPosition(getUsedColumnNames(180, generateReportService, territoryName, edition));
		/*
		 * fbobj5.setFileName("AK_Sitka_06_Main"); fbobj5.setStartPosition("");
		 */
		fbobj5.setEndPosition("");
		fbobj5.setPullForward("");
		fbobj5.setSourceCount("");
		fbobj5.setLanCount("");
		// fbobj5.setUploadInst("");
		fbobj5.setUploadInst(getUsedRUL(180, generateReportService, territoryName, edition));
		fbobj5.setAdditionalReq("");
		list2.add(fbobj5);
		ExcelFileDto fbobj6 = new ExcelFileDto();
		fbobj6.setLanFieldNumber("86");
		fbobj6.setLanFieldName("86-GARAGE TYPE (PARKING)");
		fbobj6.setLength("1");
		fbobj6.setTo("1274");
		fbobj6.setFrom("1274");
		fbobj6.setFileName(getUsedTableNames(86, generateReportService, territoryName, edition));
		fbobj6.setStartPosition(getUsedColumnNames(86, generateReportService, territoryName, edition));
		/*
		 * fbobj5.setFileName("AK_Sitka_06_Main"); fbobj5.setStartPosition("");
		 */
		fbobj6.setEndPosition("");
		fbobj6.setPullForward("");
		fbobj6.setSourceCount("");
		fbobj6.setLanCount("");
		// fbobj6.setUploadInst("");
		fbobj6.setUploadInst(getUsedRUL(86, generateReportService, territoryName, edition));
		fbobj6.setAdditionalReq("");
		list2.add(fbobj6);
		ExcelFileDto fbobj7 = new ExcelFileDto();
		fbobj7.setLanFieldNumber("87");
		fbobj7.setLanFieldName("87-PARKING - # OF CARS");
		fbobj7.setLength("4");
		fbobj7.setTo("1275");
		fbobj7.setFrom("1278");
		fbobj7.setFileName(getUsedTableNames(87, generateReportService, territoryName, edition));
		fbobj7.setStartPosition(getUsedColumnNames(87, generateReportService, territoryName, edition));
		/*
		 * fbobj5.setFileName("AK_Sitka_06_Main"); fbobj5.setStartPosition("");
		 */
		fbobj7.setEndPosition("");
		fbobj7.setPullForward("");
		fbobj7.setSourceCount("");
		fbobj7.setLanCount("");
		// fbobj7.setUploadInst("");
		fbobj7.setUploadInst(getUsedRUL(87, generateReportService, territoryName, edition));
		fbobj7.setAdditionalReq("");
		list2.add(fbobj7);
		ExcelFileDto fbobj8 = new ExcelFileDto();
		fbobj8.setLanFieldNumber("88");
		fbobj8.setLanFieldName("88-POOL");
		fbobj8.setLength("1");
		fbobj8.setTo("1279");
		fbobj8.setFrom("1279");
		fbobj8.setFileName(getUsedTableNames(88, generateReportService, territoryName, edition));
		fbobj8.setStartPosition(getUsedColumnNames(88, generateReportService, territoryName, edition));
		/*
		 * fbobj5.setFileName("AK_Sitka_06_Main"); fbobj5.setStartPosition("");
		 */
		fbobj8.setEndPosition("");
		fbobj8.setPullForward("");
		fbobj8.setSourceCount("");
		fbobj8.setLanCount("");
		// fbobj8.setUploadInst("");
		fbobj8.setUploadInst(getUsedRUL(88, generateReportService, territoryName, edition));
		fbobj8.setAdditionalReq("");
		list2.add(fbobj8);

		List<ExcelFileDto> list3 = new ArrayList<ExcelFileDto>();
		ExcelFileDto gbobj1 = new ExcelFileDto();
		gbobj1.setLanFieldNumber("82");
		gbobj1.setLanFieldName("82-TOTAL NO. OF ROOMS");
		gbobj1.setLength("2");
		gbobj1.setTo("1264");
		gbobj1.setFrom("1265");
		gbobj1.setFileName(getUsedTableNames(82, generateReportService, territoryName, edition));
		gbobj1.setStartPosition(getUsedColumnNames(82, generateReportService, territoryName, edition));
		/*
		 * cbobj1.setFileName(""); gbobj1.setStartPosition("");
		 */
		gbobj1.setEndPosition("");
		gbobj1.setPullForward("");
		gbobj1.setSourceCount("");
		gbobj1.setLanCount("");
		// gbobj1.setUploadInst("");
		gbobj1.setUploadInst(getUsedRUL(82, generateReportService, territoryName, edition));
		gbobj1.setAdditionalReq("");
		list3.add(gbobj1);
		ExcelFileDto hdbobj2 = new ExcelFileDto();
		hdbobj2.setLanFieldNumber("83");
		hdbobj2.setLanFieldName("83-NO. OF BEDROOMS");
		hdbobj2.setLength("2");
		hdbobj2.setTo("1266");
		hdbobj2.setFrom("1267");
		hdbobj2.setFileName(getUsedTableNames(83, generateReportService, territoryName, edition));
		hdbobj2.setStartPosition(getUsedColumnNames(83, generateReportService, territoryName, edition));
		/*
		 * hdbobj2.setFileName(""); hdbobj2.setStartPosition("");
		 */
		hdbobj2.setEndPosition("");
		hdbobj2.setPullForward("");
		hdbobj2.setSourceCount("");
		hdbobj2.setLanCount("");
		// hdbobj2.setUploadInst("");
		hdbobj2.setUploadInst(getUsedRUL(83, generateReportService, territoryName, edition));
		hdbobj2.setAdditionalReq("");
		list3.add(hdbobj2);
		ExcelFileDto iebobj3 = new ExcelFileDto();
		iebobj3.setLanFieldNumber("176");
		iebobj3.setLanFieldName("176-OTHER ROOMS");
		iebobj3.setLength("5");
		iebobj3.setTo("1774");
		iebobj3.setFrom("1778");
		iebobj3.setFileName(getUsedTableNames(176, generateReportService, territoryName, edition));
		iebobj3.setStartPosition(getUsedColumnNames(176, generateReportService, territoryName, edition));
		/*
		 * iebobj3.setFileName(""); iebobj3.setStartPosition("");
		 */
		iebobj3.setEndPosition("");
		iebobj3.setPullForward("");
		iebobj3.setSourceCount("");
		iebobj3.setLanCount("");
		iebobj3.setUploadInst(getUsedRUL(176, generateReportService, territoryName, edition));
		// iebobj3.setUploadInst("");
		iebobj3.setAdditionalReq("");
		list3.add(iebobj3);
		ExcelFileDto jdbobj4 = new ExcelFileDto();
		jdbobj4.setLanFieldNumber("84");
		jdbobj4.setLanFieldName("84-NO. OF BATHS");
		jdbobj4.setLength("4");
		jdbobj4.setTo("1268");
		jdbobj4.setFrom("1271");
		jdbobj4.setFileName(getUsedTableNames(84, generateReportService, territoryName, edition));
		jdbobj4.setStartPosition(getUsedColumnNames(84, generateReportService, territoryName, edition));
		/*
		 * jdbobj4.setFileName("AK_Sitka_06_Main");
		 * jdbobj4.setStartPosition("");
		 */
		jdbobj4.setEndPosition("");
		jdbobj4.setPullForward("");
		jdbobj4.setSourceCount("");
		jdbobj4.setLanCount("");
		// jdbobj4.setUploadInst("");
		jdbobj4.setUploadInst(getUsedRUL(84, generateReportService, territoryName, edition));
		jdbobj4.setAdditionalReq("");
		list3.add(jdbobj4);
		ExcelFileDto kbobj5 = new ExcelFileDto();
		kbobj5.setLanFieldNumber("85");
		kbobj5.setLanFieldName("85-NO. OF PARTIAL BATHS");
		kbobj5.setLength("2");
		kbobj5.setTo("1272");
		kbobj5.setFrom("1273");
		kbobj5.setFileName(getUsedTableNames(85, generateReportService, territoryName, edition));
		kbobj5.setStartPosition(getUsedColumnNames(85, generateReportService, territoryName, edition));
		/*
		 * kbobj5.setFileName("AK_Sitka_06_Main"); kbobj5.setStartPosition("");
		 */
		kbobj5.setEndPosition("");
		kbobj5.setPullForward("");
		kbobj5.setSourceCount("");
		kbobj5.setLanCount("");
		// kbobj5.setUploadInst("");
		kbobj5.setUploadInst(getUsedRUL(85, generateReportService, territoryName, edition));
		kbobj5.setAdditionalReq("");
		list3.add(kbobj5);

		ExcelFileDto kbobj6 = new ExcelFileDto();
		kbobj6.setLanFieldNumber("110");
		kbobj6.setLanFieldName("110-BATH MATCH CODE (BATH FILE)");
		kbobj6.setLength("3");
		kbobj6.setTo("1397");
		kbobj6.setFrom("1399");
		kbobj6.setFileName(getUsedTableNames(110, generateReportService, territoryName, edition));
		kbobj6.setStartPosition(getUsedColumnNames(110, generateReportService, territoryName, edition));
		/*
		 * kbobj5.setFileName("AK_Sitka_06_Main"); kbobj5.setStartPosition("");
		 */
		kbobj6.setEndPosition("");
		kbobj6.setPullForward("");
		kbobj6.setSourceCount("");
		kbobj6.setLanCount("");
		// kbobj6.setUploadInst("");
		kbobj6.setUploadInst(getUsedRUL(110, generateReportService, territoryName, edition));
		kbobj6.setAdditionalReq("");
		list3.add(kbobj6);
		ExcelFileDto kbobj7 = new ExcelFileDto();
		kbobj7.setLanFieldNumber("108");
		kbobj7.setLanFieldName("108-BASEMENT");
		kbobj7.setLength("1");
		kbobj7.setTo("1394");
		kbobj7.setFrom("1394");
		kbobj7.setFileName(getUsedTableNames(108, generateReportService, territoryName, edition));
		kbobj7.setStartPosition(getUsedColumnNames(108, generateReportService, territoryName, edition));
		/*
		 * kbobj5.setFileName("AK_Sitka_06_Main"); kbobj5.setStartPosition("");
		 */
		kbobj7.setEndPosition("");
		kbobj7.setPullForward("");
		kbobj7.setSourceCount("");
		kbobj7.setLanCount("");
		// kbobj7.setUploadInst("");
		kbobj5.setUploadInst(getUsedRUL(108, generateReportService, territoryName, edition));
		kbobj7.setAdditionalReq("");
		list3.add(kbobj7);
		ExcelFileDto kbobj8 = new ExcelFileDto();
		kbobj8.setLanFieldNumber("104");
		kbobj8.setLanFieldName("104-HEATING");
		kbobj8.setLength("1");
		kbobj8.setTo("1390");
		kbobj8.setFrom("1390");
		kbobj8.setFileName(getUsedTableNames(104, generateReportService, territoryName, edition));
		kbobj8.setStartPosition(getUsedColumnNames(104, generateReportService, territoryName, edition));
		/*
		 * kbobj5.setFileName("AK_Sitka_06_Main"); kbobj5.setStartPosition("");
		 */
		kbobj8.setEndPosition("");
		kbobj8.setPullForward("");
		kbobj8.setSourceCount("");
		kbobj8.setLanCount("");
		// kbobj8.setUploadInst("");
		kbobj8.setUploadInst(getUsedRUL(104, generateReportService, territoryName, edition));
		kbobj8.setAdditionalReq("");
		list3.add(kbobj8);
		ExcelFileDto kbobj9 = new ExcelFileDto();
		kbobj9.setLanFieldNumber("150");
		kbobj9.setLanFieldName("150-HEATING FUEL TYPE");
		kbobj9.setLength("1");
		kbobj9.setTo("1577");
		kbobj9.setFrom("1577");
		kbobj9.setFileName(getUsedTableNames(150, generateReportService, territoryName, edition));
		kbobj9.setStartPosition(getUsedColumnNames(150, generateReportService, territoryName, edition));
		/*
		 * kbobj5.setFileName("AK_Sitka_06_Main"); kbobj5.setStartPosition("");
		 */
		kbobj9.setEndPosition("");
		kbobj9.setPullForward("");
		kbobj9.setSourceCount("");
		kbobj9.setLanCount("");
		kbobj9.setUploadInst(getUsedRUL(150, generateReportService, territoryName, edition));
		// kbobj9.setUploadInst("");
		kbobj9.setAdditionalReq("");
		list3.add(kbobj9);
		ExcelFileDto kbobj10 = new ExcelFileDto();
		kbobj10.setLanFieldNumber("107");
		kbobj10.setLanFieldName("107-FIREPLACE");
		kbobj10.setLength("1");
		kbobj10.setTo("1393");
		kbobj10.setFrom("1393");
		kbobj10.setFileName(getUsedTableNames(107, generateReportService, territoryName, edition));
		kbobj10.setStartPosition(getUsedColumnNames(107, generateReportService, territoryName, edition));
		/*
		 * kbobj5.setFileName("AK_Sitka_06_Main"); kbobj5.setStartPosition("");
		 */
		kbobj10.setEndPosition("");
		kbobj10.setPullForward("");
		kbobj10.setSourceCount("");
		kbobj10.setLanCount("");
		// kbobj10.setUploadInst("");
		kbobj10.setUploadInst(getUsedRUL(107, generateReportService, territoryName, edition));
		kbobj10.setAdditionalReq("");
		list3.add(kbobj10);
		ExcelFileDto kbobj11 = new ExcelFileDto();
		kbobj11.setLanFieldNumber("105");
		kbobj11.setLanFieldName("105-AIR CONDITIONING");
		kbobj11.setLength("1");
		kbobj11.setTo("1391");
		kbobj11.setFrom("1391");
		kbobj11.setFileName(getUsedTableNames(105, generateReportService, territoryName, edition));
		kbobj11.setStartPosition(getUsedColumnNames(105, generateReportService, territoryName, edition));
		/*
		 * kbobj5.setFileName("AK_Sitka_06_Main"); kbobj5.setStartPosition("");
		 */
		kbobj11.setEndPosition("");
		kbobj11.setPullForward("");
		kbobj11.setSourceCount("");
		kbobj11.setLanCount("");
		// kbobj11.setUploadInst("");
		kbobj11.setUploadInst(getUsedRUL(105, generateReportService, territoryName, edition));
		kbobj11.setAdditionalReq("");
		list3.add(kbobj11);
		ExcelFileDto kbobj12 = new ExcelFileDto();
		kbobj12.setLanFieldNumber("151");
		kbobj12.setLanFieldName("151-AIR CONDITIONING TYPE");
		kbobj12.setLength("1");
		kbobj12.setTo("1578");
		kbobj12.setFrom("1578");
		kbobj12.setFileName(getUsedTableNames(151, generateReportService, territoryName, edition));
		kbobj12.setStartPosition(getUsedColumnNames(151, generateReportService, territoryName, edition));
		/*
		 * kbobj5.setFileName("AK_Sitka_06_Main"); kbobj5.setStartPosition("");
		 */
		kbobj12.setEndPosition("");
		kbobj12.setPullForward("");
		kbobj12.setSourceCount("");
		kbobj12.setLanCount("");
		// kbobj12.setUploadInst("");
		kbobj12.setUploadInst(getUsedRUL(151, generateReportService, territoryName, edition));
		kbobj12.setAdditionalReq("");
		list3.add(kbobj12);
		ExcelFileDto kbobj13 = new ExcelFileDto();
		kbobj13.setLanFieldNumber("102");
		kbobj13.setLanFieldName("102-FOUNDATION");
		kbobj13.setLength("1");
		kbobj13.setTo("1388");
		kbobj13.setFrom("1388");
		kbobj13.setFileName(getUsedTableNames(102, generateReportService, territoryName, edition));
		kbobj13.setStartPosition(getUsedColumnNames(102, generateReportService, territoryName, edition));
		/*
		 * kbobj5.setFileName("AK_Sitka_06_Main"); kbobj5.setStartPosition("");
		 */
		kbobj13.setEndPosition("");
		kbobj13.setPullForward("");
		kbobj13.setSourceCount("");
		kbobj13.setLanCount("");
		// kbobj13.setUploadInst("");
		kbobj13.setUploadInst(getUsedRUL(102, generateReportService, territoryName, edition));
		kbobj13.setAdditionalReq("");
		list3.add(kbobj13);
		ExcelFileDto kbobj14 = new ExcelFileDto();
		kbobj14.setLanFieldNumber("133");
		kbobj14.setLanFieldName("133-FLOOR COVER");
		kbobj14.setLength("2");
		kbobj14.setTo("1498");
		kbobj14.setFrom("1499");
		kbobj14.setFileName(getUsedTableNames(133, generateReportService, territoryName, edition));
		kbobj14.setStartPosition(getUsedColumnNames(133, generateReportService, territoryName, edition));
		/*
		 * kbobj5.setFileName("AK_Sitka_06_Main"); kbobj5.setStartPosition("");
		 */
		kbobj14.setEndPosition("");
		kbobj14.setPullForward("");
		kbobj14.setSourceCount("");
		kbobj14.setLanCount("");
		// kbobj14.setUploadInst("");
		kbobj14.setUploadInst(getUsedRUL(133, generateReportService, territoryName, edition));
		kbobj14.setAdditionalReq("");
		list3.add(kbobj14);
		ExcelFileDto kbobj15 = new ExcelFileDto();
		kbobj15.setLanFieldNumber("134");
		kbobj15.setLanFieldName("134-NO OF PLUMBING FIXTURES");
		kbobj15.setLength("3");
		kbobj15.setTo("1500");
		kbobj15.setFrom("1502");
		kbobj15.setFileName(getUsedTableNames(134, generateReportService, territoryName,edition));
		kbobj15.setStartPosition(getUsedColumnNames(134, generateReportService, territoryName,edition));
		/*
		 * kbobj5.setFileName("AK_Sitka_06_Main"); kbobj5.setStartPosition("");
		 */
		kbobj15.setEndPosition("");
		kbobj15.setPullForward("");
		kbobj15.setSourceCount("");
		kbobj15.setLanCount("");
		// kbobj15.setUploadInst("");
		kbobj15.setUploadInst(getUsedRUL(134, generateReportService, territoryName, edition));
		kbobj15.setAdditionalReq("");
		list3.add(kbobj15);
		ExcelFileDto kbobj16 = new ExcelFileDto();
		kbobj16.setLanFieldNumber("106");
		kbobj16.setLanFieldName("106-ELEVATOR");
		kbobj16.setLength("1");
		kbobj16.setTo("1392");
		kbobj16.setFrom("1392");
		kbobj16.setFileName(getUsedTableNames(106, generateReportService, territoryName, edition));
		kbobj16.setStartPosition(getUsedColumnNames(106, generateReportService, territoryName, edition));
		/*
		 * kbobj5.setFileName("AK_Sitka_06_Main"); kbobj5.setStartPosition("");
		 */
		kbobj16.setEndPosition("");
		kbobj16.setPullForward("");
		kbobj16.setSourceCount("");
		kbobj16.setLanCount("");
		// kbobj16.setUploadInst("");
		kbobj16.setUploadInst(getUsedRUL(106, generateReportService, territoryName, edition));
		kbobj16.setAdditionalReq("");
		list3.add(kbobj16);
		ExcelFileDto kbobj17 = new ExcelFileDto();
		kbobj17.setLanFieldNumber("154");
		kbobj17.setLanFieldName("154-INTERIOR WALLS");
		kbobj17.setLength("1");
		kbobj17.setTo("1629");
		kbobj17.setFrom("1629");
		kbobj17.setFileName(getUsedTableNames(154, generateReportService, territoryName, edition));
		kbobj17.setStartPosition(getUsedColumnNames(154, generateReportService, territoryName, edition));
		/*
		 * kbobj5.setFileName("AK_Sitka_06_Main"); kbobj5.setStartPosition("");
		 */
		kbobj17.setEndPosition("");
		kbobj17.setPullForward("");
		kbobj17.setSourceCount("");
		kbobj17.setLanCount("");
		kbobj17.setUploadInst(getUsedRUL(154, generateReportService, territoryName, edition));
		// kbobj17.setUploadInst("");
		kbobj17.setAdditionalReq("");
		list3.add(kbobj17);

		/////////////////////

		List<ExcelFileDto> list4 = new ArrayList<ExcelFileDto>();
		ExcelFileDto lobj1 = new ExcelFileDto();
		lobj1.setLanFieldNumber("162");
		lobj1.setLanFieldName("162-AMENITIES");
		lobj1.setLength("5");
		lobj1.setTo("1680");
		lobj1.setFrom("1684");
		lobj1.setFileName(getUsedTableNames(162, generateReportService, territoryName, edition));
		lobj1.setStartPosition(getUsedColumnNames(162, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj1.setEndPosition("");
		lobj1.setPullForward("");
		lobj1.setSourceCount("");
		lobj1.setLanCount("");
		// lobj1.setUploadInst("");
		lobj1.setUploadInst(getUsedRUL(162, generateReportService, territoryName, edition));
		lobj1.setAdditionalReq("");
		list4.add(lobj1);
		ExcelFileDto lobj2 = new ExcelFileDto();
		lobj2.setLanFieldNumber("170");
		lobj2.setLanFieldName("170-AMENITIES 2");
		lobj2.setLength("5");
		lobj2.setTo("1724");
		lobj2.setFrom("1728");
		lobj2.setFileName(getUsedTableNames(170, generateReportService, territoryName, edition));
		lobj2.setStartPosition(getUsedColumnNames(170, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj2.setEndPosition("");
		lobj2.setPullForward("");
		lobj2.setSourceCount("");
		lobj2.setLanCount("");
		// lobj2.setUploadInst("");
		lobj2.setUploadInst(getUsedRUL(170, generateReportService, territoryName, edition));
		lobj2.setAdditionalReq("");
		list4.add(lobj2);
		ExcelFileDto lobj3 = new ExcelFileDto();
		lobj3.setLanFieldNumber("177");
		lobj3.setLanFieldName("177-EXTRA FEATURES 1 AREA");
		lobj3.setLength("9");
		lobj3.setTo("1779");
		lobj3.setFrom("1787");
		lobj3.setFileName(getUsedTableNames(177, generateReportService, territoryName,edition));
		lobj3.setStartPosition(getUsedColumnNames(177, generateReportService, territoryName,edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj3.setEndPosition("");
		lobj3.setPullForward("");
		lobj3.setSourceCount("");
		lobj3.setLanCount("");
		// lobj3.setUploadInst("");
		lobj3.setUploadInst(getUsedRUL(177, generateReportService, territoryName, edition));
		lobj3.setAdditionalReq("");
		list4.add(lobj3);
		ExcelFileDto lobj4 = new ExcelFileDto();
		lobj4.setLanFieldNumber("178");
		lobj4.setLanFieldName("178-EXTRA FEATURES 1 INDICATOR");
		lobj4.setLength("2");
		lobj4.setTo("1788");
		lobj4.setFrom("1789");
		lobj4.setFileName(getUsedTableNames(178, generateReportService, territoryName, edition));
		lobj4.setStartPosition(getUsedColumnNames(178, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj4.setEndPosition("");
		lobj4.setPullForward("");
		lobj4.setSourceCount("");
		lobj4.setLanCount("");
		// lobj4.setUploadInst("");
		lobj4.setUploadInst(getUsedRUL(178, generateReportService, territoryName, edition));
		lobj4.setAdditionalReq("");
		list4.add(lobj4);
		ExcelFileDto lobj5 = new ExcelFileDto();
		lobj5.setLanFieldNumber("181");
		lobj5.setLanFieldName("181-EXTRA FEATURES 2 AREA");
		lobj5.setLength("9");
		lobj5.setTo("1792");
		lobj5.setFrom("1800");
		lobj5.setFileName(getUsedTableNames(181, generateReportService, territoryName, edition));
		lobj5.setStartPosition(getUsedColumnNames(181, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj5.setEndPosition("");
		lobj5.setPullForward("");
		lobj5.setSourceCount("");
		lobj5.setLanCount("");
		// lobj5.setUploadInst("");
		lobj5.setUploadInst(getUsedRUL(181, generateReportService, territoryName, edition));
		lobj5.setAdditionalReq("");
		list4.add(lobj5);
		ExcelFileDto lobj6 = new ExcelFileDto();
		lobj6.setLanFieldNumber("182");
		lobj6.setLanFieldName("182-EXTRA FEATURES 2 INDICATOR");
		lobj6.setLength("2");
		lobj6.setTo("1801");
		lobj6.setFrom("1802");
		lobj6.setFileName(getUsedTableNames(182, generateReportService, territoryName, edition));
		lobj6.setStartPosition(getUsedColumnNames(182, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj6.setEndPosition("");
		lobj6.setPullForward("");
		lobj6.setSourceCount("");
		lobj6.setLanCount("");
		// lobj6.setUploadInst("");
		lobj6.setUploadInst(getUsedRUL(182, generateReportService, territoryName, edition));
		lobj6.setAdditionalReq("");
		list4.add(lobj6);
		ExcelFileDto lobj7 = new ExcelFileDto();
		lobj7.setLanFieldNumber("183");
		lobj7.setLanFieldName("183-EXTRA FEATURES 3 AREA");
		lobj7.setLength("9");
		lobj7.setTo("1803");
		lobj7.setFrom("1811");
		lobj7.setFileName(getUsedTableNames(183, generateReportService, territoryName,edition));
		lobj7.setStartPosition(getUsedColumnNames(183, generateReportService, territoryName,edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj7.setEndPosition("");
		lobj7.setPullForward("");
		lobj7.setSourceCount("");
		lobj7.setLanCount("");
		// lobj7.setUploadInst("");
		lobj7.setUploadInst(getUsedRUL(183, generateReportService, territoryName, edition));
		lobj7.setAdditionalReq("");
		list4.add(lobj7);
		ExcelFileDto lobj8 = new ExcelFileDto();
		lobj8.setLanFieldNumber("184");
		lobj8.setLanFieldName("184-EXTRA FEATURES 3 INDICATOR");
		lobj8.setLength("2");
		lobj8.setTo("1812");
		lobj8.setFrom("1813");
		lobj8.setFileName(getUsedTableNames(184, generateReportService, territoryName, edition));
		lobj8.setStartPosition(getUsedColumnNames(184, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj8.setEndPosition("");
		lobj8.setPullForward("");
		lobj8.setSourceCount("");
		lobj8.setLanCount("");
		// lobj8.setUploadInst("");
		lobj8.setUploadInst(getUsedRUL(184, generateReportService, territoryName, edition));
		lobj8.setAdditionalReq("");
		list4.add(lobj8);
		ExcelFileDto lobj9 = new ExcelFileDto();
		lobj9.setLanFieldNumber("185");
		lobj9.setLanFieldName("185-EXTRA FEATURES 4 AREA");
		lobj9.setLength("9");
		lobj9.setTo("1814");
		lobj9.setFrom("1822");
		lobj9.setFileName(getUsedTableNames(185, generateReportService, territoryName, edition));
		lobj9.setStartPosition(getUsedColumnNames(185, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj9.setEndPosition("");
		lobj9.setPullForward("");
		lobj9.setSourceCount("");
		lobj9.setLanCount("");
		// lobj9.setUploadInst("");
		lobj9.setUploadInst(getUsedRUL(185, generateReportService, territoryName, edition));
		lobj9.setAdditionalReq("");
		list4.add(lobj9);
		ExcelFileDto lobj10 = new ExcelFileDto();
		lobj10.setLanFieldNumber("186");
		lobj10.setLanFieldName("186-EXTRA FEATURES 4 INDICATOR");
		lobj10.setLength("2");
		lobj10.setTo("1823");
		lobj10.setFrom("1824");
		lobj10.setFileName(getUsedTableNames(186, generateReportService, territoryName, edition));
		lobj10.setStartPosition(getUsedColumnNames(186, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj10.setEndPosition("");
		lobj10.setPullForward("");
		lobj10.setSourceCount("");
		lobj10.setLanCount("");
		// lobj10.setUploadInst("");
		lobj10.setUploadInst(getUsedRUL(186, generateReportService, territoryName, edition));
		lobj10.setAdditionalReq("");
		list4.add(lobj10);
		ExcelFileDto lobj11 = new ExcelFileDto();
		lobj11.setLanFieldNumber("167");
		lobj11.setLanFieldName("167-NEIGHBORHOOD CODE");
		lobj11.setLength("9");
		lobj11.setTo("1693");
		lobj11.setFrom("1701");
		lobj11.setFileName(getUsedTableNames(167, generateReportService, territoryName, edition));
		lobj11.setStartPosition(getUsedColumnNames(167, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj11.setEndPosition("");
		lobj11.setPullForward("");
		lobj11.setSourceCount("");
		lobj11.setLanCount("");
		// lobj11.setUploadInst("");
		lobj11.setUploadInst(getUsedRUL(167, generateReportService, territoryName, edition));
		lobj11.setAdditionalReq("");
		list4.add(lobj11);
		ExcelFileDto lobj12 = new ExcelFileDto();
		lobj12.setLanFieldNumber("75");
		lobj12.setLanFieldName("75-ZONING");
		lobj12.setLength("25");
		lobj12.setTo("1200");
		lobj12.setFrom("1224");
		lobj12.setFileName(getUsedTableNames(75, generateReportService, territoryName, edition));
		lobj12.setStartPosition(getUsedColumnNames(75, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj12.setEndPosition("");
		lobj12.setPullForward("");
		lobj12.setSourceCount("");
		lobj12.setLanCount("");
		// lobj12.setUploadInst("");
		lobj12.setUploadInst(getUsedRUL(75, generateReportService, territoryName, edition));
		lobj12.setAdditionalReq("");
		list4.add(lobj12);
		ExcelFileDto lobj13 = new ExcelFileDto();
		lobj13.setLanFieldNumber("161");
		lobj13.setLanFieldName("161-SITE INFLUENCE");
		lobj13.setLength("2");
		lobj13.setTo("1678");
		lobj13.setFrom("1679");
		lobj13.setFileName(getUsedTableNames(161, generateReportService, territoryName, edition));
		lobj13.setStartPosition(getUsedColumnNames(161, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj13.setEndPosition("");
		lobj13.setPullForward("");
		lobj13.setSourceCount("");
		lobj13.setLanCount("");
		// lobj13.setUploadInst("");
		lobj13.setUploadInst(getUsedRUL(161, generateReportService, territoryName, edition));
		lobj13.setAdditionalReq("");
		list4.add(lobj13);
		ExcelFileDto lobj14 = new ExcelFileDto();
		lobj14.setLanFieldNumber("179");
		lobj14.setLanFieldName("179-TOPOGRAPHY");
		lobj14.setLength("1");
		lobj14.setTo("1790");
		lobj14.setFrom("1790");
		lobj14.setFileName(getUsedTableNames(179, generateReportService, territoryName, edition));
		lobj14.setStartPosition(getUsedColumnNames(179, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj14.setEndPosition("");
		lobj14.setPullForward("");
		lobj14.setSourceCount("");
		lobj14.setLanCount("");
		// lobj14.setUploadInst("");
		lobj14.setUploadInst(getUsedRUL(179, generateReportService, territoryName, edition));
		lobj14.setAdditionalReq("");
		list4.add(lobj14);
		ExcelFileDto lobj15 = new ExcelFileDto();
		lobj15.setLanFieldNumber("192");
		lobj15.setLanFieldName("192-WATER");
		lobj15.setLength("1");
		lobj15.setTo("1797");
		lobj15.setFrom("1797");
		lobj15.setFileName(getUsedTableNames(192, generateReportService, territoryName, edition));
		lobj15.setStartPosition(getUsedColumnNames(192, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj15.setEndPosition("");
		lobj15.setPullForward("");
		lobj15.setSourceCount("");
		lobj15.setLanCount("");
		// lobj15.setUploadInst("");
		lobj15.setUploadInst(getUsedRUL(192, generateReportService, territoryName, edition));
		lobj15.setAdditionalReq("");
		list4.add(lobj15);
		ExcelFileDto lobj16 = new ExcelFileDto();
		lobj16.setLanFieldNumber("193");
		lobj16.setLanFieldName("193-SEWER");
		lobj16.setLength("1");
		lobj16.setTo("1998");
		lobj16.setFrom("1998");
		lobj16.setFileName(getUsedTableNames(193, generateReportService, territoryName, edition));
		lobj16.setStartPosition(getUsedColumnNames(193, generateReportService, territoryName, edition));
		/*
		 * bobj1.setFileName(""); bobj1.setStartPosition("");
		 */
		lobj16.setEndPosition("");
		lobj16.setPullForward("");
		lobj16.setSourceCount("");
		lobj16.setLanCount("");
		// lobj16.setUploadInst("");
		lobj16.setUploadInst(getUsedRUL(193, generateReportService, territoryName, edition));
		lobj16.setAdditionalReq("");
		list4.add(lobj16);
		///////// My Code

		//////////////////////

		Map<String, List<ExcelFileDto>> subMap3 = new LinkedHashMap<String, List<ExcelFileDto>>();
		List<ExcelFileDto> excelFileDtoList2 = new ArrayList<ExcelFileDto>();
		subMap3.put("Class Comment:", excelFileDtoList2);

		ExcelFileDto cobj1 = new ExcelFileDto();
		cobj1.setLanFieldNumber("4");
		cobj1.setLanFieldName("4-DUPLICATE APN/MULTI ADDRESS ID");
		cobj1.setLength("2");
		cobj1.setTo("73");
		cobj1.setFrom("74");
		cobj1.setFileName(getUsedTableNames(4, generateReportService, territoryName,edition));
		cobj1.setStartPosition(getUsedColumnNames(4, generateReportService, territoryName,edition));
		/*
		 * cobj1.setFileName(""); cobj1.setStartPosition("");
		 */
		cobj1.setEndPosition("");
		cobj1.setPullForward("");
		cobj1.setSourceCount("");
		cobj1.setLanCount("");
		// cobj1.setUploadInst("");
		cobj1.setUploadInst(getUsedRUL(4, generateReportService, territoryName, edition));
		cobj1.setAdditionalReq("");
		excelFileDtoList2.add(cobj1);
		ExcelFileDto cobj2 = new ExcelFileDto();
		cobj2.setLanFieldNumber("5");
		cobj2.setLanFieldName("5-PROP ADDR SRCE FLAG)");
		cobj2.setLength("1");
		cobj2.setTo("75");
		cobj2.setFrom("75");
		cobj2.setFileName(getUsedTableNames(5, generateReportService, territoryName,edition));
		cobj2.setStartPosition(getUsedColumnNames(5, generateReportService, territoryName,edition));
		/*
		 * cobj2.setFileName(""); cobj2.setStartPosition("");
		 */
		cobj2.setEndPosition("");
		cobj2.setPullForward("");
		cobj2.setSourceCount("");
		cobj2.setLanCount("");
		// cobj2.setUploadInst("Please Refer the attached Document");
		cobj2.setUploadInst(getUsedRUL(5, generateReportService, territoryName, edition));
		cobj2.setAdditionalReq("");
		excelFileDtoList2.add(cobj2);
		ExcelFileDto cobj_6 = new ExcelFileDto();
		cobj_6.setLanFieldNumber("6");
		cobj_6.setLanFieldName("6-PROP HOUSE NUMBER");
		cobj_6.setLength("7");
		cobj_6.setTo("76");
		cobj_6.setFrom("82");
		cobj_6.setFileName(getUsedTableNames(6, generateReportService, territoryName, edition));
		cobj_6.setStartPosition(getUsedColumnNames(6, generateReportService, territoryName, edition));
		/*
		 * cobj2.setFileName(""); cobj2.setStartPosition("");
		 */
		cobj_6.setEndPosition("");
		cobj_6.setPullForward("");
		cobj_6.setSourceCount("");
		cobj_6.setLanCount("");
		// cobj_6.setUploadInst("Please Refer the attached Document");
		cobj_6.setUploadInst(getUsedRUL(6, generateReportService, territoryName, edition));
		cobj_6.setAdditionalReq("");
		excelFileDtoList2.add(cobj_6);
		ExcelFileDto cobj3 = new ExcelFileDto();
		cobj3.setLanFieldNumber("7");
		cobj3.setLanFieldName("7-PROP HOUSE ALPHA");
		cobj3.setLength("6");
		cobj3.setTo("83");
		cobj3.setFrom("88");
		cobj3.setFileName(getUsedTableNames(7, generateReportService, territoryName, edition));
		cobj3.setStartPosition(getUsedColumnNames(7, generateReportService, territoryName, edition));
		/*
		 * cobj3.setFileName(""); cobj3.setStartPosition("");
		 */
		cobj3.setEndPosition("");
		cobj3.setPullForward("");
		cobj3.setSourceCount("");
		cobj3.setLanCount("");
		// cobj3.setUploadInst("");
		cobj3.setUploadInst(getUsedRUL(7, generateReportService, territoryName, edition));
		cobj3.setAdditionalReq("");
		excelFileDtoList2.add(cobj3);
		ExcelFileDto cobj4 = new ExcelFileDto();
		cobj4.setLanFieldNumber("8");
		cobj4.setLanFieldName("8-PROP STREET DIRECTION LEFT");
		cobj4.setLength("2");
		cobj4.setTo("89");
		cobj4.setFrom("90");
		cobj4.setFileName(getUsedTableNames(8, generateReportService, territoryName,edition));
		cobj4.setStartPosition(getUsedColumnNames(8, generateReportService, territoryName,edition));
		/*
		 * cobj4.setFileName(""); cobj4.setStartPosition("");
		 */
		cobj4.setEndPosition("");
		cobj4.setPullForward("");
		cobj4.setSourceCount("");
		cobj4.setLanCount("");
		// cobj4.setUploadInst("");
		cobj4.setUploadInst(getUsedRUL(8, generateReportService, territoryName, edition));
		cobj4.setAdditionalReq("");
		excelFileDtoList2.add(cobj4);
		ExcelFileDto cobj5 = new ExcelFileDto();
		cobj5.setLanFieldNumber("9");
		cobj5.setLanFieldName("9-PROP STREET NAME");
		cobj5.setLength("30");
		cobj5.setTo("91");
		cobj5.setFrom("120");
		cobj5.setFileName(getUsedTableNames(9, generateReportService, territoryName, edition));
		cobj5.setStartPosition(getUsedColumnNames(9, generateReportService, territoryName, edition));
		/*
		 * cobj5.setFileName("AK_Sitka_06_Main"); cobj5.setStartPosition(
		 * "[Street Name]");
		 */
		cobj5.setEndPosition("");
		cobj5.setPullForward("");
		cobj5.setSourceCount("");
		cobj5.setLanCount("");
		// cobj5.setUploadInst("Please Refer the attached Document");
		cobj5.setUploadInst(getUsedRUL(9, generateReportService, territoryName, edition));
		cobj5.setAdditionalReq("");
		excelFileDtoList2.add(cobj5);
		ExcelFileDto cobj6 = new ExcelFileDto();
		cobj6.setLanFieldNumber("10");
		cobj6.setLanFieldName("10-PROP STREET SUFFIX");
		cobj6.setLength("4");
		cobj6.setTo("121");
		cobj6.setFrom("124");
		cobj6.setFileName(getUsedTableNames(10, generateReportService, territoryName, edition));
		cobj6.setStartPosition(getUsedColumnNames(10, generateReportService, territoryName, edition));
		/*
		 * cobj6.setFileName("AK_Sitka_06_Main"); cobj6.setStartPosition(
		 * "[Addr Mod]");
		 */
		cobj6.setEndPosition("");
		cobj6.setPullForward("");
		cobj6.setSourceCount("");
		cobj6.setLanCount("");
		// cobj6.setUploadInst("Please Refer the attached Document");
		cobj6.setUploadInst(getUsedRUL(10, generateReportService, territoryName, edition));
		cobj6.setAdditionalReq("");
		excelFileDtoList2.add(cobj6);
		ExcelFileDto cobj7 = new ExcelFileDto();
		cobj7.setLanFieldNumber("11");
		cobj7.setLanFieldName("11-PROP STREET DIRECTION RIGHT");
		cobj7.setLength("2");
		cobj7.setTo("125");
		cobj7.setFrom("126");
		cobj7.setFileName(getUsedTableNames(11, generateReportService, territoryName,edition));
		cobj7.setStartPosition(getUsedColumnNames(11, generateReportService, territoryName,edition));
		/*
		 * cobj7.setFileName(""); cobj7.setStartPosition("");
		 */
		cobj7.setEndPosition("");
		cobj7.setPullForward("");
		cobj7.setSourceCount("");
		cobj7.setLanCount("");
		// cobj7.setUploadInst("");
		cobj7.setUploadInst(getUsedRUL(11, generateReportService, territoryName, edition));
		cobj7.setAdditionalReq("");
		excelFileDtoList2.add(cobj7);
		ExcelFileDto cobj8 = new ExcelFileDto();
		cobj8.setLanFieldNumber("12");
		cobj8.setLanFieldName("12-PROP UNIT NUMBER");
		cobj8.setLength("6");
		cobj8.setTo("127");
		cobj8.setFrom("132");
		cobj8.setFileName(getUsedTableNames(12, generateReportService, territoryName, edition));
		cobj8.setStartPosition(getUsedColumnNames(12, generateReportService, territoryName, edition));
		/*
		 * cobj8.setFileName("AK_Sitka_08_Main"); cobj8.setStartPosition(
		 * "[Addr Mod]");
		 */
		cobj8.setEndPosition("");
		cobj8.setPullForward("");
		cobj8.setSourceCount("");
		cobj8.setLanCount("");
		// cobj8.setUploadInst("Please Refer the attached Document");
		cobj8.setUploadInst(getUsedRUL(12, generateReportService, territoryName, edition));
		cobj8.setAdditionalReq("");
		excelFileDtoList2.add(cobj8);
		ExcelFileDto cobj9 = new ExcelFileDto();
		cobj9.setLanFieldNumber("13");
		cobj9.setLanFieldName("13-PROP FULL STREET ADDRESS");
		cobj9.setLength("60");
		cobj9.setTo("133");
		cobj9.setFrom("192");
		cobj9.setFileName(getUsedTableNames(13, generateReportService, territoryName, edition));
		cobj9.setStartPosition(getUsedColumnNames(13, generateReportService, territoryName, edition));
		/*
		 * cobj9.setFileName("AK_Sitka_09_Main"); cobj9.setStartPosition(
		 * "St No\n[Street Name]\n[Addr Mod]");
		 */
		cobj9.setEndPosition("");
		cobj9.setPullForward("");
		cobj9.setSourceCount("");
		cobj9.setLanCount("");
		/*
		 * cobj9.setUploadInst(
		 * "Concatenate Fields trough 6 to 11 with Space and Concatenate F12 with #. Replace double spaces and Trim leading and trailing spaces "
		 * );
		 */
		cobj9.setUploadInst(getUsedRUL(13, generateReportService, territoryName, edition));
		cobj9.setAdditionalReq("");
		excelFileDtoList2.add(cobj9);
		ExcelFileDto cobj10 = new ExcelFileDto();
		cobj10.setLanFieldNumber("14");
		cobj10.setLanFieldName("14-PROP CITY NAME");
		cobj10.setLength("30");
		cobj10.setTo("193");
		cobj10.setFrom("222");
		cobj10.setFileName(getUsedTableNames(14, generateReportService, territoryName, edition));
		cobj10.setStartPosition(getUsedColumnNames(14, generateReportService, territoryName, edition));
		/*
		 * cobj10.setFileName(""); cobj10.setStartPosition("");
		 */
		cobj10.setEndPosition("");
		cobj10.setPullForward("");
		cobj10.setSourceCount("");
		cobj10.setLanCount("");
		// cobj10.setUploadInst("");
		cobj10.setUploadInst(getUsedRUL(14, generateReportService, territoryName, edition));
		cobj10.setAdditionalReq("");
		excelFileDtoList2.add(cobj10);
		ExcelFileDto cobj11 = new ExcelFileDto();
		cobj11.setLanFieldNumber("15");
		cobj11.setLanFieldName("15-PROP STATE");
		cobj11.setLength("2");
		cobj11.setTo("223");
		cobj11.setFrom("224");
		cobj11.setFileName(getUsedTableNames(15, generateReportService, territoryName, edition));
		cobj11.setStartPosition(getUsedColumnNames(15, generateReportService, territoryName, edition));
		/*
		 * cobj11.setFileName(""); cobj11.setStartPosition("");
		 */
		cobj11.setEndPosition("");
		cobj11.setPullForward("");
		cobj11.setSourceCount("");
		cobj11.setLanCount("");
		// cobj11.setUploadInst("");
		cobj11.setUploadInst(getUsedRUL(15, generateReportService, territoryName, edition));
		cobj11.setAdditionalReq("");
		excelFileDtoList2.add(cobj11);
		ExcelFileDto cobj12 = new ExcelFileDto();
		cobj12.setLanFieldNumber("16");
		cobj12.setLanFieldName("16-PROP ZIP CODE");
		cobj12.setLength("5");
		cobj12.setTo("225");
		cobj12.setFrom("229");
		cobj12.setFileName(getUsedTableNames(16, generateReportService, territoryName,edition));
		cobj12.setStartPosition(getUsedColumnNames(16, generateReportService, territoryName,edition));
		/*
		 * cobj12.setFileName(""); cobj12.setStartPosition("");
		 */
		cobj12.setEndPosition("");
		cobj12.setPullForward("");
		cobj12.setSourceCount("");
		cobj12.setLanCount("");
		// cobj12.setUploadInst("");
		cobj12.setUploadInst(getUsedRUL(16, generateReportService, territoryName, edition));
		cobj12.setAdditionalReq("");
		excelFileDtoList2.add(cobj12);

		ExcelFileDto cobj13 = new ExcelFileDto();
		cobj13.setLanFieldNumber("17");
		cobj13.setLanFieldName("17-PROP ZIP+4");
		cobj13.setLength("4");
		cobj13.setTo("230");
		cobj13.setFrom("234");
		cobj13.setFileName(getUsedTableNames(15, generateReportService, territoryName,edition));
		cobj13.setStartPosition(getUsedColumnNames(15, generateReportService, territoryName,edition));
		/*
		 * cobj13.setFileName(""); cobj13.setStartPosition("");
		 */
		cobj13.setEndPosition("");
		cobj13.setPullForward("");
		cobj13.setSourceCount("");
		cobj13.setLanCount("");
		// cobj13.setUploadInst("");
		cobj13.setUploadInst(getUsedRUL(15, generateReportService, territoryName, edition));
		cobj13.setAdditionalReq("");
		excelFileDtoList2.add(cobj13);

		ExcelFileDto cobj16 = new ExcelFileDto();
		cobj16.setLanFieldNumber("23");
		cobj16.setLanFieldName("23-ASSE MAIL: HOUSE NUMBER");
		cobj16.setLength("7");
		cobj16.setTo("466");
		cobj16.setFrom("472");
		cobj16.setFileName(getUsedTableNames(23, generateReportService, territoryName, edition));
		cobj16.setStartPosition(getUsedColumnNames(23, generateReportService, territoryName, edition));
		/*
		 * cobj16.setFileName(""); cobj16.setStartPosition("");
		 */
		cobj16.setEndPosition("");
		cobj16.setPullForward("");
		cobj16.setSourceCount("");
		cobj16.setLanCount("");
		// cobj16.setUploadInst("");
		cobj16.setUploadInst(getUsedRUL(23, generateReportService, territoryName, edition));
		cobj16.setAdditionalReq("");
		excelFileDtoList2.add(cobj16);
		ExcelFileDto cobj17 = new ExcelFileDto();
		cobj17.setLanFieldNumber("24");
		cobj17.setLanFieldName("24-ASSE MAIL: HOUSE ALPHA");
		cobj17.setLength("6");
		cobj17.setTo("473");
		cobj17.setFrom("478");
		cobj17.setFileName(getUsedTableNames(24, generateReportService, territoryName, edition));
		cobj17.setStartPosition(getUsedColumnNames(24, generateReportService, territoryName, edition));
		/*
		 * cobj17.setFileName(""); cobj17.setStartPosition("");
		 */
		cobj17.setEndPosition("");
		cobj17.setPullForward("");
		cobj17.setSourceCount("");
		cobj17.setLanCount("");
		// cobj17.setUploadInst("");
		cobj17.setUploadInst(getUsedRUL(24, generateReportService, territoryName, edition));
		cobj17.setAdditionalReq("");
		excelFileDtoList2.add(cobj17);
		ExcelFileDto cobj18 = new ExcelFileDto();
		cobj18.setLanFieldNumber("25");
		cobj18.setLanFieldName("25-ASSE MAIL: STREET DIRECTION-LEFT");
		cobj18.setLength("2");
		cobj18.setTo("479");
		cobj18.setFrom("480");
		cobj18.setFileName(getUsedTableNames(25, generateReportService, territoryName,edition));
		cobj18.setStartPosition(getUsedColumnNames(25, generateReportService, territoryName,edition));
		/*
		 * cobj18.setFileName(""); cobj18.setStartPosition("");
		 */
		cobj18.setEndPosition("");
		cobj18.setPullForward("");
		cobj18.setSourceCount("");
		cobj18.setLanCount("");
		// cobj18.setUploadInst("");
		cobj18.setUploadInst(getUsedRUL(25, generateReportService, territoryName, edition));
		cobj18.setAdditionalReq("");
		excelFileDtoList2.add(cobj18);
		ExcelFileDto cobj19 = new ExcelFileDto();
		cobj19.setLanFieldNumber("26");
		cobj19.setLanFieldName("26-ASSE MAIL: STREET NAME");
		cobj19.setLength("30");
		cobj19.setTo("481");
		cobj19.setFrom("510");
		cobj19.setFileName(getUsedTableNames(26, generateReportService, territoryName,edition));
		cobj19.setStartPosition(getUsedColumnNames(26, generateReportService, territoryName,edition));
		/*
		 * cobj19.setFileName(""); cobj19.setStartPosition("");
		 */
		cobj19.setEndPosition("");
		cobj19.setPullForward("");
		cobj19.setSourceCount("");
		cobj19.setLanCount("");
		// cobj19.setUploadInst("");
		cobj19.setUploadInst(getUsedRUL(26, generateReportService, territoryName, edition));
		cobj19.setAdditionalReq("");
		excelFileDtoList2.add(cobj19);
		ExcelFileDto cobj20 = new ExcelFileDto();
		cobj20.setLanFieldNumber("27");
		cobj20.setLanFieldName("27-ASSE MAIL: STREET SUFFIX");
		cobj20.setLength("4");
		cobj20.setTo("511");
		cobj20.setFrom("514");
		cobj20.setFileName(getUsedTableNames(27, generateReportService, territoryName,edition));
		cobj20.setStartPosition(getUsedColumnNames(27, generateReportService, territoryName,edition));
		/*
		 * cobj20.setFileName(""); cobj20.setStartPosition("");
		 */
		cobj20.setEndPosition("");
		cobj20.setPullForward("");
		cobj20.setSourceCount("");
		cobj20.setLanCount("");
		// cobj20.setUploadInst("");
		cobj20.setUploadInst(getUsedRUL(27, generateReportService, territoryName, edition));
		cobj20.setAdditionalReq("");
		excelFileDtoList2.add(cobj20);
		ExcelFileDto cobj21 = new ExcelFileDto();
		cobj21.setLanFieldNumber("28");
		cobj21.setLanFieldName("28-ASSE MAIL: STREET DIRECTION-RIGHT");
		cobj21.setLength("2");
		cobj21.setTo("515");
		cobj21.setFrom("516");
		cobj21.setFileName(getUsedTableNames(28, generateReportService, territoryName,edition));
		cobj21.setStartPosition(getUsedColumnNames(28, generateReportService, territoryName,edition));
		/*
		 * cobj21.setFileName(""); cobj21.setStartPosition("");
		 */
		cobj21.setEndPosition("");
		cobj21.setPullForward("");
		cobj21.setSourceCount("");
		cobj21.setLanCount("");
		// cobj21.setUploadInst("");
		cobj21.setUploadInst(getUsedRUL(28, generateReportService, territoryName, edition));
		cobj21.setAdditionalReq("");
		excelFileDtoList2.add(cobj21);
		ExcelFileDto cobj22 = new ExcelFileDto();
		cobj22.setLanFieldNumber("29");
		cobj22.setLanFieldName("29-ASSE MAIL: UNIT NUMBER");
		cobj22.setLength("6");
		cobj22.setTo("517");
		cobj22.setFrom("522");
		cobj22.setFileName(getUsedTableNames(29, generateReportService, territoryName,edition));
		cobj22.setStartPosition(getUsedColumnNames(29, generateReportService, territoryName,edition));
		/*
		 * cobj22.setFileName(""); cobj22.setStartPosition("");
		 */
		cobj22.setEndPosition("");
		cobj22.setPullForward("");
		cobj22.setSourceCount("");
		cobj22.setLanCount("");
		// cobj22.setUploadInst("");
		cobj22.setUploadInst(getUsedRUL(29, generateReportService, territoryName, edition));
		cobj22.setAdditionalReq("");
		excelFileDtoList2.add(cobj22);
		ExcelFileDto cobj23 = new ExcelFileDto();
		cobj23.setLanFieldNumber("30");
		cobj23.setLanFieldName("30-ASSE MAIL: FULL STREET ADDRESS");
		cobj23.setLength("80");
		cobj23.setTo("523");
		cobj23.setFrom("602");
		cobj23.setFileName(getUsedTableNames(30, generateReportService, territoryName, edition));
		cobj23.setStartPosition(getUsedColumnNames(30, generateReportService, territoryName, edition));
		/*
		 * cobj23.setFileName("AK_Sitka_06_Main");
		 * cobj23.setStartPosition("Address_Mail\nCity_Mail\nState\nZip_Mail");
		 */
		cobj23.setEndPosition("");
		cobj23.setPullForward("");
		cobj23.setSourceCount("");
		cobj23.setLanCount("");
		// cobj23.setUploadInst("Please find the additional document");
		cobj23.setUploadInst(getUsedRUL(30, generateReportService, territoryName, edition));
		cobj23.setAdditionalReq("");
		excelFileDtoList2.add(cobj23);
		ExcelFileDto cobj24 = new ExcelFileDto();
		cobj24.setLanFieldNumber("31");
		cobj24.setLanFieldName("31-ASSE MAIL: CITY NAME");
		cobj24.setLength("30");
		cobj24.setTo("603");
		cobj24.setFrom("632");
		cobj24.setFileName(getUsedTableNames(31, generateReportService, territoryName,edition));
		cobj24.setStartPosition(getUsedColumnNames(31, generateReportService, territoryName,edition));
		/*
		 * cobj24.setFileName("AK_Sitka_06_Main");
		 * cobj24.setStartPosition("City_Mail");
		 */
		cobj24.setEndPosition("");
		cobj24.setPullForward("");
		cobj24.setSourceCount("");
		cobj24.setLanCount("");
		// cobj24.setUploadInst("Please find the additional document");
		cobj24.setUploadInst(getUsedRUL(31, generateReportService, territoryName, edition));
		cobj24.setAdditionalReq("");
		excelFileDtoList2.add(cobj24);
		ExcelFileDto cobj25 = new ExcelFileDto();
		cobj25.setLanFieldNumber("32");
		cobj25.setLanFieldName("32-ASSE MAIL: STATE");
		cobj25.setLength("2");
		cobj25.setTo("633");
		cobj25.setFrom("634");
		cobj25.setFileName(getUsedTableNames(32, generateReportService, territoryName,edition));
		cobj25.setStartPosition(getUsedColumnNames(32, generateReportService, territoryName,edition));
		/*
		 * cobj25.setFileName("AK_Sitka_06_Main");
		 * cobj25.setStartPosition("STATE");
		 */
		cobj25.setEndPosition("");
		cobj25.setPullForward("");
		cobj25.setSourceCount("");
		cobj25.setLanCount("");
		// cobj25.setUploadInst("Please find the additional document");
		cobj25.setUploadInst(getUsedRUL(32, generateReportService, territoryName, edition));
		cobj25.setAdditionalReq("");
		excelFileDtoList2.add(cobj25);
		ExcelFileDto cobj26 = new ExcelFileDto();
		cobj26.setLanFieldNumber("33");
		cobj26.setLanFieldName("33-ASSE MAIL: ZIP CODE");
		cobj26.setLength("5");
		cobj26.setTo("635");
		cobj26.setFrom("639");
		cobj26.setFileName(getUsedTableNames(33, generateReportService, territoryName,edition));
		cobj26.setStartPosition(getUsedColumnNames(33, generateReportService, territoryName,edition));
		/*
		 * cobj26.setFileName("AK_Sitka_06_Main");
		 * cobj26.setStartPosition("Zip_Mail");
		 */
		cobj26.setEndPosition("");
		cobj26.setPullForward("");
		cobj26.setSourceCount("");
		cobj26.setLanCount("");
		// cobj26.setUploadInst("Please find the additional document");
		cobj26.setUploadInst(getUsedRUL(33, generateReportService, territoryName, edition));
		cobj26.setAdditionalReq("");
		excelFileDtoList2.add(cobj26);
		ExcelFileDto cobj27 = new ExcelFileDto();
		cobj27.setLanFieldNumber("34");
		cobj27.setLanFieldName("34-ASSE MAIL: ZIP+4");
		cobj27.setLength("4");
		cobj27.setTo("640");
		cobj27.setFrom("643");
		cobj27.setFileName(getUsedTableNames(34, generateReportService, territoryName,edition));
		cobj27.setStartPosition(getUsedColumnNames(34, generateReportService, territoryName,edition));
		/*
		 * cobj27.setFileName("AK_Sitka_06_Main");
		 * cobj27.setStartPosition("Zip_Mail");
		 */
		cobj27.setEndPosition("");
		cobj27.setPullForward("");
		cobj27.setSourceCount("");
		cobj27.setLanCount("");
		// cobj27.setUploadInst("Please find the additional document");
		cobj27.setUploadInst(getUsedRUL(34, generateReportService, territoryName, edition));
		cobj27.setAdditionalReq("");
		excelFileDtoList2.add(cobj27);
		ExcelFileDto cobj28 = new ExcelFileDto();
		cobj28.setLanFieldNumber("89");
		cobj28.setLanFieldName("89-MAIL: CITY, ST & ZIP");
		cobj28.setLength("50");
		cobj28.setTo("1280");
		cobj28.setFrom("1329");
		cobj28.setFileName(getUsedTableNames(89, generateReportService, territoryName,edition));
		cobj28.setStartPosition(getUsedColumnNames(89, generateReportService, territoryName,edition));
		/*
		 * cobj28.setFileName("AK_Sitka_06_Main"); cobj28.setStartPosition(
		 * "MAIL CITY [31], MAIL STATE [32], MAIL ZIP [33] ");
		 */
		cobj28.setEndPosition("");
		cobj28.setPullForward("");
		cobj28.setSourceCount("");
		cobj28.setLanCount("");
		// cobj28.setUploadInst("Please find the additional document");
		cobj28.setUploadInst(getUsedRUL(89, generateReportService, territoryName, edition));
		cobj28.setAdditionalReq("");
		excelFileDtoList2.add(cobj28);

		//// End

		Map<String, List<ExcelFileDto>> subMap4 = new LinkedHashMap<String, List<ExcelFileDto>>();
		List<ExcelFileDto> excelFileDtoList3 = new ArrayList<ExcelFileDto>();
		subMap4.put("Class Comment:", excelFileDtoList3);

		ExcelFileDto dobj1 = new ExcelFileDto();
		dobj1.setLanFieldNumber("37");
		dobj1.setLanFieldName("37-ASSESSED LAND VALUE");
		dobj1.setLength("10");
		dobj1.setTo("655");
		dobj1.setFrom("664");
		dobj1.setFileName(getUsedTableNames(37, generateReportService, territoryName,edition));
		dobj1.setStartPosition(getUsedColumnNames(37, generateReportService, territoryName,edition));
		/*
		 * dobj1.setFileName("AK_Sitka_06_Main"); dobj1.setStartPosition(
		 * "[LAND $]");
		 */
		dobj1.setEndPosition("");
		dobj1.setPullForward("");
		dobj1.setSourceCount("");
		dobj1.setLanCount("");
		/*
		 * dobj1.setUploadInst(
		 * "Load the Column [LAND $]  by padding zeros to make it a length of 10. If it contains duplicate then Sum the values and load the resultant value by padding prefix zeros till it reaches length of ten"
		 * );
		 */
		dobj1.setUploadInst(getUsedRUL(37, generateReportService, territoryName, edition));
		dobj1.setAdditionalReq("");
		excelFileDtoList3.add(dobj1);

		ExcelFileDto dobj2 = new ExcelFileDto();
		dobj2.setLanFieldNumber("38");
		dobj2.setLanFieldName("38-ASSESSED IMPROVEMENT VALUE");
		dobj2.setLength("10");
		dobj2.setTo("665");
		dobj2.setFrom("674");
		dobj2.setFileName(getUsedTableNames(38, generateReportService, territoryName,edition));
		dobj2.setStartPosition(getUsedColumnNames(38, generateReportService, territoryName,edition));
		/*
		 * dobj2.setFileName("AK_Sitka_06_Main"); dobj2.setStartPosition(
		 * "[Imprv $]");
		 */
		dobj2.setEndPosition("");
		dobj2.setPullForward("");
		dobj2.setSourceCount("");
		dobj2.setLanCount("");
		/*
		 * dobj2.setUploadInst(
		 * "Load the Column [Imprv $] by padding zeros to make it a length of 10. If it contains duplicate then Sum the values and load the resultant value by padding prefix zeros till it reaches length of ten"
		 * );
		 */
		dobj2.setUploadInst(getUsedRUL(38, generateReportService, territoryName, edition));
		dobj2.setAdditionalReq("");
		excelFileDtoList3.add(dobj2);

		ExcelFileDto dobj3 = new ExcelFileDto();
		dobj3.setLanFieldNumber("39");
		dobj3.setLanFieldName("39-TOTAL ASSESSED VALUE");
		dobj3.setLength("11");
		dobj3.setTo("675");
		dobj3.setFrom("685");
		dobj3.setFileName(getUsedTableNames(39, generateReportService, territoryName,edition));
		dobj3.setStartPosition(getUsedColumnNames(39, generateReportService, territoryName,edition));
		/*
		 * dobj3.setFileName("AK_Sitka_06_Main"); dobj3.setStartPosition(
		 * "[Total $]");
		 */
		dobj3.setEndPosition("");
		dobj3.setPullForward("");
		dobj3.setSourceCount("");
		dobj3.setLanCount("");
		/*
		 * dobj3.setUploadInst(
		 * "Load the Column [Total $]  by padding zeros to make it a length of 11. If it contains duplicate then Sum the values and load the resultant value by padding prefix zeros till it reaches length of 11"
		 * );
		 */
		dobj3.setUploadInst(getUsedRUL(39, generateReportService, territoryName, edition));
		dobj3.setAdditionalReq("");
		excelFileDtoList3.add(dobj3);

		ExcelFileDto dobj4 = new ExcelFileDto();
		dobj4.setLanFieldNumber("94");
		dobj4.setLanFieldName("94-MARKET VALUE: LAND");
		dobj4.setLength("10");
		dobj4.setTo("1349");
		dobj4.setFrom("1358");
		dobj4.setFileName(getUsedTableNames(94, generateReportService, territoryName,edition));
		dobj4.setStartPosition(getUsedColumnNames(94, generateReportService, territoryName,edition));
		/*
		 * dobj4.setFileName(""); dobj4.setStartPosition("");
		 */
		dobj4.setEndPosition("");
		dobj4.setPullForward("");
		dobj4.setSourceCount("");
		dobj4.setLanCount("");
		// dobj4.setUploadInst("");
		dobj4.setUploadInst(getUsedRUL(94, generateReportService, territoryName, edition));
		dobj4.setAdditionalReq("");
		excelFileDtoList3.add(dobj4);

		ExcelFileDto dobj5 = new ExcelFileDto();
		dobj5.setLanFieldNumber("95");
		dobj5.setLanFieldName("95-MARKET VALUE: IMPROVEMENT");
		dobj5.setLength("10");
		dobj5.setTo("1359");
		dobj5.setFrom("1368");
		dobj5.setFileName(getUsedTableNames(95, generateReportService, territoryName,edition));
		dobj5.setStartPosition(getUsedColumnNames(95, generateReportService, territoryName,edition));
		/*
		 * dobj5.setFileName(""); dobj5.setStartPosition("");
		 */
		dobj5.setEndPosition("");
		dobj5.setPullForward("");
		dobj5.setSourceCount("");
		dobj5.setLanCount("");
		// dobj5.setUploadInst("");
		dobj5.setUploadInst(getUsedRUL(95, generateReportService, territoryName, edition));
		dobj5.setAdditionalReq("");
		excelFileDtoList3.add(dobj5);

		ExcelFileDto dobj6 = new ExcelFileDto();
		dobj6.setLanFieldNumber("96");
		dobj6.setLanFieldName("96-TOTAL MARKET VALUE");
		dobj6.setLength("11");
		dobj6.setTo("1369");
		dobj6.setFrom("1379");
		dobj6.setFileName(getUsedTableNames(96, generateReportService, territoryName,edition));
		dobj6.setStartPosition(getUsedColumnNames(96, generateReportService, territoryName,edition));
		/*
		 * dobj6.setFileName(""); dobj6.setStartPosition("");
		 */
		dobj6.setEndPosition("");
		dobj6.setPullForward("");
		dobj6.setSourceCount("");
		dobj6.setLanCount("");
		// dobj6.setUploadInst("");
		dobj6.setUploadInst(getUsedRUL(96, generateReportService, territoryName, edition));
		dobj6.setAdditionalReq("");
		excelFileDtoList3.add(dobj6);

		ExcelFileDto dobj7 = new ExcelFileDto();
		dobj7.setLanFieldNumber("97");
		dobj7.setLanFieldName("97-MARKET VALUE YEAR");
		dobj7.setLength("4");
		dobj7.setTo("1380");
		dobj7.setFrom("1383");
		dobj7.setFileName(getUsedTableNames(97, generateReportService, territoryName,edition));
		dobj7.setStartPosition(getUsedColumnNames(97, generateReportService, territoryName,edition));
		/*
		 * dobj7.setFileName(""); dobj7.setStartPosition("");
		 */
		dobj7.setEndPosition("");
		dobj7.setPullForward("");
		dobj7.setSourceCount("");
		dobj7.setLanCount("");
		// dobj7.setUploadInst("");
		dobj7.setUploadInst(getUsedRUL(97, generateReportService, territoryName, edition));
		dobj7.setAdditionalReq("");
		excelFileDtoList3.add(dobj7);

		Map<String, List<ExcelFileDto>> subMap5 = new LinkedHashMap<String, List<ExcelFileDto>>();
		List<ExcelFileDto> excelFileDtoList4 = new ArrayList<ExcelFileDto>();
		subMap5.put("Class Comment:", excelFileDtoList4);
		ExcelFileDto eobj1 = new ExcelFileDto();
		eobj1.setLanFieldNumber("71");
		eobj1.setLanFieldName("71-COUNTY LAND USE DESCRIPTION");
		eobj1.setLength("45");
		eobj1.setTo("1142");
		eobj1.setFrom("1186");
		eobj1.setFileName(getUsedTableNames(71, generateReportService, territoryName,edition));
		eobj1.setStartPosition(getUsedColumnNames(71, generateReportService, territoryName,edition));
		/*
		 * eobj1.setFileName("AK_Sitka_06_Main"); eobj1.setStartPosition(
		 * "[Legal Description]");
		 */
		eobj1.setEndPosition("");
		eobj1.setPullForward("");
		eobj1.setSourceCount("");
		eobj1.setLanCount("");
		// eobj1.setUploadInst("");
		eobj1.setUploadInst(getUsedRUL(71, generateReportService, territoryName, edition));
		eobj1.setAdditionalReq("");
		excelFileDtoList4.add(eobj1);
		ExcelFileDto eobj2 = new ExcelFileDto();
		eobj2.setLanFieldNumber("72");
		eobj2.setLanFieldName("72-COUNTY LAND USE CODE");
		eobj2.setLength("8");
		eobj2.setTo("1187");
		eobj2.setFrom("1194");
		eobj2.setFileName(getUsedTableNames(72, generateReportService, territoryName,edition));
		eobj2.setStartPosition(getUsedColumnNames(72, generateReportService, territoryName,edition));
		/*
		 * eobj2.setFileName("AK_Sitka_06_Main"); eobj2.setStartPosition(
		 * "[Legal Description]");
		 */
		eobj2.setEndPosition("");
		eobj2.setPullForward("");
		eobj2.setSourceCount("");
		eobj2.setLanCount("");
		// eobj2.setUploadInst("");
		eobj2.setUploadInst(getUsedRUL(72, generateReportService, territoryName, edition));
		eobj2.setAdditionalReq("");
		excelFileDtoList4.add(eobj2);
		ExcelFileDto eobj3 = new ExcelFileDto();
		eobj3.setLanFieldNumber("73");
		eobj3.setLanFieldName("73-STANDARDIZED LAND USE CODE");
		eobj3.setLength("4");
		eobj3.setTo("1195");
		eobj3.setFrom("1198");
		eobj3.setFileName(getUsedTableNames(73, generateReportService, territoryName,edition));
		eobj3.setStartPosition(getUsedColumnNames(73, generateReportService, territoryName,edition));
		/*
		 * eobj3.setFileName("AK_Sitka_06_Main"); eobj3.setStartPosition(
		 * "[Legal Description]");
		 */
		eobj3.setEndPosition("");
		eobj3.setPullForward("");
		eobj3.setSourceCount("");
		eobj3.setLanCount("");
		// eobj3.setUploadInst("");
		eobj3.setUploadInst(getUsedRUL(73, generateReportService, territoryName, edition));
		eobj3.setAdditionalReq("");
		excelFileDtoList4.add(eobj3);
		ExcelFileDto eobj4 = new ExcelFileDto();
		eobj4.setLanFieldNumber("74");
		eobj4.setLanFieldName("74-TIMESHARE CODE");
		eobj4.setLength("1");
		eobj4.setTo("1199");
		eobj4.setFrom("1199");
		eobj4.setFileName(getUsedTableNames(74, generateReportService, territoryName,edition));
		eobj4.setStartPosition(getUsedColumnNames(74, generateReportService, territoryName,edition));
		/*
		 * eobj4.setFileName(""); eobj4.setStartPosition("");
		 */
		eobj4.setEndPosition("");
		eobj4.setPullForward("");
		eobj4.setSourceCount("");
		eobj4.setLanCount("");
		// eobj4.setUploadInst("");
		eobj4.setUploadInst(getUsedRUL(74, generateReportService, territoryName, edition));
		eobj4.setAdditionalReq("");
		excelFileDtoList4.add(eobj4);
		ExcelFileDto eobj5 = new ExcelFileDto();
		eobj5.setLanFieldNumber("35");
		eobj5.setLanFieldName("35-STATE LAND USE CODE");
		eobj5.setLength("10");
		eobj5.setTo("644");
		eobj5.setFrom("653");
		eobj5.setFileName(getUsedTableNames(35, generateReportService, territoryName,edition));
		eobj5.setStartPosition(getUsedColumnNames(35, generateReportService, territoryName,edition));
		eobj5.setEndPosition("");
		eobj5.setPullForward("");
		eobj5.setSourceCount("");
		eobj5.setLanCount("");
		// eobj5.setUploadInst("");
		eobj5.setUploadInst(getUsedRUL(35, generateReportService, territoryName, edition));
		eobj5.setAdditionalReq("");
		excelFileDtoList4.add(eobj5);

		Map<String, List<ExcelFileDto>> subMap6 = new LinkedHashMap<String, List<ExcelFileDto>>();
		List<ExcelFileDto> excelFileDtoList5 = new ArrayList<ExcelFileDto>();
		subMap6.put("Class Comment:", excelFileDtoList5);
		ExcelFileDto fobj1 = new ExcelFileDto();
		fobj1.setLanFieldNumber("21");
		fobj1.setLanFieldName("21-TAX ACCOUNT NUMBER");
		fobj1.setLength("30");
		fobj1.setTo("376");
		fobj1.setFrom("405");
		fobj1.setFileName(getUsedTableNames(21, generateReportService, territoryName,edition));
		fobj1.setStartPosition(getUsedColumnNames(21, generateReportService, territoryName,edition));
		/*
		 * fobj1.setFileName(""); fobj1.setStartPosition("");
		 */
		fobj1.setEndPosition("");
		fobj1.setPullForward("");
		fobj1.setSourceCount("");
		fobj1.setLanCount("");
		// fobj1.setUploadInst("");
		fobj1.setUploadInst(getUsedRUL(21, generateReportService, territoryName, edition));
		fobj1.setAdditionalReq("");
		excelFileDtoList5.add(fobj1);

		ExcelFileDto fobj2 = new ExcelFileDto();
		fobj2.setLanFieldNumber("42");
		fobj2.setLanFieldName("42-TAX EXEMPTION CODE");
		fobj2.setLength("4");
		fobj2.setTo("691");
		fobj2.setFrom("694");
		fobj2.setFileName(getUsedTableNames(42, generateReportService, territoryName,edition));
		fobj2.setStartPosition(getUsedColumnNames(42, generateReportService, territoryName,edition));
		/*
		 * fobj2.setFileName("AK_Sitka_06_Main");
		 * fobj2.setStartPosition("ExCode");
		 */
		fobj2.setEndPosition("");
		fobj2.setPullForward("");
		fobj2.setSourceCount("");
		fobj2.setLanCount("");
		// fobj2.setUploadInst("Please refer the additional document");
		fobj2.setUploadInst(getUsedRUL(42, generateReportService, territoryName, edition));
		fobj2.setAdditionalReq("");
		excelFileDtoList5.add(fobj2);

		ExcelFileDto fobj3 = new ExcelFileDto();
		fobj3.setLanFieldNumber("43");
		fobj3.setLanFieldName("43-TAX RATE CODE AREA");
		fobj3.setLength("18");
		fobj3.setTo("695");
		fobj3.setFrom("712");
		fobj3.setFileName(getUsedTableNames(43, generateReportService, territoryName,edition));
		fobj3.setStartPosition(getUsedColumnNames(43, generateReportService, territoryName,edition));
		/*
		 * fobj3.setFileName(""); fobj3.setStartPosition("");
		 */
		fobj3.setEndPosition("");
		fobj3.setPullForward("");
		fobj3.setSourceCount("");
		fobj3.setLanCount("");
		// fobj3.setUploadInst("");
		fobj3.setUploadInst(getUsedRUL(43, generateReportService, territoryName, edition));
		fobj3.setAdditionalReq("");
		excelFileDtoList5.add(fobj3);

		ExcelFileDto fobj4 = new ExcelFileDto();
		fobj4.setLanFieldNumber("44");
		fobj4.setLanFieldName("44-TAX AMOUNT");
		fobj4.setLength("10");
		fobj4.setTo("713");
		fobj4.setFrom("722");
		fobj4.setFileName(getUsedTableNames(44, generateReportService, territoryName,edition));
		fobj4.setStartPosition(getUsedColumnNames(44, generateReportService, territoryName,edition));
		/*
		 * fobj4.setFileName("AK_Sitka_06_Main");
		 * fobj4.setStartPosition("Taxable$");
		 */
		fobj4.setEndPosition("");
		fobj4.setPullForward("");
		fobj4.setSourceCount("");
		fobj4.setLanCount("");
		// fobj4.setUploadInst("Load in the format of (8,2) add leading zero's
		// to make a length of 10(0000000000).");
		fobj4.setUploadInst(getUsedRUL(44, generateReportService, territoryName, edition));
		fobj4.setAdditionalReq("");
		excelFileDtoList5.add(fobj4);

		ExcelFileDto fobj5 = new ExcelFileDto();
		fobj5.setLanFieldNumber("45");
		fobj5.setLanFieldName("45-TAX YEAR");
		fobj5.setLength("4");
		fobj5.setTo("723");
		fobj5.setFrom("726");
		fobj5.setFileName(getUsedTableNames(45, generateReportService, territoryName,edition));
		fobj5.setStartPosition(getUsedColumnNames(45, generateReportService, territoryName,edition));
		/*
		 * fobj5.setFileName("AK_Sitka_06_Main"); fobj5.setStartPosition(
		 * "[Tax Year]");
		 */
		fobj5.setEndPosition("");
		fobj5.setPullForward("");
		fobj5.setSourceCount("");
		fobj5.setLanCount("");
		// fobj5.setUploadInst("Load [Tax Year] column as it is to 045-Tax
		// Year");
		fobj5.setUploadInst(getUsedRUL(45, generateReportService, territoryName, edition));
		fobj5.setAdditionalReq("");
		excelFileDtoList5.add(fobj5);

		ExcelFileDto fobj6 = new ExcelFileDto();
		fobj6.setLanFieldNumber("46");
		fobj6.setLanFieldName("46-TAX DELINQUENT YEAR");
		fobj6.setLength("4");
		fobj6.setTo("727");
		fobj6.setFrom("730");
		fobj6.setFileName(getUsedTableNames(46, generateReportService, territoryName,edition));
		fobj6.setStartPosition(getUsedColumnNames(46, generateReportService, territoryName,edition));
		/*
		 * fobj6.setFileName(""); fobj6.setStartPosition("");
		 */
		fobj6.setEndPosition("");
		fobj6.setPullForward("");
		fobj6.setSourceCount("");
		fobj6.setLanCount("");
		// fobj6.setUploadInst("");
		fobj6.setUploadInst(getUsedRUL(46, generateReportService, territoryName, edition));
		fobj6.setAdditionalReq("");
		excelFileDtoList5.add(fobj6);

		ExcelFileDto fobj7 = new ExcelFileDto();
		fobj7.setLanFieldNumber("155");
		fobj7.setLanFieldName("155-SCHOOL/TAX DISTRICT 1");
		fobj7.setLength("15");
		fobj7.setTo("1630");
		fobj7.setFrom("1644");
		fobj7.setFileName(getUsedTableNames(155, generateReportService, territoryName,edition));
		fobj7.setStartPosition(getUsedColumnNames(155, generateReportService, territoryName,edition));
		/*
		 * fobj7.setFileName(""); fobj7.setStartPosition("");
		 */
		fobj7.setEndPosition("");
		fobj7.setPullForward("");
		fobj7.setSourceCount("");
		fobj7.setLanCount("");
		// fobj7.setUploadInst("");
		fobj7.setUploadInst(getUsedRUL(155, generateReportService, territoryName, edition));
		fobj7.setAdditionalReq("");
		excelFileDtoList5.add(fobj7);

		ExcelFileDto fobj8 = new ExcelFileDto();
		fobj8.setLanFieldNumber("156");
		fobj8.setLanFieldName("156-SCHOOL/TAX DISTRICT 1 INDICATOR");
		fobj8.setLength("1");
		fobj8.setTo("1645");
		fobj8.setFrom("1645");
		fobj8.setFileName(getUsedTableNames(156, generateReportService, territoryName,edition));
		fobj8.setStartPosition(getUsedColumnNames(156, generateReportService, territoryName,edition));
		/*
		 * fobj8.setFileName(""); fobj8.setStartPosition("");
		 */
		fobj8.setEndPosition("");
		fobj8.setPullForward("");
		fobj8.setSourceCount("");
		fobj8.setLanCount("");
		// fobj8.setUploadInst("");
		fobj8.setUploadInst(getUsedRUL(156, generateReportService, territoryName, edition));
		fobj8.setAdditionalReq("");
		excelFileDtoList5.add(fobj8);

		ExcelFileDto fobj9 = new ExcelFileDto();
		fobj9.setLanFieldNumber("157");
		fobj9.setLanFieldName("157-SCHOOL/TAX DISTRICT 2");
		fobj9.setLength("15");
		fobj9.setTo("1646");
		fobj9.setFrom("1660");
		fobj9.setFileName(getUsedTableNames(157, generateReportService, territoryName,edition));
		fobj9.setStartPosition(getUsedColumnNames(157, generateReportService, territoryName,edition));
		/*
		 * fobj9.setFileName(""); fobj9.setStartPosition("");
		 */
		fobj9.setEndPosition("");
		fobj9.setPullForward("");
		fobj9.setSourceCount("");
		fobj9.setLanCount("");
		// fobj9.setUploadInst("");
		fobj9.setUploadInst(getUsedRUL(157, generateReportService, territoryName, edition));
		fobj9.setAdditionalReq("");
		excelFileDtoList5.add(fobj9);

		ExcelFileDto fobj10 = new ExcelFileDto();
		fobj10.setLanFieldNumber("158");
		fobj10.setLanFieldName("158-SCHOOL/TAX DISTRICT 2 INDICATOR");
		fobj10.setLength("1");
		fobj10.setTo("1661");
		fobj10.setFrom("1661");
		fobj10.setFileName(getUsedTableNames(158, generateReportService, territoryName,edition));
		fobj10.setStartPosition(getUsedColumnNames(158, generateReportService, territoryName,edition));
		/*
		 * fobj10.setFileName(""); fobj10.setStartPosition("");
		 */
		fobj10.setEndPosition("");
		fobj10.setPullForward("");
		fobj10.setSourceCount("");
		fobj10.setLanCount("");
		// fobj10.setUploadInst("");
		fobj10.setUploadInst(getUsedRUL(158, generateReportService, territoryName, edition));
		fobj10.setAdditionalReq("");
		excelFileDtoList5.add(fobj10);

		ExcelFileDto fobj11 = new ExcelFileDto();
		fobj11.setLanFieldNumber("159");
		fobj11.setLanFieldName("159-SCHOOL/TAX DISTRICT 3");
		fobj11.setLength("15");
		fobj11.setTo("1662");
		fobj11.setFrom("1676");
		fobj11.setFileName(getUsedTableNames(159, generateReportService, territoryName,edition));
		fobj11.setStartPosition(getUsedColumnNames(159, generateReportService, territoryName,edition));
		/*
		 * fobj11.setFileName(""); fobj11.setStartPosition("");
		 */
		fobj11.setEndPosition("");
		fobj11.setPullForward("");
		fobj11.setSourceCount("");
		fobj11.setLanCount("");
		// fobj11.setUploadInst("");
		fobj11.setUploadInst(getUsedRUL(159, generateReportService, territoryName, edition));
		fobj11.setAdditionalReq("");
		excelFileDtoList5.add(fobj11);

		ExcelFileDto fobj12 = new ExcelFileDto();
		fobj12.setLanFieldNumber("160");
		fobj12.setLanFieldName("160-SCHOOL/TAX DISTRICT 3 INDICATOR");
		fobj12.setLength("1");
		fobj12.setTo("1677");
		fobj12.setFrom("1677");
		fobj12.setFileName(getUsedTableNames(160, generateReportService, territoryName,edition));
		fobj12.setStartPosition(getUsedColumnNames(160, generateReportService, territoryName,edition));
		/*
		 * fobj12.setFileName(""); fobj12.setStartPosition("");
		 */
		fobj12.setEndPosition("");
		fobj12.setPullForward("");
		fobj12.setSourceCount("");
		fobj12.setLanCount("");
		// fobj12.setUploadInst("");
		fobj12.setUploadInst(getUsedRUL(160, generateReportService, territoryName, edition));
		fobj12.setAdditionalReq("");
		excelFileDtoList5.add(fobj12);

		ExcelFileDto fobj13 = new ExcelFileDto();
		fobj13.setLanFieldNumber("41");
		fobj13.setLanFieldName("41-CALIF. HOMEOWNER'S EXEMPTION");
		fobj13.setLength("1");
		fobj13.setTo("690");
		fobj13.setFrom("690");
		fobj13.setFileName(getUsedTableNames(41, generateReportService, territoryName,edition));
		fobj13.setStartPosition(getUsedColumnNames(41, generateReportService, territoryName,edition));
		/*
		 * fobj13.setFileName(""); fobj13.setStartPosition("");
		 */
		fobj13.setEndPosition("");
		fobj13.setPullForward("");
		fobj13.setSourceCount("");
		fobj13.setLanCount("");
		// fobj13.setUploadInst("");
		fobj13.setUploadInst(getUsedRUL(41, generateReportService, territoryName, edition));
		fobj13.setAdditionalReq("");
		excelFileDtoList5.add(fobj13);

		Map<String, List<ExcelFileDto>> subMap7 = new LinkedHashMap<String, List<ExcelFileDto>>();
		List<ExcelFileDto> excelFileDtoList6 = new ArrayList<ExcelFileDto>();
		subMap7.put("Class Comment:", excelFileDtoList6);

		ExcelFileDto gobj1 = new ExcelFileDto();
		gobj1.setLanFieldNumber("36");
		gobj1.setLanFieldName("36-OWNER OCCUPIED (SFR/CONDO)");
		gobj1.setLength("1");
		gobj1.setTo("654");
		gobj1.setFrom("654");
		gobj1.setFileName(getUsedTableNames(36, generateReportService, territoryName,edition));
		gobj1.setStartPosition(getUsedColumnNames(36, generateReportService, territoryName,edition));
		/*
		 * gobj1.setFileName("AK_Sitka_06_Main"); gobj1.setStartPosition("");
		 */
		gobj1.setEndPosition("");
		gobj1.setPullForward("");
		gobj1.setSourceCount("");
		gobj1.setLanCount("");
		// gobj1.setUploadInst("Please find the additional document");
		gobj1.setUploadInst(getUsedRUL(36, generateReportService, territoryName, edition));
		gobj1.setAdditionalReq("");
		excelFileDtoList6.add(gobj1);

		ExcelFileDto gobj2 = new ExcelFileDto();
		gobj2.setLanFieldNumber("93");
		gobj2.setLanFieldName("93-RECORD TYPE");
		gobj2.setLength("2");
		gobj2.setTo("1347");
		gobj2.setFrom("1348");
		gobj2.setFileName(getUsedTableNames(93, generateReportService, territoryName,edition));
		gobj2.setStartPosition(getUsedColumnNames(93, generateReportService, territoryName,edition));
		/*
		 * gobj2.setFileName("AK_Sitka_06_Main"); gobj2.setStartPosition("");
		 */
		gobj2.setEndPosition("");
		gobj2.setPullForward("");
		gobj2.setSourceCount("");
		gobj2.setLanCount("");
		// gobj2.setUploadInst("Please find the additional document");
		gobj2.setUploadInst(getUsedRUL(93, generateReportService, territoryName, edition));
		gobj2.setAdditionalReq("");
		excelFileDtoList6.add(gobj2);

		Map<String, List<ExcelFileDto>> subMap8 = new LinkedHashMap<String, List<ExcelFileDto>>();
		List<ExcelFileDto> excelFileDtoList7 = new ArrayList<ExcelFileDto>();
		subMap8.put("Class Comment:", excelFileDtoList7);

		ExcelFileDto hobj1 = new ExcelFileDto();
		hobj1.setLanFieldNumber("47");
		hobj1.setLanFieldName("47-RECORDER'S DOCUMENT NUMBER");
		hobj1.setLength("20");
		hobj1.setTo("731");
		hobj1.setFrom("750");
		hobj1.setFileName(getUsedTableNames(47, generateReportService, territoryName,edition));
		hobj1.setStartPosition(getUsedColumnNames(47, generateReportService, territoryName,edition));
		/*
		 * hobj1.setFileName(""); hobj1.setStartPosition("");
		 */
		hobj1.setEndPosition("");
		hobj1.setPullForward("");
		hobj1.setSourceCount("");
		hobj1.setLanCount("");
		// hobj1.setUploadInst("");
		hobj1.setUploadInst(getUsedRUL(47, generateReportService, territoryName, edition));
		hobj1.setAdditionalReq("");
		excelFileDtoList7.add(hobj1);

		ExcelFileDto hobj2 = new ExcelFileDto();
		hobj2.setLanFieldNumber("48");
		hobj2.setLanFieldName("48-RECORDER'S BOOK NUMBER");
		hobj2.setLength("10");
		hobj2.setTo("751");
		hobj2.setFrom("760");
		hobj2.setFileName(getUsedTableNames(48, generateReportService, territoryName,edition));
		hobj2.setStartPosition(getUsedColumnNames(48, generateReportService, territoryName,edition));
		/*
		 * hobj2.setFileName(""); hobj2.setStartPosition("");
		 */
		hobj2.setEndPosition("");
		hobj2.setPullForward("");
		hobj2.setSourceCount("");
		hobj2.setLanCount("");
		// hobj2.setUploadInst("");
		hobj2.setUploadInst(getUsedRUL(48, generateReportService, territoryName, edition));
		hobj2.setAdditionalReq("");
		excelFileDtoList7.add(hobj2);

		ExcelFileDto hobj3 = new ExcelFileDto();
		hobj3.setLanFieldNumber("49");
		hobj3.setLanFieldName("49-RECORDER'S PAGE NUMBER");
		hobj3.setLength("10");
		hobj3.setTo("761");
		hobj3.setFrom("770");
		hobj3.setFileName(getUsedTableNames(49, generateReportService, territoryName,edition));
		hobj3.setStartPosition(getUsedColumnNames(49, generateReportService, territoryName,edition));
		/*
		 * hobj3.setFileName(""); hobj3.setStartPosition("");
		 */
		hobj3.setEndPosition("");
		hobj3.setPullForward("");
		hobj3.setSourceCount("");
		hobj3.setLanCount("");
		// hobj3.setUploadInst("");
		hobj3.setUploadInst(getUsedRUL(49, generateReportService, territoryName, edition));
		hobj3.setAdditionalReq("");
		excelFileDtoList7.add(hobj3);
		ExcelFileDto hobj4 = new ExcelFileDto();
		hobj4.setLanFieldNumber("50");
		hobj4.setLanFieldName("50-RECORDING DATE");
		hobj4.setLength("8");
		hobj4.setTo("771");
		hobj4.setFrom("778");
		hobj4.setFileName(getUsedTableNames(50, generateReportService, territoryName,edition));
		hobj4.setStartPosition(getUsedColumnNames(50, generateReportService, territoryName,edition));
		/*
		 * hobj4.setFileName(""); hobj4.setStartPosition("");
		 */
		hobj4.setEndPosition("");
		hobj4.setPullForward("");
		hobj4.setSourceCount("");
		hobj4.setLanCount("");
		// hobj4.setUploadInst("");
		hobj4.setUploadInst(getUsedRUL(50, generateReportService, territoryName, edition));
		hobj4.setAdditionalReq("");
		excelFileDtoList7.add(hobj4);

		ExcelFileDto hobj5 = new ExcelFileDto();
		hobj5.setLanFieldNumber("51");
		hobj5.setLanFieldName("51-DOCUMENT TYPE (COUNTY DESCRIPTION)");
		hobj5.setLength("25");
		hobj5.setTo("779");
		hobj5.setFrom("803");
		hobj5.setFileName(getUsedTableNames(51, generateReportService, territoryName,edition));
		hobj5.setStartPosition(getUsedColumnNames(51, generateReportService, territoryName,edition));
		/*
		 * hobj5.setFileName(""); hobj5.setStartPosition("");
		 */
		hobj5.setEndPosition("");
		hobj5.setPullForward("");
		hobj5.setSourceCount("");
		hobj5.setLanCount("");
		// hobj5.setUploadInst("");
		hobj5.setUploadInst(getUsedRUL(51, generateReportService, territoryName, edition));
		hobj5.setAdditionalReq("");
		excelFileDtoList7.add(hobj5);

		Map<String, List<ExcelFileDto>> subMap9 = new LinkedHashMap<String, List<ExcelFileDto>>();
		List<ExcelFileDto> excelFileDtoList8 = new ArrayList<ExcelFileDto>();
		subMap9.put("Class Comment:", excelFileDtoList8);

		ExcelFileDto iobj1 = new ExcelFileDto();
		iobj1.setLanFieldNumber("52");
		iobj1.setLanFieldName("52-SALES PRICE");
		iobj1.setLength("10");
		iobj1.setTo("804");
		iobj1.setFrom("813");
		iobj1.setFileName(getUsedTableNames(52, generateReportService, territoryName,edition));
		iobj1.setStartPosition(getUsedColumnNames(52, generateReportService, territoryName,edition));
		iobj1.setEndPosition("");
		iobj1.setPullForward("");
		iobj1.setSourceCount("");
		iobj1.setLanCount("");
		// iobj1.setUploadInst("");
		iobj1.setUploadInst(getUsedRUL(52, generateReportService, territoryName, edition));
		iobj1.setAdditionalReq("");
		excelFileDtoList8.add(iobj1);

		ExcelFileDto iobj2 = new ExcelFileDto();
		iobj2.setLanFieldNumber("53");
		iobj2.setLanFieldName("53-SALES PRICE CODE");
		iobj2.setLength("1");
		iobj2.setTo("814");
		iobj2.setFrom("814");
		iobj2.setFileName(getUsedTableNames(53, generateReportService, territoryName,edition));
		iobj2.setStartPosition(getUsedColumnNames(53, generateReportService, territoryName,edition));
		/*
		 * iobj2.setFileName(""); iobj2.setStartPosition("");
		 */
		iobj2.setEndPosition("");
		iobj2.setPullForward("");
		iobj2.setSourceCount("");
		iobj2.setLanCount("");
		// iobj2.setUploadInst("");
		iobj2.setUploadInst(getUsedRUL(53, generateReportService, territoryName, edition));
		iobj2.setAdditionalReq("");
		excelFileDtoList8.add(iobj2);

		ExcelFileDto iobj3 = new ExcelFileDto();
		iobj3.setLanFieldNumber("54");
		iobj3.setLanFieldName("54-PRIOR SALES DATE");
		iobj3.setLength("8");
		iobj3.setTo("815");
		iobj3.setFrom("822");
		iobj3.setFileName(getUsedTableNames(54, generateReportService, territoryName,edition));
		iobj3.setStartPosition(getUsedColumnNames(54, generateReportService, territoryName,edition));
		/*
		 * iobj3.setFileName(""); iobj3.setStartPosition("");
		 */
		iobj3.setEndPosition("");
		iobj3.setPullForward("");
		iobj3.setSourceCount("");
		iobj3.setLanCount("");
		// iobj3.setUploadInst("");
		iobj3.setUploadInst(getUsedRUL(54, generateReportService, territoryName, edition));
		iobj3.setAdditionalReq("");
		excelFileDtoList8.add(iobj3);
		ExcelFileDto iobj4 = new ExcelFileDto();
		iobj4.setLanFieldNumber("55");
		iobj4.setLanFieldName("55-PRIOR SALES PRICE");
		iobj4.setLength("10");
		iobj4.setTo("823");
		iobj4.setFrom("832");
		iobj4.setFileName(getUsedTableNames(55, generateReportService, territoryName,edition));
		iobj4.setStartPosition(getUsedColumnNames(55, generateReportService, territoryName,edition));
		/*
		 * iobj4.setFileName(""); iobj4.setStartPosition("");
		 */
		iobj4.setEndPosition("");
		iobj4.setPullForward("");
		iobj4.setSourceCount("");
		iobj4.setLanCount("");
		// iobj4.setUploadInst("");
		iobj4.setUploadInst(getUsedRUL(55, generateReportService, territoryName, edition));
		iobj4.setAdditionalReq("");
		excelFileDtoList8.add(iobj4);

		ExcelFileDto iobj5 = new ExcelFileDto();
		iobj5.setLanFieldNumber("56");
		iobj5.setLanFieldName("56-PRIOR SALES CODE");
		iobj5.setLength("1");
		iobj5.setTo("833");
		iobj5.setFrom("833");
		iobj5.setFileName(getUsedTableNames(56, generateReportService, territoryName,edition));
		iobj5.setStartPosition(getUsedColumnNames(56, generateReportService, territoryName,edition));
		/*
		 * iobj5.setFileName(""); iobj5.setStartPosition("");
		 */
		iobj5.setEndPosition("");
		iobj5.setPullForward("");
		iobj5.setSourceCount("");
		iobj5.setLanCount("");
		// iobj5.setUploadInst("");
		iobj5.setUploadInst(getUsedRUL(56, generateReportService, territoryName, edition));
		iobj5.setAdditionalReq("");
		excelFileDtoList8.add(iobj5);

		Map<String, List<ExcelFileDto>> subMap10 = new LinkedHashMap<String, List<ExcelFileDto>>();
		List<ExcelFileDto> excelFileDtoList9 = new ArrayList<ExcelFileDto>();
		subMap10.put("Class Comment: Should Not Have Populated Values in LAN2.3", excelFileDtoList9);
		ExcelFileDto jobj1 = new ExcelFileDto();
		jobj1.setLanFieldNumber("112");
		jobj1.setLanFieldName("112-PROP ADDR: MATCH CODE");
		jobj1.setLength("1");
		jobj1.setTo("1401");
		jobj1.setFrom("1401");
		jobj1.setFileName(getUsedTableNames(112, generateReportService, territoryName,edition));
		jobj1.setStartPosition(getUsedColumnNames(112, generateReportService, territoryName,edition));
		/*
		 * jobj1.setFileName(""); jobj1.setStartPosition("");
		 */
		jobj1.setEndPosition("");
		jobj1.setPullForward("");
		jobj1.setSourceCount("");
		jobj1.setLanCount("");
		// jobj1.setUploadInst("");
		jobj1.setUploadInst(getUsedRUL(112, generateReportService, territoryName, edition));
		jobj1.setAdditionalReq("");
		excelFileDtoList9.add(jobj1);
		ExcelFileDto jobj2 = new ExcelFileDto();
		jobj2.setLanFieldNumber("113");
		jobj2.setLanFieldName("113-PROP ADDR: UNIT DESIGNATOR");
		jobj2.setLength("4");
		jobj2.setTo("1402");
		jobj2.setFrom("1405");
		jobj2.setFileName(getUsedTableNames(113, generateReportService, territoryName,edition));
		jobj2.setStartPosition(getUsedColumnNames(113, generateReportService, territoryName,edition));
		/*
		 * jobj2.setFileName(""); jobj2.setStartPosition("");
		 */
		jobj2.setEndPosition("");
		jobj2.setPullForward("");
		jobj2.setSourceCount("");
		jobj2.setLanCount("");
		// jobj2.setUploadInst("");
		jobj2.setUploadInst(getUsedRUL(113, generateReportService, territoryName, edition));
		jobj2.setAdditionalReq("");
		excelFileDtoList9.add(jobj2);
		ExcelFileDto jobj3 = new ExcelFileDto();
		jobj3.setLanFieldNumber("114");
		jobj3.setLanFieldName("114-PROP ADDR: UNIT NUMBER");
		jobj3.setLength("8");
		jobj3.setTo("1406");
		jobj3.setFrom("1413");
		jobj3.setFileName(getUsedTableNames(114, generateReportService, territoryName,edition));
		jobj3.setStartPosition(getUsedColumnNames(114, generateReportService, territoryName,edition));
		/*
		 * jobj3.setFileName(""); jobj3.setStartPosition("");
		 */
		jobj3.setEndPosition("");
		jobj3.setPullForward("");
		jobj3.setSourceCount("");
		jobj3.setLanCount("");
		// jobj3.setUploadInst("");
		jobj3.setUploadInst(getUsedRUL(114, generateReportService, territoryName, edition));
		jobj3.setAdditionalReq("");
		excelFileDtoList9.add(jobj3);
		ExcelFileDto jobj4 = new ExcelFileDto();
		jobj4.setLanFieldNumber("115");
		jobj4.setLanFieldName("115-PROP ADDR: CARRIER ROUTE");
		jobj4.setLength("4");
		jobj4.setTo("1414");
		jobj4.setFrom("1417");
		jobj4.setFileName(getUsedTableNames(115, generateReportService, territoryName,edition));
		jobj4.setStartPosition(getUsedColumnNames(115, generateReportService, territoryName,edition));
		/*
		 * jobj4.setFileName(""); jobj4.setStartPosition("");
		 */
		jobj4.setEndPosition("");
		jobj4.setPullForward("");
		jobj4.setSourceCount("");
		jobj4.setLanCount("");
		// jobj4.setUploadInst("");
		jobj4.setUploadInst(getUsedRUL(115, generateReportService, territoryName, edition));
		jobj4.setAdditionalReq("");
		excelFileDtoList9.add(jobj4);
		ExcelFileDto jobj5 = new ExcelFileDto();
		jobj5.setLanFieldNumber("116");
		jobj5.setLanFieldName("116-PROP ADDR GEOCODE MATCH CODE");
		jobj5.setLength("1");
		jobj5.setTo("1418");
		jobj5.setFrom("1418");
		jobj5.setFileName(getUsedTableNames(116, generateReportService, territoryName,edition));
		jobj5.setStartPosition(getUsedColumnNames(116, generateReportService, territoryName,edition));
		/*
		 * jobj5.setFileName(""); jobj5.setStartPosition("");
		 */
		jobj5.setEndPosition("");
		jobj5.setPullForward("");
		jobj5.setSourceCount("");
		jobj5.setLanCount("");
		// jobj5.setUploadInst("");
		jobj5.setUploadInst(getUsedRUL(116, generateReportService, territoryName, edition));
		jobj5.setAdditionalReq("");
		excelFileDtoList9.add(jobj5);
		ExcelFileDto jobj6 = new ExcelFileDto();
		jobj6.setLanFieldNumber("117");
		jobj6.setLanFieldName("117-PROP ADDR: LATITUDE");
		jobj6.setLength("10");
		jobj6.setTo("1419");
		jobj6.setFrom("1428");
		jobj6.setFileName(getUsedTableNames(117, generateReportService, territoryName,edition));
		jobj6.setStartPosition(getUsedColumnNames(117, generateReportService, territoryName,edition));
		/*
		 * jobj6.setFileName(""); jobj6.setStartPosition("");
		 */
		jobj6.setEndPosition("");
		jobj6.setPullForward("");
		jobj6.setSourceCount("");
		jobj6.setLanCount("");
		// jobj6.setUploadInst("");
		jobj6.setUploadInst(getUsedRUL(117, generateReportService, territoryName, edition));
		jobj6.setAdditionalReq("");
		excelFileDtoList9.add(jobj6);
		ExcelFileDto jobj7 = new ExcelFileDto();
		jobj7.setLanFieldNumber("118");
		jobj7.setLanFieldName("118-PROP ADDR: LONGITUDE");
		jobj7.setLength("11");
		jobj7.setTo("1429");
		jobj7.setFrom("1439");
		jobj7.setFileName(getUsedTableNames(118, generateReportService, territoryName,edition));
		jobj7.setStartPosition(getUsedColumnNames(118, generateReportService, territoryName,edition));
		/*
		 * jobj7.setFileName(""); jobj7.setStartPosition("");
		 */
		jobj7.setEndPosition("");
		jobj7.setPullForward("");
		jobj7.setSourceCount("");
		jobj7.setLanCount("");
		// jobj7.setUploadInst("");
		jobj7.setUploadInst(getUsedRUL(118, generateReportService, territoryName, edition));
		jobj7.setAdditionalReq("");
		excelFileDtoList9.add(jobj7);
		ExcelFileDto jobj8 = new ExcelFileDto();
		jobj8.setLanFieldNumber("119");
		jobj8.setLanFieldName("119-PROP ADDR: CENSUS TRACT & BLOCK");
		jobj8.setLength("16");
		jobj8.setTo("1440");
		jobj8.setFrom("1455");
		jobj8.setFileName(getUsedTableNames(119, generateReportService, territoryName,edition));
		jobj8.setStartPosition(getUsedColumnNames(119, generateReportService, territoryName,edition));
		/*
		 * jobj8.setFileName(""); jobj8.setStartPosition("");
		 */
		jobj8.setEndPosition("");
		jobj8.setPullForward("");
		jobj8.setSourceCount("");
		jobj8.setLanCount("");
		// jobj8.setUploadInst("");
		jobj8.setUploadInst(getUsedRUL(119, generateReportService, territoryName, edition));
		jobj8.setAdditionalReq("");
		excelFileDtoList9.add(jobj8);

		ExcelFileDto jobj9 = new ExcelFileDto();
		jobj9.setLanFieldNumber("120");
		jobj9.setLanFieldName("120-MAIL ADDR: MATCH CODE");
		jobj9.setLength("1");
		jobj9.setTo("1456");
		jobj9.setFrom("1456");
		jobj9.setFileName(getUsedTableNames(120, generateReportService, territoryName,edition));
		jobj9.setStartPosition(getUsedColumnNames(120, generateReportService, territoryName,edition));
		/*
		 * jobj9.setFileName(""); jobj9.setStartPosition("");
		 */
		jobj9.setEndPosition("");
		jobj9.setPullForward("");
		jobj9.setSourceCount("");
		jobj9.setLanCount("");
		// jobj9.setUploadInst("");
		jobj9.setUploadInst(getUsedRUL(120, generateReportService, territoryName, edition));
		jobj9.setAdditionalReq("");
		excelFileDtoList9.add(jobj9);
		ExcelFileDto jobj10 = new ExcelFileDto();
		jobj10.setLanFieldNumber("121");
		jobj10.setLanFieldName("121-MAIL ADDR: UNIT DESIGNATOR");
		jobj10.setLength("4");
		jobj10.setTo("1457");
		jobj10.setFrom("1460");
		jobj10.setFileName(getUsedTableNames(121, generateReportService, territoryName,edition));
		jobj10.setStartPosition(getUsedColumnNames(121, generateReportService, territoryName,edition));
		/*
		 * jobj10.setFileName(""); jobj10.setStartPosition("");
		 */
		jobj10.setEndPosition("");
		jobj10.setPullForward("");
		jobj10.setSourceCount("");
		jobj10.setLanCount("");
		// jobj10.setUploadInst("");
		jobj10.setUploadInst(getUsedRUL(121, generateReportService, territoryName, edition));
		jobj10.setAdditionalReq("");
		excelFileDtoList9.add(jobj10);
		ExcelFileDto jobj11 = new ExcelFileDto();
		jobj11.setLanFieldNumber("122");
		jobj11.setLanFieldName("122-MAIL ADDR: UNIT NUMBER");
		jobj11.setLength("8");
		jobj11.setTo("1461");
		jobj11.setFrom("1468");
		jobj11.setFileName(getUsedTableNames(122, generateReportService, territoryName,edition));
		jobj11.setStartPosition(getUsedColumnNames(122, generateReportService, territoryName,edition));
		/*
		 * jobj11.setFileName(""); jobj11.setStartPosition("");
		 */
		jobj11.setEndPosition("");
		jobj11.setPullForward("");
		jobj11.setSourceCount("");
		jobj11.setLanCount("");
		// jobj11.setUploadInst("");
		jobj11.setUploadInst(getUsedRUL(122, generateReportService, territoryName, edition));
		jobj11.setAdditionalReq("");
		excelFileDtoList9.add(jobj11);
		ExcelFileDto jobj12 = new ExcelFileDto();
		jobj12.setLanFieldNumber("123");
		jobj12.setLanFieldName("123-MAIL ADDR: CARRIER ROUTE");
		jobj12.setLength("4");
		jobj12.setTo("1469");
		jobj12.setFrom("1472");
		jobj12.setFileName(getUsedTableNames(123, generateReportService, territoryName,edition));
		jobj12.setStartPosition(getUsedColumnNames(123, generateReportService, territoryName,edition));
		/*
		 * jobj12.setFileName(""); jobj12.setStartPosition("");
		 */
		jobj12.setEndPosition("");
		jobj12.setPullForward("");
		jobj12.setSourceCount("");
		jobj12.setLanCount("");
		// jobj12.setUploadInst("");
		jobj12.setUploadInst(getUsedRUL(123, generateReportService, territoryName, edition));
		jobj12.setAdditionalReq("");
		excelFileDtoList9.add(jobj12);

		Map<String, Map<String, ?>> childMapWithKey = new LinkedHashMap<String, Map<String, ?>>();

		Map<String, List<ExcelFileDto>> childMap = new LinkedHashMap<String, List<ExcelFileDto>>();
		childMap.put(headerList.get(0), list1);
		childMap.put(headerList.get(1), list2);
		childMap.put(headerList.get(2), list3);
		childMap.put(headerList.get(3), list4);
		childMapWithKey.put("Class  Comment", childMap);
		// DATA ADDING
		parentMap.put("Header Fields", subMap1);
		parentMap.put("Name Fields", subMap2);
		parentMap.put("AddressFields", subMap3);
		parentMap.put("Assessment & Market Value Fields", subMap4);
		parentMap.put("Land Use Fields", subMap5);
		parentMap.put("Tax & Exemption Fields", subMap6);
		parentMap.put("Multi-Class Dependent Fields", subMap7);
		parentMap.put("County Recorder (Official Record) Fields", subMap8);
		parentMap.put("Sales Fields", subMap9);
		parentMap.put("PCR (Property Characteristic) Fields", childMapWithKey);
		parentMap.put("Filler Fields (Not Used Currently)", subMap10);

		return parentMap;
	}

	public static XSSFCellStyle getDifferentColor(int i, XSSFCellStyle style) {
		switch (i) {
		case 1:
			style.setFillForegroundColor(ExcelColorUtility.ARMYGREEN);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			break;
		case 2:
			style.setFillForegroundColor(ExcelColorUtility.OLIVE);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			break;
		case 3:
			style.setFillForegroundColor(ExcelColorUtility.SADDLEBROWN);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			break;
		case 4:
			style.setFillForegroundColor(ExcelColorUtility.REGALBLUE);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			break;
		case 5:
			style.setFillForegroundColor(ExcelColorUtility.ARMYGREEN);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			break;
		case 6:
			style.setFillForegroundColor(ExcelColorUtility.OLIVE);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			break;
		case 7:
			style.setFillForegroundColor(ExcelColorUtility.MORTAR);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			break;
		case 8:
			style.setFillForegroundColor(ExcelColorUtility.REGALBLUE);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			break;
		case 9:
			style.setFillForegroundColor(ExcelColorUtility.ARMYGREEN);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			break;
		case 10:
			style.setFillForegroundColor(ExcelColorUtility.OLIVE);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			break;
		case 11:
			style.setFillForegroundColor(ExcelColorUtility.DARKGOLDENROD);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			break;
		case 12:
			style.setFillForegroundColor(ExcelColorUtility.MORTAR);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			break;

		default:
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
		return style;
	}

	public static XSSFCellStyle getDifferentColorForRows(int i, XSSFCellStyle style, int colorCount) {
		switch (colorCount) {
		/*
		 * case 1:
		 * style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex()
		 * ); style.setFillPattern(CellStyle.SOLID_FOREGROUND); break; case 2:
		 * style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.
		 * getIndex()); style.setFillPattern(CellStyle.SOLID_FOREGROUND); break;
		 * case 3:
		 * style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		 * style.setFillPattern(CellStyle.SOLID_FOREGROUND); break; case 4:
		 * style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.
		 * getIndex()); style.setFillPattern(CellStyle.SOLID_FOREGROUND); break;
		 * case 5:
		 * style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex()
		 * ); style.setFillPattern(CellStyle.SOLID_FOREGROUND); break; case 6:
		 * style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		 * style.setFillPattern(CellStyle.SOLID_FOREGROUND); break; case 7:
		 * style.setFillForegroundColor(IndexedColors.OLIVE_GREEN.getIndex());
		 * style.setFillPattern(CellStyle.SOLID_FOREGROUND); break; case 8:
		 * style.setFillForegroundColor(IndexedColors.CORAL.getIndex());
		 * style.setFillPattern(CellStyle.SOLID_FOREGROUND); break; case 9:
		 * style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
		 * style.setFillPattern(CellStyle.SOLID_FOREGROUND); break; case 10:
		 * style.setFillForegroundColor(IndexedColors.INDIGO.getIndex());
		 * style.setFillPattern(CellStyle.SOLID_FOREGROUND); break; case 11:
		 * style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		 * style.setFillPattern(CellStyle.SOLID_FOREGROUND); break; case 12:
		 * style.setFillForegroundColor(IndexedColors.MAROON.getIndex());
		 * style.setFillPattern(CellStyle.SOLID_FOREGROUND); break;
		 * 
		 * default:
		 * style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		 * style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		 * 
		 */
		case 1:
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:

				/*
				 * HSSFColor lightGray = style2.setColor(workbook,(byte)
				 * 0xE0,(byte)0xE0,(byte) 0xE0);
				 * style2.setFillForegroundColor(lightGray.getIndex());
				 * style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
				 */

				style.setFillForegroundColor(ExcelColorUtility.MOSSGREEN);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
			case 10:
			case 12:
				style.setFillForegroundColor(ExcelColorUtility.TARA);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			default:
				style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			break;
		case 2:
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:
				style.setFillForegroundColor(ExcelColorUtility.CREAMBRULEE);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
			case 10:
			case 12:
				style.setFillForegroundColor(ExcelColorUtility.LEMONCHIFFON);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			default:
				style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			break;
		case 3:
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:
				style.setFillForegroundColor(ExcelColorUtility.APRICOT);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
			case 10:
			case 12:
				style.setFillForegroundColor(ExcelColorUtility.SERENDAE);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			default:
				style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			break;
		case 4:
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:
				style.setFillForegroundColor(ExcelColorUtility.TROPICALBLUE);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
			case 10:
			case 12:
				style.setFillForegroundColor(ExcelColorUtility.LAVENDER);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			default:
				style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			break;
		case 5:
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:
				style.setFillForegroundColor(ExcelColorUtility.MOSSGREEN);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
			case 10:
			case 12:
				style.setFillForegroundColor(ExcelColorUtility.TARA);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			default:
				style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			break;
		case 6:
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:
				style.setFillForegroundColor(ExcelColorUtility.CREAMBRULEE);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
			case 10:
			case 12:
				style.setFillForegroundColor(ExcelColorUtility.LEMONCHIFFON);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			default:
				style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			break;
		case 7:
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:
			case 13:
			case 15:
			case 17:
				style.setFillForegroundColor(ExcelColorUtility.NERO);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
			case 10:
			case 12:
			case 14:
			case 16:
			case 18:
				style.setFillForegroundColor(ExcelColorUtility.MORTAR);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			default:
				style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			break;
		case 8:
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:
			case 13:
			case 15:
			case 17:
				style.setFillForegroundColor(ExcelColorUtility.TROPICALBLUE);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
			case 10:
			case 12:
			case 14:
			case 16:
			case 18:
				style.setFillForegroundColor(ExcelColorUtility.PATTENSBLUE);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			default:
				style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			break;
		case 9:
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:
			case 13:
			case 15:
			case 17:
			case 19:
			case 21:
			case 23:
			case 25:
			case 27:
				style.setFillForegroundColor(ExcelColorUtility.MOSSGREEN);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
			case 10:
			case 12:
			case 14:
			case 16:
			case 18:
			case 20:
			case 22:
			case 24:
			case 26:
			case 28:
				style.setFillForegroundColor(ExcelColorUtility.TARA);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			default:
				style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			break;
		case 10:
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:
			case 13:
			case 15:
			case 17:
			case 19:
			case 21:
			case 23:
			case 25:
			case 27:
				style.setFillForegroundColor(ExcelColorUtility.CREAMBRULEE);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
			case 10:
			case 12:
			case 14:
			case 16:
			case 18:
			case 20:
			case 22:
			case 24:
			case 26:
			case 28:
				style.setFillForegroundColor(ExcelColorUtility.LEMONCHIFFON);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			default:
				style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			break;
		case 11:
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:
			case 13:
			case 15:
			case 17:
			case 19:
			case 21:
			case 23:
			case 25:
			case 27:
				style.setFillForegroundColor(ExcelColorUtility.DARKGRAY);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
			case 10:
			case 12:
			case 14:
			case 16:
			case 18:
			case 20:
			case 22:
			case 24:
			case 26:
			case 28:
				style.setFillForegroundColor(ExcelColorUtility.VERYLIGHTGRAY);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			default:
				style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			break;
		}
		return style;
	}
}
