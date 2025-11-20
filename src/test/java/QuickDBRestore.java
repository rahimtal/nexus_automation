
public class QuickDBRestore {

    public static void restoreDatabase() {
        String serverName = "DESKTOP-QU86F3Q";
        String username = "sa";
        String password = "cogs";
        String databaseName = "TWO";
        String backupFilePath = "B:\\DatabaseBackup\\TEST_CSM_58";
        try {
            // Drop database if exists
            Process dropDb = Runtime.getRuntime().exec(new String[] {
                    "sqlcmd",
                    "-S", serverName,
                    "-U", username,
                    "-P", password,
                    "-Q",
                    "IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name ='" + databaseName
                            + "') BEGIN ALTER DATABASE  " + databaseName
                            + " SET OFFLINE WITH ROLLBACK IMMEDIATE; DROP DATABASE "
                            + databaseName + "; END"
            });
            dropDb.waitFor();

            // Restore database
            Process restoreDb = Runtime.getRuntime().exec(new String[] {
                    "sqlcmd",
                    "-S", serverName,
                    "-U", username,
                    "-P", password,
                    "-Q", "RESTORE DATABASE " + databaseName + " FROM DISK='" + backupFilePath + "' WITH REPLACE"
            });
            restoreDb.waitFor();

            System.out.println("Restore DB ==============================");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}