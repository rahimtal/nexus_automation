package com.NexustAPIAutomation.java;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiHealthCheck {

    public static void main(String[] args) {
        String apiUrl = "http://your-api-endpoint.com/health"; // Replace with your API health check URL

        try {
            System.out.println("Checking API health at: " + apiUrl);
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // 5 seconds timeout
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("API is running. Response code: " + responseCode);
            } else {
                System.err.println("API is not running. Response code: " + responseCode);
                System.exit(1); // Fail the build
            }
        } catch (IOException e) {
            System.err.println("Failed to connect to the API: " + e.getMessage());
            System.exit(1); // Fail the build
        }
    }
}