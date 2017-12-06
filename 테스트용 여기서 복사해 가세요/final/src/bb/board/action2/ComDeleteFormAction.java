package bb.board.action2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.model2.CommentDao;
import bb.board.model2.CommentDto;

public class ComDeleteFormAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		int num = Integer.parseInt(request.getParameter("num"));
		CommentDao dbPro = CommentDao.getInstance();
		CommentDto article = dbPro.getArticle(cnum);
		String pageNum = request.getParameter("pageNum");
		System.out.println(pageNum);
		request.setAttribute("mem", article.getMem());
		request.setAttribute("writer", article.getWriter());
		request.setAttribute("cnum", new Integer(cnum));
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("bn", request.getParameter("bn"));
		return "/bbboard2/CommentDeleteForm.jsp";	//처리할 뷰 경로
	}

}
