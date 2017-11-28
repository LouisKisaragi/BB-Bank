package board.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//DB 컬넥션을 관리하는 Connection Pool을 사용할 수 있도록 ConnUtil 클래스 작성
public class ConnUtil {
	
	private static DataSource ds;
	static {
			try {
					InitialContext ctx = new InitialContext();
					ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myOracle");				
			} catch (NamingException e) { }
	}
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
