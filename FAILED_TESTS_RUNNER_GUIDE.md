# Failed Tests Runner - Usage Guide

## Overview

This solution provides multiple ways to extract failed tests from the test suite output and re-run only those specific tests. This is useful for:

- **Faster feedback loops** - Re-run only failed tests instead of the entire suite
- **Debugging** - Focus on specific broken tests
- **Iteration** - Verify fixes quickly
- **CI/CD integration** - Implement test failure recovery workflows

---

## Available Methods

### Method 1: PowerShell Script (Recommended for Windows)

**File**: `run_failed_tests.ps1`

#### Quick Start
```powershell
.\run_failed_tests.ps1
```

#### With Custom Paths
```powershell
.\run_failed_tests.ps1 -TestOutputFile "target/test-output/TestSuite.txt" -OutputXml "my-failed-tests.xml"
```

#### Features
- Automatic compilation
- Clean test environment
- Color-coded output
- Generates `testng-failed.xml` automatically
- Runs Maven test suite

---

### Method 2: Batch File Script (Windows)

**File**: `run_failed_tests_v2.bat`

#### Quick Start
```bash
run_failed_tests_v2.bat
```

#### What It Does
1. Compiles the project
2. Runs FailedTestExtractor
3. Generates testng-failed.xml
4. Executes failed tests

---

### Method 3: Shell Script (Linux/Mac)

**File**: `run_failed_tests.sh`

#### Quick Start
```bash
chmod +x run_failed_tests.sh
./run_failed_tests.sh
```

#### With Custom Paths
```bash
./run_failed_tests.sh target/test-output/TestSuite.txt testng-failed.xml
```

---

### Method 4: Direct Java Execution

**Class**: `FailedTestExtractor`

#### Extract Failed Tests Only
```bash
mvn exec:java \
    -Dexec.mainClass="com.NexustAPIAutomation.java.FailedTestExtractor" \
    -Dexec.args="target/test-output/TestSuite.txt testng-failed.xml"
```

#### Then Run Generated XML
```bash
mvn clean test -Dsuite=testng-failed.xml
```

---

### Method 5: Maven Command Line

#### One-Command Solution
```bash
# Step 1: Generate failed tests file
mvn exec:java -Dexec.mainClass="com.NexustAPIAutomation.java.FailedTestExtractor" \
    -Dexec.args="target/test-output/TestSuite.txt testng-failed.xml"

# Step 2: Run failed tests
mvn clean test -Dsuite=testng-failed.xml
```

---

## What the FailedTestExtractor Does

### Input
- Reads `target/test-output/TestSuite.txt` (TestNG output)
- Identifies all lines containing `<<< FAILURE!`
- Extracts the test class and method names

### Processing
- Groups failed tests by their class
- Removes duplicates
- Prints summary to console

### Output
- Generates `testng-failed.xml` with only failed test classes
- Prints total count of failed tests
- Lists all failed tests with ✗ indicator

### Example Output
```
========== FAILED TESTS SUMMARY ==========

  ✗ com.NexusAPI.Tests.Private_ActionsMenuController_Test.getActionsMenu_v4
  ✗ com.NexusAPI.Tests.Private_AlertPanelController_Test.getalertaccountAttributes_v4
  ✗ com.NexusAPI.Tests.Private_CashieringController_Test.saveReciept_2_4
  ...

==========================================
Total failed tests: 61
==========================================
```

---

## Generated testng-failed.xml

After extraction, a new file is created:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Failed Tests Suite" verbose="2" preserve-order="true" parallel="false" thread-count="1">
    <test name="Re-run Failed Tests">
        <classes>
            <class name="com.NexusAPI.Tests.Private_ActionsMenuController_Test"/>
            <class name="com.NexusAPI.Tests.Private_AlertPanelController_Test"/>
            <class name="com.NexusAPI.Tests.Private_CashieringController_Test"/>
            <!-- More test classes... -->
        </classes>
    </test>
</suite>
```

---

## Workflow Examples

### Example 1: Quick Debug Session

```bash
# Run all tests
.\run_All_tests.bat

# After tests complete, run only failed tests
.\run_failed_tests.ps1

# Fix issues and re-run failed tests immediately
.\run_failed_tests.ps1
```

### Example 2: CI/CD Pipeline

```bash
#!/bin/bash
# Run full test suite
mvn clean test

# If tests fail, extract and re-run
if [ $? -ne 0 ]; then
    ./run_failed_tests.sh
    
    # Optional: Generate report on rerun results
    if [ $? -eq 0 ]; then
        echo "All previously failed tests now pass!"
    fi
fi
```

### Example 3: Parallel Development

```powershell
# Developer 1: Run full suite and save results
.\run_All_tests.bat

# Developer 2: Meanwhile, fix specific tests
# Then quickly verify fixes by running only failed tests
.\run_failed_tests.ps1
```

---

## Performance Benefits

### Scenario: 61 Failed Tests out of 258

| Approach | Time | Tests Run |
|----------|------|-----------|
| Full Suite | 6 minutes | 258 |
| Failed Tests Only | 2-3 minutes | 61 |
| **Time Saved** | **3 minutes** | **197 fewer** |

### With Multiple Iterations
- 3 iterations: 18 minutes saved → 9 minutes total instead of 18 minutes
- 5 iterations: 30 minutes saved → 15 minutes total instead of 30 minutes

---

## Troubleshooting

### Issue: "File not found: target/test-output/TestSuite.txt"
**Solution**: Run the full test suite first
```bash
.\run_All_tests.bat
```

### Issue: "No failed tests found"
**Possible Causes**:
- All tests passed (great!)
- TestSuite.txt not in expected location
- Different output format

**Solution**: Check file path and format
```bash
mvn exec:java -Dexec.mainClass="com.NexustAPIAutomation.java.FailedTestExtractor" \
    -Dexec.args="target/test-output/TestSuite.txt debug.xml"
```

### Issue: Maven command not recognized
**Solution**: Ensure Maven is in PATH
```bash
mvn --version
```

### Issue: TestNG not finding tests in testng-failed.xml
**Solution**: Verify XML file was generated
```bash
type testng-failed.xml  # Windows
cat testng-failed.xml   # Linux/Mac
```

---

## Integration with IDE

### IntelliJ IDEA
1. Right-click on `testng-failed.xml` in file explorer
2. Select "Run 'testng-failed.xml'"
3. Or: Run > Edit Configurations > Add TestNG > Use single file

### Eclipse
1. Create TestNG configuration pointing to `testng-failed.xml`
2. Run as TestNG Suite

### VS Code
1. Install TestNG extension
2. Run tests through command palette
3. Or use integrated terminal: `mvn clean test -Dsuite=testng-failed.xml`

---

## Advanced Usage

### Generate Report Only (No Test Execution)

```bash
mvn exec:java \
    -Dexec.mainClass="com.NexustAPIAutomation.java.FailedTestExtractor" \
    -Dexec.args="target/test-output/TestSuite.txt my-failed.xml"
```

### Use Different TestSuite Output
If you have multiple test runs, extract from specific one:

```powershell
.\run_failed_tests.ps1 -TestOutputFile "old-results/TestSuite.txt"
```

### Preserve Multiple Failure Reports

```bash
# First run
mvn clean test > test-run-1.log
mvn exec:java -Dexec.mainClass="..." \
    -Dexec.args="target/test-output/TestSuite.txt testng-failed-1.xml"

# Second run after fixes
mvn clean test > test-run-2.log
mvn exec:java -Dexec.mainClass="..." \
    -Dexec.args="target/test-output/TestSuite.txt testng-failed-2.xml"
```

---

## Summary

Choose the method that best fits your workflow:

| Method | Platform | Ease | Automation |
|--------|----------|------|-----------|
| PowerShell | Windows | ⭐⭐⭐⭐⭐ | Full |
| Batch | Windows | ⭐⭐⭐ | Full |
| Shell | Linux/Mac | ⭐⭐⭐⭐ | Full |
| Maven | All | ⭐⭐⭐ | Manual |
| IDE Integration | All | ⭐⭐⭐⭐ | UI-driven |

---

## Support

For issues or questions:
1. Check the Troubleshooting section above
2. Verify TestSuite.txt exists and contains failures
3. Confirm Maven and Java are installed
4. Review generated testng-failed.xml for validity
