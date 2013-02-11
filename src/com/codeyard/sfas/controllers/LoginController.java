package com.codeyard.sfas.controllers; 

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import com.codeyard.sfas.Constants;
import com.codeyard.sfas.Utils;


@Controller
public class LoginController {
	private static Logger logger = Logger.getLogger(CtrController.class);
	
	@Autowired(required=true)
	private SecurityService securityService;

    @RequestMapping(value="/login.html", method=RequestMethod.GET)
	public String setUpForm(HttpServletRequest request,HttpServletResponse response, Model model) {
		//08/06/12 - hjl - Added action logic
		if (request.getParameter("action") != null) {
			request.getSession().setAttribute(Constants.ACTION_SESSION_KEY,request.getParameter("action"));
			if (request.getParameter("action").equals("eFileReject")) {
				String eFileStatus = (String) request.getParameter("status");
				if (!eFileStatus.equals("Complete")) {       // Only reset eFile with status of complete
					model.addAttribute("title", Utils.getMessageBundlePropertyValue("efrm.message.title.status.issue"));
					model.addAttribute("body", Utils.getMessageBundlePropertyValue("efrm.message.body.status.issue"));
					return "eFileReport";
				} else {
					//String eFileId = (String) request.getSession().getAttribute("_EFILE_ID_");
					request.getSession().setAttribute(Constants.EFILE_ID_SESSION_KEY,request.getParameter("efile_id"));
					// Will fall through to forward to login page
				}
			}
		}
		else {
			if(request.getParameter("ctr_id") != null)
				request.getSession().setAttribute(Constants.CTR_ID_SESSION_KEY,request.getParameter("ctr_id"));
			else{
				SavedRequest savedRequest = 
					new HttpSessionRequestCache().getRequest(request, response);
				if(savedRequest != null){
					String[] params = savedRequest.getParameterValues("ctr_id");
					if(params != null && params.length > 0 && !Utils.isNullOrEmpty(params[0])){
						logger.debug("saved ctr_id : "+params[0]);
						request.getSession().setAttribute(Constants.CTR_ID_SESSION_KEY,params[0]);
					}
				}
				else{
					request.getSession().setAttribute(Constants.CTR_ID_SESSION_KEY,null);
				}
			}
		}
    	model.addAttribute("pageTitle","Login View");
    	return "login";
    }
    
	@RequestMapping(value="/login-successful.html", method=RequestMethod.GET)
	public String forwardOnRole(HttpServletRequest request,Model model) {
		List<String> roles = Utils.getLoggedUserRoles();
		if (request.getSession().getAttribute(Constants.LAST_HTTP_REQUEST_SESSION_KEY) != null) {
			return "redirect:/"+((String)request.getSession().getAttribute(Constants.LAST_HTTP_REQUEST_SESSION_KEY));
		}
		if(roles.size() > 0){
			List<ResourceEntity> defaultResources = securityService.getDefaultResources(roles);
			if(defaultResources != null && defaultResources.size() > 0){
				if(defaultResources.size() == 1){
					return "redirect:/"+defaultResources.get(0).resourceURL;
				}
				else{
					model.addAttribute("defaultResources", defaultResources);
					return "redirect:/landing.html";
				}
			}
		}
		return "accessDenied";
    }

    @RequestMapping(value="/bad-login.html", method=RequestMethod.GET)
	public String loginFail(Model model) {
		model.addAttribute("error", Utils.getMessageBundlePropertyValue("login.error"));
	    return "login";
	 }

    @RequestMapping(value="/access-denied.html", method=RequestMethod.GET)
	public String accessDenied(HttpServletRequest request,Model model) {
    	String loginUrl = "./login.html";
    	if(request.getSession().getAttribute(Constants.CTR_ID_SESSION_KEY) != null){
    		model.addAttribute("url", loginUrl+"?ctr_id="+request.getSession().getAttribute(Constants.CTR_ID_SESSION_KEY));
    	}else{
    		model.addAttribute("url", loginUrl);
    	}
	    return "accessDenied";
	 }
    
    @RequestMapping(value="/logout-close.html", method=RequestMethod.GET)
	public String closeLoginWindow(HttpServletRequest request,Model model) {
    	request.getSession().invalidate();    	
    	model.addAttribute("pageTitle","Login View");
    	model.addAttribute("close","1");
    	return "login";
    }    
}
