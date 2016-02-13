package dataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.Nastavnik_;
import modeli.Usmjerenje_;
import tables.Nastavnik;
import tables.Usmjerenje;

public class DBExecuteUsmjerenje {

	private static final String SQL = "SELECT * FROM usmjerenje";

	
	public static void getUsmjerenja() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			Usmjerenje.getUsmjerenjeList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	
	public static boolean insertUsmjerenje(Usmjerenje_ usmjerenje) throws SQLException {
		
		String sqlInsert = "INSERT INTO usmjerenje (nazUsmjerenje) " + "VALUES (?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				//ResultSet rs = stmt.executeQuery(SQL);
				) {
			
			stmt.setString(1, usmjerenje.getNazUsmjerenje());
			int affected = stmt.executeUpdate();
			
			
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				usmjerenje.setSifUsmjerenje(newKey);
			} else {
				System.err.println("Niti jedan red nije izmijenjen");
				return false;
			}
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		finally {
			if(keys != null)
				keys.close();
		}
		
		return true;
	}
	
	
}
