package bb.guest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsl.dao.GuestDAO;
import jsl.dto.GuestDTO;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// �ѱ�ó��
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// id, pass�� ������
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		// ���̵�, �н����� ��ġ�ϴ��� �� �� ������ �̵�		
		GuestDAO guestDAO = GuestDAO.getinstance();		
		int result = guestDAO.checkLogin(id, pass);
		String result_url = "";
		HttpSession session = null;
		PrintWriter out = response.getWriter();

		if(result == -1)
		{
			// -1 : 아이디가 틀림
			out.println("<script language='javascript'>");
			out.println("alert('登録されていないIDです。やり直してください。');");
			out.println("history.back();");	
			out.println("</script>");
			
		}else if(result == 0)
		{
			// 0 : 비밀번호가 틀림
			out.println("<script language='javascript'>");
			out.println("alert('パスワードが違います。やり直してください。');");
			out.println("history.back();");	
			out.println("</script>");
			
		}else if(result == 1)
		{
			// 1 : �α��� ����
			result_url = "./FrontController?src=main";
			GuestDTO guestDTO = guestDAO.successLogin(id);
			session = request.getSession();
			session.setAttribute("login", guestDTO); // 객체를 세션에 담아줌.
			RequestDispatcher rdp = request.getRequestDispatcher(result_url);
			rdp.forward(request, response);
		}
		
		
		
		
	}

}
