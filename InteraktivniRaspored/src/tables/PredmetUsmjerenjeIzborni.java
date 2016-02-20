package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.PredmetUsmjerenjeIzborni_;

public class PredmetUsmjerenjeIzborni {

	public static ArrayList<PredmetUsmjerenjeIzborni_> predmetUsmjerenjeIzborniLista = new ArrayList<PredmetUsmjerenjeIzborni_>();

	public static ArrayList<PredmetUsmjerenjeIzborni_> getPredmetUsmjerenjeIzborniList(ResultSet rs) throws SQLException{
		
		while (rs.next()) {
			PredmetUsmjerenjeIzborni_ predmetUsmjerenjeIzborni = new PredmetUsmjerenjeIzborni_();	
			predmetUsmjerenjeIzborni.setSifPredmetUsmjerenje(rs.getInt("sifPredmetUsmjerenje"));
			predmetUsmjerenjeIzborni.setSifPredmet(rs.getInt("sifPredmet"));
			predmetUsmjerenjeIzborni.setSifUsmjerenje(rs.getInt("sifUsmjerenje"));

			predmetUsmjerenjeIzborniLista.add(predmetUsmjerenjeIzborni);
			
		}
		
		return predmetUsmjerenjeIzborniLista;
		
	}
	
}
