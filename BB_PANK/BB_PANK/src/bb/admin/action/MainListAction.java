package bb.admin.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bb.admin.model.AdBoardDao;
import bb.admin.model.AdBoardDto;
import bb.board.action.CommandAction;

public class MainListAction implements CommandAction{
	public String requestPro (
			HttpServletRequest request,
			HttpServletResponse response)throws Throwable{
		HttpSession session = request.getSession(true);//세션이 있는지 없는지를 확인 
		request.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("pageNum"); //페이지 번호
	
		String search=request.getParameter("search");
		String details=request.getParameter("details");
		String sbn=request.getParameter("bn");
		if(pageNum == null){
			pageNum = "1";
		}
		if(sbn==null) {
			sbn="0";
		}
		
		int pageSize = 5; //한 페이지 당 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		//페이지의 시작 글 번호
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize; //한 페이지의 마지막 글 번호
		int count = 0;
		int number = 0;
		int bn = Integer.parseInt(sbn);
		
		List<AdBoardDto> articleList = null;
		if(search==null || search=="") {//검색이아닐경우
			AdBoardDao dbPro = AdBoardDao.getInstance(); //DB연결
			
			count = dbPro.getArticleCount(bn); //전체 글 개수
			if(count > 0){ //현재 페이지의 글 목록
				articleList = dbPro.getArticles(startRow, endRow, bn);
			} else {
				articleList = Collections.emptyList();
			}
		}else {//검색일경우
			AdBoardDao dbPro = AdBoardDao.getInstance();//db연결
			count=dbPro.getSearchArticleCount(bn, details, search);
			if(count > 0){ //현재 페이지의 글 목록
				articleList = dbPro.getSearchArticles(startRow, endRow, bn, details, search);
			} else {
				articleList = Collections.emptyList();
			}
		}
		AdBoardDao MdbPro=AdBoardDao.getInstance();
		List<AdBoardDto> articleMList= MdbPro.getMArticles(bn);
		number = count - (currentPage-1) * pageSize; //글 목록에 표시할 글 번호
		
		if(session.getAttribute("super")==null) {
			return "/bbadmin/adlogin.jsp";	
		}else {
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("MarticleList", articleMList);
		request.setAttribute("bn",bn);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("details", details);
		request.setAttribute("search", search);
		return "/bbadmin/adMainlist.jsp";	//해당하는 뷰 경로 반환
		}
		}
}
