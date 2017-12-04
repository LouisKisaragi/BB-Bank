package action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import model.J002_BoardDTO;
import model.J003_DAO;

public class J008_WriteProAction implements J005_CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		J002_BoardDTO article = new J002_BoardDTO();	// 데이터를 처리할 Bean
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setBn(Integer.parseInt(request.getParameter("bn")));	// 게시판 번호
		article.setWriter(request.getParameter("writer"));
		article.setSubject(request.getParameter("subject"));
		article.setPass(request.getParameter("pass"));
		article.setRegdate(new Timestamp(System.currentTimeMillis()));
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setStep(Integer.parseInt(request.getParameter("step")));
		article.setDepth(Integer.parseInt(request.getParameter("depth")));
		article.setContent(request.getParameter("content"));
		article.setPreface(request.getParameter("preface"));
		article.setMem(Integer.parseInt(request.getParameter("mem")));
		System.out.println("bn ? : " + request.getParameter("bn"));
		System.out.println("mem ? : " + request.getParameter("mem"));
		article.setIp(request.getRemoteAddr());
		
		J003_DAO dbPro = J003_DAO.getInstance(); // DB 연결
		dbPro.insertArticle(article);
		return "/aboard/m004_writePro.jsp";	// 해당 뷰 경로 반환
	}
}