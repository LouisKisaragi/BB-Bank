package bb.board.action2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bb.board.model2.CommentDao;

public class ComDeleteProAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		int cnum= Integer.parseInt(request.getParameter("cnum"));
		int num = Integer.parseInt(request.getParameter("num"));
		int bn=Integer.parseInt(request.getParameter("bn"));
		String pageNum = request.getParameter("pageNum");
		HttpSession session = request.getSession(false);
		String pass = null;
		if(session.equals(null)) {
			pass = request.getParameter("pass");
		}else {
			pass=(String)session.getAttribute("logPass");
		}
		CommentDao dbPro = CommentDao.getInstance();
		int check = dbPro.deleteArticle(cnum, pass);
	
		//뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		request.setAttribute("bn", new Integer(bn));
		request.setAttribute("cnum", cnum);
		return "/bbboard/CommentDeletePro.jsp"; //뷰 경로
	}
}
