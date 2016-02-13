package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.Nastavnik_;

/**
 * @author dino
 * Klasa preko cijih metoda kupimo ili upisujemo podatke u bazu podataka.
 */
public class Nastavnik {

	public static ArrayList<Nastavnik_> nastavnikLista = new ArrayList<Nastavnik_>();
	//private static Nastavnik_ nastavnik = new Nastavnik_();	

	/**
	 * @author dino
	 * Metod getNastavnikList koji iz baze podataka uzima sve n-torke iz tabele nastavnik,
	 * i unosi ih u vektor Nastavnik_.
	 */
	public static ArrayList<Nastavnik_> getNastavnikList(ResultSet rs) throws SQLException{
		
		while (rs.next()) {
			Nastavnik_ nastavnik = new Nastavnik_();	
			nastavnik.setSifNastavnik(rs.getInt("sifNastavnik"));
			nastavnik.setImeNastavnik(rs.getString("imeNastavnik"));
			nastavnik.setPrezNastavnik(rs.getString("prezNastavnik"));
			nastavnik.setVrstaNastavnik(rs.getInt("vrstaNastavnik"));
			nastavnik.setUsername(rs.getString("username"));
			nastavnik.setPassword(rs.getString("password"));
			nastavnikLista.add(nastavnik);
			
		}
		
		return nastavnikLista;
		
	}
	
}
