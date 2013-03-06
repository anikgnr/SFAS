package com.codeyard.sfas.service;

import java.util.List;

import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.vo.admin.UserVo;

public interface AdminService { 
	
    List<User> getAllUserList(UserVo userVo);
    AbstractBaseEntity loadEntityById(Long id, String className);
    void saveOrUpdate(AbstractBaseEntity entity);
    void deleteEntityById(Long id, String className);
}