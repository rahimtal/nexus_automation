#### This script expects the updated code to have already been placed in the Nexus Api folder
#### There are 6 parameters required:
####    $Nexus_APIPath 				: 	Path to Nexus_Api
####    $Nexus_APIDistPathToDelete 	:	Path to DIST folder for Nexus_Api
####    $Nexus_APIPathToSQL 		:	Path to SQL in Nexus_Api folder
####    $SQLServerInstance 			:	SQL Server Instance
####    $SQLCompanyDB 				:	CSM Company Database
####    $SQLApiDB 					:	Nexus Api Database
####

param (

    [string]$DbBackup_Path_FileVersion = "B:\DatabaseBackup\TEST_CSM_58", # Path to SQL in Nexus_Api folder
    [string]$SQLServerInstance = "DESKTOP-QU86F3Q", 
    [string]$SQLCompanyDB = "TWO" # CSM Company Database
    
	
)

# Restore database the password is 'cogs'
Write-Host "Restore DB ==============================" $DbBackup_Path_FileVersion
Invoke-Command -ServerInstance "DESKTOP-QU86F3Q" -U "sa" -Password "cogs" -Query "IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name ='TWO') BEGIN ALTER DATABASE TWO SET OFFLINE WITH ROLLBACK IMMEDIATE; DROP DATABASE TWO;  END"
Restore-SqlDatabase -ServerInstance "DESKTOP-QU86F3Q" -Database "TWO" -BackupFile $DbBackup_Path_FileVersion  -ReplaceDatabase 


