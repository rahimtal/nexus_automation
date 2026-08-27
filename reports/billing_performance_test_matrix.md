# Billing Controller Performance Test Matrix

## Scope
This matrix targets Billing v4 APIs already exercised in the automation suite.

Primary objective:
- Validate throughput, latency, and stability of billing lifecycle APIs under realistic load.

Secondary objective:
- Detect regression points in report generation, posting, and transfer operations.

## Environment Preconditions
- API service available at http://localhost:3000
- Keycloak available and stable at configured URL
- SQL Server restored with known baseline data
- Java 21 runtime
- Warmup run completed once before timed measurements

## Test Profiles
- Smoke profile: quick validation after code/config changes
- Baseline profile: standard daily run
- Stress profile: peak load and saturation behavior
- Soak profile: long-running stability and leak detection

## SLO Gates
- P95 latency gate: endpoint-specific (defined below)
- Error rate gate: less than or equal to 1.0 percent for read endpoints, less than or equal to 0.5 percent for write endpoints
- Timeout gate: less than or equal to 0.2 percent
- Data integrity gate: no mismatched batch state transitions in workflow tests

## API Priority Matrix

| Priority | API | Method | Type | Why it matters |
|---|---|---|---|---|
| P1 | /billing/calculate | POST | Compute + DB | Core billing calculation and highest business impact |
| P1 | /billing/generateEditReport | POST | Report generation | Heavy aggregation after calculate |
| P1 | /billing/createStatement | POST | Materialization | Statement generation before print/post |
| P1 | /billing/printStatement | POST | Print/export | Realistic production bottleneck path |
| P1 | /billing/postingBill | POST | Transactional write | Commit/posting phase with lock risk |
| P2 | /billing/final/calculate | POST | Finalization compute | Final-bill pipeline start |
| P2 | /billing/postingFinalBillTransfer | POST | Transfer write | End-of-flow transfer commit |
| P2 | /billing/generateBillingTransferReport | POST | Reporting | Large report payload and serialization load |
| P2 | /billing/transfer/progress | GET | Polling read | UI/process polling pressure |
| P2 | /billing/billBatchStatus/{batchId} | GET | Status read | High-call status endpoint |
| P3 | /billing/utilitySetup | GET | Config read | Dependency for billing setup workflows |
| P3 | /billing/printTemplatePath | GET | Config read | Print path dependency check |
| P3 | /billing/messages and /billing/messages/{id} | CRUD | Control plane | Concurrency and update contention behavior |

## Endpoint-Level Performance Targets

| API | Profile | Concurrency | Target RPS | Duration | P95 target | P99 target |
|---|---|---:|---:|---|---:|---:|
| /billing/calculate | Baseline | 5 | 5 | 10 min | 3500 ms | 6000 ms |
| /billing/calculate | Stress | 15 | 12 | 15 min | 5000 ms | 9000 ms |
| /billing/generateEditReport | Baseline | 5 | 4 | 10 min | 3000 ms | 5000 ms |
| /billing/generateEditReport | Stress | 12 | 10 | 15 min | 4500 ms | 8000 ms |
| /billing/createStatement | Baseline | 5 | 4 | 10 min | 3000 ms | 5000 ms |
| /billing/createStatement | Stress | 12 | 9 | 15 min | 4500 ms | 8000 ms |
| /billing/printStatement | Baseline | 4 | 3 | 10 min | 4500 ms | 8000 ms |
| /billing/printStatement | Stress | 10 | 7 | 15 min | 7000 ms | 12000 ms |
| /billing/postingBill | Baseline | 3 | 2 | 10 min | 5000 ms | 9000 ms |
| /billing/postingBill | Stress | 8 | 5 | 15 min | 8000 ms | 14000 ms |
| /billing/final/calculate | Baseline | 3 | 2 | 10 min | 4000 ms | 7000 ms |
| /billing/final/calculate | Stress | 8 | 5 | 15 min | 6500 ms | 11000 ms |
| /billing/postingFinalBillTransfer | Baseline | 2 | 1 | 10 min | 4500 ms | 8000 ms |
| /billing/postingFinalBillTransfer | Stress | 6 | 3 | 15 min | 7500 ms | 13000 ms |
| /billing/generateBillingTransferReport | Baseline | 2 | 1 | 10 min | 5000 ms | 9000 ms |
| /billing/generateBillingTransferReport | Stress | 5 | 2 | 15 min | 8500 ms | 15000 ms |
| /billing/transfer/progress | Baseline | 25 | 30 | 10 min | 800 ms | 1500 ms |
| /billing/transfer/progress | Stress | 80 | 100 | 15 min | 1200 ms | 2500 ms |
| /billing/billBatchStatus/{batchId} | Baseline | 30 | 40 | 10 min | 700 ms | 1400 ms |
| /billing/billBatchStatus/{batchId} | Stress | 100 | 140 | 15 min | 1100 ms | 2200 ms |

## Workflow Scenarios

### Scenario A: End-to-End Billing Lifecycle
Sequence:
1. POST /billing/calculate
2. POST /billing/generateEditReport
3. POST /billing/createStatement
4. POST /billing/printStatement
5. POST /billing/postingBill

Load model:
- 3 concurrent workflows baseline
- 8 concurrent workflows stress
- Think time 1 to 2 seconds between steps

Pass criteria:
- Workflow success rate greater than or equal to 99 percent baseline, greater than or equal to 97 percent stress
- No stuck batch in intermediate states

### Scenario B: Final Bill Transfer Lifecycle
Sequence:
1. POST /billing/final/calculate
2. POST /billing/postingFinalBillTransfer
3. POST /billing/generateBillingTransferReport
4. GET /billing/transfer/progress (poll every 2 seconds until completion)

Load model:
- 2 concurrent workflows baseline
- 5 concurrent workflows stress

Pass criteria:
- Transfer completion under 120 seconds baseline and under 240 seconds stress
- No transfer report generation failures

### Scenario C: Polling Pressure During Active Posting
Mixed traffic:
- 20 percent POST /billing/postingBill
- 40 percent GET /billing/billBatchStatus/{batchId}
- 40 percent GET /billing/transfer/progress

Load model:
- Baseline 60 virtual users for 15 minutes
- Stress 180 virtual users for 20 minutes

Pass criteria:
- Polling endpoints keep P95 under target while posting errors remain below 0.5 percent

### Scenario D: Billing Messages Concurrency
Operations mix:
- 35 percent POST /billing/messages
- 35 percent PUT /billing/messages
- 20 percent GET /billing/messages/{id}
- 10 percent DELETE /billing/messages/{id}

Load model:
- Baseline 20 virtual users for 20 minutes
- Stress 60 virtual users for 30 minutes

Pass criteria:
- No duplicate-key race issues
- No orphaned message rows after cleanup

## Input Data Strategy
- Use isolated batch ids per virtual user to avoid false contention
- Keep one shared read-only batch id for status endpoints
- For write APIs, rotate customer/location ids across at least 50 entities
- Keep deterministic test windows for billing dates to avoid time-driven variance

## Run Order Recommendation
1. Smoke profile on P1 endpoints
2. Baseline profile on P1 and P2 endpoints
3. Workflow Scenario A and Scenario B
4. Polling pressure Scenario C
5. Stress profile and then Soak profile overnight

## Reporting Checklist
- Throughput per endpoint
- P50, P95, P99 latency per endpoint
- Error breakdown by status and message
- Batch-state transition failures
- Top 10 slowest request samples
- DB and API CPU correlation window

## Immediate Candidate APIs To Start This Week
1. POST /billing/calculate
2. POST /billing/createStatement
3. POST /billing/postingBill
4. GET /billing/billBatchStatus/{batchId}
5. GET /billing/transfer/progress
