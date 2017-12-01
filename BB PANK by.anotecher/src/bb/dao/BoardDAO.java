package bb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//import jsl.dto.BoardDTO;
//import jsl.dto.QnADTO;

public class BoardDAO
{
	private BoardDAO(){}
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getinstance()
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
	
	/////////////////////////////////// 글쓰기 /////////////////////////////////////
	public int writeBoard(BoardDTO boardDTO)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
	
		String sql = "insert into board values(numb1_seq.nextval,?,?,?,?,default)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getId());
			pstmt.setString(2, boardDTO.getTitle());
			pstmt.setString(3, boardDTO.getContents());
			pstmt.setString(4, boardDTO.getImage());
			result = pstmt.executeUpdate();

		}catch(Exception e)	{
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		
		return result;
	}
	
/////////////////////////////////// 글 죄다빼오기 /////////////////////////////////////
	public ArrayList<BoardDTO> getAllcontent(String message)
	{
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String sql = null;
		if(message.equals("all"))
		{
			sql = "select * from board  order by num desc";
		}else if(message.equals("nodel"))
		{
			sql = "select * from board where visiable = 1 order by num desc";
		}
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO dto = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				dto = new BoardDTO(rs.getInt("num"), rs.getString("guest_id"),
						rs.getString("title"), rs.getString("contents"),
						rs.getString("image"), rs.getInt("visiable"));
				list.add(dto);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public ArrayList<QnADTO> getAllQnA() {
		ArrayList<QnADTO> list = new ArrayList<QnADTO>();
		String sql = "select * from board2 order by num desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnADTO dto = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				dto = new QnADTO(rs.getInt("num"), rs.getString("guest_id"),
								rs.getString("title"), rs.getString("contents"), rs.getString("answer"));
				list.add(dto);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	

	
	
	
	
/////////////////////////////////// 글삭제&복구 /////////////////////////////////////
	public void deleteBoard(String num, int visiable)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update board set visiable=? where num=?";
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, visiable);
			pstmt.setString(2, num);
			pstmt.executeUpdate();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			close(null, pstmt, conn);
		}
	}
	 //////////////////////////////////// 글수정 ////////////////////////////////
	public void modifyBoard(BoardDTO boardDTO)
	  {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    String sql = "update board set title=?, contents=?, image=? where num=? ";

	    try
	    {
	      conn = getConnection();
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, boardDTO.getTitle());
	      pstmt.setString(2, boardDTO.getContents());
	      pstmt.setString(3, boardDTO.getImage());
	      pstmt.setInt(4, boardDTO.getNum());

	      pstmt.executeQuery();

	    } catch (Exception e)
	    {
	      e.printStackTrace();
	    } finally
	    {
	      close(null, pstmt, conn);
	    }
	  }
	
	///////// 글 하나 가져오기 ///////
	public BoardDTO getOneNote(int num)
	{
		BoardDTO dto = null;
		Connection conn = null;
		ResultSet rs = null;
	    PreparedStatement pstmt = null;
	    String sql = "select * from board where num = ?";
	    
	    try {
	    	conn = getConnection();
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, num);
	    	rs = pstmt.executeQuery();
	    	
	    	if(rs.next())
	    	{
	    		dto = new BoardDTO(rs.getInt("num"), rs.getString("guest_id"),
	    				rs.getString("title"), rs.getString("contents"),
	    				rs.getString("image"), rs.getInt("visiable"));
	    	}
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	close(rs, pstmt, conn);
	    }

		return dto;
	}
	
	//////////////////////////////////// 닫기 ////////////////////////////////
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
