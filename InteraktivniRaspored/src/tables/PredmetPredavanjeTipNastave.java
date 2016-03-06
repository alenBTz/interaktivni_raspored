package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.PredmetPredavanjeTipNastave_;

public class PredmetPredavanjeTipNastave {

	public static ArrayList<PredmetPredavanjeTipNastave_> predmetPredavanjeTipNastaveLista = new ArrayList<PredmetPredavanjeTipNastave_>();

	public static ArrayList<PredmetPredavanjeTipNastave_> getPredmetPredavanjeTipNastaveList(ResultSet rs) throws SQLException{

		while(rs.next()){
			PredmetPredavanjeTipNastave_ pptn = new PredmetPredavanjeTipNastave_();
			pptn.setSifPredmetPredavanjeTipNastave(rs.getInt("sifPredmetPredavanjeTipNastave"));
			pptn.setSifPredmet(rs.getInt("sifPredmet"));
			pptn.setSifPredavanje(rs.getInt("sifPredavanje"));
			pptn.setSifTipNastave(rs.getInt("sifTipNastave"));

			predmetPredavanjeTipNastaveLista.add(pptn);
		}
		return predmetPredavanjeTipNastaveLista;
	}
	
}
