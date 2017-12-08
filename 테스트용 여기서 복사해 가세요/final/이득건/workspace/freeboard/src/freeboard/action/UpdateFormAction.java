package freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.model.BoardDao;
import freeboard.model.BoardDto;

public class UpdateFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		BoardDao dbPro = BoardDao.getInstance();
		BoardDto article = dbPro.updateGetArticle(num);
System.out.println("bn:"+request.getParameter("bn"));
		// 뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("bn", request.getParameter("bn"));
		return "/freeboard/updateForm.jsp"; // 요청에 응답 할 뷰 경로
	}
}
