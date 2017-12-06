package bb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bb.dto.MemberDTO;

public class MemberDAO {
	private MemberDAO(){}
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getinstance()
	{
		return instance;
	}
	
	public Connection getConnection() throws Exception
	{
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/dbcp");
		conn = ds.getConnection();
		
		return conn;
	}
	
	//////////////// ID 중복 체크 ///////////////
	public int idCheck(String id)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from Member where id=?";
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = 1;
			} else result = -1;			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return result;
	}
	
	///////////////////// 로그인 체크 /////////////////////////
	public int checkLogin(String id, String pass)
	{
		int result = -1; 
		// -1 : 아이디 다름
		// 0 : 비밀번호가 다름
		// 1 : 로그인 성공
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pass from Member where id=?";
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();

			if(rs.next()) // 쿼리 성공 체크
			{ 
				if(rs.getString("pass").equals(pass)) 
				{ // 성공했다면 db비번과 입력받은 비번을 비교
					result = 1;
				}else 
				{ // 비번이 맞지 않을 시 0 반환 
					result = 0;
				}
			}else // 쿼리 결과가 없다면 id값이 잘못 됨.
			{ 
				result = -1;
			}			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			close(rs, pstmt, conn);
		}

		return result;
	}
	
	///////////////////// 로그인 체크 후에 해당 id의 정보 전부 가져옴 //////////////////////
	public MemberDTO successLogin(String id){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from Member where id=?";
		MemberDTO MemberDTO = null;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				MemberDTO = new MemberDTO(rs.getString("id"), rs.getString("pass"), rs.getString("name"), 
										  rs.getString("email"), rs.getInt("point"),rs.getDate("joindate"), 
										  rs.getInt("admin"), rs.getInt("visiable"));
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			close(rs, pstmt, conn);
		}
		
		return MemberDTO;
	}
	
	///////////////////////////// 회원 포인트 추출 /////////////////////////////////
	public int getUserPoint(String id)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select point from Member where id=?";
		int resultPoint = -1; // 기본값 false
		
		try	{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) resultPoint = rs.getInt("point");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return resultPoint;
	}
	
	///////////////////////////// 회원 포인트 수정 ////////////////////////////
	public void modifyPoint(String id, int point)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql_Member = "update Member set point=? where id=?";
		
		try
		{ // 수정한 포인트를 DB에 입력함.
			conn = getConnection();
			pstmt = conn.prepareStatement(sql_Member);

			pstmt.setInt(1, point);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			close(null, pstmt, conn);
		}
	}
	
	/////////////////////////////////// 회원 가입 /////////////////////////////////
	public boolean insertMember(MemberDTO Member)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into Member values(?,?,?,?,?,default,default,default,default)";
		boolean returnValue = false;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Member.getId());
			pstmt.setString(2, Member.getPass());
			pstmt.setString(3, Member.getName());
			pstmt.setString(5, Member.getEmail());

			int result = pstmt.executeUpdate();
			if(result > 0)
			{
				returnValue = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		
		return returnValue;
	}
	
	/////////////////////////////////// 회원 정보 수정 /////////////////////////////////
	public int modifyMember(MemberDTO MemberDTO)
	{		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update Member set pass=?, name=?, email=? where id=?";
		int result = 0;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberDTO.getPass());
			pstmt.setString(2, MemberDTO.getName());
			pstmt.setString(4, MemberDTO.getEmail());
			pstmt.setString(5, MemberDTO.getId());
			
			result = pstmt.executeUpdate();

			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(null, pstmt, conn);
		}
		
		return result;
	}
	
	///////////////////////////// 회원 삭제 ////////////////////////////////	
	public int deleteMember(String id, int visiable)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update Member set visiable=? where id=?";
		
		int result = 0;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, visiable);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			close(null, pstmt, conn);
		}
		
		return result;
	}
	
	//////////////////////////// 쓴거 닫기 ////////////////////////////////
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn)
	{
		if(rs!=null) // ResultSet
		{
			try 
			{
				rs.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(pstmt!=null)  // PreparedStatement 
		{
			try
			{
				pstmt.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(conn!=null) // Connection 
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