package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.Nastavnik_;
import modeli.Rezervacija_;
import tables.Nastavnik;
import tables.Rezervacija;

public class DBExecuteRezervacija {
	private static final String SQL = "SELECT * FROM rezervacija";

	/**
	 * @author dino
	 * Metod za uspostavljanje konekcije na bazu podataka i dohvatanja svih nastavnika.
	 * @return 
	 */
	public static void getRezervacije() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			Rezervacija.getRezervacijaList(rs);
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
	public static boolean insertRezervacija(Rezervacija_ rezervacija) throws SQLException {
		
		String sqlInsert = "INSERT INTO rezervacija (datumRezervacija, tipRezervacija, satRezervacija"
				+ ", satRezervacijaKraj, sifPredmet, sifSala, sifNastavnik) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				//ResultSet rs = stmt.executeQuery(SQL);
				) {
			
			stmt.setDate(1, rezervacija.getDatumRezervacija());
			stmt.setString(2, rezervacija.getTipRezervacije());
			stmt.setTime(3, rezervacija.getSatRezervacije());
			stmt.setTime(4, rezervacija.getSatRezervacijeKraj());
			stmt.setInt(5, rezervacija.getSifPredmet());
			stmt.setInt(6, rezervacija.getSifSala());
			stmt.setInt(7, rezervacija.getSifNastavnik());

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
				rezervacija.setSifRezervacija(newKey);
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
