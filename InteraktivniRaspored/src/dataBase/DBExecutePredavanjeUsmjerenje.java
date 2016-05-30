package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modeli.Izborni_;
import modeli.PredavanjeUsmjerenje_;
import modeli.Predmet_;
import tables.Izborni;
import tables.PredavanjeUsmjerenje;

public class DBExecutePredavanjeUsmjerenje {

	private static final String SQL = "SELECT * FROM PredavanjeUsmjerenje";

	/**
	 * @author dino
	 * Metod za uspostavljanje konekcije na bazu podataka i dohvatanja svih ntorki iz tabele.
	 * @return 
	 */
	public static void getPredavanjeUsmjerenje() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			PredavanjeUsmjerenje.getPredavanjeUsmjerenjeList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	//Sledeci metod dohvaca predmete po usmjerenjima i vraca sifre pronadjenih predmeta
	//(koristeno pri implementaciji rasporeda tacnije radi dohvacanja predmeta na odredjenom smijeru)
	
	
	public static ArrayList<Integer> getPredmetByUsmjerenje(int sifUsmjerenja) throws SQLException {
		String sqlGetByName = "SELECT * FROM PredavanjeUsmjerenje WHERE sifUsmjerenje = '" + sifUsmjerenja + "'";
		ArrayList<PredavanjeUsmjerenje_> lista = null;
		ArrayList<Integer> array = new ArrayList<Integer>();;
		try{
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sqlGetByName);
				lista = PredavanjeUsmjerenje.getPredavanjeUsmjerenjeList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			System.out.println("U bazi ne postoji predmet sa sifrom usmjerenja :"+ sifUsmjerenja);
		}
		for(int i=0;i<lista.size();i++){
			array.add(lista.get(i).getSifPredavanje());
		}
		return array;
	}
	
	/**
	 * @author dino
	 * Metod za ubacivanje jednog izbornog u tabelu.
	 * ovaj metod necemo koristiti jer su u tabeli vec ubacene dvije n-torke, "ne" i "da"
	 * @return 
	 */
	public static boolean insertPredavanjeUsmjerenje(PredavanjeUsmjerenje_ predavanjeUsmjerenje) throws SQLException {
		
		String sqlInsert = "INSERT INTO PredavanjeUsmjerenje (sifPredavanje, sifUsmjerenje) " + "VALUES (?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			Statement stmtPom1 = conn.createStatement();
			stmtPom1.execute("SET FOREIGN_KEY_CHECKS=0");
			stmtPom1.close();
			
			stmt.setInt(1, predavanjeUsmjerenje.getSifPredavanje());
			stmt.setInt(2, predavanjeUsmjerenje.getSifUsmjerenje());
			int affected = stmt.executeUpdate();
			
			/**
			 * @dino
			 * Posto se u bazi sifIzborni automatski inkrementira unutar baze, treba ovaj dio.
			 * Pogledati Lynda tutorial insertsql, pa ce biti jasnije
			 */
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				predavanjeUsmjerenje.setSifPredavanjeUsmjerenje(newKey);
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
