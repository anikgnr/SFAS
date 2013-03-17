package com.codeyard.sfas.controllers.inventory; 

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.Role;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.vo.AdminSearchVo;


@Controller
public class InventoryController {
	private static Logger logger = Logger.getLogger(InventoryController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

	@RequestMapping(value="/inventory/home.html", method=RequestMethod.GET)
	public String adminHomePanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside inventory home:::::::::::::::::");
    	return "inventory/home";
	}    
    	   
}