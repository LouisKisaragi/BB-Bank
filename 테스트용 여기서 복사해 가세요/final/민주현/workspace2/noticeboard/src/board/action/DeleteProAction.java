package board.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDao;
import board.model.BoardDto;

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		int bn = Integer.parseInt(request.getParameter("bn"));
		String pageNum = request.getParameter("pageNum");
		String pass = request.getParameter("pass");
		
		BoardDto article = BoardDao.getInstance().getArticle(num);
		String server_filename =article.getServer_filename();
		String uploadpath= request.getSession().getServletContext().getRealPath("/upload");
		
		if(server_filename != null){
		File file = new File(uploadpath,server_filename);
		if(file.isFile()) {
			file.delete();
		}
		}


		BoardDao dbPro = BoardDao.getInstance();
		int check = dbPro.deleteArticle(num, pass);

		

		// 뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		request.setAttribute("bn", new Integer(bn));
		return "/board/deletePro.jsp"; // 뷰 경로
	}

}
