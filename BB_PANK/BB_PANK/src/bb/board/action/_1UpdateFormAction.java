package bb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.model1.BoardDao;
import bb.board.model1.BoardDto;

// 수정 요청을 처리할 Action 클래스 작성
public class _1UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		BoardDao dbPro = BoardDao.getInstance();
		BoardDto article = dbPro.updateGetArticle(num);
		
		//뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("bn", request.getParameter("bn"));
		
		return "/bbboard1/updateForm.jsp"; //보여줄 뷰 경로
	}
	

}
