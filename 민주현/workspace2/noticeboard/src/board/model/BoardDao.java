package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class BoardDao {
	
//		private static BoardDao instance = null;
		private static BoardDao instance = new BoardDao();
		
		private BoardDao() {}
		public static BoardDao getInstance() {
			if(instance == null) {
				synchronized(BoardDao.class) {
					instance = new BoardDao();
				}
			}
			return instance;
		}
		// 여기에 게시판에서 필요한 작업 기능들을 메서드로 추가하게 된다.
		
		//전체 글 개수를 알아오는 메서드
		
		public int getArticleCount(int bn, String keyField, String keyWord) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
			String sql = null;
			try {
					conn = ConnUtil.getConnection();
					if(keyWord == null || "".equals(keyWord.trim())) {
						sql = "select count(*) from BOARD where BN=?";
						pstmt = conn.prepareStatement(sql);						
						pstmt.setInt(1, bn);
					}else {
						sql="select count(*) from BOARD where BN=? and "+keyField+" like ?";
						pstmt=conn.prepareStatement(sql);
						pstmt.setInt(1, bn);
						pstmt.setString(2, "%"+keyWord+"%");
					}
					rs=pstmt.executeQuery();
					if(rs.next()) {
						count = rs.getInt(1);
					}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				if(rs != null)try {rs.close();} catch(SQLException e) {}
				if(pstmt != null)try {pstmt.close();} catch(SQLException e) {}
				if(conn != null)try {conn.close();} catch(SQLException e) {}
			}
			return count;
		}
		
		// 글 목록을 가져와서 List로 반환하는 메서드
		public List<BoardDto> getArticles(int start, int end, int bn, String keyField, String keyWord){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<BoardDto> articleList = null;
			String sql = null;
			try {
					conn = ConnUtil.getConnection();
					if(keyWord == null || "".equals(keyWord.trim())) {
						sql = "select * from "
								+"(select rownum RNUM, NUM, WRITER,"
								+"SUBJECT, PASS, REGDATE,"
								+"READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN, ORIGIN_FILENAME, SERVER_FILENAME, FILESIZE, FILETYPE, PREFACE, MEM from "
								+"(select * from BOARD order by REF desc, STEP asc) where BN=?)"
								+"where RNUM >= ? and RNUM <= ?";
						pstmt = conn.prepareStatement(sql);
						System.out.println(sql);
						pstmt.setInt(1, bn);
						pstmt.setInt(2, start);
						pstmt.setInt(3, end);						
					}else {
						sql = "select * from "
								+"(select rownum RNUM, NUM, WRITER,"
								+"SUBJECT, PASS, REGDATE,"
								+"READCOUNT, REF, STEP, DEPTH, CONTENT, IP, BN, ORIGIN_FILENAME, SERVER_FILENAME, FILESIZE, FILETYPE, PREFACE, MEM from "
								+"(select * from BOARD where "+keyField+" like ? order by REF desc, STEP asc) where BN=?)"
								+"where RNUM >= ? and RNUM <= ?";
						pstmt = conn.prepareStatement(sql);
						System.out.println(sql);
						pstmt.setString(1, "%"+keyWord+"%");
						pstmt.setInt(2, bn);
						pstmt.setInt(3, start);
						pstmt.setInt(4, end);
					}
					rs=pstmt.executeQuery();
					if(rs.next()) {
							articleList =new ArrayList<BoardDto>(5);
							do {
								BoardDto article = new BoardDto();
								article.setNum(rs.getInt("num"));
								article.setWriter(rs.getString("writer"));
								article.setSubject(rs.getString("subject"));
								article.setPass(rs.getString("pass"));
								article.setRegdate(rs.getTimestamp("regdate"));
								article.setReadcount(rs.getInt("readCount"));
								article.setRef(rs.getInt("ref"));
								article.setStep(rs.getInt("step"));
								article.setDepth(rs.getInt("depth"));
								article.setContent(rs.getString("content"));
								article.setIp(rs.getString("ip"));
								article.setBn(rs.getInt("bn"));
								article.setOrigin_filename(rs.getString("origin_filename"));
								article.setServer_filename(rs.getString("server_filename"));
								article.setFilesize(rs.getInt("filesize"));
								article.setFiletype(rs.getString("filetype"));
								article.setPreface(rs.getString("preface"));
								article.setMem(rs.getInt("mem"));
								articleList.add(article);								
							} while (rs.next());
					}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null)try {rs.close();} catch(SQLException e) {}
				if(pstmt != null)try {pstmt.close();} catch(SQLException e) {}
				if(conn != null)try {conn.close();} catch(SQLException e) {}
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
			int depth=article.getDepth();
			int number = 0;
			String sql = "";
			try {
					conn = ConnUtil.getConnection();
					pstmt = conn.prepareStatement("select max(num) from BOARD");
					rs = pstmt.executeQuery();
					if(rs.next()) {
						number = rs.getInt(1) + 1;
					}else {
						number = 1;
					}
					if(num != 0) { //답글일 경우
						sql = "update BOARD set STEP = STEP+1 where REF = ? and STEP >?";
						pstmt.close();
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, ref);
						pstmt.setInt(2, step);
						pstmt.executeUpdate();
						step = step + 1 ;
						depth = depth + 1; 
					}else { //새글 일 경우
						ref = number;
						step = 0;
						depth = 0;
					}
					
					// 쿼리 작성
					sql = "insert into BOARD (NUM, WRITER, SUBJECT, PASS, "
							+"REGDATE, REF, STEP, DEPTH, CONTENT, IP, BN, ORIGIN_FILENAME, SERVER_FILENAME, FILESIZE, FILETYPE, PREFACE, MEM) "
							+"values(BOARD_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
					pstmt.setInt(10, article.getBn());
					pstmt.setString(11, article.getOrigin_filename());
					pstmt.setString(12, article.getServer_filename());
					pstmt.setInt(13, article.getFilesize());
					pstmt.setString(14, article.getFiletype());
					pstmt.setString(15, article.getPreface());
					pstmt.setInt(16, article.getMem());
					pstmt.executeUpdate();				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null)try {rs.close();} catch(SQLException e) {}
				if(pstmt != null)try {pstmt.close();} catch(SQLException e) {}
				if(conn != null)try {conn.close();} catch(SQLException e) {}
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
					if(rs.next()) {
						article = new BoardDto();
						article.setNum(rs.getInt("num"));
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
						article.setOrigin_filename(rs.getString("origin_filename"));
						article.setServer_filename(rs.getString("server_filename"));
						article.setFilesize(rs.getInt("filesize"));
						article.setFiletype(rs.getString("filetype"));
						article.setPreface(rs.getString("preface"));
						article.setMem(rs.getInt("mem"));
					}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null)try {rs.close();} catch(SQLException e) {}
				if(pstmt != null)try {pstmt.close();} catch(SQLException e) {}
				if(conn != null)try {conn.close();} catch(SQLException e) {}
			}
			return article;
		}
		
		// 글 수정을 처리할 글의 세부 데이터를 받아올 수 있는 방법
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
					if(rs.next()) {
						article = new BoardDto();
						article.setNum(rs.getInt("num"));
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
						article.setOrigin_filename(rs.getString("origin_filename"));
						article.setServer_filename(rs.getString("server_filename"));
						article.setFilesize(rs.getInt("filesize"));
						article.setFiletype(rs.getString("filetype"));
						article.setPreface(rs.getString("preface"));
						article.setMem(rs.getInt("mem"));
					}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null)try {rs.close();} catch(SQLException e) {}
				if(pstmt != null)try {pstmt.close();} catch(SQLException e) {}
				if(conn != null)try {conn.close();} catch(SQLException e) {}
			}
			return article;
			
		}
		
		//글 수정 처리할 메서드
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
							if(dbpasswd.equals(article.getPass())) {
								sql = "update BOARD set WRITER=?, SUBJECT=?, "
										+"CONTENT=?, ORIGIN_FILENAME=?, SERVER_FILENAME=?, FILESIZE=?, FILETYPE=?, PREFACE=? where NUM=?";
								pstmt.close();
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, article.getWriter());
								pstmt.setString(2, article.getSubject());
								pstmt.setString(3, article.getContent());
								pstmt.setString(4, article.getOrigin_filename());
								pstmt.setString(5, article.getServer_filename());
								pstmt.setInt(6, article.getFilesize());
								pstmt.setString(7, article.getFiletype());
								pstmt.setString(8, article.getPreface());
								pstmt.setInt(9, article.getNum());
								pstmt.executeUpdate();
								result = 1; // 수정 성공
							}else {
								result = 0; // 수정 실패
							}
					}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null)try {rs.close();} catch(SQLException e) {}
				if(pstmt != null)try {pstmt.close();} catch(SQLException e) {}
				if(conn != null)try {conn.close();} catch(SQLException e) {}
			}
			return result;
			
		}
		
		//DB에서 글을 삭제하는 메서드
		
		public int deleteArticle(int num, String pass) {
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			String dbPass ="";
			int result = -1;
			try {
					conn = ConnUtil.getConnection();
					pstmt = conn.prepareStatement("select PASS from BOARD where NUM = ?");
					pstmt.setInt(1, num);
					rs =pstmt.executeQuery();
					if (rs.next()) {
							dbPass = rs.getString("pass");
							if(dbPass.equals(pass)) {
								pstmt.close();
								pstmt = conn.prepareStatement("delete from BOARD where NUM = ?");
								pstmt.setInt(1, num);
								pstmt.executeUpdate();
								result = 1; //삭제 성공
							}else {
								result = 0; //비밀번호 불일치
							}
					}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null)try {rs.close();} catch(SQLException e) {}
				if(pstmt != null)try {pstmt.close();} catch(SQLException e) {}
				if(conn != null)try {conn.close();} catch(SQLException e) {}
			}
			return result;
		}
		
	}

