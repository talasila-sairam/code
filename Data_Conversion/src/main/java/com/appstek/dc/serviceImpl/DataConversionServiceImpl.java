package com.appstek.dc.serviceImpl;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.appstek.dc.dao.DataConversionDAO;
import com.appstek.dc.dao.FnfLan23Dao;
import com.appstek.dc.dao.FnfLanDictionaryDao;

import com.appstek.dc.dbload.Country;
import com.appstek.dc.dbload.County;
import com.appstek.dc.dbload.FieldMappingTable;

import com.appstek.dc.dbload.FnfLanCounties;
import com.appstek.dc.dbload.FnfLanCountries;
import com.appstek.dc.dbload.FnfLanCountyTables;
import com.appstek.dc.dbload.FnfLanDictionary;
import com.appstek.dc.dbload.FnfLanForQC;
import com.appstek.dc.dbload.FnfLanStates;
import com.appstek.dc.dbload.RuleMaster;
import com.appstek.dc.dbload.RuleQuery;
import com.appstek.dc.dbload.State;
import com.appstek.dc.dbload.Territory;
import com.appstek.dc.dto.DataConversionDto;
import com.appstek.dc.dto.DataConversionRuleDto;
/*import com.appstek.dc.dto.Field_71_72_73_ExcelDto;*/
import com.appstek.dc.service.DataConversionService;
import com.appstek.dc.util.FileUtil;

/**
 * 
 * @author  mrao
 * 
 *
 */
@Service
public class DataConversionServiceImpl implements DataConversionService {
	
	private static Logger log4j = Logger.getLogger("com.appstek.dc.serviceImpl.DataConversionServiceImpl");

	@Autowired
	private DataConversionDAO dataConversionDAO;
	@Autowired
	private FnfLan23Dao fnfLan23Dao;
	@Autowired
	FnfLanDictionaryDao fnfLanDictionaryDao;
	
	/**
	 * @author mrao
	 * This method calls loadCountries() method of respective dao class.
	 * @param 
	 * @return List<FnfLanCountries>
	 * 
	 */
	public List<FnfLanCountries> loadCountries() {
		return dataConversionDAO.loadCountries();
	}
	
	public List<FnfLanDictionary> loadAllFields(){
		return dataConversionDAO.loadAllFields();
	}
	
	/**
	 * @author mrao
	 * This method calls loadTableNames() method of respective dao class.
	 * @param DataConversionDto
	 * @return List<FnfLanCountyTables>
	 * 
	 */
	public List<FnfLanCountyTables> loadTableNames(DataConversionDto dataConversionDto) {
		return dataConversionDAO.loadTableNames(dataConversionDto);
	}
	/**
	 * @author mrao
	 * This method calls loadTableColumnNames() method of respective dao class.
	 * @param DataConversionDto
	 * @return Map<String, List<String>>
	 * 
	 */
	public Map<String, List<DataConversionDto>> loadTableColumnNames(DataConversionDto dataConversionDto) {
		return dataConversionDAO.loadTableColumnNames(dataConversionDto);
	}
	
	/**
	 * @author Sushanta
	 * This method calls executeQuery() method of respective dao class.
	 * @param String
	 * @return List<LinkedHashMap<String, Object>>
	 * 
	 */
	@Override
	public List<LinkedHashMap<String, Object>> getQueryData(String inputQuery) throws HibernateException,SQLException
	{
		List<LinkedHashMap<String, Object>> dataList = null;
        try{
        	dataList = dataConversionDAO.getQueryData(inputQuery);
        }catch(HibernateException e)
        {
        	throw new HibernateException(e.getMessage());
        }
		return dataList;
	}
	
	/**
	 * @author Sushanta
	 * This method calls executeUpdate() method of respective dao class.
	 * @param String
	 * @return integer value
	 * @deprecated
	 */
	@Override
	public int executeUpdate(String inputQuery) {

		return dataConversionDAO.executeUpdate(inputQuery);
	}
	
	public List<RuleQuery> loadAllTheRules(DataConversionDto dataConversionDto){
		return dataConversionDAO.loadAllTheRules(dataConversionDto);
	}
	
	public void deleteRule(RuleQuery ruleQuery){
		dataConversionDAO.deleteRule(ruleQuery);
	}
	
	public RuleQuery loadRule(DataConversionRuleDto dataConversionRuleDto){
		return dataConversionDAO.loadRule(dataConversionRuleDto);
	}
	
/*	@Override
	public List<String> getDefaultFlag(String selectedValue) {
		// TODO Auto-generated method stub
		return dataConversionDAO.executeQueryForDefalutFlag(selectedValue);
	}*/
	
	/**
	 * @author Sushanta
	 * This method handles complete process of saving the rule.
	 * @param DataConversionRuleDto
	 * @exception 
	 * @return boolean value
	 * 
	 */
	@Override
	public boolean saveRule(DataConversionRuleDto dataConversionRuleDto) {
		log4j.info("DataConversionServiceImpl - saveRule :: Start :: ");
		boolean flag = false;
		try {
			Territory territory = new Territory();
			territory = saveTerritoryDetails(dataConversionRuleDto);
			if(dataConversionRuleDto.getFieldNumber() == 71 || dataConversionRuleDto.getFieldNumber() == 72 || dataConversionRuleDto.getFieldNumber() == 73){
				List<Integer> fieldList = new ArrayList<Integer>();
				fieldList.add(71);
				fieldList.add(72);
				fieldList.add(73);
				flag = saveMultipleFieldRules(territory, dataConversionRuleDto);
			}else{
				RuleMaster ruleMaster = new RuleMaster();
				ruleMaster.setFieldNumber(dataConversionRuleDto.getFieldNumber());
				ruleMaster.setStatus(true);
				if(dataConversionRuleDto.getCountyFipsCode() > 0){
					ruleMaster.setTerritory(territory);
				}else if (dataConversionRuleDto.getStateFipsCode() > 0) {
					ruleMaster.setTerritory(territory.getParentTerritory());
				}else if (dataConversionRuleDto.getCountryFipsCode() > 0) {
					ruleMaster.setTerritory(territory.getParentTerritory().getParentTerritory());
				}else{
						
				}
				ruleMaster.setRuleName(getRuleName(ruleMaster.getTerritory().getTerritoryId(),dataConversionRuleDto.getFieldNumber()));
				ruleMaster.setEdition(dataConversionRuleDto.getEdition());
				RuleQuery ruleQuery = new RuleQuery();
				ruleQuery.setRule(dataConversionRuleDto.getRuleQuery());
				ruleQuery.setRuleMaster(ruleMaster);
				ruleQuery.setQuery(dataConversionRuleDto.getQuery());
				ruleQuery.setBaseQuery(dataConversionRuleDto.getBaseQuery());
				ruleQuery.setColumns(dataConversionRuleDto.getColumns());
				ruleQuery.setTables(dataConversionRuleDto.getTables());
				ruleQuery.setBuildBaseQuery(dataConversionRuleDto.getBuildbasequery());
				ruleQuery.setBuildRuleQuery(dataConversionRuleDto.getBuildrulequery());
				ruleQuery.setRuleColumn(dataConversionRuleDto.getRuleColumn());
				flag = dataConversionDAO.saveRule(ruleQuery);
				int recentRuleId=ruleMaster.getRuleId();

				/*if((flag==true)&&(dataConversionRuleDto.getFieldNumber()==8||dataConversionRuleDto.getFieldNumber()==11||dataConversionRuleDto.getFieldNumber()==10||dataConversionRuleDto.getFieldNumber()==20||dataConversionRuleDto.getFieldNumber()==33 || dataConversionRuleDto.getFieldNumber()==5 || dataConversionRuleDto.getFieldNumber()==36)){*/

				if(dataConversionRuleDto.getRowDataArr_ForIndividual().size()>0){
				if((flag==true)&&(dataConversionRuleDto.getFieldNumber()==8||dataConversionRuleDto.getFieldNumber()==11||dataConversionRuleDto.getFieldNumber()==10||dataConversionRuleDto.getFieldNumber()==20||dataConversionRuleDto.getFieldNumber()==33||dataConversionRuleDto.getFieldNumber()==106||dataConversionRuleDto.getFieldNumber()==107||dataConversionRuleDto.getFieldNumber()==108||dataConversionRuleDto.getFieldNumber()==176||dataConversionRuleDto.getFieldNumber()==88||dataConversionRuleDto.getFieldNumber()==86)){
					excelFromUploadForIndividual(territory,dataConversionRuleDto,ruleMaster,recentRuleId);
				}
				else if((flag==true)&&(dataConversionRuleDto.getFieldNumber()==177||dataConversionRuleDto.getFieldNumber()==178||dataConversionRuleDto.getFieldNumber()==181||dataConversionRuleDto.getFieldNumber()==182||dataConversionRuleDto.getFieldNumber()==183||dataConversionRuleDto.getFieldNumber()==184||dataConversionRuleDto.getFieldNumber()==185||dataConversionRuleDto.getFieldNumber()==186)){
					excelFromUploadForIndividual(territory,dataConversionRuleDto,ruleMaster,recentRuleId);
				}
				else if((flag==true)&&(dataConversionRuleDto.getFieldNumber()==171||dataConversionRuleDto.getFieldNumber()==172||dataConversionRuleDto.getFieldNumber()==173||dataConversionRuleDto.getFieldNumber()==174||dataConversionRuleDto.getFieldNumber()==175||dataConversionRuleDto.getFieldNumber()==163||dataConversionRuleDto.getFieldNumber()==164||dataConversionRuleDto.getFieldNumber()==165||dataConversionRuleDto.getFieldNumber()==166||dataConversionRuleDto.getFieldNumber()==169||dataConversionRuleDto.getFieldNumber()==135||dataConversionRuleDto.getFieldNumber()==136||dataConversionRuleDto.getFieldNumber()==137||dataConversionRuleDto.getFieldNumber()==138)){

					excelFromUploadForIndividual(territory,dataConversionRuleDto,ruleMaster,recentRuleId);
				}
			}
			}
			/*}*/
			
		} catch (Exception e) {
			e.printStackTrace();
			log4j.error("DataConversionServiceImpl - saveRule :: Error :: "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - saveRule :: Start :: ");
		return flag;
	}
	private void excelFromUploadForIndividual(Territory territory, DataConversionRuleDto dataConversionRuleDto,RuleMaster ruleMaster,int recentRuleId) {
		// TODO Auto-generated method stub
		try{
		//	String ruleDataExcelFileName = territory.getParentTerritory().getTerritoryName()+"_"+territory.getTerritoryName()+"_"+
		//			dataConversionRuleDto.getEdition()+"_"+ruleMaster.getRuleName()+".xlsx";
			String ruleDataExcelFileName="";
			//String ruleDataExcelSheetFilePath = "F:/ruleDataSheet_71_73/"+ruleDataExcelFileName;
			String ruleDataExcelSheetFilePath = dataConversionRuleDto.getRuleExcelFilePath()+"/"+ruleDataExcelFileName;
			String edition =dataConversionRuleDto.getEdition();
			int fieldNumber=ruleMaster.getFieldNumber();
			String fieldName=dataConversionRuleDto.getRuleFieldName();
			FileUtil.writeExcelFileForSingleFields(ruleDataExcelSheetFilePath, dataConversionRuleDto.getRowDataArr_ForIndividual(),edition,fieldNumber,territory.getParentTerritory().getTerritoryName(),dataConversionRuleDto.getRuleExcelFilePath(), fieldName,recentRuleId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * @author Sushanta
	 * This method saving the multiple rule.
	 * @param Territory, DataConversionRuleDto
	 * @exception 
	 * @return boolean value
	 * 
	 */
	private boolean saveMultipleFieldRules(Territory territory, DataConversionRuleDto dataConversionRuleDto) {
		log4j.info("DataConversionServiceImpl - saveMultipleFieldRules :: Start :: ");
		boolean flag = false;
		try {
			List<RuleQuery>  fieldsRuleQueryList = new ArrayList<RuleQuery>();
				RuleMaster ruleMaster = new RuleMaster();
				ruleMaster.setFieldNumber(71);
				ruleMaster.setStatus(true);
				if(dataConversionRuleDto.getCountyFipsCode() > 0){
					ruleMaster.setTerritory(territory);
				}else if (dataConversionRuleDto.getStateFipsCode() > 0) {
					ruleMaster.setTerritory(territory.getParentTerritory());
				}else if (dataConversionRuleDto.getCountryFipsCode() > 0) {
					ruleMaster.setTerritory(territory.getParentTerritory().getParentTerritory());
				}else{
						
				}
				ruleMaster.setRuleName(getRuleName(ruleMaster.getTerritory().getTerritoryId(),71));
				ruleMaster.setEdition(dataConversionRuleDto.getEdition());
				RuleQuery ruleQuery = new RuleQuery();
				ruleQuery.setRule(dataConversionRuleDto.getRuleQuery());
				ruleQuery.setRuleMaster(ruleMaster);
				ruleQuery.setQuery(dataConversionRuleDto.getRuleQuery71());
				ruleQuery.setBaseQuery(dataConversionRuleDto.getBaseQuery());
				ruleQuery.setColumns(dataConversionRuleDto.getColumns());
				ruleQuery.setTables(dataConversionRuleDto.getTables());
				
				
				
				RuleMaster ruleMaster72 = new RuleMaster();
				ruleMaster72.setFieldNumber(72);
				ruleMaster72.setStatus(true);
				if(dataConversionRuleDto.getCountyFipsCode() > 0){
					ruleMaster72.setTerritory(territory);
				}else if (dataConversionRuleDto.getStateFipsCode() > 0) {
					ruleMaster72.setTerritory(territory.getParentTerritory());
				}else if (dataConversionRuleDto.getCountryFipsCode() > 0) {
					ruleMaster72.setTerritory(territory.getParentTerritory().getParentTerritory());
				}else{
						
				}
				ruleMaster72.setRuleName(getRuleName(ruleMaster72.getTerritory().getTerritoryId(),72));
				ruleMaster72.setEdition(dataConversionRuleDto.getEdition());
				RuleQuery ruleQuery72 = new RuleQuery();
				ruleQuery72.setRule(dataConversionRuleDto.getRuleQuery());
				ruleQuery72.setRuleMaster(ruleMaster72);
				ruleQuery72.setQuery(dataConversionRuleDto.getRuleQuery72());
				ruleQuery72.setBaseQuery(dataConversionRuleDto.getBaseQuery());
				ruleQuery72.setColumns(dataConversionRuleDto.getColumns());
				ruleQuery72.setTables(dataConversionRuleDto.getTables());
				
				
				RuleMaster ruleMaster73 = new RuleMaster();
				ruleMaster73.setFieldNumber(73);
				ruleMaster73.setStatus(true);
				if(dataConversionRuleDto.getCountyFipsCode() > 0){
					ruleMaster73.setTerritory(territory);
				}else if (dataConversionRuleDto.getStateFipsCode() > 0) {
					ruleMaster73.setTerritory(territory.getParentTerritory());
				}else if (dataConversionRuleDto.getCountryFipsCode() > 0) {
					ruleMaster73.setTerritory(territory.getParentTerritory().getParentTerritory());
				}else{
						
				}
				ruleMaster73.setRuleName(getRuleName(ruleMaster73.getTerritory().getTerritoryId(),73));
				ruleMaster73.setEdition(dataConversionRuleDto.getEdition());
				RuleQuery ruleQuery73 = new RuleQuery();
				ruleQuery73.setRule(dataConversionRuleDto.getRuleQuery());
				ruleQuery73.setRuleMaster(ruleMaster73);
				ruleQuery73.setQuery(dataConversionRuleDto.getRuleQuery73());
				ruleQuery73.setBaseQuery(dataConversionRuleDto.getBaseQuery());
				ruleQuery73.setColumns(dataConversionRuleDto.getColumns());
				ruleQuery73.setTables(dataConversionRuleDto.getTables());
				fieldsRuleQueryList.add(ruleQuery);
				fieldsRuleQueryList.add(ruleQuery72);
				fieldsRuleQueryList.add(ruleQuery73);
				flag = dataConversionDAO.saveMultipleFieldRules(fieldsRuleQueryList);
				int recentRuleId=ruleMaster73.getRuleId();
			if(flag){
				String ruleDataExcelFileName = territory.getParentTerritory().getTerritoryName()+"_"+territory.getTerritoryName()+"_"+
												dataConversionRuleDto.getEdition()+"_71_72_73_"+ruleMaster.getRuleName()+".xlsx";
				//String ruleDataExcelSheetFilePath = "F:/ruleDataSheet_71_73/"+ruleDataExcelFileName;
				String ruleDataExcelSheetFilePath = dataConversionRuleDto.getRuleExcelFilePath()+"/"+ruleDataExcelFileName;
				String edition =dataConversionRuleDto.getEdition();
				int fieldNumber=ruleMaster.getFieldNumber();
				String fieldName=dataConversionRuleDto.getRuleFieldName();
				FileUtil.writeExcelFileForSingleFields(ruleDataExcelSheetFilePath,dataConversionRuleDto.getRowDataArr_71_72_73(),edition,fieldNumber,territory.getParentTerritory().getTerritoryName(),dataConversionRuleDto.getRuleExcelFilePath(), fieldName,recentRuleId);
			//	FileUtil.writeExcelFile(ruleDataExcelSheetFilePath, dataConversionRuleDto.getRowDataArr_71_72_73());
			//	FileUtil.readExcelFile(ruleDataExcelSheetFilePath);
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
			log4j.info("DataConversionServiceImpl - saveMultipleFieldRules :: Error :: "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - saveMultipleFieldRules :: End :: ");
		return flag;
	}

	private String getRuleName(int territoryId,int fieldNumber) {
		log4j.info("DataConversionServiceImpl - getRuleName :: Start :: ");
		String ruleName = "";
		int ruleCountForCountyField = 0;
		try {
			ruleCountForCountyField = dataConversionDAO.ruleCountForCountyField(territoryId,fieldNumber); 
			log4j.info("DataConversionServiceImpl - ruleCountForCountyField :: msg :: "+ruleCountForCountyField);
			ruleCountForCountyField = ruleCountForCountyField+1;
			ruleName = ruleCountForCountyField+"";
		} catch (Exception e) {
			log4j.info("DataConversionServiceImpl - getRuleName :: Error :: "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - getRuleName :: ruleName :: "+ruleName);
		log4j.info("DataConversionServiceImpl - getRuleName :: End :: ");
		return ruleName;
		
	}

	/**
	 * @author Sushanta
	 * This method called by the above method for saving the territory.
	 * @param DataConversionRuleDto
	 * @exception 
	 * @return Territory
	 * 
	 */
	private Territory saveTerritoryDetails(DataConversionRuleDto dataConversionRuleDto) {
		log4j.info("DataConversionServiceImpl - getTerritoryDetailsByNameFips :: Start :: ");
		Territory countyTerritory = null;
		try {
			countyTerritory = dataConversionDAO.getTerritoryDetailsByNameFips(dataConversionRuleDto.getCountyName(),dataConversionRuleDto.getCountyFipsCode());
			if(countyTerritory == null){
				countyTerritory = new Territory();
				Territory stateTerritory = dataConversionDAO.getTerritoryDetailsByNameFips(dataConversionRuleDto.getStateName(),dataConversionRuleDto.getStateFipsCode());
				if(stateTerritory == null){
					stateTerritory = new Territory();
					Territory countryTerritory = dataConversionDAO.getTerritoryDetailsByNameFips(dataConversionRuleDto.getCountryName(),dataConversionRuleDto.getCountryFipsCode());
					if(countryTerritory == null){
						countryTerritory = new Territory();
						countryTerritory.setTerritoryName(dataConversionRuleDto.getCountryName());
						countryTerritory.setFipsCode(dataConversionRuleDto.getCountyFipsCode());
						countryTerritory.setTerritoryType("COUNTRY");
						stateTerritory.setParentTerritory(countryTerritory);
						stateTerritory.setFipsCode(dataConversionRuleDto.getStateFipsCode());
						stateTerritory.setTerritoryName(dataConversionRuleDto.getStateName());
						stateTerritory.setTerritoryType("STATE");
						countyTerritory.setParentTerritory(stateTerritory);
						countyTerritory.setTerritoryName(dataConversionRuleDto.getCountyName());
						countyTerritory.setFipsCode(dataConversionRuleDto.getCountyFipsCode());
						countyTerritory.setTerritoryType("COUNTY");
					}else{
						stateTerritory.setParentTerritory(countryTerritory);
						stateTerritory.setFipsCode(dataConversionRuleDto.getStateFipsCode());
						stateTerritory.setTerritoryName(dataConversionRuleDto.getStateName());
						stateTerritory.setTerritoryType("STATE");
						countyTerritory.setParentTerritory(stateTerritory);
						countyTerritory.setTerritoryName(dataConversionRuleDto.getCountyName());
						countyTerritory.setFipsCode(dataConversionRuleDto.getCountyFipsCode());
						countyTerritory.setTerritoryType("COUNTY");
					}
				}else{
					stateTerritory.setTerritoryType("STATE");
					countyTerritory.setParentTerritory(stateTerritory);
					countyTerritory.setTerritoryName(dataConversionRuleDto.getCountyName());
					countyTerritory.setFipsCode(dataConversionRuleDto.getCountyFipsCode());
					countyTerritory.setTerritoryType("COUNTY");
				}
				boolean flag = dataConversionDAO.saveTerritory(countyTerritory);
				if(flag)
					countyTerritory = dataConversionDAO.getTerritoryDetailsByNameFips(dataConversionRuleDto.getCountyName(),dataConversionRuleDto.getCountyFipsCode());
			}else{
				
			}
		} catch (Exception e) {
			log4j.error("DataConversionServiceImpl - getTerritoryDetailsByNameFips :: Error :: "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - getTerritoryDetailsByNameFips :: End :: ");
		return countyTerritory;
	}
	/**
	 * @author Sushanta
	 * This method checks the rule name is exists in the database or not.
	 * @param ruleName
	 * @exception 
	 * @return boolean;
	 * 
	 */
	@Override
	public boolean isExistsRuleName(String ruleName) {
		log4j.info("DataConversionServiceImpl - isExistsRuleName :: Start :: ");
		boolean flag = false;
		try {
			RuleMaster ruleMaster = dataConversionDAO.getRuleMasterByRuleName(ruleName);
			if(ruleMaster != null)
				flag = true;
		} catch (Exception e) {
			log4j.error("DataConversionServiceImpl - isExistsRuleName :: Error :: "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - isExistsRuleName :: End :: ");
		return flag;
	}
	
	/**
	 * @author Sushanta
	 * This method provides the rules to the controller related to fields.
	 * @param ruleDto
	 * @exception 
	 * @return List<DataConversionRuleDto>;
	 * 
	 */
	@Override
	public List<DataConversionRuleDto> getFieldRules(DataConversionRuleDto ruleDto) {
		log4j.info("DataConversionServiceImpl - getFieldRules :: Start :: ");
		List<DataConversionRuleDto> fieldRuleList = new ArrayList<DataConversionRuleDto>();
		Territory territory = new Territory();
		try {
			if(ruleDto.getCountyFipsCode() > 0){
				territory = dataConversionDAO.getTerritoryDetailsByNameFips(ruleDto.getCountyName(), ruleDto.getCountyFipsCode());
			}else{ 
				if (ruleDto.getStateFipsCode() > 0) {
					territory = dataConversionDAO.getTerritoryDetailsByNameFips(ruleDto.getStateName(), ruleDto.getStateFipsCode());
				}else{
					if (ruleDto.getCountryFipsCode() > 0) {
						territory = dataConversionDAO.getTerritoryDetailsByNameFips(ruleDto.getCountryName(), ruleDto.getCountryFipsCode());
					}else{
						
					}
				}
			}
			List<RuleQuery> fieldRules = dataConversionDAO.getFieldRules(ruleDto.getFieldList(), territory.getTerritoryId());
			for(int i=0; i<fieldRules.size();i++){
				RuleQuery rule = (RuleQuery)fieldRules.get(i);
				DataConversionRuleDto dto = new DataConversionRuleDto();
				dto.setRuleQueryId(rule.getRuleQueryId());
				dto.setRuleName(rule.getRuleMaster().getRuleName());
				dto.setRuleQuery(rule.getRule());
				dto.setFieldNumber(rule.getRuleMaster().getFieldNumber());
				fieldRuleList.add(dto);
			}
		} catch (Exception e) {
			log4j.error("DataConversionServiceImpl - getFieldRules :: Error "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - getFieldRules :: End :: ");
		return fieldRuleList;
	}
	
	/**
	 * @author Sushanta
	 * This method sets the respective rule entity data and call the dao class method for edit rule.
	 * @param dataConversionRuleDto
	 * @exception 
	 * @return true/false
	 * 
	 */
	@Override
	public boolean editRule(DataConversionRuleDto dataConversionRuleDto) {
		log4j.info("DataConversionServiceImpl - editRule :: Start :: ");
		boolean flag = false;
		try {
			RuleQuery ruleQuery = dataConversionDAO.loadRule(dataConversionRuleDto);
			if(ruleQuery != null){
				ruleQuery.setRule(dataConversionRuleDto.getRuleQuery());
				ruleQuery.setBaseQuery(dataConversionRuleDto.getBaseQuery());
				ruleQuery.setQuery(dataConversionRuleDto.getQuery());
				if(dataConversionRuleDto.getTables() != null && dataConversionRuleDto.getTables() != ""){
					ruleQuery.setTables(dataConversionRuleDto.getTables());
				}
				if(dataConversionRuleDto.getColumns() != null && dataConversionRuleDto.getColumns() != ""){
					ruleQuery.setColumns(dataConversionRuleDto.getColumns());
				}
				if(dataConversionRuleDto.getEdition() != null && dataConversionRuleDto.getEdition() != ""){
					ruleQuery.getRuleMaster().setEdition(dataConversionRuleDto.getEdition());
				}
			}
			flag = dataConversionDAO.updateRule(ruleQuery);
		} catch (Exception e) {
			log4j.error("DataConversionServiceImpl - editRule :: Error "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - editRule :: End :: ");
		return flag;
	}
	
	/**
	 * @author Sushanta
	 * This method sets the respective rule entity data and call the dao class method for deactivate the rule.
	 * @param dataConversionRuleDto
	 * @exception 
	 * @return true/false
	 * 
	 */
	@Override
	public boolean deleteRule(DataConversionRuleDto dataConversionRuleDto) {
		log4j.info("DataConversionServiceImpl - deleteRule :: Start :: ");
		boolean flag = false;
		try {
			RuleQuery ruleQuery = dataConversionDAO.loadRule(dataConversionRuleDto);
			if(ruleQuery != null){
				ruleQuery.setDeleted(true);
				RuleMaster rlmstr = ruleQuery.getRuleMaster();
				rlmstr.setStatus(false);
				ruleQuery.setRuleMaster(rlmstr);
				//ruleQuery.getRuleMaster().setStatus(false);
			}
			flag = dataConversionDAO.updateRule(ruleQuery);
		} catch (Exception e) {
			log4j.error("DataConversionServiceImpl - deleteRule :: Error "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - deleteRule :: End :: ");
		return flag;
	}

	/*@Override
	public void getQueryDataXl() {
		// TODO Auto-generated method stub
		 dataConversionDAO.getQueryDataXl();
	}*/

	@Override
	public List<List<LinkedHashMap<String, Object>>> getResultOfJoinCondition(String tableNames) {
		// TODO Auto-generated method stub
		return dataConversionDAO.executeQueryForJoinCondition(tableNames);
	}

	@Override
	public String saveDataForLan23(String countyName,String stateName,String edition) {
		// TODO Auto-generated method stub
		String msg = "";
		log4j.info("DataConversionServiceImpl - saveDataForLan23 :: Start :: ");
		try{
			List<FnfLanDictionary> fnfLanDictionaryList = fnfLanDictionaryDao.getMappingColumnInfo();
			 msg = fnfLan23Dao.saveData(countyName,stateName,fnfLanDictionaryList,edition);
		}catch(Exception e)
		{
			msg = e.getMessage();
			log4j.error("DataConversionServiceImpl - saveDataForLan23 :: Error "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - saveDataForLan23 :: END :: ");
		return msg;
	}

	@Override
	public List<DataConversionRuleDto> getPerticularFieldRules(DataConversionRuleDto ruleDto) {
		// TODO Auto-generated method stub
		log4j.info("DataConversionServiceImpl - getPerticularFieldRules :: Start :: ");
		List<DataConversionRuleDto> fieldRuleList = new ArrayList<DataConversionRuleDto>();
		Territory territory = new Territory();
		try {
			if(ruleDto.getCountyFipsCode() > 0){
				territory = dataConversionDAO.getTerritoryDetailsByNameFips(ruleDto.getCountyName(), ruleDto.getCountyFipsCode());
			}else{ 
				if (ruleDto.getStateFipsCode() > 0) {
					territory = dataConversionDAO.getTerritoryDetailsByNameFips(ruleDto.getStateName(), ruleDto.getStateFipsCode());
				}else{
					if (ruleDto.getCountryFipsCode() > 0) {
						territory = dataConversionDAO.getTerritoryDetailsByNameFips(ruleDto.getCountryName(), ruleDto.getCountryFipsCode());
					}else{
						
					}
				}
			}
			List<RuleQuery> fieldRules = dataConversionDAO.getPerticularField(ruleDto.getFieldNumber(), territory.getTerritoryId());
			for(int i=0; i<fieldRules.size();i++){
				RuleQuery rule = (RuleQuery)fieldRules.get(i);
				DataConversionRuleDto dto = new DataConversionRuleDto();
				dto.setRuleQueryId(rule.getRuleQueryId());
				dto.setRuleName(rule.getRuleMaster().getRuleName());
				dto.setRuleQuery(rule.getRule());
				dto.setFieldNumber(rule.getRuleMaster().getFieldNumber());
				fieldRuleList.add(dto);
			}
		} catch (Exception e) {
			log4j.error("DataConversionServiceImpl - getPerticularFieldRules :: Error "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - getPerticularFieldRules :: End :: ");
		return fieldRuleList;
	}

	/*@Override
	public List<String> getTablesNames(String countryName) {
		// TODO Auto-generated method stub
		return null;
	}*/

/*	@Override
	public List<String> getTablesNames(String countyName) {
		log4j.info("DataConversionServiceImpl - getTablesNames :: Start :: ");
		List<String> returnCountyTableNames = new ArrayList();
		try {
			returnCountyTableNames = dataConversionDAO.getTablesNames(countyName);
			
		}catch (Exception e) {
			log4j.error("DataConversionServiceImpl - getTablesNames :: Error "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - getTablesNames :: End :: "); 
		return returnCountyTableNames;
	}*/
	
	/**
	 * chandu code starts here 
	 */
		@Override
		public ArrayList<Object> FetchCounties(String inputQuery) {
			// TODO Auto-generated method stub
			return dataConversionDAO.FetchCounties(inputQuery);
		}

		@Override
		public ArrayList<Object> Fetchusers(String inputQuery) {
			// TODO Auto-generated method stub
			return dataConversionDAO.Fetchusers(inputQuery);
		}

		@Override
		public void saveUserTaskmanagement(String user,String county,String state,String editionyear,String assignedTime,String countyStateReference) {
			// TODO Auto-generated method stub
			dataConversionDAO.saveUserTaskmanagement(user,county,state,editionyear,assignedTime,countyStateReference);
		}
	@Override
	public List<LinkedHashMap<String, Object>> showUserTaskmanagement(String fromDate,String toDate) {
		// TODO Auto-generated method stub
		return dataConversionDAO.showUserTaskmanagement(fromDate,toDate);
	}
		@Override
		public void showValidateUser(HttpServletRequest request, HttpServletResponse response,
				String username, String password) {
			// TODO Auto-generated method stub
			 dataConversionDAO.showValidateUser(request,response,username,password);
		}

		@Override
		public List<LinkedHashMap<String, Object>>  UserReportfetchRecords(HttpServletRequest request, HttpServletResponse response, String userName) {
			return dataConversionDAO.UserReportfetchRecords(request,response,userName);
		}

		@Override
		public void UserReportInsertRecords(HttpServletRequest request, HttpServletResponse response, String comments,
				String currentStatus,String selectedUser,String signOffStatus,String countyStateReference) {
			// TODO Auto-generated method stub
			dataConversionDAO.UserReportInsertRecords(request,response,comments,currentStatus,selectedUser,signOffStatus,countyStateReference);
		}

		@Override
		public ArrayList<Object> FetchStates(String selectQuery) {
			// TODO Auto-generated method stub
			return dataConversionDAO.FetchStates(selectQuery);
		}

		@Override
		public int FetchNoOfTablesPerCounty(String stateCountyEditionYear) {
			// TODO Auto-generated method stub
			return dataConversionDAO.FetchNoOfTablesPerCounty(stateCountyEditionYear);
		}
	/**
	 * chandu code ends here
	 */
		@Override
		public int getRuleCount(DataConversionDto dataConversionDto) {
			return dataConversionDAO.getRuleCount(dataConversionDto);
		}
		/**
		 * chandu validation code starts here
		 */
		@Override
		public List<Object> saveSelectNumber(String inputQuery) {
			// TODO Auto-generated method stub
			return dataConversionDAO.saveSelectNumber(inputQuery);
		}

	@Override
	public List<FnfLanCountries> getAllCountries() {
		return dataConversionDAO.getAllCountries();
	}

	@Override
	public List<FnfLanStates> getStatesForCountry(DataConversionDto dto) {
		return dataConversionDAO.getStatesForCountry(dto);
	}

	@Override
	public List<FnfLanCounties> getCountiesForState(DataConversionDto dto) {
		return dataConversionDAO.getCountiesForState(dto);
	}

	@Override
	public List<List<LinkedHashMap<String, Object>>> getTableInformation(String tableData) {
		return dataConversionDAO.joinConditionTablesList(tableData);
	}

	@Override
	public int getNumberofCountQuery(String checkingQuery) {
		// TODO Auto-generated method stub
		return dataConversionDAO.getCountOfJoinConditon(checkingQuery);
	}

	@Override
	public List fetchDataFromExcel(MultipartFile excelFile) {
		List dataList = FileUtil.readExcelFile(excelFile);
		return dataList;
	}

	@Override
	public void insertJoinCondition(String selectedQuery, String tablesSelectedForJoins) {
		// TODO Auto-generated method stub
		 dataConversionDAO.insertJoinCondition(selectedQuery,tablesSelectedForJoins);
	}

	@Override
	public int getCheckingTablesList(String tablesSelectedForJoinsString) {
		// TODO Auto-generated method stub
		return dataConversionDAO.getCheckingTablesListResult(tablesSelectedForJoinsString);
	}

	@Override
	public List<List<LinkedHashMap<String, Object>>> getTheJoinConditionListCheck(String selectedTableColumns) {
		// TODO Auto-generated method stub
		return dataConversionDAO.getTheJoinConditionListResult(selectedTableColumns);
	}

	
	/**
	 * Srikanth code
	 */
	@Override
	public List<Country> getCountryList() {
		return dataConversionDAO.getCountryList();
	}

	@Override
	public List<State> getStateList(Long countryCode) {
		return dataConversionDAO.getStateList(countryCode);
	}

	@Override
	public List<County> getCountyList(Long stateFipsCode) {
		return dataConversionDAO.getCountyList(stateFipsCode);
	}

	@Override
	public List<FnfLanDictionary> getSelectedCountyFields(String countyName, String stateName) {
		return dataConversionDAO.getFieldList(countyName, stateName);
	}

	@Override
	public List<FieldMappingTable> getFieldMappingForParentFieds(Integer fieldNumber) {
		// TODO Auto-generated method stub
		return dataConversionDAO.getFieldMappingForParentFieds(fieldNumber);
	}
	
	@Override
	public String updatePassword(HttpServletRequest request, HttpServletResponse response, String createusername,
			String createpassword) {
		// TODO Auto-generated method stub
		return dataConversionDAO.updatePassword(request,response,createusername,createpassword);
	}

	@Override
	public String showCreateUsers(HttpServletRequest request, HttpServletResponse response, String createusername,
			String createpassword,String menuaccess) {
		// TODO Auto-generated method stub
		return dataConversionDAO.showCreateUsers(request,response,createusername,createpassword,menuaccess);
}
	
	
	/**
	 * @author Sushanta
	 * This method performs data saving operation into individual LAN23 tables field wise.
	 * @param dataConversionRuleDto
	 * @exception 
	 * @return true/false
	 * 
	 */
	@Override
	public boolean saveFieldData(DataConversionRuleDto dataConversionRuleDto) {
		log4j.info("DataConversionServiceImpl - saveFieldData :: Start :: ");
		boolean flag = false;
		try {
			List<RuleQuery> fieldRuleQueries = dataConversionDAO.getFieldRules(dataConversionRuleDto);
			String fieldLan23TableNameSource = findFieldSpecificLan23tableName(fieldRuleQueries);
			String fieldLan23TableName = "LAN23_"+fieldLan23TableNameSource;
			String fieldRulesUnionQuery = formRulesUnionQuery(fieldRuleQueries);
			List<LinkedHashMap<String, Object>> fldQryDtaBfrValdn = getQueryData(fieldRulesUnionQuery);
			List<LinkedHashMap<String, Object>> fldQryDtaAftrValdn = new ArrayList<LinkedHashMap<String,Object>>();
			/*
			 * Validation
			 */
			if(!fldQryDtaBfrValdn.isEmpty())
				fldQryDtaAftrValdn = validateIndividualFieldData(fldQryDtaBfrValdn, dataConversionRuleDto.getFieldNumber(), dataConversionRuleDto.getMappingFieldName());
			/*
			 * Saving
			 */
			if(!fldQryDtaAftrValdn.isEmpty())
				flag = dataConversionDAO.saveFieldData(fldQryDtaAftrValdn, fieldLan23TableName, dataConversionRuleDto.getMappingFieldName());
			
		} catch (Exception e) {
			log4j.info("DataConversionServiceImpl - saveFieldData :: Error :: "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - saveFieldData :: End :: ");
		return flag;
	}
	private List<LinkedHashMap<String, Object>> validateIndividualFieldData(List<LinkedHashMap<String, Object>> fldQryDtaBfrValdn, int fieldNumber, String mappingFieldName) {
		log4j.info("DataConversionServiceImpl - validateIndividualFieldData :: Start :: ");
		List<LinkedHashMap<String, Object>> fldQryDtaAftrValdn = new ArrayList<LinkedHashMap<String, Object>>();
		try {
			fldQryDtaAftrValdn = fldQryDtaBfrValdn;
		} catch (Exception e) {
			log4j.info("DataConversionServiceImpl - validateIndividualFieldData :: Error :: "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - validateIndividualFieldData :: End :: ");
		return fldQryDtaAftrValdn;
	}

	/**
	 * @author Sushanta
	 * This method forms the union query for all rules of respective field.
	 * @param String
	 * @exception 
	 * @return String lan23TableName
	 * 
	 */
	private String formRulesUnionQuery(List<RuleQuery> fieldRuleQueries) {
		log4j.info("DataConversionServiceImpl - formRulesUnionQuery :: Start :: ");
		String fieldRulesUnionQuery = "";
		try {
			for(int i = 0; i < fieldRuleQueries.size(); i++){
				RuleQuery query = fieldRuleQueries.get(i);
				if(i == 0){
					fieldRulesUnionQuery = query.getBuildRuleQuery();
				}else{
					String notInQuery = "";
					int j=i-1;
					while(j >= 0){
						RuleQuery prevRulQuery = fieldRuleQueries.get(j);
						//String y = x.substring(0,firstPos) + "Your new text" + x.substring(lastPos);
						String rulQryStr = prevRulQuery.getBuildRuleQuery();
						int firstPos = rulQryStr.indexOf("SELECT") + "SELECT".length();
						int secondPosition = rulQryStr.indexOf("FROM", firstPos);
						if(i-j == 1)
							notInQuery = rulQryStr.substring(0, firstPos)+" id "+ rulQryStr.substring(secondPosition);
						else
							notInQuery = notInQuery+" UNION "+rulQryStr.substring(0, firstPos)+" id "+ rulQryStr.substring(secondPosition);
						j--;
					}
					if(query.getBuildRuleQuery().contains("CASE WHEN")){
						fieldRulesUnionQuery = fieldRulesUnionQuery+" UNION ALL "+query.getBuildRuleQuery()+" where id not in("+notInQuery+")";
					}else{
						fieldRulesUnionQuery = fieldRulesUnionQuery+" UNION ALL "+query.getBuildRuleQuery()+" and id not in("+notInQuery+")";
					}
				}
			}
		} catch (Exception e) {
			log4j.info("DataConversionServiceImpl - formRulesUnionQuery :: Error :: "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - formRulesUnionQuery :: End :: ");
		return fieldRulesUnionQuery;
	}

	/**
	 * @author Sushanta
	 * This method find out the individual LAN23 table name  for respective field data.
	 * @param List<RuleQuery>
	 * @exception 
	 * @return String lan23TableName
	 * 
	 */
	private String findFieldSpecificLan23tableName(List<RuleQuery> fieldRuleQueries) {
		log4j.info("DataConversionServiceImpl - findFieldSpecificLan23tableName :: Start :: ");
		String lan23TableName = "";
		try {
			HashSet<String> ruleTableNames = new HashSet<String>();
			HashSet<String> ruleColumnnames = new HashSet<String>();
			String usedtableNames = "";
			for(RuleQuery query : fieldRuleQueries){
				String[] tablenames = query.getTables().split(",");
				Collections.addAll(ruleTableNames, tablenames);
				ruleColumnnames.add(query.getRuleColumn());
			}
			if(ruleTableNames.size() == 1){
				lan23TableName = ruleTableNames.iterator().next();
			}else if(ruleTableNames.size() > 1){
				for(String tableName : ruleTableNames){
					usedtableNames += tableName+",";
					
				}
				DataConversionDto dcDto = new DataConversionDto();
				dcDto.setCountyTableNames(usedtableNames);
				Map<String, List<DataConversionDto>> tableColumnMap = dataConversionDAO.loadTableColumnNames(dcDto);
				Map<String, Integer> tableOccurancemap = new HashMap<String, Integer>();
				for (Map.Entry<String, List<DataConversionDto>> pair : tableColumnMap.entrySet()) {
				    String table = pair.getKey();
				    List<DataConversionDto> columns = pair.getValue();
				    List<String> tableColumns = new ArrayList<String>();
				    int columnsCountinTable = 0;
				    for(DataConversionDto dto : columns){
				    	tableColumns.add(dto.getColumnName());
				    }
				    for(String column : ruleColumnnames){
				    	boolean b = tableColumns.contains(column);
				    	if(b) columnsCountinTable = columnsCountinTable+1;
				    }
				   tableOccurancemap.put(table, columnsCountinTable); 
				}
				int maxValueInMap=(Collections.max(tableOccurancemap.values()));  
		        for (Entry<String, Integer> entry : tableOccurancemap.entrySet()) {  
		            if (entry.getValue()==maxValueInMap) {
		            	lan23TableName = entry.getKey();     
		            }
		        }
			}else{
				
			}
		} catch (Exception e) {
			log4j.info("DataConversionServiceImpl - save18_19_22FieldData :: Error :: "+e.getMessage());
		}
		log4j.info("DataConversionServiceImpl - findFieldSpecificLan23tableName :: End :: ");
		return lan23TableName;
	}
	@Override
	public List<LinkedHashMap<String, FnfLanForQC>> FetchDataForQc(String selectQuery,String slectedEdition) {
		// TODO Auto-generated method stub
		return dataConversionDAO.getDataForQc(selectQuery,slectedEdition);
	}
}