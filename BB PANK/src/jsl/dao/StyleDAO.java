package jsl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsl.dto.StyleDTO;


public class StyleDAO 
{	
	private StyleDAO(){}
	private static StyleDAO instance = new StyleDAO();
	public static StyleDAO getinstance()
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
	
	public ArrayList<StyleDTO> getAllcontent()
	{
		ArrayList<StyleDTO> list = new ArrayList<StyleDTO>();
		String sql = "select * from style";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StyleDTO dto = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				dto = new StyleDTO(rs.getInt("num"), rs.getString("sort"),
						rs.getString("title"), rs.getString("image"),
						rs.getString("content"), rs.getInt("visiable"));
				list.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}		
		return list;
	}
	
	public void insertStyle(StyleDTO dto)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into style values(numb2_seq, ?, ?, ?, ?, default)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSort());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getImage());
			pstmt.setString(4, dto.getContent());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}
	
	public void modifyStyle(StyleDTO dto)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update style set title=?, sort=?, image=?, content=? where num=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getSort());
			pstmt.setString(3, dto.getImage());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getNum());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}
	
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn)
	{
		if(rs!=null) // ResultSet �ݱ�
		{
			try 
			{
				rs.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(pstmt!=null)  // PreparedStatement �ݱ�
		{
			try
			{
				pstmt.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(conn!=null) // Connection �ݱ�
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
