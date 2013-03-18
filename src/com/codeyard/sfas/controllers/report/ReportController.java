package com.codeyard.sfas.controllers.report; 

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.codeyard.sfas.service.AdminService;


@Controller
public class ReportController {
	private static Logger logger = Logger.getLogger(ReportController.class);
	
	@Autowired(required=true)
	private AdminService adminService;
	

	@RequestMapping(value="/report/home.html", method=RequestMethod.GET)
	public String reportHomePanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside report home:::::::::::::::::");
    	return "report/home";
	}   
	
}
