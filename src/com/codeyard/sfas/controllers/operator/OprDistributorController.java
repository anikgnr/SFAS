package com.codeyard.sfas.controllers.operator; 

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
import com.codeyard.sfas.entity.DamageType;
import com.codeyard.sfas.entity.Distributor;
import com.codeyard.sfas.entity.DistributorDamageSummary;
import com.codeyard.sfas.entity.DistributorProductPlan;
import com.codeyard.sfas.entity.DistributorProductPlanLi;
import com.codeyard.sfas.entity.DistributorSellSummary;
import com.codeyard.sfas.entity.DistributorStockSummary;
import com.codeyard.sfas.entity.Product;
import com.codeyard.sfas.entity.ProductRegionRate;
import com.codeyard.sfas.entity.Region;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.OprDistributorService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Controller
public class OprDistributorController {
	private static Logger logger = Logger.getLogger(OprDistributorController.class);
	
	@Autowired(required=true)
	private AdminService adminService;
	
	@Autowired(required=true)
	private OprDistributorService oprDistributorService;
	

	@RequestMapping(value="/operator/distributorList.html", method=RequestMethod.GET)
	public String entityPanel(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside operator Distributor home:::::::::::::::::");
	   	model.addAttribute("rsms", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"RSM"));
	   	model.addAttribute("asms", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"ASM"));
    	model.addAttribute("tsos", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"TSO"));
    	model.addAttribute("depos", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"Depo"));
    	
	   	return "operator/distributorList";
	}

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/operator/activeDistributorList.html", method=RequestMethod.GET)
	public @ResponseBody Map entityList(HttpServletRequest request, Map map) {    	
    	List<AbstractBaseEntity> distributorList = adminService.getActiveEnityList(AdminSearchVo.fetchFromRequest(request),"Distributor");
    	map.put("distributor", distributorList);
		return map;
    }
	
    @RequestMapping(value="/operator/distributorStockList.html", method=RequestMethod.GET)
	public String stockPanel(HttpServletRequest request,Model model) {    	
	   	logger.debug(":::::::::: inside operator distributor stock List:::::::::::::::::");
	   	if(request.getParameter("id") != null){
	   		Long distributorId = Long.parseLong((String)request.getParameter("id"));	   		
	   		Distributor distributor = (Distributor)adminService.loadEntityById(distributorId, "Distributor");
	   		if(distributor != null){
	   			model.addAttribute("distributorId", distributor.getId());
	   			model.addAttribute("distributorName", distributor.getFullName());
	   		}
	   	}
	   	return "operator/distributorStockList";
	}   	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/operator/distributorCurrentStockList.html", method=RequestMethod.GET)
	public @ResponseBody Map stockList(HttpServletRequest request, Map map) {    	
	   	List<DistributorStockSummary> stockList = oprDistributorService.getDistributorCurrentStockList(StockSearchVo.fetchFromRequest(request));
	   	map.put("stock", stockList);
		return map;
	}
    
	@RequestMapping(value="/operator/distributorDamageStockList.html", method=RequestMethod.GET)
	public String damageStockPanel(HttpServletRequest request,Model model) {
	   	logger.debug(":::::::::: inside operator distributor damage stock List:::::::::::::::::");
	   	if(request.getParameter("id") != null){
	   		Long distributorId = Long.parseLong((String)request.getParameter("id"));	   		
	   		Distributor distributor = (Distributor)adminService.loadEntityById(distributorId, "Distributor");
	   		if(distributor != null){
	   			model.addAttribute("distributorId", distributor.getId());
	   			model.addAttribute("distributorName", distributor.getFullName());
	   			model.addAttribute("products", adminService.getEnityList(AdminSearchVo.fetchFromRequest(request),"Product"));
	   		   	model.addAttribute("damageTypes", DamageType.getAllDamageTypes());
	   		}
	   	}	   		   	
	   	return "operator/distributorDamageStockList";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/operator/distributorCurrentDamageList.html", method=RequestMethod.GET)
	public @ResponseBody Map damageList(HttpServletRequest request, Map map) {    	
	   	List<DistributorDamageSummary> damageList = oprDistributorService.getDistributorDamageStockList(StockSearchVo.fetchFromRequest(request));
	   	map.put("damage", damageList);
		return map;
	}

	@RequestMapping(value="/operator/distributorSaleList.html", method=RequestMethod.GET)
	public String sellPanel(HttpServletRequest request,Model model) {    	
	   	logger.debug(":::::::::: inside operator distributor sell List:::::::::::::::::");
	   	if(request.getParameter("id") != null){
	   		Long distributorId = Long.parseLong((String)request.getParameter("id"));	   		
	   		Distributor distributor = (Distributor)adminService.loadEntityById(distributorId, "Distributor");
	   		if(distributor != null){
	   			model.addAttribute("distributorId", distributor.getId());
	   			model.addAttribute("distributorName", distributor.getFullName());
	   		}
	   	}
	   	return "operator/distributorSellList";
	}   	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/operator/distributorCompleteSellList.html", method=RequestMethod.GET)
	public @ResponseBody Map sellList(HttpServletRequest request, Map map) {    	
	   	List<DistributorSellSummary> stockList = oprDistributorService.getDistributorSellSummaryList(StockSearchVo.fetchFromRequest(request));
	   	map.put("stock", stockList);
		return map;
	}
    
	@RequestMapping(value="/operator/distributorPlanSheet.html", method=RequestMethod.GET)
	public ModelAndView addEditMonthlyPlan(HttpServletRequest request,Model model) {
	    	logger.debug(":::::::::: inside operator add/edit distributor plan sheet form:::::::::::::::::");
	    	
    		Distributor distributor = (Distributor)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"Distributor");
	    	if(distributor == null){
	    		Utils.setErrorMessage(request, "No Valid Distributor ID Given.");
	    		return new ModelAndView("redirect:/operator/distributorList.html");
	    	}    		
	    	
	    	DistributorProductPlan plan = oprDistributorService.getDistributorPlanByIdMonthYear(distributor.getId(), Utils.thisMonth(), Utils.thisYear());
	    	
	    	if(plan == null){
	    		plan = new DistributorProductPlan();
	    		plan.setDistributor(distributor);
	    		plan.setPlanMonth(Utils.thisMonth());
	    		plan.setPlanYear(Utils.thisYear());
	    	}
	    	
	    	List<DistributorProductPlanLi> planLiList = new ArrayList<DistributorProductPlanLi>();
	    	List<DistributorProductPlanLi> currentPlanLiList = oprDistributorService.getDistributorPlanLiListByPlanId(plan.getId());
	    	List<AbstractBaseEntity> products = adminService.getEnityList(new AdminSearchVo(),"Product");
	    	
	    	for(AbstractBaseEntity entity: products){
	    		Product product = (Product)entity;
	    		planLiList.add(getDistributorProductPlanLiFromList(currentPlanLiList, product, plan));
	    	}
	    	
	    	plan.setPlanLiList(planLiList);	    	
	    	return new ModelAndView("operator/distributorPlanSheet", "command", plan);
	}

	private DistributorProductPlanLi getDistributorProductPlanLiFromList(List<DistributorProductPlanLi> currentPlanLiList, Product product, DistributorProductPlan plan){
		
		for(DistributorProductPlanLi planLi : currentPlanLiList){
			if(planLi.getProduct().getId() == product.getId())
				return planLi;
		}
		
		DistributorProductPlanLi planLi = new DistributorProductPlanLi();
		planLi.setPlan(plan);
		planLi.setProduct(product);
		planLi.setQuantity(0);
		planLi.setUsed(0);		
		
		return planLi;
	}
	
	@RequestMapping(value="/operator/saveDistributorPlan.html", method=RequestMethod.POST)	
	public String saveUpdateProductRegionalRate(@ModelAttribute("plan") DistributorProductPlan plan, BindingResult result, HttpServletRequest request) {
	   	logger.debug(":::::::::: inside operator save or edit distributor product plan rate:::::::::::::::::");
	    	
	   	try{    		
	   		oprDistributorService.saveOrUpdateDistributorProductPlan(plan);
	   		Utils.setSuccessMessage(request, "Product Plan successfully updated.");
	   	}catch(Exception ex){
	   		logger.debug("Error while saving/updating product plan :: "+ex);
	   		Utils.setErrorMessage(request, "Product Plan can't be updated. Please contact with System Admin.");
	   	}
	    	
	    return "redirect:/operator/distributorList.html";
	}    
	    	
}
