package com.codeyard.sfas.service.impl;

import java.util.List; 

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeyard.sfas.dao.InventoryDao;
import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.service.InventoryService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.SearchVo;


@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
	private static Logger logger = Logger.getLogger(InventoryServiceImpl.class);

	@Autowired(required=true)
	private InventoryDao inventoryDao;

		
}