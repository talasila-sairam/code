package com.appstek.dc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.springframework.web.multipart.MultipartFile;


import com.appstek.dc.dbload.Country;
import com.appstek.dc.dbload.County;
import com.appstek.dc.dbload.FieldMappingTable;

import com.appstek.dc.dbload.FnfLanCounties;
import com.appstek.dc.dbload.FnfLanCountries;
import com.appstek.dc.dbload.FnfLanCountyTables;
import com.appstek.dc.dbload.FnfLanDictionary;
import com.appstek.dc.dbload.FnfLanForQC;
import com.appstek.dc.dbload.FnfLanStates;
import com.appstek.dc.dbload.RuleQuery;
import com.appstek.dc.dbload.State;
import com.appstek.dc.dto.DataConversionDto;
import com.appstek.dc.dto.DataConversionRuleDto;
/*import com.appstek.dc.dto.Field_71_72_73_ExcelDto;*/

public interface DataConversionService {

	List<FnfLanCountries> loadCountries();

	List<FnfLanDictionary> loadAllFields();

	List<FnfLanCountyTables> loadTableNames(DataConversionDto dataConversionDto);

	Map<String, List<DataConversionDto>> loadTableColumnNames(DataConversionDto dataConversionDto);

	List<LinkedHashMap<String, Object>> getQueryData(String inputQuery) throws HibernateException,SQLException;

	int executeUpdate(String inputQuery);

	List<RuleQuery> loadAllTheRules(DataConversionDto dataConversionDto);

	boolean deleteRule(DataConversionRuleDto dataConversionRuleDto);

	RuleQuery loadRule(DataConversionRuleDto dataConversionRuleDto);

	/*List<List<LinkedHashMap<String, Object>>> getDefaultFlag(String selectedValue);*/

	boolean saveRule(DataConversionRuleDto dataConversionRuleDto);

	boolean isExistsRuleName(String ruleName);

	List<DataConversionRuleDto> getFieldRules(DataConversionRuleDto ruleDto);
	
	boolean editRule(DataConversionRuleDto dataConversionRuleDto);

	//void getQueryDataXl();

	List<List<LinkedHashMap<String, Object>>> getResultOfJoinCondition(String string);

	public String saveDataForLan23(String countyName,String stateName,String edition);

	List<DataConversionRuleDto> getPerticularFieldRules(DataConversionRuleDto ruleDto);
	int getRuleCount(DataConversionDto dataConversionDto);

/*	List<String> getTablesNames(String countryName);*/
	
	/**
	 * chandu code starts here 
	 * @param inputQuery
	 * @return
	 */
		ArrayList FetchCounties(String inputQuery);

		ArrayList Fetchusers(String inputQuery);


		void saveUserTaskmanagement(String user, String county,String state,String editionYear,String assignedTime,String countyStateReference);

		List<LinkedHashMap<String, Object>> showUserTaskmanagement(String fromDate,String toDate);

		void showValidateUser(HttpServletRequest request, HttpServletResponse response,
				String username, String password);

		List<LinkedHashMap<String, Object>>  UserReportfetchRecords(HttpServletRequest request, HttpServletResponse response, String userName);

		void UserReportInsertRecords(HttpServletRequest request,
				HttpServletResponse response, String comments, String currentStatus,String selectedUser,String signOffStatus,String countyStateReference);

		ArrayList<Object> FetchStates(String selectQuery);

		int FetchNoOfTablesPerCounty(String stateCountyEditionYear);
		/*int getRuleCount(DataConversionDto dataConversionDto);*/
		/**
		 * chandu code ends here 
		 */
		/**
		 * chandu validtin code starts here 
		 * @param inputQuery
		 * @return
		 */
		List<Object> saveSelectNumber(String inputQuery);
		

		List<FnfLanCountries> getAllCountries();

		List<FnfLanStates> getStatesForCountry(DataConversionDto dto);

		List<FnfLanCounties> getCountiesForState(DataConversionDto dto);
		List<List<LinkedHashMap<String, Object>>> getTableInformation(String tableData);
		int getNumberofCountQuery(String checkingQuery);
		void insertJoinCondition(String selectedQuery, String tablesSelectedForJoins);
		int getCheckingTablesList(String tablesSelectedForJoinsString);
		List fetchDataFromExcel(MultipartFile excelFile);
		List<List<LinkedHashMap<String, Object>>> getTheJoinConditionListCheck(String selectedTableColumns);

		
		
		List<Country> getCountryList();
		List<State> getStateList(Long countryCode);
		List<County> getCountyList(Long stateFipsCode);
		List<FnfLanDictionary> getSelectedCountyFields(String countyName, String stateName);
		

		List<FieldMappingTable> getFieldMappingForParentFieds(Integer fieldNumber);
		
		String showCreateUsers(HttpServletRequest request, HttpServletResponse response, String createusername,
				String createpassword, String menuaccess);

		String updatePassword(HttpServletRequest request, HttpServletResponse response, String createusername,
				String createpassword);

		List<LinkedHashMap<String, FnfLanForQC>> FetchDataForQc(String selectQuery,String slectedEdition);

		boolean saveFieldData(DataConversionRuleDto dataConversionRuleDto);
		
}
