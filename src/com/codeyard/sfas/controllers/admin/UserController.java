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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codeyard.sfas.entity.Role;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.admin.UserVo;


@Controller
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/userList.html", method=RequestMethod.GET)
	public String userPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin user home:::::::::::::::::");
    	model.addAttribute("roles", Role.getAllRoles());
	    return "admin/userList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeUserList.html", method=RequestMethod.GET)
	public @ResponseBody Map userList(HttpServletRequest request, Map map) {    	
    	List<User> userList = adminService.getAllUserList(fetchUserVoFromRequest(request));
    	if(userList == null)
    		userList = new ArrayList<User>();    	
    	logger.debug("loading user list from db...."+userList.size());
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
    
    private UserVo fetchUserVoFromRequest(HttpServletRequest request){
    	UserVo userVo = new UserVo();
    	
    	if(request.getParameter("fn") != null)
    		userVo.setFirstName((String)request.getParameter("fn"));
    	if(request.getParameter("ln") != null)
    		userVo.setLastName((String)request.getParameter("ln"));
    	if(request.getParameter("un") != null)
    		userVo.setUserName((String)request.getParameter("un"));
    	if(request.getParameter("mn") != null)
    		userVo.setMobileNumber((String)request.getParameter("mn"));
    	if(request.getParameter("rl") != null)
    		userVo.setRole((String)request.getParameter("rl"));
    	if(request.getParameter("ia") != null)
    		userVo.setIsActive((String)request.getParameter("ia"));
    	
    	return userVo;
    }
    
    
}
