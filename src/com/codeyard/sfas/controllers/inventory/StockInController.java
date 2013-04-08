package com.codeyard.sfas.controllers.inventory; 

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
import com.codeyard.sfas.entity.DepoOrder;
import com.codeyard.sfas.entity.Product;
import com.codeyard.sfas.entity.Role;
import com.codeyard.sfas.entity.StockIn;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.InventoryService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Controller
public class StockInController {
	private static Logger logger = Logger.getLogger(StockInController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

	@Autowired(required=true)
	private InventoryService inventoryService;

	@RequestMapping(value="/inventory/stockin.html", method=RequestMethod.GET)
    public ModelAndView addEditStockIn(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside inventory add/edit stock in form:::::::::::::::::");
    	StockIn stockIn=null;
    	if(request.getParameter("id") != null){
    		stockIn = (StockIn)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"StockIn");   		
    	}else
    		stockIn = new StockIn();
    	
    	Map<Long,String> products = new LinkedHashMap<Long,String>();
    	List<AbstractBaseEntity> productList = adminService.getEnityList(new AdminSearchVo(), "Product");
    	for(AbstractBaseEntity entity : productList){
    		Product product = (Product)entity;
    		products.put(product.getId(), product.getProductName()+" - "+product.getBagSize()+" pcs");
    	}
    	model.addAttribute("products",products);
    	    	
    	return new ModelAndView("inventory/stockin", "command", stockIn);
	}    
	
	@RequestMapping(value="/inventory/saveStockin.html", method=RequestMethod.POST)	
	public String saveUpdateStockIn(@ModelAttribute("stockIn") StockIn stockIn, BindingResult result,HttpServletRequest request) {
	  	logger.debug(":::::::::: inside inventory save or edit stockIn:::::::::::::::::");
	    	
	   	try{
	   		
	   		String message = "Stock In Entry successfully saved.";
	   		if(Utils.isInRole(Role.INVENTORY_ADMIN.getValue())){
	   			message = "Stock In Entry successfully saved. Notification for approval has been sent to Inventory Admin.";
	   		}
	   		adminService.saveOrUpdate(stockIn);	
	   		
	   		Utils.setSuccessMessage(request, message);
	   	}catch(Exception ex){
	   		logger.debug("Error while saving/updating stockIn :: "+ex);
	   		Utils.setErrorMessage(request, "Stock In Entry can't be saved/updated. Please contact with System Admin.");
	   	}
	   	
	    return "redirect:/inventory/stockinList.html";
	}    
	 
	@RequestMapping(value="/inventory/stockinList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside inventory stock in List:::::::::::::::::");
	   	model.addAttribute("products", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"Product"));
	   	model.addAttribute("users", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"User"));
	   	return "inventory/stockinList";
	}   	    
    	  
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/inventory/pendingStockInList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
	   	List<StockIn> stockInList = inventoryService.getPendingStockInList(StockSearchVo.fetchFromRequest(request));
	   	map.put("stockin", stockInList);
		return map;
	}
	
	 @RequestMapping(value="/inventory/stockinDelete.html", method=RequestMethod.GET)
	 public String deleteEntity(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside inventory delete stock in form:::::::::::::::::");
	    try{	
		   	if(request.getParameter("id") != null){	   		
		   		inventoryService.deleteStockInById(Long.parseLong(request.getParameter("id")));
		   		Utils.setSuccessMessage(request, "Stock In Entry successfully deleted.");
		   	}
	    }catch(Exception ex){
	    	logger.debug("stock in delete error "+ex);
	    	Utils.setErrorMessage(request, "Stock In Entry coudn't be deleted. Please contact with System Admin.");
	    }	    	    	
	    return "redirect:/inventory/stockinList.html";
	}        

	 @RequestMapping(value="/inventory/stockinApprove.html", method=RequestMethod.GET)
	 public String approveEntity(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside inventory approve stock in form:::::::::::::::::");
	    try{	
		   	if(request.getParameter("id") != null){	   		
		   		StockIn stockIn = (StockIn)adminService.loadEntityById(Long.parseLong((String)request.getParameter("id")),"StockIn");
		   		stockIn.setApproved(true);
		   		stockIn.setApprovedBy(Utils.getLoggedUser());
		   		stockIn.setApprovedDate(Utils.today());		   		
		   		inventoryService.saveOrUpdateStockIn(stockIn);
		   		Utils.setSuccessMessage(request, "Stock In Entry successfully approved.");
		   	}
	    }catch(Exception ex){
	    	logger.debug("stock in approved error "+ex);
			Utils.setErrorMessage(request, "Stock In Entry coudn't be approved. Please contact with System Admin.");
	    }	    	    	
	    return "redirect:/inventory/stockinList.html";
	}        
	 
}
