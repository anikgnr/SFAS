package com.codeyard.sfas.controllers.operator; 

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
import com.codeyard.sfas.entity.BankAccount;
import com.codeyard.sfas.entity.Distributor;
import com.codeyard.sfas.entity.DistributorDeposit;
import com.codeyard.sfas.entity.ManagerType;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.OprDistributorService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.OprSearchVo;


@Controller
public class DistributorDepositController {
	private static Logger logger = Logger.getLogger(DistributorDepositController.class);
	
	@Autowired(required=true)
	private AdminService adminService;
	
	@Autowired(required=true)
	private OprDistributorService oprDistributorService;
	
		
    @RequestMapping(value="/operator/distributorDepositList.html", method=RequestMethod.GET)
	public String stockPanel(HttpServletRequest request,Model model) {    	
	   	logger.debug(":::::::::: inside operator Distributor deposit List:::::::::::::::::");
	   	if(request.getParameter("id") != null){
	   		Long distributorId = Long.parseLong((String)request.getParameter("id"));	   		
	   		Distributor distributor = (Distributor)adminService.loadEntityById(distributorId, "Distributor");
	   		if(distributor != null){
	   			model.addAttribute("distributorId", distributor.getId());
	   			model.addAttribute("distributorName", distributor.getFullName());
	   			model.addAttribute("accounts", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"BankAccount"));
	   		   	model.addAttribute("users", adminService.getUserListByDept(ManagerType.ACCOUNT.getValue()));	   		   	
	   		}
	   	}
	   	return "operator/distributorDepositList";
	}   	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/operator/distributorCompleteDepositList.html", method=RequestMethod.GET)
	public @ResponseBody Map stockList(HttpServletRequest request, Map map) {    	
	   	List<DistributorDeposit> depositList = oprDistributorService.getDistributorDepositList(OprSearchVo.fetchFromRequest(request));
	   	map.put("deposit", depositList);
		return map;
	}
	
	  @RequestMapping(value="/operator/distributorDeposit.html", method=RequestMethod.GET)
	    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
	    	logger.debug(":::::::::: inside operator add/edit distributor deposit form:::::::::::::::::");
	    	DistributorDeposit deposit=null;
	    	if(request.getParameter("id") != null)
	    		deposit = (DistributorDeposit)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"DistributorDeposit");    		
	    	else{
	    		deposit = new DistributorDeposit();
	    		if(request.getParameter("did") != null){
	    			Distributor distributor = (Distributor)adminService.loadEntityById(Long.parseLong(request.getParameter("did")),"Distributor");
	    			if(distributor != null)
	    				deposit.setDistributor(distributor);
	    		}
	    	}
	    	
	    	Map<Long,String> accounts = new LinkedHashMap<Long,String>();
	    	for(AbstractBaseEntity entity : adminService.getActiveEnityList(AdminSearchVo.fetchFromRequest(request),"BankAccount")){
	    		BankAccount account = (BankAccount)entity;
	    		accounts.put(account.getId(), account.getCompleteName());    	
	    	}
	    	model.addAttribute("accounts", accounts);    	

		    return new ModelAndView("operator/distributorDeposit", "command", deposit);
		}    

	    @RequestMapping(value="/operator/saveDistributorDeposit.html", method=RequestMethod.POST)	
	    public String saveUpdateEntity(@ModelAttribute("deposit") DistributorDeposit deposit, BindingResult result, HttpServletRequest request) {
	    	logger.debug(":::::::::: inside operator save or edit distributor deposit:::::::::::::::::");
	    	
	    	try{    		
	    		adminService.saveOrUpdate(deposit);
	    		Utils.setSuccessMessage(request, "Distributor Deposit successfully saved/updated.");
	    		//Utils.sendMail("anikgnr@gmail.com", "Test email from SFAS system", "sdalkjasdl asdflkasdjf");
	    	}catch(Exception ex){
	    		logger.debug("Error while saving/updating Distributor Deposit :: "+ex);
	    		Utils.setErrorMessage(request, "Distributor Deposit can't be saved/updated. Please contact with System Admin.");
	    	}
	    	
		    return "redirect:/operator/distributorDepositList.html?id="+deposit.getDistributor().getId();
		}    
	    
	    @RequestMapping(value="/operator/distributorDepositDelete.html", method=RequestMethod.GET)
	    public String deleteEntity(HttpServletRequest request,Model model) {
	    	logger.debug(":::::::::: inside operator delete Distributor Deposit form:::::::::::::::::");
	    	
	    	try{ 	    
	    	    if(request.getParameter("id") != null){
	    	    	adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"DistributorDeposit");  
	    	    	Utils.setSuccessMessage(request, "Distributor Deposit successfully deleted.");
	    	    }
	    	}catch(Exception ex){
	    		logger.debug("Error while delete Distributor Deposit :: "+ex);
	    		Utils.setErrorMessage(request, "Distributor Deposit already in use. Please remove associated entries first.");
	    	}   
	    	if(request.getParameter("did") != null)
	    		return "redirect:/operator/distributorDepositList.html?id="+Long.parseLong(request.getParameter("did"));
	    	else{
	    		Utils.setErrorMessage(request, "Distributor id not found after deleting Distributor Deposit. Please contanct with System Admin.");
	    		return "redirect:/operator/distributorList.html";
	    	}
		}        
    
}
