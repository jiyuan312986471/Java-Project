package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import model.User;
import exception.DatabaseAccessError;

public class UserDB {
	
	private static String QUERY = "SELECT email,fistname,lastname,password FROM user WHERE email = ?";
	
	public static boolean checkLogin(String email,String password) throws DatabaseAccessError, ClassNotFoundException, SQLException, NamingException{
		User u = getUser(email);
		if ( u == null ) {
			return false;
		}
		return u.getPassword().equals(password);
	}
	
	public static User getUser(String email) throws DatabaseAccessError, ClassNotFoundException, SQLException, NamingException {
		Connection con = DBUtil.getConnection();

		try {
			User user = null;
			PreparedStatement stmt = con.prepareStatement(QUERY);
			stmt.setString(1, email);

			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				user = new User();
				user.setEmail(result.getString(1));
				user.setFirstName(result.getString(2));
				user.setLastName(result.getString(3));
				user.setPassword(result.getString(4));
			}

			result.close();
			stmt.close();

			return user;

		} finally {
			try {
				DBUtil.dropConnection(con);
			} catch (SQLException e) { /* ignored */
				e.printStackTrace();
			}
		}
	}
}
