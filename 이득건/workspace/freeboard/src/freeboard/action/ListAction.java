package freeboard.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import freeboard.model.BoardDao;
//import freeboard.model.BoardDto;

public class ListAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		String pageNum = request.getParameter("pageNum"); //페이지 번호
		String prefaces = request.getParameter("prefaces"); //구분 구분자
		String keywords = request.getParameter("keywords"); // 검색에서 입력한 키워드
		String condition = request.getParameter("condition");		// 콤보박스
		String bn = "4";			 // 게시판 번호
		if(pageNum == null){
			pageNum = "1";
			
		}
		if(prefaces == null){
			prefaces = "all";
		}
		if(condition == null){
			condition = "etc";
		}
		int pageSize = 5; //한 페이지 당 글의 수 DAO의 getArticles() 메소드에 있는 articleList = new ArrayListBoardDto>(5)와 숫자가 같아야한다.
		int currentPage = Integer.parseInt(pageNum); //페이지 번호
		//페이지의 시작 글 번호
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize; //한 페이지의 마지막 글 번호
		int count = 0;
		int number = 0;
		List<freeboard.model.BoardDto> articleList = null;
		freeboard.model.BoardDao dbPro = freeboard.model.BoardDao.getInstance(); //DB연결
		count = dbPro.getArticleCount(prefaces); //전체 글 개수
		if(count > 0){ // 현재 페이지의 글 목록
			if(keywords == null) {	// 검색 단어가 없을 때(검색 기능 사용 안함)
			articleList = dbPro.getArticles(prefaces, startRow, endRow);// 검색 키워드가 없는 글 목록 출력
			} else {				// 검색 단어가 있을 때(검색 기능 사용함)
				count = dbPro.getArticleCounts(prefaces, keywords, condition);		// 검색 키워드가 포함된 전체 글 개수
				articleList = dbPro.getArticless(prefaces, keywords, condition, startRow, endRow); // 검색 키워드를 포함한 글 목록 출력
			}
		} else {
			articleList = Collections.emptyList();
		}
		number = count - (currentPage-1) * pageSize; //글 목록에 표시할 글
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("prefaces", prefaces);
		request.setAttribute("bn", new Integer(bn));
		
		return "/freeboard/list.jsp"; //해당하는 뷰 경로 반환
	}
	

}
