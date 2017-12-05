package game.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;
import board.model.BoardDto;
import game.model.GameDao;
import game.model.GameDto;
import game.model.PlayerDao;
import game.model.PlayerDto;

public class VoteAction implements CommandAction{


	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
	int ref,depth,step;

	
		
		PlayerDto article = new PlayerDto();	//데이터를 처리할 빈
	
		article.setVotegamenum(Integer.parseInt(request.getParameter("gnum")));
		article.setVoteid(request.getParameter("id"));
		article.setVotenick(request.getParameter("nick"));
		if(request.getParameter("commentTeam1")!="") {
			//team1일때
			article.setVoteteam("team1");
			article.setVotecomment(request.getParameter("commentTeam1"));
		}else {
			article.setVoteteam("team2");
			article.setVotecomment(request.getParameter("commentTeam2"));
		}
		
		PlayerDao dbPro = PlayerDao.getInstance(); //DB 연결
		dbPro.insertPArticle(article);
		
		
		
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		
		//해당 페이지 번호
		String pageNum = request.getParameter("pageNum");
		GameDao d1bPro = GameDao.getInstance();
		
		//해당 글번호에 대한 레코드
		GameDto a1rticle = d1bPro.getGArticle(gnum);
		int team1vote = a1rticle.getTeam1vote();
		int team2vote = a1rticle.getTeam2vote();
		int sum=team1vote+team2vote;
		System.out.println("1팀배당률:"+(sum/team1vote)+"배");
		System.out.println("1팀퍼센트:"+(team1vote/sum)+"%");
		System.out.println("2팀배당률:"+(sum/team2vote)+"배");
		System.out.println("2팀퍼센트:"+(team2vote/sum)+"%");
		//dbPro.getPArticle(gnum,여기 팀이름);여기서 해당 글안에 팀별로 사람수 구하기 2개다구해서 각각 넣어야됨
		//배당률 (전체/본인팀사람수) 배
		//전체 퍼센테이지 (본인팀사람수/전체)%
		//소수점 다버림
		//뷰에서 사용할 속성
		request.setAttribute("num", new Integer(gnum));
		request.setAttribute("preface", request.getParameter("preface"));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", a1rticle);
		return "/game/votePro.jsp";	//해당 뷰 경로 반환
	}
}
