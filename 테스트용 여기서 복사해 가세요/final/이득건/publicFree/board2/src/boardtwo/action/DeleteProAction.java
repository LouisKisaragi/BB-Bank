package boardtwo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import boardtwo.model.BoardDao;

public class DeleteProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {request.setCharacterEncoding("UTF-8");
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	String pass = request.getParameter("pass");
	
	BoardDao dbPro = BoardDao.getInstance();
	int check = dbPro.deleteArticle(num, pass);
	
	//�信�� ����� �Ӽ�
	request.setAttribute("pageNum", new Integer(pageNum));
	request.setAttribute("check", new Integer(check));
	
	return "/boardtwo/deletePro.jsp"; //�� ���
	}

}
