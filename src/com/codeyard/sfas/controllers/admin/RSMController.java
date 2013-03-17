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
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.RSM;
import com.codeyard.sfas.entity.Region;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;


@Controller
public class RSMController {
	private static Logger logger = Logger.getLogger(RSMController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/rsmList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin rsm home:::::::::::::::::");
    	model.addAttribute("regions", adminService.getAllLookUpEntity("Region"));
	    return "admin/rsmList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeRSMList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> rsmList = adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"RSM");
    	map.put("rsm", rsmList);
		return map;
    }
    
    @RequestMapping(value="/admin/rsm.html", method=RequestMethod.GET)
    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit rsm form:::::::::::::::::");
    	RSM rsm=null;
    	if(request.getParameter("id") != null)
    		rsm = (RSM)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"RSM");    		
    	else
    		rsm = new RSM();
    	
    	Map<Long,String> regions = new LinkedHashMap<Long,String>();
    	for(AbstractLookUpEntity region : adminService.getAllLookUpEntity("Region"))
    		regions.put(region.getId(), region.getName());
    	
    	model.addAttribute("regions", regions);
	    return new ModelAndView("admin/rsm", "command", rsm);
	}    

    @RequestMapping(value="/admin/saveRSM.html", method=RequestMethod.POST)	
    public String saveUpdateEntity(@ModelAttribute("rsm") RSM rsm, BindingResult result, HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit rsm:::::::::::::::::");
    	
    	try{    		
    		//rsm.setRegion((Region)adminService.loadLookUpEntityById(rsm.getRegion().getId(), "Region"));
    		adminService.saveOrUpdate(rsm);
    		Utils.setSuccessMessage(request, "RSM successfully saved/updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating rsm :: "+ex);
    		Utils.setErrorMessage(request, "RSM can't be saved/updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/rsmList.html";
	}    
    
    @RequestMapping(value="/admin/rsmDelete.html", method=RequestMethod.GET)
    public String deleteEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete rsm form:::::::::::::::::");
    	try{ 	    
	    	if(request.getParameter("id") != null){
	    		adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"RSM"); 
	    		Utils.setSuccessMessage(request, "RSM successfully deleted.");
	    	}
	    }catch(Exception ex){
    		logger.debug("Error while delete rsm :: "+ex);
    		Utils.setErrorMessage(request, "RSM already in use. Please remove associated entries first.");
    	}    	
	    return "redirect:/admin/rsmList.html";
	}        
    
}
