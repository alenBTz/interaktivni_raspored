package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.PredmetUsmjerenje_;

public class PredmetUsmjerenje {

	public static ArrayList<PredmetUsmjerenje_> predmetUsmjerenjeLista = new ArrayList<PredmetUsmjerenje_>();

	public static ArrayList<PredmetUsmjerenje_> getPredmetList(ResultSet rs) throws SQLException{
		
		while (rs.next()) {
			PredmetUsmjerenje_ predmetUsmjerenje = new PredmetUsmjerenje_();	
			predmetUsmjerenje.setSifPredmetUsmjerenje(rs.getInt("sifPredmetUsmjerenje"));
			predmetUsmjerenje.setSifPredmet(rs.getInt("sifPredmet"));
			predmetUsmjerenje.setSifUsmjerenje(rs.getInt("sifUsmjerenje"));

			predmetUsmjerenjeLista.add(predmetUsmjerenje);
			
		}
		
		return predmetUsmjerenjeLista;
		
	}
	
}
