package com.appstek.dc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	public static List readExcelFile(MultipartFile excelFile) {
		List<String> headerList = new ArrayList<String>();
		List dataList = new ArrayList();
		try {
			// Creates a workbook object from the uploaded excelfile
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
			// Creates a worksheet object representing the first sheet
			XSSFSheet worksheet = workbook.getSheetAt(0);
			int i = 0;
			while (i <= worksheet.getLastRowNum()) {
				XSSFRow row = worksheet.getRow(i++);
				// Sets the Read data to the model class
				List infoList = new ArrayList();
				if(i == 1){
					for(int j=0; j<row.getLastCellNum(); j++){
						Cell cell = row.getCell(j);
						infoList.add(cell.getStringCellValue());
					}
				}else{
					for(int j=0; j<row.getLastCellNum(); j++){
						Cell cell = row.getCell(j);
						int cel_Type = cell.getCellType();                           
	                    switch(cel_Type) {
	                    case 0: 
	                    	infoList.add((int)cell.getNumericCellValue());
	                    	System.out.print(cell.getNumericCellValue()+ " ");
	                            break;
	                    case 1:
	                    	infoList.add(cell.getStringCellValue());
	                    	System.out.print(cell.getStringCellValue()+" ");
	                            break;
	                    case 4: 
	                    	infoList.add(cell.getBooleanCellValue());
	                    	System.out.print(cell.getBooleanCellValue()+" ");
	                            break;
	                    case 3:
	                        System.out.print(" "+" ");
	                            break; 
	                    default:
	                            System.out.print("inside the default..");
	                    }
					}
				}
				dataList.add(infoList);
			}			
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return dataList;
	}

	public static void writeExcelFile(String fileNamePath, List rowDataArr_71_72_73) {
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("ruleExcel");
        int rowCount = 0;
         
        try {
			for (int i=0; i<rowDataArr_71_72_73.size(); i++) {
				List rowDataList = (ArrayList)rowDataArr_71_72_73.get(i);
			    Row row = sheet.createRow(rowCount);
			    int columnCount = 0;
			    for (int j = 0; j < rowDataList.size(); j++) {
			    	Object colObj = rowDataList.get(j);
			        Cell cell = row.createCell(columnCount);
			        CellUtil.setAlignment(cell, workbook, CellStyle.ALIGN_CENTER);
			        if (colObj instanceof String) {
			            cell.setCellValue((String) colObj);
			        } else if (colObj instanceof Integer) {
			            cell.setCellValue((Integer) colObj);
			        }
			        columnCount++;
			    }
			    rowCount++;
			}
			FileOutputStream outputStream = new FileOutputStream(fileNamePath);
			workbook.write(outputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}

	public static List readExcelFile(String ruleDataExcelSheetFilePath) {
		List dataList = new ArrayList();
		try {
			// Creates a workbook object from the uploaded excelfile
			XSSFWorkbook workbook = new XSSFWorkbook(new File(ruleDataExcelSheetFilePath));
			// Creates a worksheet object representing the first sheet
			XSSFSheet worksheet = workbook.getSheetAt(0);
			int i = 0;
			while (i <= worksheet.getLastRowNum()) {
				XSSFRow row = worksheet.getRow(i++);
				// Sets the Read data to the model class
				List infoList = new ArrayList();
				if(i == 1){
					for(int j=0; j<row.getLastCellNum(); j++){
						Cell cell = row.getCell(j);
						infoList.add(cell.getStringCellValue());
					}
				}else{
					for(int j=0; j<row.getLastCellNum(); j++){
						Cell cell = row.getCell(j);
						int cel_Type = cell.getCellType();                           
	                    switch(cel_Type) {
	                    case 0: 
	                    	infoList.add((int)cell.getNumericCellValue());
	                    	System.out.print(cell.getNumericCellValue()+ " ");
	                            break;
	                    case 1:
	                    	infoList.add(cell.getStringCellValue());
	                    	System.out.print(cell.getStringCellValue()+" ");
	                            break;
	                    case 4: 
	                    	infoList.add(cell.getBooleanCellValue());
	                    	System.out.print(cell.getBooleanCellValue()+" ");
	                            break;
	                    case 3:
	                        System.out.print(" "+" ");
	                            break; 
	                    default:
	                            System.out.print("inside the default..");
	                    }
					}
				}
				dataList.add(infoList);
			}			
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return dataList;
		
	}

	public static void writeExcelFileForSingleFields(String ruleDataExcelSheetFilePath, List rowDataArr_ForIndividual,String editionYear,int fieldNumber,String state,String realPath,String fieldName,int recentRuleId) throws InvalidFormatException {
		// TODO Auto-generated method stu
        int rowCount = 1;
        boolean sheetDupCheck=false;
        String fieldNames[]=fieldName.split("-");
        String fieldNameValue=fieldNames[0];
         try {
        	 XSSFWorkbook workbook ;
    // 	File file = new File("D:\\latest\\SOUTH DAKOTA_BUFFALO_5_5.xlsx");
       	 File file = new File(realPath+MappingConstants.EXCELFULEFORRULESCREATION+".xlsx"); 
     	if (file.exists() == false) {
     		workbook = new XSSFWorkbook();
     		 XSSFSheet sheet = workbook.createSheet(state+"_"+editionYear+"_"+fieldNumber);
		//	  Row row1 = sheet.createRow(0);
		//	  Cell cell1 = row1.createCell(0);
		//	  cell1.setCellValue("Rule Id:"+recentRuleId);
 			for (int i=0; i<rowDataArr_ForIndividual.size(); i++) {
 				List rowDataList = (ArrayList)rowDataArr_ForIndividual.get(i);
 			    Row row = sheet.createRow(rowCount);
 			    int columnCount = 0;
 			    for (int j = 0; j < rowDataList.size(); j++) {
 			    	Object colObj = rowDataList.get(j);
 			        Cell cell = row.createCell(columnCount);
 			        CellUtil.setAlignment(cell, workbook, CellStyle.ALIGN_CENTER);
 			        if (colObj instanceof String) {
 			            cell.setCellValue((String) colObj);
 			        } else if (colObj instanceof Integer) {
 			            cell.setCellValue((Integer) colObj);
 			        }
 			        columnCount++;
 			    }
 			    rowCount++;
 			}
 			FileOutputStream outputStream = new FileOutputStream(file);
 			workbook.write(outputStream);
     	}
     	else{
     		int sheetOfDup=0;
     		int lastRowInSheet=0;
     		final InputStream is = new FileInputStream(file);
     		workbook = new XSSFWorkbook(is);
     		int noOfSheets=workbook.getNumberOfSheets();
     		for(int sheetCount=0;noOfSheets>sheetCount;sheetCount++){
     			String sheetOfType = workbook.getSheetAt(sheetCount).getSheetName();
     			lastRowInSheet = workbook.getSheetAt(sheetCount).getLastRowNum();
     			if(sheetOfType.equals(state+"_"+editionYear+"_"+fieldNumber)){
     				 sheetDupCheck=true;
     				sheetOfDup=sheetCount;
     				lastRowInSheet=lastRowInSheet+1;
     				 break;
     			}
     			else{
     				sheetDupCheck=false;
     			}
     		}
     		 if(sheetDupCheck){
     			XSSFSheet sheetToAddData= workbook.getSheetAt(sheetOfDup);
	 			   Row row1 = sheetToAddData.createRow(lastRowInSheet);
	 			  Cell cell1 = row1.createCell(0);
	 			  cell1.setCellValue(MappingConstants.ruleIdInExcel+recentRuleId);
	 			 lastRowInSheet++;
				for (int i=0; i<rowDataArr_ForIndividual.size(); i++) {
						List rowDataList = (ArrayList)rowDataArr_ForIndividual.get(i);
						/*int colOfLastInserted= workbook.getSheetAt(sheetOfDup).;*/
					    Row row = sheetToAddData.createRow(lastRowInSheet);
					    int columnCount = 0;
						    for (int j = 0; j < rowDataList.size(); j++) {
						    	Object colObj = rowDataList.get(j);
						        Cell cell = row.createCell(columnCount);
						        CellUtil.setAlignment(cell, workbook, CellStyle.ALIGN_CENTER);
						        if (colObj instanceof String) {
						            cell.setCellValue((String) colObj);
						        } else if (colObj instanceof Integer) {
						            cell.setCellValue((Integer) colObj);
						        }
						        columnCount++;
						    }
						    lastRowInSheet++;
				}
				FileOutputStream outputStream = new FileOutputStream(file);
				workbook.write(outputStream);
     		}
     		else{
     			XSSFSheet sheet = workbook.createSheet(state+"_"+editionYear+"_"+fieldNumber);
     		    Row row1 = sheet.createRow(0);
	 			  Cell cell1 = row1.createCell(0);
	 			  cell1.setCellValue("Rule Id:"+recentRuleId);
				for (int i=0; i<rowDataArr_ForIndividual.size(); i++) {
						List rowDataList = (ArrayList)rowDataArr_ForIndividual.get(i);
					    Row row = sheet.createRow(rowCount);
					    int columnCount = 0;
						    for (int j = 0; j < rowDataList.size(); j++) {
						    	Object colObj = rowDataList.get(j);
						        Cell cell = row.createCell(columnCount);
						        CellUtil.setAlignment(cell, workbook, CellStyle.ALIGN_CENTER);
						        if (colObj instanceof String) {
						            cell.setCellValue((String) colObj);
						        } else if (colObj instanceof Integer) {
						            cell.setCellValue((Integer) colObj);
						        }
						        columnCount++;
						    }
					    rowCount++;
				}
				FileOutputStream outputStream = new FileOutputStream(file);
				workbook.write(outputStream);
     		}
     	}
     	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}



}
