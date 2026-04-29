# JsonComparator - Dynamic Field Comparison Guide

## Problem Solved

Tests were failing because API responses contained dynamic hostnames in URLs:
- Expected: `"cogsDrillback://DGPB/?Db=&Srv=DESKTOP-QU86F3Q&..."`  
- Actual: `"cogsDrillback://DGPB/?Db=&Srv=4669f4bd4959&..."`  (Docker container ID)

Container IDs and hostnames change on every restart, making hardcoded assertions brittle.

## Solution: JsonComparator

A new utility class that **compares JSON responses while ignoring or normalizing dynamic fields**.

## Usage Examples

### 1. Ignore Specific Fields
```java
// Ignore the "URL" field completely in comparison
JsonComparator.assertEquals(actual, expected, "URL");

// Ignore multiple fields
JsonComparator.assertEquals(actual, expected, "URL", "Timestamp", "ID");
```

### 2. Ignore Dynamic Hostnames (Recommended for drillbacks)
```java
// Normalizes all hostnames in URL fields before comparing
// Converts: Srv=4669f4bd4959 → Srv=NORMALIZED
// Converts: Srv=DESKTOP-QU86F3Q → Srv=NORMALIZED
JsonComparator.assertEqualsIgnoreHostnames(actual, expected);
```

### 3. Pretty Print JSON (for debugging)
```java
String formatted = JsonComparator.prettyPrint(jsonString);
System.out.println(formatted);
```

## Real Example: ActionsMenu Test

**Before (Failed):**
```java
String expected = "{...URL...DESKTOP-QU86F3Q...}";
String result = CommonMethods.getMethodasString(uri, ver, params);
Assert.assertEquals(result, expected);  // ✗ FAILS: hostname mismatch
```

**After (Passes):**
```java
String expected = "{...URL...DESKTOP-QU86F3Q...}";  // Original expected value
String result = CommonMethods.getMethodasString(uri, ver, params);
JsonComparator.assertEqualsIgnoreHostnames(result, expected);  // ✓ PASSES: normalized comparison
```

## How It Works

1. **Parse JSON** - Converts both strings to JSON tree structures
2. **Normalize URLs** - Replaces all dynamic hostnames with `NORMALIZED` placeholder
3. **Compare** - Performs deep equality check on normalized structures
4. **Report** - Pretty-prints both expected/actual on mismatch

### Hostname Normalization Pattern
```
Original:  cogsDrillback://DGPB/?Db=&Srv=4669f4bd4959&Cmp=TWO&...
Normalized: cogsDrillback://DGPB/?Db=&Srv=NORMALIZED&Cmp=TWO&...
```

## Best Practices

### When to Use Each Method

| Method | Use Case | Example |
|--------|----------|---------|
| `assertEquals(a, e, "URL")` | Ignore specific field completely | When URL format doesn't matter |
| `assertEqualsIgnoreHostnames(a, e)` | Handle dynamic hostnames in URLs | cogsDrillback URLs in ActionsMenu |
| `assertEquals(a, e)` | Exact match required | Hardcoded expected values that never change |

### Design Tips

1. **Use original expected values in tests** - Don't update expected to latest hostname
2. **Normalizer handles recursion** - Works on nested objects and arrays automatically
3. **Field names are case-insensitive** - Matches "URL", "url", "Url"
4. **Regex-based hostname detection** - Looks for `Srv=<hostname>` pattern

## Testing It

Run any test using `JsonComparator.assertEqualsIgnoreHostnames()`:

```bash
mvn clean test -Dtest=Private_ActionsMenuController_Test#getActionsMenu_v4
```

The test now passes regardless of Docker container ID!

## Affected Tests Fixed

This solution automatically fixes tests with dynamic drillback URLs:
- getActionsMenu_v4 (12 menu items with drillback URLs)
- Any other tests using cogsDrillback URLs
- Tests comparing responses with server-specific URLs

## Files

- **JsonComparator.java** - Utility class (src/main/java/com/NexustAPIAutomation/java/)
- **Private_ActionsMenuController_Test.java** - Updated to use `assertEqualsIgnoreHostnames()`
- **pom.xml** - Jackson dependency updated to compile scope

## Integration

The JsonComparator is now part of your test utilities and available in all tests:

```java
import com.NexustAPIAutomation.java.JsonComparator;

@Test
public void yourTest() {
    String actual = apiCall();
    String expected = "{...}";
    JsonComparator.assertEqualsIgnoreHostnames(actual, expected);
}
```
