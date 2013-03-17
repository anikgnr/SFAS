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
import com.codeyard.sfas.entity.TSO;
import com.codeyard.sfas.entity.Territory;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;


@Controller
public class TSOController {
	private static Logger logger = Logger.getLogger(TSOController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/tsoList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin tso home:::::::::::::::::");
    	model.addAttribute("rsms", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"RSM"));
    	model.addAttribute("asms", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"ASM"));
    	model.addAttribute("territories", adminService.getAllLookUpEntity("Territory"));
	    return "admin/tsoList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeTSOList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> tsoList = adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"TSO");
    	map.put("tso", tsoList);
		return map;
    }
    
    @RequestMapping(value="/admin/tso.html", method=RequestMethod.GET)
    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit tso form:::::::::::::::::");
    	TSO tso=null;
    	if(request.getParameter("id") != null)
    		tso = (TSO)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"TSO");    		
    	else
    		tso = new TSO();
    	
    	Map<Long,String> rsms = new LinkedHashMap<Long,String>();
    	for(AbstractBaseEntity entity : adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"RSM")){
    		RSM rsm = (RSM)entity;
    		rsms.put(rsm.getId(), rsm.getFirstName()+" "+rsm.getLastName()+"-"+rsm.getRegion().getName());    	
    	}
    	model.addAttribute("rsms", rsms);    	

    	Map<Long,String> asms = new LinkedHashMap<Long,String>();
    	AdminSearchVo searchVo = new AdminSearchVo();
    	if(tso.getAsm().getRsm().getId() == null || tso.getAsm().getRsm().getId() == 0)
    		searchVo.setRsmId(-1);
    	else
    		searchVo.setRsmId(tso.getAsm().getRsm().getId());
    	for(AbstractBaseEntity entity : adminService.getEnityList(searchVo,"ASM")){
    		ASM asm = (ASM)entity;
    		asms.put(asm.getId(), asm.getFirstName()+" "+asm.getLastName()+"-"+asm.getArea().getName());    	
    	}
    	model.addAttribute("asms", asms);    	

    	Map<Long,String> territories = new LinkedHashMap<Long,String>();
    	for(AbstractLookUpEntity entity : adminService.getLookUpEntityList("Territory","area.id",tso.getAsm().getArea().getId())){
    		territories.put(entity.getId(), entity.getName());
    	}
    	model.addAttribute("territories", territories);    	
	    return new ModelAndView("admin/tso", "command", tso);
	}    

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/asmListByRSM.html", method=RequestMethod.GET)
	public @ResponseBody Map populateASMResponse(HttpServletRequest request, Map map) {
		Long rsmId = Long.valueOf(request.getParameter("rsm_id"));
		AdminSearchVo searchVo = new AdminSearchVo();
		searchVo.setRsmId(rsmId);
		map.put("results", adminService.getEnityList(searchVo,"ASM"));
		return map; 	
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/territoryListByASM.html", method=RequestMethod.GET)
	public @ResponseBody Map populateAreaResponse(HttpServletRequest request, Map map) {
		Long asmId = Long.valueOf(request.getParameter("asm_id"));	
		ASM asm = (ASM)adminService.loadEntityById(asmId,"ASM");    		
		logger.debug("Area ID :: "+asm.getArea().getId());
		map.put("results", adminService.getLookUpEntityList("Territory","area.id",asm.getArea().getId()));
		return map; 	
	}
	
    @RequestMapping(value="/admin/saveTSO.html", method=RequestMethod.POST)	
    public String saveUpdateEntity(@ModelAttribute("tso") TSO tso, BindingResult result, HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit tso:::::::::::::::::");
    	
    	try{    		
    		//tso.setAsm((ASM)adminService.loadEntityById(tso.getAsm().getId(),"ASM"));
    		//tso.setTerritory((Territory)adminService.loadLookUpEntityById(tso.getTerritory().getId(), "Territory"));
    		adminService.saveOrUpdate(tso);
    		Utils.setSuccessMessage(request, "TSO successfully saved/updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating tso :: "+ex);
    		Utils.setErrorMessage(request, "TSO can't be saved/updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/tsoList.html";
	}    
    
    @RequestMapping(value="/admin/tsoDelete.html", method=RequestMethod.GET)
    public String deleteEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete tso form:::::::::::::::::");
    	
    	try{ 	    
    	    if(request.getParameter("id") != null){
    	    	adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"TSO");  
    	    	Utils.setSuccessMessage(request, "TSO successfully deleted.");
    	    }
    	}catch(Exception ex){
    		logger.debug("Error while delete tso :: "+ex);
    		Utils.setErrorMessage(request, "TSO already in use. Please remove associated entries first.");
    	}           	
	    return "redirect:/admin/tsoList.html";
	}        
}
