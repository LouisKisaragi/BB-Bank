package freeboard.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.model.BoardDao;
import freeboard.model.BoardDto;

public class WriteProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		BoardDto article = new BoardDto(); // 데이터를 처리할 빈
		System.out.println("num:"+request.getParameter("num"));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setBn(Integer.parseInt(request.getParameter("bn")));
		article.setWriter(request.getParameter("writer"));
		article.setPreface(request.getParameter("preface"));
		article.setSubject(request.getParameter("subject"));
		article.setPass(request.getParameter("pass"));
		article.setRegdate(new Timestamp(System.currentTimeMillis()));
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setStep(Integer.parseInt(request.getParameter("step")));
		article.setDepth(Integer.parseInt(request.getParameter("depth")));
		article.setContent(request.getParameter("content"));
		article.setIp(request.getRemoteAddr());

		BoardDao dbPro = BoardDao.getInstance(); // DB 연결
		dbPro.insertArticle(article);
		return "/freeboard/writePro.jsp"; //해당 뷰 경로 반환
	}
}
