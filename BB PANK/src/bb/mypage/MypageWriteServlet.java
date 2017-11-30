package jsl.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsl.dao.MypageDAO;
import jsl.dto.GuestDTO;
import jsl.dto.QnADTO;

/**
 * Servlet implementation class MypageServlet
 */
@WebServlet("/qnawrite.do")
public class MypageWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
		}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글패치
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession session = request.getSession();
		GuestDTO guest = (GuestDTO)session.getAttribute("login");
		
		
		//qna 보드
		String guest_id = guest.getId();
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		QnADTO qnaDTO = new QnADTO(guest_id,title,contents);
		MypageDAO myDAO = MypageDAO.getinstance();
		System.out.println(guest_id);
		System.out.println(title);
		System.out.println(contents);
		
		int result = myDAO.writeQnAboard(qnaDTO);
		
		PrintWriter out = response.getWriter();
		
		if(result <= 0)
		{
			// 게시글 등록 실패
			out.println("<script language='javascript'>");
			out.println("alert('Q&A 登録に失敗しました。');");
			out.println("</script>");
			
		}else if(result > 0)
		{
			// 등록 성공
			out.println("<script language='javascript'>");
			out.println("alert('Q&A 登録に成功しました。');");
			out.println("</script>");
		}
		RequestDispatcher rdp = request.getRequestDispatcher("./FrontController?src=mypage");
		rdp.forward(request, response);
		
	}

}
