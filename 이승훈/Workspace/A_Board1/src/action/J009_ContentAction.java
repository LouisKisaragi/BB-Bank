package action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.J002_BoardDTO;
import model.J003_DAO;

// �� ������ ó��
public class J009_ContentAction implements J005_CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// �ش� �� ��ȣ
		int num = Integer.parseInt(request.getParameter("num"));
		
		// �ش� ������ ��ȣ
		String pageNum = request.getParameter("pageNum");
		J003_DAO dbPro = J003_DAO.getInstance();
		
		// �ش� �� ��ȣ�� ���� ���ڵ�
		J002_BoardDTO article = dbPro.getArticle(num);
		
		// �信�� ����� �Ӽ�
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		return "/aboard/m002_content.jsp";	// �ش� �� ��� ��ȯ
	}
}