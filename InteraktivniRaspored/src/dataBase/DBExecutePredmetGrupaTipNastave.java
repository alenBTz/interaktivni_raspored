package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.PredmetGrupaTipNastave_;
import tables.PredmetGrupaTipNastave;

public class DBExecutePredmetGrupaTipNastave {

	private static final String SQL = "SELECT * FROM PredmetGrupaTipNastave";

	public static void getPredmetGrupaTipNastave() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			PredmetGrupaTipNastave.getPredmetGrupaTipNastaveList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}


	public static boolean insertPredmetGrupaTipNastave(PredmetGrupaTipNastave_ predmetGrupaTipNastave) throws SQLException {

		String sqlInsert = "INSERT INTO PredmetGrupaTipNastave (sifPremet, sifGrupa, sifTipNastaven) " + "VALUES (?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, predmetGrupaTipNastave.getSifPredmet());
			stmt.setInt(2, predmetGrupaTipNastave.getSifGrupa());
			stmt.setInt(3, predmetGrupaTipNastave.getSifTipNastave());
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
				predmetGrupaTipNastave.setSifPredmetGrupaTipNastave(newKey);
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
