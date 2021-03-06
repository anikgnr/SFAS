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
import com.codeyard.sfas.entity.DepoOrder;
import com.codeyard.sfas.entity.DepoOrderLi;
import com.codeyard.sfas.entity.DepoSellSummary;
import com.codeyard.sfas.entity.DepoStockSummary;
import com.codeyard.sfas.entity.DistributorProductPlanLi;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
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
	
	@SuppressWarnings("unchecked")
	public List<DepoSellSummary> getDepoSellSummaryList(StockSearchVo searchVo){
		searchVo.buildFilterQueryClauses("FROM DepoSellSummary ",new ArrayList<Object>(),false);
		return hibernateTemplate.find(searchVo.getSql(), searchVo.getParams());
	}
	
	@SuppressWarnings("unchecked")
	public DepoDeposit getLatestDepoDeposit(Long depoId){
		List<DepoDeposit> list = hibernateTemplate.find("FROM DepoDeposit where depo.id = ? and accountApproved = true order by depositDate desc", depoId);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	public void saveOrUpdateDepoOrder(DepoOrder order){
		if(order.getId() == null || order.getId() == 0){
			order.setCreated(Utils.today());
			order.setCreatedBy(Utils.getLoggedUser());
		}
		order.setLastModified(Utils.today());
		order.setLastModifiedBy(Utils.getLoggedUser());
		hibernateTemplate.saveOrUpdate(order);
		
		for(DepoOrderLi orderLi : order.getOrderLiList()){
			orderLi.setDepoOrder(order);
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
	public List<DepoOrderLi> getDepoOrderLiList(Long depoOrderId){
		return hibernateTemplate.find("FROM DepoOrderLi where depoOrder.id = ? order by serial ", depoOrderId);
	}
	
	@SuppressWarnings("unchecked")	
	public List<DepoOrder> getDepoOrderList(OprSearchVo searchVo){
		searchVo.buildFilterQueryClauses("FROM DepoOrder ",new ArrayList<Object>(),false);
		return hibernateTemplate.find(searchVo.getSql()+" order by orderDate desc", searchVo.getParams());
	}
	
	public void deleteDepoOrderById(Long orderId){
		
		hibernateTemplate.bulkUpdate("DELETE FROM DepoOrderLi where depoOrder.id =  ?", orderId);
		hibernateTemplate.bulkUpdate("DELETE FROM DepoOrder where id =  ?", orderId);
	}
	
	@SuppressWarnings("unchecked")
	public DepoStockSummary getDepoStockSummaryByProductId(Long productId, Long depoId){
		List<DepoStockSummary> summaryList = hibernateTemplate.find("From DepoStockSummary where product.id = ? and depo.id = ?", productId, depoId);
    	if(summaryList != null && summaryList.size() > 0)
    		return (DepoStockSummary) summaryList.get(0);
    	else
    		return null;  
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributorProductPlanLi> getDistributorPlanLiListByDepoIdMonthYearProductId(Long depoId, int month, int year, Long productId){
		
		String sql = "FROM DistributorProductPlanLi where plan.distributor.depo.id = ? and plan.planMonth = ? and plan.planYear = ? and product.id = ? ";		
		return hibernateTemplate.find(sql, depoId, month, year, productId);
	}
}