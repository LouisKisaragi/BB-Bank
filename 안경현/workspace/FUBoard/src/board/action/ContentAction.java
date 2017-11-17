package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDao;
import board.model.BoardDto;
import board.model.CommentDao;
import board.model.CommentDto;

//글 내용을 처리
public class ContentAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		
		//해당 글번호
		int num = Integer.parseInt(request.getParameter("num"));
		int bn=Integer.parseInt(request.getParameter("bn"));
		//해당 페이지 번호
		String pageNum = request.getParameter("pageNum");
		BoardDao dbPro = BoardDao.getInstance();
		CommentDao dbProc = CommentDao.getInstance();
		//해당 글번호에 대한 레코드
		BoardDto article = dbPro.getArticle(num);
		CommentDto articlec = dbProc.getArticle(num);
		int ref = 1, step=0, depth=0;
		try{
			if(request.getParameter("num") != null){
				ref = Integer.parseInt(request.getParameter("ref"));
				step = Integer.parseInt(request.getParameter("step"));
				depth = Integer.parseInt(request.getParameter("depth"));
			}
		} catch (Exception e) { e.printStackTrace(); }
		//뷰에서 사용할 속성
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("step", new Integer(step));
		request.setAttribute("depth", new Integer(depth));
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("bn", new Integer(bn));
		request.setAttribute("artclec", articlec);
		return "/board/content.jsp"; //요청에 응답할 뷰 경로
	}

}
