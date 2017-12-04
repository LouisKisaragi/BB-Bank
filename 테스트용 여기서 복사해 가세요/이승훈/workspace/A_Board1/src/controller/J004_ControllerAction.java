package controller;
// java 관련 import
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
// servlet 관련 import
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.J005_CommandAction; // CommandAction을 import 시켜준다.

public class J004_ControllerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 명령어와 명령어 처리 클래스를 쌍으로 저장해두는 MAP
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	
	// 명령어와 처리클래스가 Mapping되어있는 CommandPro.properties파일을 읽어 Map 객체인 commandMap에 저장한다.
	
	// web.xml에서 propertyConfig에 해당하는 init-param의 값을 읽어온다.
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("propertyConfig");
		
		// 명령어와 커리클래스의 매핑 정보를 저장할 Properties 객체 생성
		Properties pr = new Properties();
		FileInputStream f = null;
		String path = config.getServletContext().getRealPath("/WEB-INF");
		try {
			f = new FileInputStream(new File(path, props));
			// CommandPro.properties의 정보를 Properties 객체에 저장
			pr.load(f);
		} catch(IOException e) {
			throw new ServletException(e);
		} finally {
			if(f != null) {
				try {
					f.close();
				} catch(IOException e) {
					
				} // end try-catch
			}	// end if
		}		// end finally
		// Iterator 객체 사용
		Iterator<Object> keyIter = pr.keySet().iterator();
		
		while(keyIter.hasNext()) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			try {
				// 가져온 문자열을 클래스로 만듬
				Class<?> commandClass = Class.forName(className);
				// 만들어진 해당 클래스의 객체 생성
				Object commandInstance = commandClass.newInstance();
				// 생성된 객체를 commandMap에 저장
				commandMap.put(command, commandInstance);
			} catch(ClassNotFoundException e) {
				throw new ServletException(e);
			} catch(InstantiationException e) {
				throw new ServletException(e);
			} catch(IllegalAccessException e) {
				throw new ServletException(e);
			}	// end try-catch
		}		// end while
	}			// end init()
	
	// Get 방식의 서비스 메소드
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	// Post 방식의 서비스 메소드
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	// 사용자의 요청에 따라 분석하여 해당 작업을 처리
	public void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		J005_CommandAction com = null;
		try {
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			com = (J005_CommandAction)commandMap.get(command);
			view = com.requestPro(request, response);
		} catch(Throwable e) {
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
	
}