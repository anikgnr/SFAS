package com.codeyard.sfas.controllers.operator; 

import java.util.Iterator;
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

import com.codeyard.sfas.entity.ASM;
import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.BankAccount;
import com.codeyard.sfas.entity.DamageSummary;
import com.codeyard.sfas.entity.DamageType;
import com.codeyard.sfas.entity.Depo;
import com.codeyard.sfas.entity.DepoDamageSummary;
import com.codeyard.sfas.entity.DepoDeposit;
import com.codeyard.sfas.entity.DepoStockSummary;
import com.codeyard.sfas.entity.ManagerType;
import com.codeyard.sfas.entity.NotificationType;
import com.codeyard.sfas.entity.RSM;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.notification.NotificationGenerator;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.OperatorService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.OprSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Controller
public class DepoDepositController {
	private static Logger logger = Logger.getLogger(DepoDepositController.class);
	
	@Autowired(required=true)
	private AdminService adminService;
	
	@Autowired(required=true)
	private OperatorService operatorService;
	
		
    @RequestMapping(value="/operator/depoDepositList.html", method=RequestMethod.GET)
	public String stockPanel(HttpServletRequest request,Model model) {    	
	   	logger.debug(":::::::::: inside operator depo deposit List:::::::::::::::::");
	   	if(request.getParameter("id") != null){
	   		Long depoId = Long.parseLong((String)request.getParameter("id"));	   		
	   		Depo depo = (Depo)adminService.loadEntityById(depoId, "Depo");
	   		if(depo != null){
	   			model.addAttribute("depoId", depo.getId());
	   			model.addAttribute("depoName", depo.getFullName());
	   			model.addAttribute("accounts", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"BankAccount"));
	   		   	model.addAttribute("users", adminService.getUserListByDept(ManagerType.ACCOUNT.getValue()));	   		   	
	   		}
	   	}
	   	return "operator/depoDepositList";
	}   	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/operator/depoCompleteDepositList.html", method=RequestMethod.GET)
	public @ResponseBody Map stockList(HttpServletRequest request, Map map) {    	
	   	List<DepoDeposit> depositList = operatorService.getDepoDepositList(OprSearchVo.fetchFromRequest(request));
	   	map.put("deposit", depositList);
		return map;
	}
	
	  @RequestMapping(value="/operator/depoDeposit.html", method=RequestMethod.GET)
	    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
	    	logger.debug(":::::::::: inside operator add/edit depo deposit form:::::::::::::::::");
	    	DepoDeposit deposit=null;
	    	if(request.getParameter("id") != null)
	    		deposit = (DepoDeposit)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"DepoDeposit");    		
	    	else{
	    		deposit = new DepoDeposit();
	    		if(request.getParameter("did") != null){
	    			Depo depo = (Depo)adminService.loadEntityById(Long.parseLong(request.getParameter("did")),"Depo");
	    			if(depo != null)
	    				deposit.setDepo(depo);
	    		}
	    	}
	    	
	    	Map<Long,String> accounts = new LinkedHashMap<Long,String>();
	    	for(AbstractBaseEntity entity : adminService.getActiveEnityList(AdminSearchVo.fetchFromRequest(request),"BankAccount")){
	    		BankAccount account = (BankAccount)entity;
	    		accounts.put(account.getId(), account.getCompleteName());    	
	    	}
	    	model.addAttribute("accounts", accounts);    	

		    return new ModelAndView("operator/depoDeposit", "command", deposit);
		}    

	    @RequestMapping(value="/operator/saveDepoDeposit.html", method=RequestMethod.POST)	
	    public String saveUpdateEntity(@ModelAttribute("deposit") DepoDeposit deposit, BindingResult result, HttpServletRequest request) {
	    	logger.debug(":::::::::: inside operator save or edit depo deposit:::::::::::::::::");
	    	
	    	try{    		
	    		Depo depo = (Depo)adminService.loadEntityById(deposit.getDepo().getId(),"Depo");
	    		deposit.setDepo(depo);
	    		BankAccount account = (BankAccount)adminService.loadEntityById(deposit.getAccount().getId(),"BankAccount");
	    		deposit.setAccount(account);
	    		
	    		adminService.saveOrUpdate(deposit);
	    		Utils.setSuccessMessage(request, "Depo Deposit successfully saved/updated.");
	    		NotificationGenerator.sendPostWiseNotification(ManagerType.ACCOUNT.getValue(), NotificationType.DEPO_DEPOSIT_IN, deposit);
	    	}catch(Exception ex){
	    		logger.debug("Error while saving/updating Depo Deposit :: "+ex);
	    		Utils.setErrorMessage(request, "Depo Deposit can't be saved/updated. Please contact with System Admin.");
	    	}
	    	
		    return "redirect:/operator/depoDepositList.html?id="+deposit.getDepo().getId();
		}    
	    
	    @RequestMapping(value="/operator/depoDepositDelete.html", method=RequestMethod.GET)
	    public String deleteEntity(HttpServletRequest request,Model model) {
	    	logger.debug(":::::::::: inside operator delete Depo Deposit form:::::::::::::::::");
	    	
	    	try{ 	    
	    	    if(request.getParameter("id") != null){
	    	    	DepoDeposit deposit = (DepoDeposit)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"DepoDeposit");
	    	    	adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"DepoDeposit");  
	    	    	Utils.setSuccessMessage(request, "Depo Deposit successfully deleted.");
	    	    	NotificationGenerator.sendUserNameWiseNotification(deposit.getLastModifiedBy(), NotificationType.DEPO_DEPOSIT_DELETED, deposit);
	    	    }
	    	}catch(Exception ex){
	    		logger.debug("Error while delete Depo Deposit :: "+ex);
	    		Utils.setErrorMessage(request, "Depo Deposit already in use. Please remove associated entries first.");
	    	}   
	    	if(request.getParameter("did") != null)
	    		return "redirect:/operator/depoDepositList.html?id="+Long.parseLong(request.getParameter("did"));
	    	else{
	    		Utils.setErrorMessage(request, "Depo id not found after deleting Depo Deposit. Please contanct with System Admin.");
	    		return "redirect:/operator/depoList.html";
	    	}
		}        
    
}
