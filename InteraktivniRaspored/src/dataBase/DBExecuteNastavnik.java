package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modeli.Nastavnik_;
import modeli.Predavanje_;
import modeli.Sala_;
import tables.Nastavnik;
import tables.Predavanje;

/**
 * @author dino
 * Klasa koja uspostavlja konekciju sa Bazom podataka, i izvršava jednu SQl naredbu vezanu za tabelu nastavnik.
 */
public class DBExecuteNastavnik {

	private static final String SQL = "SELECT * FROM nastavnik";

	/**
	 * @author dino
	 * Metod za uspostavljanje konekcije na bazu podataka i dohvatanja svih nastavnika.
	 * @return 
	 */
	public static void getNastavnici() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			Nastavnik.getNastavnikList(rs);
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
	public static boolean insertNastavnik(Nastavnik_ nastavnik) throws SQLException {
		
		String sqlInsert = "INSERT INTO nastavnik (imeNastavnik, prezNastavnik, vrstaNastavnik) " + "VALUES (?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				//ResultSet rs = stmt.executeQuery(SQL);
				) {
			
			stmt.setString(1, nastavnik.getImeNastavnik());
			stmt.setString(2, nastavnik.getPrezNastavnik());
			stmt.setInt(3, nastavnik.getVrstaNastavnik());
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
				nastavnik.setSifNastavnik(newKey);
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
	
	public static int getSifNastavnikByNameAndSurname(String name, String surname) throws SQLException 
	{
		String sqlGetByName = "SELECT * FROM nastavnik WHERE imeNastavnik = '" + name + "' AND prezNastavnik = '" + surname + "'";
		System.out.println(sqlGetByName);
		ArrayList<Nastavnik_> nastavnik = new ArrayList<Nastavnik_>();
		try
		{
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sqlGetByName);
				nastavnik = Nastavnik.getNastavnikList(rs);
		} 
		catch (SQLException e)
		{
			DBUtil.processException(e);
			System.out.println("U bazi ne postoji nastavnik sa imenom :"+ name + " " + surname);
		}
		
		return nastavnik.get(0).getSifNastavnik();
	}
	
	
	public static Nastavnik_ getNastavnikBySifra(int sifraNastavnik) throws SQLException {
		String sqlGetByName = "SELECT * FROM nastavnik WHERE sifNastavnik = '" + sifraNastavnik + "'";
		Nastavnik_ nastavnik = new Nastavnik_();
		try{
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sqlGetByName);
				while (rs.next())
				{
					nastavnik.setImeNastavnik(rs.getString("imeNastavnik"));
					nastavnik.setPrezNastavnik(rs.getString("prezNastavnik"));
					nastavnik.setSifNastavnik(rs.getInt("sifNastavnik"));
					nastavnik.setVrstaNastavnik(rs.getInt("vrstaNastavnik"));
				}
				
				return nastavnik;
				
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			System.out.println("U bazi ne postoji nastavnik sa sifrom : "+ sifraNastavnik);
		}
		return nastavnik;
	}
	
	public static int countProdekana() throws SQLException {
		String sqlCount = "SELECT COUNT(*) AS rowcount FROM nastavnik WHERE vrstaNastavnik = '0' ";
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
