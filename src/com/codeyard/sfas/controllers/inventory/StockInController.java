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
import com.codeyard.sfas.vo.SearchVo;


@Controller
public class StockInController {
	private static Logger logger = Logger.getLogger(StockInController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

	@Autowired(required=true)
	private InventoryService inventoryService;

	@RequestMapping(value="/inventory/stockIn.html", method=RequestMethod.GET)
    public ModelAndView addEditStockIn(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside inventory add/edit stock in form:::::::::::::::::");
    	StockIn stockIn=null;
    	if(request.getParameter("id") != null)
    		stockIn = (StockIn)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"StockIn");    		
    	else
    		stockIn = new StockIn();
    	
    	Map<Long,String> products = new LinkedHashMap<Long,String>();
    	List<AbstractBaseEntity> productList = adminService.getEnityList(new SearchVo(), "Product");
    	for(AbstractBaseEntity entity : productList){
    		Product product = (Product)entity;
    		products.put(product.getId(), product.getProductName()+" - "+product.getBagSize()+" pcs");
    	}
    	model.addAttribute("products",products);
    	    	
    	return new ModelAndView("inventory/stockIn", "command", stockIn);
	}    
	
	 @RequestMapping(value="/inventory/saveStockIn.html", method=RequestMethod.POST)	
	    public String saveUpdateStockIn(@ModelAttribute("stockIn") StockIn stockIn, BindingResult result) {
	    	logger.debug(":::::::::: inside inventory save or edit stockIn:::::::::::::::::");
	    	
	    	try{
	    		adminService.saveOrUpdate(stockIn);
	    	}catch(Exception ex){
	    		logger.debug("Error while saving/updating stockIn :: "+ex);
	    	}
	    	
		    return "redirect:/inventory/stockIn.html";
		}    

    	   
}
