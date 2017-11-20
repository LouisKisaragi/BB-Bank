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
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/adminLogin.do")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	   String id = request.getParameter("id");
    	   String pass = request.getParameter("pass");
    	   
    	   AdminDAO dao = AdminDAO.getinstance();
    	   GuestDAO g_dao = GuestDAO.getinstance();
    	   int result = dao.checkLogin(id, pass);
    	   String url = null;
    	   HttpSession session = request.getSession();
    	   
    	   if(result > 0) 
    	   {
    		   url = "bbADMIN/adminMain.jsp";//어드민 주소, 경로 수정해도 되지만 경로를 고칠 경우 전부 다 바꿔야 합니다.
    		   session.setAttribute("Admin", g_dao.successLogin(id));
    	   }
    	   else
    	   {
    		   url = "bbADMIN/adminLogin.jsp";
    		   request.setAttribute("message","login error!")
    	   }
    	   RequestDispatcher rdp = request.getRequestDispatcher(url);
    	   rdp.forward(request, response);
       }
    

}
