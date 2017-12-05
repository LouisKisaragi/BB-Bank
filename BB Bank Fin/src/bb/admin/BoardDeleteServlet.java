package bb.admin;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.dao.BoardDAO;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/BoardDeleteServlet")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 한글 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
			
		// 요청 처리		
		String num = request.getParameter("num");
		String url = request.getParameter("url");
		int visiable = Integer.parseInt(request.getParameter("visiable"));
		int newvisiable = 0;
		//num을 받아와야함. 
		if(visiable == 0)
		{ // 삭제 된 상태 -> 복구
			newvisiable = 1;
		}
		else newvisiable = 0;
		// 삭제해야하는 상태
				
		BoardDAO boardDAO = BoardDAO.getinstance();
				
		boardDAO.deleteBoard(num, newvisiable);
		
		RequestDispatcher rdp = request.getRequestDispatcher(url);
		rdp.forward(request, response);
	}

}