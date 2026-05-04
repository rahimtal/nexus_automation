# Failed Tests Summary - 55 Tests Requiring Investigation

## Test Execution Summary
- **Date**: May 4, 2026
- **Total Tests Run**: 265
- **Passed**: 197 (74%)
- **Failed**: 55 (21%)
- **Skipped**: 13 (5% - Known bugs)
- **Duration**: ~7:50 minutes

---

## Failed Tests by Category

### 1. Cashiering Controller - 7 Tests
**Location**: `Private_CashieringController_Test`
**Issue Pattern**: Receipt operations (POST/GET persistence)

```
- TC007_getAutoApply
- saveReciept_2_4
- saveReciept_4_prepaymentExistingCustomer
- saveReciept_4_prepaymentNewCustomer
- saveReciept_4_SOTaskCompleteDepositPayment
- saveReciept_4_SOTaskCompleteDepositPaymenttask2
- saveReciept_SOTaskCompleteDepositPaymenttaskNewCustomer
```

**Investigation**: POST returns success but subsequent GET shows old state. May need explicit confirmation/commit step.

---

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
