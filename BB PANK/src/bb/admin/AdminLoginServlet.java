package bb.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsl.dao.AdminDAO;
import jsl.dao.GuestDAO;

@WebServlet("/adminLogin.do")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		AdminDAO dao = AdminDAO.getinstance();
		GuestDAO g_dao = GuestDAO.getinstance();
		int result = dao.checkLogin(id, pass);
		String url = null;
		HttpSession session = request.getSession();
		
		if (result > 0)
		{
			url = "hairADMIN/adminMain.jsp";
			session.setAttribute("AdminAuthority", g_dao.successLogin(id));
		}
		else 
		{
			url = "hairADMIN/adminLogin.jsp";
			request.setAttribute("message", "로그인 실패");
		}
		
		RequestDispatcher rdp = request.getRequestDispatcher(url);
		rdp.forward(request, response);
	}

}
