package com.codeyard.sfas.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List; 

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.codeyard.sfas.dao.InventoryDao;
import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.DamageSummary;
import com.codeyard.sfas.entity.Region;
import com.codeyard.sfas.entity.StockIn;
import com.codeyard.sfas.entity.StockOut;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;
 

@Repository
public class InventoryDaoImpl implements InventoryDao {
	private static Logger logger = Logger.getLogger(InventoryDaoImpl.class);

	private HibernateTemplate hibernateTemplate;
    
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
    	hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Autowired(required=true)
	private AdminService adminService;
	
	public void saveOrUpdateStockIn(StockIn stockIn){
		
		StockSummary stockSummary = getStockSummaryByProductId(stockIn.getProduct().getId());
		if(stockSummary == null){
			stockSummary = new StockSummary();
			stockSummary.setProduct(stockIn.getProduct());
			stockSummary.setCreated(Utils.today());
			stockSummary.setCreatedBy(Utils.getLoggedUser());			
		}		
		stockSummary.setQuantity(stockSummary.getQuantity() + stockIn.getQuantity());
		stockSummary.setLastModified(Utils.today());
		stockSummary.setLastModifiedBy(Utils.getLoggedUser());
		stockSummary.setLastStockInDate(stockIn.getStockInDate());		
		hibernateTemplate.saveOrUpdate(stockSummary);
		
		stockIn.setLastModifiedBy(Utils.getLoggedUser());
		stockIn.setLastModified(Utils.today());
		hibernateTemplate.saveOrUpdate(stockIn);
		
	}
 
	@SuppressWarnings("unchecked")
	public List<StockIn> getPendingStockInList(StockSearchVo searchVo){
		String sql = "From StockIn WHERE approved = ? ";
		List<Object> params = new ArrayList<Object>();
		params.add(new Boolean(false));		
		searchVo.buildFilterQueryClauses(sql,params,true);
    	return hibernateTemplate.find(searchVo.getSql(), searchVo.getParams());
	}
	
	@SuppressWarnings("unchecked")
	public StockSummary getStockSummaryByProductId(Long productId){
		List<StockSummary> summaryList = hibernateTemplate.find("From StockSummary where product.id = ? ", productId);
    	if(summaryList != null && summaryList.size() > 0)
    		return (StockSummary) summaryList.get(0);
    	else
    		return null;  
	}
	
	public void deleteStockInById(Long stockInId){
		
		hibernateTemplate.bulkUpdate("DELETE FROM StockIn WHERE id = ? ", stockInId);		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<StockSummary> getCurrentStockList(StockSearchVo searchVo){
		searchVo.buildFilterQueryClauses("FROM StockSummary ",new ArrayList<Object>(),false);
		return hibernateTemplate.find(searchVo.getSql(), searchVo.getParams());
	}
	
	@SuppressWarnings("unchecked")
	public List<DamageSummary> getDamageStockList(StockSearchVo searchVo){
		searchVo.buildFilterQueryClauses("FROM DamageSummary ",new ArrayList<Object>(),false);
		return hibernateTemplate.find(searchVo.getSql(), searchVo.getParams());
	}
}