package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Exercise;

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
		// get exo and code
		Exercise exo = (Exercise) req.getSession().getAttribute("exo");
		String code = req.getParameter("code");
		
		// generate java code
		String javaCode = exo.getContentHead() + code + exo.getContentFoot();
		
		// compile
	}

}
