package bb.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bb.dao.AdminDAO;
import bb.dao.GuestDAO;
import bb.dto.GuestDTO;

/**
 * Servlet implementation class AdminLoginServlet
 * @param <AdminDAO>
 */
@WebServlet("/adminLogin.do")
public class AdminLoginServlet<AdminDAO> extends HttpServlet {
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
    		   url = "bbADMIN/adminMain.jsp";//�뼱�뱶誘� 二쇱냼, 寃쎈줈 �닔�젙�빐�룄 �릺吏�留� 寃쎈줈瑜� 怨좎튌 寃쎌슦 �쟾遺� �떎 諛붽퓭�빞 �빀�땲�떎.
    		   session.setAttribute("Admin", g_dao.successLogin(id));
    	   }
    	   else
    	   {
    		   url = "bbADMIN/adminLogin.jsp";
    		   request.setAttribute("message","login fail!");
    	   }
    	   RequestDispatcher rdp = request.getRequestDispatcher(url);
    	   rdp.forward(request, response);
       }
    

}
