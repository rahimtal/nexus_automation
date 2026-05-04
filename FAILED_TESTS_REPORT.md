# Failed Tests Report - May 4, 2026

## Executive Summary
- **Total Tests**: 265
- **Passing**: 210 (79%)
- **Failing**: 42 (16%)
- **Skipped**: 13 (5% - Known bugs)
- **Improvement**: 4 fewer failures than previous run (46 → 42)

---

## Failure Categories

### 1. API Response Schema Changes (6-7 tests)
**Pattern**: Tests have exact JSON matching but API now returns additional fields

**Example - RateInUse Field:**
```
Expected: No "RateInUse" field
Actual:   "RateInUse":true (now included in responses)
```

**Affected Tests:**
- `Private_rateController_Test.getrateID3()`
- `Private_rateController_Test.getrateID4()`
- `Private_rateController_Test.getrateId()`

**Fix Strategy**: Update test data files to include new fields OR change assertions to use pattern matching instead of exact JSON comparison

---

### 2. Error Message Format Changes (3-4 tests)
**Pattern**: Backend error messages changed format

**Example:**
```
Expected: "Error in stored procedure csmApi_spRateGet. Missing message with Id = 90996"
Actual:   "Rate (EMP-1) with Effective Date (2001-01-01) not found."
```

**Affected Tests:**
- `Private_rateController_Test.getrateIdNegative()`

**Fix Strategy**: Update expected error messages to match current backend responses

---

### 3. Case Sensitivity Issues (1-2 tests)
**Pattern**: Error messages differ only in case

**Example:**
```
Expected: "Invalid Service Type steam."
Actual:   "Invalid Service Type STEAM."
```

**Affected Tests:**
- `Private_rateController_Test.getrateIserviceTypeSetupSteamNegative()`

**Fix Strategy**: Update test assertions to handle case-insensitive matching

---

### 4. Test Data Mismatch (2-3 tests)
**Pattern**: Database state differs from test expectations

**Example - Transaction Batch:**
```
Expected: BatchTotal=92.00, NumberOfTransactions=1
Actual:   BatchTotal=20.00, NumberOfTransactions=2
```

**Affected Tests:**
- `Private_transactionsControllerv4_Test.gettransactionbatch_v4()`
- `Private_transactionsControllerv4_Test.gettransactionpayment_v4_AuthCode()` (invalid document)

**Fix Strategy**: Either update test data expectations or verify database contains correct test data

---

### 5. SQL Server Configuration Issues (1-2 tests)
**Pattern**: ANSI_NULLS/QUOTED_IDENTIFIER SET options not configured correctly

**Error:**
```
"INSERT failed because the following SET options have incorrect settings: 
'ANSI_NULLS, QUOTED_IDENTIFIER'"
```

**Affected Tests:**
- `Private_voidController_Test.putvoidv4()`

**Fix Strategy**: Server-side configuration fix needed (requires DBA access) OR implement client-side workaround

---

### 6. Authentication Issues (2-3 tests)
**Pattern**: 401 Unauthorized responses

**Affected Tests:**
- `Private_rateController_Test.putUpdateRatev4()`

**Fix Strategy**: Verify test credentials and authentication token refresh

---

## Recent Improvements (This Session)

✅ **Pattern Matching Fix** - `Public_Test_ServiceOrderControllerV4.getServiceOrderdetails_v4()`
- Replaced exact JSON matching with flexible pattern assertions
- Now accommodates hostname differences between environments

✅ **Null Pointer Protection** - `Public_Test_createandcancelSpaV3.cancelSPA_v_3()`
- Added null check before calling booleanValue()
- Prevents NullPointerException when result is null

**Result**: 4 fewer failing tests (46 → 42)

---

## Next Steps (Priority Order)

### High Priority (Quick Wins)
1. **Update Rate Test Data** - Add `RateInUse` field to expected responses (5 tests)
2. **Update Error Messages** - Match new backend error message format (2-3 tests)
3. **Case-Insensitive Matching** - Update test assertions (1-2 tests)

### Medium Priority
4. **Verify Test Data** - Ensure database contains expected test data (2-3 tests)
5. **Authentication Fix** - Verify test credentials (2-3 tests)

### Low Priority (Server-Side)
6. **SQL Server Configuration** - ANSI_NULLS/QUOTED_IDENTIFIER (requires DBA)

---

## Recommendations

1. **Implement Flexible Assertions**: Move away from exact JSON comparison for API responses
   - Use pattern matching for structural validation
   - Extract and validate key fields separately
   - This handles API evolution better

2. **Maintain Test Data Separately**: 
   - Keep expected data in separate configuration files
   - Update easily when API changes
   - Version control test expectations

3. **Add Response Validation Framework**:
   - Create reusable assertion helpers
   - Handle schema variations automatically
   - Reduce test maintenance overhead

---

## Files for Investigation
- Test output: `target/test-output/testng-results.xml`
- Full logs: Check individual test class output files
- Rate test data: `TestData/getrate*.json`
