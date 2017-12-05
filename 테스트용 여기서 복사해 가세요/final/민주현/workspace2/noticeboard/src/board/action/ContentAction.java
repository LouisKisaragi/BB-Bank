package board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDao;
import board.model.BoardDto;
import board.model.CommentDao;
import board.model.CommentDto;

// 글 내용을 처리
public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		List<CommentDto> commentList = null;
	
		int num = Integer.parseInt(request.getParameter("num"));
		
		
		CommentDao cdbPro = CommentDao.getInstance();//DB연결
		int count = cdbPro.getCommentCount(num); //전체 댓글 개수
		if(count>0) { //현재 페이지의 댓글 목록
			commentList=cdbPro.getComments(num);
		}else {
			commentList=Collections.emptyList();
		}
		
		//해당 댓글번호
		CommentDto comment =cdbPro.getComment(num);
		int bn = Integer.parseInt(request.getParameter("bn"));
		
		// 해당 페이지 번호
		String pageNum = request.getParameter("pageNum");
		BoardDao dbPro = BoardDao.getInstance();
		
		// 해당 글 번호에 대한 레코드
		BoardDto article =dbPro.getArticle(num);
		
		
		
		// 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("bn", new Integer(bn));
		request.setAttribute("comment", comment);
		request.setAttribute("count", count);
		request.setAttribute("commentList", commentList);
		return "/board/content.jsp"; // 요청에 응답할 뷰 경로
	}

}
