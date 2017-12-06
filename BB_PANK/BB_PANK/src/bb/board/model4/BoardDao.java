package bb.board.model4;

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

	// 이제부터 여기에 게시판에서 필요한 작업 기능들을 메소드를 추가하게 된다.
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
			// 지정된 prefaces에 따라서 사용하는 sql문이 달라진다.
			if (prefaces.equals("1") || prefaces.equals("2") || prefaces.equals("3") || prefaces.equals("4")) {
				sql = "select count(*) from BOARD where bn=4 and mem!=2 and preface=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, prefaces);
			} else {
				sql = "select count (*) from BOARD where bn=4 and mem!=2";
				pstmt = conn.prepareStatement(sql);
			}
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
		} // end try-catch
		return count;
	} // end getArticleCount()
	/*---수정1 begin---*/ // 검색기능
	// 전체 글 개수를 알아오는 변수형 메소드(검색 전용)
	// int값, 매개변수 prefaces, keywords

	public int getArticleCounts(String prefaces, String keywords, String condition) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0; // 실제로 return 될 값.
		String sql = ""; // sql 문
		try {
			conn = ConnUtil.getConnection();

			// SQL문 선택
			if (keywords == null) { // 검색 키워드가 없을 때는 검색 키워드와 관련이 없는 SQL문을 사용한다.
				// prefaces 값에 따라 사용하는 SQL문이 달라진다.
				if (prefaces.equals("1") || prefaces.equals("2") || prefaces.equals("3") || prefaces.equals("4")) {
					sql = "select count(*) from BOARD where bn=4 and mem!=2 and preface=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, prefaces);
				} else {
					sql = "select count(*) from BOARD where bn=4 and mem!=2";
					pstmt = conn.prepareStatement(sql);
				}
			} else { // 검색 키워드가 존재할 때는 검색 키워드와 관련이 있는 SQL문을 사용한다.
				// prefaces 값에 따라 사용하는 SQL문이 달라진다.
				if (prefaces.equals("1") || prefaces.equals("2") || prefaces.equals("3") || prefaces.equals("4")) {
					if (condition.equals("1")) {
						sql = "select count(*) from BOARD where bn=4 and mem!=2 and preface=? and writer like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자
															// 입력 가능, 이렇게 별도로
															// 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
					} else if (condition.equals("2")) {
						sql = "select count(*) from BOARD where bn=4  and mem!=2 and preface=? and subject like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자
															// 입력 가능, 이렇게 별도로
															// 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);

					} else if (condition.equals("3")) {
						sql = "select count(*) from BOARD where bn=4 and mem!=2 and preface=? and content like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자
															// 입력 가능, 이렇게 별도로
															// 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
					} else if (condition.equals("4")) {
						sql = "select count(*) from BOARD where bn=4 and mem!=2 and preface=? and (content like ? or subject like ?)";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자
															// 입력 가능, 이렇게 별도로
															// 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
						pstmt.setString(3, keywords);
					} else {
						sql = "select count(*) from BOARD where bn=4 and mem!=2 and preface=? and subject like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자
															// 입력 가능, 이렇게 별도로
															// 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
					}

					// 2017-12-04
				} else {
					if (condition.equals("1")) {
						sql = "select count(*) from BOARD where bn=4 and mem!=2 and writer like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자
															// 입력 가능, 이렇게 별도로
															// 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
					} else if (condition.equals("2")) {
						sql = "select count(*) from BOARD where bn=4 and mem!=2 and subject like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자
															// 입력 가능, 이렇게 별도로
															// 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
					} else if (condition.equals("3")) {
						sql = "select count(*) from BOARD where bn=4 and mem!=2 and content like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자
															// 입력 가능, 이렇게 별도로
															// 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
					} else if (condition.equals("4")) {
						sql = "select count(*) from BOARD where bn=4 and mem!=2 and (content like ? or subject like ?)";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자
															// 입력 가능, 이렇게 별도로
															// 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
						pstmt.setString(2, keywords);

					} else {
						sql = "select count(*) from BOARD where bn=4 and mem!=2 and subject like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자
															// 입력 가능, 이렇게 별도로
															// 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
					}
				}
			}
			System.out.println("카운트가 사용한 SQL : " + sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {

					rs.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {

					pstmt.close();
				} catch (SQLException e) {
				}

			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		} // end try-catch
		return count;
	}// end getArticleCounts()
	/*---수정1 end---*/

	// 글 목록을 가져와서 List로 반환하는 메서드
	// List값, String 매개변수가 하나, int 매개변수가 2개
	public List<BoardDto> getArticles(String prefaces, int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDto> articleList = null; // 실제로 return 될 값
		String sql = ""; // sql문
		try {
			conn = ConnUtil.getConnection();
			if (prefaces.equals("1") || prefaces.equals("2") || prefaces.equals("3") || prefaces.equals("4")){
					 
				sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN, MEM from (select * from BOARD order by REF desc, STEP asc) where bn=4 and mem!=2 and preface=?)) where RNUM >= ? and RNUM <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, prefaces);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			} else {
				sql = "select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN, MEM from (select * from BOARD order by REF desc, STEP asc) where bn=4 and mem!=2) where RNUM >= ? and RNUM <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}
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
					article.setMem(rs.getInt("mem"));
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
		} // end try-catch
		return articleList;
	} // getArticles()

	/* 수정2 begin */
	// 글 목록을 가져와서 List로 변환하는 변수형 메소드(공지 전용)
			// List값, String 매개변수가 하나, int 매개변수가 2개
			public List<BoardDto> getArticlesNotice(String bn) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<BoardDto> articleList = null;	// 실제로 return 될 값
				String sql = "";						// sql문
				try {
					conn = ConnUtil.getConnection();
					sql = "select NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN, MEM from (select * from BOARD order by REF desc, STEP asc) where bn=? and mem!=2";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, bn);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						articleList = new ArrayList<BoardDto>(5); // ListAction에 선언된 pageSize와 같은 수로 리스트크기를 정하자.
						do {
							BoardDto article = new BoardDto();	// DTO 생성
							article.setNum(rs.getInt("num"));
							article.setPreface(rs.getString("preface"));
							article.setWriter(rs.getString("writer"));
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
							article.setMem(rs.getInt("mem"));
							articleList.add(article);
						} while(rs.next());
					}	// end if
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
					if(rs != null) {
						try {
							rs.close();
							} catch(SQLException e) {
								
							}
					}
					if(pstmt != null) {
						try{
							pstmt.close();
							} catch(SQLException e) {
								
							}
					}
					if(conn != null) {
						try{
							conn.close();
							} catch(SQLException e) {
								
							}
					}
				}	// end try-catch
				return articleList;
			}		// getArticlesNotice()
			
	// 글 목록을 가져와서 List로 변환하는 변수형 메소드(검색 전용)
	// List값, String 매개변수가 둘, int 매개변수가 2개
	public List<BoardDto> getArticless(String prefaces, String keywords, String condition, int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDto> articleList = null; // 실제로 return 될 값
		String sql = ""; // sql문
		try {
			conn = ConnUtil.getConnection();
			// prefaces 값에 따라 달라진다.
						if(prefaces.equals("1") || prefaces.equals("2") || prefaces.equals("3") || prefaces.equals("4")) {
							// 검색 조건에 따라 다른 SQL문을 사용하도록 한다.
							if(condition.equals("1")) {
								sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=4 and mem!=2 and preface=? and writer like ?)) where RNUM >= ? and RNUM <= ?";
								keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, prefaces);
								pstmt.setString(2, keywords);
								pstmt.setInt(3, start);
								pstmt.setInt(4, end);
							} else if(condition.equals("2")) {
								sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=4 and mem!=2 and preface=? and subject like ?)) where RNUM >= ? and RNUM <= ?";
								keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, prefaces);
								pstmt.setString(2, keywords);
								pstmt.setInt(3, start);
								pstmt.setInt(4, end);
							} else if(condition.equals("3")) {
								sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=4 and mem!=2 and preface=? and content like ?)) where RNUM >= ? and RNUM <= ?";
								keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, prefaces);
								pstmt.setString(2, keywords);
								pstmt.setInt(3, start);
								pstmt.setInt(4, end);
							} else if(condition.equals("4")) {
								sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=4 and mem!=2 and preface=? and (subject like ? or content like ?))) where RNUM >= ? and RNUM <= ?";
								keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, prefaces);
								pstmt.setString(2, keywords);
								pstmt.setString(3, keywords);
								pstmt.setInt(4, start);
								pstmt.setInt(5, end);
							}
			} else { //preface가 존재하지 않는 모든 글을 볼때
				// 검색 조건에 따라 다른 SQL문을 사용하도록 한다.
				if (condition.equals("1")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=4 and writer like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력
														// 가능, 이렇게 별도로 키워드를
														// 지정해줘야 한다.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keywords);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
				} else if (condition.equals("2")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN, MEM from (select * from BOARD order by REF desc, STEP asc) where bn=4 and subject like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력
														// 가능, 이렇게 별도로 키워드를
														// 지정해줘야 한다.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keywords);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
				} else if (condition.equals("3")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN, MEM from (select * from BOARD order by REF desc, STEP asc) where bn=4 and content like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력
														// 가능, 이렇게 별도로 키워드를
														// 지정해줘야 한다.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keywords);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
				} else if (condition.equals("4")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN, MEM from (select * from BOARD order by REF desc, STEP asc) where bn=4 and (subject like ? or content like ?))) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력
														// 가능, 이렇게 별도로 키워드를
														// 지정해줘야 한다.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keywords);
					pstmt.setString(2, keywords);
					pstmt.setInt(3, start);
					pstmt.setInt(4, end);
				}
			}
						System.out.println("리스트가 사용한 SQL : " + sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				articleList = new ArrayList<BoardDto>(5); // ListAction에 선언된
															// pageSize와 같은 수로
															// 리스트크기를 정하자.
				do {
					BoardDto article = new BoardDto(); // DTO 생성
					article.setNum(rs.getInt("num"));
					article.setPreface(rs.getString("preface"));
					article.setWriter(rs.getString("writer"));
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
					article.setMem(rs.getInt("mem"));
					articleList.add(article);
				} while (rs.next());
			} // end if
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		} // end try-catch
		return articleList;
	} // getArticless()
	/* 수정2 end */
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
					+ "REGDATE, REF, STEP, DEPTH, CONTENT, IP, BN, MEM) "
					+ "values(BOARD_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			pstmt.setInt(12, article.getMem());
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
				article.setMem(rs.getInt("mem"));
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
		} // end try-catch
		return article;
	} // end getArticle()

	// 글 수정을 처리할 글의 세부 테이터를 받아올 수 있는 메소드
	public BoardDto updateGetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto article = null;
		String sql = ""; // sql문
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
				article.setMem(rs.getInt("mem"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		} // end try-catch
		return article;
	}// end updateGetArticle()

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
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	// DB에서 글을 삭제하는 메소드
	public int deleteArticle(int num, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = ""; // sql문
		String dbPass = "";
		int result = -1; // flag
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
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
		return result;
	}
}
