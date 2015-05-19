package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import compiler.DynamicCompiler;
import model.Exercise;
import model.User;

@WebServlet("/CompilerServlet")
public class CompilerServlet extends HttpServlet {

	private static final long serialVersionUID = 9166992002212699252L;
	
	private static String msgErrorLogin = "Please login";
	
	public CompilerServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if ( req.getSession().getAttribute("mail") == null ) {
			req.getSession().setAttribute("messageError", msgErrorLogin);
			resp.sendRedirect("index.jsp");
		}
		else {
			// get user, exo and code
			User u = (User) req.getSession().getAttribute("User");
			Exercise exo = (Exercise) req.getSession().getAttribute("exo");
			String code = req.getParameter("code");

			// compile
			String result = DynamicCompiler.dynamicCompile(exo, code, u);

			// store code and result into session
			req.getSession().setAttribute("code", code);
			req.setAttribute("result", result);

			// turn to result page
			req.getRequestDispatcher("/ResultPageServlet").forward(req, resp);

			// check result
			System.out.println(result);
		}
	}

}
