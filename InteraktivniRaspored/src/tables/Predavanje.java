package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.Predavanje_;

public class Predavanje {

public static ArrayList<Predavanje_> predavanjeLista = new ArrayList<Predavanje_>();
	
	public static ArrayList<Predavanje_> getPredavanjeList(ResultSet rs) throws SQLException{
		
		while(rs.next()){
			Predavanje_ predavanje = new Predavanje_();
			predavanje.setSifPredavanje(rs.getInt("sifPredavanje"));
			predavanje.setDanPredavanje(rs.getString("danPredavanje"));
			predavanje.setPocetakPredavanje(rs.getTime("pocetakPredavanje"));
			predavanje.setKrajPredavanje(rs.getTime("krajPredavanje"));
			predavanje.setSifSala(rs.getInt("sifSala"));
			predavanjeLista.add(predavanje);
		}
		return predavanjeLista;
	}
	
	
}
