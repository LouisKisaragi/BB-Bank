package freeboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
	private static BoardDao instance = null;
	private BoardDao(){}
	public static BoardDao getInstance(){
		if(instance == null){
			synchronized(BoardDao.class){
				instance = new BoardDao();
			}
		}
		return instance;
	}
	//이제부터 여기에 게시판에서 필요한 작업 기능들을 메서도르 추가하게 된다.
	//전체 글 개수를 알아오는 메서드
	public int getArticleCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from BOARD");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (rs != null)	try {rs.close(); } catch (SQLException e) {}
			if (pstmt != null) try {pstmt.close();	} catch (SQLException e) {}
			if (conn != null) try {conn.close(); } catch (SQLException e) {}
		}
		return count;
	}
	//글 목록을 가져와서 List로 반환하는 메서드
	public List<BoardDto> getArticles(int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDto> articleList = null;
		try {
			conn = ConnUtil.getConnection();
			String sql = "select * from " 
					+ "(select rownum RNUM, NUM, WRITER,"
					+ "EMAIL, SUBJECT, PASS, REGDATE,"
					+ "READCOUNT, REF, STEP, DEPTH, CONTENT, IP from "
					+ "(select * from BOARD order by REF desc, STEP asc)) "
					+ "where RNUM >= ? and RNUM <=?";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				articleList = new ArrayList<BoardDto>(5);
				do {
					BoardDto article = new BoardDto();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)	try {rs.close(); } catch (SQLException e) {}
			if (pstmt != null) try {pstmt.close(); } catch (SQLException e) {}
			if (conn != null) try {conn.close(); } catch (SQLException e) {}
		}
		return articleList;
	}
}
