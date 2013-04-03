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
	   	//List<DepoDeposit> depositList = operatorService.getDepoDepositList(OprSearchVo.fetchFromRequest(request));
	   	map.put("deposit", new ArrayList<DepoOrder>());
		return map;
	}
		
	 @RequestMapping(value="/operator/depoOrder.html", method=RequestMethod.GET)
	 public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside operator add/edit depo order form:::::::::::::::::");
	   	DepoOrder order=null;
	   	if(request.getParameter("er") != null && request.getSession().getAttribute(Constants.SESSION_DEPO_ORDER) != null){
	   		
	   		order = (DepoOrder)request.getSession().getAttribute(Constants.SESSION_DEPO_ORDER);
	   		
	   	}else if(request.getParameter("id") != null){
	   		
	   		order = (DepoOrder)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"DepoOrder");
	   		if(order.isMisApproved()){
	   			Utils.setErrorMessage(request, "Order has been already approved by MIS.Can't edit/delete this anymore.");
	   			return new ModelAndView("redirect:/operator/depoOrderList.html");
	   		}
	   		order.setOrderLiList(operatorService.getDepoOrderLiList(order.getId()));
	   		
	   	}else{
	   		
	   		order = new DepoOrder();
	   		if(request.getParameter("did") != null){
	   			Depo depo = (Depo)adminService.loadEntityById(Long.parseLong(request.getParameter("did")),"Depo");
	   			if(depo != null){
	   				order.setDepo(depo);
	   				DepoDeposit deposit = operatorService.getLatestDepoDeposit(depo.getId());
	   				if(deposit != null)
	   					order.setLastDeposit(deposit);
	   				StockSearchVo searchVo = new StockSearchVo();
	   				searchVo.setDepoId(depo.getId());
	   				List<DepoStockSummary> stockList = operatorService.getDepoCurrentStockList(searchVo);
	   				List<DepoDamageSummary> damageList = operatorService.getDepoDamageStockList(searchVo);
	   				List<DepoSellSummary> saleList = operatorService.getDepoSellSummaryList(searchVo);
	   				
	   				List<AbstractBaseEntity> products = adminService.getActiveEnityList(AdminSearchVo.fetchFromRequest(request),"Product");
	   				DepoOrderLi orderLi = null;
	   				int serial = 1;
	   				for(AbstractBaseEntity entity : products){
	   					Product product = (Product)entity;
	   					orderLi = new DepoOrderLi();
	   					
	   					orderLi.setSerial(serial++);
	   					orderLi.setProduct(product);
	   					orderLi.setCurrentStock(getCurrentStock(stockList, product));
	   					orderLi.setTotalSale(getCurrentSale(saleList, product));
	   					orderLi.setTotalDamage(getCurrentDamage(damageList, product));
	   					orderLi.setQuantity(0L);
	   					orderLi.setCurrentRate(product.getRate());
	   					orderLi.setCurrentProfitMargin(product.getProfitMargin());
	   					orderLi.setAmount(0.0);
	   					
	   					order.getOrderLiList().add(orderLi);
	   				}
	   			}
	   		}
	   	}	    
	   	
	    return new ModelAndView("operator/depoOrder", "command", order);
	 }
	 
	 @RequestMapping(value="/operator/saveDepoOrder.html", method=RequestMethod.POST)	
	 public String saveUpdateEntity(@ModelAttribute("order") DepoOrder order, BindingResult result, HttpServletRequest request) {
	   	logger.debug(":::::::::: inside operator save or edit depo order:::::::::::::::::");
	    	
	   		try{
	   			order.setErrorMsg("");
	   			List<StockSummary> stocks = inventoryService.getCurrentStockList(new StockSearchVo());
	   			boolean hasError = false;
	   			for(DepoOrderLi orderLi : order.getOrderLiList()){
	   				if(isQuantityExceededWithInventory(stocks, orderLi)){
	   					orderLi.setHasError(true);
	   					hasError = true;
	   				}
	   			}
	   			if(hasError){
	   				order.setErrorMsg("* Highlighted Product quantities are not available on Inventory now.<br/>");
	   			}
	   			Depo depo = (Depo)adminService.loadEntityById(order.getDepo().getId(),"Depo");
	   			if(order.getOrderAmount() > depo.getCurrentBalance()){
	   				order.setErrorMsg(order.getErrorMsg()+"* Payable Amount exceeds Current Balance. Please refactor your Order.<br/>");
	   				order.setHasError(true);
	   			}
	   			if(!Utils.isNullOrEmpty(order.getErrorMsg())){
	   				order.setDepo(depo);
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
		    return "redirect:/operator/depoList.html";
	}    
	 
	 private boolean isQuantityExceededWithInventory(List<StockSummary> stocks, DepoOrderLi orderLi){
		 for(StockSummary stock : stocks){
			 if(stock.getProduct().getId() == orderLi.getProduct().getId()){
				 if(orderLi.getQuantity() > stock.getQuantity())
					 return true;
				 else
					 return false;
			 }
		 }
		 if(orderLi.getQuantity() > 0)
			 return true;
		 else
			 return false;
	 }
	 
	 private Long getCurrentStock(List<DepoStockSummary> stockList, Product product){
		 for(DepoStockSummary summary : stockList){
			 if(summary.getProduct().getId() == product.getId()){
				 return summary.getQuantity();
			 }
		 }
		 return 0L;
	 }
	 private Long getCurrentDamage(List<DepoDamageSummary> stockList, Product product){
		 for(DepoDamageSummary summary : stockList){
			 if(summary.getProduct().getId() == product.getId()){
				 return summary.getQuantity();
			 }
		 }
		 return 0L;
	 }
	 private Long getCurrentSale(List<DepoSellSummary> stockList, Product product){
		 for(DepoSellSummary summary : stockList){
			 if(summary.getProduct().getId() == product.getId()){
				 return summary.getQuantity();
			 }
		 }
		 return 0L;
	 }
	/*	
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
	    		adminService.saveOrUpdate(deposit);
	    		Utils.setSuccessMessage(request, "Depo Deposit successfully saved/updated.");
	    		//Utils.sendMail("anikgnr@gmail.com", "Test email from SFAS system", "sdalkjasdl asdflkasdjf");
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
	    	    	adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"DepoDeposit");  
	    	    	Utils.setSuccessMessage(request, "Depo Deposit successfully deleted.");
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
		*/       
    
}
