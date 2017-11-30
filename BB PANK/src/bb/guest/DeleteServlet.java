package jsl.guest;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsl.dao.GuestDAO;


@WebServlet("/delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; utf-8");
		
		HttpSession session = request.getSession();
		
		// 요청처리
		String id = request.getParameter("id");
		int visiable = Integer.parseInt(request.getParameter("visiable"));

		GuestDAO guestDAO = GuestDAO.getinstance();
		int result = guestDAO.deleteGuest(id, visiable);
		
		if(result > 0)
		{
			// ȸ�� ���� ����
			session.invalidate();
		}else
		{
			// ���� ����
		}
	}

}
