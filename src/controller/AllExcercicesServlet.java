package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.DatabaseAccessError;
import model.Exercise;
import model.db.ExerciseDB;

@WebServlet("/AllExcercicesServlet")
public class AllExcercicesServlet extends HttpServlet {

	private static final long serialVersionUID = -6612875793111318238L;
	
	private static String msgErrorLogin = "Please login";
	
	public AllExcercicesServlet() {
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
			// get all exo
			ArrayList<Exercise> listExo = null;
			try {
				listExo = ExerciseDB.getAllExos();
			} catch (ClassNotFoundException | SQLException | NamingException | DatabaseAccessError e) {
				e.printStackTrace();
			}
	
			// set list into request
			req.setAttribute("listExo", listExo);
	
			// Turn to Page MyProjects
			req.getRequestDispatcher("/page/AllExercises.jsp").forward(req, resp);
		}
	}

}
