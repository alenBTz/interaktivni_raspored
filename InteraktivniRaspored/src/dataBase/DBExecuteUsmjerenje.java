package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.Usmjerenje_;
import tables.Usmjerenje;

public class DBExecuteUsmjerenje {
	
	private static final String SQL = "SELECT * FROM usmjerenje";
	
	/**
	 * @author dino
	 * Metod za uspostavljanje konekcije na bazu podataka i dohvatanja svih predmetUsmjerenja.
	 * @return 
	 */
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
	public static int getSifUsmjerenja(String imeUsmjerenja) throws SQLException {
		String upitZaSifru = "SELECT sifUsmjerenje FROM usmjerenje WHERE nazUsmjerenje = '"+imeUsmjerenja+"'";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(upitZaSifru);
				) {
			rs.next();
			
			return rs.getInt("sifUsmjerenje");
			
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
		return -1;
	}

	
	/**
	 * @author dino
	 * Metod za ubacivanje jedne zgrade u bazu.
	 * @return 
	 */
	public static boolean insertUsmjerenje(Usmjerenje_ usmjerenje) throws SQLException {
		
		String sqlInsert = "INSERT INTO usmjerenje (nazUsmjerenje, kratUsmjerenje) " + "VALUES (?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setString(1, usmjerenje.getNazUsmjerenje());
			stmt.setString(2, usmjerenje.getKratUsmjerenje());
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
				usmjerenje.setSifUsmjerenje(newKey);
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

