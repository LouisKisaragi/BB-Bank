package bb.guest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsl.dao.GuestDAO;
import jsl.dto.GuestDTO;
import jsl.service.PointService;

@WebServlet("/join.do") // JoinServlet
public class JoinServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// ?���?처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset = utf-8");

		// ?���?�? 처리
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String pass_ch = request.getParameter("pass_ch");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		String result_url = "./FrontController?src=join";

		if(pass.equals(pass_ch))
		{
			GuestDAO dao = GuestDAO.getinstance();
			GuestDTO guest = new GuestDTO(id, pass, name, address, phone);
			Boolean result = dao.insertGuest(guest);
		
			if(result) 
			{
				result_url = "./FrontController?src=main";
				request.setAttribute("message", "Signに成功しました。");
				session.setAttribute("login", guest);
				out.println("<script language='javascript'>");
				out.println("alert('口コミ登録で100PT獲得しました。');");
				out.println("</script>");
				PointService service = new PointService();
				service.ModifyPoint(id, 1000, "会員登録");
			}
			else request.setAttribute("message", "エラーが発生しました。やり直してください。");
			
		}else{
			request.setAttribute("pass_message", "パスワードが一致していません。");
		}

		RequestDispatcher rdp = request.getRequestDispatcher(result_url);
		rdp.forward(request, response);
	}

}
