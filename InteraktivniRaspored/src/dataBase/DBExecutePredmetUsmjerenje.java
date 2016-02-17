package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.PredmetUsmjerenje_;
import tables.PredmetUsmjerenje;

public class DBExecutePredmetUsmjerenje {
	
	private static final String SQL = "SELECT * FROM PredmetUsmjerenje";

	public static void getPredmetUsmjerenja() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			PredmetUsmjerenje.getPredmetList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}


	public static boolean insertPredmetUsmjerenje(PredmetUsmjerenje_ predmetUsmjerenje) throws SQLException {

		String sqlInsert = "INSERT INTO PredmetUsmjerenje (sifPremet, sifUsmjerenje) " + "VALUES (?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, predmetUsmjerenje.getSifPredmet());
			stmt.setInt(2, predmetUsmjerenje.getSifUsmjerenje());
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
				predmetUsmjerenje.setSifUsmjerenje(newKey);
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





