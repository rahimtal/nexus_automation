SET NOCOUNT ON;
USE TWO;
SELECT 'BatchCreate_PostDate_JSON' AS Chk,
       CASE WHEN OBJECT_DEFINITION(OBJECT_ID('dbo.csmApi_spBatchCreate')) LIKE '%PostDate DATE ''$.PostDate''%' THEN 'OK' ELSE 'MISSING' END AS Result;
SELECT 'BatchCreate_SetumGLPOST' AS Chk,
       CASE WHEN OBJECT_DEFINITION(OBJECT_ID('dbo.csmApi_spBatchCreate')) LIKE '%umGLPOST = @PostDate%' THEN 'OK' ELSE 'MISSING' END AS Result;
SELECT 'Msg_51080_51081' AS Chk, CAST(COUNT(*) AS varchar(10)) + ' of 2' AS Result
  FROM Api_Messages WHERE MsgId IN (51080, 51081);
SELECT 'MenuID_31_Rates' AS Chk,
       CASE WHEN EXISTS (SELECT 1 FROM CsmApi_Dashboard_Menu_Item WHERE MenuType = 'Account' AND MenuID = 31) THEN 'OK' ELSE 'MISSING' END AS Result;
SELECT 'AccountMenu_RowCount' AS Chk, CAST(COUNT(*) AS varchar(10)) AS Result
  FROM CsmApi_Dashboard_Menu_Item WHERE MenuType = 'Account';
