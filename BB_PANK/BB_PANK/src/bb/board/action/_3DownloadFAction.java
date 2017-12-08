package bb.board.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.model3.BoardDao;
import bb.board.model3.BoardDto;

public class _3DownloadFAction  implements CommandAction{
	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		//해당 글번호
		
		//제목글과 답변글의 구분
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("pageNum::"+request.getParameter("pageNum"));
		String pageNum = request.getParameter("pageNum");
		System.out.println("num::"+num);

//		Collection<Part> parts=request.getParts();
		//해당 페이지 번호
	//	String pageNum = request.getParameter("pageNum");
	//	List<BoardDto> articleList = null;
		BoardDao dbPro = BoardDao.getInstance(); //DB연결
		BoardDto article = dbPro.getArticle(num);
//		request.setAttribute("num", request.getParameter("num"));
//		request.setAttribute("pageNum",request.getParameter("pageNum"));
//		request.setAttribute("article", article);

		ServletOutputStream outStream = null;
		FileInputStream inputStream = null;
		//DB에있는 정보가져오기
		String fileName = null;
		fileName=article.getServer_filename();
		File downfile = new File("D:\\자바JSP\\jsp프로잭트\\workspace\\FUBoard\\WebContent\\board\\upload\\"+fileName);
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
	
	return "/bbboard3/content.jsp";	//해당 뷰 경로 반환
}
	
}
