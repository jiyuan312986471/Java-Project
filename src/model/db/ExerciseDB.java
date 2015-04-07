package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import exception.DatabaseAccessError;
import model.Exercise;

public class ExerciseDB {
	private static String QUERY_GET_EXOS = "SELECT title, description FROM exercise";
	private static String QUERY_GET_EXO_BY_PK = "SELECT title, description FROM exercise WHERE title = ?";
	
	// get exo by PK
		public static Exercise getExoByTitle(String title) throws Exception {
			Connection con = DBUtil.getConnection();

			try {
				Exercise exo = null;
				PreparedStatement stmt = con.prepareStatement(QUERY_GET_EXO_BY_PK);
				stmt.setString(1, title);

				ResultSet result = stmt.executeQuery();

				if (result.next()) {

					// set acronym, description, budget, create date and category
					exo = new Exercise();
					exo.setTitle(result.getString(1));
					exo.setDescription(result.getString(2));
				}

				result.close();
				stmt.close();

				return exo;

			} finally {
				try {
					DBUtil.dropConnection(con);
				} catch (SQLException e) { /* ignored */
					e.printStackTrace();
				}
			}
		}
		
		
		// get all projects
		public static ArrayList<Exercise> getAllExos() throws ClassNotFoundException, SQLException, NamingException, DatabaseAccessError {
			Connection con = DBUtil.getConnection();
			
			ArrayList<Exercise> listExo = new ArrayList<Exercise>();
			Statement stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery(QUERY_GET_EXOS);
			
			while ( result.next() ) {
				// create exo
				Exercise exo = new Exercise();
				exo.setTitle(result.getString(1));
				exo.setDescription(result.getString(2));
				
				// add exo into list
				listExo.add(exo);
			}
			
			result.close();
			stmt.close();
			
			DBUtil.dropConnection(con);

			return listExo;		
		}
}
