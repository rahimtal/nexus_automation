package com.NexustAPIAutomation.java;

import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailSender {

	public static void sendEmail(String from, String to, String subject, String body)
			throws AddressException, MessagingException, IOException {
		String host = "smtp.gmail.com"; // Replace with your SMTP host
		final String username = "cogsauto@gmail.com"; // Your email
		final String password = "ahkalznkxnynhlcz"; // Use app-specific password if Gmail

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject("TestNG API Test Report");

		String reportPath = System.getProperty("user.dir") + "/test-output/emailable-report.html";
		System.out.println("User directory: " + System.getProperty("user.dir"));
		String htmlContent = new String(Files.readAllBytes(Paths.get(reportPath)));

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(htmlContent, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		message.setContent(multipart);
		Transport.send(message);
	}

	@Test
	public void testemail() throws AddressException, MessagingException, IOException

	{
		EmailSender.sendEmail("cogsauto@gmail.com", "trahim@cogsdale.com", "Test", "Test");
	}

}
