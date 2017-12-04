package action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.J002_BoardDTO;
import model.J003_DAO;

public class J010_UpdateFormAction implements J005_CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		J003_DAO dbPro = J003_DAO.getInstance();
		J002_BoardDTO article = dbPro.updateGetArticle(num);
		
		// 뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
		return "/aboard/m005_updateForm.jsp";	// 해당 뷰 경로 반환
	}
}