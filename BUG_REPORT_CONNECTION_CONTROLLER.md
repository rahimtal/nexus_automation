# Bug Report: Connection Controller Tests - SQL SET Options Issue

## Test Suite Summary
- **Module**: Private_connectionControllerV4_Test
- **Total Tests**: 9
- **Passed**: 1
- **Failed**: 7
- **Skipped**: 1
- **Execution Time**: ~80 seconds
- **Date**: 2026-05-04

## Root Cause
**Database Stored Procedure Configuration Issue** - All 7 failures are caused by the same backend SQL error:

```
INSERT failed because the following SET options have incorrect settings: 'ANSI_NULLS, QUOTED_IDENTIFIER'. 
Verify that SET options are correct for use with indexed views and/or indexes on computed columns 
and/or filtered indexes and/or query notifications and/or XML data type methods and/or spatial index operations.
```

This error occurs in the stored procedures when attempting CREATE/UPDATE/DELETE operations on connection-related tables.

## Failure Details

### 1. **postconnectionFlatv4** (Line 75)
- **Operation**: POST /api/v4/connection/flat
- **Status**: FAILED
- **Expected**: `{"Connection":{"Success":true,"Data":{"LocationId":"000000000523000","ConnectionSequence":7},"Messages":[{"Enabled":1,"Info":"Created","Level":1}]}}`
- **Actual**: `{"Connection":{"Success":false,"Data":null,"Messages":[{"Enabled":1,"Info":"csmApi_spConnectionFlatCreate - INSERT failed because the following SET options have incorrect settings: 'ANSI_NULLS, QUOTED_IDENTIFIER'...","Level":3}]}}`
- **Root Cause**: Stored procedure `csmApi_spConnectionFlatCreate` has SET options configuration issue

### 2. **postconnectionalternatev4** (Line 99)
- **Operation**: POST /api/v4/connection/alternate
- **Status**: FAILED
- **Expected**: `{"Connection":{"Success":true,"Data":{"LocationId":"AUTO1001","AlternateConnectionSequence":2},"Messages":[{"Enabled":1,"Info":"Created","Level":1}]}}`
- **Actual**: `Success=false` with SET options error in `csmApi_spConnectionAlternateCreate`
- **Root Cause**: Stored procedure `csmApi_spConnectionAlternateCreate` configuration issue

### 3. **postconnectionmeterv4** (Line 126)
- **Operation**: POST /api/v4/connection/meter
- **Status**: FAILED
- **Expected**: `{"Connection":{"Success":true,"Data":{"LocationId":"AUTO1001","ConnectionSequence":3},"Messages":[{"Enabled":1,"Info":"Created","Level":1}]}}`
- **Actual**: `Success=false` with SET options error in `csmApi_spConnectionMeterCreate`
- **Root Cause**: Stored procedure `csmApi_spConnectionMeterCreate` configuration issue

### 4. **putconnectionmeterv4** (Line 174)
- **Operation**: PUT /api/v4/connection/meter
- **Status**: FAILED
- **Expected**: Success with updated connection data
- **Actual**: `Success=false` with SET options error in `csmApi_spConnectionMeterUpdate_Regular`
- **Root Cause**: Stored procedure `csmApi_spConnectionMeterUpdate_Regular` configuration issue

### 5. **deleteconnectionmetergrpv4** (Line 186)
- **Operation**: DELETE /api/v4/connection/metergrp
- **Status**: FAILED
- **Expected**: `{"Connection":{"Success":true,"Data":null,"Messages":[{"Enabled":1,"Info":"Deleted","Level":1}]}}`
- **Actual**: `Success=true` but error message: SET options error in `csmApi_spConnectionMeterGroupDelete` (note: API returned Success=true but actual failure)
- **Root Cause**: Stored procedure `csmApi_spConnectionMeterGroupDelete` configuration issue

### 6. **postconnectionMeterInstallMeter** (Line 287)
- **Operation**: POST /api/v4/connection/meter (install meter)
- **Status**: FAILED
- **Expected**: `{"Connection":{"Success":true,"Data":{"LocationId":"LOCATION003","ConnectionSequence":2},"Messages":[{"Enabled":1,"Info":"Created","Level":1}]}}`
- **Actual**: `Success=false` with SET options error in `csmApi_spConnectionMeterCreate`
- **Root Cause**: Stored procedure `csmApi_spConnectionMeterCreate` configuration issue

### 7. **putconnectionmeterAlternatev4** (Line 229)
- **Operation**: PUT /api/v4/connection/meter/alternate
- **Status**: FAILED
- **Expected**: `{"Connection":{"Success":true,"Data":{"LocationId":"AUTO1001","AlternateConnectionSequence":2},"Messages":[{"Enabled":1,"Info":"Updated","Level":1}]}}`
- **Actual**: `{"Connection":{"Success":false,"Data":null,"Messages":[{"Enabled":1,"Info":"Alternate Connection 2 is not valid on location AUTO1001.","Level":3}]}}`
- **Root Cause**: Data validation error (different from others)

## Affected Stored Procedures
1. `csmApi_spConnectionFlatCreate` - Missing ANSI_NULLS, QUOTED_IDENTIFIER settings
2. `csmApi_spConnectionAlternateCreate` - Missing ANSI_NULLS, QUOTED_IDENTIFIER settings
3. `csmApi_spConnectionMeterCreate` - Missing ANSI_NULLS, QUOTED_IDENTIFIER settings (affects install meter)
4. `csmApi_spConnectionMeterUpdate_Regular` - Missing ANSI_NULLS, QUOTED_IDENTIFIER settings
5. `csmApi_spConnectionMeterGroupDelete` - Missing ANSI_NULLS, QUOTED_IDENTIFIER settings

## Required Fix
All stored procedures must be created/altered with the correct SET options:

```sql
SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
```

This should be applied before the CREATE PROCEDURE statement for each affected stored procedure.

## Priority
**HIGH** - Blocks all connection management operations (CREATE, UPDATE, DELETE)

## Impact
- Users cannot create new connections
- Users cannot update existing connections
- Users cannot delete connections
- All connection-related API endpoints fail

## Note
- Test `putconnectionmeterAlternatev4` shows a different error (data validation) - may need separate investigation
- 1 test skipped (status not captured in output)
