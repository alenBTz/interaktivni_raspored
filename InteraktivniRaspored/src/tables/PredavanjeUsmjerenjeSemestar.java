package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.PredavanjeUsmjerenjeSemestar_;


public class PredavanjeUsmjerenjeSemestar 
{

public static ArrayList<PredavanjeUsmjerenjeSemestar_> predavanjeUsmjerenjeSemestarLista = new ArrayList<PredavanjeUsmjerenjeSemestar_>();
	
	public static ArrayList<PredavanjeUsmjerenjeSemestar_> getPredavanjeUsmjerenjeSemestarList(ResultSet rs) throws SQLException
	{
		predavanjeUsmjerenjeSemestarLista.clear();
		
		while(rs.next())
		{
			PredavanjeUsmjerenjeSemestar_ predavanjeUsmjerenje = new PredavanjeUsmjerenjeSemestar_();
			predavanjeUsmjerenje.setSifPredavanjeUsmjerenjeSemestar(rs.getInt("sifPredavanjeUsmjerenjeSemestar"));
			predavanjeUsmjerenje.setSifPredavanje(rs.getInt("sifPredavanje"));
			predavanjeUsmjerenje.setSifUsmjerenje(rs.getInt("sifUsmjerenje"));
			predavanjeUsmjerenje.setSifSemestar(rs.getInt("sifSemestar"));
			
			predavanjeUsmjerenjeSemestarLista.add(predavanjeUsmjerenje);
		}
		return predavanjeUsmjerenjeSemestarLista;
	}
}
