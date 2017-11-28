package action;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.J002_BoardDTO;
import model.J003_DAO;

public class J006_ListAction implements J005_CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");				// 한글 인식이 잘 되도록 하는 것
		String pageNum = request.getParameter("pageNum");	// 페이지 번호
		String prefaces = request.getParameter("prefaces"); // 분류 구분자
		String keywords = request.getParameter("keywords"); // 검색에서 입력한 키워드
		String jogun = request.getParameter("jogun");		// 콤보박스
		String bn = "5";			// 게시판 번호
		if(pageNum == null) {
			pageNum = "1";
		}
		if(prefaces == null) {
			prefaces = "all";
		}
		if(jogun == null) {
			jogun = "etc";
		}
		if (keywords == "") { // keywords가 쓸데없는 값으로 입력되었을 경우 null로 고쳐준다.
			keywords = null;
		}
		// 한 페이지당 글의 개수, DAO의 getArticles() 메소드에 있는 articleList = new ArrayList<J002_BoardDTO>(5)와 숫자가 같아야한다.
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum); // 페이지 번호
		int startRow = (currentPage - 1) * pageSize + 1; // 페이지의 시작 글 번호
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글 번호
		int count = 0;
		int number = 0;
		// 콘솔에 테스트용으로 출력
		System.out.println("=========[]===========");
		System.out.println("한페이지당 글 개수 : " + pageSize);
		System.out.println("currentPage : " + currentPage);
		System.out.println("페이지의 시작 글 번호 : " + startRow);
		System.out.println("페이지의 마지막 글 번호 : " + endRow);
		
		List<J002_BoardDTO> articleList = null;
		J003_DAO dbPro = J003_DAO.getInstance(); // DB 연결
		count = dbPro.getArticleCount(prefaces); // 전체 글 개수
		if(count > 0) {				// 글이 하나라도 존재할때
			if(keywords == null) {	// 검색 단어가 없을 때(검색 기능 사용 안함)
				articleList = dbPro.getArticles(prefaces, startRow, endRow); // 검색 키워드가 없는 글 목록 출력
			} else {				// 검색 단어가 있을 때(검색 기능 사용함)
				count = dbPro.getArticleCounts(prefaces, keywords, jogun);		// 검색 키워드가 포함된 전체 글 개수
				articleList = dbPro.getArticless(prefaces, keywords, jogun, startRow, endRow); // 검색 키워드를 포함한 글 목록 출력
			}
		} else {					// 글이 하나도 없을 때
			articleList = Collections.emptyList();
		}
		number = count - (currentPage - 1) * pageSize; // 글 목록에 표시할 번호
		// 재확인
		System.out.println("count : " + count);
		System.out.println("number : " + number);
		
		// 해당 뷰에서 사용할 속성
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("prefaces", prefaces);
		request.setAttribute("keywords", keywords);
		request.setAttribute("bn", new Integer(bn));
		request.setAttribute("jogun", jogun);
		// 이렇게 필요한 요소들을 DAO에서 찾아서 하나하나 추가하자. 이거 위에 있는건 예시다.
		
		return "/aboard/m001_list.jsp"; // 해당하는 뷰 경로 반환
	}
}