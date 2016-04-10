package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.Usmjerenje_;

public class Usmjerenje {
	
	public static ArrayList<Usmjerenje_> usmjerenjeLista = new ArrayList<Usmjerenje_>();
	public static String nazPrveKolone = "";
	/**
	 * @author dino
	 * Metod getNastavnikList koji iz baze podataka uzima sve n-torke iz tabele nastavnik,
	 * i unosi ih u vektor Nastavnik_.
	 */
	public static ArrayList<Usmjerenje_> getUsmjerenjeList(ResultSet rs) throws SQLException{
		
		while (rs.next()) {
			Usmjerenje_ usmjerenje = new Usmjerenje_();	
			usmjerenje.setSifUsmjerenje(rs.getInt("sifUsmjerenje"));
			usmjerenje.setNazUsmjerenje(rs.getString("nazUsmjerenje"));
			usmjerenje.setKratUsmjerenje(rs.getString("kratUsmjerenje"));
			usmjerenjeLista.add(usmjerenje);
			
		}
		
		return usmjerenjeLista;
		
	}

}