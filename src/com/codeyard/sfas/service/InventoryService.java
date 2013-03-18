package com.codeyard.sfas.service;

import java.util.List;

import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.DamageSummary;
import com.codeyard.sfas.entity.StockIn;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;

public interface InventoryService { 
	
	void saveOrUpdateStockIn(StockIn stockIn);
	List<StockIn> getTodaysStockInList(StockSearchVo searchVo);
	List<StockSummary> getCurrentStockList(StockSearchVo searchVo);
	List<DamageSummary> getDamageStockList(StockSearchVo searchVo);
	void deleteStockInById(Long stockInId);
	boolean isStockInAlreadyUsedById(Long stockInId);
}