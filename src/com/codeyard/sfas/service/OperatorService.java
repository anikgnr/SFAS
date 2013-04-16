package com.codeyard.sfas.service;

import java.util.List;
import com.codeyard.sfas.entity.DepoDamageSummary;
import com.codeyard.sfas.entity.DepoDeposit;
import com.codeyard.sfas.entity.DepoOrder;
import com.codeyard.sfas.entity.DepoOrderLi;
import com.codeyard.sfas.entity.DepoSellSummary;
import com.codeyard.sfas.entity.DepoStockSummary;
import com.codeyard.sfas.entity.DistributorProductPlanLi;
import com.codeyard.sfas.vo.OprSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;

public interface OperatorService { 
	
	List<DepoStockSummary> getDepoCurrentStockList(StockSearchVo searchVo);
	List<DepoDamageSummary> getDepoDamageStockList(StockSearchVo searchVo);	
	List<DepoDeposit> getDepoDepositList(OprSearchVo searchVo);
	List<DepoSellSummary> getDepoSellSummaryList(StockSearchVo searchVo);
	boolean approveDepoDeposit(Long depositId);
	DepoDeposit getLatestDepoDeposit(Long depoId);
	void saveOrUpdateDepoOrder(DepoOrder order);
	List<DepoOrder> getDepoOrderList(OprSearchVo searchVo);
	List<DepoOrderLi> getDepoOrderLiList(Long depoOrderId);
	void deleteDepoOrderById(Long orderId);
	boolean hasUnDeliveredOrderForDepo(Long depoId);
	void approveDepoOrder(DepoOrder order, String type);
	void unApproveDepoOrder(DepoOrder order, String type);
	void deliverDepoOrder(DepoOrder order);
	DepoStockSummary getDepoCurrentStockSummaryByProductIdAndDepoId(Long productId, Long depoId);
	DepoSellSummary getDepoSellSummaryByProductIdAndDepoId(Long productId, Long depoId);
	List<DistributorProductPlanLi> getDistributorPlanLiListByDepoIdMonthYearProductId(Long depoId, int month, int year, Long productId);
}