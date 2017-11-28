package controller;
// java ���� import
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
// servlet ���� import
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.J005_CommandAction; // CommandAction�� import �����ش�.

public class J004_ControllerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// ��ɾ�� ��ɾ� ó�� Ŭ������ ������ �����صδ� MAP
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	
	// ��ɾ�� ó��Ŭ������ Mapping�Ǿ��ִ� CommandPro.properties������ �о� Map ��ü�� commandMap�� �����Ѵ�.
	
	// web.xml���� propertyConfig�� �ش��ϴ� init-param�� ���� �о�´�.
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("propertyConfig");
		
		// ��ɾ�� Ŀ��Ŭ������ ���� ������ ������ Properties ��ü ����
		Properties pr = new Properties();
		FileInputStream f = null;
		String path = config.getServletContext().getRealPath("/WEB-INF");
		try {
			f = new FileInputStream(new File(path, props));
			// CommandPro.properties�� ������ Properties ��ü�� ����
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
		// Iterator ��ü ���
		Iterator<Object> keyIter = pr.keySet().iterator();
		
		while(keyIter.hasNext()) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			try {
				// ������ ���ڿ��� Ŭ������ ����
				Class<?> commandClass = Class.forName(className);
				// ������� �ش� Ŭ������ ��ü ����
				Object commandInstance = commandClass.newInstance();
				// ������ ��ü�� commandMap�� ����
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
	
	// Get ����� ���� �޼ҵ�
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	// Post ����� ���� �޼ҵ�
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	// ������� ��û�� ���� �м��Ͽ� �ش� �۾��� ó��
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