package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.Korisnik_;


public class Korisnik {

public static ArrayList<Korisnik_> korisnikLista = new ArrayList<Korisnik_>();
	
	public static ArrayList<Korisnik_> getKorisnikList(ResultSet rs) throws SQLException{
		
		while(rs.next()){
			Korisnik_ korisnik = new Korisnik_();
			korisnik.setSifKorisnik(rs.getInt("sifKorisnik"));
			korisnik.setKorisnickoIme(rs.getString("korisnickoIme"));
			korisnik.setUloga(rs.getString("uloga"));
			korisnik.setIme(rs.getString("ime"));
			korisnik.setPrezime(rs.getString("prezime"));
			korisnik.setSifra(rs.getString("sifra"));
			korisnikLista.add(korisnik);
		}
		return korisnikLista;
	}
	
}
