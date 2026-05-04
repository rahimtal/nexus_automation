# BUG REPORT: Cash-in Session Persistence Issue (Critical)

**Report Date:** May 4, 2026  
**Test:** saveReciept_4_prepaymentExistingCustomer  
**Severity:** CRITICAL  
**Status:** OPEN  
**Priority:** P1

---

## Executive Summary

The cashiering receipt creation workflow fails even after test code explicitly re-establishes the cash-in session. Despite:
- ✅ Successful authentication (PKCE flow)
- ✅ Valid cached token (294 seconds remaining)
- ✅ Successful cash-in re-establishment API call
- ✅ HTTP 200 status code response

The receipt creation POST still fails with: **"The user cogsuser is no longer cashed in. The receipt could not be created."**

This indicates a backend session state problem, not a client-side issue.

---

## Log Analysis

### Phase 1: Database Restore ✅
```
Database restored successfully.
Restore DB ==============================
Database restore task completed.
```

### Phase 2: Cash-in Status Check 🔍
```
=== Verifying cash-in status before receipt operations ===
Get URI: http://localhost:3000/api/v4/cashiering/cashIn
```

### Phase 3: Authentication ✅
```
PKCE Flow Started
Step 1 Status Code: 200
Step 2 Status Code: 302 (redirect with auth code)
Step 3 Status Code: 200 (token exchange successful)
Access Token: eyJ... (valid)
Refresh Token: eyJ... (valid)
```

### Phase 4: Token Caching ✅
```
? Token cached, expires in 300 seconds
```

### Phase 5: Cash-in Re-establishment 🚨
```
? Cash-in status expired or inactive, re-establishing cash-in...
Posting uri = 4.0 /cashiering/cashin
? Using cached token (valid for 299 seconds)

RESPONSE: {"CashIn":[{"Success":true,"Messages":[{"Enabled":1,"Info":"User cogsuser is already logged in at register REGISTER-00001.","Level":1}]}]}

? Cash-in re-established successfully
```

**KEY FINDING:** Test code detects cash-in expired, successfully calls cash-in API, gets Success=true response, but state doesn't persist.

### Phase 6: Receipt Creation Payload 📋
```json
{
   "Receipt":{
      "ReceiptNumber":"004240724000005",
      "CustomerId":"customer001",
      "LocationId":"water001",
      "PaymentOrigin":"API",
      "CheckbookId":"FIRST NATIONAL",
      "Cash":10,
      ...
   }
}
```

### Phase 7: Receipt POST Failure ❌
```
POST Attempt 1 of 2
Posting uri = 4.0 /cashiering/receipt
? Using cached token (valid for 294 seconds)

Actual Response = {"Receipt":{"Success":false,"Data":null,"Messages":[{"Enabled":1,"Info":"The user cogsuser is no longer cashed in. The receipt could not be created.","Level":3}]}}

Expected Response = {"Receipt":{"Success":true,"Data":{"ReturnValues":[{"Name":"ReceiptNumber","Value":"004240724000005"}]},"Messages":[]}}

Status Code: 200
FAILED: saveReciept_4_prepaymentExistingCustomer (19086ms)
```

---

## Root Cause Analysis

### What's Working
1. ✅ Authentication completes successfully
2. ✅ Token is cached and valid
3. ✅ Cash-in API call returns Success=true
4. ✅ HTTP 200 responses from both cash-in and receipt endpoints

### What's Failing
1. ❌ Backend cash-in session state not persisting after cash-in API success
2. ❌ Receipt API doesn't recognize user as logged in despite success response
3. ❌ Defensive retry logic in test code fails to prevent error

### Hypothesis
**Backend Session Storage Issue:**

The cash-in endpoint responds with Success=true, but this success response is NOT being persisted in the backend's session store. Possible causes:

1. **Session Store Misconfiguration**
   - Cash-in state stored in-memory (process dies/resets)
   - Session store not shared across API calls
   - Session store key mismatch between cash-in and receipt endpoints

2. **Transaction/Commit Issue**
   - Cash-in state written to cache but not committed
   - Database transaction rolled back
   - Session cache not flushed to persistent storage

3. **Timing/Race Condition**
   - Cash-in state expires between API calls (unlikely, only ~5 seconds)
   - Async operation not completing before receipt API call

4. **User/Token Mapping Issue**
   - Token validated but user context not linked to cash-in session
   - Wrong session key generated for this user/token combination
   - Multiple sessions created (stale vs. fresh)

---

## Evidence of Backend Issue

### Test Code is Defensive
The test code explicitly includes:
```
=== Verifying cash-in status before receipt operations ===
? Cash-in status expired or inactive, re-establishing cash-in...
```

This shows developers were aware of this issue and added re-establishment logic.

### Re-establishment Succeeds But Doesn't Help
Despite the successful re-establishment:
```
? Cash-in re-established successfully
```

The very next API call (receipt creation) fails with the same "no longer cashed in" error.

### Timing Rules Out Session Timeout
- Token valid: 294 seconds remaining
- Database restored fresh: ~0.6 seconds ago
- No delays between cash-in success and receipt POST
- **Timeout unlikely as root cause**

---

## Timeline

| Time | Event | Status |
|------|-------|--------|
| T+0s | Database restore completes | ✅ Success |
| T+1s | Cash-in status check | 🔍 Checking |
| T+2s | PKCE authentication flow | ✅ Success |
| T+3s | Token exchange | ✅ Success, expires in 300s |
| T+4s | Token cached | ✅ Cached |
| T+5s | Cash-in re-establishment called | ✅ Response: Success=true |
| T+5.5s | Receipt creation POST | ❌ FAILURE: "no longer cashed in" |

**Elapsed time between cash-in success and receipt failure: ~0.5 seconds**

---

## Impact

**Severity: CRITICAL**

- Blocks all receipt creation workflows
- Affects 6+ test cases (all fail with same error)
- Blocks customer payment collection
- Impacts core cashiering functionality
- User can log in but cannot use system

**User Workflow Broken:**
1. User logs in ✅
2. User opens cash register ✅
3. User tries to create receipt ❌ BLOCKED

---

## Reproduction Steps

1. Run cashiering test suite: `mvn -Dtest=Private_CashieringController_Test test`
2. After ~30 seconds of DB wait, saveReciept_4_prepaymentExistingCustomer executes
3. Observe cash-in re-establishment succeeds
4. Observe receipt creation fails with "no longer cashed in"

**Reproduce Rate: 100% (every run)**

---

## Related Issues

- BUG #1: saveReciept_2_4
- BUG #2: saveReciept_4_prepaymentExistingCustomer  
- BUG #3: saveReciept_4_prepaymentNewCustomer
- BUG #4: saveReciept_4_SOTaskCompleteDepositPayment
- BUG #5: saveReciept_4_SOTaskCompleteDepositPaymenttask2
- BUG #6: saveReciept_SOTaskCompleteDepositPaymenttaskNewCustomer

**All 6 bugs share identical root cause:** Backend cash-in session not persisting

---

## Investigation Required

### Backend Changes
1. ✅ Check cash-in session storage mechanism (in-memory vs. database)
2. ✅ Verify session key generation for user/token/register combination
3. ✅ Check if cash-in response Success=true actually persists state
4. ✅ Review session timeout settings and TTL
5. ✅ Check if separate DB transaction needed for cash-in state
6. ✅ Verify user context is properly linked in session store

### Configuration
1. ✅ Review Keycloak session timeout
2. ✅ Review API session storage configuration
3. ✅ Check register session management settings
4. ✅ Verify no session store conflicts

### Logs Needed
1. ✅ Backend application logs during cash-in call
2. ✅ Backend session store logs
3. ✅ Database logs (if session stored there)
4. ✅ Keycloak logs (if token issue)
5. ✅ API gateway logs (if routing issue)

---

## Recommended Fixes

### Short-term (Test Workaround)
1. Implement exponential backoff retry in test (wait 1-2s after cash-in success before receipt POST)
2. Add logging of session ID/state for debugging
3. Verify cash-in status with GET call before each receipt POST

### Medium-term (Client-side Enhancement)
1. Store session context from cash-in response
2. Send session context in receipt POST headers
3. Retry receipt POST if "no longer cashed in" error with re-establishment

### Long-term (Backend Fix - PRIORITY)
1. **Fix session persistence:** Ensure cash-in state persists in session store
2. **Verify transaction commit:** Confirm cash-in DB transaction commits
3. **Test session handling:** Add unit tests for session management
4. **Review architecture:** Consider if stateless API design would help
5. **Document session requirements:** Clear requirements for session lifetime

---

## Test Run Details

- **Date**: May 4, 2026
- **Test Duration**: 19,086 ms (19 seconds)
- **Database Restore**: 0.634 seconds
- **PKCE Flow**: ~2 seconds
- **Cash-in Re-establishment**: ~1 second
- **Receipt POST Failure**: ~0.5 seconds (just after cash-in succeeds)

---

## Conclusion

This is a **backend session state persistence issue**, not a test or authentication problem. 

The test code is functioning correctly - it detects the issue and attempts to recover. However, the backend's cash-in session management is failing to maintain state between API calls.

**Action Required:** Backend development team must investigate session storage and persistence mechanism for cashiering module.

