package bb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.model4.BoardDao;
import bb.board.model4.BoardDto;

public class _4UpdateProAction implements CommandAction {
	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
	request.setCharacterEncoding("UTF-8");
	String pageNum = request.getParameter("pageNum");
	
	BoardDto article = new BoardDto(); //데이터를 처리할 빈
	article.setNum(Integer.parseInt(request.getParameter("num")));
	article.setWriter(request.getParameter("writer"));
	article.setPreface(request.getParameter("preface"));
	article.setSubject(request.getParameter("subject"));
	article.setPass(request.getParameter("pass"));
	article.setContent(request.getParameter("content"));
	article.setBn(Integer.parseInt(request.getParameter("bn")));
	BoardDao dbPro = BoardDao.getInstance(); //DB 연결
	int check = dbPro.updateArticle(article);
	
	//뷰에 사용할 속성
	request.setAttribute("pageNum", new Integer(pageNum));
	request.setAttribute("check", new Integer(check));
	request.setAttribute("bn", request.getParameter("bn"));
	return "/bbboard4/updatePro.jsp";//해당 뷰 경로 반환
	}
 
}
