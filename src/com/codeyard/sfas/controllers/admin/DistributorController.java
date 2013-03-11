package com.codeyard.sfas.controllers.admin; 

import java.util.ArrayList;
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
import com.codeyard.sfas.entity.Depo;
import com.codeyard.sfas.entity.Distributor;
import com.codeyard.sfas.entity.RSM;
import com.codeyard.sfas.entity.TSO;
import com.codeyard.sfas.entity.Territory;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Constants;
import com.codeyard.sfas.vo.SearchVo;


@Controller
public class DistributorController {
	private static Logger logger = Logger.getLogger(DistributorController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/distributorList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin distributor home:::::::::::::::::");
    	model.addAttribute("rsms", adminService.getEnityList(SearchVo.fetchFromRequest(request),"RSM"));
    	model.addAttribute("asms", adminService.getEnityList(SearchVo.fetchFromRequest(request),"ASM"));
    	model.addAttribute("tsos", adminService.getEnityList(SearchVo.fetchFromRequest(request),"TSO"));
    	model.addAttribute("depos", adminService.getEnityList(SearchVo.fetchFromRequest(request),"Depo"));
    	return "admin/distributorList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeDistributorList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> disList = adminService.getEnityList(SearchVo.fetchFromRequest(request),"Distributor");
    	map.put("distributor", disList);
		return map;
    }
    
    @RequestMapping(value="/admin/distributor.html", method=RequestMethod.GET)
    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit distributor form:::::::::::::::::");
    	Distributor distributor=null;
    	if(request.getParameter("id") != null)
    		distributor = (Distributor)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"Distributor");    		
    	else
    		distributor = new Distributor();
    	
    	Map<Long,String> regions = new LinkedHashMap<Long,String>();
    	for(AbstractLookUpEntity region : adminService.getAllLookUpEntity("Region"))
    		regions.put(region.getId(), region.getName());
    	model.addAttribute("regions", regions);    	

    	Map<Long,String> areas = new LinkedHashMap<Long,String>();
    	for(AbstractLookUpEntity entity : adminService.getLookUpEntityList("Area","region.id",distributor.getTso().getAsm().getRsm().getRegion().getId())){
    		areas.put(entity.getId(), entity.getName());
    	}
    	model.addAttribute("areas", areas);    	

    	Map<Long,String> tsos = new LinkedHashMap<Long,String>();
    	SearchVo searchVo = new SearchVo();
    	if(distributor.getTso().getAsm().getArea().getId() == null || distributor.getTso().getAsm().getArea().getId() == 0)
    		searchVo.setAsmAreaId(-1);
    	else
    		searchVo.setAsmAreaId(distributor.getTso().getAsm().getArea().getId());
    	for(AbstractBaseEntity entity : adminService.getEnityList(searchVo,"TSO")){
    		TSO tso = (TSO)entity;
    		tsos.put(tso.getId(), tso.getName()+"-("+tso.getTerritory().getName()+")");    	
    	}
    	model.addAttribute("tsos", tsos);    	

    	Map<Long,String> depos = new LinkedHashMap<Long,String>();
    	depos.put(Constants.companyInventoryId, Constants.companyInventoryName);
    	searchVo = new SearchVo();
    	if(distributor.getTso().getAsm().getRsm().getId() == null || distributor.getTso().getAsm().getRsm().getId() == 0)
    		searchVo.setRsmId(-1);
    	else
    		searchVo.setRsmId(distributor.getTso().getAsm().getRsm().getId());    	
    	for(AbstractBaseEntity entity : adminService.getEnityList(searchVo,"Depo")){
    		Depo depo = (Depo)entity;
    		depos.put(depo.getId(), depo.getName());    	
    	}
    	model.addAttribute("depos", depos);    	

    	return new ModelAndView("admin/distributor", "command", distributor);
	}    

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/areaListByRegion.html", method=RequestMethod.GET)
	public @ResponseBody Map populateAreaResponse(HttpServletRequest request, Map map) {
		Long regionId = Long.valueOf(request.getParameter("region_id"));	
		map.put("results", adminService.getLookUpEntityList("Area","region.id",regionId));
		return map; 	
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/tsoListByArea.html", method=RequestMethod.GET)
	public @ResponseBody Map populateASMResponse(HttpServletRequest request, Map map) {
		Long areaId = Long.valueOf(request.getParameter("area_id"));
		SearchVo searchVo = new SearchVo();
		searchVo.setAsmAreaId(areaId);
		map.put("results", adminService.getEnityList(searchVo,"TSO"));
		return map; 	
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/depoListByTso.html", method=RequestMethod.GET)
	public @ResponseBody Map populateDepoResponse(HttpServletRequest request, Map map) {
		Long tsoId = Long.valueOf(request.getParameter("tso_id"));
		TSO tso = (TSO)adminService.loadEntityById(tsoId,"TSO");
		SearchVo searchVo = new SearchVo();
		searchVo.setRsmId(tso.getAsm().getRsm().getId());
		List<AbstractBaseEntity> depos = new ArrayList<AbstractBaseEntity>();
		depos.add(adminService.loadEntityById(Constants.companyInventoryId,"Depo"));
		List<AbstractBaseEntity> depoList = adminService.getEnityList(searchVo,"Depo");
		if(depoList != null && depoList.size() > 0)
			depos.addAll(depoList);
		map.put("results", depos);
		return map; 	
	}
	
    
    @RequestMapping(value="/admin/saveDistributor.html", method=RequestMethod.POST)	
    public String saveUpdateEntity(@ModelAttribute("distributor") Distributor distributor, BindingResult result) {
    	logger.debug(":::::::::: inside admin save or edit distributor:::::::::::::::::");
    	
    	try{    		
    		distributor.setTso((TSO)adminService.loadEntityById(distributor.getTso().getId(),"TSO"));    		
    		adminService.saveOrUpdate(distributor);
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating distributor :: "+ex);
    	}
    	
	    return "redirect:/admin/distributorList.html";
	}    
    
    @RequestMapping(value="/admin/distributorDelete.html", method=RequestMethod.GET)
    public String deleteEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete distributor form:::::::::::::::::");
    	
    	if(request.getParameter("id") != null)
    		adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"Distributor");    		
    	    	
	    return "redirect:/admin/distributorList.html";
	}
        
}
