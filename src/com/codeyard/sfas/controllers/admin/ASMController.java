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
public class ASMController {
	private static Logger logger = Logger.getLogger(ASMController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/asmList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin asm home:::::::::::::::::");
    	model.addAttribute("rsms", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"RSM"));
    	model.addAttribute("areas", adminService.getAllLookUpEntity("Area"));
	    return "admin/asmList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeASMList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> asmList = adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"ASM");
    	map.put("asm", asmList);
		return map;
    }
    
    @RequestMapping(value="/admin/asm.html", method=RequestMethod.GET)
    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit asm form:::::::::::::::::");
    	ASM asm=null;
    	if(request.getParameter("id") != null)
    		asm = (ASM)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"ASM");    		
    	else
    		asm = new ASM();
    	
    	Map<Long,String> rsms = new LinkedHashMap<Long,String>();
    	for(AbstractBaseEntity entity : adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"RSM")){
    		RSM rsm = (RSM)entity;
    		rsms.put(rsm.getId(), rsm.getFirstName()+" "+rsm.getLastName()+"-("+rsm.getRegion().getName()+")");    	
    	}
    	model.addAttribute("rsms", rsms);    	

    	Map<Long,String> areas = new LinkedHashMap<Long,String>();
    	for(AbstractLookUpEntity entity : adminService.getLookUpEntityList("Area","region.id",asm.getRsm().getRegion().getId())){
    		areas.put(entity.getId(), entity.getName());
    	}
    	model.addAttribute("areas", areas);    	
	    return new ModelAndView("admin/asm", "command", asm);
	}    

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/areaListByRSM.html", method=RequestMethod.GET)
	public @ResponseBody Map populateAreaResponse(HttpServletRequest request, Map map) {
		Long rsmId = Long.valueOf(request.getParameter("rsm_id"));	
		RSM rsm = (RSM)adminService.loadEntityById(rsmId,"RSM");    		
		logger.debug("Region ID :: "+rsm.getRegion().getId());
		map.put("results", adminService.getLookUpEntityList("Area","region.id",rsm.getRegion().getId()));
		return map; 	
	}
	
    @RequestMapping(value="/admin/saveASM.html", method=RequestMethod.POST)	
    public String saveUpdateEntity(@ModelAttribute("asm") ASM asm, BindingResult result, HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit asm:::::::::::::::::");
    	
    	try{    		
    		//asm.setRsm((RSM)adminService.loadEntityById(asm.getRsm().getId(),"RSM"));
    		//asm.setArea((Area)adminService.loadLookUpEntityById(asm.getArea().getId(), "Area"));
    		adminService.saveOrUpdate(asm);
    		Utils.setSuccessMessage(request, "ASM successfully saved/updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating asm :: "+ex);
    		Utils.setErrorMessage(request, "ASM can't be saved/updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/asmList.html";
	}    
    
    @RequestMapping(value="/admin/asmDelete.html", method=RequestMethod.GET)
    public String deleteEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete asm form:::::::::::::::::");
    	
    	try{ 	    
    	    if(request.getParameter("id") != null){
    	    	adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"ASM");  
    	    	Utils.setSuccessMessage(request, "ASM successfully deleted.");
    	    }
    	}catch(Exception ex){
    		logger.debug("Error while delete asm :: "+ex);
    		Utils.setErrorMessage(request, "ASM already in use. Please remove associated entries first.");
    	}       
	    return "redirect:/admin/asmList.html";
	}        
    
}
