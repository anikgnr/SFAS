package com.codeyard.sfas.service.impl;

import java.util.List; 

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeyard.sfas.dao.AdminDao;
import com.codeyard.sfas.dao.InventoryDao;
import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.Role;
import com.codeyard.sfas.entity.StockIn;
import com.codeyard.sfas.service.InventoryService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
	private static Logger logger = Logger.getLogger(InventoryServiceImpl.class);

	@Autowired(required=true)
	private InventoryDao inventoryDao;

	@Transactional(readOnly = false)
	public void saveOrUpdateStockIn(StockIn stockIn){		
		inventoryDao.saveOrUpdateStockIn(stockIn);
	}
	
	public List<StockIn> getTodaysStockInList(StockSearchVo searchVo){
	
		List<StockIn> stockInList = inventoryDao.getTodaysStockInList(searchVo);
		if(stockInList != null && stockInList.size() > 0){
			for(StockIn stockIn : stockInList){
				if(stockIn != null){
					if(Utils.isInRole(Role.INVENTORY_ADMIN.getValue()))
						Utils.setEditDeleteLinkOnAbstractEntity(stockIn,"stockin");
					else{
						stockIn.setEditLink("<a href='javascript:void(0);' onclick='alert('You don't have permission for this. Please contact with Inventory Admin.');'>edit</a>");
						stockIn.setDeleteLink("<a href='javascript:void(0);' onclick='alert('You don't have permission for this. Please contact with Inventory Admin.');'>delete</a>");						
					}
				}
			}
		}
		return stockInList;
	}
		
}