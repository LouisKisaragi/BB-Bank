package jsl.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsl.dao.BookingDAO;

@WebServlet("/delete_booking")
public class AdminDeleteBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String num = request.getParameter("num");
		int visiable = Integer.parseInt(request.getParameter("visiable"));
		
		BookingDAO dao = BookingDAO.getinstance();
		dao.visiableBoard(num, visiable);		
	}
}
