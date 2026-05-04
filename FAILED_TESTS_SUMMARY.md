# Failed Tests Detailed Summary

**Report Date:** May 4, 2026  
**Status:** Execution phase complete - 8 tests analyzed in detail (Cashiering + Collection)

---

## Executive Summary

| Category | Count | Status |
|----------|-------|--------|
| **Total Failed Tests Analyzed** | 8 | In Detail |
| **Cashiering Failures** | 6 | Session persistence issue |
| **Collection Failures** | 2 | Data retrieval issue |
| **Root Causes Identified** | 2 | Distinct patterns |
| **Severity Level** | All HIGH | Blocking production transactions |

---

# CASHIERING CONTROLLER - 6 FAILURES

## Common Root Cause: User Cash-in Session Not Persisting

**Error Pattern:** `"The user cogsuser is no longer cashed in. The receipt could not be created."`  
**HTTP Status:** 200 OK (masks the actual failure)  
**API Response:** `Success=false` in JSON payload  
**Timeline:** Cash-in succeeds, then 0.5 seconds later session is lost  
**Impact:** ALL receipt creation blocked after cash-in

---

## CASHIERING FAILURES - DETAILED

### ❌ Failure 1: saveReciept_2_4 (Line 429)
**Test Class:** Private_CashieringController_Test  
**Error:** `expected [true] but found [false]`  
**API Endpoint:** POST /api/v4/cashiering/receipt  
**Message:** "The user cogsuser is no longer cashed in. The receipt could not be created."  
**Time Since Cash-in:** ~0.5 seconds  
**Token Status:** Valid (300s TTL)  
**Impact:** Cannot create receipt 

---

### ❌ Failure 2: saveReciept_4_prepaymentExistingCustomer (Line 497)
**Test Class:** Private_CashieringController_Test  
**Customer Type:** Existing  
**Transaction Type:** Prepayment  
**Expected Receipt Number:** 004240724000005  
**Actual Response:** Success=false  
**Message:** "The user cogsuser is no longer cashed in. The receipt could not be created."  
**Root Cause:** Session lost after cash-in  

---

### ❌ Failure 3: saveReciept_4_prepaymentNewCustomer (Line 526)
**Test Class:** Private_CashieringController_Test  
**Customer Type:** New  
**Transaction Type:** Prepayment  
**Error Pattern:** Identical to Failure 2  
**Message:** "The user cogsuser is no longer cashed in. The receipt could not be created."  

---

### ❌ Failure 4: saveReciept_4_SOTaskCompleteDepositPayment (Line 555)
**Test Class:** Private_CashieringController_Test  
**Receipt Type:** Service Order Task Complete  
**Payment Type:** Deposit  
**Error Pattern:** Identical session loss  
**Message:** "The user cogsuser is no longer cashed in. The receipt could not be created."  

---

### ❌ Failure 5: saveReciept_4_SOTaskCompleteDepositPaymenttask2 (Line 584)
**Test Class:** Private_CashieringController_Test  
**Receipt Type:** Service Order Task Complete (Task 2)  
**Payment Type:** Deposit  
**Error Pattern:** Identical session loss  
**Message:** "The user cogsuser is no longer cashed in. The receipt could not be created."  

---

### ❌ Failure 6: saveReciept_SOTaskCompleteDepositPaymenttaskNewCustomer (Line 613)
**Test Class:** Private_CashieringController_Test  
**Customer Type:** New  
**Receipt Type:** Service Order Task Complete  
**Payment Type:** Deposit  
**Error Pattern:** Identical session loss  
**Message:** "The user cogsuser is no longer cashed in. The receipt could not be created."  

---

## CASHIERING - ROOT CAUSE ANALYSIS

**Test Flow:**
```
1. TC001_1_Cashin → ✅ POST /api/v4/cashiering/cashin
   Response: Success=true, HTTP 200
   Token expires in: 300 seconds
   User: cogsuser
   
2. [0.5 second delay]

3. saveReciept_* → ❌ POST /api/v4/cashiering/receipt
   Response: Success=false, HTTP 200 ← PROBLEM: HTTP 200 masks failure
   Message: "user no longer cashed in"
   Token status: Still valid
```

**Critical Issues:**
1. ❌ Backend session state not persisting between API calls
2. ❌ HTTP 200 returned even when operation fails (hides errors)
3. ❌ Cash-in state expires or cleared during 0.5s window
4. ⚠️ Test workaround re-establishes cash-in but still fails receipt POST

**Backend Investigation Required:**
- [ ] Verify cash-in state stored in session cache (Redis/memory)
- [ ] Check session TTL vs token TTL coordination
- [ ] Review SQL procedures for cash-in state persistence
- [ ] Trace execution flow: is state saved to DB?
- [ ] Add logging to track cash-in state lifecycle

---

# COLLECTION CONTROLLER - 2 FAILURES

## Common Root Cause: Data Query Returning Empty Results

**Issue Pattern:** Successful API calls but no data returned  
**HTTP Status:** 200 OK  
**Database:** Fresh restore before test (test data present)  
**Impact:** Collection reports and queries blocked

---

### ❌ Failure 7: getcollectioncriteriav4 (Line 190)
**Test Class:** Private_CollectionControllerv4_Test  
**Error:** `expected [true] but found [false]`  
**API Endpoint:** GET /api/v4/collection/criteria  
**Duration:** 31ms  
**Test Result:** Boolean validation failed  
**Root Cause:** Unknown - requires code inspection  

---

### ❌ Failure 8: getcollectionv4 (Line 47)
**Test Class:** Private_CollectionControllerv4_Test  
**API Endpoint:** GET /api/v4/collection/query  
**Expected Records:** 16+ notice displays + 40+ notices  
**Actual Records:** 0 (empty arrays)  

**Expected Data Sample:**
```json
{
  "NoticesDisplay": [
    {
      "CustomerId": "03332301204",
      "LocationId": "SPALOCATION1",
      "CustomerName": "Talha Rahim",
      "ServiceAddress": "BSMT 8000 8000, Cartersville"
    },
    ... [15 more] ...
  ]
}
```

**Actual Data:**
```json
{
  "NoticesDisplay": [],
  "Notices": []
}
```

**Root Causes to Investigate:**
- [ ] Database restore missing collection notice test data
- [ ] Query filter parameters too restrictive
- [ ] Collection notices marked as inactive
- [ ] Query not executing or returning wrong result set

---

## COLLECTION RESULTS SUMMARY
**Total Collection Tests:** 10  
**Passed:** 8 ✅  
**Failed:** 2 ❌  
**Pass Rate:** 80%  
**Duration:** 39.3 seconds  

### 2. Collection Controller - 7 Tests
**Location**: `Private_CollectionControllerv4_Test`
**Issue Pattern**: Collection operations failing

```
- getcollectionv4
- postcollectionprocessv4
- postcollectionprintv4
- getcollectionMessagesv4
- confirmNoticev4
- getcollectionNoticeTypev4
- getcollectioncriteriav4
```

---

### 3. Connection Controller - 7 Tests
**Location**: `Private_connectionControllerV4_Test`
**Issue Pattern**: Connection setup operations failing

```
- postconnectionFlatv4
- postconnectionalternatev4
- postconnectionmeterv4
- putconnectionmeterv4
- deleteconnectionmetergrpv4
- putconnectionmeterAlternatev4
- postconnectionMeterInstallMeter
```

---

### 4. Rate Controller - 9 Tests
**Location**: `Private_rateController_Test`
**Issue Pattern**: Schema changes, error message format, case sensitivity

```
- getrateID              (Schema: Extra "RateInUse" field)
- getrateID2            (Schema: Extra "RateInUse" field)
- getrateID3            (Schema: Extra "RateInUse" field)
- getrateID4            (Schema: Extra "RateInUse" field)
- postCreateRate        (Unknown)
- deleteRate            (Unknown)
- getrateId             (Schema: Extra "RateInUse" field)
- getrateIdNegative     (Error message format changed)
- getrateIserviceTypeSetupSteamNegative (Case sensitivity: "steam" vs "STEAM")
```

---

### 5. Service Order Controller - 2 Tests
**Location**: `Private_ServiceOrderController_Test`

```
- delBatv4
- delServiceOrderv4
```

---

### 6. Customer Controller - 1 Test
**Location**: `Private_customerControllerV4_Test`

```
- putpreauthorizedPaymentPlanv4
```

---

### 7. Drillback - 1 Test
**Location**: `Private_Drillback_Test`

```
- getdrillbackv4
```

---

### 8. Lookup Controller - 5 Tests
**Location**: `Private_lookupControllerv4_Test`

```
- getapplyByService_Paymentsv4
- lookupequipmentClassv4
- lookupserviceOrderTask
- lookupPaymentDocuments
- lookuprateFilterVisible
```

---

### 9. Miscellaneous Charge - 1 Test
**Location**: `Private_MiscellaneousChargeControllerV4_Test`

```
- miscellaneousChargepostv4
```

---

### 10. Move In/Move Out - 1 Test
**Location**: `Private_moveInMoveOutController_Test`

```
- postTransferv4
```

---

### 11. Payment Controller - 3 Tests
**Location**: `Private_PaymentControllerV4_Test`

```
- postPaymentSimulatev4
- postPaymentSimulateExt
- postPaymentSimulatev4_savePayments
```

---

### 12. Penalty Controller - 5 Tests
**Location**: `Private_penaltyController_Test`

```
- postpenaltyDocuments_v_4
- getcalculatedDocuments_v4
- postpenaltyDocumentsRoute_v_4
- postpenaltyDocumentsZones_v_4
- getpenaltyHeader_v_4
```

---

### 13. Transactions & Validation - 4 Tests
**Location**: `Private_transactionsControllerv4_Test`, `Private_SmartyStreetValidationV4_Test`

```
- gettransactionpayment_v4_AuthCode
- postvalidateAddresses
- postvalidLocation
- putvalidLocation
```

---

### 14. Void Controller - 2 Tests
**Location**: `Private_voidController_Test`

```
- putvoidv4                  (SQL Server ANSI_NULLS issue)
- getlinkedDocumentv4
```

---

## Critical Issues Identified

### Issue #1: POST/GET Data Persistence (HIGH PRIORITY)
**Affects**: 20+ tests (Cashiering, Collections, Connections, Payment, etc.)
**Problem**: POST operations return success but subsequent GET requests show old/missing data
**Impact**: Cascading failures across multiple controllers
**Root Cause**: Likely transaction handling or asynchronous processing without proper commit

### Issue #2: API Response Schema Evolution (HIGH PRIORITY)
**Affects**: 5+ tests (Rate Controller)
**Problem**: API responses now include additional fields (e.g., `RateInUse`)
**Impact**: Exact JSON matching assertions fail
**Solution**: Update test data files or implement pattern-based assertions

### Issue #3: Error Message Format Changes (MEDIUM PRIORITY)
**Affects**: 2-3 tests
**Problem**: Backend error messages have changed format/content
**Impact**: Error validation tests fail
**Solution**: Update expected error messages in test cases

### Issue #4: Case Sensitivity in Error Messages (LOW PRIORITY)
**Affects**: 1-2 tests
**Problem**: "steam" vs "STEAM" case difference
**Impact**: Assertion mismatch
**Solution**: Case-insensitive string comparison or update expected values

### Issue #5: SQL Server Configuration (LOW PRIORITY)
**Affects**: 1-2 tests
**Problem**: ANSI_NULLS/QUOTED_IDENTIFIER not configured
**Impact**: INSERT operations fail
**Solution**: Server-side DBA configuration

---

## How to Run Failed Tests

### Option 1: Batch File
```batch
run_failed_tests_55.bat
```

### Option 2: PowerShell
```powershell
.\run_failed_tests_55.ps1
```

### Option 3: Maven Command Line
```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng-failed-55.xml
```

---

## Investigation Recommendations

1. **Start with POST/GET Persistence Issue** - This affects the most tests and is likely the root cause of many failures
2. **Review API Response Changes** - Update test data to match new schema
3. **Verify Error Messages** - Check if backend error handling changed
4. **Database Configuration** - Confirm ANSI_NULLS settings

---

## Files Created This Session

- ✅ `testng-failed-55.xml` - TestNG suite file with 55 failed tests
- ✅ `run_failed_tests_55.bat` - Batch script to run failed tests
- ✅ `run_failed_tests_55.ps1` - PowerShell script to run failed tests
- ✅ `FAILED_TESTS_REPORT.md` - Detailed failure analysis
- ✅ `FAILED_TESTS_SUMMARY.md` - This file

---

## Next Session Tasks

1. Run `testng-failed-55.xml` to confirm all tests execute
2. Investigate POST/GET persistence issue (highest priority)
3. Update Rate Controller test data with schema changes
4. Create flexible assertion framework
5. Fix error message format issues

---

Generated: May 4, 2026
Total Failed Tests: 55
Total Test Classes Affected: 15
