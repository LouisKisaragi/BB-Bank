package board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDao;
import board.model.BoardDto;
import board.model.CommentDao;
import board.model.CommentDto;

public class CommentAction implements CommandAction{


	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
	int ref,depth,step;

	
		if(request.getParameter("ref")==null) {
			ref=0;
		}else {
			ref=Integer.parseInt(request.getParameter("ref"));
		}
		if(request.getParameter("depth")==null) {
			depth=0;
		}else {
			depth=Integer.parseInt(request.getParameter("depth"));
		}
		if(request.getParameter("step")==null) {
		 	step=0;
		}else {		
			 step=Integer.parseInt(request.getParameter("step"));
		}
		CommentDto article = new CommentDto();	//데이터를 처리할 빈
		System.out.println("writer=="+request.getParameter("cwriter"));
		System.out.println("pass=="+request.getParameter("cpass"));
		System.out.println("ref=="+ref);
		System.out.println("step=="+step);
		System.out.println("depth=="+depth);
		System.out.println("content=="+request.getParameter("ccomment"));
		System.out.println("bn=="+request.getParameter("num"));
		article.setNum(Integer.parseInt(request.getParameter("cnum")));
		article.setWriter(request.getParameter("cwriter"));
		article.setPass(request.getParameter("cpass"));
		article.setRegdate(new Timestamp(System.currentTimeMillis()));
		article.setRef(ref);
		article.setStep(step);
		article.setDepth(depth);
		article.setContent(request.getParameter("ccomment"));
		article.setBn(Integer.parseInt(request.getParameter("num")));
		article.setIp(request.getRemoteAddr());
		CommentDao dbPro = CommentDao.getInstance(); //DB 연결
		dbPro.insertArticle(article);
		
		
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		//해당 페이지 번호
		String pageNum = request.getParameter("pageNum");
		String bn = request.getParameter("bn");
		BoardDao d1bPro = BoardDao.getInstance();
		
		//해당 글번호에 대한 레코드
		BoardDto a1rticle = d1bPro.getArticle(num);
		
		//뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", a1rticle);
		request.setAttribute("bn", new Integer(bn));
		return "/board/commentPro.jsp";	//해당 뷰 경로 반환
	}
}
