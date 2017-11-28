package board.model;


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
	public int getCommentCount(int num){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from BOARDCOMMENT where BN=?");
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
	public List<CommentDto> getComments(int num){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CommentDto> CommentList = null;
		try{
			conn = ConnUtil.getConnection();
			String sql ="select * from "
					+"(select NUM, WRITER,"
					+"PASS, REGDATE,"
					+"REF, STEP, DEPTH, CONTENT, IP, BN, MEM from " 
					+"(select * from BOARDCOMMENT order by STEP desc, REF asc) where BN=?)";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				CommentList = new ArrayList<CommentDto>(5);
				do {
					CommentDto comment = new CommentDto();
					comment.setNum(rs.getInt("num"));
					comment.setWriter(rs.getString("writer"));
					comment.setPass(rs.getString("pass"));
					comment.setRegdate(rs.getTimestamp("regdate"));
					comment.setRef(rs.getInt("ref"));
					comment.setStep(rs.getInt("step"));
					comment.setDepth(rs.getInt("depth"));
					comment.setContent(rs.getString("content"));
					comment.setIp(rs.getString("ip"));
					comment.setBn(rs.getInt("bn"));
					comment.setMem(rs.getInt("mem"));
					CommentList.add(comment);
				} while(rs.next());
			}
		} catch(Exception e){
				e.printStackTrace();
		} finally{
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return CommentList;
	}
	//댓글 저장을 처리하는 메서드
	public void insertComment(CommentDto comment){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = comment.getNum();
		int ref = comment.getRef();
		int step = comment.getStep();
		int depth = comment.getDepth();
		int number = 0;
		String sql = "";
		System.out.println("depth::"+depth);
		System.out.println("ref ::"+ref);
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(num) from BOARDCOMMENT");
			rs = pstmt.executeQuery();
			if(rs.next()){
				number = rs.getInt(1)+1;
			} else {
				number = 1;
			}
			if(num != 0){	//답글일 경우
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
			sql = "insert into BOARDCOMMENT "
					+ "(NUM, WRITER, PASS, "
					+ "REGDATE, REF, STEP, DEPTH, CONTENT, IP, BN, MEM) "
					+ "values(BOARDCOMMENT_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getWriter());
			pstmt.setString(2, comment.getPass());
			pstmt.setTimestamp(3, comment.getRegdate());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, step);
			pstmt.setInt(6, depth);
			pstmt.setString(7, comment.getContent());
			pstmt.setString(8, comment.getIp());
			pstmt.setInt(9,  comment.getBn());
			pstmt.setInt(10, comment.getMem());
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
	public CommentDto getComment(int num){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommentDto comment = null;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select * from BOARDCOMMENT where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				comment = new CommentDto();
				comment.setNum(rs.getInt("num"));
				comment.setWriter(rs.getString("writer"));
				comment.setPass(rs.getString("pass"));
				comment.setRegdate(rs.getTimestamp("regdate"));
				comment.setRef(rs.getInt("ref"));
				comment.setStep(rs.getInt("step"));
				comment.setDepth(rs.getInt("depth"));
				comment.setContent(rs.getString("content"));
				comment.setIp(rs.getString("ip"));
				comment.setBn(rs.getInt("bn"));
				comment.setMem(rs.getInt("mem"));
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return comment;
	}
	//댓글삭제
	public int deleteComment(int num, String pass){
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
			System.out.println("re:"+result);
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
