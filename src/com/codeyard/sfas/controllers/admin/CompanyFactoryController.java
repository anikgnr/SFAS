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
import com.codeyard.sfas.entity.CompanyFactory;
import com.codeyard.sfas.entity.Product;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;


@Controller
public class CompanyFactoryController {
	private static Logger logger = Logger.getLogger(CompanyFactoryController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/factoryList.html", method=RequestMethod.GET)
	public String productPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin factory home:::::::::::::::::");    	
	    return "admin/factoryList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeFactoryList.html", method=RequestMethod.GET)
	public @ResponseBody Map productList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> factoryList = adminService.getEnityList(AdminSearchVo.fetchFromRequest(request), "CompanyFactory");
    	map.put("factory", factoryList);
		return map;
    }
    
    @RequestMapping(value="/admin/companyfactory.html", method=RequestMethod.GET)
    public ModelAndView addEditProduct(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit company factory form:::::::::::::::::");
    	CompanyFactory factory=null;
    	if(request.getParameter("id") != null)
    		factory = (CompanyFactory)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"CompanyFactory");    		
    	else
    		factory = new CompanyFactory();
    	
    	return new ModelAndView("admin/companyfactory", "command", factory);
	}    

    @RequestMapping(value="/admin/saveCompanyFactory.html", method=RequestMethod.POST)	
    public String saveUpdateProduct(@ModelAttribute("factory") CompanyFactory factory, BindingResult result, HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit CompanyFactory:::::::::::::::::");
    	
    	try{
    		adminService.saveOrUpdate(factory);
    		Utils.setSuccessMessage(request, "Company Factory successfully saved/updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating Company Factory :: "+ex);
    		Utils.setErrorMessage(request, "Company Factory can't be saved/updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/factoryList.html";
	}    
    
    @RequestMapping(value="/admin/companyfactoryDelete.html", method=RequestMethod.GET)
    public String deleteProduct(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete company factory form:::::::::::::::::");
    	
    	try{ 	    
    	    if(request.getParameter("id") != null){
    	    	adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"CompanyFactory");  
    	    	Utils.setSuccessMessage(request, "Company Factory successfully deleted.");
    	    }
    	}catch(Exception ex){
    		logger.debug("Error while delete Company Factory :: "+ex);
    		Utils.setErrorMessage(request, "Company Factory already in use. Please remove associated entries first.");
    	}      	    	
	    return "redirect:/admin/factoryList.html";
	}    
}
