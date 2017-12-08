package bb.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bb.board.model2.ConnUtil;

public class  AdminDao{
	private static AdminDao instance = null;
	private AdminDao(){}
	public static AdminDao getInstance(){
		if(instance == null){
			synchronized(AdminDao.class){
				instance = new AdminDao();
			}
		}
		return instance;
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
	//아이디 찾아서 출력
	public String getId(String name,String email) {
		String id=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try{
			conn = ConnUtil.getConnection();
			//쿼리 작성
			//System.out.println("name"+name);
			//System.out.println("email"+email);
			
			sql = "select id from MEMBER where NAME=? and EMAIL=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.executeQuery();
			rs=pstmt.executeQuery();
			if(rs.next()) {
				id=rs.getString("id");
			}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(rs != null) try { rs.close(); } catch (SQLException e){}
				if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
				if(conn != null) try { conn.close(); } catch (SQLException e){}
			}
		//System.out.println("sq+l"+sql);
		return id;
	}
	//비밀번호 찾아서 출력
	public String getPass(String id,String email) {
		String pass=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try{
			conn = ConnUtil.getConnection();
			//쿼리 작성
			//System.out.println("id="+id);
			//System.out.println("email="+email);
			sql = "select pass from MEMBER where ID=? and EMAIL=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			pstmt.executeQuery();
			rs=pstmt.executeQuery();
			if(rs.next()) {
				pass=rs.getString("pass");
				//System.out.println("pass:::"+pass);
			}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(rs != null) try { rs.close(); } catch (SQLException e){}
				if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
				if(conn != null) try { conn.close(); } catch (SQLException e){}
			}
		return pass;
	}
	//회원정보 보기-닉네임으로검색
	public AdminDto memberSeeArticle(String nick) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		AdminDto article = null;
		try{
			conn = ConnUtil.getConnection();
			//System.out.println("1");
			//쿼리 작성
			sql = "select * from MEMBER where NICKNAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nick);
			pstmt.executeQuery();
			rs=pstmt.executeQuery();
			if(rs.next()) {
				article = new AdminDto();
				article.setName(rs.getString("name"));
				article.setEmail(rs.getString("email"));
				article.setId(rs.getString("id"));
				article.setJoindate(rs.getTimestamp("joindate"));
				article.setPoint(rs.getInt("point"));
				article.setSuper_m(rs.getString("super_m"));
				article.setNickname(rs.getString("nickname"));
				article.setLogindate(rs.getString("logindate"));
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
	
	//회원정보 가져오기
	public AdminDto memberArticle(String id,String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		AdminDto article = null;
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
				article = new AdminDto();
				article.setName(rs.getString("name"));
				article.setEmail(rs.getString("email"));
				article.setId(rs.getString("id"));
				article.setJoindate(rs.getTimestamp("joindate"));
				article.setPass(rs.getString("pass"));
				article.setPoint(rs.getInt("point"));
				article.setSuper_m(rs.getString("super_m"));
				article.setNickname(rs.getString("nickname"));
				article.setLogindate(rs.getString("logindate"));
				
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