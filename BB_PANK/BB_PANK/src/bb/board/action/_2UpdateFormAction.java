package bb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.model2.BoardDao;
import bb.board.model2.BoardDto;

public class _2UpdateFormAction implements CommandAction {

	public String requestPro(
			HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		BoardDao dbPro = BoardDao.getInstance();
		BoardDto article = dbPro.updateGetArticle(num);
		
		//뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("bn", request.getParameter("bn"));
		return "/bbboard2/updateForm.jsp"; //보여줄 뷰 경로
	}

}
