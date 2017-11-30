package jsl.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsl.dao.AdminDAO;

@WebServlet("/delete_qna")
public class AdminQnADelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 한글 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
					
		// 요청 처리		
		String num = request.getParameter("num");
		String url = request.getParameter("url");
					
		AdminDAO adminDAO = AdminDAO.getinstance();
						
		adminDAO.deleteQnA(num);
				
		RequestDispatcher rdp = request.getRequestDispatcher(url);
		rdp.forward(request, response);
	}

}
