package bb.board.model2;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
	private static CommentDao instance = null;
	private CommentDao(){}
	public static CommentDao getInstance(){
		if(instance == null){
			synchronized(CommentDao.class){
				instance = new CommentDao();
			}
		}
		return instance;
	}
	//이제부터 여기에 댓글에서 필요한 작업 기능들을 메서드로 추가하게 된다.
	
	//전체 댓글 개수를 알아오는 메서드
	public int getCArticleCount(int num){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from BOARDCOMMENT where bn=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
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
	//댓글 목록을 가져와서 List로 반환하는 메서드
	public List<CommentDto> getCArticles(int num){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CommentDto> articleList = null;
		try{
			conn = ConnUtil.getConnection();
			String sql ="select * from "
					+"(select NUM, WRITER,"
					+"PASS, REGDATE,"
					+"REF, STEP, DEPTH, CONTENT, IP, BN, MEM from" 
					+"(select * from BOARDCOMMENT order by STEP desc, REF asc)where bn=?)";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				articleList = new ArrayList<CommentDto>(5);
				do {
					CommentDto article = new CommentDto();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setPass(rs.getString("pass"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					article.setBn(rs.getInt("bn"));
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
	//댓글 저장을 처리하는 메서드
	public void insertArticle(CommentDto article){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int number = 0;
		String sql = "";
		System.out.println("depth::"+depth);
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(num) from BOARDCOMMENT");
			rs = pstmt.executeQuery();
			if(rs.next()){
				number = rs.getInt(1) + 1;
			} else {
				number = 1;
			}
			if(num != 0){	//답글일 경우
				System.out.println("num::"+num);
				sql = "update BOARDCOMMENT set STEP = STEP+1 where REF = ? and STEP > ?";
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
			sql = "insert into BOARDCOMMENT"
					+ "(NUM, WRITER, PASS, "
					+ "REGDATE, REF, STEP, DEPTH, CONTENT, IP, BN, MEM) "
					+ "values(BOARDCOMMENT_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getPass());
			pstmt.setTimestamp(3, article.getRegdate());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, step);
			pstmt.setInt(6, depth);
			pstmt.setString(7, article.getContent());
			pstmt.setString(8, article.getIp());
			pstmt.setInt(9,  article.getBn());
			pstmt.setInt(10,  article.getMem());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
	}
	//댓글 내용을 가져오는 메서드
	public CommentDto getArticle(int num){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommentDto article = null;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select * from BOARDCOMMENT where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				article = new CommentDto();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
				article.setBn(rs.getInt("bn"));
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
	//댓글삭제
	public int deleteArticle(int num, String pass){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPass = "";
		int result = -1;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select PASS from BOARDCOMMENT where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dbPass = rs.getString("pass");
				if(dbPass.equals(pass)){
					pstmt.close(); 
					pstmt = conn.prepareStatement(
							"delete from BOARDCOMMENT where NUM = ?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					result = 1; //삭제 성공
					}else {
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
}
