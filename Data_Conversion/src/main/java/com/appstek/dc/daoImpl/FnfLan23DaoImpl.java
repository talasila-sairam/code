package com.appstek.dc.daoImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.appstek.dc.dao.FnfLan23Dao;
import com.appstek.dc.dbload.FnfLanDictionary;
import com.appstek.dc.util.ApplyingFieldRules;
import com.appstek.dc.util.RecordTypeTableConstant;

@Repository
@Transactional
@EnableTransactionManagement
public class FnfLan23DaoImpl implements FnfLan23Dao {

	Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
	private static Logger log4j = Logger.getLogger("com.appstek.dc.daoImpl.FnfLanDictionaryDaoImpl");

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * @Override public List<String> getColumn() { log4j.info(
	 * "FnfLan23DaoImpl - getColumn :: Start"); List<String> columnList = null;
	 * try { Session session = sessionFactory.getCurrentSession(); Query query =
	 * session.createSQLQuery(
	 * "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'fnf_lan23'"
	 * ); columnList = query.list(); System.out.println(" columnList = " +
	 * columnList); log4j.info("FnfLan23DaoImpl - getColumn :: End");
	 * }catch(Exception e) { log4j.error(
	 * "FnfLanDictionaryDaoImpl - FnfLanDictionaryDaoImpl :: Error " +
	 * e.getMessage()); e.printStackTrace(); } return columnList; }
	 */
	@Override
	public BufferedWriter callProcedureForTXTGeneration(List<FnfLanDictionary> fnfLanDictionaryList, String realPath) {
		log4j.info("FnfLan23DaoImpl - callProcedureForTXTGeneration :: Start");

		BufferedWriter br = null;
		ResultSet rs = null;
		String s = "D:/textfile/filename.txt";
		try {
			File fl = new File(realPath);
			if (fl.exists()) {
				fl.delete();
			}
			FileWriter file = new FileWriter(realPath, true);
			// FileWriter file = new FileWriter(s,true);
			Session session = sessionFactory.getCurrentSession();
			SessionImplementor sessionImplementor = (SessionImplementor) session;
			Connection con = sessionImplementor.connection();
			CallableStatement cs = con.prepareCall("{CALL dbo.dataConversionForFNFLAN23Table()}");
			rs = cs.executeQuery();
			System.out.println(fnfLanDictionaryList.size());
			br = new BufferedWriter(file);
			while (rs.next()) {
				int i = 0;
				for (int j = 1; j <= fnfLanDictionaryList.size(); j++) {
					String columnValue = rs.getString(j);
					String[] splitColumnValue = columnValue.split("DELMETER__");
					// mapped with the column value
					for (; i < fnfLanDictionaryList.size();) {
						FnfLanDictionary fnfLanDictionary = fnfLanDictionaryList.get(i);
						i++;
						String map1 = fnfLanDictionary.getMappingFieldName().replace("_", "");
						String map2 = splitColumnValue[1].replace("_", "");
						if (map1.trim().equalsIgnoreCase(map2.trim())) {
							applyRulesForGeneratingText(splitColumnValue[0], fnfLanDictionary.getLength(), br);
							break;
						}
					}
				}

				br.newLine();
			}
		} catch (Exception e) {
			log4j.error("FnfLanDictionaryDaoImpl - callProcedureForTXTGeneration :: Error " + e.getMessage());
			e.printStackTrace();
		}
		return br;
	}

	public void applyRulesForGeneratingText(String mappedValue, int length, BufferedWriter br) {
		if (!mappedValue.equals("")) {
			Matcher firstCharMatch = pattern.matcher(Character.toString(mappedValue.charAt(0)));
			Matcher secondCharMatch = pattern.matcher(Character.toString(mappedValue.charAt(mappedValue.length() - 1)));
			if (firstCharMatch.find()) {
				mappedValue = mappedValue.substring(1);
			}
			if (secondCharMatch.find()) {
				mappedValue = mappedValue.substring(0, mappedValue.length() - 2);
			}
		}

		if (mappedValue.length() < length) {
			int len = length - mappedValue.length();
			for (int k = 1; k <= len; k++) {
				mappedValue = mappedValue.concat(" ");
			}
		} else if (mappedValue.length() > length) {
			mappedValue = mappedValue.substring(0, length - 1);
		}
		try {
			br.write(mappedValue);
			br.flush();
		} catch (Exception e) {
			log4j.error("FnfLanDictionaryDaoImpl - applyRulesForGeneratingText :: Error " + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public String saveData(String countyName, String stateName, List<FnfLanDictionary> fnfLanDictionaryList,String edition) {
		String msg = "Data Saved Successfully.";
		Map<String,String> realMap = new LinkedHashMap<String, String>();
		Map<String,String> dummyValueHoldingMap = new LinkedHashMap<String, String>();
		try {
			String fieldsNumbers = "";
			String fieldsName = "";
			String queryString = "";
			Session session = sessionFactory.getCurrentSession();
			SessionImplementor sessionImplementor = (SessionImplementor) session;
			Connection con = sessionImplementor.connection();
			/*
			 * countyName = "BACON1"; stateName="Georgia1";
			 */
			CallableStatement cs = con.prepareCall("{CALL dbo.saveRuleQuery(?,?,?)}");
			cs.setString(1, countyName);
			cs.setString(2, stateName);
			cs.setString(3, edition);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				String result = rs.getString(1);
				if (result == null) {
					msg = "Could not extract resule set. kindly check the Rules.";
					throw new Exception(msg);
				} else if (result.contains("ERROR")) {
					msg = result;
					throw new Exception(msg);
				} else {
					String[] splitResult = result.split("DELMETER__");
					fieldsName = splitResult[0];
					fieldsNumbers = splitResult[1].substring(0, splitResult[1].length() - 1);
					queryString = splitResult[2];
				}
			}
			String[] fieldsNameSplit = fieldsName.split(",");
			StringBuilder recordtypeTablesFields = new StringBuilder("");
			
			// creating fields info for record type table that has to be
			// dynamically created
			for (int i = 0; i < fieldsNameSplit.length; i++) {
				Field field = RecordTypeTableConstant.class.getField(fieldsNameSplit[i].toString());// RecordTypeTableConstant+""+fieldsNameSplit[i];
				RecordTypeTableConstant objectInstance = new RecordTypeTableConstant();
				Object value = field.get(objectInstance);
				recordtypeTablesFields.append(value + ",");
			}
			Query deleterecordType = session.createSQLQuery("drop table recordType");
			deleterecordType.executeUpdate();
			String createRecordTypeTableString = recordtypeTablesFields.toString().substring(0,
					recordtypeTablesFields.toString().length() - 1);
			String createRecordTypeTable = "create table recordType(" + createRecordTypeTableString + ")";
			Query createTableResult = session.createSQLQuery(createRecordTypeTable);
			createTableResult.executeUpdate();
			
		    //This is for demo test. Make comment after use.
		//	queryString = demoMethod();
          //  fieldsNumbers="73,20,19,18,3,2";
			 // fieldsNumbers="2,3,18,19,20,73";
			// Method call has to be remove later once It has been done
			SQLQuery sqlQueryResult = session.createSQLQuery(queryString);
			List<Object[]> list = (List<Object[]>) sqlQueryResult.list();
			String[] fieldsArray = fieldsNumbers.split(",");
			int totalFields = fieldsArray.length;
			StringBuilder insertValue = new StringBuilder("");
			String deleteQuery = "delete from fnf_lan23_demo";
			Query deleteResult = session.createSQLQuery(deleteQuery);
			int k = deleteResult.executeUpdate();
			String fieldValue = "";
			int fieldNum = 0;
			for (int j = 0; j < list.size(); j++) {
				Object[] objArr = list.get(j);
				for (int i = 0; i < totalFields; i++) {
					// Get Field Number
					fieldNum = Integer.valueOf(fieldsArray[i]);
					if (objArr[i + 1] != null) {
						fieldValue = objArr[i + 1].toString();
					} else if (objArr[i + 1] == null) {
						fieldValue = null;
					}
					// Pass fieldNum and fieldValue to the method for applying
					// rule
					int dicVal = fieldNum - 1;
					FnfLanDictionary FnfLanDictionary = fnfLanDictionaryList.get(dicVal);
					//String val = ApplyingFieldRules.fieldRules(fieldNum, fieldValue, FnfLanDictionary.getLength(),objArr,list,fieldsNumbers,realMap);
					realMap = ApplyingFieldRules.fieldRules(fieldNum, fieldValue, FnfLanDictionary.getLength(),objArr,list,fieldsNumbers,realMap,dummyValueHoldingMap);
					/*	if (val == null) {
						insertValue.append(val + ",");
					} else if (val != null) {
						boolean check = isNumeric(val);
						if (check == true) {
							insertValue.append(val + ",");
						} else {
							insertValue.append("'" + val + "'" + ",");
						}
					}*/
				}
			Iterator entries = realMap.entrySet().iterator();
				while (entries.hasNext()) {
				    Map.Entry entry = (Map.Entry) entries.next();
				    String key = (String) entry.getKey();
				    String val = (String)entry.getValue();
				    if (val == null) {
						insertValue.append(val + ",");
					} else if (val != null) {
						boolean check = isNumeric(val);
						if (check == true) {
							insertValue.append(val + ",");
						} else {
							insertValue.append("'" + val + "'" + ",");
						}
					}
				    System.out.println("Key = " + key + ", Value = " + val);
				}
				realMap.clear();
				fieldsName = "COUNTYNAME,APNORPINNUMBER,ASSESSEEOWNERNAME,SECOND_ASSESSEEOWNERNAME,ASSEVESTINGIDCODE,STANDARDIZEDLANDUSECODE";
				String values = insertValue.toString().substring(0, insertValue.toString().length() - 1);
				String insertQuery = "insert into fnf_lan23_demo(" + fieldsName + ") values(" + values + ")";
				Query insertResult = session.createSQLQuery(insertQuery);
				insertResult.executeUpdate();
				String insertQueryForRecordType = "insert into recordType(" + fieldsName + ") values(" + values + ")";
				Query insertResultRecordType = session.createSQLQuery(insertQueryForRecordType);
				insertResultRecordType.executeUpdate();
				insertValue.delete(0, insertValue.length());
			}
		} catch (Exception e) {
			msg = e.getMessage();
			log4j.error("FnfLanDictionaryDaoImpl - saveData :: Error " + e);
		}
		return msg;
	}

	public boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	public String demoMethod() {
		//select * from (select  FIELD_73.APPRCL  APPRCLNUM,FIELD_73.STANDARDIZEDLANDUSECODE,FIELD_20.ASSEVESTINGIDCODE,FIELD_19.SECOND_ASSESSEEOWNERNAME,FIELD_18.ASSESSEEOWNERNAME,FIELD_3.APNORPINNUMBER,FIELD_2.COUNTYNAME from ( select MOBILEKEY as APPRCL,MFG as STANDARDIZEDLANDUSECODE from GA_Bacon_08_Main_28 (nolock)
		String query = "select * from (select  FIELD_73.APPRCL  APPRCLNUM,FIELD_2.COUNTYNAME,FIELD_3.APNORPINNUMBER,FIELD_18.ASSESSEEOWNERNAME,FIELD_19.SECOND_ASSESSEEOWNERNAME,FIELD_20.ASSEVESTINGIDCODE,FIELD_73.STANDARDIZEDLANDUSECODE from ( select MOBILEKEY as APPRCL,MFG as STANDARDIZEDLANDUSECODE from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG  like 'DE%'" + "or MFG  like 'SC%'"
				+ "or MFG  like 'FL%' UNION ALL select MOBILEKEY as APPRCL,MFG as STANDARDIZEDLANDUSECODE from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG  like 'DE%'" + "or MFG  like 'SC%'"
				+ "or MFG  like 'BE%' UNION ALL select MOBILEKEY as APPRCL,MFG as STANDARDIZEDLANDUSECODE from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG NOT like 'DE%'" + "and MFG NOT like 'SC%'"
				+ "and MFG  like 'BE%' ) FIELD_73 FULL OUTER JOIN ( select MOBILEKEY as APPRCL,MFG as ASSEVESTINGIDCODE from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG  like 'DE%' UNION ALL select MOBILEKEY as APPRCL,MFG as ASSEVESTINGIDCODE from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG  like 'SC%' UNION ALL select MOBILEKEY as APPRCL,MFG as ASSEVESTINGIDCODE from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG  like 'DE%'"
				+ "and MFG  like 'SC' UNION ALL select MOBILEKEY as APPRCL,MFG as ASSEVESTINGIDCODE from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG  like 'DE%'" + "or MFG  like 'SC%'"
				+ "or MFG  like 'FL%' ) FIELD_20 ON FIELD_73.APPRCL=FIELD_20.APPRCL FULL OUTER JOIN ( select MOBILEKEY as APPRCL,MFG as SECOND_ASSESSEEOWNERNAME from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG  like 'VE%' UNION ALL select MOBILEKEY as APPRCL,MFG as SECOND_ASSESSEEOWNERNAME from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG  like '%VE%' UNION ALL select MOBILEKEY as APPRCL,MFG as SECOND_ASSESSEEOWNERNAME from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG  like '%VE%'"
				+ "and MFG not  like 'LI%' UNION ALL select MOBILEKEY as APPRCL,MFG as SECOND_ASSESSEEOWNERNAME from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG  like '%VE%'"
				+ "and MFG not  like 'NO%' ) FIELD_19 ON FIELD_20.APPRCL=FIELD_19.APPRCL FULL OUTER JOIN ( select MOBILEKEY as APPRCL,MFG as ASSESSEEOWNERNAME from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG not like ''"
				+ "and MFG not like 'SC%' UNION ALL select MOBILEKEY as APPRCL,MFG as ASSESSEEOWNERNAME from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG not like ''"
				+ "and MFG not like 'FL%' UNION ALL select MOBILEKEY as APPRCL,MFG as ASSESSEEOWNERNAME from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG not like ''" + "and MFG not like 'BE%'"
				+ "and MFG not like 'FL%' UNION ALL select MOBILEKEY as APPRCL,MFG as ASSESSEEOWNERNAME from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG not like ''" + "and MFG not like 'DE%'" + "and MFG not like 'GE%'"
				+ "and MFG not like 'FL%' UNION ALL select MOBILEKEY as APPRCL,MFG as ASSESSEEOWNERNAME from GA_Bacon_08_Main_28 (nolock)"
				+ "where MFG not like ''" + "and MFG not like 'DE%'" + "and MFG not like 'PI%'"
				+ "and MFG not like 'FL%' ) FIELD_18 ON FIELD_18.APPRCL=FIELD_19.APPRCL FULL OUTER JOIN ( select 'SD' as APNORPINNUMBER ) FIELD_3 ON 1=1 FULL OUTER JOIN ( select 'SD' as COUNTYNAME ) FIELD_2 ON 1=1) main  where APPRCLNUM is not null GROUP BY APPRCLNUM,STANDARDIZEDLANDUSECODE,ASSEVESTINGIDCODE,SECOND_ASSESSEEOWNERNAME,ASSESSEEOWNERNAME,APNORPINNUMBER,COUNTYNAME";
		return query;
	}
}
