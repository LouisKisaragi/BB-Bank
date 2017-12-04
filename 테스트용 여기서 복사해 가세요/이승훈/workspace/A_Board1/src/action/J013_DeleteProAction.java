package action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.J003_DAO;

public class J013_DeleteProAction implements J005_CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String pass = request.getParameter("pass");
		
		J003_DAO dbPro = J003_DAO.getInstance();
		int check = dbPro.deleteArticle(num, pass);
		
		// 뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		return "/aboard/m008_deletePro.jsp";	// 해당 뷰 경로 반환
	}
}