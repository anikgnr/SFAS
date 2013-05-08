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

import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.Region;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;


@Controller
public class RegionController {
	private static Logger logger = Logger.getLogger(RegionController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/regionList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin region home:::::::::::::::::");
	    return "admin/regionList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeRegionList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractLookUpEntity> regionList = adminService.getLookUpEnityList(AdminSearchVo.fetchFromRequest(request),"Region");
    	map.put("region", regionList);
		return map;
    }
    
    @RequestMapping(value="/admin/region.html", method=RequestMethod.GET)
    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit region form:::::::::::::::::");
    	Region region=null;
    	if(request.getParameter("id") != null)
    		region = (Region)adminService.loadLookUpEntityById(Long.parseLong(request.getParameter("id")),"Region");    		
    	else
    		region = new Region();
    	
    	return new ModelAndView("admin/region", "command", region);
	}    

    @RequestMapping(value="/admin/saveRegion.html", method=RequestMethod.POST)	
    public String saveUpdateEntity(@ModelAttribute("region") Region region, BindingResult result, HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit region:::::::::::::::::");
    	
    	try{    		
    		adminService.saveOrUpdateLookUpEntity(region);
    		Utils.setSuccessMessage(request, "Region successfully saved/updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating region :: "+ex);
    		Utils.setErrorMessage(request, "Region can't be saved/updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/regionList.html";
	}    
    
    @RequestMapping(value="/admin/regionDelete.html", method=RequestMethod.GET)
    public String deleteEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete region form:::::::::::::::::");
    	try{ 	    
	    	if(request.getParameter("id") != null){
	    		adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"Region"); 
	    		Utils.setSuccessMessage(request, "Region successfully deleted.");
	    	}
	    }catch(Exception ex){
    		logger.debug("Error while delete region :: "+ex);
    		Utils.setErrorMessage(request, "Region already in use. Please remove associated entries first.");
    	}    	
	    return "redirect:/admin/regionList.html";
	}        
    
}
