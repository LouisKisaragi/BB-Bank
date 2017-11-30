package jsl.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminLogout.do")
public class AdminLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("AdminAuthority") != null)
		{
			session.invalidate();
		}

		request.setAttribute("message", "로그아웃 되었습니다.");
		RequestDispatcher rdp = request.getRequestDispatcher("hairADMIN/adminLogin.jsp");
		rdp.forward(request, response);
	}

}
