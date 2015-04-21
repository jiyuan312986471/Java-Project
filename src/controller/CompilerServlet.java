package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import compiler.DynamicCompile;

import model.Exercise;
import model.User;

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
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// get user, exo and code
		User u = (User) req.getSession().getAttribute("User");
		Exercise exo = (Exercise) req.getSession().getAttribute("exo");
		String code = req.getParameter("code");
		
		// compile
		String result = null;
		try {
			result = DynamicCompile.compile(exo, code, u);
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println(result);
	}

}
