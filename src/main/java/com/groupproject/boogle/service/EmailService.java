package com.groupproject.boogle.service;

import java.time.LocalDate;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.groupproject.boogle.model.Guest;
import com.groupproject.boogle.model.Newsletter;
import com.groupproject.boogle.model.Order;
import com.groupproject.boogle.model.User;

@Service("EmailService")
public class EmailService {
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public void constructOrderConfirmationEmailForUser (User user, Order order) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("orderItemList", order.getOrderItemList());
		String text = templateEngine.process("orderConfirmationEmailTemplate", context);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getEmail());
				email.setSubject("Order Confirmation - " + order.getOrderId());
				email.setText(text, true);
				email.setFrom(new InternetAddress("boogle.2021.s.e.m.norwalkcc@gmail.com"));
				
			}
		};
		
		mailSender.send(messagePreparator);
	}
	
	public void constructOrderConfirmationEmailForGuest (Guest guest, Order order) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("guest", guest);
		context.setVariable("orderItemList", order.getOrderItemList());
		String text = templateEngine.process("orderConfirmationEmailTemplate", context);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(guest.getEmail());
				email.setSubject("Order Confirmation - " + order.getOrderId());
				email.setText(text, true);
				email.setFrom(new InternetAddress("boogle.2021.s.e.m.norwalkcc@gmail.com"));
				
			}
		};
		
		mailSender.send(messagePreparator);
	}
	
	public void constructNewsletterEmail (Newsletter newsletter) {
		Context context = new Context();
		context.setVariable("newsletter", newsletter);
		String text = templateEngine.process("newsletterEmailTemplate", context);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(newsletter.getEmail());
				LocalDate currentDate = LocalDate.now();
				email.setSubject("Boogle's Newsletter " + currentDate);
				email.setText(text, true);
				email.setFrom(new InternetAddress("boogle.2021.s.e.m.norwalkcc@gmail.com"));
				
			}
		};
		
		mailSender.send(messagePreparator);
	}
	
	public void constructForgotPasswordEmail (String emailAddress, String token) {

		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("boogle.2021.s.e.m.norwalkcc@gmail.com");
        message.setTo(emailAddress); 
        message.setSubject("Boogle - Verification code to reset your password");
        message.setText("Verification code: " + token);
        mailSender.send(message);
		
	}

}
