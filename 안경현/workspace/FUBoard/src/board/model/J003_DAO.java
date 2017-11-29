package board.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// ���� �޼ҵ�, Ư���� �������� ���Ե� Ŭ����
// ���� : �޼ҵ� �����͵��� ǥ���� �� �����ϸ� ǥ�� ������ �����ϵ��� ����.
// ���� : sql���� ������ String ������ �����ؼ� �ű�� ������� �Ѵٸ� ��� �޼ҵ忡 �� ��������.
public class J003_DAO {
	private static J003_DAO instance = null;
	private J003_DAO() {
		// ������
	}
	public static J003_DAO getInstance() {
		if(instance == null) {
			synchronized(J003_DAO.class) {
				instance = new J003_DAO();
			}
		}
		return instance;
	}
	
	// ��ü �� ������ �˾ƿ��� ������ �޼ҵ�
	// int��, �Ű����� prefaces
	public int getArticleCount(String prefaces) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int count = 0;		// ������ return �� ��
		String sql = "";	// sql��
		try {
			conn = J001_ConnUtil.getConnection();
			// ������ prefaces�� ���� ����ϴ� sql���� �޶�����.
			if(prefaces.equals("a") || prefaces.equals("b") || prefaces.equals("c") || prefaces.equals("d") || prefaces.equals("e")) {
				sql = "select count(*) from BOARD where bn=5 and preface=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, prefaces);
			} else {
				sql = "select count(*) from BOARD where bn=5";
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

	// ��ü �� ������ �˾ƿ��� ������ �޼ҵ�(�˻� ����)
	// int��, �Ű����� prefaces, keywords
	public int getArticleCounts(String prefaces, String keywords, String jogun) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int count = 0;		// ������ return �� ��
		String sql = "";	// sql��
		try {
			conn = J001_ConnUtil.getConnection();
			
			// SQL�� ����
			if(keywords == null) {	// �˻� Ű���尡 ���� ���� �˻� Ű����� ������ ���� SQL���� ����Ѵ�.
				// prefaces ���� ���� ����ϴ� SQL���� �޶�����.
				if(prefaces.equals("a") || prefaces.equals("b") || prefaces.equals("c") || prefaces.equals("d") || prefaces.equals("e")) {
					sql = "select count(*) from BOARD where bn=5 and preface=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, prefaces);
				} else {
					sql = "select count(*) from BOARD where bn=5";
					pstmt = conn.prepareStatement(sql);
				}
			} else { // �˻� Ű���尡 ������ ���� �˻� Ű����� ������ �ִ� SQL���� ����Ѵ�.
				// prefaces ���� ���� ����ϴ� SQL���� �޶�����.
				if(prefaces.equals("a") || prefaces.equals("b") || prefaces.equals("c") || prefaces.equals("d") || prefaces.equals("e")) {
					if(jogun.equals("a")) {
						sql = "select count(*) from BOARD where bn=5 and preface=? and subject like ?";
						keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
					} else if(jogun.equals("b")) {
						sql = "select count(*) from BOARD where bn=5 and preface=? and content like ?";
						keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
					} else if(jogun.equals("c")) {
						sql = "select count(*) from BOARD where bn=5 and preface=? and (content like ? or subject like ?)";
						keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
						pstmt.setString(3, keywords);
					} else if(jogun.equals("d")) {
						sql = "select count(*) from BOARD where bn=5 and preface=? and writer like ?";
						keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
					} else {
						sql = "select count(*) from BOARD where bn=5 and preface=? and subject like ?";
						keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, prefaces);
						pstmt.setString(2, keywords);
					}
				} else {
					if(jogun.equals("a")) {
						sql = "select count(*) from BOARD where bn=5 and subject like ?";
						keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
					} else if(jogun.equals("b")) {
						sql = "select count(*) from BOARD where bn=5 and content like ?";
						keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
					} else if(jogun.equals("c")) {
						sql = "select count(*) from BOARD where bn=5 and (content like ? or subject like ?)";
						keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
						pstmt.setString(2, keywords);
					} else if(jogun.equals("d")) {
						sql = "select count(*) from BOARD where bn=5 and writer like ?";
						keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
					} else {
						sql = "select count(*) from BOARD where bn=5 and subject like ?";
						keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keywords);
					}
				}
			}
			res = pstmt.executeQuery();
			System.out.println("ī��Ʈ�� ����� SQL : " + sql);
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

	// �� ����� �����ͼ� List�� ��ȯ�ϴ� ������ �޼ҵ�
	// List��, String �Ű������� �ϳ�, int �Ű������� 2��
	public List<J002_BoardDTO> getArticles(String prefaces, int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<J002_BoardDTO> articleList = null;	// ������ return �� ��
		String sql = "";						// sql��
		try {
			conn = J001_ConnUtil.getConnection();
			if(prefaces.equals("a") || prefaces.equals("b") || prefaces.equals("c") || prefaces.equals("d") || prefaces.equals("e")) {
				sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and preface=?)) where RNUM >= ? and RNUM <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, prefaces);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			} else {
				sql = "select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5) where RNUM >= ? and RNUM <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}
			res = pstmt.executeQuery();
			if(res.next()) {
				articleList = new ArrayList<J002_BoardDTO>(5); // ListAction�� ����� pageSize�� ���� ���� ����Ʈũ�⸦ ������.
				do {
					J002_BoardDTO article = new J002_BoardDTO();	// DTO ����
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
	
	// �� ����� �����ͼ� List�� ��ȯ�ϴ� ������ �޼ҵ�(�˻� ����)
	// List��, String �Ű������� ��, int �Ű������� 2��
	public List<J002_BoardDTO> getArticless(String prefaces, String keywords, String jogun, int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<J002_BoardDTO> articleList = null;	// ������ return �� ��
		String sql = "";						// sql��
		try {
			conn = J001_ConnUtil.getConnection();
			// prefaces ���� ���� �޶�����.
			if(prefaces.equals("a") || prefaces.equals("b") || prefaces.equals("c") || prefaces.equals("d") || prefaces.equals("e")) {
				// �˻� ���ǿ� ���� �ٸ� SQL���� ����ϵ��� �Ѵ�.
				if(jogun.equals("a")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and preface=? and subject like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, prefaces);
					pstmt.setString(2, keywords);
					pstmt.setInt(3, start);
					pstmt.setInt(4, end);
				} else if(jogun.equals("b")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and preface=? and content like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, prefaces);
					pstmt.setString(2, keywords);
					pstmt.setInt(3, start);
					pstmt.setInt(4, end);
				} else if(jogun.equals("c")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and preface=? and (subject like ? or content like ?))) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, prefaces);
					pstmt.setString(2, keywords);
					pstmt.setString(3, keywords);
					pstmt.setInt(4, start);
					pstmt.setInt(5, end);
				} else if(jogun.equals("d")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and preface=? and writer like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, prefaces);
					pstmt.setString(2, keywords);
					pstmt.setInt(3, start);
					pstmt.setInt(4, end);
				}
			} else { // prefaces�� �������� �ʴ�, ��� ���� �� ���� preface ������ �����ش�.
				// �˻� ���ǿ� ���� �ٸ� SQL���� ����ϵ��� �Ѵ�.
				if(jogun.equals("a")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and subject like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keywords);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
				} else if(jogun.equals("b")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and content like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keywords);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
				} else if(jogun.equals("c")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and subject like ? or content like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keywords);
					pstmt.setString(2, keywords);
					pstmt.setInt(3, start);
					pstmt.setInt(4, end);
				} else if(jogun.equals("d")) {
					sql = "select * from (select * from (select rownum RNUM, NUM, PREFACE, WRITER, SUBJECT, PASS, REGDATE, READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN from (select * from BOARD order by REF desc, STEP asc) where bn=5 and writer like ?)) where RNUM >= ? and RNUM <= ?";
					keywords = "%" + keywords + "%"; // keywords�� ������ ��� ���� �Է� ����, �̷��� ������ Ű���带 ��������� �Ѵ�.
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keywords);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
				}
			}
			res = pstmt.executeQuery();
			System.out.println("����Ʈ�� ����� SQL : " + sql);
			if(res.next()) {
				articleList = new ArrayList<J002_BoardDTO>(5); // ListAction�� ����� pageSize�� ���� ���� ����Ʈũ�⸦ ������.
				do {
					J002_BoardDTO article = new J002_BoardDTO();	// DTO ����
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

	// �� ������ ó���ϴ� �޼ҵ�
	public void insertArticle(J002_BoardDTO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";		// sql��
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
			if(num != 0) {	// ����� ���
				sql = "update BOARD set STEP = STEP + 1 where REF = ? and STEP > ?";
				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.executeUpdate();
				step = step + 1;
				depth = depth + 1;
			} else {	// �� ���� ���
				ref = number;
				step = 0;
				depth = 0;
			}
			
			sql = "insert into BOARD (NUM, WRITER, SUBJECT, PASS, REGDATE, REF, STEP, DEPTH, CONTENT, IP, PREFACE, BN) values(BOARD_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
	
	// �� ������ �������� �޼ҵ�
	public J002_BoardDTO getArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		J002_BoardDTO article = null;
		String sql = "";		// sql��
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
	
	// �� ������ ó���� ���� ���� �����͸� �޾ƿ� �� �ִ� �޼ҵ�
	public J002_BoardDTO updateGetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		J002_BoardDTO article = null;
		String sql = "";		// sql��
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
	
	// �� ���� ó�� �޼ҵ�
	public int updateArticle(J002_BoardDTO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";		// sql��
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
					result = 1;	// ���� ����
				} else {
					result = 0; // ���� ����
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
	
	
	// DB���� ���� �����ϴ� �޼ҵ�
	public int deleteArticle(int num, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";		// sql��
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
					result = 1; // ���� ����
				} else {
					result = 0; // �н����� ����ġ
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
	
	// �޼ҵ���� �߰�
}