package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.DatabaseAccessError;
import model.User;
import model.db.UserDB;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 4249780684684026453L;
	
	private static String messageError = "Invalid Username or Password";
	
	public LoginServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			if ( UserDB.checkLogin(username, password) ) {
				User u = UserDB.getUser(username);
				req.getSession().setAttribute("mail", u.getEmail());
				req.getSession().setAttribute("name", u.getFirstName() + u.getLastName());
				req.getSession().setAttribute("User", u);
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}
			else {
				req.getSession().setAttribute("messageError", messageError);
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}
		} catch (DatabaseAccessError e1) {
			req.getSession().setAttribute("messageError", messageError);
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			req.getSession().removeAttribute("messageError");
		}
	}

}
