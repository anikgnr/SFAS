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
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;


@Controller
public class AreaController {
	private static Logger logger = Logger.getLogger(AreaController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/areaList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin area home:::::::::::::::::");
    	model.addAttribute("regions", adminService.getAllLookUpEntity("Region"));
	    return "admin/areaList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeAreaList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractLookUpEntity> areaList = adminService.getLookUpEnityList(AdminSearchVo.fetchFromRequest(request),"Area");
    	map.put("area", areaList);
		return map;
    }
    
    @RequestMapping(value="/admin/area.html", method=RequestMethod.GET)
    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit area form:::::::::::::::::");
    	Area area=null;
    	if(request.getParameter("id") != null)
    		area = (Area)adminService.loadLookUpEntityById(Long.parseLong(request.getParameter("id")),"Area");    		
    	else
    		area = new Area();
    	
    	Map<Long,String> regions = new LinkedHashMap<Long,String>();
    	for(AbstractLookUpEntity entity : adminService.getAllLookUpEntity("Region")){
    		regions.put(entity.getId(), entity.getName());
    	}
    	model.addAttribute("regions", regions);    	
	    return new ModelAndView("admin/area", "command", area);
	}    

    
    @RequestMapping(value="/admin/saveArea.html", method=RequestMethod.POST)	
    public String saveUpdateEntity(@ModelAttribute("area") Area area, BindingResult result, HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit area:::::::::::::::::");
    	
    	try{    		
    		adminService.saveOrUpdateLookUpEntity(area);
    		Utils.setSuccessMessage(request, "Area successfully saved/updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating area :: "+ex);
    		Utils.setErrorMessage(request, "Area can't be saved/updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/areaList.html";
	}    
    
    @RequestMapping(value="/admin/areaDelete.html", method=RequestMethod.GET)
    public String deleteEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete area form:::::::::::::::::");
    	
    	try{ 	    
    	    if(request.getParameter("id") != null){
    	    	adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"Area");  
    	    	Utils.setSuccessMessage(request, "Area successfully deleted.");
    	    }
    	}catch(Exception ex){
    		logger.debug("Error while delete area :: "+ex);
    		Utils.setErrorMessage(request, "Area already in use. Please remove associated entries first.");
    	}       
	    return "redirect:/admin/areaList.html";
	}        
    
}
