package com.NexustAPIAutomation.java;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.net.URL;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeycloakStep1And2And3WithCookies {

    private static final SecureRandom random = new SecureRandom();
    private static final String PKCE_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-._~";

    public static void main(String[] args) {
        String kcBase = "http://localhost:8080/realms/nexus";
        String clientId = "nexus-portal";
        String redirectUri = "https://oauth.usebruno.com/callback";
        String username = "cogsuser";
        String password = "password";
        String rememberMe = "on";

        // Step 1: Auth GET
        Map<String, Object> context = stage1Auth(kcBase, clientId, redirectUri);

        // Step 2: Login POST (if needed)
        if ("need_login".equals(context.get("flow_step"))) {
            context.putAll(step2Login((String) context.get("kc_login_action"),
                    username, password, rememberMe,
                    (Map<String, String>) context.get("cookies")));
        }

        // Step 3: Token Exchange
        Map<String, Object> tokenContext = step3TokenExchange(
                kcBase, clientId, redirectUri,
                (String) context.getOrDefault("auth_code", ""), // may be empty if 302
                (String) context.get("pkce_verifier"),
                (Map<String, String>) context.get("cookies")
        );

        System.out.println("\n========== Step 3 Token Result ==========");
        tokenContext.forEach((k, v) -> System.out.println(k + " = " + v));
        System.out.println("========================================");
    }

    // ================= Step 1 =================
    public static Map<String, Object> stage1Auth(String kcBase, String clientId, String redirectUri) {
        Map<String, Object> context = new HashMap<>();

        String pkceVerifier = randomString(64);
        String pkceChallenge = pkceChallengeS256(pkceVerifier);
        String state = randomString(24);
        String nonce = randomString(24);

        context.put("pkce_verifier", pkceVerifier);
        context.put("pkce_challenge", pkceChallenge);
        context.put("oauth_state", state);
        context.put("oauth_nonce", nonce);

        String authUrl = kcBase + "/protocol/openid-connect/auth"
                + "?response_type=code"
                + "&client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&scope=openid"
                + "&code_challenge=" + pkceChallenge
                + "&code_challenge_method=S256"
                + "&state=" + state
                + "&nonce=" + nonce;

        System.out.println("Auth URL:\n" + authUrl);

        Response response = RestAssured
                .given()
                .redirects().follow(false)
                .header("User-Agent", "Mozilla/5.0")
                .when()
                .get(authUrl);

        int status = response.statusCode();
        System.out.println("Step 1 Status Code: " + status);

        Map<String, String> cookies = response.getCookies();
        context.put("cookies", cookies);

        String location = response.getHeader("Location") != null ? response.getHeader("Location") : "";

        if (status == 302 && location.contains("code=")) {
            String authCode = extractQueryParam(location, "code");
            context.put("auth_code", authCode);
            context.put("flow_step", "got_code");
            System.out.println("Auth code (Step 1 SSO): " + authCode);
        } else if (status == 200) {
            String html = response.asString();
            String loginAction = parseLoginFormAction(html);
            if (loginAction != null) {
                context.put("kc_login_action", loginAction);
                context.put("flow_step", "need_login");
                System.out.println("Login action URL: " + loginAction);
            } else {
                System.out.println("❌ Could not parse login action URL");
            }
        } else {
            System.out.println("❌ Unexpected response body:\n" + response.asString());
        }

        return context;
    }

    // ================= Step 2 =================
    public static Map<String, Object> step2Login(String kcLoginAction, String username, String password,
                                                 String rememberMe, Map<String, String> cookies) {
        Map<String, Object> context = new HashMap<>();

        System.out.println("Step 2 - Posting to: " + kcLoginAction);
        System.out.println("Step 2 - Username: " + username);
        System.out.println("Step 2 - Password: " + password);
        System.out.println("Step 2 - RememberMe: " + rememberMe);

        Response response = RestAssured
                .given()
                .redirects().follow(false)
                .header("User-Agent", "Mozilla/5.0")
                .cookies(cookies)
                .formParam("username", username)
                .formParam("password", password)
                .formParam("rememberMe", rememberMe)
                .when()
                .post(kcLoginAction);

        int status = response.statusCode();
        System.out.println("Step 2 Status Code: " + status);
        String location = response.getHeader("Location");
        System.out.println("Step 2 Location Header: " + (location != null ? location : "NULL"));

        if (location != null && location.contains("code=")) {
            String authCode = extractQueryParam(location, "code");
            context.put("auth_code", authCode);
            context.put("flow_step", "got_code");
            System.out.println("Auth code (Step 2 login): " + authCode);
        } else if (status == 200) {
            // Login failed, show response for debugging
            String response_body = response.asString();
            System.out.println("❌ Login failed - credentials rejected or form incomplete");
            System.out.println("Response length: " + response_body.length() + " chars");
            if (response_body.contains("alert-error")) {
                // Extract error message if present
                Pattern p = Pattern.compile("<span[^>]*class=\"[^\"]*alert-error[^\"]*\"[^>]*>([^<]+)</span>", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(response_body);
                if (m.find()) {
                    System.out.println("Error message: " + m.group(1));
                }
            }
        } else {
            System.out.println("❌ No auth code found. Response body:\n" + response.asString());
        }

        return context;
    }

    // ================= Step 3 =================
    public static Map<String, Object> step3TokenExchange(String kcBase, String clientId, String redirectUri,
                                                         String authCode, String pkceVerifier, Map<String, String> cookies) {
        Map<String, Object> context = new HashMap<>();
        String tokenUrl = kcBase + "/protocol/openid-connect/token";

        Response response = RestAssured
                .given()
                .cookies(cookies)
                .header("User-Agent", "Mozilla/5.0")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("grant_type", "authorization_code")
                .formParam("client_id", clientId)
                .formParam("code", authCode) // may be empty
                .formParam("redirect_uri", redirectUri)
                .formParam("code_verifier", pkceVerifier)
                .when()
                .post(tokenUrl);

        int status = response.statusCode();
        System.out.println("Step 3 Status Code: " + status);
        System.out.println("Step 3 Response Body: " + response.asString());

        if (status == 200) {
            try {
                context.put("access_token", response.jsonPath().getString("access_token"));
                context.put("refresh_token", response.jsonPath().getString("refresh_token"));
            } catch (Exception e) {
                System.out.println("❌ Failed to parse token response: " + e.getMessage());
                context.put("access_token", "");
                context.put("refresh_token", "");
            }
        } else {
            context.put("access_token", "");
            context.put("refresh_token", "");
            System.out.println("❌ Token exchange failed with status " + status);
            try {
                String error = response.jsonPath().getString("error");
                String errorDescription = response.jsonPath().getString("error_description");
                System.out.println("   Error: " + error);
                System.out.println("   Description: " + errorDescription);
            } catch (Exception e) {
                System.out.println("   Could not parse error response");
            }
        }

        return context;
    }

    // ================= Helper Methods =================
    private static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(PKCE_CHARS.charAt(random.nextInt(PKCE_CHARS.length())));
        }
        return sb.toString();
    }

    private static String pkceChallengeS256(String verifier) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(verifier.getBytes(java.nio.charset.StandardCharsets.US_ASCII));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
        } catch (Exception e) {
            throw new RuntimeException("PKCE generation failed", e);
        }
    }

    private static String extractQueryParam(String urlString, String param) {
        try {
            URL url = new URL(urlString);
            String[] queryParams = url.getQuery().split("&");
            for (String q : queryParams) {
                if (q.startsWith(param + "=")) {
                    return q.split("=")[1];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String parseLoginFormAction(String html) {
        Pattern p = Pattern.compile("<form[^>]*id=\"kc-form-login\"[^>]*action=\"([^\"]+)\"", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(html);
        if (m.find()) {
            return m.group(1).replace("&amp;", "&");
        }
        return null;
    }
}
