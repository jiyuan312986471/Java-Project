package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import model.User;
import exception.DatabaseAccessError;

public class UserDB {
	
	private static String QUERY = "SELECT email,firstname,lastname,password FROM user WHERE email = ?";
	private static String ADD = "INSERT into user (email,firstname,lastname,password) values (?,?,?,?)";
	
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
	
	public static void add(User u) throws DatabaseAccessError {
		Connection con = null;
		try {
			// connection
			con = DBUtil.getConnection();
			
			// statement
			PreparedStatement stmt = con.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, u.getEmail());
			stmt.setString(2, u.getFirstName());
			stmt.setString(3, u.getLastName());
			stmt.setString(4, u.getPassword());
			int lines = stmt.executeUpdate();

			if (lines != 1)
				throw new DatabaseAccessError("Invalid quantity of returned lines");

			stmt.close();
		} catch (ClassNotFoundException e) {
			throw new DatabaseAccessError("Class not found", e);
		} catch (SQLException e) {
			throw new DatabaseAccessError("SQL exception", e);
		} catch (NamingException e) {
			throw new DatabaseAccessError("Naming exception", e);
		} finally {
			try {
				DBUtil.dropConnection(con);
			} catch (SQLException e) { /* ignored */
				e.printStackTrace();
			}
		}
	}
}
