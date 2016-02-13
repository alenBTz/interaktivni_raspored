package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.Nastavnik_;
import tables.Nastavnik;

/**
 * @author dino
 * Klasa koja uspostavlja konekciju sa Bazom podataka, i izvršava jednu SQl naredbu vezanu za tabelu nastavnik.
 */
public class DBExecuteNastavnik {

	private static final String SQL = "SELECT * FROM nastavnik";

	/**
	 * @author dino
	 * Metod za uspostavljanje konekcije na bazu podataka i dohvatanja svih nastavnika.
	 * @return 
	 */
	public static void getNastavnici() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			Nastavnik.getNastavnikList(rs);
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
	public static boolean insertNastavnik(Nastavnik_ nastavnik) throws SQLException {
		
		String sqlInsert = "INSERT INTO nastavnik (imeNastavnik, prezNastavnik, vrstaNastavnik, username, password) " + "VALUES (?, ?, ?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				//ResultSet rs = stmt.executeQuery(SQL);
				) {
			
			stmt.setString(1, nastavnik.getImeNastavnik());
			stmt.setString(2, nastavnik.getPrezNastavnik());
			stmt.setInt(3, nastavnik.getVrstaNastavnik());
			stmt.setString(4, nastavnik.getUsername());
			stmt.setString(5, nastavnik.getPassword());
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
				nastavnik.setSifNastavnik(newKey);
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
