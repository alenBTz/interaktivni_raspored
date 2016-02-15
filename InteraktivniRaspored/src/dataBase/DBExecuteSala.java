package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.Sala_;
import tables.Sala;

public class DBExecuteSala {
	
	private static final String SQL = "SELECT * FROM sala";

	public static void getSala() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			Sala.getSalaList(rs);
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
	public static boolean insertSala(Sala_ sala) throws SQLException {
		
		String sqlInsert = "INSERT INTO sala (kratSala, nazivSala, sifZgrada, brMjesta) " + "VALUES (?, ?, ?, ?)";

		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			Statement stmtPom1 = conn.createStatement();
			stmtPom1.execute("SET FOREIGN_KEY_CHECKS=0");
			stmtPom1.close();
			
			stmt.setString(1, sala.getKratSala());
			stmt.setString(2, sala.getNazivSala());
			stmt.setInt(3, sala.getSifZgrada());
			stmt.setInt(4, sala.getBrMjesta());
			int affected = stmt.executeUpdate();
			
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				sala.setSifSala(newKey);
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
