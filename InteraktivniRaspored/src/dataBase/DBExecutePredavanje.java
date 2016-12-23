package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modeli.PredavanjeUsmjerenjeSemestar_;
import modeli.Predavanje_;
import tables.Predavanje;
import tables.PredavanjeUsmjerenjeSemestar;

public class DBExecutePredavanje {

	public static int sifPredavanjePublic = -1;
	private static final String SQL = "SELECT * FROM predavanje";

	/**
	 * @author dino
	 * Metod za uspostavljanje konekcije na bazu podataka i dohvatanja svih predavanja.
	 * @return 
	 */
	public static ArrayList<Predavanje_> getPredavanja() throws SQLException 
	{
		ArrayList <Predavanje_> lista = new ArrayList<Predavanje_>();
		try
		{
			Connection conn = DBUtil.getConnection(DBType.MYSQL);
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SQL);
			lista = Predavanje.getPredavanjeList(rs);
		} 
		catch (SQLException e) 
		{
			DBUtil.processException(e);
		}
		System.out.println("listaPredavanje:" + lista.size());
		return lista;
	}
	
	/**
	 * @author dino
	 * Metod za ubacivanje jednog nastavnika u tabelu.
	 * @return 
	 */
	public static boolean insertPredavanje(Predavanje_ predavanje) throws SQLException {
		
		String sqlInsert = "INSERT INTO predavanje (danPredavanje, pocetakPredavanje, krajPredavanje, sifSala, tipPredavanja) " + "VALUES (?, ?, ?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			
			stmt.setString(1, predavanje.getDanPredavanje());
			stmt.setTime(2, predavanje.getPocetakPredavanje());
			stmt.setTime(3, predavanje.getKrajPredavanje());
			stmt.setInt(4, predavanje.getSifSala());
			stmt.setString(5, predavanje.getTipPredavanja());
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
				predavanje.setSifPredavanje(newKey);
				sifPredavanjePublic = newKey;
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
	
	public static ArrayList<Integer> getPredavanjaBySala(int sifSala) throws SQLException 
	{
		String sqlGetByName = "SELECT * FROM predavanje WHERE sifSala = '" + sifSala + "'";
		System.out.println(sqlGetByName);
		ArrayList<Predavanje_> lista = null;
		try
		{
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sqlGetByName);
				lista = Predavanje.getPredavanjeList(rs);
		} 
		catch (SQLException e)
		{
			DBUtil.processException(e);
			System.out.println("U bazi ne postoji predavanje sa sifrom sale  :"+ sifSala);
		}
		ArrayList<Integer> listaSifPredavanja = new ArrayList<Integer>();
		for(int i=0; i<lista.size();i++)
		{
			listaSifPredavanja.add(lista.get(i).getSifPredavanje());
		}
		
		return listaSifPredavanja;
	}
}
