package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.PredmetNastavnikTipNastave_;


public class PredmetNastavnikTipNastave {

	
	public static ArrayList<PredmetNastavnikTipNastave_> predmetNastavnikTipNastaveLista = new ArrayList<PredmetNastavnikTipNastave_>();

	public static ArrayList<PredmetNastavnikTipNastave_> getPredmetNastavnikTipNastaveList(ResultSet rs) throws SQLException{
		predmetNastavnikTipNastaveLista.clear();
		while(rs.next()){
			PredmetNastavnikTipNastave_ predmetNastavnikTipNastave = new PredmetNastavnikTipNastave_();
			predmetNastavnikTipNastave.setSifPredmetNastavnikTipNastave(rs.getInt("sifPredmetNastavnikTipNastave"));
			predmetNastavnikTipNastave.setSifNastavnik(rs.getInt("sifNastavnik"));
			predmetNastavnikTipNastave.setSifPredmet(rs.getInt("sifPredmet"));
			predmetNastavnikTipNastave.setSifTipNastave(rs.getInt("sifTipNastave"));

			predmetNastavnikTipNastaveLista.add(predmetNastavnikTipNastave);
		}
		return predmetNastavnikTipNastaveLista;
	}
	
	
}
