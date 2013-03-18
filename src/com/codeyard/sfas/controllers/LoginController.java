package com.codeyard.sfas.controllers; 

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeyard.sfas.entity.Role;
import com.codeyard.sfas.util.Utils;


@Controller
public class LoginController {
	private static Logger logger = Logger.getLogger(LoginController.class);
		
    @RequestMapping(value="/login.html", method=RequestMethod.GET)
	public String redirectLogin(@RequestParam(value="error", required=false) String error,HttpServletRequest request,Model model) {
    	logger.debug("::  inside redirectLogin :: ");
    	if(!Utils.isNullOrEmpty(error)){
    		logger.debug("LOGIN ERROR :: wrong username/password provided.");
    		model.addAttribute("error", Utils.getMessageBundlePropertyValue("login.error"));
    	}
    	return "login";
    }
    
	@RequestMapping(value="/forward.html", method=RequestMethod.GET)
	public String forwardOnRole(HttpServletRequest request,Model model) {
		logger.debug("::  LOGIN SUCCESSFUL :: ");
		if(Utils.isInRole(Role.ADMIN.getValue())){
			return "redirect:/admin/home.html";
		}else if(Utils.isInRole(Role.INVENTORY_ADMIN.getValue()) || Utils.isInRole(Role.INVENTORY_OPERATOR.getValue())){
			return "redirect:/inventory/home.html";
		}else if(Utils.isInRole(Role.OPERATOR.getValue())){
			return "redirect:/operator/home.html";
		}else if(Utils.isInRole(Role.REPORT_MGR.getValue()) || Utils.isInRole(Role.REPORT_OPERATOR.getValue())){
			return "redirect:/report/home.html";
		}
		return "login";
    }
    
    @RequestMapping(value="/access-denied.html", method=RequestMethod.GET)
	public String accessDenied(HttpServletRequest request,Model model) {    	
	    return "accessDenied";
	 }    
    
}
