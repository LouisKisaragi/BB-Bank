package jsl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsl.dto.QnADTO;
import jsl.dto.ReservationDTO;
import jsl.dto.PointManagerDTO;

public class MypageDAO {
	
	private static MypageDAO instance = new MypageDAO();

	public static MypageDAO getinstance() {
		return instance;
	}

	public Connection getConnection() throws Exception {
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/dbcp");
		conn = ds.getConnection();

		return conn;
	}

	public ArrayList<PointManagerDTO> getpointAllcontent(String id) {
		ArrayList<PointManagerDTO> list = new ArrayList<PointManagerDTO>();
		String sql = "select * from pointlog where guest_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PointManagerDTO dto = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new PointManagerDTO(rs.getString("guest_id"), rs.getInt("p_calcul"), rs.getString("p_cont"),
						rs.getDate("p_date"));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}

		return list;
	}
	
	public int getUserPoint(String id)
	{
		int my_point = 0;
		String sql = "select point from guest where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				my_point = rs.getInt("point");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return my_point;
	}
	
	public ArrayList<QnADTO> getqnaAllcontent(String id) {
		ArrayList<QnADTO> list = new ArrayList<QnADTO>();
		String sql = "select * from board2 where guest_id=? order by num desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnADTO dto = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new QnADTO(rs.getInt("num"), rs.getString("guest_id"), rs.getString("title"),
						rs.getString("contents"),rs.getString("answer"));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}

		return list;
	}
	
	
	public ArrayList<ReservationDTO> getreservationAllcontent(String id) {
		ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
		String sql = "select * from reservation where guest_id=? order by num desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReservationDTO dto = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new ReservationDTO(rs.getInt("num"),rs.getString("guest_id"),
						rs.getInt("gender"),rs.getInt("contents"),
						rs.getDate("res_day"),rs.getString("res_time"),rs.getInt("designer_id"),rs.getInt("visiable"));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}

		return list;
	}
	
	public int writeQnAboard(QnADTO qnaDTO)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		String sql = "insert into board2 values(numb2_seq.nextval,?,?,?,default,default)";
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnaDTO.getGuest_id());
			pstmt.setString(2, qnaDTO.getTitle());
			pstmt.setString(3, qnaDTO.getContents());
			
			pstmt.executeUpdate();

		}catch(Exception e)	{
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		
		return result;
	}
	
	
	
	//////////////////////////////////// 닫기 ////////////////////////////////
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs != null) // ResultSet �ݱ�
		{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pstmt != null) // PreparedStatement �ݱ�
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) // Connection �ݱ�
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
