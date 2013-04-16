package com.codeyard.sfas.service;

import java.util.List;
import com.codeyard.sfas.entity.DistributorDamageSummary;
import com.codeyard.sfas.entity.DistributorDeposit;
import com.codeyard.sfas.entity.DistributorOrder;
import com.codeyard.sfas.entity.DistributorOrderLi;
import com.codeyard.sfas.entity.DistributorProductPlan;
import com.codeyard.sfas.entity.DistributorProductPlanLi;
import com.codeyard.sfas.entity.DistributorSellSummary;
import com.codeyard.sfas.entity.DistributorStockSummary;
import com.codeyard.sfas.vo.OprSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;

public interface OprDistributorService { 
	
	List<DistributorStockSummary> getDistributorCurrentStockList(StockSearchVo searchVo);
	List<DistributorDamageSummary> getDistributorDamageStockList(StockSearchVo searchVo);	
	List<DistributorDeposit> getDistributorDepositList(OprSearchVo searchVo);
	List<DistributorSellSummary> getDistributorSellSummaryList(StockSearchVo searchVo);
	boolean approveDistributorDeposit(Long depositId);
	DistributorDeposit getLatestDistributorDeposit(Long distributorId);
	void saveOrUpdateDistributorOrder(DistributorOrder order);
	List<DistributorOrder> getDistributorOrderList(OprSearchVo searchVo);
	List<DistributorOrderLi> getDistributorOrderLiList(Long distributorOrderId);
	void deleteDistributorOrderById(Long orderId);
	boolean hasUnDeliveredOrderForDistributor(Long distributorId);
	void approveDistributorOrder(DistributorOrder order, String type);
	void unApproveDistributorOrder(DistributorOrder order, String type);
	void deliverDistributorOrder(DistributorOrder order);
	DistributorProductPlan getDistributorPlanByIdMonthYear(Long distributorId, int month, int year);
	List<DistributorProductPlanLi> getDistributorPlanLiListByPlanId(Long planId);
	void saveOrUpdateDistributorProductPlan(DistributorProductPlan plan);
	DistributorProductPlanLi getDistributorPlanLiByDistributorIdMonthYearProductId(Long distributorId, int month, int year, Long productId);	
}