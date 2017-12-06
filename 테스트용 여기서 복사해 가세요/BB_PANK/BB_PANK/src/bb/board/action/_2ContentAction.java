package bb.board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.model2.BoardDao;
import bb.board.model2.BoardDto;
import bb.board.model2.CommentDao;
import bb.board.model2.CommentDto;

//글 내용을 처리
public class _2ContentAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		List<CommentDto> carticleList = null;
		int num = Integer.parseInt(request.getParameter("num"));
		CommentDao cdbPro = CommentDao.getInstance(); //DB연결
		int count = cdbPro.getCArticleCount(num); //전체 글 개수
		if(count > 0){ //현재 페이지의 글 목록
			carticleList = cdbPro.getCArticles(num);
		} else {
			carticleList = Collections.emptyList();
		}
		//해당 글번호
		CommentDto articlec = cdbPro.getArticle(num);
		int bn=Integer.parseInt(request.getParameter("bn"));
		//해당 페이지 번호
		String pageNum = request.getParameter("pageNum");
		BoardDao dbPro = BoardDao.getInstance();
		//해당 글번호에 대한 레코드
		BoardDto article = dbPro.getArticle(num);
		//뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("bn", new Integer(bn));
		request.setAttribute("artclec", articlec);
		request.setAttribute("count", count);
		request.setAttribute("articleList", carticleList);

		return "/bbboard2/content.jsp"; //요청에 응답할 뷰 경로
	}

}
