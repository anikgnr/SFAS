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
import org.springframework.web.servlet.ModelAndView;

import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.BankAccount;
import com.codeyard.sfas.entity.Depo;
import com.codeyard.sfas.entity.DepoDeposit;
import com.codeyard.sfas.entity.DepoOrder;
import com.codeyard.sfas.entity.DepoOrderLi;
import com.codeyard.sfas.entity.DistributorDeposit;
import com.codeyard.sfas.entity.DistributorOrder;
import com.codeyard.sfas.entity.ManagerType;
import com.codeyard.sfas.entity.NotificationType;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.notification.NotificationGenerator;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.InventoryService;
import com.codeyard.sfas.service.OperatorService;
import com.codeyard.sfas.service.OprDistributorService;
import com.codeyard.sfas.util.Constants;
import com.codeyard.sfas.util.DepoOrderHelper;
import com.codeyard.sfas.util.DistributorOrderHelper;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.OprSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Controller
public class ManagerDistributorController {
	private static Logger logger = Logger.getLogger(ManagerDistributorController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

	@Autowired(required=true)
	private OprDistributorService oprDistributorService;
	
	
    @RequestMapping(value="/manager/pendingDistributorDepositList.html", method=RequestMethod.GET)
	public String stockPanel(HttpServletRequest request,Model model) {    	
	   	logger.debug(":::::::::: inside manager account pending Distributor deposit List:::::::::::::::::");
	   	List<AbstractBaseEntity> distributorList = adminService.getActiveEnityList(AdminSearchVo.fetchFromRequest(request),"Distributor");
    	model.addAttribute("distributors", distributorList);
	   	model.addAttribute("accounts", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"BankAccount"));
	   	return "manager/pendingDistributorDepositList";
	}   
    
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manager/distributorCompleteDepositList.html", method=RequestMethod.GET)
	public @ResponseBody Map stockList(HttpServletRequest request, Map map) {
		OprSearchVo searchVo = OprSearchVo.fetchFromRequest(request);
		searchVo.setAccountApproved(false);
	   	List<DistributorDeposit> depositList = oprDistributorService.getDistributorDepositList(searchVo);
	   	map.put("deposit", depositList);
		return map;
	}

	 @RequestMapping(value="/manager/approveDistributorDeposit.html", method=RequestMethod.GET)
	 public String approveDistributorDeposit(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside manager account approve Distributor deposit form:::::::::::::::::");
	   	
	   	try{ 	    
	   	    if(request.getParameter("id") != null){
	   	    	if(oprDistributorService.approveDistributorDeposit(Long.parseLong(request.getParameter("id")))){
	   	    		Utils.setSuccessMessage(request, "Distributor Deposit approved successfully.");
	   	    		DistributorDeposit deposit = (DistributorDeposit)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"DistributorDeposit");
	   	    		NotificationGenerator.sendUserNameWiseNotification(deposit.getLastModifiedBy(), NotificationType.DISTRIBUTOR_DEPOSIT_APPROVED, deposit);
	   	    	}else
	   	    		Utils.setErrorMessage(request, "Distributor Deposit couldn't be approved. Please contact with System Admin.");	   	    		
	   	    }else
	    		Utils.setErrorMessage(request, "Distributor Deposit couldn't be approved. Please contact with System Admin.");
	   	}catch(Exception ex){
	   		logger.debug("Error while approving Distributor deposit :: "+ex);
	   		Utils.setErrorMessage(request, "Distributor Deposit couldn't be approved. Please contact with System Admin.");	   		
	   	}      	    	
		return "redirect:/manager/pendingDistributorDepositList.html";
	}	
	 
	 
	@RequestMapping(value="/manager/distributorOrderList.html", method=RequestMethod.GET)
	public String orderListPanel(HttpServletRequest request,Model model) {    	
	   	logger.debug(":::::::::: inside manager distributor order List:::::::::::::::::");
	   	
    	model.addAttribute("distributors", adminService.getActiveEnityList(AdminSearchVo.fetchFromRequest(request),"Distributor"));
    	model.addAttribute("rsms", adminService.getActiveEnityList(AdminSearchVo.fetchFromRequest(request),"RSM"));	   	
	   	
	   	return "manager/distributorOrderList";
	}   	
			
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manager/distributorCompleteOrderList.html", method=RequestMethod.GET)
	public @ResponseBody Map orderList(HttpServletRequest request, Map map) {
		OprSearchVo searchVo = OprSearchVo.fetchFromRequest(request);
		if(!Utils.isNullOrEmpty(Utils.getLoggedSysMgrPost()))
			searchVo.setApproveType(Utils.getLoggedSysMgrPost());
		List<DistributorOrder> orderList = oprDistributorService.getDistributorOrderList(searchVo);
	   	map.put("order", orderList);
		return map;
	}	 
	
	 @RequestMapping(value="/manager/distributorOrder.html", method=RequestMethod.GET)
	 public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside manager add/edit distributor order form:::::::::::::::::");
	   	return DistributorOrderHelper.loadDistributorOrderForm(request, model, adminService, oprDistributorService, "manager");
	 }
	 
	 @RequestMapping(value="/manager/distributorOrderApprove.html", method=RequestMethod.GET)
	 public String approveDistributorOrder(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside manager account approve distributor order form:::::::::::::::::");
	   	
	   	try{ 	    
	   	    if(request.getParameter("id") != null && request.getParameter("type") != null){
	   	    	DistributorOrder order = (DistributorOrder)adminService.loadEntityById(Long.parseLong((String)request.getParameter("id")),"DistributorOrder");
	   	    	if(order != null){
	   	    		
	   	    		DistributorOrderHelper.oderBalanceCurrentBalanceComparison(order);
	   	    		
	   	    		if(!Utils.isNullOrEmpty(order.getErrorMsg())){
		   				request.getSession().setAttribute(Constants.SESSION_DISTRIBUTOR_ORDER, order);
		   				return "redirect:/manager/distributorOrder.html?er=1";
		   			}
		   			
	   	    		oprDistributorService.approveDistributorOrder(order, (String)request.getParameter("type"));
		   			Utils.setSuccessMessage(request, "Distributor Order successfully approved.");
	   	    	}else
		   	    	Utils.setErrorMessage(request, "Distributor Order couldn't be approved. Please contact with System Admin.");
	   	    }else
	   	    	Utils.setErrorMessage(request, "Distributor Order couldn't be approved. Please contact with System Admin.");
	   	}catch(Exception ex){
	   		logger.debug("Error while approving Distributor order :: "+ex);
	   		Utils.setErrorMessage(request, "Distributor Order couldn't be approved. Please contact with System Admin.");	   		
	   	}      	    	
		return "redirect:/manager/distributorOrderList.html";
	}	

	 @RequestMapping(value="/manager/distributorOrderUnApprove.html", method=RequestMethod.GET)
	 public String unApproveDistributorOrder(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside manager account un-approve Distributor order form:::::::::::::::::");
	   	
	   	try{ 	    
	   	    if(request.getParameter("id") != null && request.getParameter("type") != null){
	   	    	DistributorOrder order = (DistributorOrder)adminService.loadEntityById(Long.parseLong((String)request.getParameter("id")),"DistributorOrder");
	   	    	if(order != null){
	   	    				   			
	   	    		oprDistributorService.unApproveDistributorOrder(order, (String)request.getParameter("type"));
		   			Utils.setSuccessMessage(request, "Distributor Order successfully un-approved.");
	   	    	}else
		   	    	Utils.setErrorMessage(request, "Distributor Order couldn't be un-approved. Please contact with System Admin.");
	   	    }else
	   	    	Utils.setErrorMessage(request, "Distributor Order couldn't be un-approved. Please contact with System Admin.");
	   	}catch(Exception ex){
	   		logger.debug("Error while un-approving Distributor order :: "+ex);
	   		Utils.setErrorMessage(request, "Distributor Order couldn't be un-approved. Please contact with System Admin.");	   		
	   	}      	    	
		return "redirect:/manager/distributorOrderList.html";
	}
}
