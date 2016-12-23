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
	public static Predmet_ getPredmetByName(String imePredmeta) throws SQLException {
		String sqlGetByName = "SELECT * FROM predmet WHERE nazPredmet = '" + imePredmeta + "'";
		System.out.println(sqlGetByName);
		Predmet_ predmet = new Predmet_();
		try{
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sqlGetByName);
				while (rs.next())
				{
					predmet.setNazPredmet(rs.getString("nazPredmet"));
					predmet.setSifPredmet(rs.getInt("sifPredmet"));
					predmet.setKratPredmet(rs.getString("kratPredmet"));
					predmet.setSifSemestar(rs.getInt("sifSemestar"));
				}
				
				return predmet;
				
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			System.out.println("U bazi ne postoji predmet sa imenom :"+ imePredmeta);
		}
		return predmet;
	}
	
	public static Predmet_ getPredmetBySifPredavanje(int sifraPredavanje) throws SQLException {
		String sqlGetByName = "SELECT predmet.* FROM predmet INNER JOIN predmetpredavanjetipnastave ON predmet.sifPredmet = predmetpredavanjetipnastave.sifPredmet WHERE predmetpredavanjetipnastave.sifPredavanje LIKE '" + sifraPredavanje + "'";
		Predmet_ predmet = new Predmet_();
		try{
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sqlGetByName);
				while (rs.next())
				{
					predmet.setNazPredmet(rs.getString("nazPredmet"));
					predmet.setSifPredmet(rs.getInt("sifPredmet"));
					predmet.setKratPredmet(rs.getString("kratPredmet"));
					predmet.setSifSemestar(rs.getInt("sifSemestar"));
				}
				
				return predmet;
				
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			System.out.println("U bazi ne postoji predmet sa sifrom Predmeta : "+ sifraPredavanje);
		}
		return predmet;
	}
	
	public static Predmet_ getPredmetBySifraAndSemestar(int sifPredmeta, int sifSemestra) throws SQLException {
		String sqlGetByName = "SELECT * FROM predmet WHERE sifPredmet = '" + sifPredmeta + "' AND sifSemestar = '" + sifSemestra + "'";
		Predmet_ predmet = new Predmet_();
		try{
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sqlGetByName);
				predmet.setNazPredmet(rs.getString("nazPredmet"));
				predmet.setSifPredmet(rs.getInt("sifPredmet"));
				predmet.setKratPredmet(rs.getString("kratPredmet"));
				predmet.setSifSemestar(rs.getInt("sifSemestar"));
				return predmet;
				
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			System.out.println("U bazi ne postoji predmet sa sifrom :"+ sifPredmeta +"ili sa sifrom semestra:"+sifSemestra	);
		}
		return predmet;
	}
	
	/**
	 * @author dino
	 * Metod za ubacivanje jednog predmeta u tabelu.
	 * @return 
	 */
	public static boolean insertPredmet(Predmet_ predmet) throws SQLException {
		
		String sqlInsert = "INSERT INTO predmet (nazPredmet, kratPredmet, sifSemestar) " + "VALUES (?, ?, ?)";

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
			stmt.setInt(3, predmet.getSifSemestar());
			
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
	
	public static Predmet_ getPredmetBySifra(int sifraPredmeta) throws SQLException {
		String sqlGetByName = "SELECT * FROM predmet WHERE sifPredmet = '" + sifraPredmeta + "'";
		System.out.println(sqlGetByName);
		Predmet_ predmet = new Predmet_();
		try{
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sqlGetByName);
				while (rs.next())
				{
					predmet.setNazPredmet(rs.getString("nazPredmet"));
					predmet.setSifPredmet(rs.getInt("sifPredmet"));
					predmet.setKratPredmet(rs.getString("kratPredmet"));
					predmet.setSifSemestar(rs.getInt("sifSemestar"));
				}
				
				return predmet;
				
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			System.out.println("U bazi ne postoji predmet sa sifrom : "+ sifraPredmeta);
		}
		return predmet;
	}
}
