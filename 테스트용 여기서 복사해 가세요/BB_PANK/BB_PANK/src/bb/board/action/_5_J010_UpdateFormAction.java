package bb.board.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.model5.J002_BoardDTO;
import bb.board.model5.J003_DAO;

public class _5_J010_UpdateFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		J003_DAO dbPro = J003_DAO.getInstance();
		J002_BoardDTO article = dbPro.updateGetArticle(num);
		
		// 뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
		return "/bbboard5/m005_updateForm.jsp";	// 해당 뷰 경로 반환
	}
}