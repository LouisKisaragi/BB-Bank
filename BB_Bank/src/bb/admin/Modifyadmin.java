package bb.admin;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bb.dao.MemberDAO;
import bb.dto.MemberDTO;

/**
 * Servlet implementation class Modifyadmin
 */
@WebServlet("/Modifyadmin.do")
public class Modifyadmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @param email 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response, Object email) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		MemberDTO oldMenberDTO = (MemberDTO)session.getAttribute("AdminAuthority");
		
		String id = oldMenberDTO.getId();
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String email1 = request.getParameter("email");
		int point = oldMenberDTO.getPoint();
		Date joindate = oldMenberDTO.getJoindate();
		int admin = oldMenberDTO.getAdmin();
		int visiable = oldMenberDTO.getVisiable();
		
		String result_url = "bbADMIN/adminMain.jsp";
		
		MemberDTO adminDTO = new MemberDTO(id, pass, name, email1, point, joindate, admin, visiable);
		//포인트 기능이 구현 되지 않았기 때문에 주석으로 처리함, 따라서 adminDTO 변수는 전체 에러, 직접 찾아서 수정 바람.
	
		MemberDAO memberDAO = MemberDAO.getinstance();
		int result = memberDAO.modifyMember(adminDTO);
		
		System.out.println(pass);
		
		if(result > 0) {
			request.setAttribute("success", "연결되었습니다");
			session.setAttribute("AdminAuthority", adminDTO);
		} else {
			request.setAttribute("fail", "연결 실패.");
		}
		
		RequestDispatcher rdp = request.getRequestDispatcher(result_url);
		rdp.forward(request, response);	
	}
}