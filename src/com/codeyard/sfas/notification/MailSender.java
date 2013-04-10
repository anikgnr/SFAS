package com.codeyard.sfas.notification;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.codeyard.sfas.util.Constants;

public class MailSender {
	 
    private String from;
    private String password;
    private Message simpleMessage;
	 
    public MailSender(){
    	this.from = Constants.MAIL_FROM_ADDRESS;
    	this.password = Constants.MAIL_FROM_PASSWORD;
    	
        Properties props = new Properties();
        props.put("mail.smtp.host", Constants.MAIL_SMTP_HOST);
        props.put("mail.smtp.port", Constants.MAIL_SMTP_PORT);
        props.put("mail.smtp.auth", Constants.MAIL_SMTP_AUTH);
        props.put("mail.smtp.starttls.enable", Constants.MAIL_SMTP_TLS);
        Session mailSession = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(from, password);
        }});
        simpleMessage = new MimeMessage(mailSession);       
    }
	 
    public void send(String to, String subject, String text){
	     	
        InternetAddress fromAddress = null;
        InternetAddress toAddress = null;
        try {
            fromAddress = new InternetAddress(from);
            toAddress = new InternetAddress(to);
        } catch (AddressException e) {
            e.printStackTrace();
        }
	 
        try {
            simpleMessage.setFrom(fromAddress);
            simpleMessage.setRecipient(RecipientType.TO, toAddress);
            simpleMessage.setSubject(subject);
            simpleMessage.setContent(text, "text/html; charset=utf-8");
	 
            Transport.send(simpleMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

