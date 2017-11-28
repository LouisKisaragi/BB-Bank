package board.action;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDao;
import board.model.BoardDto;

//수정 요청을 처리하는 Action 클래스 작성
public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");

		String pageNum = request.getParameter("pageNum");

		String uploadpath = request.getSession().getServletContext().getRealPath("/upload");
		String ENCODING_TYPE = "utf-8";
		int MAX_SIZE = 1 * 1024 * 1024 * 1024;
		DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(request, uploadpath, MAX_SIZE, ENCODING_TYPE, policy);
		int num = Integer.parseInt(multi.getParameter("num"));
		int bn = Integer.parseInt(request.getParameter("bn"));
		BoardDto articlec = BoardDao.getInstance().getArticle(num);
		String db_server_filename = articlec.getServer_filename();
		String db_origin_filename = articlec.getOrigin_filename();
		int db_filesize = articlec.getFilesize();
		String db_filetype = articlec.getFiletype();

		String server_filename = multi.getFilesystemName("fileName");

		BoardDto article = new BoardDto();
		article.setNum(Integer.parseInt(multi.getParameter("num")));
		article.setWriter(multi.getParameter("writer"));
		article.setSubject(multi.getParameter("subject"));
		article.setPass(multi.getParameter("pass"));
		article.setContent(multi.getParameter("content"));
		article.setPreface(multi.getParameter("preface"));
		if (db_server_filename != null && server_filename != null) {
			if (!(db_server_filename.equals(server_filename))) {
				File oldFile = new File(uploadpath, db_server_filename);
				if (oldFile.isFile()) {
					oldFile.delete();
				}
				article.setOrigin_filename(multi.getOriginalFileName("fileName"));
				article.setServer_filename(multi.getFilesystemName("fileName"));
				article.setFilesize((int) multi.getFile("fileName").length());
				article.setFiletype(multi.getContentType("fileName"));
			}
		} else if (db_server_filename != null && server_filename == null) {
			article.setOrigin_filename(db_origin_filename);
			article.setServer_filename(db_server_filename);
			article.setFilesize(db_filesize);
			article.setFiletype(db_filetype);
		} else if (db_server_filename == null && server_filename != null) {
			article.setOrigin_filename(multi.getOriginalFileName("fileName"));
			article.setServer_filename(multi.getFilesystemName("fileName"));
			article.setFilesize((int) multi.getFile("fileName").length());
			article.setFiletype(multi.getContentType("fileName"));
		}
		BoardDao dbPro = BoardDao.getInstance();
		int check = dbPro.updateArticle(article);
		// 뷰에 사용할 속성
		request.setAttribute("bn", new Integer(bn));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));

		return "/board/updatePro.jsp"; // 해당 뷰 경로 반환
	}
	
	

}
