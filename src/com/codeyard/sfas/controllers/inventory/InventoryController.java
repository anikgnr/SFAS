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
import com.codeyard.sfas.entity.Role;
import com.codeyard.sfas.entity.StockIn;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.InventoryService;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Controller
public class InventoryController {
	private static Logger logger = Logger.getLogger(InventoryController.class);
	
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
    	   
}
