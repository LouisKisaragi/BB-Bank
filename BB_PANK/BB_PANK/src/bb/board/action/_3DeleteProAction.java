
package bb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.model3.BoardDao;

public class _3DeleteProAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		int bn=Integer.parseInt(request.getParameter("bn"));
		String pageNum = request.getParameter("pageNum");
		String pass = request.getParameter("pass");
		String location=request.getSession().getServletContext().getRealPath("/upload/");
		BoardDao dbPro = BoardDao.getInstance();
		int check = dbPro.deleteArticle(num, pass, location);
	
		//뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		request.setAttribute("bn", new Integer(bn));
		return "/bbboard3/deletePro.jsp"; //뷰 경로
	}
}
