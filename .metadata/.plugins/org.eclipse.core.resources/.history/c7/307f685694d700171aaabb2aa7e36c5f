package bb.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Modifyadmin
 */
@WebServlet("/Modifyadmin.do")
public class Modifyadmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		MenberDTO oldMenberDTO = (MenberDTO) session.getAttribute("AdminAuthority");
		
		String id = oldMenberDTO.getId();
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String address = request.getParameter("email");
		int point = oldMenberDTO.getPoint();
		Date joindate = oldMenberDTO.getJoindate();
		int admin = oldMenberDTO.getAdmin();
		int visiable = oldMenberDTO.getVisiable();
		
		String result_url = "bbADMIN/adminMain.jsp";
		
		MenberDTO adminDTO = new MenberDTO(id, pass, name, e-mail, point, joindate, admin, visiable);
		
		MenberDAO guestDAO = MenberDAO.getinstance();
		int result = guestDAO.modifyGuest(adminDTO);
		
		System.out.println(pass);
		
		if(result > 0)
		{
			request.setAttribute("success", "Àû¿ëµÇ¾ú½À´Ï´Ù.");
			session.setAttribute("AdminAuthority", adminDTO);
		}else
		{
			request.setAttribute("success", "íÂåöã÷ø¨ª·ªŞª·ª¿¡£");
		}
		
		RequestDispatcher rdp = request.getRequestDispatcher(result_url);
		rdp.forward(request, response);	
	}

}
