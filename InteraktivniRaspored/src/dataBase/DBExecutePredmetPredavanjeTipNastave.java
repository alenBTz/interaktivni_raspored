package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.PredmetPredavanjeTipNastave_;
import tables.PredmetPredavanjeTipNastave;

public class DBExecutePredmetPredavanjeTipNastave {

	private static final String SQL = "SELECT * FROM PredmetPredavanjeTipNastave";

	public static void getPredmetPredavanjeTipNastave() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			PredmetPredavanjeTipNastave.getPredmetPredavanjeTipNastaveList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}


	public static boolean insertPredmetNastavnikTipNastave(PredmetPredavanjeTipNastave_ pptn) throws SQLException {

		String sqlInsert = "INSERT INTO PredmetPredavanjeTipNastave (sifPredmet, sifPredavanje, sifTipNastave) " + "VALUES (?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			Statement stmtPom1 = conn.createStatement();
			stmtPom1.execute("SET FOREIGN_KEY_CHECKS=0");
			stmtPom1.close();
			
			stmt.setInt(1, pptn.getSifPredmet());
			stmt.setInt(2, pptn.getSifPredavanje());
			stmt.setInt(3, pptn.getSifTipNastave());
			
			int affected = stmt.executeUpdate();

			
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				pptn.setSifPredmetPredavanjeTipNastave(newKey);
			} else {
				System.err.println("Nijedan red nije izmjenjen");
				return false;
			}
			
			Statement stmtPom2 = conn.createStatement();
			stmtPom2.execute("SET FOREIGN_KEY_CHECKS=1");
			stmtPom2.close();
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
