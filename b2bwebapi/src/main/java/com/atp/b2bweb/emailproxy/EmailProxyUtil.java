package com.atp.b2bweb.emailproxy;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class EmailProxyUtil {
	public static boolean sendEmail(List<String> ccEmails, List<String> bccEmails, String emailBody, boolean htmlContent,List<String> toEmails, UUID uniqueKey) throws AddressException,
			MessagingException, IOException, TemplateException {
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put(CommonConstants.MAIL_SMTP_HOST, CommonConstants.SMTP_GMAIL_COM);
		properties.put(CommonConstants.MAIL_SMTP_STARTTLS, CommonConstants.TRUE);
		properties.put(CommonConstants.MAIL_SMTP_PORT, CommonConstants.MAIL_SMTP_PORT_NUMBER);
		properties.put(CommonConstants.MAIL_SMTP_AUTH, CommonConstants.TRUE);
        properties.put(CommonConstants.MAIL_SMTP_STARTTLS, CommonConstants.TRUE);
        System.out.println("properties"+properties);
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
		
		
		
		
		
		 // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File("C:/Users/user/Desktop/atpwebapi/adstopublishb2bwebapi/b2bwebapi/src/main/resources/templates"));
        System.out.println("cfgcfgcfgcfg"+cfg);
        freemarker.template.Template template = cfg.getTemplate("email_template.ftl");
        System.out.println("template"+template);
        Map<String, String> rootMap = new HashMap<String, String>();
       // String imagelocation = request.getServletContext().getRealPath("resources/images/logo.png");
        
        rootMap.put("name","shruthi");
        rootMap.put("link","http://localhost:8080/b2bweb?token="+uniqueKey);
        Writer out = new StringWriter();
        template.process(rootMap, out);

        messageBodyPart.setContent(out.toString(), "text/html");
       
        // Create a multipart message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
        msg.setContent(multipart);
		
		/*htmlContent = true;
		
        if(htmlContent){
        	msg.setContent(emailBody, CommonConstants.TEXT_HTML);
        } else {
        	msg.setText(emailBody);
        }*/		
		// To Email List
		List<String> toEmailList = toEmails;
		if(toEmailList != null && toEmailList.size() > 0){
			for(String toEmail : toEmailList ){
				if(emailValidation(toEmail.trim())){
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail.trim()));			
				}
			}
		}		
		
		// CC Email List
		List<String> ccEmailList = ccEmails;
		if(ccEmailList != null && ccEmailList.size() > 0){
			for(String ccEmail : ccEmailList ){
				if(emailValidation(ccEmail.trim())){
					msg.addRecipient(Message.RecipientType.CC, new InternetAddress(ccEmail.trim()));			
				}
			}
		}
		//BCC Email List
		List<String> bccEmailList = ccEmails;
		if(bccEmailList != null && bccEmailList.size() > 0){
			for(String bccEmail : bccEmailList ){
				if(emailValidation(bccEmail.trim())){
					msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccEmail.trim()));			
				}
			}
		}
		System.out.println("msg"+msg);
		// sends the e-mail
		Transport.send(msg);
		return true;
	}
	
	public static boolean  emailValidation(String email){		
		String EMAIL_REGEX = CommonConstants.REG_EXP;
		return email.matches(EMAIL_REGEX)? true : false;
	}

}
