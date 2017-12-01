package bb.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsl.dao.AdminDAO;

@WebServlet("/qna_board")
public class AdminQnAanswer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String answer = request.getParameter("answer");
		int num = Integer.parseInt(request.getParameter("num"));
		
		if(answer != null)
		{
			AdminDAO dao = AdminDAO.getinstance();
			dao.insertAnswer(answer, num);
		}
		
		RequestDispatcher rdp = request.getRequestDispatcher("./AdminFrontController?src=qna&contentno=0&pageno=0");
		rdp.forward(request, response);
	}

}
