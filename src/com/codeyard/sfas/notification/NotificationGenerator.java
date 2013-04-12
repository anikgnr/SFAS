package com.codeyard.sfas.notification;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.ContextLoader;

import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.DepoDeposit;
import com.codeyard.sfas.entity.DepoOrder;
import com.codeyard.sfas.entity.DepoOrderLi;
import com.codeyard.sfas.entity.DistributorDeposit;
import com.codeyard.sfas.entity.NotificationType;
import com.codeyard.sfas.entity.StockIn;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.util.Constants;
import com.codeyard.sfas.util.Utils;

public class NotificationGenerator {
	private static Logger logger = Logger.getLogger(NotificationGenerator.class);

	public static void sendRoleWiseNotification(String role, NotificationType type, AbstractBaseEntity entity){		
		execute(new MailSenderThread(getUsersByRole(role), getSubject(type), getContent(type, entity)));
	}
	
	public static void sendPostWiseNotification(String post, NotificationType type, AbstractBaseEntity entity){
		execute(new MailSenderThread(getUsersByPost(post), getSubject(type), getContent(type, entity)));		
	}
	
	public static void sendUserNameWiseNotification(String userName, NotificationType type, AbstractBaseEntity entity){
		execute(new MailSenderThread(getUserByUserName(userName), getSubject(type), getContent(type, entity)));		
	}
	
	private static void execute(Thread thread){
		ApplicationContext context = new ClassPathXmlApplicationContext("../SFAS-config.xml");
	    ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
		taskExecutor.execute(thread);		
	}
	
	private static List<User> getUsersByRole(String role){
		try{
			AdminService adminService = (AdminService)ContextLoader.getCurrentWebApplicationContext().getBean("adminService");
			if(adminService != null)
				return adminService.getUserListByRole(role);				
						
		}catch(Exception ex){
			logger.debug("getUsersByRole :: "+ex);
		}
		return null;
	}
	
	private static List<User> getUsersByPost(String post){
		try{
			AdminService adminService = (AdminService)ContextLoader.getCurrentWebApplicationContext().getBean("adminService");
			if(adminService != null)
				return adminService.getUserListByDept(post);				
						
		}catch(Exception ex){
			logger.debug("getUsersByPoste :: "+ex);
		}
		return null;
	}
	
	private static User getUserByUserName(String userName){
		try{
			AdminService adminService = (AdminService)ContextLoader.getCurrentWebApplicationContext().getBean("adminService");
			if(adminService != null)
				return adminService.getUserByUserName(userName);				
						
		}catch(Exception ex){
			logger.debug("getUserByUserName :: "+ex);
		}
		return null;
	}
	
	
	private static String getSubject(NotificationType type){
		
		String subject = "SFAS Notification :: "; 
		
		if(type == NotificationType.INVENTORY_STOCK_IN){
			subject += "Pending Company Inventory Stock In Entry for Approval";
		}else if(type == NotificationType.INVENTORY_STOCK_APPROVED){
			subject += "Company Inventory Stock In Entry Approved";
		}else if(type == NotificationType.INVENTORY_STOCK_DELETED){			
			subject += "Company Inventory Stock In Entry Deleted";
		}else if(type == NotificationType.DEPO_DEPOSIT_IN){
			subject += "Pending DEPO Deposit Entry for Approval";
		}else if(type == NotificationType.DEPO_DEPOSIT_APPROVED){
			subject += "DEPO Deposit Entry Approved";
		}else if(type == NotificationType.DEPO_DEPOSIT_DELETED){
			subject += "DEPO Deposit Entry Deleted";
		}else if(type == NotificationType.DISTRIBUTOR_DEPOSIT_IN){
			subject += "Pending Distributor Deposit Entry for Approval";
		}else if(type == NotificationType.DISTRIBUTOR_DEPOSIT_APPROVED){
			subject += "Distributor Deposit Entry Approved";
		}else if(type == NotificationType.DISTRIBUTOR_DEPOSIT_DELETED){
			subject += "Distributor Deposit Entry Deleted";
		}else if(type == NotificationType.DEPO_ORDER_IN){
			subject += "Pending DEPO Order for Approval";
		}else if(type == NotificationType.DEPO_ORDER_APPROVED){
			subject += "Pending DEPO Order for Delivery";
		}else if(type == NotificationType.DEPO_ORDER_UNAPPROVED){
			subject += "DEPO Order Un-Approved";
		}else if(type == NotificationType.DEPO_ORDER_DELIVERED){
			subject += "DEPO Order Delivered";
		}
		return subject;
	}
	
	private static String getContent(NotificationType type, AbstractBaseEntity entity){
		
		String content = "Dear ";
		
		if(type == NotificationType.INVENTORY_STOCK_IN){
			StockIn stockIn = (StockIn)entity;
			content += "Inventory Admin,<br/><br/>New Company Inventory Stock In Entry is waiting for your Approval. Here is the Stock In Entry Summary : <br/><br/>"+
					   "Product : <b>"+stockIn.getProduct().getFullName()+"</b><br/>"+
					   "Stock In Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, stockIn.getStockInDate())+"</b><br/>"+
					   "Stock In Quantity : <b>"+stockIn.getQuantity()+"</b><br/>"+
					   "Stock In By : <b>"+stockIn.getLastModifiedBy()+"</b><br/>"+
					   "<br/>Please take appropriate action asap (<a href='"+getAppUrl()+"/inventory/stockinList.html'>Click here</a>)"+
					   "<br/><br/>Regards<br/><span style='font-weight: bold;color: #0f3854;'>SFAS Notification Panel</span>";
			
		}else if(type == NotificationType.INVENTORY_STOCK_APPROVED){
			StockIn stockIn = (StockIn)entity;
			content += stockIn.getCreatedBy()+",<br/><br/>Your Company Inventory Stock In Entry has been <span style='font-weight: bold;color: green;'>Approved</span>. Here is the Stock In Approval Summary : <br/><br/>"+
					   "Product : <b>"+stockIn.getProduct().getFullName()+"</b><br/>"+
					   "Stock In Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, stockIn.getStockInDate())+"</b><br/>"+
					   "Stock In Quantity : <b>"+stockIn.getQuantity()+"</b><br/>"+
					   "Stock In By : <b>"+stockIn.getLastModifiedBy()+" (you)</b><br/>"+
					   "Approved By : <b>"+stockIn.getApprovedBy()+"</b><br/>"+
					   "Approved Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, stockIn.getApprovedDate())+"</b><br/>"+
					   "<br/>Company Inventory stock has been updated as well. Please <a href='"+getAppUrl()+"/inventory/stockList.html'>click here</a> "+
					   "to see the updated stock summary.<br/><br/>Regards<br/><span style='font-weight: bold;color: #0f3854;'>SFAS Notification Panel</span>";
			
		}else if(type == NotificationType.INVENTORY_STOCK_DELETED){
			StockIn stockIn = (StockIn)entity;
			content += stockIn.getCreatedBy()+",<br/><br/>Your Company Inventory Stock In Entry has been <span style='font-weight: bold;color: red;'>Deleted</span>. Here is the Stock In Deletion Summary : <br/><br/>"+
					   "Product : <b>"+stockIn.getProduct().getFullName()+"</b><br/>"+
					   "Stock In Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, stockIn.getStockInDate())+"</b><br/>"+
					   "Stock In Quantity : <b>"+stockIn.getQuantity()+"</b><br/>"+
					   "Stock In By : <b>"+stockIn.getLastModifiedBy()+" (you)</b><br/>"+
					   "Deleted By : <b>"+Utils.getLoggedUser()+"</b><br/>"+
					   "Deleted Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, Utils.today())+"</b><br/>"+
					   "<br/>Please <a href='"+getAppUrl()+"/inventory/stockin.html'>visit here</a> "+
					   "in-order to enter more Inventory Stock-In Entry.<br/><br/>Regards<br/><span style='font-weight: bold;color: #0f3854;'>SFAS Notification Panel</span>";
		
		}else if(type == NotificationType.DEPO_DEPOSIT_IN){
			DepoDeposit deposit = (DepoDeposit)entity;
			content += "System Accountant,<br/><br/>New <b>DEPOSIT</b> has been submitted for your Approval. Here is the Deposit Summary : <br/><br/>"+
					   "DEPO Name : <b>"+deposit.getDepo().getFullName()+"</b><br/>"+
					   "Bank Name : <b>"+deposit.getAccount().getBankName()+"</b><br/>"+
					   "Bank Account : <b>"+deposit.getAccount().getBankAccount()+"</b><br/>"+
					   "Deposit Amount : <b>"+deposit.getDepositAmount()+" Tk</b><br/>"+
					   "Deposit Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getDepositDate())+"</b><br/>"+
					   "Submitted By : <b>"+deposit.getLastModifiedBy()+"</b><br/>"+
					   "Submitted Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getLastModified())+"</b><br/>"+
					   "<br/>Please take appropriate action asap (<a href='"+getAppUrl()+"/manager/pendingDepoDepositList.html'>Click here</a>)"+
					   "<br/><br/>Regards<br/><span style='font-weight: bold;color: #0f3854;'>SFAS Notification Panel</span>";
		
		}else if(type == NotificationType.DEPO_DEPOSIT_DELETED){
			DepoDeposit deposit = (DepoDeposit)entity;
			content += deposit.getLastModifiedBy()+",<br/><br/>Your DEPO Deposit Entry has been <span style='font-weight: bold;color: red;'>Deleted</span>. Here is the Depo Deposit Entry Deletion Summary : <br/><br/>"+
					   "DEPO Name : <b>"+deposit.getDepo().getFullName()+"</b><br/>"+
					   "Bank Name : <b>"+deposit.getAccount().getBankName()+"</b><br/>"+
					   "Bank Account : <b>"+deposit.getAccount().getBankAccount()+"</b><br/>"+
					   "Deposit Amount : <b>"+deposit.getDepositAmount()+" Tk</b><br/>"+
					   "Deposit Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getDepositDate())+"</b><br/>"+
					   "Submitted By : <b>"+deposit.getLastModifiedBy()+"</b><br/>"+
					   "Submitted Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getLastModified())+"</b><br/>"+
					   "Deleted By : <b>"+Utils.getLoggedUser()+"</b><br/>"+
					   "Deleted Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, Utils.today())+"</b><br/>"+
					   "<br/>Please <a href='"+getAppUrl()+"/operator/depoDeposit.html?did="+deposit.getDepo().getId()+"'>visit here</a> "+
					   "in-order to enter more deposit for this DEPO.<br/><br/>Regards<br/><span style='font-weight: bold;color: #0f3854;'>SFAS Notification Panel</span>";
		
		}else if(type == NotificationType.DEPO_DEPOSIT_APPROVED){
			DepoDeposit deposit = (DepoDeposit)entity;
			content += deposit.getLastModifiedBy()+",<br/><br/>Your DEPO Deposit Entry has been <span style='font-weight: bold;color: green;'>Approved</span>. Here is the Depo Deposit Entry Approval Summary : <br/><br/>"+
					   "DEPO Name : <b>"+deposit.getDepo().getFullName()+"</b><br/>"+
					   "Bank Name : <b>"+deposit.getAccount().getBankName()+"</b><br/>"+
					   "Bank Account : <b>"+deposit.getAccount().getBankAccount()+"</b><br/>"+
					   "Deposit Amount : <b>"+deposit.getDepositAmount()+" Tk</b><br/>"+
					   "Deposit Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getDepositDate())+"</b><br/>"+
					   "Submitted By : <b>"+deposit.getLastModifiedBy()+"</b><br/>"+
					   "Submitted Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getLastModified())+"</b><br/>"+
					   "Approved By : <b>"+deposit.getAccountApprovedBy()+"</b><br/>"+
					   "Approved Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getAccountApprovedDate())+"</b><br/>"+
					   "<br/>DEPO's current balance has been updated as well. Please <a href='"+getAppUrl()+"/operator/depoList.html'>click here</a> "+
					   "to see the updated DEPO list.<br/><br/>Regards<br/><span style='font-weight: bold;color: #0f3854;'>SFAS Notification Panel</span>";
			
		}else if(type == NotificationType.DISTRIBUTOR_DEPOSIT_IN){
			DistributorDeposit deposit = (DistributorDeposit)entity;
			content += "System Accountant,<br/><br/>New <b>DEPOSIT</b> has been submitted for your Approval. Here is the Deposit Summary : <br/><br/>"+
					   "Distributor Name : <b>"+deposit.getDistributor().getFullName()+"</b><br/>"+
					   "Bank Name : <b>"+deposit.getAccount().getBankName()+"</b><br/>"+
					   "Bank Account : <b>"+deposit.getAccount().getBankAccount()+"</b><br/>"+
					   "Deposit Amount : <b>"+deposit.getDepositAmount()+" Tk</b><br/>"+
					   "Deposit Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getDepositDate())+"</b><br/>"+
					   "Submitted By : <b>"+deposit.getLastModifiedBy()+"</b><br/>"+
					   "Submitted Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getLastModified())+"</b><br/>"+
					   "<br/>Please take appropriate action asap (<a href='"+getAppUrl()+"/manager/pendingDistributorDepositList.html'>Click here</a>)"+
					   "<br/><br/>Regards<br/><span style='font-weight: bold;color: #0f3854;'>SFAS Notification Panel</span>";
		
		}else if(type == NotificationType.DISTRIBUTOR_DEPOSIT_DELETED){
			DistributorDeposit deposit = (DistributorDeposit)entity;
			content += deposit.getLastModifiedBy()+",<br/><br/>Your Distributor Deposit Entry has been <span style='font-weight: bold;color: red;'>Deleted</span>. Here is the Distributor Deposit Entry Deletion Summary : <br/><br/>"+
					   "Distributor Name : <b>"+deposit.getDistributor().getFullName()+"</b><br/>"+
					   "Bank Name : <b>"+deposit.getAccount().getBankName()+"</b><br/>"+
					   "Bank Account : <b>"+deposit.getAccount().getBankAccount()+"</b><br/>"+
					   "Deposit Amount : <b>"+deposit.getDepositAmount()+" Tk</b><br/>"+
					   "Deposit Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getDepositDate())+"</b><br/>"+
					   "Submitted By : <b>"+deposit.getLastModifiedBy()+"</b><br/>"+
					   "Submitted Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getLastModified())+"</b><br/>"+
					   "Deleted By : <b>"+Utils.getLoggedUser()+"</b><br/>"+
					   "Deleted Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, Utils.today())+"</b><br/>"+
					   "<br/>Please <a href='"+getAppUrl()+"/operator/distributorDeposit.html?did="+deposit.getDistributor().getId()+"'>visit here</a> "+
					   "in-order to enter more deposit for this Distributor.<br/><br/>Regards<br/><span style='font-weight: bold;color: #0f3854;'>SFAS Notification Panel</span>";
		
		}else if(type == NotificationType.DISTRIBUTOR_DEPOSIT_APPROVED){
			DistributorDeposit deposit = (DistributorDeposit)entity;
			content += deposit.getLastModifiedBy()+",<br/><br/>Your Distributor Deposit Entry has been <span style='font-weight: bold;color: green;'>Approved</span>. Here is the Distributor Deposit Entry Approval Summary : <br/><br/>"+
					   "Distributor Name : <b>"+deposit.getDistributor().getFullName()+"</b><br/>"+
					   "Bank Name : <b>"+deposit.getAccount().getBankName()+"</b><br/>"+
					   "Bank Account : <b>"+deposit.getAccount().getBankAccount()+"</b><br/>"+
					   "Deposit Amount : <b>"+deposit.getDepositAmount()+" Tk</b><br/>"+
					   "Deposit Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getDepositDate())+"</b><br/>"+
					   "Submitted By : <b>"+deposit.getLastModifiedBy()+"</b><br/>"+
					   "Submitted Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getLastModified())+"</b><br/>"+
					   "Approved By : <b>"+deposit.getAccountApprovedBy()+"</b><br/>"+
					   "Approved Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, deposit.getAccountApprovedDate())+"</b><br/>"+
					   "<br/>Distributor's current balance has been updated as well. Please <a href='"+getAppUrl()+"/operator/distributorList.html'>click here</a> "+
					   "to see the updated Distributor list.<br/><br/>Regards<br/><span style='font-weight: bold;color: #0f3854;'>SFAS Notification Panel</span>";
						
		}else if(type == NotificationType.DEPO_ORDER_IN){
			DepoOrder order = (DepoOrder)entity;			
			content += "Sir,<br/><br/>New <b>DEPO Order</b> has been submitted for your Approval. Here is the DEPO Order Summary : <br/><br/>"+
					   "<b><i><u>Order Details:</u></i></b><br/>"+
					   "DEPO Name : <b>"+order.getDepo().getName()+"</b><br/>"+
					   "RSM Name : <b>"+order.getDepo().getRsm().getNameWithRegion()+"</b><br/>"+
					   "Prepared By : <b>"+order.getCreatedBy()+"</b><br/>"+
					   "Order Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, order.getOrderDate())+"</b><br/><br/>"+
					   "<b><i><u>Order Summary:</u></i></b><br/>"+
					   "<table style='text-align: center;'><tr><td style='width:40px;'>Serial</td><td style='width:200px;'>Product</td>"+
					   "<td style='width:80px;'>Order Qty</td><td style='width:80px;'>Rate</td><td style='width:100px;'>Amount</td>"+
					   "<td>Remarks</td></tr>";
						for(DepoOrderLi orderLi : order.getOrderLiList()){
							if(orderLi.getQuantity() <= 0) continue;
							content += "<tr><td>"+orderLi.getSerial()+"</td><td>"+orderLi.getProduct().getFullName()+
									"</td><td>"+orderLi.getQuantity()+"</td><td>"+orderLi.getCurrentRate()+"</td><td>"+orderLi.getAmount()+
									"</td><td>"+orderLi.getRemark()+"</td></tr>";
						}
			
			content += "</table><br/>Total Order Amount : <b>"+order.getOrderAmount()+" Tk</b><br/><br/>"+
						"<b><i><u>Payment Details:</u></i></b><br/>"+ 
					   "Last Deposit Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, order.getLastDeposit().getDepositDate())+"</b><br/>"+
					   "Last Deposit Bank : <b>"+order.getLastDeposit().getAccount().getBankName()+"</b><br/>"+
					   "Last Deposit Account : <b>"+order.getLastDeposit().getAccount().getBankAccount()+"</b><br/>"+
					   "Current Available Balance : <b>"+order.getDepoBalance()+" Tk</b><br/>"+					   
					   "<br/>Please take appropriate action asap (<a href='"+getAppUrl()+"/manager/depoOrderList.html'>Click here</a>)"+
					   "<br/><br/>Regards<br/><span style='font-weight: bold;color: #0f3854;'>SFAS Notification Panel</span>";
		
		}
				
		return content;
	}
	
	private static String getAppUrl(){
		try{
			Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("../configurations/properties/deployment.properties"));			
			return props.getProperty("app.context.url");
		}catch(Exception ex){
			logger.debug("error : "+ex);
			return "";
		}
	}
}
