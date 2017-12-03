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
	
	//////////////// ID �ߺ� üũ ///////////////
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
	
	///////////////////// �α��� üũ /////////////////////////
	public int checkLogin(String id, String pass)
	{
		int result = -1; 
		// -1 : ���̵� �ٸ�
		// 0 : ��й�ȣ�� �ٸ�
		// 1 : �α��� ����
		
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

			if(rs.next()) // ���� ���� üũ
			{ 
				if(rs.getString("pass").equals(pass)) 
				{ // �����ߴٸ� db����� �Է¹��� ����� ��
					result = 1;
				}else 
				{ // ����� ���� ���� �� 0 ��ȯ 
					result = 0;
				}
			}else // ���� ����� ���ٸ� id���� �߸� ��.
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
	
	///////////////////// �α��� üũ �Ŀ� �ش� id�� ���� ���� ������ //////////////////////
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
	
	///////////////////////////// ȸ�� ����Ʈ ���� /////////////////////////////////
	public int getUserPoint(String id)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select point from Member where id=?";
		int resultPoint = -1; // �⺻�� false
		
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
	
	///////////////////////////// ȸ�� ����Ʈ ���� ////////////////////////////
	public void modifyPoint(String id, int point)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql_Member = "update Member set point=? where id=?";
		
		try
		{ // ������ ����Ʈ�� DB�� �Է���.
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
	
	/////////////////////////////////// ȸ�� ���� /////////////////////////////////
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
	
	/////////////////////////////////// ȸ�� ���� ���� /////////////////////////////////
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
	
	///////////////////////////// ȸ�� ���� ////////////////////////////////	
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
	
	//////////////////////////// ���� �ݱ� ////////////////////////////////
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
