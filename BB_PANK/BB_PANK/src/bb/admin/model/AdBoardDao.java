package bb.admin.model;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdBoardDao {
	private static AdBoardDao instance = null;
	private AdBoardDao(){}
	public static AdBoardDao getInstance(){
		if(instance == null){
			synchronized(AdBoardDao.class){
				instance = new AdBoardDao();
			}
		}
		return instance;
	}
	//이제부터 여기에 게시판에서 필요한 작업 기능들을 메서드로 추가하게 된다.
	
	//전체 글 개수를 알아오는 메서드
	public int getArticleCount(int bn){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = ConnUtil.getConnection();
			if(bn==0) {//[전부]일경우
					pstmt = conn.prepareStatement("select count(*) from BOARD where mem=2");
					rs = pstmt.executeQuery();
			}else {
				pstmt = conn.prepareStatement("select count(*) from BOARD where bn=? and mem=2");
				pstmt.setInt(1, bn);
				rs = pstmt.executeQuery();
			}
		if(rs.next()){
				count = rs.getInt(1);
			}
		} catch(Exception ex){
				ex.printStackTrace();
		} finally{
			if(rs != null) try{rs.close(); } catch(SQLException e){}
			if(pstmt != null) try{pstmt.close(); } catch(SQLException e){}
			if(conn != null) try{conn.close(); } catch(SQLException e){}
		}
		return count;
	}
	//검색 했을때 글 개수 알아오는 메서드
	public int getSearchArticleCount(int bn ,String details, String search){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		search="%"+search+"%";
		try{
			conn = ConnUtil.getConnection();
			if(bn==0) {
				if(details.equals("subject")) {
					pstmt = conn.prepareStatement("select count(*) from BOARD where subject like ? and mem=2");

					pstmt.setString(1, search);
					rs = pstmt.executeQuery();
				}else if(details.equals("content")) {
					pstmt = conn.prepareStatement("select count(*) from BOARD wherecontent like ? and mem=2");

					pstmt.setString(1, search);
					rs = pstmt.executeQuery();
				}else if(details.equals("subjectcontent")) {
					pstmt = conn.prepareStatement("select count(*) from BOARD where content like ? or subject like ?  and mem=2");
					pstmt.setString(1, search);
					pstmt.setString(2, search);
					rs = pstmt.executeQuery();
				}else if(details.equals("writer")) {
					pstmt = conn.prepareStatement("select count(*) from BOARD where and writer like ?  and mem=2");

					pstmt.setString(1, search);
					rs = pstmt.executeQuery();
				}
					
			}else{
				if(details.equals("subject")) {
					pstmt = conn.prepareStatement("select count(*) from BOARD where bn=? and subject like ?  and mem=2");
					pstmt.setInt(1, bn);
					pstmt.setString(2, search);
					rs = pstmt.executeQuery();
				}else if(details.equals("content")) {
					pstmt = conn.prepareStatement("select count(*) from BOARD where bn=? and content like ?  and mem=2");
					pstmt.setInt(1, bn);
					pstmt.setString(2, search);
					rs = pstmt.executeQuery();
				}else if(details.equals("subjectcontent")) {
					pstmt = conn.prepareStatement("select count(*) from BOARD where bn=? and (content like ? or subject like ?)  and mem=2");
					pstmt.setInt(1, bn);
					pstmt.setString(2, search);
					pstmt.setString(3, search);
					rs = pstmt.executeQuery();
				}else if(details.equals("writer")) {
					pstmt = conn.prepareStatement("select count(*) from BOARD where bn=? and writer like ? and mem=2");
					pstmt.setInt(1, bn);
					pstmt.setString(2, search);
					rs = pstmt.executeQuery();
				}
					
			}
		if(rs.next()){
				count = rs.getInt(1);
			}
		} catch(Exception ex){
				ex.printStackTrace();
		} finally{
			if(rs != null) try{rs.close(); } catch(SQLException e){}
			if(pstmt != null) try{pstmt.close(); } catch(SQLException e){}
			if(conn != null) try{conn.close(); } catch(SQLException e){}
		}
		return count;
	}
	//글 목록을 가져와서 List로 반환하는 메서드+분류별
	public List<AdBoardDto> getArticles(int start, int end, int bn){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AdBoardDto> articleList = null;
		try{
			conn = ConnUtil.getConnection();
			String sql=null;
			if(bn==0) {
				 sql="select * from(select * from "
						+"(select rownum RNUM, NUM, WRITER,"
						+"ORIGIN_FILENAME, SUBJECT, PASS, REGDATE,"
						+"READCOUNT, REF, STEP, DEPTH, CONTENT, SERVER_FILENAME, FILETYPE, FILESIZE, IP, BN, PREFACE, MEM from" 
						+"(select * from BOARD order by REF desc, STEP asc)where mem=2))"
						+"where RNUM >= ? and RNUM <= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
			}else{
				 sql="select * from(select * from "
							+"(select rownum RNUM, NUM, WRITER,"
							+"ORIGIN_FILENAME, SUBJECT, PASS, REGDATE,"
							+"READCOUNT, REF, STEP, DEPTH, CONTENT, SERVER_FILENAME, FILETYPE, FILESIZE, IP, BN, PREFACE, MEM from" 
							+"(select * from BOARD order by REF desc, STEP asc)where bn=? and mem=2))"
							+"where RNUM >= ? and RNUM <= ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, bn);
						pstmt.setInt(2, start);
						pstmt.setInt(3, end);
						rs = pstmt.executeQuery();
				}
		
			if(rs.next()){
				articleList = new ArrayList<AdBoardDto>(5);
				do {
					AdBoardDto article = new AdBoardDto();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setOrigin_filename(rs.getString("origin_filename"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setContent(rs.getString("content"));
					article.setServer_filename(rs.getString("server_filename"));
					article.setFiletype(rs.getString("filetype"));
					article.setFilesize(rs.getInt("filesize"));
					article.setIp(rs.getString("ip"));
					article.setBn(rs.getInt("bn"));
					article.setPreface(rs.getString("preface"));
					article.setMem(rs.getInt("mem"));
					articleList.add(article);
				} while(rs.next());
			}
		} catch(Exception e){
				e.printStackTrace();
		} finally{
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return articleList;
	}
	//어드민이쓴 목록을 list로 반환하는 메서드
	public List<AdBoardDto> getMArticles(int bn){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AdBoardDto> articleList = null;
		try{
			conn = ConnUtil.getConnection();
			String sql=null;
			sql="select * from "
							+"(select rownum RNUM, NUM, WRITER,"
							+"ORIGIN_FILENAME, SUBJECT, PASS, REGDATE,"
							+"READCOUNT, REF, STEP, DEPTH, CONTENT, SERVER_FILENAME, FILETYPE, FILESIZE, IP, BN, PREFACE, MEM from" 
							+"(select * from BOARD order by REF desc, STEP asc)where mem=2)";
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
			if(rs.next()){
				articleList = new ArrayList<AdBoardDto>(5);
				do {
					AdBoardDto article = new AdBoardDto();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setOrigin_filename(rs.getString("origin_filename"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setContent(rs.getString("content"));
					article.setServer_filename(rs.getString("server_filename"));
					article.setFiletype(rs.getString("filetype"));
					article.setFilesize(rs.getInt("filesize"));
					article.setIp(rs.getString("ip"));
					article.setBn(rs.getInt("bn"));
					article.setPreface(rs.getString("preface"));
					article.setMem(rs.getInt("mem"));
					articleList.add(article);
				} while(rs.next());
			}
		} catch(Exception e){
				e.printStackTrace();
		} finally{
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return articleList;
	}
	//글 목록을 가져와서 List로 반환하는 메서드+검색했을때
	public List<AdBoardDto> getSearchArticles(int start, int end, int bn, String details,String search){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AdBoardDto> articleList = null;
		String sql=null;
		search="%"+search+"%";
		try{
			conn = ConnUtil.getConnection();
			if(bn==0) {//[전부]일경우
				if(details.equals("subject")) {//[제목]
					 sql="select * from(select * from "
								+"(select rownum RNUM, NUM, WRITER,"
								+"ORIGIN_FILENAME, SUBJECT, PASS, REGDATE,"
								+"READCOUNT, REF, STEP, DEPTH, CONTENT, SERVER_FILENAME, FILETYPE, FILESIZE, IP, BN, PREFACE, MEM from" 
								+"(select * from BOARD order by REF desc, STEP asc)where mem=2 and (subject like ?)))"
								+"where RNUM >= ? and RNUM <= ?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, search);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
					rs = pstmt.executeQuery();
				}else if(details.equals("content")) {//내용
					 sql="select * from(select * from "
								+"(select rownum RNUM, NUM, WRITER,"
								+"ORIGIN_FILENAME, SUBJECT, PASS, REGDATE,"
								+"READCOUNT, REF, STEP, DEPTH, CONTENT, SERVER_FILENAME, FILETYPE, FILESIZE, IP, BN, PREFACE, MEM from" 
								+"(select * from BOARD order by REF desc, STEP asc)where mem=2 and (content like ?)))"
								+"where RNUM >= ? and RNUM <= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, search);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
					rs = pstmt.executeQuery();
				}else if(details.equals("subjectcontent")) {
					 sql="select * from(select * from "
								+"(select rownum RNUM, NUM, WRITER,"
								+"ORIGIN_FILENAME, SUBJECT, PASS, REGDATE,"
								+"READCOUNT, REF, STEP, DEPTH, CONTENT, SERVER_FILENAME, FILETYPE, FILESIZE, IP, BN, PREFACE, MEM from" 
								+"(select * from BOARD order by REF desc, STEP asc)where mem=2 and (content like ? or subject like ?)))"
								+"where RNUM >= ? and RNUM <= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, search);
					pstmt.setString(2, search);
					pstmt.setInt(3, start);
					pstmt.setInt(4, end);
					rs = pstmt.executeQuery();
				}else if(details.equals("writer")) {
					 sql="select * from(select * from "
								+"(select rownum RNUM, NUM, WRITER,"
								+"ORIGIN_FILENAME, SUBJECT, PASS, REGDATE,"
								+"READCOUNT, REF, STEP, DEPTH, CONTENT, SERVER_FILENAME, FILETYPE, FILESIZE, IP, BN, PREFACE, MEM from" 
								+"(select * from BOARD order by REF desc, STEP asc)where mem=2 and (writer like ?)))"
								+"where RNUM >= ? and RNUM <= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, search);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
					rs = pstmt.executeQuery();
				}
					
			}else{
				if(details.equals("subject")) {
					 sql="select * from(select * from "
								+"(select rownum RNUM, NUM, WRITER,"
								+"ORIGIN_FILENAME, SUBJECT, PASS, REGDATE,"
								+"READCOUNT, REF, STEP, DEPTH, CONTENT, SERVER_FILENAME, FILETYPE, FILESIZE, IP, BN, PREFACE, MEM from" 
								+"(select * from BOARD order by REF desc, STEP asc)where bn=? and mem=2 and (subject like ?)))"
								+"where RNUM >= ? and RNUM <= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, bn);
					pstmt.setString(2, search);
					pstmt.setInt(3, start);
					pstmt.setInt(4, end);
					rs = pstmt.executeQuery();
				}else if(details.equals("content")) {
					sql="select * from(select * from "
							+"(select rownum RNUM, NUM, WRITER,"
							+"ORIGIN_FILENAME, SUBJECT, PASS, REGDATE,"
							+"READCOUNT, REF, STEP, DEPTH, CONTENT, SERVER_FILENAME, FILETYPE, FILESIZE, IP, BN, PREFACE, MEM from" 
							+"(select * from BOARD order by REF desc, STEP asc)where bn=? and mem=2 and (content like ?)))"
							+"where RNUM >= ? and RNUM <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bn);
				pstmt.setString(2, search);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
				rs = pstmt.executeQuery();
				}else if(details.equals("subjectcontent")) {
					sql="select * from(select * from "
							+"(select rownum RNUM, NUM, WRITER,"
							+"ORIGIN_FILENAME, SUBJECT, PASS, REGDATE,"
							+"READCOUNT, REF, STEP, DEPTH, CONTENT, SERVER_FILENAME, FILETYPE, FILESIZE, IP, BN, PREFACE, MEM from" 
							+"(select * from BOARD order by REF desc, STEP asc)where bn=? and mem=2 and (content like ? or subject like ?)))"
							+"where RNUM >= ? and RNUM <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bn);
				pstmt.setString(2, search);
				pstmt.setString(3, search);
				pstmt.setInt(4, start);
				pstmt.setInt(5, end);
				rs = pstmt.executeQuery();
				}else if(details.equals("writer")) {
					sql="select * from(select * from "
							+"(select rownum RNUM, NUM, WRITER,"
							+"ORIGIN_FILENAME, SUBJECT, PASS, REGDATE,"
							+"READCOUNT, REF, STEP, DEPTH, CONTENT, SERVER_FILENAME, FILETYPE, FILESIZE, IP, BN, PREFACE, MEM from" 
							+"(select * from BOARD order by REF desc, STEP asc)where bn=? and mem=2 and (writer like ?)))"
							+"where RNUM >= ? and RNUM <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bn);
				pstmt.setString(2, search);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
				rs = pstmt.executeQuery();
				}
			}
			if(rs.next()){
				articleList = new ArrayList<AdBoardDto>(5);
				do {
					AdBoardDto article = new AdBoardDto();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setOrigin_filename(rs.getString("origin_filename"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setContent(rs.getString("content"));
					article.setServer_filename(rs.getString("server_filename"));
					article.setFiletype(rs.getString("filetype"));
					article.setFilesize(rs.getInt("filesize"));
					article.setIp(rs.getString("ip"));
					article.setBn(rs.getInt("bn"));
					article.setPreface(rs.getString("preface"));
					article.setMem(rs.getInt("mem"));
					articleList.add(article);
				} while(rs.next());
			}
		} catch(Exception e){
				e.printStackTrace();
		} finally{
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return articleList;
	}
	//글 저장을 처리하는 메서드
	public void insertArticle(AdBoardDto article){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int number = 0;
		String sql = "";
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(num) from BOARD");
			rs = pstmt.executeQuery();
			if(rs.next()){
				number = rs.getInt(1) + 1;
			} else {
				number = 1;
			}
			if(num != 0){	//답글일 경우
				sql = "update BOARD set STEP = STEP+1 where REF = ? and STEP > ?";
				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.executeUpdate();
				step = step + 1;
				depth = depth + 1;
			} else {	//새글일 경우
				ref = number;
				step = 0;
				depth = 0;
			}	
			//쿼리 작성
			sql = "insert into BOARD"
					+ "(NUM, WRITER, ORIGIN_FILENAME, SUBJECT, PASS, "
					+ "REGDATE, REF, STEP, DEPTH, CONTENT, SERVER_FILENAME, FILETYPE, FILESIZE, IP, BN, PREFACE, MEM) "
					+ "values(BOARD_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getOrigin_filename());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPass());
			pstmt.setTimestamp(5, article.getRegdate());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, step);
			pstmt.setInt(8, depth);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getServer_filename());
			pstmt.setString(11, article.getFiletype());
			pstmt.setInt(12,  article.getFilesize());
			pstmt.setString(13, article.getIp());
			pstmt.setInt(14,  article.getBn());
			pstmt.setString(15, article.getPreface());
			pstmt.setInt(16,  article.getMem());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
	}
	//글 내용을 가져오는 메서드
	public AdBoardDto getArticle(int num){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdBoardDto article = null;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"update BOARD set READCOUNT=READCOUNT+1 where NUM = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = conn.prepareStatement(
					"select * from BOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				article = new AdBoardDto();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setOrigin_filename(rs.getString("origin_filename"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setServer_filename(rs.getString("server_filename"));
				article.setFiletype(rs.getString("filetype"));
				article.setFilesize(rs.getInt("filesize"));
				article.setIp(rs.getString("ip"));
				article.setBn(rs.getInt("bn"));
				article.setPreface(rs.getString("preface"));
				article.setMem(rs.getInt("mem"));
				
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return article;
	}
	//글 수정을 처리할 글의 세부 테이터를 받아올 수 있는 방법
	public AdBoardDto updateGetArticle(int num){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdBoardDto article = null;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select * from BOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				article = new AdBoardDto();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setOrigin_filename(rs.getString("origin_filename"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setServer_filename(rs.getString("server_filename"));
				article.setFiletype(rs.getString("filetype"));
				article.setFilesize(rs.getInt("filesize"));
				article.setIp(rs.getString("ip"));
				article.setBn(rs.getInt("bn"));
				article.setPreface(rs.getString("preface"));
				article.setMem(rs.getInt("mem"));
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return article;
	}
	//글 수정 처리할 메서드
	public int updateArticle(AdBoardDto article){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		String sql = "";
		int result = -1;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select pass from BOARD where NUM = ?");
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			if(rs.next()){
				dbpasswd = rs.getString("pass");
				if(dbpasswd.equals(article.getPass())){
					sql = "update BOARD set WRITER=?,ORIGIN_FILENAME=?,SERVER_FILENAME=?,"
							+ "SUBJECT=?,CONTENT=?, FILETYPE=?, FILESIZE=?, PREFACE=? where NUM=?";
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getOrigin_filename());
					pstmt.setString(3, article.getServer_filename());
					pstmt.setString(4, article.getSubject());
					pstmt.setString(5, article.getContent());
					pstmt.setString(6, article.getFiletype());
					pstmt.setInt(7, article.getFilesize());
					pstmt.setString(8, article.getPreface());
					pstmt.setInt(9, article.getNum());
					pstmt.executeUpdate();
					result = 1; //수정 성공
				} else {
					result = 0; //수정 실패
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return result;
	}
	//DB에서 글을 삭제하는 메서드
	public int deleteArticle(int num, String pass,String location){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPass = "";
		String fileName=null;
		int result = -1;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select PASS,SERVER_FILENAME from BOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				fileName=rs.getString("server_filename");
				dbPass = rs.getString("pass");
				if(dbPass.equals(pass)){
					pstmt.close(); 
					pstmt = conn.prepareStatement(
							"delete from BOARD where NUM = ?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					result = 1; //삭제 성공
					if(fileName.equals(null)) {
					}else {
					File f= new File(location+fileName);
					f.delete();
					}
				} else {
					result = 0; //비밀번호 불일치
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return result;
	}
	//관리자가 삭제하는 메서드
	public int deleteMArticle(int num,String location){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String fileName=null;
		int result = -1;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select SERVER_FILENAME from BOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
					fileName=rs.getString("server_filename");
					pstmt.close(); 
					pstmt = conn.prepareStatement(
							"delete from BOARD where NUM = ?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					result = 1; //삭제 성공
					if(fileName.equals(null)) {
					}else {
					File f= new File(location+fileName);
					f.delete();
				} 
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return result;
	}


}
