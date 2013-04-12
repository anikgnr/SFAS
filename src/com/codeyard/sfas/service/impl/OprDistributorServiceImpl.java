package com.codeyard.sfas.service.impl;

import java.util.List; 

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeyard.sfas.dao.JdbcDao;
import com.codeyard.sfas.dao.OprDistributorDao;
import com.codeyard.sfas.entity.DepoSellSummary;
import com.codeyard.sfas.entity.DepoStockSummary;
import com.codeyard.sfas.entity.Distributor;
import com.codeyard.sfas.entity.DistributorDamageSummary;
import com.codeyard.sfas.entity.DistributorDeposit;
import com.codeyard.sfas.entity.DistributorOrder;
import com.codeyard.sfas.entity.DistributorOrderLi;
import com.codeyard.sfas.entity.DistributorSellSummary;
import com.codeyard.sfas.entity.DistributorStockSummary;
import com.codeyard.sfas.entity.ManagerType;
import com.codeyard.sfas.entity.OrderType;
import com.codeyard.sfas.entity.StockOut;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.InventoryService;
import com.codeyard.sfas.service.OperatorService;
import com.codeyard.sfas.service.OprDistributorService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.OprSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Service("oprDistributorService")
@Transactional(readOnly = true)
public class OprDistributorServiceImpl implements OprDistributorService {
	private static Logger logger = Logger.getLogger(OprDistributorServiceImpl.class);

	@Autowired(required=true)
	private OprDistributorDao oprDistributorDao;
	
	@Autowired(required=true)
	private AdminService adminService;

	@Autowired(required=true)
	private InventoryService inventoryService;
	
	@Autowired(required=true)
	private OperatorService operatorService;
	
	@Autowired(required=true)
	private JdbcDao jdbcDao;

	public List<DistributorStockSummary> getDistributorCurrentStockList(StockSearchVo searchVo){
		return oprDistributorDao.getDistributorCurrentStockList(searchVo);
	}
	
	public List<DistributorDamageSummary> getDistributorDamageStockList(StockSearchVo searchVo){
		return oprDistributorDao.getDistributorDamageStockList(searchVo);
	}
	
	public List<DistributorDeposit> getDistributorDepositList(OprSearchVo searchVo){
		return oprDistributorDao.getDistributorDepositList(searchVo);
	}
	
	@Transactional(readOnly = false)
	public boolean approveDistributorDeposit(Long depositId){
		
		DistributorDeposit deposit = (DistributorDeposit)adminService.loadEntityById(depositId,"DistributorDeposit");
		if(deposit != null){
			deposit.setAccountApproved(true);
	    	deposit.setAccountApprovedBy(Utils.getLoggedUser());
	    	deposit.setAccountApprovedDate(Utils.today());
	    	adminService.onlySaveOrUpdate(deposit);
	    	
	    	Double currentBalance = deposit.getDistributor().getCurrentBalance();
	    	if(currentBalance == null)
	    		currentBalance = 0.0;
	    	deposit.getDistributor().setCurrentBalance(currentBalance+deposit.getDepositAmount());
	    	adminService.onlySaveOrUpdate(deposit.getDistributor());
	    	
	    	return true;
		}
		return false;		
	}
	
	public List<DistributorSellSummary> getDistributorSellSummaryList(StockSearchVo searchVo){
		return oprDistributorDao.getDistributorSellSummaryList(searchVo);
	}
	
	public DistributorDeposit getLatestDistributorDeposit(Long distributorId){
		return oprDistributorDao.getLatestDistributorDeposit(distributorId);
	}
	
	@Transactional(readOnly = false)
	public void saveOrUpdateDistributorOrder(DistributorOrder order){
		
		List<DistributorOrderLi> orderLiList = jdbcDao.getLiteDistributorOrderLiList(order.getId());
		Long oldQty = 0L;
		Long newQty = 0L;
		
		if(order.getDepo().isCompanyInventory()){
			List<StockSummary> stocks = inventoryService.getCurrentStockList(new StockSearchVo());
			for(StockSummary stock : stocks){
				oldQty = 0L;
				newQty = 0L;
				
				for(DistributorOrderLi oldLi : orderLiList){
					if(stock.getProduct().getId() == oldLi.getProduct().getId()){
						oldQty = oldLi.getQuantity();
						break;
					}
				}
				
				for(DistributorOrderLi newLi : order.getOrderLiList()){
					if(stock.getProduct().getId() == newLi.getProduct().getId()){
						newQty = newLi.getQuantity();
						break;
					}
				}
				logger.debug(stock.getProduct().getFullName()+" inventory current stock :: "+stock.getQuantity());
				logger.debug(stock.getProduct().getFullName()+" old Order Qty :: "+oldQty);
				logger.debug(stock.getProduct().getFullName()+" new Order Qty :: "+newQty);
				stock.setQuantity(stock.getQuantity()+oldQty-newQty);
				adminService.saveOrUpdate(stock);
			}
		}else{
			StockSearchVo searchVo = new StockSearchVo();
			searchVo.setDepoId(order.getDepo().getId());
			List<DepoStockSummary> stocks = operatorService.getDepoCurrentStockList(searchVo);
			for(DepoStockSummary stock : stocks){
				oldQty = 0L;
				newQty = 0L;
				
				for(DistributorOrderLi oldLi : orderLiList){
					if(stock.getProduct().getId() == oldLi.getProduct().getId()){
						oldQty = oldLi.getQuantity();
						break;
					}
				}
				
				for(DistributorOrderLi newLi : order.getOrderLiList()){
					if(stock.getProduct().getId() == newLi.getProduct().getId()){
						newQty = newLi.getQuantity();
						break;
					}
				}
				logger.debug(stock.getProduct().getFullName()+" depo current stock :: "+stock.getQuantity());
				logger.debug(stock.getProduct().getFullName()+" old Order Qty :: "+oldQty);
				logger.debug(stock.getProduct().getFullName()+" new Order Qty :: "+newQty);
				stock.setQuantity(stock.getQuantity()+oldQty-newQty);
				adminService.saveOrUpdate(stock);
			}
		}
		
		oprDistributorDao.saveOrUpdateDistributorOrder(order);
	}
	
	public List<DistributorOrderLi> getDistributorOrderLiList(Long distributorOrderId){
		return oprDistributorDao.getDistributorOrderLiList(distributorOrderId);
	}
	
	public List<DistributorOrder> getDistributorOrderList(OprSearchVo searchVo){
		return oprDistributorDao.getDistributorOrderList(searchVo);
	}
	
	@Transactional(readOnly = false)
	public void deleteDistributorOrderById(Long orderId){
		
		DistributorOrder order = (DistributorOrder)adminService.loadEntityById(orderId,"DistributorOrder");
		List<DistributorOrderLi> orderLiList = getDistributorOrderLiList(order.getId());
		
		if(order.getDepo().isCompanyInventory()){			
			List<StockSummary> stocks = inventoryService.getCurrentStockList(new StockSearchVo());		
			for(StockSummary stock : stocks){
				if(orderLiList != null){
					for(DistributorOrderLi oldLi : orderLiList){
						if(stock.getProduct().getId() == oldLi.getProduct().getId()){						
							stock.setQuantity(stock.getQuantity()+oldLi.getQuantity());
							break;
						}
					}
				}
				adminService.saveOrUpdate(stock);			
			}
		}else{
			StockSearchVo searchVo = new StockSearchVo();
			searchVo.setDepoId(order.getDepo().getId());
			List<DepoStockSummary> stocks = operatorService.getDepoCurrentStockList(searchVo);
			for(DepoStockSummary stock : stocks){
				if(orderLiList != null){
					for(DistributorOrderLi oldLi : orderLiList){
						if(stock.getProduct().getId() == oldLi.getProduct().getId()){						
							stock.setQuantity(stock.getQuantity()+oldLi.getQuantity());
							break;
						}
					}
				}
				adminService.saveOrUpdate(stock);			
			}		
		}
		
		oprDistributorDao.deleteDistributorOrderById(orderId);
	}
	
	public boolean hasUnDeliveredOrderForDistributor(Long distributorId){
		return jdbcDao.hasUnDeliveredOrderForDistributor(distributorId);
	}
	
	@Transactional(readOnly = false)
	public void approveDistributorOrder(DistributorOrder order, String type){
		if(ManagerType.MIS.getValue().equals(type)){
			order.setMisApproved(true);
			order.setMisApprovedBy(Utils.getLoggedUser());
			order.setMisApprovedDate(Utils.today());
		}else if(ManagerType.ACCOUNT.getValue().equals(type)){
			order.setAccountApproved(true);
			order.setAccountApprovedBy(Utils.getLoggedUser());
			order.setAccountApprovedDate(Utils.today());
		}else if(ManagerType.Manager.getValue().equals(type)){
			order.setMgrApproved(true);
			order.setMgrApprovedBy(Utils.getLoggedUser());
			order.setMgrApprovedDate(Utils.today());
		}else if(ManagerType.MM.getValue().equals(type)){
			order.setMmApproved(true);
			order.setMmApprovedBy(Utils.getLoggedUser());
			order.setMmApprovedDate(Utils.today());
		}else if(ManagerType.MD.getValue().equals(type)){
			order.setMdApproved(true);
			order.setMdApprovedBy(Utils.getLoggedUser());
			order.setMdApprovedDate(Utils.today());
		}
		adminService.onlySaveOrUpdate(order);
	}
	
	@Transactional(readOnly = false)
	public void unApproveDistributorOrder(DistributorOrder order, String type){
		if(ManagerType.ACCOUNT.getValue().equals(type)){
			order.setMisApproved(false);			
		}else if(ManagerType.Manager.getValue().equals(type)){
			order.setAccountApproved(false);			
		}else if(ManagerType.MM.getValue().equals(type)){
			order.setMgrApproved(false);			
		}else if(ManagerType.MD.getValue().equals(type)){
			order.setMmApproved(false);			
		}
		adminService.onlySaveOrUpdate(order);
	}
	
	@Transactional(readOnly = false)
	public void deliverDistributorOrder(DistributorOrder order){
		order.setDelivered(true);
		adminService.saveOrUpdate(order);
		
		Distributor distributor = order.getDistributor();
		distributor.setCurrentBalance(distributor.getCurrentBalance() - order.getOrderAmount());
		adminService.onlySaveOrUpdate(distributor);
		
		List <DistributorOrderLi> orderLiList = getDistributorOrderLiList(order.getId());
		if(orderLiList != null){
			for(DistributorOrderLi orderLi : orderLiList){
				if(orderLi.getQuantity() <= 0)
					continue;
				
				DistributorStockSummary stockSummary = oprDistributorDao.getDistributorStockSummaryByProductId(orderLi.getProduct().getId(), order.getDistributor().getId());
				if(stockSummary == null){
					stockSummary = new DistributorStockSummary();
					stockSummary.setDistributor(distributor);
					stockSummary.setProduct(orderLi.getProduct());
				}
				stockSummary.setQuantity(stockSummary.getQuantity() + orderLi.getQuantity());
				stockSummary.setLastStockInDate(Utils.today());
				adminService.saveOrUpdate(stockSummary);
				
				if(order.getDepo().isCompanyInventory()){
					StockOut stockOut = new StockOut();
					stockOut.setProduct(orderLi.getProduct());
					stockOut.setQuantity(orderLi.getQuantity());
					stockOut.setOrderFrom(OrderType.DISTRIBUTOR.getValue());
					stockOut.setOrderId(order.getId());
					stockOut.setStockOutDate(Utils.today());
					adminService.saveOrUpdate(stockOut);
					
					StockSummary inventoryStockSummary = inventoryService.getStockSummaryByProductId(orderLi.getProduct().getId());
					if(inventoryStockSummary != null){
						inventoryStockSummary.setLastStockOutDate(Utils.today());
						adminService.saveOrUpdate(inventoryStockSummary);	
					}
				}else{

					DepoSellSummary depoSaleSummary = operatorService.getDepoSellSummaryByProductIdAndDepoId(orderLi.getProduct().getId(), order.getDepo().getId());
					if(depoSaleSummary == null){
						depoSaleSummary = new DepoSellSummary();
						depoSaleSummary.setProduct(orderLi.getProduct());
						depoSaleSummary.setDepo(order.getDepo());						
					}							
					depoSaleSummary.setLastSellDate(Utils.today());
					depoSaleSummary.setQuantity(depoSaleSummary.getQuantity() + orderLi.getQuantity());
					adminService.saveOrUpdate(depoSaleSummary);
					
					DepoStockSummary depoStockSummary = operatorService.getDepoCurrentStockSummaryByProductIdAndDepoId(orderLi.getProduct().getId(), order.getDepo().getId());
					if(depoStockSummary != null){
						depoStockSummary.setLastStockOutDate(Utils.today());
						adminService.saveOrUpdate(depoStockSummary);	
					}
				}				
			}
		}
		
	}
}