package com.NexustAPIAutomation.java;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;

public class verifyPDFReports {

	public static void verifyPDF(String expected, String uri) throws Exception {
		// URL of the REST API that returns a PDF
		URL url = new URL("http://localhost:3000/api/v4" + uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		String basicAuth = "Bearer " + CommonMethods.getToken();
		// Set the request method (GET is default)
		connection.setRequestProperty("Authorization", basicAuth);
		connection.setRequestMethod("GET");
		// Get the response code
		int responseCode = connection.getResponseCode();
		// If successful (HTTP_OK = 200), read the input stream and save it
		if (responseCode == HttpURLConnection.HTTP_OK) {
			try (InputStream is = connection.getInputStream()) {
				// Load the PDF document
				PDDocument document = PDDocument.load(is);
				// Instantiate PDFTextStripper class
				PDFTextStripper pdfStripper = new PDFTextStripper();
				// Retrieving text from PDF document
				String text = pdfStripper.getText(document);
				// Check if the PDF contains the expected text
				if (text.contains(expected)) {
					System.out.println("PDF verification passed");
				} else {
					System.out.println(text);
					Assert.fail("PDF verification failed");
				}
				// Closing the document
				document.close();
			}
		} else {
			System.out.println("GET request not worked");
		}
		// Disconnect the HttpURLConnection stream
		connection.disconnect();
	}

	public static String returnPDF(String uri) throws Exception {
		// URL of the REST API that returns a PDF
		URL url = new URL("http://localhost:3000/api/v4" + uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		String basicAuth = "Bearer " + CommonMethods.getToken();
		// Set the request method (GET is default)
		connection.setRequestProperty("Authorization", basicAuth);
		connection.setRequestMethod("GET");
		// Get the response code
		int responseCode = connection.getResponseCode();
		// If successful (HTTP_OK = 200), read the input stream and save it
		if (responseCode == HttpURLConnection.HTTP_OK) {
			try (InputStream is = connection.getInputStream()) {
				// Load the PDF document
				PDDocument document = PDDocument.load(is);
				// Instantiate PDFTextStripper class
				PDFTextStripper pdfStripper = new PDFTextStripper();
				// Retrieving text from PDF document
				String text = pdfStripper.getText(document);
				// Check if the PDF contains the expected text
				document.close();
				return text;
			}
		} else {
			System.out.println("GET request not worked");
		}
		// Disconnect the HttpURLConnection stream
		connection.disconnect();
		return null;
	}
}