package action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.J002_BoardDTO;
import model.J003_DAO;

// 글 내용을 처리
public class J009_ContentAction implements J005_CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 해당 글 번호
		int num = Integer.parseInt(request.getParameter("num"));
		
		// 해당 페이지 번호
		String pageNum = request.getParameter("pageNum");
		J003_DAO dbPro = J003_DAO.getInstance();
		
		// 해당 글 번호에 대한 레코드
		J002_BoardDTO article = dbPro.getArticle(num);
		
		// 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		return "/aboard/m002_content.jsp";	// 해당 뷰 경로 반환
	}
}