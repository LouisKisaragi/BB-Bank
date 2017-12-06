package bb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.model2.BoardDao;
import bb.board.model2.BoardDto;

public class _2DeleteFormAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		System.out.println(pageNum);
		BoardDao dbPro = BoardDao.getInstance();
		BoardDto article = dbPro.getArticle(num);
		
		request.setAttribute("writer", article.getWriter());
		request.setAttribute("mem", article.getMem());
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("bn",request.getParameter("bn"));
		return "/bbboard2/deleteForm.jsp";	//처리할 뷰 경로
	}

}
