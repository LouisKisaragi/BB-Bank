package boardtwo.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import boardtwo.model.BoardDao;
import boardtwo.model.BoardDto;

public class ListAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		String pageNum = request.getParameter("pageNum"); //������ ��ȣ
		if(pageNum == null){
			pageNum = "1";
		}
		int pageSize = 5; //�� ������ �� ���� ��
		int currentPage = Integer.parseInt(pageNum);
		//�������� ���� �� ��ȣ
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize; //�� �������� ������ �� ��ȣ
		int count = 0;
		int number = 0;
		List<BoardDto> articleList = null;
		BoardDao dbPro = BoardDao.getInstance(); //DB����
		count = dbPro.getArticleCount(); //��ü �� ����
		if(count > 0){ // ���� �������� �� ���
			articleList = dbPro.getArticles(startRow, endRow);
		} else {
			articleList = Collections.emptyList();
		}
		number = count - (currentPage-1) * pageSize; //�� ��Ͽ� ǥ���� ��
		
		//�ش� �信�� ����� �Ӽ�
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		
		return "/boardtwo/list.jsp"; //�ش��ϴ� �� ��� ��ȯ
	}
	

}
