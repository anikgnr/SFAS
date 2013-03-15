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
import com.codeyard.sfas.entity.SR;
import com.codeyard.sfas.entity.TSO;
import com.codeyard.sfas.entity.Territory;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.vo.AdminSearchVo;


@Controller
public class SRController {
	private static Logger logger = Logger.getLogger(SRController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/srList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin sr home:::::::::::::::::");
    	model.addAttribute("rsms", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"RSM"));
    	model.addAttribute("asms", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"ASM"));
    	model.addAttribute("tsos", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"TSO"));    	
	    return "admin/srList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeSRList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> srList = adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"SR");
    	map.put("sr", srList);
		return map;
    }
    
    @RequestMapping(value="/admin/sr.html", method=RequestMethod.GET)
    public ModelAndView addEditEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit sr form:::::::::::::::::");
    	SR sr=null;
    	if(request.getParameter("id") != null)
    		sr = (SR)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"SR");    		
    	else
    		sr = new SR();
    	
    	Map<Long,String> rsms = new LinkedHashMap<Long,String>();
    	for(AbstractBaseEntity entity : adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"RSM")){
    		RSM rsm = (RSM)entity;
    		rsms.put(rsm.getId(), rsm.getFirstName()+" "+rsm.getLastName()+"-("+rsm.getRegion().getName()+")");    	
    	}
    	model.addAttribute("rsms", rsms);    	

    	Map<Long,String> asms = new LinkedHashMap<Long,String>();
    	AdminSearchVo searchVo = new AdminSearchVo();
    	if(sr.getTso().getAsm().getRsm().getId() == null || sr.getTso().getAsm().getRsm().getId() == 0)
    		searchVo.setRsmId(-1);
    	else
    		searchVo.setRsmId(sr.getTso().getAsm().getRsm().getId());
    	for(AbstractBaseEntity entity : adminService.getEnityList(searchVo,"ASM")){
    		ASM asm = (ASM)entity;
    		asms.put(asm.getId(), asm.getFirstName()+" "+asm.getLastName()+"-("+asm.getArea().getName()+")");    	
    	}
    	model.addAttribute("asms", asms);    	

    	Map<Long,String> tsos = new LinkedHashMap<Long,String>();
    	searchVo = new AdminSearchVo();
    	if(sr.getTso().getAsm().getId() == null || sr.getTso().getAsm().getId() == 0)
    		searchVo.setAsmId(-1);
    	else
    		searchVo.setAsmId(sr.getTso().getAsm().getId());
    	for(AbstractBaseEntity entity : adminService.getEnityList(searchVo,"TSO")){
    		TSO tso = (TSO)entity;
    		tsos.put(tso.getId(), tso.getFirstName()+" "+tso.getLastName()+"-("+tso.getTerritory().getName()+")");    	
    	}
    	model.addAttribute("tsos", tsos);    	

    	return new ModelAndView("admin/sr", "command", sr);
	}    
/*
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/asmListByRSM.html", method=RequestMethod.GET)
	public @ResponseBody Map populateASMResponse(HttpServletRequest request, Map map) {
		Long rsmId = Long.valueOf(request.getParameter("rsm_id"));
		SearchVo searchVo = new SearchVo();
		searchVo.setRsmId(rsmId);
		map.put("results", adminService.getEnityList(searchVo,"ASM"));
		return map; 	
	}
*/	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/admin/tsoListByASM.html", method=RequestMethod.GET)
	public @ResponseBody Map populateTSOResponse(HttpServletRequest request, Map map) {
		Long asmId = Long.valueOf(request.getParameter("asm_id"));
		AdminSearchVo searchVo = new AdminSearchVo();
		searchVo.setAsmId(asmId);
		map.put("results", adminService.getEnityList(searchVo,"TSO"));
		return map; 	
	}
	
    
    @RequestMapping(value="/admin/saveSR.html", method=RequestMethod.POST)	
    public String saveUpdateEntity(@ModelAttribute("sr") SR sr, BindingResult result) {
    	logger.debug(":::::::::: inside admin save or edit sr:::::::::::::::::");
    	
    	try{    		
    		sr.setTso((TSO)adminService.loadEntityById(sr.getTso().getId(),"TSO"));    		
    		adminService.saveOrUpdate(sr);
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating sr :: "+ex);
    	}
    	
	    return "redirect:/admin/srList.html";
	}    
    
    @RequestMapping(value="/admin/srDelete.html", method=RequestMethod.GET)
    public String deleteEntity(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete sr form:::::::::::::::::");
    	
    	if(request.getParameter("id") != null)
    		adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"SR");    		
    	    	
	    return "redirect:/admin/srList.html";
	}        
}
