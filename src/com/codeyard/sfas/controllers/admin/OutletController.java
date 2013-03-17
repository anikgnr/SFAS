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
import com.codeyard.sfas.entity.Distributor;
import com.codeyard.sfas.entity.Outlet;
import com.codeyard.sfas.entity.RSM;
import com.codeyard.sfas.entity.TSO;
import com.codeyard.sfas.entity.Territory;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;


@Controller
public class OutletController {
	private static Logger logger = Logger.getLogger(OutletController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/outletList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin outlet home:::::::::::::::::");
    	model.addAttribute("rsms", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"RSM"));
    	model.addAttribute("asms", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"ASM"));
    	model.addAttribute("tsos", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"TSO"));
      	model.addAttribute("distributors", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"Distributor"));
      	return "admin/outletList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeOutletList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> outletList = adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"Outlet");
    	map.put("outlet", outletList);
		return map;
    }
    
    @RequestMapping(value="/admin/outlet.html", method=RequestMethod.GET)
    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit outlet form:::::::::::::::::");
    	Outlet outlet=null;
    	if(request.getParameter("id") != null)
    		outlet = (Outlet)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"Outlet");    		
    	else
    		outlet = new Outlet();
    	
    	Map<Long,String> regions = new LinkedHashMap<Long,String>();
    	for(AbstractLookUpEntity region : adminService.getAllLookUpEntity("Region"))
    		regions.put(region.getId(), region.getName());
    	model.addAttribute("regions", regions);    	

    	Map<Long,String> areas = new LinkedHashMap<Long,String>();
    	for(AbstractLookUpEntity entity : adminService.getLookUpEntityList("Area","region.id",outlet.getDistributor().getTso().getAsm().getRsm().getRegion().getId())){
    		areas.put(entity.getId(), entity.getName());
    	}
    	model.addAttribute("areas", areas);    	

    	Map<Long,String> territories = new LinkedHashMap<Long,String>();
    	for(AbstractLookUpEntity entity : adminService.getLookUpEntityList("Territory","area.id",outlet.getDistributor().getTso().getAsm().getArea().getId())){
    		territories.put(entity.getId(), entity.getName());
    	}
    	model.addAttribute("territories", territories);    	
    	
    	Map<Long,String> routes = new LinkedHashMap<Long,String>();
    	for(AbstractLookUpEntity entity : adminService.getLookUpEntityList("Route","territory.id",outlet.getDistributor().getTso().getTerritory().getId())){
    		routes.put(entity.getId(), entity.getName());
    	}
    	model.addAttribute("routes", routes);   

    	
    	Map<Long,String> distributors = new LinkedHashMap<Long,String>();
    	AdminSearchVo searchVo = new AdminSearchVo();
    	if(outlet.getDistributor().getTso().getTerritory().getId() == null || outlet.getDistributor().getTso().getTerritory().getId() == 0)
    		searchVo.setTsoTerritoryId(-1);
    	else
    		searchVo.setTsoTerritoryId(outlet.getDistributor().getTso().getTerritory().getId());
    	for(AbstractBaseEntity entity : adminService.getEnityList(searchVo,"Distributor")){
    		Distributor distributor = (Distributor)entity;
    		distributors.put(distributor.getId(), distributor.getPointName());    	
    	}
    	model.addAttribute("distributors", distributors);    	

    	return new ModelAndView("admin/outlet", "command", outlet);
	}    
/*
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/areaListByRegion.html", method=RequestMethod.GET)
	public @ResponseBody Map populateAreaResponse(HttpServletRequest request, Map map) {
		Long regionId = Long.valueOf(request.getParameter("region_id"));	
		map.put("results", adminService.getLookUpEntityList("Area","region.id",regionId));
		return map; 	
	}
*/
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/territoryListByArea.html", method=RequestMethod.GET)
	public @ResponseBody Map populateTerritoryResponse(HttpServletRequest request, Map map) {
		Long areaId = Long.valueOf(request.getParameter("area_id"));	
		map.put("results", adminService.getLookUpEntityList("Territory","area.id",areaId));
		return map; 	
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/distributorListByTerritory.html", method=RequestMethod.GET)
	public @ResponseBody Map populateASMResponse(HttpServletRequest request, Map map) {
		Long territoryId = Long.valueOf(request.getParameter("territory_id"));
		AdminSearchVo searchVo = new AdminSearchVo();
		searchVo.setTsoTerritoryId(territoryId);
		map.put("results", adminService.getEnityList(searchVo,"Distributor"));
		return map; 	
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/routeListByTerritory.html", method=RequestMethod.GET)
	public @ResponseBody Map populateRouteResponse(HttpServletRequest request, Map map) {
		Long territoryId = Long.valueOf(request.getParameter("territory_id"));	
		map.put("results", adminService.getLookUpEntityList("Route","territory.id",territoryId));
		return map; 	
	}
    
    @RequestMapping(value="/admin/saveOutlet.html", method=RequestMethod.POST)	
    public String saveUpdateEntity(@ModelAttribute("outlet") Outlet outlet, BindingResult result, HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit outlet:::::::::::::::::");
    	
    	try{    		
    		//outlet.setDistributor((Distributor)adminService.loadEntityById(outlet.getDistributor().getId(),"Distributor"));    		
    		adminService.saveOrUpdate(outlet);
    		Utils.setSuccessMessage(request, "Outlet successfully saved/updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating outlet :: "+ex);
    		Utils.setErrorMessage(request, "Outlet can't be saved/updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/outletList.html";
	}    
    
    @RequestMapping(value="/admin/outletDelete.html", method=RequestMethod.GET)
    public String deleteEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete outlet form:::::::::::::::::");
    	
    	try{ 	    
    	    if(request.getParameter("id") != null){
    	    	adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"Outlet");  
    	    	Utils.setSuccessMessage(request, "Outlet successfully deleted.");
    	    }
    	}catch(Exception ex){
    		logger.debug("Error while delete Outlet :: "+ex);
    		Utils.setErrorMessage(request, "Outlet already in use. Please remove associated entries first.");
    	}      	
	    return "redirect:/admin/outletList.html";
	}
        
}
