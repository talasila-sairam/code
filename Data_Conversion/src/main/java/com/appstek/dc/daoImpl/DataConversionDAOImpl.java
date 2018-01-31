package com.appstek.dc.daoImpl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;*/
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.appstek.dc.dao.DataConversionDAO;

import com.appstek.dc.dbload.Country;
import com.appstek.dc.dbload.County;
import com.appstek.dc.dao.FnfLanDictionaryDao;
import com.appstek.dc.dbload.FieldMappingTable;
import com.appstek.dc.dbload.FnfLan23;
import com.appstek.dc.dbload.FnfLan23Id;

import com.appstek.dc.dbload.FnfLanCounties;
import com.appstek.dc.dbload.FnfLanCountries;
import com.appstek.dc.dbload.FnfLanCountyTables;
import com.appstek.dc.dbload.FnfLanDictionary;
import com.appstek.dc.dbload.FnfLanForQC;
import com.appstek.dc.dbload.FnfLanStates;
import com.appstek.dc.dbload.LoginCredentials;
import com.appstek.dc.dbload.RuleMaster;
import com.appstek.dc.dbload.RuleQuery;
import com.appstek.dc.dbload.SourceVerificationAnswer;
import com.appstek.dc.dbload.State;
import com.appstek.dc.dbload.Territory;
import com.appstek.dc.dbload.UserTask;
import com.appstek.dc.dto.DataConversionDto;
import com.appstek.dc.dto.DataConversionRuleDto;
import com.appstek.dc.dto.ExcelQuestionAnswerDto;
import com.appstek.dc.util.AliasToEntityLinkedMapResultTransformer;

/**
 * @author mrao
 *
 */
@SuppressWarnings("unused")
@Repository
@Transactional
@EnableTransactionManagement
public class DataConversionDAOImpl implements DataConversionDAO {

	private static Logger log4j = Logger.getLogger("com.appstek.dc.daoImpl.DataConversionDAOImpl");

	@Autowired
	private SessionFactory sessionFactory;

	Transaction txn = null;

	/**
	 * @author mrao This method retrives all countries.
	 * @param
	 * @return List<FnfLanCountries>
	 * @exception Exception
	 *
	 */
	/**
	 * This method loads all the countries ,states and counties
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FnfLanCountries> loadCountries() {
		log4j.info("DataConversionDAOImpl - loadCountries :: Start");
		List<FnfLanCountries> countries = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(FnfLanCountries.class);
			countries = criteria.list();
			for (FnfLanCountries fnfLanCountries : countries) {
				fnfLanCountries.getStates().size();
				for (FnfLanStates fnfLanState : fnfLanCountries.getStates()) {
					fnfLanState.getCounties().size();
					for(int i = 0;i < fnfLanState.getCounties().size(); i++){
						FnfLanCounties fnfLanCounties = fnfLanState.getCounties().get(i);
						fnfLanCounties.setListOfFields(getFieldList(fnfLanCounties.getCountyName(), fnfLanState.getStateName()));
						fnfLanState.getCounties().remove(i);
						fnfLanState.getCounties().add(i, fnfLanCounties);
					}
				}
			}
		} catch (Exception e) {
			log4j.error("DataConversionDAOImpl - loadCountries :: Error " + e.getMessage());
			e.printStackTrace();
		}
		log4j.info("DataConversionDAOImpl - loadCountries :: End");
		return countries;
	}

	@SuppressWarnings("unchecked")
	public List<FnfLanDictionary> getFieldList(String countyName, String stateName) {
		ArrayList<FnfLanDictionary> resultArray = new ArrayList<FnfLanDictionary>();
		String fieldName = "";
		log4j.info("DataConversionDAOImpl - loadCountries :: Start " + countyName +" : " +stateName);
		Session session = sessionFactory.openSession();

		String sqlQuery = "select a.field_number,count(b.rule_name) ruleCount,a.field_Name,a.DEFAULT_FLAG,a.MAPPING_FIELD_NAME from fnf_lan_dictionary a left outer join rule_master b on a.field_number = b.field_number and b.rule_level = (select territory_id from territory where TERRITORY_NAME = '"+countyName+"' and TERRITORY_TYPE = 'COUNTY' and PARENT_ID = (select territory_id from territory where TERRITORY_NAME = '"+stateName+"' and TERRITORY_TYPE = 'STATE')) and status = 1 group by a.FIELD_NUMBER,a.field_name,a.DEFAULT_FLAG,a.MAPPING_FIELD_NAME,b.STATUS";
/*||||||| .r701
=======
	String sqlQry="select a.field_number,count(b.rule_name) ruleCount,a.field_Name,a.DEFAULT_FLAG,a.MAPPING_FIELD_NAME from fnf_lan_dictionary a left outer join rule_master b on a.field_number = b.field_number and b.STATUS=1 and b.rule_level = (select territory_id from territory where TERRITORY_NAME = '"+countyName+"' and TERRITORY_TYPE = 'COUNTY' and PARENT_ID = (select territory_id from territory where TERRITORY_NAME = '"+stateName+"' and TERRITORY_TYPE = 'STATE')) group by a.FIELD_NUMBER,a.field_name,a.DEFAULT_FLAG,a.MAPPING_FIELD_NAME,b.STATUS";
	//	String sqlQuery = "select a.field_number,count(b.rule_name) ruleCount,a.field_Name,a.DEFAULT_FLAG,a.MAPPING_FIELD_NAME from fnf_lan_dictionary a left outer join rule_master b on a.field_number = b.field_number b.STATUS=1 and b.rule_level = (select territory_id from territory where TERRITORY_NAME = '"+countyName+"' and TERRITORY_TYPE = 'COUNTY' and PARENT_ID = (select territory_id from territory where TERRITORY_NAME = '"+stateName+"' and TERRITORY_TYPE = 'STATE')) group by a.FIELD_NUMBER,a.field_name,a.DEFAULT_FLAG,a.MAPPING_FIELD_NAME,b.STATUS";
>>>>>>> .r709
*/		//String sqlQuery = "select a.field_number,count(b.rule_name) ruleCount,a.field_Name,a.mapping_field_name from fnf_lan_dictionary a left outer join rule_master b on a.field_number = b.field_number and b.rule_level = (select territory_id from territory where TERRITORY_NAME = '"+countyName+"' and PARENT_ID = (select territory_id from territory where TERRITORY_NAME = '"+stateName+"')) group by a.FIELD_NUMBER,a.field_name,a.mapping_field_name";
		log4j.info("DataConversionDAOImpl - loadCountries :: Query  " + sqlQuery);
		SQLQuery sqlQueryResult = session.createSQLQuery(sqlQuery);
		List<Object[]> list = (List<Object[]>)sqlQueryResult.list();
		/*Iterator iterator = list.iterator();*/
		FnfLanDictionary fnDictionary = null;
		for (int j = 0; j < list.size(); j++) {
			fnDictionary = new FnfLanDictionary();
			Object[]  objArr  = list.get(j);
			int fieldNumber = 0;
			int count = 0;
			String val = "";
			if (null != objArr[0]) {
				fieldNumber = (int) objArr[0];
			} else {
				fieldNumber = 0;
			}
			fnDictionary.setFieldNumber(fieldNumber);
			if (null != objArr[1]) {
				count = (int) objArr[1];
			} else {
				count = 0;
			}
			fnDictionary.setRuleCount(count);
			if (null != objArr[2]) {
				val = objArr[2].toString();
			} else {
				val = "";
			}
			fnDictionary.setFieldName(val);
			if (null != objArr[3]) {
				val = objArr[3].toString();
			} else {
				val = "";
			}
			fnDictionary.setDefaultFlag(val);
			if (null != objArr[4]) {
				val = objArr[4].toString();
			} else {
				val = "";
			}
			fnDictionary.setMappingFieldName(val);
			resultArray.add(fnDictionary);
		}
		
		/*fnDictionary = (FnfLanDictionary)resultArray.get(1);*/
		log4j.info("DataConversionDAOImpl - loadCountries :: End +++ resultArray:::");
		return resultArray;
	}
	/**
	 * @author mrao This method retrives table names w.r.t county.
	 * @param DataConversionDto
	 * @return List<FnfLanCountyTables>
	 * @exception Exception
	 *
	 */
	/**
	 * This method loads fields 1 to 194
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FnfLanDictionary> loadAllFields() {
		log4j.info("DataConversionDAOImpl - loadAllFields :: Start");
		List<FnfLanDictionary> fieldsList = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(FnfLanDictionary.class);
			fieldsList = criteria.list();
		} catch (Exception e) {
			log4j.error("DataConversionDAOImpl - loadAllFields :: Error " + e.getMessage());
			e.printStackTrace();
		}
		log4j.info("DataConversionDAOImpl - loadAllFields :: End");
		return fieldsList;
	}

	/**
	 * This method loads all the county related tables
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FnfLanCountyTables> loadTableNames(DataConversionDto dataConversionDto) {
		log4j.info("DataConversionDAOImpl - loadTableNames :: Start");
		List<FnfLanCountyTables> countyTableNamesList = new ArrayList<FnfLanCountyTables>();
		List<String> countyTableNames = new ArrayList<String>();
		try {
			Session session = sessionFactory.openSession();
			String queryString = "select table_name from INFORMATION_SCHEMA.TABLES where TABLE_TYPE = 'BASE TABLE' and table_name like '%"+dataConversionDto.getStateCode()+"_"+dataConversionDto.getCountyName().replace(' ', '_')+"%'";
			/*String queryString = "select table_name from INFORMATION_SCHEMA.TABLES where TABLE_TYPE = 'BASE TABLE' and table_name like '%mn_pope%'";*/
			log4j.info(queryString);
			SQLQuery sqlQuery = session.createSQLQuery(queryString);
			countyTableNames = sqlQuery.list();
			for(String tableName : countyTableNames){
				FnfLanCountyTables countyTable = new FnfLanCountyTables();
				countyTable.setOracleTableName(tableName);
				countyTableNamesList.add(countyTable);
			}
		} catch (Exception e) {
			log4j.error("DataConversionDAOImpl - loadTableNames :: Error " + e.getMessage());
			e.printStackTrace();
		}
		log4j.info("DataConversionDAOImpl - loadTableNames :: End");
		return countyTableNamesList;
	}

	/**
	 * @author mrao This method retrives table column names w.r.t table name.
	 * @param DataConversionDto
	 * @return Map<String, List<String>>
	 * @exception Exception
	 *
	 */
	/**
	 * This method loads all the columns of a selected tables from the UI
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, List<DataConversionDto>> loadTableColumnNames(DataConversionDto dataConversionDto) {
		log4j.info("DataConversionDAOImpl - loadTableColumnNames :: Start");
		List<String> countyTableColumnNamesList = null;
		List<String> tableInfoList = null;
		List<DataConversionDto> tableColumnList = null;
		Map<String, List<DataConversionDto>> tableMapWithColumns = new LinkedHashMap<String, List<DataConversionDto>>();
		try {
			Session session = sessionFactory.openSession();
			String[] tableNames = dataConversionDto.getCountyTableNames().split(",");
			for (int i = 0; i < tableNames.length; i++) {
				tableColumnList = new ArrayList<DataConversionDto>();
				/*log4j.info("tableNames=============>" + tableNames[i]);*/
				/*
				 * String loadColumnsQuery =
				 * "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE  TABLE_NAME IN ("
				 * + tableNames[i] + ")"; SQLQuery query =
				 * session.createSQLQuery(loadColumnsQuery);
				 * countyTableColumnNamesList = query.list();
				 * System.out.println(tableNames[i]);
				 * tableMapWithColumns.put(tableNames[i].replaceAll("\'", ""),
				 * countyTableColumnNamesList);
				 */

				// Next done by me(Nagendra) as descussed with Sishant. Previous
				// code is commited above.
				String loadColumnsQuery = "SELECT COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS WHERE  TABLE_NAME IN ("
						+ tableNames[i] + ")";
				SQLQuery query = session.createSQLQuery(loadColumnsQuery);
				List<Object[]> list = (List<Object[]>) query.list();
				for (int j = 0; j < list.size(); j++) {
					Object[] objArr = list.get(j);
					String val = "";
					DataConversionDto dataconversionDto = new DataConversionDto();
					if (null != objArr[0]) {
						val = objArr[0].toString();
					} else {
						val = " ";
					}
					dataconversionDto.setColumnName(val);
					System.out.println(objArr[0].toString());
					if (null != objArr[1]) {
						val = objArr[1].toString();
					} else {
						val = " ";
					}
					dataconversionDto.setColumnType(val);
									if (null != objArr[2]) {
						val = objArr[2].toString();
					} else {
						val = " ";
					}
					dataconversionDto.setColumnSize(val);
					tableColumnList.add(dataconversionDto);
				}

				tableMapWithColumns.put(tableNames[i].replaceAll("\'", ""), tableColumnList);
			}

		} catch (Exception e) {
			log4j.error("DataConversionDAOImpl - loadTableColumnNames :: Error " + e.getMessage());
			e.printStackTrace();
		}
		log4j.info("DataConversionDAOImpl - loadTableColumnNames :: End");
		return tableMapWithColumns;
	}

	/**
	 * This method fetch the results with generated query
	 * 
	 * @return
	 */
	/**
	 * @author Sushanta This method executes select query.
	 * @param String
	 * @return List<LinkedHashMap<String,Object>>
	 * @exception HibernateException
	 *
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<String, Object>> getQueryData(String inputQuery) throws HibernateException,SQLException{
		List<LinkedHashMap<String, Object>> dataList = new ArrayList<LinkedHashMap<String, Object>>();
		log4j.info("DataConversionDAOImpl - getQueryData :: Start :: " + inputQuery);
		try {
			Session session = sessionFactory.openSession();
			Query query = session.createSQLQuery(inputQuery);
			query.setResultTransformer(AliasToEntityLinkedMapResultTransformer.INSTANCE);
			query.setFetchSize(100000);
			query.setFetchSize(100000);
			// log4j.info("list =======>"+query.list());
			// query.setMaxResults(10);
			dataList = query.list();
			/*for(LinkedHashMap<String, Object> dataMap : dataList){
				for (Map.Entry<String, Object> pair : dataMap.entrySet()) {
				    log4j.info("DataConversionDAOImpl - getQueryData :: Data :: " +pair.getKey()+" =========> "+pair.getValue());
				}
				break;
			}*/
			log4j.info("DataConversionDAOImpl - getQueryData :: Data List size :: " + dataList.size());
		} catch (Exception e) {
			System.out.println(e.getCause());
     		log4j.error("DataConversionDAOImpl - getQueryData :: Error " + e.getCause());
			log4j.error("DataConversionDAOImpl - getQueryData :: Error ", e);
			throw new SQLException(e.getCause());
		}
		log4j.info("DataConversionDAOImpl - getQueryData :: End");
		return dataList;
	}

	/**
	 * @author Sushanta This method executes insert/update/delete query.
	 * @param String
	 * @exception HibernateException
	 * @return int
	 * @deprecated
	 */
	@Override
	public int executeUpdate(String inputQuery) {
		int recordCount = 0;
		log4j.info("DataConversionDAOImpl - executeUpdate :: Start :: " + inputQuery);
		try {
			Session session = sessionFactory.openSession();
			Query query = session.createSQLQuery(inputQuery);
			recordCount = query.executeUpdate();
			log4j.info("DataConversionDAOImpl - executeUpdate :: Record Count :: " + recordCount);
		} catch (HibernateException e) {
			log4j.error("DataConversionDAOImpl - executeUpdate :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - executeUpdate :: End");
		return recordCount;
	}

	/**
	 * @author Sushanta This method inserting data into rule_master & rule_query
	 *         tables.
	 * @param RuleQuery
	 * @exception HibernateException
	 * @return true/false.
	 */
	@Override
	public boolean saveRule(RuleQuery ruleQuery) {
		log4j.info("DataConversionDAOImpl - saveRule :: Start :: ");
		boolean flag = false;
		try {
			Session session = sessionFactory.openSession();
			txn = session.beginTransaction();
			// session.save(ruleQuery.getRuleMaster());
			session.save(ruleQuery);
			txn.commit();
			flag = true;
			session.close();
		} catch (HibernateException e) {
			txn.rollback();
			log4j.error("DataConversionDAOImpl - saveRule :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - saveRule :: End");
		return flag;
	}

	/**
	 * @author Sushanta This method retrives territory details.
	 * @param territoryName
	 * @param fipsCode
	 * @exception HibernateException
	 * @return Territory
	 */
	@Override
	public Territory getTerritoryDetailsByNameFips(String territoryName, int fipsCode) {
		log4j.info("DataConversionDAOImpl - getTerritoryDetailsByNameFips :: Start :: ");
		Territory territory = new Territory();
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Territory.class);
			criteria.add(Restrictions.eq("territoryName", territoryName));
			criteria.add(Restrictions.eq("fipsCode", fipsCode));
			territory = (Territory) criteria.uniqueResult();
			// log4j.info("DataConversionDAOImpl - getTerritoryDetailsByNameFips
			// :: Territory Parent Name ::
			// "+territory.getParentTerritory().getTerritoryName());
			session.close();
		} catch (HibernateException e) {
			log4j.error("DataConversionDAOImpl - getTerritoryDetailsByNameFips :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - getTerritoryDetailsByNameFips :: End :: ");
		return territory;
	}

	/**
	 * @author Sushanta This method inserting data into territory table.
	 * @param territory
	 * @exception HibernateException
	 * @return true/false.
	 */
	@Override
	public boolean saveTerritory(Territory territory) {
		log4j.info("DataConversionDAOImpl - saveTerritory :: Start :: ");
		boolean flag = false;
		try {
			Session session = sessionFactory.openSession();
			txn = session.beginTransaction();
			session.saveOrUpdate(territory);
			txn.commit();
			flag = true;
			session.close();
		} catch (HibernateException e) {
			txn.rollback();
			log4j.error("DataConversionDAOImpl - saveTerritory :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - saveTerritory :: End");
		return flag;
	}

	/**
	 * @author Sushanta This method retrives rule master data from rule_master
	 *         table by using rule name.
	 * @param ruleName
	 * @exception HibernateException
	 * @return RuleMaster
	 */
	@Override
	public RuleMaster getRuleMasterByRuleName(String ruleName) {
		log4j.info("DataConversionDAOImpl - getRuleMasterByRuleName :: Start :: ");
		RuleMaster ruleMaster = new RuleMaster();
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(RuleMaster.class);
			criteria.add(Restrictions.eq("ruleName", ruleName));
			ruleMaster = (RuleMaster) criteria.uniqueResult();
			// log4j.info("DataConversionDAOImpl - getTerritoryDetailsByNameFips
			// :: Territory Parent Name ::
			// "+territory.getParentTerritory().getTerritoryName());
			session.close();
		} catch (HibernateException e) {
			log4j.error("DataConversionDAOImpl - getRuleMasterByRuleName :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - getRuleMasterByRuleName :: End :: ");
		return ruleMaster;
	}

	/**
	 * This method loads all the rules
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RuleQuery> loadAllTheRules(DataConversionDto dataConversionDto) {
		log4j.info("DataConversionDAOImpl - loadAllTheRules :: Start ");
		List<RuleQuery> rules = null;
		try {
			int pageStartIndex = ((dataConversionDto.getPageNumber()-1)*dataConversionDto.getPageSize())+1;
			int pageEndIndex = pageStartIndex+dataConversionDto.getPageSize()-1;
			log4j.info("DataConversionDAOImpl - loadAllTheRules ::  pageStartIndex : "+pageStartIndex+" pageEndIndex : "+pageEndIndex);
			
			Session session = sessionFactory.openSession();
			/*Criteria criteria = session.createCriteria(RuleQuery.class);
			criteria.setFetchMode("ruleMaster", FetchMode.JOIN).createAlias("ruleMaster", "ruleMaster");
			criteria.setFetchMode("ruleMaster.territory", FetchMode.JOIN).createAlias("ruleMaster.territory",
					"territory");
			if (dataConversionDto.getCountyFipsCode() != null) {
				criteria.add(Restrictions.eq("territory.fipsCode", dataConversionDto.getCountyFipsCode().intValue()));
			}
			if(dataConversionDto.getRuleLevel().equalsIgnoreCase("global")){
				
			}else if (dataConversionDto.getRuleLevel().equalsIgnoreCase("country")) {
				log4j.info("Country");
				criteria.add(Restrictions.in("territory.fipsCode", getCountyFipsCodesForCountry(dataConversionDto.getCountryFipsCode())));
			}else{
				
			}
			//criteria.add(Restrictions.eq("territory.territoryType", dataConversionDto.getRuleLevel()));
			criteria.add(Restrictions.eq("territory.territoryType", "county"));
			criteria.add(Restrictions.eq("ruleMaster.status", true));
			//criteria.add(Restrictions.eq("ruleMaster.fieldNumber", dataConversionDto.getFieldNumber()));
			criteria.add(Restrictions.eq("deleted", false));
			rules = criteria.list();
			//rules = criteria.list().subList(pageStartIndex, pageEndIndex);*/
			String queryStr = "";
			if(dataConversionDto.getRuleLevel().equalsIgnoreCase("global")){
				queryStr = "select * from (select rq.RULE_QUERY_ID, rq.RUL, rq.DELETED, rq.QUERY, rq.basequery, rq.TABLES, rq.COLUMNS, rq.BASEQUERYRULEHINT, rq.BUILDBASEQUERY, rq.BUILDRULEQUERY, rq.RULECOLUMN, rm.*, t.*, ROW_NUMBER() OVER (ORDER BY FIELD_NUMBER) as ROWNUM from rule_query rq, rule_master rm, territory t where rm.RULE_ID=rq.RULE_ID and t.TERRITORY_ID = rm.rule_level and status=1 and deleted=0) a where ROWNUM between "+pageStartIndex+" and "+pageEndIndex;
			}else if (dataConversionDto.getRuleLevel().equalsIgnoreCase("country")) {
				queryStr = "select * from (select rq.RULE_QUERY_ID, rq.RUL, rq.DELETED, rq.QUERY, rq.basequery, rq.TABLES, rq.COLUMNS, rq.BASEQUERYRULEHINT, rq.BUILDBASEQUERY, rq.BUILDRULEQUERY, rq.RULECOLUMN, rm.*, t.*, ROW_NUMBER() OVER (ORDER BY FIELD_NUMBER) as ROWNUM from rule_query rq, rule_master rm, territory t where rm.RULE_ID=rq.RULE_ID and t.TERRITORY_ID = rm.rule_level and rm.rule_level in"
						+ " (SELECT TERRITORY_ID FROM territory where PARENT_ID in (select TERRITORY_ID from territory where PARENT_ID in "
						+ "(select TERRITORY_ID from territory where FIPS_CODE = "+dataConversionDto.getCountryFipsCode()+"))) and status=1 and deleted=0) a where ROWNUM between "+pageStartIndex+" and "+pageEndIndex;
			}else if(dataConversionDto.getRuleLevel().equalsIgnoreCase("state")){
				queryStr = "select * from (select rq.RULE_QUERY_ID, rq.RUL, rq.DELETED, rq.QUERY, rq.basequery, rq.TABLES, rq.COLUMNS, rq.BASEQUERYRULEHINT, rq.BUILDBASEQUERY, rq.BUILDRULEQUERY, rq.RULECOLUMN, rm.*, t.*, ROW_NUMBER() OVER (ORDER BY FIELD_NUMBER) as ROWNUM from rule_query rq, rule_master rm, territory t where rm.RULE_ID=rq.RULE_ID and t.TERRITORY_ID = rm.rule_level and rm.rule_level in"
						+ " (SELECT TERRITORY_ID FROM territory where PARENT_ID in (select TERRITORY_ID from territory where FIPS_CODE = "+dataConversionDto.getStateFipsCode()+" and PARENT_ID in "
						+ "(select TERRITORY_ID from territory where FIPS_CODE = "+dataConversionDto.getCountryFipsCode()+"))) and status=1 and deleted=0) a where ROWNUM between "+pageStartIndex+" and "+pageEndIndex;
			}else if(dataConversionDto.getRuleLevel().equalsIgnoreCase("county")){
				queryStr = "select * from (select rq.RULE_QUERY_ID, rq.RUL, rq.DELETED, rq.QUERY, rq.basequery, rq.TABLES, rq.COLUMNS, rq.BASEQUERYRULEHINT, rq.BUILDBASEQUERY, rq.BUILDRULEQUERY, rq.RULECOLUMN, rm.*, t.*, ROW_NUMBER() OVER (ORDER BY FIELD_NUMBER) as ROWNUM from rule_query rq, rule_master rm, territory t where rm.RULE_ID=rq.RULE_ID and t.TERRITORY_ID = rm.rule_level and rm.rule_level in"
						+ " (SELECT TERRITORY_ID FROM territory where FIPS_CODE = "+dataConversionDto.getCountyFipsCode()+" and PARENT_ID in (select TERRITORY_ID from territory where FIPS_CODE = "+dataConversionDto.getStateFipsCode()+" and PARENT_ID in "
						+ "(select TERRITORY_ID from territory where FIPS_CODE = "+dataConversionDto.getCountryFipsCode()+"))) and status=1 and deleted=0) a where ROWNUM between "+pageStartIndex+" and "+pageEndIndex;
			}else if(dataConversionDto.getRuleLevel().equalsIgnoreCase("field")){
				queryStr = "select * from (select rq.RULE_QUERY_ID, rq.RUL, rq.DELETED, rq.QUERY, rq.basequery, rq.TABLES, rq.COLUMNS, rq.BASEQUERYRULEHINT, rq.BUILDBASEQUERY, rq.BUILDRULEQUERY, rq.RULECOLUMN, rm.*, t.*, ROW_NUMBER() OVER (ORDER BY FIELD_NUMBER) as ROWNUM from rule_query rq, rule_master rm, territory t where rm.RULE_ID=rq.RULE_ID and t.TERRITORY_ID = rm.rule_level and rm.rule_level in"
						+ " (SELECT TERRITORY_ID FROM territory where FIPS_CODE = "+dataConversionDto.getCountyFipsCode()+" and PARENT_ID in (select TERRITORY_ID from territory where FIPS_CODE = "+dataConversionDto.getStateFipsCode()+" and PARENT_ID in "
						+ "(select TERRITORY_ID from territory where FIPS_CODE = "+dataConversionDto.getCountryFipsCode()+"))) and FIELD_NUMBER = "+dataConversionDto.getFieldNumber()+" and status=1 and deleted=0) a where ROWNUM between "+pageStartIndex+" and "+pageEndIndex;
			}else if(dataConversionDto.getRuleLevel().equalsIgnoreCase("edition")){
				queryStr = "select * from (select rq.RULE_QUERY_ID, rq.RUL, rq.DELETED, rq.QUERY, rq.basequery, rq.TABLES, rq.COLUMNS, rq.BASEQUERYRULEHINT, rq.BUILDBASEQUERY, rq.BUILDRULEQUERY, rq.RULECOLUMN, rm.*, t.*, ROW_NUMBER() OVER (ORDER BY FIELD_NUMBER) as ROWNUM from rule_query rq, rule_master rm, territory t where rm.RULE_ID=rq.RULE_ID and t.TERRITORY_ID = rm.rule_level and rm.rule_level in"
						+ " (SELECT TERRITORY_ID FROM territory where FIPS_CODE = "+dataConversionDto.getCountyFipsCode()+" and PARENT_ID in (select TERRITORY_ID from territory where FIPS_CODE = "+dataConversionDto.getStateFipsCode()+" and PARENT_ID in "
						+ "(select TERRITORY_ID from territory where FIPS_CODE = "+dataConversionDto.getCountryFipsCode()+"))) and FIELD_NUMBER = "+dataConversionDto.getFieldNumber()+" and status=1 and deleted=0 and EDITION = "+dataConversionDto.getEdition()+") a where ROWNUM between "+pageStartIndex+" and "+pageEndIndex;
			}
			log4j.info("DataConversionDAOImpl - loadAllTheRules :: rules " + queryStr);
			SQLQuery query = session.createSQLQuery(queryStr).addEntity(RuleQuery.class);
			//rules = query.list().subList(pageStartIndex, pageEndIndex);
			rules = query.list();
			log4j.info("DataConversionDAOImpl - loadAllTheRules :: rules " + rules.size());
			/*log4j.info("DataConversionDAOImpl - loadAllTheRules :: rules " + ((RuleQuery)rules.get(0)).getRuleMaster().getFieldNumber());
			log4j.info("DataConversionDAOImpl - loadAllTheRules :: rules " + ((RuleQuery)rules.get(0)).getRuleMaster().getEdition());
			log4j.info("DataConversionDAOImpl - loadAllTheRules :: rules " + ((RuleQuery)rules.get(0)).getRuleMaster().getRuleId());*/
			
		} catch (Exception e) {
			e.printStackTrace();
			log4j.error("DataConversionDAOImpl - loadAllTheRules :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - loadAllTheRules :: End");
		return rules;
	}
	
	@Override
	public int getRuleCount(DataConversionDto dataConversionDto) {
		log4j.info("DataConversionDAOImpl - getRuleCount :: Start ");
		int ruleCount = 0;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(RuleQuery.class);
			criteria.setFetchMode("ruleMaster", FetchMode.JOIN).createAlias("ruleMaster", "ruleMaster");
			criteria.setFetchMode("ruleMaster.territory", FetchMode.JOIN).createAlias("ruleMaster.territory",
					"territory");
			if (dataConversionDto.getCountyFipsCode() != null) {
				criteria.add(Restrictions.eq("territory.fipsCode", dataConversionDto.getCountyFipsCode().intValue()));
			}
			criteria.add(Restrictions.eq("territory.territoryType", dataConversionDto.getRuleLevel()));
			criteria.add(Restrictions.eq("ruleMaster.status", true));
			criteria.add(Restrictions.eq("ruleMaster.fieldNumber", dataConversionDto.getFieldNumber()));
			criteria.add(Restrictions.eq("deleted", false));
			ruleCount = criteria.list().size();
			log4j.info("DataConversionDAOImpl - getRuleCount :: rules " + ruleCount);
		} catch (Exception e) {
			log4j.error("DataConversionDAOImpl - getRuleCount :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - getRuleCount :: End");
		return ruleCount;
	}


	@SuppressWarnings("unchecked")
	public RuleQuery loadRule(DataConversionRuleDto dataConversionRuleDto) {
		log4j.info("DataConversionDAOImpl - loadRule :: Start ");
		List<RuleQuery> ruleQueries = null;
		RuleQuery ruleQuery = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(RuleQuery.class, "ruleQuery");
			if(dataConversionRuleDto.getRuleId() > 0){
				criteria.add(Restrictions.eq("ruleQuery.ruleMaster.ruleId", dataConversionRuleDto.getRuleId()));
			}else{
				criteria.add(Restrictions.eq("ruleQuery.ruleQueryId", dataConversionRuleDto.getRuleQueryId()));
			}
			ruleQueries = criteria.list();
			if (ruleQueries != null && !ruleQueries.isEmpty()) {
				ruleQuery = ruleQueries.get(0);
			}
		} catch (Exception e) {
			log4j.error("DataConversionDAOImpl - loadRule :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - loadRule :: End ");
		return ruleQuery;
	}

	public void deleteRule(RuleQuery ruleQuery) {
		log4j.info("DataConversionDAOImpl - deleteRule :: Start ");
		try {
			Session session = sessionFactory.openSession();
			session.saveOrUpdate(ruleQuery);
		} catch (Exception e) {
			log4j.error("DataConversionDAOImpl - deleteRule :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - deleteRule :: End");
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<String> executeQueryForDefalutFlag(String
	 * selectedValue) { // TODO Auto-generated method stub log4j.info(
	 * "DataConversionDAOImpl - executeQueryForDefalutFlag :: Start : "
	 * +selectedValue ); List<String> defaultFlag = null; try { Session session
	 * = sessionFactory.getCurrentSession(); String hql =
	 * "SELECT DEFAULT_FlAG FROM fnf_lan_dictionary WHERE field_name = :fieldName "
	 * ; SQLQuery query = session.createSQLQuery(hql);
	 * query.setParameter("fieldName", selectedValue); defaultFlag =
	 * query.list(); log4j.info(
	 * "DataConversionDAOImpl - executeQueryForDefalutFlag :: execute" +
	 * defaultFlag);
	 * 
	 * } catch (Exception e) { log4j.error(
	 * "DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " +
	 * e.getMessage()); e.printStackTrace(); } log4j.info(
	 * "DataConversionDAOImpl - loadCountries :: End");
	 * 
	 * return defaultFlag; }
	 */

	/**
	 * @author Sushanta This method retrives rules w.r.t fields.
	 * @param fieldList
	 * @param territoryId
	 * @exception HibernateException
	 * @return List<RuleQuery>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RuleQuery> getFieldRules(List<Integer> fieldList, int territoryId) {
		log4j.info("DataConversionDAOImpl - getFieldRules :: Start ");
		List<RuleQuery> fieldRules = null;
		try {
			Session session = sessionFactory.openSession();
			DetachedCriteria rlMstrCrit = DetachedCriteria.forClass(RuleMaster.class, "rm");
			rlMstrCrit.add(Restrictions.eq("rm.territory.territoryId", territoryId));
			rlMstrCrit.add(Restrictions.in("rm.fieldNumber", fieldList));
			rlMstrCrit.add(Restrictions.eq("rm.status", true));
			rlMstrCrit.setProjection(Projections.projectionList().add(Projections.property("rm.ruleId")));
			Criteria criteria = session.createCriteria(RuleQuery.class, "rq");
			criteria.add(Subqueries.propertyIn("rq.ruleMaster.ruleId", rlMstrCrit));
			criteria.add(Restrictions.eq("rq.deleted", false));
			fieldRules = criteria.list();
			log4j.info("DataConversionDAOImpl - getFieldRules :: field rules size :: " + fieldRules.size());
		} catch (HibernateException e) {
			e.printStackTrace();
			log4j.error("DataConversionDAOImpl - getFieldRules :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - getFieldRules :: End ");
		return fieldRules;
	}

	/**
	 * @author Sushanta This method update the rule.
	 * @param ruleQuery
	 * @exception HibernateException
	 * @return true/false
	 */
	@Override
	public boolean updateRule(RuleQuery ruleQuery) {
		log4j.info("DataConversionDAOImpl - updateRule :: Start ");
		boolean flag = false;
		try {
			Session session = sessionFactory.openSession();
			txn = session.beginTransaction();
			session.saveOrUpdate(ruleQuery);
			txn.commit();
			flag = true;
			session.close();
		} catch (HibernateException e) {
			txn.rollback();
			log4j.error("DataConversionDAOImpl - updateRule :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - updateRule :: End");
		return flag;
	}

	/* @Override
	  public void getQueryDataXl() { // TODO Auto-generated method stub
	  List<LinkedHashMap<String, Object>> list = new
	  ArrayList<LinkedHashMap<String, Object>>(); String message="";
	  log4j.info("DataConversionDAOImpl - getQueryDataXl :: Start"); try{
	  Session session = sessionFactory.openSession(); Transaction tx = null; tx
	  = session.beginTransaction(); SQLQuery query =
	  session.createSQLQuery(inputQuery); query.addEntity(FnfLan23.class);
	  query.setResultTransformer(AliasToEntityLinkedMapResultTransformer.
	  INSTANCE); list = query.list(); for(Iterator iterator = list.iterator();
	  iterator.hasNext();){ LinkedHashMap<String, Object> employee =
	  (LinkedHashMap<String, Object>) iterator.next(); for(FnfLan23Id emp :
	  employee.){ System.out.println(emp.getValue()); } } String
	  filename="D:/FnfLan_23.xls" ; HSSFWorkbook workbook = new HSSFWorkbook();
	  HSSFSheet sheet = workbook.createSheet("new sheet"); Criteria cr =
	  session.createCriteria(FnfLan23.class); List<FnfLan23> resultData =
	  cr.list(); log4j.info("resultData============>"+resultData);
	  log4j.info("resultData====size========>"+resultData.size()); int i=1;
	  String headers[]={"COUNTYAID","LAN23ID","CYCLENUM","STATEPOSTALCODE",
	  "COUNTYNAME","APNORPINNUMBER","DUPLICATEAPNMULTIADDRESSID",
	  "PROPADDRSRCEFLAG","PROPHOUSENUMBER","PROPHOUSEALPHA",
	  "PROPSTREETDIRECTIONLEFT","PROPSTREETNAME",
	  "PROPSTREETSUFFIX","PROPSTREETDIRECTIONRIGHT","PROPUNITNUMBER",
	  "PROPFULLSTREETADDRESS","PROPCITYNAME",
	  "PROPSTATE","PROPZIPCODE","PROPZIP4","ASSESSEEOWNERNAME",
	  "SECOND_ASSESSEEOWNERNAME","ASSEVESTINGIDCODE",
	  "TAXACCOUNTNUMBER","ASSEMAILCAREOFNAME","ASSEMAILHOUSENUMBER",
	  "ASSEMAILHOUSEALPHA","ASSEMAILSTREETDIRECTIONLEFT",
	  "ASSEMAILSTREETNAME","ASSEMAILSTREETSUFFIX",
	  "ASSEMAILSTREETDIRECTIONRIGHT","ASSEMAILUNITNUMBER",
	  "ASSEMAILFULLSTREETADDRESS",
	  "ASSEMAILCITYNAME","ASSEMAILSTATE","ASSEMAILZIPCODE","ASSEMAILZIP4",
	  "STATELANDUSECODE","OWNEROCCUPIEDSFRCONDO",
	  "ASSESSEDLANDVALUE","ASSESSEDIMPROVEMENTVALUE","TOTALASSESSEDVALUE",
	  "ASSESSMENTYEAR","CALIFHOMEOWNERSEXEMPTION",
	  "TAXEXEMPTIONCODE","TAXRATECODEAREA","TAXAMOUNT","TAXYEAR",
	  "TAXDELINQUENTYEAR","RECORDERSDOCUMENTNUMBER",
	  "RECORDERSBOOKNUMBER","RECORDERSPAGENUMBER","RECORDINGDATE",
	  "DOCUMENTTYPECOUNTYDESCRIPTION",
	  "SALESPRICE","SALESPRICECODE","PRIORSALEDATE","PRIORSALESPRICE",
	  "PRIORSALESCODE","LEGALLOTCODE",
	  "LEGALLOTNUMBER","LEGALLANDLOT","LEGALBLOCK","LEGALSECTION",
	  "LEGALDISTRICT","LEGALUNIT","LEGALCITYMUNICIPALITYTOWNSHIP",
	  "LEGALSUBDIVISIONNAME","LEGALPHASENO","LEGALTRACTNO","LEGALSECTWPRNGMER",
	  "LEGALBRIEFDESCRIPTION",
	  "LEGALASSESSORSMAPREF","COUNTYLANDUSEDESCRIPTION","COUNTYLANDUSECODE",
	  "STANDARDIZEDLANDUSECODE",
	  "TIMESHARECODE","ZONING","LOTSIZEORAREA","BUILDINGAREA","YEARBUILT",
	  "NOOFBUILDINGS",
	  "NOOFSTORIES","NOOFUNITS","TOTALNOOFROOMS","NOOFBEDROOMS","NOOFBATHS",
	  "NOOFPARTIALBATHS",
	  "GARAGETYPEPARKING","PARKINGNUMBEROFCARS","POOL","MAILCITYSTZIP",
	  "FIPSCODE","TAPECUTDATE","CENSUSTRACT",
	  "RECORDTYPE","MARKETVALUELAND","MARKETVALUEIMPROVEMENT",
	  "TOTALMARKETVALUE","MARKETVALUEYEAR",
	  "BUILDINGCLASS","STYLE","TYPECONSTRUCTION","EXTERIORWALLS","FOUNDATION",
	  "ROOFCOVER","HEATING", "AIRCONDITIONING","ELEVATOR","FIREPLACE",
	  "BASEMENT","EDITIONNUMBER","PROPERTYCOUNTRYCODE","BUILDINGAREAINDICATOR",
	  "PROPADDRMATCHCODE",
	  "PROPADDRUNITDESIGNATOR","PROPADDRUNITNUMBER","PROPADDRCARRIERROUTE",
	  "PROPADDRGEOCODEMATCHCODE",
	  "PROPADDRLATITUDE","PROPADDRLONGITUDE","PROPADDRCENSUSTRACTBLOCK",
	  "MAILADDRMATCHCODE","MAILADDRUNITDESIGNATOR",
	  "MAILADDRUNITNUMBER","MAILADDRCARRIERROUTE","ASSESSEEOWNERNAMEINDICATOR",
	  "SECOND_ASSESSEEOWNER_INDICATOR",
	  "MAILCAREOFNAMEINDICATOR","ASSESSEEOWNERNAMETYPE",
	  "SECONDASSESSEEOWNERNAMETYPE","ALTOLDAPNINDICATOR",
	  "CERTIFICATIONDATE","LOTSIZESQFT","BUILDINGQUALITY","FLOORCOVER",
	  "NOOFPLUMBINGFIXTURES","BUILDINGAREA1",
	  "BUILDINGAREA1INDICATOR","BUILDINGAREA2","BUILDINGAREA2INDICATOR",
	  "BUILDINGAREA3","BUILDINGAREA3INDICATOR",
	  "BUILDINGAREA4","BUILDINGAREA4INDICATOR","BUILDINGAREA5",
	  "BUILDINGAREA5INDICATOR","BUILDINGAREA6",
	  "BUILDINGAREA6INDICATOR","BUILDINGAREA7","BUILDINGAREA7INDICATOR",
	  "EFFECTIVEYEARBUILT","HEATINGFUELTYPE",
	  "AIRCONDITIONINGTYPE","LOTSIZEACRES","MORTGAGELENDERNAME","INTERIORWALLS"
	  ,"SCHOOLTAXDISTRICT1","SCHOOLTAXDISTRICT1INDICATOR",
	  "SCHOOLTAXDISTRICT2","SCHOOLTAXDISTRICT2INDICATOR","SCHOOLTAXDISTRICT3",
	  "SCHOOLTAXDISTRICT3INDICATOR","SITEINFLUENCE",
	  "AMENITIES","OTHERIMPRBUILDINGINDICATOR1","OTHERIMPRBUILDINGINDICATOR2",
	  "OTHERIMPRBUILDINGINDICATOR3","OTHERIMPRBUILDINGINDICATOR4",
	  "NEIGHBORHOODCODE","CONDOPROJECTBUILDINGNAME",
	  "OTHERIMPRBUILDINGINDICATOR5","AMENITIES2","OTHERIMPRBUILDINGAREA1",
	  "OTHERIMPRBUILDINGAREA2","OTHERIMPRBUILDINGAREA3",
	  "OTHERIMPRBUILDINGAREA4","OTHERIMPRBUILDINGAREA5","OTHERROOMS",
	  "EXTRAFEATURES1AREA","EXTRAFEATURES1INDICATOR","TOPOGRAPHY","ROOFTYPE",
	  "EXTRAFEATURES2AREA",
	  "EXTRAFEATURES2INDICATOR","EXTRAFEATURES3AREA","EXTRAFEATURES3INDICATOR",
	  "EXTRAFEATURES4AREA",
	  "EXTRAFEATURES4INDICATOR","OLDAPN","BUILDINGCONDITION",
	  "LOTSIZEFRONTAGEFEET","LOTSIZEDEPTHFEET"}; HSSFRow
	  rowhead=sheet.createRow(0); HSSFCellStyle headerStyle =
	  workbook.createCellStyle(); HSSFFont headerFont = workbook.createFont();
	  headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	  headerFont.setColor(HSSFColor.BLUE.index);
	  headerStyle.setFont(headerFont); for(int j=0;j<headers.length;j++){ //
	  HSSFCell cell=rowhead.createCell(j); //
	  rowhead.createCell(j).setCellValue(headers[j]); //
	  cell.setCellStyle(headerStyle); } for (FnfLan23 fnfLan23 : resultData) {
	  log4j.info("fnfLan23===========>"+fnfLan23.getLan23id()); HSSFRow row=
	  sheet.createRow((short)i);
	  row.createCell(0).setCellValue(fnfLan23.getLan23id());
	  row.createCell(1).setCellValue(fnfLan23.getCountyaid());
	  row.createCell(3).setCellValue(fnfLan23.getCyclenum());
	  row.createCell(4).setCellValue(fnfLan23.getStatepostalcode());
	  row.createCell(5).setCellValue(fnfLan23.getStatepostalcode());
	  row.createCell(6).setCellValue(fnfLan23.getStatepostalcode());
	  row.createCell(7).setCellValue(fnfLan23.getCountyname());
	  row.createCell(8).setCellValue(fnfLan23.getApnorpinnumber());
	  row.createCell(9).setCellValue(fnfLan23.getDuplicateapnmultiaddressid());
	  row.createCell(10).setCellValue(fnfLan23.getPropaddrsrceflag());
	  row.createCell(11).setCellValue(fnfLan23.getProphousenumber());
	  row.createCell(12).setCellValue(fnfLan23.getProphousealpha());
	  row.createCell(13).setCellValue(fnfLan23.getPropstreetdirectionleft());
	  row.createCell(14).setCellValue(fnfLan23.getPropstreetname());
	  row.createCell(15).setCellValue(fnfLan23.getPropstreetsuffix());
	  row.createCell(16).setCellValue(fnfLan23.getPropstreetdirectionright());
	  row.createCell(17).setCellValue(fnfLan23.getPropunitnumber());
	  row.createCell(18).setCellValue(fnfLan23.getPropfullstreetaddress());
	  row.createCell(19).setCellValue(fnfLan23.getPropcityname());
	  row.createCell(20).setCellValue(fnfLan23.getPropstate());
	  row.createCell(21).setCellValue(fnfLan23.getPropzipcode());
	  row.createCell(22).setCellValue(fnfLan23.getPropzip4());
	  row.createCell(23).setCellValue(fnfLan23.getAssesseeownername());
	  row.createCell(24).setCellValue(fnfLan23.getSecondAssesseeownername());
	  row.createCell(25).setCellValue(fnfLan23.getAssevestingidcode());
	  row.createCell(26).setCellValue(fnfLan23.getAssemailcareofname());
	  row.createCell(27).setCellValue(fnfLan23.getAssemailhousenumber());
	  row.createCell(28).setCellValue(fnfLan23.getAssemailhousealpha());
	  row.createCell(29).setCellValue(fnfLan23.getAssemailstreetdirectionleft()
	  ); row.createCell(30).setCellValue(fnfLan23.getAssemailstreetname());
	  row.createCell(31).setCellValue(fnfLan23.getAssemailstreetsuffix());
	  row.createCell(32).setCellValue(fnfLan23.getAssemailstreetdirectionright(
	  )); row.createCell(33).setCellValue(fnfLan23.getAssemailunitnumber());
	  row.createCell(34).setCellValue(fnfLan23.getAssemailfullstreetaddress());
	  row.createCell(35).setCellValue(fnfLan23.getAssemailcityname());
	  row.createCell(36).setCellValue(fnfLan23.getAssemailstate());
	  row.createCell(37).setCellValue(fnfLan23.getAssemailzipcode());
	  row.createCell(38).setCellValue(fnfLan23.getAssemailzip4());
	  row.createCell(39).setCellValue(fnfLan23.getStatelandusecode());
	  row.createCell(40).setCellValue(fnfLan23.getOwneroccupiedsfrcondo());
	  row.createCell(41).setCellValue(fnfLan23.getTotalassessedvalue());
	  row.createCell(42).setCellValue(fnfLan23.getAssessedimprovementvalue());
	  row.createCell(43).setCellValue(fnfLan23.getTotalassessedvalue());
	  row.createCell(44).setCellValue(fnfLan23.getAssessmentyear());
	  row.createCell(45).setCellValue(fnfLan23.getCalifhomeownersexemption());
	  row.createCell(46).setCellValue(fnfLan23.getTaxexemptioncode());
	  row.createCell(47).setCellValue(fnfLan23.getTaxratecodearea());
	  row.createCell(48).setCellValue(fnfLan23.getTaxamount());
	  row.createCell(49).setCellValue(fnfLan23.getTaxyear());
	  row.createCell(50).setCellValue(fnfLan23.getTaxdelinquentyear());
	  row.createCell(51).setCellValue(fnfLan23.getRecordersdocumentnumber());
	  row.createCell(52).setCellValue(fnfLan23.getRecordersbooknumber());
	  row.createCell(53).setCellValue(fnfLan23.getRecorderspagenumber());
	  row.createCell(54).setCellValue(fnfLan23.getRecordingdate());
	  row.createCell(55).setCellValue(fnfLan23.getDocumenttypecountydescription
	  ()); row.createCell(56).setCellValue(fnfLan23.getSalesprice());
	  row.createCell(57).setCellValue(fnfLan23.getPriorsalesprice());
	  row.createCell(58).setCellValue(fnfLan23.getPriorsalescode());
	  row.createCell(59).setCellValue(fnfLan23.getLegallotcode());
	  row.createCell(60).setCellValue(fnfLan23.getLegallotnumber());
	  row.createCell(61).setCellValue(fnfLan23.getLegallotnumber());
	  row.createCell(62).setCellValue(fnfLan23.getLegallandlot());
	  row.createCell(63).setCellValue(fnfLan23.getLegalblock());
	  row.createCell(64).setCellValue(fnfLan23.getLegalsection());
	  row.createCell(65).setCellValue(fnfLan23.getLegaldistrict());
	  row.createCell(66).setCellValue(fnfLan23.getLegalunit());
	  row.createCell(67).setCellValue(fnfLan23.getLegalcitymunicipalitytownship
	  ()); row.createCell(68).setCellValue(fnfLan23.getLegalsubdivisionname());
	  row.createCell(69).setCellValue(fnfLan23.getLegalphaseno());
	  row.createCell(70).setCellValue(fnfLan23.getLegaltractno());
	  row.createCell(71).setCellValue(fnfLan23.getLegalsectwprngmer());
	  row.createCell(72).setCellValue(fnfLan23.getLegalbriefdescription());
	  row.createCell(73).setCellValue(fnfLan23.getLegalassessorsmapref());
	  row.createCell(74).setCellValue(fnfLan23.getCountylandusedescription());
	  row.createCell(75).setCellValue(fnfLan23.getCountylandusecode());
	  row.createCell(76).setCellValue(fnfLan23.getStandardizedlandusecode());
	  row.createCell(77).setCellValue(fnfLan23.getTimesharecode());
	  row.createCell(78).setCellValue(fnfLan23.getZoning());
	  row.createCell(79).setCellValue(fnfLan23.getLotsizeorarea());
	  row.createCell(80).setCellValue(fnfLan23.getBuildingarea());
	  row.createCell(81).setCellValue(fnfLan23.getYearbuilt());
	  row.createCell(82).setCellValue(fnfLan23.getNoofbuildings());
	  row.createCell(83).setCellValue(fnfLan23.getNoofstories());
	  row.createCell(84).setCellValue(fnfLan23.getNoofunits());
	  row.createCell(85).setCellValue(fnfLan23.getTotalnoofrooms());
	  row.createCell(86).setCellValue(fnfLan23.getNoofbedrooms());
	  row.createCell(87).setCellValue(fnfLan23.getNoofbaths());
	  row.createCell(88).setCellValue(fnfLan23.getNoofpartialbaths());
	  row.createCell(89).setCellValue(fnfLan23.getGaragetypeparking());
	  row.createCell(90).setCellValue(fnfLan23.getParkingnumberofcars());
	  row.createCell(91).setCellValue(fnfLan23.getPool());
	  row.createCell(92).setCellValue(fnfLan23.getMailcitystzip());
	  row.createCell(93).setCellValue(fnfLan23.getFipscode());
	  row.createCell(94).setCellValue(fnfLan23.getTapecutdate());
	  row.createCell(95).setCellValue(fnfLan23.getCensustract());
	  row.createCell(96).setCellValue(fnfLan23.getRecordtype());
	  row.createCell(97).setCellValue(fnfLan23.getMarketvalueland());
	  row.createCell(98).setCellValue(fnfLan23.getMarketvalueimprovement());
	  row.createCell(99).setCellValue(fnfLan23.getTotalmarketvalue());
	  row.createCell(100).setCellValue(fnfLan23.getMarketvalueyear());
	  row.createCell(101).setCellValue(fnfLan23.getBuildingclass());
	  row.createCell(102).setCellValue(fnfLan23.getStyle());
	  row.createCell(103).setCellValue(fnfLan23.getTypeconstruction());
	  row.createCell(104).setCellValue(fnfLan23.getExteriorwalls());
	  row.createCell(105).setCellValue(fnfLan23.getFoundation());
	  row.createCell(106).setCellValue(fnfLan23.getRoofcover());
	  row.createCell(107).setCellValue(fnfLan23.getHeating());
	  row.createCell(108).setCellValue(fnfLan23.getAirconditioning());
	  row.createCell(109).setCellValue(fnfLan23.getElevator());
	  row.createCell(110).setCellValue(fnfLan23.getFireplace());
	  row.createCell(111).setCellValue(fnfLan23.getBasement());
	  row.createCell(112).setCellValue(fnfLan23.getEditionnumber());
	  row.createCell(113).setCellValue(fnfLan23.getPropertycountrycode());
	  row.createCell(114).setCellValue(fnfLan23.getBuildingareaindicator());
	  row.createCell(115).setCellValue(fnfLan23.getPropaddrgeocodematchcode());
	  row.createCell(116).setCellValue(fnfLan23.getPropaddrunitdesignator());
	  row.createCell(117).setCellValue(fnfLan23.getPropaddrunitnumber());
	  row.createCell(118).setCellValue(fnfLan23.getPropaddrcarrierroute());
	  row.createCell(119).setCellValue(fnfLan23.getPropaddrgeocodematchcode());
	  row.createCell(120).setCellValue(fnfLan23.getPropaddrlatitude());
	  row.createCell(121).setCellValue(fnfLan23.getPropaddrlongitude());
	  row.createCell(122).setCellValue(fnfLan23.getPropaddrcensustractblock());
	  row.createCell(123).setCellValue(fnfLan23.getMailaddrmatchcode());
	  row.createCell(124).setCellValue(fnfLan23.getMailaddrunitnumber());
	  row.createCell(125).setCellValue(fnfLan23.getMailaddrcarrierroute());
	  row.createCell(126).setCellValue(fnfLan23.getAssesseeownernameindicator()
	  );
	  row.createCell(127).setCellValue(fnfLan23.getSecondAssesseeownerIndicator
	  ());
	  row.createCell(128).setCellValue(fnfLan23.getMailcareofnameindicator());
	  row.createCell(129).setCellValue(fnfLan23.getAssesseeownernametype());
	  row.createCell(130).setCellValue(fnfLan23.getSecondAssesseeownername());
	  row.createCell(131).setCellValue(fnfLan23.getAltoldapnindicator());
	  row.createCell(132).setCellValue(fnfLan23.getCertificationdate());
	  row.createCell(133).setCellValue(fnfLan23.getLotsizesqft());
	  row.createCell(134).setCellValue(fnfLan23.getBuildingquality());
	  row.createCell(135).setCellValue(fnfLan23.getFloorcover());
	  row.createCell(136).setCellValue(fnfLan23.getNoofplumbingfixtures());
	  row.createCell(137).setCellValue(fnfLan23.getBuildingarea1());
	  row.createCell(138).setCellValue(fnfLan23.getBuildingarea1indicator());
	  row.createCell(139).setCellValue(fnfLan23.getBuildingarea2());
	  row.createCell(140).setCellValue(fnfLan23.getBuildingarea2indicator());
	  row.createCell(141).setCellValue(fnfLan23.getBuildingarea3());
	  row.createCell(142).setCellValue(fnfLan23.getBuildingarea3indicator());
	  row.createCell(143).setCellValue(fnfLan23.getBuildingarea4());
	  row.createCell(144).setCellValue(fnfLan23.getBuildingarea4indicator());
	  row.createCell(145).setCellValue(fnfLan23.getBuildingarea5());
	  row.createCell(146).setCellValue(fnfLan23.getBuildingarea5indicator());
	  row.createCell(147).setCellValue(fnfLan23.getBuildingarea6());
	  row.createCell(148).setCellValue(fnfLan23.getBuildingarea6indicator());
	  row.createCell(149).setCellValue(fnfLan23.getBuildingarea7());
	  row.createCell(150).setCellValue(fnfLan23.getBuildingarea7indicator());
	  row.createCell(151).setCellValue(fnfLan23.getEffectiveyearbuilt());
	  row.createCell(152).setCellValue(fnfLan23.getAirconditioningtype());
	  row.createCell(153).setCellValue(fnfLan23.getLotsizeacres());
	  row.createCell(154).setCellValue(fnfLan23.getMortgagelendername());
	  row.createCell(155).setCellValue(fnfLan23.getInteriorwalls());
	  row.createCell(156).setCellValue(fnfLan23.getSchooltaxdistrict1());
	  row.createCell(157).setCellValue(fnfLan23.getSchooltaxdistrict1indicator(
	  )); row.createCell(158).setCellValue(fnfLan23.getSchooltaxdistrict2());
	  row.createCell(159).setCellValue(fnfLan23.getSchooltaxdistrict2indicator(
	  )); row.createCell(160).setCellValue(fnfLan23.getSchooltaxdistrict3());
	  row.createCell(161).setCellValue(fnfLan23.getSchooltaxdistrict3indicator(
	  )); row.createCell(162).setCellValue(fnfLan23.getSiteinfluence());
	  row.createCell(163).setCellValue(fnfLan23.getAmenities());
	  row.createCell(164).setCellValue(fnfLan23.getOtherimprbuildingindicator1(
	  ));
	  row.createCell(165).setCellValue(fnfLan23.getOtherimprbuildingindicator2(
	  ));
	  row.createCell(166).setCellValue(fnfLan23.getOtherimprbuildingindicator3(
	  ));
	  row.createCell(167).setCellValue(fnfLan23.getOtherimprbuildingindicator4(
	  )); row.createCell(168).setCellValue(fnfLan23.getNeighborhoodcode());
	  row.createCell(169).setCellValue(fnfLan23.getCondoprojectbuildingname());
	  row.createCell(170).setCellValue(fnfLan23.getOtherimprbuildingindicator5(
	  )); row.createCell(171).setCellValue(fnfLan23.getAmenities2());
	  row.createCell(172).setCellValue(fnfLan23.getOtherimprbuildingarea1());
	  row.createCell(173).setCellValue(fnfLan23.getOtherimprbuildingarea2());
	  row.createCell(174).setCellValue(fnfLan23.getOtherimprbuildingarea3());
	  row.createCell(175).setCellValue(fnfLan23.getOtherimprbuildingindicator4(
	  ));
	  row.createCell(176).setCellValue(fnfLan23.getOtherimprbuildingindicator5(
	  )); row.createCell(177).setCellValue(fnfLan23.getOtherrooms());
	  row.createCell(178).setCellValue(fnfLan23.getExtrafeatures1area());
	  row.createCell(179).setCellValue(fnfLan23.getExtrafeatures1indicator());
	  row.createCell(180).setCellValue(fnfLan23.getTopography());
	  row.createCell(181).setCellValue(fnfLan23.getRooftype());
	  row.createCell(182).setCellValue(fnfLan23.getExtrafeatures2area());
	  row.createCell(183).setCellValue(fnfLan23.getExtrafeatures2indicator());
	  row.createCell(184).setCellValue(fnfLan23.getExtrafeatures3area());
	  row.createCell(185).setCellValue(fnfLan23.getExtrafeatures3indicator());
	  row.createCell(186).setCellValue(fnfLan23.getExtrafeatures4area());
	  row.createCell(187).setCellValue(fnfLan23.getExtrafeatures4indicator());
	  row.createCell(188).setCellValue(fnfLan23.getOldapn());
	  row.createCell(189).setCellValue(fnfLan23.getBuildingcondition());
	  row.createCell(190).setCellValue(fnfLan23.getLotsizefrontagefeet());
	  row.createCell(191).setCellValue(fnfLan23.getLotsizedepthfeet());
	  row.createCell(192).setCellValue(fnfLan23.getComments());
	  row.createCell(193).setCellValue(fnfLan23.getWater());
	  row.createCell(194).setCellValue(fnfLan23.getSewer());
	  row.createCell(195).setCellValue(fnfLan23.getNewrecordtype()); if(i==3)
	  break; i++; } FileOutputStream fileOut = new FileOutputStream(filename);
	  workbook.write(fileOut); fileOut.close();
	  
	  } catch (Exception e) 
	  { e.printStackTrace(); } 
	  
	  }*/
	 
	@Override
	public HSSFWorkbook getQueryDataXl(String inputQuery,String realPath) {
		 List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>(); 
		 HSSFWorkbook workbook = new HSSFWorkbook();
		 String message="";
		 log4j.info("DataConversionDAOImpl - getQueryDataXl :: Start"); 
		/* try{
			  Session session = sessionFactory.openSession();
			  Transaction tx = null; 
			  tx = session.beginTransaction(); 
			  SQLQuery query =  session.createSQLQuery(inputQuery); 
			  query.addEntity(FnfLan23.class);
			  query.setResultTransformer(AliasToEntityLinkedMapResultTransformer.INSTANCE); 
			  list = query.list(); 
			  System.out.println("list = "+list);
			  for(Iterator iterator = list.iterator(); iterator.hasNext();)
			  { 
				  LinkedHashMap<String, Object> employee =  (LinkedHashMap<String, Object>) iterator.next(); 
			
			  } 
			  String filename=realPath+"/FnfLan_23.xls" ;
			  System.out.println(filename);
			  System.out.println(realPath);
			  File fl = new File(realPath);
			  if(!fl.exists())
			  {
				  fl.createNewFile();
			  }else{
				  fl.delete();
				  fl.createNewFile();
			  }
			  HSSFSheet sheet = workbook.createSheet("new sheet");
			  Criteria cr = session.createCriteria(FnfLan23.class); 
			  List<FnfLan23> resultData = cr.list(); 
			  log4j.info("resultData============>"+resultData);
			  log4j.info("resultData====size========>"+resultData.size());
			  int i=1;
			  String headers[]={"COUNTYAID","LAN23ID","CYCLENUM","STATEPOSTALCODE",
				  "COUNTYNAME","APNORPINNUMBER","DUPLICATEAPNMULTIADDRESSID",
				  "PROPADDRSRCEFLAG","PROPHOUSENUMBER","PROPHOUSEALPHA",
				  "PROPSTREETDIRECTIONLEFT","PROPSTREETNAME",
				  "PROPSTREETSUFFIX","PROPSTREETDIRECTIONRIGHT","PROPUNITNUMBER",
				  "PROPFULLSTREETADDRESS","PROPCITYNAME",
				  "PROPSTATE","PROPZIPCODE","PROPZIP4","ASSESSEEOWNERNAME",
				  "SECOND_ASSESSEEOWNERNAME","ASSEVESTINGIDCODE",
				  "TAXACCOUNTNUMBER","ASSEMAILCAREOFNAME","ASSEMAILHOUSENUMBER",
				  "ASSEMAILHOUSEALPHA","ASSEMAILSTREETDIRECTIONLEFT",
				  "ASSEMAILSTREETNAME","ASSEMAILSTREETSUFFIX",
				  "ASSEMAILSTREETDIRECTIONRIGHT","ASSEMAILUNITNUMBER",
				  "ASSEMAILFULLSTREETADDRESS",
				  "ASSEMAILCITYNAME","ASSEMAILSTATE","ASSEMAILZIPCODE","ASSEMAILZIP4",
				  "STATELANDUSECODE","OWNEROCCUPIEDSFRCONDO",
				  "ASSESSEDLANDVALUE","ASSESSEDIMPROVEMENTVALUE","TOTALASSESSEDVALUE",
				  "ASSESSMENTYEAR","CALIFHOMEOWNERSEXEMPTION",
				  "TAXEXEMPTIONCODE","TAXRATECODEAREA","TAXAMOUNT","TAXYEAR",
				  "TAXDELINQUENTYEAR","RECORDERSDOCUMENTNUMBER",
				  "RECORDERSBOOKNUMBER","RECORDERSPAGENUMBER","RECORDINGDATE",
				  "DOCUMENTTYPECOUNTYDESCRIPTION",
				  "SALESPRICE","SALESPRICECODE","PRIORSALEDATE","PRIORSALESPRICE",
				  "PRIORSALESCODE","LEGALLOTCODE",
				  "LEGALLOTNUMBER","LEGALLANDLOT","LEGALBLOCK","LEGALSECTION",
				  "LEGALDISTRICT","LEGALUNIT","LEGALCITYMUNICIPALITYTOWNSHIP",
				  "LEGALSUBDIVISIONNAME","LEGALPHASENO","LEGALTRACTNO","LEGALSECTWPRNGMER",
				  "LEGALBRIEFDESCRIPTION",
				  "LEGALASSESSORSMAPREF","COUNTYLANDUSEDESCRIPTION","COUNTYLANDUSECODE",
				  "STANDARDIZEDLANDUSECODE",
				  "TIMESHARECODE","ZONING","LOTSIZEORAREA","BUILDINGAREA","YEARBUILT",
				  "NOOFBUILDINGS",
				  "NOOFSTORIES","NOOFUNITS","TOTALNOOFROOMS","NOOFBEDROOMS","NOOFBATHS",
				  "NOOFPARTIALBATHS",
				  "GARAGETYPEPARKING","PARKINGNUMBEROFCARS","POOL","MAILCITYSTZIP",
				  "FIPSCODE","TAPECUTDATE","CENSUSTRACT",
				  "RECORDTYPE","MARKETVALUELAND","MARKETVALUEIMPROVEMENT",
				  "TOTALMARKETVALUE","MARKETVALUEYEAR",
				  "BUILDINGCLASS","STYLE","TYPECONSTRUCTION","EXTERIORWALLS","FOUNDATION",
				  "ROOFCOVER","HEATING", "AIRCONDITIONING","ELEVATOR","FIREPLACE",
				  "BASEMENT","EDITIONNUMBER","PROPERTYCOUNTRYCODE","BUILDINGAREAINDICATOR",
				  "PROPADDRMATCHCODE",
				  "PROPADDRUNITDESIGNATOR","PROPADDRUNITNUMBER","PROPADDRCARRIERROUTE",
				  "PROPADDRGEOCODEMATCHCODE",
				  "PROPADDRLATITUDE","PROPADDRLONGITUDE","PROPADDRCENSUSTRACTBLOCK",
				  "MAILADDRMATCHCODE","MAILADDRUNITDESIGNATOR",
				  "MAILADDRUNITNUMBER","MAILADDRCARRIERROUTE","ASSESSEEOWNERNAMEINDICATOR",
				  "SECOND_ASSESSEEOWNER_INDICATOR",
				  "MAILCAREOFNAMEINDICATOR","ASSESSEEOWNERNAMETYPE",
				  "SECONDASSESSEEOWNERNAMETYPE","ALTOLDAPNINDICATOR",
				  "CERTIFICATIONDATE","LOTSIZESQFT","BUILDINGQUALITY","FLOORCOVER",
				  "NOOFPLUMBINGFIXTURES","BUILDINGAREA1",
				  "BUILDINGAREA1INDICATOR","BUILDINGAREA2","BUILDINGAREA2INDICATOR",
				  "BUILDINGAREA3","BUILDINGAREA3INDICATOR",
				  "BUILDINGAREA4","BUILDINGAREA4INDICATOR","BUILDINGAREA5",
				  "BUILDINGAREA5INDICATOR","BUILDINGAREA6",
				  "BUILDINGAREA6INDICATOR","BUILDINGAREA7","BUILDINGAREA7INDICATOR",
				  "EFFECTIVEYEARBUILT","HEATINGFUELTYPE",
				  "AIRCONDITIONINGTYPE","LOTSIZEACRES","MORTGAGELENDERNAME","INTERIORWALLS"
				  ,"SCHOOLTAXDISTRICT1","SCHOOLTAXDISTRICT1INDICATOR",
				  "SCHOOLTAXDISTRICT2","SCHOOLTAXDISTRICT2INDICATOR","SCHOOLTAXDISTRICT3",
				  "SCHOOLTAXDISTRICT3INDICATOR","SITEINFLUENCE",
				  "AMENITIES","OTHERIMPRBUILDINGINDICATOR1","OTHERIMPRBUILDINGINDICATOR2",
				  "OTHERIMPRBUILDINGINDICATOR3","OTHERIMPRBUILDINGINDICATOR4",
				  "NEIGHBORHOODCODE","CONDOPROJECTBUILDINGNAME",
				  "OTHERIMPRBUILDINGINDICATOR5","AMENITIES2","OTHERIMPRBUILDINGAREA1",
				  "OTHERIMPRBUILDINGAREA2","OTHERIMPRBUILDINGAREA3",
				  "OTHERIMPRBUILDINGAREA4","OTHERIMPRBUILDINGAREA5","OTHERROOMS",
				  "EXTRAFEATURES1AREA","EXTRAFEATURES1INDICATOR","TOPOGRAPHY","ROOFTYPE",
				  "EXTRAFEATURES2AREA",
				  "EXTRAFEATURES2INDICATOR","EXTRAFEATURES3AREA","EXTRAFEATURES3INDICATOR",
				  "EXTRAFEATURES4AREA",
				  "EXTRAFEATURES4INDICATOR","OLDAPN","BUILDINGCONDITION",
				  "LOTSIZEFRONTAGEFEET","LOTSIZEDEPTHFEET"}; 
			      HSSFRow  rowhead=sheet.createRow(0); 
			      HSSFCellStyle headerStyle = workbook.createCellStyle(); 
			      HSSFFont headerFont = workbook.createFont();
				  headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				  headerFont.setColor(HSSFColor.BLUE.index);
				  headerStyle.setFont(headerFont); for(int j=0;j<headers.length;j++){ //
				  HSSFCell cell=rowhead.createCell(j); //
				  rowhead.createCell(j).setCellValue(headers[j]); //
				  cell.setCellStyle(headerStyle); } for (FnfLan23 fnfLan23 : resultData) {
				  log4j.info("fnfLan23===========>"+fnfLan23.getLan23id()); HSSFRow row=
				  sheet.createRow((short)i);
				  row.createCell(0).setCellValue(fnfLan23.getLan23id()!=null?fnfLan23.getLan23id():0.0);
				  row.createCell(1).setCellValue(fnfLan23.getCountyaid()!=null?fnfLan23.getCountyaid():0.0);
				  row.createCell(3).setCellValue(fnfLan23.getCyclenum()!=null?fnfLan23.getCyclenum():0.0);
				  row.createCell(4).setCellValue(fnfLan23.getStatepostalcode());
				  row.createCell(5).setCellValue(fnfLan23.getStatepostalcode());
				  row.createCell(6).setCellValue(fnfLan23.getStatepostalcode());
				  row.createCell(7).setCellValue(fnfLan23.getCountyname());
				  row.createCell(8).setCellValue(fnfLan23.getApnorpinnumber());
				  row.createCell(9).setCellValue(fnfLan23.getDuplicateapnmultiaddressid());
				  row.createCell(10).setCellValue(fnfLan23.getPropaddrsrceflag());
				  row.createCell(11).setCellValue(fnfLan23.getProphousenumber()!=null?fnfLan23.getProphousenumber():0.0);
				  row.createCell(12).setCellValue(fnfLan23.getProphousealpha());
				  row.createCell(13).setCellValue(fnfLan23.getPropstreetdirectionleft());
				  row.createCell(14).setCellValue(fnfLan23.getPropstreetname());
				  row.createCell(15).setCellValue(fnfLan23.getPropstreetsuffix());
				  row.createCell(16).setCellValue(fnfLan23.getPropstreetdirectionright());
				  row.createCell(17).setCellValue(fnfLan23.getPropunitnumber());
				  row.createCell(18).setCellValue(fnfLan23.getPropfullstreetaddress());
				  row.createCell(19).setCellValue(fnfLan23.getPropcityname());
				  row.createCell(20).setCellValue(fnfLan23.getPropstate());
				  row.createCell(21).setCellValue(fnfLan23.getPropzipcode()!=null?fnfLan23.getPropzipcode():0.0);
				  row.createCell(22).setCellValue(fnfLan23.getPropzip4());
				  row.createCell(23).setCellValue(fnfLan23.getAssesseeownername());
				  row.createCell(24).setCellValue(fnfLan23.getSecondAssesseeownername());
				  row.createCell(25).setCellValue(fnfLan23.getAssevestingidcode());
				  row.createCell(26).setCellValue(fnfLan23.getAssemailcareofname());
				  row.createCell(27).setCellValue(fnfLan23.getAssemailhousenumber());
				  row.createCell(28).setCellValue(fnfLan23.getAssemailhousealpha());
				  row.createCell(29).setCellValue(fnfLan23.getAssemailstreetdirectionleft()); 
				  row.createCell(30).setCellValue(fnfLan23.getAssemailstreetname());
				  row.createCell(31).setCellValue(fnfLan23.getAssemailstreetsuffix());
				  row.createCell(32).setCellValue(fnfLan23.getAssemailstreetdirectionright()); 
				  row.createCell(33).setCellValue(fnfLan23.getAssemailunitnumber());
				  row.createCell(34).setCellValue(fnfLan23.getAssemailfullstreetaddress());
				  row.createCell(35).setCellValue(fnfLan23.getAssemailcityname());
				  row.createCell(36).setCellValue(fnfLan23.getAssemailstate());
				  row.createCell(37).setCellValue(fnfLan23.getAssemailzipcode()!=null?fnfLan23.getAssemailzipcode():0.0);
				  row.createCell(38).setCellValue(fnfLan23.getAssemailzip4()!=null?fnfLan23.getAssemailzip4():0.0);
				  row.createCell(39).setCellValue(fnfLan23.getStatelandusecode());
				  row.createCell(40).setCellValue(fnfLan23.getOwneroccupiedsfrcondo());
				  row.createCell(41).setCellValue(fnfLan23.getTotalassessedvalue()!=null?fnfLan23.getTotalassessedvalue():0.0);
				  row.createCell(42).setCellValue(fnfLan23.getAssessedimprovementvalue()!=null?fnfLan23.getAssessedimprovementvalue():0.0);
				  row.createCell(43).setCellValue(fnfLan23.getTotalassessedvalue()!=null?fnfLan23.getTotalassessedvalue():0.0);
				  row.createCell(44).setCellValue(fnfLan23.getAssessmentyear()!=null?fnfLan23.getAssessmentyear():0.0);
				  row.createCell(45).setCellValue(fnfLan23.getCalifhomeownersexemption());
				  row.createCell(46).setCellValue(fnfLan23.getTaxexemptioncode());
				  row.createCell(47).setCellValue(fnfLan23.getTaxratecodearea());
				  row.createCell(48).setCellValue(fnfLan23.getTaxamount()!=null?fnfLan23.getTaxamount():0.0);
				  row.createCell(49).setCellValue(fnfLan23.getTaxyear());
				  row.createCell(50).setCellValue(fnfLan23.getTaxdelinquentyear());
				  row.createCell(51).setCellValue(fnfLan23.getRecordersdocumentnumber());
				  row.createCell(52).setCellValue(fnfLan23.getRecordersbooknumber());
				  row.createCell(53).setCellValue(fnfLan23.getRecorderspagenumber());
				  row.createCell(54).setCellValue(fnfLan23.getRecordingdate()!=null?fnfLan23.getRecordingdate():0.0);
				  row.createCell(55).setCellValue(fnfLan23.getDocumenttypecountydescription()); 
				  row.createCell(56).setCellValue(fnfLan23.getSalesprice()!=null?fnfLan23.getSalesprice():0.0);
				  row.createCell(57).setCellValue(fnfLan23.getPriorsalesprice()!=null?fnfLan23.getPriorsalesprice():0.0);
				  row.createCell(58).setCellValue(fnfLan23.getPriorsalescode());
				  row.createCell(59).setCellValue(fnfLan23.getLegallotcode());
				  row.createCell(60).setCellValue(fnfLan23.getLegallotnumber());
				  row.createCell(61).setCellValue(fnfLan23.getLegallotnumber());
				  row.createCell(62).setCellValue(fnfLan23.getLegallandlot());
				  row.createCell(63).setCellValue(fnfLan23.getLegalblock());
				  row.createCell(64).setCellValue(fnfLan23.getLegalsection());
				  row.createCell(65).setCellValue(fnfLan23.getLegaldistrict()!=null?fnfLan23.getLegaldistrict():0.0);
				  row.createCell(66).setCellValue(fnfLan23.getLegalunit());
				  row.createCell(67).setCellValue(fnfLan23.getLegalcitymunicipalitytownship()); 
				  row.createCell(68).setCellValue(fnfLan23.getLegalsubdivisionname());
				  row.createCell(69).setCellValue(fnfLan23.getLegalphaseno());
				  row.createCell(70).setCellValue(fnfLan23.getLegaltractno());
				  row.createCell(71).setCellValue(fnfLan23.getLegalsectwprngmer());
				  row.createCell(72).setCellValue(fnfLan23.getLegalbriefdescription());
				  row.createCell(73).setCellValue(fnfLan23.getLegalassessorsmapref());
				  row.createCell(74).setCellValue(fnfLan23.getCountylandusedescription());
				  row.createCell(75).setCellValue(fnfLan23.getCountylandusecode()!=null?fnfLan23.getCountylandusecode():0.0);
				  row.createCell(76).setCellValue(fnfLan23.getStandardizedlandusecode()!=null?fnfLan23.getStandardizedlandusecode():0.0);
				  row.createCell(77).setCellValue(fnfLan23.getTimesharecode());
				  row.createCell(78).setCellValue(fnfLan23.getZoning());
				  row.createCell(79).setCellValue(fnfLan23.getLotsizeorarea());
				  row.createCell(80).setCellValue(fnfLan23.getBuildingarea()!=null?fnfLan23.getBuildingarea():0.0);
				  row.createCell(81).setCellValue(fnfLan23.getYearbuilt()!=null?fnfLan23.getYearbuilt():0.0);
				  row.createCell(82).setCellValue(fnfLan23.getNoofbuildings()!=null?fnfLan23.getNoofbuildings():0.0);
				  row.createCell(83).setCellValue(fnfLan23.getNoofstories());
				  row.createCell(84).setCellValue(fnfLan23.getNoofunits());
				  row.createCell(85).setCellValue(fnfLan23.getTotalnoofrooms());
				  row.createCell(86).setCellValue(fnfLan23.getNoofbedrooms());
				  row.createCell(87).setCellValue(fnfLan23.getNoofbaths());
				  row.createCell(88).setCellValue(fnfLan23.getNoofpartialbaths());
				  row.createCell(89).setCellValue(fnfLan23.getGaragetypeparking());
				  row.createCell(90).setCellValue(fnfLan23.getParkingnumberofcars()!=null?fnfLan23.getParkingnumberofcars():0.0);
				  row.createCell(91).setCellValue(fnfLan23.getPool());
				  row.createCell(92).setCellValue(fnfLan23.getMailcitystzip());
				  row.createCell(93).setCellValue(fnfLan23.getFipscode()!=null?fnfLan23.getFipscode():0.0);
				  row.createCell(94).setCellValue(fnfLan23.getTapecutdate()!=null?fnfLan23.getTapecutdate():0.0);
				  row.createCell(95).setCellValue(fnfLan23.getCensustract());
				  row.createCell(96).setCellValue(fnfLan23.getRecordtype());
				  row.createCell(97).setCellValue(fnfLan23.getMarketvalueland()!=null?fnfLan23.getMarketvalueland():0.0);
				  row.createCell(98).setCellValue(fnfLan23.getMarketvalueimprovement()!=null?fnfLan23.getMarketvalueimprovement():0.0);
				  row.createCell(99).setCellValue(fnfLan23.getTotalmarketvalue()!=null?fnfLan23.getTotalmarketvalue():0.0);
				  row.createCell(100).setCellValue(fnfLan23.getMarketvalueyear()!=null?fnfLan23.getMarketvalueyear():0.0);
				  row.createCell(101).setCellValue(fnfLan23.getBuildingclass());
				  row.createCell(102).setCellValue(fnfLan23.getStyle());
				  row.createCell(103).setCellValue(fnfLan23.getTypeconstruction());
				  row.createCell(104).setCellValue(fnfLan23.getExteriorwalls());
				  row.createCell(105).setCellValue(fnfLan23.getFoundation());
				  row.createCell(106).setCellValue(fnfLan23.getRoofcover());
				  row.createCell(107).setCellValue(fnfLan23.getHeating());
				  row.createCell(108).setCellValue(fnfLan23.getAirconditioning());
				  row.createCell(109).setCellValue(fnfLan23.getElevator());
				  row.createCell(110).setCellValue(fnfLan23.getFireplace()!=null?fnfLan23.getFireplace():0.0);
				  row.createCell(111).setCellValue(fnfLan23.getBasement());
				  row.createCell(112).setCellValue(fnfLan23.getEditionnumber()!=null?fnfLan23.getEditionnumber():0.0);
				  row.createCell(113).setCellValue(fnfLan23.getPropertycountrycode());
				  row.createCell(114).setCellValue(fnfLan23.getBuildingareaindicator());
				  row.createCell(115).setCellValue(fnfLan23.getPropaddrgeocodematchcode());
				  row.createCell(116).setCellValue(fnfLan23.getPropaddrunitdesignator());
				  row.createCell(117).setCellValue(fnfLan23.getPropaddrunitnumber());
				  row.createCell(118).setCellValue(fnfLan23.getPropaddrcarrierroute());
				  row.createCell(119).setCellValue(fnfLan23.getPropaddrgeocodematchcode());
				  row.createCell(120).setCellValue(fnfLan23.getPropaddrlatitude());
				  row.createCell(121).setCellValue(fnfLan23.getPropaddrlongitude());
				  row.createCell(122).setCellValue(fnfLan23.getPropaddrcensustractblock());
				  row.createCell(123).setCellValue(fnfLan23.getMailaddrmatchcode());
				  row.createCell(124).setCellValue(fnfLan23.getMailaddrunitnumber());
				  row.createCell(125).setCellValue(fnfLan23.getMailaddrcarrierroute());
				  row.createCell(126).setCellValue(fnfLan23.getAssesseeownernameindicator()!=null?fnfLan23.getAssesseeownernameindicator():0.0);
				  row.createCell(127).setCellValue(fnfLan23.getSecondAssesseeownerIndicator());
				  row.createCell(128).setCellValue(fnfLan23.getMailcareofnameindicator()!=null?fnfLan23.getMailcareofnameindicator():0.0);
				  row.createCell(129).setCellValue(fnfLan23.getAssesseeownernametype());
				  row.createCell(130).setCellValue(fnfLan23.getSecondAssesseeownername());
				  row.createCell(131).setCellValue(fnfLan23.getAltoldapnindicator());
				  row.createCell(132).setCellValue(fnfLan23.getCertificationdate()!=null?fnfLan23.getCertificationdate():0.0);
				  row.createCell(133).setCellValue(fnfLan23.getLotsizesqft()!=null?fnfLan23.getLotsizesqft():0.0);
				  row.createCell(134).setCellValue(fnfLan23.getBuildingquality());
				  row.createCell(135).setCellValue(fnfLan23.getFloorcover());
				  row.createCell(136).setCellValue(fnfLan23.getNoofplumbingfixtures());
				  row.createCell(137).setCellValue(fnfLan23.getBuildingarea1()!=null?fnfLan23.getBuildingarea1():0.0);
				  row.createCell(138).setCellValue(fnfLan23.getBuildingarea1indicator());
				  row.createCell(139).setCellValue(fnfLan23.getBuildingarea2()!=null?fnfLan23.getBuildingarea2():0.0);
				  row.createCell(140).setCellValue(fnfLan23.getBuildingarea2indicator());
				  row.createCell(141).setCellValue(fnfLan23.getBuildingarea3()!=null?fnfLan23.getBuildingarea3():0.0);
				  row.createCell(142).setCellValue(fnfLan23.getBuildingarea3indicator());
				  
				  row.createCell(143).setCellValue(fnfLan23.getBuildingarea4()!=null?fnfLan23.getBuildingarea4():0.0);
				  row.createCell(144).setCellValue(fnfLan23.getBuildingarea4indicator());
				  row.createCell(145).setCellValue(fnfLan23.getBuildingarea5()!=null?fnfLan23.getBuildingarea5():0.0);
				  row.createCell(146).setCellValue(fnfLan23.getBuildingarea5indicator());
				  row.createCell(147).setCellValue(fnfLan23.getBuildingarea6()!=null?fnfLan23.getBuildingarea6():0.0);
				  row.createCell(148).setCellValue(fnfLan23.getBuildingarea6indicator());
				  row.createCell(149).setCellValue(fnfLan23.getBuildingarea7()!=null?fnfLan23.getBuildingarea7():0.0);
				  row.createCell(150).setCellValue(fnfLan23.getBuildingarea7indicator());
				  row.createCell(151).setCellValue(fnfLan23.getEffectiveyearbuilt());
				  row.createCell(152).setCellValue(fnfLan23.getAirconditioningtype());
				  row.createCell(153).setCellValue(fnfLan23.getLotsizeacres()!=null?fnfLan23.getLotsizeacres():0.0);
				  row.createCell(154).setCellValue(fnfLan23.getMortgagelendername());
				  row.createCell(155).setCellValue(fnfLan23.getInteriorwalls());
				  row.createCell(156).setCellValue(fnfLan23.getSchooltaxdistrict1()!=null?fnfLan23.getSchooltaxdistrict1():0.0);
				  row.createCell(157).setCellValue(fnfLan23.getSchooltaxdistrict1indicator());
				  row.createCell(158).setCellValue(fnfLan23.getSchooltaxdistrict2());
				  row.createCell(159).setCellValue(fnfLan23.getSchooltaxdistrict2indicator()); 
				  row.createCell(160).setCellValue(fnfLan23.getSchooltaxdistrict3());
				  row.createCell(161).setCellValue(fnfLan23.getSchooltaxdistrict3indicator()); 
				  row.createCell(162).setCellValue(fnfLan23.getSiteinfluence());
				  row.createCell(163).setCellValue(fnfLan23.getAmenities());
				  row.createCell(164).setCellValue(fnfLan23.getOtherimprbuildingindicator1());
				  row.createCell(165).setCellValue(fnfLan23.getOtherimprbuildingindicator2());
				  row.createCell(166).setCellValue(fnfLan23.getOtherimprbuildingindicator3());
				  row.createCell(167).setCellValue(fnfLan23.getOtherimprbuildingindicator4()); 
				  row.createCell(168).setCellValue(fnfLan23.getNeighborhoodcode());
				  row.createCell(169).setCellValue(fnfLan23.getCondoprojectbuildingname());
				  row.createCell(170).setCellValue(fnfLan23.getOtherimprbuildingindicator5()); 
				  row.createCell(171).setCellValue(fnfLan23.getAmenities2());
				  row.createCell(172).setCellValue(fnfLan23.getOtherimprbuildingarea1()!=null?fnfLan23.getOtherimprbuildingarea1():0.0);
				  row.createCell(173).setCellValue(fnfLan23.getOtherimprbuildingarea2()!=null?fnfLan23.getOtherimprbuildingarea2():0.0);
				  row.createCell(174).setCellValue(fnfLan23.getOtherimprbuildingarea3()!=null?fnfLan23.getOtherimprbuildingarea3():0.0);
				  row.createCell(175).setCellValue(fnfLan23.getOtherimprbuildingindicator4()!=null?fnfLan23.getOtherimprbuildingarea4():0.0);
				  row.createCell(176).setCellValue(fnfLan23.getOtherimprbuildingindicator5()!=null?fnfLan23.getOtherimprbuildingarea5():0.0); 
				  row.createCell(177).setCellValue(fnfLan23.getOtherrooms());
				  row.createCell(178).setCellValue(fnfLan23.getExtrafeatures1area()!=null?fnfLan23.getExtrafeatures1area():0.0);
				  
				  row.createCell(179).setCellValue(fnfLan23.getExtrafeatures1indicator());
				  row.createCell(180).setCellValue(fnfLan23.getTopography());
				  row.createCell(181).setCellValue(fnfLan23.getRooftype());
				  row.createCell(182).setCellValue(fnfLan23.getExtrafeatures2area()!=null?fnfLan23.getExtrafeatures2area():0.0);
				  row.createCell(183).setCellValue(fnfLan23.getExtrafeatures2indicator());
				  row.createCell(184).setCellValue(fnfLan23.getExtrafeatures3area()!=null?fnfLan23.getExtrafeatures3area():0.0);
				  row.createCell(185).setCellValue(fnfLan23.getExtrafeatures3indicator());
				  row.createCell(186).setCellValue(fnfLan23.getExtrafeatures4area()!=null?fnfLan23.getExtrafeatures4area():0.0);
				  row.createCell(187).setCellValue(fnfLan23.getExtrafeatures4indicator());
				  row.createCell(188).setCellValue(fnfLan23.getOldapn());
				  row.createCell(189).setCellValue(fnfLan23.getBuildingcondition());
				  row.createCell(190).setCellValue(fnfLan23.getLotsizefrontagefeet());
				  row.createCell(191).setCellValue(fnfLan23.getLotsizedepthfeet());
				  row.createCell(192).setCellValue(fnfLan23.getComments());
				  row.createCell(193).setCellValue(fnfLan23.getWater());
				  row.createCell(194).setCellValue(fnfLan23.getSewer());
				  row.createCell(195).setCellValue(fnfLan23.getNewrecordtype()); 
				  if(i==3)
				  break; i++; 
				  } 
				  FileOutputStream fileOut = new FileOutputStream(fl);
				  workbook.write(fileOut); 
				  fileOut.close();
				  } catch (Exception e){ 
					  e.printStackTrace();
					  } 
*/		 return workbook;
	}

	/**
	 * @author Maheshvarun 
	 * This method is used to Get the Result of join condition
	 * @param tableNames
	 * @return getResultOfJoinsCondition
	 */
	@Override
	public List<List<LinkedHashMap<String, Object>>> executeQueryForJoinCondition(String tableNames) {
		// TODO Auto-generated method stub
		log4j.info("DataConversionDAOImpl - executeQueryForDefalutFlag :: Start : " + "'"+tableNames+"'");
		@SuppressWarnings("rawtypes")
		List getResultOfJoinsCondition = null;
		try {
			Session session = sessionFactory.openSession();
			String hql = "select join_conditions from  dbo.table_join_conditions where JOIN_TABLES = "+"'"+tableNames+"'";
			SQLQuery query = session.createSQLQuery(hql);
			/*query.setParameter("tableNames", "'"+tableNames+"'");*/
			log4j.info("DataConversionDAOImpl - executeQueryForDefalutFlag :: Query" + query);
			getResultOfJoinsCondition = query.list();
			log4j.info("DataConversionDAOImpl - executeQueryForDefalutFlag :: execute" + getResultOfJoinsCondition);
		} catch (Exception e) {
			log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
			e.printStackTrace();
		}
		log4j.info("DataConversionDAOImpl - loadCountries :: End");
		return getResultOfJoinsCondition;
	}
/**
 * @author Maheshvarun This method retrives rules w.r.t fields.
 * @param fieldList
 * @param territoryId
 * @exception HibernateException
 * @return List<RuleQuery>
 */
@SuppressWarnings("unchecked")
@Override
public List<RuleQuery> getPerticularField(int fieldNumber, int territoryId) {
	log4j.info(territoryId+"DataConversionDAOImpl - getPerticularFieldRules :: Start "+fieldNumber);
	List<RuleQuery> fieldRules = null;
	try {
		Session session = sessionFactory.openSession();
		DetachedCriteria rlMstrCrit = DetachedCriteria.forClass(RuleMaster.class, "rm");
		rlMstrCrit.add(Restrictions.eq("rm.territory.territoryId", territoryId));
		rlMstrCrit.add(Restrictions.eq("rm.fieldNumber", fieldNumber));
		rlMstrCrit.add(Restrictions.eq("rm.status", true));
		rlMstrCrit.setProjection(Projections.projectionList().add(Projections.property("rm.ruleId")));
		Criteria criteria = session.createCriteria(RuleQuery.class, "rq");
		criteria.add(Subqueries.propertyIn("rq.ruleMaster.ruleId", rlMstrCrit));
		criteria.add(Restrictions.eq("rq.deleted", false));
		fieldRules = criteria.list();
		log4j.info("DataConversionDAOImpl - getPerticularFieldRules :: field rules size :: " + fieldRules.size());
	} catch (HibernateException e) {
		e.printStackTrace();
		log4j.error("DataConversionDAOImpl - getPerticularFieldRules :: Error " + e.getMessage());
	}
	log4j.info("DataConversionDAOImpl - getPerticularFieldRules :: End ");
	return fieldRules;
}
	@Override
	public List<RuleQuery> getQueryDataForField(String fildNumber,String countyName,String stateName,String edition) {
		log4j.info("DataConversionDAOImpl - getQueryDataForField :: Start : ");
		List ruleQueryList = null;
		try
		{
			Session session = sessionFactory.openSession();
			//String sqlQuery = "select RULE_NAME,BASEQUERY,RUL from rule_master rm,rule_query rq where rm.RULE_ID=rq.RULE_ID and rm.FIELD_NUMBER=:fildNumber  order by rm.RULE_NAME";
			String sqlQuery = "select RULE_NAME,BASEQUERY,RUL from rule_master rm,rule_query rq,territory t where rm.RULE_ID=rq.RULE_ID and rm.RULE_LEVEL=t.TERRITORY_ID and TERRITORY_NAME=:countyName and PARENT_ID = (select TERRITORY_ID from territory where TERRITORY_NAME=:stateName)  and rm.FIELD_NUMBER=:fildNumber and rm.edition=:edition order by rm.RULE_NAME";
			SQLQuery query = session.createSQLQuery(sqlQuery);
			query.setParameter("stateName", stateName);
			query.setParameter("countyName", countyName);
			query.setParameter("fildNumber", Integer.parseInt(fildNumber));
			query.setParameter("edition", edition);
			ruleQueryList = query.list();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		log4j.info("DataConversionDAOImpl - getQueryDataForField :: End : ");
		return ruleQueryList;
	}

	@Override
	public int getTotalRecordsOfBaseQuery(String sqlQuery) {
		log4j.info("DataConversionDAOImpl - getTotalRecordsOfBaseQuery :: Start : ");
		int count = 0;
		List countList = new ArrayList<>();
		try
		{
		//	sqlQuery = "SELECT a.[acct_number] as acct_number1, a.[owner address], b.[acct_number] as acct_number2, c.[acct_number] as acct_number3 FROM SD_Buffalo_04_Main_01 a (nolock) , SD_Buffalo_04_Main_02 b (nolock) , SD_Buffalo_04_Main_03 c (nolock)  where a.[owner address] not like ('null') And  a.[owner address] not like ('empty')";
			Session session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(sqlQuery);
			System.out.println(query.list());
			countList = query.list();
			count = countList.size();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		log4j.info("DataConversionDAOImpl - getTotalRecordsOfBaseQuery :: End : ");
		return count;
	}

	@Override
	public List<String> getTablesNames(String countyName) {
		log4j.info("DataConversionDAOImpl - getTablesNames :: Start : ");
		List<String> countyTableNames = new ArrayList<String>();
		try {
			Session session = sessionFactory.openSession();
			String queryString = "select table_name from INFORMATION_SCHEMA.TABLES where TABLE_TYPE = 'BASE TABLE' and table_name like '%"+countyName+"%'";
			SQLQuery sqlQuery = session.createSQLQuery(queryString);
			countyTableNames = sqlQuery.list();
		} catch (HibernateException e) {
			log4j.error("DataConversionDAOImpl - getTablesNames :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - getTablesNames :: End : ");
		return countyTableNames;
	}

	@Override
	public int ruleCountForCountyField(int territoryId, int fieldNumber) {
		log4j.info("DataConversionDAOImpl - ruleCountForCountyField :: Start : ");
		int ruleCount = 0;
		try {
			Session session = sessionFactory.openSession();
			//String queryString = "select max(convert(int,rule_name)) from rule_master where  field_number = "+fieldNumber+" and rule_level = "+territoryId;
			String queryString = "select * from rule_master where  field_number = "+fieldNumber+" and rule_level = "+territoryId;
			log4j.info("DataConversionDAOImpl - ruleCountForCountyField query "+queryString);
			SQLQuery sqlQuery = session.createSQLQuery(queryString);
			@SuppressWarnings("unchecked")
			List<Object> objList = (List<Object>)sqlQuery.list();
			ruleCount = objList.size();
			log4j.info("Rule Count Value "+ruleCount);
		} catch (HibernateException e) {
			log4j.error("DataConversionDAOImpl - ruleCountForCountyField :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - ruleCountForCountyField :: End : ");
		return ruleCount;  
	}

	@Override
	public List<String> getUsedTableNames(int fieldNumber, String territoryName,String edition) {
		log4j.info("DataConversionDAOImpl - getUsedTableNames :: Start : ");
		String queryString = "";
		List<String> usedTableNames = new ArrayList<String>();
		try {
			Session session = sessionFactory.openSession();
			 if(edition == null || !edition.isEmpty())
			   {
				 queryString = "select TABLES from rule_query where RULE_ID in (select RULE_ID from rule_master where Field_Number = "+fieldNumber+" and rule_level = (select territory_id from territory where territory_name = '"+territoryName+"') and status=1) and (rm.edition is NULL or rm.edition='')";
			   }
			   else
			   {
				   queryString = "select TABLES from rule_query where RULE_ID in (select RULE_ID from rule_master where Field_Number = "+fieldNumber+" and rule_level = (select territory_id from territory where territory_name = '"+territoryName+"') and status=1) and rm.edition = "+edition+"";
			   }
			   
			//String queryString = "select TABLES from rule_query where RULE_ID in (select RULE_ID from rule_master where Field_Number = "+fieldNumber+" and rule_level = (select territory_id from territory where territory_name = '"+territoryName+"') and status=1)";
			SQLQuery sqlQuery = session.createSQLQuery(queryString);
			usedTableNames = sqlQuery.list();
		} catch (HibernateException e) {
			log4j.error("DataConversionDAOImpl - getUsedTableNames :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - getUsedTableNames :: End : ");
		return usedTableNames;
	}

	@Override
	public List<String> getUsedColumnNames(int fieldNumber, String territoryName,String edition) {
		log4j.info("DataConversionDAOImpl - getUsedColumnNames :: Start : ");
		List<String> usedColumnNames = new ArrayList<String>();
		try {
			Session session = sessionFactory.openSession();
			String queryString = "select COLUMNS from rule_query where RULE_ID in (select RULE_ID from rule_master where Field_Number = "+fieldNumber+" and rule_level = (select territory_id from territory where territory_name = '"+territoryName+"') and status=1)";
			SQLQuery sqlQuery = session.createSQLQuery(queryString);
			usedColumnNames = sqlQuery.list();
		} catch (HibernateException e) {
			log4j.error("DataConversionDAOImpl - getUsedColumnNames :: Error " + e.getMessage());
		}
		log4j.info("DataConversionDAOImpl - getUsedColumnNames :: End : ");
		return usedColumnNames;
	}

	/*@Override
	public HSSFWorkbook getQueryDataXl(String inputQuery, String realPath) {
		// TODO Auto-generated method stub
		return null;
	}*/
/**
 * Chandu code Start here
**/

	@Override
	public ArrayList<Object> FetchCounties(String inputQuery) {
		// TODO Auto-generated method stub
		ArrayList counties=null;
		try{
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(inputQuery);
		counties= (ArrayList<Object>) query.list();
		}
		catch (Exception e) {
			log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
			e.printStackTrace();
		}
		return counties;
	}

	@Override
	public ArrayList<Object> Fetchusers(String inputQuery) {
		// TODO Auto-generated method stub
		ArrayList users=null;
		try{
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(inputQuery);
			users= (ArrayList<Object>) query.list();
			}
			catch (Exception e) {
				log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
				e.printStackTrace();
			}
		return users;
	}

	@Override
	public  void saveUserTaskmanagement(String userNeme,String county,String state,String editionYear,String assignedTime,String tableCount) {
		// TODO Auto-generated method stub
		try{
			Session session = sessionFactory.getCurrentSession();
			UserTask userTask = new UserTask();
			userTask.setcounty(county);
			userTask.setuserName(userNeme);
			userTask.setstatus("Assigned");
			userTask.setState(state);
			userTask.setEditionYear(editionYear);
		    userTask.setAssignedTime(new Date());
		    userTask.setStateCounty(state+"-"+county+"-"+editionYear);
		    userTask.setCount(0);
		    userTask.setTableCount(tableCount);
			session.save(userTask);
			}
			catch (Exception e) {
				log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
				e.printStackTrace();
			}
	}

	/*@Override
	public List<LinkedHashMap<String, Object>> showUserTaskmanagement(String fromdate,String toDate) {
		// TODO Auto-generated method stub
		List<LinkedHashMap<String, Object>> data=new ArrayList<LinkedHashMap<String,Object>>();
		List<UserTask> userTaskList = null;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = formatter.parse(fromdate);
			Date todate = formatter.parse(toDate);
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserTask.class);
			criteria.add(Restrictions.between("assignedtime",fromDate,todate)).addOrder(Order.desc("status"));
			data=criteria.list();
			}
			catch (Exception e) {
				log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
				e.printStackTrace();
			}
		return data;
	}*/

	/*@Override
	public void showValidateUser(HttpServletRequest request,
			HttpServletResponse response, String username, String password) {
		// TODO Auto-generated method stub
		List<LoginCredentials> userList = null;
		try{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(LoginCredentials.class);
			criteria.add(Restrictions.and(Restrictions.eq("userName", username),Restrictions.eq("password", password)));
			
			userList=criteria.list();
			HttpSession checkUserSession = request.getSession(true);
			String loginMessage = "";
			
				if(userList.size()>0){
					checkUserSession.setAttribute("username", username.toString());
					response.sendRedirect("index.jsp");
				}
				else{
					loginMessage = "Please enter valid details.";
					checkUserSession.setAttribute("message", loginMessage);
					response.sendRedirect("LoginPage.jsp");
				}
			
			}
			catch (Exception e) {
				log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
				e.printStackTrace();
			}
	}
*/
	/*@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<String, Object>> UserReportfetchRecords(HttpServletRequest request, HttpServletResponse response, String userName) {
		// TODO Auto-generated method stub
		List<LinkedHashMap<String, Object>> data=new ArrayList<LinkedHashMap<String,Object>>();
		try{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserTask.class);
		//	criteria.add(Restrictions.eq("userName",userName)).addOrder(Order.desc("status"));
			data=criteria.list();
			}
			catch (Exception e) {
				log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
				e.printStackTrace();
			}
		return data;
	}*/

	/*@SuppressWarnings("unchecked")
	@Override
	public void UserReportInsertRecords(HttpServletRequest request, HttpServletResponse response, String comments,
	        String currentStatus,String selectedUser,String signOffStatus,String countyStateReference) {
	    // TODO Auto-generated method stub
	        SimpleDateFormat     simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date today = new Date();
	        String dateOutput = simpleDateFormat.format(today);
	        List<UserTask> processingTimeData=new ArrayList<UserTask>();
	        List<UserTask> userReviedCounty=new ArrayList<UserTask>();
	        String processingTime="";
	    try{
	        Session session = sessionFactory.getCurrentSession();
	        Criteria countCriteria = session.createCriteria(UserTask.class);
	        countCriteria.add(Restrictions.and(Restrictions.eq("userName", selectedUser),Restrictions.eq("countyStateCode", countyStateReference)));
	        userReviedCounty=countCriteria.list();
	        int countyReviewCount=0;
	        for(UserTask time:userReviedCounty){
	            countyReviewCount=time.getCount();
	            Query updateQueryCount = session.createSQLQuery("update USER_TASKS set processCount="+countyReviewCount+" where username ='"+selectedUser+"'");
	            int updateQueryCountResult = updateQueryCount.executeUpdate();
	        }
	        if(currentStatus.equals("Ready For Processing")&&countyReviewCount==0){
	            Query query = session.createSQLQuery("update USER_TASKS set status ='"+currentStatus+"', comments='"+comments+"',processintime='"+dateOutput+"' where username ='"+selectedUser+"' and county_state_code='"+countyStateReference+"'");
	            int result = query.executeUpdate();
	        }
	        else if(currentStatus.equals("Completed")&&countyReviewCount==0){
	            Query query = session.createSQLQuery("update USER_TASKS set status ='"+currentStatus+"', comments='"+comments+"',completedtime='"+dateOutput+"',processCount=1 where username ='"+selectedUser+"'and county_state_code='"+countyStateReference+"'");
	            int result = query.executeUpdate();
	            Criteria criteria = session.createCriteria(UserTask.class);
	            criteria.add(Restrictions.and(Restrictions.eq("userName", selectedUser),Restrictions.eq("countyStateCode", countyStateReference)));
	            processingTimeData=criteria.list();
	            for(UserTask time:processingTimeData){
	                processingTime=time.getProcessingTime();
	            }
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            Date startedTime = format.parse(processingTime);
	            Date completedTime = format.parse(dateOutput);
	            long diff = completedTime.getTime() - startedTime.getTime();
	            long diffMinutes = diff / (60 * 1000) % 60;
	            long diffHours = diff / (60 * 60 * 1000) % 24;
	            long diffDays = diff / (24 * 60 * 60 * 1000);
	            String duration=diffDays+"D,"+diffHours+"H,"+diffMinutes+"M";
	            Query updateQuery = session.createSQLQuery("update USER_TASKS set duration='"+duration+"' where username ='"+selectedUser+"' and county_state_code='"+countyStateReference+"'");
	            int updateResult = updateQuery.executeUpdate();
	        }
	        else if(currentStatus.equals("Completed")){
	            countyReviewCount++;
	            Query updateQueryCount = session.createSQLQuery("update USER_TASKS set processCount="+countyReviewCount+" where username ='"+selectedUser+"' and county_state_code='"+countyStateReference+"'");
	            int updateQueryCountResult = updateQueryCount.executeUpdate();
	        }
	        if(signOffStatus.equals("Sign Off")){
	            Query query = session.createSQLQuery("update USER_TASKS set signoff ='"+signOffStatus+"' where username ='"+selectedUser+"' and county_state_code='"+countyStateReference+"'");
	            int result = query.executeUpdate();
	        }
	        }
	        catch (Exception e) {
	            log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
	            e.printStackTrace();
	        }
	    
	}*/

	/*@SuppressWarnings("unchecked")
	@Override
	public ArrayList<java.lang.Object> FetchStates(String selectQuery) {
		// TODO Auto-generated method stub
		ArrayList states=null;
		try{
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(selectQuery);
			states= (ArrayList<Object>) query.list();
			}
			catch (Exception e) {
				log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
				e.printStackTrace();
			}
		return states;
	}*/

/*	@Override
	public int FetchNoOfTablesPerCounty(String stateCountyEditionYear) {
		// TODO Auto-generated method stub
		int tableCount=0;
		try{
			String countQuery="select * from Assessor_Source.INFORMATION_SCHEMA.TABLES where TABLE_TYPE = 'BASE TABLE' and table_name like '%"+stateCountyEditionYear+"%'";
			Session session = sessionFactory.getCurrentSession();
			   SQLQuery query = session.createSQLQuery(countQuery);
			   tableCount=query.list().size();
			}
			catch (Exception e) {
				log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
				e.printStackTrace();
			}
		return tableCount;
	}*/

	

/**
 * code end here
 */
	/**
	 * chandu code starts here
	 * @param inputQuery
	 * @return
	 */
	/*	@Override
		public ArrayList<Object> FetchCounties(String inputQuery) {
			// TODO Auto-generated method stub
			ArrayList counties=null;
			try{
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(inputQuery);
			counties= (ArrayList<Object>) query.list();
			}
			catch (Exception e) {
				log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
				e.printStackTrace();
			}
			return counties;
		}*/

		/*@Override
		public ArrayList<Object> Fetchusers(String inputQuery) {
			// TODO Auto-generated method stub
			ArrayList users=null;
			try{
				Session session = sessionFactory.getCurrentSession();
				SQLQuery query = session.createSQLQuery(inputQuery);
				users= (ArrayList<Object>) query.list();
				}
				catch (Exception e) {
					log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
					e.printStackTrace();
				}
			return users;
		}*/

	/*	@Override
		public  void saveUserTaskmanagement(String userNeme,String county,String state,String editionYear,String assignedTime,String tableCount) {
			// TODO Auto-generated method stub
			try{
				Session session = sessionFactory.getCurrentSession();
				UserTask userTask = new UserTask();
				userTask.setcounty(county);
				userTask.setuserName(userNeme);
				userTask.setstatus("Assigned");
				userTask.setState(state);
				userTask.setEditionYear(editionYear);
			    userTask.setAssignedTime(new Date());
			    userTask.setStateCounty(state+"-"+county+"-"+editionYear);
			    userTask.setCount(0);
			    userTask.setTableCount(tableCount);
				session.save(userTask);
				}
				catch (Exception e) {
					log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
					e.printStackTrace();
				}
		}
*/
		@Override
		public List<LinkedHashMap<String, Object>> showUserTaskmanagement(String fromdate,String toDate) {
			// TODO Auto-generated method stub
			List<LinkedHashMap<String, Object>> data=new ArrayList<LinkedHashMap<String,Object>>();
			List<UserTask> userTaskList = null;
			try{
				String startDate=fromdate+" "+"00:00:00";
				String fronDate=toDate+" "+"23:59:59";
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
							Date gebDatum = df.parse(startDate);
					Date gebDatum1 = df.parse(fronDate);
					Session session = sessionFactory.getCurrentSession();
					Criteria criteria = session.createCriteria(UserTask.class);
					criteria.add(Restrictions.between("assignedtime",gebDatum,gebDatum1)).addOrder(Order.desc("status"));
					data=criteria.list();
				}
				catch (Exception e) {
					log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
					e.printStackTrace();
				}
			return data;
		}

		@Override
		public void showValidateUser(HttpServletRequest request,
				HttpServletResponse response, String username, String password) {
			// TODO Auto-generated method stub
			List<LoginCredentials> userList = null;
			try{
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(LoginCredentials.class);
				criteria.add(Restrictions.and(Restrictions.eq("userName", username),Restrictions.eq("password", password)));
				
				userList=criteria.list();
				HttpSession checkUserSession = request.getSession(true);
				String loginMessage = "";
				String menuAccess="";
					if(userList.size()>0){
						for(LoginCredentials usersdata:userList){
							menuAccess=usersdata.getMenuaccess();
						}
						checkUserSession.setAttribute("username", username.toString());
						checkUserSession.setAttribute("menuAccess",menuAccess);
						response.sendRedirect("index.jsp");
					}
					else{
						loginMessage = "Please enter valid details.";
						checkUserSession.setAttribute("message", loginMessage);
						response.sendRedirect("LoginPage.jsp");
					}
				
				}
				catch (Exception e) {
					log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
					e.printStackTrace();
				}
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<LinkedHashMap<String, Object>> UserReportfetchRecords(HttpServletRequest request, HttpServletResponse response, String userName) {
			// TODO Auto-generated method stub
			List<LinkedHashMap<String, Object>> data=new ArrayList<LinkedHashMap<String,Object>>();
			try{
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(UserTask.class);
				criteria.add(Restrictions.eq("userName",userName)).addOrder(Order.desc("status"));
				data=criteria.list();
				}
				catch (Exception e) {
					log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
					e.printStackTrace();
				}
			return data;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void UserReportInsertRecords(HttpServletRequest request, HttpServletResponse response, String comments,
		        String currentStatus,String selectedUser,String signOffStatus,String countyStateReference) {
		    // TODO Auto-generated method stub
		        SimpleDateFormat     simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        Date today = new Date();
		        String dateOutput = simpleDateFormat.format(today);
		        List<UserTask> processingTimeData=new ArrayList<UserTask>();
		        List<UserTask> userReviedCounty=new ArrayList<UserTask>();
		        String processingTime="";
		    try{
		        Session session = sessionFactory.getCurrentSession();
		        Criteria countCriteria = session.createCriteria(UserTask.class);
		        countCriteria.add(Restrictions.and(Restrictions.eq("userName", selectedUser),Restrictions.eq("countyStateCode", countyStateReference)));
		        userReviedCounty=countCriteria.list();
		        int countyReviewCount=0;
		        for(UserTask time:userReviedCounty){
		            countyReviewCount=time.getCount();
		            /*Query updateQueryCount = session.createSQLQuery("update USER_TASKS set processCount="+countyReviewCount+" where username ='"+selectedUser+"'");
		            int updateQueryCountResult = updateQueryCount.executeUpdate();*/
		        }
		        if(currentStatus.equals("Ready For Processing")&&countyReviewCount==0){
		            Query query = session.createSQLQuery("update USER_TASKS set status ='"+currentStatus+"', comments='"+comments+"',processintime='"+dateOutput+"' where username ='"+selectedUser+"' and county_state_code='"+countyStateReference+"'");
		            int result = query.executeUpdate();
		        }
		        else if(currentStatus.equals("Completed")&&countyReviewCount==0){
		            Query query = session.createSQLQuery("update USER_TASKS set status ='"+currentStatus+"', comments='"+comments+"',completedtime='"+dateOutput+"',processCount=1 where username ='"+selectedUser+"'and county_state_code='"+countyStateReference+"'");
		            int result = query.executeUpdate();
		            Criteria criteria = session.createCriteria(UserTask.class);
		            criteria.add(Restrictions.and(Restrictions.eq("userName", selectedUser),Restrictions.eq("countyStateCode", countyStateReference)));
		            processingTimeData=criteria.list();
		            for(UserTask time:processingTimeData){
		                processingTime=time.getProcessingTime();
		            }
		            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		            Date startedTime = format.parse(processingTime);
		            Date completedTime = format.parse(dateOutput);
		            long diff = completedTime.getTime() - startedTime.getTime();
		            long diffMinutes = diff / (60 * 1000) % 60;
		            long diffHours = diff / (60 * 60 * 1000) % 24;
		            long diffDays = diff / (24 * 60 * 60 * 1000);
		            String duration=diffDays+"D,"+diffHours+"H,"+diffMinutes+"M";
		            Query updateQuery = session.createSQLQuery("update USER_TASKS set duration='"+duration+"' where username ='"+selectedUser+"' and county_state_code='"+countyStateReference+"'");
		            int updateResult = updateQuery.executeUpdate();
		        }
		        else if(currentStatus.equals("Completed")){
		            countyReviewCount++;
		            Query updateQueryCount = session.createSQLQuery("update USER_TASKS set processCount="+countyReviewCount+" where username ='"+selectedUser+"' and county_state_code='"+countyStateReference+"'");
		            int updateQueryCountResult = updateQueryCount.executeUpdate();
		        }
		        if(signOffStatus.equals("Sign Off")){
		            Query query = session.createSQLQuery("update USER_TASKS set signoff ='"+signOffStatus+"' where username ='"+selectedUser+"' and county_state_code='"+countyStateReference+"'");
		            int result = query.executeUpdate();
		        }
		        }
		        catch (Exception e) {
		            log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
		            e.printStackTrace();
		        }
		    
		}

		@SuppressWarnings("unchecked")
		@Override
		public ArrayList<java.lang.Object> FetchStates(String selectQuery) {
			// TODO Auto-generated method stub
			ArrayList states=null;
			try{
				Session session = sessionFactory.getCurrentSession();
				SQLQuery query = session.createSQLQuery(selectQuery);
				states= (ArrayList<Object>) query.list();
				}
				catch (Exception e) {
					log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
					e.printStackTrace();
				}
			return states;
		}

		@Override
		public int FetchNoOfTablesPerCounty(String stateCountyEditionYear) {
			// TODO Auto-generated method stub
			int tableCount=0;
			try{
				String countQuery="select * from Assessor_Source.INFORMATION_SCHEMA.TABLES where TABLE_TYPE = 'BASE TABLE' and table_name like '%"+stateCountyEditionYear+"%'";
				Session session = sessionFactory.getCurrentSession();
				   SQLQuery query = session.createSQLQuery(countQuery);
				   tableCount=query.list().size();
				}
				catch (Exception e) {
					log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
					e.printStackTrace();
				}
			return tableCount;
		}
		/**
		 * chandu code ends here 
		 */
		@Override
		public String getUsedRUL(int fieldNumber, String territoryName, String edition) {
			// TODO Auto-generated method stub
			log4j.info("DataConversionDAOImpl - getUsedRUL :: Start : ");
			String usedRUL = "";
			List<String> usedColumnNames = new ArrayList<String>();
			String queryString = "";
			try {
				Session session = sessionFactory.openSession();
				//String queryString = "select COLUMNS from rule_query where RULE_ID in (select RULE_ID from rule_master where Field_Number = "+fieldNumber+" and rule_level = (select territory_id from territory where territory_name = '"+territoryName+"') and status=1)";
			   if(edition == null || !edition.isEmpty())
			   {
				 //  queryString = "select rq.RUL from rule_master rm,rule_query rq where rm.RULE_ID=rq.RULE_ID and rq.RULE_ID in (select RULE_ID from rule_master where Field_Number = "+fieldNumber+" and rule_level = (select territory_id from territory where territory_name = '"+territoryName+"') and status=1 and RUL like 'Load %') and rm.edition is NULL";  
				   queryString = "select rq.RUL from rule_master rm,rule_query rq where rm.RULE_ID=rq.RULE_ID and rq.RULE_ID in (select RULE_ID from rule_master where Field_Number = "+fieldNumber+" and rule_level = (select territory_id from territory where territory_name = '"+territoryName+"') and status=1) and (rm.edition is NULL or rm.edition='')";
			   }
			   else
			   {
				  // queryString = "select rq.RUL from rule_master rm,rule_query rq where rm.RULE_ID=rq.RULE_ID and rq.RULE_ID in (select RULE_ID from rule_master where Field_Number = "+fieldNumber+" and rule_level = (select territory_id from territory where territory_name = '"+territoryName+"') and status=1 and RUL like 'Load %') and rm.edition = "+edition+"";   
				   queryString = "select rq.RUL from rule_master rm,rule_query rq where rm.RULE_ID=rq.RULE_ID and rq.RULE_ID in (select RULE_ID from rule_master where Field_Number = "+fieldNumber+" and rule_level = (select territory_id from territory where territory_name = '"+territoryName+"') and status=1) and rm.edition = "+edition+"";
			   }
				
				SQLQuery sqlQuery = session.createSQLQuery(queryString);
				usedColumnNames = sqlQuery.list();
				if(usedColumnNames != null && !usedColumnNames.isEmpty())
				{
					if(usedColumnNames.size()>1)
					{
						usedRUL = "Please refer additional document";
					}else if(usedColumnNames.size()==1)
					{
						usedRUL = usedColumnNames.get(0);
					}	
				}
				
				//System.out.println(usedColumnNames.get(0));
			} catch (HibernateException e) {
				log4j.error("DataConversionDAOImpl - getUsedRUL :: Error " + e.getMessage());
			}
			log4j.info("DataConversionDAOImpl - getUsedRUL :: End : ");
			/*if(usedColumnNames != null && !usedColumnNames.isEmpty())
			    return usedColumnNames.get(0);
			else
				return "";*/
			return usedRUL;
		}
		/**
		 * chandu validation code 
		 */
		@Override
		public List<Object> saveSelectNumber(String inputQuery) {
			// TODO Auto-generated method stub
			log4j.info("DataConversionDAOImpl - executesavenumber:: Start : " + inputQuery);
			List<Object> checkRuleLevelList=null;
			try {
				log4j.info("DataConversionDAOImpl - query:: Start : " + inputQuery);
				Session session = sessionFactory.getCurrentSession();
				SQLQuery query = session.createSQLQuery(inputQuery);
				checkRuleLevelList= query.list();
			} catch (Exception e) {
				log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
				e.printStackTrace();
			}
			return checkRuleLevelList;
		}

		@Override
		public List<FnfLanCountries> getAllCountries() {
			log4j.info("DataConversionDAOImpl - getAllCountries :: Start");
			List<FnfLanCountries> countries = null;
			try {
				Session session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(FnfLanCountries.class);
				countries = (List<FnfLanCountries>)criteria.list();
				
			} catch (Exception e) {
				log4j.error("DataConversionDAOImpl - getAllCountries :: Error " + e.getMessage());
				//e.printStackTrace();
			}
			log4j.info("DataConversionDAOImpl - getAllCountries :: End"+countries.size());
			return countries;
		}

		
		@Override
		public List<FnfLanStates> getStatesForCountry(DataConversionDto dto) {
			log4j.info("DataConversionDAOImpl - getStatesForCountry :: Start");
			List<FnfLanStates> states = null;
			try {
				Session session = sessionFactory.openSession();
				String queryStr = "SELECT * FROM fnf_lan_states where COUNTRY_CODE = "+dto.getCountryFipsCode();
				SQLQuery query = session.createSQLQuery(queryStr).addEntity(FnfLanStates.class);
				states = (List<FnfLanStates>)query.list();
				
			} catch (Exception e) {
				log4j.error("DataConversionDAOImpl - getStatesForCountry :: Error " + e.getMessage());
				//e.printStackTrace();
			}
			log4j.info("DataConversionDAOImpl - getStatesForCountry :: End"+states.size());
			return states;
		}

		@Override
		public List<FnfLanCounties> getCountiesForState(DataConversionDto dto) {
			log4j.info("DataConversionDAOImpl - getCountiesForState :: Start");
			List<FnfLanCounties> counties = null;
			try {
				Session session = sessionFactory.openSession();
				String queryStr = "SELECT * FROM fnf_lan_counties where STATE_FIPS_CODE = "+dto.getStateFipsCode()+" AND STATE_FIPS_CODE in (select FIPS_CODE from fnf_lan_states where COUNTRY_CODE = "+dto.getCountryFipsCode()+")";
				SQLQuery query = session.createSQLQuery(queryStr).addEntity(FnfLanCounties.class);
				counties = (List<FnfLanCounties>)query.list();
				
			} catch (Exception e) {
				log4j.error("DataConversionDAOImpl - getCountiesForState :: Error " + e.getMessage());
				//e.printStackTrace();
			}
			log4j.info("DataConversionDAOImpl - getCountiesForState :: End"+counties.size());
			return counties;
		}

		@Override
		public List<SourceVerificationAnswer> getSourceVerificationAnswers(DataConversionDto dataConversionDto) {
			log4j.info("DataConversionDAOImpl - getSourceVerificationAnswers :: Start");
			List<SourceVerificationAnswer> answerList = null;
			try {
				Session session = sessionFactory.openSession();
				String queryStr = "select * from source_verification_answer where COUNTY_FIPS_CODE = "+dataConversionDto.getCountyFipsCode()+" and STATE_FIPS_CODE = "+dataConversionDto.getStateFipsCode()+" and COUNTRY_FIPS_CODE = "+dataConversionDto.getCountryFipsCode()+" and EDITION = '"+dataConversionDto.getEdition()+"'";
				SQLQuery query = session.createSQLQuery(queryStr).addEntity(SourceVerificationAnswer.class);
				answerList = (List<SourceVerificationAnswer>)query.list();
				
			} catch (Exception e) {
				log4j.error("DataConversionDAOImpl - getSourceVerificationAnswers :: Error " + e.getMessage());
				//e.printStackTrace();
			}
			log4j.info("DataConversionDAOImpl - getSourceVerificationAnswers :: End"+answerList.size());
			return answerList;
		}

		@Override
		public boolean saveSourceVerificationResult(ExcelQuestionAnswerDto exclQADto) {
			log4j.info("DataConversionDAOImpl - saveSourceVerificationResult :: Start");
			boolean flag = false;
			try {
				Session session = sessionFactory.openSession();
				txn = session.beginTransaction();
				for(int i=1; i<=10; i++){
					SourceVerificationAnswer srcAnswer = new SourceVerificationAnswer();
					srcAnswer.setCountryFipsCode(exclQADto.getCountryFipsCode());
					srcAnswer.setStateFipsCode(exclQADto.getStateFipsCode());
					srcAnswer.setCountyFipsCode(exclQADto.getCountyFipsCode());
					srcAnswer.setQuestionId(i);
					srcAnswer.setEdition(exclQADto.getEdition());
					if(i==1){
						srcAnswer.setAnswer(exclQADto.getQ1());
					}else if(i==2){
						srcAnswer.setAnswer(exclQADto.getQ2());
					}else if(i==3){
						srcAnswer.setAnswer(exclQADto.getQ3());
					}else if(i==4){
						srcAnswer.setAnswer(exclQADto.getQ4());
					}else if(i==5){
						srcAnswer.setAnswer(exclQADto.getQ5());
					}else if(i==6){
						srcAnswer.setAnswer(exclQADto.getQ6_a());
					}else if(i==7){
						srcAnswer.setAnswer(exclQADto.getQ6_b());
					}else if(i==8){
						srcAnswer.setAnswer(exclQADto.getQ7());
					}else if(i==9){
						srcAnswer.setAnswer(exclQADto.getQ8());
					}else if(i==10){
						srcAnswer.setAnswer(exclQADto.getQ9());
					}else{
						
					}
					session.save(srcAnswer);
				    if ( i % 5 == 0 ) { 
				        session.flush();
				        session.clear();
				    }
				}
				txn.commit();
				flag = true;
			} catch (Exception e) {
				txn.rollback();
				log4j.error("DataConversionDAOImpl - saveSourceVerificationResult :: Error " + e.getMessage());
				//e.printStackTrace();
			}
			log4j.info("DataConversionDAOImpl - saveSourceVerificationResult :: End"+flag);
			return flag;
		}

		@Override
		public List<FnfLanCountyTables> tablesRecordCount(String stateCode, String countyName, String editon) {
			log4j.info("DataConversionDAOImpl - tablesRecordCount :: Start ");
			List<FnfLanCountyTables> tablesRecordCount = new ArrayList<FnfLanCountyTables>();
			List<String> countyTableNames = new ArrayList<String>();
					if(editon.length() == 1)
						editon = "0"+editon;
			try{
					String queryStr="select table_name from Assessor_Source.INFORMATION_SCHEMA.TABLES where TABLE_TYPE = 'BASE TABLE' and table_name like '%"+stateCode+"_"+countyName+"_"+editon+"%'";
					Session session = sessionFactory.getCurrentSession();
					SQLQuery sqlQuery = session.createSQLQuery(queryStr);
					countyTableNames = sqlQuery.list();
					for(String tableName : countyTableNames){
						FnfLanCountyTables countyTable = new FnfLanCountyTables();
						countyTable.setOracleTableName(tableName);
						countyTable.setTableRecordCount(getTableRecordCount(tableName));
						tablesRecordCount.add(countyTable);
					}
					
				}
				catch (Exception e) {
					log4j.error("DataConversionDAOImpl - tablesRecordCount :: Error " + e.getMessage());
				}
			log4j.info("DataConversionDAOImpl - tablesRecordCount :: End "+tablesRecordCount.size());
			return tablesRecordCount;
		}

		private int getTableRecordCount(String tableName) {
			log4j.info("DataConversionDAOImpl - getTableRecordCount :: Start ");
			int recordCount = 0;
			try{
				String queryStr="select * from "+tableName.trim();
				Session session = sessionFactory.openSession();
				SQLQuery sqlQuery = session.createSQLQuery(queryStr);
				List<Object[]> list = sqlQuery.list();
				recordCount = list.size();
			}
			catch (Exception e) {
				log4j.error("DataConversionDAOImpl - getTableRecordCount :: Error " + e.getMessage());
			}
			log4j.info("DataConversionDAOImpl - getTableRecordCount :: End "+recordCount);
			return recordCount;
		}

		@Override
		public List<List<LinkedHashMap<String, Object>>> joinConditionTablesList(String tableData) {
			// TODO Auto-generated method stub
			log4j.info("DataConversionDAOImpl - joinConditionTablesList :: Start : " + "'"+tableData+"'");
			@SuppressWarnings("rawtypes")
			List joinsCondition = null;
			try {
				Session session = sessionFactory.openSession();
				String hql = "select table_name from INFORMATION_SCHEMA.TABLES where TABLE_TYPE = 'BASE TABLE' and table_name like '%"+tableData+"%'";
				SQLQuery query = session.createSQLQuery(hql);
				log4j.info("DataConversionDAOImpl - joinConditionTablesList :: Query" + query);
				joinsCondition = query.list();
				log4j.info("DataConversionDAOImpl - joinConditionTablesList :: execute" + joinsCondition);
			} catch (Exception e) {
				log4j.error("DataConversionDAOImpl - joinConditionTablesList :: Error " + e.getMessage());
				e.printStackTrace();
			}
			log4j.info("DataConversionDAOImpl - joinConditionTablesList :: End");
			return joinsCondition;
		}

		@Override
		public int getCountOfJoinConditon(String checkingQuery) {
			// TODO Auto-generated method stub
			log4j.info("DataConversionDAOImpl - getCountOfJoinConditon :: Start : " + "'"+checkingQuery+"'");
			
			int countOfRows = 0;
			try{
				Session session = sessionFactory.openSession();
				String hql = checkingQuery;
				hql = checkingQuery;
				SQLQuery query = session.createSQLQuery(hql);
				log4j.info("DataConversionDAOImpl - getCountOfJoinConditon :: Query" + query);
				List<Object> list = query.list();
				String count = list.get(0).toString();
				countOfRows = Integer.parseInt(count);
				//countOfRows = query.getFetchSize();
				log4j.info("DataConversionDAOImpl - getCountOfJoinConditon :: execute" + countOfRows);
			}
			catch (Exception e) {
				log4j.error("DataConversionDAOImpl - getCountOfJoinConditon :: Error " + e.getMessage());
				e.printStackTrace();
			}
			log4j.info("DataConversionDAOImpl - getCountOfJoinConditon :: End");
			return countOfRows;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void insertJoinCondition(String selectedQuery, String tablesSelectedForJoins) {
			// TODO Auto-generated method stub
			log4j.info("DataConversionDAOImpl - getCountOfJoinConditon :: selectedQuery : " + "'"+selectedQuery+"'" +"tablesSelectedForJoins" + tablesSelectedForJoins);
			
			try{
				Session session = sessionFactory.openSession();
				String countQuery = "select count(*) from table_join_conditions";
				SQLQuery query = session.createSQLQuery(countQuery);
				List<Object> list = query.list();
				String count = list.get(0).toString();
				int countOfRows = Integer.parseInt(count);
				session.beginTransaction();
				
				String queryForExistingTables = "select count(*) from table_join_conditions where [JOIN_TABLES] = '"+tablesSelectedForJoins+"'";
				SQLQuery queryExisiting = session.createSQLQuery(queryForExistingTables);
				List<Object> resultList = queryExisiting.list();
				String checkCount = resultList.get(0).toString();
				int dupCountOfRows = Integer.parseInt(checkCount);
				
				
				if(dupCountOfRows > 0){
					SQLQuery updateQuery = session.createSQLQuery("" +"update table_join_conditions set [JOIN_CONDITIONS] = '"+selectedQuery+"' where [JOIN_TABLES] = '"+tablesSelectedForJoins+"'");
					updateQuery.executeUpdate();
					session.getTransaction().commit();
					log4j.info("DataConversionDAOImpl - getCountOfJoinConditon :: Query" + updateQuery);
				}
				else{
					SQLQuery insertQuery = session.createSQLQuery("" +"INSERT INTO table_join_conditions VALUES(?,?,?)");
					countOfRows = countOfRows+1;
					insertQuery.setParameter(0, countOfRows);
					insertQuery.setParameter(1, tablesSelectedForJoins);
					insertQuery.setParameter(2, selectedQuery);
					insertQuery.executeUpdate();
					session.getTransaction().commit();
					log4j.info("DataConversionDAOImpl - getCountOfJoinConditon :: Query" + insertQuery);
				}
			}
			
			catch (Exception e) {
				log4j.error("DataConversionDAOImpl - getCountOfJoinConditon :: Error " + e.getMessage());
				e.printStackTrace();
			}
			log4j.info("DataConversionDAOImpl - getCountOfJoinConditon :: End");
		}

		@Override
		public int getCheckingTablesListResult(String tablesSelectedForJoinsString) {
			// TODO Auto-generated method stub
			log4j.info("DataConversionDAOImpl - getCheckingTablesListResult :: Start : " + "'"+tablesSelectedForJoinsString+"'");
			
			int countOfTables = 0;
			try{
				Session session = sessionFactory.openSession();
				String hql = "select count(*) from table_join_conditions where JOIN_TABLES = '"+tablesSelectedForJoinsString+"'";
				SQLQuery query = session.createSQLQuery(hql);
				log4j.info("DataConversionDAOImpl - getCheckingTablesListResult :: Query" + query);
				List<Object> list = query.list();
				String count = list.get(0).toString();
				countOfTables = Integer.parseInt(count);
				//countOfRows = query.getFetchSize();
				log4j.info("DataConversionDAOImpl - getCheckingTablesListResult :: execute" + countOfTables);
			}
			catch (Exception e) {
				log4j.error("DataConversionDAOImpl - getCheckingTablesListResult :: Error " + e.getMessage());
				e.printStackTrace();
			}
			log4j.info("DataConversionDAOImpl - getCheckingTablesListResult :: End");
			return countOfTables;
		}


		@Override
		public List<List<LinkedHashMap<String, Object>>> getTheJoinConditionListResult(String selectedTableColumns) {
			log4j.info("DataConversionDAOImpl - getCheckingTablesListResult :: Start : " + "'"+selectedTableColumns+"'");
			
			List<List<LinkedHashMap<String, Object>>> list = null;
			try{
				Session session = sessionFactory.openSession();
				String hql = "select JOIN_CONDITIONS from table_join_conditions where JOIN_TABLES = '"+selectedTableColumns+"'";
				SQLQuery query = session.createSQLQuery(hql);
				log4j.info("DataConversionDAOImpl - getCheckingTablesListResult :: Query" + query);
				list = query.list();
				//countOfRows = query.getFetchSize();
				log4j.info("DataConversionDAOImpl - getCheckingTablesListResult :: execute" + list);
			}
			catch (Exception e) {
				log4j.error("DataConversionDAOImpl - getCheckingTablesListResult :: Error " + e.getMessage());
				e.printStackTrace();
			}
			log4j.info("DataConversionDAOImpl - getCheckingTablesListResult :: End");
			return list;
		}		
		/**
		 * @author Sushanta 
		 * This method inserting data into rule_master & rule_query  tables for multiple fields.
		 * @param List<RuleQuery>
		 * @exception HibernateException
		 * @return true/false.
		 */
		@Override
		public boolean saveMultipleFieldRules(List<RuleQuery> fieldsRuleQueryList) {
			log4j.info("DataConversionDAOImpl - saveMultipleFieldRules :: Start :: "+fieldsRuleQueryList.size());
			boolean flag = false;
			try {
				Session session = sessionFactory.openSession();
				txn = session.beginTransaction();
				// session.save(ruleQuery.getRuleMaster());
				for(RuleQuery ruleQuery : fieldsRuleQueryList){
					log4j.info("Field Number"+ruleQuery.getRuleMaster().getFieldNumber());
					session.save(ruleQuery);
					//session.flush();
			        //session.clear();
				}
				txn.commit();
				flag = true;
				session.close();
			} catch (HibernateException e) {
				txn.rollback();
				log4j.error("DataConversionDAOImpl - saveMultipleFieldRules :: Error " + e.getMessage());
			}
			log4j.info("DataConversionDAOImpl - saveMultipleFieldRules :: End");
			return flag;
		}
		
		/**
		 * @author Srikanth Tummapudi
		 * This selects the all countries from database
		 * 
		 * @param 
		 * @return countryList
		 */
		@Override
		public List<Country> getCountryList() {
			log4j.info("DataConversionDAOImpl - getCountryList :: Start");
			List<Country> countryList = null;
			try {
				Session session = sessionFactory.getCurrentSession();
				Criteria query = session.createCriteria(Country.class);
				query.addOrder(Order.asc("countryName"));
				countryList = query.list(); 
			} catch (HibernateException e) {
				log4j.error("DataConversionDAOImpl - getCountryList :: Error " + e.getMessage());
			}
			log4j.info("DataConversionDAOImpl - getStateList :: countryListSize------>" + countryList.size());
			log4j.info("DataConversionDAOImpl - getCountryList :: End");
			
			return countryList;
		}

		/**
		 * @author Srikanth Tummapudi
		 * This selects the all the states for a specific country from database
		 * 
		 * @param countryCode
		 * @return stateList
		 */
		@Override
		public List<State> getStateList(Long countryCode) {
			log4j.info("DataConversionDAOImpl - getStateList :: Start");
			List<State> stateList = null;
			try {
				Session session = sessionFactory.getCurrentSession();
				Criteria query = session.createCriteria(State.class);
				query.addOrder(Order.asc("stateName"));
				query.add(Restrictions.eq("countryCode", countryCode));
				stateList = query.list(); 
			} catch (HibernateException e) {
				log4j.error("DataConversionDAOImpl - getStateList :: Error " + e.getMessage());
			}
			log4j.info("DataConversionDAOImpl - getStateList :: stateListSize------>" + stateList.size());
			log4j.info("DataConversionDAOImpl - getStateList :: End");
			return stateList;
		}

		/**
		 * @author Srikanth Tummapudi
		 * This selects the all the counties for a specific state from database
		 * 
		 * @param stateFipsCode
		 * @return countyList
		 */
		@Override
		public List<County> getCountyList(Long stateFipsCode) {
			log4j.info("DataConversionDAOImpl - getCountyList :: Start");
			List<County> countyList = null;
			try {
				Session session = sessionFactory.getCurrentSession();
				Criteria query = session.createCriteria(County.class);
				query.addOrder(Order.asc("countyName"));
				query.add(Restrictions.eq("stateFipsCode", stateFipsCode));
				countyList = query.list(); 
			} catch (HibernateException e) {
				log4j.error("DataConversionDAOImpl - getCountyList :: Error " + e.getMessage());
			}
			log4j.info("DataConversionDAOImpl - getCountyList :: countyListSize------>" + countyList.size());
			log4j.info("DataConversionDAOImpl - getCountyList :: End");
			return countyList;
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<FieldMappingTable> getFieldMappingForParentFieds(Integer fieldNumber) {
			// TODO Auto-generated method stub
			List<FieldMappingTable> fieldList = null;
			try{
				Session session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(FieldMappingTable.class);
				String hql = "select Dependent_Column from Field_MappingTables where Common_Column="+fieldNumber;
				Query query = session.createSQLQuery(hql);
				fieldList = query.list();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return fieldList;
		}

		

		/**
		 * @author Sushanta 
		 * This method creates Table .
		 * @param String tableName
		 * @exception HibernateException
		 * @return void
		 */
		@Override
		public void createIndividualLan23(String tableName) {
			log4j.info("DataConversionDAOImpl - createIndividualLan23 :: Start");
			try{
				Session session = sessionFactory.openSession();
				SQLQuery query = session.createSQLQuery("CREATE TABLE [dbo].["+tableName+"]([APPRCL] [varchar](100) NULL,[STATEPOSTALCODE] [varchar](500) NULL,"
						+ "[COUNTYNAME] [varchar](500) NULL,[APNORPINNUMBER] [varchar](500) NULL,[DUPLICATEAPNMULTIADDRESSID] [varchar](500) NULL,"
						+ "[PROPADDRSRCEFLAG] [varchar](500) NULL,[PROPHOUSENUMBER] [varchar](500) NULL,[PROPHOUSEALPHA] [varchar](500) NULL,"
						+ "[PROPSTREETDIRECTIONLEFT] [varchar](500) NULL,[PROPSTREETNAME] [varchar](500) NULL,[PROPSTREETSUFFIX] [varchar](500) NULL,"
						+ "[PROPSTREETDIRECTIONRIGHT] [varchar](500) NULL,[PROPUNITNUMBER] [varchar](500) NULL,[PROPFULLSTREETADDRESS] [varchar](500) NULL,"
						+ "[PROPCITYNAME] [varchar](500) NULL,[PROPSTATE] [varchar](500) NULL,[PROPZIPCODE] [varchar](500) NULL,[PROPZIP4] [varchar](500) NULL,"
						+ "[ASSESSEEOWNERNAME] [varchar](500) NULL,[SECOND_ASSESSEEOWNERNAME] [varchar](500) NULL,[ASSEVESTINGIDCODE] [varchar](500) NULL,"
						+ "[TAXACCOUNTNUMBER] [varchar](500) NULL,[ASSEMAILCAREOFNAME] [varchar](500) NULL,[ASSEMAILHOUSENUMBER] [varchar](150) NULL,"
						+ "[ASSEMAILHOUSEALPHA] [varchar](500) NULL,[ASSEMAILSTREETDIRECTIONLEFT] [varchar](500) NULL,[ASSEMAILSTREETNAME] [varchar](500) NULL,"
						+ "[ASSEMAILSTREETSUFFIX] [varchar](500) NULL,[ASSEMAILSTREETDIRECTIONRIGHT] [varchar](500) NULL,[ASSEMAILUNITNUMBER] [varchar](500) NULL,"
						+ "[ASSEMAILFULLSTREETADDRESS] [varchar](500) NULL,[ASSEMAILCITYNAME] [varchar](500) NULL,[ASSEMAILSTATE] [varchar](500) NULL,"
						+ "[ASSEMAILZIPCODE] [varchar](500) NULL,[ASSEMAILZIP4] [varchar](500) NULL,[STATELANDUSECODE] [varchar](500) NULL,"
						+ "[OWNEROCCUPIEDSFRCONDO] [varchar](500) NULL,[ASSESSEDLANDVALUE] [varchar](500) NULL,[ASSESSEDIMPROVEMENTVALUE] [varchar](500) NULL,"
						+ "[TOTALASSESSEDVALUE] [varchar](500) NULL,[ASSESSMENTYEAR] [varchar](500) NULL,[CALIFHOMEOWNERSEXEMPTION] [varchar](500) NULL,"
						+ "[TAXEXEMPTIONCODE] [varchar](500) NULL,[TAXRATECODEAREA] [varchar](500) NULL,[TAXAMOUNT] [varchar](500) NULL,[TAXYEAR] [varchar](500) NULL,"
						+ "[TAXDELINQUENTYEAR] [varchar](500) NULL,[RECORDERSDOCUMENTNUMBER] [varchar](500) NULL,[RECORDERSBOOKNUMBER] [varchar](500) NULL,"
						+ "[RECORDERSPAGENUMBER] [varchar](500) NULL,[RECORDINGDATE] [varchar](500) NULL,[DOCUMENTTYPECOUNTYDESCRIPTION] [varchar](500) NULL,"
						+ "[SALESPRICE] [varchar](500) NULL,[SALESPRICECODE] [varchar](500) NULL,[PRIORSALEDATE] [varchar](500) NULL,"
						+ "[PRIORSALESPRICE] [varchar](500) NULL,[PRIORSALESCODE] [varchar](500) NULL,[LEGALLOTCODE] [varchar](500) NULL,"
						+ "[LEGALLOTNUMBER] [varchar](500) NULL,[LEGALLANDLOT] [varchar](500) NULL,[LEGALBLOCK] [varchar](500) NULL,"
						+ "[LEGALSECTION] [varchar](500) NULL,[LEGALDISTRICT] [varchar](500) NULL,[LEGALUNIT] [varchar](500) NULL,"
						+ "[LEGALCITYMUNICIPALITYTOWNSHIP] [varchar](500) NULL,[LEGALSUBDIVISIONNAME] [varchar](500) NULL,[LEGALPHASENO] [varchar](500) NULL,"
						+ "[LEGALTRACTNO] [varchar](500) NULL,[LEGALSECTWPRNGMER] [varchar](500) NULL,[LEGALBRIEFDESCRIPTION] [varchar](200) NULL,"
						+ "[LEGALASSESSORSMAPREF] [varchar](500) NULL,[COUNTYLANDUSEDESCRIPTION] [varchar](500) NULL,[COUNTYLANDUSECODE] [varchar](500) NULL,"
						+ "[STANDARDIZEDLANDUSECODE] [varchar](500) NULL,[TIMESHARECODE] [varchar](500) NULL,[ZONING] [varchar](500) NULL,"
						+ "[LOTSIZEORAREA] [varchar](500) NULL,[BUILDINGAREA] [varchar](500) NULL,[YEARBUILT] [varchar](500) NULL,"
						+ "[NOOFBUILDINGS] [varchar](500) NULL,[NOOFSTORIES] [varchar](500) NULL,[NOOFUNITS] [varchar](500) NULL,"
						+ "[TOTALNOOFROOMS] [varchar](500) NULL,[NOOFBEDROOMS] [varchar](500) NULL,[NOOFBATHS] [varchar](500) NULL,"
						+ "[NOOFPARTIALBATHS] [varchar](500) NULL,[GARAGETYPEPARKING] [varchar](500) NULL,[PARKINGNUMBEROFCARS] [varchar](500) NULL,"
						+ "[POOL] [varchar](500) NULL,[MAILCITYSTZIP] [varchar](500) NULL,[FIPSCODE] [varchar](500) NULL,[TAPECUTDATE] [varchar](500) NULL,"
						+ "[CENSUSTRACT] [varchar](500) NULL,[RECORDTYPE] [varchar](500) NULL,[MARKETVALUELAND] [varchar](500) NULL,"
						+ "[MARKETVALUEIMPROVEMENT] [varchar](500) NULL,[TOTALMARKETVALUE] [varchar](500) NULL,[MARKETVALUEYEAR] [varchar](500) NULL,"
						+ "[BUILDINGCLASS] [varchar](500) NULL,[STYLE] [varchar](500) NULL,[TYPECONSTRUCTION] [varchar](500) NULL,"
						+ "[EXTERIORWALLS] [varchar](500) NULL,[FOUNDATION] [varchar](500) NULL,[ROOFCOVER] [varchar](500) NULL,[HEATING] [varchar](500) NULL,"
						+ "[AIRCONDITIONING] [varchar](500) NULL,[ELEVATOR] [varchar](500) NULL,[FIREPLACE] [varchar](500) NULL,[BASEMENT] [varchar](500) NULL,"
						+ "[EDITIONNUMBER] [varchar](500) NULL,[PROPERTYCOUNTRYCODE] [varchar](500) NULL,[BUILDINGAREAINDICATOR] [varchar](500) NULL,"
						+ "[PROPADDRMATCHCODE] [varchar](500) NULL,[PROPADDRUNITDESIGNATOR] [varchar](500) NULL,[PROPADDRUNITNUMBER] [varchar](500) NULL,"
						+ "[PROPADDRCARRIERROUTE] [varchar](500) NULL,[PROPADDRGEOCODEMATCHCODE] [varchar](500) NULL,[PROPADDRLATITUDE] [varchar](500) NULL,"
						+ "[PROPADDRLONGITUDE] [varchar](500) NULL,[PROPADDRCENSUSTRACTBLOCK] [varchar](500) NULL,[MAILADDRMATCHCODE] [varchar](500) NULL,"
						+ "[MAILADDRUNITDESIGNATOR] [varchar](500) NULL,[MAILADDRUNITNUMBER] [varchar](500) NULL,[MAILADDRCARRIERROUTE] [varchar](500) NULL,"
						+ "[ASSESSEEOWNERNAMEINDICATOR] [varchar](500) NULL,[SECOND_ASSESSEEOWNER_INDICATOR] [varchar](500) NULL,"
						+ "[MAILCAREOFNAMEINDICATOR] [varchar](500) NULL,[ASSESSEEOWNERNAMETYPE] [varchar](500) NULL,[SECONDASSESSEEOWNERNAMETYPE] [varchar](500) NULL,"
						+ "[ALTOLDAPNINDICATOR] [varchar](500) NULL,[CERTIFICATIONDATE] [varchar](500) NULL,[LOTSIZESQFT] [varchar](500) NULL,"
						+ "[BUILDINGQUALITY] [varchar](500) NULL,[FLOORCOVER] [varchar](500) NULL,[NOOFPLUMBINGFIXTURES] [varchar](500) NULL,"
						+ "[BUILDINGAREA1] [varchar](500) NULL,[BUILDINGAREA1INDICATOR] [varchar](500) NULL,[BUILDINGAREA2] [varchar](500) NULL,"
						+ "[BUILDINGAREA2INDICATOR] [varchar](500) NULL,[BUILDINGAREA3] [varchar](500) NULL,[BUILDINGAREA3INDICATOR] [varchar](500) NULL,"
						+ "[BUILDINGAREA4] [varchar](500) NULL,[BUILDINGAREA4INDICATOR] [varchar](500) NULL,[BUILDINGAREA5] [varchar](500) NULL,"
						+ "[BUILDINGAREA5INDICATOR] [varchar](500) NULL,[BUILDINGAREA6] [varchar](500) NULL,[BUILDINGAREA6INDICATOR] [varchar](500) NULL,"
						+ "[BUILDINGAREA7] [varchar](500) NULL,[BUILDINGAREA7INDICATOR] [varchar](500) NULL,[EFFECTIVEYEARBUILT] [varchar](500) NULL,"
						+ "[HEATINGFUELTYPE] [varchar](500) NULL,[AIRCONDITIONINGTYPE] [varchar](500) NULL,[LOTSIZEACRES] [varchar](500) NULL,"
						+ "[MORTGAGELENDERNAME] [varchar](500) NULL,[INTERIORWALLS] [varchar](500) NULL,[SCHOOLTAXDISTRICT1] [varchar](500) NULL,"
						+ "[SCHOOLTAXDISTRICT1INDICATOR] [varchar](500) NULL,[SCHOOLTAXDISTRICT2] [varchar](500) NULL,[SCHOOLTAXDISTRICT2INDICATOR] [varchar](500) NULL,"
						+ "[SCHOOLTAXDISTRICT3] [varchar](500) NULL,[SCHOOLTAXDISTRICT3INDICATOR] [varchar](500) NULL,[SITEINFLUENCE] [varchar](500) NULL,"
						+ "[AMENITIES] [varchar](500) NULL,[OTHERIMPRBUILDINGINDICATOR1] [varchar](500) NULL,[OTHERIMPRBUILDINGINDICATOR2] [varchar](500) NULL,"
						+ "[OTHERIMPRBUILDINGINDICATOR3] [varchar](500) NULL,[OTHERIMPRBUILDINGINDICATOR4] [varchar](500) NULL,"
						+ "[NEIGHBORHOODCODE] [varchar](500) NULL,[CONDOPROJECTBUILDINGNAME] [varchar](500) NULL,"
						+ "[OTHERIMPRBUILDINGINDICATOR5] [varchar](500) NULL,[AMENITIES2] [varchar](500) NULL,"
						+ "[OTHERIMPRBUILDINGAREA1] [varchar](500) NULL,[OTHERIMPRBUILDINGAREA2] [varchar](500) NULL,"
						+ "[OTHERIMPRBUILDINGAREA3] [varchar](500) NULL,[OTHERIMPRBUILDINGAREA4] [varchar](500) NULL,"
						+ "[OTHERIMPRBUILDINGAREA5] [varchar](500) NULL,[OTHERROOMS] [varchar](500) NULL,[EXTRAFEATURES1AREA] [varchar](500) NULL,"
						+ "[EXTRAFEATURES1INDICATOR] [varchar](500) NULL,[TOPOGRAPHY] [varchar](500) NULL,[ROOFTYPE] [varchar](500) NULL,"
						+ "[EXTRAFEATURES2AREA] [varchar](500) NULL,[EXTRAFEATURES2INDICATOR] [varchar](500) NULL,[EXTRAFEATURES3AREA] [varchar](500) NULL,"
						+ "[EXTRAFEATURES3INDICATOR] [varchar](500) NULL,[EXTRAFEATURES4AREA] [varchar](500) NULL,[EXTRAFEATURES4INDICATOR] [varchar](500) NULL,"
						+ "[OLDAPN] [varchar](500) NULL,[BUILDINGCONDITION] [varchar](500) NULL,[LOTSIZEFRONTAGEFEET] [varchar](500) NULL,[LOTSIZEDEPTHFEET] [varchar](500) NULL,"
						+ "[COMMENTS] [varchar](500) NULL,[WATER] [varchar](500) NULL,[SEWER] [varchar](500) NULL,[NEWRECORDTYPE] [varchar](500) NULL)");
			query.executeUpdate();
			}
			catch(Exception e){
				log4j.info("DataConversionDAOImpl - createIndividualLan23 :: Error :: "+e.getMessage());
			}
			log4j.info("DataConversionDAOImpl - createIndividualLan23 :: End");
			
		}

		@Override
		public List<RuleQuery> getFieldRules(DataConversionRuleDto dataConversionRuleDto) {
			log4j.info("DataConversionDAOImpl - getFieldRules :: Start");
			List<RuleQuery> fieldRuleList = new ArrayList<RuleQuery>();
			try {
				Session session = sessionFactory.openSession();
				String queryStr = "select * from (select rq.RULE_QUERY_ID, rq.RUL, rq.DELETED, rq.QUERY, rq.basequery, rq.TABLES, rq.COLUMNS,rq.BaseQueryRuleHint,rq.buildbasequery,rq.buildrulequery,rq.ruleColumn, rm.*, t.*, ROW_NUMBER() OVER (ORDER BY FIELD_NUMBER) as ROWNUM from rule_query rq, rule_master rm, territory t where rm.RULE_ID=rq.RULE_ID and t.TERRITORY_ID = rm.rule_level and rm.rule_level in"
						+ " (SELECT TERRITORY_ID FROM territory where FIPS_CODE = "+dataConversionRuleDto.getCountyFipsCode()+" and PARENT_ID in (select TERRITORY_ID from territory where FIPS_CODE = "+dataConversionRuleDto.getStateFipsCode()+" and PARENT_ID in "
						+ "(select TERRITORY_ID from territory where FIPS_CODE = "+dataConversionRuleDto.getCountryFipsCode()+"))) and FIELD_NUMBER = "+dataConversionRuleDto.getFieldNumber()+" and status=1 and deleted=0 and EDITION = "+dataConversionRuleDto.getEdition()+") a ";
				log4j.info("DataConversionDAOImpl - getFieldRules :: query :: " + queryStr);
				SQLQuery query = session.createSQLQuery(queryStr).addEntity(RuleQuery.class);
				fieldRuleList = query.list();
				log4j.info("DataConversionDAOImpl - getFieldRules :: size :: " + fieldRuleList.size());
			} catch (Exception e) {
				log4j.info("DataConversionDAOImpl - getFieldRules :: Error :: "+e.getMessage());
			}
			log4j.info("DataConversionDAOImpl - getFieldRules :: End");
			return fieldRuleList;
		}
			public String showCreateUsers(HttpServletRequest request, HttpServletResponse response, String createusername,
				String createpassword,String menuaccess) {
			List<LoginCredentials> userList = null;
			try{
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(LoginCredentials.class);
				criteria.add(Restrictions.and(Restrictions.eq("userName", createusername),Restrictions.eq("password", createpassword)));
				LoginCredentials login=new LoginCredentials();
				userList=criteria.list();
				int id=login.getEmpid();
				HttpSession checkUserSession = request.getSession(true);
				String loginMessage = "";
				String menuAccess="";
				int empid;
					if(userList.size()>0){
						for(LoginCredentials usersdata:userList){
						System.out.println("Error occured while entering details");
						checkUserSession.setAttribute("username", createusername.toString());
						menuAccess=usersdata.getMenuaccess();
						empid=usersdata.getEmpid();
					}
					}
					else{
						login.setuserName(createusername);
						login.setPassword(createpassword);
						login.setMenuaccess(menuaccess);
						session.save(login);
						System.out.println("Created Successfully");

					}
				}
				catch (Exception e) {
					log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
					e.printStackTrace();
				}
			return createusername;
		}
		
		
		public String updatePassword(HttpServletRequest request, HttpServletResponse response, String createusername,
				String createpassword) {
			List<LoginCredentials> userList = null;
			try{
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(LoginCredentials.class);
				criteria.add(Restrictions.and(Restrictions.eq("userName", createusername)));
				LoginCredentials login=new LoginCredentials();
				userList=criteria.list();
				HttpSession checkUserSession = request.getSession(true);
				String loginMessage = "";
				String menuAccess="";
				//int empid=(int)((LoginCredentials)userList.get(0)).getEmpid();
				
				if(userList.size()>0){
					    /*login.setEmpid(empid++);
						login.setuserName(createusername);
						login.setPassword(createpassword);*/
						Query query = session.createSQLQuery("update LOGIN_CREDENTIALS set PASSWORD='"+createpassword+"' where USER_NAME='"+createusername+"'");
						//query.setParameter("password", createpassword);
						//query.setParameter("username", createusername);
						query.executeUpdate();

/*						session.merge(login);
*/						
			
						System.out.println("Updated Successfully");
						response.sendRedirect("index.jsp");
						/*for(LoginCredentials usersdata:userList){
						System.out.println("Error occured while entering details");
						checkUserSession.setAttribute("username", createusername.toString());
						menuAccess=usersdata.getMenuaccess();
						empid=usersdata.getEmpid();
						response.sendRedirect("index.jsp");*/
					}
					
					/*else{
						login.setus2erName(createusername);
						login.setPassword(createpassword);
						session.merge(login);
						System.out.println("Created Successfully");
						response.sendRedirect("LoginPage.jsp");

					}*/
				}
				catch (Exception e) {
					log4j.error("DataConversionDAOImpl - executeQueryForDefalutFlag :: Error " + e.getMessage());
					e.printStackTrace();
				}
			return createpassword;
		}
		/**
		 * @author Chandu 
		 * This method for qc .
		 * @param String country,county,state,selected column
		 * @exception HibernateException
		 * @return List
		 */
		@Override
		public List<LinkedHashMap<String, FnfLanForQC>> getDataForQc(String selectQuery,String slectedEdition) {
			// TODO Auto-generated method stub
			List<LinkedHashMap<String, FnfLanForQC>> dataForQc =  new ArrayList<LinkedHashMap<String, FnfLanForQC>>();
			String[] cloumnsToCmpare=slectedEdition.split(",");
				try{
				Session session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(FnfLanForQC.class);
				Query query = session.createSQLQuery(selectQuery);
				query.setResultTransformer(AliasToEntityLinkedMapResultTransformer.INSTANCE);
				dataForQc = query.list();
				List<FnfLanForQC> list = new ArrayList<FnfLanForQC>();
				Query query1 = session.createSQLQuery(selectQuery).addEntity(FnfLanForQC.class);
			//	list=query1.list();			
				/*
				for (Map.Entry<String, FnfLanForQC> entry : ((Map<String, FnfLanForQC>) dataForQc).entrySet())
				{
				System.out.println(entry.getKey() + "/" + entry.getValue());
				list.add(entry.getValue());
				} */
				/*for(FnfLanForQC listValues:list){
					System.out.println(listValues.getAPPRCL());
				}*/
				/*for(LinkedHashMap<String, FnfLanForQC> jjj:dataForQc){
					Iterator<Entry<String, FnfLanForQC>> entries = jjj.entrySet().iterator();
					while(entries.hasNext()){
						Map.Entry<String, FnfLanForQC> entry = entries.next();
						list.add(entry.getValue());
					}
				}*/
				for(FnfLanForQC listValues:list){
					System.out.println(listValues.getAPPRCL());
				}
				}
			catch(Exception e){
				e.printStackTrace();
			}
			return dataForQc;
		}
		/**
		 * @author Sushanta 
		 * This method saves field data .
		 * @param List<LinkedHashMap<String, Object>>
		 * @exception Exception
		 * @return true/false;
		 */
		@Override
		public boolean saveFieldData(List<LinkedHashMap<String, Object>> fldQryDtaAftrValdn, String fieldLan23TableName, String mappingFieldName) {
			log4j.info("DataConversionDAOImpl - saveFieldData :: Start");
			boolean flag = false;
			try{
				Session session = sessionFactory.openSession();
				txn = session.beginTransaction();
				String insQryStr = "INSERT INTO "+fieldLan23TableName+" (ID, APPRCL, "+mappingFieldName+") VALUES (?, ?, ?)";
				Query insQuery = session.createSQLQuery(insQryStr);
				String updtQryStr = "UPDATE "+fieldLan23TableName+" SET "+mappingFieldName+" = ? WHERE ID = ? AND APPRCL = ?";
				Query updtQuery = session.createSQLQuery(updtQryStr);
				int i = 0;
				for(LinkedHashMap<String, Object> dataMap : fldQryDtaAftrValdn){
					int id = 0;
					String apprcl = "";
					String fieldColumnValue = "";
					for (Map.Entry<String, Object> pair : dataMap.entrySet()) {
						if("ID".equalsIgnoreCase(pair.getKey())){
					    	id = (int) pair.getValue();
					    }else if("APPRCL".equalsIgnoreCase(pair.getKey())){
					    	apprcl = (String) pair.getValue();
					    }else if(mappingFieldName.equalsIgnoreCase(pair.getKey())) {
					    	fieldColumnValue = (String) pair.getValue();
						}else{
							
						}
					}
					if(idExists(id, fieldLan23TableName, session)){
						updtQuery.setParameter(0, fieldColumnValue);
						updtQuery.setParameter(1, id);
						updtQuery.setParameter(2, apprcl);
						updtQuery.executeUpdate();
					}else{
						insQuery.setParameter(0, id);
						insQuery.setParameter(1, apprcl);
						insQuery.setParameter(2, fieldColumnValue);
						insQuery.executeUpdate();
					}
					if(i%5 == 0){
						session.flush();
						session.clear();
					}
					i++;
				}
				
				txn.commit();
				session.close();
				flag = true;
			}catch(Exception e){
				txn.rollback();
				log4j.info("DataConversionDAOImpl - saveFieldData :: Error"+e.getMessage());
			}
			log4j.info("DataConversionDAOImpl - saveFieldData :: End");
			return flag;
		}
		/**
		 * @author Sushanta 
		 * This method checks the specific record exists in individual lan23 or not.
		 * @param Integer,String
		 * @exception Exception
		 * @return true/false;
		 */
		private boolean idExists(int id, String fieldLan23TableName, Session session) {
			log4j.info("DataConversionDAOImpl - idExists :: Start" + id);
			boolean flag = false;
			try {
				//Session session = sessionFactory.openSession();
				String queryStr = "SELECT * FROM "+fieldLan23TableName+" WHERE ID = "+id;
				SQLQuery query = session.createSQLQuery(queryStr);
				List<Object[]> list = query.list();
				log4j.info("DataConversionDAOImpl - idExists :: checklist "+list.size());
				if(list.size() > 0)
					flag = true;
				//session.flush();
				//session.close();
			} catch (Exception e) {
				log4j.info("DataConversionDAOImpl - idExists :: Error"+e.getMessage());
			}
			log4j.info("DataConversionDAOImpl - idExists :: End"+flag);
			return flag;
		}

		
}

