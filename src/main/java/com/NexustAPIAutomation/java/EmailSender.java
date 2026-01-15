package com.NexustAPIAutomation.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailSender {

	public static void sendEmail(String from, String to, String subject, String body)
			throws AddressException, MessagingException, IOException, InterruptedException {
		String host = "smtp.gmail.com"; // Replace with your SMTP host
		final String username = "cogsauto@gmail.com"; // Your email
		final String password = "ahkalznkxnynhlcz"; // Use app-specific password if Gmail

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		System.out.println("Initializing SMTP connection...");
		System.out.println("SMTP Host: " + host);
		System.out.println("SMTP Port: 587");
		System.out.println("From Email: " + from);
		System.out.println("To Email: " + to);

		try {
			Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			System.out.println("SMTP connection initialized successfully.");

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("TestNG API Test Report");

			String reportPath = Files.walk(Paths.get(System.getProperty("user.dir")))
					.filter(path -> path.getFileName().toString().equals("emailable-report.html"))
					.findFirst()
					.orElseThrow(() -> new IOException("emailable-report.html not found in any folder"))
					.toString();
			System.out.println("Starting EmailSender...");
			System.out.println("Report path: " + reportPath);

			// Retry mechanism to wait for the report file
			int maxRetries = 1; // Maximum retries (60 seconds total)
			int retryInterval = 1000; // Retry every 1 second
			for (int i = 0; i < maxRetries; i++) {
				if (Files.exists(Paths.get(reportPath))) {
					System.out.println("Report file found: " + reportPath);
					break;
				}
				System.out.println("Report file not found. Retrying in 1 second...");
				Thread.sleep(retryInterval);
			}

			// Final check if the report file exists
			if (!Files.exists(Paths.get(reportPath))) {
				System.err.println("Report file not found after waiting: " + reportPath);
				throw new IOException("Report file not found after waiting: " + reportPath);
			}
			System.out.println("Reading report file...");
			String htmlContent = new String(Files.readAllBytes(Paths.get(reportPath)));
			System.out.println("Report file read successfully.");

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(htmlContent, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Email sent successfully to " + to);
		} catch (MessagingException e) {
			System.err.println("Failed to send email: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Failed to read report file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// @Test
	public void testemail() throws AddressException, MessagingException, IOException, InterruptedException

	{
		EmailSender.sendEmail("cogsauto@gmail.com", "trahim@cogsdale.com", "Test", "Test");
	}

	public static void main(String[] args) {
		try {
			System.out.println("Waiting for 10 seconds before sending email...");
			Thread.sleep(10000); // 10-second delay
			sendEmail("cogsauto@gmail.com", "trahim@cogsdale.com", "Nexus API Regression Tests (Dockers) Report ",
					"Test");
			sendEmail("cogsauto@gmail.com", "rthurairasa@cogsdale.com", "Nexus API Regression Tests (Dockers) Report ",
					"Test");
			sendEmail("cogsauto@gmail.com", "makhlaq@cogsdale.com", "Nexus API Regression Tests (Dockers) Report ",
					"Test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
