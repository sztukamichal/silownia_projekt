package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import silownia_java.DBConnection;
import model.Exercise;

public class ExerciseDAO {

	
	public static Exercise getExercise(int id) throws SQLException{
	
		Connection dbConn = null;
		Exercise exercise = new Exercise(); 
		
	     try {
	    	 try{
	    		 dbConn = DBConnection.createConnection();
	             System.out.println(dbConn);
	            } 
	    	 	catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            Statement stmt = dbConn.createStatement();
	            String query = "SELECT * FROM exercise WHERE exercise_id = '" + id+"'";
	            System.out.println(query);
	            ResultSet rs = stmt.executeQuery(query);
	            while (rs.next()) {
	            	exercise.setExercise_id(rs.getInt(1));
	            	exercise.setName(rs.getString(2));
	            	exercise.setDesription(rs.getString(3));
	            	exercise.setUrl(rs.getString(4));
	            	exercise.setPermission(rs.getInt(5));
	            	exercise.setUser_id(rs.getInt(6));   
	            }
	        } catch (SQLException sqle) {
	            throw sqle;
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            if (dbConn != null) {
	                dbConn.close();
	            }
	            throw e;
	        } finally {
	            if (dbConn != null) {
	                dbConn.close();
	            }
	        }
		 
		 return exercise;
	}
	
	
	public static void addExercise(Exercise exercise) throws SQLException{
		
		Connection dbConn = null;
		String sql = "call add_exercise(?, ?, ?, ?, ?);";
	     try {
	    	 try{
	    		 dbConn = DBConnection.createConnection();
	             System.out.println(dbConn);
	            } 
	    	 	catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	    	 	java.sql.PreparedStatement ps = dbConn.prepareStatement(sql);
	    	 	
	    	 	ps.setString(1, exercise.getName());
	    	 	ps.setString(2, exercise.getDesription());
	    	 	
	    	 	if(exercise.getUrl()==null)
	    	 		ps.setNull(3, Types.VARCHAR);
	    	 	else
	    	 		ps.setString(3,exercise.getUrl());
	    	 	
	    	 	ps.setInt(4, exercise.getpermission());
	    	 	ps.setInt(5, exercise.getUser_id());    	 	
	    	 	ps.execute();
	    	 
	        } catch (SQLException sqle) {
	            throw sqle;
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            if (dbConn != null) {
	                dbConn.close();
	            }
	            throw e;
	        } finally {
	            if (dbConn != null) {
	                dbConn.close();
	            }
	        }		
		
		
	}
	
}
