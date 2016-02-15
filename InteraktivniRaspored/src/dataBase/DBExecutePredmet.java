package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.Predmet_;
import modeli.Sala_;
import tables.Predmet;
import tables.Sala;

public class DBExecutePredmet {
	
	private static final String SQL = "SELECT * FROM predmet";

	public static void getPredmeti() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			Predmet.getPredmetList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	/**
	 * @author dino
	 * Metod za ubacivanje jednog predmeta u tabelu.
	 * @return 
	 */
	public static boolean insertPredmet(Predmet_ predmet) throws SQLException {
		
		String sqlInsert = "INSERT INTO predmet (nazPredmet, kratPredmet, izborni, tipNastave, sifNastavnik, sifSemestar) " + "VALUES (?, ?, ?, ?, ?, ?)";

		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			Statement stmtPom1 = conn.createStatement();
			stmtPom1.execute("SET FOREIGN_KEY_CHECKS=0");
			stmtPom1.close();
			
			stmt.setString(1, predmet.getNazPredmet());
			stmt.setString(2, predmet.getKratPredmet());
			stmt.setInt(3, predmet.getIzborni());
			stmt.setString(4, predmet.getTipNastave());
			stmt.setInt(5, predmet.getSifNastavnik());
			stmt.setInt(6, predmet.getSifSemestar());
			
			int affected = stmt.executeUpdate();
			
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				predmet.setSifPredmet(newKey);
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
