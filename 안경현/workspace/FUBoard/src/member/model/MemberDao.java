package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import board.model.ConnUtil;

public class  MemberDao{
	private static MemberDao instance = null;
	private MemberDao(){}
	public static MemberDao getInstance(){
		if(instance == null){
			synchronized(MemberDao.class){
				instance = new MemberDao();
			}
		}
		return instance;
	}
	//이메일 중복확인
	public int MemberEmailCheck(String email) {
		int result=-1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//0이면 가능 / 1이면 중복
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from MEMBER where email=? and super_m=0");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result=1;
			}else {
				result=0;
			}
		} catch(Exception ex){
				ex.printStackTrace();
		} finally{
			if(rs != null) try{rs.close(); } catch(SQLException e){}
			if(pstmt != null) try{pstmt.close(); } catch(SQLException e){}
			if(conn != null) try{conn.close(); } catch(SQLException e){}
		}
		return result;
	}
	//닉네임 중복확인
	public int MemberNickCheck(String nick) {
		int result=-1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//0이면 가능 / 1이면 중복
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from MEMBER where NICKNAME=? and super_m=0");
			pstmt.setString(1, nick);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result=1;
			}else {
				result=0;
			}
		} catch(Exception ex){
				ex.printStackTrace();
		} finally{
			if(rs != null) try{rs.close(); } catch(SQLException e){}
			if(pstmt != null) try{pstmt.close(); } catch(SQLException e){}
			if(conn != null) try{conn.close(); } catch(SQLException e){}
		}
		return result;
	}
	
	//아이디 중복확인
	public int MemberIdCheck(String id) {
		int result=-1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//0이면 가능 / 1이면 중복
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from MEMBER where id=? and super_m=0");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result=1;
			}else {
				result=0;
			}
		} catch(Exception ex){
				ex.printStackTrace();
		} finally{
			if(rs != null) try{rs.close(); } catch(SQLException e){}
			if(pstmt != null) try{pstmt.close(); } catch(SQLException e){}
			if(conn != null) try{conn.close(); } catch(SQLException e){}
		}
		return result;
	}
	//회원 가입(저장)
	public void insertArticle(MemberDto article){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String super_m="0";
		try{
			conn = ConnUtil.getConnection();
			//쿼리 작성
			sql = "insert into MEMBER"
					+ "(PNUM, ID, PASS, NAME, EMAIL, NICKNAME, JOINDATE, SUPER_M)"					
					+ "values(BOARD_SEQ.nextval, ?, ?, ?, ?, ? ,? ,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getId());
			pstmt.setString(2, article.getPass());
			pstmt.setString(3, article.getName());
			pstmt.setString(4, article.getEmail());
			pstmt.setString(5, article.getNickname());
			pstmt.setTimestamp(6, article.getJoindate());
			pstmt.setString(7, super_m);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
	}
	//로그인
	public int loginArticle(String id,String pass) {
		int result=-1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try{
			conn = ConnUtil.getConnection();
			//쿼리 작성
			sql = "select * from MEMBER where ID=? and PASS=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2,pass);
			pstmt.executeQuery();
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=1;//로그인성공
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return result;
	}
	
	//회원정보 가져오기
	public MemberDto memberArticle(String id,String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		MemberDto article = null;
		try{
			conn = ConnUtil.getConnection();
			//쿼리 작성
			sql = "select * from MEMBER where ID=? and PASS=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2,pass);
			pstmt.executeQuery();
			rs=pstmt.executeQuery();
			if(rs.next()) {
				article = new MemberDto();
				article.setName(rs.getString("name"));
				article.setEmail(rs.getString("email"));
				article.setId(rs.getString("id"));
				article.setJoindate(rs.getTimestamp("joindate"));
				article.setPass(rs.getString("pass"));
				article.setPoint(rs.getInt("point"));
				article.setSuper_m(rs.getString("super_m"));
				article.setNickname(rs.getString("nickname"));
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return article;
	}
}