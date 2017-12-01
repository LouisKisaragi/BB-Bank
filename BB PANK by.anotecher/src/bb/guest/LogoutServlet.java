package bb.guest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") != null)
		{
			session.invalidate();
			request.setAttribute("message", "로그아웃이 되었습니다.");
		}
		else request.setAttribute("message", "로그인 객체가 존재하지 않습니다.");		
		
		RequestDispatcher rdp = request.getRequestDispatcher("./FrontController?src=main");
		rdp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
