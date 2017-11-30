package bb.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsl.dao.StyleDAO;
import jsl.dto.StyleDTO;

@WebServlet("/insert_style")
public class AdminInsertStyle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		

		
		String savePath = "/hairIMAGE/hairIMG";
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "utf-8";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		String url = null;
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
			String fileName = multi.getFilesystemName("uploadFile");
			String image = multi.getParameter("img");
			if(fileName == null)
			{
				System.out.println("파일 업로드 실패");
			}
			else
			{
				System.out.println("파일 업로드 성공");
				image = fileName;
			}
			
			String title = multi.getParameter("title");
			String content = multi.getParameter("contents");
			url = multi.getParameter("url");
			String sort = multi.getParameter("sort");
			
			StyleDTO dto = new StyleDTO(sort, title, image, content);
			StyleDAO dao = StyleDAO.getinstance();
			dao.insertStyle(dto);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rdp = request.getRequestDispatcher(url);
		rdp.forward(request, response);
		
		
		
	}
}
