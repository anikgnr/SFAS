package com.codeyard.sfas.util;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.DepoStockSummary;
import com.codeyard.sfas.entity.Distributor;
import com.codeyard.sfas.entity.DistributorDamageSummary;
import com.codeyard.sfas.entity.DistributorDeposit;
import com.codeyard.sfas.entity.DistributorOrder;
import com.codeyard.sfas.entity.DistributorOrderLi;
import com.codeyard.sfas.entity.DistributorSellSummary;
import com.codeyard.sfas.entity.DistributorStockSummary;
import com.codeyard.sfas.entity.Product;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.InventoryService;
import com.codeyard.sfas.service.OperatorService;
import com.codeyard.sfas.service.OprDistributorService;
import com.codeyard.sfas.vo.AdminSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;

public class DistributorOrderHelper {
	private static Logger logger = Logger.getLogger(DistributorOrderHelper.class);
	
	public static ModelAndView loadDistributorOrderForm(HttpServletRequest request,Model model, AdminService adminService, OprDistributorService operatorService, String moduleName){
		
		DistributorOrder order=null;
	   	if(request.getParameter("er") != null && request.getSession().getAttribute(Constants.SESSION_DISTRIBUTOR_ORDER) != null){
	   		
	   		order = (DistributorOrder)request.getSession().getAttribute(Constants.SESSION_DISTRIBUTOR_ORDER);
	   		
	   	}else if(request.getParameter("id") != null){
	   		
	   		order = (DistributorOrder)adminService.loadEntityById(Long.parseLong(request.getParameter("id")),"DistributorOrder");	   		
	   		order.setOrderLiList(operatorService.getDistributorOrderLiList(order.getId()));
	   		if(order.getOrderLiList() != null && order.getOrderLiList().size() > 0){
	   			for(DistributorOrderLi orderLi : order.getOrderLiList())
	   				orderLi.setPreviousQty(orderLi.getQuantity());	   			
	   		}
	   		
	   		if(request.getParameter("rd") != null){
	   			model.addAttribute("readOnly", true);
	   			if(request.getParameter("ap") != null && request.getParameter("tp") != null){
	   				model.addAttribute("approveType", (String)request.getParameter("tp"));
	   			}
	   		}else if(order.isMisApproved()){
	   			Utils.setErrorMessage(request, "Order already in Approval process.Can't edit/delete this anymore.");
	   			return new ModelAndView("redirect:/"+moduleName+"/distributorOrderList.html?id="+order.getDistributor().getId());
	   		}
	   	}else{
	   			   		
	   		order = new DistributorOrder();
	   		if(request.getParameter("did") != null){
	   			Distributor distributor = (Distributor)adminService.loadEntityById(Long.parseLong(request.getParameter("did")),"Distributor");
	   			if(distributor != null){
	   				if(operatorService.hasUnDeliveredOrderForDistributor(distributor.getId())){
	   					Utils.setErrorMessage(request, "This Distributor already have a pending Order. Please deliver the existing Order and try again later.");
	   		   			return new ModelAndView("redirect:/"+moduleName+"/distributorOrderList.html?id="+distributor.getId());
	   				}
	   				
	   				order.setDistributor(distributor);
	   				order.setDepo(distributor.getDepo());
	   				DistributorDeposit deposit = operatorService.getLatestDistributorDeposit(distributor.getId());
	   				if(deposit != null)
	   					order.setLastDeposit(deposit);
	   				order.setDistributorBalance(distributor.getCurrentBalance());
	   				StockSearchVo searchVo = new StockSearchVo();
	   				searchVo.setDistributorId(distributor.getId());
	   				List<DistributorStockSummary> stockList = operatorService.getDistributorCurrentStockList(searchVo);
	   				List<DistributorDamageSummary> damageList = operatorService.getDistributorDamageStockList(searchVo);
	   				List<DistributorSellSummary> saleList = operatorService.getDistributorSellSummaryList(searchVo);
	   				
	   				List<AbstractBaseEntity> products = adminService.getActiveEnityList(AdminSearchVo.fetchFromRequest(request),"Product");
	   				DistributorOrderLi orderLi = null;
	   				int serial = 1;
	   				for(AbstractBaseEntity entity : products){
	   					Product product = (Product)entity;
	   					orderLi = new DistributorOrderLi();
	   					
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
	   	
	    return new ModelAndView(moduleName+"/distributorOrder", "command", order);
	}
	
	public static void inventoryStockComparison(DistributorOrder order, InventoryService inventoryService){
		order.setErrorMsg("");
		List<StockSummary> stocks = inventoryService.getCurrentStockList(new StockSearchVo());
		boolean hasError = false;
		String error = "* Highlighted Product quantities are not available on Company Inventory :<br/>";
		for(DistributorOrderLi orderLi : order.getOrderLiList()){
			long stockQty = DistributorOrderHelper.getQuantityExceededWithInventory(stocks, orderLi);
			if(stockQty > -1){
				orderLi.setHasError(true);
				error += "&nbsp;&nbsp;&nbsp;&nbsp;- Serial: '"+orderLi.getSerial()+"'&nbsp;&nbsp;Product: '"+orderLi.getProduct().getFullName()+"'&nbsp;&nbsp;Order Qty: '"+orderLi.getQuantity()+"'&nbsp;&nbsp;Inventory Qty: '"+stockQty+"'<br/>";
				hasError = true;
			}
		}
		if(hasError){
			order.setErrorMsg(error);
		}
	}
	
	public static void depoStockComparison(DistributorOrder order, OperatorService operatorService){
		order.setErrorMsg("");
		StockSearchVo searchVo = new StockSearchVo();
		searchVo.setDepoId(order.getDepo().getId());
		List<DepoStockSummary> stocks = operatorService.getDepoCurrentStockList(searchVo);
		boolean hasError = false;
		String error = "* Highlighted Product quantities are not available on "+order.getDepo().getName()+" Inventory :<br/>";
		for(DistributorOrderLi orderLi : order.getOrderLiList()){
			long stockQty = DistributorOrderHelper.getQuantityExceededWithDepo(stocks, orderLi);
			if(stockQty > -1){
				orderLi.setHasError(true);
				error += "&nbsp;&nbsp;&nbsp;&nbsp;- Serial: '"+orderLi.getSerial()+"'&nbsp;&nbsp;Product: '"+orderLi.getProduct().getFullName()+"'&nbsp;&nbsp;Order Qty: '"+orderLi.getQuantity()+"'&nbsp;&nbsp;Inventory Qty: '"+stockQty+"'<br/>";
				hasError = true;
			}
		}
		if(hasError){
			order.setErrorMsg(error);
		}
	}
	
	public static void oderBalanceCurrentBalanceComparison(DistributorOrder order){
		if(order.getOrderAmount() > order.getDistributor().getCurrentBalance()){
			order.setErrorMsg(order.getErrorMsg()+"* Payable Amount exceeds Current Balance. Please refactor your Order.<br/>");
			order.setHasError(true);
		}			
	}
		
	private static long getQuantityExceededWithInventory(List<StockSummary> stocks, DistributorOrderLi orderLi){
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
	 
	private static long getQuantityExceededWithDepo(List<DepoStockSummary> stocks, DistributorOrderLi orderLi){
		 for(DepoStockSummary stock : stocks){
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
	 
	 private static Long getCurrentStock(List<DistributorStockSummary> stockList, Product product){
		 for(DistributorStockSummary summary : stockList){
			 if(summary.getProduct().getId() == product.getId()){
				 return summary.getQuantity();
			 }
		 }
		 return 0L;
	 }
	 private static  Long getCurrentDamage(List<DistributorDamageSummary> stockList, Product product){
		 for(DistributorDamageSummary summary : stockList){
			 if(summary.getProduct().getId() == product.getId()){
				 return summary.getQuantity();
			 }
		 }
		 return 0L;
	 }
	 private static Long getCurrentSale(List<DistributorSellSummary> stockList, Product product){
		 for(DistributorSellSummary summary : stockList){
			 if(summary.getProduct().getId() == product.getId()){
				 return summary.getQuantity();
			 }
		 }
		 return 0L;
	 }
	
}
