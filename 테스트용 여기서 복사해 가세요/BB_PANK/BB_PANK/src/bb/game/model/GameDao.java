package bb.game.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bb.board.model2.ConnUtil;

public class  GameDao{
	private static GameDao instance = null;
	private GameDao(){}
	
	public static GameDao getInstance(){
		if(instance == null){
			synchronized(GameDao.class){
				instance = new GameDao();
			}
		}
		return instance;
	}
	//닉네임이 존제하는가 확인
	public boolean getGNick(String nick, int gnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result=false;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select * from VOTEPLAYER where VOTEGAMENUM = ? and VOTENICK=?");
			pstmt.setInt(1, gnum);
			pstmt.setString(2, nick);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result=true;
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
	//경기 종료날짜 확인
	//응원 팀별 응원글수
	public int getPArticle(int gnum, String team) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result=0;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select count(*) from VOTEPLAYER where VOTEGAMENUM = ? and VOTETEAM=?");
			pstmt.setInt(1, gnum);
			pstmt.setString(2, team);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
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
	//응원시 투표율+배율 갱신
	public void updateBArticle(int gnum, int team1vote, int team2vote, String team1votetime, String team2votetime){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "";
			try{
				conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement(
						"select * from GAME where GNUM = ?");
				pstmt.setInt(1, gnum);
				rs = pstmt.executeQuery();
				if(rs.next()){
				//쿼리 작성
				sql = "update GAME set "
						+ "TEAM1VOTE=?, TEAM2VOTE=?, TEAM1VOTETIME=?, TEAM2VOTETIME=?"
						+ "where GNUM=?";
				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, team1vote);
				pstmt.setInt(2, team2vote);
				pstmt.setString(3, team1votetime);
				pstmt.setString(4, team2votetime);
				pstmt.setInt(5, gnum);
				pstmt.executeUpdate();
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(rs != null) try { rs.close(); } catch (SQLException e){}
				if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
				if(conn != null) try { conn.close(); } catch (SQLException e){}
			}
		}
	//응원글 내용
	public GameDto getGArticle(int gnum){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GameDto article = null;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select * from Game where GNUM = ?");
			pstmt.setInt(1, gnum);
			rs = pstmt.executeQuery();
			if(rs.next()){
				article = new GameDto();
				article.setGnum(rs.getInt("gnum"));
				article.setTeam1(rs.getString("team1"));
				article.setTeam2(rs.getString("team2"));
				article.setStartday(rs.getString("startday"));
				article.setEndday(rs.getString("endday"));
				article.setLeague(rs.getString("league"));
				article.setTitle(rs.getString("title"));
				article.setTeam1vote(rs.getInt("team1vote"));
				article.setTeam2vote(rs.getInt("team2vote"));
				article.setTeam1votetime(rs.getString("team1votetime"));
				article.setTeam2votetime(rs.getString("team2votetime"));
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
	//글 목록을 가져와서 List로 반환하는 메서드(비회원용)
	public List<GameDto> getNArticles(int start, int end, String preface){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GameDto> articleList = null;
		try{
			conn = ConnUtil.getConnection();
			String sql=null;
			if(preface.equals("all")) {
				sql="select * from(select * from "
						+"(select rownum RNUM, GNUM, TEAM1,"
						+"TEAM2, STARTDAY, ENDDAY, PLAY,"
						+"WINNER,LEAGUE,TITLE, TEAM1VOTE, TEAM2VOTE, TEAM1VOTETIME, TEAM2VOTETIME from" 
						+"(select * from GAME order by GNUM)))"
						+"where RNUM >= ? and RNUM <= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
			}
			else {
				sql="select * from(select * from "
						+"(select rownum RNUM, GNUM, TEAM1,"
						+"TEAM2, STARTDAY, ENDDAY, PLAY,"
						+"WINNER,LEAGUE,TITLE, TEAM1VOTE, TEAM2VOTE, TEAM1VOTETIME, TEAM2VOTETIME from" 
						+"(select * from GAME order by GNUM))where PLAY=?)"
						+"where RNUM >= ? and RNUM <= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "yes");
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
					rs = pstmt.executeQuery();
			}
			if(rs.next()){
				articleList = new ArrayList<GameDto>(5);
				do {
					GameDto article = new GameDto();
					article.setGnum(rs.getInt("gnum"));
					article.setTeam1(rs.getString("team1"));
					article.setTeam2(rs.getString("team2"));
					article.setStartday(rs.getString("startday"));
					article.setEndday(rs.getString("endday"));
					article.setLeague(rs.getString("league"));
					article.setPlay(rs.getString("play"));
					article.setWinner(rs.getString("winner"));
					article.setTitle(rs.getString("title"));
					article.setTeam1vote(rs.getInt("team1vote"));
					article.setTeam2vote(rs.getInt("team2vote"));
					article.setTeam1votetime(rs.getString("team1votetime"));
					article.setTeam2votetime(rs.getString("team2votetime"));
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
	//글 목록을 가져와서 List로 반환하는 메서드+분류별
	public List<GameDto> getArticles(int start, int end, String preface, String id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GameDto> articleList = null;
		try{
			conn = ConnUtil.getConnection();
			String sql=null;
			if(preface.equals("all")) {
				 sql="select * from(select * from "
						+"(select rownum RNUM, GNUM, TEAM1,"
						+"TEAM2, STARTDAY, ENDDAY, PLAY,"
						+"WINNER,LEAGUE, TITLE, TEAM1VOTE, TEAM2VOTE, TEAM1VOTETIME, TEAM2VOTETIME from" 
						+"(select * from GAME order by GNUM)))"
						+"where RNUM >= ? and RNUM <= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
			}else if(preface.equals("me")) {
				 sql="select * from(select * from "
							+"(select rownum RNUM, GNUM, TEAM1,"
							+"TEAM2, STARTDAY, ENDDAY, PLAY,"
							+"WINNER, LEAGUE, TITLE, TEAM1VOTE, TEAM2VOTE, TEAM1VOTETIME, TEAM2VOTETIME from" 
							+"(select * from GAME order by GNUM)where GNUM in (select VOTEGAMENUM from VOTEPLAYER where VOTEID=?))"
							+"where RNUM >= ? and RNUM <= ?";
	
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, id);
						pstmt.setInt(2, start);
						pstmt.setInt(3, end);
						rs = pstmt.executeQuery();
				}else if(preface.equals("notme")) {
					sql="select * from(select * from "
							+"(select rownum RNUM, GNUM, TEAM1,"
							+"TEAM2, STARTDAY, ENDDAY, PLAY,"
							+"WINNER,LEAGUE, TITLE, TEAM1VOTE, TEAM2VOTE, TEAM1VOTETIME, TEAM2VOTETIME from" 
							+"(select * from GAME order by GNUM)where GNUM in (select VOTEGAMENUM from VOTEPLAYER where VOTEGAMENUM not in(select VOTEGAMENUM from VOTEPLAYER where VOTEID=?))"
							+"where RNUM >= ? and RNUM <= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
					rs = pstmt.executeQuery();
				}else if(preface.equals("endgame")) {
					sql="select * from(select * from "
							+"(select rownum RNUM, GNUM, TEAM1,"
							+"TEAM2, STARTDAY, ENDDAY, PLAY,"
							+"WINNER,LEAGUE, TITLE, TEAM1VOTE, TEAM2VOTE, TEAM1VOTETIME, TEAM2VOTETIME from" 
							+"(select * from GAME order by GNUM)where PLAY=?))"
							+"where RNUM >= ? and RNUM <= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "yes");
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
					rs = pstmt.executeQuery();
				}else if(preface.equals("endmygame")) {
					sql="select * from(select * from "
							+"(select rownum RNUM, GNUM, TEAM1,"
							+"TEAM2, STARTDAY, ENDDAY, PLAY,"
							+"WINNER,LEAGUE, TITLE, TEAM1VOTE, TEAM2VOTE, TEAM1VOTETIME, TEAM2VOTETIME from" 
							+"(select * from GAME order by GNUM)where PLAY=? and GNUM in (select VOTEGAMENUM from VOTEPLAYER where VOTEID=?)))"
							+"where RNUM >= ? and RNUM <= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "yes");
					pstmt.setString(2, id);
					pstmt.setInt(3, start);
					pstmt.setInt(4, end);
					rs = pstmt.executeQuery();
				}
		
			if(rs.next()){
				articleList = new ArrayList<GameDto>(5);
				do {
					GameDto article = new GameDto();
					article.setGnum(rs.getInt("gnum"));
					article.setTeam1(rs.getString("team1"));
					article.setTeam2(rs.getString("team2"));
					article.setStartday(rs.getString("startday"));
					article.setEndday(rs.getString("endday"));
					article.setLeague(rs.getString("league"));
					article.setTitle(rs.getString("title"));
					article.setTeam1vote(rs.getInt("team1vote"));
					article.setTeam2vote(rs.getInt("team2vote"));
					article.setWinner(rs.getString("winner"));
					article.setPlay(rs.getString("play"));
					article.setTeam1votetime(rs.getString("team1votetime"));
					article.setTeam2votetime(rs.getString("team2votetime"));
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
	//응원글 전체 개수(비회원용)
	public int getArticleNCount(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement("select count(*) from GAME");
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
	//응원글 전체 개수
	public int getArticleCount(String preface, String id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = ConnUtil.getConnection();
			if(preface.equals("all")) {//[전부]일경우
					pstmt = conn.prepareStatement("select count(*) from GAME");
					rs = pstmt.executeQuery();
			}else if(preface.equals("me")){
				pstmt = conn.prepareStatement("select count(*) from game where gnum in(select votegamenum from voteplayer where voteid=?);");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
			}else if(preface.equals("notme")){
				pstmt = conn.prepareStatement("select count(*) from game where gnum in(select votegamenum from voteplayer where votegamenum not in(select votegamenum from voteplayer where voteid=?));");
				pstmt.setString(1, id);
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
}
