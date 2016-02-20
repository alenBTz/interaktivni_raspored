package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.PredmetNastavnikTipNastave_;
import tables.PredmetNastavnikTipNastave;


public class DBExecutePredmetNastavnikTipNastave {

	
	private static final String SQL = "SELECT * FROM PredmetNastavnikTipNastave";

	public static void getPredmetNastavnikTipNastave() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			PredmetNastavnikTipNastave.getPredmetNastavnikTipNastaveList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}


	public static boolean insertPredmetNastavnikTipNastave(PredmetNastavnikTipNastave_ predmetNastavnikTipNastave) throws SQLException {

		String sqlInsert = "INSERT INTO PredmetNastavnikTipNastave (sifNastavnik, sifPredmet, sifTipNastave) " + "VALUES (?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, predmetNastavnikTipNastave.getSifNastavnik());
			stmt.setInt(2, predmetNastavnikTipNastave.getSifPredmet());
			stmt.setInt(3, predmetNastavnikTipNastave.getSifTipNastave());
			
			int affected = stmt.executeUpdate();

			
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				predmetNastavnikTipNastave.setSifPredmetNastavnikTipNastave(newKey);
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
