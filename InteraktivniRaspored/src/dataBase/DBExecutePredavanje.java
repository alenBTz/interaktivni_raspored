package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.Predavanje_;
import tables.Predavanje;

public class DBExecutePredavanje {

	
	private static final String SQL = "SELECT * FROM predavanje";

	/**
	 * @author dino
	 * Metod za uspostavljanje konekcije na bazu podataka i dohvatanja svih predavanja.
	 * @return 
	 */
	public static void getPredavanja() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			Predavanje.getPredavanjeList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	/**
	 * @author dino
	 * Metod za ubacivanje jednog nastavnika u tabelu.
	 * @return 
	 */
	public static boolean insertPredavanje(Predavanje_ predavanje) throws SQLException {
		
		String sqlInsert = "INSERT INTO predavanje (danPredavanje, pocetakPredavanjeH, pocetakPredavanjeMin"
				+ ", krajPredavanjeH, krajPredavanjeMin) " + "VALUES (?, ?, ?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			
			stmt.setString(1, predavanje.getDanPredavanje());
			stmt.setInt(2, predavanje.getPocetakPredavanjeH());
			stmt.setInt(3, predavanje.getPocetakPredavanjeMin());
			stmt.setInt(4, predavanje.getKrajPredavanjeH());
			stmt.setInt(5, predavanje.getKrajPredavanjeMin());
					
			int affected = stmt.executeUpdate();
			
			/**
			 * @dino
			 * Posto se u bazi sifNastavnik automatski inkrementira unutar baze, treba ovaj dio.
			 * Pogledati Lynda tutorial insertsql, pa ce biti jasnije
			 */
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				predavanje.setSifPredavanje(newKey);
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
