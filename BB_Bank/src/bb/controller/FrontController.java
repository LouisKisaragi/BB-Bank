package bb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 모든 링크 요청 처리를 하는 프론트 컨트롤러
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String src = request.getParameter("src");
		String url = null;
		
		if (src.equals("main"))
		{
			url = null;
		}
		/*else if (src.equals("introduce"))
		{
			url = "introduce.jsp";
		}*/
		
		else if (src.equals("board"))
		{
			url = "boardlist.jsp";
		}
		else if (src.equals("join"))
		{
			url = "join.jsp";
		}
		else if (src.equals("mypage"))
		{
			url = "mypage.jsp";
		}
		else if (src.equals("boardwrite"))
		{
			url = "boardform.jsp";
		}
		else if (src.equals("boardview"))
		{
			url = "boardview.jsp";
		}
		else if (src.equals("boardmodify"))
		{
			url = "boardmodify.jsp";
		}
		
		/* main.jsp
		 * introduce.jsp
		 * style.jsp
		 * event.jsp
		 * booking.jsp
		 * boardlist.jsp
		 */

		
		request.setAttribute("pageindex", url);		
		RequestDispatcher rdp = request.getRequestDispatcher("./bbJSP/index.jsp");
		rdp.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
