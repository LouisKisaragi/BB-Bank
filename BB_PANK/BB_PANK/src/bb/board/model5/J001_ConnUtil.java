package bb.board.model5;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class J001_ConnUtil {
	public static String ID = "scottt";
	public static String PASSWORD = "tiger";
	public static String IP = "localhost";
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", ID, PASSWORD);
	}
}
