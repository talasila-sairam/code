package com.appstek.dc.service;

import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.appstek.dc.dbload.SourceVerificationAnswer;
import com.appstek.dc.dto.DataConversionDto;
import com.appstek.dc.dto.ExcelFileDto;
import com.appstek.dc.dto.ExcelQuestionAnswerDto;

public interface GenerateReportService {

	public List<ExcelFileDto> getFirstTableRecordsList();
	public List<ExcelFileDto> getSecondTableRecordsList();
	public List<ExcelFileDto> getThirdTableRecordsList();
	public Map<String, Map<String,?>> getForthTableRecordsList();
	public BufferedWriter generateTextReport(String realPath);
	public HSSFWorkbook getQueryDataXl(String inputString,String realPath ) ;
	public List<String> createWordDocumentForExcelReportGeneration(String realPath,String countyName,String stateName,String edition);
	
	public String getUsedTableNames(int fieldNumber, String territoryName,String edition); 
	public String getUsedColumnNames(int fieldNumber, String territoryName,String edition);
	public String getUsedRUL(int fieldNumber,String territoryName,String edition);
	public List<SourceVerificationAnswer> getSourceVerificationAnswers(DataConversionDto dataConversionDto);
	public boolean saveSourceVerificationResult(ExcelQuestionAnswerDto exclQADto);
	public List<ExcelFileDto> getFirstTableRecordsList(String editon, Long countryFipsCode, Long stateFipsCode,
			Long countyFipsCode);
	public List<ExcelFileDto> getSecondTableRecordsList(String editon, Long countryFipsCode, Long stateFipsCode,
			Long countyFipsCode);
	public List<ExcelFileDto> getThirdTableRecordsList(String editon, String stateCode, String countyName);
	
	public String createWordDocumentForIndividualField(HttpServletResponse response,String realPath,String countyName,String stateName,String edition,int fieldNumber,String fieldName);
}
