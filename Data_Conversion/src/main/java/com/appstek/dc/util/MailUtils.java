package com.appstek.dc.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

public class MailUtils {
	final static Logger logger=Logger.getLogger(MailUtils.class);
	
	public static boolean sendMail(String email,String subject,String content){
		boolean flag = false;
		try {
			Properties props = new Properties();
			final String mailUserName="s.amrutav@gmail.com";
			final String mailPassword="YouAreNotAllowedHere";
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "587");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.user", mailUserName);
			props.put("mail.smtp.starttls.enable", true);
			
			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(mailUserName,mailPassword);
						}
					});
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailUserName));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(content);
			Transport.send(message);
			logger.debug("Message Send Successfully");
			flag = true;
		} catch (MessagingException e) {
			logger.error("Exception occurs in : ", e);
		}
		
		return flag;
		
	}
}
