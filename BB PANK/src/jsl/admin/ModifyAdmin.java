package jsl.admin;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsl.dao.GuestDAO;
import jsl.dto.GuestDTO;

@WebServlet("/modifyadmin.do")
public class ModifyAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		GuestDTO oldGuestDTO = (GuestDTO) session.getAttribute("AdminAuthority");
		
		String id = oldGuestDTO.getId();
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		int point = oldGuestDTO.getPoint();
		Date joindate = oldGuestDTO.getJoindate();
		int admin = oldGuestDTO.getAdmin();
		int visiable = oldGuestDTO.getVisiable();
		
		String result_url = "hairADMIN/adminMain.jsp";
		
		GuestDTO adminDTO = new GuestDTO(id, pass, name, address, phone, point, joindate, admin, visiable);
		
		GuestDAO guestDAO = GuestDAO.getinstance();
		int result = guestDAO.modifyGuest(adminDTO);
		
		System.out.println(pass);
		
		if(result > 0)
		{
			request.setAttribute("success", "作業完了しました。");
			session.setAttribute("AdminAuthority", adminDTO);
		}else
		{
			request.setAttribute("success", "作業失敗しました。");
		}
		
		RequestDispatcher rdp = request.getRequestDispatcher(result_url);
		rdp.forward(request, response);	
	}

}
