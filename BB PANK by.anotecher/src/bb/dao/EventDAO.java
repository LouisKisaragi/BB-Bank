package bb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsl.dto.EventDTO;

public class EventDAO
{
	private EventDAO() {}
	private static EventDAO instance = new EventDAO();
	public static EventDAO getinstance()
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
	
	public ArrayList<EventDTO> getAllEvent()
	{
		ArrayList<EventDTO> list = new ArrayList<EventDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from event";
		EventDTO dto = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				dto = new EventDTO(rs.getInt("num"), rs.getString("title"),
						rs.getString("content"), rs.getString("image"), rs.getString("linkvalue"),
						rs.getString("datevalue"), rs.getInt("visiable"));
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public void insertEvent(EventDTO dto)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into event values(numb2_seq.nextval, ?, ?, ?, ?, ?, default)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getImage());
			pstmt.setString(4, dto.getLinkvalue());
			pstmt.setString(5, dto.getDatevalue());
			
			pstmt.executeUpdate();		
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}
	
	public void ModifyEvent(EventDTO dto)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update event set title = ?, content=?, linkvalue = ? , datevalue = ? , image = ? where num = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getLinkvalue());
			pstmt.setString(4, dto.getDatevalue());
			pstmt.setString(5, dto.getImage());
			pstmt.setInt(6, dto.getNum());
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}
	
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn)
	{
		if(rs!=null) // ResultSet 있을 경우
		{
			try 
			{
				rs.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(pstmt!=null)  // PreparedStatement 있을 경우
		{
			try
			{
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
