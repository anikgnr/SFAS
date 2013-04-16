package com.codeyard.sfas.dao.impl;

import java.util.ArrayList;
import java.util.List; 

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.codeyard.sfas.dao.OprDistributorDao;
import com.codeyard.sfas.entity.DistributorDamageSummary;
import com.codeyard.sfas.entity.DistributorDeposit;
import com.codeyard.sfas.entity.DistributorOrder;
import com.codeyard.sfas.entity.DistributorOrderLi;
import com.codeyard.sfas.entity.DistributorProductPlan;
import com.codeyard.sfas.entity.DistributorProductPlanLi;
import com.codeyard.sfas.entity.DistributorSellSummary;
import com.codeyard.sfas.entity.DistributorStockSummary;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.OprSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;
 

@Repository
public class OprDistributorDaoImpl implements OprDistributorDao {
	private static Logger logger = Logger.getLogger(OprDistributorDaoImpl.class);

	private HibernateTemplate hibernateTemplate;
    
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
    	hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
			
	@SuppressWarnings("unchecked")
	public List<DistributorStockSummary> getDistributorCurrentStockList(StockSearchVo searchVo){
		searchVo.buildFilterQueryClauses("FROM DistributorStockSummary ",new ArrayList<Object>(),false);
		return hibernateTemplate.find(searchVo.getSql(), searchVo.getParams());
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributorDamageSummary> getDistributorDamageStockList(StockSearchVo searchVo){
		searchVo.buildFilterQueryClauses("FROM DistributorDamageSummary ",new ArrayList<Object>(),false);
		return hibernateTemplate.find(searchVo.getSql(), searchVo.getParams());
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributorDeposit> getDistributorDepositList(OprSearchVo searchVo){
		searchVo.buildFilterQueryClauses("FROM DistributorDeposit ",new ArrayList<Object>(),false);
		return hibernateTemplate.find(searchVo.getSql(), searchVo.getParams());
	}	
	
	@SuppressWarnings("unchecked")
	public List<DistributorSellSummary> getDistributorSellSummaryList(StockSearchVo searchVo){
		searchVo.buildFilterQueryClauses("FROM DistributorSellSummary ",new ArrayList<Object>(),false);
		return hibernateTemplate.find(searchVo.getSql(), searchVo.getParams());
	}
	
	@SuppressWarnings("unchecked")
	public DistributorDeposit getLatestDistributorDeposit(Long distributorId){
		List<DistributorDeposit> list = hibernateTemplate.find("FROM DistributorDeposit where distributor.id = ? and accountApproved = true order by depositDate desc", distributorId);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	public void saveOrUpdateDistributorOrder(DistributorOrder order){
		if(order.getId() == null || order.getId() == 0){
			order.setCreated(Utils.today());
			order.setCreatedBy(Utils.getLoggedUser());
		}
		order.setLastModified(Utils.today());
		order.setLastModifiedBy(Utils.getLoggedUser());
		hibernateTemplate.saveOrUpdate(order);
		
		for(DistributorOrderLi orderLi : order.getOrderLiList()){
			orderLi.setDistributorOrder(order);
			if(orderLi.getId() == null || orderLi.getId() == 0){
				orderLi.setCreated(Utils.today());
				orderLi.setCreatedBy(Utils.getLoggedUser());
			}
			orderLi.setLastModified(Utils.today());
			orderLi.setLastModifiedBy(Utils.getLoggedUser());
			
			hibernateTemplate.saveOrUpdate(orderLi);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributorOrderLi> getDistributorOrderLiList(Long distributorOrderId){
		return hibernateTemplate.find("FROM DistributorOrderLi where distributorOrder.id = ? order by serial ", distributorOrderId);
	}
	
	@SuppressWarnings("unchecked")	
	public List<DistributorOrder> getDistributorOrderList(OprSearchVo searchVo){
		searchVo.buildFilterQueryClauses("FROM DistributorOrder ",new ArrayList<Object>(),false);
		return hibernateTemplate.find(searchVo.getSql()+" order by orderDate desc", searchVo.getParams());
	}
	
	public void deleteDistributorOrderById(Long orderId){
		
		hibernateTemplate.bulkUpdate("DELETE FROM DistributorOrderLi where distributorOrder.id =  ?", orderId);
		hibernateTemplate.bulkUpdate("DELETE FROM DistributorOrder where id =  ?", orderId);
	}
	
	@SuppressWarnings("unchecked")
	public DistributorStockSummary getDistributorStockSummaryByProductId(Long productId, Long distributorId){
		List<DistributorStockSummary> summaryList = hibernateTemplate.find("From DistributorStockSummary where product.id = ? and distributor.id = ?", productId, distributorId);
    	if(summaryList != null && summaryList.size() > 0)
    		return (DistributorStockSummary) summaryList.get(0);
    	else
    		return null;  
	}
	
	@SuppressWarnings("unchecked")
	public DistributorProductPlan getDistributorPlanByIdMonthYear(Long distributorId, int month, int year){
		List<DistributorProductPlan> planList = hibernateTemplate.find("From DistributorProductPlan where distributor.id = ? and planMonth = ? and planYear = ? ", distributorId, month, year);
    	if(planList != null && planList.size() > 0)
    		return (DistributorProductPlan) planList.get(0);
    	else
    		return null;  
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributorProductPlanLi> getDistributorPlanLiListByPlanId(Long planId){
		return hibernateTemplate.find("From DistributorProductPlanLi where plan.id = ?", planId);
	}
	
	@SuppressWarnings("unchecked")
	public DistributorProductPlanLi getDistributorPlanLiByDistributorIdMonthYearProductId(Long distributorId, int month, int year, Long productId){
		
		String sql = "FROM DistributorProductPlanLi where plan.distributor.id = ? and plan.planMonth = ? and plan.planYear = ? and product.id = ? ";
		
		List<DistributorProductPlanLi> planLiList = hibernateTemplate.find(sql, distributorId, month, year, productId);
    	if(planLiList != null && planLiList.size() > 0)
    		return (DistributorProductPlanLi) planLiList.get(0);
    	else
    		return null;
	}
	
	@SuppressWarnings("unchecked")
	public DistributorProductPlanLi getLiteDistributorPlanLiByDistributorIdMonthYearProductId(Long distributorId, int month, int year, Long productId){
		
		String sql = "SELECT new com.codeyard.sfas.entity.DistributorProductPlanLi(id, plan.id, product.id, used, quantity) FROM DistributorProductPlanLi where plan.distributor.id = ? and plan.planMonth = ? and plan.planYear = ? and product.id = ? ";
		
		List<DistributorProductPlanLi> planLiList = hibernateTemplate.find(sql, distributorId, month, year, productId);
    	if(planLiList != null && planLiList.size() > 0)
    		return (DistributorProductPlanLi) planLiList.get(0);
    	else
    		return null;
	}
	
}