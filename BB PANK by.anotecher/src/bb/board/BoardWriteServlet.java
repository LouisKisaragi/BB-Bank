package bb.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsl.dao.BoardDAO;
import jsl.dto.BoardDTO;
import jsl.dto.GuestDTO;
import jsl.service.PointService;

@WebServlet("/write.do")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		GuestDTO dto = (GuestDTO) session.getAttribute("login");
	
		String id = dto.getId();
		String savePath = "/hairIMAGE/hairSELF";
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "utf-8";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		String url = "./FrontController?src=board";
		int result = 0;
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
			String fileName = multi.getFilesystemName("uploadFile");
			if(fileName == null)
			{
				System.out.println("파일 업로드 실패");
			}
			else
			{
				System.out.println("파일 업로드 성공");
			}
			String title = multi.getParameter("title");
			String contents = multi.getParameter("contents");
			
			BoardDTO board_dto = new BoardDTO(id, title, contents, fileName);
			BoardDAO dao = BoardDAO.getinstance();
			result = dao.writeBoard(board_dto);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		if(result > 0)
		{
			// 게시글 등록 성공
			out.println("<script language='javascript'>");
			out.println("alert('口コミ登録で100PT獲得しました。');");
			out.println("</script>");
			PointService service = new PointService();
			service.ModifyPoint(dto.getId(), 100, "口コミ登録");
		}
		
		RequestDispatcher rdp = request.getRequestDispatcher(url);
		rdp.forward(request, response);
	}
}