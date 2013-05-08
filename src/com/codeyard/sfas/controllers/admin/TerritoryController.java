package com.codeyard.sfas.controllers.admin; 

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
import com.codeyard.sfas.entity.ASM;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.Area;
import com.codeyard.sfas.entity.RSM;
import com.codeyard.sfas.entity.Territory;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;


@Controller
public class TerritoryController {
	private static Logger logger = Logger.getLogger(TerritoryController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/territoryList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin territory home:::::::::::::::::");
    	model.addAttribute("regions", adminService.getAllLookUpEntity("Region"));
    	model.addAttribute("areas", adminService.getAllLookUpEntity("Area"));
	    return "admin/territoryList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeTerritoryList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractLookUpEntity> territoryList = adminService.getLookUpEnityList(AdminSearchVo.fetchFromRequest(request),"Territory");
    	map.put("territory", territoryList);
		return map;
    }
    
    @RequestMapping(value="/admin/territory.html", method=RequestMethod.GET)
    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit territory form:::::::::::::::::");
    	Territory territory=null;
    	if(request.getParameter("id") != null)
    		territory = (Territory)adminService.loadLookUpEntityById(Long.parseLong(request.getParameter("id")),"Territory");    		
    	else
    		territory = new Territory();
    	
    	Map<Long,String> regions = new LinkedHashMap<Long,String>();
    	for(AbstractLookUpEntity entity : adminService.getAllLookUpEntity("Region")){
    		regions.put(entity.getId(), entity.getName());
    	}
    	model.addAttribute("regions", regions);
    	
    	Map<Long,String> areas = new LinkedHashMap<Long,String>();
    	AdminSearchVo searchVo = new AdminSearchVo();
    	if(territory.getArea().getRegion().getId() == null || territory.getArea().getRegion().getId() == 0)
    		searchVo.setRegionId(-1);
    	else
    		searchVo.setRegionId(territory.getArea().getRegion().getId());
    	for(AbstractLookUpEntity entity : adminService.getLookUpEnityList(searchVo,"Area")){    	
    		Area area = (Area)entity;
    		areas.put(area.getId(), area.getName());    	
    	}
    	model.addAttribute("areas", areas);    	
    	    	
	    return new ModelAndView("admin/territory", "command", territory);
	}    

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/areaListByRegionId.html", method=RequestMethod.GET)
	public @ResponseBody Map populateAreaResponse(HttpServletRequest request, Map map) {
		Long regionId = Long.valueOf(request.getParameter("region_id"));
		AdminSearchVo searchVo = new AdminSearchVo();
		searchVo.setRegionId(regionId);
		map.put("results", adminService.getLookUpEnityList(searchVo, "Area"));
		return map; 	
	}
	
    
    @RequestMapping(value="/admin/saveTerritory.html", method=RequestMethod.POST)	
    public String saveUpdateEntity(@ModelAttribute("territory") Territory territory, BindingResult result, HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit territory:::::::::::::::::");
    	
    	try{    		
    		adminService.saveOrUpdateLookUpEntity(territory);
    		Utils.setSuccessMessage(request, "Territory successfully saved/updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating territory :: "+ex);
    		Utils.setErrorMessage(request, "Territory can't be saved/updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/territoryList.html";
	}    
    
    @RequestMapping(value="/admin/territoryDelete.html", method=RequestMethod.GET)
    public String deleteEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete territory form:::::::::::::::::");
    	
    	try{ 	    
    	    if(request.getParameter("id") != null){
    	    	adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"Territory");  
    	    	Utils.setSuccessMessage(request, "Territory successfully deleted.");
    	    }
    	}catch(Exception ex){
    		logger.debug("Error while delete territory :: "+ex);
    		Utils.setErrorMessage(request, "Territory already in use. Please remove associated entries first.");
    	}       
	    return "redirect:/admin/territoryList.html";
	}        
    
}
