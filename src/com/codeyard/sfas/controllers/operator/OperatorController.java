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
import com.codeyard.sfas.entity.RSM;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Controller
public class OperatorController {
	private static Logger logger = Logger.getLogger(OperatorController.class);
	
	@Autowired(required=true)
	private AdminService adminService;
	

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
	
	
}
