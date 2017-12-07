package bb.admin.action;


import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.action.CommandAction;
import bb.board.model1.BoardDao;
import bb.board.model1.BoardDto;


public class MainAction  implements CommandAction{
	public String requestPro (
			HttpServletRequest request,
			HttpServletResponse response)throws Throwable{
		request.setCharacterEncoding("UTF-8");
		String preface = request.getParameter("preface");
		String pageNum = request.getParameter("pageNum"); //페이지 번호
		if(pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 3; //한 페이지 당 글의 개수
		int currentPage = Integer.parseInt(pageNum); //페이지의 시작 글 번호
		int startRow = (currentPage -1) * pageSize +1; 				
		int endRow = currentPage * pageSize; //한 페이지의 마지막 글번호
		int count1 = 0;
		int count2 = 0;
		int count4 = 0;
		int count5 = 0;
		int number = 0;
		int bn1=1;
		int bn2=2;
		int bn4=4;
		int bn5=5;
		List<BoardDto> articleList1 = null;
		List<BoardDto> articleList2 = null;
		List<BoardDto> articleList4 = null;
		List<BoardDto> articleList5 = null;
		BoardDao dbPro = BoardDao.getInstance(); //DB연결
		count1 =dbPro.getMainArticleCount(bn1);
		count2 =dbPro.getMainArticleCount(bn2);
		count4 =dbPro.getMainArticleCount(bn4);
		count5 =dbPro.getMainArticleCount(bn5);
		if(count1>0) { //현재 페이지의 글 목록
			articleList1 =dbPro.getMainArticles(startRow, endRow, bn1);			
		}else {
			articleList1 = Collections.emptyList();
		}
		if(count2>0) { //현재 페이지의 글 목록
			articleList2 =dbPro.getMainArticles(startRow, endRow, bn2);
			
		}else {
			articleList2 = Collections.emptyList();
		}
		if(count4>0) { //현재 페이지의 글 목록
			articleList4 =dbPro.getMainArticles(startRow, endRow, bn4);
			
		}else {
			articleList4 = Collections.emptyList();
		}
		if(count5>0) { //현재 페이지의 글 목록
			articleList5 =dbPro.getMainArticles(startRow, endRow, bn5);
			
		}else {
			articleList5 = Collections.emptyList();
		}
		number = count1 - (currentPage-1) * pageSize; //글 목록에 표시할 글 번호
		number = count2 - (currentPage-1) * pageSize; //글 목록에 표시할 글 번호
		number = count4 - (currentPage-1) * pageSize; //글 목록에 표시할 글 번호
		number = count5 - (currentPage-1) * pageSize; //글 목록에 표시할 글 번호
		
		// 해당 뷰에서 사용할 속성
			request.setAttribute("bn1", bn1);
			request.setAttribute("bn2", bn2);
			request.setAttribute("bn4", bn4);
			request.setAttribute("bn5", bn5);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", new Integer(currentPage));
			request.setAttribute("startRow", new Integer(startRow));
			request.setAttribute("endRow", new Integer(endRow));
			request.setAttribute("pageSize", new Integer(pageSize));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("articleList1",articleList1);
			request.setAttribute("articleList2",articleList2);
			request.setAttribute("articleList4",articleList4);
			request.setAttribute("articleList5",articleList5);
			request.setAttribute("preface", preface);
			
		return "/bbadmin/adMain.jsp";
	}

}
