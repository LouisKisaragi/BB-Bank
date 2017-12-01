package action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.J002_BoardDTO;
import model.J003_DAO;

public class J011_UpdateProAction implements J005_CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("pageNum");
		
		J002_BoardDTO article = new J002_BoardDTO();	// 데이터를 처리할 Bean
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setSubject(request.getParameter("subject"));
		article.setPass(request.getParameter("pass"));
		article.setContent(request.getParameter("content"));
		
		J003_DAO dbPro = J003_DAO.getInstance();
		int check = dbPro.updateArticle(article);
		
		// 뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		return "/aboard/m006_updatePro.jsp";	// 해당 뷰 경로 반환
	}
}