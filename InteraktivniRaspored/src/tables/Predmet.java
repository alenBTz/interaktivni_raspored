package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.Predmet_;


public class Predmet {

	public static ArrayList<Predmet_> predmetLista = new ArrayList<Predmet_>();

	public static ArrayList<Predmet_> getPredmetList(ResultSet rs) throws SQLException{
		
		while (rs.next()) {
			Predmet_ predmet = new Predmet_();	
			predmet.setSifPredmet(rs.getInt("sifPredmet"));
			predmet.setNazPredmet(rs.getString("nazPredmet"));
			predmet.setKratPredmet(rs.getString("kratPredmet"));
			predmet.setIzborni(rs.getInt("izborni"));
			predmet.setTipNastave(rs.getString("tipNastave"));
			predmet.setSifNastavnik(rs.getInt("sifNastavnik"));
			predmet.setSifSemestar(rs.getInt("sifSemestar"));
			

			predmetLista.add(predmet);
			
		}
		
		return predmetLista;
		
	}
	
	
}
