package bb.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import bb.dto.MemberDTO;
import bb.dto.PointManagerDTO;

public class AdminDAO {
	
	private AdminDAO() {}
	private static AdminDAO instance = new AdminDAO();
	public static AdminDAO getinstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception {
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/dbcp");
		conn = ds.getConnection();
		
		return conn;
	}
	
	/////// 관리자 계정 로그인 처리//////
	public int checkLogin(String id, String pass) {
		int result = -1;
		// 기본값 -1. 인증 안됨.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pass from Menber where id=? and admin=1";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("pass").equals(pass)) {
					result = 1;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return result;
	}
	
	/////// 모든 회원의 정보를 가져옴 //////
	public ArrayList<MemberDTO> listMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();	
		MemberDTO menberDTO = null;
		
		String sql = "select * from Menber order by id desc";
		
		try	{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				menberDTO = new MemberDTO(rs.getString("id"), rs.getString("pass"), rs.getString("name"), 
				        rs.getString("email"), rs.getInt("point"), rs.getDate("joindate"), rs.getInt("admin"), rs.getInt("visiable"));
				
				list.add(menberDTO);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	///// 모든 포인트 로그의 정보를 가져옴. /////
	public ArrayList<PointManagerDTO> LoadPointLog() {
		ArrayList<PointManagerDTO> list = new ArrayList<PointManagerDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PointManagerDTO dto = null;
		
		String sql = "select * from pointlog";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new PointManagerDTO(String guest_id, int p_calcul, String p_cont, Date p_date);
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public void insertPointlog(String id, int addPoint, String pointlog) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql_pointlog = "insert into pointlog values(?,?,?,sysdate)";
		try	{ // 그 포인트 수정한 내역을 포인트로그에 입력함.
			conn = getConnection();
			pstmt = conn.prepareStatement(sql_pointlog);

			pstmt.setString(1, id);
			pstmt.setInt(2, addPoint);
			pstmt.setString(3, pointlog);
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}
	
	public void insertAnswer(String answer, int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update board2 set answer=? where num = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, answer);
			pstmt.setInt(2, num);
			
			pstmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}

	public void deleteQnA(String num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from board2 where num=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}
	
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) // ResultSet 있을 경우
		{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(pstmt!=null)  // PreparedStatement 있을 경우
		{
			try	{
				pstmt.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(conn!=null) // Connection 있을 경우
		{
			try 
			{
				conn.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}	
}
