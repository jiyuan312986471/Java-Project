package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ExercicePageServlet")
public class ExercicePageServlet extends HttpServlet {

	private static final long serialVersionUID = -8810490687275553270L;
	
	public ExercicePageServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		
	}

}
