package bb.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jsl.service.PointService;

@WebServlet("/adminPointGive")
/*public class AdminPointGive extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String id = request.getParameter("id");
		int point = Integer.parseInt(request.getParameter("point"));
		String pointlog = request.getParameter("context");
		String message = null;
		
		PointService service = new PointService();
		
		int userPoint = service.ModifyPoint(id, point, pointlog);
		if(userPoint == -1)
		{
			message = "対象を確認できません。";
		}
		else 
		{
			message = "確認しました。";
		}
		
		request.setAttribute("message", message);
		RequestDispatcher rdp = request.getRequestDispatcher("./AdminFrontController?src=point&contentno=0&pageno=0");
		rdp.forward(request, response);
	}*/

}
