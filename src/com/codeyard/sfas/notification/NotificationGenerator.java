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
			subject += "Pending Company Inventory Stock In Entry for Approval.";
		}
		return subject;
	}
	
	private static String getContent(NotificationType type, AbstractBaseEntity entity){
		
		String content = "Dear ";
		
		if(type == NotificationType.INVENTORY_STOCK_IN){
			StockIn stockIn = (StockIn)entity;
			content += "Inventory Admin,<br/><br/>New Company Inventory Stock In Entry is waiting for your Approval. Here is the Stock In Summary : <br/><br/>"+
					   "Product : <b>"+stockIn.getProduct().getFullName()+"</b><br/>"+
					   "Stock In Date : <b>"+Utils.getStringFromDate(Constants.DATE_FORMAT, stockIn.getStockInDate())+"</b><br/>"+
					   "Stock In Quantity : <b>"+stockIn.getQuantity()+"</b><br/>"+
					   "Stock In By : <b>"+stockIn.getLastModifiedBy()+"</b><br/>";
		}
		
		content += "<br/>Please take appropriate action asap (<a href='"+getAppUrl()+"/inventory/stockinList.html'>Click here</a>)<br/><br/>Regards<br/><b>SFAS Notification Panel</b>";
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
