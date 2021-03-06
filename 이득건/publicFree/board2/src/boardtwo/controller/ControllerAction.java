package boardtwo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardtwo.action.CommandAction;


public class ControllerAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// ��ɾ�� ��ɾ� ó�� Ŭ������ ������ �����صδ� MAP
	private Map<String, Object> commandMap = new HashMap<String, Object>();

	/*
	 * ��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties����(CommandPro.properties)�� �о� Map��ü��
	 * commandMap�� �����Ѵ�.
	 */

	// web.xml���� propertyConfig�� �ش��ϴ� init-param�� ���� �о�´�.
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("propertyConfig");

		// ��ɾ�� ó��Ŭ������ ���� ������ ������ Properties��ü ����
		Properties pr = new Properties();
		FileInputStream f = null;
		String path = config.getServletContext().getRealPath("/WEB-INF");
		try {
			f = new FileInputStream(new File(path, props));

			// Command.properties������ ������ Properties��ü�� ����
			pr.load(f);
		} catch(IOException e) {
			throw new ServletException(e);
		} finally{
			if(f != null)
				try {
					f.close();
				} catch (IOException e) {
				}
		}
		// Iterator ��ü���
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
			}
		}
	}
	//Get��� ���� �޼���
	public void doGet(
		HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			requestPro(request, response);
		}
	
	//Post��� ���� �޼���
		public void doPost(
					HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
			requestPro(request, response);
				
	}
		
		//������� ��û�� ���� �м��Ͽ� �ش� �۾��� ó��
		private void requestPro(
				HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			String view = null;
			CommandAction com = null;
			try{
				String command = request.getRequestURI();
				if(command.indexOf(request.getContextPath()) == 0){
					command = command.substring(request.getContextPath().length());
				}
				com = (CommandAction)commandMap.get(command);
				view = com.requestPro(request, response);
			}catch(Throwable e){
				throw new ServletException(e);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
}
