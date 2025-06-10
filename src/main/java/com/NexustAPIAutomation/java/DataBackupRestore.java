package com.NexustAPIAutomation.java;

import org.testng.annotations.Test; import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.ibatis.common.jdbc.ScriptRunner;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

import org.apache.http.ConnectionClosedException;
import org.testng.Assert;
import org.testng.annotations.Test; import org.testng.Assert;



public class DataBackupRestore {

    public static ReadProjectProperties Read = new ReadProjectProperties();
    static String rDatabaseName = Read.ReadFile("rDatabaseName");
    static String rDatabaseLocation = Read.ReadFile("rDatabaseLocation");
    static String bDatabaseName = Read.ReadFile("bDatabaseName");
    static String bDatabaseLocation = Read.ReadFile("bDatabaseLocation");
    static String ConnectionString = Read.ReadFile("ConnectionStringServ");
    static String ConnectionStringTWO = Read.ReadFile("ConnectionStringServTWO");

    static String ConnectionString2 = Read.ReadFile("ConnectionStringApi");
    static String ConnectionStringcompany = Read.ReadFile("ConnectionStringServTWO");
    static String csmspversion = Read.ReadFile("csmspversion");
    static String csmversion = Read.ReadFile("csmversion");

    static Connection con;

    // @Test
    public void test() throws ClassNotFoundException, SQLException, InterruptedException {
	CompanyDBVersionVerify(csmversion, csmspversion);
    }

    @Test
    public void restore() throws ClassNotFoundException, SQLException, InterruptedException {

	// This function will restore latest db backup along with new patches and script
	// from source
	CompanyDBRestore();

    }

    // @Test(priority = 1)
    public void CompanyDBBackup() throws SQLException, ClassNotFoundException {

	// String DatabaseName = "[TEST2]";
	// String DatabaseLocation = "'F:\\TestDBBackup\\TEST2.Bak'";

	String Backup = "USE MASTER; BACKUP DATABASE " + bDatabaseName + "TO DISK =" + bDatabaseLocation
		+ "  WITH FORMAT, MEDIANAME = 'Z_SQLServerBackups'," + "      NAME = 'Full Backup of " + bDatabaseName
		+ "';";

	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	// Creating connection to the database
	Connection con = DriverManager.getConnection(ConnectionString);
	Statement st = con.createStatement();
	// Executing the SQL command
	st.executeUpdate(Backup);
	con.close();
    }

    public static void CompanyDBRestore() throws SQLException, ClassNotFoundException, InterruptedException {

	try (PowerShell powerShell = PowerShell.openSession()) {
	    // Execute a command in PowerShell session
	    PowerShellResponse response;
	    Map<String, String> config = new HashMap<String, String>();
	    config.put("maxWait", "20000");
	    System.out.println("Restoring Database ...");
	    response = powerShell.configuration(config).executeScript("./\\Configuration\\DBOnlyrestore.ps1");
	    System.out.println("Script output:" + response.getCommandOutput());
	    // response =
	    // powerShell.configuration(config).executeScript("./\\Configurations\\DB_sc_pat02.ps1");
	    // System.out.println("Script output:" + response.getCommandOutput());

	} catch (Exception e) {
	    e.printStackTrace();
	    Assert.fail("Scripts got error while rinning DB Scripts, please see logs");
	    System.exit(1);
	}

    }

    public static void CompanyDBRestoreCustom(String Full)
	    throws SQLException, ClassNotFoundException, InterruptedException {
	String Restore = "ALTER DATABASE " + rDatabaseName
		+ " SET SINGLE_USER WITH ROLLBACK IMMEDIATE USE master; RESTORE DATABASE " + rDatabaseName
		+ " FROM DISK = " + rDatabaseLocation + " WITH REPLACE;";
	String multi = "ALTER DATABASE " + rDatabaseName + " SET MULTI_USER WITH ROLLBACK IMMEDIATE";

	try {
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	} catch (ClassNotFoundException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	    Assert.fail("Scripts got error, please see logs");

	}

	try {
	    con = DriverManager.getConnection(ConnectionString);
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	    Assert.fail("Scripts got error, please see logs");

	}
	Statement st;
	try {
	    st = con.createStatement();
	    st.executeUpdate(Restore);
	    st.executeUpdate(multi);
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	    Assert.fail("Data Restore Scripts got error, please see logs");

	}

	finally {
	    con.close();
	    CompanyDBVersionVerify(csmversion, csmspversion);
	}
	CommonMethods.Delay(10000);

	try (PowerShell powerShell = PowerShell.openSession()) {
	    // Execute a command in PowerShell session
	    PowerShellResponse response;
	    Map<String, String> config = new HashMap<String, String>();
	    config.put("maxWait", "200000");
	    response = powerShell.configuration(config).executeScript("./\\Configurations\\DBOnlyrestore.ps1");
	    System.out.println("Script output:" + response.getCommandOutput());

	} catch (PowerShellNotAvailableException e) {
	    e.printStackTrace();
	    Assert.fail("Scripts got error while rinning DB Scripts, please see logs");
	    // System.exit(1);
	}

    }

    public static void CompanyDBVersionVerify(String csmVersion, String spVersion)
	    throws SQLException, ClassNotFoundException, InterruptedException {
	String getcsmVersion = "select * from [COGS_Product] where BuildNumber like '" + csmVersion + "';";

	String getcsmSPVersion = "select * from [UMGSET01] where umSettingName = 'AQSQLVERSION' and umSettingValue like '"
		+ spVersion + "'";

	

	String result = CommonMethods.selectFromDb(getcsmVersion, ConnectionStringcompany, "BuildNumber");

	if (result == "") {
	    Assert.fail("CSM version Not matched, found please check data");
	    System.exit(1);
	}
	result = "";
	result = CommonMethods.selectFromDb(getcsmSPVersion, ConnectionStringcompany, "umSettingValue");

	if (result == "") {
	    Assert.fail("CSM Sp version Not matched, please check data");
	    System.exit(1);
	}

    }

    void GetDb() throws ClassNotFoundException {

	// Load SQL SERVER JDBC Driver
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	// Creating connection to the database
	try {
	    Connection con = DriverManager.getConnection(
		    "jdbc:sqlserver://RND-BASE-A\\SQL_2017;" + "databaseName=TWO;user=auto;password=password123;");
	    Statement st = con.createStatement();
	    String selectquery = "SELECT * FROM TWO.dbo.UM00600 ";
	    // Executing the SQL Query and store the results in ResultSet
	    ResultSet rs = st.executeQuery(selectquery);
	    // While loop to iterate through all data and print results

	    while (rs.next()) {
		String locationID = rs.getString("umlocationID");
		System.out.print(locationID);

	    }
	    // Closing DB Connection
	    con.close();
	} catch (SQLException ex) {
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	    System.exit(1);
	}

    }

  


}
