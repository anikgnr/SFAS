package com.codeyard.sfas.service.impl;

import java.util.List; 

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeyard.sfas.dao.AdminDao;
import com.codeyard.sfas.dao.InventoryDao;
import com.codeyard.sfas.dao.JdbcDao;
import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.DamageSummary;
import com.codeyard.sfas.entity.Role;
import com.codeyard.sfas.entity.StockIn;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.service.InventoryService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Service("inventoryService")
@Transactional(readOnly = true)
public class InventoryServiceImpl implements InventoryService {
	private static Logger logger = Logger.getLogger(InventoryServiceImpl.class);

	@Autowired(required=true)
	private InventoryDao inventoryDao;
	
	@Autowired(required=true)
	private JdbcDao jdbcDao;

	@Transactional(readOnly = false)
	public void saveOrUpdateStockIn(StockIn stockIn){		
		inventoryDao.saveOrUpdateStockIn(stockIn);
	}
	
	public List<StockIn> getPendingStockInList(StockSearchVo searchVo){
	
		return inventoryDao.getPendingStockInList(searchVo);		
	}
	
	public StockSummary getStockSummaryByProductId(Long productId){
		return inventoryDao.getStockSummaryByProductId(productId);
	}
	
	@Transactional(readOnly = false)
	public void deleteStockInById(Long stockInId){
		inventoryDao.deleteStockInById(stockInId);
	}
	
	public boolean isStockInAlreadyUsedById(Long stockInId){
		return jdbcDao.isStockInAlreadyUsedById(stockInId);
	}
	
	public List<StockSummary> getCurrentStockList(StockSearchVo searchVo){
		return inventoryDao.getCurrentStockList(searchVo);
	}
	
	public List<DamageSummary> getDamageStockList(StockSearchVo searchVo){
		return inventoryDao.getDamageStockList(searchVo);
	}
		
}