package jsl.booking;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsl.dao.BookingDAO;
import jsl.dto.GuestDTO;
import jsl.dto.ReservationDTO;

@WebServlet("/booking_modify.do")
public class ReservationModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession session = request.getSession();
		GuestDTO guest = (GuestDTO)session.getAttribute("login");
		
		
		String guest_id = guest.getId();
		int gender =Integer.parseInt(request.getParameter("res_gender"));
		int contents = Integer.parseInt(request.getParameter("res_hairStyle"));
		String res_time = request.getParameter("res_time");
		String res_day_d =request.getParameter("res_date");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		int num = Integer.parseInt(request.getParameter("num"));
		int visiable = Integer.parseInt(request.getParameter("visiable"));
			
		Date res_day = null;
		try {
			res_day = (Date) sdf1.parse(res_day_d);
			System.out.println(res_day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int designer_id = Integer.parseInt(request.getParameter("store_designer"));
		
		ReservationDTO reservationDTO = new ReservationDTO(num, guest_id, gender, contents, res_day, res_time, designer_id, visiable); 
		BookingDAO bookingDAO = BookingDAO.getinstance();
		
		bookingDAO.modifyReservation(reservationDTO);
		
		RequestDispatcher rdp = request.getRequestDispatcher("./FrontController?src=mypage");
		rdp.forward(request, response);
	}

}
