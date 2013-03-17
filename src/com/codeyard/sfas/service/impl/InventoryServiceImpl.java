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
	
	public List<StockIn> getTodaysStockInList(StockSearchVo searchVo){
	
		List<StockIn> stockInList = inventoryDao.getTodaysStockInList(searchVo);
		if(stockInList != null && stockInList.size() > 0){
			for(StockIn stockIn : stockInList){
				if(stockIn != null){
					if(Utils.isInRole(Role.INVENTORY_ADMIN.getValue())){
						Utils.setEditDeleteLinkOnAbstractEntity(stockIn,"stockin");
					}else{
						stockIn.setEditLink("<a href='javascript:void(0);' onclick='alert(\"You do not have permission for this action. Please contact with Inventory Admin.\");'>edit</a>");
						stockIn.setDeleteLink("<a href='javascript:void(0);' onclick='alert(\"You do not have permission for this action. Please contact with Inventory Admin.\");'>delete</a>");						
					}
				}
			}
		}
		return stockInList;
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
		
}