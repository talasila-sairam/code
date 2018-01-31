package com.appstek.dc.util;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSettingBorderColorUtility {


	public static XSSFCellStyle createLeftTopBottomBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderLeft(XSSFCellStyle.BORDER_THICK);             
		 style.setBorderTop(XSSFCellStyle.BORDER_THICK);              
		 style.setBorderBottom(XSSFCellStyle.BORDER_THICK);
         style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
         style.setTopBorderColor(IndexedColors.BLACK.getIndex());
         style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
         return style;
	}
	

	public static XSSFCellStyle createRightTopBottomBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderRight(XSSFCellStyle.BORDER_THICK);             
		 style.setBorderTop(XSSFCellStyle.BORDER_THICK);              
		 style.setBorderBottom(XSSFCellStyle.BORDER_THICK);
         style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
         style.setTopBorderColor(IndexedColors.BLACK.getIndex());
         style.setRightBorderColor(IndexedColors.BLACK.getIndex());
     	 return style;
	}
	
	public static XSSFCellStyle createTopBottomBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderTop(XSSFCellStyle.BORDER_THICK);              
		 style.setBorderBottom(XSSFCellStyle.BORDER_THICK);
         style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
         style.setTopBorderColor(IndexedColors.BLACK.getIndex());
    	 return style;
	}
	

	
	public static XSSFCellStyle createLeftBottomBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderLeft(XSSFCellStyle.BORDER_THICK);              
		 style.setBorderBottom(XSSFCellStyle.BORDER_THICK);
         style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
         style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
         return style;
	}
	

	public static XSSFCellStyle createRightBottomBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderRight(XSSFCellStyle.BORDER_THICK);              
		 style.setBorderBottom(XSSFCellStyle.BORDER_THICK);
         style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
         style.setRightBorderColor(IndexedColors.BLACK.getIndex());
         return style;
	}
	

	public static XSSFCellStyle createLeftBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderLeft(XSSFCellStyle.BORDER_THICK);              
		 style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
     	 return style;
	}
	public static XSSFCellStyle createRightBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderRight(XSSFCellStyle.BORDER_THICK);              
		 style.setRightBorderColor(IndexedColors.BLACK.getIndex());
         return style;
	}
	public static XSSFCellStyle createTopBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderTop(XSSFCellStyle.BORDER_THICK);              
		 style.setTopBorderColor(IndexedColors.BLACK.getIndex());
   	 return style;
	}
	public static XSSFCellStyle createBottomBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderBottom(XSSFCellStyle.BORDER_THICK);              
		 style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
 		 return style;
	}
	
	public static XSSFCellStyle createLeftRightTopBottomBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderLeft(XSSFCellStyle.BORDER_THICK);  
		 style.setBorderRight(XSSFCellStyle.BORDER_THICK);  
		 style.setBorderTop(XSSFCellStyle.BORDER_THICK);              
		 style.setBorderBottom(XSSFCellStyle.BORDER_THICK);
         style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
         style.setTopBorderColor(IndexedColors.BLACK.getIndex());
         style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
         style.setRightBorderColor(IndexedColors.BLACK.getIndex());
         return style;
	}
	public static XSSFCellStyle createLeftRightBlueBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderLeft(XSSFCellStyle.BORDER_THIN);  
		 style.setBorderRight(XSSFCellStyle.BORDER_THIN);  
		 style.setLeftBorderColor(IndexedColors.BLUE.getIndex());
         style.setRightBorderColor(IndexedColors.BLUE.getIndex());
         return style;
	}
	
	public static XSSFCellStyle createBottomThinBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderBottom(XSSFCellStyle.BORDER_THIN);  
		 style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
         return style;
	}
	public static XSSFCellStyle createLeftBottomThinBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderLeft(XSSFCellStyle.BORDER_THIN);  
		 style.setBorderBottom(XSSFCellStyle.BORDER_THIN);  
		 style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
         style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
         return style;
	}
	
	public static XSSFCellStyle createLeftRightTopBottomTHINBorderStyle(XSSFWorkbook workbook)
	{
		 XSSFCellStyle style = workbook.createCellStyle();
		 style.setBorderLeft(XSSFCellStyle.BORDER_THIN);             
		 style.setBorderTop(XSSFCellStyle.BORDER_THIN);              
		 style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		 style.setBorderRight(XSSFCellStyle.BORDER_THIN);
         style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
         style.setTopBorderColor(IndexedColors.BLACK.getIndex());
         style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
         style.setRightBorderColor(IndexedColors.BLACK.getIndex());
         return style;
	}
}
