package com.aste.lsme.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	JavaMailSender mailSender;
	@Async
	public void send2faAuthCode(String message,String email,String subject){
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");
			msg.setContent(message, "text/html");
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setFrom("<info@stie.com.sg>");
			System.err.println("Sending code for 2FA");
			mailSender.send(msg);
			System.err.println("2FA code sent");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
