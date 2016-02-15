package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.Zgrada_;
import tables.Zgrada;

public class DBExecuteZgrada {
	
	private static final String SQL = "SELECT * FROM zgrada";

	/**
	 * @author dino
	 * Metod za uspostavljanje konekcije na bazu podataka i dohvatanja svih zgrada.
	 * @return 
	 */
	public static void getZgrade() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			Zgrada.getZgradaList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	/**
	 * @author dino
	 * Metod za ubacivanje jedne zgrade u bazu.
	 * @return 
	 */
	public static boolean insertZgrada(Zgrada_ zgrada) throws SQLException {
		
		String sqlInsert = "INSERT INTO zgrada (nazZgrada, kratZgrada) " + "VALUES (?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				//ResultSet rs = stmt.executeQuery(SQL);
				) {
			stmt.setString(1, zgrada.getNazZgrada());
			stmt.setString(2, zgrada.getKratZgrada());
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
				zgrada.setSifZgrada(newKey);
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
