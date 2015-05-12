package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Exercise;
import model.db.ExerciseDB;

@WebServlet("/ExercisePageServlet")
public class ExercisePageServlet extends HttpServlet {

	private static final long serialVersionUID = -8810490687275553270L;
	
	private static String msgErrorLogin = "Please login";
	
	public ExercisePageServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ( req.getSession().getAttribute("mail") == null ) {
			req.getSession().setAttribute("messageError", msgErrorLogin);
			resp.sendRedirect("index.jsp");
		}
		else {
			// get title
			String title = req.getParameter("exoTitle");
			
			// get exo from db
			Exercise exo = null;
			try {
				exo = ExerciseDB.getExoByTitle(title);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// save to request
			req.getSession().setAttribute("exo", exo);
			
			// forward to ExercisePage
			req.getRequestDispatcher("/page/ExercisePage.jsp").forward(req, resp);
		}
	}

}
