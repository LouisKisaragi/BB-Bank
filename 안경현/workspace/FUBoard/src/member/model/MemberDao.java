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
	//포인트 증감기능
	public int MemberPoint(String id, int P) {
		int point = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPoint = "";
		int result = -1;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select POINT from MEMBER where ID = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dbPoint = rs.getString("pass");
				point=Integer.parseInt(dbPoint)+P;
				pstmt.close();
				pstmt = conn.prepareStatement(
						"update MEMBER set POINT=? where ID=?");
				pstmt.setInt(1,point);
				pstmt.setString(2, id);
				pstmt.executeUpdate();
				result=1;
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
	//아이디 찾기
	public int MemberIdFind(String name,String email) {
		int result=-1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//0이면 가능 / 1이면 중복
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from MEMBER where name=? and email=? and super_m=0");
			pstmt.setString(1, name);
			pstmt.setString(2, email);
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
	//비밀번호 찾기
	public int MemberPassFind(String id,String email) {
		int result=-1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//0이면 가능 / 1이면 중복
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from MEMBER where id=? and email=? and super_m=0");
			pstmt.setString(1, id);
			pstmt.setString(2, email);
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
			System.out.println("name"+name);
			System.out.println("email"+email);
			
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
		System.out.println("sq+l"+sql);
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
			System.out.println("id="+id);
			System.out.println("email="+email);
			sql = "select pass from MEMBER where ID=? and EMAIL=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			pstmt.executeQuery();
			rs=pstmt.executeQuery();
			if(rs.next()) {
				pass=rs.getString("pass");
				System.out.println("pass:::"+pass);
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
	public MemberDto memberSeeArticle(String nick) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		MemberDto article = null;
		try{
			conn = ConnUtil.getConnection();
			System.out.println("1");
			//쿼리 작성
			sql = "select * from MEMBER where NICKNAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nick);
			pstmt.executeQuery();
			rs=pstmt.executeQuery();
			if(rs.next()) {
				article = new MemberDto();
				article.setName(rs.getString("name"));
				article.setEmail(rs.getString("email"));
				article.setId(rs.getString("id"));
				article.setJoindate(rs.getTimestamp("joindate"));
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
	//회원탈퇴
	public int memberOut(String id, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPass = "";
		int result = -1;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select PASS from MEMBER where ID = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dbPass = rs.getString("pass");
				if(dbPass.equals(pass)){
					pstmt.close(); 
					pstmt = conn.prepareStatement(
							"delete from MEMBER where ID = ?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					result = 1; //삭제 성공
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