import java.sql.SQLException;

public class Main {
	
	public static void migrate(String dbnamesrc,String source , String urlsrc,String usersrc,String passwordsrc,String dbnamedst,String destination,
			String urldst, String userdst , String passworddst) {
			
			try {
				String classnamesrc = Main.chooseClassName(source);
				String classnamedst = Main.chooseClassName(destination);
				
				ConnexionDB cdbdst = new ConnexionDB(classnamedst, urldst, userdst, passworddst,"Destination");
				ConnexionDB cdbsrc = new ConnexionDB(classnamesrc, urlsrc+dbnamesrc, usersrc, passwordsrc,"Source");
				DataBase db = new DataBase(dbnamedst,cdbsrc);
				db.createDataBase(cdbdst, cdbsrc);
				db.addDataBase(cdbdst, cdbsrc ,destination);
			} catch(SQLException e){
				e.printStackTrace();
			}
	}
	public static String chooseClassName(String type) {
		switch (type)
        {
            case "MySQL":
                return "com.mysql.jdbc.Driver";
                
            case "PostgreSQL":
                return "org.postgresql.Driver";
                
            case "Oracle":
                return "oracle.jdbc.driver.OracleDriver";
                
            case "SQL Server":
                return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                
            case "Sqlite":
                return "org.sqlite.JDBC";
                
            default:
                return "com.mysql.jdbc.Driver";
        }
	}
	
}
