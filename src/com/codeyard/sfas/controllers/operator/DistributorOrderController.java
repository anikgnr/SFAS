package com.codeyard.sfas.controllers.operator; 

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

import com.codeyard.sfas.entity.Depo;
import com.codeyard.sfas.entity.Distributor;
import com.codeyard.sfas.entity.DistributorDeposit;
import com.codeyard.sfas.entity.DistributorOrder;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.InventoryService;
import com.codeyard.sfas.service.OperatorService;
import com.codeyard.sfas.service.OprDistributorService;
import com.codeyard.sfas.util.Constants;
import com.codeyard.sfas.util.DistributorOrderHelper;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.OprSearchVo;


@Controller
public class DistributorOrderController {
	private static Logger logger = Logger.getLogger(DistributorOrderController.class);
	
	@Autowired(required=true)
	private AdminService adminService;
	
	@Autowired(required=true)
	private InventoryService inventoryService;
	
	@Autowired(required=true)
	private OprDistributorService oprDistributorService;
	
	@Autowired(required=true)
	private OperatorService operatorService;
	
	@RequestMapping(value="/operator/distributorOrderList.html", method=RequestMethod.GET)
	public String orderListPanel(HttpServletRequest request,Model model) {    	
	   	logger.debug(":::::::::: inside operator distributor order List:::::::::::::::::");
	   	if(request.getParameter("id") != null){
	   		Long distributorId = Long.parseLong((String)request.getParameter("id"));	   		
	   		Distributor distributor = (Distributor)adminService.loadEntityById(distributorId, "Distributor");
	   		if(distributor != null){
	   			model.addAttribute("distributorId", distributor.getId());
	   			model.addAttribute("distributorName", distributor.getFullName());	   				   		   	
	   		}
	   	}
	   	return "operator/distributorOrderList";
	}   	
		
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/operator/distributorCompleteOrderList.html", method=RequestMethod.GET)
	public @ResponseBody Map stockList(HttpServletRequest request, Map map) {    	
		List<DistributorOrder> orderList = oprDistributorService.getDistributorOrderList(OprSearchVo.fetchFromRequest(request));
	   	map.put("order", orderList);
		return map;
	}
		
	 @RequestMapping(value="/operator/distributorOrder.html", method=RequestMethod.GET)
	 public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside operator add/edit distributor order form:::::::::::::::::");
	   	return DistributorOrderHelper.loadDistributorOrderForm(request, model, adminService, oprDistributorService, "operator");
	 }
	 
	 @RequestMapping(value="/operator/saveDistributorOrder.html", method=RequestMethod.POST)	
	 public String saveUpdateEntity(@ModelAttribute("order") DistributorOrder order, BindingResult result, HttpServletRequest request) {
	   	logger.debug(":::::::::: inside operator save or edit Distributor order:::::::::::::::::");
	    	
	   		try{
	   			
	   			Distributor distributor = (Distributor)adminService.loadEntityById(order.getDistributor().getId(),"Distributor");
	   			order.setDistributor(distributor);
	   			
	   			Depo depo= (Depo)adminService.loadEntityById(order.getDepo().getId(),"Depo");
	   			order.setDepo(depo);
	   			
	   			if(order.getDepo().isCompanyInventory())
	   				DistributorOrderHelper.inventoryStockComparison(order, inventoryService);
	   			else
	   				DistributorOrderHelper.depoStockComparison(order, operatorService);
	   			
	   			DistributorOrderHelper.oderBalanceCurrentBalanceComparison(order);
	   			
	   			if(!Utils.isNullOrEmpty(order.getErrorMsg())){
	   				order.setDistributor(distributor);
	   				order.setDistributorBalance(distributor.getCurrentBalance());
	   				order.setLastDeposit((DistributorDeposit)adminService.loadEntityById(order.getLastDeposit().getId(),"DistributorDeposit"));
	   				request.getSession().setAttribute(Constants.SESSION_DISTRIBUTOR_ORDER, order);
	   				return "redirect:/operator/distributorOrder.html?er=1";
	   			}else if(request.getSession().getAttribute(Constants.SESSION_DISTRIBUTOR_ORDER) != null)
	   				request.getSession().removeAttribute(Constants.SESSION_DISTRIBUTOR_ORDER);
	   			oprDistributorService.saveOrUpdateDistributorOrder(order);
	   			Utils.setSuccessMessage(request, "Distributor Order successfully saved/updated.");
	   		}catch(Exception ex){
	   			logger.debug("Operator save/edit Distributor order exception :: "+ex);
	   			Utils.setErrorMessage(request, "Distributor Order can't be saved/updated. Please contact with System Admin.");
	   		}
		    return "redirect:/operator/distributorOrderList.html?id="+order.getDistributor().getId();
	}    
	 
			    
	@RequestMapping(value="/operator/distributorOrderDelete.html", method=RequestMethod.GET)
	public String deleteEntity(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside operator delete Distributor Order form:::::::::::::::::");
	    	
	   	try{ 	    
	   	    if(request.getParameter("id") != null){
	   	    	oprDistributorService.deleteDistributorOrderById(Long.parseLong((String) request.getParameter("id")));
	   	    	Utils.setSuccessMessage(request, "Distributor Order successfully deleted.");
	   	    }
	   	}catch(Exception ex){
	   		logger.debug("Error while delete Distributor Order :: "+ex);
	   		Utils.setErrorMessage(request, "Distributor Order can't be deleted. Please contact with System Admin.");
	   	}   
	   	if(request.getParameter("did") != null)
	   		return "redirect:/operator/distributorOrderList.html?id="+Long.parseLong(request.getParameter("did"));
	   	else{
	   		Utils.setErrorMessage(request, "Distributor id not found after deleting Distributor Order. Please contanct with System Admin.");
	   		return "redirect:/operator/distributorList.html";
	   	}
	} 

	 @RequestMapping(value="/operator/distributorOrderDeliver.html", method=RequestMethod.GET)
	 public String deliverDistributorOrder(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside operator deliver Distributor order form:::::::::::::::::");
	   	
	   	try{ 	    
	   	    if(request.getParameter("id") != null){
	   	    	DistributorOrder order = (DistributorOrder)adminService.loadEntityById(Long.parseLong((String)request.getParameter("id")),"DistributorOrder");
	   	    	if(order != null){
	   	    		
	   	    		DistributorOrderHelper.oderBalanceCurrentBalanceComparison(order);
	   	    		
	   	    		if(!Utils.isNullOrEmpty(order.getErrorMsg())){
		   				request.getSession().setAttribute(Constants.SESSION_DISTRIBUTOR_ORDER, order);
		   				return "redirect:/operator/distributorOrder.html?er=1";
		   			}
		   			
	   	    		oprDistributorService.deliverDistributorOrder(order);
		   			Utils.setSuccessMessage(request, "Distributor Order successfully delivered.");
	   	    	}else
		   	    	Utils.setErrorMessage(request, "Distributor Order couldn't be delivered. Please contact with System Admin.");
	   	    }else
	   	    	Utils.setErrorMessage(request, "Distributor Order couldn't be delivered. Please contact with System Admin.");
	   	}catch(Exception ex){
	   		logger.debug("Error while delivering Distributor order :: "+ex);
	   		Utils.setErrorMessage(request, "Distributor Order couldn't be delivered. Please contact with System Admin.");	   		
	   	}      	    	
		return "redirect:/operator/distributorOrderList.html";
	}	
	 
	 @RequestMapping(value="/operator/distributorOrderReject.html", method=RequestMethod.GET)
	 public String rejectDistributorOrder(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside operator reject Distributor order form:::::::::::::::::");
	   	
	   	try{ 	    
	   	    if(request.getParameter("id") != null){
	   	    	DistributorOrder order = (DistributorOrder)adminService.loadEntityById(Long.parseLong((String)request.getParameter("id")),"DistributorOrder");
	   	    	if(order != null){
	   	    		
	   	    		order.setMdApproved(false);
	   	    		adminService.saveOrUpdate(order);
		   			Utils.setSuccessMessage(request, "Distributor Order successfully rejected.");
	   	    	}else
		   	    	Utils.setErrorMessage(request, "Distributor Order couldn't be rejected. Please contact with System Admin.");
	   	    }else
	   	    	Utils.setErrorMessage(request, "Distributor Order couldn't be rejected. Please contact with System Admin.");
	   	}catch(Exception ex){
	   		logger.debug("Error while rejecting Distributor order :: "+ex);
	   		Utils.setErrorMessage(request, "Distributor Order couldn't be rejected. Please contact with System Admin.");	   		
	   	}      	    	
		return "redirect:/operator/distributorOrderList.html";
	}		 
}
