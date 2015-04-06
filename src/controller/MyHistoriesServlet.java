package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyHistoriesServlet")
public class MyHistoriesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 4293189345653839480L;
	
	public MyHistoriesServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		
	}

}
