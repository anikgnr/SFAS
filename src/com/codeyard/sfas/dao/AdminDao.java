package com.codeyard.sfas.dao;

import java.util.List;
import com.codeyard.sfas.entity.User;

public interface AdminDao { 
	
    List<User> getAllUserList();
}
