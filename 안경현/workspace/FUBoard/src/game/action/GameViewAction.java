package game.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;
import game.model.GameDao;
import game.model.GameDto;
import game.model.PlayerDao;
import game.model.PlayerDto;

//글 내용을 처리
public class GameViewAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		List<PlayerDto> ParticleList = null;
		int gnum = Integer.parseInt(request.getParameter("gnum"));
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
		GameDao dbPro = GameDao.getInstance();
		//해당 글번호에 대한 레코드
		GameDto article = dbPro.getGArticle(gnum);
		//뷰에서 사용할 속성
		request.setAttribute("gnum", new Integer(gnum));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("artclec", articlec);
		request.setAttribute("count", Pcount);
		request.setAttribute("articleList", ParticleList);

		return "/game/gameView.jsp"; //요청에 응답할 뷰 경로
	}

}
