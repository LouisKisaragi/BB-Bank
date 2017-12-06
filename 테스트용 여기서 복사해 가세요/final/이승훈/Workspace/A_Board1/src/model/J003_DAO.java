package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 각종 메소드, 특수한 변수들이 포함된 클래스
// 주의 : 메소드 같은것들을 표현할 때 웬만하면 표시 형식을 통일하도록 하자.
// 예시 : sql문을 별도의 String 변수로 선언해서 거기다 써넣으려 한다면 모든 메소드에 다 적용하자.
public class J003_DAO {
	private static J003_DAO instance = null;
	private J003_DAO() {
		// 생성자
	}
	public static J003_DAO getInstance() {
		if(instance == null) {
			synchronized(J003_DAO.class) {
				instance = new J003_DAO();
			}
		}
		return instance;
	}
	
	// 전체 글 개수를 알아오는 변수형 메소드
	// int값, 매개변수 prefaces
	public int getArticleCount(String prefaces) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int count = 0;		// 실제로 return 될 값
		String sql = "";	// sql문
		try {
			conn = J001_ConnUtil.getConnection();
			// 지정된 prefaces에 따라서 사용하는 sql문이 달라진다.
			if(prefaces.equals("a") || prefaces.equals("b") || prefaces.equals("c") || prefaces.equals("d") || prefaces.equals("e")) {
				sql = "select count(*) from BOARD where bn=5 and mem!=2 and preface=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, prefaces);
			} else {
				sql = "select count(*) from BOARD where bn=5 and mem!=2";
				pstmt = conn.prepareStatement(sql);
			}
			res = pstmt.executeQuery();
			if(res.next()) {
				count = res.getInt(1);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(res != null) {
				try {
					res.close();
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
		return count;
	}		// end getArticleCount()

	// 전체 글 개수를 알아오는 변수형 메소드(검색 전용)
	// int값, 매개변수 prefaces, keywords
	public int getArticleCounts(String prefaces, String keywords, String jogun) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int count = 0;		// 실제로 return 될 값
		String sql = "";	// sql문
		try {
			conn = J001_ConnUtil.getConnection();
			
			// SQL문 선택
			if(keywords == null) {	// 검색 키워드가 없을 때는 검색 키워드와 관련이 없는 SQL문을 사용한다.
				// prefaces 값에 따라 사용하는 SQL문이 달라진다.
				if(prefaces.equals("a") || prefaces.equals("b") || prefaces.equals("c") || prefaces.equals("d") || prefaces.equals("e")) {
					sql = "select count(*) from BOARD where bn=5 and mem!=2 and preface=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, prefaces);
				} else {
					sql = "select count(*) from BOARD where bn=5 and mem!=2";
					pstmt = conn.prepareStatement(sql);
				}
			} else { // 검색 키워드가 존재할 때는 검색 키워드와 관련이 있는 SQL문을 사용한다.
				// prefaces 값에 따라 사용하는 SQL문이 달라진다.
				if(prefaces.equals("a") || prefaces.equals("b") || prefaces.equals("c") || prefaces.equals("d") || prefaces.equals("e")) {
					if(jogun.equals("a")) {
						sql = "select count(*) from BOARD where bn=5 and mem!=2 and preface=? and subject like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
					} else if(jogun.equals("b")) {
						sql = "select count(*) from BOARD where bn=5 and mem!=2 and preface=? and content like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
					} else if(jogun.equals("c")) {
						sql = "select count(*) from BOARD where bn=5 and mem!=2 and preface=? and (content like ? or subject like ?)";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
						pstmt.setString(3, keywords);
					} else if(jogun.equals("d")) {
						sql = "select count(*) from BOARD where bn=5 and mem!=2 and preface=? and writer like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
					} else {
						sql = "select count(*) from BOARD where bn=5 and mem!=2 and preface=? and subject like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
					}
					
					//2017-12-04
				} else {
					if(jogun.equals("a")) {
						sql = "select count(*) from BOARD where bn=5 and mem!=2 and subject like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
					} else if(jogun.equals("b")) {
						sql = "select count(*) from BOARD where bn=5 and mem!=2 and content like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
					} else if(jogun.equals("c")) {
						sql = "select count(*) from BOARD where bn=5 and mem!=2 and (content like ? or subject like ?)";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
						pstmt.setString(2, keywords);
					} else if(jogun.equals("d")) {
						sql = "select count(*) from BOARD where bn=5 and mem!=2 and writer like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
					} else {
						sql = "select count(*) from BOARD where bn=5 and mem!=2 and subject like ?";
						keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
					}
				}
			}
			res = pstmt.executeQuery();
			System.out.println("카운트가 사용한 SQL : " + sql);
			if(res.next()) {
				count = res.getInt(1);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(res != null) {
				try {
					res.close();
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
		return count;
	}		// end getArticleCounts()

	// 글 목록을 가져와서 List로 변환하는 변수형 메소드
	// List값, String 매개변수가 하나, int 매개변수가 2개
	public List<J002_BoardDTO> getArticles(String prefaces, int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<J002_BoardDTO> articleList = null;	// 실제로 return 될 값
		String sql = "";						// sql문
		try {
			conn = J001_ConnUtil.getConnection();
			if(prefaces.equals("a") || prefaces.equals("b") || prefaces.equals("c") || prefaces.equals("d") || prefaces.equals("e")) {
				sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and mem!=2 and preface=?)) where RNUM >= ? and RNUM <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, prefaces);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			} else {
				sql = "select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and mem!=2) where RNUM >= ? and RNUM <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}
			res = pstmt.executeQuery();
			if(res.next()) {
				articleList = new ArrayList<J002_BoardDTO>(5); // ListAction에 선언된 pageSize와 같은 수로 리스트크기를 정하자.
				do {
					J002_BoardDTO article = new J002_BoardDTO();	// DTO 생성
					article.setNum(res.getInt("num"));
					article.setPreface(res.getString("preface"));
					article.setWriter(res.getString("writer"));
					article.setSubject(res.getString("subject"));
					article.setPass(res.getString("pass"));
					article.setRegdate(res.getTimestamp("regdate"));
					article.setReadcount(res.getInt("readcount"));
					article.setRef(res.getInt("ref"));
					article.setStep(res.getInt("step"));
					article.setDepth(res.getInt("depth"));
					article.setContent(res.getString("content"));
					article.setIp(res.getString("ip"));
					article.setBn(res.getInt("bn"));
					articleList.add(article);
				} while(res.next());
			}	// end if
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(res != null) {
				try {
					res.close();
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
	}		// getArticles()
	
	// 글 목록을 가져와서 List로 변환하는 변수형 메소드(공지 전용)
		// List값, String 매개변수가 하나, int 매개변수가 2개
		public List<J002_BoardDTO> getArticlesNotice(int start, int end) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet res = null;
			List<J002_BoardDTO> articleList = null;	// 실제로 return 될 값
			String sql = "";						// sql문
			try {
				conn = J001_ConnUtil.getConnection();
				sql = "select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and mem=2) where RNUM >= ? and RNUM <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				res = pstmt.executeQuery();
				if(res.next()) {
					articleList = new ArrayList<J002_BoardDTO>(5); // ListAction에 선언된 pageSize와 같은 수로 리스트크기를 정하자.
					do {
						J002_BoardDTO article = new J002_BoardDTO();	// DTO 생성
						article.setNum(res.getInt("num"));
						article.setPreface(res.getString("preface"));
						article.setWriter(res.getString("writer"));
						article.setSubject(res.getString("subject"));
						article.setPass(res.getString("pass"));
						article.setRegdate(res.getTimestamp("regdate"));
						article.setReadcount(res.getInt("readcount"));
						article.setRef(res.getInt("ref"));
						article.setStep(res.getInt("step"));
						article.setDepth(res.getInt("depth"));
						article.setContent(res.getString("content"));
						article.setIp(res.getString("ip"));
						article.setBn(res.getInt("bn"));
						articleList.add(article);
					} while(res.next());
				}	// end if
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if(res != null) {
					try {
						res.close();
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
	public List<J002_BoardDTO> getArticless(String prefaces, String keywords, String jogun, int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<J002_BoardDTO> articleList = null;	// 실제로 return 될 값
		String sql = "";						// sql문
		try {
			conn = J001_ConnUtil.getConnection();
			// prefaces 값에 따라 달라진다.
			if(prefaces.equals("a") || prefaces.equals("b") || prefaces.equals("c") || prefaces.equals("d") || prefaces.equals("e")) {
				// 검색 조건에 따라 다른 SQL문을 사용하도록 한다.
				if(jogun.equals("a")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and mem!=2 and preface=? and subject like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, prefaces);
					pstmt.setString(2, keywords);
					pstmt.setInt(3, start);
					pstmt.setInt(4, end);
				} else if(jogun.equals("b")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and mem!=2 and preface=? and content like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, prefaces);
					pstmt.setString(2, keywords);
					pstmt.setInt(3, start);
					pstmt.setInt(4, end);
				} else if(jogun.equals("c")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and mem!=2 and preface=? and (subject like ? or content like ?))) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, prefaces);
					pstmt.setString(2, keywords);
					pstmt.setString(3, keywords);
					pstmt.setInt(4, start);
					pstmt.setInt(5, end);
				} else if(jogun.equals("d")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and mem!=2 and preface=? and writer like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, prefaces);
					pstmt.setString(2, keywords);
					pstmt.setInt(3, start);
					pstmt.setInt(4, end);
				}
			} else { // prefaces가 존재하지 않는, 모든 글을 볼 때는 preface 조건을 없애준다.
				// 검색 조건에 따라 다른 SQL문을 사용하도록 한다.
				if(jogun.equals("a")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and mem!=2 and subject like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keywords);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
				} else if(jogun.equals("b")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and mem!=2 and content like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keywords);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
				} else if(jogun.equals("c")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and mem!=2 and subject like ? or content like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keywords);
					pstmt.setString(2, keywords);
					pstmt.setInt(3, start);
					pstmt.setInt(4, end);
				} else if(jogun.equals("d")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and mem!=2 and writer like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords를 포함한 모든 문자 입력 가능, 이렇게 별도로 키워드를 지정해줘야 한다.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keywords);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
				}
			}
			res = pstmt.executeQuery();
			System.out.println("리스트가 사용한 SQL : " + sql);
			if(res.next()) {
				articleList = new ArrayList<J002_BoardDTO>(5); // ListAction에 선언된 pageSize와 같은 수로 리스트크기를 정하자.
				do {
					J002_BoardDTO article = new J002_BoardDTO();	// DTO 생성
					article.setNum(res.getInt("num"));
					article.setPreface(res.getString("preface"));
					article.setWriter(res.getString("writer"));
					article.setSubject(res.getString("subject"));
					article.setPass(res.getString("pass"));
					article.setRegdate(res.getTimestamp("regdate"));
					article.setReadcount(res.getInt("readcount"));
					article.setRef(res.getInt("ref"));
					article.setStep(res.getInt("step"));
					article.setDepth(res.getInt("depth"));
					article.setContent(res.getString("content"));
					article.setIp(res.getString("ip"));
					article.setBn(res.getInt("bn"));
					articleList.add(article);
				} while(res.next());
			}	// end if
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(res != null) {
				try {
					res.close();
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
	}		// getArticless()

	// 글 저장을 처리하는 메소드
	public void insertArticle(J002_BoardDTO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";		// sql문
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int number = 0;
		try {
			conn = J001_ConnUtil.getConnection();
			sql = "select max(num) from BOARD";
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			if(res.next()) {
				number = res.getInt(1) + 1;
			} else {
				number = 1;
			}
			if(num != 0) {	// 답글일 경우
				sql = "update BOARD set STEP = STEP + 1 where REF = ? and STEP > ?";
				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.executeUpdate();
				step = step + 1;
				depth = depth + 1;
			} else {	// 새 글일 경우
				ref = number;
				step = 0;
				depth = 0;
			}
			
			sql = "insert into BOARD (NUM, WRITER, SUBJECT, PASS, REGDATE, REF, STEP, DEPTH, CONTENT, IP, PREFACE, BN, MEM) values(BOARD_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getPass());
			pstmt.setTimestamp(4, article.getRegdate());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, step);
			pstmt.setInt(7, depth);
			pstmt.setString(8, article.getContent());
			pstmt.setString(9, article.getIp());
			pstmt.setString(10, article.getPreface());
			pstmt.setInt(11, article.getBn());
			pstmt.setInt(12, article.getMem());
						
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(res != null) {
				try {
					res.close();
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
	}		// end insertArticle()
	
	// 글 내용을 가져오는 메소드
	public J002_BoardDTO getArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		J002_BoardDTO article = null;
		String sql = "";		// sql문
		try {
			conn = J001_ConnUtil.getConnection();
			sql = "update BOARD set READCOUNT = READCOUNT + 1 where NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt.close();
			sql = "select * from BOARD where NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			res = pstmt.executeQuery();
			if(res.next()) {
				article = new J002_BoardDTO();
				article.setNum(res.getInt("num"));
				article.setWriter(res.getString("writer"));
				article.setBn(res.getInt("bn"));
				article.setSubject(res.getString("subject"));
				article.setPass(res.getString("pass"));
				article.setPreface(res.getString("preface"));
				article.setRegdate(res.getTimestamp("regdate"));
				article.setReadcount(res.getInt("readcount"));
				article.setRef(res.getInt("ref"));
				article.setStep(res.getInt("step"));
				article.setDepth(res.getInt("depth"));
				article.setContent(res.getString("content"));
				article.setIp(res.getString("ip"));
				article.setMem(res.getInt("mem"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(res != null) {
				try {
					res.close();
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
		return article;
	} // end getArticle()
	
	// 글 수정을 처리할 글의 세부 데이터를 받아올 수 있는 메소드
	public J002_BoardDTO updateGetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		J002_BoardDTO article = null;
		String sql = "";		// sql문
		try {
			conn = J001_ConnUtil.getConnection();
			sql = "select * from BOARD where NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			res = pstmt.executeQuery();
			if(res.next()) {
				article = new J002_BoardDTO();
				article.setNum(res.getInt("num"));
				article.setWriter(res.getString("writer"));
				article.setBn(res.getInt("bn"));
				article.setSubject(res.getString("subject"));
				article.setPass(res.getString("pass"));
				article.setPreface(res.getString("preface"));
				article.setRegdate(res.getTimestamp("regdate"));
				article.setReadcount(res.getInt("readcount"));
				article.setRef(res.getInt("ref"));
				article.setStep(res.getInt("step"));
				article.setDepth(res.getInt("depth"));
				article.setContent(res.getString("content"));
				article.setIp(res.getString("ip"));
				article.setMem(res.getInt("mem"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(res != null) {
				try {
					res.close();
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
		return article;
	} // end updateGetArticle()
	
	// 글 수정 처리 메소드
	public int updateArticle(J002_BoardDTO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";		// sql문
		String dbpasswd = "";
		int result = -1;		// flag
		try {
			conn = J001_ConnUtil.getConnection();
			sql = "select pass from BOARD where NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article.getNum());
			res = pstmt.executeQuery();
			if(res.next()) {
				dbpasswd = res.getString("pass");
				if(dbpasswd.equals(article.getPass())) {
					sql = "update BOARD set WRITER = ?, SUBJECT = ?, CONTENT = ? where NUM = ?";
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getSubject());
					pstmt.setString(3, article.getContent());
					pstmt.setInt(4, article.getNum());
					pstmt.executeUpdate();
					result = 1;	// 수정 성공
				} else {
					result = 0; // 수정 실패
				}
			} // end if
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(res != null) {
				try {
					res.close();
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
		return result;
	} // end updateArticle()
	
	
	// DB에서 글을 삭제하는 메소드
	public int deleteArticle(int num, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";		// sql문
		String dbPass = "";
		int result = -1;		// flag
		try {
			conn = J001_ConnUtil.getConnection();
			sql = "select pass from BOARD where NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			res = pstmt.executeQuery();
			if(res.next()) {
				dbPass = res.getString("pass");
				if(dbPass.equals(pass)) {
					pstmt.close();
					sql = "delete from BOARD where NUM = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					result = 1; // 삭제 성공
				} else {
					result = 0; // 패스워드 불일치
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(res != null) {
				try {
					res.close();
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
		return result;
	} // end deleteArticle()
	
	// 메소드들을 추가
}