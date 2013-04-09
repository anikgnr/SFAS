package com.codeyard.sfas.controllers.operator; 

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

import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.DamageSummary;
import com.codeyard.sfas.entity.DamageType;
import com.codeyard.sfas.entity.Depo;
import com.codeyard.sfas.entity.DepoDamageSummary;
import com.codeyard.sfas.entity.DepoSellSummary;
import com.codeyard.sfas.entity.DepoStockSummary;
import com.codeyard.sfas.entity.RSM;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.OperatorService;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Controller
public class OprDepoController {
	private static Logger logger = Logger.getLogger(OprDepoController.class);
	
	@Autowired(required=true)
	private AdminService adminService;
	
	@Autowired(required=true)
	private OperatorService operatorService;
	

	@RequestMapping(value="/operator/home.html", method=RequestMethod.GET)
	public String adminHomePanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside operator home:::::::::::::::::");
    	return "operator/home";
	}   
	
	@RequestMapping(value="/operator/depoList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside operator depo home:::::::::::::::::");
	   	model.addAttribute("rsms", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"RSM"));
	   	return "operator/depoList";
	}

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/operator/activeDepoList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> depoList = adminService.getActiveEnityList(AdminSearchVo.fetchFromRequest(request),"Depo");
    	Iterator<AbstractBaseEntity> itr = depoList.iterator();
    	while(itr.hasNext()){
    		Depo depo = (Depo)itr.next();
    		if(depo.isCompanyInventory()){
    			itr.remove();
    			continue;
    		}
    		
    	}
    	map.put("depo", depoList);
		return map;
    }
	
    @RequestMapping(value="/operator/depoStockList.html", method=RequestMethod.GET)
	public String stockPanel(HttpServletRequest request,Model model) {    	
	   	logger.debug(":::::::::: inside operator depo stock List:::::::::::::::::");
	   	if(request.getParameter("id") != null){
	   		Long depoId = Long.parseLong((String)request.getParameter("id"));	   		
	   		Depo depo = (Depo)adminService.loadEntityById(depoId, "Depo");
	   		if(depo != null){
	   			model.addAttribute("depoId", depo.getId());
	   			model.addAttribute("depoName", depo.getFullName());
	   		}
	   	}
	   	return "operator/depoStockList";
	}   	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/operator/depoCurrentStockList.html", method=RequestMethod.GET)
	public @ResponseBody Map stockList(HttpServletRequest request, Map map) {    	
	   	List<DepoStockSummary> stockList = operatorService.getDepoCurrentStockList(StockSearchVo.fetchFromRequest(request));
	   	map.put("stock", stockList);
		return map;
	}
    
	@RequestMapping(value="/operator/depoDamageStockList.html", method=RequestMethod.GET)
	public String damageStockPanel(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside operator depo damage stock List:::::::::::::::::");
	   	if(request.getParameter("id") != null){
	   		Long depoId = Long.parseLong((String)request.getParameter("id"));	   		
	   		Depo depo = (Depo)adminService.loadEntityById(depoId, "Depo");
	   		if(depo != null){
	   			model.addAttribute("depoId", depo.getId());
	   			model.addAttribute("depoName", depo.getFullName());
	   			model.addAttribute("products", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"Product"));
	   		   	model.addAttribute("damageTypes", DamageType.getAllDamageTypes());
	   		}
	   	}	   		   	
	   	return "operator/depoDamageStockList";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/operator/depoCurrentDamageList.html", method=RequestMethod.GET)
	public @ResponseBody Map damageList(HttpServletRequest request, Map map) {    	
	   	List<DepoDamageSummary> damageList = operatorService.getDepoDamageStockList(StockSearchVo.fetchFromRequest(request));
	   	map.put("damage", damageList);
		return map;
	}

	@RequestMapping(value="/operator/depoSaleList.html", method=RequestMethod.GET)
	public String sellPanel(HttpServletRequest request,Model model) {    	
	   	logger.debug(":::::::::: inside operator depo sell List:::::::::::::::::");
	   	if(request.getParameter("id") != null){
	   		Long depoId = Long.parseLong((String)request.getParameter("id"));	   		
	   		Depo depo = (Depo)adminService.loadEntityById(depoId, "Depo");
	   		if(depo != null){
	   			model.addAttribute("depoId", depo.getId());
	   			model.addAttribute("depoName", depo.getFullName());
	   		}
	   	}
	   	return "operator/depoSellList";
	}   	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/operator/depoCompleteSellList.html", method=RequestMethod.GET)
	public @ResponseBody Map sellList(HttpServletRequest request, Map map) {    	
	   	List<DepoSellSummary> stockList = operatorService.getDepoSellSummaryList(StockSearchVo.fetchFromRequest(request));
	   	map.put("stock", stockList);
		return map;
	}
    
	
}
