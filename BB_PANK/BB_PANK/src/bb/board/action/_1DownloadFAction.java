package bb.board.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.model1.BoardDao;
import bb.board.model1.BoardDto;

public class _1DownloadFAction  implements CommandAction{
	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		//해당 글번호
		
		//제목글과 답변글의 구분
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");



		BoardDao dbPro = BoardDao.getInstance(); //DB연결
		BoardDto article = dbPro.getArticle(num);


		ServletOutputStream outStream = null;
		FileInputStream inputStream = null;
		//DB에있는 정보가져오기
		String filename = null;
		filename=article.getServer_filename();
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		String uploadFileName = uploadPath+ "/" + filename;
		 
	        // 해당 스트링에서 파일을 가져온 후
	        File downfile = new File(uploadFileName);
	        
		if(!downfile.exists()) {
			throw new FileNotFoundException();
		}
		
	try {	
		outStream = response.getOutputStream();
		inputStream = new FileInputStream(downfile);
		//Setting response header
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=\""+downfile.getName()+"\"");
		byte[] outByte = new byte[4096];
		while(inputStream.read(outByte,0,4096)!=-1) {
			outStream.write(outByte,0,4096);
		}
	}catch(Exception e) {
		throw new IOException();
	} finally {
		inputStream.close();
		outStream.flush();
		outStream.close();
	}
	
	request.setAttribute("num", new Integer(num));
	request.setAttribute("pageNum", new Integer(pageNum));
	request.setAttribute("article", article);
	
	return "/bbboard1/content.jsp";	//해당 뷰 경로 반환
}
	
}
