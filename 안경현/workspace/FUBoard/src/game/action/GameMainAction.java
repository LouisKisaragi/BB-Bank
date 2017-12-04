package game.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;

public class GameMainAction  implements CommandAction{
	public String requestPro (
			HttpServletRequest request,
			HttpServletResponse response)throws Throwable{
		request.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("pageNum"); //페이지 번호
		String preface=request.getParameter("preface");
		int pageSize = 5; //한 페이지 당 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		//페이지의 시작 글 번호
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize; //한 페이지의 마지막 글 번호
		int count = 0;
		int number = 0;
		
		
		return "/game/gameMain.jsp";
	}

}
