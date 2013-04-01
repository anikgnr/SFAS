package com.codeyard.sfas.controllers.manager; 

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.BankAccount;
import com.codeyard.sfas.entity.Depo;
import com.codeyard.sfas.entity.DepoDeposit;
import com.codeyard.sfas.entity.ManagerType;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.OperatorService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.OprSearchVo;


@Controller
public class ManagerController {
	private static Logger logger = Logger.getLogger(ManagerController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

	@Autowired(required=true)
	private OperatorService operatorService;


	@RequestMapping(value="/manager/home.html", method=RequestMethod.GET)
	public String reportHomePanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside manager home:::::::::::::::::");
    	return "manager/home";
	}   
	
    @RequestMapping(value="/manager/pendingDepoDepositList.html", method=RequestMethod.GET)
	public String stockPanel(HttpServletRequest request,Model model) {    	
	   	logger.debug(":::::::::: inside manager account pending depo deposit List:::::::::::::::::");
	   	List<AbstractBaseEntity> depoList = adminService.getActiveEnityList(AdminSearchVo.fetchFromRequest(request),"Depo");
    	Iterator<AbstractBaseEntity> itr = depoList.iterator();
    	while(itr.hasNext()){
    		Depo depo = (Depo)itr.next();
    		if(depo.isCompanyInventory()){
    			itr.remove();
    			continue;
    		}
    		
    	}  	
	   	model.addAttribute("depos", depoList);
	   	model.addAttribute("accounts", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"BankAccount"));
	   	return "manager/pendingDepoDepositList";
	}   
    
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manager/depoCompleteDepositList.html", method=RequestMethod.GET)
	public @ResponseBody Map stockList(HttpServletRequest request, Map map) {
		OprSearchVo searchVo = OprSearchVo.fetchFromRequest(request);
		searchVo.setAccountApproved(false);
	   	List<DepoDeposit> depositList = operatorService.getDepoDepositList(searchVo);
	   	map.put("deposit", depositList);
		return map;
	}

	 @RequestMapping(value="/manager/approveDepoDeposit.html", method=RequestMethod.GET)
	 public String approveDepoDeposit(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside manager account approve depo deposit form:::::::::::::::::");
	   	
	   	try{ 	    
	   	    if(request.getParameter("id") != null){
	   	    	if(operatorService.approveDepoDeposit(Long.parseLong(request.getParameter("id"))))
	   	    		Utils.setSuccessMessage(request, "Depo Deposit approved successfully.");
	   	    	else
	   	    		Utils.setErrorMessage(request, "Depo Deposit couldn't be approved. Please contact with System Admin.");	   	    		
	   	    }else
	    		Utils.setErrorMessage(request, "Depo Deposit couldn't be approved. Please contact with System Admin.");
	   	}catch(Exception ex){
	   		logger.debug("Error while approving depo deposit :: "+ex);
	   		Utils.setErrorMessage(request, "Depo Deposit couldn't be approved. Please contact with System Admin.");	   		
	   	}      	    	
		return "redirect:/manager/pendingDepoDepositList.html";
	}	
	
}
