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
import com.codeyard.sfas.entity.Product;
import com.codeyard.sfas.entity.Role;
import com.codeyard.sfas.entity.StockIn;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.InventoryService;
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
    	if(request.getParameter("id") != null)
    		stockIn = (StockIn)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"StockIn");    		
    	else
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
	public String saveUpdateStockIn(@ModelAttribute("stockIn") StockIn stockIn, BindingResult result) {
	  	logger.debug(":::::::::: inside inventory save or edit stockIn:::::::::::::::::");
	    	
	   	try{
	   		if(stockIn.getId() != null && stockIn.getId() > 0){
	   			StockIn mainStockIn = (StockIn)adminService.loadEntityById(stockIn.getId(),"StockIn");
	   			mainStockIn.setPreviousQuantity(mainStockIn.getQuantity());
	   			mainStockIn.merge(stockIn);
	   			inventoryService.saveOrUpdateStockIn(mainStockIn);
	   		}else
	   			inventoryService.saveOrUpdateStockIn(stockIn);
	   	}catch(Exception ex){
	   		logger.debug("Error while saving/updating stockIn :: "+ex);
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
	@RequestMapping(value = "/inventory/todayStockInList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
	   	List<StockIn> stockInList = inventoryService.getTodaysStockInList(StockSearchVo.fetchFromRequest(request));
	   	map.put("stockin", stockInList);
		return map;
	}
	
	 @RequestMapping(value="/inventory/stockinDelete.html", method=RequestMethod.GET)
	 public String deleteEntity(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside inventory delete stock in form:::::::::::::::::");
	    	
	   	if(request.getParameter("id") != null){	   		
	   		inventoryService.deleteStockInById(Long.parseLong(request.getParameter("id")));
	   	}
	    	    	
	    return "redirect:/inventory/stockinList.html";
	}        
	    
}
