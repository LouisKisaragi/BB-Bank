package bb.admin.admin;
import java.io.IOException;

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

import bb.admin.dao.BoardDAO;
import bb.admin.dto.BoardDTO;
import bb.admin.dto.MemberDTO;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet("/Modify_board")
public class BoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");

		String id = dto.getId();
		String savePath = "/bbIMAGE/adminset";
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "utf-8";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		String url = "./FrontController?src=board";

		try {
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
			String fileName = multi.getFilesystemName("uploadFile");
			String image = multi.getParameter("image");
			if(fileName == null) {
				System.out.println("�뙆�씪 �뾽濡쒕뱶 �떎�뙣");
			} else {
				System.out.println("�뙆�씪 �뾽濡쒕뱶 �꽦怨�");
				image = fileName;
			}
			String title = multi.getParameter("title");
			String contents = multi.getParameter("contents");
			int visiable = Integer.parseInt(multi.getParameter("visiable"));
			int num = Integer.parseInt(multi.getParameter("num"));

			BoardDTO board_dto = new BoardDTO(num, id, title, contents, image, visiable);
			BoardDAO dao = BoardDAO.getinstance();
			dao.modifyBoard(board_dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rdp = request.getRequestDispatcher(url);
		rdp.forward(request, response);
	}
}