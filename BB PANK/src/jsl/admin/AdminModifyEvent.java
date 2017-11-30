package jsl.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsl.dao.EventDAO;
import jsl.dto.EventDTO;

@WebServlet("/modify_event")
public class AdminModifyEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String url = request.getParameter("url");
		String img = request.getParameter("img");
		String linkvalue = request.getParameter("linkvalue");
		String datevalue = request.getParameter("datevalue");
		int visiable = Integer.parseInt(request.getParameter("visiable"));
		
		EventDTO dto = new EventDTO(num, title, contents, img, linkvalue, datevalue, visiable);
		EventDAO dao = EventDAO.getinstance();
		
		dao.ModifyEvent(dto);
		
		RequestDispatcher rdp = request.getRequestDispatcher(url);
		rdp.forward(request, response);
	}
}
