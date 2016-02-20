package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.Grupa_;


public class Grupa {

	
public static ArrayList<Grupa_> grupaLista = new ArrayList<Grupa_>();
	
	public static ArrayList<Grupa_> getGrupaList(ResultSet rs) throws SQLException{
		
		while(rs.next()){
			Grupa_ grupa = new Grupa_();
			grupa.setSifGrupa(rs.getInt("sifGrupa"));
			grupa.setNazivGrupa(rs.getString("nazivGrupa"));
			
			grupaLista.add(grupa);
		}
		return grupaLista;
	}
	
}
