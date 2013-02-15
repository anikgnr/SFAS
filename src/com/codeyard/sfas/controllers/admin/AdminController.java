package com.codeyard.sfas.controllers.admin; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeyard.sfas.entity.Role;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;


@Controller
public class AdminController {
	private static Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/userList.html", method=RequestMethod.GET)
	public String userPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin user home:::::::::::::::::");
	    return "admin/userList";
	}    

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeUserList.html", method=RequestMethod.GET)
	public @ResponseBody Map userList(HttpServletRequest request, Map map) {
    	List<User> userList = adminService.getAllUserList();
    	if(userList == null)
    		userList = new ArrayList<User>();    	
    	logger.debug("loading user list from db...."+userList.size());
    	map.put("user", userList);
		return map;
    }
	
}
