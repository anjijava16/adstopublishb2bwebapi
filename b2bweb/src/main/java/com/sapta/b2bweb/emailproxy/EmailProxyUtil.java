package com.sapta.b2bweb.emailproxy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import freemarker.template.TemplateException;

public class EmailProxyUtil {
	public static boolean sendEmail(String ccEmails, String bccEmails, String emailBody, boolean htmlContent,String toEmails) throws AddressException,
			MessagingException, IOException, TemplateException {
		System.out.println(" toEmailList "+toEmails);
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put(CommonConstants.MAIL_SMTP_HOST, CommonConstants.SMTP_GMAIL_COM);
		properties.put(CommonConstants.MAIL_SMTP_STARTTLS, CommonConstants.TRUE);
		properties.put(CommonConstants.MAIL_SMTP_PORT, CommonConstants.MAIL_SMTP_PORT_NUMBER);
		properties.put(CommonConstants.MAIL_SMTP_AUTH, CommonConstants.TRUE);
        properties.put(CommonConstants.MAIL_SMTP_STARTTLS, CommonConstants.TRUE);
		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {						
			public PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication passwordAuthentication = null;
				try {					
					passwordAuthentication = new PasswordAuthentication(CommonConstants.FROMEMAIL,CommonConstants.PASSWORD);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return passwordAuthentication;
			}
		};

		Session session = Session.getInstance(properties, auth);
		//Session session = Session.getInstance(properties,null);
		// Create Message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(CommonConstants.FROMEMAIL));
		msg.setSentDate(new Date());
		htmlContent = true;
		
        if(htmlContent){
        	msg.setContent(emailBody, CommonConstants.TEXT_HTML);
        } else {
        	msg.setText(emailBody);
        }		
        System.out.println(" fromEmailList "+CommonConstants.FROMEMAIL);
		// To Email List
		List<String> toEmailList = Arrays.asList(toEmails.split(CommonConstants.COMMA));
		System.out.println(" toEmailList "+toEmailList);
		if(toEmailList != null){
			for(String toEmail : toEmailList ){
				if(emailValidation(toEmail.trim())){
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail.trim()));			
				}
			}
		}		
		
		// CC Email List
		List<String> ccEmailList = Arrays.asList(ccEmails.split(CommonConstants.COMMA));
		if(toEmailList != null){
			for(String ccEmail : ccEmailList ){
				if(emailValidation(ccEmail.trim())){
					msg.addRecipient(Message.RecipientType.CC, new InternetAddress(ccEmail.trim()));			
				}
			}
		}
		
		//BCC Email List
		List<String> bccEmailList = Arrays.asList(bccEmails.split(CommonConstants.COMMA));
		if(toEmailList != null){
			for(String bccEmail : bccEmailList ){
				if(emailValidation(bccEmail.trim())){
					msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccEmail.trim()));			
				}
			}
		}
		
		// sends the e-mail
		Transport.send(msg);
		
		return true;
	}
	
	public static boolean  emailValidation(String email){		
		String EMAIL_REGEX = CommonConstants.REG_EXP;
		return email.matches(EMAIL_REGEX)? true : false;
	}

}
