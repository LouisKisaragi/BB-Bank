package bb.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bb.board.model1.BoardDao;
import bb.board.model1.BoardDto;

public class _1WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		
		String  ENCODING_TYPE = "utf-8";
		int MAX_SIZE = 1*1024*1024*1024;
		
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, uploadPath, MAX_SIZE, ENCODING_TYPE,new DefaultFileRenamePolicy());
	
		BoardDto article = new BoardDto(); 			//데이터를 처리할 빈
		if(multi.getFilesystemName("filename") != null ) {
			
			article.setOrigin_filename(multi.getOriginalFileName("filename"));
			article.setServer_filename(multi.getFilesystemName("filename"));
			article.setFilesize((int) multi.getFile("filename").length());
			article.setFiletype(multi.getContentType("filename"));
			
		}
		
		
		article.setNum(Integer.parseInt(multi.getParameter("num")));
		article.setWriter(multi.getParameter("writer"));
		article.setSubject(multi.getParameter("subject"));
		article.setPass(multi.getParameter("pass"));
		article.setRegdate(new Timestamp(System.currentTimeMillis()));
		article.setRef(Integer.parseInt(multi.getParameter("ref")));
		article.setStep(Integer.parseInt(multi.getParameter("step")));
		article.setDepth(Integer.parseInt(multi.getParameter("depth")));
		article.setContent(multi.getParameter("content"));
		article.setIp(request.getRemoteAddr());
		article.setBn(Integer.parseInt(multi.getParameter("bn")));
		article.setPreface(multi.getParameter("preface"));
		article.setMem(Integer.parseInt(multi.getParameter("mem")));
		BoardDao dbPro = BoardDao.getInstance(); // DB 연결
		dbPro.insertArticle(article);
		
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int bn = Integer.parseInt(request.getParameter("bn"));
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("bn", bn);		
		return "/bbboard1/writePro.jsp"; //해당 뷰 경로 반환
		
}

}
