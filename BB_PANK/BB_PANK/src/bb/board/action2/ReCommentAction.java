package bb.board.action2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.model2.CommentDao;
import bb.board.model2.CommentDto;

public class ReCommentAction implements CommandAction{


	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
	
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		
		//해당 페이지 번호
		String pageNum = request.getParameter("pageNum");
		String bn = request.getParameter("bn");
		
		
	
		CommentDao cdbPro = CommentDao.getInstance();
		CommentDto article = cdbPro.getArticle(cnum);

		
		//뷰에서 사용할 속성
		request.setAttribute("cnum", new Integer(cnum));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("bn", new Integer(bn));
		request.setAttribute("num", new Integer(request.getParameter("num")));
		return "/bbboard/reComment.jsp";	//해당 뷰 경로 반환
	}
}
