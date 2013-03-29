package com.codeyard.sfas.controllers.admin; 

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
import com.codeyard.sfas.entity.BankAccount;
import com.codeyard.sfas.entity.Product;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;


@Controller
public class BankAccountController {
	private static Logger logger = Logger.getLogger(BankAccountController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/accountList.html", method=RequestMethod.GET)
	public String productPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin bank account home:::::::::::::::::");    	
	    return "admin/accountList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeAccountList.html", method=RequestMethod.GET)
	public @ResponseBody Map productList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> accountList = adminService.getEnityList(AdminSearchVo.fetchFromRequest(request), "BankAccount");
    	map.put("account", accountList);
		return map;
    }
    
    @RequestMapping(value="/admin/bankaccount.html", method=RequestMethod.GET)
    public ModelAndView addEditProduct(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit bank account form:::::::::::::::::");
    	BankAccount account=null;
    	if(request.getParameter("id") != null)
    		account = (BankAccount)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"BankAccount");    		
    	else
    		account = new BankAccount();
    	
    	return new ModelAndView("admin/bankaccount", "command", account);
	}    

    @RequestMapping(value="/admin/saveBankAccount.html", method=RequestMethod.POST)	
    public String saveUpdateProduct(@ModelAttribute("account") BankAccount account, BindingResult result, HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit bank account:::::::::::::::::");
    	
    	try{
    		adminService.saveOrUpdate(account);
    		Utils.setSuccessMessage(request, "Bank Account successfully saved/updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating Bank Account :: "+ex);
    		Utils.setErrorMessage(request, "Bank Account can't be saved/updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/accountList.html";
	}    
    
    @RequestMapping(value="/admin/bankaccountDelete.html", method=RequestMethod.GET)
    public String deleteProduct(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete bank account form:::::::::::::::::");
    	
    	try{ 	    
    	    if(request.getParameter("id") != null){
    	    	adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"BankAccount");  
    	    	Utils.setSuccessMessage(request, "Bank Account successfully deleted.");
    	    }
    	}catch(Exception ex){
    		logger.debug("Error while delete Bank Account :: "+ex);
    		Utils.setErrorMessage(request, "Bank Account already in use. Please remove associated entries first.");
    	}      	    	
	    return "redirect:/admin/accountList.html";
	}
    
}
