package com.codeyard.sfas.notification;

import java.util.ArrayList;
import java.util.List;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.util.Utils;

public class MailSenderThread extends Thread{
	 
    private List<User> toList;
    private String subject;
    private String text;
	 
    public MailSenderThread(List<User> toList, String subject, String text){
    	this.toList = toList;
    	this.subject = subject;
    	this.text = text;
    }

    public MailSenderThread(User to, String subject, String text){
    	this.toList = new ArrayList<User>();
    	this.toList.add(to);
    	this.subject = subject;
    	this.text = text;
    }

    public synchronized void run(){
    	MailSender sender = new MailSender();
    	for(User to : toList){
    		if(!Utils.isNullOrEmpty(to.getEmail()))
    			sender.send(to.getEmail(), this.subject, this.text);
    	}
    }
}

