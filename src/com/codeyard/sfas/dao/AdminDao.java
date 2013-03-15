package com.codeyard.sfas.dao;

import java.util.List;

import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.vo.AdminSearchVo;

public interface AdminDao { 
	
	List<AbstractBaseEntity> getEnityList(AdminSearchVo searchVo, String className);
    AbstractBaseEntity loadEntityById(Long id, String className);
    AbstractLookUpEntity loadLookUpEntityById(Long id, String className);
    void saveOrUpdate(AbstractBaseEntity entity);
    void deleteEntityById(Long id, String className);
    List<AbstractLookUpEntity> getAllLookUpEntity(String className);
    List<AbstractLookUpEntity> getLookUpEntityList(String className, String property, Object value);
}

