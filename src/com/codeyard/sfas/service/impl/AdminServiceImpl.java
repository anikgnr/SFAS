package com.codeyard.sfas.service.impl;

import java.util.List; 

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codeyard.sfas.dao.AdminDao;
import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.ProductRegionRate;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;


@Service("adminService")
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {
	private static Logger logger = Logger.getLogger(AdminServiceImpl.class);

	@Autowired(required=true)
	private AdminDao adminDao;

	
	public 	boolean hasUserByUserName(String userName, Long id){
		return adminDao.hasUserByUserName(userName, id);
	}
	
	public User getUserByUserName(String userName){
		return adminDao.getUserByUserName(userName);
	}
	
	public List<AbstractBaseEntity> getEnityList(AdminSearchVo searchVo, String className){
		
		List<AbstractBaseEntity> entityList = adminDao.getEnityList(searchVo, className);
		if(entityList != null && entityList.size() > 0){
			for(AbstractBaseEntity entity : entityList){
				if(entity != null)
					Utils.setEditDeleteLinkOnAbstractEntity(entity,className.toLowerCase());
			}
		}
		return entityList;
	}
	
	public List<AbstractBaseEntity> getActiveEnityList(AdminSearchVo searchVo, String className){
		return adminDao.getActiveEnityList(searchVo, className);
	}
	
	public AbstractBaseEntity loadEntityById(Long id, String className){	
		return adminDao.loadEntityById(id, className);
	}
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(AbstractBaseEntity entity){
		adminDao.saveOrUpdate(entity);		
	}
	
	@Transactional(readOnly = false)
	public void onlySaveOrUpdate(AbstractBaseEntity entity){
		adminDao.onlySaveOrUpdate(entity);		
	}
	
	@Transactional(readOnly = false)
	public void deleteEntityById(Long id, String className){
		adminDao.deleteEntityById(id, className);
	}
	
	public List<AbstractLookUpEntity> getAllLookUpEntity(String className){
		return adminDao.getAllLookUpEntity(className);
	}

	public AbstractLookUpEntity loadLookUpEntityById(Long id, String className){
		return adminDao.loadLookUpEntityById(id, className);
	}
	
	public List<AbstractLookUpEntity> getLookUpEntityList(String className, String property, Object value){
		return adminDao.getLookUpEntityList(className, property, value);
	}
	
	public List<User> getUserListByDept(String dept){
		return adminDao.getUserListByDept(dept);
	}
	
	public List<User> getUserListByRole(String role){
		return adminDao.getUserListByRole(role);
	}
	
	@Transactional(readOnly = false)
	public void deleteProductById(Long productId){
		adminDao.deleteProductById(productId);
	}
	
	public ProductRegionRate getRegionalProductRate(Long productId, Long regionId){
		return adminDao.getRegionalProductRate(productId, regionId);
	}
	
	public double getProductRateByIdAndRegionId(Long productId, Long regionId){
		ProductRegionRate rate = getRegionalProductRate(productId, regionId);
		if(rate != null && rate.getRate() != null)
			return rate.getRate();
		return 0.0;
	}
	
	public double getProductMarginByIdAndRegionId(Long productId, Long regionId){
		ProductRegionRate rate = getRegionalProductRate(productId, regionId);
		if(rate != null && rate.getProfitMargin() != null)
			return rate.getProfitMargin();
		return 0.0;
	}
	
	@Transactional(readOnly = false)
	public void saveOrUpdateProductRegionalRates(List<ProductRegionRate> rateList){		
		for(ProductRegionRate rate : rateList){
			adminDao.saveOrUpdate(rate);
		}
	}
	
}