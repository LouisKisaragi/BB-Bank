package game.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import board.model.ConnUtil;

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
				pstmt = conn.prepareStatement("select count(*) from VOTEPLAYER where VOTEID=?");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
			}else {
				pstmt = conn.prepareStatement("select count(*) from VOTEPLAYER where VOTEID!=?");
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