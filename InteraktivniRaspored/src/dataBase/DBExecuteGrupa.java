package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.Grupa_;
import tables.Grupa;



public class DBExecuteGrupa {

	private static final String SQL = "SELECT * FROM grupa";

	/**
	 * @author dino
	 * Metod za uspostavljanje konekcije na bazu podataka i dohvatanja svih ntorki iz grupa.
	 * @return 
	 */
	public static void getGrupe() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			Grupa.getGrupaList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	/**
	 * @author dino
	 * Metod za ubacivanje jedne grupe u tabelu.

	 * @return 
	 */
	public static boolean insertGrupa(Grupa_ grupa) throws SQLException {
		
		String sqlInsert = "INSERT INTO grupa (nazivGrupa) " + "VALUES (?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			
			stmt.setString(1, grupa.getNazivGrupa());
			int affected = stmt.executeUpdate();
			
			/**
			 * @dino
			 * Posto se u bazi sifIzborni automatski inkrementira unutar baze, treba ovaj dio.
			 * Pogledati Lynda tutorial insertsql, pa ce biti jasnije
			 */
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				grupa.setSifGrupa(newKey);
			} else {
				System.err.println("Nijedan red nije izmjenjen");
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
