package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.PredavanjeUsmjerenje_;


public class PredavanjeUsmjerenje {

public static ArrayList<PredavanjeUsmjerenje_> predavanjeUsmjerenjeLista = new ArrayList<PredavanjeUsmjerenje_>();
	
	public static ArrayList<PredavanjeUsmjerenje_> getPredavanjeUsmjerenjeList(ResultSet rs) throws SQLException{
		
		while(rs.next()){
			PredavanjeUsmjerenje_ predavanjeUsmjerenje = new PredavanjeUsmjerenje_();
			predavanjeUsmjerenje.setSifPredavanjeUsmjerenje(rs.getInt("sifPredavanjeUsmjerenje"));
			predavanjeUsmjerenje.setSifPredavanje(rs.getInt("sifPredavanje"));
			predavanjeUsmjerenje.setSifUsmjerenje(rs.getInt("sifUsmjerenje"));
			
			predavanjeUsmjerenjeLista.add(predavanjeUsmjerenje);
		}
		return predavanjeUsmjerenjeLista;
	}
}
