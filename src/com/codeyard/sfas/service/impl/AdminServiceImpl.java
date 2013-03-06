package com.codeyard.sfas.service.impl;

import java.util.List; 

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeyard.sfas.dao.AdminDao;
import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.Region;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.SearchVo;


@Service("adminService")
public class AdminServiceImpl implements AdminService {
	private static Logger logger = Logger.getLogger(AdminServiceImpl.class);

	@Autowired(required=true)
	private AdminDao adminDao;

	
	@Transactional(readOnly=true)
	public List<AbstractBaseEntity> getEnityList(SearchVo searchVo, String className){
		
		List<AbstractBaseEntity> entityList = adminDao.getEnityList(searchVo, className);
		if(entityList != null && entityList.size() > 0){
			for(AbstractBaseEntity entity : entityList){
				if(entity != null)
					Utils.setEditDeleteLinkOnAbstractEntity(entity,className.toLowerCase());
			}
		}
		return entityList;
	}
	
	@Transactional(readOnly=true)
	public AbstractBaseEntity loadEntityById(Long id, String className){	
		return adminDao.loadEntityById(id, className);
	}
	
	public void saveOrUpdate(AbstractBaseEntity entity){
		adminDao.saveOrUpdate(entity);
	}
	
	public void deleteEntityById(Long id, String className){
		adminDao.deleteEntityById(id, className);
	}
	
	public List<Region> getAllRegions(){
		return adminDao.getAllRegions();
	}

	public AbstractLookUpEntity loadLookUpEntityById(Long id, String className){
		return adminDao.loadLookUpEntityById(id, className);
	}
}