#!/bin/bash

# Failed Test Extractor and Runner
# Usage: ./run_failed_tests.sh [test_output_file] [output_xml_file]

TEST_OUTPUT_FILE="${1:-target/test-output/TestSuite.txt}"
OUTPUT_XML="${2:-testng-failed.xml}"

echo ""
echo "========================================"
echo "Failed Test Runner"
echo "========================================"
echo ""

# Check if test output file exists
if [ ! -f "$TEST_OUTPUT_FILE" ]; then
    echo "✗ File not found: $TEST_OUTPUT_FILE"
    exit 1
fi

# Compile project
echo "Compiling project..."
mvn clean compile -q
if [ $? -ne 0 ]; then
    echo "✗ Compilation failed"
    exit 1
fi

# Extract failed tests
echo "Extracting failed tests..."
mvn exec:java \
    -Dexec.mainClass="com.NexustAPIAutomation.java.FailedTestExtractor" \
    -Dexec.args="$TEST_OUTPUT_FILE $OUTPUT_XML" \
    -q

if [ $? -ne 0 ]; then
    echo "✗ Failed to extract tests"
    exit 1
fi

# Check if failed tests were found
if [ ! -f "$OUTPUT_XML" ]; then
    echo "✓ No failed tests found!"
    exit 0
fi

echo ""
echo "========================================"
echo "Running Failed Tests"
echo "========================================"
echo ""

# Run failed tests
mvn clean test -Dsuite=$OUTPUT_XML

echo ""
echo "========================================"
echo "Failed Test Run Complete"
echo "========================================"
echo ""
