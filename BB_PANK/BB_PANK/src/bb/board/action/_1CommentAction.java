package bb.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.model1.BoardDao;
import bb.board.model1.BoardDto;
import bb.board.model1.CommentDao;
import bb.board.model1.CommentDto;

public class _1CommentAction implements CommandAction{


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
		CommentDto comment = new CommentDto();	//데이터를 처리할 빈
		comment.setWriter(request.getParameter("cwriter"));
		comment.setPass(request.getParameter("cpass"));
		comment.setRegdate(new Timestamp(System.currentTimeMillis()));
		comment.setRef(ref);
		comment.setStep(step);
		comment.setDepth(depth);
		comment.setContent(request.getParameter("ccontent"));
		comment.setBn(Integer.parseInt(request.getParameter("num")));
		comment.setMem(Integer.parseInt(request.getParameter("mem")));
		comment.setIp(request.getRemoteAddr());
		CommentDao cdbPro = CommentDao.getInstance(); //DB 연결
		cdbPro.insertComment(comment);
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		//해당 페이지 번호
		String pageNum = request.getParameter("pageNum");
		String bn = request.getParameter("bn");
		BoardDao dbPro = BoardDao.getInstance();
		
		//해당 글번호에 대한 레코드
		BoardDto article = dbPro.getArticle(num);
		
		//뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("bn", new Integer(bn));
		return "/bbboard1/commentPro.jsp";	//해당 뷰 경로 반환
	}
}
