package com.codeyard.sfas.controllers.admin; 

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
import com.codeyard.sfas.entity.Inventory;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.vo.SearchVo;


@Controller
public class InventoryController {
	private static Logger logger = Logger.getLogger(InventoryController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/inventoryList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin inventory home:::::::::::::::::");
    	return "admin/inventoryList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeInventoryList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> inventoryList = adminService.getEnityList(SearchVo.fetchFromRequest(request),"Inventory");
    	map.put("inventory", inventoryList);
		return map;
    }
    
    @RequestMapping(value="/admin/inventory.html", method=RequestMethod.GET)
    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit inventory form:::::::::::::::::");
    	Inventory inventory=null;
    	if(request.getParameter("id") != null)
    		inventory = (Inventory)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"Inventory");    		
    	else
    		inventory = new Inventory();
    	    	
    	return new ModelAndView("admin/inventory", "command", inventory);
	}    
    
    @RequestMapping(value="/admin/saveInventory.html", method=RequestMethod.POST)	
    public String saveUpdateEntity(@ModelAttribute("inventory") Inventory inventory, BindingResult result) {
    	logger.debug(":::::::::: inside admin save or edit inventory:::::::::::::::::");
    	
    	try{    		
    		adminService.saveOrUpdate(inventory);
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating inventory :: "+ex);
    	}
    	
	    return "redirect:/admin/inventoryList.html";
	}    
    
    @RequestMapping(value="/admin/inventoryDelete.html", method=RequestMethod.GET)
    public String deleteEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete inventory form:::::::::::::::::");
    	
    	if(request.getParameter("id") != null)
    		adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"Inventory");    		
    	    	
	    return "redirect:/admin/inventoryList.html";
	}        
}
