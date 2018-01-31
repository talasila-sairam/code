package com.appstek.dc.util;

public interface MappingConstants {

	public String GETCOUNTIES = "/getCounties";
	public String GETRULES = "getRules";
	public String GETCOUNTYTABLES = "/getCountyTables";
	public String GETCOUNTYTABLECOLUMNS = "/getCountyTableColumns";
	public String EXECUTEQUERY = "executeQuery";
	public String EXECUTEMULTIPLEQUERY = "executeMultiPleQuery";
	public String EXECUTEUPDATE = "executeUpdate";
	public String SAVERULE = "/saveRule";
	public String DELETERULE = "deleteRule";
	public String DOWNLOADEXCELREPORT = "/downloadExcelReport";
	public String DOWNLOADTEXTREPORT = "/downloadTextReport";
	public String EXCELREPORTNAME = "AK_Sitka_06_Data_Spec_Sheet.xlsx";
	public String EXCELREPORTNAMEFNF_Lan23 = "FNF_Lan23.xls";
	public String TEXTREPORTNAME = "AK_Sitka_06_Data_Spec_Sheet.txt";
	//public String DOWNLOADTEXTREPORT = "/downloadTextReport";
	public String EXPORTDATAQUERY = "/executeExportDataQuery";
	public String GETDEFAULTFLAG = "/getDefaultValue";
	public String LOADFIELDRULES = "/loadFieldRules";
	public String EDITRULE = "editRule";
	public String EXPORTDATAXL = "/executeExportDataXl";
	public String COUNTYFIELDS = "/getCountyFields";
	public String EXECUTEQUERYTOGETJOINS = "/executeQueryToGetJoins";
	public String SAVEDATA = "/saveData";
	public String  LOADPAGERULES = "/loadPageRules";

	public String LOADPERTICULARFIELDRULES = "/loadPerticularFieldRules";

	public String DOC1 = "018-ASSESSE OWNER�S NAME#019-SECOND OWNER NAME#020-ASSE VESTING/ID CODES";
	public String DOC2 = "005-PROPERTY ADDRESS SOURCE FLAG#006-PROPERTY HOUSE NUMBER#009-PROPERTY STREET NAME#010-PROPERTY STREET SUFFIX#011-PROPERTY STREET DIRECTION RIGHT#012-PROPERTY UNIT NUMBER#013-PROPERTY FULL STREET ADDRESS";
	public String DOC3 = "30-ASSESSE MAIL- FULL STREET ADDRESS#031-MAILING CITY#032-MAILING STATE#033-MAILING ZIP CODE#033-MAILING ZIP + 4 CODE#89-Mail City, State & ZIP";
	public String DOC4 = "71-COUNTY LAND USE DESCRIPTION#73-STANDARDIZED LAND USE CODE";
	public String DOC5 = "042-Tax Exemption Codes";
	public String DOC6 = "57-LEGAL LOT CODE#58-LEGAL LOT NUMBER#60-LEGAL BLOCK#65-LEGAL SUBDIVISION NAME#67-LEGAL TRACT NO";
	public String DOC7 = "036-OWNER OCCUPIED (SFR/CONDO)#093-RECORD TYPE";
	public String DOC8 = "076-LOT SIZE OR AREA#189-LOT SIZE FRONTAGE FEET#190-LOT SIZE DEPTH FEET#152-LOT SIZE ACRES#131-LOT SIZE SQUARE FEET";
	public String DOC9 = "077-BUILDING AREA";
	public String LOADALLTABLENAMES = "/loadAllTableNames";

	
	public String countyNmae = "/getCounties";
	public String userNmae = "/getusers";
	public String saveUserTasks = "saveAssignedUsers";
	public String ShowAssignedUsers = "showAssignedUsers";
	public String loginDetails = "/loginPageValidation";
	public String logoutDetails = "/logoutPageValidation";
	public String userReportFetch = "/userReportFetch";
	public String userInsertStatus = "/userStatusInsert";
	public String stateLovs = "/getStates";
	public String GettingNoTablesPerCounty = "/getNumberTablesToUser";
	public String FieldsNumber = "13,69,77,78,83,84,93";
	public String GetFieldsWithCountries = "/getFieldsWithCountries";
	public String GetStatesForCountry = "/getStatesForCountry";
	public String GetCountiesForState = "/getCountiesForState";
	public String saveForFields = "/saveForFields";
	public String CheckSourceVerificationResult = "/checkSourceVerificationResult";
	public String SaveSourceVerificationResult = "/saveSourceVerificationResult";

	public String GetTableNamesForJoin = "/getTableNamesForJoin";
	public String GETCOUNTYTABLECOLUMNSFORJOINS = "/getCountyTableColumnsForJoins";
	public String COUNTOFJOINCONDITION = "/countOfJoinCondition";
	public String InsertTheJoinCondition = "/insertTheJoinCondition";
	public String DwnloadIndividualFiedDoc="dwnloadIndividualFiedDoc";

	public String[] cityArray ={"BENSON","HANCOCK","CYRUS","FARWELL","GLENWOOD","KENSINGTON","LOWRY","STARBUCK","VILLARD"};  
	public String ShowExcelData = "showExcelData";
	public String GETJOINCONDITIONCHECK = "/getJoinConditionCheck";

	
	public String GetCountryList = "/getCountriesList";
	public String GetStateList = "/getStatesList";
	public String GetCountyList = "/getCountiesList";
	public String GetSelectedCountyFields = "/getSelectedCountyFields";
	public String ruleIdInExcel="Rule Id:";
	public String EXCELFULEFORRULESCREATION="DataConersionUploadxlFiles";

	public String EXECUTEFORQC="executeQueryForQC";
	public String EXECUTEQUERYFORDELETE="rowsToDelete";
	public String showCreateUsers = "/SignUpPageValidation";
	public String updatePassword = "UpDatePassword";
	public String SAVEFIELDDATA = "saveFieldData";
	public String FIELDTODISPLAY = "fieldsToShow";

}
