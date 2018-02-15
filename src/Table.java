import java.sql.*;
import java.util.*;

public class Table {
	private String name;
	private List<String> primarykeys = new ArrayList<String>();
	private List<String> foreignkeys = new ArrayList<String>();
	private int cols;
	//Constructeurs
	public Table() {
		super();
	}
	public Table(String tname, ConnexionDB cdb) throws SQLException {
		super();
		name = tname;
		//Nomre de colonnes dans la table
		String request = "SELECT * FROM "+tname +" ;";
		ResultSet rs = cdb.getSt().executeQuery(request);
		ResultSetMetaData rsmd = rs.getMetaData();
		cols = rsmd.getColumnCount();
		//Les clés primaires de la table
		DatabaseMetaData mtdt = cdb.getConnection().getMetaData();
		ResultSet rsprimary = mtdt.getPrimaryKeys(null, null, tname);
		while (rsprimary.next()) {
			primarykeys.add(rsprimary.getString("COLUMN_NAME"));
		}
		//Les clés étrangers de la table
		ResultSet rsforeign = mtdt.getImportedKeys(null, null, tname);
		while (rsforeign.next()) {
	        String pkTable = rsforeign.getString("PKTABLE_NAME");
	        String pkColName = rsforeign.getString("PKCOLUMN_NAME");
	        String fkTable = rsforeign.getString("FKTABLE_NAME");
	        String fkColName = rsforeign.getString("FKCOLUMN_NAME");
	        String req = "ADD CONSTRAINT `FK_"+fkTable+"_"+fkColName+"` FOREIGN KEY (`"+fkColName+"`) REFERENCES `"+pkTable+"` (`"+pkColName+"`)";
	        foreignkeys.add(req);
	      }
	}
	//Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}
	public List<String> getPrimarykeys() {
		return primarykeys;
	}
	public void setPrimarykeys(List<String> primarykeys) {
		this.primarykeys = primarykeys;
	}
	public List<String> getForeignkeys() {
		return foreignkeys;
	}
	public void setForeignkeys(List<String> foreignkeys) {
		this.foreignkeys = foreignkeys;
	}
	//Ajouter une table
	@SuppressWarnings("unused")
	public void addTable(ConnexionDB cdbsrc, ConnexionDB cdbdst, String type) {		
		String request = "select * from "+this.getName();
		try {
				ResultSet rs = cdbsrc.getSt().executeQuery(request);
				ResultSetMetaData rsmd = rs.getMetaData();
				String sql1 = "CREATE TABLE "+this.getName()+" ( ";
				for(int i=1 ; i<= this.getCols() ; i++) {
					String coltype = getType(type , rsmd.getColumnTypeName(i),rsmd.getColumnDisplaySize(i));
					System.out.println(coltype);
					String size = (coltype=="TEXT" || coltype=="VARCHAR") ? "(" +rsmd.getColumnDisplaySize(i)+")" : "";
					if(i<this.getCols())
						sql1 += " " +rsmd.getColumnName(i)+" "+ coltype + size + " ,";
					else
						sql1 += " " +rsmd.getColumnName(i)+" "+ coltype + size +" );";
				}
				System.out.println("sql1 : "+ sql1);
				int retour1 = cdbdst.executeDDL(sql1);
			}catch (SQLException e) {
				e.printStackTrace();
			}
	}
	// insertion les elements de la table
	public void insertIntoTable(ConnexionDB cdbsrc, ConnexionDB cdbdst) {
		String request = "select * from "+this.getName();
		try {
			ResultSet rs = cdbsrc.getSt().executeQuery(request);
			while(rs.next()) {
				String sql3 = "INSERT INTO "+this.getName()+" VALUES (";
				for(int i=1 ; i<=this.getCols() ; i++) {
					String value = (rs.getString(i)!="null") ? rs.getString(i) : "";
					if(value != null) {
						if(i<this.getCols())
							sql3 += "'"+ value  +"'," ;
						else 
							sql3 += "'" + value +"');" ;
					} else {
						if(i<this.getCols())
							sql3 +=  value  +"," ;
						else 
							sql3 +=  value +");" ;
					}
				}
				cdbdst.executeDDL(sql3);
				System.out.println(sql3);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// insertion des clés primaires
	public void addPrimaryKeys(ConnexionDB cdbdst) {
		
			if(this.getPrimarykeys().size()>0) {
				String sql2 = "ALTER TABLE `"+ this.getName() +"` ADD PRIMARY KEY (";
				for(int i=0 ; i< this.getPrimarykeys().size() ; i++) {
					if(i==0) sql2 +=  "`"+this.getPrimarykeys().get(i) + "`";
					else sql2 +=  ",`"+this.getPrimarykeys().get(i) + "`";
				}
				sql2 += ");";
				System.out.println(sql2);
				cdbdst.executeDDL(sql2);
			}
	}
	//choisr le type convenable
	public static String getType(String typeofserver, String columntype, int size) {
		if(typeofserver == "MySQL") return Converter.toMysqlType(columntype,size);
		else if(typeofserver == "PostgreSQL") return Converter.toPostgresqlType(columntype,size);
		else if(typeofserver == "Oracle") return Converter.toOracleType(columntype,size);
		else if(typeofserver == "Sqlite") return Converter.toSqliteType(columntype,size);
		else  return Converter.toServerType(columntype, size);
	}
}
