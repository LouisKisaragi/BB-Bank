package game.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.action.CommandAction;
import game.model.GameDao;
import game.model.GameDto;

public class GameMainAction  implements CommandAction{
	public String requestPro (
			HttpServletRequest request,
			HttpServletResponse response)throws Throwable{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		String pageNum = request.getParameter("pageNum"); //페이지 번호
		String preface=request.getParameter("preface");
		int pageSize = 5; //한 페이지 당 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		//페이지의 시작 글 번호
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize; //한 페이지의 마지막 글 번호
		int count = 0;
		int number = 0;
		List<GameDto> articleList = null;

			GameDao dbPro = GameDao.getInstance(); //DB연결
			String id = (String)session.getAttribute("loginId");

			if(id!=null) {
				count = dbPro.getArticleCount(preface,id); //전체 글 개수
				if(count > 0){ //현재 페이지의 글 목록
					articleList = dbPro.getArticles(startRow, endRow, preface, id);
				} else {
					articleList = Collections.emptyList();
				}
			
			}else {
				count = dbPro.getArticleNCount(); //전체 글 개수
				if(count > 0){ //현재 페이지의 글 목록
					articleList = dbPro.getNArticles(startRow, endRow);
					System.out.println("5");
				} else {
					articleList = Collections.emptyList();
				}
			
			}
			number = count - (currentPage-1) * pageSize; //글 목록에 표시할 글 번호
			
			//해당 뷰에서 사용할 속성
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", new Integer(currentPage));
			request.setAttribute("startRow", new Integer(startRow));
			request.setAttribute("endRow", new Integer(endRow));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("pageSize", new Integer(pageSize));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("articleList", articleList);
			request.setAttribute("preface", preface);

		return "/game/gameMain.jsp";
	}

}
