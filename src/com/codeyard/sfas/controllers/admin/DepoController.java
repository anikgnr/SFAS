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
import com.codeyard.sfas.entity.Depo;
import com.codeyard.sfas.entity.RSM;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;


@Controller
public class DepoController {
	private static Logger logger = Logger.getLogger(DepoController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/depoList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin depo home:::::::::::::::::");
    	model.addAttribute("rsms", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"RSM"));
    	return "admin/depoList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeDepoList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> depoList = adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"Depo");
    	for(AbstractBaseEntity entity : depoList){
    		Depo depo = (Depo)entity;
    		if(depo.isCompanyInventory()){
    			depo.setRsm(new RSM());
    			depo.setEditLink("");
    			depo.setDeleteLink("");
    			break;
    		}
    	}
    	map.put("depo", depoList);
		return map;
    }
    
    @RequestMapping(value="/admin/depo.html", method=RequestMethod.GET)
    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit depo form:::::::::::::::::");
    	Depo depo=null;
    	if(request.getParameter("id") != null)
    		depo = (Depo)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"Depo");    		
    	else
    		depo = new Depo();
    	
    	Map<Long,String> rsms = new LinkedHashMap<Long,String>();
    	for(AbstractBaseEntity entity : adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"RSM")){
    		RSM rsm = (RSM)entity;
    		rsms.put(rsm.getId(), rsm.getName()+"-("+rsm.getRegion().getName()+")");
    	}    		
    	model.addAttribute("rsms", rsms);    	

    	return new ModelAndView("admin/depo", "command", depo);
	}    
        
    @RequestMapping(value="/admin/saveDepo.html", method=RequestMethod.POST)	
    public String saveUpdateEntity(@ModelAttribute("depo") Depo depo, BindingResult result, HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit depo:::::::::::::::::");
    	
    	try{    		
    		//depo.setRsm((RSM)adminService.loadEntityById(depo.getRsm().getId(),"RSM"));    		
    		adminService.saveOrUpdate(depo);
    		Utils.setSuccessMessage(request, "Depo successfully saved/updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating depo :: "+ex);
    		Utils.setErrorMessage(request, "Depo can't be saved/updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/depoList.html";
	}    
    
    @RequestMapping(value="/admin/depoDelete.html", method=RequestMethod.GET)
    public String deleteEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete depo form:::::::::::::::::");
    	
      	try{ 	    
    	    if(request.getParameter("id") != null){
    	    	adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"Depo");  
    	    	Utils.setSuccessMessage(request, "Depo successfully deleted.");
    	    }
    	}catch(Exception ex){
    		logger.debug("Error while delete Depo :: "+ex);
    		Utils.setErrorMessage(request, "Depo already in use. Please remove associated entries first.");
    	}      	
    	
	    return "redirect:/admin/depoList.html";
	}
        
}
