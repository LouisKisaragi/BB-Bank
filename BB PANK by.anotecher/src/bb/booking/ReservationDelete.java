package bb.booking;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsl.dao.BookingDAO;

@WebServlet("/booking_delete.do")
public class ReservationDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		
		BookingDAO dao = BookingDAO.getinstance();
		dao.res_Delete(num);
		
		RequestDispatcher rdp = request.getRequestDispatcher("./FrontController?src=mypage");
		rdp.forward(request, response);
	}
}
