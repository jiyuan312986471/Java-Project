package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Exercise;

@WebServlet("/ResultPageServlet")
public class ResultPageServlet extends HttpServlet {

	private static final long serialVersionUID = -4587571621273999391L;
	
	public ResultPageServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get user, exo and code
		Exercise exo = (Exercise) req.getSession().getAttribute("exo");
		String code = req.getSession().getAttribute("code").toString();
		
		// code process
		String[] split = code.split("\\n");
		code = "";
		for (String s: split) {
			s = "		" + s;
			code = code + s + "\n";
		}
		
		// generate source code
		String srcCode = exo.getContentHead() + "\n" + code + "\n" + exo.getContentFoot();
		
		// store source code into session
		req.getSession().setAttribute("code", srcCode);
		
		// turn to result page
		req.getRequestDispatcher("/page/ResultPage.jsp").forward(req, resp);
	}

}
