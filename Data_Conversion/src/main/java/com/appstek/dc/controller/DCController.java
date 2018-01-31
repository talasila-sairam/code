package com.appstek.dc.controller;

import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.appstek.dc.dbload.Country;
import com.appstek.dc.dbload.County;
import com.appstek.dc.dbload.FieldMappingTable;
import com.appstek.dc.dbload.FnfLanCounties;
import com.appstek.dc.dbload.FnfLanCountries;
import com.appstek.dc.dbload.FnfLanCountyTables;
import com.appstek.dc.dbload.FnfLanDictionary;
import com.appstek.dc.dbload.FnfLanForQC;
import com.appstek.dc.dbload.FnfLanStates;
import com.appstek.dc.dbload.LoginCredentials;
import com.appstek.dc.dbload.RuleQuery;
import com.appstek.dc.dbload.State;
import com.appstek.dc.dbload.Territory;
import com.appstek.dc.dto.CountryStateCountyDto;
import com.appstek.dc.dto.DataConversionDto;
import com.appstek.dc.dto.DataConversionRuleDto;
/*import com.appstek.dc.dto.Field_71_72_73_ExcelDto;*/
import com.appstek.dc.dto.JsonResponse;
import com.appstek.dc.service.DataConversionService;
import com.appstek.dc.util.ApplicationConstants;
import com.appstek.dc.util.JspConstants;
import com.appstek.dc.util.MappingConstants;

/**
 * @author mrao
 *
 */
@Controller
public class DCController {
	@Autowired
	private DataConversionService dataConversionService;

	static final Logger log4j = Logger.getLogger(DCController.class);
	//static List completeTableNames = new ArrayList();

	/*@RequestMapping(value = MappingConstants.LOADALLTABLENAMES)
	public List<String> ListTableData(@RequestParam("county") String countyName){
		log4j.info("DCController - getFieldRules :: Start");
		List<String> countyTableNames = new ArrayList<String>();
		try{
			countyTableNames  = dataConversionService.getTablesNames(countyName);
		} 
		catch (Exception e) { 
			 log4j.info("DCController - getFieldRules :: End");
		}
		 
		return countyTableNames;
	}*/
	
	/**
	 * @author sushanta
	 * This method loads field related rules
	 * @param ruleDto
	 * @param result
	 * @exception
	 * @return List<DataConversionRuleDto>
	 */
	@RequestMapping(value = MappingConstants.LOADFIELDRULES, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<DataConversionRuleDto> loadFieldRules(@RequestBody DataConversionRuleDto ruleDto,BindingResult result) {
		log4j.info("DCController - getFieldRules :: Start");
		List<DataConversionRuleDto> fieldRules = new ArrayList<DataConversionRuleDto>();
		try { 
			fieldRules = dataConversionService.getFieldRules(ruleDto);
			log4j.info("DCController - getFieldRules :: fieldRules size :: "+fieldRules.size());
		 } catch (Exception e) { 
			 log4j.info("DCController - getFieldRules :: End");
		 }
		 
		return fieldRules;
	}
	
	/**
	 * @author sushanta
	 * This method loads pagination related rules
	 * @param ruleDto
	 * @param result
	 * @exception
	 * @return List<DataConversionRuleDto>
	 */
	@RequestMapping(value = MappingConstants.LOADPAGERULES, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<RuleQuery> loadPageRules(@RequestBody DataConversionDto dataConversionDto,BindingResult result) {
		log4j.info("DCController - loadPageRules :: Start");
		List<RuleQuery> pageRules = new ArrayList<RuleQuery>();
		try { 
			pageRules = dataConversionService.loadAllTheRules(dataConversionDto);
			log4j.info("DCController - loadPageRules :: pageRules size :: "+pageRules.size());
		 } catch (Exception e) { 
			 log4j.info("DCController - loadPageRules :: End");
		 }
		 
		return pageRules;
	}

	
	
	/**
	 * @author sushanta
	 * This method load rules according to the perticular field
	 * @param ruleDto
	 * @param result
	 * @exception
	 * @return List<DataConversionRuleDto>
	 */
	@RequestMapping(value = MappingConstants.LOADPERTICULARFIELDRULES, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<DataConversionRuleDto> loadPerticularFieldRules(@RequestBody DataConversionRuleDto ruleDto,BindingResult result) {
		log4j.info("DCController - getPerticularFieldRules :: Start");
		List<DataConversionRuleDto> fieldRules = new ArrayList<DataConversionRuleDto>();
		try { 
			fieldRules = dataConversionService.getPerticularFieldRules(ruleDto);
			log4j.info("DCController - getPerticularFieldRules :: fieldRules size :: "+fieldRules.size());
		 } catch (Exception e) { 
			 log4j.info("DCController - getPerticularFieldRules :: End");
		 }
		 
		return fieldRules;
	}

	/**
	 * @author mrao
	 * This loads all the countries,states and counties
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	//@RequestMapping(MappingConstants.GETCOUNTIES)
	@RequestMapping(value = MappingConstants.GETCOUNTIES , method = RequestMethod.POST)
	public ModelAndView getCounties(HttpServletRequest request, HttpServletResponse response) {
		log4j.info("DCController - getCounties :: Start");
		ModelAndView modelAndView = new ModelAndView();
		List<FnfLanCountries> countries = new ArrayList<FnfLanCountries>();
		try {
			countries = dataConversionService.loadCountries();
			modelAndView.addObject("countries", countries);
			modelAndView.setViewName(JspConstants.GETCOUNTIES);
		} catch (Exception e) {
			log4j.error("DCController - getCounties :: Error " + e.getMessage());
		}
		log4j.info("DCController - getCounties :: End");
		return modelAndView;
	}

	/**
	 * This method loads all the fields(1 to 194) ,table names and rules
	 * 
	 * @param request
	 * @param response
	 * @param dataConversionDto
	 * @return
	 */
	@RequestMapping(MappingConstants.GETCOUNTYTABLES)
	public ModelAndView getCountyTables(HttpServletRequest request, HttpServletResponse response,
			DataConversionDto dataConversionDto) {
		log4j.info("DCController - getCountyTables :: Start"+dataConversionDto.getPageNumber());
		ModelAndView modelAndView = new ModelAndView();
		List<FnfLanCountyTables> countyTableNames = new ArrayList<FnfLanCountyTables>();
		List<FnfLanDictionary> fields = new ArrayList<FnfLanDictionary>();
		List<RuleQuery> rules = new ArrayList<RuleQuery>();
		int ruleCount = 0;
		try {
			// This method is to load all the fields
			dataConversionDto.setCountyTableNames("fnf_lan23");
			fields = dataConversionService.loadAllFields();
			log4j.info("DCController - getCountyTables :: field list "+fields.size());
			// It loads all the table Names
			countyTableNames = dataConversionService.loadTableNames(dataConversionDto);
			List<FieldMappingTable> dependentFields = new ArrayList<FieldMappingTable>();
			//countyTableNames  = dataConversionService.getTablesNames(dataConversionDto.);
			
			// It loads all the rules
			rules = dataConversionService.loadAllTheRules(dataConversionDto);
			int ruleSize=rules.size();
			ruleCount = dataConversionService.getRuleCount(dataConversionDto);
			dependentFields=dataConversionService.getFieldMappingForParentFieds(dataConversionDto.getFieldNumber());
			/*modelAndView.addObject(ApplicationConstants.FIELDS, fields);*/
			String fieldTODisplay=dataConversionDto.getFieldName()+"|"+dataConversionDto.getFieldNumber();
			modelAndView.addObject(ApplicationConstants.COUNTYTABLENAMES, countyTableNames);
			modelAndView.addObject(ApplicationConstants.RULES, rules);
			modelAndView.addObject(ApplicationConstants.RULESIZE,ruleSize);
			modelAndView.addObject(ApplicationConstants.DATACONVERSIONDTO, dataConversionDto);
			modelAndView.setViewName(JspConstants.GETCOUNTYTABLES);
			modelAndView.addObject(ApplicationConstants.RULECOUNT, ruleCount);
			modelAndView.addObject(ApplicationConstants.MAPPINGFIELDS, dependentFields);
			modelAndView.addObject(ApplicationConstants.FIELDTODISPLAY, fieldTODisplay);
		} catch (Exception e) {
			log4j.error("DCController - getCountyTables :: Error " + e.getMessage());
		}
		log4j.info("DCController - getCountyTables :: End");
		return modelAndView;
	}
	


	/**
	 * This method loads column names of a selected tables from the UI
	 * 
	 * @param request
	 * @param response
	 * @param dataConversionDto
	 * @return
	 */
	@RequestMapping(MappingConstants.GETCOUNTYTABLECOLUMNS)
	public ModelAndView getCountyTableColumns(HttpServletRequest request, HttpServletResponse response,
			DataConversionDto dataConversionDto) {
		log4j.info("DCController - getCountyTableColumns :: Start");
		ModelAndView modelAndView = new ModelAndView();
		List<String> columnNames = new ArrayList<String>();
		Map<String, List<DataConversionDto>> tableMapWithColumns = new LinkedHashMap<String, List<DataConversionDto>>();
		RuleQuery rule = new RuleQuery();
		try {
			if (dataConversionDto.getCountyTableNames() != null && dataConversionDto.getCountyTableNames() != "") {
				tableMapWithColumns = dataConversionService.loadTableColumnNames(dataConversionDto);
			}else if (dataConversionDto.getRuleQueryId() != null && dataConversionDto.getRuleQueryId() > 0 ) {
				DataConversionRuleDto ruleDto = new DataConversionRuleDto();
				ruleDto.setRuleQueryId(dataConversionDto.getRuleQueryId());
				rule = dataConversionService.loadRule(ruleDto);
			}
			log4j.error("DCController - getCountyTableColumns :: Error " + rule.getRuleQueryId());
			modelAndView.addObject(ApplicationConstants.COUNTYTABLECOLUMNNAMES, columnNames);
			modelAndView.addObject(ApplicationConstants.TABLEMAPWITHCOLUMNS, tableMapWithColumns);
			modelAndView.addObject(ApplicationConstants.RULE, rule);
			modelAndView.setViewName(JspConstants.GETCOUNTYTABLECOLUMNS);
		} catch (Exception e) {
			e.printStackTrace();
			log4j.error("DCController - getCountyTableColumns :: Error " + e.getMessage());
		}
		log4j.info("DCController - getCountyTableColumns :: End");
		return modelAndView;
	}

	
	/**
	 * @author sushanta
	 * This method process the select queries.
	 * @param inputQuery
	 * @param ruleQuery
	 * @exception Exception
	 * @return json (List<LinkedHashMap<String, Object>>)
	 * 
	 *
	 */
	@RequestMapping(value = MappingConstants.EXECUTEMULTIPLEQUERY)
	public @ResponseBody List<List<LinkedHashMap<String, Object>>> executeMultipleQuery(
			@RequestParam("input_query") String inputQuery, @RequestParam("rule_query") String ruleQuery,
			@RequestParam("ruleQuery71") String ruleQuery71, @RequestParam("ruleQuery72") String ruleQuery72, @RequestParam("ruleQuery73") String ruleQuery73) {
		log4j.info("DCController - executeMultipleQuery :: Start");
		String specialChars[] =  new String[]{"&","+","#"};
		String codeNumbersForSplChars[]= new String[]{"%26","%2B","%23"};
		for(int sp = 0 ; sp < specialChars.length;sp++){
			if(inputQuery != null && inputQuery.length() > 7){
				inputQuery.replaceAll(codeNumbersForSplChars[sp], specialChars[sp]);
			}
			if(ruleQuery != null && ruleQuery.length() > 7){
				ruleQuery.replaceAll(codeNumbersForSplChars[sp], specialChars[sp]);
			}
			if(ruleQuery71 != null && ruleQuery71.length() > 7){
				ruleQuery71.replaceAll(codeNumbersForSplChars[sp], specialChars[sp]);
			}
			if(ruleQuery72 != null && ruleQuery72.length() > 7) {
				ruleQuery72.replaceAll(codeNumbersForSplChars[sp], specialChars[sp]);
			}
			if(ruleQuery73 != null && ruleQuery73.length() > 7) {
				ruleQuery73.replaceAll(codeNumbersForSplChars[sp], specialChars[sp]);
			}
		}
		LinkedHashMap<String, Object> errorMap = new LinkedHashMap<>();
		log4j.info("DCController - executeMultipleQuery :: Start");
		log4j.info("DCController - executeMultipleQuery :: inputQuery :: " + inputQuery);
		log4j.info("DCController - executeMultipleQuery :: ruleQuery :: " + ruleQuery);
		log4j.info("DCController - executeMultipleQuery :: ruleQuery71 :: " + ruleQuery71);
		log4j.info("DCController - executeMultipleQuery :: ruleQuery72 :: " + ruleQuery72);
		log4j.info("DCController - executeMultipleQuery :: ruleQuery73 :: " + ruleQuery73);
		List<List<LinkedHashMap<String, Object>>> dataList = new ArrayList<List<LinkedHashMap<String, Object>>>();
		List<LinkedHashMap<String, Object>> inputQueryDataList = null;
		try {
			inputQueryDataList = dataConversionService.getQueryData(inputQuery);
			log4j.info("DCController - executeMultipleQuery :: inputQueryDataList Size :: " + inputQueryDataList.size());
			if(inputQueryDataList.size() > 0){
				dataList.add(inputQueryDataList);
			}else{
				errorMap.put("error", "No data found.");
			    List errlist = new ArrayList<>();
			    errlist.add(errorMap);
				dataList.add(errlist);
			}
			
			if(ruleQuery != null && ruleQuery.length() > 7){
				List<LinkedHashMap<String, Object>> ruleQueryDataList = dataConversionService.getQueryData(ruleQuery);
				log4j.info("DCController - executeMultipleQuery :: ruleQueryDataList Size :: " + ruleQueryDataList.size());
				if(ruleQueryDataList.size() > 0){
					dataList.add(ruleQueryDataList);
				}else{
					errorMap.put("error", "No data found.");
				    List errlist = new ArrayList<>();
				    errlist.add(errorMap);
					dataList.add(errlist);
				}
			}
			if(ruleQuery71 != null && ruleQuery71.length() > 7){
				List<LinkedHashMap<String, Object>> ruleQuery71DataList = dataConversionService.getQueryData(ruleQuery71);
				log4j.info("DCController - executeMultipleQuery :: ruleQuery71DataList Size :: " + ruleQuery71DataList.size());
				if(ruleQuery71DataList.size() > 0){
					dataList.add(ruleQuery71DataList);
				}else{
					errorMap.put("error", "No data found.");
				    List errlist = new ArrayList<>();
				    errlist.add(errorMap);
					dataList.add(errlist);
				}
				
			}
			if(ruleQuery72 != null && ruleQuery72.length() > 7) {
				List<LinkedHashMap<String, Object>> ruleQuery72DataList = dataConversionService.getQueryData(ruleQuery72);
				log4j.info("DCController - executeMultipleQuery :: ruleQuery72DataList Size :: " + ruleQuery72DataList.size());
				if(ruleQuery72DataList.size() > 0){
					dataList.add(ruleQuery72DataList);
				}else{
					errorMap.put("error", "No data found.");
				    List errlist = new ArrayList<>();
				    errlist.add(errorMap);
					dataList.add(errlist);
				}
				
			}
			if(ruleQuery73 != null && ruleQuery73.length() > 7) {
				List<LinkedHashMap<String, Object>> ruleQuery73DataList = dataConversionService.getQueryData(ruleQuery73);
				log4j.info("DCController - executeMultipleQuery :: ruleQuery73DataList Size :: " + ruleQuery73DataList.size());
				if(ruleQuery73DataList.size() > 0){
					dataList.add(ruleQuery73DataList);
				}else{
					errorMap.put("error", "No data found.");
				    List errlist = new ArrayList<>();
				    errlist.add(errorMap);
					dataList.add(errlist);
				}
				
			}
			
			log4j.info("DCController - executeMultipleQuery :: Data List Size :: " + dataList.size());
			
		} catch (Exception e) {
			
			String[] exp = e.getMessage().split(":");
		    errorMap.put("error", exp[1]);
		    List list = new ArrayList<>();
		    list.add(errorMap);
			dataList.add(list);
			log4j.error("DCController - executeMultipleQuery :: Error " + e.getMessage());
		}
		log4j.info("DCController - executeMultipleQuery :: End");
		return dataList;
	}
	
	/**
	 * This method is to execute base query
	 * @param inputQuery
	 * @return
	 */
	@RequestMapping(value = MappingConstants.EXECUTEQUERY)
	public @ResponseBody List<LinkedHashMap<String, Object>> executeQuery(
			@RequestParam("input_query") String inputQuery) {
		String specialChars[] =  new String[]{"&","+","#"};
		String codeNumbersForSplChars[]= new String[]{"%26","%2B","%23"};
		for(int sp = 0 ; sp < specialChars.length;sp++){
			inputQuery.replaceAll(codeNumbersForSplChars[sp], specialChars[sp]);
		}
		LinkedHashMap<String, Object> errorMap = new LinkedHashMap<>();
		log4j.info("DCController - executeQuery :: Start :: " + inputQuery);
		List<LinkedHashMap<String, Object>> dataList = new ArrayList<LinkedHashMap<String, Object>>();
		try {
			dataList = dataConversionService.getQueryData(inputQuery);
			log4j.info("DCController - executeQuery :: Data List Size :: " + dataList.size());
			if(dataList.size() <= 0){
				errorMap.put("error", "No data found.");
			    dataList.add(errorMap);
			}
		} catch (Exception e) {
			String exp [];
			String msg = "";
			if(e.getMessage().contains(":")){
				exp = e.getMessage().split(":");
				errorMap.put("error", exp[1]);
				dataList.add(errorMap);
			}
			else{
				msg = e.getMessage();
			    errorMap.put("error", msg);
				dataList.add(errorMap);	
			}
			log4j.error("DCController - executeQuery :: Error " + e.getMessage());
		}
		log4j.info("DCController - executeQuery :: End");
		return dataList;
	}
	
	/**
	 * @author sushanta
	 * This method process the insert/update/delete query.
	 * @param inputQuery
	 * @exception Exception
	 * @return json value
	 * @deprecated
	 * 
	 *
	 */
	@RequestMapping(value = MappingConstants.EXECUTEUPDATE)
	public @ResponseBody int executeUpdate(@RequestParam("input_query") String inputQuery) {
		log4j.info("DCController - executeUpdate :: Start :: " + inputQuery);
		int resultCount = 0;
		try {
			resultCount = dataConversionService.executeUpdate(inputQuery);
			log4j.info("DCController - executeUpdate :: Result Count :: " + resultCount);
		} catch (Exception e) {
			log4j.error("DCController - executeUpdate :: Error " + e.getMessage());
		}
		log4j.info("DCController - executeUpdate :: End");
		return resultCount;
	}

	/**
	 * @author sushanta
	 * This method saves the rule into the database.
	 * @param request
	 * @param response
	 * @param DataConversionRuleDto
	 * @exception Exception
	 * @return ModelAndView
	 * 
	 *
	 */
	@RequestMapping(value = MappingConstants.SAVERULE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JsonResponse saveRule(@RequestBody DataConversionRuleDto dataConversionRuleDto,BindingResult result,HttpServletRequest request) {
		log4j.info("DCController - saveRule :: Start :: " );
		JsonResponse jsonResponse = new JsonResponse();
		try {
			/*if(dataConversionService.isExistsRuleName(dataConversionRuleDto.getRuleName())){
				jsonResponse.setStatus("EXISTS");
			}else{*/
			String fileNamePath = request.getSession().getServletContext().getRealPath("");
			dataConversionRuleDto.setRuleExcelFilePath(fileNamePath);
				boolean flag = dataConversionService.saveRule(dataConversionRuleDto);
				if(flag){
					//String fileName = "ruleExcel_71_73.xlsx";
					//String fileNamePath = "F:/ruleDataSheet_71_73/"+fileName;
					//String fileNamePath = request.getSession().getServletContext().getRealPath("/fieldRuleDataSheet/");
					//File ruleExcel = new File(realPath);
					//FileUtil.writeExcelFile(fileNamePath,dataConversionRuleDto.getRowDataArr_71_72_73());
					jsonResponse.setStatus("SUCCESS");
				}else{
					jsonResponse.setStatus("FAILED");
				}
			/*}*/
		} catch (Exception e) {
			log4j.error("DCController - saveRule :: Error " + e.getMessage());
		}
		log4j.info("DCController - saveRule :: End" + jsonResponse.getStatus());
		return jsonResponse;
	}
	
	
	/**
	 * @param dataConversionRuleDto
	 */
	@RequestMapping(value = MappingConstants.DELETERULE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JsonResponse deleteRule(@RequestBody DataConversionRuleDto dataConversionRuleDto, BindingResult result){
		log4j.info("DCController - deleteRule :: Start ");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			boolean flag = dataConversionService.deleteRule(dataConversionRuleDto);
			if(flag)
				jsonResponse.setStatus("SUCCESS");
		} catch (Exception e) {
			log4j.error("DCController - deleteRule :: Error " + e.getMessage());
		}
		log4j.info("DCController - deleteRule :: End");
		return jsonResponse;
	}
	@RequestMapping(value = MappingConstants.EXPORTDATAQUERY)
	public @ResponseBody List<LinkedHashMap<String, Object>> executeExportDataQuery() {
		List<LinkedHashMap<String, Object>> dataList = new ArrayList<LinkedHashMap<String, Object>>();
		try {
			String inputQuery="SELECT * FROM  fnf_lan23_demo";
			dataList = dataConversionService.getQueryData(inputQuery);
			//log4j.info("DCController - executeQuery :: Data List Size :: " + dataList.size());
		} catch (Exception e) {
			log4j.error("DCController - executeQuery :: Error " + e.getMessage());
		}
		log4j.info("DCController - executeQuery :: End");
		return dataList;
	}
//	@RequestMapping(value = MappingConstants.EXPORTDATAXL)
/*	@RequestMapping(value = MappingConstants.EXPORTDATAXL)

	public void executeExportDataXl() {
		log4j.info("DCController - executeExportDataXl :: Start");
		List<LinkedHashMap<String, Object>> dataList = new ArrayList<LinkedHashMap<String, Object>>();
		try {
			dataConversionService.getQueryDataXl();
		} catch (Exception e) {
			log4j.error("DCController - executeExportDataXl :: Error " + e.getMessage());
		}
		log4j.info("DCController - executeQuery :: End");
		
	}*/
	
/*	@RequestMapping(MappingConstants.GETDEFAULTFLAG)
	public @ResponseBody List<String> exicuteQueryForDefaultFlag(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("selectedValue") String selectedValue) {
		log4j.info("DCController - getDefaultValue :: Start : " + selectedValue);
		List<String> defaultValue = null;
		
		try {
			defaultValue = dataConversionService.getDefaultFlag(selectedValue);
			
		} catch (Exception e) {
			log4j.error("DCController - getDefaultValue :: Error " + e.getMessage());
		}
		log4j.info("DCController - getDefaultValue :: End");
		return defaultValue;
	}*/
	
	/**
	 * @author sushanta
	 * This method performs edit rule actions.
	 * @param result
	 * @param DataConversionRuleDto
	 * @exception Exception
	 * @return JsonResponse
	 * 
	 *
	 */
	@RequestMapping(value = MappingConstants.EDITRULE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JsonResponse editRule(@RequestBody DataConversionRuleDto dataConversionRuleDto, BindingResult result){
		log4j.info("DCController - editRule :: Start ");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			boolean flag = dataConversionService.editRule(dataConversionRuleDto);
			if(flag)
				jsonResponse.setStatus("SUCCESS");
		} catch (Exception e) {
			log4j.error("DCController - editRule :: Error " + e.getMessage());
		}
		log4j.info("DCController - editRule :: End");
		return jsonResponse;
	}
	@RequestMapping(value = MappingConstants.COUNTYFIELDS)
	public @ResponseBody List<LinkedHashMap<String, Object>> getCountyFields() {
		List<LinkedHashMap<String, Object>> dataList = new ArrayList<LinkedHashMap<String, Object>>();
		try {
			String inputQuery="SELECT `FIELD_NAME` FROM `fnf_lan_dictionary`";
			dataList = dataConversionService.getQueryData(inputQuery);
			log4j.info("DCController - executeQuery :: Data List Size :: " + dataList.size());
		} catch (Exception e) {
			log4j.error("DCController - executeQuery :: Error " + e.getMessage());
		}
		log4j.info("DCController - executeQuery :: End");
		return dataList;
	}
	
	/**
	 * @author Maheshvarun
	 * This method is used to get the tables join condition and it's relation
	 * @param tableNamesSelected
	 * @return List
	 */
	@RequestMapping(value = MappingConstants.EXECUTEQUERYTOGETJOINS,method = RequestMethod.POST)
	public @ResponseBody List<List<LinkedHashMap<String, Object>>> executeQueryToGetJoins(
			@RequestParam(value="tableNamesSelected") String tableNamesSelected) {
		log4j.info("DCController - executeQueryToGetJoins :: Start :: tableNames " + tableNamesSelected);
		List<List<LinkedHashMap<String, Object>>> defaultValue = null;
		@SuppressWarnings("unused")
		JSONObject jSONObject = new JSONObject();
		@SuppressWarnings("unused")
		String errorMessage = "";
		try {
			defaultValue = dataConversionService.getResultOfJoinCondition(tableNamesSelected);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			log4j.error("DCController - executeQueryToGetJoins :: Error " + e.getMessage());
		}
		log4j.info("DCController - executeQueryToGetJoins :: End");
		
		return defaultValue;
	}
	
	@RequestMapping(value = MappingConstants.SAVEFIELDDATA,method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody JsonResponse  saveFieldData(@RequestBody DataConversionRuleDto dataConversionRuleDto, BindingResult result){
		log4j.info("DCController - saveFieldData :: Start :: ");
		JsonResponse jsonResponse = new JsonResponse();
		try{
			boolean b = dataConversionService.saveFieldData(dataConversionRuleDto);
			if(b)
				jsonResponse.setStatus("SUCCESS");
			else
				jsonResponse.setStatus("FAILED");
		}catch(Exception e){
			log4j.error("DCController - saveFieldData :: Error " + e.getMessage());
		}
		log4j.info("DCController - saveFieldData :: END :: ");
		return jsonResponse;
	}
	
	@RequestMapping(value = MappingConstants.SAVEDATA,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody String  saveRuleData(@RequestParam("countyName") String countyName,@RequestParam("stateName") String stateName,@RequestParam("edition") String edition) 
	{
		log4j.info("DCController - saveRuleData :: Start :: ");
		System.out.println("countyName="+countyName);
		System.out.println("stateName="+stateName);
		String message = "";
		JSONObject jSONObject = new JSONObject();
		ModelAndView modelAndView = new ModelAndView();
		try
		{
			message = dataConversionService.saveDataForLan23(countyName,stateName,edition);
			//message = "{'response':'your string value'}";
		}catch(Exception e)
		{
			e.printStackTrace();
			log4j.error("DCController - saveRuleData :: Error " + e.getMessage());
		}
		log4j.info("DCController - saveRuleData :: END :: ");
		//modelAndView.addObject("MESSAGE","DATA SAVE SUCCESSFULLY");
		return JSONObject.quote(message);
	}
	
/*	*//**
	 * Chandu Code Start Here
	 *//*
	
/*	@SuppressWarnings("unchecked")
	@RequestMapping(MappingConstants.countyNmae)
	public @ResponseBody ArrayList<Object> FetchCountiesUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stateSelected") String stateSelected) {
		ArrayList<Object> counties= null;
		try 
		{
			SessionFactory sessionFactory = null;
			String selectQuery="SELECT  COUNTY_NAME FROM fnf_lan_counties where State_fips_code="+stateSelected;
			counties=dataConversionService.FetchCounties(selectQuery);
		} 
		catch (Exception e) {
			log4j.error("DCController - counties :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return counties;

	
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(MappingConstants.userNmae)
	public @ResponseBody ArrayList<Object> FetchUserNameUser(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Object> users= null;
		try 
		{
			SessionFactory sessionFactory = null;
			String selectQuery="SELECT  USERNAME FROM LOGIN_CREDENTIALS";
			users=dataConversionService.Fetchusers(selectQuery);
		} 
		catch (Exception e) {
			log4j.error("DCController - users :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return users;

	
	}
	@RequestMapping(MappingConstants.saveUserTasks)
	public  void userInsertData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userName") String userName,@RequestParam("county") String county,@RequestParam("states") String states,@RequestParam("editionYear") String editionYear,@RequestParam("assignedTime") String assignedTime,@RequestParam("noOfTables") String countyStateReference) {
		try 
		{
			log4j.info("DCController - save  :: Start");
			dataConversionService.saveUserTaskmanagement(userName,county,states,editionYear,assignedTime,countyStateReference);
		} 
		catch (Exception e) {
			log4j.error("DCController - users :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");

	
	}
	@RequestMapping(MappingConstants.ShowAssignedUsers)
	public @ResponseBody List<LinkedHashMap<String, Object>> userInsertData(HttpServletRequest request, HttpServletResponse response
			,@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate) {
		List<LinkedHashMap<String, Object>> userDetails=null;
		try 
		{
			log4j.info("DCController - save  :: Start");
			userDetails=dataConversionService.showUserTaskmanagement(fromDate,toDate);
		} 
		catch (Exception e) {
			log4j.error("DCController - users :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return userDetails;
	
	}
	@RequestMapping(MappingConstants.loginDetails)
	public void validateUser(HttpServletRequest request, HttpServletResponse response) {
		LinkedHashMap<String, Object> userDetails=null;
		String username = request.getParameter("loginname");
		String password = request.getParameter("password");
		try 
		{
			log4j.info("DCController - save  :: Start");
			dataConversionService.showValidateUser(request,response,username,password);
		} 
		catch (Exception e) {
			log4j.error("DCController - users :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
	
	}
	@RequestMapping(MappingConstants.logoutDetails)
	public void logoutpage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession UserSession = request.getSession(true);
		String loginMessage="";
		try 
		{
			log4j.info("DCController - louout  :: Start");
			loginMessage = "User logged out successfully.";
			UserSession.setAttribute("message", loginMessage);
			response.sendRedirect("LoginPage.jsp");
		} 
		catch (Exception e) {
			log4j.error("DCController - logout :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
	
	}
	@RequestMapping(MappingConstants.userReportFetch)
	public @ResponseBody List<LinkedHashMap<String, Object>> userFetchReport(HttpServletRequest request, HttpServletResponse response,@RequestParam("userLogged") String userName) {
		List<LinkedHashMap<String, Object>> userDetails=null;
		try 
		{
			log4j.info("DCController - save  :: Start");
			userDetails=dataConversionService.UserReportfetchRecords(request,response,userName);
		} 
		catch (Exception e) {
			log4j.error("DCController - logout :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return userDetails;
	}
	@RequestMapping(MappingConstants.userInsertStatus)
	public void userInsertStauts(HttpServletRequest request, HttpServletResponse response,@RequestParam("comments") String comments,@RequestParam("currentStatus") String currentStatus,
			@RequestParam("selectedUser") String selectedUser,@RequestParam("signOffStatus") String signOffStatus,@RequestParam("county_state") String countyStateReference) {
		List<LinkedHashMap<String, Object>> userDetails=null;
		try 
		{
			log4j.info("DCController - save  :: Start");
			dataConversionService.UserReportInsertRecords(request,response,comments,currentStatus,selectedUser,signOffStatus,countyStateReference);
		} 
		catch (Exception e) {
			log4j.error("DCController - logout :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		
	}
	@RequestMapping(MappingConstants.stateLovs)
	public @ResponseBody ArrayList<Object> FetchStatesOfCounty(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Object> states= null;
		try 
		{
			SessionFactory sessionFactory = null;
			String selectQuery="SELECT FIPS_CODE, STATE_CODE FROM fnf_lan_states";
			states=dataConversionService.FetchStates(selectQuery);
		} 
		catch (Exception e) {
			log4j.error("DCController - users :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return states;

	
	}*/
	/**
	 * chandu code starts here
	 * @param request
	 * @param response
	 * @param stateSelected
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(MappingConstants.countyNmae)
	public @ResponseBody ArrayList<Object> FetchCountiesUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stateSelected") String stateSelected) {
		ArrayList<Object> counties= null;
		try 
		{
			SessionFactory sessionFactory = null;
			String selectQuery="SELECT  COUNTY_NAME FROM fnf_lan_counties where State_fips_code="+stateSelected;
			counties=dataConversionService.FetchCounties(selectQuery);
		} 
		catch (Exception e) {
			log4j.error("DCController - counties :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return counties;

	
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(MappingConstants.userNmae)
	public @ResponseBody ArrayList<Object> FetchUserNameUser(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Object> users= null;
		try 
		{
			SessionFactory sessionFactory = null;
			String selectQuery="SELECT  USER_NAME FROM LOGIN_CREDENTIALS";
			users=dataConversionService.Fetchusers(selectQuery);
		} 
		catch (Exception e) {
			log4j.error("DCController - users :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return users;

	
	}
	@RequestMapping(MappingConstants.saveUserTasks)
	public  void userInsertData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userName") String userName,@RequestParam("county") String county,@RequestParam("states") String states,@RequestParam("editionYear") String editionYear,@RequestParam("assignedTime") String assignedTime,@RequestParam("noOfTables") String countyStateReference) {
		try 
		{
			log4j.info("DCController - save  :: Start");
			dataConversionService.saveUserTaskmanagement(userName,county,states,editionYear,assignedTime,countyStateReference);
		} 
		catch (Exception e) {
			log4j.error("DCController - users :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");

	
	}
	@RequestMapping(MappingConstants.ShowAssignedUsers)
	public @ResponseBody List<LinkedHashMap<String, Object>> userInsertData1(HttpServletRequest request, HttpServletResponse response
			,@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate) {
		List<LinkedHashMap<String, Object>> userDetails=null;
		try 
		{
			log4j.info("DCController - save  :: Start");
			userDetails=dataConversionService.showUserTaskmanagement(fromDate,toDate);
		} 
		catch (Exception e) {
			log4j.error("DCController - users :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return userDetails;
	
	}
	@RequestMapping(MappingConstants.loginDetails)
	public void validateUser(HttpServletRequest request, HttpServletResponse response) {
		LinkedHashMap<String, Object> userDetails=null;
		String username = request.getParameter("loginname");
		String password = request.getParameter("password");
		try 
		{
			String path = request.getServletContext().getRealPath("");
			log4j.info("DCController - save  :: Start");
			dataConversionService.showValidateUser(request,response,username,password);
			File fil=new File(path+"\\DataConersionUploadxlFiles.xlsx");
		//	readExcelFile(path,fil);
		} 
		catch (Exception e) {
			log4j.error("DCController - users :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
	
	}
/*	private static void readExcelFile(String realpath,File file) {
		// TODO Auto-generated method stub
		try{
			XWPFDocument document= new XWPFDocument();
			XWPFTable table = document.createTable();
			FileOutputStream out = new FileOutputStream(new File(realpath+"\\create_table.docx"));
			 FileInputStream inputStream = new FileInputStream(file);
			 XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			 for(int i=0;workbook.getNumberOfSheets()>i;i++){
		        XSSFSheet firstSheet = workbook.getSheetAt(i);
		        System.out.println( workbook.getSheetAt(i).getSheetName());
		        Iterator<Row> iterator = firstSheet.iterator();
		        int rowCount=0; 
		        boolean tillRowsReadFlag=true;
		        while(iterator.hasNext()){
		        	Row nextRow = iterator.next();
		            Iterator<Cell> cellIterator = nextRow.cellIterator();
		            while (cellIterator.hasNext()) {
		            	int cellCount=0;
		            	Cell cell = cellIterator.next();
		            	if(cell.getStringCellValue().equalsIgnoreCase("Rule Id:996")){
		            		while(tillRowsReadFlag){
		            			
			            		Row  getRowsForDoc=firstSheet.getRow(rowCount+1);
			            		Iterator<Cell> cellIterator1 = getRowsForDoc.cellIterator();
			            		while(cellIterator1.hasNext()){
			            			Cell cellVlaues = cellIterator1.next();
			            			if(cellVlaues.getStringCellValue().contains(MappingConstants.ruleIdInExcel)){
			            				tillRowsReadFlag=false;
			            				break;
			            			}
			            			if(cellCount==0){
			            			XWPFTableRow tableRowOne = table.getRow(cellCount);
			            			 tableRowOne.getCell(cellCount).setText(cellVlaues.getStringCellValue());
			            		     tableRowOne.addNewTableCell().setText(cellVlaues.getStringCellValue());
			            		     tableRowOne.addNewTableCell().setText(cellVlaues.getStringCellValue());
			            			// System.out.println(cellVlaues.getStringCellValue());
			            			}
			            			else{
			            				XWPFTableRow tableRowThree = table.createRow();
			            				tableRowThree.getCell(0).setText(cellVlaues.getStringCellValue());
			            			  
			            			}
			            		}
			            		cellCount++;
			            		rowCount++;
		            		}
		            	}
		            }
		            }
			 	}
			  document.write(out);
		      out.close();

		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}*/
	@RequestMapping(MappingConstants.logoutDetails)
	public void logoutpage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession UserSession = request.getSession(true);
		String loginMessage="";
		try 
		{
			log4j.info("DCController - louout  :: Start");
			loginMessage = "User logged out successfully.";
			UserSession.setAttribute("message", loginMessage);
			response.sendRedirect("LoginPage.jsp");
		} 
		catch (Exception e) {
			log4j.error("DCController - logout :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
	
	}
	@RequestMapping(MappingConstants.userReportFetch)
	public @ResponseBody List<LinkedHashMap<String, Object>> userFetchReport(HttpServletRequest request, HttpServletResponse response,@RequestParam("userLogged") String userName) {
		List<LinkedHashMap<String, Object>> userDetails=null;
		try 
		{
			log4j.info("DCController - save  :: Start");
			userDetails=dataConversionService.UserReportfetchRecords(request,response,userName);
		} 
		catch (Exception e) {
			log4j.error("DCController - logout :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return userDetails;
	}
	@RequestMapping(MappingConstants.userInsertStatus)
	public void userInsertStauts(HttpServletRequest request, HttpServletResponse response,@RequestParam("comments") String comments,@RequestParam("currentStatus") String currentStatus,
			@RequestParam("selectedUser") String selectedUser,@RequestParam("signOffStatus") String signOffStatus,@RequestParam("county_state") String countyStateReference) {
		List<LinkedHashMap<String, Object>> userDetails=null;
		try 
		{
			log4j.info("DCController - save  :: Start");
			dataConversionService.UserReportInsertRecords(request,response,comments,currentStatus,selectedUser,signOffStatus,countyStateReference);
		} 
		catch (Exception e) {
			log4j.error("DCController - logout :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		
	}
	@RequestMapping(MappingConstants.stateLovs)
	public @ResponseBody ArrayList<Object> FetchStatesOfCounty(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Object> states= null;
		try 
		{
			SessionFactory sessionFactory = null;
			String selectQuery="SELECT FIPS_CODE, STATE_CODE FROM fnf_lan_states";
			states=dataConversionService.FetchStates(selectQuery);
		} 
		catch (Exception e) {
			log4j.error("DCController - users :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return states;

	
	}
	@RequestMapping(MappingConstants.GettingNoTablesPerCounty)
	public @ResponseBody int FetchNoOfTablesPerCounty(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stateCountyEditionYear") String stateCountyEditionYear) {
		int totalNoOfTables = 0;
		try 
		{
			totalNoOfTables=dataConversionService.FetchNoOfTablesPerCounty(stateCountyEditionYear);
		} 
		catch (Exception e) {
			log4j.error("DCController - users :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return totalNoOfTables;

	
	}
	/**
	 * chandu code ends here starts here 
	 */
	/**
	 * chandu validation code
	 */
	@RequestMapping(MappingConstants.saveForFields)
	public @ResponseBody List<Object> saveSelectNumberForFields(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("fieldNumber") String field, @RequestParam("county") String county) {
		log4j.info("DCController - save for fields :: Start");
		List<Object> saveQueryData= null;
		try 
		{
			SessionFactory sessionFactory = null;
			String inputQuery="select count(RULE_NAME) from rule_master where FIELD_NUMBER = "+field+" and rule_level = (select territory_id from territory where TERRITORY_NAME ='"+county+"')";
			//String inputQuery="SELECT * FROM Test.dbo.rule_query where RULE_ID in (select RULE_ID from Test.dbo.rule_master where FIELD_NUMBER ="+field+" and rule_level = (select territory_id from Test.dbo.territory where TERRITORY_NAME ='"+county+"'))";
			saveQueryData=dataConversionService.saveSelectNumber(inputQuery);
		} 
		catch (Exception e) {
			log4j.error("DCController - save for fields :: Error " + e.getMessage());
		}
		log4j.info("DCController - save for fields :: End");
		return saveQueryData;
	}	
	
	/**
	 * @author sushanta
	 * This method gets the fields and territory information from the database.
	 * @exception Exception
	 * @return DataConversionDto
	 * 
	 *
	 */
	@RequestMapping(value = MappingConstants.GetFieldsWithCountries, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataConversionDto getFieldsWithCountries(@RequestParam("ruleQueryId") int ruleQueryId) {
		log4j.info("DCController - getFieldsWithCountries :: Start :: "+ruleQueryId);
		DataConversionDto dataConversionDto = new DataConversionDto();
		List<FnfLanDictionary> fields = new ArrayList<FnfLanDictionary>();
		List<FnfLanCountries> countries = new ArrayList<FnfLanCountries>();
		List<CountryStateCountyDto> countryList = new ArrayList<CountryStateCountyDto>();
		RuleQuery rule = new RuleQuery();
		 
		try {
			if (ruleQueryId > 0) {
				DataConversionRuleDto ruleDto = new DataConversionRuleDto();
				ruleDto.setRuleQueryId(ruleQueryId);
				rule = dataConversionService.loadRule(ruleDto);
				dataConversionDto.setRule(rule);
			}
			fields = dataConversionService.loadAllFields();
			dataConversionDto.setFieldList(fields);
			
			countries = dataConversionService.getAllCountries();
			for(FnfLanCountries country : countries){
				CountryStateCountyDto dto = new CountryStateCountyDto();
				dto.setCountryCode(country.getCountryCode());
				dto.setCountryName(country.getCountryName());
				countryList.add(dto);
			}
			dataConversionDto.setCountryList(countryList);
		} catch (Exception e) {
			log4j.error("DCController - getFieldsWithCountries :: Error " + e.getMessage());
			e.printStackTrace();
		}
		log4j.info("DCController - getFieldsWithCountries :: End" + dataConversionDto.getFieldList().size());
		return dataConversionDto;
	}
	/**
	 * @author sushanta
	 * This method gets the fields and territory information from the database.
	 * @exception Exception
	 * @return DataConversionDto
	 * 
	 *
	 */
	@RequestMapping(value = MappingConstants.GetStatesForCountry, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CountryStateCountyDto> getStatesForCountry(@RequestBody DataConversionDto dto, BindingResult result,HttpServletRequest req,HttpServletResponse resp) {
		log4j.info("DCController - getStatesForCountry :: Start :: " );
		List<CountryStateCountyDto> stateList = new ArrayList<CountryStateCountyDto>();
		List<FnfLanStates> states = new ArrayList<FnfLanStates>();
		try {
			states = dataConversionService.getStatesForCountry(dto);
			for(FnfLanStates state : states){
				CountryStateCountyDto countryStateCountyDto = new CountryStateCountyDto();
				countryStateCountyDto.setFipsCode(state.getFipsCode());
				countryStateCountyDto.setStateCode(state.getStateCode());
				countryStateCountyDto.setStateName(state.getStateName());
				stateList.add(countryStateCountyDto);
			}
		} catch (Exception e) {
			log4j.error("DCController - getStatesForCountry :: Error " + e.getMessage());
		}
		log4j.info("DCController - getStatesForCountry :: End" + stateList.size());
		return stateList;
	}
	/**
	 * @author sushanta
	 * This method gets the fields and territory information from the database.
	 * @exception Exception
	 * @return DataConversionDto
	 * 
	 *
	 */
	@RequestMapping(value = MappingConstants.GetCountiesForState, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CountryStateCountyDto> getCountiesForState(@RequestBody DataConversionDto dto, BindingResult result, HttpServletRequest req, HttpServletResponse resp) {
		log4j.info("DCController - getCountiesForState :: Start :: " );
		List<CountryStateCountyDto> countyList = new ArrayList<CountryStateCountyDto>();
		List<FnfLanCounties> counties = new ArrayList<FnfLanCounties>();
		try {
			counties = dataConversionService.getCountiesForState(dto);
			for(FnfLanCounties county : counties){
				CountryStateCountyDto countryStateCountyDto = new CountryStateCountyDto();
				countryStateCountyDto.setCountyFipsCode(county.getCountyFipsCode());
				countryStateCountyDto.setCountyName(county.getCountyName());
				countyList.add(countryStateCountyDto);
			}
		} catch (Exception e) {
			log4j.error("DCController - getCountiesForState :: Error " + e.getMessage());
		}
		log4j.info("DCController - getCountiesForState :: End" + countyList.size());
		return countyList;
	}
	
	@RequestMapping(value = MappingConstants.GetTableNamesForJoin,method = RequestMethod.POST)
	public @ResponseBody List<List<LinkedHashMap<String, Object>>> getTablesNamesForJoins(@RequestParam(value="tableData") String tableData) {
		log4j.info("DataConversionDAOImpl - getTablesNames :: Start : ");
		
		
		List<List<LinkedHashMap<String, Object>>> tablesValue = null;
		String errorMessage = "";
		try {
			tablesValue = dataConversionService.getTableInformation(tableData);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			log4j.error("DCController - getTablesNames :: Error " + e.getMessage());
		}
		
		log4j.info("DataConversionDAOImpl - getTablesNames :: End : ");
		return tablesValue;
	
	}

	
	@RequestMapping(MappingConstants.GETCOUNTYTABLECOLUMNSFORJOINS)
	public @ResponseBody Map<String, List<DataConversionDto>> getCountyTableColumnsForJoins(HttpServletRequest request, HttpServletResponse response,DataConversionDto dataConversionDto) {
		log4j.info("DCController - getCountyTableColumns :: Start");
		
		Map<String, List<DataConversionDto>> tableMapWithColumns = new LinkedHashMap<String, List<DataConversionDto>>();
		
		try {
			if (dataConversionDto.getCountyTableNames() != null && dataConversionDto.getCountyTableNames() != "") {
				tableMapWithColumns = dataConversionService.loadTableColumnNames(dataConversionDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log4j.error("DCController - getCountyTableColumns :: Error " + e.getMessage());
		}
		log4j.info("DCController - getCountyTableColumns :: End");
		return tableMapWithColumns;
	}
	
	
	@SuppressWarnings("null")
	@RequestMapping(value = MappingConstants.COUNTOFJOINCONDITION,method = RequestMethod.POST)
	public @ResponseBody List countOfjoinCondition(@RequestParam(value="checkingQuery") String checkingQuery,@RequestParam(value="tablesSelectedForJoinsString") String tablesSelectedForJoinsString) {
		log4j.info("DataConversionDAOImpl - getTablesNames :: Start : ");
		List<Integer> list = new ArrayList<Integer> (); ;
		int countOfJoinCondition = 0;
		int checkingTablesList = 0;
		String errorMessage = "";
		try {
			countOfJoinCondition = dataConversionService.getNumberofCountQuery(checkingQuery);
			checkingTablesList =  dataConversionService.getCheckingTablesList(tablesSelectedForJoinsString);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			log4j.error("DCController - executeQueryToGetJoins :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - getTablesNames :: countOfJoinCondition : " + countOfJoinCondition);
		log4j.info("DataConversionDAOImpl - getTablesNames :: checkingTablesList : " + checkingTablesList);
		list.add(countOfJoinCondition);
		list.add(checkingTablesList);
		log4j.info("DataConversionDAOImpl - getTablesNames :: End : ");
		return list;
	}
	
	@RequestMapping(value = MappingConstants.InsertTheJoinCondition,method = RequestMethod.POST)
	public void insertTheJoinCondition(HttpServletRequest request, HttpServletResponse response,@RequestParam("selectedQuery") String selectedQuery,@RequestParam("tablesSelectedForJoinsString") String tablesSelectedForJoinsString) {
		log4j.info("DataConversionDAOImpl - insertTheJoinCondition :: Start : ");
		String errorMessage = "";
		try {
			dataConversionService.insertJoinCondition(selectedQuery,tablesSelectedForJoinsString);
			
		} catch (Exception e) {
			errorMessage = e.getMessage();
			log4j.error("DCController - insertTheJoinCondition :: Error " + e.getMessage());
		}
		
		log4j.info("DataConversionDAOImpl - insertTheJoinCondition :: End : ");
	}
	/**
	 * @author sushanta
	 * This method reads excel file and return data.
	 * @exception Exception
	 * @return DataConversionDto
	 * 
	 *
	 */
	@RequestMapping(MappingConstants.ShowExcelData)
	public ModelAndView showExcelData(HttpServletRequest req, HttpServletResponse resp, @RequestParam("ruleDataExcel") MultipartFile excelFile) {
		log4j.info("DCController - showExcelData :: Start :: " );
		ModelAndView modelAndView = new ModelAndView();
		List dataList = new ArrayList();
		try {
			log4j.info("DCController - showExcelData :: File Name :: "+excelFile.getOriginalFilename() );
			dataList = dataConversionService.fetchDataFromExcel(excelFile);
			modelAndView.addObject("ruleInfo", dataList);
			modelAndView.setViewName("ruleInfo");
		} catch (Exception e) {
			log4j.error("DCController - showExcelData :: Error " + e.getMessage());
		}
		log4j.info("DCController - showExcelData :: End" + dataList.size());
		return modelAndView;
	};
	
	
	
	@RequestMapping(value = MappingConstants.GETJOINCONDITIONCHECK,method = RequestMethod.POST)
	public @ResponseBody List<List<LinkedHashMap<String, Object>>> joinConditionCheck(@RequestParam(value="selectedColumnNamesString") String selectedColumnNamesString) {
		log4j.info("DataConversionDAOImpl - joinConditionCheck :: Start : ");
		List<List<LinkedHashMap<String, Object>>> list = null;
		String errorMessage = "";
		try {
			list =  dataConversionService.getTheJoinConditionListCheck(selectedColumnNamesString);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			log4j.error("DCController - joinConditionCheck :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - joinConditionCheck :: checkingTablesList : " + list);
		log4j.info("DataConversionDAOImpl - joinConditionCheck :: End : ");
		return list;
	}
	
	
	/**
	 * @author Srikanth Tummapudi
	 * This loads the counties to the view
	 * 
	 * @param request
	 * @param response
	 * @return countryList
	 */
	@RequestMapping(value = MappingConstants.GetCountryList, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Country> getCountryList(HttpServletRequest request, HttpServletResponse response) {
		log4j.info("DCController - getCountryList :: Start");
		//String country = "USA";
		List<Country> countryList = null;
		try {
			countryList = dataConversionService.getCountryList();
		} catch (Exception e) {
			log4j.error("DCController - getCountryList :: Error " + e.getMessage());
		}
		log4j.info("DCController - getCountryList :: End");
		return countryList;
	}
	
	/**
	 * @author Srikanth Tummapudi
	 * This loads the states to the view
	 * 
	 * @param request
	 * @param response
	 * @param countryCode
	 * @return stateList
	 */
	@RequestMapping(value = MappingConstants.GetStateList, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<State> getStateList(HttpServletRequest request, HttpServletResponse response, Long countryCode) {
		log4j.info("DCController - getStateList :: Start");
		String state = "TS";
		List<State> stateList = null;

		try {
			stateList = dataConversionService.getStateList(countryCode);
		} catch (Exception e) {
			log4j.error("DCController - getStateList :: Error " + e.getMessage());
		}
		log4j.info("DCController - getStateList :: End");
		return stateList;
	}
	
	/**
	 * @author Srikanth Tummapudi
	 * This loads the counties to the view
	 * 
	 * @param request
	 * @param response
	 * @param stateFipsCode
	 * @return countyList
	 */
	@RequestMapping(value = MappingConstants.GetCountyList, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<County> getCountyList(HttpServletRequest request, HttpServletResponse response, Long stateFipsCode) {
		log4j.info("DCController - getCountyList :: Start");
		String county = "Miami";
		List<County> countyList = null;
		try {
			countyList = dataConversionService.getCountyList(stateFipsCode);
			
		} catch (Exception e) {
			log4j.error("DCController - getCountyList :: Error " + e.getMessage());
		}
		log4j.info("DCController - getCountyList :: End");
		return countyList;
	}
	
	
	/**
	 * @author Srikanth Tummapudi
	 * This constructs the data required for hierarchy tree
	 * 
	 * @param request
	 * @param response
	 * @param countryInfo
	 * @param stateInfo
	 * @param countyInfo
	 * @return modelAndView - getSelectedCounty.jsp
	 */
	@RequestMapping(value = MappingConstants.GetSelectedCountyFields, method = RequestMethod.POST)
	public @ResponseBody ModelAndView getSelectedCountyFields(HttpServletRequest request, HttpServletResponse response, String countryInfo, String stateInfo, String countyInfo) {
		log4j.info("DCController - getSelectedCountyFields :: Start");
		ModelAndView modelAndView = null;
		List<FnfLanDictionary> fieldsList = null;
		String[] countryInfoArray = countryInfo.split("\\|");
		String[] stateInfoArray = stateInfo.split("\\|");
		String[] countyInfoArray = countyInfo.split("\\|");
		
		String countryCode = countryInfoArray[0];
		String countryName = countryInfoArray[1];
		String stateFipsCode = stateInfoArray[0];
		String stateName = stateInfoArray[1];
		String countyFipsCode = countyInfoArray[0];
		String countyName = countyInfoArray[1];
		
		

		try {
			modelAndView = new ModelAndView();
			fieldsList = dataConversionService.getSelectedCountyFields(countyName, stateName);
			modelAndView.addObject("countryCode", countryCode);
			modelAndView.addObject("countryName", countryName);
			modelAndView.addObject("stateFipsCode", stateFipsCode);
			modelAndView.addObject("stateName", stateName);
			modelAndView.addObject("countyFipsCode", countyFipsCode);
			modelAndView.addObject("countyName", countyName);
			modelAndView.addObject("fieldsList", fieldsList);
			modelAndView.setViewName(JspConstants.GETSELECTEDCOUNTY);
		} catch (Exception e) {
			log4j.error("DCController - getSelectedCountyFields :: Error " + e.getMessage());
		}
		log4j.info("DCController - getSelectedCountyFields :: End");
		return modelAndView;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(MappingConstants.EXECUTEFORQC)
	public @ResponseBody List<LinkedHashMap<String, FnfLanForQC>> FetchDataForQc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("slectedEdition") String slectedEdition) {
		List<LinkedHashMap<String, FnfLanForQC>> dataForQc= new ArrayList<LinkedHashMap<String, FnfLanForQC>>();
		try 
		{
			String selectQuery="select top 4000 * from fnf_lan23_demo1 order by "+slectedEdition;
			dataForQc=dataConversionService.FetchDataForQc(selectQuery,slectedEdition);
		} 
		catch (Exception e) {
			log4j.error("DCController - rowsForQc :: Error " + e.getMessage());
		}
		log4j.info("DCController - rowsForQc :: End");
		return dataForQc;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(MappingConstants.EXECUTEQUERYFORDELETE)
	public @ResponseBody String QueryTodeleteAfterQc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("rowsToDelete") String rowsToDelete) {
		String message="";
		String[] parcelNumbersToDelete=rowsToDelete.split(",");
		try 
		{
			String selectQuery="select top 400 * from fnf_lan23_demo1";
		//	message=dataConversionService.QueryTodeleteAfterQc(selectQuery);
		} 
		catch (Exception e) {
			log4j.error("DCController - counties :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return message;
	}
	/**
	 * @author Tripura
	 * This Method is used to create user from admin screen
	 * @param request
	 * @param response
	 * @return userList
	 */
	@RequestMapping(MappingConstants.showCreateUsers)
	public String showCreateUsers(HttpServletRequest request, HttpServletResponse response) {
		String userList=null;
		String createusername = request.getParameter("createloginname");
		String createpassword = request.getParameter("createpassword");
		String menuaccess = request.getParameter("checkbox6");
		if(menuaccess != null && menuaccess.equals("on")){
			menuaccess = "Y";
		}
		else{
			menuaccess = "N";
		}
		try 
		{
			log4j.info("DCController - save  :: Start");
			dataConversionService.showCreateUsers(request,response,createusername,createpassword,menuaccess);
		} 
		catch (Exception e) {
			log4j.error("DCController - users :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return userList;
	
	}
	/**
	 * @author Tripura
	 * This method is used to update the passwords 
	 * @param request
	 * @param response
	 * @return userList
	 */
	
	@RequestMapping(MappingConstants.updatePassword)
	public String updatePassword(HttpServletRequest request, HttpServletResponse response) {
		String userList=null;
		String createusername = request.getParameter("createloginname");
		String createpassword = request.getParameter("createpassword");
		
		try 
		{
			log4j.info("DCController - save  :: Start");
			dataConversionService.updatePassword(request,response,createusername,createpassword);
		} 
		catch (Exception e) {
			log4j.error("DCController - users :: Error " + e.getMessage());
		}
		log4j.info("DCController - counties :: End");
		return userList;
	
	}
	@RequestMapping(value="/checking",method=RequestMethod.POST)
	public List<State> stateList(@RequestBody DataConversionRuleDto dto,@RequestParam(value="") String value,BindingResult result){
		Logger logger=Logger.getLogger(DCController.class);
		try{
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}

