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
import com.codeyard.sfas.entity.Inventory;
import com.codeyard.sfas.entity.Role;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.vo.SearchVo;


@Controller
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/userList.html", method=RequestMethod.GET)
	public String userPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin user home:::::::::::::::::");
    	model.addAttribute("roles", Role.getAllRoles());
    	model.addAttribute("inventories", adminService.getEnityList(new SearchVo(), "Inventory"));
	    return "admin/userList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeUserList.html", method=RequestMethod.GET)
	public @ResponseBody Map userList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> userList = adminService.getEnityList(SearchVo.fetchFromRequest(request), "User");
    	map.put("user", userList);
		return map;
    }
    
    @RequestMapping(value="/admin/user.html", method=RequestMethod.GET)
    public ModelAndView addEditUser(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit user form:::::::::::::::::");
    	User user=null;
    	if(request.getParameter("id") != null)
    		user = (User)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"User");    		
    	else
    		user = new User();
    	
    	Map<String,String> roles = new LinkedHashMap<String,String>();
    	for(Role role : Role.getAllRoles())
    		roles.put(role.getValue(), role.getLabel());
    	
    	model.addAttribute("roles", roles);
    	
    	Map<Long,String> inventories = new LinkedHashMap<Long,String>();
    	for(AbstractBaseEntity entity : adminService.getEnityList(new SearchVo(), "Inventory")){
    		Inventory inventory = (Inventory)entity;
    		inventories.put(inventory.getId(), inventory.getName());
    	}
    	model.addAttribute("inventories", inventories);
	    
    	return new ModelAndView("admin/user", "command", user);
	}    

    @RequestMapping(value="/admin/saveUser.html", method=RequestMethod.POST)	
    public String saveUpdateUser(@ModelAttribute("user") User user, BindingResult result) {
    	logger.debug(":::::::::: inside admin save or edit user:::::::::::::::::");
    	
    	try{
    		adminService.saveOrUpdate(user);
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating user :: "+ex);
    	}
    	
	    return "redirect:/admin/userList.html";
	}    
    
    @RequestMapping(value="/admin/userDelete.html", method=RequestMethod.GET)
    public String deleteUser(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete user form:::::::::::::::::");
    	
    	if(request.getParameter("id") != null)
    		adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"User");    		
    	    	
	    return "redirect:/admin/userList.html";
	}
    
}
