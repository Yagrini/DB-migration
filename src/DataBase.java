import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DataBase {
	private String name;
	private List<String> tables = new ArrayList<String>();
	private List<Table> allTables = new ArrayList<Table>();
	
	// Constructeurs
	public DataBase() {
		super();
	}
	public DataBase(String dbname, ConnexionDB cdb) throws SQLException {
		DatabaseMetaData mtdt = cdb.getConnection().getMetaData();
		name = dbname;
	    ResultSet rs = mtdt.getTables(cdb.getConnection().getCatalog(), null, "%", null);
	    while (rs.next()) {
	    		tables.add(rs.getString("TABLE_NAME"));
	    		System.out.println(rs.getString("TABLE_NAME"));
	    }
	}
	// Getters and Setters
	public List<String> getTables() {
		return tables;
	}
	public void setTables(List<String> tables) {
		this.tables = tables;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Table> getAllTables() {
		return allTables;
	}
	public void setAllTables(Table oneTable) {
		allTables.add(oneTable);
	}
	//Créer database
	public void createDataBase(ConnexionDB cdbdst,ConnexionDB cdbsrc) {
		String sql1 = "CREATE DATABASE "+ this.getName() + " ;";
		cdbdst.executeDDL(sql1);
		System.out.println(sql1);
	}
	// Ajouter une base de données avec ses propres tables
	public void addDataBase(ConnexionDB cdbdst,ConnexionDB cdbsrc, String type) {
		this.addTablesToDataBase(cdbdst, cdbsrc,type);
		this.addAllPrimaryKeysToDataBase(cdbdst);
		this.insertIntoTablesToDataBase(cdbdst, cdbsrc);
		this.addAllForeignKeysToDataBase( cdbdst);
		JOptionPane.showMessageDialog(null, "Migration is done successfully.");
		cdbdst.closeDB();
		cdbsrc.closeDB();
	}
	//Ajout des tables et leurs éléments
	public void addTablesToDataBase(ConnexionDB cdbdst,ConnexionDB cdbsrc,String type) {
		try {
			String sql2 = "USE "+this.getName() +" ;";
			cdbdst.executeDDL(sql2);
			int size = this.getTables().size();
			for(int i=0 ; i<size ;i++) {
				Table tablei = new Table(this.getTables().get(i),cdbsrc);
				this.setAllTables(tablei);
				tablei.addTable(cdbsrc,cdbdst,type);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Ajout les clés primaires et leurs éléments
	public void addAllPrimaryKeysToDataBase(ConnexionDB cdbdst) {
		String sql2 = "USE "+this.getName() +" ;";
		cdbdst.executeDDL(sql2);
		int size = this.getTables().size();
		for(int i=0 ; i<size ;i++) {
			this.getAllTables().get(i).addPrimaryKeys(cdbdst);
		}
	}
	//Ajout des clés étrangères des tables
	public void addAllForeignKeysToDataBase(ConnexionDB cdbdst) {
		String sql2 = "USE "+this.getName() + " ;";
		cdbdst.executeDDL(sql2);
		int size = this.getTables().size();
		//Ajout des tables et leurs éléments
		for(int i=0 ; i<size ;i++) {
			if(this.getAllTables().get(i).getForeignkeys().size()>0) {
				String req = "ALTER TABLE `"+ this.getAllTables().get(i).getName() + "`";
				for(int j=0 ; j<this.getAllTables().get(i).getForeignkeys().size() ; j++) {
					if(j==0) req += this.getAllTables().get(i).getForeignkeys().get(j);
					else req += ", " + this.getAllTables().get(i).getForeignkeys().get(j);
				}
				req += ";";
				cdbdst.executeDDL(req);
			}
		}
	}
	//Insertion les éléments des tables
	public void insertIntoTablesToDataBase(ConnexionDB cdbdst,ConnexionDB cdbsrc) {
		int size = this.getTables().size();
		for(int i=0 ; i<size ;i++) {
			this.getAllTables().get(i).insertIntoTable(cdbsrc,cdbdst);
		}
	}
}
