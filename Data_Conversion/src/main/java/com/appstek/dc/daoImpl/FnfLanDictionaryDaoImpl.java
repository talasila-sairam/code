package com.appstek.dc.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.appstek.dc.dao.FnfLanDictionaryDao;
import com.appstek.dc.dbload.FnfLanCountries;
import com.appstek.dc.dbload.FnfLanDictionary;

@Repository
@Transactional
@EnableTransactionManagement
public class FnfLanDictionaryDaoImpl implements FnfLanDictionaryDao{
	private static Logger log4j = Logger.getLogger("com.appstek.dc.daoImpl.FnfLanDictionaryDaoImpl");
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<FnfLanDictionary> getMappingColumnInfo() {
		log4j.info("FnfLanDictionaryDaoImpl - getMappingColumnInfo :: Start");
		List<FnfLanDictionary> dictionaryList = null;
		try
		{
		  Session session = sessionFactory.getCurrentSession();
		  Criteria criteria = session.createCriteria(FnfLanDictionary.class);
		  dictionaryList = criteria.list();
		}
		catch(Exception e)
		{
			log4j.error("FnfLanDictionaryDaoImpl - FnfLanDictionaryDaoImpl :: Error " + e.getMessage());
			e.printStackTrace();
		}
		return dictionaryList;
	}

}
