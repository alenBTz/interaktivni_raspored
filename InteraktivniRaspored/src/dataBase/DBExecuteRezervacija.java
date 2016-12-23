package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		
		
		if(!doesExist(rezervacija))
		{
			String sqlInsert = "INSERT INTO rezervacija (datumRezervacija, tipRezervacije, satRezervacije"
					+ ", satRezervacijeKraj, sifPredmet, sifSala, sifNastavnik) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
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
		}
		else
		{
			System.out.println("Vec ima kreirana rezervacija u tom terminu");
		}
		
		return true;
	
	}

	public static void updateRezervacija(Rezervacija_ editovanaRezervacija) {
		String sqlUpdate = "UPDATE rezervacija SET datumRezervacija = '" + editovanaRezervacija.getDatumRezervacija() +"',tipRezervacije = '" + editovanaRezervacija.getTipRezervacije() + "',satRezervacije = '" + editovanaRezervacija.getSatRezervacije() + "', satRezervacijeKraj = '" + editovanaRezervacija.getSatRezervacijeKraj() + "',sifPredmet = '" + editovanaRezervacija.getSifPredmet() + "',sifSala = '" + editovanaRezervacija.getSifSala() +"',sifNastavnik = '" + editovanaRezervacija.getSifNastavnik() + "'WHERE sifRezervacija = '" + editovanaRezervacija.getSifRezervacija() +"'";
		//String sqlUpdate = "UPDATE rezervacija SET (datumRezervacija, tipRezervacije, satRezervacije, satRezervacijeKraj, sifPredmet, sifSala, sifNastavnik) " + "VALUES (?, ?, ?, ?, ?, ?, ?) WHERE sifRezervacija = '" + editovanaRezervacija.getSifRezervacija() + "'";
		System.out.println(sqlUpdate);
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				//ResultSet rs = stmt.executeQuery(SQL);
				) 
		{
			
			/*stmt.setDate(1, editovanaRezervacija.getDatumRezervacija());
			stmt.setString(2, editovanaRezervacija.getTipRezervacije());
			stmt.setTime(3, editovanaRezervacija.getSatRezervacije());
			stmt.setTime(4, editovanaRezervacija.getSatRezervacijeKraj());
			stmt.setInt(5, editovanaRezervacija.getSifPredmet());
			stmt.setInt(6, editovanaRezervacija.getSifSala());
			stmt.setInt(7, editovanaRezervacija.getSifNastavnik());*/
			
			int affected = stmt.executeUpdate();
			System.out.println("affected:" + affected);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			
		} 
		finally {
			if(keys != null)
				try {
					keys.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		

	}
	
	public static Boolean doesExist(Rezervacija_ rezervacijaZaDodavanje)
	{
		try
		{
			DBExecuteRezervacija.getRezervacije();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		ArrayList<Rezervacija_> rezervacije = Rezervacija.rezervacijaLista;
		
		for (int i=0; i<rezervacije.size(); i++)
		{
			//da li u bazi vec postoji rezervacija sa istim datumom
			Boolean postojiRezSaDatumom = rezervacije.get(i).getDatumRezervacija().equals(rezervacijaZaDodavanje.getDatumRezervacija());
			
			//da li vrijeme pocetka rezervacije vece od vremena pocetka rezervacije u bazi
			Boolean pocetakRezUlaziUTerminDrugeRez = rezervacijaZaDodavanje.getSatRezervacije().after(rezervacije.get(i).getSatRezervacije());
			
			//da li vrijeme kraja rezervacije vece od vremena kraja rezervacije u bazi
			Boolean krajRezNeUlaziUTerminDrugeRez = rezervacijaZaDodavanje.getSatRezervacijeKraj().after(rezervacije.get(i).getSatRezervacijeKraj());
			
			// da li je vrijeme pocetka rezervacije jednako vremenu pocetka rezervacije u bazi
			Boolean pocetakRezIstiKaoPocDrugeRez = rezervacijaZaDodavanje.getSatRezervacije().equals(rezervacije.get(i).getSatRezervacije());
			
			// da li je vrijeme kraja rezervacije jednako vremenu kraja rezervacije u bazi
			Boolean krajRezIstiKaoKrajDrugeRez = rezervacijaZaDodavanje.getSatRezervacijeKraj().equals(rezervacije.get(i).getSatRezervacijeKraj());
			
			if(postojiRezSaDatumom 
				&& (pocetakRezUlaziUTerminDrugeRez && !krajRezNeUlaziUTerminDrugeRez || pocetakRezIstiKaoPocDrugeRez || krajRezIstiKaoKrajDrugeRez))
			{
				return true;
			}
		}
		
		
		return false;
	}
}
