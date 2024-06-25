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

	[string]$DbBackup_Path_FileVersion = "D:\DatabaseBackup\TWO_2022R2.bak", # Path to SQL in Nexus_Api folder
    [string]$Nexus_APIPath = "C:\Program Files (x86)\Jenkins\workspace\NexusAPIBuild\", # Path to Nexus_Api
	[string]$BackupDestination = "C:\Program Files (x86)\Jenkins\workspace\Backup\", # Path to Nexus_Api backup
    [string]$Nexus_APIDistPathToDelete = $Nexus_APIPath+"dist\server\*", # Path to DIST folder for Nexus_Api
    [string]$Nexus_APIPathToSQL = $Nexus_APIPath+"src\server\db scripts\", # Path to SQL in Nexus_Api folder
    [string]$SQLServerInstance = "DESKTOP-QU86F3Q\MSSQLSERVER2019", # SQL Server Instance
    [string]$SQLCompanyDB = "TWO", # CSM Company Database
    [string]$SQLApiDB = "NEXUS_API", # Nexus Api Database
	[string]$Nexus_APIPathToSQLpatch = $Nexus_APIPathToSQL+"\Patches\" # Nexus Api Patches
	
	
	
	
)

Stop-Service -Name PM2
pm2 delete csm-web-api

	
# Restore database the password is 'cogs'
Write-Host "Restore DB =============================="
invoke-sqlcmd -ServerInstance $SQLServerInstance -U "sa" -Password "cogs" -Query "IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name ='TWO') BEGIN ALTER DATABASE TWO SET OFFLINE WITH ROLLBACK IMMEDIATE; DROP DATABASE TWO;  END"
Restore-SqlDatabase -ServerInstance $SQLServerInstance -Database $SQLCompanyDB -BackupFile $DbBackup_Path_FileVersion  -ReplaceDatabase 

$TakeBackup = "n"

IF ($TakeBackup -eq "y") 
{
	#$BackupDestination = Read-Host "What is the backup destination?"
	Write-Host $TakeBackup $BackupDestination
	IF (Test-Path $BackupDestination)
	
	{
		Write-Host "Backing up " $Nexus_APIPath "...."
		Copy-Item -Path $Nexus_APIPath -Destination $BackupDestination -Force -Recurse 
		Write-Host "Backing  " $Nexus_APIPath "....DONE"
		#Read-Host "Press any key to continue or CTRL+C to quit"
	}
	Else
	{ 
		#Read-Host "Your path is invalid!: Press any key to continue or CTRL+C to quit"
	}

}


# Testing for valid paths
If (!(Test-Path $Nexus_APIPath)) 
{
    Write-Host "Your path is invalid!" $Nexus_APIPath
    exit
}
If (!(Test-Path $Nexus_APIDistPathToDelete)) 
{
    #Read-Host "Your path is invalid! " $Nexus_APIDistPathToDelete "No DIST folder to delete: Press any key to continue or CTRL+C to quit"
}
Else
{
	# Delete the dist folder 
	Write-Host "API path: '$Nexus_APIDistPathToDelete'" 
	#Read-Host -Prompt "Does that look right? Press any key to continue or CTRL+C to quit" 
	#Get-ChildItem -Path $Nexus_APIDistPathToDelete -Include * -File -Force -Recurse | foreach { $_.Delete()
	Remove-Item -path $Nexus_APIDistPathToDelete -Recurse -Force -EA SilentlyContinue -Verbose
	#Remove-Item -Path $Nexus_APIDistPathToDelete -Force

}
If (!(Test-Path $Nexus_APIPathToSQL)) 
{
    Write-Host "Your path is invalid!" $Nexus_APIPathToSQL
    exit
}
If (!(Test-Path $Nexus_APIPath"node_modules")) 
{
write-host $Nexus_APIPath+"node_modules"
	Write-Host "npm not installed locally.  Exiting..."
	Exit
}
Else
{
# Stop PM2 service & delete csm-web-api


Set-Location $Nexus_APIPathToSQLpatch
#Invoke-sqlcmd -inputfile "CSM_Coreum_udReceiptHeader_Pre2021R2.sql" -serverinstance $SQLServerInstance -database $SQLCompanyDB
Write-Host "Running Patches.sql...."
#Invoke-sqlcmd -inputfile "CSM_CoreSwitchMeters_Pre2020R2.sql" -serverinstance $SQLServerInstance -database $SQLCompanyDB
#Write-Host "Running CSM_CoreSwitchMeters_Pre2020R2.sql....DONE"
$scripts = Get-ChildItem $Nexus_APIPathToSQLpatch | Where-Object {$_.Extension -eq ".sql"}
 
foreach ($s in $scripts)
    {
        Write-Host "Running Script : " $s.Name -BackgroundColor DarkGreen -ForegroundColor White
        $script = $s.FullName
        Invoke-Sqlcmd -InputFile $script -serverinstance $SQLServerInstance -database $SQLCompanyDB  | Out-File  -FilePath "C:\TestSqlCmd.rpt"
    }


Set-Location  $Nexus_APIPathToSQL 
Write-Host "Running Search_Remove_CSM_DatabaseComponents.sql...."
Invoke-sqlcmd -inputfile "Search_Remove_CSM_DatabaseComponents.sql" -serverinstance $SQLServerInstance -database $SQLCompanyDB 

# this will build and create and new entities required - config files need to be set to true
Set-Location $Nexus_APIPath
npm run build:prod
#npm run pm2:start
#npm run pm2:stop
#Read-Host -Prompt "Does that look right? Press any key to continue or CTRL+C to quit" 

#npm run license:copyfile




# Update SQL
Set-Location $Nexus_APIPathToSQL
Invoke-Command {cmd.exe /c BuildInstallScript.bat} 
Start-Sleep -s 5
#Read-Host -Prompt "Did the batch file complete successfully? Press <ENTER> to continue or CTRL+C to quit" 
Write-Host "Updating COMPANY procedures...."
Invoke-sqlcmd -inputfile "InstallCsmApiDatabaseComponents.sql" -serverinstance $SQLServerInstance -database $SQLCompanyDB | Out-File -FilePath "C:\TestSqlCmd.rpt"
Write-Host "Updating COMPANY procedures....DONE"
#Read-Host -Prompt "Does that look right? Press <ENTER> to continue or CTRL+C to quit" 

# SQL Search database components
Write-Host "Running search to install into Nexus Api Database...."
Invoke-sqlcmd -inputfile "Search_Install_Api_DatabaseComponents.sql" -serverinstance $SQLServerInstance -database $SQLApiDB
Write-Host "....DONE"
#Read-Host -Prompt "Does that look right? Press <ENTER> to continue or CTRL+C to quit" 
Write-Host "Running search to install into CSM Database...."
Invoke-sqlcmd -inputfile "Search_Install_CSM_DatabaseComponents.sql" -serverinstance $SQLServerInstance -database $SQLCompanyDB
Write-Host "....DONE"
#Read-Host -Prompt "Does that look right? Press <ENTER> to continue or CTRL+C to quit"


Write-Host "Running Initialize.sql...."
Invoke-sqlcmd -inputfile "Initialize.sql" -serverinstance $SQLServerInstance -database $SQLApiDB | Out-File -FilePath "C:\TestSqlCmd.rpt"
Write-Host "Running Initialize.sql....DONE"
#Read-Host -Prompt "Does that look right? Press <ENTER> to continue or CTRL+C to quit" 

Write-Host "Running PostInitialize.sql...."
Invoke-sqlcmd -inputfile "PostInitializeCompanyDB.sql" -serverinstance $SQLServerInstance -database $SQLCompanyDB | Out-File -FilePath "C:\TestSqlCmd.rpt"
Write-Host "Running PostInitialize.sql....DONE"
#Read-Host -Prompt "Does that look right? Press <ENTER> to continue or CTRL+C to quit" 

#npm run prod
Set-Location $Nexus_APIPath
pm2 start ecosystem.config.js
pm2 stop csm-web-api
pm2 start csm-web-api
pm2 list
pm2 save

npm run dllLibraries:copyfile
npm run reportTemplates:copyfile
npm run apidoc-make-private
# Restart PM2 service

Write-Host "Nexus API should now be up and running."
}