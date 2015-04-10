package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.DatabaseAccessError;
import model.User;
import model.db.UserDB;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 3830415097795711232L;
	
	private static final String errorEmail = "Invalid Email.";
	private static final String errorName = "Invalid Name.";
	private static final String errorPwd = "Invalid Password.";
	private static final String errorConfirmPwd = "The second password doesn't match the first one.";
	
	public RegistrationServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// remove error msg
		req.getSession().removeAttribute("messageError");
		
		// get info
		String mail = req.getParameter("userEmail").toString();
		String firstName = req.getParameter("userFirstName").toString();
		String lastName = req.getParameter("userLastName").toString();
		String pwd = req.getParameter("userPwd").toString();
		String confirmPwd = req.getParameter("userConfirmPwd").toString();
		
		// error msg
		String errorAdd = "The user \"" + firstName + " " + lastName + "\" already exists.";
		
		// check info
		if ( !User.isEmail(mail) ) {
			req.getSession().setAttribute("messageError", errorEmail);
			req.getRequestDispatcher("/page/Registration.jsp").forward(req, resp);
			req.getSession().removeAttribute("messageError");
			return;
		}
		else if ( !User.isName(firstName) || !User.isName(lastName) ) {
			req.getSession().setAttribute("messageError", errorName);
			req.getRequestDispatcher("/page/Registration.jsp").forward(req, resp);
			req.getSession().removeAttribute("messageError");
			return;
		}
		else if ( !User.isValidPwd(pwd) ) {
			req.getSession().setAttribute("messageError", errorPwd);
			req.getRequestDispatcher("/page/Registration.jsp").forward(req, resp);
			req.getSession().removeAttribute("messageError");
			return;
		}
		else if ( !confirmPwd.equals(pwd) ) {
			req.getSession().setAttribute("messageError", errorConfirmPwd);
			req.getRequestDispatcher("/page/Registration.jsp").forward(req, resp);
			req.getSession().removeAttribute("messageError");
			return;
		}
		

		/**************************************************
         * 				IF ALL GOES WELL
         **************************************************/
		// generate user
		User u = new User(mail, firstName, lastName, pwd);

		// store in DB
		try {
			UserDB.add(u);
		} catch (DatabaseAccessError e) {
			// if add failed
			req.getSession().setAttribute("messageError", errorAdd);
			req.getRequestDispatcher("/page/Registration.jsp").forward(req, resp);
			req.getSession().removeAttribute("messageError");
			return;
		} 

		// remove parameters from request
		req.removeAttribute("userEmail");
		req.removeAttribute("userFirstName");
		req.removeAttribute("userLastName");
		req.removeAttribute("userPwd");
		req.removeAttribute("userConfirmPwd");
		

		// save attribute to request
		req.setAttribute("username", mail);
		req.setAttribute("password", pwd);

		// to the index page
		req.getRequestDispatcher("/LoginServlet").forward(req, resp);
	}

}
