package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.Semestar_;

public class Semestar {

	public static ArrayList<Semestar_> semestarLista = new ArrayList<Semestar_>();

	public static ArrayList<Semestar_> getSemestarList(ResultSet rs) throws SQLException{
		
		while (rs.next()) {
			Semestar_ semestar = new Semestar_();	
			semestar.setSifSemestar(rs.getInt("sifSemestar"));
			semestar.setNazSemestar(rs.getString("nazSemestar"));			

			semestarLista.add(semestar);
			
		}
		
		return semestarLista;
		
	}
	
	
	
}
