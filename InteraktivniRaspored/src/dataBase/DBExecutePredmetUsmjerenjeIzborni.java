package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.PredmetUsmjerenjeIzborni_;
import tables.PredmetUsmjerenjeIzborni;

public class DBExecutePredmetUsmjerenjeIzborni {
	
	private static final String SQL = "SELECT * FROM predmetusmjerenjeizborni";

	public static void getPredmetUsmjerenja() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			PredmetUsmjerenjeIzborni.getPredmetUsmjerenjeIzborniList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}


	public static boolean insertPredmetUsmjerenje(PredmetUsmjerenjeIzborni_ predmetUsmjerenje) throws SQLException {

		String sqlInsert = "INSERT INTO predmetusmjerenjeizborni (sifPredmet, sifUsmjerenje, sifIzborni) " + "VALUES (?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, predmetUsmjerenje.getSifPredmet());
			stmt.setInt(2, predmetUsmjerenje.getSifUsmjerenje());
			stmt.setInt(3, predmetUsmjerenje.getSifIzborni());
			int affected = stmt.executeUpdate();

			/**
			 * @dino
			 * Posto se u bazi prim kljuc automatski inkrementira unutar baze, treba ovaj dio.
			 * Pogledati Lynda tutorial insertsql, pa ce biti jasnije
			 */
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				predmetUsmjerenje.setSifPredmUsmjIzborni(newKey);
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

	public static boolean updatePredmetUsmjerenje(PredmetUsmjerenjeIzborni_ predmetUsmjerenje) throws SQLException {
		
		/**
		 * sifIzborni = 2 je po defaultu, tj da predmeti "izborni" predmeti na svim usmjerenjima.
		 * za odgovarajuci predmet na odgovarajucem usmjerenju, postavljamo da je sifIzborni = 1, tj da je obavezan
		 */
		String sqlInsert = "UPDATE predmetusmjerenjeizborni SET sifIzborni = ? " + "WHERE sifPredmet = ? AND sifUsmjerenje = ?";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, predmetUsmjerenje.getSifIzborni());
			stmt.setInt(2, predmetUsmjerenje.getSifPredmet());
			stmt.setInt(3, predmetUsmjerenje.getSifUsmjerenje());
			stmt.executeUpdate();

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





