package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CompilerServlet")
public class CompilerServlet extends HttpServlet {

	private static final long serialVersionUID = 9166992002212699252L;
	
	public CompilerServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		
	}

}
