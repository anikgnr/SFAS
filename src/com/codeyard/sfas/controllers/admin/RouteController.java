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
import com.codeyard.sfas.entity.Route;
import com.codeyard.sfas.entity.Territory;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;


@Controller
public class RouteController {
	private static Logger logger = Logger.getLogger(RouteController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/routeList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin route home:::::::::::::::::");
    	model.addAttribute("regions", adminService.getAllLookUpEntity("Region"));
    	model.addAttribute("areas", adminService.getAllLookUpEntity("Area"));
    	model.addAttribute("territories", adminService.getAllLookUpEntity("Territory"));
	    return "admin/routeList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeRouteList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractLookUpEntity> routeList = adminService.getLookUpEnityList(AdminSearchVo.fetchFromRequest(request),"Route");
    	map.put("route", routeList);
		return map;
    }
    
    @RequestMapping(value="/admin/route.html", method=RequestMethod.GET)
    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit route form:::::::::::::::::");
    	Route route=null;
    	if(request.getParameter("id") != null)
    		route = (Route)adminService.loadLookUpEntityById(Long.parseLong(request.getParameter("id")),"Route");    		
    	else
    		route = new Route();
    	
    	Map<Long,String> regions = new LinkedHashMap<Long,String>();
    	for(AbstractLookUpEntity entity : adminService.getAllLookUpEntity("Region")){
    		regions.put(entity.getId(), entity.getName());
    	}
    	model.addAttribute("regions", regions);
    	
    	Map<Long,String> areas = new LinkedHashMap<Long,String>();
    	AdminSearchVo searchVo = new AdminSearchVo();
    	if(route.getTerritory().getArea().getRegion().getId() == null || route.getTerritory().getArea().getRegion().getId() == 0)
    		searchVo.setRegionId(-1);
    	else
    		searchVo.setRegionId(route.getTerritory().getArea().getRegion().getId());
    	for(AbstractLookUpEntity entity : adminService.getLookUpEnityList(searchVo,"Area")){    	
    		Area area = (Area)entity;
    		areas.put(area.getId(), area.getName());    	
    	}
    	model.addAttribute("areas", areas);
    	
    	Map<Long,String> territories = new LinkedHashMap<Long,String>();
    	searchVo = new AdminSearchVo();
    	if(route.getTerritory().getArea().getId() == null || route.getTerritory().getArea().getId() == 0)
    		searchVo.setAreaId(-1);
    	else
    		searchVo.setAreaId(route.getTerritory().getArea().getId());
    	for(AbstractLookUpEntity entity : adminService.getLookUpEnityList(searchVo,"Territory")){    	
    		Territory territory = (Territory)entity;
    		territories.put(territory.getId(), territory.getName());    	
    	}
    	model.addAttribute("territories", territories);
    	    	
	    return new ModelAndView("admin/route", "command", route);
	}    

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/territoriesListByAreaId.html", method=RequestMethod.GET)
	public @ResponseBody Map populateAreaResponse(HttpServletRequest request, Map map) {
		Long areaId = Long.valueOf(request.getParameter("area_id"));
		AdminSearchVo searchVo = new AdminSearchVo();
		searchVo.setAreaId(areaId);
		map.put("results", adminService.getLookUpEnityList(searchVo, "Territory"));
		return map; 	
	}
	
    
    @RequestMapping(value="/admin/saveRoute.html", method=RequestMethod.POST)	
    public String saveUpdateEntity(@ModelAttribute("route") Route route, BindingResult result, HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit route:::::::::::::::::");
    	
    	try{    		
    		adminService.saveOrUpdateLookUpEntity(route);
    		Utils.setSuccessMessage(request, "Route successfully saved/updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating route :: "+ex);
    		Utils.setErrorMessage(request, "Route can't be saved/updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/routeList.html";
	}    
    
    @RequestMapping(value="/admin/routeDelete.html", method=RequestMethod.GET)
    public String deleteEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete route form:::::::::::::::::");
    	
    	try{ 	    
    	    if(request.getParameter("id") != null){
    	    	adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"Route");  
    	    	Utils.setSuccessMessage(request, "Route successfully deleted.");
    	    }
    	}catch(Exception ex){
    		logger.debug("Error while delete route :: "+ex);
    		Utils.setErrorMessage(request, "Route already in use. Please remove associated entries first.");
    	}       
	    return "redirect:/admin/routeList.html";
	}        
    
}
