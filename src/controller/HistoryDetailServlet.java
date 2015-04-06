package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HistoryDetailServlet")
public class HistoryDetailServlet extends HttpServlet {

	private static final long serialVersionUID = -3706211978190675127L;
	
	public HistoryDetailServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		
	}

}
