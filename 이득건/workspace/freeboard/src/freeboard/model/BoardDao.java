package freeboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
	private static BoardDao instance = null;

	private BoardDao() {
	}

	public static BoardDao getInstance() {
		if (instance == null) {
			synchronized (BoardDao.class) {
				instance = new BoardDao();
			}
		}
		return instance;
	}

	// 이제부터 여기에 게시판에서 필요한 작업 기능들을 메서도르 추가하게 된다.
	// 전체 글 개수를 알아오는 메서드
	// int 값, 매개변수 prefaces
	public int getArticleCount(String prefaces) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0; // 실제로 return 될 값
		String sql = ""; // sql 문
		try {
			conn = ConnUtil.getConnection();
			System.out.println("구분 : " + prefaces);
			if (prefaces.equals("1")) {
				sql = "select count (*) from BOARD where bn=4 and preface='1'";
			} else if (prefaces.equals("2")) {
				sql = "select count (*) from BOARD where bn=4 and preface='2'";
			} else if (prefaces.equals("3")) {
				sql = "select count (*) from BOARD where bn=4 and preface='3'";
			} else {
				sql = "select count (*) from BOARD where bn=4";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return count;
	}

	// 글 목록을 가져와서 List로 반환하는 메서드
	public List<BoardDto> getArticles(String prefaces, int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDto> articleList = null; // 실제로 return 될 값
		String sql = ""; // sql문
		try {
			conn = ConnUtil.getConnection();
			System.out.println("리스트 구분 : " + prefaces);
			if (prefaces.equals("1")) {
				sql = "select * from (select * from "
						+ "(select rownum RNUM, NUM, WRITER, PREFACE, SUBJECT, PASS, REGDATE, "
						+ "READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from "
						+ "(select * from BOARD order by REF desc, STEP asc) where bn=4 and preface='1')) "
						+ "where RNUM >= ? and RNUM <= ?";
			} else if (prefaces.equals("2")) {
				sql = "select * from (select * from "
						+ "(select rownum RNUM, NUM, WRITER, PREFACE, SUBJECT, PASS, REGDATE, "
						+ "READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from "
						+ "(select * from BOARD order by REF desc, STEP asc) where bn=4 and preface='2')) "
						+ "where RNUM >= ? and RNUM <= ?";
			} else if (prefaces.equals("3")) {
				sql = "select * from (select * from "
						+ "(select rownum RNUM, NUM, WRITER, PREFACE, SUBJECT, PASS, REGDATE, "
						+ "READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from "
						+ "(select * from BOARD order by REF desc, STEP asc) where bn=4 and preface='3')) "
						+ "where RNUM >= ? and RNUM <= ?";
			} else {
				sql = "select * from (select rownum RNUM, NUM, WRITER, PREFACE,  SUBJECT, PASS, "
						+ "REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from "
						+ "(select * from BOARD order by REF desc, STEP asc) where bn=4) where RNUM >=? and RNUM <=?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				articleList = new ArrayList<BoardDto>(5); // ListAction에 선언된
															// pageSize와 같은 수로
															// 리스트크기를 정하자.
				do {
					BoardDto article = new BoardDto(); // DTO 생성
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setPreface(rs.getString("preface"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					article.setBn(rs.getInt("bn"));
					articleList.add(article);
				} while (rs.next());
			} // end if
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return articleList;
	}

	// 글 저장을 처리하는 메서드
	public void insertArticle(BoardDto article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int number = 0;
		String sql = ""; // sql 문
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(num) from BOARD");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				number = rs.getInt(1) + 1;
			} else {
				number = 1;
			}
			if (num != 0) {// 답글일 경우
				sql = "update BOARD set STEP = STEP+1 where REF = ? and STEP > ?";
				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.executeUpdate();
				step = step + 1;
				depth = depth + 1;
			} else { // 새글일 경우
				ref = number;
				step = 0;
				depth = 0;
			}
			// 쿼리 작성
			sql = "insert into BOARD" + "(NUM, WRITER, PREFACE, SUBJECT, PASS,"
					+ "REGDATE, REF, STEP, DEPTH, CONTENT, IP, BN) "
					+ "values(BOARD_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getPreface());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPass());
			pstmt.setTimestamp(5, article.getRegdate());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, step);
			pstmt.setInt(8, depth);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			pstmt.setInt(11, article.getBn());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

	// 글 내용을 가져오는 메서드
	public BoardDto getArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto article = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("update BOARD set READCOUNT=READCOUNT+1 where NUM = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = conn.prepareStatement("select * from BOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = new BoardDto();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setBn(rs.getInt("bn"));
				article.setPreface(rs.getString("preface"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return article;
	}

	// 글 수정을 처리할 글의 세부 테이터를 받아올 수 있는 방법
	public BoardDto updateGetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto article = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from BOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = new BoardDto();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setPreface(rs.getString("preface"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
				article.setBn(rs.getInt("bn"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return article;
	}

	// 글 수정 처리할 메서드
	public int updateArticle(BoardDto article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		String sql = "";
		int result = -1;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select PASS from BOARD where NUM = ?");
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbpasswd = rs.getString("pass");
				if (dbpasswd.equals(article.getPass())) {
					sql = "update BOARD set WRITER=?, PREFACE=?," + "SUBJECT=?,CONTENT=? where NUM=?";
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getPreface());
					pstmt.setString(3, article.getSubject());
					pstmt.setString(4, article.getContent());
					pstmt.setInt(5, article.getNum());
					pstmt.executeUpdate();
					result = 1; // 수정 성공
				} else {
					result = 0; // 수정 실패
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return result;
	}

	public int deleteArticle(int num, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPass = "";
		int result = -1;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select PASS from BOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPass = rs.getString("pass");
				if (dbPass.equals(pass)) {
					pstmt.close();
					pstmt = conn.prepareStatement("delete from BOARD where NUM = ?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					result = 1; // 삭제 성공
				} else {
					result = 0; // 비밀번호 불일치
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return result;
	}
}
