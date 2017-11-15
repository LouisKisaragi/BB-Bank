package fileUpload;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/*
@WebServlet("/upload")
@MultipartConfig(
		location = "C:\\dev\\apache-tomcat-8.5.11\\upload",
		maxFileSize=1024L,
		maxRequestSize = -1,
		fileSizeThreshold = -1)*/
public class UploadServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println("<html><body>");
		String contentType = request.getContentType();
		if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			printPartInfo(request, writer);
		} else {
			writer.println("multipart가 아님(일반 파라미터)");
		}
		writer.println("</body></html>");
	}
	
	private void printPartInfo(HttpServletRequest request, PrintWriter
			writer)throws ServletException, IOException{
		Collection<Part> parts=request.getParts();
		for(Part part : parts) {
			writer.println("<br> name = "+part.getName());
			String contentType= part.getContentType();
			System.out.println("CT:" + contentType);
			writer.println("<br> contentType = "+contentType);
			if(contentType==null) {
				part.delete();
			}
			else if( contentType.startsWith("application/")) {
				long size=part.getSize();
				System.out.println("size ::"+size);
				writer.println("<br> size = "+size);
				String filename = getFileName(part);
				System.out.println("fn ::"+filename);
				writer.println("<br> filename = "+filename);
				if(size > 0) {
					part.write("c:\\temp\\" + filename);
					part.delete();
				}
			}else if( contentType.startsWith("image/")) {
				long size=part.getSize();
				System.out.println("size ::"+size);
				writer.println("<br> size = "+size);
				String filename = getFileName(part);
				System.out.println("fn ::"+filename);
				writer.println("<br> filename = "+filename);
				if(size > 0) {
					part.write("c:\\temp\\" + filename);
					part.delete();
				}
			}else if( contentType.startsWith("text/")) {
				long size=part.getSize();
				System.out.println("size ::"+size);
				writer.println("<br> size = "+size);
				String filename = getFileName(part);
				System.out.println("fn ::"+filename);
				writer.println("<br> filename = "+filename);
				if(size > 0) {
					part.write("c:\\temp\\" + filename);
					part.delete();
				}
			}
			else {
				String value = readParameterValue(part);
				writer.println("<br> value = "+value);
			}
			writer.println("<hr/>");
		}
	}
	private String getFileName(Part part) throws UnsupportedEncodingException{
		for( String cd : part.getHeader("Content-Disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				String tmp = cd.substring(cd.indexOf('=')+
						1).trim().replace("\"", "");
				tmp=tmp.substring(tmp.indexOf(":")+1);
				return tmp;
			}
			
		}return null;
	}
	private String readParameterValue(Part part)throws IOException{
		InputStreamReader isr = new InputStreamReader(part.getInputStream(),"utf-8");
		char[] data = new char[512];
		int len =-1;
		StringBuilder builder = new StringBuilder();
		while( (len = isr.read(data))!=-1) {
			builder.append(data, 0 , len);
		}
		return builder.toString();
	}
	
}

