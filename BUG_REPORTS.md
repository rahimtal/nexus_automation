# Bug Reports - Cashiering Controller Test Failures

**Test Run Date:** May 4, 2026  
**Test Class:** Private_CashieringController_Test  
**Total Failures:** 6  
**Common Issue:** Session/Cash-in state not persisting between tests

---

## BUG #1: saveReciept_2_4 Failed
**Severity:** HIGH  
**Status:** OPEN  
**Test Line:** 429

### Error Message
```
expected [true] but found [false]
```

### Full Error Details
```
java.lang.AssertionError: expected [true] but found [false]
at org.testng.Assert.fail(Assert.java:111)
at org.testng.Assert.failNotEquals(Assert.java:1590)
at org.testng.Assert.assertEqualsImpl(Assert.java:150)
at org.testng.Assert.assertEquals(Assert.java:132)
at org.testng.Assert.assertEquals(Assert.java:656)
at org.testng.Assert.assertEquals(Assert.java:666)
at com.NexustAPIAutomation.java.CommonMethods.postMethodString(CommonMethods.java:568)
at com.NexusAPI.Tests.Private_CashieringController_Test.saveReciept_2_4(Private_CashieringController_Test.java:429)
```

### Root Cause Analysis
Receipt creation POST request returning `Success=false`. Backend message indicates: **"The user cogsuser is no longer cashed in. The receipt could not be created."**

### Expected Behavior
After successful TC001_1_Cashin login, subsequent receipt tests should be able to create receipts with active cash-in session.

### Actual Behavior
Cash-in session is lost or expired before saveReciept_2_4 executes. Likely causes:
- Session timeout (30+ seconds wait between tests)
- Backend session not being maintained across API calls
- Test isolation issue - each test loses the authenticated state

### Steps to Reproduce
1. Run Private_CashieringController_Test full class
2. TC001_1_Cashin succeeds and logs in user
3. Wait ~30 seconds for DB transaction
4. Execute saveReciept_2_4 test
5. See receipt creation fail with "no longer cashed in" message

### Files Affected
- [Private_CashieringController_Test.java](Private_CashieringController_Test.java#L429) - Line 429

### Investigation Needed
1. Check if cash-in session should persist across tests
2. Verify token/session management between test methods
3. Check if TC001_1_Cashin needs to be called before each receipt test
4. Review API session timeout settings

---

## BUG #2: saveReciept_4_prepaymentExistingCustomer Failed
**Severity:** HIGH  
**Status:** OPEN  
**Test Line:** 497

### Error Message
```
expected [{"Receipt":{"Success":true,"Data":{"ReturnValues":[{"Name":"ReceiptNumber","Value":"004240724000005"}]},"Messages":[]}}] 
but found 
[{"Receipt":{"Success":false,"Data":null,"Messages":[{"Enabled":1,"Info":"The user cogsuser is no longer cashed in. The receipt could not be created.","Level":3}]}}]
```

### Root Cause Analysis
Same as BUG #1: User session has been lost. Cash-in state expired between TC001_1_Cashin and this test.

### Expected Receipt Number
004240724000005

### Actual Response
- Success: false
- Data: null
- Message: "The user cogsuser is no longer cashed in. The receipt could not be created."

### Impact
Cannot create prepayment receipt for existing customer - blocks customer transaction workflow.

### Files Affected
- [Private_CashieringController_Test.java](Private_CashieringController_Test.java#L497) - Line 497

### Related Issues
- BUG #1 (same root cause)
- BUG #3 (same root cause)
- BUG #4 (same root cause)
- BUG #5 (same root cause)
- BUG #6 (same root cause)

---

## BUG #3: saveReciept_4_prepaymentNewCustomer Failed
**Severity:** HIGH  
**Status:** OPEN  
**Test Line:** 526

### Error Message
```
expected [{"Receipt":{"Success":true,"Data":{"ReturnValues":[{"Name":"ReceiptNumber","Value":"004240724000009"}]},"Messages":[]}}] 
but found 
[{"Receipt":{"Success":false,"Data":null,"Messages":[{"Enabled":1,"Info":"The user cogsuser is no longer cashed in. The receipt could not be created.","Level":3}]}}]
```

### Root Cause Analysis
User session/cash-in state lost between tests.

### Expected Receipt Number
004240724000009

### Actual Response
- Success: false
- Data: null
- Message: "The user cogsuser is no longer cashed in. The receipt could not be created."

### Impact
Cannot create prepayment receipt for new customer - blocks new customer onboarding workflow.

### Files Affected
- [Private_CashieringController_Test.java](Private_CashieringController_Test.java#L526) - Line 526

---

## BUG #4: saveReciept_4_SOTaskCompleteDepositPayment Failed
**Severity:** HIGH  
**Status:** OPEN  
**Test Line:** 555

### Error Message
```
expected [{"Receipt":{"Success":true,"Data":{"ReturnValues":[{"Name":"ReceiptNumber","Value":"004240805000004"}]},"Messages":[]}}] 
but found 
[{"Receipt":{"Success":false,"Data":null,"Messages":[{"Enabled":1,"Info":"The user cogsuser is no longer cashed in. The receipt could not be created.","Level":3}]}}]
```

### Root Cause Analysis
User session/cash-in state lost between tests.

### Expected Receipt Number
004240805000004

### Actual Response
- Success: false
- Data: null
- Message: "The user cogsuser is no longer cashed in. The receipt could not be created."

### Impact
Cannot create deposit payment receipt for Service Order task - blocks deposit collection workflow.

### Files Affected
- [Private_CashieringController_Test.java](Private_CashieringController_Test.java#L555) - Line 555

---

## BUG #5: saveReciept_4_SOTaskCompleteDepositPaymenttask2 Failed
**Severity:** HIGH  
**Status:** OPEN  
**Test Line:** 584

### Error Message
```
expected [{"Receipt":{"Success":true,"Data":{"ReturnValues":[{"Name":"ReceiptNumber","Value":"004240805000013"}]},"Messages":[]}}] 
but found 
[{"Receipt":{"Success":false,"Data":null,"Messages":[{"Enabled":1,"Info":"The user cogsuser is no longer cashed in. The receipt could not be created.","Level":3}]}}]
```

### Root Cause Analysis
User session/cash-in state lost between tests.

### Expected Receipt Number
004240805000013

### Actual Response
- Success: false
- Data: null
- Message: "The user cogsuser is no longer cashed in. The receipt could not be created."

### Impact
Cannot create second deposit payment receipt for Service Order task - blocks multi-deposit workflows.

### Files Affected
- [Private_CashieringController_Test.java](Private_CashieringController_Test.java#L584) - Line 584

---

## BUG #6: saveReciept_SOTaskCompleteDepositPaymenttaskNewCustomer Failed
**Severity:** HIGH  
**Status:** OPEN  
**Test Line:** 613

### Error Message
```
expected [{"Receipt":{"Success":true,"Data":{"ReturnValues":[{"Name":"ReceiptNumber","Value":"004240805000008"}]},"Messages":[]}}] 
but found 
[{"Receipt":{"Success":false,"Data":null,"Messages":[{"Enabled":1,"Info":"The user cogsuser is no longer cashed in. The receipt could not be created.","Level":3}]}}]
```

### Root Cause Analysis
User session/cash-in state lost between tests.

### Expected Receipt Number
004240805000008

### Actual Response
- Success: false
- Data: null
- Message: "The user cogsuser is no longer cashed in. The receipt could not be created."

### Impact
Cannot create deposit payment receipt for new customer Service Order - blocks new customer deposit collection.

### Files Affected
- [Private_CashieringController_Test.java](Private_CashieringController_Test.java#L613) - Line 613

---

## Common Root Cause Analysis

### Problem
All 6 receipt creation tests fail with identical message:
```
"The user cogsuser is no longer cashed in. The receipt could not be created."
```

### Timeline
1. ✅ TC001_1_Cashin - Succeeds (user logs in and opens cash register)
2. ⏱️ 30-second wait for DB transaction commit
3. ❌ saveReciept_* tests - Fail (user session lost)

### Hypothesis
**Session state is not persisting from TC001_1_Cashin to subsequent receipt tests.**

Possible causes:
1. **Session Timeout**: 30-second wait exceeds session timeout
2. **Token Expiration**: JWT/auth token expires during wait
3. **Backend State Not Persisted**: Cash-in state stored in memory, not database
4. **Test Isolation Issue**: Each test gets new/clean session instead of shared session
5. **API Design Issue**: Cash-in session requires re-authentication per API call

### Evidence
- Test TC001_1_Cashin returns: `"Success":true,"Messages":[{"Info":"User cogsuser is already logged in..."}]`
- Test saveReciept_* returns: `"Success":false,...Messages":[{"Info":"The user cogsuser is no longer cashed in..."}]`
- Time gap: ~30 seconds between successful login and failed receipt creation

---

## Recommended Solutions

### Short-term (Test-level fixes)
1. **Call TC001_1_Cashin before each receipt test** - Ensure user is logged in
2. **Reduce DB transaction wait time** - Maybe 5 seconds instead of 30?
3. **Call login API between tests** - Re-authenticate if session is lost

### Medium-term (Code-level fixes)
1. Review cashiering session management logic
2. Check if cash-in state should be persisted to database
3. Implement session refresh mechanism
4. Store session info (token) between test methods

### Long-term (Architecture review)
1. Evaluate if stateless API design is more appropriate
2. Consider token-based authentication refresh
3. Review session timeout settings
4. Document expected session behavior

---

## Test Run Summary
- **Date**: May 4, 2026 @ 17:28:25
- **Duration**: 2:30 minutes
- **Tests Run**: 16
- **Passed**: 10
- **Failed**: 6 (all same root cause)
- **Skipped**: 1

---

## Next Steps
1. ✅ Identified all 6 failures (completed)
2. ⏳ Determine if each receipt test needs separate login
3. ⏳ Check if cash-in session should persist between tests
4. ⏳ Implement fix (likely adding login before each receipt test)
5. ⏳ Re-run tests to verify fix

