package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.PredmetGrupaTipNastave_;

public class PredmetGrupaTipNastave {

	public static ArrayList<PredmetGrupaTipNastave_> predmetGrupaTipNastaveLista = new ArrayList<PredmetGrupaTipNastave_>();

	public static ArrayList<PredmetGrupaTipNastave_> getPredmetGrupaTipNastaveList(ResultSet rs) throws SQLException{

		while(rs.next()){
			PredmetGrupaTipNastave_ predmetGrupaTipNastave = new PredmetGrupaTipNastave_();
			predmetGrupaTipNastave.setSifPredmetGrupaTipNastave(rs.getInt("sifPredmetGrupaTipNastave"));
			predmetGrupaTipNastave.setSifPredmet(rs.getInt("sifPredmet"));
			predmetGrupaTipNastave.setSifGrupa(rs.getInt("sifGrupa"));
			predmetGrupaTipNastave.setSifTipNastave(rs.getInt("sifTipNastave"));

			predmetGrupaTipNastaveLista.add(predmetGrupaTipNastave);
		}
		return predmetGrupaTipNastaveLista;
	}
}
