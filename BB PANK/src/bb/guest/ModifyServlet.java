package bb.guest;

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


@WebServlet("/modify.do")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		GuestDTO oldGuestDTO = (GuestDTO) session.getAttribute("login");
		
		String id = oldGuestDTO.getId();
		String pass = request.getParameter("pass");
		String pass_ch = request.getParameter("pass_ch");
		String moto_pass = request.getParameter("moto_pass");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		int point = oldGuestDTO.getPoint();
		Date joindate = oldGuestDTO.getJoindate();
		int admin = oldGuestDTO.getAdmin();
		int visiable = oldGuestDTO.getVisiable();
		
		if (pass == null) // 신규 패스가 존재하지 않음
		{
			pass = moto_pass;
		}
		
		String result_url = "./FrontController?src=mypage";
		
		if(pass.equals(pass_ch))
		{
			GuestDTO guestDTO = new GuestDTO(id, pass, name, address, phone, point, joindate, admin, visiable);
			
			
			GuestDAO guestDAO = GuestDAO.getinstance();
			int result = guestDAO.modifyGuest(guestDTO);
			
			System.out.println(id);
			
			if(result > 0)
			{
				// �닔�젙 �꽦怨�
				request.setAttribute("modify_message", "修正完了しました。");
				session.setAttribute("login", guestDTO);
			}else
			{
				// �닔�젙 �떎�뙣
				request.setAttribute("modify_message", "修正失敗しました。");
			}
			
			
			
		}else{
			request.setAttribute("pass_error", "비밀번호가 일치하지 않습니다.");
		}
		
		RequestDispatcher rdp = request.getRequestDispatcher(result_url);
		rdp.forward(request, response);	
	}
	
	
}
