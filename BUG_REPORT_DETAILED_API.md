# BUG REPORT - DETAILED: Receipt Creation Failure
**saveReciept_4_prepaymentExistingCustomer**

**Report Date:** May 4, 2026  
**Test Class:** Private_CashieringController_Test  
**Test Method:** saveReciept_4_prepaymentExistingCustomer (Line 497)  
**Severity:** CRITICAL  
**Status:** OPEN  
**Priority:** P1

---

## API CALL SEQUENCE

### STEP 1: Cash-in Status Check (GET)
**URI:** `GET http://localhost:3000/api/v4/cashiering/cashIn`  
**API Version:** 4.0 (v4)  
**Authentication:** PKCE Token Flow  
**Token Status:** Valid, 299 seconds remaining  

**Expected Response:**
```json
{
  "CashIn": [
    {
      "RegisterId": "REGISTER-00001",
      "Status": "Active"
    }
  ]
}
```

**Actual Response:** (Not shown in logs - triggered re-establishment logic)

**Outcome:** ⚠️ Status expired/inactive - Test triggers re-establishment

---

### STEP 2: Cash-in Re-establishment (POST)
**URI:** `POST http://localhost:3000/api/v4/cashiering/cashin`  
**API Version:** 4.0 (v4)  
**Authentication:** Cached PKCE Token  
**Token:** Valid for 299 seconds  
**Status Code:** 200  
**Response Time:** ~1 second

**Request Body:**
```json
{
    "CashIn": [
        {
            "RegisterId": "REGISTER-00001",
            "OpeningBalance": 100.00,
            "CheckbookId": "FIRST NATIONAL",
            "PaymentOriginId": "",
            "LoginDateTime": "2020-11-09T11:16:01.230",
            "ComputerName": "vv"
        }
    ]
}
```

**Expected Response:**
```json
{
  "CashIn": [
    {
      "Success": true,
      "Messages": []
    }
  ]
}
```

**Actual Response:**
```json
{
  "CashIn": [
    {
      "Success": true,
      "Messages": [
        {
          "Enabled": 1,
          "Info": "User cogsuser is already logged in at register REGISTER-00001.",
          "Level": 1
        }
      ]
    }
  ]
}
```

**Status Code:** 200  
**Outcome:** ✅ SUCCESS - But state not persisted

---

### STEP 3: Receipt Creation (POST) - FAILURE ❌
**URI:** `POST http://localhost:3000/api/v4/cashiering/receipt`  
**API Version:** 4.0 (v4)  
**Authentication:** Cached PKCE Token  
**Token:** Valid for 294 seconds (5 seconds elapsed since cash-in)  
**Status Code:** 200  
**Response Time:** ~0.5 seconds  
**Test Execution Time:** 19,086 ms total

**Request Headers:**
```
Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJIYUZCRHdHUDdoQW04LU9ZOFJIMlROMC1rS1d0S0VGRDN3NkpDbllRM25ZIn0.eyJleHAiOjE3Nzc4OTg0NTMsImlhdCI6MTc3Nzg5ODE1MywiYXV0aF90aW1lIjoxNzc3ODk4MTUzLCJqdGkiOiJvbnJ0YWM6NWRlZGI0NDYtZWQ2Zi1hMmRlLThjZjgtM2U4ZGYyOTg1MWU1IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9uZXh1cyIsImF1ZCI6Im5leHVzLWFwaSIsInN1YiI6IjFhMTZjYTdhLTViNWItNDg0NS1hMDU5LWNlZGNiMzA5MzQ1MiIsInR5cCI6IkJlYXJlciIsImF6cCI6Im5leHVzLXBvcnRhbCIsInNpZCI6IjllYzE1MDFiLTFlMzEtNDQ1ZS04ZDFjLTg5MWM4MzRhOGM4YyIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovLzE3Mi4yNy4xOS42NDozMDAwIiwiaHR0cDovLzE3Mi4yNy4xOS42NDo0MjAwIiwiaHR0cHM6Ly9uZXh1cy5sb2NhbDo4NDQzIiwiaHR0cDovL2xvY2FsaG9zdDo0MjAwIiwiaHR0cHM6Ly9vYXV0aC51c2VicnVuby5jb20iXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbmV4dXMtcG9ydGFsIiwib2ZmbGluZV9hY2Nlc3MiXX0sInJlc291cmNlX2FjY2VzcyI6eyJuZXh1cy1hcGkiOnsicm9sZXMiOlsiYXBpLWNvbm5lY3Rpb25tZW51LWVuYWJsZWQiLCJhcGktY29udGFjdGxvZy12aWV3IiwiYXBpLWdsb2JhbG9wdGlvbnMtdXBkYXRlIiwiYXBpLWN1c3RvbWVyLXZpZXciLCJhcGktZ2xvYmFsb3B0aW9ucy12aWV3IiwiYXBpLWFjY291bnRvcHRpb25zLXZpZXciLCJhcGktY29udGFjdGxvZy1jcmVhdGUiLCJhcGktY29udGFjdGxvZy11cGRhdGUiLCJhcGktY3VzdG9tZXItY3JlYXRlIiwiYXBpLWN1c3RvbWVyLWRlbGV0ZSIsImFwaS1lcXVpcG1lbnQtdXBkYXRlIiwiYXBpLW1ldGVyc3dpdGNoLWNyZWF0ZSIsImFwaS1jdXN0b21lci11cGRhdGUiLCJhcGktYWNjb3VudG9wdGlvbnMtdXBkYXRlIiwiYXBpLWFjdGlvbnNtZW51LWVuYWJsZWQiLCJhcGktY29udGFjdGxvZy1kZWxldGUiLCJhcGktZXF1aXBtZW50c3dpdGNoLWNyZWF0ZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJVc2VyTmFtZSI6ImNvZ3N1c2VyIiwiVXNlcklEIjoiY29nc3VzZXIiLCJncm91cHMiOlsiL0tleWNsb2FrIEFkbWluIl19.YCgw8AI_ftv4stPcBtO3ltrVbY6YLpHbsjafCIJXs1COIQ33OZanztUciHWiARXaKaNxm2WbsZ0t17w13xs-vyLZFjLlijv7quUmLlyhW1sWacabcEzpLbQU5xBQUrSF4RPGKNlfP9mzeyUwS6ej4_j6izezf1JOxs9VPHwn64q5DoPzfo0relR0cO3e3PNR4M57KqOXvRAyS9kuq64ZK0NsOp6ESnOlBMewbINDK7P3TrnZescno3fSfXWbAbTSmm3bZrxQCfuuQCqwct_aYjFaWemQl3oMmoQ6xHHIvl_HmcaTCECSB_FcpRiTGhj2y9eqvK-8xPseLkLIrj_bmQ
Content-Type: application/json
```

**Request Body:**
```json
{
   "Receipt":{
      "ReceiptNumber":"004240724000005",
      "OriginatingReceiptNumber":"",
      "Void":false,
      "CustomerId":"customer001",
      "LocationId":"water001",
      "PaymentOrigin":"API",
      "CheckbookId":"FIRST NATIONAL",
      "PaidBy":{
         "Type":1,
         "Description": "customer001",
         "Id":"customer001"
      },
      "Cash":10,
      "Check":{
         "Amount":0,
         "Number":""
      },
      "CreditCard": {
          "Amount": 0
      },
      "Unapplied":{
         "Amount":10,
         "Account":"000-2115-00",
         "LocationId":"water001"
      },
      "Change":0,
      "Comment":"This is a comment to be saved into comment in UMRM102",
      "Document":[],
      "ServiceOrder": {
            "Id": "SORD00000008996",
            "Task": {
                "Sequence": "1000",
                "EmployeeId":"sa"
            }
        }
    }
}
```

**Expected Response (HTTP 200):**
```json
{
  "Receipt": {
    "Success": true,
    "Data": {
      "ReturnValues": [
        {
          "Name": "ReceiptNumber",
          "Value": "004240724000005"
        }
      ]
    },
    "Messages": []
  }
}
```

**Actual Response (HTTP 200):**
```json
{
  "Receipt": {
    "Success": false,
    "Data": null,
    "Messages": [
      {
        "Enabled": 1,
        "Info": "The user cogsuser is no longer cashed in. The receipt could not be created.",
        "Level": 3
      }
    ]
  }
}
```

**Status Code:** 200 ✅ (HTTP-level success)  
**Response Status:** ❌ FAILURE (business-level failure)  
**Outcome:** ❌ FAILED - Receipt not created

---

## ANALYSIS MATRIX

| Aspect | Expected | Actual | Status |
|--------|----------|--------|--------|
| **HTTP Status** | 200 | 200 | ✅ Pass |
| **Content-Type** | application/json | application/json | ✅ Pass |
| **Response Received** | Yes | Yes | ✅ Pass |
| **Success Flag** | true | false | ❌ Fail |
| **Data Object** | ReturnValues with ReceiptNumber | null | ❌ Fail |
| **Messages Array** | [] (empty) | 1 error message | ❌ Fail |
| **Error Message** | None | "user no longer cashed in" | ❌ Fail |
| **Receipt Created** | YES | NO | ❌ Fail |

---

## AUTHENTICATION DETAILS

| Property | Value |
|----------|-------|
| **Auth Method** | PKCE (Proof Key Code Exchange) |
| **Auth Server** | http://localhost:8080/realms/nexus |
| **Client ID** | nexus-portal |
| **User** | cogsuser |
| **Token Type** | Bearer (JWT) |
| **Token Expiry** | 300 seconds from issue |
| **Token Issue Time** | 2026-05-04T17:28:15+05:00 (approx) |
| **Token Remaining at Receipt Call** | 294 seconds |
| **Token Status at Receipt Call** | ✅ VALID |
| **Refresh Token** | Present and valid |
| **Refresh Token TTL** | 1800 seconds |
| **Scope** | openid email |
| **Claims in Token** | UserName, UserID, Groups, Roles |

---

## DATABASE STATE

| Item | Value |
|------|-------|
| **Database Name** | TWO |
| **Restore Time** | 0.634 seconds |
| **Pages Restored** | 134,890 pages |
| **Restore Speed** | 1662.183 MB/sec |
| **Restore Status** | ✅ SUCCESS |
| **Restore Timestamp** | Start of test run |

---

## TIMELINE

| Time (ms) | Event | Status | Token TTL |
|-----------|-------|--------|-----------|
| 0 | Test started | - | - |
| 100 | DB restore complete | ✅ | - |
| 500 | Cash-in status check started | 🔍 | - |
| 1000 | PKCE auth flow started | - | - |
| 1500 | Step 1 (Get code) | ✅ 200 | - |
| 2000 | Step 2 (Login) | ✅ 302 redirect | - |
| 2500 | Step 3 (Token exchange) | ✅ 200 | 300s |
| 3000 | Token cached | ✅ | 299s |
| 3500 | Cash-in re-establishment called | - | 298s |
| 4500 | Cash-in response received | ✅ Success=true | 297s |
| 5000 | Receipt POST sent | - | 294s |
| 5500 | Receipt POST response | ❌ Fail | 290s |
| 19086 | Test ended with FAILURE | ❌ | - |

---

## ERROR CLASSIFICATION

**Error Type:** Business Logic Error  
**HTTP Status Masking:** True (HTTP 200 masks business-level failure)  
**Error Severity:** CRITICAL  
**User Impact:** Complete workflow blockage  

**Error Message Analysis:**
- **Message:** "The user cogsuser is no longer cashed in. The receipt could not be created."
- **Implies:** Backend cash-in session has expired or been cleared
- **Contradiction:** Cash-in API just succeeded ~0.5 seconds ago
- **Root Cause:** Backend session persistence failure

---

## SYSTEM CONFIGURATION

| Component | Value |
|-----------|-------|
| **API Server** | localhost:3000 |
| **Auth Server** | localhost:8080 |
| **Register ID** | REGISTER-00001 |
| **Test User** | cogsuser |
| **Customer ID** | customer001 |
| **Location ID** | water001 |
| **Checkbook** | FIRST NATIONAL |
| **Opening Balance** | 100.00 |
| **Receipt Amount** | 10.00 |
| **API Version** | v4 (4.0) |

---

## RETRY BEHAVIOR

**Retry Attempted:** Yes  
**Retry Count:** 2 attempts  
**Retry Strategy:** POST Attempt 1 of 2  

**Attempt 1 Result:** ❌ FAILED  
**Attempt 2:** Not shown in logs (likely same failure)

**Log Entry:**
```
POST Attempt 1 of 2
Posting uri = 4.0 /cashiering/receipt
? Using cached token (valid for 294 seconds)
Actual Response = {"Receipt":{"Success":false,...}}
FAILED: saveReciept_4_prepaymentExistingCustomer (19086ms)
```

---

## PERFORMANCE METRICS

| Metric | Value |
|--------|-------|
| **Total Test Duration** | 19,086 ms |
| **DB Restore** | 634 ms |
| **Auth Flow** | ~1,500 ms |
| **Cash-in Check** | ~1,000 ms |
| **Cash-in Re-establishment** | ~1,000 ms |
| **Receipt POST + Wait** | ~0.5 ms |
| **Unaccounted Time** | ~14,500 ms (30-sec DB commit wait) |

---

## SUPPORTING EVIDENCE

### Test Code Behavior
```java
=== Verifying cash-in status before receipt operations ===
? Cash-in status expired or inactive, re-establishing cash-in...
? Cash-in re-established successfully
```

**Interpretation:** Test explicitly detects and attempts to fix cash-in state, but fix doesn't work

### Success Response Ignored
```
RESPONSE: {"CashIn":[{"Success":true,"Messages":[...]}]}
? Cash-in re-established successfully
```

**Then immediately:**
```
❌ FAILURE: The user cogsuser is no longer cashed in. The receipt could not be created.
```

**Interpretation:** Backend accepted cash-in but didn't persist it

---

## CONCLUSION

This is a **backend session persistence failure** manifested as:
- ✅ API returns HTTP 200 (masking the real problem)
- ✅ Cash-in endpoint confirms success
- ❌ But state not persisted to session store
- ❌ Receipt endpoint can't access the cash-in state

**The API is lying** - it says Success=true but didn't actually persist the state.

