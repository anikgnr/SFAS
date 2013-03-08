package com.codeyard.sfas.controllers.admin; 

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
import com.codeyard.sfas.entity.Product;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.vo.SearchVo;


@Controller
public class ProductController {
	private static Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired(required=true)
	private AdminService adminService;

		   
    @RequestMapping(value="/admin/productList.html", method=RequestMethod.GET)
	public String productPanel(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin product home:::::::::::::::::");    	
	    return "admin/productList";
	}    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/completeProductList.html", method=RequestMethod.GET)
	public @ResponseBody Map productList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> productList = adminService.getEnityList(SearchVo.fetchFromRequest(request), "Product");
    	map.put("product", productList);
		return map;
    }
    
    @RequestMapping(value="/admin/product.html", method=RequestMethod.GET)
    public ModelAndView addEditProduct(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit product form:::::::::::::::::");
    	Product product=null;
    	if(request.getParameter("id") != null)
    		product = (Product)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"Product");    		
    	else
    		product = new Product();
    	
    	return new ModelAndView("admin/product", "command", product);
	}    

    @RequestMapping(value="/admin/saveProduct.html", method=RequestMethod.POST)	
    public String saveUpdateProduct(@ModelAttribute("product") Product product, BindingResult result) {
    	logger.debug(":::::::::: inside admin save or edit product:::::::::::::::::");
    	
    	try{
    		adminService.saveOrUpdate(product);
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating product :: "+ex);
    	}
    	
	    return "redirect:/admin/productList.html";
	}    
    
    @RequestMapping(value="/admin/productDelete.html", method=RequestMethod.GET)
    public String deleteProduct(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete product form:::::::::::::::::");
    	
    	if(request.getParameter("id") != null)
    		adminService.deleteEntityById(Long.parseLong(request.getParameter("id")),"Product");    		
    	    	
	    return "redirect:/admin/productList.html";
	}
    
}
