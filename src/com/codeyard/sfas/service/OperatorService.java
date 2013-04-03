package com.codeyard.sfas.service;

import java.util.List;
import com.codeyard.sfas.entity.DepoDamageSummary;
import com.codeyard.sfas.entity.DepoDeposit;
import com.codeyard.sfas.entity.DepoOrder;
import com.codeyard.sfas.entity.DepoOrderLi;
import com.codeyard.sfas.entity.DepoSellSummary;
import com.codeyard.sfas.entity.DepoStockSummary;
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
	List<DepoOrderLi> getDepoOrderLiList(Long depoOrderId);
}