package bb.board.model2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {
	public static String ID="scott";
	public static String PASSWORD="tiger";
	public static String IP="localhost";
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:oracle:thin:@"+IP+":1521:xe", ID, PASSWORD);
	}
}
