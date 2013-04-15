package com.codeyard.sfas.service;

import java.util.List;

import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.ProductRegionRate;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.vo.AdminSearchVo;

public interface AdminService { 
	
	boolean hasUserByUserName(String userName);
	User getUserByUserName(String userName);
	List<AbstractBaseEntity> getEnityList(AdminSearchVo searchVo, String className);
	List<AbstractBaseEntity> getActiveEnityList(AdminSearchVo searchVo, String className);
    AbstractBaseEntity loadEntityById(Long id, String className);
    AbstractLookUpEntity loadLookUpEntityById(Long id, String className);
    void saveOrUpdate(AbstractBaseEntity entity);
    void onlySaveOrUpdate(AbstractBaseEntity entity);
    void deleteEntityById(Long id, String className);
    List<AbstractLookUpEntity> getAllLookUpEntity(String className);
    List<AbstractLookUpEntity> getLookUpEntityList(String className, String property, Object value);
    List<User> getUserListByDept(String dept);
    List<User> getUserListByRole(String role);
    void deleteProductById(Long productId);
    ProductRegionRate getRegionalProductRate(Long productId, Long regionId);
    void saveOrUpdateProductRegionalRates(List<ProductRegionRate> rateList);
    double getProductRateByIdAndRegionId(Long productId, Long regionId);
    double getProductMarginByIdAndRegionId(Long productId, Long regionId);    
}