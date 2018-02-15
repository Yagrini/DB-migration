import java.sql.*;
import javax.swing.JOptionPane;

public class ConnexionDB {
	private Connection connection;
	private Statement st;
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public ConnexionDB(String classname,String url,String user, String password , String type) {
		try {
			Class.forName(classname);
			connection = DriverManager.getConnection(url, user, password);
			st = connection.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null,"Connection for the "+ type +" is failed.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public int executeDDL(String request) {
		int result = 0;
		try {
			result = st.executeUpdate(request);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void closeDB() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
