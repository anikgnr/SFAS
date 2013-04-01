package com.codeyard.sfas.dao.impl;

import java.util.ArrayList;
import java.util.List; 

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.codeyard.sfas.dao.OperatorDao;
import com.codeyard.sfas.entity.DepoDamageSummary;
import com.codeyard.sfas.entity.DepoDeposit;
import com.codeyard.sfas.entity.DepoStockSummary;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.vo.OprSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;
 

@Repository
public class OperatorDaoImpl implements OperatorDao {
	private static Logger logger = Logger.getLogger(OperatorDaoImpl.class);

	private HibernateTemplate hibernateTemplate;
    
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
    	hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
			
	@SuppressWarnings("unchecked")
	public List<DepoStockSummary> getDepoCurrentStockList(StockSearchVo searchVo){
		searchVo.buildFilterQueryClauses("FROM DepoStockSummary ",new ArrayList<Object>(),false);
		return hibernateTemplate.find(searchVo.getSql(), searchVo.getParams());
	}
	
	@SuppressWarnings("unchecked")
	public List<DepoDamageSummary> getDepoDamageStockList(StockSearchVo searchVo){
		searchVo.buildFilterQueryClauses("FROM DepoDamageSummary ",new ArrayList<Object>(),false);
		return hibernateTemplate.find(searchVo.getSql(), searchVo.getParams());
	}
	
	@SuppressWarnings("unchecked")
	public List<DepoDeposit> getDepoDepositList(OprSearchVo searchVo){
		searchVo.buildFilterQueryClauses("FROM DepoDeposit ",new ArrayList<Object>(),false);
		return hibernateTemplate.find(searchVo.getSql(), searchVo.getParams());
	}	
	
}