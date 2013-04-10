package com.codeyard.sfas.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.Depo;
import com.codeyard.sfas.entity.DepoDamageSummary;
import com.codeyard.sfas.entity.DepoDeposit;
import com.codeyard.sfas.entity.DepoOrder;
import com.codeyard.sfas.entity.DepoOrderLi;
import com.codeyard.sfas.entity.DepoSellSummary;
import com.codeyard.sfas.entity.DepoStockSummary;
import com.codeyard.sfas.entity.DistributorOrder;
import com.codeyard.sfas.entity.DistributorOrderLi;
import com.codeyard.sfas.entity.Product;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.InventoryService;
import com.codeyard.sfas.service.OperatorService;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;

public class DepoOrderHelper {
	private static Logger logger = Logger.getLogger(DepoOrderHelper.class);
	
	public static ModelAndView loadDepoOrderForm(HttpServletRequest request,Model model, AdminService adminService, OperatorService operatorService, String moduleName){
		
		DepoOrder order=null;
	   	if(request.getParameter("er") != null && request.getSession().getAttribute(Constants.SESSION_DEPO_ORDER) != null){
	   		
	   		order = (DepoOrder)request.getSession().getAttribute(Constants.SESSION_DEPO_ORDER);
	   		
	   	}else if(request.getParameter("id") != null){
	   		
	   		order = (DepoOrder)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"DepoOrder");	   		
	   		order.setOrderLiList(operatorService.getDepoOrderLiList(order.getId()));
	   		if(order.getOrderLiList() != null && order.getOrderLiList().size() > 0){
	   			for(DepoOrderLi orderLi : order.getOrderLiList())
	   				orderLi.setPreviousQty(orderLi.getQuantity());	   			
	   		}
	   		
	   		if(request.getParameter("rd") != null){
	   			model.addAttribute("readOnly", true);
	   			if(request.getParameter("ap") != null && request.getParameter("tp") != null){
	   				model.addAttribute("approveType", (String)request.getParameter("tp"));
	   			}
	   		}else if(order.isMisApproved()){
	   			Utils.setErrorMessage(request, "Order already in Approval process.Can't edit/delete this anymore.");
	   			return new ModelAndView("redirect:/"+moduleName+"/depoOrderList.html?id="+order.getDepo().getId());
	   		}
	   	}else{
	   			   		
	   		order = new DepoOrder();
	   		if(request.getParameter("did") != null){
	   			Depo depo = (Depo)adminService.loadEntityById(Long.parseLong(request.getParameter("did")),"Depo");
	   			if(depo != null){
	   				if(operatorService.hasUnDeliveredOrderForDepo(depo.getId())){
	   					Utils.setErrorMessage(request, "This Depo already have a pending Order. Please deliver the existing Order and try again later.");
	   		   			return new ModelAndView("redirect:/"+moduleName+"/depoOrderList.html?id="+depo.getId());
	   				}
	   				
	   				order.setDepo(depo);
	   				DepoDeposit deposit = operatorService.getLatestDepoDeposit(depo.getId());
	   				if(deposit != null)
	   					order.setLastDeposit(deposit);
	   				order.setDepoBalance(depo.getCurrentBalance());
	   				StockSearchVo searchVo = new StockSearchVo();
	   				searchVo.setDepoId(depo.getId());
	   				List<DepoStockSummary> stockList = operatorService.getDepoCurrentStockList(searchVo);
	   				List<DepoDamageSummary> damageList = operatorService.getDepoDamageStockList(searchVo);
	   				List<DepoSellSummary> saleList = operatorService.getDepoSellSummaryList(searchVo);
	   				
	   				List<AbstractBaseEntity> products = adminService.getActiveEnityList(AdminSearchVo.fetchFromRequest(request),"Product");
	   				DepoOrderLi orderLi = null;
	   				int serial = 1;
	   				for(AbstractBaseEntity entity : products){
	   					Product product = (Product)entity;
	   					orderLi = new DepoOrderLi();
	   					
	   					orderLi.setSerial(serial++);
	   					orderLi.setProduct(product);
	   					orderLi.setCurrentStock(getCurrentStock(stockList, product));
	   					orderLi.setTotalSale(getCurrentSale(saleList, product));
	   					orderLi.setTotalDamage(getCurrentDamage(damageList, product));
	   					orderLi.setQuantity(0L);
	   					orderLi.setPreviousQty(0L);
	   					orderLi.setCurrentRate(product.getRate());
	   					orderLi.setCurrentProfitMargin(product.getProfitMargin());
	   					orderLi.setAmount(0.0);
	   					
	   					order.getOrderLiList().add(orderLi);
	   				}
	   			}
	   		}
	   	}	    
	   	
	    return new ModelAndView(moduleName+"/depoOrder", "command", order);
	}
	
	public static void inventoryStockComparison(DepoOrder order, InventoryService inventoryService){
		order.setErrorMsg("");
		List<StockSummary> stocks = inventoryService.getCurrentStockList(new StockSearchVo());
		boolean hasError = false;
		String error = "* Highlighted Product quantities are not available on Company Inventory :<br/>";
		for(DepoOrderLi orderLi : order.getOrderLiList()){
			long stockQty = DepoOrderHelper.getQuantityExceededWithInventory(stocks, orderLi);
			if(stockQty > -1){
				orderLi.setHasError(true);
				error += "&nbsp;&nbsp;&nbsp;&nbsp;- Serial: '"+orderLi.getSerial()+"'&nbsp;&nbsp;Product: '"+orderLi.getProduct().getFullName()+"'&nbsp;&nbsp;Order Qty: '"+orderLi.getQuantity()+"'&nbsp;&nbsp;Available Qty: '"+(stockQty+orderLi.getPreviousQty())+"'<br/>";
				hasError = true;
			}
		}
		if(hasError){
			order.setErrorMsg(error);
		}
	}
		
	public static void oderBalanceCurrentBalanceComparison(DepoOrder order){
		if(order.getOrderAmount() > order.getDepo().getCurrentBalance()){
			order.setErrorMsg(order.getErrorMsg()+"* Payable Amount exceeds Current Balance. Please refactor your Order.<br/>");
			order.setHasError(true);
		}			
	}
	
	private static long getQuantityExceededWithInventory(List<StockSummary> stocks, DepoOrderLi orderLi){
		 for(StockSummary stock : stocks){
			 if(stock.getProduct().getId() == orderLi.getProduct().getId()){
				 if(orderLi.getQuantity() > (stock.getQuantity()+orderLi.getPreviousQty()))
					 return stock.getQuantity();
				 else
					 return -1;
			 }
		 }
		 if(orderLi.getQuantity() > 0)
			 return 0;
		 else
			 return -1;
	 }

	 private static Long getCurrentStock(List<DepoStockSummary> stockList, Product product){
		 for(DepoStockSummary summary : stockList){
			 if(summary.getProduct().getId() == product.getId()){
				 return summary.getQuantity();
			 }
		 }
		 return 0L;
	 }
	 private static  Long getCurrentDamage(List<DepoDamageSummary> stockList, Product product){
		 for(DepoDamageSummary summary : stockList){
			 if(summary.getProduct().getId() == product.getId()){
				 return summary.getQuantity();
			 }
		 }
		 return 0L;
	 }
	 private static Long getCurrentSale(List<DepoSellSummary> stockList, Product product){
		 for(DepoSellSummary summary : stockList){
			 if(summary.getProduct().getId() == product.getId()){
				 return summary.getQuantity();
			 }
		 }
		 return 0L;
	 }
	
}
