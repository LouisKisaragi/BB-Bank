package bb.admin.admin;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminFrontController
 */
@WebServlet("/AdminFrontController")
public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		String src = request.getParameter("src");
		String url = null;
		
		if (src.equals("board"))
		{
			url = "boardManagerment.jsp";
		}
		/*else if (src.equals("qna"))
		{
			url = "qnaManagerment.jsp";
		}*/
		else if (src.equals("point"))
		{
			url = "pointManagerment.jsp";
		}
		else if (src.equals("modify"))
		{
			url = "modifyAdmin.jsp";
		}
		else if (src.equals("guest"))
		{
			url = null;
		}		
		request.setAttribute("pageindex", url);		
		RequestDispatcher rdp = request.getRequestDispatcher("bbADMIN/adminMain.jsp");
		rdp.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}