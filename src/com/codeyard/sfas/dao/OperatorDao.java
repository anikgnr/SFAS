package com.codeyard.sfas.dao;

import java.util.List;

import com.codeyard.sfas.entity.DepoDamageSummary;
import com.codeyard.sfas.entity.DepoDeposit;
import com.codeyard.sfas.entity.DepoStockSummary;
import com.codeyard.sfas.vo.OprSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;

public interface OperatorDao { 
	
	List<DepoStockSummary> getDepoCurrentStockList(StockSearchVo searchVo);
	List<DepoDamageSummary> getDepoDamageStockList(StockSearchVo searchVo);
	List<DepoDeposit> getDepoDepositList(OprSearchVo searchVo);
}
