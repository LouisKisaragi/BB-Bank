package game.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.model.ConnUtil;

public class  PlayerDao{
	private static PlayerDao instance = null;
	private PlayerDao(){}
	public static PlayerDao getInstance(){
		if(instance == null){
			synchronized(PlayerDao.class){
				instance = new PlayerDao();
			}
		}
		return instance;
	}
	//응원 갯수를 불러오는 메서드
	public int getPArticleCount(int gnum){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from VOTEPLAYER where VOTEGAMENUM=?");
			pstmt.setInt(1, gnum);
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
	//응원 목록을 가져와서 List로 반환하는 메서드
	public List<PlayerDto> getPArticles(int gnum){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PlayerDto> articleList = null;
		try{
			conn = ConnUtil.getConnection();
			String sql ="select * from" 
					+"(select VOTENUM, VOTENICK,"
					+"VOTEID, VOTEGAMENUM,"
					+"VOTETEAM, VOTECOMMENT from"
					+"(select * from VOTEPLAYER order by votenum)where votegamenum=?)";
			pstmt = conn.prepareStatement(sql);
			//System.out.println(sql);
			pstmt.setInt(1, gnum);
			rs = pstmt.executeQuery();
			if(rs.next()){
				articleList = new ArrayList<PlayerDto>(5);
				do {
					PlayerDto article = new PlayerDto();
					article.setVotenum(rs.getInt("votenum"));
					article.setVotenick(rs.getString("votenick"));
					article.setVoteid(rs.getString("voteid"));
					article.setVotegamenum(rs.getInt("votegamenum"));
					article.setVoteteam(rs.getString("voteteam"));
					article.setVotecomment(rs.getString("votecomment"));
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
	//응원글 저장을 처리하는 메서드
	public void insertPArticle(PlayerDto article){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql=null;
			//쿼리 작성
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(num) from VOTEPLAYER");
			sql = "insert into VOTEPLAYER"
					+ "(VOTENUM, VOTENICK, VOTEID, "
					+ "VOTEGAMENUM, VOTETEAM, VOTECOMMENT) "
					+ "values(VOTENUM_SEQ.nextval, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getVotenick());
			pstmt.setString(2, article.getVoteid());
			pstmt.setInt(3, article.getVotegamenum());
			pstmt.setString(4, article.getVoteteam());
			pstmt.setString(5, article.getVotecomment());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
	}
	//응원글 내용을 가져오는 메서드
	public PlayerDto getPArticle(int gnum){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PlayerDto article = null;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select * from VOTEPLAYER where VOTEGAMENUM = ?");
			pstmt.setInt(1, gnum);
			rs = pstmt.executeQuery();
			if(rs.next()){
				article = new PlayerDto();
				article.setVotenum(rs.getInt("votenum"));
				article.setVotenick(rs.getString("votenick"));
				article.setVoteid(rs.getString("voteid"));
				article.setVotegamenum(rs.getInt("votegamenum"));
				article.setVoteteam(rs.getString("voteteam"));
				article.setVotecomment(rs.getString("votecomment"));
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
}