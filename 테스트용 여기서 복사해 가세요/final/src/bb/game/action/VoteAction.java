package bb.game.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bb.board.action2.CommandAction;
import bb.game.model.GameDao;
import bb.game.model.GameDto;
import bb.game.model.PlayerDao;
import bb.game.model.PlayerDto;
import bb.member.model.MemberDao;
import bb.member.model.MemberDto;

public class VoteAction implements CommandAction{


	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		String team1Name=null;
		String team2Name=null;

		team1Name=request.getParameter("article.team1");
		team2Name=request.getParameter("article.team2");
		
		PlayerDto article = new PlayerDto();	//데이터를 처리할 빈

		article.setVotegamenum(Integer.parseInt(request.getParameter("gnum")));
		article.setVoteid(request.getParameter("id"));
		article.setVotenick(request.getParameter("nick"));
		GameDao d1bPro = GameDao.getInstance();
		int team1vote = d1bPro.getPArticle(gnum, team1Name);
		int team2vote = d1bPro.getPArticle(gnum, team2Name);
	//	System.out.println("저장된1팀응원사람수:"+team1vote);
	//	System.out.println("저장된2팀응원사람수:"+team2vote);
		if(request.getParameter("commentTeam1")!="") {
			//team1일때
			team1vote=team1vote+1;
			article.setVoteteam(request.getParameter("article.team1"));
			article.setVotecomment(request.getParameter("commentTeam1"));
		}else {
			team2vote=team2vote+1;
			//team2일때
			article.setVoteteam(request.getParameter("article.team2"));
			article.setVotecomment(request.getParameter("commentTeam2"));
		}
		
		PlayerDao dbPro = PlayerDao.getInstance(); //DB 연결
		dbPro.insertPArticle(article);
		
		//point삭감 및 session갱신
			HttpSession session = request.getSession();
			MemberDao dbMPro= MemberDao.getInstance();//회원 DB연결
			dbMPro.MemberPoint(session.getAttribute("logNick"),-500);
			String id=(String) session.getAttribute("logNick");
			MemberDto articleM = dbMPro.memberSeeArticle(id);
			session.setAttribute("logPoint", articleM.getPoint());
		//////////
		
	
		
		//해당 페이지 번호
		String pageNum = request.getParameter("pageNum");
		
		//해당 글번호에 대한 레코드
		GameDto a1rticle = d1bPro.getGArticle(gnum);
	//	System.out.println("변경된1팀응원사람수:"+team1vote);
	//	System.out.println("변경된2팀응원사람수:"+team2vote);
		
		float sum=team1vote+team2vote;
		float bat1team=sum/team1vote;
		float bat2team=sum/team2vote;
	//	System.out.println("총합:"+sum);
	//	System.out.printf("계산:"+String.format("%.1f",bat1team));
	//	System.out.println("1팀배당률:"+(bat1team)+"배");
	//	System.out.println("1팀퍼센트:"+(team1vote*100/sum)+"%");
	//	System.out.println("2팀배당률:"+(bat2team)+"배");
	//	System.out.println("2팀퍼센트:"+(team2vote*100/sum)+"%");
		team1vote=(int) (team1vote*100/sum);
		team2vote=(int) (team2vote*100/sum);
	//	System.out.println("저장될1팀퍼센트:"+(team1vote)+"%");
	//	System.out.println("저장될2팀퍼센트:"+(team2vote)+"%");
		String team1votetime=String.format("%.1f", bat1team);
		String team2votetime=String.format("%.1f", bat2team);
	//	System.out.println("1팀저장될거 : "+team1votetime);
	//	System.out.println("2팀저장될거 : "+team2votetime);
		d1bPro.updateBArticle(gnum, team1vote, team2vote, team1votetime, team2votetime);
		//뷰에서 사용할 속성
		request.setAttribute("gnum", new Integer(gnum));
		request.setAttribute("preface", request.getParameter("preface"));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", a1rticle);
		return "/bbgame/votePro.jsp";	//해당 뷰 경로 반환
	}
}
