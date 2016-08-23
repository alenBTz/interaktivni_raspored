package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.Korisnik_;
import tables.Korisnik;


public class DBExecuteKorisnik {
	private static final String SQL = "SELECT * FROM Korisnik";
	
	/**
	 * @author dino
	 * Metod za uspostavljanje konekcije na bazu podataka i dohvatanja svih ntorki iz tabele.
	 * @return 
	 */
	public static void getKorisnici() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			Korisnik.getKorisnikList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	public static Korisnik_ getKorisnik(String korisnickoIme, String korisnickaLozinka) throws SQLException {
		String sqlGetUser = "SELECT * FROM korisnik WHERE korisnickoIme = '" + korisnickoIme + "' AND sifra = '" + korisnickaLozinka + "'";
		Korisnik_ korisnik = new Korisnik_();
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sqlGetUser);
				) {
			while(rs.next()){
				korisnik.setKorisnickoIme(rs.getString("korisnickoIme"));
				korisnik.setIme(rs.getString("ime"));
				korisnik.setPrezime(rs.getString("prezime"));
				korisnik.setUloga(rs.getString("uloga"));
			}
		} 
		catch (SQLException e) {
			System.err.println("Pogresno uneseni podaci,probajte ponovo!");
			DBUtil.processException(e);
		}
		return korisnik;
	}
	
	public static boolean insertKorisnik(Korisnik_ korisnik) throws SQLException {
		
		String sqlInsert = "INSERT INTO korisnik (korisnickoIme, ime, prezime, sifra, uloga) " + "VALUES (?, ?, ?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				//ResultSet rs = stmt.executeQuery(SQL);
				) {
			
			stmt.setString(1, korisnik.getKorisnickoIme());
			stmt.setString(2, korisnik.getIme());
			stmt.setString(3, korisnik.getPrezime());
			stmt.setString(4, korisnik.getSifra());
			stmt.setString(5, korisnik.getUloga());
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
				korisnik.setSifKorisnik(newKey);
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
	
	public static int countProdekana() throws SQLException {
		String sqlCount = "SELECT COUNT(*) AS rowcount FROM korisnik WHERE uloga = '" + "Prodekan" + "'";
		int brojProdekana = -1;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sqlCount);
				) {
			while(rs.next()){
				brojProdekana = rs.getInt("rowcount");
			}
			
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
		return brojProdekana;
	}
}
