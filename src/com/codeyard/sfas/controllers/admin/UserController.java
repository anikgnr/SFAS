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

import com.codeyard.sfas.entity.CompanyFactory;
import com.codeyard.sfas.entity.DepoOrder;
import com.codeyard.sfas.entity.ManagerType;
import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.Role;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Constants;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;


@Controller
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

	@RequestMapping(value="/admin/home.html", method=RequestMethod.GET)
	public String adminHomePanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin home:::::::::::::::::");
    	return "admin/home";
	}    
    	   
    @RequestMapping(value="/admin/userList.html", method=RequestMethod.GET)
	public String userPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin user home:::::::::::::::::");
    	model.addAttribute("roles", Role.getAllRoles());
    	return "admin/userList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeUserList.html", method=RequestMethod.GET)
	public @ResponseBody Map userList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> userList = adminService.getEnityList(AdminSearchVo.fetchFromRequest(request), "User");
    	map.put("user", userList);
		return map;
    }
    
    @RequestMapping(value="/admin/user.html", method=RequestMethod.GET)
    public ModelAndView addEditUser(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit user form:::::::::::::::::");
    	User user=null;
    	if(request.getParameter("er") != null){
    		user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
    	}else{
	    	if(request.getParameter("id") != null)
	    		user = (User)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"User");    		
	    	else{
	    		user = new User();
	    		user.setId(0L);
	    	}
    	}
    	Map<String,String> roles = new LinkedHashMap<String,String>();
    	for(Role role : Role.getAllRoles())
    		roles.put(role.getValue(), role.getLabel());
    	
    	model.addAttribute("roles", roles);
    	
    	Map<String,String> types = new LinkedHashMap<String,String>();
    	for(ManagerType type : ManagerType.getAllTypes())
    		types.put(type.getValue(), type.getLabel());
    	
    	model.addAttribute("types", types);
    	
    	Map<Long,String> factories = new LinkedHashMap<Long,String>();
    	List<AbstractBaseEntity> factoryList = adminService.getEnityList(AdminSearchVo.fetchFromRequest(request), "CompanyFactory");
    	for(AbstractBaseEntity entity: factoryList){
    		CompanyFactory factory = (CompanyFactory)entity;
    		factories.put(factory.getId(), factory.getName());
    	}
    	model.addAttribute("factories", factories);
    	
    	return new ModelAndView("admin/user", "command", user);
	}    

    @RequestMapping(value="/admin/saveUser.html", method=RequestMethod.POST)	
    public String saveUpdateUser(@ModelAttribute("user") User user, BindingResult result,HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit user:::::::::::::::::");
    	
    	try{
    		if(adminService.hasUserByUserName(user.getUserName(), user.getId())){
    			user.setSameUserName(true);
    			request.getSession().setAttribute(Constants.SESSION_USER, user);
    			return "redirect:/admin/user.html?er=1";
    		}else{
    			request.getSession().removeAttribute(Constants.SESSION_USER);
    		}
    		
    		adminService.saveOrUpdateUser(user);
    		Utils.setSuccessMessage(request, "User successfully saved/updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating user :: "+ex);
    		Utils.setErrorMessage(request, "User can't be saved/updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/userList.html";
	}    
    
    @RequestMapping(value="/admin/userDelete.html", method=RequestMethod.GET)
    public String deleteUser(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete user form:::::::::::::::::");
    	
    	try{
	    	if(request.getParameter("id") != null){
	    		adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"User");    		
	    		Utils.setSuccessMessage(request, "User successfully deleted.");
	    	}
    	}catch(Exception ex){
    		logger.debug("Error while delete user :: "+ex);
    		Utils.setErrorMessage(request, "User can't be deleted. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/userList.html";
	}
    
}
