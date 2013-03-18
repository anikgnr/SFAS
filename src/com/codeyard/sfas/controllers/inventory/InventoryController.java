package com.codeyard.sfas.controllers.inventory; 

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
import com.codeyard.sfas.entity.DamageSummary;
import com.codeyard.sfas.entity.DamageType;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.InventoryService;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Controller
public class InventoryController {
	private static Logger logger = Logger.getLogger(InventoryController.class);
	
	@Autowired(required=true)
	private AdminService adminService;
	
	@Autowired(required=true)
	private InventoryService inventoryService;

	@RequestMapping(value="/inventory/home.html", method=RequestMethod.GET)
	public String adminHomePanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside inventory home:::::::::::::::::");
    	return "inventory/home";
	}   
	
	@RequestMapping(value="/inventory/stockList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside inventory stock List:::::::::::::::::");
	   	return "inventory/stockList";
	}   	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/inventory/currentStockList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
	   	List<StockSummary> stockList = inventoryService.getCurrentStockList(StockSearchVo.fetchFromRequest(request));
	   	map.put("stock", stockList);
		return map;
	}
    
	@RequestMapping(value="/inventory/damageStockList.html", method=RequestMethod.GET)
	public String damageStockPanel(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside inventory damage stock List:::::::::::::::::");
	   	model.addAttribute("products", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"Product"));
	   	model.addAttribute("damageTypes", DamageType.getAllDamageTypes());
	   	return "inventory/damageStockList";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/inventory/currentDamageList.html", method=RequestMethod.GET)
	public @ResponseBody Map damageList(HttpServletRequest request, Map map) {    	
	   	List<DamageSummary> damageList = inventoryService.getDamageStockList(StockSearchVo.fetchFromRequest(request));
	   	map.put("damage", damageList);
		return map;
	}
    
}
