package com.codeyard.sfas.controllers.operator; 

import java.util.ArrayList;
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
import com.codeyard.sfas.entity.DepoOrder;
import com.codeyard.sfas.entity.DepoOrderLi;
import com.codeyard.sfas.entity.DepoSellSummary;
import com.codeyard.sfas.entity.DepoStockSummary;
import com.codeyard.sfas.entity.ManagerType;
import com.codeyard.sfas.entity.Product;
import com.codeyard.sfas.entity.RSM;
import com.codeyard.sfas.entity.StockSummary;
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
public class DepoOrderController {
	private static Logger logger = Logger.getLogger(DepoOrderController.class);
	
	@Autowired(required=true)
	private AdminService adminService;
	
	@Autowired(required=true)
	private InventoryService inventoryService;
	
	@Autowired(required=true)
	private OperatorService operatorService;
	
	@RequestMapping(value="/operator/depoOrderList.html", method=RequestMethod.GET)
	public String orderListPanel(HttpServletRequest request,Model model) {    	
	   	logger.debug(":::::::::: inside operator depo order List:::::::::::::::::");
	   	if(request.getParameter("id") != null){
	   		Long depoId = Long.parseLong((String)request.getParameter("id"));	   		
	   		Depo depo = (Depo)adminService.loadEntityById(depoId, "Depo");
	   		if(depo != null){
	   			model.addAttribute("depoId", depo.getId());
	   			model.addAttribute("depoName", depo.getFullName());	   				   		   	
	   		}
	   	}
	   	return "operator/depoOrderList";
	}   	
		
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/operator/depoCompleteOrderList.html", method=RequestMethod.GET)
	public @ResponseBody Map stockList(HttpServletRequest request, Map map) {    	
		List<DepoOrder> orderList = operatorService.getDepoOrderList(OprSearchVo.fetchFromRequest(request));
	   	map.put("order", orderList);
		return map;
	}
		
	 @RequestMapping(value="/operator/depoOrder.html", method=RequestMethod.GET)
	 public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside operator add/edit depo order form:::::::::::::::::");
	   	return DepoOrderHelper.loadDepoOrderForm(request, model, adminService, operatorService, "operator");
	 }
	 
	 @RequestMapping(value="/operator/saveDepoOrder.html", method=RequestMethod.POST)	
	 public String saveUpdateEntity(@ModelAttribute("order") DepoOrder order, BindingResult result, HttpServletRequest request) {
	   	logger.debug(":::::::::: inside operator save or edit depo order:::::::::::::::::");
	    	
	   		try{
	   			
	   			Depo depo = (Depo)adminService.loadEntityById(order.getDepo().getId(),"Depo");
	   			order.setDepo(depo);
	   			
	   			DepoOrderHelper.inventoryStockComparison(order, inventoryService);
	   			
	   			DepoOrderHelper.oderBalanceCurrentBalanceComparison(order);
	   			
	   			if(!Utils.isNullOrEmpty(order.getErrorMsg())){
	   				order.setDepo(depo);
	   				order.setDepoBalance(depo.getCurrentBalance());
	   				order.setLastDeposit((DepoDeposit)adminService.loadEntityById(order.getLastDeposit().getId(),"DepoDeposit"));
	   				request.getSession().setAttribute(Constants.SESSION_DEPO_ORDER, order);
	   				return "redirect:/operator/depoOrder.html?er=1";
	   			}else if(request.getSession().getAttribute(Constants.SESSION_DEPO_ORDER) != null)
	   				request.getSession().removeAttribute(Constants.SESSION_DEPO_ORDER);
	   			operatorService.saveOrUpdateDepoOrder(order);
	   			Utils.setSuccessMessage(request, "Depo Order successfully saved/updated.");
	   		}catch(Exception ex){
	   			logger.debug("Operator save/edit depo order exception :: "+ex);
	   			Utils.setErrorMessage(request, "Depo Order can't be saved/updated. Please contact with System Admin.");
	   		}
		    return "redirect:/operator/depoOrderList.html?id="+order.getDepo().getId();
	}    
	 
			    
	@RequestMapping(value="/operator/depoOrderDelete.html", method=RequestMethod.GET)
	public String deleteEntity(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside operator delete Depo Order form:::::::::::::::::");
	    	
	   	try{ 	    
	   	    if(request.getParameter("id") != null){
	   	    	operatorService.deleteDepoOrderById(Long.parseLong((String) request.getParameter("id")));
	   	    	Utils.setSuccessMessage(request, "Depo Order successfully deleted.");
	   	    }
	   	}catch(Exception ex){
	   		logger.debug("Error while delete Depo Order :: "+ex);
	   		Utils.setErrorMessage(request, "Depo Order can't be deleted. Please contact with System Admin.");
	   	}   
	   	if(request.getParameter("did") != null)
	   		return "redirect:/operator/depoOrderList.html?id="+Long.parseLong(request.getParameter("did"));
	   	else{
	   		Utils.setErrorMessage(request, "Depo id not found after deleting Depo Order. Please contanct with System Admin.");
	   		return "redirect:/operator/depoList.html";
	   	}
	} 

}
