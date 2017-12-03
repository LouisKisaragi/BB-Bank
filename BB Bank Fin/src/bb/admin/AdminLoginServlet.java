package bb.admin;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bb.dao.AdminDAO;
//import bb.dto.MemberDAO;
/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/adminLogin.do")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		AdminDAO dao = AdminDAO.getinstance();
		//MemberDAO g_dao = MemberDAO.getinstance();
		int result = dao.checkLogin(id, pass);
		String url = null;
		HttpSession session = request.getSession();
		
		if (result > 0)
		{
			url = "bbADMIN/adminMain.jsp";
			session.setAttribute("AdminAuthority", m_dao.successLogin(id));
		}
		else 
		{
			url = "bbADMIN/adminLogin.jsp";
			request.setAttribute("message", "로그인 실패");
		}
		
		RequestDispatcher rdp = request.getRequestDispatcher(url);
		rdp.forward(request, response);
	}

}
