package com.codeyard.sfas.dao;

import java.util.List;

import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.DamageSummary;
import com.codeyard.sfas.entity.StockIn;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;

public interface InventoryDao { 
	
	void saveOrUpdateStockIn(StockIn stockIn);
	List<StockIn> getPendingStockInList(StockSearchVo searchVo);
	List<StockSummary> getCurrentStockList(StockSearchVo searchVo);
	StockSummary getStockSummaryByProductId(Long productId);
	void deleteStockInById(Long stockInId);
	List<DamageSummary> getDamageStockList(StockSearchVo searchVo);
}

