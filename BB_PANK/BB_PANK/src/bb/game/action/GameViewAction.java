package bb.game.action;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bb.board.action.CommandAction;
import bb.game.model.GameDao;
import bb.game.model.GameDto;
import bb.game.model.PlayerDao;
import bb.game.model.PlayerDto;

//글 내용을 처리
public class GameViewAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		List<PlayerDto> ParticleList = null;
		HttpSession session = request.getSession(true);//세션이 있는지 없는지를 확인 
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		GameDao dbPro = GameDao.getInstance();
		//닉네임 비교
		String lognick=(String)session.getAttribute("logNick");
		if(dbPro.getGNick(lognick,gnum)==true) {
			//이미 해당글에 투표했어요!
			request.setAttribute("vote","true");
		}else {
			request.setAttribute("vote", "false");
		}
		PlayerDao PdbPro = PlayerDao.getInstance(); //DB연결
		int Pcount = PdbPro.getPArticleCount(gnum); //전체 글 개수
		if(Pcount > 0){ //현재 페이지의 글 목록
			ParticleList = PdbPro.getPArticles(gnum);
		} else {
			ParticleList = Collections.emptyList();
		}
		//해당 글번호
		PlayerDto articlec = PdbPro.getPArticle(gnum);
		//해당 페이지 번호
		String pageNum = request.getParameter("pageNum");

		//해당 글번호에 대한 레코드
		GameDto article = dbPro.getGArticle(gnum);
		//날짜 비교
				Date d = new Date();
				String end = article.getEndday();
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				String now = sdf.format(d);
				Date today=java.sql.Date.valueOf(now);
				Date endday=java.sql.Date.valueOf(end);
				boolean after=today.after(endday);
				//end기간이 지났을경우 true값이 나온다
		//뷰에서 사용할 속성
		request.setAttribute("gnum", new Integer(gnum));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("artclec", articlec);
		request.setAttribute("count", Pcount);
		request.setAttribute("articleList", ParticleList);
		request.setAttribute("finish", after);
		return "/bbgame/gameView.jsp"; //요청에 응답할 뷰 경로
	}

}
