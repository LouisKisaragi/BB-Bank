package bb.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsl.dto.ReservationDTO;

public class BookingDAO {
	private BookingDAO() {
	}

	private static BookingDAO instance = new BookingDAO();

	public static BookingDAO getinstance() {
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

	// 예약하기
	public void writeReservation(ReservationDTO reservationDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into reservation values(numb2_seq.nextval,?,?,?,to_date(?,'yyyy-MM-dd'),?,?,default)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservationDTO.getGuest_id());
			pstmt.setInt(2, reservationDTO.getGender());
			pstmt.setInt(3, reservationDTO.getContents());
			pstmt.setDate(4, new java.sql.Date(reservationDTO.getRes_day().getTime()));
			pstmt.setString(5, reservationDTO.getRes_time());
			pstmt.setInt(6, reservationDTO.getDesigner_id());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}

	// 예약 수정
	public void modifyReservation(ReservationDTO reservationDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "update reservation set guest_id=?, gender=?, contents=?, res_day=?, res_time=?, designer_id=? where num=? ";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservationDTO.getGuest_id());
			pstmt.setInt(2, reservationDTO.getGender());
			pstmt.setInt(3, reservationDTO.getContents());
			pstmt.setDate(4, new java.sql.Date(reservationDTO.getRes_day().getTime()));
			pstmt.setString(5, reservationDTO.getRes_time());
			pstmt.setInt(6, reservationDTO.getDesigner_id());
			pstmt.setInt(7, reservationDTO.getNum());

			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}

	// 예약 전체 리스트 출력
	public ArrayList<ReservationDTO> getAllcontent() {
		ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
		String sql = "select * from reservation order by num desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReservationDTO dto = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new ReservationDTO(rs.getInt("num"), rs.getString("guest_id"), rs.getInt("gender"),
						rs.getInt("contents"), rs.getDate("res_day"), rs.getString("res_time"), rs.getInt("designer_id"),
						rs.getInt("visiable"));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}

		return list;
	}

	// 예약 리스트중 아이디 날짜값으로 출력
	public ArrayList<ReservationDTO> idgetcontent(String id) {
		ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
		String sql = "select * from reservation where guest_id=?";
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
				dto = new ReservationDTO(rs.getInt("num"), rs.getString("guest_id"), rs.getInt("gender"),
						rs.getInt("contents"), rs.getDate("res_day"), rs.getString("res_time"), rs.getInt("designer_id"),
						rs.getInt("visiable"));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}

		return list;
	}

	// 예약 리스트중 날짜값 기준으로 뽑아옴
	/* public ArrayList<ReservationDTO> dategetcontent(Date res_day) {
		ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
		String sql = "select * from reservation where res_day=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReservationDTO dto = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(res_day));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new ReservationDTO(rs.getInt("num"), rs.getString("guest_id"), rs.getInt("gender"),
						rs.getInt("contents"), rs.getDate("res_day"), rs.getString("res_time"), rs.getInt("designer_id"),
						rs.getInt("visiable"));
				list.add(dto);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}

		return list;
	} */

	// 예약 시간경과후 요거로 표시안되게 바꿀지도..?
	public void visiableBoard(String num, int visiable) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "update resvation set visiable=0 where num=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, visiable);
			pstmt.setString(2, num);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}

	// 이걸로 예약취소 랄까.. 삭제!
	public void res_Delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "delete from reservation where num=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}
	
	public String getOneDesignerName(int des_no)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String returnValue = null;
		String sql = "select designer_name from designer where designer_id = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, des_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				returnValue = rs.getString("designer_name");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return returnValue;
	}

	public Boolean checkReservation(Date res_day, String res_time, int designer_id)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean returnValue = false;
		String sql = "select * from reservation where res_day = ? and res_time = ? and designer_id = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(res_day.getTime()));
			pstmt.setString(2, res_time);
			pstmt.setInt(3, designer_id);
			System.out.println(new java.sql.Date(res_day.getTime()));
			System.out.println(res_time);
			System.out.println(designer_id);
			
			rs = pstmt.executeQuery();
			System.out.println(rs);
			
			if(!rs.next())
			{
				returnValue = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return returnValue;
	}

	
	//문을 닫으시오
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs != null) //
		{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pstmt != null) //
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
