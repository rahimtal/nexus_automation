-- Investigation: Why is PNLT00000000063 penalty not being created?

-- Step 1: Check if PNLT00000000063 was posted (should be in MiscDocument)
PRINT '=== STEP 1: Check if PNLT00000000063 misc document was posted ==='
SELECT DocumentNumber, CustomerId, LocationId, Amount, DocumentStatus, CreatedDate 
FROM Two.dbo.MiscDocument 
WHERE DocumentNumber = 'PNLT00000000063'

-- Step 2: Check if BUDG00000002775 exists
PRINT '=== STEP 2: Check if BUDG00000002775 document exists ==='
SELECT DocumentNumber, CustomerId, LocationId, Amount, DocumentStatus, CreatedDate 
FROM Two.dbo.BudgetDoc 
WHERE DocumentNumber = 'BUDG00000002775'

-- Step 3: Check penalty preparation history for PENALTY2 batch
PRINT '=== STEP 3: Penalty Preparation History for PENALTY2 batch ==='
SELECT [umDocumentNumber] as SourceDoc, [umDocumentNumberSecond] as PenaltyDoc, 
       [umUnappliedAmount], [umAmount], [umPenaltyID], [Number_Of_Days], [umServiceType]
FROM Two.dbo.csmApi_PenaltyPreparationHistory 
WHERE BatchId = 'PENALTY2'

-- Step 4: Check calculated penalty documents
PRINT '=== STEP 4: Calculated Penalty Documents in PENALTY2 batch ==='
SELECT [PenaltyDocumentNumber], [SourceDocumentNumber], [PenaltyId], [PenaltyAmount], [OriginalBalance]
FROM Two.dbo.csmApi_PenaltyDocument 
WHERE BatchId = 'PENALTY2'

-- Step 5: Check if there's a filter on document type or status
PRINT '=== STEP 5: Check MiscDocument status for PNLT00000000063 ==='
SELECT DocumentNumber, DocumentStatus, Amount, OutstandingAmount, CreatedDate
FROM Two.dbo.MiscDocument
WHERE DocumentNumber IN ('PNLT00000000063')

-- Step 6: Check the penalty preparation setup for PENALTY batch
PRINT '=== STEP 6: Check PENALTY batch (the one that posted documents) ==='
SELECT [umDocumentNumber] as SourceDoc, [umDocumentNumberSecond] as PenaltyDoc, 
       [umAmount], [umPenaltyID]
FROM Two.dbo.csmApi_PenaltyPreparationHistory 
WHERE BatchId = 'PENALTY'

-- Step 7: Check calculation parameters
PRINT '=== STEP 7: Check calculation params for PENALTY2 ==='
SELECT * FROM Two.dbo.csmApi_PenaltyCalculation WHERE BatchId = 'PENALTY2'
