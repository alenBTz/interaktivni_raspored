package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.Rezervacija_;

public class Rezervacija {

	public static ArrayList<Rezervacija_> rezervacijaLista = new ArrayList<Rezervacija_>();

	public static ArrayList<Rezervacija_> getRezervacijaList(ResultSet rs) throws SQLException{
		
		while(rs.next()){
			Rezervacija_ rezervacija = new Rezervacija_();
			rezervacija.setSifRezervacija(rs.getInt("sifRezervacija"));
			rezervacija.setDatumRezervacija(rs.getDate("datumRezervacija"));
			rezervacija.setTipRezervacije(rs.getString("tipRezervacije"));
			rezervacija.setSatRezervacije(rs.getTime("satRezervacije"));
			rezervacija.setSatRezervacijeKraj(rs.getTime("satRezervacijeKraj"));
			rezervacija.setSifPredmet(rs.getInt("sifPredmet"));
			rezervacija.setSifSala(rs.getInt("sifSala"));
			rezervacija.setSifNastavnik(rs.getInt("sifNastavnik"));
			rezervacijaLista.add(rezervacija);
		}
		return rezervacijaLista;
	}
}
