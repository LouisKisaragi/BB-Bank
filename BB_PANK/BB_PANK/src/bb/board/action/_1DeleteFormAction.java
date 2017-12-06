package bb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.model1.BoardDao;
import bb.board.model1.BoardDto;

public class _1DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDao dbPro = BoardDao.getInstance();
		BoardDto article = dbPro.getArticle(num);
		
		request.setAttribute("id", article.getWriter());
		request.setAttribute("mem", article.getMem());
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("bn", request.getParameter("bn"));
		
		return "/bbboard1/deleteForm.jsp"; //처리할 뷰 경로
	}

}
