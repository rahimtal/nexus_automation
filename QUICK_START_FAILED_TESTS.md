# Failed Tests Runner - Quick Start Guide

## TL;DR - Just Want to Run Failed Tests?

### Option 1: PowerShell (Easiest for Windows)
```powershell
.\Rerun_Failed_Tests.ps1
```

### Option 2: Direct Maven Command
```bash
# First, run your full test suite
.\run_All_tests.bat

# Then extract and re-run failed tests only
java -cp target/classes com.NexustAPIAutomation.java.FailedTestExtractor ^
    target/test-output/TestSuite.txt testng-failed.xml

mvn clean test -Dsuites=testng-failed.xml
```

---

## What You Get

✅ **Faster Iteration** - Run only 61 failed tests instead of 258 (saves ~3 minutes per run)
✅ **Easy Debugging** - Focus on broken tests without distractions
✅ **Multiple Tools** - PowerShell, batch, shell, or direct Java execution
✅ **CI/CD Ready** - Integrate into build pipelines
✅ **No Build Changes** - Doesn't modify your pom.xml or project structure

---

## How It Works

### The Flow
```
Full Test Run
    ↓
TestSuite.txt Generated
    ↓
FailedTestExtractor Reads File
    ↓
Extracts Failed Test Classes
    ↓
Generates testng-failed.xml
    ↓
Maven Runs Only Failed Tests
    ↓
Reports Generated
```

### Example Output
```
=========================================
Re-run Failed Tests
=========================================

✓ Found 61 failed tests in 34 classes

========== FAILED TESTS ==========
  ✗ com.NexusAPI.Tests.Private_ActionsMenuController_Test.getActionsMenu_v4
  ✗ com.NexusAPI.Tests.Private_AlertPanelController_Test.getalertaccountAttributes_v4
  ✗ com.NexusAPI.Tests.Private_CashieringController_Test.TC003_1_getCashin
  ... (61 total)
==================================

[INFO] Running 61 tests...
```

---

## Available Tools

### 1. **Rerun_Failed_Tests.ps1** (Recommended)
- **Easiest to use**
- Fully automated
- Beautiful colored output
- Includes all 5 steps

```powershell
.\Rerun_Failed_Tests.ps1
```

### 2. **FailedTestExtractor.java**
- **Core utility class**
- Can be used programmatically
- Works on all platforms

Direct execution:
```bash
java -cp target/classes com.NexustAPIAutomation.java.FailedTestExtractor \
    target/test-output/TestSuite.txt testng-failed.xml
```

### 3. **run_failed_tests.ps1**
- Alternative PowerShell version
- Takes command-line arguments

```powershell
.\run_failed_tests.ps1 -TestOutputFile "target/test-output/TestSuite.txt"
```

### 4. **run_failed_tests_v2.bat**
- Windows batch file version
- Simple one-click execution

```bash
run_failed_tests_v2.bat
```

### 5. **run_failed_tests.sh**
- Linux/Mac shell script

```bash
chmod +x run_failed_tests.sh
./run_failed_tests.sh
```

---

## Step-by-Step Guide

### Step 1: Run Full Test Suite
```bash
.\run_All_tests.bat
```
This creates `target/test-output/TestSuite.txt` with all test results.

### Step 2: Extract Failed Tests (Choose One)

**Option A: PowerShell (Automatic)**
```powershell
.\Rerun_Failed_Tests.ps1
```
This does everything automatically.

**Option B: Manual Command**
```bash
java -cp target/classes com.NexustAPIAutomation.java.FailedTestExtractor ^
    target/test-output/TestSuite.txt testng-failed.xml
```

### Step 3: Review Generated File
```bash
type testng-failed.xml
```
You'll see something like:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Failed Tests Suite" verbose="2" parallel="false">
    <test name="Re-run Failed Tests">
        <classes>
            <class name="com.NexusAPI.Tests.Private_CashieringController_Test"/>
            <class name="com.NexusAPI.Tests.Private_ActionsMenuController_Test"/>
            <!-- More classes... -->
        </classes>
    </test>
</suite>
```

### Step 4: Run Failed Tests
```bash
mvn clean test -Dsuites=testng-failed.xml
```
Or if using testng-failed.xml with surefire:
```bash
mvn test -f testng-failed.xml
```

---

## Common Scenarios

### Scenario 1: Find Exact Failed Test Method
```bash
# Search TestSuite.txt for specific test
Select-String -Path "target/test-output/TestSuite.txt" -Pattern "methodName"
```

### Scenario 2: Generate Report Only (No Execution)
```bash
java -cp target/classes com.NexustAPIAutomation.java.FailedTestExtractor ^
    target/test-output/TestSuite.txt report.xml

# Review report.xml without running tests
```

### Scenario 3: Keep Multiple Failure Reports
```bash
# First run
.\run_All_tests.bat
java -cp target/classes com.NexustAPIAutomation.java.FailedTestExtractor ^
    target/test-output/TestSuite.txt testng-failed-run1.xml

# After fixes
.\run_All_tests.bat  
java -cp target/classes com.NexustAPIAutomation.java.FailedTestExtractor ^
    target/test-output/TestSuite.txt testng-failed-run2.xml

# Compare: diff testng-failed-run1.xml testng-failed-run2.xml
```

### Scenario 4: Continuous Testing Loop
```powershell
while ($true) {
    Write-Host "Running failed tests..." -ForegroundColor Cyan
    & mvn clean test -Dsuites=testng-failed.xml
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "All tests passed! Exiting." -ForegroundColor Green
        break
    }
    
    Write-Host "`nRe-extracting failed tests..." -ForegroundColor Yellow
    java -cp target/classes com.NexustAPIAutomation.java.FailedTestExtractor `
        target/test-output/TestSuite.txt testng-failed.xml
    
    Read-Host "Press Enter to run again"
}
```

---

## Troubleshooting

### Problem: "File not found: target/test-output/TestSuite.txt"
**Solution**: Run full tests first
```bash
.\run_All_tests.bat
```

### Problem: "No failed tests found"
**Meaning**: All tests passed! ✓
```
✓ No failed tests found! All tests passed.
```

### Problem: testng-failed.xml not created
**Solution**: Check TestSuite.txt has proper format
```bash
# View first 20 lines
Get-Content target/test-output/TestSuite.txt -Head 20
```

### Problem: Tests still fail when re-run
**Reason**: Flaky tests or data issues
**Solution**: 
1. Re-run with seed data from ConfigureAndRunTests
2. Check for race conditions
3. Verify database state

### Problem: PowerShell script won't run
**Solution**: Enable script execution
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

---

## Performance Comparison

| Task | Full Suite (258 tests) | Failed Only (61 tests) | Time Saved |
|------|--:|--:|--:|
| Single Run | 6 min | 2 min | 4 min |
| 3 Iterations | 18 min | 6 min | 12 min |
| 5 Iterations | 30 min | 10 min | 20 min |

---

## File Reference

| File | Purpose | Platform |
|------|---------|----------|
| `Rerun_Failed_Tests.ps1` | All-in-one failed test runner (RECOMMENDED) | Windows PowerShell |
| `run_failed_tests.ps1` | Alternative PowerShell runner | Windows PowerShell |
| `run_failed_tests_v2.bat` | Batch script runner | Windows CMD |
| `run_failed_tests.sh` | Shell script runner | Linux/Mac |
| `FailedTestExtractor.java` | Core extractor utility | All (Java) |
| `testng-failed.xml` | Generated test file (do not edit) | N/A |
| `FAILED_TESTS_RUNNER_GUIDE.md` | Comprehensive guide | All |

---

## Quick Reference

```bash
# Run everything automatically (easiest)
.\Rerun_Failed_Tests.ps1

# Manual multi-step (for CI/CD)
.\run_All_tests.bat
java -cp target/classes com.NexustAPIAutomation.java.FailedTestExtractor target/test-output/TestSuite.txt testng-failed.xml
mvn clean test -Dsuites=testng-failed.xml

# Check what's in TestSuite.txt
Select-String -Path "target/test-output/TestSuite.txt" -Pattern "FAILURE" | wc -l

# Search for specific failed test
Select-String -Path "target/test-output/TestSuite.txt" -Pattern "YourTestName" -Context 0,5

# View generated failed tests file
type testng-failed.xml

# Clean up (remove generated files)
Remove-Item testng-failed.xml
```

---

## Support & Questions

If you encounter issues:
1. Check Troubleshooting section above
2. Verify `target/test-output/TestSuite.txt` exists
3. Confirm Java and Maven are installed: `java -version` and `mvn -version`
4. Review generated `testng-failed.xml` for validity

---

**Created**: Failed Tests Runner Suite
**Version**: 1.0
**Platform**: Windows/Linux/Mac (with appropriate scripts)
**Framework**: TestNG 7.11.0 with Maven 3.x
