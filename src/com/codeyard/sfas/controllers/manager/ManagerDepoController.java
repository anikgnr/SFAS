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
import com.codeyard.sfas.entity.ManagerType;
import com.codeyard.sfas.entity.NotificationType;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.notification.NotificationGenerator;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.InventoryService;
import com.codeyard.sfas.service.OperatorService;
import com.codeyard.sfas.util.Constants;
import com.codeyard.sfas.util.DepoOrderHelper;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.OprSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Controller
public class ManagerDepoController {
	private static Logger logger = Logger.getLogger(ManagerDepoController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

	@Autowired(required=true)
	private OperatorService operatorService;
	
	@Autowired(required=true)
	private InventoryService inventoryService;


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
	   	    	if(operatorService.approveDepoDeposit(Long.parseLong(request.getParameter("id")))){
	   	    		Utils.setSuccessMessage(request, "Depo Deposit approved successfully.");
	   	    		DepoDeposit deposit = (DepoDeposit)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"DepoDeposit");
	   	    		NotificationGenerator.sendUserNameWiseNotification(deposit.getLastModifiedBy(), NotificationType.DEPO_DEPOSIT_APPROVED, deposit);
	   	    	}else
	   	    		Utils.setErrorMessage(request, "Depo Deposit couldn't be approved. Please contact with System Admin.");	   	    		
	   	    }else
	    		Utils.setErrorMessage(request, "Depo Deposit couldn't be approved. Please contact with System Admin.");
	   	}catch(Exception ex){
	   		logger.debug("Error while approving depo deposit :: "+ex);
	   		Utils.setErrorMessage(request, "Depo Deposit couldn't be approved. Please contact with System Admin.");	   		
	   	}      	    	
		return "redirect:/manager/pendingDepoDepositList.html";
	}	
	 
	@RequestMapping(value="/manager/depoOrderList.html", method=RequestMethod.GET)
	public String orderListPanel(HttpServletRequest request,Model model) {    	
	   	logger.debug(":::::::::: inside manager depo order List:::::::::::::::::");
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
	   	model.addAttribute("regions", adminService.getAllLookUpEntity("Region"));
	   	
	   	return "manager/depoOrderList";
	}   	
			
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manager/depoCompleteOrderList.html", method=RequestMethod.GET)
	public @ResponseBody Map orderList(HttpServletRequest request, Map map) {
		OprSearchVo searchVo = OprSearchVo.fetchFromRequest(request);
		if(!Utils.isNullOrEmpty(Utils.getLoggedSysMgrPost()))
			searchVo.setApproveType(Utils.getLoggedSysMgrPost());
		List<DepoOrder> orderList = operatorService.getDepoOrderList(searchVo);
	   	map.put("order", orderList);
		return map;
	}	 
	
	 @RequestMapping(value="/manager/depoOrder.html", method=RequestMethod.GET)
	 public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside manager add/edit depo order form:::::::::::::::::");
	   	return DepoOrderHelper.loadDepoOrderForm(request, model, adminService, operatorService, "manager");
	 }
	 
	 @RequestMapping(value="/manager/depoOrderApprove.html", method=RequestMethod.GET)
	 public String approveDepoOrder(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside manager account approve depo order form:::::::::::::::::");
	   	
	   	try{ 	    
	   	    if(request.getParameter("id") != null && request.getParameter("type") != null){
	   	    	DepoOrder order = (DepoOrder)adminService.loadEntityById(Long.parseLong((String)request.getParameter("id")),"DepoOrder");
	   	    	if(order != null){
	   	    		order.setOrderLiList(operatorService.getDepoOrderLiList(order.getId()));
	   	    		//DepoOrderHelper.inventoryStockComparison(order, inventoryService);
	   	    		DepoOrderHelper.oderBalanceCurrentBalanceComparison(order);
	   	    		
	   	    		if(!Utils.isNullOrEmpty(order.getErrorMsg())){
		   				request.getSession().setAttribute(Constants.SESSION_DEPO_ORDER, order);
		   				return "redirect:/manager/depoOrder.html?er=1";
		   			}
		   			String type = (String)request.getParameter("type");
	   	    		operatorService.approveDepoOrder(order, type);
		   			Utils.setSuccessMessage(request, "Depo Order successfully approved.");		   			
		   			if(ManagerType.MD.getValue().equals(type)){
		   				NotificationGenerator.sendUserNameWiseNotification(order.getCreatedBy(), NotificationType.DEPO_ORDER_APPROVED, order);
		   			}else{
		   				NotificationGenerator.sendPostWiseNotification(getNotificationType(type), NotificationType.DEPO_ORDER_IN, order);	
		   			}
		   			
	   	    	}else
		   	    	Utils.setErrorMessage(request, "Depo Order couldn't be approved. Please contact with System Admin.");
	   	    }else
	   	    	Utils.setErrorMessage(request, "Depo Order couldn't be approved. Please contact with System Admin.");
	   	}catch(Exception ex){
	   		logger.debug("Error while approving depo order :: "+ex);
	   		Utils.setErrorMessage(request, "Depo Order couldn't be approved. Please contact with System Admin.");	   		
	   	}      	    	
		return "redirect:/manager/depoOrderList.html";
	}	
	 
	private String getNotificationType(String type){
			if(ManagerType.MIS.getValue().equals(type)){
				return ManagerType.ACCOUNT.getValue();
			}else if(ManagerType.ACCOUNT.getValue().equals(type)){
				return ManagerType.Manager.getValue();
			}else if(ManagerType.Manager.getValue().equals(type)){
				return ManagerType.MM.getValue();
			}else if(ManagerType.MM.getValue().equals(type)){
				return ManagerType.MD.getValue();
			}
			return null;
	}
	 @RequestMapping(value="/manager/depoOrderUnApprove.html", method=RequestMethod.GET)
	 public String unApproveDepoOrder(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside manager account un-approve depo order form:::::::::::::::::");
	   	
	   	try{ 	    
	   	    if(request.getParameter("id") != null && request.getParameter("type") != null){
	   	    	DepoOrder order = (DepoOrder)adminService.loadEntityById(Long.parseLong((String)request.getParameter("id")),"DepoOrder");
	   	    	if(order != null){
	   	    		order.setOrderLiList(operatorService.getDepoOrderLiList(order.getId()));
	   	    		String type = (String)request.getParameter("type");		   			
	   	    		operatorService.unApproveDepoOrder(order, type);
		   			Utils.setSuccessMessage(request, "Depo Order successfully un-approved.");
		   			NotificationGenerator.sendUserNameWiseNotification(getUnApprovedNotificationType(type, order), NotificationType.DEPO_ORDER_UNAPPROVED, order);
	   	    	}else
		   	    	Utils.setErrorMessage(request, "Depo Order couldn't be un-approved. Please contact with System Admin.");
	   	    }else
	   	    	Utils.setErrorMessage(request, "Depo Order couldn't be un-approved. Please contact with System Admin.");
	   	}catch(Exception ex){
	   		logger.debug("Error while un-approving depo order :: "+ex);
	   		Utils.setErrorMessage(request, "Depo Order couldn't be un-approved. Please contact with System Admin.");	   		
	   	}      	    	
		return "redirect:/manager/depoOrderList.html";
	}
	 
	private String getUnApprovedNotificationType(String type, DepoOrder order){
			if(ManagerType.MD.getValue().equals(type)){
				return order.getMmApprovedBy();
			}else if(ManagerType.MM.getValue().equals(type)){
				return order.getMgrApprovedBy();
			}else if(ManagerType.Manager.getValue().equals(type)){
				return order.getAccountApprovedBy();
			}else if(ManagerType.ACCOUNT.getValue().equals(type)){
				return order.getCreatedBy();
			}
			return null;
	}
	 
	 
}
