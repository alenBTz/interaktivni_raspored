package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.Zgrada_;


public class Zgrada {
	
	
	public static ArrayList<Zgrada_> zgradaLista = new ArrayList<Zgrada_>();

	/**
	 * @author dino
	 * Metod getNastavnikList koji iz baze podataka uzima sve n-torke iz tabele nastavnik,
	 * i unosi ih u vektor Nastavnik_.
	 */
	public static ArrayList<Zgrada_> getZgradaList(ResultSet rs) throws SQLException{
		
		while (rs.next()) {
			Zgrada_ zgrada = new Zgrada_();	
			zgrada.setSifZgrada(rs.getInt("sifZgrada"));
			zgrada.setNazZgrada(rs.getString("nazZgrada"));
			zgrada.setKratZgrada(rs.getString("kratZgrada"));
			zgradaLista.add(zgrada);
			
		}
		
		return zgradaLista;
		
	}
}
