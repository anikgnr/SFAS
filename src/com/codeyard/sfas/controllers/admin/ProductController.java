package com.codeyard.sfas.controllers.admin; 

import java.util.ArrayList;
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
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.Product;
import com.codeyard.sfas.entity.ProductRegionRate;
import com.codeyard.sfas.entity.Region;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;


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
    	List<AbstractBaseEntity> productList = adminService.getEnityList(AdminSearchVo.fetchFromRequest(request), "Product");
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
    public String saveUpdateProduct(@ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit product:::::::::::::::::");
    	
    	try{
    		adminService.saveOrUpdate(product);
    		Utils.setSuccessMessage(request, "Product successfully saved/updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating product :: "+ex);
    		Utils.setErrorMessage(request, "Product can't be saved/updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/productList.html";
	}    
    
    @RequestMapping(value="/admin/productDelete.html", method=RequestMethod.GET)
    public String deleteProduct(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin delete product form:::::::::::::::::");
    	
    	try{ 	    
    	    if(request.getParameter("id") != null){
    	    	adminService.deleteProductById(Long.parseLong(request.getParameter("id")));  
    	    	Utils.setSuccessMessage(request, "Product successfully deleted.");
    	    }
    	}catch(Exception ex){
    		logger.debug("Error while delete Product :: "+ex);
    		Utils.setErrorMessage(request, "Product already in use. Please remove associated entries first.");
    	}      	    	
	    return "redirect:/admin/productList.html";
	}
    
    @RequestMapping(value="/admin/setupRegionalRates.html", method=RequestMethod.GET)
    public ModelAndView addEditProductRate(HttpServletRequest request,Model model) {
    	logger.debug(":::::::::: inside admin add/edit setupRegionalRates form:::::::::::::::::");
    	Product product=null;
    	if(request.getParameter("id") != null)
    		product = (Product)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"Product");    		
    	
    	if(product == null){
    		Utils.setErrorMessage(request, "No Valid Product found.");
    		return new ModelAndView("redirect:/admin/productList.html");
    	}    		
    	
    	List<ProductRegionRate> rateList = new ArrayList<ProductRegionRate>();
    	List<AbstractLookUpEntity> regions = adminService.getAllLookUpEntity("Region");
    	for(AbstractLookUpEntity entity : regions){
    		Region region = (Region)entity;
    		ProductRegionRate regionalRate = adminService.getRegionalProductRate(product.getId(), region.getId());
    		if(regionalRate == null){
    			regionalRate = new ProductRegionRate();
    			regionalRate.setProduct(product);
    			regionalRate.setRegion(region);    			
    		}
    		rateList.add(regionalRate);
    	}
    	product.setRegionalRateList(rateList);
    	
    	return new ModelAndView("admin/setupRegionalRates", "command", product);
	}    
    
    @RequestMapping(value="/admin/saveRegionalProductRate.html", method=RequestMethod.POST)	
    public String saveUpdateProductRegionalRate(@ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request) {
    	logger.debug(":::::::::: inside admin save or edit product regional rate:::::::::::::::::");
    	
    	try{    		
    		adminService.saveOrUpdateProductRegionalRates(product.getRegionalRateList());
    		Utils.setSuccessMessage(request, "Product Rates successfully updated.");
    	}catch(Exception ex){
    		logger.debug("Error while saving/updating product Rates :: "+ex);
    		Utils.setErrorMessage(request, "Product Rates can't be updated. Please contact with System Admin.");
    	}
    	
	    return "redirect:/admin/productList.html";
	}    
    
}
